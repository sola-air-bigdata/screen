package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 9:39
 * Describe:
 */
@Entity(name = "station_show_day_table")
public class StationShowDay {

    @Id
    //	'站点id',
    private Long	l_station_id;
    //	'商家id',
    private Long	l_seller_id;
    //	'日期',
    private String	d_day_time;
    //	'日站点总充电量',
    private BigInteger	day_station_power_count;
    //	'日站点车队会员总充电量',
    private BigInteger	day_station_team_power_count;
    //	'日站点总收入',
    private BigInteger	day_station_balance_count;
    //	'日站点订单数量',
    private BigInteger	day_station_pile_count;
    //	'日站点服务公交车数量',
    private BigInteger	day_station_service_bus_car_num;
    //	'日站点服务社会车数量',
    private BigInteger	day_station_service_other_car_num;
    //	'日站点服务车队车辆数量',
    private BigInteger	day_station_service_team_car_num;
    //	'日站点车队订单数量',
    private BigInteger	day_station_service_pile_team_count;
    //	'日站点车队会员数',
    private BigInteger	day_station_service_team_num_count;
    //	'日站点车队会员峰总充电量',
    private BigInteger	day_station_feng_power_count;
    //	'日站点车队会员平总充电量',
    private BigInteger	day_station_ping_power_count;
    //	'日站点谷车队会员总充电量'
    private BigInteger	day_station_gu_power_count;
}
