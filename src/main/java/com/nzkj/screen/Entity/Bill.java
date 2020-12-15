package com.nzkj.screen.Entity;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 账单表
 * @author zengfeng
 */

@TableName("t_bill")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Bill extends Entity {
	private static final long serialVersionUID = 1L;

	/**
	 * 枪表id
	 */
	private Long gunId;
	/**
	 * 车辆id
	 */
	private Long vehicleId;
	/**
	 * 流水
	 */
	private String busId;

	/**
	 * 业务类型
	 */
	private Integer busType;

	/**
	 * 会员id
	 */
	private Long memberId;

	/**
	 * 车辆所属会员id
	 */
	private Long carMemberId;

	/**
	 * 卡号
	 */
	private String cardNo;

	/**
	 * 开始时间
	 */

	private Date timeS;

	/**
	 * 结束时间
	 */

	private Date timeE;

	/**
	 * 总电量
	 */
	private Long sumPower;

	/**
	 * 总起始值
	 */
	private Integer sumS;
	/**
	 * 总止示值
	 */
	private Integer sumE;

	/**
	 * vin
	 */
	private String vin;

	/**
	 * 设备id
	 */
	private Long pileId;

	/**
	 * 账单金额(分)
	 */
	private Long busBalance;

	/**
	 * 是否完成
	 */
	private Boolean finish;

	/**
	 * 剩余时间
	 */
	private Integer timeLeft;



	/**
	 * 服务金额(分)
	 */
	private Long serviceBalance;

	/**
	 * 商家id
	 */
	private Long sellerId;
	/**
	 * 会员类型
	 */
	private Short  memberType;

	/**
	 * 折扣金额(分)
	 */
	private Long discountBalance;
	/**
	 * 折扣(‰)
	 */
	private Integer discount;

	/**
	 * 桩体号
	 */
	private String pileNo;

	/**
	 * 会员编号(手机号码)
	 */
	private String telephone;

	/**
	 * 实收金额(分)
	 */
	private Long actualBalance;

	/**
	 * 电费(分)
	 */
	private Long powerBalance;



	private Integer limitValue;

	/**
	 * 奖励金抵扣金额(分)
	 */
	private Long deductibleBalance;

	/**
	 * 是否免单
	 */
    private Boolean feeSingle=false;


	//团队子成员id
	private Long leaguerId;

	//团队子成员手机号
	private String leaguerPhone;

	//车牌号
	private String plateno;


	//是否合约价
	private Boolean contract;


	//描述
	private String billNote;

	//集团合约
	private Long groupContractId;

	//开始soc
	private Integer beginSoc;

	//结束soc
	private Integer endSoc;


	/**
	 * 充电停止原因
	 */
	private String endReason;

	//司机编号（曹操专车）
	private String driverNo;


	//是否免整单服务费
	protected Boolean feeService = Boolean.valueOf(false);
	//第三方订单号
	private String thirdPartyOrderNum;

	//电费券抵扣的金额（分）
	private Integer powerCoupon;

	//服务券抵扣的金额（分）
	private Integer serviceCoupon;

	//现金券抵扣的金额（分）
	private Integer cashCoupon;

	//充电平台类型(1.捷电通,2.小桔平台)
	private Integer platformType = 4;

	// 车队会员充电扣费模式
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String deductMode;

	//平台Id
	private Long platformId;

	//预约id
	private Long appointId;
	//添加时间
//	private Date addTime;

//	//充电模式
//	private Integer chargeingMode;

//	//是否免单
//	private Boolean feesingle;

	private String number;
//	1,"自动模式" 2,"金额模式" , 3,"时间模式" 4,"电量模式"
	private Integer chargingMode;
	// 是否发送1 已经发送， 2 未发送
//	private Integer send;
//	MianDan("1","免单"),DiKou("2","抵扣"),ZheKou("3","折扣"),MianFuWuFei("4","免服务费"), WuYouHui("5", "无优惠"), XuNiDianKa("6", "虚拟电卡");
	public Integer getPreferentialType() {
		if(this.getDeductibleBalance() != null) {
			if(this.getDeductibleBalance() > 0) {
				return  3;
			}
		}

		if(this.getDiscountBalance() != null) {
			if(this.getDiscountBalance() > 0) {
				return  2;
			}
		}

		if(this.getFeeService() != null) {
			if(this.getFeeService()) {
				return 4;
			}
		}

		if(this.getFeeSingle() != null) {
			if (this.getFeeSingle()) {
				return 1;
			}
		}

		return null;
	}


	/**
	 * 获取实付服务费
	 * @return
	 */
	public Long getActualServiceBalance() {
		if (this.feeSingle != null && this.feeSingle) {
			return 0L;
		}
		if (this.feeService != null && this.feeService) {
			return 0L;
		}
		Long sb = this.serviceBalance == null ? 0L : this.serviceBalance;
		Long ab = this.discountBalance == null ? 0L: this.discountBalance;
		Long sc = this.serviceCoupon == null ? 0L : this.serviceCoupon;
		Long cc = this.cashCoupon == null ? 0L : this.cashCoupon;
		Long db = this.deductibleBalance == null ? 0L : this.deductibleBalance;
		Long pb = this.powerBalance == null ? 0L : this.powerBalance;
		if (cc >= pb) {
			return sb - ab - db - sc - (cc - pb);
		}else {
			return sb - ab - db - sc;
		}

	}


	public Long getActualPowerBalance() {
		if (actualBalance == null || getActualServiceBalance() == null) {
			return 0L;
		}
		return actualBalance - getActualServiceBalance();
	}
}
