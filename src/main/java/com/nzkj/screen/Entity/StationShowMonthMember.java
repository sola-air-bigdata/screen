package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 11:08
 * Describe:
 */
@Entity(name = "station_show_month_member_table")
public class StationShowMonthMember {

    @Id
    //	'站点id'
    private BigInteger	l_station_id;
    //	'商家id'
    private int	l_seller_id;
    //	'月份'
    private String	d_month_time;
    //	'个人会员id'
    private BigInteger	l_member_id;
    //	'个人会员名称'
    private String	n_member_name;
    //	'会员类型'
    int	s_member_flag;
    //	'月会员在该站点充电总次数'
    private BigInteger	month_member_charge_count;
    //	'月车队总充电量'
    private BigInteger	month_team_power_count;
    //	'会员总消费金额'
    private BigInteger	month_team_balance_count;
    //	'月车队总充电消费金额'
    private BigInteger month_team_power_balance_count;
}
