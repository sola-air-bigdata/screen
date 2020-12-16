package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:12
 * Describe:
 */
@TableName("station_show_month_grouth_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowMonthGrouth {

    private static final long serialVersionUID = 1L;

    //	'站点id'
    private BigInteger	lStationId;
    //	'商家id'
    private int	lSellerId;
    //	'月份'
    private String	dMonthTime;
    //	'月个人会员增长数'
    private BigInteger monthGrouthPersonalNum;
}
