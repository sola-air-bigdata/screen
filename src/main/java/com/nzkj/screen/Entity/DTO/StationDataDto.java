package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 站点基础数据(站点总览)
 * @author hyc
 * @date 2019-5-15
 *
 */
public class StationDataDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//站点总数
	private int statioTotal;
	
	//建设中的站点数
	private int inConstructionCount;
	
	//待建站点数
	private int toBeBulitCount;
	
	//桩数
	private int pileCount;
	
	//枪数
	private int gunCount;
	
	//对外站点数
	private int outsideCount;
	
	//对内站点数
	private int insideCount;
	
	//对外占比
	private double outsideRate;
	
	//对内占比
	private double insideRate;

	public int getStatioTotal() {
		return statioTotal;
	}

	public void setStatioTotal(int statioTotal) {
		this.statioTotal = statioTotal;
	}

	public int getInConstructionCount() {
		return inConstructionCount;
	}

	public void setInConstructionCount(int inConstructionCount) {
		this.inConstructionCount = inConstructionCount;
	}

	public int getToBeBulitCount() {
		return toBeBulitCount;
	}

	public void setToBeBulitCount(int toBeBulitCount) {
		this.toBeBulitCount = toBeBulitCount;
	}

	public int getPileCount() {
		return pileCount;
	}

	public void setPileCount(int pileCount) {
		this.pileCount = pileCount;
	}

	public int getGunCount() {
		return gunCount;
	}

	public void setGunCount(int gunCount) {
		this.gunCount = gunCount;
	}

	public int getOutsideCount() {
		return outsideCount;
	}

	public void setOutsideCount(int outsideCount) {
		this.outsideCount = outsideCount;
	}

	public int getInsideCount() {
		return insideCount;
	}

	public void setInsideCount(int insideCount) {
		this.insideCount = insideCount;
	}

	public double getOutsideRate() {
		return outsideRate;
	}

	public void setOutsideRate(double outsideRate) {
		this.outsideRate = outsideRate;
	}

	public double getInsideRate() {
		return insideRate;
	}

	public void setInsideRate(double insideRate) {
		this.insideRate = insideRate;
	}

}
