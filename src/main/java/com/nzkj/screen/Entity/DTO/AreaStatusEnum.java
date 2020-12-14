package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 区域状态
 * @author zhangtian
 */
public enum AreaStatusEnum {
	
    ShiXiao(1,"失效"),ZhengChang(2,"正常");
	private	String cnName;
	private   Integer coetx;
	
    AreaStatusEnum(Integer coetx, String cnName){
	    this.coetx=coetx;
	    this.cnName=cnName;
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
		for(AreaStatusEnum c: AreaStatusEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static AreaStatusEnum getEnumByCode(int code){
		if(code == 1){
			return AreaStatusEnum.ShiXiao;
		}else if(code == 2){
			return AreaStatusEnum.ZhengChang;
		}
		return null;
	}

}
