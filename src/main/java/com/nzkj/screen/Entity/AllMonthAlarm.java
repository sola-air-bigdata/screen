package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 10:24
 * Describe:
 */
@Entity(name = "t_all_month_alarm_table")
public class AllMonthAlarm {

    @Id
    //	'商家id'
    private int	l_seller_id;
    //	'桩号'
    private int	l_pile_id;
    //	'日期'
    private String	alarm_date;
    //	'警告次数'
    private BigInteger alarm_count;

}
