package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:14
 * Describe:
 */
@TableName("station_show_month_member_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowMonthMember {

    private static final long serialVersionUID = 1L;

    //	'站点id'
    private BigInteger	lStationId;
    //	'商家id'
    private int	lSellerId;
    //	'月份'
    private String	dMonthTime;
    //	'个人会员id'
    private BigInteger	lMemberId;
    //	'个人会员名称'
    private String	nMemberName;
    //	'会员类型'
    private int	sMemberFlag;
    //	'月会员在该站点充电总次数'
    private BigInteger monthMemberChargeCount;
    //	'月车队总充电量'
    private BigInteger	monthTeamPowerCount;
    //	'会员总消费金额'
    private BigInteger	monthTeamBalanceCount;
    //	'月车队总充电消费金额'
    private BigInteger monthTeamPowerBalanceCount;
}
