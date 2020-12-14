package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 服务运营
 * @author hyc
 * @date 2019-5-15
 *
 */
public class ServiceRunDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//累计充电次数
	private long allChargeCount;
	
	//车队充电次数
	private long teamChargeCount;
	//其他充电次数
	private long otherChargeCount;
	
	//车队百分比
	private double teamRate;
	
	//其他百分比
	private double otherRate;
	
	//累计充电时长(秒)
	private long allChargeTime;
	
	//车队充电时长(秒)
	private long teamChargeTime;
	
	//其他充电时长(秒)
	private long otherChargeTime;
	
	//车队平均时长(小时/次)
	private double teamMeanTime;
	
	//其他平均时长(小时/次)
	private double otherMeanTime;

	public long getAllChargeCount() {
		return allChargeCount;
	}

	public void setAllChargeCount(long allChargeCount) {
		this.allChargeCount = allChargeCount;
	}

	public long getTeamChargeCount() {
		return teamChargeCount;
	}

	public void setTeamChargeCount(long teamChargeCount) {
		this.teamChargeCount = teamChargeCount;
	}

	public long getOtherChargeCount() {
		return otherChargeCount;
	}

	public void setOtherChargeCount(long otherChargeCount) {
		this.otherChargeCount = otherChargeCount;
	}

	public double getTeamRate() {
		return teamRate;
	}

	public void setTeamRate(double teamRate) {
		this.teamRate = teamRate;
	}

	public double getOtherRate() {
		return otherRate;
	}

	public void setOtherRate(double otherRate) {
		this.otherRate = otherRate;
	}

	public long getAllChargeTime() {
		return allChargeTime;
	}

	public void setAllChargeTime(long allChargeTime) {
		this.allChargeTime = allChargeTime;
	}

	public long getTeamChargeTime() {
		return teamChargeTime;
	}

	public void setTeamChargeTime(long teamChargeTime) {
		this.teamChargeTime = teamChargeTime;
	}

	public long getOtherChargeTime() {
		return otherChargeTime;
	}

	public void setOtherChargeTime(long otherChargeTime) {
		this.otherChargeTime = otherChargeTime;
	}

	public double getTeamMeanTime() {
		return teamMeanTime;
	}

	public void setTeamMeanTime(double teamMeanTime) {
		this.teamMeanTime = teamMeanTime;
	}

	public double getOtherMeanTime() {
		return otherMeanTime;
	}

	public void setOtherMeanTime(double otherMeanTime) {
		this.otherMeanTime = otherMeanTime;
	}

}
