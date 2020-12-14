package com.nzkj.screen.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * 区域
 * 
 * @author zhangtian
 */

@Entity(name = "t_business_base_operation_area")
public class Area {

	@Id
	@GeneratedValue
	//id
	private long id;

	/**
	 * 添加的用户id
	 */
	@Column(name = "l_add_user_id")
	private Long addUserId;
	/**
	 * 区域名称
	 */
	@Column(name = "v_name")
	private String name;

	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "l_parent_area_id")
	private Area area;

	/**
	 * 区域的状态(失效)
	 */
	@Column(name = "s_status")
	private Short status;

	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "l_seller_id")
	private Operator operator;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Long addUserId) {
		this.addUserId = addUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

}
