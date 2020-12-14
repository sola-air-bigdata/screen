package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 10:19
 * Describe:
 */
@Entity(name = "t_all_day_member_table")
public class AllDayMember {

    @Id
    //	'商家id'
    private int	l_seller_id;
    //	'日期'
    private String	d_day_time;
    //	'个人会员id'
    private BigInteger l_member_id;
    //	'个人会员名称'
    private String	n_member_name;
    //	'日个人会员充电总次数'
    private BigInteger	day_personal_charge_count;
    //	'日个人会员总消费金额'
    private BigInteger	day_personal_m_consume_balance_count;
    //	'日个人会员充电消费金额'
    private BigInteger	day_personal_i_power_balance_count;
    //	'日非公交会员充电消费金额（排名用）
    private BigInteger	day_nbus_i_actual_balance_count_rank;
}
