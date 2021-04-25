package com.nzkj.screen.Entity.DTO;


import com.nzkj.screen.Utils.ValUtil;

import java.io.Serializable;

/**
 * 站点车队会员充电信息
 * 
 * @author lwz
 * @date 2019-05-17
 */
public class MemberRechargeInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	// ----------累计----------//
	// 服务人数-服务车队用户总数（个）
	private Integer serviceMember = 0;
	// 服务车队-服务车队总数量（辆）
	private Integer serviceTeamCar = 0;
	// 充电总量(瓦)
	private Long power = 0L;
	// 充电总金额(分)
	private Long balance = 0L;
	// 充电总时长(s)
	private Long rechargeTime = 0L;
	// 充电订单总数
	private Integer billCount = 0;
	// 车队单车充电量（瓦/次）= 充电总量/充电订单总数
	private Long busPower = 0L;
	// 车队单车消费（分/次）= 充电总金额/充电订单总数
	private Long busBalance = 0L;
	// 车队单车充电时长（s/辆）= 充电总时长/充电订单总数
	private Long busRechargeTime = 0L;
	// ----------当日----------//
	// 会员总数(个)
	private Integer memberCount = 0;
	// 车辆总数(辆)
	private Integer carCount = 0;
	// 车队日充电量（瓦）= 充电总量/会员总数
	private Long memberPower = 0L;
	// 车队均充电频率 = 充电订单总数/充电车辆总数
	private Double busRechargeCount = 0d;
	// 车队日充电次数（次）= 充电订单总数/会员总数
	private Double memberRechargeCount = 0d;

	public Integer getServiceMember() {
		return serviceMember;
	}

	public void setServiceMember(Integer serviceMember) {
		this.serviceMember = serviceMember;
	}

	public Integer getServiceTeamCar() {
		return serviceTeamCar;
	}

	public void setServiceTeamCar(Integer serviceTeamCar) {
		this.serviceTeamCar = serviceTeamCar;
	}

	public Long getPower() {
		return power;
	}

	public void setPower(Long power) {
		this.power = power;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Long rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public Integer getBillCount() {
		return billCount;
	}

	public void setBillCount(Integer billCount) {
		this.billCount = billCount;
	}

	public Long getBusPower() {
		return busPower;
	}

	public void setBusPower(Long busPower) {
		this.busPower = busPower;
	}
	
	/**
	 * 车队单车充电量（瓦/次）= 充电总量/充电订单总数
	 */
	public void setBusPower() {
		if (this.billCount == null || this.power == null || this.billCount == 0) {
			this.busPower = 0L;
		} else {
			this.busPower = this.power / this.billCount;
		}
	}

	public Long getBusBalance() {
		return busBalance;
	}

	public void setBusBalance(Long busBalance) {
		this.busBalance = busBalance;
	}
	
	/**
	 * 车队单车消费（分/次）= 充电总金额/充电订单总数
	 */
	public void setBusBalance() {
		if (this.billCount == null || this.balance == null || this.billCount == 0) {
			this.busBalance = 0L;
		} else {
			this.busBalance = this.balance / this.billCount;
		}
	}

	public Long getBusRechargeTime() {
		return busRechargeTime;
	}

	public void setBusRechargeTime(Long busRechargeTime) {
		this.busRechargeTime = busRechargeTime;
	}
	
	/**
	 * 车队单车充电时长（s/辆）= 充电总时长/充电订单总数
	 */
	public void setBusRechargeTime() {
		if (this.billCount == null || this.rechargeTime == null || this.billCount == 0) {
			this.busRechargeTime = 0L;
		} else {
			this.busRechargeTime = this.rechargeTime / this.billCount;
		}
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getCarCount() {
		return carCount;
	}

	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}

	public Long getMemberPower() {
		return memberPower;
	}

	public void setMemberPower(Long memberPower) {
		this.memberPower = memberPower;
	}
	
	/**
	 * 车队日充电量（瓦）= 充电总量/会员总数
	 */
	public void setMemberPower() {
		if (this.serviceMember == null || this.power == null || this.serviceMember == 0) {
			this.memberPower = 0L;
		} else {
			this.memberPower = this.power / this.serviceMember;
		}
	}

	public Double getBusRechargeCount() {
		return busRechargeCount;
	}

	public void setBusRechargeCount(Double busRechargeCount) {
		this.busRechargeCount = busRechargeCount;
	}

	/**
	 * 车队均充电频率 = 充电订单总数/充电的车队车辆总数
	 */
	public void setBusRechargeCount() {
		if (this.serviceTeamCar == null || this.billCount == null || this.serviceTeamCar == 0) {
			this.busRechargeCount = 0d;
		} else {
			
			this.busRechargeCount = ValUtil.div(this.billCount.doubleValue(), this.serviceTeamCar.doubleValue(), 2);
		}
	}

	public Double getMemberRechargeCount() {
		return memberRechargeCount;
	}

	public void setMemberRechargeCount(Double memberRechargeCount) {
		this.memberRechargeCount = memberRechargeCount;
	}
	
	/**
	 * 车队日充电次数（次）= 充电订单总数/会员总数
	 */
	public void setMemberRechargeCount() {
		if (this.serviceMember == null || this.billCount == null || this.serviceMember == 0) {
			this.memberRechargeCount = 0d;
		} else {
			this.memberRechargeCount = ValUtil.div(this.billCount.doubleValue(), this.serviceMember.doubleValue(), 4);
		}
	}

}
