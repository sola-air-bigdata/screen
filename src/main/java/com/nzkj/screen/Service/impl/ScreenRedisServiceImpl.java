package com.nzkj.screen.Service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.DTO.*;
import com.nzkj.screen.Entity.Station;
import com.nzkj.screen.Entity.StationShowTotal;
import com.nzkj.screen.Repo.PileRepository;
import com.nzkj.screen.Repo.StationRepository;
import com.nzkj.screen.Repo.StationShowDayRepository;
import com.nzkj.screen.Repo.StationShowTotalRepository;
import com.nzkj.screen.Service.ScreenRedisService;
import com.nzkj.screen.Service.StationService;
import com.nzkj.screen.Utils.ParseUtils;
import com.nzkj.screen.Utils.RedisCacheUtil;
import com.nzkj.screen.Utils.TimeUtils;
import com.nzkj.screen.Utils.ValUtil;
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
import java.math.BigInteger;
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
    private StationShowTotalRepository stationShowTotalRepository;

    @Autowired
    private StationShowDayRepository stationShowDayRepository;

    @Autowired
    private StationService stationService;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PileRepository pileRepository;

    @Autowired
    private IMemoryData memoryData;

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
//        try {
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
                    // 充电枪信息统计
                    switch (gunMonitorDto.getGunState()) {
                        case CHARGING:
                            // 充电中的电枪
                            charging++;
                            break;

                        case FREE:
                            // 空闲中的电枪
                            free++;
                            break;

                        case CHARGEFINISH:
                            // 已充满的枪
                            chargeFinsh++;
                            break;
                        case OFFLINE:
                            // 离线中的电枪
                            offLine++;
                            break;
                        case CHARGEPREPARE:
                            // 充电准备中的电枪
                            chargePrepare++;
                            break;
                        case BESPEAK:
                            // 预约的电枪
                            bespeak++;
                            break;
                        case Problem:
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
//        } catch (Exception e) {
//            retMap.put("code", "1");
//
//        }
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
        Object res = new Object();
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
                res = stationShowDayRepository.getTodayServideCarSum(stationId, sellerId, dateTime);
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
                    Object[] obj = (Object[]) res;
                    //3.1.1取出的数据存进redis
                    redisCache.put(busCarNumKey, obj[0]);
                    carNums = ValUtil.toLong(obj[0]);
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
                    Object[] obj = (Object[]) res;
                    //4.1.1取出的数据存进redis
                    redisCache.put(otherNumKey, obj[1]);
                    otherNums = ValUtil.toLong(obj[1]);
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
                    Object[] obj = (Object[]) res;
                    //5.2.1取出的数据存进redis
                    redisCache.put(dayStationBalanceCountKey, obj[2]);
                    dayStationBalance = ValUtil.toLong(obj[2], 0L);
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
                gunJson.put("pileName", val(gunMonitorDto.getPileDto().getName()));
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

        lst = stationRepository.findBySellIdAndDeleteFlag(sellerId,false);
        if (CollectionUtils.isNotEmpty(lst)) {
            for (Station station : lst) {
                StationDto dto = new StationDto();
                BeanUtils.copyProperties(station, dto);
                dto.setId(station.getId());

                if(StringUtils.isNotBlank(station.getStatus())){
                    dto.setStationEnums(StationEnum.getEnumByName(station.getStatus()));
                }

                if (station.getStationParent() != null) {
                    dto.setStationId(station.getStationParent().getId());
                }
                if (station.getSellId() != null) {
                    dto.setSellerId(station.getSellId());
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
                if (station.getArea() != null) {
                    dto.setAreaid(station.getArea().getId());
                }
                if(StringUtils.isNotBlank(station.getConfigType())){
                    dto.setConfigTypeEnum(StationConfigTypeEnum.valueOf(station.getConfigType()));
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
        List<PileDto> pileByStationID = memoryData.getPileByStation(stationID);
        Double allPower = 0.00;// 设备总功率
        for (PileDto pile : pileByStationID) {
            if (pile.getPileTypeDto().getPower() != null) {
                Double power = Double.valueOf(pile.getPileTypeDto().getPower());
                allPower += power;
            }
        }
        result.put("totalPowerOfEquipment", allPower);

//        Integer transformerPower = 0;// 变压功率
//        TransformerDto transformerDto = new TransformerDto();
//        transformerDto.setStationId(stationID);
//        List<TransformerDto> list = transformerManager.getList(transformerDto);
//        for (TransformerDto transformer : list) {
//            transformerPower += transformer.getPowerRate();
//        }
//        result.put("variableVoltagePower", transformerPower);
        new ParseUtils();
        // 充电站点电能
        StationShowTotal t = stationShowTotalRepository.getByL_station_id(stationID);

        result.put("chargingEnergy", ParseUtils.whParseKwh(t.getTotal_station_i_power_count().longValue()));

        return result;
    }

    @Override
    public JSONArray getStationInServiceMapData() {
        //        JSONArray resultJson = new JSONArray();
//
//        //从redis里取 这个商家下所有站点信息  或者从数据库取并存入redis
//        JSONArray stationBySeller = getStationInfo();
//
//
//        if(CollectionUtils.isEmpty(stationBySeller))return resultJson;
//        JSONObject stationJson = null;
//        List<GunMonitorDto> gunByStation = null;
//        Set<String> memberNoSet = null;
        JSONArray resultJson = new JSONArray();
        List<StationDto> stationBySeller = memoryData.getStationBySeller(sellerId);
        if(CollectionUtil.isEmpty(stationBySeller))return resultJson;
        JSONObject stationJson = null;
        List<GunMonitorDto> gunByStation = null;
        Set<String> memberNoSet = null;
        for(Object o : stationBySeller) {
            JSONObject station = (JSONObject) o;
            memberNoSet = new HashSet<>();
            gunByStation = stationService.getGunByStation(station.getLong("stationId"));
            if(!org.springframework.util.CollectionUtils.isEmpty(gunByStation)) {
                int inServiceUsers = 0;
                for(GunMonitorDto gun : gunByStation) {
                    if(gun.getGunState() == GunStateEnum.CHARGING && com.nzkj.screen.Utils.StringUtils.isNotEmpty(gun.getMemberNo()))memberNoSet.add(gun.getMemberNo());
                    else if(gun.getGunState() == GunStateEnum.CHARGING)inServiceUsers++;
                }
                stationJson = new JSONObject();
                stationJson.put("stationId", station.get("stationId"));
                stationJson.put("stationName", station.get("stationName"));
                stationJson.put("stationInServiceUserCount", memberNoSet.size()+inServiceUsers);
                stationJson.put("lng", station.get("lng"));
                stationJson.put("lat", station.get("lat"));
                resultJson.add(stationJson);
            }
        }
        return resultJson;
    }

    private Map<String, Object> getStationData(Long stationId,Long sellerId){
        Object stationData = stationRepository.getStationDatanum(stationId,sellerId);
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
        Object[] object = (Object[]) stationData;
        map.put("stationImage", ValUtil.toString(object[0],"0"));
        map.put("stationName",ValUtil.toString(object[1],"0"));
        map.put("stationaddres",ValUtil.toString(object[2],"0"));
        map.put("stationRating",ValUtil.toInteger(object[3],0));
        map.put("stationPile",ValUtil.toInteger(object[4],0));
        Long electricityPrice = ValUtil.toDouble(object[5],0D).longValue();
        map.put("electricityPrice",electricityPrice);
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
        String totalServicesKey = String.format("STT_%s_%s_%s", stationId,  sellerId, "total_station_service_count");//站点总服务次数
        String stationCountKey = String.format("STT_%s_%s_%s", stationId, sellerId, "total_station_power_count");//站点总充电量
        String totalBlanceKey = String.format("STT_%s_%s_%s", stationId, sellerId, "total_station_money_count");//站点总收入
        String stationJianPowerCountKey = String.format("STT_%s_%s_%s", stationId, sellerId, "total_station_jian_power_count");//站点尖总充电量
        String statiFengPowerCountKey = String.format("STT_%s_%s_%s", stationId, sellerId, "total_station_feng_power_count");//站点峰总充电量
        String statiPingPowerCountKey = String.format("STT_%s_%s_%s", stationId, sellerId, "total_station_ping_power_count");//站点平总充电量
        String statiGuPowerCountKey = String.format("STT_%s_%s_%s", stationId, sellerId, "total_station_gu_power_count");//站点谷总充电量


        Object totalData = new Object();
        Object dayData = new Object();
        if(redisCache.get(totalServicesKey) == null || redisCache.get(stationCountKey) == null || redisCache.get(totalBlanceKey) == null || redisCache.get(stationJianPowerCountKey) == null
                ||  redisCache.get(statiFengPowerCountKey) == null || redisCache.get(statiPingPowerCountKey) == null || redisCache.get(statiGuPowerCountKey) == null){
            //站点总服务次数，总电量，总收入，充电峰谷
            totalData = stationShowTotalRepository.getStationBalanceInfoByStationId(sellerId,stationId);
        }


        Map<String,Object> map = new HashMap<>();

        //1、总服务次数
        //如果redis有数据直接获取
        if( redisCache.get(totalServicesKey ) == null){
            //1.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("servicesNums", ValUtil.toLong(object[0],0L));
                redisCache.put(totalServicesKey,object[0]);
            }else{
                //1.2如果如果数据库中不存在
                map.put("servicesNums", 0L);
            }
        }else {
            map.put("servicesNums", ValUtil.toLong(redisCache.get(totalServicesKey),0L));
        }
        //2、总电量
        if( redisCache.get(stationCountKey) == null){
            //2.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("totalPower", ValUtil.toLong(object[1],0L));
                redisCache.put(stationCountKey,object[1]);
            }else{
                //2.2如果如果数据库中不存在
                map.put("totalPower", 0L);
            }
        }else {
            map.put("totalPower", ValUtil.toLong(redisCache.get(stationCountKey),0L));
        }
        //3、总收入
        if( redisCache.get(totalBlanceKey ) == null){
            //3.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("totalRevenue", ValUtil.toLong(object[2],0L));
                redisCache.put(totalBlanceKey,object[2]);
            }else{
                //3.2如果如果数据库中不存在
                map.put("totalRevenue", 0L);
            }
        }else {
            map.put("totalRevenue", ValUtil.toLong(redisCache.get(totalBlanceKey),0L));
        }



        //4、充电尖总充电量
        if( redisCache.get(stationJianPowerCountKey ) == null){
            //4.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("jianCharge", ValUtil.toLong(object[3],0L));
                redisCache.put(stationJianPowerCountKey,object[3]);
            }else{
                //4.2如果如果数据库中不存在
                map.put("jianCharge", 0L);
            }
        }else {
            map.put("jianCharge", ValUtil.toLong(redisCache.get(stationJianPowerCountKey),0L));
        }

        //5、充电峰总充电量
        if( redisCache.get(statiFengPowerCountKey ) == null){
            //5.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("fengCharge", ValUtil.toLong(object[4],0L));
                redisCache.put(statiFengPowerCountKey,object[4]);
            }else{
                //5.2如果如果数据库中不存在
                map.put("fengCharge", 0L);
            }
        }else {
            map.put("fengCharge", ValUtil.toLong(redisCache.get(statiFengPowerCountKey),0L));
        }

        //6、充电平总充电量
        if( redisCache.get(statiPingPowerCountKey ) == null){
            //6.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("pingCharge", ValUtil.toLong(object[5],0L));
                redisCache.put(statiPingPowerCountKey,object[5]);
            }else{
                //6.2如果如果数据库中不存在
                map.put("pingCharge", 0L);
            }
        }else {
            map.put("pingCharge", ValUtil.toLong(redisCache.get(statiPingPowerCountKey),0L));
        }
        //7、充电谷总充电量
        if( redisCache.get(statiGuPowerCountKey ) == null){
            //7.1如果如果数据库中存在
            if( totalData != null ){
                Object[] object = (Object[]) totalData;
                map.put("guCharge", ValUtil.toLong(object[6],0L));
                redisCache.put(statiGuPowerCountKey,object[6]);
            }else{
                //7.2如果如果数据库中不存在
                map.put("guCharge", 0L);
            }
        }else {
            map.put("guCharge", ValUtil.toLong(redisCache.get(statiGuPowerCountKey),0L));
        }

        // station_show_day_table redis key：→ redis-key： redis-key：SDT_站点ID_商家号_日期(yyyy-MM-dd)_查询的字段名   日表
        String dayTotalServicesKey = String.format("SDT_%s_%s_%s_%s", stationId,  sellerId, date,  "day_station_pile_count");//站点天总服务次数
        String dayTstationCountKey = String.format("SDT_%s_%s_%s_%s", stationId, sellerId, date, "day_station_power_count");//站点天总充电量
        String dayTtotalBlanceKey = String.format("SDT_%s_%s_%s_%s", stationId, sellerId, date, "day_station_balance_count");//站点天总收入
        if(redisCache.get(dayTotalServicesKey) == null || redisCache.get(dayTstationCountKey) == null || redisCache.get(dayTtotalBlanceKey) == null){
            //站点今日总服务次数， 总电量，总收入统计
            dayData = stationShowDayRepository.getDayMemberInfo( stationId, sellerId, date);
        }

        //8、今日服务次数
        if( redisCache.get(dayTotalServicesKey ) == null){
            //8.1如果如果数据库中存在
            if( dayData != null ){
                Object[] object = (Object[]) dayData;
                map.put("todayServicesNums", ValUtil.toLong(object[0],0L));
                redisCache.put(dayTotalServicesKey,object[0]);
            }else{
                //8.2如果如果数据库中不存在
                map.put("todayServicesNums", 0L);
            }
        }else {
            map.put("todayServicesNums", ValUtil.toLong(redisCache.get(dayTotalServicesKey),0L));
        }
        //9、今日总电量
        if( redisCache.get(dayTstationCountKey ) == null){
            //9.1如果如果数据库中存在
            if( dayData != null ){
                Object[] object = (Object[]) dayData;
                map.put("todayTotalPower", ValUtil.toLong(object[1],0L));
                redisCache.put(dayTstationCountKey,object[1]);
            }else{
                //9.2如果如果数据库中不存在
                map.put("todayTotalPower", 0L);
            }
        }else {
            map.put("todayTotalPower", ValUtil.toLong(redisCache.get(dayTstationCountKey),0L));
        }
        //10、今日总收入统计
        if( redisCache.get( dayTtotalBlanceKey ) == null){
            //10.1如果如果数据库中存在
            if( dayData != null ){
                Object[] object = (Object[]) dayData;
                map.put("todayTotalRevenue", ValUtil.toLong(object[2],0L));
                redisCache.put(dayTtotalBlanceKey,object[2]);
            }else{
                //10.2如果如果数据库中不存在
                map.put("todayTotalRevenue", 0L);
            }
        }else {
            map.put("todayTotalRevenue", ValUtil.toLong(redisCache.get(dayTtotalBlanceKey),0L));
        }
        return map;
    }

    private Map<String,Object> getStationBalanceData(Long stationId, Long sellerId, Date date) {

        String startTime = TimeUtils.formatFullTime(TimeUtils.getStartDate(date));
        String endTime = TimeUtils.formatFullTime(TimeUtils.getEndDate(date));
        List<Object> stationBalanceData1 = stationShowDayRepository.getStationBalanceData(stationId, sellerId,startTime, endTime);
        if(stationBalanceData1 == null){
            Map<String,Object> map = new HashMap<>();
            map.put("monthServicesNums", 0L);
            map.put("monthTotalPower",0L);
            map.put("monthTotalRevenue",0L);
            return map;
        }
        Object obj = stationBalanceData1.get(0);
        Object[] object = (Object[]) obj;
        Map<String,Object> map = new HashMap<>();
        map.put("monthServicesNums", ValUtil.toLong(object[0],0L));
        map.put("monthTotalPower", ValUtil.toLong(object[1],0L));
        map.put("monthTotalRevenue", ValUtil.toLong(object[2],0L));
        return map;
    }
}
