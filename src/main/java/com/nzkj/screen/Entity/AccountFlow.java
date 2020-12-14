package com.nzkj.screen.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * 流水
 * 
 * @author zhangtian
 *
 */

@Entity(name = "t_business_base_operation_persional_member_account_flowing")
public class AccountFlow {

	@Id
	@GeneratedValue
	//id
	private long id;


	/**
	 * 账户余额
	 */
	@Column(name = "m_account_balance")
	private Long accountBalance;
	/**
	 * 收入
	 */
	@Column(name = "m_income")
	private Integer income;

	/**
	 * 支出
	 */
	@Column(name = "m_spending")
	private Integer spending;

	/**
	 * 收入时间
	 */
	@Column(name = "d_income_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date incomeTime;
	/**
	 * 支出时间
	 */	
	@Column(name = "d_spending_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date spendingTime;

	/**
	 * 信用额度
	 */
	@Column(name = "m_line_of_credit")
	private Integer lineOfCredit;
	/**
	 * 币种详见码表
	 */
	@Column(name = "i_currency_type")
	private Integer currencyType;

	/**
	 * 事件动作之前的余额
	 */
	@Column(name = "m_account_balance_bef")
	private Integer accountBalanceBef;
	/**
	 * 事件名称
	 */
	@Column(name = "v_event_name")
	private Integer eventName;
	
	
    /**
     * 事件id	
     */
	@Column(name = "v_event_id")
	private Long eventId;
	
	@Column(name = "l_seller_id")
	private Long sellerId;
	
	/**
	 * 充电前总金额
	 */
	@Column(name="m_account_total_balance_bef")
	private Long accountTotalBalanceBef ;

//	public AccountFlow() { 回滚
//	}
//
//	public AccountFlow(Long accountBalance, Integer income, Integer spending, Date incomeTime, Date spendingTime, Integer lineOfCredit, Integer currencyType, Integer accountBalanceBef, Integer eventName, Long eventId, Long sellerId, Long accountTotalBalanceBef) {
//		this.accountBalance = accountBalance;
//		this.income = income;
//		this.spending = spending;
//		this.incomeTime = incomeTime;
//		this.spendingTime = spendingTime;
//		this.lineOfCredit = lineOfCredit;
//		this.currencyType = currencyType;
//		this.accountBalanceBef = accountBalanceBef;
//		this.eventName = eventName;
//		this.eventId = eventId;
//		this.sellerId = sellerId;
//		this.accountTotalBalanceBef = accountTotalBalanceBef;
//	}

	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	public Date getSpendingTime() {
		return spendingTime;
	}

	public void setSpendingTime(Date spendingTime) {
		this.spendingTime = spendingTime;
	}
	public Integer getLineOfCredit() {
		return lineOfCredit;
	}

	public void setLineOfCredit(Integer lineOfCredit) {
		this.lineOfCredit = lineOfCredit;
	}

	public Integer getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Integer currencyType) {
		this.currencyType = currencyType;
	}

	public Integer getEventName() {
		return eventName;
	}

	public void setEventName(Integer eventName) {
		this.eventName = eventName;
	}
	
	
	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "l_member_account_id")
	private Account account;
	

	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getSpending() {
		return spending;
	}

	public void setSpending(Integer spending) {
		this.spending = spending;
	}

	public Integer getAccountBalanceBef() {
		return accountBalanceBef;
	}

	public void setAccountBalanceBef(Integer accountBalanceBef) {
		this.accountBalanceBef = accountBalanceBef;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Long getAccountTotalBalanceBef() {
		return accountTotalBalanceBef;
	}

	public void setAccountTotalBalanceBef(Long accountTotalBalanceBef) {
		this.accountTotalBalanceBef = accountTotalBalanceBef;
	}

	@Override
	public String toString() {
		return "AccountFlow{" +
				"accountBalance=" + accountBalance +
				", income=" + income +
				", spending=" + spending +
				", incomeTime=" + incomeTime +
				", spendingTime=" + spendingTime +
				", lineOfCredit=" + lineOfCredit +
				", currencyType=" + currencyType +
				", accountBalanceBef=" + accountBalanceBef +
				", eventName=" + eventName +
				", eventId=" + eventId +
				", sellerId=" + sellerId +
				", accountTotalBalanceBef=" + accountTotalBalanceBef +
				", account=" + account +
				'}';
	}
}
