package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 11:13
 * Describe:
 */
@Entity(name = "station_show_month_table")
public class StationShowMonth {

    @Id
    //	'站点id'
    private BigInteger	l_station_id;
    //	'商家id'
    int	l_seller_id;
    //	'月份'
    private String	d_month_time;
    //	'月总充电量'
    private BigInteger	month_i_power_count;
    //	'月充电总收入'
    private BigInteger	month_i_power_balance_count;
    //	'月订单总数'
    private BigInteger	month_pile_num_count;
    //	'月个人会员订单总数'
    private BigInteger	month_pile_personal_num_count;
    //	'月个人会员充电会员总数'
    private BigInteger	month_charge_personal_num_count;
    //	'月个人会员预约成功总数'
    private BigInteger	month_personal_booked_num;
    //	'月个人会员参与活动总数'
    private BigInteger	month_personal_visit_num;
    //	'月个人会员增长数'
    private BigInteger	month_grouth_personal_num;
    //	'月个人会员0点充电总数'
    private BigInteger	month_hour_zero_charge_count;
    //	'月个人会员1点充电总数'
    private BigInteger	month_hour_one_charge_count;
    //	'月个人会员2点充电总数'
    private BigInteger	month_hour_two_charge_count;
    //	'月个人会员3点充电总数'
    private BigInteger	month_hour_three_charge_count;
    //	'月个人会员4点充电总数'
    private BigInteger	month_hour_four_charge_count;
    //	'月个人会员5点充电总数'
    private BigInteger	month_hour_five_charge_count;
    //	'月个人会员6点充电总数'
    private BigInteger	month_hour_six_charge_count;
    //	'月个人会员7点充电总数'
    private BigInteger	month_hour_seven_charge_count;
    //	'月个人会员8点充电总数'
    private BigInteger	month_hour_eight_charge_count;
    //	'月个人会员9点充电总数'
    private BigInteger	month_hour_nine_charge_count;
    //	'月个人会员10点充电总数'
    private BigInteger	month_hour_ten_charge_count;
    //	'月个人会员11点充电总数'
    private BigInteger month_hour_eleven_charge_count;
    //	'月个人会员12点充电总数'
    private BigInteger	month_hour_twelve_charge_count;
    //	'月个人会员13点充电总数'
    private BigInteger	month_hour_thirteen_charge_count;
    //	'月个人会员14点充电总数'
    private BigInteger	month_hour_fourteen_charge_count;
    //	'月个人会员15点充电总数'
    private BigInteger	month_hour_fifteen_charge_count;
    //	'月个人会员16点充电总数'
    private BigInteger	month_hour_sixteen_charge_count;
    //	'月个人会员17点充电总数'
    private BigInteger	month_hour_seventeen_charge_count;
    //	'月个人会员18点充电总数'
    private BigInteger	month_hour_eighteen_charge_count;
    //	'月个人会员19点充电总数'
    private BigInteger	month_hour_nineteen_charge_count;
    //	'月个人会员20点充电总数'
    private BigInteger	month_hour_twenty_charge_count;
    //	'月个人会员21点充电总数'
    private BigInteger	month_hour_twenty_one_charge_count;
    //	'月个人会员22点充电总数'
    private BigInteger	month_hour_twenty_two_charge_count;
    //	'月个人会员23点充电总数'
    private BigInteger	month_hour_twenty_three_charge_count;

}
