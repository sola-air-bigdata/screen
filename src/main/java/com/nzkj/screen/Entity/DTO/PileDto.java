package com.nzkj.screen.Entity.DTO;

import java.util.List;


/**
 * 充电桩
 * @author zhangtian
 */
public class PileDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 桩名称
	 */
	private String name;
	/**
	 * 桩体号
	 */
	private String pointNum;

	/**
	 * 创建人
	 */
	private String handle;
	/**
	 * 是否是离散桩
	 */
	private Boolean bulk;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 地区
	 */
	private String area;
	/**
	 * 街道
	 */
	private String street;
	/**
	 * 协议类型
	 */
	private ProtocolEnum protocolEnum;

	/**
	 * 详细地址
	 */
	private String location;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
    /**
     * 站点id
     */
	private Long stationId;
	
	private StationDto stationDto;
    /**
     * 桩类型id
     */
	private Long pileTypeId;
	
	private PileTypeDto pileTypeDto;

	private OperatorDto operatorDto;
	

	private Boolean deleteFlag;
	
	
    private	List<GunDto> GunList;
    
    private Long userId;
    
    /**
     * 成本价
     */
    private Integer costPrice;
    
    /**
     * 月租金
     */
    private Integer monthlyRent; 
    /**
     *  折旧率
     */
    private Integer monthDepreciation;
    
    /**
	 *  预计使用寿命（年）
	 */
    private Integer life;
    
    /**
	 * 净值
	 */
    private Integer netWorth;
    
    /**
	 * 预计净残值率
	 */
	private Integer residualValue;

	/**
	 * 月折旧额
	 */
	private Integer depreciationAmount;
	
	private Integer fixedCountDepreciation=0; // 累计折旧
	
    private String equipmentownerid;// 设备所属方ID
	
	private String manufacturerid;// 设备生产商ID
	
	private PileOperateTypeEnum pileOperateTypeEnum;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 是否缺省合约      1:是,其他:不是  ,
	 */
	private Integer defaultBillRu;
	
	//关闭开始时间1
	private Integer closeStartOne;
	
	//关闭开始时间2
	private Integer closeStartTwo;
		
	//关闭开始时间3
	private Integer closeStartThree;
		
	//关闭开始时间4
	private Integer closeStartFour;
		
	//关闭开始时间5
	private Integer closeStartFive;
		
	//关闭开始时间6
	private Integer closeStartSix;
	
	//关闭结束时间1
	private Integer closeFinishOne;
	
	//关闭结束时间2
	private Integer closeFinishTwo;
		
	//关闭结束时间3
	private Integer closeFinishThree;
		
	//关闭结束时间4
	private Integer closeFinishFour;
		
	//关闭结束时间5
	private Integer closeFinishFive;
		
	//关闭结束时间6
	private Integer closeFinishSix;
	
	//互联接口id
	private Long huLianInterfaceId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPointNum() {
		return pointNum;
	}

	public void setPointNum(String pointNum) {
		this.pointNum = pointNum;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Boolean getBulk() {
		return bulk;
	}

	public void setBulk(Boolean bulk) {
		this.bulk = bulk;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public ProtocolEnum getProtocolEnum() {
		return protocolEnum;
	}

	public void setProtocolEnum(ProtocolEnum protocolEnum) {
		this.protocolEnum = protocolEnum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public StationDto getStationDto() {
		return stationDto;
	}

	public void setStationDto(StationDto stationDto) {
		this.stationDto = stationDto;
	}

	public Long getPileTypeId() {
		return pileTypeId;
	}

	public void setPileTypeId(Long pileTypeId) {
		this.pileTypeId = pileTypeId;
	}

	public PileTypeDto getPileTypeDto() {
		return pileTypeDto;
	}

	public void setPileTypeDto(PileTypeDto pileTypeDto) {
		this.pileTypeDto = pileTypeDto;
	}

	public OperatorDto getOperatorDto() {
		return operatorDto;
	}

	public void setOperatorDto(OperatorDto operatorDto) {
		this.operatorDto = operatorDto;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<GunDto> getGunList() {
		return GunList;
	}

	public void setGunList(List<GunDto> gunList) {
		GunList = gunList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(Integer monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public Integer getMonthDepreciation() {
		return monthDepreciation;
	}

	public void setMonthDepreciation(Integer monthDepreciation) {
		this.monthDepreciation = monthDepreciation;
	}

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public Integer getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(Integer netWorth) {
		this.netWorth = netWorth;
	}

	public Integer getResidualValue() {
		return residualValue;
	}

	public void setResidualValue(Integer residualValue) {
		this.residualValue = residualValue;
	}

	public Integer getDepreciationAmount() {
		return depreciationAmount;
	}

	public void setDepreciationAmount(Integer depreciationAmount) {
		this.depreciationAmount = depreciationAmount;
	}

	public Integer getFixedCountDepreciation() {
		return fixedCountDepreciation;
	}

	public void setFixedCountDepreciation(Integer fixedCountDepreciation) {
		this.fixedCountDepreciation = fixedCountDepreciation;
	}

	public String getEquipmentownerid() {
		return equipmentownerid;
	}

	public void setEquipmentownerid(String equipmentownerid) {
		this.equipmentownerid = equipmentownerid;
	}

	public String getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(String manufacturerid) {
		this.manufacturerid = manufacturerid;
	}

	public PileOperateTypeEnum getPileOperateTypeEnum() {
		return pileOperateTypeEnum;
	}

	public void setPileOperateTypeEnum(PileOperateTypeEnum pileOperateTypeEnum) {
		this.pileOperateTypeEnum = pileOperateTypeEnum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getDefaultBillRu() {
		return defaultBillRu;
	}

	public void setDefaultBillRu(Integer defaultBillRu) {
		this.defaultBillRu = defaultBillRu;
	}

	public Integer getCloseStartOne() {
		return closeStartOne;
	}

	public void setCloseStartOne(Integer closeStartOne) {
		this.closeStartOne = closeStartOne;
	}

	public Integer getCloseStartTwo() {
		return closeStartTwo;
	}

	public void setCloseStartTwo(Integer closeStartTwo) {
		this.closeStartTwo = closeStartTwo;
	}

	public Integer getCloseStartThree() {
		return closeStartThree;
	}

	public void setCloseStartThree(Integer closeStartThree) {
		this.closeStartThree = closeStartThree;
	}

	public Integer getCloseStartFour() {
		return closeStartFour;
	}

	public void setCloseStartFour(Integer closeStartFour) {
		this.closeStartFour = closeStartFour;
	}

	public Integer getCloseStartFive() {
		return closeStartFive;
	}

	public void setCloseStartFive(Integer closeStartFive) {
		this.closeStartFive = closeStartFive;
	}

	public Integer getCloseStartSix() {
		return closeStartSix;
	}

	public void setCloseStartSix(Integer closeStartSix) {
		this.closeStartSix = closeStartSix;
	}

	public Integer getCloseFinishOne() {
		return closeFinishOne;
	}

	public void setCloseFinishOne(Integer closeFinishOne) {
		this.closeFinishOne = closeFinishOne;
	}

	public Integer getCloseFinishTwo() {
		return closeFinishTwo;
	}

	public void setCloseFinishTwo(Integer closeFinishTwo) {
		this.closeFinishTwo = closeFinishTwo;
	}

	public Integer getCloseFinishThree() {
		return closeFinishThree;
	}

	public void setCloseFinishThree(Integer closeFinishThree) {
		this.closeFinishThree = closeFinishThree;
	}

	public Integer getCloseFinishFour() {
		return closeFinishFour;
	}

	public void setCloseFinishFour(Integer closeFinishFour) {
		this.closeFinishFour = closeFinishFour;
	}

	public Integer getCloseFinishFive() {
		return closeFinishFive;
	}

	public void setCloseFinishFive(Integer closeFinishFive) {
		this.closeFinishFive = closeFinishFive;
	}

	public Integer getCloseFinishSix() {
		return closeFinishSix;
	}

	public void setCloseFinishSix(Integer closeFinishSix) {
		this.closeFinishSix = closeFinishSix;
	}

	public Long getHuLianInterfaceId() {
		return huLianInterfaceId;
	}

	public void setHuLianInterfaceId(Long huLianInterfaceId) {
		this.huLianInterfaceId = huLianInterfaceId;
	}


	

}
