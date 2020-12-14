package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zhangtian
 *
 */
public enum StationTypeEnum {
	
	GongGong(1,"公共"),GeRen(2,"个人"),GongJiao(3,"公交"),HuanWei(4,"环卫"),WuLiu(5,"物流"),ChuZuChe(6,"出租车"),QiTa(7,"其他");
	  private	String cnName;
	  private   Integer coetx;
	
	  StationTypeEnum(Integer coetx, String cnName){
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
		for(StationTypeEnum c: StationTypeEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

	public final static StationTypeEnum getEnumByCode(int code){
		if(code == 1){
			return StationTypeEnum.GongGong;
		}else if(code == 2){
			return StationTypeEnum.GeRen;
		}else if(code == 3){
			return StationTypeEnum.GongJiao;
		}else if(code == 4){
			return StationTypeEnum.HuanWei;
		}else if(code == 5){
			return StationTypeEnum.WuLiu;
		}else if(code == 6){
			return StationTypeEnum.ChuZuChe;
		}else if(code == 7){
			return StationTypeEnum.QiTa;
		}
		return null;
	}

}
