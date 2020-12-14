package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员类型
 * @author Administrator
 *
 */
public enum MemberTypeEnum {
	GeRen(1,"个人"),TuanDui(2,"车队"),HuLianHuTong(3,"互联互通");
	
	private Integer code;
	private String cnName;
	private MemberTypeEnum(Integer code, String cnName){
		this.code = code;
	    this.cnName = cnName;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCoetx(Integer code) {
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
	 * @return
	 */
	public  final static Map<String,String> getSelect(){
    	Map<String,String> valueMap=new HashMap<String,String>();
		for(MemberTypeEnum memberType: MemberTypeEnum.values()){
			valueMap.put(memberType.name(), memberType.getCnName());
		}
		return valueMap;
	}
	
	public final static MemberTypeEnum getEnumByCode(int code){
		if(code == 1){
			return MemberTypeEnum.GeRen;
		}else if(code == 2){
			return MemberTypeEnum.TuanDui;
		}else if(code == 3){
			return MemberTypeEnum.HuLianHuTong;
		}
		return null;
	}
}
