package com.nzkj.screen.memory;



import com.nzkj.screen.Entity.DTO.AreaDto;
import com.nzkj.screen.Entity.DTO.GunMonitorDto;

import com.nzkj.screen.Entity.DTO.PileDto;
import com.nzkj.screen.Entity.DTO.StationDto;
import com.nzkj.screen.mapper.pile.config.IGunMapper;
import com.nzkj.screen.mapper.pile.config.IPileMapper;
import com.nzkj.screen.mapper.pile.config.IStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@SuppressWarnings({"unchecked","rawtypes"})
public class LocalMemoryData extends RedisKeyBuilder implements IMemoryData{
	private  static Map<Long, PileDto> pileMap = new ConcurrentHashMap<>();
	private  static Map<Long, StationDto> stationMap = new ConcurrentHashMap<>();
	private  static Map<Long, AreaDto> areaMap = new ConcurrentHashMap<>();
	private  static Map<String, Long> pileIdMap = new ConcurrentHashMap<>();
	//pileId -> gun在redis的key
	private  static Map<Long, Set<String>> pileGunMap = new ConcurrentHashMap<>();
	private  static Map<Long, Set<Long>> sellerStationMap = new ConcurrentHashMap<>();
	private  static Map<Long, Set<Long>> stationPileMap = new ConcurrentHashMap<>();
	private  static Map<String, StationDto> stationNoMap = new ConcurrentHashMap<>();
	private  static Map<String, String> localGunKeyMap = new ConcurrentHashMap<>();
	private static Map<String,String> gunConnectorIDMap = new ConcurrentHashMap<>();
	private static Map<Long,String> gunIDMap = new ConcurrentHashMap<>();
//	private static Map<Long, AlarmTypeDto> alarmTypeMap = new ConcurrentHashMap<>();
//	//车队会员跟站点绑定的合约价
//	private static Map<String,List<ContractBillRuleDto>> memberContractMap = new ConcurrentHashMap<>();
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisJsonTemplate;

//	@Resource
//	private StringRedisTemplate redisTemplate;

	@Autowired
	private IStationMapper stationMapper;

	@Autowired
	private IPileMapper pileMapper;

	@Autowired
	private IGunMapper gunMapper;


//	@Autowired
//	private PileHeartBeat pileHeartBeat;
	private final static byte[] RANDOM_KEY = "xxxxx".getBytes();


	@PostConstruct
	public void initData(){
		//在spring容器初始化后自动调用一次  初始化这些数组的数据
		loadData(1l);
	}

	@Override
	public List<StationDto> getStationBySeller(Long sellerId) {
		Set<Long> stationIds = sellerStationMap.get(sellerId);
		if(CollectionUtil.isEmpty(stationIds)) {
			return Collections.emptyList();
		}
		int size = stationIds.size();
		List<StationDto> stations = new ArrayList<>(size);
		for(Long id : stationIds) {
			stations.add(getStation(id));
		}
		return stations;
	}

	@Override
	public StationDto getStation(Long stationId) {
		return stationMap.get(stationId);
	}

	private void updateStation(Set<Long> stationIds){
		for(Long stationId :stationIds){

		}
	}

	@Override
	public List<GunMonitorDto> getGunBySeller(Long sellerId) {
		Set<Long> stations = sellerStationMap.get(sellerId);
		if(CollectionUtil.isEmpty(stations)) {
			stations = stationMapper.findIdBySeller(sellerId);
			if(CollectionUtil.isEmpty(stations)){
				return Collections.emptyList();
			}else{
				loadData(sellerId);
			}
			sellerStationMap.put(sellerId,stations);
		}
		Set<Long> piles = new HashSet<>(), stationPiles = null;
		for(Long stationId: stations) {
			stationPiles = stationPileMap.get(stationId);
			if(CollectionUtil.isNotEmpty(stationPiles)) {
				piles.addAll(stationPiles);
			}else{
				stationPiles = pileMapper.findPileByStationId(stationId);
				if(CollectionUtil.isNotEmpty(stationPiles)){
					stationPileMap.put(stationId,stationPiles);
					piles.addAll(stationPiles);
				}
			}
		}
		if(CollectionUtil.isEmpty(piles)) {
			return Collections.emptyList();
		}
		final List<String> keys = new ArrayList<>();
		
		Set<String> gunKeys = null;
		for(Long pileId : piles) {
			gunKeys = pileGunMap.get(pileId);
			if(CollectionUtil.isNotEmpty(gunKeys)) {
				keys.addAll(gunKeys);
			}
		}
		if(keys.size() == 0) {
			return Collections.emptyList();
		}
		return redisJsonTemplate.executePipelined(new RedisCallback<GunMonitorDto>() {
			@Override
			public GunMonitorDto doInRedis(RedisConnection conn) throws DataAccessException {
				conn.openPipeline();
				for(int i = 0, size = keys.size(); i < size; i++) {
					conn.get(keys.get(i).getBytes());
				}
				return null;
			}
		},redisJsonTemplate.getValueSerializer());
	}

