package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 电池温度信息上行（异步）
 * @author LiuLulin
 *
 */
public class SingleBatTempInfo   implements Serializable   {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String SingleBatVol;//蓄电池温度检测点
    private String index;//序号
	public String getSingleBatVol() {
		return SingleBatVol;
	}
	public void setSingleBatVol(String singleBatVol) {
		SingleBatVol = singleBatVol;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
   
    
    
}