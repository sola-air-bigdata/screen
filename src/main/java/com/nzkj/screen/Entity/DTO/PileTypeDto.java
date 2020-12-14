package com.nzkj.screen.Entity.DTO;

/**
 * 桩类型
 * 
 * @author zhangtian
 */
public class PileTypeDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 协议值
	 */
	private ProtocolEnum protocolValue;
	/**
	 * 类型编号
	 */
	private String typeNo;
	/**
	 * 枪数
	 */
	private Integer gunCount;
	/**
	 * 设备类型名称
	 */
	private String name;

	/**
	 * 品牌
	 */
	private CatalogEnum catalog;

	/**
	 * 图片路径
	 */
	private String imagePath;
	/**
	 * 图片地址
	 */
	private String iconPath;
    /**
     * 备注
     */
	private String remark;
	/**
	 * 桩的电流类型模式
	 */
	private CurrentModeEnum currentModeEnum;
	
	/**
	 * 是否桩计费
	 * true桩计费的值为准，false台计费为准
	 */
	private Boolean billing;
	
	//标准
	private NationalStandardEnum nationalStandardEnum;
	
	//额定电压上限
	private Integer voltageUpperLimits;
	
	//额定电压下限
	private Integer voltageLowerLimits;
	
	//额定电流
    private Integer ratedCurrent;
    
    //额定功率
    private Float power;
	
	/**
	 * 枪数
	 */
	public Integer getGunCount() {
		return gunCount;
	}
	/**
	 * 枪数
	 */
	public void setGunCount(Integer gunCount) {
		this.gunCount = gunCount;
	}
	 /**
     * 备注
     */
	public String getRemark() {
		return remark;
	}
	 /**
     * 备注
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 设备类型名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设备类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 图片路径
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * 图片路径
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * 图片地址
	 */
	public String getIconPath() {
		return iconPath;
	}
	/**
	 * 图片地址
	 */
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	/**
	 * 协议值
	 */
	public ProtocolEnum getProtocolValue() {
		return protocolValue;
	}

	/**
	 * 协议值
	 */
	public void setProtocolValue(ProtocolEnum protocolValue) {
		this.protocolValue = protocolValue;
	}
	/**
	 * 品牌
	 */
	public CatalogEnum getCatalog() {
		return catalog;
	}
	/**
	 * 品牌
	 */
	public void setCatalog(CatalogEnum catalog) {
		this.catalog = catalog;
	}
	public CurrentModeEnum getCurrentModeEnum() {
		return currentModeEnum;
	}
	public void setCurrentModeEnum(CurrentModeEnum currentModeEnum) {
		this.currentModeEnum = currentModeEnum;
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
	public NationalStandardEnum getNationalStandardEnum() {
		return nationalStandardEnum;
	}
	public void setNationalStandardEnum(NationalStandardEnum nationalStandardEnum) {
		this.nationalStandardEnum = nationalStandardEnum;
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
