package com.nzkj.screen.Service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.*;
import com.nzkj.screen.Entity.DTO.*;
import com.nzkj.screen.Service.ScreenRedisService;
import com.nzkj.screen.Service.StationService;
import com.nzkj.screen.Utils.*;
import com.nzkj.screen.mapper.pile.config.IPileMapper;
import com.nzkj.screen.mapper.pile.config.IStationMapper;
import com.nzkj.screen.mapper.pile.member.IMemberMapper;
import com.nzkj.screen.mapper.pile.screen.*;
import com.nzkj.screen.memory.IMemoryData;
import com.nzkj.screen.memory.LocalMemoryData;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.nzkj.screen.Utils.ValUtil.val;

/**
 * @Author: Liu yang
 * @Date: 2020/11/18 9:18
 * Describe:
 */
@Service
public class ScreenRedisServiceImpl implements ScreenRedisService {

    @Autowired
    private RedisCacheUtil redisCache;

    @Autowired
    private IStationShowTotalMapper stationShowTotalMapper;

    @Autowired
    private IStationShowDayMapper stationShowDayMapper;

    @Autowired
    private StationService stationService;

    @Autowired
    private IStationMapper stationMapper;

    @Autowired
    private IPileMapper pileMapper;

    @Autowired
    private IMemoryData memoryData;

    @Autowired
    private ITotalOperateMapper totalOperateMapper;

    @Autowired
    private IAllMonthDataMapper allMonthDataMapper;

    @Autowired
    private IStationShowMonthMapper stationShowMonthMapper;

    @Autowired
    private IStationShowMonthMemberMapper stationShowMonthMemberMapper;

    @Autowired
    private IMemberMapper memberMapper;

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


    @Override
    public Map<String, Object> doGetStationSumInfoByID(Long stationID, Date date) {
        Map<String, Object> stationSumInfo = new ConcurrentHashMap<>();
        if (date != null) {
            stationSumInfo = getStationBalanceData(stationID, sellerId, date);
        } else {
            stationSumInfo = getStationBalanceData(stationID, sellerId);
        }

        return stationSumInfo;
    }

