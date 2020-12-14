package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

public enum StationFastSlowEnum {

	kuai(1,"快充站"),man(2,"慢充站");
	
	private String cnName;
	private Integer coetx;
	
	StationFastSlowEnum(Integer coetx, String cnName){
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
	 * @return
	 */
	public  final static Map<String,String> getSelect(){
		Map<String,String> maps=new HashMap<String,String>();
		for(StationFastSlowEnum c : StationFastSlowEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	public final static StationFastSlowEnum getEnumByName(String name){
		if("快充站".equals(name)){
			return StationFastSlowEnum.kuai;
		}else if("慢充站".equals(name)){
			
			return StationFastSlowEnum.man;
		}
		return null;
	}
	
}
