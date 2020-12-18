package com.nzkj.screen.Controller;

import com.nzkj.screen.Entity.DTO.IncomeRankDto;
import com.nzkj.screen.Entity.DTO.SelectCondition;
import com.nzkj.screen.Entity.DTO.StationDto;
import com.nzkj.screen.Service.StationService;
import com.nzkj.screen.Utils.AmountUtils;
import com.nzkj.screen.Utils.TypeRotationUtil;
import com.nzkj.screen.mapper.pile.config.IStationMapper;
import com.nzkj.screen.mapper.pile.screen.IAllDayMapper;
import com.nzkj.screen.mapper.pile.screen.IAllMonthDataMapper;
import com.nzkj.screen.mapper.pile.screen.ITotalOperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Liu yang
 * @Date: 2020/7/8 15:59
 * Describe:
 */
@RestController
public class ScreenDataController {

    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate (RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }


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
     * 站点累计总收入,列表
     */
    @RequestMapping(value = "/statistic/ScreenNewInCome/GetBalances.json",method = RequestMethod.POST)
    public Map<String, Object> getStationInServiceMapData(Long stationId){
        Map<String, Object> json = new HashMap<>(2);
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        try {
            Map<Long, Long> getBalances = stationService.getBalances(sellerId, userId);
            Map<String, Object> dataMap = null;
            if(getBalances!=null) {
                for (Entry<Long, Long> entry : getBalances.entrySet()) {
                    StationDto station=stationService.findById(entry.getKey());
                    if(station!=null&&station.getName()!=null) {
                        dataMap = new HashMap<String, Object>();
                        dataMap.put("stationId", entry.getKey());
                        dataMap.put("stationName",station.getName());
                        String valueStr="";
                        if(entry.getValue()!=null) {
                            if(entry.getValue()>1000000) {
                                valueStr=entry.getValue()/1000000+"万元";
                            }else {
                                valueStr=entry.getValue()/100+"元";
                            }}
                        dataMap.put("income", valueStr);
                        dataMap.put("longitude",station.getLongitude() );
                        dataMap.put("latitude",station.getLatitude() );
                        data.add(dataMap);
                    }
                }
            }
            json.put("data", data);
            json.put("stateCode", 0);
        } catch (Exception e) {
            json.put("getBalance", "");
            json.put("stateCode", 1);
        }
        return json;
    }

    /**
     * @describe
     * 当日累计收入-当日充电收入-当日公交充电收入和占比-非公交累计总收入-非公交充电收入和占比
     * @return powerBalanceAll(当日充电收入-所有车辆纯电费收入), GongJiaoPowerBalance(公交车辆纯电费收入), otherPowerBalance(非公交车辆纯电费收入),
     *  actualBalanceAll(当日累计收入-所有车辆总收入), otherActualBalance(非公交累计总收入) 单位:分
     */
    @RequestMapping(value = "/statistic/ScreenNewInCome/GetTransBalance.json",method = RequestMethod.POST)
    public Map<String, Object> doGetTransBalance(){
        Map<String, Object> json = new HashMap<>(3);
        try {
            SelectCondition condition = new SelectCondition();
            condition.setSellerId(sellerId);
            condition.setUserId(userId);
            Map<String, Long> getTransBalance = stationService.getTransBalance(condition);
            if(getTransBalance!=null&&getTransBalance.get("actualBalanceAll")!=null
                    &&getTransBalance.get("actualBalanceAll")!=0) {
                String actualBalanceAll=String.valueOf(getTransBalance.get("actualBalanceAll"));
                String powerBalanceAll=String.valueOf(getTransBalance.get("powerBalanceAll"));
                String otherActualBalance=String.valueOf(getTransBalance.get("otherActualBalance"));
                String GongJiaoPowerBalance=String.valueOf(getTransBalance.get("GongJiaoPowerBalance"));
                String otherPowerBalance=String.valueOf(getTransBalance.get("otherPowerBalance"));

                json.put("chargingPercent", AmountUtils.mul(AmountUtils.div(powerBalanceAll, actualBalanceAll, 4), 100.0, 2));//当日充电百分数
                json.put("numQitaPercent",AmountUtils.mul(AmountUtils.div(AmountUtils.sub(actualBalanceAll,powerBalanceAll), actualBalanceAll, 4), 100.0, 2));//当日其他百分数
                json.put("numGongJiaoPercent", AmountUtils.mul(AmountUtils.div(GongJiaoPowerBalance, powerBalanceAll, 4), 100.0, 2));//当日公交收入百分数
                json.put("otherPowerPercent",AmountUtils.mul(AmountUtils.div(otherPowerBalance, powerBalanceAll, 4), 100.0, 2));//当日非公交充电收入百分数
            }else {
                json.put("chargingPercent", 0);//当日充电百分数
                json.put("numQitaPercent", 0);//当日其他百分数
                json.put("numGongJiaoPercent", 0);//当日公交收入百分数
                json.put("otherPowerPercent", 0);//当日非公交充电收入百分数
            }

            json.put("getTransBalance", getTransBalance);
            json.put("stateCode", 0);
        } catch (Exception e) {
            json.put("getTransBalance", "");
            json.put("stateCode", 1);
        }

        return json;
    }

