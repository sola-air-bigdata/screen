package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点设置枚举
 * @author hyc
 * @date 2018-12-10
 *
 */
public enum StationConfigTypeEnum {

	YiSheZhi(1,"已设置"),WeiSheZhi(2,"未设置");
	private Integer code;
	private String cnName;
	private StationConfigTypeEnum(Integer code, String cnName){
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
		for (StationConfigTypeEnum c : StationConfigTypeEnum.values()) {
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
}
