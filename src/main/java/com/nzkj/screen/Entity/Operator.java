package com.nzkj.screen.Entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商家
 * @author zhangtian
 */

@Entity(name = "t_business_base_operation_seller")
public class Operator {

	@Id
	@GeneratedValue
	//id
	private long id;


	/**
	 * 商家名称
	 */
	@Column(name = "v_name")
	private String name;
	/**
	 * 商家属性,参照码表
	 */
	@Column(name = "i_property")
	private Integer property;
	/**
	 * 卡类型
	 */
	@Column(name = "s_card_charge_type")
	private Short cardChargeType;
	/**
	 * 启用 跟 停用 状态
	 */
	@Column(name = "s_status", nullable = true, length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status;

	/**
	 * 注册地址
	 */
	@Column(name = "v_regist_address")
	private String registAddress;
	/**
	 * 官网网址
	 */
	@Column(name = "v_web_address")
	private String webAddress;
	/**
	 * 电话号码
	 */
	@Column(name = "v_telephone")
	private String telephone;
	/**
	 * 联系人1
	 */
	@Column(name = "v_contact1")
	private String contact1;
	/**
	 * 联系人2
	 */
	@Column(name = "v_contact2")
	private String contact2;
	/**
	 * 联系人1电话
	 */
	@Column(name = "v_contact1_phone")
	private String contact1Phone;
	/**
	 * 联系人2电话
	 */
	@Column(name = "v_contact2_phone")
	private String contact2Phone;
	/**
	 * 备注说明
	 */
	@Column(name = "v_comment")
	private String comment;

	/**
	 * 是否资源共享
	 */
	@Column(name = "b_share_resource", nullable = true, length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean shareResource;

	/**
	 * 合同有效期开始
	 */
	@Column(name = "d_effective_time_s", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveTimeS;
	/**
	 * 合同有效期截止
	 */
	@Column(name = "d_effective_time_e", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveTimeE;
	/**
	 * 商家编号
	 */
	@Column(name = "v_operatorno")
	private String operatorNo;
	/**
	 * 佣金百分比
	 */
	@Column(name = "m_commision_scale", columnDefinition = "decimal(6,2)")
	private BigDecimal commisionScale;

	/**
	 * 周期类型详见码表
	 */
	@Column(name = "i_period")
	private Integer period;

	/**
	 * 结算号数
	 */
	@Column(name = "s_balance_date")
	private Short balanceDate;


	@OneToMany(mappedBy = "operator")
	@Where(clause="b_delete_flag=0")
	private List<Member> lstMember;
	
	
	/**
	 * 登陆密码
	 */
	@Column(name = "v_passwd")
	private String passwd;
	
	
	@Column(name = "v_img")
	private String img;
	
	
	@Column(name = "v_colour")
	private String colour;
	
	@Column(name = "v_licnum")
	private String licnum;
	
	@Column(name = "v_lpcard")
	private String lpcard;
	/**
	 * 商家applogo
	 */
	@Column(name = "v_app_img")
    private String appImg;
	
	
	//银卡会员>=
	@Column(name = "i_silver_gteq")
    private Integer  silverGteq;
    //银卡会员<
	@Column(name = "i_silver_lt")
    private Integer  silverLt;
    //金卡会员>=
	@Column(name = "i_gold_gteq")
    private Integer  goldGteq;
    //金卡会员<
	@Column(name = "i_gold_lt")
    private Integer  goldLt;
    //白金卡会员>=
	@Column(name = "i_pt_gteq")
    private Integer  ptGteq;
    //白金卡会员<
	@Column(name = "i_pt_lt")
    private Integer  ptLt;
	//钻石卡会员>=
	@Column(name = "i_diamond_gteq")
	private Integer diamondGteq;
	//钻石卡会员<
	@Column(name = "i_diamond_lt")
	private Integer diamondLt;
	
	//黑金卡会员>=
	@Column(name = "i_black_gold_gteq")
	private Integer blackGoldGteq;
	
	//公众号
	@Column(name = "v_commonnumber")
	private String commonNumber;
	
	//公众号appId
	@Column(name = "v_commonnumber_appid")
	private String commonNumberAppId;
	
	//钻石服务包ID
	@Column(name = "i_diamonds_pkg_id")
	private Long	diamondsPkgId;
	
	//白金服务包ID
	@Column(name = "i_pt_pkg_id")
	private Long	ptPkgId;
	
	//金卡服务包ID
	@Column(name = "i_gold_pkg_id")
	private Long	goldPkgId;
	
	//银卡服务包ID
	@Column(name = "i_silver_pkg_id")
	private Long  silverPkgId;
	
	//黑金服务包ID
	@Column(name = "l_black_gold_pkg_id")
	private Long  blackGoldPkgId;
	
	//桩限制数量
	@Column(name = "i_limit_number")
	private Integer limitNumber=60;
	
	//桩的限制量
	@Column(name = "i_limit_gun")
	private Integer limitGun;
	
	//是否默认支付配置
	@Column(name = "b_payment_config", nullable = true, length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean paymentConfig=true;
	
	@Column(name = "i_sms_id")
	private Long smsId;

	//公众号热线
	@Column(name = "v_hotline")
	private String hotline;
	
	//公众号热线2
	@Column(name = "v_hotline2")
	private String hotline2;
	
	
	public String getHotline2() {
		return hotline2;
	}

	public void setHotline2(String hotline2) {
		this.hotline2 = hotline2;
	}

	//公众号强制绑定
	@Column(name = "b_public_number_force_bind", nullable = true, length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean publicNumberForcBind=true;
	
	/**
	 * 商家简称
	 */
	@Column(name = "v_abbreviation_name")
	private String abbreviationName;
	
	//是否清除会员的消费积分
	@Column(name = "b_clear_consume_integral")
	private Boolean clearConsumeIntegral;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Short getCardChargeType() {
		return cardChargeType;
	}

	public void setCardChargeType(Short cardChargeType) {
		this.cardChargeType = cardChargeType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getRegistAddress() {
		return registAddress;
	}

	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getContact1Phone() {
		return contact1Phone;
	}

	public void setContact1Phone(String contact1Phone) {
		this.contact1Phone = contact1Phone;
	}

	public String getContact2Phone() {
		return contact2Phone;
	}

	public void setContact2Phone(String contact2Phone) {
		this.contact2Phone = contact2Phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getShareResource() {
		return shareResource;
	}

	public void setShareResource(Boolean shareResource) {
		this.shareResource = shareResource;
	}

	public Date getEffectiveTimeS() {
		return effectiveTimeS;
	}

	public void setEffectiveTimeS(Date effectiveTimeS) {
		this.effectiveTimeS = effectiveTimeS;
	}

	public Date getEffectiveTimeE() {
		return effectiveTimeE;
	}

	public void setEffectiveTimeE(Date effectiveTimeE) {
		this.effectiveTimeE = effectiveTimeE;
	}



	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public BigDecimal getCommisionScale() {
		return commisionScale;
	}

	public void setCommisionScale(BigDecimal commisionScale) {
		this.commisionScale = commisionScale;
	}
	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Short getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Short balanceDate) {
		this.balanceDate = balanceDate;
	}
	
	public List<Member> getLstMember() {
		return lstMember;
	}

	public void setLstMember(List<Member> lstMember) {
		this.lstMember = lstMember;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getLicnum() {
		return licnum;
	}

	public void setLicnum(String licnum) {
		this.licnum = licnum;
	}

	public String getLpcard() {
		return lpcard;
	}

	public void setLpcard(String lpcard) {
		this.lpcard = lpcard;
	}

	public String getAppImg() {
		return appImg;
	}

	public void setAppImg(String appImg) {
		this.appImg = appImg;
	}

	public Integer getSilverGteq() {
		return silverGteq;
	}

	public void setSilverGteq(Integer silverGteq) {
		this.silverGteq = silverGteq;
	}

	public Integer getSilverLt() {
		return silverLt;
	}

	public void setSilverLt(Integer silverLt) {
		this.silverLt = silverLt;
	}

	public Integer getGoldGteq() {
		return goldGteq;
	}

	public void setGoldGteq(Integer goldGteq) {
		this.goldGteq = goldGteq;
	}

	public Integer getGoldLt() {
		return goldLt;
	}

	public void setGoldLt(Integer goldLt) {
		this.goldLt = goldLt;
	}

	public Integer getPtGteq() {
		return ptGteq;
	}

	public void setPtGteq(Integer ptGteq) {
		this.ptGteq = ptGteq;
	}

	public Integer getPtLt() {
		return ptLt;
	}

	public void setPtLt(Integer ptLt) {
		this.ptLt = ptLt;
	}

	public Integer getDiamondGteq() {
		return diamondGteq;
	}

	public void setDiamondGteq(Integer diamondGteq) {
		this.diamondGteq = diamondGteq;
	}

	public String getCommonNumber() {
		return commonNumber;
	}

	public void setCommonNumber(String commonNumber) {
		this.commonNumber = commonNumber;
	}

	public String getCommonNumberAppId() {
		return commonNumberAppId;
	}

	public void setCommonNumberAppId(String commonNumberAppId) {
		this.commonNumberAppId = commonNumberAppId;
	}

	public Long getDiamondsPkgId() {
		return diamondsPkgId;
	}

	public void setDiamondsPkgId(Long diamondsPkgId) {
		this.diamondsPkgId = diamondsPkgId;
	}

	public Long getPtPkgId() {
		return ptPkgId;
	}

	public void setPtPkgId(Long ptPkgId) {
		this.ptPkgId = ptPkgId;
	}

	public Long getGoldPkgId() {
		return goldPkgId;
	}

	public void setGoldPkgId(Long goldPkgId) {
		this.goldPkgId = goldPkgId;
	}

	public Long getSilverPkgId() {
		return silverPkgId;
	}

	public void setSilverPkgId(Long silverPkgId) {
		this.silverPkgId = silverPkgId;
	}

	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Boolean getPaymentConfig() {
		return paymentConfig;
	}

	public void setPaymentConfig(Boolean paymentConfig) {
		this.paymentConfig = paymentConfig;
	}

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public Boolean getPublicNumberForcBind() {
		return publicNumberForcBind;
	}

	public void setPublicNumberForcBind(Boolean publicNumberForcBind) {
		this.publicNumberForcBind = publicNumberForcBind;
	}

	public String getAbbreviationName() {
		return abbreviationName;
	}

	public void setAbbreviationName(String abbreviationName) {
		this.abbreviationName = abbreviationName;
	}

	public Integer getDiamondLt() {
		return diamondLt;
	}

	public void setDiamondLt(Integer diamondLt) {
		this.diamondLt = diamondLt;
	}

	public Integer getBlackGoldGteq() {
		return blackGoldGteq;
	}

	public void setBlackGoldGteq(Integer blackGoldGteq) {
		this.blackGoldGteq = blackGoldGteq;
	}

	public Long getBlackGoldPkgId() {
		return blackGoldPkgId;
	}

	public void setBlackGoldPkgId(Long blackGoldPkgId) {
		this.blackGoldPkgId = blackGoldPkgId;
	}

	public Boolean getClearConsumeIntegral() {
		return clearConsumeIntegral;
	}

	public void setClearConsumeIntegral(Boolean clearConsumeIntegral) {
		this.clearConsumeIntegral = clearConsumeIntegral;
	}

	public Integer getLimitGun() {
		return limitGun;
	}

	public void setLimitGun(Integer limitGun) {
		this.limitGun = limitGun;
	}
	
}
