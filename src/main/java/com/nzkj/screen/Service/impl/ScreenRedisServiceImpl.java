package com.nzkj.screen.Service.impl;

import java.math.RoundingMode;
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
import com.xiaoleilu.hutool.util.ObjectUtil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.nzkj.screen.Utils.ValUtil.val;
import static com.nzkj.screen.Utils.ValUtil.valN;

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

    @Autowired
    private IAllMemberMapper allMemberMapper;

    //??????id????????????
    @Value("${sellerId}")
    private long sellerId;

    //userId????????????
    @Value("${userId}")
    private long userId;

    //?????????redis??????key????????????  ????????????  ???????????????????????????
    //??????????????????????????????redis???????????? ????????????????????????
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
            int gunCount = 0;// ?????????
            int charging = 0;// ???????????????
            int free = 0;// ???????????????
            int chargePrepare = 0;// ?????????????????????
            int chargeFinsh = 0;// ?????????????????????
            int offLine = 0;// ???????????????
            int problem = 0;// ???????????????
            int bespeak = 0;// ???????????????
            List<GunMonitorDto> guns = memoryData.getGunByStation(stationId);
            if (CollectionUtil.isNotEmpty(guns)) {
                for (GunMonitorDto gunMonitorDto : guns) {
                    gunCount++;
                    // GunStateEnum ???????????????????????????????????????
                    // ?????????????????????
                    switch (gunMonitorDto.getGunState()) {
                        case 3:
                            // ??????????????????
                            charging++;
                            break;

                        case 1:
                            // ??????????????????
                            free++;
                            break;

                        case 4:
                            // ???????????????
                            chargeFinsh++;
                            break;
                        case 5:
                            // ??????????????????
                            offLine++;
                            break;
                        case 2:
                            // ????????????????????????
                            chargePrepare++;
                            break;
                        case 8:
                            // ???????????????
                            bespeak++;
                            break;
                        case 7:
                            // ??????????????????
                            problem++;
                            break;
                        default:
                            break;
                    }

                }
            }
            retMap.put("code", "0");
            // ????????????????????????
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
     * @Description???????????????id???????????????????????????????????????
     * @param stationId ??????id
     * @Return??? List<Map<String, Object>> carNums(??????????????????) charingBalance(????????????(???)) date(??????
     */
    public List<Map<String, Object>> getCarSumData(Long stationId) {
        Date date = new Date();
        List<Map<String, Object>> carSumData = new ArrayList<>();
        List<String> timeList = TimeUtils.getTimeList(TimeUtils.getStartDate(date), TimeUtils.getEndDate(date), TimeSharingEnum.Day);
        StationShowDay res = new StationShowDay();
        //1???????????????
        for (String dateTime: timeList) {

            res = stationShowDayMapper.get(stationId,sellerId,dateTime);
            Map<String,Object> map = new HashMap<>();

            Long otherNums = 0L;
            Long carNums = 0L;
            Long dayStationBalance = 0L;
            if(res != null){
                //3??? ????????????????????????????????????
                carNums = ValUtil.toLong(res.getDayStationServiceBusCarNum(), 0L);
                //4??? ????????????????????????????????????
                otherNums = ValUtil.toLong(res.getDayStationServiceOtherCarNum(), 0L);
                //5??? ???????????????????????????
                dayStationBalance = ValUtil.toLong(res.getDayStationBalanceCount(), 0L);
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
//        Double allPower = 0.00;// ???????????????
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

        //???????????????????????????????????????????????????--carChargingPercent
        Map<String,Object> sumRate = getSumPowerAndRate();
        double percent=(double) sumRate.get("rate");
        map.put("carChargingPercent", percent);

        //???????????????????????????????????????sumPower??????
        long sumPower=(long) sumRate.get("sumPower");
        map.put("sumPower", TypeRotationUtil.stringTurnKw2(sumPower));

        //?????????12?????????????????????????????????????????????????????????---team12ChargingData
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        Map<String, Object> teamAndPersonChargePower = getTeamAndPersonChargePower();
        List timeList=(List) teamAndPersonChargePower.get("timeList");
        List personPowerList=(List) teamAndPersonChargePower.get("personPowerList");
        List teamPowerList=(List) teamAndPersonChargePower.get("teamPowerList");
        Map<String, Object> dataMap = null;
        for (int i = 0; i < timeList.size(); i++){
            dataMap = new HashMap<String, Object>();
            dataMap.put("data", timeList.get(i));
            dataMap.put("?????????", TypeRotationUtil.stringTurnKw2((Long)teamPowerList.get(i)));
            dataMap.put("??????", TypeRotationUtil.stringTurnKw2((Long)personPowerList.get(i)));
            data.add(dataMap);
        }
        map.put("team12ChargingData", data);

        return map;
    }

    @Override
    public List<Map<String, Object>> doGetUserServiceNum(Long stationID) {
        List<Map<String, Object>> userServiceNum = new ArrayList<>();

        //????????????Id?????????????????????????????????????????????
        StationShowTotal data = stationShowTotalMapper.get(stationID,sellerId);

        //1???????????????????????????????????????
        BigInteger totalNum = ValUtil.valN(data.getTotalStationServiceUseCount());

        //2?????????????????????????????????????????????
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

        //3?????????????????????????????????????????????
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

        //4????????????????????????????????????????????????
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

        //5??????????????????????????????????????????
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

        //6????????????????????????????????????????????????
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

        //1????????????????????????????????? = month_pile_personal_num_count ???????????????????????????
        map.put("allservice",ValUtil.toLong(data.getMonthPilePersonalNumCount(),0L));

        //2???????????????????????????(???) = ?????????????????????????????????????????????????????????????????????????????????????????=????????????????????????/??????*********************       ????????? ******************
        //2.1????????????????????????????????????????????????
        BigInteger powerBalanceCount = ValUtil.valN(stationShowTotalMapper.getUserTotalCharge(stationID,sellerId));
        //2.2?????????????????????????????????????????????
        BigInteger orderNumber = ValUtil.valN(stationShowMonthMapper.getUserCumulativeOrderNumber(stationID,sellerId));

        if(BigInteger.ZERO.equals(orderNumber)){
            map.put("perCapitaCharging",0) ;
        }else{
            map.put("perCapitaCharging",powerBalanceCount.divide(orderNumber).longValue());
        }
        //3??????????????????????????? = ???????????????????????????????????????????????????????????????
        map.put("monthAppointment",ValUtil.toLong(data.getMonthPersonalBookedNum(),0L));

        //4??????????????????????????? =  ?????????????????????????????????????????????????????????????????????????????????????????????
        map.put("monthEvent",ValUtil.toLong(data.getMonthPersonalVisitNum(),0L));



        String moreThan5FansRateKey = "moreThan5FansRate-"+stationID+"-"+sellerId+"-"+date;
        if(redisCache.get(moreThan5FansRateKey) == null){
            // ??????????????????????????????mysql???????????? ????????????redis  ??????????????????
            //5??????????????????
            double totalFans = stationShowMonthMemberMapper.getTotalFansData(stationID,sellerId,date).doubleValue();
            //6?????????????????????????????????????????????5????????????
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


        //7???????????????????????????(???) = ????????????????????????????????????????????????????????????????????????????????
        //????????????????????????????????????????????????
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
        //?????????????????????
        List<String> nowOfTwelveMonth = TimeUtils.getNowOfTwelveMonth();
        //???????????????
        List<Map<String, Object>> list = new ArrayList<>();
        //1????????????????????????????????????
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
     * @Description???????????????id????????????????????????????????????????????????20??????10??????5??????????????????????????????
     * @Param???stationId ??????id sellerId ??????id
     * @Return???Map<String, Object>  ??????5???  ??????5??? ??????10??? ??????20???
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

        //??????5???
        double oneToFive = stationShowMonthMemberMapper.getLessFiveRate(stationId,sellerId,startTime).doubleValue();
        map.put("oneToFive", ValUtil.div(oneToFive,sum,2)*100);


        //??????5???
        double thanFive = stationShowMonthMemberMapper.getThanFiveRate(stationId,sellerId,startTime).doubleValue();
        map.put("fiveToTen", ValUtil.div(thanFive,sum,2)*100);

        //??????10???
        double thanTen = stationShowMonthMemberMapper.getThanTenRate(stationId,sellerId,startTime).doubleValue();
        map.put("tenToTwenty", ValUtil.div(thanTen,sum,2)*100);

        //??????20???
        double thanTwenty = stationShowMonthMemberMapper.getThanTwentyRate(stationId,sellerId,startTime).doubleValue();
        map.put("twentyTo", ValUtil.div(thanTwenty,sum,2)*100);

        return map;
    }

    /**
     * @Title
     * @Description???????????????id?????????????????????????????????????????????8 ?????????????????????
     * @Param??? tationId ??????id sellerId ??????id
     * @Return???Lis</map> key : image(????????????) name(????????????) charingNum(????????????) allbalance(???????????????(???))
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

    @Override
    public Map<String, Object> doGetTotalRechargeInfo(Long stationId) {
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();

        MemberRechargeInfoDto totalRechargeInfo = getTotalRechargeInfo(stationId);

        MemberRechargeInfoDto rechargeInfo = getRechargeInfo(stationId);
        //????????????-?????????????????????????????????
        map.put("serviceMember", totalRechargeInfo.getBillCount());
        //????????????-??????????????????????????????
        map.put("serviceTeamCar", totalRechargeInfo.getServiceTeamCar());
        //??????????????????????????????????????????
        Long busPower = totalRechargeInfo.getBusPower();
        if (busPower >= 100000) {
            totalRechargeInfo.setBusPower(busPower / 10 * 10);
        }
        //??????????????????????????????/??????= ????????????/?????????????????? *
        map.put("busPower", TypeRotationUtil.stringTurnKw2(totalRechargeInfo.getBusPower()));
        //????????????????????????/??????= ???????????????/??????????????????*
        map.put("busBalance", TypeRotationUtil.stringTurnYuan(totalRechargeInfo.getBusBalance()));
        //????????????????????? = ??????????????????/????????????
        map.put("busRechargeCount", rechargeInfo.getBusRechargeCount());
        // ??????????????????????????????= ????????????/???????????? *
        map.put("memberPower", TypeRotationUtil.stringTurnKw2(rechargeInfo.getMemberPower()));
        //??????????????????????????????= ??????????????????/????????????
        map.put("memberRechargeCount", rechargeInfo.getMemberRechargeCount());
        //???????????????????????????h/??????= ???????????????/??????????????????*
        BigDecimal busRechargeTime = new BigDecimal(totalRechargeInfo.getBusRechargeTime()).divide(new BigDecimal(3600), 2,RoundingMode.HALF_UP);
        map.put("busRechargeTime", busRechargeTime.toString());
        return map;
    }

    @Override
    public Map<String, Object> doGetTeamMemberStageElectro(Long stationId) {
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();

        StationShowDay data = stationShowDayMapper.get(stationId,sellerId,TimeUtils.format(TimeUtils.getTodayStart(), "yyyy-MM-dd"));
        if(data == null){
            return null;
        }
        long feng = data.getDayStationFengPowerCount().longValue();
        long gu = data.getDayStationGuPowerCount().longValue();
        long ping = data.getDayStationPingPowerCount().longValue();

        // ????????????????????????
        map.put("peakPower",TypeRotationUtil.stringTurnKw2(feng));
        // ????????????????????????
        map.put("ravinePower",TypeRotationUtil.stringTurnKw2(gu));
        // ????????????????????????
        map.put("flatPower",TypeRotationUtil.stringTurnKw2(ping));
        BigDecimal sumPower = new BigDecimal(feng + gu + ping);
        BigDecimal peakPercentage = new BigDecimal(feng).divide(sumPower,4,RoundingMode.HALF_UP);
        //?????????????????????
        BigDecimal peakPer = peakPercentage.multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        map.put("peakPercentage", peakPer+"%");
        BigDecimal ravinePercentage = new BigDecimal(gu).divide(sumPower,4,RoundingMode.HALF_UP);
        //?????????????????????
        BigDecimal ravinePer = ravinePercentage.multiply(BigDecimal.valueOf(100)).setScale(2);
        map.put("ravinePercentage", ravinePer+"%" );
        //?????????????????????
        BigDecimal flatPercentage = BigDecimal.ONE.subtract(peakPercentage).subtract(ravinePercentage);
        map.put("flatPercentage",flatPercentage.multiply(BigDecimal.valueOf(100)).setScale(2)+"%" );

        return map;
    }

    @Override
    public List<Map<String, Object>> doGetTeamStageElectro(Long stationID) {
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

        Map<String, Object> teamStageElectro = getTeamStageElectro(stationID);

        List<String> timeList=(List<String>) teamStageElectro.get("timeList");
        List<Long> peakPowerList = (List<Long>) teamStageElectro.get("peakPowerList");
        List<Long> flatPowerList = (List<Long>) teamStageElectro.get("flatPowerList");
        List<Long> ravinePowerList = (List<Long>) teamStageElectro.get("ravinePowerList");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("timeList", timeList);
        List<String> peakPowerListNew = new ArrayList<>();
        List<String> flatPowerListNew = new ArrayList<>();
        List<String> ravinePowerListNew = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++){
            peakPowerListNew.add(TypeRotationUtil.stringTurnKw2((Long)peakPowerList.get(i)));
            flatPowerListNew.add(TypeRotationUtil.stringTurnKw2((Long)flatPowerList.get(i)));
            ravinePowerListNew.add(TypeRotationUtil.stringTurnKw2((Long)ravinePowerList.get(i)));
        }
        dataMap.put("peakPowerList", peakPowerListNew);
        dataMap.put("flatPowerList", flatPowerListNew);
        dataMap.put("ravinePowerList", ravinePowerListNew);
        data.add(dataMap);
        return data;
    }

    @Override
    public List<Map<String, Object>> doGetTeamMemberRank(Long stationID,Integer count) {
        if(count == null || count == 0) {
            count = 3;
        }

        List<Map<String, Object>> list = new ArrayList<>();

        try {
            Map<String, Object> startDateAndEndDate = TimeUtils.getLastMonSAndE();
            List<IncomeRankDto> teamMemberRank = getTeamMemberRank2(sellerId,
                    stationID, TimeUtils.getParses(startDateAndEndDate.get("thisStart").toString()),
                    TimeUtils.getParse(startDateAndEndDate.get("thisEnd").toString()),
                    TimeUtils.getParse(startDateAndEndDate.get("lastlastStart").toString()),
                    TimeUtils.getParse(startDateAndEndDate.get("lastlastEnd").toString()), count);

            for (IncomeRankDto incomeRankDto : teamMemberRank) {
                Map<String, Object> map = new ConcurrentHashMap<String, Object>();
                // ??????
                map.put("name", incomeRankDto.getName());
                // ????????????????????????/??????= ?????????????????????/???????????????????????????
                map.put("avePower", TypeRotationUtil.stringTurnKw2(incomeRankDto.getAvePower()));
                // ??????????????????/??????= ???????????????????????????/????????????????????????????????????1?????????
                map.put("frePower",incomeRankDto.getFrePower());
                // 1,?????????0?????????-1??????
                if(incomeRankDto.getFrePower() > incomeRankDto.getFrePowerLast()) {
                    map.put("upOrDown", 1);
                }else if(ObjectUtil.equal(incomeRankDto.getFrePower(), incomeRankDto.getFrePowerLast())) {
                    map.put("upOrDown", 0);
                }else {
                    map.put("upOrDown", -1);
                }
                list.add(map);
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> doGetTeamMemberConsumeRank(Long stationID, Integer count) throws ParseException {
        if(count == null || count == 0) {
            count = 8;
        }

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> startDateAndEndDate = TimeUtils.getLastMonSAndE();
        //????????????
        List<IncomeRankDto> teamMemberRank = getTeamMemberConsumeRank(sellerId,
                stationID, TimeUtils.getParses(startDateAndEndDate.get("lastStart").toString()));

        for (IncomeRankDto incomeRankDto : teamMemberRank) {
            Map<String, Object> map = new ConcurrentHashMap<String, Object>();
            // ??????
            map.put("name", incomeRankDto.getMemberName());
            // ??????ID
            map.put("memberId", incomeRankDto.getMemberId());
            // ??????
            map.put("icon", incomeRankDto.getMemberIcon());
            // ????????????
            map.put("billCount", incomeRankDto.getChargeCount());
            // ?????????
            map.put("plateno", incomeRankDto.getPlateno());
            //????????????-???
            map.put("actualConsume", TypeRotationUtil.stringTurnYuan(incomeRankDto.getActualConsume()));
            list.add(map);
        }
        return list;
    }

    private List<IncomeRankDto> getTeamMemberConsumeRank(Long sellerId, Long stationId, Date monthStart){
        List<IncomeRankDto> incomeRankDtoList = new ArrayList<>();
        // ??????????????????
        String thisMonth = TimeUtils.format(monthStart,"yyyy-MM");
        List<TeamRankingDTO> consumeRank = allMemberMapper.getTeamRankingInfo(sellerId, stationId, thisMonth);
        if (CollectionUtils.isNotEmpty(consumeRank)) {
            for (TeamRankingDTO data : consumeRank) {
                Member member = memberMapper.get(data.getMemberId().longValue());
                Long memberId = ValUtil.toLong(data.getMemberId());		// ??????ID
                String name = ValUtil.toString(member.getName());		// ??????
                Long totalConsumebalance = ValUtil.toLong(data.getActualBalanceCount(), 0L);	// ??????????????????????????????
                Long grandConsumebalance = ValUtil.toLong(data.getTotalBalanceCount());	// ???????????????
                Integer carCount = ValUtil.toInteger(data.getCarNum());		//??????????????????-???
                String icon = ValUtil.toString(member.getIcon());		// ??????
                String telephone = ValUtil.toString(member.getTelephone());		// ?????????
                IncomeRankDto incomeRankDto = new IncomeRankDto();
//                name = CodeUtil.decodeUTF8(name);
//                if (name == null) {
//                    name = telephone;
//                }
                incomeRankDto.setMemberId(memberId);
                incomeRankDto.setMemberName(name);
                incomeRankDto.setTotalConsumebalance(totalConsumebalance); //??????????????????????????????
                incomeRankDto.setGrandConsumebalance(grandConsumebalance);    // ??????????????????
                incomeRankDto.setCarCount(carCount); //??????????????????-???
                incomeRankDto.setMemberIcon(icon);
                incomeRankDtoList.add(incomeRankDto);
            }
        }
        return incomeRankDtoList;

    }

    private List<IncomeRankDto> getTeamMemberRank2(Long sellerId, Long stationId, Date monthStart, Date monthEnd, Date lastMonthStart,
                                                   Date lastMonthEnd, Integer count){
        if (sellerId == null || stationId == null || monthStart == null || monthEnd == null || lastMonthStart == null
                || lastMonthEnd == null) {
            return null;
        }
        String thisMonth = TimeUtils.format(monthStart,"yyyy-MM");
        String lastMonth = TimeUtils.format(lastMonthStart,"yyyy-MM");
        List<TeamChargeRankingDTO> teamMemberRank = stationShowMonthMemberMapper.getTeamSingleChargeRankingList(sellerId, stationId, thisMonth, lastMonth);
        List<IncomeRankDto> incomeRankDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(teamMemberRank)) {
            for (TeamChargeRankingDTO data : teamMemberRank) {
                String name = data.getMemberName();		// ??????
                Integer carCount = ValUtil.toInteger(data.getCarCount(), 0);	// ??????????????????
                IncomeRankDto incomeRankDto = new IncomeRankDto();
                incomeRankDto.setCarCount(carCount);
                incomeRankDto.setName(name);
                incomeRankDto.setAvePower(ValUtil.toLong(data.getAvePower(), 0L));	// ?????????????????????/??????= ?????????????????????/???????????????????????????
                incomeRankDto.setFrePower(ValUtil.toDouble(data.getIFfrePower())); //??????????????????/??????= ???????????????????????????/????????????????????????????????????1?????????
                incomeRankDto.setFrePowerLast(ValUtil.toDouble(data.getLastCount()));//???????????????????????????/??????= ????????????????????????????????????/?????????????????????????????????????????????1?????????
                incomeRankDtoList.add(incomeRankDto);
            }
        }
        return incomeRankDtoList;

    }

    /**
     * @Title
     * @Description????????????????????????
     * @Param???    sellerId,stationId,startDate,endDate
     * @Return???  timeList-????????????[String], peakPowerList-????????????????????????[Long],
     */
    private Map<String, Object> getTeamStageElectro(Long stationID){
        // ???????????????(yyyy-mm-dd)??????
        List<String> timeList = TimeUtils.getTimeList(TimeUtils.plusDayZeroTime(-30, new Date()) , new Date(),  TimeSharingEnum.getEnumByCode(3));
        List<Long> peakPowerList = new ArrayList<>();
        List<Long> flatPowerList = new ArrayList<>();
        List<Long> ravinePowerList = new ArrayList<>();
        Map<String, Object> resTeamStageElectroMap = new HashMap<>();

        //1??? ???????????????????????????
        for (String day: timeList) {

            StationShowDay data = stationShowDayMapper.get(stationID,sellerId,day);
            if(data != null){
                peakPowerList.add(data.getDayStationFengPowerCount().longValue());
                flatPowerList.add(data.getDayStationPingPowerCount().longValue());
                ravinePowerList.add(data.getDayStationGuPowerCount().longValue());
            }else{
                peakPowerList.add(0l);
                flatPowerList.add(0l);
                ravinePowerList.add(0l);
            }

        }
        resTeamStageElectroMap.put("timeList", timeList);
        resTeamStageElectroMap.put("peakPowerList", peakPowerList);
        resTeamStageElectroMap.put("flatPowerList", flatPowerList);
        resTeamStageElectroMap.put("ravinePowerList", ravinePowerList);
        return resTeamStageElectroMap;

    }

    private MemberRechargeInfoDto getRechargeInfo(Long stationId){
        StationShowDay data = stationShowDayMapper.get(stationId,sellerId,TimeUtils.format(TimeUtils.getTodayStart(), "yyyy-MM-dd"));

        MemberRechargeInfoDto memberRechargeInfoDto = new MemberRechargeInfoDto();
        /**??????????????????????????????????????????**/
        BigInteger pileTeam = ValUtil.valN(data.getDayStationServicePileTeamCount());

        /**???????????????????????????????????????**/
        BigInteger teamCarNum = ValUtil.valN(data.getDayStationServiceTeamCarNum());

        /**?????????????????????????????????**/
        BigInteger teamPower = ValUtil.valN(data.getDayStationTeamPowerCount());

        /**????????????????????????**/
        BigInteger teamNumCount = ValUtil.valN(data.getDayStationServiceTeamNumCount());
        Double busRechargeCount = ValUtil.toDouble(ValUtil.toDivBigDecimal(pileTeam, teamCarNum, 0), 0D);
        Long memberPower = ValUtil.toLong(ValUtil.toDivBigDecimal(teamPower, teamNumCount, 0), 0L);
        Double memberRechargeCount = ValUtil.toDouble(ValUtil.toDivBigDecimal(pileTeam, teamNumCount, 0), 0D);
        memberRechargeInfoDto.setBusRechargeCount(busRechargeCount);// ?????????????????????=????????????/????????????
        memberRechargeInfoDto.setMemberPower(memberPower);// ??????????????????=?????????/????????????
        memberRechargeInfoDto.setMemberRechargeCount(memberRechargeCount);// ?????????????????????=????????????/????????????
        return memberRechargeInfoDto;
    }


    private MemberRechargeInfoDto getTotalRechargeInfo(Long stationId){
        MemberRechargeInfoDto memberRechargeInfoDto = new MemberRechargeInfoDto();
        StationShowTotal data = stationShowTotalMapper.get(stationId,sellerId);
        /**??????????????????????????????**/
        BigInteger serviceTeamCount = ValUtil.valN(data.getTotalStationServiceTeamCount());

        /**??????????????????????????????**/
        BigInteger teamCars = ValUtil.valN(data.getTotalStationServiceTeamCars());

        /**????????????????????????**/
        BigInteger teamPower = ValUtil.valN(data.getTotalStationTeamPowerCount());

        /**????????????????????????**/
        BigInteger teamNumCount = ValUtil.valN(data.getTotalStationTeamBillCount());

        /**???????????????????????????**/
        BigInteger teamMenoy = ValUtil.valN(data.getTotalStationTeamMenoyCount());

        /**?????????????????????????????????**/
        BigInteger teamTime = ValUtil.valN(data.getTotalStationServiceTeamTime());

        Long busRechargeCount = ValUtil.toLong(ValUtil.toDivBigDecimal(teamPower, teamNumCount, 0), 0L);
        Long busBalance = ValUtil.toLong(ValUtil.toDivBigDecimal(teamMenoy, teamNumCount, 0), 0L);
        Long busRechargeTime = ValUtil.toLong(ValUtil.toDivBigDecimal(teamTime, teamNumCount, 0), 0L);
        memberRechargeInfoDto.setServiceMember(serviceTeamCount.intValue());
        memberRechargeInfoDto.setServiceTeamCar(teamCars.intValue());
        memberRechargeInfoDto.setBusPower(busRechargeCount);//???????????????????????????/??????= ????????????/??????????????????
        memberRechargeInfoDto.setBusBalance(busBalance);//????????????????????????/??????= ???????????????/??????????????????
        memberRechargeInfoDto.setBusRechargeTime(busRechargeTime);//???????????????????????????s/??????= ???????????????/??????????????????
        return memberRechargeInfoDto;
    }

    private Map<String, Object> getTeamAndPersonChargePower(){
        Map<String, Object> mapData = new HashMap<>();
        //????????????
        List<String> timeList = new ArrayList<>();
        for (int i = 11; i >= 0; i--) {
            String time = TimeUtils.format(TimeUtils.addMonth(new Date(), -i), "yyyy-MM");
            timeList.add(time);
        }
        List<Long> personPowerList = new ArrayList<>();
        List<Long> teamPowerList = new ArrayList<>();
        //1????????????????????????
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
     * @Description???????????????id????????????????????????????????????????????????????????????,???????????????????????????????????????????????????
     * @Param???  stationId ??????id sellerId ??????id
     * @Return???  Map servicesNums(???????????????) totalPower(?????????(w)) totalRevenue(?????????(???)) jianCharge(?????????)(w) fengCharge(?????????(w))
     * 	 pingCharge(?????????(w)) guCharge(?????????(w)) todayServicesNums(?????????????????????) todayTotalPower(?????????(w)) todayTotalRevenue(???????????????(???))
     * @author <a href="mail to: *******@******.com" rel="nofollow">huangxw</a>
     * @CreateDate???2019???9???23???11:41:37
     *@update???[??????] [??????YYYY-MM-DD] [????????????] [????????????]
     */
    private Map<String,Object> getStationBalanceData(Long stationId,Long sellerId) {

        // station_show_total_table redis-key???STT_??????ID_?????????_??????????????????   ??????
        String date = TimeUtils.format(new Date(), "yyyy-MM-dd");

        StationShowTotal totalData = stationShowTotalMapper.getBySeller(stationId,sellerId);



        Map<String,Object> map = new HashMap<>();

        //1??????????????????
            if( totalData != null ){
                map.put("servicesNums", ValUtil.toLong(totalData.getTotalStationServiceCount(),0L));
            }else{
                //1.2?????????????????????????????????
                map.put("servicesNums", 0L);
            }

        //2????????????

            if( totalData != null ){
                map.put("totalPower", ValUtil.toLong(totalData.getTotalStationPowerCount(),0L));
            }else{
                //2.2?????????????????????????????????
                map.put("totalPower", 0L);
            }

        //3????????????
            if( totalData != null ){
                map.put("totalRevenue", ValUtil.toLong(totalData.getTotalStationMoneyCount(),0L));
            }else{
                //3.2?????????????????????????????????
                map.put("totalRevenue", 0L);
            }



        //4????????????????????????
            if( totalData != null ){
                map.put("jianCharge", ValUtil.toLong(totalData.getTotalStationJianPowerCount(),0L));
            }else{
                //4.2?????????????????????????????????
                map.put("jianCharge", 0L);
            }

        //5????????????????????????
            if( totalData != null ){
                map.put("fengCharge", ValUtil.toLong(totalData.getTotalStationFengPowerCount(),0L));
            }else{
                map.put("fengCharge", 0L);
            }


        //6????????????????????????
            if( totalData != null ){
                map.put("pingCharge", ValUtil.toLong(totalData.getTotalStationPingPowerCount(),0L));
            }else{
                //6.2?????????????????????????????????
                map.put("pingCharge", 0L);
            }

        //7????????????????????????
            if( totalData != null ){
                map.put("guCharge", ValUtil.toLong(totalData.getTotalStationGuPowerCount(),0L));
            }else{
                //7.2?????????????????????????????????
                map.put("guCharge", 0L);
            }

        StationShowDay dayData = stationShowDayMapper.get(stationId,sellerId,date);


        //8?????????????????????
            if( dayData != null ){
                map.put("todayServicesNums", ValUtil.toLong(dayData.getDayStationPileCount(),0L));
            }else{
                //8.2?????????????????????????????????
                map.put("todayServicesNums", 0L);
            }

        //9??????????????????
            if( dayData != null ){
                map.put("todayTotalPower", ValUtil.toLong(dayData.getDayStationPowerCount(),0L));
            }else{
                //9.2?????????????????????????????????
                map.put("todayTotalPower", 0L);
            }

        //10????????????????????????
            if( dayData != null ){
                map.put("todayTotalRevenue", ValUtil.toLong(dayData.getDayStationBalanceCount(),0L));
            }else{
                //10.2?????????????????????????????????
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
