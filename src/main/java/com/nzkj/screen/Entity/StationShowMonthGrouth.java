package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 10:29
 * Describe:
 */
@Entity(name ="station_show_month_grouth_table")
public class StationShowMonthGrouth {

    @Id
    //	'站点id'
    private BigInteger	l_station_id;
    //	'商家id'
    private int	l_seller_id;
    //	'月份'
    private String	d_month_time;
    //	'月个人会员增长数'
    private BigInteger month_grouth_personal_num;
}
