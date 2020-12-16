package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 14:31
 * Describe:
 */
@TableName("t_all_day_member_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllDayMember {
    private static final long serialVersionUID = 1L;

    //	'商家id'
    private int	lSellerId;
    //	'日期'
    private String	dDayTime;
    //	'个人会员id'
    private BigInteger lMemberId;
    //	'个人会员名称'
    private String	nMemberName;
    //	'日个人会员充电总次数'
    private BigInteger	dayPersonalChargeCount;
    //	'日个人会员总消费金额'
    private BigInteger	dayPersonalMConsumeBalanceCount;
    //	'日个人会员充电消费金额'
    private BigInteger	dayPersonalIPowerBalanceCount;
    //	'日非公交会员充电消费金额（排名用）
    private BigInteger	dayNbusIActualBalanceCountRank;

}
