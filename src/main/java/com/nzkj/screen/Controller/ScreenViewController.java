package com.nzkj.screen.Controller;

/**
 * @Author: Liu yang
 * @Date: 2020/6/24 17:01
 * Describe:获取汇总的各种数据  几乎所有数据思路都一样
 * 先尝试在redis获取 获取不到则从数据库获取 再缓存到redis
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.AllDayData;
import com.nzkj.screen.Entity.AllMonthData;
import com.nzkj.screen.Entity.DTO.*;
import com.nzkj.screen.Entity.Station;
import com.nzkj.screen.Entity.TotalOperateData;
import com.nzkj.screen.Service.ScreenRedisService;
import com.nzkj.screen.Service.StationService;
import com.nzkj.screen.Utils.*;
import com.nzkj.screen.mapper.pile.config.IStationMapper;
import com.nzkj.screen.mapper.pile.screen.IAllDayMapper;
import com.nzkj.screen.mapper.pile.screen.IAllMonthDataMapper;
import com.nzkj.screen.mapper.pile.screen.IStationShowTotalMapper;
import com.nzkj.screen.mapper.pile.screen.ITotalOperateMapper;
import com.nzkj.screen.memory.IMemoryData;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class ScreenViewController {

    @Autowired
    private IMemoryData localMemoryData;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private IStationShowTotalMapper stationShowTotalMapper;



    @Autowired
    private ITotalOperateMapper totalOperateMapper;

    private StationService stationService;
    @Autowired
    public void setStationService(StationService stationService){
        this.stationService = stationService;
    }

    @Autowired
    private IAllMonthDataMapper allMonthDataMapper;

    @Autowired
    private IAllDayMapper allDayMapper;

    @Autowired
    private IStationMapper stationMapper;

    @Autowired
    private ScreenRedisService screenRedisService;



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

    /**
     * @describe
     * 累计总收入   累计服务用户数   累计服务次数
     */
    @RequestMapping(value="/statistic/screenNewAjax/getTotalNum.json", method=RequestMethod.POST)
    public Map<String, Object> getTotalNum() {

        Map<String, Object> map = new ConcurrentHashMap<String, Object>();

        TotalOperateData data = totalOperateMapper.get(sellerId);

        if (data != null){
            //累计总收入totalMoney元
            map.put("totalMoney",data.getTotalIActualBalance().toString());
            //累计服务用户数totalServiceMemberNum
            map.put("totalServiceMemberNum",data.getTotalServiceUserCount());
            //累计服务次数serviceTotalCount
            map.put("serviceTotalCount",data.getPersonalServiceCount());
        }else {
            //累计总收入totalMoney元
            map.put("totalMoney", 0);
            //累计服务用户数totalServiceMemberNum
            map.put("totalServiceMemberNum", 0);
            //累计服务次数serviceTotalCount
            map.put("serviceTotalCount",0);
        }

        return map;

    }

    /*
     * 根据站点Id统计总共服务用户分级数量
     *
     * @param stationID
     *
     * @url /statistic/gJStationViewAjax/getUserServiceNum.json
     *
     * @return map key : memNums(人数) grade(等级) proportion(占比)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getUserServiceNum.json" ,method = RequestMethod.POST)
    public List<Map<String, Object>> doGetUserServiceNum(Long stationID){
        return screenRedisService.doGetUserServiceNum(stationID);
    }

    /*
     * 根据站点Id统计站点个人用户数据
     *
     * @url /statistic/gJStationViewAjax/getStationMemData.json
     *
     * @return map key :allservice(服务用户总数) perCapitaCharging(人均充电消费)
     * monthAppointment(月充电预约) monthEvent(月活动参与) fans(忠实粉丝占比)
     * chargingConsumption(月均人均充电)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getStationMemData.json" ,method = RequestMethod.POST)
    public Map<String, Object> doGetStationMemData(Long stationID){
        return screenRedisService.doGetStationMemData(stationID);
    }

    /*
     * 根据站点id统计每个月第一次充电用户的增长数量，共12个月
     *
     * @url /statistic/gJStationViewAjax/getMemberAddByMonth.json
     *
     * @return map key : time(年月) memberNum(人数)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getMemberAddByMonth.json" ,method = RequestMethod.POST)
    public List<Map<String, Object>> doGetMemberAddByMonth(Long stationID){
        return screenRedisService.getMemberAddByMonth(stationID);
    }

    /*
     * 根据站点id显示个人会员用户在场站每月24小时内，每小时内的消费总次数
     *
     * @url /statistic/gJStationViewAjax/getMemberByHours.json
     *
     * @return map key ：charingNum(充电数) time(时间)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getMemberByHours.json" ,method = RequestMethod.POST)
    public List<Map<String, Object>> doGetMemberByHours(Long stationID){
        return screenRedisService.getMemberByHours(stationID);
    }

    /*
     * 根据站点id显示当月在该场站重复充电大于等于20次、10次、5次的个人会员数量占比
     *
     * @url /statistic/gJStationViewAjax/getFansProportion.json
     *
     * @return map key : oneToFive（1≤X﹤5） fiveToTen(5≤X﹤10) tenToTwenty(10≤X﹤20)
     * twentyTo(20≤X﹤∞)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getFansProportion.json" ,method = RequestMethod.POST)
    public Map<String, Object> doGetFansProportion(Long stationID){
        return screenRedisService.getFansProportion(stationID);
    }

    /*
     * 根据站点id显示当月在该场站充电消费排名前8的个人会员用户
     *
     * @url /statistic/gJStationViewAjax/getConsumptionRanking.json
     *
     * @return map key : name(用户名称) charingNum(充电次数) allbalance(消费总金额(分)) image 头像
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getConsumptionRanking.json" ,method = RequestMethod.POST)
    public List<Map<String, Object>> doGetConsumptionRanking(Long stationID){
        return screenRedisService.getConsumptionRanking(stationID);
    }

    /**
     * 车队用户
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getTotalRechargeInfo.json" ,method = RequestMethod.POST)
    public Map<String, Object> doGetTotalRechargeInfo(Long stationID){
        return screenRedisService.doGetTotalRechargeInfo(stationID);
    }

    /**
     * 当日车队用户分析
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getTeamMemberStageElectro.json" ,method = RequestMethod.POST)
    public Map<String, Object> doGetTeamMemberStageElectro(Long stationID){
        return screenRedisService.doGetTeamMemberStageElectro(stationID);
    }

    /**
     * 车队峰平谷分析
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getTeamStageElectro.json" ,method = RequestMethod.POST)
    public List<Map<String,Object>> doGetTeamStageElectro(Long stationID){
        return screenRedisService.doGetTeamStageElectro(stationID);
    }

    /**
     * 车队单车月充电频率排名
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getTeamMemberRank.json" ,method = RequestMethod.POST)
    public List<Map<String,Object>> doGetTeamMemberRank(Long stationID,Integer count){
        return screenRedisService.doGetTeamMemberRank(stationID,count);
    }

    /**
     * 车队单车月充电频率排名
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getTeamMemberConsumeRank.json" ,method = RequestMethod.POST)
    public List<Map<String,Object>> doGetTeamMemberConsumeRank(Long stationID,Integer count) throws ParseException {
        return screenRedisService.doGetTeamMemberConsumeRank(stationID,count);
    }

    /**
     * @describe
     * 获取总功率
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getAllPowerAndCarCharging.json" ,method = RequestMethod.POST)
    public Map<String, Object> getAllPowerAndCarCharging(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
//        String key = String.format("AT_%s_%s", sellerId, "total_equipment_power");
//        Object all = redisTemplate.opsForValue().get(key);
//        if (all == null){
        BigInteger allPower = totalOperateMapper.get(sellerId).getTotalEquipmentPower();
//        redisTemplate.opsForValue().set(key,allPower,expireTime,TimeUnit.SECONDS);
        map.put("allPower",allPower);
//        }else {
//            map.put("allPower", all);
//        }
        return map;
    }

    /**
     * @describe
     * 总充电量和公交累计充电量比例
     */
    @RequestMapping(value = "/statistic/screenNewAjax/historyTotalCharging.json",method = RequestMethod.POST)
    public Map<String, Object> historyTotalCharging(){
        return screenRedisService.doHistoryTotalCharging();
    }

    /**
     * @describe
     *  站点服务频率排行
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getStationServiceRanking.json",method = RequestMethod.POST)
    public Map<String, Object> getStationServiceRanking(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        List<Map<String, String>> mapList = new ArrayList<>();
        try {
            //为了出数据把这里写死 实际项目应该改回来 todo
//            List<StationServiceDTO> objList = stationShowTotalMapper.getStationServiceList(sellerId, "2020-05");
            List<StationServiceDTO> objList = stationShowTotalMapper.getStationServiceList(sellerId, TimeUtils.format(new Date(), "yyyy-MM"));
            if( !CollectionUtils.isEmpty(objList) ) {
                for (StationServiceDTO object : objList) {
                    Map<String, String> mapData = new HashMap<>();
                    mapData.put("stationName", ValUtil.toString(stationMapper.get(object.getlStationId()).getName()));
                    mapData.put("chargeCount", ValUtil.toString(object.getTotalStationServiceCount(), "0"));
                    Object allCount = ValUtil.toLong(object.getMonthPileNumCount());
                    Object gunCount = ValUtil.toLong(object.getTotalStationGunNum());
                    mapData.put("allChargeCount", ValUtil.toString(allCount, "0"));
                    mapData.put("gunCount", ValUtil.toString(gunCount, "0"));
                    mapList.add(mapData);
                }
            }
            //站点服务频率排行
            map.put("rankList", mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @describe
     *
     */
    @RequestMapping(value = "/statistic/screenNewAjax/serviceOperateData.json",method = RequestMethod.POST)
    public Map<String, Object> serviceOperateData(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        ServiceRunDto dto = new ServiceRunDto();
        TotalOperateData res = new TotalOperateData();
        try {
            String totalServiceCountKey = String.format("AMT_%s_%s", sellerId, "total_service_count");
            String teamServiceCountKey = String.format("AMT_%s_%s", sellerId, "team_service_count");
            String personalServiceCountKey = String.format("AMT_%s_%s", sellerId, "personal_service_count");
            String totalServiceTimeKey = String.format("AMT_%s_%s", sellerId, "total_service_time");
            String teamServiceTimeKey = String.format("AMT_%s_%s", sellerId, "team_service_time");
            String personalServiceTimeKey = String.format("AMT_%s_%s", sellerId, "personal_service_time");

            Object totalServiceCount = redisTemplate.opsForValue().get(totalServiceCountKey);
            Object teamServiceCount = redisTemplate.opsForValue().get(teamServiceCountKey);
            Object personalServiceCount = redisTemplate.opsForValue().get(personalServiceCountKey);
            Object totalServiceTime = redisTemplate.opsForValue().get(totalServiceTimeKey);
            Object teamServiceTime = redisTemplate.opsForValue().get(teamServiceTimeKey);
            Object personalServiceTime = redisTemplate.opsForValue().get(personalServiceTimeKey);

            if(totalServiceCount == null || teamServiceCount == null ||
                    personalServiceCount == null || totalServiceTime == null ||
                    teamServiceTime == null || personalServiceTime == null){
                //redis缓存中未取到 从数据库取
                res = totalOperateMapper.get(sellerId);
                // 将数据库中获取的数据更新到redis中
                // 1 累计服务次数
                redisTemplate.opsForValue().set(totalServiceCountKey,res.getTotalServiceCount(),expireTime,TimeUnit.SECONDS);
                // 2 车队累计服务次数
                redisTemplate.opsForValue().set(teamServiceCountKey,res.getTeamServiceCount(),expireTime,TimeUnit.SECONDS);
                // 3 其他服务次数
                redisTemplate.opsForValue().set(personalServiceCountKey,res.getPersonalServiceCount(),expireTime,TimeUnit.SECONDS);
                // 4 累计服务总时长
                redisTemplate.opsForValue().set(totalServiceTimeKey,res.getTotalServiceTime(),expireTime,TimeUnit.SECONDS);
                // 5 车队累计服务时长
                redisTemplate.opsForValue().set(teamServiceTimeKey,res.getTeamServiceTime(),expireTime,TimeUnit.SECONDS);
                // 6 其他累计服务时长
                redisTemplate.opsForValue().set(personalServiceTimeKey,res.getPersonalServiceTime(),expireTime,TimeUnit.SECONDS);

                dto.setAllChargeCount(ValUtil.toLong(res.getTotalServiceCount(), 0L));
                dto.setTeamChargeCount(ValUtil.toLong(res.getTeamServiceCount(), 0L));
                dto.setOtherChargeCount(ValUtil.toLong(res.getPersonalServiceCount(), 0L));
                dto.setAllChargeTime(ValUtil.toLong(res.getTotalServiceTime(), 0L));
                dto.setTeamChargeTime(ValUtil.toLong(res.getTeamServiceTime(), 0L));
                dto.setOtherChargeTime(ValUtil.toLong(res.getPersonalServiceTime(), 0L));

            }else {
                //直接使用从redis获取的值
                dto.setAllChargeCount(Long.valueOf(totalServiceCount+""));
                dto.setTeamChargeCount(Long.valueOf(teamServiceCount+""));
                dto.setOtherChargeCount(Long.valueOf(personalServiceCount+""));
                dto.setAllChargeTime(Long.valueOf(totalServiceTime+""));
                dto.setTeamChargeTime(Long.valueOf(teamServiceTime+""));
                dto.setOtherChargeTime(Long.valueOf(personalServiceTime+""));

            }
                double teamRate = ValUtil.toDivBigDecimal(dto.getTeamChargeCount()*100, dto.getAllChargeCount(), 2).doubleValue();
                double otherRate = ValUtil.toDivBigDecimal(dto.getOtherChargeCount()*100, dto.getAllChargeCount(), 2).doubleValue();
                dto.setTeamRate(teamRate);
                dto.setOtherRate(otherRate);
                if(dto.getOtherChargeCount() != 0){
                    ////其他平均时长(小时/次)
                    double otherMeanTime = ValUtil.toDivBigDecimal(dto.getOtherChargeTime(), dto.getOtherChargeCount()*3600, 2).doubleValue();
                    dto.setOtherMeanTime(otherMeanTime);
                }
                if(dto.getTeamChargeCount() != 0){
                    //车队平均时长(小时/次)
                    double teamMeanTime = ValUtil.toDivBigDecimal(dto.getTeamChargeTime(), dto.getTeamChargeCount()*3600, 2).doubleValue();
                    dto.setTeamMeanTime(teamMeanTime);
                }

            //累计服务次数serviceTotalCount
            map.put("serviceTotalCount", dto.getAllChargeCount());
            //公交车辆服务订单总数serviceGongCount
            map.put("serviceGongCount", dto.getTeamChargeCount());
            //其他车辆服务订单总数serviceAnotherCarCount
            map.put("serviceAnotherCarCount", dto.getOtherChargeCount());
            //累计充电总时长totalChargingTime
            String num1= TimeUtils.secondToHour(dto.getAllChargeTime());
            map.put("totalChargingTime", num1);//小时
            //公交车辆充电总时长gongChargingTime
            String gongChargingTime= TimeUtils.secondToHour(dto.getTeamChargeTime());
            map.put("gongChargingTime", gongChargingTime);//小时
            //其他车辆充电总时长otherChargingTime
            String otherChargingTime= TimeUtils.secondToHour(dto.getOtherChargeTime());
            map.put("otherChargingTime", otherChargingTime);//小时
            //公交百分比gongPercent
            map.put("gongPercent", dto.getTeamRate());
            //其他百分比anotherPercent
            map.put("anotherPercent", dto.getOtherRate());
            //公交车辆平均充电时长AverageTimeForGong
            map.put("AverageTimeForGong", dto.getTeamMeanTime());
            //其他车辆平均充电时长AverageTimeForOther
            map.put("AverageTimeForOther", dto.getOtherMeanTime());



        }catch (Exception e){
            e.printStackTrace();
        }


        return map;
    }

    /**
     * @describe
     *  收入方式统计 5seconds
     */
    @RequestMapping(value = "/statistic/screenNewAjax/incomeWayData.json",method = RequestMethod.POST)
    public Map<String, Object> incomeWayData(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        TotalOperateData obj = new TotalOperateData();

        try {
            //AT_商家号_查询的字段名 total_wechat_actual_balance, total_app_actual_balance, total_card_actual_balance, total_vin_actual_balance
            String totalWechatKey = String.format("AT_%s_%s",sellerId, "total_wechat_actual_balance");
            String totalAppKey = String.format("AT_%s_%s", sellerId, "total_app_actual_balance");
            String totalCardKey = String.format("AT_%s_%s", sellerId, "total_card_actual_balance");
            String totalVinKey = String.format("AT_%s_%s", sellerId, "total_vin_actual_balance");

            Object totalWechat = redisTemplate.opsForValue().get(totalWechatKey);
            Object totalApp = redisTemplate.opsForValue().get(totalAppKey);
            Object totalCard = redisTemplate.opsForValue().get(totalCardKey);
            Object totalVin = redisTemplate.opsForValue().get(totalVinKey);

            if(totalWechat == null || totalApp == null || totalCard == null || totalVin == null) {
                //redis缓存中缺失数据  从数据库中取值
                obj = totalOperateMapper.get(sellerId);

                redisTemplate.opsForValue().set(totalWechatKey,obj.getTotalWechatActualBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(totalAppKey,obj.getTotalAppActualBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(totalCardKey,obj.getTotalCardActualBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(totalVinKey,obj.getTotalVinActualBalance(),expireTime,TimeUnit.SECONDS);

                map.put("wx", ValUtil.toLong(obj.getTotalWechatActualBalance(),0L));
                map.put("app", ValUtil.toLong(obj.getTotalAppActualBalance(),0L));
                map.put("card", ValUtil.toLong(obj.getTotalCardActualBalance(),0L));
                map.put("vin", ValUtil.toLong(obj.getTotalVinActualBalance(),0L));

            }else {
                map.put("wx", ValUtil.toLong(totalWechat,0L));
                map.put("app", ValUtil.toLong(totalApp,0L));
                map.put("card", ValUtil.toLong(totalCard,0L));
                map.put("vin", ValUtil.toLong(totalVin,0L));
            }
        }catch (Exception e){

            e.printStackTrace();
        }

        return map;
    }

    /**
     * @describe
     *
     */
    @RequestMapping(value = "/statistic/screenNewAjax/incomeOperate.json",method = RequestMethod.POST)
    public Map incomeOperate(){

        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        IncomeRunDto dto = new IncomeRunDto();
        TotalOperateData object = new TotalOperateData();
        //充电收入运营
        Long teamBalan = new Long(0);
        //其他收入运营
        Long personageBalance = new Long(0);
        //车队收入运营
        Long powerBalance = new Long(0);
        //个人收入运营
        Long otherBalance = new Long(0);

        try {
            //累计收入运营
            String totalServiceUserCountKey = String.format("AT_%s_%s", sellerId, "total_i_actual_balance");
            //累计车队收入运营
            String teamActualBalanceKey = String.format("AT_%s_%s", sellerId, "team_i_actual_balance");
            //累计个人收入运营
            String personalActualBalanceKey = String.format("AT_%s_%s", sellerId, "personal_i_actual_balance");
            //累计充电收入运营
            String totalPowerBalanceKey = String.format("AT_%s_%s", sellerId, "total_i_power_balance");
            //累计其他收入运营
            String totalserviceBalanceKey = String.format("AT_%s_%s", sellerId, "total_i_service_balance");

            //从缓存中提取  变量名字和上面某个方法一样所以报绿  实际无影响
            Object totalServiceUserCount = redisTemplate.opsForValue().get(totalServiceUserCountKey);
            Object teamActualBalance = redisTemplate.opsForValue().get(teamActualBalanceKey);
            Object personalActualBalance = redisTemplate.opsForValue().get(personalActualBalanceKey);
            Object totalPowerBalance = redisTemplate.opsForValue().get(totalPowerBalanceKey);
            Object totalserviceBalance = redisTemplate.opsForValue().get(totalserviceBalanceKey);

            if (totalServiceUserCount == null || teamActualBalance == null || personalActualBalance == null
                ||totalPowerBalance == null || totalserviceBalance == null){
                object = totalOperateMapper.get(sellerId);

                redisTemplate.opsForValue().set(totalServiceUserCountKey,object.getTotalIActualBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(teamActualBalanceKey,object.getTeamIActualBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(personalActualBalanceKey,object.getPersonalIActualBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(totalPowerBalanceKey,object.getTotalIPowerBalance(),expireTime,TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(totalserviceBalanceKey,object.getTotalIServiceBalance(),expireTime,TimeUnit.SECONDS);

                dto.setTotalIncome(ValUtil.toLong(object.getTotalIActualBalance(), 0L));

                powerBalance = ValUtil.toLong(object.getTotalIPowerBalance(), 0L);
                otherBalance = ValUtil.toLong(object.getTotalIServiceBalance(), 0L);
                teamBalan = ValUtil.toLong(object.getTeamIActualBalance(), 0L);
                personageBalance = ValUtil.toLong(object.getPersonalIActualBalance(), 0L);

            }else{
                dto.setTotalIncome(ValUtil.toLong(totalServiceUserCount, 0L));

                powerBalance  = ValUtil.toLong(teamActualBalance, 0L);//电费占比(实收)
                otherBalance = ValUtil.toLong(personalActualBalance, 0L);//其他费用占比(实收服务费、预约费、停车费等)
                teamBalan = ValUtil.toLong(totalPowerBalance, 0L); //公交充电收入
                personageBalance = ValUtil.toLong(totalserviceBalance, 0L);////其他车辆充电收入占比
            }

                dto.setTeamIncomeRate(ValUtil.toDivBigDecimal( powerBalance*100, dto.getTotalIncome(), 2).doubleValue());	//公交充电收入占比
                dto.setOtherIncomeRate(ValUtil.toDivBigDecimal(otherBalance*100, dto.getTotalIncome(), 2).doubleValue());	//其他车辆充电收入占比
                dto.setPowerBalanceRate(ValUtil.toDivBigDecimal(teamBalan*100, dto.getTotalIncome(), 2).doubleValue());//电费占比(实收)
                dto.setOtherBalanceRate(ValUtil.toDivBigDecimal(personageBalance*100, dto.getTotalIncome(), 2).doubleValue());	//其他费用占比(实收服务费、预约费、停车费等)

                map.put("totalMoney", TypeRotationUtil.stringTurnYuan(dto.getTotalIncome()));
                //公交充电收入占比
                map.put("teamIncomeRate", dto.getTeamIncomeRate());
                //其他车辆充电收入占比otherIncomeRate
                map.put("otherIncomeRate", dto.getOtherIncomeRate());
                //电费占比(实收) powerBalanceRate;
                map.put("powerBalanceRate", dto.getPowerBalanceRate());
                //其他费用占比(实收服务费、预约费、停车费等)otherBalanceRate
                map.put("otherBalanceRate", dto.getOtherBalanceRate());

        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    /**
     * @describe
     * 收入趋势
     */
    @RequestMapping(value = "/statistic/screenNewAjax/incomeTrend.json",method = RequestMethod.POST)
    public Map<String, Object> incomeTrend(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        SelectCondition condition=new SelectCondition();
        condition.setSellerId(sellerId);
        //总收入趋势totalIncomeTrend
        List<Map<String,Object>> totalIncomeTrend=getTotalIncomeTrend(condition);
        map.put("totalIncomeTrend", totalIncomeTrend);
        //非公交总收入趋势notTotalIncomeTrend
        condition.setMemberTypeEnum(MemberTypeEnum.GeRen);
        List<Map<String,Object>> notTotalIncomeTrend=getTotalIncomeTrend(condition);
        map.put("notTotalIncomeTrend", notTotalIncomeTrend);

        return map;
    }

    /**
     * @describe
     * 用户增长趋势以及用户活跃趋势
     */
    @RequestMapping(value = "/statistic/screenNewAjax/addAndActiveTrend.json",method = RequestMethod.POST)
    public Map<String, Object> addAndActiveTrend(){

        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        SelectCondition con2=new SelectCondition();
        con2.setSellerId(sellerId);
        //用户活跃量趋势（memberActiveTrend）--timeList(时间) personageList(个人充电次数)  teamList(车队会员充电次数)
        Map<String,Object> memberActiveTrend = getMemberChargeMap(con2);
        map.put("memberActiveTrend", memberActiveTrend);
        con2.setTimeSharingEnum(TimeSharingEnum.Month);
        //(总用户增长趋势)近12个月会员增长趋势（memberAddTrend）timeList(时间) personageList(个人会员增长)  teamList(车队会员增长)
        Map<String,Object> memberAddTrend = getMemberChargeMap(con2);
        map.put("memberAddTrend", memberAddTrend);

        return map;
    }

    /**
     * @describe
     * 站点总览数据
     */
    @RequestMapping(value = "/statistic/screenNewAjax/stationAllData.json",method = RequestMethod.POST)
    public Map<String, Object> stationAllData(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        StationDataDto dto = new StationDataDto();

        try {
            //redis-key：AT_商家号_查询的字段名
            String totalStationKey = String.format("AMT_%s_%s", sellerId, "total_station_count");
            String foreignKey = String.format("AMT_%s_%s", sellerId, "total_foreign_station_count");
            String internalKey = String.format("AMT_%s_%s", sellerId, "total_internal_station_count");
            String totalServiceTimeKey = String.format("AMT_%s_%s", sellerId, "total_construction_station_count");

            String bebuiltStationKey = String.format("AMT_%s_%s", sellerId, "total_bebuilt_station_count");
            String chargingPileKey = String.format("AMT_%s_%s", sellerId, "total_charging_pile_count");
            String chargingGunKey = String.format("AMT_%s_%s", sellerId, "total_charging_gun_count");
            TotalOperateData resDate =  new TotalOperateData();
            if(redisTemplate.opsForValue().get(totalStationKey) == null || redisTemplate.opsForValue().get(foreignKey) == null || redisTemplate.opsForValue().get(internalKey) == null || redisTemplate.opsForValue().get(totalServiceTimeKey) == null
                    || redisTemplate.opsForValue().get(bebuiltStationKey) == null || redisTemplate.opsForValue().get(chargingPileKey) == null || redisTemplate.opsForValue().get(chargingGunKey) == null){
                resDate = totalOperateMapper.get(sellerId);
            }
            //充电站总数量
            if(redisTemplate.opsForValue().get(totalStationKey) == null  && resDate != null){
                dto.setStatioTotal(ValUtil.toInteger(resDate.getTotalStationCount(), 0));
                redisTemplate.opsForValue().set(totalStationKey, resDate.getTotalStationCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setStatioTotal(ValUtil.toInteger(redisTemplate.opsForValue().get(totalStationKey), 0));
            }
            //对外充电站总数量
            if(redisTemplate.opsForValue().get(foreignKey) == null && resDate != null){
                dto.setOutsideCount(ValUtil.toInteger(resDate.getTotalForeignStationCount(), 0));
                redisTemplate.opsForValue().set(foreignKey, resDate.getTotalForeignStationCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setOutsideCount(ValUtil.toInteger(redisTemplate.opsForValue().get(foreignKey), 0));
            }
            //对内充电站总数量
            if(redisTemplate.opsForValue().get(internalKey) == null && resDate != null){
                dto.setInsideCount(ValUtil.toInteger(resDate.getTotalInternalStationCount(), 0));
                redisTemplate.opsForValue().set(internalKey, resDate.getTotalInternalStationCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setInsideCount(ValUtil.toInteger(redisTemplate.opsForValue().get(internalKey), 0));
            }
            //在建充电站总数量
            if(redisTemplate.opsForValue().get(totalServiceTimeKey) == null && resDate != null ){
                dto.setInConstructionCount(ValUtil.toInteger(resDate.getTotalConstructionStationCount(), 0));
                redisTemplate.opsForValue().set(totalServiceTimeKey, resDate.getTotalConstructionStationCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setInConstructionCount(ValUtil.toInteger(redisTemplate.opsForValue().get(totalServiceTimeKey), 0));
            }
            //待建充电站总数量
            if(redisTemplate.opsForValue().get(bebuiltStationKey) == null && resDate != null ){
                dto.setToBeBulitCount(ValUtil.toInteger(resDate.getTotalBebuiltStationCount(), 0));
                redisTemplate.opsForValue().set(bebuiltStationKey, resDate.getTotalBebuiltStationCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setToBeBulitCount(ValUtil.toInteger(redisTemplate.opsForValue().get(bebuiltStationKey), 0));
            }

            //桩数量
            if(redisTemplate.opsForValue().get(chargingPileKey) == null && resDate != null){
                dto.setPileCount(ValUtil.toInteger(resDate.getTotalChargingPileCount(), 0));
                redisTemplate.opsForValue().set(chargingPileKey, resDate.getTotalChargingPileCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setPileCount(ValUtil.toInteger(redisTemplate.opsForValue().get(chargingPileKey), 0));
            }
            //枪数量
            if(redisTemplate.opsForValue().get(chargingGunKey) == null && resDate != null ){
                dto.setGunCount(ValUtil.toInteger(resDate.getTotalChargingGunCount(), 0));
                redisTemplate.opsForValue().set(chargingGunKey, resDate.getTotalChargingGunCount(),expireTime,TimeUnit.SECONDS);
            }else{
                dto.setGunCount(ValUtil.toInteger(redisTemplate.opsForValue().get(chargingGunKey), 0));
            }
            if(resDate != null){
                if(dto.getStatioTotal() != 0){
                    double outsideRate = ValUtil.toDivBigDecimal(dto.getOutsideCount()*100, dto.getStatioTotal(), 2).doubleValue();
                    double insideRate = ValUtil.toDivBigDecimal(dto.getInsideCount()*100, dto.getStatioTotal(), 2).doubleValue();
                    dto.setOutsideRate(outsideRate);
                    dto.setInsideRate(insideRate);

                }
            }
            //站点总览
            map.put("station", dto);


        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    /**
     * @describe
     * 获取地图上的站点数据
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getStationBySeller.json",method = RequestMethod.POST)
    public Map<String, Object> getStationBySeller(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        String key = String.format("AT_%s_%s", sellerId, "all_station");
        String value = (String) redisTemplate.opsForValue().get(key);
        if(value == null){
            map.put("stationList", stationService.getStationList(sellerId));
            value = JSONObject.toJSONString(map);
            redisTemplate.opsForValue().set(key,value,60,TimeUnit.SECONDS);
        }
        map = (Map<String, Object>)JSONObject.parse(value);
        return map;
    }

    /**
     * @describe
     * 实时功率 离线率 故障率
     */
    @RequestMapping(value = "/statistic/screenNewAjax/getRealPower.json",method = RequestMethod.POST)
    public Map<String, Object> doGetRealPower(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        //获取总功率
//        String key = String.format("AT_%s_%s", sellerId, "total_equipment_power");
//        double allPower = (double)redisTemplate.opsForValue().get(key);
//        if(allPower == 0){
        double allPower = totalOperateMapper.get(sellerId).getTotalEquipmentPower().longValue();
//        }

        //获取实时功率
        int realTimePower =  (int)redisTemplate.opsForValue().get("powerSum-"+sellerId);
        int gunCount = (int)redisTemplate.opsForValue().get("gunCount-"+sellerId);
        map.put("realTimePower", AmountUtils.div(realTimePower, 1000,0));//单位千瓦
        //显示当前充电电桩的百分比值chargingPercent
        BigDecimal num=new BigDecimal(AmountUtils.div(realTimePower, 1000,3)).divide(new BigDecimal(String.valueOf(allPower)),4, BigDecimal.ROUND_HALF_UP);
        num=num.multiply(new BigDecimal(100)).setScale(2);
        map.put("chargingPercent", num);
        //离线率offLinePercent

        BigDecimal offLinePercent=new BigDecimal(String.valueOf(redisTemplate.opsForValue().get("offlineCount-"+sellerId))).divide(new BigDecimal(String.valueOf(redisTemplate.opsForValue().get("gunCount-"+sellerId))),4, BigDecimal.ROUND_HALF_UP);
        offLinePercent=offLinePercent.multiply(new BigDecimal(100)).setScale(2);
        map.put("offLinePercent", offLinePercent);
        //当前故障数
        int problemCount = (int)redisTemplate.opsForValue().get("problemCount-"+sellerId);
        map.put("problemCount", problemCount);
        //故障率problemPercent
        BigDecimal problemPercent=new BigDecimal(String.valueOf(problemCount)).divide(new BigDecimal(String.valueOf(redisTemplate.opsForValue().get("gunCount-"+sellerId))),4, BigDecimal.ROUND_HALF_UP);
        problemPercent=problemPercent.multiply(new BigDecimal(100)).setScale(2);
        map.put("problemPercent", problemPercent);

        return map;

    }







    /**
     * @describe
     * 用户总览->累计新增用户数
     */
    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getUsersViewData.json",method = RequestMethod.POST)
    public JSONObject getUsersViewData(){
        JSONObject resultJson = new JSONObject();
        SelectCondition condition = new SelectCondition();
        condition.setStartDate(new Date());
        condition.setSellerId(sellerId);
        ServiceMemberDto serviceMemberDto = getServiceMemberDto(condition);
        resultJson.put("memberTotal", serviceMemberDto.getMemberTotal());//服务的总会员数
        resultJson.put("teamCount", serviceMemberDto.getTeamCount());//车队数
        resultJson.put("personageCount", serviceMemberDto.getPersonageCount());//个人会员数
        resultJson.put("teamRate", serviceMemberDto.getTeamRate());//车队占比
        resultJson.put("personageRate", serviceMemberDto.getPersonageRate());//个人占比
        resultJson.put("appCount", serviceMemberDto.getAppCount());//app数
        resultJson.put("wxCount", serviceMemberDto.getWxCount());//微信数
        return resultJson;
    }

    /**
     * @describe
     * 用户总览->近30天新用户增长趋势
     */
    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getUserTrendOfMonthlyData.json",method = RequestMethod.POST)
    public JSONObject getUserTrendOfMonthlyData(){
        JSONObject resultJson = new JSONObject();
        SelectCondition condition = new SelectCondition();
        condition.setSellerId(sellerId);
        condition.setStartDate(new Date());
        condition.setTimeSharingEnum(TimeSharingEnum.Day);
        Map<String, Object> memberAddMap = getMemberAddMap(condition);
        resultJson.put("timeList", memberAddMap.get("timeList"));//近三十天的日期
        resultJson.put("personageList", memberAddMap.get("personageList"));//个人会员数
        resultJson.put("teamList", memberAddMap.get("teamList"));//车队会员数
        return resultJson;

    }

    /**
     * @describe
     * 用户总览->累计新增用户数
     */
    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getUserCountOfMonthlyAndDayData.json",method = RequestMethod.POST)
    public JSONObject getUserCountOfMonthlyAndDayData(){
        JSONObject resultJson = new JSONObject();
        SelectCondition condition = new SelectCondition();
        condition.setTimeSharingEnum(TimeSharingEnum.Month);
        condition.setStartDate(new Date());
        condition.setSellerId(sellerId);
        int memberCountOfMonthly = getMemberCount(condition);
        condition.setTimeSharingEnum(TimeSharingEnum.Day);
        int memberCountOfDay = getMemberCount(condition);
        resultJson.put("memberCountOfMonthly", memberCountOfMonthly);
        resultJson.put("memberCountOfToday", memberCountOfDay);
        return resultJson;

    }

    /**
     * @describe
     * 用户总览->总用户增长趋势  todo 访问速度有点慢有空优化一下
     */
    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getTotalUserIncreaseTrendData.json",method = RequestMethod.POST)
    public JSONObject getTotalUserIncreaseTrendData(){
        JSONObject resultJson = new JSONObject();
        SelectCondition condition = new SelectCondition();
        condition.setSellerId(sellerId);
        Map<String,Object> memberAddByWeek = getMemberAddByWeek(condition);
        resultJson.put("person", memberAddByWeek.get("personageTotalList"));
        resultJson.put("team", memberAddByWeek.get("teamTotalList"));
        return resultJson;
    }

//    /**
//     * @describe
//     * 获取正在服务中用户数据
//     */
//    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getInServiceUsersCount.json",method = RequestMethod.POST)
//    public Map getInServiceUsersCount(){
//        JSONObject resultJson = new JSONObject();
//        int inServiceUserCount = 0;
//        Set<String>memberNoSet = new HashSet<>();
//
//        //从redis里取 这个商家下所有站点信息  或者从数据库取并存入redis
//        JSONArray stationBySeller = getStationInfo();
//
//        List<GunMonitorDto> gunByStation = null;
//        for(Object o : stationBySeller) {
//            JSONObject station = (JSONObject) o;
//            memberNoSet = new HashSet<>();
//            gunByStation = stationService.getGunByStation(station.getLong("stationId"));
//            if(!CollectionUtils.isEmpty(gunByStation)) {
//                for(GunMonitorDto gun : gunByStation) {
//                    if(PileUtil.isInterflow(gun.getPileDto()) || gun.getGunState() != GunStateEnum.CHARGING) {
//                        continue;
//                    }
//                    //用户多充算一个
//                    if(StringUtils.isNotEmpty(gun.getMemberNo())) {
//                        memberNoSet.add(gun.getMemberNo());
//                    }else {
//                        inServiceUserCount++;
//                    }
//                }
//            }
//        }
//
//        resultJson.put("inServiceUserCount", inServiceUserCount+memberNoSet.size());
//        return resultJson;
//    }

    /**
     * @describe
     * 用户运营
     */
    @RequestMapping(value = "/statistic/screenNewAjax/userOperateData.json",method = RequestMethod.POST)
    public Map<String, Object> doUserOperateData(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();

        SelectCondition condition=new SelectCondition();
        condition.setSellerId(sellerId);
        //累计服务用户数totalServiceMemberNum
        int totalServiceMemberNum=stationService.getMemberChargeCount(sellerId);
        map.put("totalServiceMemberNum", totalServiceMemberNum);
        MemberDataDto memberData=stationService.getMemberDataDto(condition);
        map.put("memberData", memberData);

        return map;

    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public JSONArray getStation(){



        return null;

    }





    /**
     * @describe
     * 用户总览中央地图模块->使用充电服务中的用户数
     */
    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getInServiceUsersCount.json",method = RequestMethod.POST)
    public JSONObject getInServiceUsersCount(){
        JSONObject resultJson = new JSONObject();
        int inServiceUserCount = 0;
        Set<String>memberNoSet = new HashSet<>();
        List<GunMonitorDto> guns = localMemoryData.getGunBySeller(sellerId);
        if(CollectionUtil.isNotEmpty(guns)) {
            for(GunMonitorDto gun : guns) {
                // gun.getGunState() != 3 老架构是这个  迁移之后gunState为null
                if(gun.getRealGunState() != 3) {
                    continue;
                }
                //用户多充算一个
                if(StrUtil.isNotEmpty(gun.getMemberNo())) {
                    memberNoSet.add(gun.getMemberNo());
                }else {
                    inServiceUserCount++;
                }
            }
        }
        resultJson.put("inServiceUserCount", inServiceUserCount+memberNoSet.size());
        return resultJson;
    }




    private JSONArray getStationInfo(){
        //从redis里取 这个商家下所有站点信息  或者从数据库取并存入redis
        JSONArray stationBySeller = new JSONArray();
        String stationInfoUnderSellerId = String.format("AT_%s_%s", sellerId, "station_info_under_sellerId");
        if(redisTemplate.opsForValue().get(stationInfoUnderSellerId) != null){
            stationBySeller = (JSONArray) redisTemplate.opsForValue().get(stationInfoUnderSellerId);
        }else {
            List<Station> stations = stationMapper.findBySeller(sellerId);
            for(Station station :stations){
                JSONObject stationDto = new JSONObject();
                stationDto.put("stationId",station.getId());
                stationDto.put("stationName",station.getName());
                stationDto.put("lng",station.getLongitude());
                stationDto.put("lat",station.getLatitude());
                stationBySeller.add(stationDto);
            }
            redisTemplate.opsForValue().set(stationInfoUnderSellerId,stationBySeller,1,TimeUnit.DAYS);
        }
        return stationBySeller;
    }

    /**
     * @describe
     * 设备故障率
     */
    @RequestMapping(value = "statistic/screenNewAjax/deviceProblemData.json",method = RequestMethod.POST)
    public Map<String, Object> getDeviceProblemData(){
        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        SelectCondition con2=new SelectCondition();
        con2.setSellerId(sellerId);
        //故障曲线图falutMap
        Map<String, Object> falutMap=stationService.getFaultCountMap(con2);
        map.put("falutMap", falutMap);
        con2.setStartDate(new Date());
        Map<String,Object> alarm=stationService.getFaultAndRate(con2);
        //故障数faultCount
        int faultCount=(int) alarm.get("faultCount");
        map.put("faultCount", faultCount);
        //占比rate故障率
        double rate =(double) alarm.get("rate");
        map.put("rate", rate);

        return map;

    }

    /**
     * 获取站点的实时功率
     * @param stationID
     * @return
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getRealtimePower.json",method = RequestMethod.POST)
    public Map<String, Object> doGetRealtimePower(Long stationID) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        float realTimePower = 0;// 实时功率
        if (stationID != null) {
            List<GunMonitorDto> getGunByStation = localMemoryData.getGunByStation(stationID);
            for (GunMonitorDto real : getGunByStation) {
                if (real.getRealGunState() == 3 && real.getCurrent() != null
                        && real.getVoltage() != null) {
                    realTimePower += (real.getCurrent() / 1000) * (real.getVoltage() / 1000);
                }
            }
            result.put("realTimePower", AmountUtils.divOfStr(realTimePower + "", 1000 + "", 0));// 实时总功率
        } else {
            result.put("realTimePower", 0);
        }

        return result;

    }




    /**
     *收入趋势柱状图整理数据
     * @param condition
     * @return
     */
    private  List<Map<String,Object>> getTotalIncomeTrend(SelectCondition condition){
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        Map<String, Object> teamAndPersonChargePower = getTotalIncomeMap(condition);
        List timeList=(List) teamAndPersonChargePower.get("timeList");
        List powerBalanceList=(List) teamAndPersonChargePower.get("powerBalanceList");
        List otherBalanceList=(List) teamAndPersonChargePower.get("otherBalanceList");
        Map<String, Object> dataMap = null;
        for (int i = 0; i < timeList.size(); i++){
            dataMap = new HashMap<String, Object>();
            dataMap.put("data", timeList.get(i));
            dataMap.put("充电", TypeRotationUtil.stringTurnYuan((Long)powerBalanceList.get(i)));
            dataMap.put("其他", TypeRotationUtil.stringTurnYuan((Long)otherBalanceList.get(i)));
            data.add(dataMap);
        }
        return data;
    }

    private Map<String, Object> getTotalIncomeMap(SelectCondition condition) {
        Map<String, Object> mapData = new HashMap<>();
        List<String> timeList = new ArrayList<>();
        for(int i=11; i >=0; i--){
            String time = TimeUtils.format(TimeUtils.addMonth(new Date(), -i), "yyyy-MM");
            timeList.add(time);
        }

        List<Long> chargeBalanceList = new ArrayList<>();
        List<Long> personalBlanceList = new ArrayList<>();

        AllMonthData res = new AllMonthData();
        for (String moth: timeList) {
            //AMT_商家号_月份(yyyy-MM)_查询的字段名
            String chargeBalanceKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), moth, "month_i_charging_actual_balance");//()月充电总收入
            String otheKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), moth, "month_i_service_actual_balance");//月其他总收入

            String personalBlanceKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), moth, "month_personal_i_charging_actual_balance");//月个人(其他)充电收入
            String personaOtherKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), moth, "month_personal_i_service_actual_balance");//月个人其他总收入

            //1、 如果redis中不存在数据从数据库中获取数据集
            condition.setStartTime(moth);
            condition.setEndTime(moth);
            if (redisTemplate.opsForValue().get(chargeBalanceKey) == null || redisTemplate.opsForValue().get(otheKey) == null || redisTemplate.opsForValue().get(otheKey) == null || redisTemplate.opsForValue().get(personaOtherKey) == null) {
                if(condition.getMemberTypeEnum() != null &&"1".equals(condition.getMemberTypeEnum().getCode().shortValue())){
                    res = allMonthDataMapper.get(sellerId,condition.getStartTime());

                }else {
                    res = allMonthDataMapper.get(sellerId,condition.getStartTime());
                }
            }
            //2、当变量 memberTypeEnum等于1时查询月个人(其他)充电收入和月个人其他总收入
            if(condition.getMemberTypeEnum() != null && "1".equals(condition.getMemberTypeEnum().getCode().shortValue())){
                if (redisTemplate.opsForValue().get(personalBlanceKey) == null) {
                    //2,1如果数据库有数据从数据库中取
                    if (res != null) {
                        //2.1.1取出的数据存进redis
                        redisTemplate.opsForValue().set(personalBlanceKey, res.getMonthPersonalIChargingActualBalance(),expireTime,TimeUnit.SECONDS);
                        Long chargeBalance = ValUtil.toLong(res.getMonthPersonalIChargingActualBalance(), 0L);
                        chargeBalanceList.add(chargeBalance);
                    } else {
                        //2.1.2如果redis和数据库中都不存在，对应月份的数据则返回空
                        chargeBalanceList.add(0L);
                    }
                } else {
                    //2.1.3如果redis中存在，直接从redis获取
                    Long chargeBalance = ValUtil.toLong(redisTemplate.opsForValue().get(personalBlanceKey), 0L);
                    chargeBalanceList.add(chargeBalance);
                }

                if (redisTemplate.opsForValue().get(personaOtherKey) == null) {
                    //2.2如果数据库有数据从数据库中取
                    if (res != null) {
                        //2.2.1取出的数据存进redis
                        redisTemplate.opsForValue().set(personaOtherKey, res.getMonthPersonalIServiceActualBalance(),expireTime,TimeUnit.SECONDS);
                        Long personageOtherRevenue = ValUtil.toLong(res.getMonthPersonalIServiceActualBalance(), 0L);
                        personalBlanceList.add(personageOtherRevenue);
                    } else {
                        // 2.1.2如果redis和数据库中都不存在，对应月份的数据则返回空
                        personalBlanceList.add(0L);
                    }
                } else {
                    //2.1.3如果redis中存在，直接从redis获取
                    Long personageOtherRevenue = ValUtil.toLong(redisTemplate.opsForValue().get(personaOtherKey), 0L);
                    personalBlanceList.add(personageOtherRevenue);
                }
            }else{
                //3、当变量 memberTypeEnum等于空时查询公交总收入公交充电总收入
                if (redisTemplate.opsForValue().get(chargeBalanceKey) == null) {
                    //3.1如果数据库有数据从数据库中取
                    if (res != null) {
                        //3.1.1取出的数据存进redis
                        redisTemplate.opsForValue().set(chargeBalanceKey,res.getMonthIChargingActualBalance(),expireTime,TimeUnit.SECONDS);
                        Long chargeBalance = ValUtil.toLong(res.getMonthIChargingActualBalance(), 0L);
                        chargeBalanceList.add(chargeBalance);
                    } else {
                        //3.1.2如果redis和数据库中都不存在，对应月份的数据则返回空
                        chargeBalanceList.add(0L);
                    }
                } else {
                    //3.1.3如果redis中存在，直接从redis获取
                    Long chargeBalance = ValUtil.toLong(redisTemplate.opsForValue().get(chargeBalanceKey), 0L);
                    chargeBalanceList.add(chargeBalance);
                }

                if (redisTemplate.opsForValue().get(personaOtherKey) == null) {
                    //3.2如果数据库有数据从数据库中取
                    if (res != null) {
                        //3.2.1取出的数据存进redis
                        redisTemplate.opsForValue().set(personaOtherKey, res.getMonthIServiceActualBalance(),expireTime,TimeUnit.SECONDS);
                        Long personageCount = ValUtil.toLong(res.getMonthIServiceActualBalance(), 0L);
                        personalBlanceList.add(personageCount);
                    } else {
                        // 3.2.2如果redis和数据库中都不存在，对应月份的数据则返回空
                        personalBlanceList.add(0L);
                    }
                } else {
                    //3.2.3如果redis中存在，直接从redis获取
                    Long personageCount = ValUtil.toLong(redisTemplate.opsForValue().get(personaOtherKey), 0L);
                    personalBlanceList.add(personageCount);
                }
            }
        }
        mapData.put("powerBalanceList", chargeBalanceList);
        mapData.put("otherBalanceList", personalBlanceList);
        mapData.put("timeList", timeList);
        return mapData;
    }

    /**
     * @Title
     * @Description：近30天会员充电活跃量趋势
     * @Param： SelectCondition
     * @Return：Map personageList(个人充电次数)  teamList(车队会员充电次数)
     * @author <a>huangxw</a>
     * @CreateDate：2019年9月17日10:53:59
     *@update：[序号] [日期YYYY-MM-DD] [更改人名] [变更描述]
     */
    private Map<String, Object> getMemberChargeMap(SelectCondition condition) {
        Map<String, Object> mapData = new HashMap<>();
        List<String> timeList = new ArrayList<>();
        List<Integer> personageList = new ArrayList<>();
        List<Integer> teamList = new ArrayList<>();
        AllDayData res = new AllDayData();
        for(int i = 29; i>=0; i--){
            String time = TimeUtils.format(TimeUtils.addDay(new Date(), -i), "yyyy-MM-dd");
            timeList.add(time);
        }

        for (String date: timeList){
            //ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
            String temkey = String.format("ADT_%s_%s_%s", condition.getSellerId(), date, "day_team_active_count");//车队
            String personalkey = String.format("ADT_%s_%s_%s", condition.getSellerId(), date, "day_personal_active_count");//个人
            condition.setStartTime(date);
            if( redisTemplate.opsForValue().get(temkey) == null ){
                res =  allDayMapper.getUserActivityList(sellerId,condition.getStartTime());
                //1,2如果数据库有数据从数据库中取
                if( res != null ){
                    //1.2.1取出的数据存进redis
                    redisTemplate.opsForValue().set(temkey, res.getDayTeamActiveCount(),expireTime,TimeUnit.SECONDS);
                    Integer teamCount = ValUtil.toInteger(res.getDayTeamActiveCount(), 0);
                    teamList.add(teamCount);
                }
                else {
                    //1,.3如果redis和数据库中都不存在，对应月份的数据则返回空
                    teamList.add(0);
                }
            }
            else{
                //1,4如果redis中存在，直接从redis获取
                Integer teamPower=  ValUtil.toInteger(redisTemplate.opsForValue().get(temkey), 0);
                teamList.add(teamPower);
            }

            //1,1如果redis中不存在从数据库中取
            if( redisTemplate.opsForValue().get(personalkey) == null ){
                res =  allDayMapper.getUserActivityList(sellerId,condition.getStartTime());
                //1,2如果数据库有数据从数据库中取
                if( res != null ){
                    //1.2.1取出的数据存进redis

                    redisTemplate.opsForValue().set(personalkey,res.getDayPersonalActiveCount(),expireTime,TimeUnit.SECONDS);
                    Integer personageCount = ValUtil.toInteger(res.getDayPersonalActiveCount(), 0);
                    personageList.add(personageCount);
                }
                else {
                    // 1,.3如果redis和数据库中都不存在，对应月份的数据则返回空
                    personageList.add(0);
                }
            }
            else{
                //1,4如果redis中存在，直接从redis获取
                Integer otherPower =  ValUtil.toInteger(redisTemplate.opsForValue().get(personalkey), 0);
                personageList.add(otherPower);
            }
        }

        mapData.put("timeList", timeList);
        mapData.put("personageList", personageList);
        mapData.put("teamList", teamList);

        return mapData;
    }

    private int getMemberCount(SelectCondition condition) {
        BigInteger a = null;
        BigInteger b = null;
        Integer dayMemberCount=0;
        Integer dayPersonalCount=0;
        try {
            //redisKey ->ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
            //redisKey ->AMT_商家号_月份(yyyy-MM)_查询的字段名
            String dayMemberCountKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"), "day_growth_team_user");//日车队用户增长数
            String dayPersonalCountKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"), "day_growth_personal_user");//日个人用户增长数
            String mothMemberCountKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM"), "month_growth_team_user");//月车队用户增长数
            String mothPersonalCountKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), TimeUtils.format(condition.getStartDate(),"yyyy-MM"), "month_growth_personal_user");//月个人用户增长数

            if(redisTemplate.opsForValue().get(dayMemberCountKey) == null || redisTemplate.opsForValue().get(dayPersonalCountKey) == null  || redisTemplate.opsForValue().get(mothMemberCountKey) == null || redisTemplate.opsForValue().get(mothPersonalCountKey) == null){
                if(condition.getTimeSharingEnum().getCoetx() == 3){
                    AllDayData obj = allDayMapper.get(sellerId, TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"));
                    if(obj != null){
                        a = obj.getDayGrowthTeamUser();
                        b = obj.getDayGrowthPersonalUser();
                    }
                }else {
                    //(condition.getTimeSharingEnum().getCoetx() == 2)
                    AllMonthData obj = allMonthDataMapper.get(sellerId, TimeUtils.format(condition.getStartDate(),"yyyy-MM"));
                    if(obj != null){
                        a = obj.getMonthGrowthTeamUser();
                        b = obj.getMonthGrowthPersonalUser();
                    }
                }
            }
            //1如果传的日期不为空，类型为 2（月）
            if( condition.getStartDate()!= null  && condition.getTimeSharingEnum() != null && condition.getTimeSharingEnum().getCoetx() == 2 ){

                //1.1、如果redis没有数据从数据库获取
                if(redisTemplate.opsForValue().get(mothMemberCountKey) == null ){
                    if(a != null) {
                        dayMemberCount = ValUtil.toInteger(a, 0);
                        redisTemplate.opsForValue().set(mothMemberCountKey,dayMemberCount,expireTime,TimeUnit.SECONDS);
                    }
                }else{
                    //1.2、如果redism有数据从redis获取
                    dayMemberCount = ValUtil.toInteger(redisTemplate.opsForValue().get(mothMemberCountKey),0);
                }
                //1.3、如果redis没有数据从数据库获取
                if(redisTemplate.opsForValue().get(mothPersonalCountKey) == null ){
                    if(b != null) {
                        dayPersonalCount = ValUtil.toInteger(b, 0);
                        redisTemplate.opsForValue().set(mothPersonalCountKey,dayPersonalCount,expireTime,TimeUnit.SECONDS);
                    }
                }else{
                    //1.4、如果redism有数据从redis获取
                    dayPersonalCount = ValUtil.toInteger(redisTemplate.opsForValue().get(mothPersonalCountKey),0);
                }
                return  dayMemberCount + dayPersonalCount;
            }else {
                //2如果传的日期为空，类型为 3（日）
                //2.1如果redis没有数据从数据库获取
                if(redisTemplate.opsForValue().get(dayMemberCountKey) == null ){
                    if(a != null) {
                        dayMemberCount = ValUtil.toInteger(a, 0);
                        redisTemplate.opsForValue().set(dayMemberCountKey,dayMemberCount,expireTime,TimeUnit.SECONDS);
                    }
                }else{
                    //1.2、如果redism有数据从redis获取
                    dayMemberCount = ValUtil.toInteger(redisTemplate.opsForValue().get(dayMemberCountKey),0);
                }
                //1.3、如果redis没有数据从数据库获取
                if(redisTemplate.opsForValue().get(dayPersonalCountKey) == null ){
                    if(b != null) {
                        dayPersonalCount = ValUtil.toInteger(b, 0);
                        redisTemplate.opsForValue().set(dayPersonalCountKey,dayPersonalCount,expireTime,TimeUnit.SECONDS);
                    }
                }else{
                    //1.4、如果redism有数据从redis获取
                    dayPersonalCount = ValUtil.toInteger(redisTemplate.opsForValue().get(dayPersonalCountKey),0);
                }
                return  dayMemberCount + dayPersonalCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Map<String, Object> getMemberAddMap(SelectCondition condition) {
        condition.setTimeSharingEnum(TimeSharingEnum.Month);
        List<Integer> personageList = new ArrayList<>();
        List<Integer> teamList = new ArrayList<>();
        Map<String, Object> mapData = new HashMap<>();
        List<String> timeList = new ArrayList<>();
        //初始化日期集合
        if(condition != null && condition.getTimeSharingEnum() != null){
            if(condition.getTimeSharingEnum().getCoetx() == 2){
                for(int i=11; i >=0; i--){
                    String time = TimeUtils.format(TimeUtils.addMonth(new Date(), -i), "yyyy-MM");
                    timeList.add(time);
                }
            }
            if(condition.getTimeSharingEnum().getCoetx() == 3){
                for(int i=29; i >=0; i--){
                    String time = TimeUtils.format(TimeUtils.addDay(new Date(), -i), "yyyy-MM-dd");
                    timeList.add(time);
                }
            }
        }
        //1 开始遍历
        for (String date: timeList){
            //AMT_商家号_月份(yyyy-MM)_查询的字段名
            String temkey = null;
            String personalkey = null;
            if(condition != null && condition.getTimeSharingEnum() != null) {
                if (condition.getTimeSharingEnum().getCoetx() == 2) {
                    temkey = String.format("AMT_%s_%s_%s", condition.getSellerId(), date, "month_growth_team_user");
                    personalkey = String.format("AMT_%s_%s_%s", condition.getSellerId(), date, "month_growth_personal_user");
                }
            }
            //ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
            if(condition.getTimeSharingEnum().getCoetx() == 3){
                temkey = String.format("ADT_%s_%s_%s", condition.getSellerId(), date, "day_growth_team_user");
                personalkey = String.format("ADT_%s_%s_%s", condition.getSellerId(), date, "day_growth_personal_user");
            }
            condition.setStartTime(date);
            condition.setEndTime(date);
            if( redisTemplate.opsForValue().get(temkey) == null ){
                BigInteger ob ;
                if(condition.getTimeSharingEnum().getCoetx() == 3){
                    ob = allDayMapper.get(sellerId,date).getDayGrowthTeamUser();
                }else {
                    //(condition.getTimeSharingEnum().getCoetx() == 2)
                    ob = allMonthDataMapper.get(sellerId,date).getMonthGrowthTeamUser();
                }
                //1,1如果数据库有数据从数据库中取
                if( ob!= null ){
                    //1.1.1、取出的数据存进redis
                    redisTemplate.opsForValue().set(temkey, ob,expireTime,TimeUnit.SECONDS);
                    Integer teamCount = ValUtil.toInteger(ob, 0);
                    teamList.add(teamCount);
                }
                else {
                    // 1.1.1、如果redis和数据库中都不存在，对应月份的数据则返回空
                    teamList.add(0);
                }
            }else{
                //1.1.2如果redis中存在，直接从redis获取
                Integer teamPower=  ValUtil.toInteger(redisTemplate.opsForValue().get(temkey), 0);
                teamList.add(teamPower);
            }

            //1,2如果redis中不存在从数据库中取
            if( redisTemplate.opsForValue().get(personalkey) == null ){
                BigInteger ob ;
                if(condition.getTimeSharingEnum().getCoetx() == 3){
                    ob = allDayMapper.get(sellerId,date).getDayGrowthPersonalUser();
                }else {
                    //(condition.getTimeSharingEnum().getCoetx() == 2)
                    ob = allMonthDataMapper.get(sellerId,date).getMonthGrowthPersonalUser();
                }
                //1.2.1如果数据库有数据从数据库中取
                if( ob != null ){
                    //1.2.2取出的数据存进redis
                    redisTemplate.opsForValue().set(personalkey, ob,expireTime,TimeUnit.SECONDS);
                    Integer personageCount = ValUtil.toInteger(ob, 0);
                    personageList.add(personageCount);
                }
                else {
                    // 1.2.3如果redis和数据库中都不存在，对应月份的数据则返回空
                    personageList.add(0);
                }
            }else{
                //1.2.4如果redis中存在，直接从redis获取
                Integer otherPower =  ValUtil.toInteger(redisTemplate.opsForValue().get(personalkey), 0);
                personageList.add(otherPower);
            }
        }
        mapData.put("timeList", timeList);
        mapData.put("personageList", personageList);
        mapData.put("teamList", teamList);
        return mapData;
    }
    
    
    private ServiceMemberDto getServiceMemberDto(SelectCondition condition) {
        ServiceMemberDto dto = new ServiceMemberDto();
        try {
            AllDayData object = new AllDayData();
            //redis-key：ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
            String time = TimeUtils.format(condition.getStartDate(), "yyyy-MM-dd");
            String appUserKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), time, "day_service_app_user");//app用户数
            String wechatUserKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), time, "day_service_wechat_user");//微信数
            String personanlKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), time, "personageCount");//个人数
            String teamKey = String.format("AMT_%s_%s_%s", condition.getSellerId(), time, "teamCount");//车队数
            if(redisTemplate.opsForValue().get(appUserKey) == null || redisTemplate.opsForValue().get(wechatUserKey) == null || redisTemplate.opsForValue().get(personanlKey) == null || redisTemplate.opsForValue().get(teamKey) == null){
                object = allDayMapper.get(sellerId, TimeUtils.format(condition.getStartDate(),"yyyy-MM-dd"));
            }
            //app
            if(redisTemplate.opsForValue().get(appUserKey) == null && object != null ){
                dto.setAppCount(ValUtil.toInteger(object.getDayServiceAppUser(), 0));
            }else{
                dto.setAppCount(ValUtil.toInteger(redisTemplate.opsForValue().get(appUserKey), 0));
            }
            //wetch
            if(redisTemplate.opsForValue().get(wechatUserKey) == null && object != null ){
                dto.setWxCount(ValUtil.toInteger(object.getDayServiceWechatUser(), 0));
            }else{
                dto.setWxCount(ValUtil.toInteger(redisTemplate.opsForValue().get(wechatUserKey), 0));
            }

            //team
            if(redisTemplate.opsForValue().get(teamKey) == null && object != null ){
                dto.setTeamCount(ValUtil.toInteger(object.getDayTeamServiceUser(), 0));
            }else{
                dto.setTeamCount(ValUtil.toInteger(redisTemplate.opsForValue().get(teamKey), 0));
            }

            //personan
            if(redisTemplate.opsForValue().get(personanlKey) == null && object != null ){
                dto.setPersonageCount(ValUtil.toInteger(object.getDayPersonalServiceUser(), 0));
            }else{
                dto.setPersonageCount(ValUtil.toInteger(redisTemplate.opsForValue().get(personanlKey), 0));
            }
            int membertotal = dto.getTeamCount() + dto.getPersonageCount();
            dto.setMemberTotal(membertotal);


            if(dto.getMemberTotal() != 0){
                double teamRate = ValUtil.toDivBigDecimal(dto.getTeamCount()*100, dto.getMemberTotal(), 2).doubleValue();
                double personageRate = ValUtil.toDivBigDecimal(dto.getPersonageCount()*100, dto.getMemberTotal(), 2).doubleValue();
                dto.setTeamRate(teamRate);
                dto.setPersonageRate(personageRate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    private Map<String, Object> getMemberAddByWeek(SelectCondition condition) {
        Map<String, Object> mapData = new HashMap<>();
        List<Integer> personageTotalList = new ArrayList<>();
        List<Integer> teamTotalList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int dayOfYear = c.get(Calendar.DAY_OF_YEAR);
        if(dayOfYear >= 35){
            dayOfYear = 35;
        }
        //1、 初始化日期集合
        List<String> timeList = new ArrayList<>();
        for(int i=dayOfYear-1; i>=0; i--){
            timeList.add(TimeUtils.format(TimeUtils.addDay(new Date(), -i), "yyyy-MM-dd"));
        }
        //2、 获取每个月车队或个人用户增长数
        AllDayData res = new AllDayData();
        for (String dateTime: timeList) {
            // redis-key：ADT_商家号_日期(yyyy-MM-dd)_查询的字段名
            String dayGrowthTeamUserKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), dateTime, "day_growth_team_user");//日车队用户增长数
            String dayGrowthPersonalUserKey = String.format("ADT_%s_%s_%s", condition.getSellerId(), dateTime, "day_growth_personal_user");//日个人用户增长数
            //设置月份变量
            condition.setStartTime(dateTime);
            //2.1、 如果redis中不存在数据从数据库中获取数据集
            if (redisTemplate.opsForValue().get(dayGrowthTeamUserKey) == null || redisTemplate.opsForValue().get(dayGrowthPersonalUserKey) == null ) {
                res = allDayMapper.get(sellerId,dateTime);
            }

            //3、 获取日车队用户增长数
            if (redisTemplate.opsForValue().get(dayGrowthTeamUserKey) == null) {
                //3,1如果数据库有数据从数据库中取
                if (res != null) {
                    //3.1.1取出的数据存进redis
                    redisTemplate.opsForValue().set(dayGrowthTeamUserKey, res.getDayGrowthTeamUser(),expireTime,TimeUnit.SECONDS);
                    Integer teamCount = ValUtil.toInteger(res.getDayGrowthTeamUser());
                    teamTotalList.add(teamCount);
                } else {
                    //3.1.2如果redis和数据库中都不存在，对应月份的数据则返回空
                    teamTotalList.add(0);
                }
            } else {
                //3.1.3如果redis中存在，直接从redis获取
                Integer chargeBalance = ValUtil.toInteger(redisTemplate.opsForValue().get(dayGrowthTeamUserKey));
                teamTotalList.add(chargeBalance);
            }
            //4、获取日个人用户增长数
            if (redisTemplate.opsForValue().get(dayGrowthPersonalUserKey) == null) {
                //4,1如果数据库有数据从数据库中取
                if (res != null) {
                    //4.1.1 取出的数据存进redis
                    redisTemplate.opsForValue().set(dayGrowthPersonalUserKey, res.getDayGrowthPersonalUser(),expireTime,TimeUnit.SECONDS);
                    Integer personal = ValUtil.toInteger(res.getDayGrowthPersonalUser());
                    personageTotalList.add(personal);
                } else {
                    //4.1.2如果redis和数据库中都不存在，对应月份的数据则返回空
                    personageTotalList.add(0);
                }
            } else {
                //4.1.3如果redis中存在，直接从redis获取
                Integer chargeBalance = ValUtil.toInteger(redisTemplate.opsForValue().get(dayGrowthPersonalUserKey));
                personageTotalList.add(chargeBalance);
            }
        }



        try {
            //转换日期将天转换周
            int personageOneTotal = 0,personageTwoTotal = 0,personageThreeTotal = 0,
                    personageFourTotal = 0,personageFiveTotal = 0,flag = 1;
            int teamOneTotal = 0,teamTwoTotal = 0,teamThreeTotal = 0,
                    teamFourTotal = 0,teamFiveTotal = 0;
            for(int i=0; i<timeList.size(); i++){
                if(flag<=7){
                    personageOneTotal += personageTotalList.get(i);//一周
                    teamOneTotal += teamTotalList.get(i);
                }
                if(7<flag && flag<=14){
                    personageTwoTotal += personageTotalList.get(i);//二周
                    teamTwoTotal += teamTotalList.get(i);
                }
                if(14<flag && flag<=21){
                    personageThreeTotal += personageTotalList.get(i);//三周
                    teamThreeTotal += teamTotalList.get(i);
                }
                if(21<flag && flag<=28){
                    personageFourTotal += personageTotalList.get(i);//四周
                    teamFourTotal += teamTotalList.get(i);
                }
                if(28<flag && flag<=35){
                    personageFiveTotal += personageTotalList.get(i);//五周
                    teamFiveTotal += teamTotalList.get(i);
                }
                flag ++;
            }

            personageTotalList.add(personageOneTotal);
            personageTotalList.add(personageTwoTotal);
            personageTotalList.add(personageThreeTotal);
            personageTotalList.add(personageFourTotal);
            personageTotalList.add(personageFiveTotal);

            teamTotalList.add(teamOneTotal);
            teamTotalList.add(teamTwoTotal);
            teamTotalList.add(teamThreeTotal);
            teamTotalList.add(teamFourTotal);
            teamTotalList.add(teamFiveTotal);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mapData.put("personageTotalList", personageTotalList);
        mapData.put("teamTotalList", teamTotalList);
        return mapData;
    }



}
