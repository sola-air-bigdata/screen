package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 充电机基础信息上行（异步）
 * @author LiuLulin
 *
 */
public class ChargeDevBaseInfo   implements Serializable   {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ChargeMode;//充电模式         1：恒压充电 2：恒流充电
    private String OutputCur;//充电机输出电流
    private String OutputVol;//充电机输出电压
    private String Stauts;//充电机当前状态
	public String getChargeMode() {
		return ChargeMode;
	}
	public void setChargeMode(String chargeMode) {
		ChargeMode = chargeMode;
	}
	public String getOutputCur() {
		return OutputCur;
	}
	public void setOutputCur(String outputCur) {
		OutputCur = outputCur;
	}
	public String getOutputVol() {
		return OutputVol;
	}
	public void setOutputVol(String outputVol) {
		OutputVol = outputVol;
	}
	public String getStauts() {
		return Stauts;
	}
	public void setStauts(String stauts) {
		Stauts = stauts;
	}
   
}