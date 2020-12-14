package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 充电模块总信息上行（异步）
 * @author LiuLulin
 *
 */
public class ChargeModulePollInfo    implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MaxCur;//充电模块最高输出电流
    private String MaxVol;//充电模块最高输出电压
    private String MinCur;//充电模块最低输出电流
    private String MinVol;//充电模块最低输出电压
    private String OnlineCount;//充电模块在线模块总数量
    private String WorkCount;//充电模块工作模块数量
	public String getMaxCur() {
		return MaxCur;
	}
	public void setMaxCur(String maxCur) {
		MaxCur = maxCur;
	}
	public String getMaxVol() {
		return MaxVol;
	}
	public void setMaxVol(String maxVol) {
		MaxVol = maxVol;
	}
	public String getMinCur() {
		return MinCur;
	}
	public void setMinCur(String minCur) {
		MinCur = minCur;
	}
	public String getOnlineCount() {
		return OnlineCount;
	}
	public void setOnlineCount(String onlineCount) {
		OnlineCount = onlineCount;
	}
	public String getWorkCount() {
		return WorkCount;
	}
	public void setWorkCount(String workCount) {
		WorkCount = workCount;
	}
	public String getMinVol() {
		return MinVol;
	}
	public void setMinVol(String minVol) {
		MinVol = minVol;
	}
  
    

}