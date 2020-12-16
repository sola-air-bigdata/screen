package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:06
 * Describe:
 */
@TableName("station_show_member_balance_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowMemberBalance {
    private static final long serialVersionUID = 1L;

    //	'会员id'
    private BigInteger lMemberId;
    //	'站点id'
    private BigInteger	lStationId;
    //	'累计消费金额'
    private BigInteger totalBalanceCount;
}
