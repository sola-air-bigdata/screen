package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 电池充电参数信息上行
 * @author LiuLulin
 *
 */
public class BatChargeParam  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String AllowChargingMaxCur;//最高允许充电电流
    private String AllowChargingMaxTemp;//最高允许温度
    private String AllowChargingMaxVal; //最高允许充电总电压
    private String BatterTotalEnergy;//动力蓄电池标称总能量
    private String CarBatteryVal;//整车动力蓄电池当前电池电压
    private String SOC;//整车动力蓄电池荷电状态SOC
    private String SingleBatterMaxVal;//单体动力蓄电池最高允许充电电压
	public String getAllowChargingMaxCur() {
		return AllowChargingMaxCur;
	}
	public void setAllowChargingMaxCur(String allowChargingMaxCur) {
		AllowChargingMaxCur = allowChargingMaxCur;
	}
	public String getAllowChargingMaxTemp() {
		return AllowChargingMaxTemp;
	}
	public void setAllowChargingMaxTemp(String allowChargingMaxTemp) {
		AllowChargingMaxTemp = allowChargingMaxTemp;
	}
	public String getAllowChargingMaxVal() {
		return AllowChargingMaxVal;
	}
	public void setAllowChargingMaxVal(String allowChargingMaxVal) {
		AllowChargingMaxVal = allowChargingMaxVal;
	}
	public String getBatterTotalEnergy() {
		return BatterTotalEnergy;
	}
	public void setBatterTotalEnergy(String batterTotalEnergy) {
		BatterTotalEnergy = batterTotalEnergy;
	}
	public String getCarBatteryVal() {
		return CarBatteryVal;
	}
	public void setCarBatteryVal(String carBatteryVal) {
		CarBatteryVal = carBatteryVal;
	}
	public String getSOC() {
		return SOC;
	}
	public void setSOC(String sOC) {
		SOC = sOC;
	}
	public String getSingleBatterMaxVal() {
		return SingleBatterMaxVal;
	}
	public void setSingleBatterMaxVal(String singleBatterMaxVal) {
		SingleBatterMaxVal = singleBatterMaxVal;
	}
   
    

}