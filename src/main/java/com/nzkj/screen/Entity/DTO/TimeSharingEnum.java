package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 分时段枚举
 * @author lwz
 */
public enum TimeSharingEnum {

	Year(1,"年"), Month(2,"月"), Day(3,"日"), Hour(4, "时");
	
	private Integer coetx;
	private	String cnName;
	
	private TimeSharingEnum(Integer coetx, String cnName) {
		this.cnName = cnName;
		this.coetx = coetx;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public Integer getCoetx() {
		return coetx;
	}
	public void setCoetx(Integer coetx) {
		this.coetx = coetx;
	}
	/**
	 * 下拉列表
	 */
	public final static Map<String, String> getSelect() {
		Map<String, String> maps = new HashMap<String, String>();
		for (TimeSharingEnum tEnum : TimeSharingEnum.values()) {
			maps.put(tEnum.name(), tEnum.getCnName());
		}
		return maps;
	}
	
	public final static TimeSharingEnum getEnumByCode(int code){
		if(code == 1){
			return TimeSharingEnum.Year;
		}else if(code == 2){
			return TimeSharingEnum.Month;
		}else if(code == 3){
			return TimeSharingEnum.Day;
		}else if(code == 4){
			return TimeSharingEnum.Hour;
		}
		return null;
	}
}
