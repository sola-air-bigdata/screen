package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 国家标准
 * @author zhangtian
 */
public enum NationalStandardEnum {
	N2011(1, "app消息"),N2015(2, "短信"),QiTa(3,"其他");
	private Integer code;
	private String cnName;

	private NationalStandardEnum(Integer code, String cnName) {
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
		for (NationalStandardEnum c : NationalStandardEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static NationalStandardEnum getEnumByCode(int code){
		if(code == 1){
			return NationalStandardEnum.N2011;
		}else if(code == 2){
			return NationalStandardEnum.N2015;
		}else if(code == 3){
			return NationalStandardEnum.QiTa;
		}
		return null;
	}
}
