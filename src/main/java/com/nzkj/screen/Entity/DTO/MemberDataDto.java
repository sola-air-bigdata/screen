package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 会员数据(用户运营)
 * @author hyc
 * date 2019-5-16
 *
 */
public class MemberDataDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//车队总数
	private int teamCount;
	
	//个人会员总数
	private int personageCount;
	
	//车队占比
	private double teamRate;
	
	//个人占比
	private double personageRate;
	
	//银卡占比
	private double silverRate;
	
	//金卡占比
	private double goldRate;
	
	//白金占比
	private double platinumRate;
	
	//钻石占比
	private double diamondRate;
	
	//黑金占比
	private double blackGoldRate;

	public int getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}

	public int getPersonageCount() {
		return personageCount;
	}

	public void setPersonageCount(int personageCount) {
		this.personageCount = personageCount;
	}

	public double getTeamRate() {
		return teamRate;
	}

	public void setTeamRate(double teamRate) {
		this.teamRate = teamRate;
	}

	public double getPersonageRate() {
		return personageRate;
	}

	public void setPersonageRate(double personageRate) {
		this.personageRate = personageRate;
	}

	public double getSilverRate() {
		return silverRate;
	}

	public void setSilverRate(double silverRate) {
		this.silverRate = silverRate;
	}

	public double getGoldRate() {
		return goldRate;
	}

	public void setGoldRate(double goldRate) {
		this.goldRate = goldRate;
	}

	public double getPlatinumRate() {
		return platinumRate;
	}

	public void setPlatinumRate(double platinumRate) {
		this.platinumRate = platinumRate;
	}

	public double getDiamondRate() {
		return diamondRate;
	}

	public void setDiamondRate(double diamondRate) {
		this.diamondRate = diamondRate;
	}

	public double getBlackGoldRate() {
		return blackGoldRate;
	}

	public void setBlackGoldRate(double blackGoldRate) {
		this.blackGoldRate = blackGoldRate;
	}

}
