package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务类型
 * @author zhangtian
 */
public enum BillBusTypeEnum {
	
	
	KaChongDian(1,"卡充电"),APPChongDian(2,"APP充电"),Vin(3,"vin充电"),WeiXinGongZhongHao(4,"微信公众号"),WeiXinXiaoChengXu(5,"微信小程序"),ZhiFuBaoXiaoChengXu(6,"支付宝小程序");
	
	private	String cnName;
	private   Integer coetx;
	
	BillBusTypeEnum(Integer coetx, String cnName){
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
		for(BillBusTypeEnum c: BillBusTypeEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}

	public final static BillBusTypeEnum getEnumByCode(int code){
		if(code == 1){
			return BillBusTypeEnum.KaChongDian;
		}else if(code == 2){
			return BillBusTypeEnum.APPChongDian;
		}else if(code == 3){
			return BillBusTypeEnum.Vin;
		}else if(code == 4){
			return BillBusTypeEnum.WeiXinGongZhongHao;
		}else if(code == 5){
			return BillBusTypeEnum.WeiXinXiaoChengXu;
		}else if(code == 6){
			return BillBusTypeEnum.ZhiFuBaoXiaoChengXu;
		}
		return null;
	}
	
	
	
	

}
