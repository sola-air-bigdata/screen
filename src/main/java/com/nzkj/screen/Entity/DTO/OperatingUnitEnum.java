package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 运营单位管理状态
 * @author hyc
 * @date 2019-7-30
 *
 */
public enum OperatingUnitEnum {

	QiYong(1,"启用"),JinYong(2,"禁用");
	
	private String cnName;
	
	private Integer code;
	
	OperatingUnitEnum(Integer code, String cnName){
		this.cnName = cnName;
		this.code = code;
	}
	
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 下拉列表
	 * @return
	 */
	public  final static Map<String,String> getSelect(){
  	Map<String,String> maps=new HashMap<String,String>();
		for(OperatingUnitEnum c: OperatingUnitEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
}
