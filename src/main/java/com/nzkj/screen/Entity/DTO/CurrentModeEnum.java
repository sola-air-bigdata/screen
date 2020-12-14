
package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 桩的电流类型模式
 * 
 * @author Administrator
 *
 */
public enum CurrentModeEnum {	
	AC(1,"交流"),DC(2,"直流");
	private Integer code;
	private String cnName;

	private CurrentModeEnum(Integer code, String cnName) {
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
		for (CurrentModeEnum c : CurrentModeEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

	public final static CurrentModeEnum getEnumByCode(int code){
		if(code == 1){
			return CurrentModeEnum.AC;
		}else if(code == 2){
			return CurrentModeEnum.DC;
		}
		return null;
	}
}
