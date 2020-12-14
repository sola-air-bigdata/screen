package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 单体蓄电池电压信息上行（异步）
 * Auto-generated: 2018-10-28 8:38:21
 *
 */
public class SingleBatVoInfo    implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String SingleBatVol;//单体蓄电池电压
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