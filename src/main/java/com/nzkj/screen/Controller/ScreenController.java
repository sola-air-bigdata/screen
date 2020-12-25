package com.nzkj.screen.Controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nzkj.screen.Entity.DTO.GunMonitorDto;
import com.nzkj.screen.Entity.DTO.GunStateEnum;
import com.nzkj.screen.Entity.DTO.StationDto;
import com.nzkj.screen.Entity.Gun;
import com.nzkj.screen.Service.ScreenRedisService;
import com.nzkj.screen.Service.impl.ScreenRedisServiceImpl;
import com.nzkj.screen.Utils.StringUtils;
import com.xiaoleilu.hutool.util.CollectionUtil;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Liu yang
 * @Date: 2020/7/7 10:10
 * Describe:
 */
@RestController
public class ScreenController {

    @Autowired
    private RedisTemplate redisTemplate;

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

    @Autowired
    private ScreenRedisService screenRedisService;

//    @Autowired
//    JdbcTemplate jdbcTemplate1;
//
//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public Gun test(){
//
//        Gun gun = jdbcTemplate1.queryForObject("select * from t_gun where id = 455", Gun.class);
//        return gun;
//    }


    /*
     * 根据站点id获取总服务次数，总电量，总收入，充电峰谷,今日总服务次数，总电量，总收入统计
     *
     * @param stationID 站点id month 时间
     * @return map key : servicesNums(总服务次数) totalPower(总电量) totalRevenue(总收入)
     * jianCharge(尖电量) fengCharge(峰电量) pingCharge(平电量) guCharge(谷电量)
     * todayServicesNums(今日总服务次数) todayTotalPower(总电量) todayTotalRevenue(总收入统计)
     *
     * @return map key : monthServicesNums(月总服务次数) monthTotalPower(总电量)
     * monthTotalRevenue(总收入统计)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getStationSumInfoByID.json",method = RequestMethod.POST)
    public Map<String, Object> doGetStationSumInfoByID(@Param("stationID") Long stationID, @Param("month") Date date) {
        return screenRedisService.doGetStationSumInfoByID(stationID,date);
    }


    /*
     * 站点总览数据（枪）
     *
     * @url /statistic/gJStationViewAjax/getStationSumViewData.json
     *
     * @Param stationID
     *
     * @return key:gunCount 枪总数 charging 充电中的枪 free 空闲中的枪 chargePrepare 充电准备中的枪
     * chargeFinsh 充电完成中的枪 offLine 离线中的枪 problem 故障中的枪 bespeak 预约中的枪
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getStationSumViewData.json",method = RequestMethod.POST)
    public Map<String, Object> doGetStationSumViewData(@Param("stationID") Long stationID){
        return screenRedisService.doGetStationSumViewData(stationID);
    }

    /*
     * 根据站点id获取这个月每天服务的车辆数
     *
     * @param stationID
     *
     * @url /statistic/gJStationViewAjax/getStationCarServiceTime.json
     *
     * @return map key : carNums(服务的车辆数) date(日期) charingBalance(收入)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getStationCarServiceTime.json",method = RequestMethod.POST)
    public Map<String, Object> doGetStationCarServiceTime(@Param("stationID") Long stationID) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        List<Map<String, Object>> carSumData = screenRedisService.getCarSumData(stationID);
        List<Object> date = new CopyOnWriteArrayList<>();
        List<Object> carNums = new CopyOnWriteArrayList<>();
        List<Object> charingBalance = new CopyOnWriteArrayList<>();
        if (!carSumData.isEmpty()) {
            for (Map<String, Object> map : carSumData) {
                if (map.get("date") != null) {

                    date.add(map.get("date"));
                }
                if (map.get("carNums") != null) {
                    carNums.add(map.get("carNums"));
                }
                if (map.get("charingBalance") != null) {
                    charingBalance.add(map.get("charingBalance"));
                }
            }
            result.put("date", date);
            result.put("carNums", carNums);
            result.put("charingBalance", charingBalance);
        }
        return result;
    }

    /*
     * 枪列表
     *
     * @param stationID
     *
     * @url /statistic/gJStationViewAjax/getGunData.json
     *
     * @return key:pileName桩名称 pileID 桩ID gunJson 枪列表（List） soc soc值 remainTime 剩余时间
     * current 充电电流 pileName 桩名称 gunNo枪号 gunState枪状态 power已冲电量
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getGunData.json",method = RequestMethod.POST)
    public List<Object> doGetGunData(@Param("stationID") Long stationID){
        return screenRedisService.doGetGunData(stationID);
    }


    @RequestMapping(value = "/statistic/gJStationViewAjax/getSelloNoAndAccount.json",method = RequestMethod.POST)
    public Map<String, Object> doGetSelloNoAndAccount() {
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("sellerNo", sellerId);
        result.put("account","");
        return result;
    }

    /*
     * 根据站点id获取相关信息
     *
     * @param stationID
     *
     * @url /statistic/gJStationViewAjax/getStationInfoByID.json
     *
     * @return map key : stationImage(站点图片) stationName(站点名称) stationaddres(站点地址)
     * stationRating(站点评级) stationPile(站点桩数量)
     *  //删除 --> electricityPrice(优惠电价)
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getStationInfoByID.json", method = RequestMethod.GET)
    public Map<String, Object> doGetStationInfoByID(@Param("stationID") Long stationID) {
        return screenRedisService.doGetStationInfoByID(stationID);
    }


    /**
     * 充电站设备信息
     * @param stationID
     * @return
     */
    @RequestMapping(value = "/statistic/gJStationViewAjax/getStationEquipmentInfo.json", method = RequestMethod.GET)
    public Map<String, Object> doGetStationEquipmentInfo(@Param("stationID") Long stationID){
        return screenRedisService.doGetStationEquipmentInfo(stationID);
    }

    /**
     * @describe
     * 用户总览中央地图模块->使用充电服务中的用户数
     */
    @RequestMapping(value = "/statistic/GuangJiaoUsersViewAjax/getStationInServiceMapData.json",method = RequestMethod.POST)
    public JSONArray getStationInServiceMapData(){
        return screenRedisService.getStationInServiceMapData();
    }









}
