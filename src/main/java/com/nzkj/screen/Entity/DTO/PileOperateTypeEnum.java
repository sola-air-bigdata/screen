package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

public enum PileOperateTypeEnum {
	ZiYing(1,"自营"),JiaMeng(2,"加盟"),DuLiYunYing(3,"独立运营"),HuLianHuTong(4,"互联互通");
	
	private String cnName;
	private Integer coetx;

	PileOperateTypeEnum(Integer coetx, String cnName) {
		this.coetx = coetx;
		this.cnName = cnName;
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
	 * 
	 * @return
	 */
	public final static Map<String, String> getSelect() {
		Map<String, String> maps = new HashMap<String, String>();
		for (PileOperateTypeEnum c : PileOperateTypeEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static PileOperateTypeEnum getEnumByCode(int code){
		if(code == 1){
			return PileOperateTypeEnum.ZiYing;
		}else if(code == 2){
			return PileOperateTypeEnum.JiaMeng;
		}else if(code == 3){
			return PileOperateTypeEnum.DuLiYunYing;
		}else if(code == 4){
			return PileOperateTypeEnum.HuLianHuTong;
		}
		return null;
	}

}