	@Override
	public List<PileDto> getPileByStation(Long stationId) {
		Set<Long> pileIds = stationPileMap.get(stationId);
		if(CollectionUtil.isEmpty(pileIds)) {
			return Collections.emptyList();
		}
		List<PileDto> piles = new ArrayList<>(pileIds.size());
		for(Long pileId : pileIds) {
			piles.add(getPile(pileId));
		}
		return piles;
	}

	@Override
	public PileDto getPile(Long pileId) {
		return pileMap.get(pileId);
	}

	@Override
	public List<GunMonitorDto> getGunByPile(Long pileId) {
		Set<String> gunKeys = pileGunMap.get(pileId);
		if(CollectionUtil.isEmpty(gunKeys)) {
			return Collections.emptyList();
		}
		int size = gunKeys.size();
		List<GunMonitorDto> guns = new ArrayList<>(size);
		ValueOperations<String, GunMonitorDto> ops = redisJsonTemplate.opsForValue();
		GunMonitorDto gun = null;
		for(String gunKey : gunKeys) {
			gun = ops.get(gunKey);
			if(gun != null) {
				guns.add(gun);
			}
		}
		return guns;
	}


	private void loadData(Long seller){
		Set<Long> stations = new HashSet<>();
		stations = stationMapper.findIdBySeller(seller);
		if(CollectionUtil.isNotEmpty(stations)){
			sellerStationMap.put(seller,stations);
		}else {
			return;
		}
		Set<Long> piles = new HashSet<>(), stationPiles = null,guns = null;

		String key = null;
		for(Long stationId: stations) {
			stationPiles = pileMapper.findPileByStationId(stationId);
			if(CollectionUtil.isNotEmpty(stationPiles)){
				stationPileMap.put(stationId,stationPiles);
				piles.addAll(stationPiles);
				for (Long pileId : stationPiles){
					//依靠pileId去查gun  mysql访问量会很大 先试试别的方法
					Set<String> gunKeys= new HashSet<>();
					guns = gunMapper.findGunNoByPileId(pileId);
					for(Long gunNo : guns){
						key = buildKey(RedisDataEnum.GUN, stationId, pileId, gunNo);
						gunKeys.add(key);
					}
					pileGunMap.put(pileId,gunKeys);
				}
			}
		}
		//先从数据库查出所有 gunId 并且对应的pileId stationId  一些废弃的gun也可能被添加进去？？
	}

	@Override
	public List<GunMonitorDto> getGunByStation(Long stationId) {
		Set<Long> piles = stationPileMap.get(stationId);
		if(CollectionUtil.isEmpty(piles)) {
			return Collections.emptyList();
		}
		final List<String> keys = new ArrayList<>();

		Set<String> gunKeys = null;
		for(Long pileId : piles) {
			gunKeys = pileGunMap.get(pileId);
			if(CollectionUtil.isNotEmpty(gunKeys)) {
				keys.addAll(gunKeys);
			}
		}
		if(keys.size() == 0) {
			return Collections.emptyList();
		}
		return redisJsonTemplate.executePipelined((RedisCallback<GunMonitorDto>) conn -> {
			conn.openPipeline();
			for(int i = 0, size = keys.size(); i < size; i++) {
				conn.get(keys.get(i).getBytes());
			}
			return null;
		},redisJsonTemplate.getValueSerializer());

//		return redisJsonTemplate.executePipelined(new SessionCallback<GunMonitorDto>() {
//
//			@Override
//			public GunMonitorDto execute(RedisOperations operations) throws DataAccessException {
//				for(int i = 0, size = keys.size(); i < size; i++) {
//					operations.opsForValue().get(keys.get(i));
//				}
//				return null;
//			}
//		});
	}


}