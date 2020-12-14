package com.nzkj.screen.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * 充电桩
 * 
 * @author zhangtian
 */

@Entity(name = "t_business_base_operation_pile")
public class Pile {

	@Id
	@GeneratedValue
	//id
	private long id;


	/**
	 * 桩名称
	 */
	@Column(name = "v_name", length = 50)
	private String name;
	/**
	 * 桩体号
	 */
	@Column(name = "v_point_num")
	private String pointNum;

	/**
	 * 创建人
	 */
	@Column(name = "v_handle")
	private String handle;
	/**
	 * 是否是离散桩
	 */
	@Column(name = "b_bulk", nullable = true, length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean bulk;
	/**
	 * 省份
	 */
	@Column(name = "v_province")
	private String province;
	/**
	 * 城市
	 */
	@Column(name = "v_city")
	private String city;
	/**
	 * 地区
	 */
	@Column(name = "v_area")
	private String area;
	/**
	 * 街道
	 */
	@Column(name = "v_street")
	private String street;
	/**
	 * 协议类型
	 */
	@Column(name = "s_protocol_type")
	private Short protocolType;

	/**
	 * 详细地址
	 */
	@Column(name = "v_location")
	private String location;
	/**
	 * 经度
	 */
	@Column(name = "v_longitude")
	private String longitude;
	/**
	 * 纬度
	 */
	@Column(name = "v_latitude")
	private String latitude;

	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "l_station_id")
	private Station station;

	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "l_type_id")
	private PileType pileType;

	@ManyToOne
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "l_seller_id")
	private Operator operator;
	
	/**
	 *固定资产原价
	 */
	@Column(name = "i_cost_price")
	private Integer costPrice;
	/**
	 * 月租金
	 */
	@Column(name = "i_monthly_rent")
	private Integer monthlyRent; 
	
	/**
	 * 折旧率
	 */
	@Column(name = "i_month_depreciation")
	private Integer monthDepreciation;
	
	
	
	/**
	 *  预计使用寿命（年）
	 */
	@Column(name = "i_life")
	private Integer life;
	
	/**
	 * 净值
	 */
	@Column(name = "i_net_worth")
	private Integer netWorth;
	/**
	 * 预计净残值率
	 */
	@Column(name = "i_residual_value")
	private Integer residualValue;
	
	/**
	 * 月折旧额
	 */
	@Column(name = "i_depreciation_amount")
	private Integer depreciationAmount;
	
	
	@Column(name = "i_fixed_count_depreciation")
	private Integer fixedCountDepreciation=0; // 累计折旧
	
	// 设备所属方ID
	@Column(name = "v_equipmentownerid")
    private String equipmentownerid;
	
	// 设备生产商ID
	@Column(name = "v_manufacturerid")
	private String manufacturerid;
	
	//运营类型
	@Column(name = "l_operate_type")
	private String operateType;
	//是否缺省合约
	@Column(name = "i_default_Bill_Ru")
	private Integer defaultBillRu;
	
	//关闭开始时间1
	@Column(name = "d_close_start_one")
	private Integer closeStartOne;
	
	//关闭开始时间2
	@Column(name = "d_close_start_two")
	private Integer closeStartTwo;
		
	//关闭开始时间3
	@Column(name = "d_close_start_three")
	private Integer closeStartThree;
		
	//关闭开始时间4
	@Column(name = "d_close_start_four")
	private Integer closeStartFour;
		
	//关闭开始时间5
	@Column(name = "d_close_start_five")
	private Integer closeStartFive;
		
	//关闭开始时间6
	@Column(name = "d_close_start_six")
	private Integer closeStartSix;
	
	//关闭结束时间1
	@Column(name = "d_close_finish_one")
	private Integer closeFinishOne;
	
	//关闭结束时间2
	@Column(name = "d_close_finish_two")
	private Integer closeFinishTwo;
		
	//关闭结束时间3
	@Column(name = "d_close_finish_three")
	private Integer closeFinishThree;
		
	//关闭结束时间4
	@Column(name = "d_close_finish_four")
	private Integer closeFinishFour;
		
	//关闭结束时间5
	@Column(name = "d_close_finish_five")
	private Integer closeFinishFive;
		
	//关闭结束时间6
	@Column(name = "d_close_finish_six")
	private Integer closeFinishSix;
	
	//互联接口id
	@Column(name = "l_h_interface_id")
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

	public Short getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(Short protocolType) {
		this.protocolType = protocolType;
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

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public PileType getPileType() {
		return pileType;
	}

	public void setPileType(PileType pileType) {
		this.pileType = pileType;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
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

	@Transient
	public Long operatorId ;
	@Transient
	public Long pileTypeId ;
	@Transient
	public Long stationId ;
	@Transient
	public Integer gunCount;
	
	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Long getPileTypeId() {
		return pileTypeId;
	}

	public void setPileTypeId(Long pileTypeId) {
		this.pileTypeId = pileTypeId;
	}

	public Integer getGunCount() {
		return gunCount;
	}

	public void setGunCount(Integer gunCount) {
		this.gunCount = gunCount;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Pile() {
		super();
	}
	
	public Pile(Long id, String pointNum, Long stationId, Integer gunCount) {
		super();
		this.id = id;
		this.pointNum = pointNum;
		this.stationId = stationId;
		this.gunCount = gunCount;
	}
	
	public Pile(String name, String pointNum, String handle, Boolean bulk, String province, String city, String street,
                Integer closeStartOne, Integer closeStartTwo, Integer closeStartThree, Integer closeStartFour, Integer closeStartFive, Integer closeStartSix, Integer closeFinishOne, Integer closeFinishTwo, Integer closeFinishThree , Integer closeFinishFour, Integer closeFinishFive, Integer closeFinishSix,
                Short protocolType, String location, String longitude, String latitude, Integer costPrice,
                Integer monthlyRent, Integer monthDepreciation, Integer life, Integer netWorth, Integer residualValue,
                Integer depreciationAmount, Integer fixedCountDepreciation, String equipmentownerid, String manufacturerid,
                String operateType, Long operatorId, Long pileTypeId, Long stationId, Long id, Date addTime, Date updateTime,
                Boolean deleteFlag, String area, Integer defaultBillRu, Long huLianInterfaceId) {
		super();
		this.name = name;
		this.pointNum = pointNum;
		this.handle = handle;
		this.bulk = bulk;
		this.province = province;
		this.city = city;
		this.street = street;
		this.closeStartOne = closeStartOne;
		this.closeStartTwo = closeStartTwo;
		this.closeStartThree = closeStartThree;
		this.closeStartFour = closeStartFour;
		this.closeStartFive = closeStartFive;
		this.closeStartSix = closeStartSix;
		this.closeFinishOne = closeFinishOne;
		this.closeFinishTwo = closeFinishTwo;
		this.closeFinishThree = closeFinishThree;
		this.closeFinishFour = closeFinishFour;
		this.closeFinishFive = closeFinishFive;
		this.closeFinishSix = closeFinishSix;
		this.protocolType = protocolType;
		this.location = location;
		this.longitude = longitude;
		this.latitude = latitude;
		this.costPrice = costPrice;
		this.monthlyRent = monthlyRent;
		this.monthDepreciation = monthDepreciation;
		this.life = life;
		this.netWorth = netWorth;
		this.residualValue = residualValue;
		this.depreciationAmount = depreciationAmount;
		this.fixedCountDepreciation = fixedCountDepreciation;
		this.equipmentownerid = equipmentownerid;
		this.manufacturerid = manufacturerid;
		this.operateType = operateType;
		this.operatorId = operatorId;
		this.pileTypeId = pileTypeId;
		this.stationId = stationId;
		this.id = id;
		this.area = area;
		this.defaultBillRu = defaultBillRu;
		this.huLianInterfaceId = huLianInterfaceId;
	}


}
