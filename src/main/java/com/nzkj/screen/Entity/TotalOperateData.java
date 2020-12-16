package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:21
 * Describe:
 */
@TableName("t_all_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TotalOperateData {
    private static final long serialVersionUID = 1L;

    //商家id
    private long lSellerId;

    //累计收入运营
    private Long totalIActualBalance;

    //累计车队收入运营
    private Long teamIActualBalance;

    //累计个人收入运营
    private Long personalIActualBalance;

    //累计充电收入运营
    private Long totalIPowerBalance;

    //累计其他收入运营
    private Long totalIServiceBalance;

    //累计服务会员数
    private int totalServiceUserCount;

    //累计服务次数
    private int totalServiceCount;

    //总功率
    private BigInteger totalEquipmentPower;

    //累计总充电量
    private BigInteger totalChargingPower;

    //车队总充电量
    private BigInteger teamChargingPower;

    //车队累计服务次数
    private BigInteger teamServiceCount;

    //个人累计服务次数
    private BigInteger personalServiceCount;

    //累计服务时长
    private BigInteger totalServiceTime;

    //车队累计服务时长
    private BigInteger teamServiceTime;

    //个人累计服务时长
    private BigInteger personalServiceTime;

    //累计微信运营收入
    private BigInteger totalWechatActualBalance;

    //累计app运营收入
    private BigInteger totalAppActualBalance;

    //累计card 运营收入
    private BigInteger totalCardActualBalance;

    //累计vin运营收入
    private BigInteger totalVinActualBalance;

    //充电站数量
    private BigInteger totalStationCount;

    //对外充电站数量
    private BigInteger totalForeignStationCount;

    //对内充电站数量
    private BigInteger totalInternalStationCount;

    //在建充电站数量
    private BigInteger totalConstructionStationCount;

    //桩数量
    private BigInteger totalChargingPileCount;

    //待建充电站数量
    private BigInteger totalBebuiltStationCount;

    //枪数量
    private BigInteger totalChargingGunCount;

    //累计服务黑金卡用户数
    private BigInteger totalServiceBlackgoldUserCount;

    //累计服务钻石卡用户数
    private BigInteger totalServiceDiamondUserCount;

    //累计服务白金卡用户数
    private BigInteger totalServicePlatinumUserCount;

    //累计服务黄金卡用户数
    private BigInteger totalServiceGoldUserCount;

    //累计服务白银卡用户数
    private BigInteger totalServiceSilverUserCount;

    //累计服务个人用户数
    private BigInteger totalServicePersonalUserCount;

    //累计服务车队用户数
    private BigInteger totalServiceTeamUserCount;
}
