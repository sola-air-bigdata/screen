package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/6/30 14:49
 * Describe:
 */
@Entity(name = "t_all_month_table")
public class AllMonthData {

    @Id
    @GeneratedValue
    //商家id
    private long l_seller_id;

    //月充电总收入
    private BigInteger month_i_charging_actual_balance;

    //月其他总收入
    private BigInteger month_i_service_actual_balance;

    //时间
    private String d_month_time;

    //月个人充电收入
    private BigInteger month_personal_i_charging_actual_balance;

    //月个人其他收入
    private BigInteger month_personal_i_service_actual_balance;

    //月车队用户增长数
    private BigInteger month_growth_team_user;

    //月个人用户增长数
    private BigInteger month_growth_personal_user;

    //月故障桩数
    private BigInteger month_fault_pile;

}
