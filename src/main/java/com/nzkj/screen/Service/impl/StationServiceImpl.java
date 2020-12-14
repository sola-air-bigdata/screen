package com.nzkj.screen.Service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.DTO.*;
import com.nzkj.screen.Entity.OperatingUnit;
import com.nzkj.screen.Entity.Station;
import com.nzkj.screen.Repo.*;
import com.nzkj.screen.Service.StationService;
import com.nzkj.screen.Utils.StringUtils;
import com.nzkj.screen.Utils.TimeUtils;
import com.nzkj.screen.Utils.ValUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import org.apache.commons.collections.CollectionUtils;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Liu yang
 * @Date: 2020/7/3 11:17
 * Describe:
 */
@Service
public class StationServiceImpl implements StationService {

    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate (RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    //商家id为固定值
    @Value("${sellerId}")
    private long sellerId;

    //userId为固定值
    @Value("${userId}")
    private long userId;

    //缓存到redis中的key过期时间  单位为秒  最好不要设置为永久
    //设置为永久后永远能从redis拿到数据 如有错误很难发现
    @Value("${expireTime}")
    private int expireTime;


    private StationRepository stationRepository;
    @Autowired
    public void setStationRepository(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }
    
    private AllDayDataRepository allDayDataRepository;
    @Autowired
    public void setAllDayDataRepository(AllDayDataRepository allDayDataRepository){
        this.allDayDataRepository = allDayDataRepository;
    }

    private TotalOperateDataRepository totalOperateDataRepository;
    @Autowired
    public void setTotalOperateDataRepository (TotalOperateDataRepository totalOperateDataRepository){
        this.totalOperateDataRepository = totalOperateDataRepository;
    }

    private OperatingUnitRepository operatingUnitRepository;
    @Autowired
    public void setOperatingUnitRepository(OperatingUnitRepository operatingUnitRepository){
        this.operatingUnitRepository = operatingUnitRepository;
    }

    private AllMonthDataRepository allMonthDataRepository;
    @Autowired
    public void setAllMonthDataRepository(AllMonthDataRepository allMonthDataRepository){
        this.allMonthDataRepository = allMonthDataRepository;
    }

    private PileRepository pileRepository;
    @Autowired
    public void setPileRepository (PileRepository pileRepository){
        this.pileRepository = pileRepository;
    }

    private GunRepository gunRepository;
    @Autowired
    public void setGunRepository(GunRepository gunRepository){
        this.gunRepository = gunRepository;
    }

    @Override
    public List<StationDto> getStationList(long id) {
        List<Station> list = stationRepository.findBySellIdAndDeleteFlag(id,false);
        List<StationDto> list2 = new ArrayList<>();
        for(Station station : list){
            list2.add(transform(station));
        }
        return list2;
    }



    private StationDto transform (Station station) {
        StationDto stationDto = new StationDto();
        BeanUtils.copyProperties(station, stationDto);
        stationDto.setId(station.getId());
        stationDto.setManagerId(station.getManagerId());
        if(station.getSellId() != null){
            stationDto.setSellerId(station.getSellId());
        }
        if (station.getStationParentId() != null) {
            stationDto.setStationId(station.getStationParentId());
        }
        if (station.getOperatorId() != null) {
            stationDto.setSellerId(station.getOperatorId());
        }
        if (station.getArea() != null) {
            stationDto.setAreaid(station.getArea().getId());
        }
        if (StringUtils.isNotEmpty(station.getStatus())) {
            stationDto.setStationEnums(StationEnum.getEnumByName(station.getStatus()));
        }

        if (station.getFastSlow() != null) {
            stationDto.setFastSlow(StationFastSlowEnum.valueOf(station.getFastSlow()));
        }
        if (station.getRunType() != null) {
            stationDto.setRunType(PileOperateTypeEnum.valueOf(station.getRunType()));
        }

        if (station.getStationType() != null) {
            stationDto.setStationTypeEnum(StationTypeEnum.getEnumByCode(station.getStationType().intValue()));
        }

        if (station.getRunType() != null) {
            stationDto.setRunType(PileOperateTypeEnum.valueOf(station.getRunType()));
        }

        if (station.getConstruction() != null) {
            stationDto.setConstructionEnum(ConstructionEnum.getEnumByCode(station.getConstruction().intValue()));
        }
        if (StringUtils.isNotEmpty(station.getConfigType())) {
            stationDto.setConfigTypeEnum(StationConfigTypeEnum.valueOf(station.getConfigType()));
        } else {
            stationDto.setConfigTypeEnum(StationConfigTypeEnum.WeiSheZhi);
        }
        if (station.getOperatingUnitId() != null) {
            OperatingUnit unit = operatingUnitRepository.findById(station.getOperatingUnitId()).orElse(null);
            if (unit != null) {
                OperatingUnitDto operatingUnitDto = new OperatingUnitDto();
                BeanUtils.copyProperties(unit, operatingUnitDto);
                stationDto.setOperatingUnitDto(operatingUnitDto);

            }
        }
        return stationDto;
    }

    public StationDto findById(long stationId){
        return transform(stationRepository.findById(stationId).get());
    }


    @Override
    public List<GunMonitorDto> getGunByStation(long stationId) {

        String stationGunKeys = String.format("SGK_%s_%s", stationId, "station_gun_key");
        if(redisTemplate.opsForValue().get(stationGunKeys) == null){
            final JSONArray gunKeys = new JSONArray();
            List<BigInteger> piles =  pileRepository.findPileUnderStation(stationId);
            for(BigInteger pileId :piles){
                List<Object> guns = gunRepository.findGunUnderPile(pileId.longValue());
                for(Object gunId : guns){
                    gunKeys.add(buildKey(RedisDataEnum.GUN, stationId, pileId, gunId));
                }
            }
            redisTemplate.opsForValue().set(stationGunKeys,gunKeys,1,TimeUnit.DAYS);
            return redisTemplate.executePipelined(new RedisCallback<GunMonitorDto>() {
                @Override
                public GunMonitorDto doInRedis(RedisConnection conn) throws DataAccessException {
                    conn.openPipeline();
                    for(int i = 0, size = gunKeys.size(); i < size; i++) {
                        conn.get(gunKeys.get(i).toString().getBytes());
                    }
                    return null;
                }
            },redisTemplate.getValueSerializer());

        }else {
            final JSONArray gunKeys1 = (JSONArray) redisTemplate.opsForValue().get(stationGunKeys);
            return redisTemplate.executePipelined(new RedisCallback<GunMonitorDto>() {
                @Override
                public GunMonitorDto doInRedis(RedisConnection conn) throws DataAccessException {
                    conn.openPipeline();
                    for(int i = 0, size = gunKeys1.size(); i < size; i++) {
                        conn.get(gunKeys1.get(i).toString().getBytes());
                    }
                    return null;
                }
            },redisTemplate.getValueSerializer());

        }

    }

    /*分隔符*/
    private final static String SEPARATOR = ":";

    private String buildKey(RedisDataEnum redisData ,Object... patterns) {
        StringBuilder key = new StringBuilder(redisData.getPrefix()).append(SEPARATOR);
        for(int i = 0 , size = patterns.length ; i < size; i++) {
            key.append(patterns[i]);
            if(i != size - 1) {
                key.append(SEPARATOR);
            }
        }
        return key.toString();
    }

    /**
     * @Title
     * @Description：获取故障曲线图
     * @Param：SelectCondition
     * @Return：Map
     * @author <a>huangxw</a>
     * @CreateDate：2019年9月12日15:26:09
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    @Override
    public Map<String, Object> getFaultCountMap(SelectCondition condition) {
        Map<String, Object> mapData = new HashMap<>();
        List<String> timeList = new ArrayList<>();
        for(int i=11; i >=0; i--){
            String time = TimeUtils.format(TimeUtils.addMonth(new Date(), -i), "yyyy-MM");
            timeList.add(time);
        }
        List<Integer> faultCountList = new ArrayList<>();
        //1、开始遍历数据集
        for (String date : timeList) {
            //1.1、 如果redis中没有故障桩值, 根据key： redis-key：AMT_商家号_月份(yyyy-MM)_查询的字段名
            String monthFaultPileKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), date, "month_fault_pile");
            //1,2如果redis中不存在从数据库中取
            Map map = new HashMap();
            map.put("time", date);
            if (redisTemplate.opsForValue().get(monthFaultPileKey) == null) {
                Object ob = (Object) allMonthDataRepository.getMothFault( sellerId,date);
                //1.2.1如果数据库有数据从数据库中取
                if (ob != null) {
                    //1.2.2取出的数据存进redis
                    redisTemplate.opsForValue().set(monthFaultPileKey, ob,expireTime,TimeUnit.SECONDS);
                    Integer monthFaultPile = ValUtil.toInteger(ob);
                    faultCountList.add(monthFaultPile);
                } else {
                    faultCountList.add(0);
                }
            } else {
                Integer monthFaultPile = ValUtil.toInteger(redisTemplate.opsForValue().get(monthFaultPileKey));
                faultCountList.add(monthFaultPile);
            }
        }
        mapData.put("timeList", timeList);
        mapData.put("faultCountList", faultCountList);

        return mapData;
    }


    @Override
    public Map<String, Object> getFaultAndRate(SelectCondition condition) {
        Map<String, Object> mapData = new HashMap<>();
        //月故障桩数
        int faultCount = 0;

        //故障率
        double rate = 0;

        double offlineRate = 0;

        //总桩数
        int totalfaultCount = 0;
        try {
            //t_all_month_table AMT_商家号_月份(yyyy-MM)_查询的字段名
            String moth = TimeUtils.format(new Date(), "yyyy-MM");
            String key = String.format("AMT_%s_%s_%s", condition.getSellerId(), moth, "month_fault_pile");
            //1、 如果redis中没有故障桩值
            if( redisTemplate.opsForValue().get(key) == null ){
                //1.1 从数据库获取故障桩值
                BigInteger faultCountKey = (BigInteger)allMonthDataRepository.getFaultCount(sellerId,TimeUtils.format(new Date(), "yyyy-MM"));
                //1.2 存进redis中并赋值给变量faultCount
                faultCountKey = faultCountKey == null ? BigInteger.ZERO: faultCountKey;
                redisTemplate.opsForValue().set(key, faultCountKey,expireTime,TimeUnit.SECONDS);
                faultCount = faultCountKey.intValue();
            }else
            {
                //2、 如果redis有从redis获取
                faultCount = ValUtil.toInteger(redisTemplate.opsForValue().get(key));
            }


            String totalChargingPileCountKey = String.format("AT_%s_%s", condition.getSellerId(), "total_charging_pile_count");
            //3、如果redis中没有总故障桩值
            //根据key： 总桩数 AT_商家号_查询的字段名
            if( redisTemplate.opsForValue().get(totalChargingPileCountKey) == null ){
                //3.1 从数据库获取总故障桩值
                BigInteger totalChargingPileCount = (BigInteger) totalOperateDataRepository.getChargingPileCountCount(sellerId);
                //3.2 存进redis中并赋值给变量totalfaultCount
                redisTemplate.opsForValue().set(totalChargingPileCountKey, totalChargingPileCount,expireTime,TimeUnit.SECONDS);
                totalfaultCount = totalChargingPileCount.intValue();
            }else {
                //4、 如果redis有从redis获取
                totalfaultCount = Integer.parseInt(redisTemplate.opsForValue().get(totalChargingPileCountKey).toString());
            }

            //5、 计算桩故障率=当月故障电桩总数÷电桩总数
            rate = ValUtil.toDivBigDecimal(faultCount*100, totalfaultCount, 2).doubleValue();

        } catch (Exception e) {
            e.printStackTrace();
            mapData.put("faultCount", faultCount);
            mapData.put("rate", rate);
            return mapData;
        }
        mapData.put("faultCount", faultCount);
        mapData.put("rate", rate);
        return mapData;
    }


    /**
     * @Title
     * @Description：获取累计服务会员数
     * @Param： sellerId 商户id
     * @Return：int
     * @author <a>huangxw</a>
     * @CreateDate：2019年9月16日16:57:41
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    @Override
    public int getMemberChargeCount(Long sellerId) {
        int memberCount = 0;
        try {
            String totalServiceCountKey = String.format("AT_%s_%s", sellerId, "total_service_user_count");

            //1 累计服务会员数
            if( redisTemplate.opsForValue().get(totalServiceCountKey) == null ) {
                //1.1、 从数据库读取并存进redis
                Object object = totalOperateDataRepository.getUserChargeCount(sellerId);
                if (object != null) {
//                    BigInteger obj = (BigInteger) object;
                    //存进redis
                    redisTemplate.opsForValue().set(totalServiceCountKey, object,expireTime,TimeUnit.SECONDS);
                    memberCount = ValUtil.toInteger(object, 0);
                }
            }else {
                memberCount = ValUtil.toInteger(redisTemplate.opsForValue().get(totalServiceCountKey).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberCount;
    }


    /**
     * @Title
     * @Description：会员&车队数据(用户运营)
     * @Param：
     * @Return：黑卡 钻石 白金 金卡 银卡 车队 个人
     * @author <a>huangxw</a>
     * @CreateDate：2019年9月17日09:37:14
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    @Override
    public MemberDataDto getMemberDataDto(SelectCondition condition) {
        MemberDataDto dto = new MemberDataDto();
        try {
            String blackgoldKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_blackgold_user_count");//黑卡
            String diamondKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_diamond_user_count");//钻石
            String platinumKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_platinum_user_count");//白金;
            String goldKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_gold_user_count");//金卡
            String silverKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_silver_user_count");//银卡
            String teamKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_personal_user_count");//车队
            String personalKey = String.format("AT_%s_%s", condition.getSellerId(), "total_service_team_user_count");//个人

            int silverCount = 0;
            int goldCount = 0;
            int platinumCount = 0;
            int diamondCount = 0;
            int blackGoldCount = 0;
            int teamCount = 0;
            int personalCount = 0;
            Object res = new Object();
            if( redisTemplate.opsForValue().get(blackgoldKey) == null || redisTemplate.opsForValue().get(diamondKey) == null ||  redisTemplate.opsForValue().get(platinumKey) == null ||
                    redisTemplate.opsForValue().get(goldKey) == null ||  redisTemplate.opsForValue().get(silverKey) == null  ||  redisTemplate.opsForValue().get(teamKey) == null || redisTemplate.opsForValue().get(personalKey) == null){
                res = totalOperateDataRepository.getMemberAndTeamData(sellerId);
            }
            //1 黑卡
            if( redisTemplate.opsForValue().get(blackgoldKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(blackgoldKey, obj[0],expireTime,TimeUnit.SECONDS);
                    blackGoldCount = ValUtil.toInteger(obj[0], 0);
                }
            }else {
                blackGoldCount = ValUtil.toInteger(redisTemplate.opsForValue().get(blackgoldKey), 0);
            }

            //2 钻石
            if( redisTemplate.opsForValue().get(diamondKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(diamondKey, obj[1],expireTime,TimeUnit.SECONDS);
                    diamondCount = ValUtil.toInteger(obj[1], 0);
                }
            }else {
                diamondCount = ValUtil.toInteger(redisTemplate.opsForValue().get(diamondKey), 0);
            }

            //3 白金
            if( redisTemplate.opsForValue().get(platinumKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(platinumKey, obj[2],expireTime,TimeUnit.SECONDS);
                    platinumCount = ValUtil.toInteger(obj[2], 0);
                }
            }else {
                platinumCount = ValUtil.toInteger(redisTemplate.opsForValue().get(platinumKey), 0);
            }

            //4 金卡
            if( redisTemplate.opsForValue().get(goldKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(goldKey, obj[3],expireTime,TimeUnit.SECONDS);
                    goldCount = ValUtil.toInteger(obj[3], 0);
                }
            }else {
                goldCount = ValUtil.toInteger(redisTemplate.opsForValue().get(goldKey), 0);
            }

            //5 银卡
            if( redisTemplate.opsForValue().get(silverKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(silverKey, obj[4],expireTime,TimeUnit.SECONDS);
                    silverCount = ValUtil.toInteger(obj[4], 0);
                }
            }else {
                silverCount = ValUtil.toInteger(redisTemplate.opsForValue().get(silverKey), 0);
            }
            //5 车队
            if( redisTemplate.opsForValue().get(teamKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(teamKey, obj[6],expireTime,TimeUnit.SECONDS);
                    teamCount = ValUtil.toInteger(obj[6], 0);
                    dto.setTeamCount(teamCount);
                }
            }else {
                teamCount = ValUtil.toInteger(redisTemplate.opsForValue().get(teamKey), 0);
                dto.setTeamCount(teamCount);
            }
            //6 个人
            if( redisTemplate.opsForValue().get(personalKey) == null ) {
                if (res != null) {
                    Object[] obj = (Object[]) res;
                    redisTemplate.opsForValue().set(personalKey, obj[5],expireTime,TimeUnit.SECONDS);
                    personalCount = ValUtil.toInteger(obj[5], 0);
                    dto.setPersonageCount(personalCount);
                }
            }else {
                personalCount = ValUtil.toInteger(redisTemplate.opsForValue().get(personalKey), 0);
                dto.setPersonageCount(personalCount);
            }

            int total = dto.getTeamCount() + dto.getPersonageCount();
            dto.setPersonageRate(ValUtil.toDivBigDecimal(dto.getPersonageCount()*100, total, 2).doubleValue());
            dto.setTeamRate(ValUtil.toDivBigDecimal(dto.getTeamCount()*100, total, 2).doubleValue());
            dto.setSilverRate(ValUtil.toDivBigDecimal(silverCount*100, total, 2).doubleValue());
            dto.setGoldRate(ValUtil.toDivBigDecimal(goldCount*100, total, 2).doubleValue());
            dto.setPlatinumRate(ValUtil.toDivBigDecimal(platinumCount*100, total, 2).doubleValue());
            dto.setDiamondRate(ValUtil.toDivBigDecimal(diamondCount*100, total, 2).doubleValue());
            dto.setBlackGoldRate(ValUtil.toDivBigDecimal(blackGoldCount*100, total, 2).doubleValue());

            double tot = dto.getSilverRate() + dto.getGoldRate() + dto.getPlatinumRate() + dto.getDiamondRate()
                    + dto.getBlackGoldRate();
            if (tot != 100) {
                double cha = 100.0 - tot;
                if (dto.getSilverRate() >= 0) {
                    double d = dto.getSilverRate();
                    dto.setSilverRate(ValUtil.toDoubleDiv(d + cha, 1.0, 2));
                } else if (dto.getSilverRate() >= 0) {
                    double d = dto.getGoldRate() + cha;
                    dto.setSilverRate(ValUtil.toDoubleDiv(d + cha, 1.0, 2));
                } else if (dto.getSilverRate() >= 0) {
                    double d = dto.getPlatinumRate() + cha;
                    dto.setSilverRate(ValUtil.toDoubleDiv(d + cha, 1.0, 2));
                } else if (dto.getSilverRate() >= 0) {
                    double d = dto.getDiamondRate() + cha;
                    dto.setSilverRate(ValUtil.toDoubleDiv(d + cha, 1.0, 2));
                } else if (dto.getSilverRate() >= 0) {
                    double d = dto.getBlackGoldRate() + cha;
                    dto.setSilverRate(ValUtil.toDoubleDiv(d + cha, 1.0, 2));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public Map<Long, Long> getBalances(Long sellerId, Long userId) {
        Map<Long, Long> dataMap = new HashMap<>();
        List<Object> balances = totalOperateDataRepository.getBalances(sellerId, userId);
        if (CollectionUtils.isNotEmpty(balances)) {
            for (Object object : balances) {
                Object[] obj = (Object[]) object;
                Long stationId = ValUtil.toLong(obj[0]);
                Long balance = ValUtil.toLong(obj[1], 0L);
                if (stationId != null) {
                    dataMap.put(stationId, balance);
                }
            }
        }
        return dataMap;
    }

    @Override
    public Map<String, Long> getTransBalance(SelectCondition condition) {
        Map<String, Long> mapData = new HashMap<>();
        if (condition == null) {
            return mapData;
        }
        // 纯电费总收入
        Long powerBalanceAll = 0L;
        // 总收入
        Long actualBalanceAll = 0L;
        Date now = new Date();
        Date startTime = TimeUtils.getDayStart(now);
        Date endTime = TimeUtils.getDayEnd(now);
        //当日充电总收入
        BigInteger totalChargingRevenue = new BigInteger("0");
        //当日非公交充电收入
        BigInteger otherBusFee = new BigInteger("0");
        //当日公交充电收入
        BigInteger busFee = new BigInteger("0");
        //当日非公交总收入
        BigInteger dayIActualBalancBalance  = new BigInteger("0");
        //当日总收入
        BigInteger totalBanlance = new BigInteger("0");
        //当日公交总收入
        BigInteger totalBusBanlance = new BigInteger("0");
        //redis-key：ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
        String dayIActualBalanceKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(new Date(), "yyyy-MMM-dd"), "day_i_actual_balance");//当日累计运营收入

        String dayIPowerBalanceKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(new Date(), "yyyy-MMM-dd"), "day_i_power_balance");//当日充电收入

        String dayIPowerBalanceNteamKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(new Date(), "yyyy-MMM-dd"), "day_i_power_balance_nteam");//当日非公交充电收入

        String dayBuysTranstionKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(new Date(), "yyyy-MMM-dd"), "day_i_bus_banlance_nteam");//当日公交充电总收入  需要单独计算***********

        String dayIActualBalancBalanceNteamKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(new Date(), "yyyy-MMM-dd"), "day_i_actual_balance_balance_nteam");//当日非公交总收入

        String dayBusTotalBalanceKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(new Date(), "yyyy-MMM-dd"), "day_i_bus_total_balance_nteam");//当日公交总收入
        Object resDate = new Object();
        //1、如果redis没有数据从数据库获取赋值数据集 obj;
        //redis key -》 ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
        if( redisTemplate.opsForValue().get(dayIActualBalanceKey) == null || redisTemplate.opsForValue().get(dayIPowerBalanceKey) == null || redisTemplate.opsForValue().get(dayIPowerBalanceNteamKey) == null ||
                redisTemplate.opsForValue().get(dayBuysTranstionKey) == null ||  redisTemplate.opsForValue().get(dayIActualBalancBalanceNteamKey) == null  ||  redisTemplate.opsForValue().get(dayBusTotalBalanceKey) == null  ){
            resDate = allDayDataRepository.getDayTransBalance(condition.getSellerId(), TimeUtils.format(startTime, "yyyy-MM-dd"));
        }

        //2获取当日充电收入(所有车辆充电费用加总计)  总计充电费
        mapData.put("powerBalanceAll",0L);
        if(redisTemplate.opsForValue().get(dayIPowerBalanceKey) == null  && resDate != null){
            //2.1、如果redis没有数据从数据集获取
            Object[] obj = (Object[]) resDate;
            redisTemplate.opsForValue().set(dayIPowerBalanceKey, obj[1],expireTime,TimeUnit.SECONDS);
            totalChargingRevenue = ValUtil.toBigInteger(obj[1]);
            mapData.put("powerBalanceAll", ValUtil.toLong(obj[1], 0L));
        }else{
            //2.2、如果redis有数据从redis获取
            mapData.put("powerBalanceAll",  ValUtil.toLong(redisTemplate.opsForValue().get(dayIPowerBalanceKey), 0L));
        }


        //3、获取当日累计收入(所有车辆总收入)   累计总收入
        //初始化默认为0
        mapData.put("actualBalanceAll",0L);
        if(redisTemplate.opsForValue().get(dayIActualBalanceKey) == null  && resDate != null){
            //3.1、如果redis没有数据从数据集获取
            Object[] obj = (Object[]) resDate;
            redisTemplate.opsForValue().set(dayIActualBalanceKey, obj[0],expireTime,TimeUnit.SECONDS);
            totalBanlance = ValUtil.toBigInteger(obj[0]);
            mapData.put("actualBalanceAll", ValUtil.toLong(obj[0], 0L));
        }else{
            //3.2、如果redis有数据从redis获取
            mapData.put("actualBalanceAll",  ValUtil.toLong(redisTemplate.opsForValue().get(dayIActualBalanceKey), 0L));
        }


        //4、获取当日非公交累计充电收入（非公交总电费）   非公交充电总电费
        //初始化默认为0
        mapData.put("otherPowerBalance",0L);
        if(redisTemplate.opsForValue().get(dayIPowerBalanceNteamKey) == null  && resDate != null){
            //4.2、如果redis没有数据从数据集获取
            Object[] obj = (Object[]) resDate;
            redisTemplate.opsForValue().set(dayIPowerBalanceNteamKey, obj[2],expireTime,TimeUnit.SECONDS);
            otherBusFee = ValUtil.toBigInteger(obj[2]);
            mapData.put("otherPowerBalance", ValUtil.toLong(obj[2], 0L));
            //4.3、如果redis和数据集中都没有默认为0
            mapData.put("otherPowerBalance",  0L);
        }else{
            //4.1、如果redis有数据从redis获取
            mapData.put("otherPowerBalance",  ValUtil.toLong(redisTemplate.opsForValue().get(dayIPowerBalanceNteamKey), 0L));
        }


        //5、获取当日非公交总收入                     非公交总收入
        if(redisTemplate.opsForValue().get(dayIActualBalancBalanceNteamKey) == null ){
            //5.1、如果redis没有数据从数据集获取
            if(resDate != null ) {
                Object[] obj = (Object[]) resDate;
                redisTemplate.opsForValue().set(dayIActualBalancBalanceNteamKey, obj[3],expireTime,TimeUnit.SECONDS);
                dayIActualBalancBalance = ValUtil.toBigInteger(obj[3]);
                mapData.put("otherActualBalance", ValUtil.toLong(obj[3], 0L));
            }else {
                //5.2、如果redis和数据集中都没有默认为0
                mapData.put("otherActualBalance",  0L);
            }
        }else{
            //5.3、如果redis有数据从redis获取
            mapData.put("otherActualBalance",  ValUtil.toLong(redisTemplate.opsForValue().get(dayIActualBalancBalanceNteamKey), 0L));
        }


        //6、获取当日公交充电收入 = 当日充电收入 - 非公交累计充电费用 （公交总电费）  公交充电总费用
        BigInteger nonBusFee = new BigInteger("0");
        if(redisTemplate.opsForValue().get(dayBuysTranstionKey) == null ){
            //6.1、如果redis没有数据从数据集获取
            if(resDate != null ) {
                Object[] obj = (Object[]) resDate;
                busFee = totalChargingRevenue.subtract(otherBusFee);
                redisTemplate.opsForValue().set(dayBuysTranstionKey, ValUtil.toLong(busFee, 0L),expireTime,TimeUnit.SECONDS);
                mapData.put("GongJiaoPowerBalance", ValUtil.toLong(busFee, 0L));
            }else{
                mapData.put("GongJiaoPowerBalance",  0L);
            }
        }else{
            //6.3、如果redis有数据从redis获取
            mapData.put("GongJiaoPowerBalance",  ValUtil.toLong(redisTemplate.opsForValue().get(dayBuysTranstionKey), 0L));
        }


        //7、获取当日公交总收入  = 当日充电收入 - 非公交累计充电费用
        if(redisTemplate.opsForValue().get(dayBusTotalBalanceKey) == null ){
            //7.1、如果redis没有数据从数据集获取
            if(resDate != null ){
                Object[] obj = (Object[]) resDate;
                totalBusBanlance = totalBanlance.subtract(dayIActualBalancBalance);
                redisTemplate.opsForValue().set(dayBusTotalBalanceKey, ValUtil.toLong(totalBusBanlance, 0L),expireTime,TimeUnit.SECONDS);
                mapData.put("GongJiaoActualBalance",  ValUtil.toLong(totalBusBanlance, 0L));
            }else {
                //7.2、如果redis和数据集中都没有默认为0
                mapData.put("GongJiaoActualBalance",  0L);
            }
        }else{
            //7.3、如果redis有数据从redis获取
            mapData.put("GongJiaoActualBalance",  ValUtil.toLong(redisTemplate.opsForValue().get(dayBusTotalBalanceKey), 0L));
        }
        return mapData;
    }


    /**
     * @Title
     * @Description：获取当日非公交充电排行(admin用户登陆和个人用户登陆)********************************************
     * @Param： sellerId 商家id 会员id
     * @Return：List
     * @author <a href="mail to: *******@******.com" rel="nofollow">huangxw</a>
     * @CreateDate：2019年9月20日15:22:26
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    public List<IncomeRankDto> getTransIncomeRank(Long sellerId, Long userId) {
        List<IncomeRankDto> incomeRankDtoList = new ArrayList<>();
        IncomeRankDto incomeRankDto = new IncomeRankDto();
        //t_all_day_member_table→ redis-key：DMEMT_商家号_日期(yyyy-MM-dd)_会员ID_查询的字段名
        String NonBusChargingKey = String.format("DMEMT_%s_%s_%s_%s", sellerId, TimeUtils.format(new Date(), "yyyy-MM-dd"), userId,  "day_nbus_i_actual_balance_count_rank");//日个人会员充电消费金额
        String userNameKey = String.format("DMEMT_%s_%s_%s_%s", sellerId, TimeUtils.format(new Date(), "yyyy-MM-dd"), userId,  "n_member_name");//日个人会员名字
        //ADMIN登陆展示排行
        List<Object> obj = (List<Object>) allDayDataRepository.getdayTransIncomeRank(sellerId, userId, TimeUtils.formatTimeToDay(new Date()));
        if (CollectionUtils.isNotEmpty(obj)) {
            for (Object object : obj) {
                Object[] res = (Object[]) object;
                incomeRankDto.setActualBalance(ValUtil.toLong(res[0], 0L));
                incomeRankDto.setName(ValUtil.toString(res[1]));
                incomeRankDtoList.add(incomeRankDto);
            }
            return incomeRankDtoList;
        } else {
            //普通用户登陆展示排行
            //1、如果redis有数据从数据库获取t_all_day_member_table→ redis-key：DMEMT_商家号_日期(yyyy-MM-dd)_会员ID_查询的字段名
            if (redisTemplate.opsForValue().get(NonBusChargingKey) != null) {
                incomeRankDto.setActualBalance(ValUtil.toLong(redisTemplate.opsForValue().get(NonBusChargingKey), 0L));
            } else {
                //1.1、如果redis没有数据从数据库获取
                List<Object> resData = (List<Object>) allDayDataRepository.getdayTransIncomeRank(sellerId, userId, TimeUtils.formatTimeToDay(new Date()));
                if (CollectionUtils.isNotEmpty(resData)) {
                    Object[] res = (Object[]) resData.get(0);
                    redisTemplate.opsForValue().set(NonBusChargingKey,res[0],expireTime,TimeUnit.SECONDS);
                    incomeRankDto.setActualBalance(ValUtil.toLong(res[0], 0L));
                }
            }

            //2、如果redis有数据从数据库获取t_all_day_member_table→ redis-key：DMEMT_商家号_日期(yyyy-MM-dd)_会员ID_查询的字段名
            if (redisTemplate.opsForValue().get(userNameKey) != null) {
                incomeRankDto.setName(ValUtil.toString(redisTemplate.opsForValue().get(userNameKey)));
            } else {
                //2.1、如果redis没有数据从数据库获取
                List<Object> resData = (List<Object>) allDayDataRepository.getdayTransIncomeRank(sellerId, userId, TimeUtils.formatTimeToDay(new Date()));
                if (CollectionUtils.isNotEmpty(resData)) {
                    Object[] res = (Object[]) resData.get(0);
                    redisTemplate.opsForValue().set(userNameKey,res[1],expireTime,TimeUnit.SECONDS);
                    incomeRankDto.setName(ValUtil.toString(res[1]));
                }
            }
            incomeRankDtoList.add(incomeRankDto);

        }
        return incomeRankDtoList;
    }


    /**
     * @Title
     * @Description：获取历史总收入或当日收入(收入方式统计)
     * @Param：
     * @Return：Map day_i_actual_balance_wechat 微信  day_i_actual_balance_app APP day_i_actual_balance_card 银行卡 day_i_actual_balance_vin vin卡
     * @author  huangxw
     * @CreateDate：2019年9月23日11:30:45
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    @Override
    public Map<String, Long> getChargeBalanceSum(SelectCondition condition) {
        Map<String, Long> balanceMap = new HashMap<>();
        String wechatKey=null;
        String appKey =null;
        String cardKey =null;
        String vinKey =null;
        Object obj = new Object();
        try {
            //AT_商家号_查询的字段名 total_wechat_actual_balance, total_app_actual_balance, total_card_actual_balance, total_vin_actual_balance
            String totalWechatKey = String.format("AT_%s_%s", condition.getSellerId(), "total_wechat_actual_balance");
            String totalAppKey = String.format("AT_%s_%s", condition.getSellerId(), "total_app_actual_balance");
            String totalCardKey = String.format("AT_%s_%s", condition.getSellerId(), "total_card_actual_balance");
            String totalVinKey = String.format("AT_%s_%s", condition.getSellerId(), "total_vin_actual_balance");

            //1 如果有传日期从商家日期统计表获取数据
            if( condition.getStartDate() != null ) {
                //ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
                wechatKey = String.format("ADT_%s_%s_%s", condition.getSellerId(),TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"), "day_i_actual_balance_wechat");
                appKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"), "day_i_actual_balance_app");
                cardKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"), "day_i_actual_balance_card");
                vinKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"), "day_i_actual_balance_vin");
                if(redisTemplate.opsForValue().get(wechatKey) == null || redisTemplate.opsForValue().get(appKey) == null || redisTemplate.opsForValue().get(cardKey) == null || redisTemplate.opsForValue().get(vinKey) == null ){
                    //如果redis中不存在缓存，从数据库中获取数据集复制给变量 obj
                    obj = allDayDataRepository.getTotalBalanceData(sellerId,TimeUtils.formatTimeToDay(condition.getStartDate()));
                }

                //1.1从数据集获取微信日收入统计
                balanceMap.put("wx", 0L);
                if( redisTemplate.opsForValue().get(wechatKey) == null ) {
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(wechatKey, rs[0],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("wx",  ValUtil.toLong(rs[0], 0L));
                    }
                }else {
                    //从redis获取微信日收入统计
                    balanceMap.put("wx",  ValUtil.toLong(redisTemplate.opsForValue().get(wechatKey), 0L));
                }

                //1.2从数据集redis获取APP日收入统计
                balanceMap.put("app", 0L);
                if( redisTemplate.opsForValue().get(appKey) == null ) {
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(appKey, rs[1],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("app",  ValUtil.toLong(rs[1], 0L));
                    }
                }else {
                    //从redis获取APP日收入统计
                    balanceMap.put("app",  ValUtil.toLong(redisTemplate.opsForValue().get(appKey), 0L));
                }
                //1.3从数据集获取银行卡日收入统计
                balanceMap.put("card", 0L);
                if( redisTemplate.opsForValue().get(cardKey) == null ) {
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(cardKey, rs[2],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("card",  ValUtil.toLong(rs[2], 0L));
                    }
                }else {
                    //从redis获取银行卡日收入统计
                    balanceMap.put("card",  ValUtil.toLong(redisTemplate.opsForValue().get(cardKey), 0L));
                }
                //1.4从数据集获取vin卡日收入统计
                balanceMap.put("vin", 0L);
                if( redisTemplate.opsForValue().get(vinKey) == null ) {
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(vinKey, rs[3],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("vin",  ValUtil.toLong(rs[3], 0L));
                    }
                }else {
                    //从redis获取vin卡日收入统计
                    balanceMap.put("vin",  ValUtil.toLong(redisTemplate.opsForValue().get(vinKey), 0L));
                }
            }else {
                //2 如果日期为空从商家分类统计表获取数据
                if (redisTemplate.opsForValue().get(totalWechatKey) == null || redisTemplate.opsForValue().get(totalAppKey) == null || redisTemplate.opsForValue().get(totalCardKey) == null || redisTemplate.opsForValue().get(totalVinKey) == null) {
                    //如果redis中不存在缓存，从数据库中获取数据集复制给变量 obj
                    obj = allDayDataRepository.getTotalBalanceData(sellerId,TimeUtils.formatTimeToDay(new Date()));
                }
                //2.1 如果redis中不存在历史收入统计数据
                balanceMap.put("wx", 23L);
                if( redisTemplate.opsForValue().get(totalWechatKey) == null ) {
                    //2.1.1从数据集获取微信历史收入统计添加到
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(totalWechatKey, rs[0],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("wx",  ValUtil.toLong(rs[0], 0L));
                    }else {
                        balanceMap.put("wx", 0L);
                    }
                }else {
                    //从redis获取微信历史收入统计
                    balanceMap.put("wx",  ValUtil.toLong(redisTemplate.opsForValue().get(totalWechatKey), 0L));
                }
                //2.2 如果redis中不存在APP历史收入统计数据
                if( redisTemplate.opsForValue().get(totalAppKey) == null ) {
                    //2.2.1从数据集获取APP历史收入统计
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(totalAppKey, rs[1],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("app",  ValUtil.toLong(rs[1], 0L));
                    }else {
                        balanceMap.put("app", 0L);
                    }
                }else {
                    //从redis获取APP历史收入统计
                    balanceMap.put("app",  ValUtil.toLong(redisTemplate.opsForValue().get(totalAppKey), 0L));
                }
                //2.3 如果redis中不存在银行卡历史收入统数据
                if( redisTemplate.opsForValue().get(totalCardKey) == null ) {
                    //2.3.1从数据集获取银行卡历史收入统计
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(totalCardKey, rs[2],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("card",  ValUtil.toLong(rs[2], 0L));
                    }else {
                        balanceMap.put("card", 0L);
                    }
                }else {
                    //从redis获取银行卡历史收入统计
                    balanceMap.put("card",  ValUtil.toLong(redisTemplate.opsForValue().get(totalCardKey), 0L));
                }
                //2.4 如果redis中不存在vin卡历史收入统计数据
                if( redisTemplate.opsForValue().get(totalVinKey) == null ) {
                    //2.4.1从数据集获取vin卡历史收入统计
                    if (obj != null)
                    {	Object[] rs = (Object[]) obj;
                        redisTemplate.opsForValue().set(totalVinKey, rs[3],expireTime,TimeUnit.SECONDS);
                        balanceMap.put("vin",  ValUtil.toLong(rs[3], 0L));
                    }else {
                        balanceMap.put("vin", 0L);
                    }
                }else {
                    //从redis获取vin卡历史收入统计
                    balanceMap.put("vin",  ValUtil.toLong(redisTemplate.opsForValue().get(totalVinKey), 0L));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return balanceMap;
    }


    /**
     * @Title
     * @Description：获取 非公交总收入趋势
     * @Param：SelectCondition
     * @Return： Map
     * @author <a href="mail to: *******@******.com" rel="nofollow">huangxw</a>
     * @CreateDate：2019年9月20日10:05:22
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    @Override
    public Map<String, Object> getTransIncomeIndicator(SelectCondition condition) {
        if (condition == null || condition.getSellerId() == null) {
            return null;
        }
        if (condition.getStartDate() == null) {
            condition.setStartDate(TimeUtils.getStartDate(TimeUtils.getDayStart(new Date())));
        }
        Map<String, Object> statisticsData = new HashMap<>();
        //1、 初始化日期(yyyy-mm-dd)集合
        List<String> timeList = TimeUtils.getTimeList(condition.getStartDate() , condition.getEndDate(),  TimeSharingEnum.getEnumByCode(3));

        List<Long> personIncomeList = new ArrayList<>();
        List<Long> noPublicBusIncomeList = new ArrayList<>();


        //1、 遍历所有时间数据集
        for (String day: timeList) {
            Object transIncome = new Object();
            //1.1.2如果没有数据从数据集中获取
            condition.setStartDate(TimeUtils.parse(day, "yyyy-MM-dd"));
            condition.setEndDate(TimeUtils.parse(day, "yyyy-MM-dd"));
            transIncome = allDayDataRepository.getPersonalTrans(sellerId,TimeUtils.formatTimeToDay(new Date()));
            //1.1.2.1从数据库获取个收入总和
            if (transIncome != null) {
                //1.1.2.2存入redis，返回结果集
//					redisCache.put(userKey, transIncome);
                personIncomeList.add(ValUtil.toLong(transIncome, 0L));// 个人会员充电收入;
            }else {
                // map里面不存在对应数据则添加默认数据
                personIncomeList.add(0L);
            }
//			}

            long totalTrans = 0L;
            BigInteger transIncomeBlance = new BigInteger("0");
            BigInteger personnalBlance = new BigInteger("0");;
            //1.2.2如果redis没有数据从数据库中获取
            //1.2.2.1从数据库获取所有收入总和	(车队和个人)
            Object totalTransIncome = allDayDataRepository.getTotalTransIncome(sellerId,TimeUtils.formatTimeToDay(new Date()));
            if( totalTransIncome != null ){
                transIncomeBlance = (BigInteger) totalTransIncome;
            }
            //1.2.2.2从数据库获取个人的所有收入
            BigDecimal personalTrans = (BigDecimal)allDayDataRepository.getPersonalTrans(sellerId,TimeUtils.formatTimeToDay(new Date()));
            if( personalTrans != null ){
                personnalBlance = personalTrans.toBigInteger();
            }
            //1.2.2.3车队的收入 = 收入总和 - 个人收入
            totalTrans = ValUtil.toLong(transIncomeBlance.subtract(personnalBlance));
            noPublicBusIncomeList.add(totalTrans);
            //1.2.2.4存入redis，返回结果集
//				redisCache.put(teamKey, totalTrans);
//		  }

        }
        statisticsData.put("timeList", timeList);
        statisticsData.put("personIncomeList", personIncomeList);
        statisticsData.put("noPublicBusIncomeList", noPublicBusIncomeList);
        return statisticsData;
    }


}
