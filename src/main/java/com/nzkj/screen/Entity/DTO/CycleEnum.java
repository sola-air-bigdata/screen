package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 周期类型
 * 
 * @author zhangtian
 */
public enum CycleEnum {
	
    MeiYue(1,"每月"), MeiJiDu(2,"每季度"),MeiBanNian(3,"每半年"),MeiNian(4,"每年");
	
	private	String cnName; 
	private Integer coetx;

	CycleEnum(Integer coetx, String cnName){
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
		for(CycleEnum c: CycleEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static CycleEnum getEnumByCode(int code){
		if(code == 1){
			return CycleEnum.MeiYue;
		}else if(code == 2){
			return CycleEnum.MeiJiDu;
		}else if(code == 3){
			return CycleEnum.MeiBanNian;
		}else if(code == 4){
			return CycleEnum.MeiNian;
		}
		return null;
	}
}
