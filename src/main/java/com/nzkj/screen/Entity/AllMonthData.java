package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:03
 * Describe:
 */
@TableName("t_all_month_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllMonthData {

    private static final long serialVersionUID = 1L;

    //商家id
    private long lSellerId;

    //月车队累计充电量
    private BigInteger monthTeamChargingPower;

    //月个人累计充电量
    private BigInteger monthPersonalChargingPower;

    //月充电总收入
    private BigInteger monthIChargingActualBalance;

    //月其他总收入
    private BigInteger monthIServiceActualBalance;

    //时间
    private String dMonthTime;

    //月个人充电收入
    private BigInteger monthPersonalIChargingActualBalance;

    //月个人其他收入
    private BigInteger monthPersonalIServiceActualBalance;

    //月车队用户增长数
    private BigInteger monthGrowthTeamUser;

    //月个人用户增长数
    private BigInteger monthGrowthPersonalUser;

    //月故障桩数
    private BigInteger monthFaultPile;
}
