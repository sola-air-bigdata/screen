package com.nzkj.screen.Entity.DTO;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/16 16:54
 * Describe:
 */
public class StationServiceDTO {

    private String name;
    private Long lStationId;
    private BigInteger totalStationServiceCount;
    private BigInteger monthPileNumCount;
    private BigInteger totalStationGunNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getlStationId() {
        return lStationId;
    }

    public void setlStationId(Long lStationId) {
        this.lStationId = lStationId;
    }

    public BigInteger getTotalStationServiceCount() {
        return totalStationServiceCount;
    }

    public void setTotalStationServiceCount(BigInteger totalStationServiceCount) {
        this.totalStationServiceCount = totalStationServiceCount;
    }

    public BigInteger getMonthPileNumCount() {
        return monthPileNumCount;
    }

    public void setMonthPileNumCount(BigInteger monthPileNumCount) {
        this.monthPileNumCount = monthPileNumCount;
    }

    public BigInteger getTotalStationGunNum() {
        return totalStationGunNum;
    }

    public void setTotalStationGunNum(BigInteger totalStationGunNum) {
        this.totalStationGunNum = totalStationGunNum;
    }
}
