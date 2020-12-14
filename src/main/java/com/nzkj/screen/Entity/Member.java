package com.nzkj.screen.Entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 会员
 * 
 * @author zhangtian
 */

@Entity(name = "t_business_base_operation_persional_member")
public class Member {

	@Id
	@GeneratedValue
	//id
	private long id;


	/**
	 * 会员名称
	 */
	@Column(name = "v_name")
	private String name;
	/**
	 * 身份证
	 */
	@Column(name = "v_identity_card")
	private String identityCard;
	/**
	 * 手机号码
	 */
	@Column(name = "v_telephone")
	private String telephone;

	/**
	 * 密码
	 */
	@Column(name = "v_charge_pass")
	private String pass;
	/**
	 * 操作的用户id
	 */
	@Column(name = "l_add_user_id")
	private Long addUserId;
	/**
	 * 车牌号
	 */
	@Column(name = "v_plateno")
	private String plateno;
	/**
	 * 公众号
	 */
	@Column(name = "v_open_id")
	private String openId;
	/**
	 * 工作单位
	 */
	@Column(name = "v_company")
	private String company;
	/**
	 * 状态
	 */
	@Column(name = "i_status")
	private Integer status;

	/**
	 * 是否是app 或者 平台
	 */
	@Column(name = "b_app_oa_type")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean appOaType;
	/**
	 * 会员类型 ( 团队会员,个人会员)详见码表
	 */
	@Column(name = "s_member_type")
	private Short memberType;
	/**
	 * 会员等级
	 */
	@Column(name = "i_level")
	private Integer level;
	/**
	 * 积分
	 */
	@Column(name = "i_integral")
	private Integer integral=0;
	
	/**
	 * 性别(true:男 false:女)
	 */
	@Column(name="b_sex",length=1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean sex;
	
	@Column(name = "v_icon")
	private String icon;
	/**
	 * 信用额度
	 */
	@Column(name = "i_line_of_credit")
	private Integer lineOfCredit;
	/**
	 * 累计消费金额
	 */
	@Column(name = "i_total_consumption")
	private Long totalConsumption;
	
	
	//累计电度
	@Column(name = "i_total_dgree")
	private Integer totalDgree=0;
	
	
	/**
	 * 免单剩余次数
	 */
	@Column(name = "i_single_remainder")
    private Integer singleRemainder=0;
	
	/**
	 * 余额状态
	 */
	@Column(name = "i_balance_status")
	private Integer  balanceStatus;
	
	//签到次数
	@Column(name = "i_signIn")
    private Integer signIn;
    //收藏数目
	@Column(name = "i_collect")
    private Integer collect;
    //出生日期
	@Column(name = "d_birth_time", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date birthTime;
    //学历
    @Column(name = "v_education")
    private String education;
    //居住城市
    @Column(name = "v_live_city")
    private String liveCity;
    
    //收货地址
    @Column(name = "v_url")
    private String url;
    
    //小程序
    @Column(name = "v_miniapps_id")
    private String miniappsId;
	
    /**
	 * 消费积分
	 */
	@Column(name = "i_consume_integral")
	private Integer consumeIntegral=0;
	
	/**
	 * 是否第一次使用消费积分
	 */
	@Column(name="b_first_use",length=1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean firstUse;
	
	//未配置桩的类型（不能充电，按合约价，按正常价）
	@Column(name = "v_not_config_type")
	private String notCnfigType;
	
	//注册方式
    @Column(name = "v_register_type")
    private String registerType;

    // 是否是互联互通扣费会员
	@Column(name="b_is_connectivity",length=1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isConnectivity;
    	
	/**
	 * 邀请人id
	 */
	@Column(name = "l_inviter_id")
	private Long inviterMemberId;
	
	// 扣费模式(车队-子成员)
	@Column(name = "v_deduct_mode")
	private String deductMode;
	
	// 渠道来源
	@Column(name = "v_channel")
	private String channel;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Long getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Long addUserId) {
		this.addUserId = addUserId;
	}

	public String getPlateno() {
		return plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getAppOaType() {
		return appOaType;
	}

	public void setAppOaType(Boolean appOaType) {
		this.appOaType = appOaType;
	}

	public Short getMemberType() {
		return memberType;
	}

	public void setMemberType(Short memberType) {
		this.memberType = memberType;
	}
	
	@ManyToOne
	@JoinColumn(name = "l_seller_id")
	private Operator operator;


	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	@OneToMany(mappedBy = "member", cascade = {CascadeType.ALL})
	@Where(clause = "b_delete_flag=0")
	private Set<Account> setAccount;

	public Set<Account> getSetAccount() {
		return setAccount;
	}

	public void setSetAccount(Set<Account> setAccount) {
		this.setAccount = setAccount;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getLineOfCredit() {
		return lineOfCredit;
	}

	public void setLineOfCredit(Integer lineOfCredit) {
		this.lineOfCredit = lineOfCredit;
	}

	public Long getTotalConsumption() {
		return totalConsumption;
	}

	public void setTotalConsumption(Long totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getSingleRemainder() {
		return singleRemainder;
	}

	public void setSingleRemainder(Integer singleRemainder) {
		this.singleRemainder = singleRemainder;
	}

	public Integer getBalanceStatus() {
		return balanceStatus;
	}

	public void setBalanceStatus(Integer balanceStatus) {
		this.balanceStatus = balanceStatus;
	}

	public Integer getTotalDgree() {
		return totalDgree;
	}

	public void setTotalDgree(Integer totalDgree) {
		this.totalDgree = totalDgree;
	}

	public Integer getSignIn() {
		return signIn;
	}

	public void setSignIn(Integer signIn) {
		this.signIn = signIn;
	}

	public Integer getCollect() {
		return collect;
	}

	public void setCollect(Integer collect) {
		this.collect = collect;
	}

	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMiniappsId() {
		return miniappsId;
	}

	public void setMiniappsId(String miniappsId) {
		this.miniappsId = miniappsId;
	}

	public Integer getConsumeIntegral() {
		return consumeIntegral;
	}

	public void setConsumeIntegral(Integer consumeIntegral) {
		this.consumeIntegral = consumeIntegral;
	}

	public Boolean getFirstUse() {
		return firstUse;
	}

	public void setFirstUse(Boolean firstUse) {
		this.firstUse = firstUse;
	}

	public String getNotCnfigType() {
		return notCnfigType;
	}

	public void setNotCnfigType(String notCnfigType) {
		this.notCnfigType = notCnfigType;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public Long getInviterMemberId() {
		return inviterMemberId;
	}

	public void setInviterMemberId(Long inviterMemberId) {
		this.inviterMemberId = inviterMemberId;
	}

	public String getDeductMode() {
		return deductMode;
	}

	public void setDeductMode(String deductMode) {
		this.deductMode = deductMode;
	}

	public Boolean getConnectivity() {
		return isConnectivity;
	}

	public void setConnectivity(Boolean connectivity) {
		isConnectivity = connectivity;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
