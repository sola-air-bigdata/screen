package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 协议类型
 * @author root
 *
 */
public enum ProtocolEnum {
	NanWang(1, "南网协议"), KeHua(2, "智能充电桩标准协议"), Wu(3, "没有协议"), GongJiaoNanWang(4, "公交南网协议"), KeLuNanWang(5, "科陆南网协议"),
	KeLuDianZiXieYi(6,"科陆电子协议"), HeKangZhiNengXieYi(7,"合康智能协议"), WeiJing(8,"蔚景协议");
	private String cnName;
	private Integer coetx;

	ProtocolEnum(Integer coetx, String cnName) {
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
		for(ProtocolEnum c: ProtocolEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	  
	public final static ProtocolEnum getEnumByCode(int code){
		if(code == 1){
			return ProtocolEnum.NanWang;
		}else if(code == 2){
			return ProtocolEnum.KeHua;
		}else if(code == 3){
			return ProtocolEnum.Wu;
		}else if(code == 4){
			return ProtocolEnum.GongJiaoNanWang;
		}else if(code == 5){
			return ProtocolEnum.KeLuNanWang;
		}else if(code == 6){
			return ProtocolEnum.KeLuDianZiXieYi;
		}else if(code == 7){
			return ProtocolEnum.HeKangZhiNengXieYi;
		}else if(code == 8){
			return ProtocolEnum.WeiJing;
		}
		return null;
	}
}
