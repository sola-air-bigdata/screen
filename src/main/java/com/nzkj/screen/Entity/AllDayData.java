package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 13:08
 * Describe:
 */
@TableName("t_all_day_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllDayData {
    private static final long serialVersionUID = 1L;

    //商家id
    private long lSellerId;

    //车队用户活跃度
    private BigInteger dayTeamActiveCount;

    //个人用户活跃度
    private BigInteger dayPersonalActiveCount;

    //日车队用户增长数
    private BigInteger dayGrowthTeamUser;

    //日个人用户增长数
    private BigInteger dayGrowthPersonalUser;

    //日期
    private String dDayTime;

    //当日服务车队用户总数
    private BigInteger dayTeamServiceUser;


    //当日服务个人用户总数
    private BigInteger dayPersonalServiceUser;


    //当日服务app用户总数
    private BigInteger dayServiceAppUser;


    //当日服务微信用户总数
    private BigInteger dayServiceWechatUser;

    //当日累计运营收入
    private BigInteger dayIActualBalance;


    //当日充电收入
    private BigInteger dayIPowerBalance;


    //当日非公交累计充电收入
    private BigInteger dayIPowerBalanceNteam;


    //当日非公交累计运营收入
    private BigInteger dayIActualBalanceBalanceNteam;

    //当日微信累计运营收入
    private BigInteger dayIActualBalanceWechat;


    //当日app累计运营收入
    private BigInteger dayIActualBalanceApp;


    //当日card累计运营收入
    private BigInteger dayIActualBalanceCard;


    //当日vin累计运营收入
    private BigInteger dayIActualBalanceVin;


}