    /**
     * @describe
     * 获取当日非公交充电排行
     */
    @RequestMapping(value = "/statistic/ScreenNewInCome/GetTransIncomeRank.json",method = RequestMethod.POST)
    public Map getStationInServiceMapData2(){
        Map<String, Object> json = new HashMap<>(2);
        try {
            List<IncomeRankDto> getTransIncomeRank = stationService.getTransIncomeRank(sellerId, userId);
            json.put("getTransIncomeRank", getTransIncomeRank);
            json.put("stateCode", 0);
        } catch (Exception e) {
            json.put("getTransIncomeRank", "");
            json.put("stateCode", 1);
        }

        return json;
    }

    /**
     * @describe
     *
     */
    @RequestMapping(value = "/statistic/ScreenNewInCome/IncomeWayData.json",method = RequestMethod.POST)
    public Map<String, Object> getStationInServiceMapData3(){
        Map<String, Object> json = new ConcurrentHashMap<String, Object>();
        try {
            SelectCondition condition=new SelectCondition();
            condition.setSellerId(sellerId);
            condition.setStartDate(new Date());
            Map<String,Long> incomeWay=stationService.getChargeBalanceSum(condition);
            json.put("app", TypeRotationUtil.stringTurnYuan(incomeWay.get("app")));
            json.put("card", TypeRotationUtil.stringTurnYuan(incomeWay.get("card")));
            json.put("vin", TypeRotationUtil.stringTurnYuan(incomeWay.get("vin")));
            json.put("wx", TypeRotationUtil.stringTurnYuan(incomeWay.get("wx")));
        } catch (Exception e) {
            json.put("getTransBalance", "");
            json.put("stateCode", 1);
        }
        return json;
    }

    /**
     * @describe
     * 非公交总收入趋势
     */
    @RequestMapping(value = "/statistic/ScreenNewInCome/GetTransIncomeIndicator.json",method = RequestMethod.POST)
    public Map getStationInServiceMapData5(){
        Map<String, Object> json = new HashMap<>(2);
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -30);
        try {
            SelectCondition condition = new SelectCondition();
            condition.setSellerId(sellerId);
            condition.setUserId(userId);
            condition.setStartDate(now.getTime());
            condition.setEndDate(new Date());
            Map<String, Object> getTransIncomeIndicator = stationService
                    .getTransIncomeIndicator(condition);
            List timeList=(List) getTransIncomeIndicator.get("timeList");
            List noPublicBusIncomeList=(List) getTransIncomeIndicator.get("noPublicBusIncomeList");
            List personIncomeList=(List) getTransIncomeIndicator.get("personIncomeList");
            Map<String, Object> dataMap = null;
            for (int i = 0; i < timeList.size(); i++){
                dataMap = new HashMap<String, Object>();
                dataMap.put("data", timeList.get(i));
                dataMap.put("个人", TypeRotationUtil.stringTurnYuan((Long)personIncomeList.get(i)));
                dataMap.put("车队", TypeRotationUtil.stringTurnYuan((Long)noPublicBusIncomeList.get(i)));
                data.add(dataMap);
            }
            json.put("getTransIncomeIndicator", data);
            json.put("stateCode", 0);
        } catch (Exception e) {
            json.put("getTransIncomeIndicator", "");
            json.put("stateCode", 1);
        }

        return json;
    }


}
