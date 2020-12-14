package com.nzkj.screen.Entity.DTO;
/**
 * 电池充电模式
 * @author 007idle
 * @date 2018年5月28日
 * @descrption
 */
public enum BatteryChargeModelEnum {
	CONSTANT_VOLTAGE(1,"恒压"),CONSTANT_CURRENT(2,"恒流"),UNKNOWN(99,"未知");
	private Integer code;
	private String description;
	private BatteryChargeModelEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
