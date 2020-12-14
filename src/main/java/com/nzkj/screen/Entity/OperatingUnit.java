package com.nzkj.screen.Entity;


import javax.persistence.*;

/**
 * 运营单位管理
 * @author hyc
 * @date 2019-7-30
 *
 */

@Entity(name = "t_business_base_operation_operating_unit")
public class OperatingUnit {

	@Id
	@GeneratedValue
	//id
	private long id;
	
	//运营单位名称
	@Column(name="v_name")
	private String name;
	
	//联系人
	@Column(name="v_linkman")
	private String linkman;
	
	//联系电话
	@Column(name="v_telephone")
	private String telephone;
	
	//工作单位
	@Column(name="v_work_unit")
	private String workUnit;
	
	//状态
	@Column(name="v_status_type")
	private String statusType;
	
	//联系地址
	@Column(name="v_address")
	private String address;
	
	//上级运营单位
	@Column(name="l_superior_id")
	private Long superiorId;
	
	@Column(name="l_seller_id")
	private Long sellerId;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

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

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
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

}
