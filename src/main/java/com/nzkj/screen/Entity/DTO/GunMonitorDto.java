package com.nzkj.screen.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 枪的实时数据
 * 
 * @author NZ
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GunMonitorDto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 充电枪id
	 */
	private Long id;
	/**
	 * 充电枪号
	 */
	private String gunNo;
	/**
	 * 充电枪名称
	 */
	private String gunName;
	/**
	 * 充电桩ID
	 */
	private Long pileId;
	/**
	 * 互联互通充电枪ID
	 */
	public String connectorId;
	/**
	 * 充电桩编号
	 */
	private String pileNo;
	/**
	 * 充电方式 BillBusTypeEnum
	 */
	private Integer BillBusType;
	/**
	 * 电流类型 CurrentModeEnum
	 */
	private Integer currentMode;
	/**
	 * 充电模式 ChargingModeEnum3
	 */
	private Integer chargeMode;
	/**
	 * 枪真实状态
	 */
	private Integer realGunState;

	/**
	 * 充电枪的实时工作状态
	 */
	private Integer gunState;
	/**
	 * 充电枪锁定之前的实时工作状态
	 */
	private Integer beforeLockState;
	/**
	 * 充电枪状态变化前的上一次状态
	 */
	private Integer previousState;
	/**
	 * 是否告警标志
	 */
	private Boolean alrmFlag;
	/**
	 * 告警编号
	 */
	private List<Long> alarms;
	/**
	 * 充电时长(分钟)
	 */
	private Integer chargeTime;
	/**
	 * 剩余时间（分钟）
	 */
	private Integer remainTime;
	/**
	 * 电量总(度)
	 */
	private String TotalElect;
	/**
	 * 电量百分比
	 */
	private Integer soc;
	/**
	 * 充电电压(毫伏)
	 */
	private Integer voltage;
	/**
	 * 充电电流（毫安）
	 */
	private Integer current;
	/**
	 * 充电电能（瓦时）
	 */
	private Integer power;
	/**
	 * 充电金额（分）
	 */
	private Integer chargeAmount;
	/**
	 * 会员编号
	 */
	private String memberNo;
	/**
	 * 账户余额（分）
	 */
	private Integer balance;
	/**
	 * 充电中车车牌号
	 */
	private String plateno;
	/**
	 * 充电会员所属商家
	 */
	private Long memberSellerId;
	/**
	 * 车位锁实时工作状态 ParkingPlaceLockStateEnum
	 */
	private Integer ParkingPlaceLockState;
	/**
	 * 车位锁升降绑定会员编号
	 */
	private String lockMemberNo;
	/**
	 * vin码
	 */
	private String vin;
	/**
	 * 电表读数
	 */
	private Integer totalPower;
	/**
	 * 正极温度
	 */
	private String gunPositiveTemperature;
	/**
	 * 负极温度
	 */
	private String gunNegativeTemperature;
	/**
	 * 电池允许充电
	 */
	private Boolean batteryAllowCharge;
	/**
	 * 实时数据更新次数
	 */
	private Integer times=0;
	/**
	 * 充电开始时间
	 */
	private String startTime;
	/**
	 * 一键启动的备注
	 */
	private String oneKeyStartNote;
	/**
	 * 一键启动的标志
	 */
	private Boolean oneKeyStartFlag;
	/**
	 * 桩送上来的卡号
	 */
	private String card;
	/**
	 * 地锁状态枚举
	 */
	private Integer parkingLockStateEnum;
	/**
	 * 车辆自编号
	 */
	private String CarsSelfNumber;
	/**
	 * 充电车辆自编号
	 */
	private String memberName;
	/**
	 * 峰值电压
	 */
	private Integer peakVol;
	/**
	 * 谷值电压
	 */
	private Integer valleyVol;
	/**
	 * 峰值电流
	 */
	private Integer peakCurrent;
	/**
	 *谷值电流
	 */
	private Integer valleyCurrent;
	/**
	 * 盛宏充电桩实时数据标识
	 */
	private Long TSPTR;
	/**
	 * 桩温度
	 */
	private String pileTemperature;
}