    @Override
    public Map<String, Object> doGetStationSumViewData(Long stationId) {
        Map<String, Object> retMap = new ConcurrentHashMap<String, Object>();
        try {
            int gunCount = 0;// 枪总数
            int charging = 0;// 充电中的枪
            int free = 0;// 空闲中的枪
            int chargePrepare = 0;// 充电准备中的枪
            int chargeFinsh = 0;// 充电完成中的枪
            int offLine = 0;// 离线中的枪
            int problem = 0;// 故障中的枪
            int bespeak = 0;// 预约中的枪
            List<GunMonitorDto> guns = memoryData.getGunByStation(stationId);
            if (CollectionUtil.isNotEmpty(guns)) {
                for (GunMonitorDto gunMonitorDto : guns) {
                    gunCount++;
                    // GunStateEnum 类型对应数字请查找这个枚举
                    // 充电枪信息统计
                    switch (gunMonitorDto.getGunState()) {
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
                            chargeFinsh++;
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
            retMap.put("code", "0");
            // 保存各类枪的总数
            retMap.put("gunCount", gunCount);
            retMap.put("charging", charging);
            retMap.put("free", free);
            retMap.put("chargePrepare", chargePrepare);
            retMap.put("chargeFinsh", chargeFinsh);
            retMap.put("offLine", offLine);
            retMap.put("problem", problem);
            retMap.put("bespeak", bespeak);
        } catch (Exception e) {
            retMap.put("code", "1");

        }
        return retMap;
    }

    /**
     * @Title
     * @Description：根据站点id获取这个月每天服务的车辆数
     * @param stationId 站点id
     * @Return： List<Map<String, Object>> carNums(服务的车辆数) charingBalance(充电收入(分)) date(日期
     * @author <a href="mail to: *******@******.com" rel="nofollow">huangxw</a>
     * @CreateDate：2019年9月27日15:15:53 </ p>
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    public List<Map<String, Object>> getCarSumData(Long stationId) {
        Date date = new Date();
        List<Map<String, Object>> carSumData = new ArrayList<>();
        List<String> timeList = TimeUtils.getTimeList(TimeUtils.getStartDate(date), TimeUtils.getEndDate(date), TimeSharingEnum.Day);
        StationShowDay res = new StationShowDay();
        //1、开始遍历
        for (String dateTime: timeList) {
            // redis-key：SDT_站点ID_商家号_日期(yyyy-MM-dd)_查询的字段名
            //日站点服务公交车数量
            String busCarNumKey = String.format("ADT_%s_%s_%s", stationId, sellerId, dateTime, "day_station_service_bus_car_num");
            //日站点服务社会车数量
            String otherNumKey = String.format("ADT_%s_%s_%s", stationId, sellerId, dateTime, "day_station_service_other_car_num");
            //日站点总收入
            String dayStationBalanceCountKey = String.format("ADT_%s_%s_%s", stationId, sellerId, dateTime, "day_station_balance_count");

            //2、 如果redis中不存在数据从数据库中获取数据集
            if (redisCache.get(busCarNumKey) == null || redisCache.get(otherNumKey) == null ) {
//                res = stationShowDayRepository.getTodayServideCarSum(stationId, sellerId, dateTime);
                res = stationShowDayMapper.get(stationId,sellerId,dateTime);
            }

            Map<String,Object> map = new HashMap<>();

            //3、 获取日站点服务公交车数量
            Long carNums = 0L;
            //3,1如果redis有数据从redis中取
            if (redisCache.get(busCarNumKey) != null) {
                carNums = ValUtil.toLong(redisCache.get(busCarNumKey));
            } else {
                //3.2如果redis不存在从数据库获取
                if (res != null) {
                    //3.1.1取出的数据存进redis
                    redisCache.put(busCarNumKey, res.getDayStationServiceBusCarNum());
                    carNums = ValUtil.toLong(res.getDayStationServiceBusCarNum());
                }
            }

            //4、 获取日站点服务社会车数量
            Long otherNums = 0L;
            //4,1如果redis有数据从redis中取
            if (redisCache.get(otherNumKey) != null) {
                otherNums = ValUtil.toLong(redisCache.get(otherNumKey), 0L);
            } else {
                //4.2如果redis不存在从数据库获取
                if (res != null) {
                    //4.1.1取出的数据存进redis
                    redisCache.put(otherNumKey, res.getDayStationServiceOtherCarNum());
                    otherNums = ValUtil.toLong(res.getDayStationServiceOtherCarNum());
                }
            }


            //5、 获取日站点充电收入
            Long dayStationBalance = 0L;
            //5,1如果redis有数据从redis中取
            if (redisCache.get(dayStationBalanceCountKey) != null) {
                dayStationBalance = ValUtil.toLong(redisCache.get(dayStationBalanceCountKey), 0L);
            } else {
                //5.2如果redis不存在从数据库获取
                if (res != null) {
                    //5.2.1取出的数据存进redis
                    redisCache.put(dayStationBalanceCountKey, res.getDayStationBalanceCount());
                    dayStationBalance = ValUtil.toLong(res.getDayStationBalanceCount(), 0L);
                }
            }
            map.put("carNums", carNums+otherNums);
            map.put("date",dateTime);
            map.put("charingBalance",dayStationBalance);
            carSumData.add(map);
        }

        return carSumData;

    }

    @Override
    public List<Object> doGetGunData(Long stationID) {
        Map<String, Object> map = null;
        List<Object> result = new CopyOnWriteArrayList<>();
        List<PileDto> pileByStationID = memoryData.getPileByStation(stationID);
        for (PileDto pileDto : pileByStationID) {
            map = new ConcurrentHashMap<>();
            List<GunMonitorDto> gunByPile = memoryData.getGunByPile(pileDto.getId());
            JSONObject gunJson = null;
            List<JSONObject> gunInfoList = new CopyOnWriteArrayList<>();

            for (GunMonitorDto gunMonitorDto : gunByPile) {

                gunJson = new JSONObject();
                gunJson.put("soc", val(gunMonitorDto.getSoc()));
                gunJson.put("remainTime", val(gunMonitorDto.getRemainTime()));
                gunJson.put("current", val(gunMonitorDto.getCurrent()/1000));
//todo                gunJson.put("pileName", val(gunMonitorDto.getPileDto().getName()));
                gunJson.put("pileName", val(gunMonitorDto.getPileNo()));
                gunJson.put("gunNo", val(gunMonitorDto.getGunNo()));
                gunJson.put("gunState", val(gunMonitorDto.getGunState()));
                gunJson.put("power", val(ParseUtils.whParseKwh(Long.valueOf(gunMonitorDto.getPower()))));
                gunInfoList.add(gunJson);
            }
            map.put("pileName", val(pileDto.getName()));
            map.put("pileID", val(pileDto.getId()));
            map.put("gunJson", gunInfoList);

            result.add(map);
        }
        return result;
    }

    @Override
    public List<StationDto> doGetStationList() {

        List<StationDto> stations = null;
        List<StationDto> rettuList = new LinkedList<>();
        List<Station> lst = null;

//        lst = stationRepository.findBySellIdAndDeleteFlag(sellerId,false);
        lst = stationMapper.findBySeller(sellerId);
        if (CollectionUtils.isNotEmpty(lst)) {
            for (Station station : lst) {
                StationDto dto = new StationDto();
                BeanUtils.copyProperties(station, dto);
                dto.setId(station.getId());

                if(station.getStatus() != null){
                    dto.setStationEnums(StationEnum.getEnumByName(station.getStatus()));
                }

//                if (station.getStationParent() != null) {
//                    dto.setStationId(station.getStationParent().getId());
//                }
                if (station.getSellerId() != null) {
                    dto.setSellerId(station.getSellerId());
                }
                if(station.getStationType()!=null){
                    dto.setStationTypeEnum(StationTypeEnum.getEnumByCode(station.getStationType().intValue()));
                }
                if(station.getRunType() != null){
                    dto.setRunType(PileOperateTypeEnum.valueOf(station.getRunType()));
                }

                if(station.getConstruction()!=null){
                    dto.setConstructionEnum(ConstructionEnum.getEnumByCode(station.getConstruction().intValue()));
                }
                if (station.getAreaId() != null) {
                    dto.setAreaid(station.getAreaId());
                }
                if(StringUtils.isNotBlank(station.getConfigType().toString())){
                    dto.setConfigTypeEnum(station.getConfigType());
                }else{
                    dto.setConfigTypeEnum(StationConfigTypeEnum.WeiSheZhi);
                }
                    dto.setGoodsMachine(false);

                rettuList.add(dto);
            }
        }
        return stations;
    }

    @Override
    public Map<String, Object> doGetStationInfoByID(Long stationID) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        Map<String, Object> stationData = getStationData(stationID, sellerId);
        if (stationData.isEmpty()) {
            result.put("code", 1);
            return result;
        }
        return stationData;

    }

    @Override
    public Map<String, Object> doGetStationEquipmentInfo(Long stationID) {
        Map<String, Object> result = new ConcurrentHashMap<>();
//        List<PileDto> pileByStationID = memoryData.getPileByStation(stationID);
//        Double allPower = 0.00;// 设备总功率
//        for (PileDto pile : pileByStationID) {
//            if (pile.getPileTypeDto().getPower() != null) {
//                Double power = Double.valueOf(pile.getPileTypeDto().getPower());
//                allPower += power;
//            }
//        }
//        new ParseUtils();

        StationShowTotal t = stationShowTotalMapper.get(stationID,sellerId);

        result.put("variableVoltagePower", 0);
        result.put("totalPowerOfEquipment", t.getTotalStationIPowerCount());
        result.put("chargingEnergy", ParseUtils.whParseKwh(t.getTotalStationChangePowerCount().longValue()));

        return result;
    }

    @Override
    public JSONArray getStationInServiceMapData() {

        JSONArray resultJson = new JSONArray();
        List<StationDto> stationBySeller = memoryData.getStationBySeller(sellerId);
        if(CollectionUtil.isEmpty(stationBySeller))return resultJson;
        JSONObject stationJson = null;
        List<GunMonitorDto> gunByStation = null;
        Set<String> memberNoSet = null;
        for(StationDto station : stationBySeller) {
            memberNoSet = new HashSet<>();
            gunByStation = memoryData.getGunByStation(station.getId());
            if(!org.springframework.util.CollectionUtils.isEmpty(gunByStation)) {
                int inServiceUsers = 0;
                for(GunMonitorDto gun : gunByStation) {
                    if(gun.getRealGunState() == 3 && StringUtils.isNotBlank(gun.getMemberNo()))memberNoSet.add(gun.getMemberNo());
                    else if(gun.getRealGunState() == 3)inServiceUsers++;
                }
                stationJson = new JSONObject();
                stationJson.put("stationId", station.getId());
                stationJson.put("stationName", station.getName());
                stationJson.put("stationInServiceUserCount", memberNoSet.size()+inServiceUsers);
                stationJson.put("lng", station.getLongitude());
                stationJson.put("lat", station.getLatitude());
                resultJson.add(stationJson);
            }
        }
        return resultJson;
    }

    @Override
    public List<StationDto> getStationDto() {
        return stationMapper.getStationDtoListBySellerId(sellerId);
    }

    @Override
    public Map<String, Object> doHistoryTotalCharging() {
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();

        //公交车辆充电占其他车辆充电的占比值--carChargingPercent
        Map<String,Object> sumRate = getSumPowerAndRate();
        double percent=(double) sumRate.get("rate");
        map.put("carChargingPercent", percent);

        //累计充电量的历史总的充电量sumPower千瓦
        long sumPower=(long) sumRate.get("sumPower");
        map.put("sumPower", TypeRotationUtil.stringTurnKw2(sumPower));

        //显示近12个月公交车辆和其他车辆充电的每月充电量---team12ChargingData
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        Map<String, Object> teamAndPersonChargePower = getTeamAndPersonChargePower();
        List timeList=(List) teamAndPersonChargePower.get("timeList");
        List personPowerList=(List) teamAndPersonChargePower.get("personPowerList");
        List teamPowerList=(List) teamAndPersonChargePower.get("teamPowerList");
        Map<String, Object> dataMap = null;
        for (int i = 0; i < timeList.size(); i++){
            dataMap = new HashMap<String, Object>();
            dataMap.put("data", timeList.get(i));
            dataMap.put("公交车", TypeRotationUtil.stringTurnKw2((Long)teamPowerList.get(i)));
            dataMap.put("其他", TypeRotationUtil.stringTurnKw2((Long)personPowerList.get(i)));
            data.add(dataMap);
        }
        map.put("team12ChargingData", data);

        return map;
    }

    @Override
    public List<Map<String, Object>> doGetUserServiceNum(Long stationID) {
        List<Map<String, Object>> userServiceNum = new ArrayList<>();

        //根据站点Id统计总共服务用户分级数量和占比
        StationShowTotal data = stationShowTotalMapper.get(stationID,sellerId);

        //1、获取站点服务个人用户总数
        BigInteger totalNum = ValUtil.valN(data.getTotalStationServiceUseCount());

        //2、获取站点服务个人银卡用户总数
        BigInteger silverUseCount = ValUtil.valN(data.getTotalStationServiceSilverUseCount());
        double resSilverUseCountRate = 0D;
        Map map =  new HashMap();
        if(!BigInteger.ZERO.equals(silverUseCount)){
            resSilverUseCountRate =  silverUseCount.divide(totalNum).doubleValue();
        }
        map.put("memNums",silverUseCount.intValue());
        map.put("grade",0);
        map.put("proportion", resSilverUseCountRate);
        userServiceNum.add(map);

        //3、获取站点服务个人金卡用户总数
        BigInteger serviceGoldUseCount = ValUtil.valN(data.getTotalStationServiceGoldUseCount());
        double serviceGoldUseCountRate = 0D;
        Map goldUseCountMap =  new HashMap();
        if(!BigInteger.ZERO.equals(serviceGoldUseCount)){
            serviceGoldUseCountRate =  serviceGoldUseCount.divide(totalNum).doubleValue();
        }
        goldUseCountMap.put("memNums",serviceGoldUseCount);
        goldUseCountMap.put("grade",1);
        goldUseCountMap.put("proportion", serviceGoldUseCountRate);
        userServiceNum.add(goldUseCountMap);

        //4、获取站点服务个人白金卡用户总数
        BigInteger platinumUseCount = ValUtil.valN(data.getTotalStationServicePlatinumUseCount());
        double platinumUseCountRate = 0D;
        Map platinumUseMap =  new HashMap();
        if(!BigInteger.ZERO.equals(platinumUseCount)){
            platinumUseCountRate =  platinumUseCount.divide(totalNum).doubleValue();
        }
        platinumUseMap.put("memNums",platinumUseCount);
        platinumUseMap.put("grade",2);
        platinumUseMap.put("proportion", platinumUseCountRate);
        userServiceNum.add(platinumUseMap);

        //5、站点服务个人钻石卡用户总数
        BigInteger diamondCount = ValUtil.valN(data.getTotalStationServiceDiamondUseCount());
        double diamondRate = 0D;
        Map diamondMap =  new HashMap();
        if(!BigInteger.ZERO.equals(platinumUseCount)){
            diamondRate =  platinumUseCount.divide(totalNum).doubleValue();
        }
        diamondMap.put("memNums",diamondCount);
        diamondMap.put("grade",3);
        diamondMap.put("proportion", diamondRate);
        userServiceNum.add(diamondMap);

        //6、获取站点服务个人黑金卡用户总数
        BigInteger blackgoldCount = ValUtil.valN(data.getTotalStationServiceBlackgoldUseCount());
        double blackgoldRate = 0D;
        Map blackgoldMap =  new HashMap();
        if(!BigInteger.ZERO.equals(platinumUseCount)){
            blackgoldRate =  platinumUseCount.divide(totalNum).doubleValue();
        }
        blackgoldMap.put("memNums",blackgoldCount);
        blackgoldMap.put("grade",4);
        blackgoldMap.put("proportion", blackgoldRate);
        userServiceNum.add(blackgoldMap);


        return userServiceNum;
    }

    @Override
    public Map<String, Object> doGetStationMemData(Long stationID) {
        String date = TimeUtils.format(new Date(),"yyyy-MM");
        DecimalFormat format = new DecimalFormat("0.00");
        StationShowMonth data = stationShowMonthMapper.get(stationID,sellerId,date);
        Map<String,Object> map = new HashMap<>();

        //1、获取站点服务用户总数 = month_pile_personal_num_count 月个人会员订单总数
        map.put("allservice",ValUtil.toLong(data.getMonthPilePersonalNumCount(),0L));

        //2、获取人均充电消费(分) = 站点个人用户累计充电消费总金额÷站点个人用户累计消费订单总数=人均充电消费（元/次）*********************       待确定 ******************
        //2.1、站点个人用户累计充电消费总金额
        BigInteger powerBalanceCount = ValUtil.valN(stationShowTotalMapper.getUserTotalCharge(stationID,sellerId));
        //2.2、站点个人用户累计消费订单总数
        BigInteger orderNumber = ValUtil.valN(stationShowMonthMapper.getUserCumulativeOrderNumber(stationID,sellerId));

        if(BigInteger.ZERO.equals(orderNumber)){
            map.put("perCapitaCharging",0) ;
        }else{
            map.put("perCapitaCharging",powerBalanceCount.divide(orderNumber).longValue());
        }
        //3、获取月充电预约数 = 当月个人会员在该场站预约充电成功的用户数量
        map.put("monthAppointment",ValUtil.toLong(data.getMonthPersonalBookedNum(),0L));

        //4、获取月活动参与数 =  显示个人会员当月在该站点享受过充电折扣、抵扣或其他优惠的订单数
        map.put("monthEvent",ValUtil.toLong(data.getMonthPersonalVisitNum(),0L));



        String moreThan5FansRateKey = "moreThan5FansRate-"+stationID+"-"+sellerId+"-"+date;
        if(redisCache.get(moreThan5FansRateKey) == null){
            // 这两个数值查询起来对mysql消耗较大 所以交给redis  降低查询频次
            //5、获取总用户
            double totalFans = stationShowMonthMemberMapper.getTotalFansData(stationID,sellerId,date).doubleValue();
            //6、获取忠实粉丝充电次数大于等于5次以上的
            double stationMemData = stationShowMonthMemberMapper.getThanFiveFansData(stationID,sellerId,date).doubleValue();
            map.put("fans",0F);
            if( totalFans != 0 ){
                double fansRate = ValUtil.div(stationMemData*100,totalFans,2);
                map.put("fans",fansRate);
                redisCache.put(moreThan5FansRateKey,fansRate);
            }
        }else{
            map.put("fans",Double.valueOf(redisCache.get(moreThan5FansRateKey)));
        }


        //7、获取月均人均充电(次) = 个人会员当月在该场站充电订单总数÷在该场站月充电会员数
        //个人会员当月在该场站充电订单总数
        StationShowMonth res = stationShowMonthMapper.get(stationID,sellerId,date);
        map.put("chargingConsumption",0L);
        if( res != null ){
            double toTalOrderCount = res.getMonthPilePersonalNumCount().doubleValue();
            double memberCount = res.getMonthChargePersonalNumCount().doubleValue();
            map.put("chargingConsumption",ValUtil.div(toTalOrderCount,memberCount,2));
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> getMemberAddByMonth(Long stationID) {
        //初始化日期集合
        List<String> nowOfTwelveMonth = TimeUtils.getNowOfTwelveMonth();
        //返回数据集
        List<Map<String, Object>> list = new ArrayList<>();
        //1、开始遍历用户增长数量集
        for (int i = nowOfTwelveMonth.size() - 1; i >= 0; i--) {
            Map<String,Object> map = new HashMap<>();
            String time = nowOfTwelveMonth.get(i);
            StationShowMonth data = stationShowMonthMapper.get(stationID,sellerId,time);
            if(data != null){
                map.put("time",time);
                map.put("memberNum", ValUtil.valN(data.getMonthGrouthPersonalNum()));
                list.add(map);
            }else{
                map.put("time",time);
                map.put("memberNum",0);
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getMemberByHours(Long stationId) {
        Date date = new Date();
        String startTime = (TimeUtils.formatFullTime(TimeUtils.getStartDate(date))).substring(0,7);
        StationShowMonth s = stationShowMonthMapper.get(stationId,sellerId,startTime);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if(s == null){
            for(int j = 0;j <24;j++){
                Map<String,Object> map = new HashMap<>();
                map.put("charingNum",0);
                if( j == 0 ){
                    map.put("time",24);
                }else{
                    map.put("time", j);
                }
                map.put("time",j);
                mapList.add(map);
            }
            return mapList;
        }

        JSONArray data = new JSONArray();
        data.add(s.getMonthHourZeroChargeCount());
        data.add(s.getMonthHourOneChargeCount());
        data.add(s.getMonthHourTwoChargeCount());
        data.add(s.getMonthHourThreeChargeCount());
        data.add(s.getMonthHourFourChargeCount());
        data.add(s.getMonthHourFiveChargeCount());
        data.add(s.getMonthHourSixChargeCount());
        data.add(s.getMonthHourSevenChargeCount());
        data.add(s.getMonthHourEightChargeCount());
        data.add(s.getMonthHourNineChargeCount());
        data.add(s.getMonthHourTenChargeCount());
        data.add(s.getMonthHourElevenChargeCount());
        data.add(s.getMonthHourTwelveChargeCount());
        data.add(s.getMonthHourThirteenChargeCount());
        data.add(s.getMonthHourFourteenChargeCount());
        data.add(s.getMonthHourFifteenChargeCount());
        data.add(s.getMonthHourSixteenChargeCount());
        data.add(s.getMonthHourSeventeenChargeCount());
        data.add(s.getMonthHourEighteenChargeCount());
        data.add(s.getMonthHourNineteenChargeCount());
        data.add(s.getMonthHourTwentyChargeCount());
        data.add(s.getMonthHourTwentyOneChargeCount());
        data.add(s.getMonthHourTwentyTwoChargeCount());
        data.add(s.getMonthHourTwentyThreeChargeCount());


        for(int i=0;i < data.size(); i++){
            Map<String,Object> map = new HashMap<>();
            if(i == 0 ){
                map.put("time",24);
            }else{
                map.put("time",i);
            }
            BigInteger val = ValUtil.toBigInteger(data.get(i));
            map.put("charingNum", val.intValue());
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * @Title
     * @Description：根据站点id显示当月在该场站重复充电大于等于20次、10次、5次的个人会员数量占比
     * @Param：stationId 站点id sellerId 商户id
     * @Return：Map<String, Object>  小于5次  大于5次 大于10次 大于20次
     */
    @Override
    public Map<String, Object> getFansProportion(Long stationId) {
        Date date = new Date();
        String startTime = (TimeUtils.formatFullTime(TimeUtils.getStartDate(date))).substring(0,7);
        Map<String,Object> map = new HashMap<>();

        StationShowMonth s = stationShowMonthMapper.get(stationId,sellerId,startTime);
        double sum = 0;
        if(s != null){
            sum = s.getMonthChargePersonalNumCount().doubleValue();
        }else{
            map.put("oneToFive", 0);
            map.put("fiveToTen", 0);
            map.put("tenToTwenty", 0);
            map.put("twentyTo", 0);
            return map;
        }

        //小于5次
        double oneToFive = stationShowMonthMemberMapper.getLessFiveRate(stationId,sellerId,startTime).doubleValue();
        map.put("oneToFive", ValUtil.div(oneToFive,sum,2)*100);


        //大于5次
        double thanFive = stationShowMonthMemberMapper.getThanFiveRate(stationId,sellerId,startTime).doubleValue();
        map.put("fiveToTen", ValUtil.div(thanFive,sum,2)*100);

        //大于10次
        double thanTen = stationShowMonthMemberMapper.getThanTenRate(stationId,sellerId,startTime).doubleValue();
        map.put("tenToTwenty", ValUtil.div(thanTen,sum,2)*100);

        //大于20次
        double thanTwenty = stationShowMonthMemberMapper.getThanTwentyRate(stationId,sellerId,startTime).doubleValue();
        map.put("twentyTo", ValUtil.div(thanTwenty,sum,2)*100);

        return map;
    }

    /**
     * @Title
     * @Description：根据站点id显示当月在该场站充电消费排名前8 的个人会员用户
     * @Param： tationId 站点id sellerId 商户id
     * @Return：Lis</map> key : image(用户头像) name(用户名称) charingNum(充电次数) allbalance(消费总金额(分))
     */
    @Override
    public List<Map<String, Object>> getConsumptionRanking(Long stationId) {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String startTime = dft.format(cal.getTime());
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<StationShowMonthMemberDTO> members = stationShowMonthMemberMapper.getStationConsumptionRanking(stationId,sellerId,startTime);
        if(members == null){
            Map<String,Object> map = new HashMap<>();
            map.put("charingNum",0);
            map.put("allbalance",0L);
            map.put("name", null);
            map.put("image",null);
            mapList.add(map);
            return mapList;
        }
        for(StationShowMonthMemberDTO s : members){
            Map<String,Object> map = new HashMap<>();
            Member m = memberMapper.get(s.getLMemberId());
            map.put("image",m.getIcon());

            //String newname = CodeUtil.decodeUTF8(name);
            map.put("name",m.getName());

            map.put("charingNum",s.getMonthMemberChargeCount());
            map.put("allbalance",s.getMonthTeamBalanceCount());
            mapList.add(map);
        }
        return mapList;
    }

    private Map<String, Object> getTeamAndPersonChargePower(){
        Map<String, Object> mapData = new HashMap<>();
        //日期集合
        List<String> timeList = new ArrayList<>();
        for (int i = 11; i >= 0; i--) {
            String time = TimeUtils.format(TimeUtils.addMonth(new Date(), -i), "yyyy-MM");
            timeList.add(time);
        }
        List<Long> personPowerList = new ArrayList<>();
        List<Long> teamPowerList = new ArrayList<>();
        //1、开始遍历数据集
        for (String date : timeList) {
            AllMonthData a = allMonthDataMapper.get(sellerId,date);
            if(a != null){
                if(a.getMonthTeamChargingPower() != null){
                    teamPowerList.add(a.getMonthTeamChargingPower().longValue());
                }else{
                    teamPowerList.add(0l);
                }
                if(a.getMonthPersonalChargingPower() != null){
                    personPowerList.add(a.getMonthPersonalChargingPower().longValue());
                }else {
                    personPowerList.add(0L);
                }

            } else{
                teamPowerList.add(0l);
                personPowerList.add(0L);
            }
        }
        mapData.put("timeList", timeList);
        mapData.put("personPowerList", personPowerList);
        mapData.put("teamPowerList", teamPowerList);
        return mapData;

    }

    private Map<String,Object> getSumPowerAndRate(){
        Map<String, Object> mapDatas = new HashMap<>();
        long sumPower = 0L;
        long temPower = 0L;
        double rate = 0;

        try {
            sumPower = totalOperateMapper.get(sellerId).getTotalChargingPower().longValue();
            temPower = totalOperateMapper.get(sellerId).getTeamChargingPower().longValue();

            if(sumPower != 0){
                rate = BigDecimal.valueOf(temPower*100).divide(BigDecimal.valueOf(sumPower), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            mapDatas.put("sumPower", sumPower);
            mapDatas.put("rate", rate);
        }catch (Exception e){
            e.printStackTrace();
            mapDatas.put("sumPower", sumPower);
            mapDatas.put("rate", rate);
            return mapDatas;
        }
        return mapDatas;
    }


    private Map<String, Object> getStationData(Long stationId,Long sellerId){
        StationInfoDTO stationData = stationMapper.getStationDatanum(stationId,sellerId);
        if(stationData ==null){
            Map<String,Object> map = new HashMap<>();
            map.put("stationImage", "0");
            map.put("stationName","0");
            map.put("stationaddres","0");
            map.put("stationRating",0);
            map.put("stationPile",0);
            map.put("electricityPrice",0L);
            return map;
        }
        Map<String,Object> map = new HashMap<>();

        map.put("stationImage", ValUtil.toString(stationData.getImage(),"0"));
        map.put("stationName",ValUtil.toString(stationData.getName(),"0"));
        map.put("stationaddres",ValUtil.toString(stationData.getAddress(),"0"));
        map.put("stationRating",ValUtil.toInteger(stationData.getScore(),0));
        map.put("stationPile",ValUtil.toInteger(stationData.getPilesum(),0));
//        Long electricityPrice = ValUtil.toDouble(object[5],0D).longValue();
//        map.put("electricityPrice",electricityPrice);
        return map;
    }

    /**
     * @Title
     * @Description：根据站点id获取总服务次数，总电量，总收入，充电峰谷,今日总服务次数，总电量，总收入统计
     * @Param：  stationId 站点id sellerId 商户id
     * @Return：  Map servicesNums(总服务次数) totalPower(总电量(w)) totalRevenue(总收入(分)) jianCharge(尖电量)(w) fengCharge(峰电量(w))
     * 	 pingCharge(平电量(w)) guCharge(谷电量(w)) todayServicesNums(今日总服务次数) todayTotalPower(总电量(w)) todayTotalRevenue(总收入统计(分))
     * @author <a href="mail to: *******@******.com" rel="nofollow">huangxw</a>
     * @CreateDate：2019年9月23日11:41:37
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    private Map<String,Object> getStationBalanceData(Long stationId,Long sellerId) {

        // station_show_total_table redis-key：STT_站点ID_商家号_查询的字段名   总表
        String date = TimeUtils.format(new Date(), "yyyy-MM-dd");

        StationShowTotal totalData = stationShowTotalMapper.getBySeller(stationId,sellerId);



        Map<String,Object> map = new HashMap<>();

        //1、总服务次数
            if( totalData != null ){
                map.put("servicesNums", ValUtil.toLong(totalData.getTotalStationServiceCount(),0L));
            }else{
                //1.2如果如果数据库中不存在
                map.put("servicesNums", 0L);
            }

        //2、总电量

            if( totalData != null ){
                map.put("totalPower", ValUtil.toLong(totalData.getTotalStationPowerCount(),0L));
            }else{
                //2.2如果如果数据库中不存在
                map.put("totalPower", 0L);
            }

        //3、总收入
            if( totalData != null ){
                map.put("totalRevenue", ValUtil.toLong(totalData.getTotalStationMoneyCount(),0L));
            }else{
                //3.2如果如果数据库中不存在
                map.put("totalRevenue", 0L);
            }



        //4、充电尖总充电量
            if( totalData != null ){
                map.put("jianCharge", ValUtil.toLong(totalData.getTotalStationJianPowerCount(),0L));
            }else{
                //4.2如果如果数据库中不存在
                map.put("jianCharge", 0L);
            }

        //5、充电峰总充电量
            if( totalData != null ){
                map.put("fengCharge", ValUtil.toLong(totalData.getTotalStationFengPowerCount(),0L));
            }else{
                map.put("fengCharge", 0L);
            }


        //6、充电平总充电量
            if( totalData != null ){
                map.put("pingCharge", ValUtil.toLong(totalData.getTotalStationPingPowerCount(),0L));
            }else{
                //6.2如果如果数据库中不存在
                map.put("pingCharge", 0L);
            }

        //7、充电谷总充电量
            if( totalData != null ){
                map.put("guCharge", ValUtil.toLong(totalData.getTotalStationGuPowerCount(),0L));
            }else{
                //7.2如果如果数据库中不存在
                map.put("guCharge", 0L);
            }

        StationShowDay dayData = stationShowDayMapper.get(stationId,sellerId,date);


        //8、今日服务次数
            if( dayData != null ){
                map.put("todayServicesNums", ValUtil.toLong(dayData.getDayStationPileCount(),0L));
            }else{
                //8.2如果如果数据库中不存在
                map.put("todayServicesNums", 0L);
            }

        //9、今日总电量
            if( dayData != null ){
                map.put("todayTotalPower", ValUtil.toLong(dayData.getDayStationPowerCount(),0L));
            }else{
                //9.2如果如果数据库中不存在
                map.put("todayTotalPower", 0L);
            }

        //10、今日总收入统计
            if( dayData != null ){
                map.put("todayTotalRevenue", ValUtil.toLong(dayData.getDayStationBalanceCount(),0L));
            }else{
                //10.2如果如果数据库中不存在
                map.put("todayTotalRevenue", 0L);
            }

        return map;
    }

    private Map<String,Object> getStationBalanceData(Long stationId, Long sellerId, Date date) {

//        String startTime = TimeUtils.formatFullTime(TimeUtils.getStartDate(date));
//        String endTime = TimeUtils.formatFullTime(TimeUtils.getEndDate(date));
        String time = TimeUtils.formatYearAndMonth(date);
//        List<Object> stationBalanceData1 = stationShowDayRepository.getStationBalanceData(stationId, sellerId,startTime, endTime);
        StationShowDay data = stationShowDayMapper.get(stationId,sellerId,time);
        if(data == null){
            Map<String,Object> map = new HashMap<>();
            map.put("monthServicesNums", 0L);
            map.put("monthTotalPower",0L);
            map.put("monthTotalRevenue",0L);
            return map;
        }

        Map<String,Object> map = new HashMap<>();
        map.put("monthServicesNums", ValUtil.toLong(data.getDayStationPileCount(),0L));
        map.put("monthTotalPower", ValUtil.toLong(data.getDayStationPowerCount(),0L));
        map.put("monthTotalRevenue", ValUtil.toLong(data.getDayStationBalanceCount(),0L));
        return map;
    }
}
