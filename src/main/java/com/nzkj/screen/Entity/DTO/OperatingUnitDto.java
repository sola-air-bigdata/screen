package com.nzkj.screen.Entity.DTO;

/**
 * 运营单位管理
 * @author hyc
 * @date 2019-7-30
 *
 */
public class OperatingUnitDto extends BaseDto{

	private static final long serialVersionUID = 1L;

		
	//运营单位名称
	private String name;
	
	//联系人
	private String linkman;
	
	//联系电话
	private String telephone;
	
	//工作单位
	private String workUnit;
	
	//状态
	private OperatingUnitEnum operatingUnitEnum;
	
	//联系地址
	private String address;
	
	//上级运营单位id
	private Long superiorId;
	
	//上级运营单位
	private OperatingUnitDto operatingUnitDto;
	
	//添加时间
	private String addDate;
	
	//下属数量
	private int lowerCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public OperatingUnitEnum getOperatingUnitEnum() {
		return operatingUnitEnum;
	}

	public void setOperatingUnitEnum(OperatingUnitEnum operatingUnitEnum) {
		this.operatingUnitEnum = operatingUnitEnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(Long superiorId) {
		this.superiorId = superiorId;
	}

	public OperatingUnitDto getOperatingUnitDto() {
		return operatingUnitDto;
	}

	public void setOperatingUnitDto(OperatingUnitDto operatingUnitDto) {
		this.operatingUnitDto = operatingUnitDto;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public int getLowerCount() {
		return lowerCount;
	}

	public void setLowerCount(int lowerCount) {
		this.lowerCount = lowerCount;
	}
	
}
