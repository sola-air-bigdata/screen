package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 枪的实时数据
 * 
 * @author NZ
 *
 */
public class GunMonitorDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 桩
	 */
	private PileDto pileDto;
	/**
	 * 枪号
	 */
	private String gunNo;
	/**
	 * 枪的实时工作状态
	 */
	private GunStateEnum gunState;
	/**
	 * 充电时长(分钟)
	 */
	private Integer chargeTime;
	/**
	 * 会员编号
	 */
	private String memberNo;
	/**
	 * 充电电压(毫伏)
	 */
	private Integer voltage;
	/**
	 * 充电电流（毫安）
	 */
	private Integer current;
	/**
	 * 充电金额（分）
	 */
	private Integer chargeAmount;
	/**
	 * 充电电能（瓦时）
	 */
	private Integer power;
	/**
	 * 剩余时间（分钟）
	 */
	private Integer remainTime;
	/**
	 * 电量百分比
	 */
	private Integer soc;
	/**
	 * 账户余额（分）
	 */
	private Integer balance;
	/**
	 * 充电类型
	 */
	private BillBusTypeEnum busType;
	/**
	 * 电流类型
	 */
	private CurrentModeEnum currentMode;
	/**
	 * 充电模式
	 */
	private ChargingModeEnum3 chargeMode;
	/**
	 * 车位锁实时工作状态
	 */
	private ParkingPlaceLockStateEnum ParkingPlaceLockState;
	/**
	 * 车位锁升降绑定会员编号
	 */
	private String lockMemberNo;
	
	/**
	 * 是否告警标志
	 */
	private Boolean alrmFlag;
	/**
	 * vin码
	 */
	private String vin;
	/**
	 * 电表读数
	 */
	private Integer totalPower;
	/**
	 * 桩编号
	 */
	private String pileNo;
	/**
	 * A相电压
	 */
	private String voltageA;
	/**
	 * B相电压
	 */
	private String voltageB;
	/**
	 * C相电压
	 */
	private String voltageC;
	/**
	 * A相电流
	 */
	private String currentA;
	/**
	 * B相电流
	 */
	private String currentB;
	/**
	 * C相电流
	 */
	private String currentC;
	/**
	 * 告警编号
	 */
	private List<Long> alarms;
	/**
	 * 正极温度
	 */
	private String gunPositiveTemperature;
	/**
	 * 负极温度
	 */
	private String gunNegativeTemperature;

	/**
	 * 实时数据更新次数
	 */
	private Integer times=0;
	/*单体动力蓄电池最高允许充电电压*/
	private String singleBatteryAllowChargeHighestVol;
	/*动力蓄电池标称总能量*/
	private String sumBatteryPower;
	/*整车动力蓄电池荷电状态(充电前SOC)%*/
	private Integer batterySOC;
	/*电池充电模式*/
	private BatteryChargeModelEnum batteryChargeModel;
	/*电池允许充电*/
	private Boolean batteryAllowCharge;
	/*最高允许充电总电压*/
	private String allowHighestVol;
	/*最高允许充电电流*/
	private String allowHighestCurrent;
	/*整车动力蓄电池当前电池电压*/
	private String carBatteryVol;
	/*电池单体最高电压*/
	private String singleBatteryHighestVol;
	/*电池单体最低电压*/
	private String singleBatteryLowestVol;
	/*电池单体最高温度*/
	private String singleBatteryHighestTemperature;
	/*电池单体最低温度*/
	private String singleBatteryLowestTemperature;
	//充电中车车牌号
	private String plateno;
	/*充电会员所属商家*/
	private Long memberSellerId;
	/*峰值电压*/
	private Integer peakVol;
	/*谷值电压*/
	private Integer valleyVol;
	/*峰值电流*/
	private Integer peakCurrent;
	/*谷值电流*/
	private Integer valleyCurrent;
	/*电池充电参数信息上行*/
	private BatChargeParam batChargeParam;
	/* 电池充电总信息上行*/
	private BatChargePoolinfo batChargePoolinfo;
	/*BMS 由于某种原因停止充电时，主动异步上送*/
	private BmsStopChargeReason bmsStopChargeReason;
	/*车辆基础信息上行（异步）*/
	private CarInfo carInfo;
	/*充电机基础信息上行（异步）*/
	private ChargeDevBaseInfo chargeDevBaseInfo;
	/*当充电机由于某种原因停止充电时，主动异步上送。*/
	private ChargeDevStopChargeReason chargeDevStopChargeReason;
	/*充电模块详细信息上行（异步）*/
	private List<ChargeModuleDeatileDInfo> chargeModuleDeatileDInfoS;
	/*充电模块总信息上行（异步）*/
	private ChargeModulePollInfo chargeModulePollInfo;
	/*电池温度信息上行（异步）*/
	private List<SingleBatTempInfo> singleBatTempInfoS;
	/*单体蓄电池电压信息上行（异步）*/
	private List<SingleBatVoInfo> singleBatVoInfoS;
	/*充电锁定之前的状态*/
	private GunStateEnum beforeLockState;
	/*Vin充电时 --车辆自编号*/
	private String carsSelfNumber ;
	/*充电开始时间*/
	private String startTime;
	/*充电会员名*/
	private String memberName;
	/*一键启动的备注*/
	private String oneKeyStartNote;
	/*一键启动的标志*/
	private boolean oneKeyStartFlag;
	/*枪真实状态*/
	private GunStateEnum realGunState;
	private String connectorID;
	
	//预约单id
	private Long appointmentId;
	//预约姓名
	private String appointmentName;
	//预约电话
	private String appointmentTel;
	//剩余时间
	private String appointmentTimeRemaining;
	//开始预约时间
	private Date appointmentStartTime;
	/*桩送上来的卡号*/
	private String card;
	/*枪名*/
	private String name;
	/*状态变化前的上一次状态*/
	private GunStateEnum previousState;
	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentName() {
		return appointmentName;
	}

	public void setAppointmentName(String appointmentName) {
		this.appointmentName = appointmentName;
	}

	public String getAppointmentTel() {
		return appointmentTel;
	}

	public void setAppointmentTel(String appointmentTel) {
		this.appointmentTel = appointmentTel;
	}

	public String getAppointmentTimeRemaining() {
		return appointmentTimeRemaining;
	}

	public void setAppointmentTimeRemaining(String appointmentTimeRemaining) {
		this.appointmentTimeRemaining = appointmentTimeRemaining;
	}

	public Date getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(Date appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GunStateEnum getRealGunState() {
		return realGunState;
	}

	public void setRealGunState(GunStateEnum realGunState) {
		this.realGunState = realGunState;
	}
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCarsSelfNumber() {
		return carsSelfNumber;
	}

	public void setCarsSelfNumber(String carsSelfNumber) {
		this.carsSelfNumber = carsSelfNumber;
	}

	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}

	public String getLockMemberNo() {
		return lockMemberNo;
	}

	public void setLockMemberNo(String lockMemberNo) {
		this.lockMemberNo = lockMemberNo;
	}

	public ParkingPlaceLockStateEnum getParkingPlaceLockState() {
		return ParkingPlaceLockState;
	}

	public void setParkingPlaceLockState(ParkingPlaceLockStateEnum parkingPlaceLockState) {
		ParkingPlaceLockState = parkingPlaceLockState;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public PileDto getPileDto() {
		return pileDto;
	}

	public void setPileDto(PileDto pileDto) {
		this.pileDto = pileDto;
	}

	public String getGunNo() {
		return gunNo;
	}

	public void setGunNo(String gunNo) {
		this.gunNo = gunNo;
	}

	public GunStateEnum getGunState() {
		return gunState;
	}

	public void setGunState(GunStateEnum gunState) {
		this.gunState = gunState;
	}

	public Integer getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(Integer chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Integer chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Integer remainTime) {
		this.remainTime = remainTime;
	}

	public Integer getSoc() {
		return soc;
	}

	public void setSoc(Integer soc) {
		this.soc = soc;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public CurrentModeEnum getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(CurrentModeEnum currentMode) {
		this.currentMode = currentMode;
	}

	public ChargingModeEnum3 getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(ChargingModeEnum3 chargeMode) {
		this.chargeMode = chargeMode;
	}

	public Boolean getAlrmFlag() {
		return alrmFlag;
	}

	public void setAlrmFlag(Boolean alrmFlag) {
		this.alrmFlag = alrmFlag;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(Integer totalPower) {
		this.totalPower = totalPower;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPileNo() {
		return pileNo;
	}

	public void setPileNo(String pileNo) {
		this.pileNo = pileNo;
	}

	public String getVoltageA() {
		return voltageA;
	}

	public void setVoltageA(String voltageA) {
		this.voltageA = voltageA;
	}

	public String getVoltageB() {
		return voltageB;
	}

	public void setVoltageB(String voltageB) {
		this.voltageB = voltageB;
	}

	public String getVoltageC() {
		return voltageC;
	}

	public void setVoltageC(String voltageC) {
		this.voltageC = voltageC;
	}

	public String getCurrentA() {
		return currentA;
	}

	public void setCurrentA(String currentA) {
		this.currentA = currentA;
	}

	public String getCurrentB() {
		return currentB;
	}

	public void setCurrentB(String currentB) {
		this.currentB = currentB;
	}

	public String getCurrentC() {
		return currentC;
	}

	public void setCurrentC(String currentC) {
		this.currentC = currentC;
	}

	public List<Long> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Long> alarms) {
		this.alarms = alarms;
	}

	public BillBusTypeEnum getBusType() {
		return busType;
	}

	public void setBusType(BillBusTypeEnum busType) {
		this.busType = busType;
	}

	public String getGunPositiveTemperature() {
		return gunPositiveTemperature;
	}

	public void setGunPositiveTemperature(String gunPositiveTemperature) {
		this.gunPositiveTemperature = gunPositiveTemperature;
	}

	public String getGunNegativeTemperature() {
		return gunNegativeTemperature;
	}

	public void setGunNegativeTemperature(String gunNegativeTemperature) {
		this.gunNegativeTemperature = gunNegativeTemperature;
	}

	public String getSingleBatteryAllowChargeHighestVol() {
		return singleBatteryAllowChargeHighestVol;
	}

	public void setSingleBatteryAllowChargeHighestVol(String singleBatteryAllowChargeHighestVol) {
		this.singleBatteryAllowChargeHighestVol = singleBatteryAllowChargeHighestVol;
	}

	public String getSumBatteryPower() {
		return sumBatteryPower;
	}

	public void setSumBatteryPower(String sumBatteryPower) {
		this.sumBatteryPower = sumBatteryPower;
	}

	public Integer getBatterySOC() {
		return batterySOC;
	}

	public void setBatterySOC(Integer batterySOC) {
		this.batterySOC = batterySOC;
	}

	public BatteryChargeModelEnum getBatteryChargeModel() {
		return batteryChargeModel;
	}

	public void setBatteryChargeModel(BatteryChargeModelEnum batteryChargeModel) {
		this.batteryChargeModel = batteryChargeModel;
	}

	public Boolean getBatteryAllowCharge() {
		return batteryAllowCharge;
	}

	public void setBatteryAllowCharge(Boolean batteryAllowCharge) {
		this.batteryAllowCharge = batteryAllowCharge;
	}

	public String getAllowHighestVol() {
		return allowHighestVol;
	}

	public void setAllowHighestVol(String allowHighestVol) {
		this.allowHighestVol = allowHighestVol;
	}

	public String getAllowHighestCurrent() {
		return allowHighestCurrent;
	}

	public void setAllowHighestCurrent(String allowHighestCurrent) {
		this.allowHighestCurrent = allowHighestCurrent;
	}

	public String getCarBatteryVol() {
		return carBatteryVol;
	}

	public void setCarBatteryVol(String carBatteryVol) {
		this.carBatteryVol = carBatteryVol;
	}

	public String getSingleBatteryHighestVol() {
		return singleBatteryHighestVol;
	}

	public void setSingleBatteryHighestVol(String singleBatteryHighestVol) {
		this.singleBatteryHighestVol = singleBatteryHighestVol;
	}

	public String getSingleBatteryLowestVol() {
		return singleBatteryLowestVol;
	}

	public void setSingleBatteryLowestVol(String singleBatteryLowestVol) {
		this.singleBatteryLowestVol = singleBatteryLowestVol;
	}

	public String getSingleBatteryHighestTemperature() {
		return singleBatteryHighestTemperature;
	}

	public void setSingleBatteryHighestTemperature(String singleBatteryHighestTemperature) {
		this.singleBatteryHighestTemperature = singleBatteryHighestTemperature;
	}

	public String getSingleBatteryLowestTemperature() {
		return singleBatteryLowestTemperature;
	}

	public void setSingleBatteryLowestTemperature(String singleBatteryLowestTemperature) {
		this.singleBatteryLowestTemperature = singleBatteryLowestTemperature;
	}

	public Long getMemberSellerId() {
		return memberSellerId;
	}

	public void setMemberSellerId(Long memberSellerId) {
		this.memberSellerId = memberSellerId;
	}

	public Integer getPeakVol() {
		return peakVol;
	}

	public void setPeakVol(Integer peakVol) {
		this.peakVol = peakVol;
	}

	public Integer getValleyVol() {
		return valleyVol;
	}

	public void setValleyVol(Integer valleyVol) {
		this.valleyVol = valleyVol;
	}

	public Integer getPeakCurrent() {
		return peakCurrent;
	}

	public void setPeakCurrent(Integer peakCurrent) {
		this.peakCurrent = peakCurrent;
	}

	public Integer getValleyCurrent() {
		return valleyCurrent;
	}

	public void setValleyCurrent(Integer valleyCurrent) {
		this.valleyCurrent = valleyCurrent;
	}

	public BatChargeParam getBatChargeParam() {
		return batChargeParam;
	}

	public void setBatChargeParam(BatChargeParam batChargeParam) {
		this.batChargeParam = batChargeParam;
	}

	public BatChargePoolinfo getBatChargePoolinfo() {
		return batChargePoolinfo;
	}

	public void setBatChargePoolinfo(BatChargePoolinfo batChargePoolinfo) {
		this.batChargePoolinfo = batChargePoolinfo;
	}

	public BmsStopChargeReason getBmsStopChargeReason() {
		return bmsStopChargeReason;
	}

	public void setBmsStopChargeReason(BmsStopChargeReason bmsStopChargeReason) {
		this.bmsStopChargeReason = bmsStopChargeReason;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public ChargeDevBaseInfo getChargeDevBaseInfo() {
		return chargeDevBaseInfo;
	}

	public void setChargeDevBaseInfo(ChargeDevBaseInfo chargeDevBaseInfo) {
		this.chargeDevBaseInfo = chargeDevBaseInfo;
	}

	public ChargeDevStopChargeReason getChargeDevStopChargeReason() {
		return chargeDevStopChargeReason;
	}

	public void setChargeDevStopChargeReason(ChargeDevStopChargeReason chargeDevStopChargeReason) {
		this.chargeDevStopChargeReason = chargeDevStopChargeReason;
	}

	public List<ChargeModuleDeatileDInfo> getChargeModuleDeatileDInfoS() {
		return chargeModuleDeatileDInfoS;
	}

	public void setChargeModuleDeatileDInfoS(List<ChargeModuleDeatileDInfo> chargeModuleDeatileDInfoS) {
		this.chargeModuleDeatileDInfoS = chargeModuleDeatileDInfoS;
	}

	public ChargeModulePollInfo getChargeModulePollInfo() {
		return chargeModulePollInfo;
	}

	public void setChargeModulePollInfo(ChargeModulePollInfo chargeModulePollInfo) {
		this.chargeModulePollInfo = chargeModulePollInfo;
	}


	public List<SingleBatTempInfo> getSingleBatTempInfoS() {
		return singleBatTempInfoS;
	}

	public void setSingleBatTempInfoS(List<SingleBatTempInfo> singleBatTempInfoS) {
		this.singleBatTempInfoS = singleBatTempInfoS;
	}

	public List<SingleBatVoInfo> getSingleBatVoInfoS() {
		return singleBatVoInfoS;
	}

	public void setSingleBatVoInfoS(List<SingleBatVoInfo> singleBatVoInfoS) {
		this.singleBatVoInfoS = singleBatVoInfoS;
	}

	public GunStateEnum getBeforeLockState() {
		return beforeLockState;
	}

	public void setBeforeLockState(GunStateEnum beforeLockState) {
		this.beforeLockState = beforeLockState;
	}

	public String getOneKeyStartNote() {
		return oneKeyStartNote;
	}

	public void setOneKeyStartNote(String oneKeyStartNote) {
		this.oneKeyStartNote = oneKeyStartNote;
	}

	public boolean getOneKeyStartFlag() {
		return oneKeyStartFlag;
	}

	public void setOneKeyStartFlag(boolean oneKeyStartFlag) {
		this.oneKeyStartFlag = oneKeyStartFlag;
	}

	public String getConnectorID() {
		return connectorID;
	}

	public void setConnectorID(String connectorID) {
		this.connectorID = connectorID;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GunStateEnum getPreviousState() {
		return previousState;
	}

	public void setPreviousState(GunStateEnum previousState) {
		this.previousState = previousState;
	}
}
