package com.nzkj.screen.memory;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.DTO.*;

import com.nzkj.screen.Entity.Station;
import com.nzkj.screen.mapper.pile.config.IGunMapper;
import com.nzkj.screen.mapper.pile.config.IPileMapper;
import com.nzkj.screen.mapper.pile.config.IStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings({"unchecked","rawtypes"})
public class LocalMemoryData extends RedisKeyBuilder implements IMemoryData{
	private  static Map<Long, PileDto> pileMap = new ConcurrentHashMap<>();
	private  static Map<Long, StationDto> stationMap = new ConcurrentHashMap<>();
	private  static Map<Long, AreaDto> areaMap = new ConcurrentHashMap<>();
	private  static Map<String, Long> pileIdMap = new ConcurrentHashMap<>();
	//pileId -> gun在redis的key
	private  static Map<Long, Set<String>> pileGunMap = new ConcurrentHashMap<>();
	private static Map<Long,Set<String>> stationGunMap = new ConcurrentHashMap<>();
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

	@Autowired
	public RedisUtils redisTemp;

	@Autowired
	public StringRedisTemplate StringredisTemplate;

//	@Resource
//	private StringRedisTemplate redisTemplate;

	@Autowired
	private IStationMapper stationMapper;

	@Autowired
	private IPileMapper pileMapper;

	@Autowired
	private IGunMapper gunMapper;

	@Value("${expireTime}")
	private int expireTime;

	//商家id为固定值
	@Value("${sellerId}")
	private long sellerId;


//	@Autowired
//	private PileHeartBeat pileHeartBeat;
	private final static byte[] RANDOM_KEY = "xxxxx".getBytes();


	@PostConstruct
	public void initData(){
		//在spring容器初始化后自动调用一次  初始化这些数组的数据
		loadData(sellerId);
	}

