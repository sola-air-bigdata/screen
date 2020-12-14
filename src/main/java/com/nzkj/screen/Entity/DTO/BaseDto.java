package com.nzkj.screen.Entity.DTO;

import java.math.BigInteger;
import java.util.Date;

/**
 * BaseDto
 *
 * @Company nz
 * @author lwz
 * @date 2018-10-17
 */
public class BaseDto extends Result {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	private Long id;

	/**
	 * 添加时间
	 */
	private Date addTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	/**
	 * 商家id
	 */
	private Long sellerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		return "BaseDto [id=" + id + ", addTime=" + addTime + ", updateTime=" + updateTime + ", sellerId=" + sellerId
				+ "]";
	}

}
