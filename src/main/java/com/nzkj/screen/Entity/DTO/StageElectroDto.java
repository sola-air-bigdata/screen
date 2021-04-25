package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 阶段充电分析
 * 
 * @author lwz
 *
 */
public class StageElectroDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long stationId;
	// 尖充电量（瓦）
	private Long cuspPower = 0L;
	// 峰充电量（瓦）
	private Long peakPower = 0L;
	// 平充电量（瓦）
	private Long flatPower = 0L;
	// 谷充电量（瓦）
	private Long ravinePower = 0L;
	// 总电量（瓦）
	private Long sumPower = 0L;

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getCuspPower() {
		return cuspPower;
	}

	public void setCuspPower(Long cuspPower) {
		this.cuspPower = cuspPower;
	}

	public Long getPeakPower() {
		return peakPower;
	}

	public void setPeakPower(Long peakPower) {
		this.peakPower = peakPower;
	}

	public Long getFlatPower() {
		return flatPower;
	}

	public void setFlatPower(Long flatPower) {
		this.flatPower = flatPower;
	}

	public Long getRavinePower() {
		return ravinePower;
	}

	public void setRavinePower(Long ravinePower) {
		this.ravinePower = ravinePower;
	}

	public Long getSumPower() {
		return sumPower;
	}

	public void setSumPower(Long sumPower) {
		this.sumPower = sumPower;
	}

}
