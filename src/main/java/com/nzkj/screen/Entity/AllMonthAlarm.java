package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:01
 * Describe:
 */
@TableName("t_all_month_alarm_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AllMonthAlarm {

    private static final long serialVersionUID = 1L;
    //	'商家id'
    private int	lSellerId;
    //	'桩号'
    private int	lPileId;
    //	'日期'
    private String	alarmDate;
    //	'警告次数'
    private BigInteger alarmCount;
}
