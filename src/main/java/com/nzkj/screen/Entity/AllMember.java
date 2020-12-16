package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 14:59
 * Describe:
 */
@TableName("t_all_member_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllMember {
    private static final long serialVersionUID = 1L;

    //	'会员id'
    private BigInteger	lMemberId;
    //	'会员名称'
    private String	nMemberName;
    //	'车队车辆总数'
    private BigInteger teamCarNum;
}
