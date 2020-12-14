package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * BMS 由于某种原因停止充电时，主动异步上送
 * @author LiuLulin
 *
 */
public class BmsStopChargeReason   implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String StopChargeErrorReason;//BMS 中止充电错误原因
    private String StopChargeFaultReason;//BMS 中止充电故障原因
    private String StopChargeReason;//BMS 中止充电原因
    private String Time;//时间
	public String getStopChargeErrorReason() {
		return StopChargeErrorReason;
	}
	public void setStopChargeErrorReason(String stopChargeErrorReason) {
		StopChargeErrorReason = stopChargeErrorReason;
	}
	public String getStopChargeFaultReason() {
		return StopChargeFaultReason;
	}
	public void setStopChargeFaultReason(String stopChargeFaultReason) {
		StopChargeFaultReason = stopChargeFaultReason;
	}
	public String getStopChargeReason() {
		return StopChargeReason;
	}
	public void setStopChargeReason(String stopChargeReason) {
		StopChargeReason = stopChargeReason;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
   
    
    

}