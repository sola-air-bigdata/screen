package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 收入运营
 * @author hyc
 * @date 2019-5-17
 *
 */
public class IncomeRunDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//累计总收入(分)
	private long totalIncome;
	
	//公交充电收入占比
	private double teamIncomeRate;
	
	//其他车辆充电收入占比
	private double otherIncomeRate;
	
	//电费占比(实收)
	private double powerBalanceRate;
	
	//其他费用占比(实收服务费、预约费、停车费等)
	private double otherBalanceRate;

	public long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(long totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTeamIncomeRate() {
		return teamIncomeRate;
	}

	public void setTeamIncomeRate(double teamIncomeRate) {
		this.teamIncomeRate = teamIncomeRate;
	}

	public double getOtherIncomeRate() {
		return otherIncomeRate;
	}

	public void setOtherIncomeRate(double otherIncomeRate) {
		this.otherIncomeRate = otherIncomeRate;
	}

	public double getPowerBalanceRate() {
		return powerBalanceRate;
	}

	public void setPowerBalanceRate(double powerBalanceRate) {
		this.powerBalanceRate = powerBalanceRate;
	}

	public double getOtherBalanceRate() {
		return otherBalanceRate;
	}

	public void setOtherBalanceRate(double otherBalanceRate) {
		this.otherBalanceRate = otherBalanceRate;
	}

}
