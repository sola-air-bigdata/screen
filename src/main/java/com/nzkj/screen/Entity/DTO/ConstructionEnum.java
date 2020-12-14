package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 建设场所
 * @author zhangtian
 *
 */
public enum ConstructionEnum {
	
	JMQ(1,"居民区"),GGJG(2,"公共机构"),QSYDW(3,"企事业单位"),XZL(4,"写字楼"),GYYQ(5,"工业园区"),JTSK(6,"交通枢纽"),DXWTSS(7,"大型文体设施"),CSLD(8,"城市绿地"),DXJZPJTCC(9,"大型建筑配件停车场"),LBTCW(10,"路边停车位"),CSGSFWQ(11,"城际高速服务区"),QT(12,"其他");
	private Integer code;
	private String cnName;

	private ConstructionEnum(Integer code, String cnName) {
		this.code = code;
		this.cnName = cnName;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	/**
	 * 下拉列表
	 * 
	 * @return
	 */
	public final static Map<String, String> getSelect() {
		Map<String, String> maps = new HashMap<String, String>();
		for (ConstructionEnum c : ConstructionEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static ConstructionEnum getEnumByCode(int code){
		if(code == 1){
			return ConstructionEnum.JMQ;
		}else if(code == 2){
			return ConstructionEnum.GGJG;
		}else if(code == 3){
			return ConstructionEnum.QSYDW;
		}else if(code == 4){
			return ConstructionEnum.XZL;
		}else if(code == 5){
			return ConstructionEnum.GYYQ;
		}else if(code == 6){
			return ConstructionEnum.JTSK;
		}else if(code == 7){
			return ConstructionEnum.DXWTSS;
		}else if(code == 8){
			return ConstructionEnum.CSLD;
		}else if(code == 9){
			return ConstructionEnum.DXJZPJTCC;
		}else if(code == 10){
			return ConstructionEnum.LBTCW;
		}else if(code == 11){
			return ConstructionEnum.CSGSFWQ;
		}else if(code == 12){
			return ConstructionEnum.QT;
		}
		return null;
	}

}
