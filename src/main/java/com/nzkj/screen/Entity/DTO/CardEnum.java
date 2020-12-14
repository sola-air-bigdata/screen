package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 卡类型
 * @author zhangtian
 */
public enum CardEnum {
	
	YiKaYiZhuang(1,"一卡一桩"),YiKaDuoZhuang(2,"一卡多桩"),ZiYouKongZhi(3,"自由控制");
	
	private	String cnName;
	private   Integer coetx;
	
	CardEnum(Integer coetx, String cnName){
	    this.coetx=coetx;
	    this.cnName=cnName;
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
	 * @return
	 */
	public  final static Map<String,String> getSelect(){
    	Map<String,String> maps=new HashMap<String,String>();
		for(CardEnum c: CardEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	
	public final static CardEnum getEnumByCode(int code){
		if(code == 1){
			return CardEnum.YiKaYiZhuang;
		}else if(code == 2){
			return CardEnum.YiKaDuoZhuang;
		}else if(code == 3){
			return CardEnum.ZiYouKongZhi;
		}
		return null;
	}

}
