package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 11:16
 * Describe:
 */
@Entity(name = "station_show_month_team_balance_table")
public class StationShowMonthTeamBalance {

    @Id
    //	'站点id'
    private BigInteger l_station_id;
    //	'商家id'
    private int	l_seller_id;
    //	'月份'
    private String	d_month_time;
    //	'vin'
    private String	v_vin;
    //	'车牌号'
    private String	v_plateno;
    //	'会员id'
    private BigInteger	l_member_id;
    //	'月车队总充电消费金额'
    private BigInteger	month_team_actual_balance_count;

}
