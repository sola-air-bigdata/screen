package com.nzkj.screen.Entity.DTO;


import java.util.List;

public class OperatingUnitCondition extends BaseCondition {

	private static final long serialVersionUID = 1L;
	
	//运营单位名称
	private String name;
	
	private String telephone;
	
	private String startTime;
	
	private String endTime;
	
	private OperatingUnitEnum operatingUnitEnum;
	
	//所属上级id
	private Long superiorId;
	
	//是否查询二级运营单位(false--一级  true--二级)
	private Boolean flag;

	//站点id
	private Long stationId;
	
	/** -------------------汇总查询------------------- **/
	// 运营单位
	private List<Long> unitIds;
	// 分公司
	private List<Long> companyIds;
	// 车队
	private List<Long> memberIds;
	// 单个车队
	private Long memberId;
	//车牌号
	private String platNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public OperatingUnitEnum getOperatingUnitEnum() {
		return operatingUnitEnum;
	}

	public void setOperatingUnitEnum(OperatingUnitEnum operatingUnitEnum) {
		this.operatingUnitEnum = operatingUnitEnum;
	}

	public Long getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(Long superiorId) {
		this.superiorId = superiorId;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public List<Long> getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(List<Long> unitIds) {
		this.unitIds = unitIds;
	}

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public List<Long> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPlatNo() {
		return platNo;
	}

	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}


	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
}
