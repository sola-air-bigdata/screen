package com.nzkj.screen.Entity.DTO;

/**
 * @Author: Liu yang
 * @Date: 2021/4/8 13:58
 * Describe:
 */
public class TeamChargeRankingDTO {

    private String memberName;

    private double avePower;

    private double IFfrePower;

    private int lastCount;

    private int carCount;

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public double getAvePower() {
        return avePower;
    }

    public void setAvePower(double avePower) {
        this.avePower = avePower;
    }

    public double getIFfrePower() {
        return IFfrePower;
    }

    public void setIFfrePower(double IFfrePower) {
        this.IFfrePower = IFfrePower;
    }

    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }
}
