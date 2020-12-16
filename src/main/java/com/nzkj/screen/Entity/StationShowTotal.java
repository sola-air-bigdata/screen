package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:20
 * Describe:
 */
@TableName("station_show_total_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowTotal {
    private static final long serialVersionUID = 1L;

    //站点
    private Long	lStationId;
    //商家
    private Long	lSellerId;
    //站点总桩数
    private BigInteger	totalStationPileNum;
    //站点总枪数
    private BigInteger	totalStationGunNum;
    //站点总服务次数
    private BigInteger	totalStationServiceCount;
    //站点服务个人用户总数
    private BigInteger	totalStationServiceUseCount;
    //站点服务车队用户总数
    private BigInteger	totalStationServiceTeamCount;
    //站点车队总充电量
    private BigInteger	totalStationTeamPowerCount;
    //站点车队总订单数
    private BigInteger	totalStationTeamBillCount;
    //站点车队订单总金额
    private BigInteger	totalStationTeamMenoyCount;
    //站点服务车队车辆总数
    private BigInteger	totalStationServiceTeamCars;
    //站点服务车队充电总时长
    private BigInteger	totalStationServiceTeamTime;
    //站点服务个人银卡用户总数
    private BigInteger	totalStationServiceSilverUseCount;
    //站点服务个人金卡用户总数
    private BigInteger	totalStationServiceGoldUseCount;
    //站点服务个人白金卡用户总数
    private BigInteger	totalStationServicePlatinumUseCount;
    //站点服务个人钻石卡用户总数
    private BigInteger	totalStationServiceDiamondUseCount;
    //站点服务个人黑金卡用户总数
    private BigInteger	totalStationServiceBlackgoldUseCount;
    //站点总充电量
    private BigInteger	totalStationPowerCount;
    //站点总收入
    private BigInteger	totalStationMoneyCount;
    //站点总功率
    private BigInteger	totalStationIPowerCount;
    //站点变压器总功率
    private BigInteger	totalStationChangePowerCount;
    //站点尖总充电量
    private BigInteger	totalStationJianPowerCount;
    //站点峰总充电量
    private BigInteger	totalStationFengPowerCount;
    //站点平总充电量
    private BigInteger	totalStationPingPowerCount;
    //站点谷总充电量
    private BigInteger totalStationGuPowerCount;
}
