package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 车位锁的状态
 * 
 * @author HF
 *
 */
public enum ParkingPlaceLockStateEnum {
	
	//升起状态(闭锁)=> 正常运动状态(下降) => 开锁(车位无车)=>开锁(车位有车)=>开锁(车开走后,车位无车)=>正常运动状态(上升)=>升起状态(闭锁)
	LOCKED(1, "闭锁"),OUTTIME(10, "超时"), MOVEING(2, "正常运动状态"), UNLOCK(3, "开锁无车"), 
	UNLOCKANDCAR(4, "开锁有车"),UNKNOWSTATE(101,"未知故障"),UPFAIL(102,"上升受阻"),
	DOWNFAIL(103,"下降受阻"),LOCKEDBERIGHT(104,"闭锁后右倾斜"),LOCKEDBELEFT(105,"闭锁后左倾斜")
	,OFFLINE(106,"离线"),problem(110,"未知故障");
	private Integer code;
	private String cnName;
	
	private ParkingPlaceLockStateEnum(Integer code, String cnName) {
		this.code = code;
		this.cnName = cnName;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	
	/**
	 * 下拉列表
	 * 
	 * @return
	 */
	public final static Map<String, String> getSelect() {
		Map<String, String> maps = new HashMap<String, String>();
		for (ParkingPlaceLockStateEnum c : ParkingPlaceLockStateEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

}
