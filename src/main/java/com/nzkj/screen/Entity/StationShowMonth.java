package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:08
 * Describe:
 */
@TableName("station_show_month_table")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationShowMonth {

    private static final long serialVersionUID = 1L;

    //	'站点id'
    private BigInteger	lStationId;
    //	'商家id'
    private int	lSellerId;
    //	'月份'
    private String	dMonthTime;
    //	'月总充电量'
    private BigInteger	monthIPowerCount;
    //	'月充电总收入'
    private BigInteger	monthIPowerBalanceCount;
    //	'月订单总数'
    private BigInteger	monthPileNumCount;
    //	'月个人会员订单总数'
    private BigInteger	monthPilePersonalNumCount;
    //	'月个人会员充电会员总数'
    private BigInteger	monthChargePersonalNumCount;
    //	'月个人会员预约成功总数'
    private BigInteger	monthPersonalBookedNum;
    //	'月个人会员参与活动总数'
    private BigInteger	monthPersonalVisitNum;
    //	'月个人会员增长数'
    private BigInteger	monthGrouthPersonalNum;
    //	'月个人会员0点充电总数'
    private BigInteger	monthHourZeroChargeCount;
    //	'月个人会员1点充电总数'
    private BigInteger	monthHourOneChargeCount;
    //	'月个人会员2点充电总数'
    private BigInteger	monthHourTwoChargeCount;
    //	'月个人会员3点充电总数'
    private BigInteger	monthHourThreeChargeCount;
    //	'月个人会员4点充电总数'
    private BigInteger	monthHourFourChargeCount;
    //	'月个人会员5点充电总数'
    private BigInteger	monthHourFiveChargeCount;
    //	'月个人会员6点充电总数'
    private BigInteger	monthHourSixChargeCount;
    //	'月个人会员7点充电总数'
    private BigInteger	monthHourSevenChargeCount;
    //	'月个人会员8点充电总数'
    private BigInteger	monthHourEightChargeCount;
    //	'月个人会员9点充电总数'
    private BigInteger	monthHourNineChargeCount;
    //	'月个人会员10点充电总数'
    private BigInteger	monthHourTenChargeCount;
    //	'月个人会员11点充电总数'
    private BigInteger monthHourElevenChargeCount;
    //	'月个人会员12点充电总数'
    private BigInteger	monthHourTwelveChargeCount;
    //	'月个人会员13点充电总数'
    private BigInteger	monthHourThirteenChargeCount;
    //	'月个人会员14点充电总数'
    private BigInteger	monthHourFourteenChargeCount;
    //	'月个人会员15点充电总数'
    private BigInteger monthHourFifteenChargeCount;
    //	'月个人会员16点充电总数'
    private BigInteger	monthHourSixteenChargeCount;
    //	'月个人会员17点充电总数'
    private BigInteger	monthHourSeventeenChargeCount;
    //	'月个人会员18点充电总数'
    private BigInteger	monthHourEighteenChargeCount;
    //	'月个人会员19点充电总数'
    private BigInteger	monthHourNineteenChargeCount;
    //	'月个人会员20点充电总数'
    private BigInteger	monthHourTwentyChargeCount;
    //	'月个人会员21点充电总数'
    private BigInteger	monthHourTwentyOneChargeCount;
    //	'月个人会员22点充电总数'
    private BigInteger	monthHourTwentyTwoChargeCount;
    //	'月个人会员23点充电总数'
    private BigInteger	monthHourTwentyThreeChargeCount;
}
