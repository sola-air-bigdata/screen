package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询条件基类
 * 
 * @Company: nz
 * @author lwz
 * @date 2018-10-17
 */
public class BaseCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商家ID(必填)
	 */
	private Long sellerId;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 开始时间
	 */
	private Date timeS;

	/**
	 * 结束时间
	 */
	private Date timeE;
	
	//页码
	private Integer page;
	
	//页数
	private Integer pageSize;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getTimeS() {
		return timeS;
	}

	public void setTimeS(Date timeS) {
		this.timeS = timeS;
	}

	public Date getTimeE() {
		return timeE;
	}

	public void setTimeE(Date timeE) {
		this.timeE = timeE;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Condition [sellerId=" + sellerId + ", userId=" + userId + ", timeS=" + timeS + ", timeE=" + timeE + "]";
	}

}
