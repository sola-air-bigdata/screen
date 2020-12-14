package com.nzkj.screen.Entity.DTO;



import java.util.List;

/**
 * 站点
 * 
 * @author zhangtian
 */
public class StationDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 区域添加者id
	 */
	private Long managerId;
	/**
	 * 区域名称
	 */
	private String name;
	/**
	 * 站点电话
	 */
	private String managerPhone;
	/**
	 * 站点经度
	 */
	private String Longitude;
	/**
	 * 站点纬度
	 */
	private String latitude;

	/**
	 * 详细地址
	 */
	private String street;
	

    /**
     * 父站点id
     */
	private Long  stationId;
	/**
	 * 站点的状态
	 */
	private StationEnum stationEnums;
	/**
	 * 图片的路径
	 */
	private String imagePath;

	/**
	 * 上午开放时间(分钟)
	 */

	private Integer openTimeAm;
	/**
	 * 上午关闭时间(分钟)
	 */

	private Integer closetimeAm;

	/**
	 * 下午开放时间
	 */

	private Integer openTimePm;
	/**
	 * 下午关闭时间
	 */
	
	private Integer closeTimePm;
   
    /**
     * 区域id
     */
	private Long areaid;
	// 是否包含子区域
	private boolean includeSubArea;
	private AreaDto areaDto;

	private OperatorDto operatorDto;
	
	private Boolean deleteFlag;
	/**
	 * 评分
	 */
	private Double score;
	/*
	 * 停车位数量
	 */
	private Integer vehicleNumber;
	/*
	 * 补贴说明
	 */
	private String subsidy;
	/*
	 * 图片1
	 */
	private String img1;
	/*
	 * 图片2
	 */
	private String img2;
	/*
	 * 图片3
	 */
	private String img3;
	
	/**
	 * 查询用户下的站点
	 */
	private Long userId;
	
	/**
	 * 月租金
	 */
	private Integer monthlyRent=0;
	/**
	 * 站点编码
	 */
	private String areaCode;
	
	private StationTypeEnum stationTypeEnum;
	
	private ConstructionEnum constructionEnum;
	
	/**
	 * 站点简称
	 */
	private String abbreviationName;
	
	/**
	 * 是否有售货机
	 */
	private Boolean goodsMachine;
	
	/**
	 * 快慢充
	 * @return
	 */
	private StationFastSlowEnum fastSlow;
	/**
	 * 运营类型
	 * @return
	 */
	private PileOperateTypeEnum runType;
	
	//站点建设目标投入金额（分）
	private Long putIntoMoney;
	
	//站点建设目标实际投入金额（分）
	private Long actualPutIntoMoney;
	
	//站点充电量指标（度）
	private Long chargeTarget;
	
	//站点建桩指标
	private Integer pileTarget;
	
	//桩排数
	private Integer pileRowNum;
	
	//每一排的桩数量
	private Integer pileNumOfRow;
	
	//站点的设置状态
	private StationConfigTypeEnum configTypeEnum;
	
	//站点桩排设置List
	private List<List<StationRowConfigDto>> configList;
	
	// 站点编号
	private String stationNumber;
	
	
	//  ===========    限制优惠券使用的时间段配置     ====== start
	private String preferentialLimitS1;
	private String preferentialLimitS2;
	private String preferentialLimitS3;
	private String preferentialLimitS4;
	private String preferentialLimitE1;
	private String preferentialLimitE2;
	private String preferentialLimitE3;
	private String preferentialLimitE4;
	//  ===========    限制优惠券使用的时间段配置     ====== end
	
	//互联接口id
	private Long huLianInterfaceId;
	//列表图标
	private String listImg;
	
	//运营单位id
	private Long operatingUnitId;

	private Long memberId;
	
	private OperatingUnitDto operatingUnitDto;
	
	// search
	private List<Long> stationIds;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}



	public StationEnum getStationEnums() {
		return stationEnums;
	}

	public void setStationEnums(StationEnum stationEnums) {
		this.stationEnums = stationEnums;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Integer getOpenTimeAm() {
		return openTimeAm;
	}

	public void setOpenTimeAm(Integer openTimeAm) {
		this.openTimeAm = openTimeAm;
	}

	public Integer getClosetimeAm() {
		return closetimeAm;
	}

	public void setClosetimeAm(Integer closetimeAm) {
		this.closetimeAm = closetimeAm;
	}


	public Integer getOpenTimePm() {
		return openTimePm;
	}

	public void setOpenTimePm(Integer openTimePm) {
		this.openTimePm = openTimePm;
	}

	public Integer getCloseTimePm() {
		return closeTimePm;
	}

	public void setCloseTimePm(Integer closeTimePm) {
		this.closeTimePm = closeTimePm;
	}

	public Long getAreaid() {
		return areaid;
	}

	public void setAreaid(Long areaid) {
		this.areaid = areaid;
	}


	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}


	public Integer getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(Integer vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(Integer monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public StationTypeEnum getStationTypeEnum() {
		return stationTypeEnum;
	}

	public void setStationTypeEnum(StationTypeEnum stationTypeEnum) {
		this.stationTypeEnum = stationTypeEnum;
	}

	public ConstructionEnum getConstructionEnum() {
		return constructionEnum;
	}

	public void setConstructionEnum(ConstructionEnum constructionEnum) {
		this.constructionEnum = constructionEnum;
	}

	public AreaDto getAreaDto() {
		return areaDto;
	}

	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}

	public OperatorDto getOperatorDto() {
		return operatorDto;
	}

	public void setOperatorDto(OperatorDto operatorDto) {
		this.operatorDto = operatorDto;
	}

	public String getAbbreviationName() {
		return abbreviationName;
	}

	public void setAbbreviationName(String abbreviationName) {
		this.abbreviationName = abbreviationName;
	}

	public Boolean getGoodsMachine() {
		return goodsMachine;
	}

	public void setGoodsMachine(Boolean goodsMachine) {
		this.goodsMachine = goodsMachine;
	}

	public StationFastSlowEnum getFastSlow() {
		return fastSlow;
	}

	public void setFastSlow(StationFastSlowEnum fastSlow) {
		this.fastSlow = fastSlow;
	}

	public PileOperateTypeEnum getRunType() {
		return runType;
	}

	public void setRunType(PileOperateTypeEnum runType) {
		this.runType = runType;
	}

	public Long getPutIntoMoney() {
		return putIntoMoney;
	}

	public void setPutIntoMoney(Long putIntoMoney) {
		this.putIntoMoney = putIntoMoney;
	}

	public Long getActualPutIntoMoney() {
		return actualPutIntoMoney;
	}

	public void setActualPutIntoMoney(Long actualPutIntoMoney) {
		this.actualPutIntoMoney = actualPutIntoMoney;
	}

	public Long getChargeTarget() {
		return chargeTarget;
	}

	public void setChargeTarget(Long chargeTarget) {
		this.chargeTarget = chargeTarget;
	}

	public Integer getPileTarget() {
		return pileTarget;
	}

	public void setPileTarget(Integer pileTarget) {
		this.pileTarget = pileTarget;
	}

	public Integer getPileRowNum() {
		return pileRowNum;
	}

	public void setPileRowNum(Integer pileRowNum) {
		this.pileRowNum = pileRowNum;
	}

	public Integer getPileNumOfRow() {
		return pileNumOfRow;
	}

	public void setPileNumOfRow(Integer pileNumOfRow) {
		this.pileNumOfRow = pileNumOfRow;
	}

	public StationConfigTypeEnum getConfigTypeEnum() {
		return configTypeEnum;
	}

	public void setConfigTypeEnum(StationConfigTypeEnum configTypeEnum) {
		this.configTypeEnum = configTypeEnum;
	}

	public List<List<StationRowConfigDto>> getConfigList() {
		return configList;
	}

	public void setConfigList(List<List<StationRowConfigDto>> configList) {
		this.configList = configList;
	}

	public String getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public String getPreferentialLimitS1() {
		return preferentialLimitS1;
	}

	public void setPreferentialLimitS1(String preferentialLimitS1) {
		this.preferentialLimitS1 = preferentialLimitS1;
	}

	public String getPreferentialLimitS2() {
		return preferentialLimitS2;
	}

	public void setPreferentialLimitS2(String preferentialLimitS2) {
		this.preferentialLimitS2 = preferentialLimitS2;
	}

	public String getPreferentialLimitS3() {
		return preferentialLimitS3;
	}

	public void setPreferentialLimitS3(String preferentialLimitS3) {
		this.preferentialLimitS3 = preferentialLimitS3;
	}

	public String getPreferentialLimitS4() {
		return preferentialLimitS4;
	}

	public void setPreferentialLimitS4(String preferentialLimitS4) {
		this.preferentialLimitS4 = preferentialLimitS4;
	}

	public String getPreferentialLimitE1() {
		return preferentialLimitE1;
	}

	public void setPreferentialLimitE1(String preferentialLimitE1) {
		this.preferentialLimitE1 = preferentialLimitE1;
	}

	public String getPreferentialLimitE2() {
		return preferentialLimitE2;
	}

	public void setPreferentialLimitE2(String preferentialLimitE2) {
		this.preferentialLimitE2 = preferentialLimitE2;
	}

	public String getPreferentialLimitE3() {
		return preferentialLimitE3;
	}

	public void setPreferentialLimitE3(String preferentialLimitE3) {
		this.preferentialLimitE3 = preferentialLimitE3;
	}

	public String getPreferentialLimitE4() {
		return preferentialLimitE4;
	}

	public void setPreferentialLimitE4(String preferentialLimitE4) {
		this.preferentialLimitE4 = preferentialLimitE4;
	}

	public Long getHuLianInterfaceId() {
		return huLianInterfaceId;
	}

	public void setHuLianInterfaceId(Long huLianInterfaceId) {
		this.huLianInterfaceId = huLianInterfaceId;
	}

	public String getListImg() {
		return listImg;
	}

	public void setListImg(String listImg) {
		this.listImg = listImg;
	}

	public Long getOperatingUnitId() {
		return operatingUnitId;
	}

	public void setOperatingUnitId(Long operatingUnitId) {
		this.operatingUnitId = operatingUnitId;
	}

	public OperatingUnitDto getOperatingUnitDto() {
		return operatingUnitDto;
	}

	public void setOperatingUnitDto(OperatingUnitDto operatingUnitDto) {
		this.operatingUnitDto = operatingUnitDto;
	}

	public boolean isIncludeSubArea() {
		return includeSubArea;
	}

	public void setIncludeSubArea(boolean includeSubArea) {
		this.includeSubArea = includeSubArea;
	}

	public List<Long> getStationIds() {
		return stationIds;
	}

	public void setStationIds(List<Long> stationIds) {
		this.stationIds = stationIds;
	}
	
}