	@Override
	public List<StationDto> getStationBySeller(Long sellerId) {
		String key = "StationUpdateParameter"+sellerId;
		if(redisJsonTemplate.opsForValue().get(key)== null){
			updateStation(sellerId);
			redisJsonTemplate.opsForValue().set(key,"当这个key失效，就更新一次sellerId下的station",expireTime*3, TimeUnit.SECONDS);
		}
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

	private void updateStation(Long sellerId){
		List<Station> stations = stationMapper.findBySeller(sellerId);
		Set<Long> stationIds = null;
		for(Station s : stations){
			StationDto dto = new StationDto();
			dto.setId(s.getId());
			dto.setAreaid(s.getAreaId());
			dto.setConfigTypeEnum(s.getConfigType());
			dto.setAbbreviationName(s.getAbbreviationName());
			dto.setClosetimeAm(s.getCloseTimeAm());
			dto.setCloseTimePm(s.getCloseTimePm());
			dto.setImagePath(s.getImagePath());
			dto.setImg1(s.getImg1());
			dto.setImg2(s.getImg2());
			dto.setImg3(s.getImg3());
			dto.setFastSlow(StationFastSlowEnum.getEnumByName(s.getFastSlow()));
			dto.setLongitude(s.getLongitude());
			dto.setLatitude(s.getLatitude());
			dto.setScore(s.getScore());
			stationMap.put(s.getId(),dto);
			stationIds.add(s.getId());
		}
		sellerStationMap.put(sellerId,stationIds);
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
//		Set<Long> piles = new HashSet<>(), stationPiles = null;
//		for(Long stationId: stations) {
//			stationPiles = stationPileMap.get(stationId);
//			if(CollectionUtil.isNotEmpty(stationPiles)) {
//				piles.addAll(stationPiles);
//			}else{
//				stationPiles = pileMapper.findPileByStationId(stationId);
//				if(CollectionUtil.isNotEmpty(stationPiles)){
//					stationPileMap.put(stationId,stationPiles);
//					piles.addAll(stationPiles);
//				}
//			}
//		}
//		if(CollectionUtil.isEmpty(piles)) {
//			return Collections.emptyList();
//		}
		final List<String> keys = new ArrayList<>();
		
		Set<String> gunKeys = null;
		for(Long stationId : stations) {
			gunKeys = stationGunMap.get(stationId);
			if(CollectionUtil.isNotEmpty(gunKeys)) {
				keys.addAll(gunKeys);
			}
		}
		if(keys.size() == 0) {
			return Collections.emptyList();
		}
		List<GunMonitorDto> gunMonitorDtoList = new ArrayList<GunMonitorDto>();
		try {
			List<Object> results = StringredisTemplate.executePipelined(new RedisCallback<Object>() {
				@Override
				public String doInRedis(RedisConnection conn) throws DataAccessException {
					conn.openPipeline();
					for (int i = 0; i < keys.size(); i++) {
						String key = keys.get(i).toString().replaceAll(RedisDataEnum.GUNKEYSCACHE.getPrefix(), RedisDataEnum.GUNMONITOR.getPrefix());
						conn.get(key.getBytes());
					}
					return null;
				}
			});
			if (results != null && !results.isEmpty()) {
				for (int j = 0; j < results.size(); j++) {
					String monitorvalue = (String) results.get(j);
					if (monitorvalue != null) {
						JSONObject o = (JSONObject) JSONObject.parse(monitorvalue);
						o.remove("@type");
						GunMonitorDto monitorDto = o.toJavaObject(GunMonitorDto.class);
//						GunMonitorDto monitorDto = (GunMonitorDto) JSONObject.parse(monitorvalue);
						gunMonitorDtoList.add(monitorDto);
					}
				}
			}
			return CollectionUtil.newArrayList(gunMonitorDtoList);
		}catch ( Exception e){
			e.printStackTrace();
//			log.error("调用配置服务获【查询枪gunKeys】失败，返回空,事件编码：l020100027 站点ID【{}】", stationId);
			return CollectionUtil.newArrayList(gunMonitorDtoList);
		}
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
			}

			//初始化站点下所有的gunKey
			String gunKeysCachekey = redisTemp.buildKey(RedisDataEnum.GUNKEYSCACHE, stationId);
			String value = StringredisTemplate.opsForValue().get(gunKeysCachekey);
			if(StrUtil.isEmpty(value)){
				continue;
			}
			JSONArray array = JSONArray.parseArray(value);
			Set<String> gunKeys= new HashSet<>();
			for (int i = 0; i < array.size(); i++) {
				String keys = array.get(i).toString().replaceAll(RedisDataEnum.GUNKEYSCACHE.getPrefix(), RedisDataEnum.GUNMONITOR.getPrefix());
				gunKeys.add(keys);
			}
			stationGunMap.put(stationId,gunKeys);
		}

		//统计功率放到redis
		int powerCount = 0;
		int gunCount = 0;// 枪总数
		int charging = 0;// 充电中的枪
		int free = 0;// 空闲中的枪
		int chargePrepare = 0;// 充电准备中的枪
		int chargeFinish = 0;// 充电完成中的枪
		int offLine = 0;// 离线中的枪
		int problem = 0;// 故障中的枪
		int bespeak = 0;// 预约中的枪
		for(Long stationId: stations) {
			Set<String> array = stationGunMap.get(stationId);

			if(array != null){
				List<Object> results = StringredisTemplate.executePipelined(new RedisCallback<Object>() {
					@Override
					public String doInRedis(RedisConnection conn) throws DataAccessException {
						conn.openPipeline();
						for (String key : array) {
							conn.get(key.getBytes());
						}
						return null;
					}
				});
				if (results != null && !results.isEmpty()) {

					for (int j = 0; j < results.size(); j++) {
						String monitorvalue = (String) results.get(j);
						gunCount++;
						if (monitorvalue != null) {
//							GunMonitorDto monitorDto = (GunMonitorDto) JSONObject.parse(monitorvalue);
							JSONObject o = (JSONObject) JSONObject.parse(monitorvalue);
							o.remove("@type");
							GunMonitorDto monitorDto = o.toJavaObject(GunMonitorDto.class);
//							// 充电枪信息统计
							powerCount += monitorDto.getPower();
							switch (monitorDto.getGunState()) {
								case 3:
									// 充电中的电枪
									charging++;
									break;

								case 1:
									// 空闲中的电枪
									free++;
									break;

								case 4:
									// 已充满的枪
									chargeFinish++;
									break;
								case 5:
									// 离线中的电枪
									offLine++;
									break;
								case 2:
									// 充电准备中的电枪
									chargePrepare++;
									break;
								case 8:
									// 预约的电枪
									bespeak++;
									break;
								case 7:
									// 告警中的电枪
									problem++;
									break;
								default:
									break;
							}
						}
					}
				}

			}
			}

