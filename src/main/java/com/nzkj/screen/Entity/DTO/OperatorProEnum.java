package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

public enum OperatorProEnum {

	GuoYing(1, "国营"), MingYing(2, "民营"), ShiYeDanW(3, "事业单位"), ZhenFJG(4, "政府机构"), QiTa(5, "其他");

	private String cnName;
	private Integer coetx;

	OperatorProEnum(Integer coetx, String cnName) {
		this.coetx = coetx;
		this.cnName = cnName;
	}

	public String getCnName() {
		return cnName;
	}

	public Integer getCoetx() {
		return coetx;
	}

	public void setCoetx(int coetx) {
		this.coetx = coetx;
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
		for (OperatorProEnum c : OperatorProEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static OperatorProEnum getEnumByCode(int code){
		if(code == 1){
			return OperatorProEnum.GuoYing;
		}else if(code == 2){
			return OperatorProEnum.MingYing;
		}else if(code == 3){
			return OperatorProEnum.ShiYeDanW;
		}else if(code == 4){
			return OperatorProEnum.ZhenFJG;
		}else if(code == 5){
			return OperatorProEnum.QiTa;
		}
		return null;
	}

}
