package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 桩的状态
 * 
 * @author Administrator
 *
 */
public enum GunStateEnum {
	//空闲（插枪）=> 充电准备（开始充电）=> 充电锁定（桩真正开始充电）=> 充电中（停止充电）=> 充电完成（拔枪）=> 空闲
	FREE(1, "空闲"), CHARGEPREPARE(2, "充电准备"), CHARGING(3, "充电中"), 
	CHARGEFINISH(4, "充电完成"), OFFLINE(5,"离线"),CHARGELOCK(6,"充电锁定"),Problem(7,"故障"),BESPEAK(8,"预约");
	private Integer code;
	private String cnName;
	
	private GunStateEnum(Integer code, String cnName) {
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
		for (GunStateEnum c : GunStateEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

}