		// 将第一次统计结果缓存到redis  后续变化统计由sparkStreaming完成
		redisJsonTemplate.opsForValue().set("powerSum-"+sellerId,powerCount);
		redisJsonTemplate.opsForValue().set("chargingCount-"+sellerId,charging);
		redisJsonTemplate.opsForValue().set("freeCount-"+sellerId,free);
		redisJsonTemplate.opsForValue().set("chargeFinishCount-"+sellerId,chargeFinish);
		redisJsonTemplate.opsForValue().set("offlineCount-"+sellerId,offLine);
		redisJsonTemplate.opsForValue().set("chargePrepareCount-"+sellerId,chargePrepare);
		redisJsonTemplate.opsForValue().set("bespeakCount-"+sellerId,bespeak);
		redisJsonTemplate.opsForValue().set("problemCount-"+sellerId,problem);
		redisJsonTemplate.opsForValue().set("gunCount-"+sellerId,gunCount);

	}

	@Override
	public List<GunMonitorDto> getGunByStation(Long stationId) {
		List<GunMonitorDto> gunMonitorDtoList = new ArrayList<GunMonitorDto>();
		String gunKeysCachekey = redisTemp.buildKey(RedisDataEnum.GUNKEYSCACHE, stationId);
		String value = StringredisTemplate.opsForValue().get(gunKeysCachekey);
		if(StrUtil.isEmpty(value)){
//			log.error("调用配置服务获【查询枪gunKeys】失败，返回空,事件编码：l020100025 站点ID【{}】", stationId);
			return CollectionUtil.newArrayList(gunMonitorDtoList);
		}
		JSONArray array = JSONArray.parseArray(value);
		try {
			List<Object> results = StringredisTemplate.executePipelined(new RedisCallback<Object>() {
				@Override
				public String doInRedis(RedisConnection conn) throws DataAccessException {
					conn.openPipeline();
					for (int i = 0; i < array.size(); i++) {
						String keys = array.get(i).toString().replaceAll(RedisDataEnum.GUNKEYSCACHE.getPrefix(), RedisDataEnum.GUNMONITOR.getPrefix());
						conn.get(keys.getBytes());
					}
					return null;
				}
			});
			if (results != null && !results.isEmpty()) {
				for (int j = 0; j < results.size(); j++) {
					String monitorvalue = (String) results.get(j);
					if (monitorvalue != null) {
						JSONObject o = (JSONObject) JSONObject.parse(monitorvalue);
						o.remove("@type");
						GunMonitorDto monitorDto = o.toJavaObject(GunMonitorDto.class);
//						GunMonitorDto monitorDto = (GunMonitorDto) JSONObject.parse(monitorvalue);
						gunMonitorDtoList.add(monitorDto);
					}
				}
			}
			return CollectionUtil.newArrayList(gunMonitorDtoList);
		}catch ( Exception e){
			e.printStackTrace();
//			log.error("调用配置服务获【查询枪gunKeys】失败，返回空,事件编码：l020100027 站点ID【{}】", stationId);
			return CollectionUtil.newArrayList(gunMonitorDtoList);
		}

	}




}