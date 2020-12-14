package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 服务会员数据
 * @author hyc
 * @date 2019-5-17
 *
 */
public class ServiceMemberDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//服务的总会员数
	private int memberTotal;
	
	//车队数
	private int teamCount;
	
	//个人会员数
	private int personageCount;
	
	//车队占比
	private double teamRate;
	
	//个人占比
	private double personageRate;
	
	//app数
	private int appCount;
	
	//微信数
	private int wxCount;

	public int getMemberTotal() {
		return memberTotal;
	}

	public void setMemberTotal(int memberTotal) {
		this.memberTotal = memberTotal;
	}

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

	public int getAppCount() {
		return appCount;
	}

	public void setAppCount(int appCount) {
		this.appCount = appCount;
	}

	public int getWxCount() {
		return wxCount;
	}

	public void setWxCount(int wxCount) {
		this.wxCount = wxCount;
	}

}
