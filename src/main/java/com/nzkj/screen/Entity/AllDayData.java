package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/6/30 16:32
 * Describe:
 */
@Entity(name = "t_all_day_table")
public class AllDayData {

    @Id
    @GeneratedValue
    //商家id
    private long l_seller_id;

    //车队用户活跃度
    private BigInteger day_team_active_count;

    //个人用户活跃度
    private BigInteger day_personal_active_count;

    //日车队用户增长数
    private BigInteger day_growth_team_user;

    //日个人用户增长数
    private BigInteger day_growth_personal_user;

    //日期
    private String d_day_time;

    //当日服务车队用户总数
    private BigInteger day_team_service_user;


    //当日服务个人用户总数
    private BigInteger day_personal_service_user;


    //当日服务app用户总数
    private BigInteger day_service_app_user;


    //当日服务微信用户总数
    private BigInteger day_service_wechat_user;

    //当日累计运营收入
    private BigInteger day_i_actual_balance;


    //当日充电收入
    private BigInteger day_i_power_balance;


    //当日非公交累计充电收入
    private BigInteger day_i_power_balance_nteam;


    //当日非公交累计运营收入
    private BigInteger day_i_actual_balance_balance_nteam;

    //当日微信累计运营收入
    private BigInteger day_i_actual_balance_wechat;


    //当日app累计运营收入
    private BigInteger day_i_actual_balance_app;


    //当日card累计运营收入
    private BigInteger day_i_actual_balance_card;


    //当日vin累计运营收入
    private BigInteger day_i_actual_balance_vin;


}
