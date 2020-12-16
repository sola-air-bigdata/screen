package com.nzkj.screen.Entity.DTO;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/16 9:18
 * Describe:
 */
public class StationShowTotalDTO {

    //	'站点id'
    private BigInteger lStationId;

    //站点总服务次数
    private BigInteger	totalStationServiceCount;

    //站点总枪数
    private BigInteger	totalStationGunNum;

    //	'月订单总数'
    private BigInteger	monthPileNumCount;

    public BigInteger getlStationId() {
        return lStationId;
    }

    public void setlStationId(BigInteger lStationId) {
        this.lStationId = lStationId;
    }

    public BigInteger getTotalStationServiceCount() {
        return totalStationServiceCount;
    }

    public void setTotalStationServiceCount(BigInteger totalStationServiceCount) {
        this.totalStationServiceCount = totalStationServiceCount;
    }

    public BigInteger getTotalStationGunNum() {
        return totalStationGunNum;
    }

    public void setTotalStationGunNum(BigInteger totalStationGunNum) {
        this.totalStationGunNum = totalStationGunNum;
    }

    public BigInteger getMonthPileNumCount() {
        return monthPileNumCount;
    }

    public void setMonthPileNumCount(BigInteger monthPileNumCount) {
        this.monthPileNumCount = monthPileNumCount;
    }
}
