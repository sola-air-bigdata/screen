package com.nzkj.screen.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

/**
 * 账户表
 * 
 * @author zhangtian
 */

@Entity(name = "t_business_base_operation_persional_member_account")
public class Account {

	@Id
	@GeneratedValue
	//id
	private long id;



	/**
	 * 账户余额
	 */
	@Column(name = "m_account_balance")
	private Integer accountBalance;

	/**
	 * 账户名称
	 */
	@Column(name = "v_accoun_name")
	private String accounName;
	/**
	 * 卡 或者 手机号
	 */
	@Column(name = "v_number")
	private String number;
	/**
	 * 账户类型 (手机类型和卡类型)详见码表
	 */
	@Column(name = "s_number_type")
	private Short numberType;
	/**
	 * 状态(冻结，禁用)
	 */
	@Column(name = "s_status")
	private Short status;
	/**
	 * 充电方式的类型（一卡多桩，一卡一桩）以卡为主 1 一卡一桩   2 一卡多桩
	 */
	@Column(name = "l_card_charge_type")
	private Short cardChargeType;

	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "l_member_id")
	private Member member;

	@OneToMany(mappedBy = "account")
	private Set<AccountFlow> setAccountFlow;
	
	
	@Column(name = "l_seller_id")
	private Long sellerId;
	
	@Column(name = "l_group_id")
	private Long groupId;
	
	// 成员
	@Column(name = "l_leaguer_id")
	private Long leaguerId;

	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccounName() {
		return accounName;
	}

	public void setAccounName(String accounName) {
		this.accounName = accounName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Short getNumberType() {
		return numberType;
	}

	public void setNumberType(Short numberType) {
		this.numberType = numberType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getCardChargeType() {
		return cardChargeType;
	}

	public void setCardChargeType(Short cardChargeType) {
		this.cardChargeType = cardChargeType;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<AccountFlow> getSetAccountFlow() {
		return setAccountFlow;
	}

	public void setSetAccountFlow(Set<AccountFlow> setAccountFlow) {
		this.setAccountFlow = setAccountFlow;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(Long leaguerId) {
		this.leaguerId = leaguerId;
	}



	public Account() {
		super();
	}

	public Account(Long id, Integer accountBalance, String number, Short numberType, Short status, Short cardChargeType,
                   Long sellerId) {
		super();
		this.id = id;
		this.accountBalance = accountBalance;
		this.number = number;
		this.numberType = numberType;
		this.status = status;
		this.cardChargeType = cardChargeType;
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountBalance=" + accountBalance +
				", accounName='" + accounName + '\'' +
				", number='" + number + '\'' +
				", numberType=" + numberType +
				", status=" + status +
				", cardChargeType=" + cardChargeType +
				", sellerId=" + sellerId +
				", groupId=" + groupId +
				", leaguerId=" + leaguerId +
				", id=" + id +
				'}';
	}

}
