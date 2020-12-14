package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

public enum ChargingModeEnum3 {
	
	Normal(1,"自动模式"),Money(2,"金额模式"),Time(3,"时间模式"),Electricity(4,"电量模式");
	private Integer code;
	private String cnName;

	private ChargingModeEnum3(Integer code, String cnName) {
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
		for (ChargingModeEnum3 c : ChargingModeEnum3.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

	public final static ChargingModeEnum3 getEnumByCode(int code){
		if(code == 1){
			return ChargingModeEnum3.Normal;
		}else if(code == 2){
			return ChargingModeEnum3.Money;
		}else if(code == 3){
			return ChargingModeEnum3.Time;
		}else if(code == 4){
			return ChargingModeEnum3.Electricity;
		}
		return null;
	}

}
