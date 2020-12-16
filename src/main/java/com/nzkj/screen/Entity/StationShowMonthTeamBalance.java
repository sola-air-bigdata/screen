package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:18
 * Describe:
 */
@TableName("station_show_month_team_balance_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowMonthTeamBalance {

    private static final long serialVersionUID = 1L;

    //	'站点id'
    private BigInteger lStationId;
    //	'商家id'
    private int	lSellerId;
    //	'月份'
    private String	dMonthTime;
    //	'vin'
    private String	vVin;
    //	'车牌号'
    private String	vPlateno;
    //	'会员id'
    private BigInteger	lMemberId;
    //	'月车队总充电消费金额'
    private BigInteger monthTeamActualBalanceCount;
}
