package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 电池充电总信息上行
 * @author LiuLulin
 *
 */
public class BatChargePoolinfo  implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String BSBatStat1;//电池状态1
    private String BSBatStat2;//电池状态2
    private String BSMaxTemp;//最高动力蓄电池温度
    private String BSMaxTempCode;//最高温度检测点编号
    private String BSMaxVal;//最高单体动力蓄电池电池电压
    private String BSMaxValCode;//最高单体动力蓄电池电压所在编号
    private String BSMinTemp;//最低动力蓄电池温度
    private String BSMinTempCode;//最低温度检测点编号
    private String BSMinVal;//最低单体动力蓄电池电池电压
    private String BSMinValCode;//最低单体动力蓄电池电压所在编号
    private String ChargeCurMeasureVal;//充电电流测量值
    private String ChargeMode;//充电模式             1：恒压充电 2：恒流充电
    private String ChargeVolMeasureVal;//充电电压测量值 
    private String CurrentDmand;//电流需求
    private String VoltageDemand;//电压需求
	public String getBSBatStat1() {
		return BSBatStat1;
	}
	public void setBSBatStat1(String bSBatStat1) {
		BSBatStat1 = bSBatStat1;
	}
	public String getBSBatStat2() {
		return BSBatStat2;
	}
	public void setBSBatStat2(String bSBatStat2) {
		BSBatStat2 = bSBatStat2;
	}
	public String getBSMaxTemp() {
		return BSMaxTemp;
	}
	public void setBSMaxTemp(String bSMaxTemp) {
		BSMaxTemp = bSMaxTemp;
	}
	public String getBSMaxTempCode() {
		return BSMaxTempCode;
	}
	public void setBSMaxTempCode(String bSMaxTempCode) {
		BSMaxTempCode = bSMaxTempCode;
	}
	public String getBSMaxVal() {
		return BSMaxVal;
	}
	public void setBSMaxVal(String bSMaxVal) {
		BSMaxVal = bSMaxVal;
	}
	public String getBSMaxValCode() {
		return BSMaxValCode;
	}
	public void setBSMaxValCode(String bSMaxValCode) {
		BSMaxValCode = bSMaxValCode;
	}
	public String getBSMinTemp() {
		return BSMinTemp;
	}
	public void setBSMinTemp(String bSMinTemp) {
		BSMinTemp = bSMinTemp;
	}
	public String getBSMinTempCode() {
		return BSMinTempCode;
	}
	public void setBSMinTempCode(String bSMinTempCode) {
		BSMinTempCode = bSMinTempCode;
	}
	public String getBSMinVal() {
		return BSMinVal;
	}
	public void setBSMinVal(String bSMinVal) {
		BSMinVal = bSMinVal;
	}
	public String getBSMinValCode() {
		return BSMinValCode;
	}
	public void setBSMinValCode(String bSMinValCode) {
		BSMinValCode = bSMinValCode;
	}
	public String getChargeCurMeasureVal() {
		return ChargeCurMeasureVal;
	}
	public void setChargeCurMeasureVal(String chargeCurMeasureVal) {
		ChargeCurMeasureVal = chargeCurMeasureVal;
	}
	public String getChargeMode() {
		return ChargeMode;
	}
	public void setChargeMode(String chargeMode) {
		ChargeMode = chargeMode;
	}
	public String getChargeVolMeasureVal() {
		return ChargeVolMeasureVal;
	}
	public void setChargeVolMeasureVal(String chargeVolMeasureVal) {
		ChargeVolMeasureVal = chargeVolMeasureVal;
	}
	public String getCurrentDmand() {
		return CurrentDmand;
	}
	public void setCurrentDmand(String currentDmand) {
		CurrentDmand = currentDmand;
	}
	public String getVoltageDemand() {
		return VoltageDemand;
	}
	public void setVoltageDemand(String voltageDemand) {
		VoltageDemand = voltageDemand;
	}
    

}