package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zhangtian
 */
public enum StationEnum {
	
	KaiQi(1,"开启"),GuanBi(2,"关闭"),WeiZhi(3,"未知"),JianShenZhong(4,"建设中"),WeiHuZhong(5,"维护中");
	  private	String cnName;
	  private   Integer coetx;
	
	  StationEnum(Integer coetx, String cnName){
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
		for(StationEnum c: StationEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

	public final static StationEnum getEnumByName(String name){
		
		if("开启".equals(name)){
			return StationEnum.KaiQi;
		}else if("关闭".equals(name)){
			return StationEnum.GuanBi;
		}else if("未知".equals(name)){
			return StationEnum.WeiZhi;
		}else if("建设中".equals(name)){
			return StationEnum.JianShenZhong;
		}else if("维护中".equals(name)){
			return StationEnum.WeiHuZhong;
		}
		return null;
	}
}
