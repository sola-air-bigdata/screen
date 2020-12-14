package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 10:22
 * Describe:
 */
@Entity(name = "t_all_member_table")
public class AllMember {

    @Id
    //	'会员id'
    private BigInteger	l_member_id;
    //	'会员名称'
    private String	n_member_name;
    //	'车队车辆总数'
    private BigInteger team_car_num;

}
