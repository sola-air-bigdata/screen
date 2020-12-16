package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:04
 * Describe:
 */
@TableName("station_show_day_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowDay {
    private static final long serialVersionUID = 1L;

    //	'站点id',
    private Long	lStationId;
    //	'商家id',
    private Long	lSellerId;
    //	'日期',
    private String	dDayTime;
    //	'日站点总充电量',
    private BigInteger	dayStationPowerCount;
    //	'日站点车队会员总充电量',
    private BigInteger	dayStationTeamPowerCount;
    //	'日站点总收入',
    private BigInteger	dayStationBalanceCount;
    //	'日站点订单数量',
    private BigInteger	dayStationPileCount;
    //	'日站点服务公交车数量',
    private BigInteger	dayStationServiceBusCarNum;
    //	'日站点服务社会车数量',
    private BigInteger	dayStationServiceOtherCarNum;
    //	'日站点服务车队车辆数量',
    private BigInteger	dayStationServiceTeamCarNum;
    //	'日站点车队订单数量',
    private BigInteger	dayStationServicePileTeamCount;
    //	'日站点车队会员数',
    private BigInteger	dayStationServiceTeamNumCount;
    //	'日站点车队会员峰总充电量',
    private BigInteger	dayStationFengPowerCount;
    //	'日站点车队会员平总充电量',
    private BigInteger	dayStationPingPowerCount;
    //	'日站点谷车队会员总充电量'
    private BigInteger dayStationGuPowerCount;
}
