package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 *  充电模块详细信息上行（异步）
 * @author LiuLulin
 *
 */
public class ChargeModuleDeatileDInfo   implements Serializable   {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ModuleOuptputCur;//充电模块输出电流
    private String ModuleOuptputVol;//充电模块输出电压
    private String ModuleSN;//充电模块工作模块数量
    private String index;//模块n序号
	public String getModuleOuptputCur() {
		return ModuleOuptputCur;
	}
	public void setModuleOuptputCur(String moduleOuptputCur) {
		ModuleOuptputCur = moduleOuptputCur;
	}
	public String getModuleOuptputVol() {
		return ModuleOuptputVol;
	}
	public void setModuleOuptputVol(String moduleOuptputVol) {
		ModuleOuptputVol = moduleOuptputVol;
	}
	public String getModuleSN() {
		return ModuleSN;
	}
	public void setModuleSN(String moduleSN) {
		ModuleSN = moduleSN;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
   
    
    

}