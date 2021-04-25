package com.nzkj.screen.Entity.DTO;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2021/4/8 14:42
 * Describe:
 */
public class TeamRankingDTO {

    private BigInteger memberId;

    private BigInteger actualBalanceCount;

    private BigInteger totalBalanceCount;

    private int carNum;

    public BigInteger getMemberId() {
        return memberId;
    }

    public void setMemberId(BigInteger memberId) {
        this.memberId = memberId;
    }

    public BigInteger getActualBalanceCount() {
        return actualBalanceCount;
    }

    public void setActualBalanceCount(BigInteger actualBalanceCount) {
        this.actualBalanceCount = actualBalanceCount;
    }

    public BigInteger getTotalBalanceCount() {
        return totalBalanceCount;
    }

    public void setTotalBalanceCount(BigInteger totalBalanceCount) {
        this.totalBalanceCount = totalBalanceCount;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }
}
