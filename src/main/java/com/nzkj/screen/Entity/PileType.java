package com.nzkj.screen.Entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 桩类型
 * 
 * @author zhangtian
 */
@Entity
@Table(name = "t_business_config_operation_pile_type")
public class PileType {


	@Id
	@GeneratedValue
	//id
	private long id;

	/**
	 * 协议值
	 */
	@Column(name = "s_protocol_value")
	private Short protocolValue;
	
	/**
	 * 类型编号
	 */
	@Column(name = "s_typeno")
	private String typeNo;
	
	/**
	 * 枪数
	 */
	@Column(name = "s_gun_num")
	private Integer gunNum;
	/**
	 * 设备类型名称
	 */
	@Column(name = "v_name")
	private String name;

	/**
	 * 类型（设备）Id
	 */
	@Column(name = "v_chargeing_type")
	private String chargeingType;

	/**
	 * 图片路径
	 */
	@Column(name = "v_image_path")
	private String imagePath;
	/**
	 * 图片地址
	 */
	@Column(name = "v_icon_path")
	private String iconPath;
	
	/**
	 * 备注
	 */
	@Column(name = "v_remark")
	private String remark;
	
	/**
	 * 电流类型
	 */
	@Column(name = "i_currentmode")
	private Integer currentmode;
	
	
	@Column(name="b_billing",nullable = true,length=1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	protected Boolean billing=true;
	
	
	
	//标准
	@Column(name = "i_national_standard")
	private Integer nationalStandard;
	
	//额定电压上限
	@Column(name = "i_voltage_upper_limits")
	private Integer voltageUpperLimits;
	
	//额定电压下限
	@Column(name = "i_voltage_lower_limits")
	private Integer voltageLowerLimits;
	
	//额定电流
	@Column(name = "i_rated_current")
    private Integer ratedCurrent;
    
    //额定功率
	@Column(name = "i_power")
    private Float power;
	
	
	public Short getProtocolValue() {
		return protocolValue;
	}

	public void setProtocolValue(Short protocolValue) {
		this.protocolValue = protocolValue;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChargeingType() {
		return chargeingType;
	}

	public void setChargeingType(String chargeingType) {
		this.chargeingType = chargeingType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public Integer getGunNum() {
		return gunNum;
	}

	public void setGunNum(Integer gunNum) {
		this.gunNum = gunNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCurrentmode() {
		return currentmode;
	}

	public void setCurrentmode(Integer currentmode) {
		this.currentmode = currentmode;
	}

	public Boolean getBilling() {
		return billing;
	}

	public void setBilling(Boolean billing) {
		this.billing = billing;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	

	public Integer getNationalStandard() {
		return nationalStandard;
	}

	public void setNationalStandard(Integer nationalStandard) {
		this.nationalStandard = nationalStandard;
	}

	public Integer getVoltageUpperLimits() {
		return voltageUpperLimits;
	}

	public void setVoltageUpperLimits(Integer voltageUpperLimits) {
		this.voltageUpperLimits = voltageUpperLimits;
	}

	public Integer getVoltageLowerLimits() {
		return voltageLowerLimits;
	}

	public void setVoltageLowerLimits(Integer voltageLowerLimits) {
		this.voltageLowerLimits = voltageLowerLimits;
	}

	public Integer getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(Integer ratedCurrent) {
		this.ratedCurrent = ratedCurrent;
	}

	public Float getPower() {
		return power;
	}

	public void setPower(Float power) {
		this.power = power;
	}

}
