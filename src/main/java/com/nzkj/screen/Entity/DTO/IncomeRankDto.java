package com.nzkj.screen.Entity.DTO;


import com.nzkj.screen.Utils.ValUtil;

import java.io.Serializable;

/**
 * 收入排行-统计数据
 * 
 * @author lwz
 */
public class IncomeRankDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;
	
	// 实际收入-分
	private Long actualBalance;

	/**
	 * 收入
	 */
	private String incomeBalance;

	/**
	 * 上次收入
	 */
	private String lastIncomeBalance;
	
	//======车队单车充电排名=====//
	// 充电总量-瓦
	private Long sumPower;

	// 充电订单总数-次
	private Integer billCount;

	// 上次充电订单总数-次
	private Integer lastBillCount;

	// 车队车辆总数-辆
	private Integer carCount;
	
	// 上次车队车辆总数-辆
	private Integer carCountLast;
	
	// 平均充电量（瓦/次）= 车对月充电总量/车队月充电订单总数
	private Long avePower;
	
	// 充电频率（次/月）= 车队月充电订单总数/车队车辆总数——频率保留1位小数
	private Double frePower;
	
	//上次 充电频率（次/月）= 上次车队月充电订单总数/上次车队车辆总数——频率保留1位小数
	private Double frePowerLast;

	//======车队平均月充电消费排名=====//
	// 会员名称
	private String memberName;
	// 车牌号
	private String plateno;
	// 实际消费-分
	private Long actualConsume;
	// 充电次数-次
	private Integer chargeCount;
	// 会员id
	private Long memberId;
	// 会员头像
	private String memberIcon;
	//  月车队总充电消费金额
	private Long totalConsumebalance;
    // 累计消费金额
	private Long grandConsumebalance;

	public void setTotalConsumebalance(Long totalConsumebalance) {
		this.totalConsumebalance = totalConsumebalance;
	}

	public void setGrandConsumebalance(Long grandConsumebalance) {
		this.grandConsumebalance = grandConsumebalance;
	}

	public Long getTotalConsumebalance() {
		return totalConsumebalance;
	}

	public Long getGrandConsumebalance() {
		return grandConsumebalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIncomeBalance() {
		return incomeBalance;
	}

	public void setIncomeBalance(String incomeBalance) {
		this.incomeBalance = incomeBalance;
	}

	public String getLastIncomeBalance() {
		return lastIncomeBalance;
	}

	public void setLastIncomeBalance(String lastIncomeBalance) {
		this.lastIncomeBalance = lastIncomeBalance;
	}

	public Long getActualBalance() {
		return actualBalance;
	}

	public void setActualBalance(Long actualBalance) {
		this.actualBalance = actualBalance;
	}

	public Long getSumPower() {
		return sumPower;
	}

	public void setSumPower(Long sumPower) {
		this.sumPower = sumPower;
	}

	public Integer getBillCount() {
		return billCount;
	}

	public void setBillCount(Integer billCount) {
		this.billCount = billCount;
	}

	public Integer getLastBillCount() {
		return lastBillCount;
	}

	public void setLastBillCount(Integer lastBillCount) {
		this.lastBillCount = lastBillCount;
	}

	public Integer getCarCount() {
		return carCount;
	}

	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}

	public Integer getCarCountLast() {
		return carCountLast;
	}

	public void setCarCountLast(Integer carCountLast) {
		this.carCountLast = carCountLast;
	}

	public Long getAvePower() {
		return avePower;
	}

	public void setAvePower(Long avePower) {
		this.avePower = avePower;
	}

	public void setAvePower() {
		if (this.sumPower == null || this.billCount == null || this.billCount == 0) {
			this.avePower = 0L;
		} else {
			this.avePower = this.sumPower / this.billCount;
		}
	}

	public Double getFrePower() {
		return frePower;
	}

	public void setFrePower(Double frePower) {
		this.frePower = frePower;
	}

	public void setFrePower() {
		if (this.billCount == null || this.carCount == null || this.carCount == 0) {
			this.frePower = 0.0;
		} else {
			this.frePower = ValUtil.div(this.billCount.doubleValue(), this.carCount.doubleValue(), 1);
		}
	}

	public Double getFrePowerLast() {
		return frePowerLast;
	}

	public void setFrePowerLast(Double frePowerLast) {
		this.frePowerLast = frePowerLast;
	}

	public void setFrePowerLast() {
		if (this.lastBillCount == null || this.carCountLast == null || this.carCountLast == 0) {
			this.frePowerLast = 0.0;
		} else {
			this.frePowerLast = ValUtil.div(this.lastBillCount.doubleValue(), this.carCountLast.doubleValue(), 1);
		}
	}

	public Long getActualConsume() {
		return actualConsume;
	}

	public void setActualConsume(Long actualConsume) {
		this.actualConsume = actualConsume;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}

	public Integer getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(Integer chargeCount) {
		this.chargeCount = chargeCount;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberIcon() {
		return memberIcon;
	}

	public void setMemberIcon(String memberIcon) {
		this.memberIcon = memberIcon;
	}
	
}
