package com.nzkj.screen.Entity.DTO;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 商家
 * @author zhangtian
 */
public class OperatorDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	/**
	 * 商家名称
	 */
	private String name;
	/**
	 * 商家属性,参照码表
	 */
	private OperatorProEnum operatorProEnum;
	/**
	 * 卡类型
	 */
	private CardEnum cardEnum;
	/**
	 * 启用 跟 停用 状态
	 */

	private Boolean status;

	/**
	 * 注册地址
	 */
	private String registAddress;
	/**
	 * 官网网址
	 */
	private String webAddress;
	/**
	 * 电话号码
	 */
	private String telephone;
	/**
	 * 联系人1
	 */
	private String contact1;
	/**
	 * 联系人2
	 */
	private String contact2;
	/**
	 * 联系人1电话
	 */
	private String contact1Phone;
	/**
	 * 联系人2电话
	 */
	private String contact2Phone;
	/**
	 * 备注说明
	 */
	private String comment;

	/**
	 * 是否资源共享
	 */

	private Boolean shareResource;

	/**
	 * 合同有效期开始
	 */

	private Date effectiveTimeS;
	/**
	 * 合同有效期截止
	 */

	private Date effectiveTimeE;
	/**
	 * 帐号
	 */

	private String operatorNo;
	/**
	 * 佣金百分比
	 */
	private BigDecimal commisionScale;

	/**
	 * 周期类型详见码表
	 */
	private CycleEnum cycleEnum;

	/**
	 * 结算号数
	 */
	private Short balanceDate;

	private String passwd;
    /**
     * 图片logo
     */
	private String img;

	private String colour;
	/**
	 * 营业执照编号
	 */
	private String licnum;
	/**
	 * 法人身份证
	 */
	private String lpcard;

	/**
	 * 商家applogo
	 */
	private String appImg;

	/**
	 * 银卡会员>=
	 */
	private Integer silverGteq;

	/**
	 * 银卡会员<
	 */
	private Integer silverLt;

	/**
	 * 金卡会员>=
	 */
	private Integer goldGteq;

	/**
	 * 金卡会员<
	 */
	private Integer goldLt;

	/**
	 * 白金卡会员>=
	 */
	private Integer ptGteq;
	/**
	 * 白金卡会员<
	 */
	private Integer ptLt;
	
	/**
	 *钻石卡会员>=
	 */
	private Integer diamondGteq;
	
	//钻石卡会员<
	private Integer diamondLt;
	
	//黑金卡会员>=
	private Integer blackGoldGteq;
	
    /**
     * 公众号
     */
	private String commonNumber;
	
	/**
	 * /公众号appId
	 */
	private String commonNumberAppId;
	
	//钻石服务包ID
	private Long	diamondsPkgId;
	
	//白金服务包ID
	private Long	ptPkgId;
	
	//金卡服务包ID
	private Long	goldPkgId;
	
	//银卡服务包ID
	private Long  silverPkgId;
	
	//黑金服务包ID
	private Long  blackGoldPkgId;
	
	//桩的限制量
	private Integer limitNumber;
	
	//桩的限制量
	private Integer limitGun;
	
	//是否默认支付配置
	private Boolean paymentConfig;
	//短信配置id
	private Long smsId;
	//公众号热线
	private String hotline;
	
	//公众号热线2
	private String hotline2;
	
	//公众号强制绑定
	private Boolean publicNumberForcBind;
	
	//商家简称
	private String abbreviationName;
	
	//是否清除会员的消费积分
	private Boolean clearConsumeIntegral;
	
	

	public Integer getLimitGun() {
		return limitGun;
	}

	public void setLimitGun(Integer limitGun) {
		this.limitGun = limitGun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OperatorProEnum getOperatorProEnum() {
		return operatorProEnum;
	}

	public void setOperatorProEnum(OperatorProEnum operatorProEnum) {
		this.operatorProEnum = operatorProEnum;
	}

	public CardEnum getCardEnum() {
		return cardEnum;
	}

	public void setCardEnum(CardEnum cardEnum) {
		this.cardEnum = cardEnum;
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

	public CycleEnum getCycleEnum() {
		return cycleEnum;
	}

	public void setCycleEnum(CycleEnum cycleEnum) {
		this.cycleEnum = cycleEnum;
	}

	public Short getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Short balanceDate) {
		this.balanceDate = balanceDate;
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

	public String getHotline2() {
		return hotline2;
	}

	public void setHotline2(String hotline2) {
		this.hotline2 = hotline2;
	}
	

}
