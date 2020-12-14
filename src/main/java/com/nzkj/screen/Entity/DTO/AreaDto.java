package com.nzkj.screen.Entity.DTO;

/**
 * 区域
 * 
 * @author zhangtian
 */
public class AreaDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 添加的用户id
	 */
	private Long addUserId;
	/**
	 * 区域名称
	 */
	private String name;

	
	/**
	 * 父区域id
	 */
	private Long parentId;

	/**
	 * 区域的状态(失效)
	 */
	private AreaStatusEnum areaStatusEnum;

	private Boolean deleteFlag;
	
	
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

	public AreaStatusEnum getAreaStatusEnum() {
		return areaStatusEnum;
	}

	public void setAreaStatusEnum(AreaStatusEnum areaStatusEnum) {
		this.areaStatusEnum = areaStatusEnum;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public static void main(String[] args) {
		System.out.println(AreaStatusEnum.getSelect());
	}

}
