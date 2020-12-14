package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口类型
 * @author zhangtian
 */
public enum InterfaceTypeEnum {
	
	YangChengChong(1,"羊城充"),ShangHaiShiZhengFu(2,"上海市政府"),ChongDianSheShi(3,"广东省电动汽车充电设施平台"),
	KUNSHAN(4,"昆山监管平台"),EVCSTANDARD(5,"中联电标准"),XiaoJieCharge(6,"小桔充电"),DongGuan(7,"东莞监管平台"),
	CaoCaoKeJi(8,"曹操科技"), JiaoTouPingTai(9,"交投平台"),JieDianTong(10,"捷电通");
	

	private Integer code;
	private String cnName;

	private InterfaceTypeEnum(Integer code, String cnName) {
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
		for (InterfaceTypeEnum c : InterfaceTypeEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	
	public final static InterfaceTypeEnum getEnumByCode(int code){
		if(code ==1){
			return InterfaceTypeEnum.YangChengChong;
		}else if(code ==2){
			return InterfaceTypeEnum.ShangHaiShiZhengFu;
		}else if(code ==3){
			return InterfaceTypeEnum.ChongDianSheShi;
		}else if(code ==4){
			return InterfaceTypeEnum.KUNSHAN;
		}else if(code ==5){
			return InterfaceTypeEnum.EVCSTANDARD;
		}else if(code ==6){
			return InterfaceTypeEnum.XiaoJieCharge;
		}else if(code ==7){
			return InterfaceTypeEnum.DongGuan;
		}else if(code ==8){
			return InterfaceTypeEnum.CaoCaoKeJi;
		}else if(code ==9){
			return InterfaceTypeEnum.JiaoTouPingTai;
		}else if(code ==10){
			return InterfaceTypeEnum.JieDianTong;
		}else{
			return null;
		}
	}
}
