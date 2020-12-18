package com.nzkj.screen.memory;
/**
 * redis缓存数据类型
 * @author 007idle
 * @date 2017-12-27
 */
public enum RedisDataEnum {
	APP_TOKEN("appToken","app令牌"), 
	APP_TOKEN_MAP("appTokenMap","app令牌"),
	MAIN_APP_TOKEN("mainAppToken","运维小程序令牌"),
	MAIN_APP_TOKEN_MAP("mainAppTokenMap","运维小程序令牌"),
	Operator_APP_TOKEN("OperatorAppToken","运营小程序令牌"),
	Operator_APP_TOKEN_ID_MAP("OperatorAppTokenMap","运营小程序令牌map"),
	CHARGEMAN_APP_TOKEN("ChargeManAppToken","充电工小程序令牌"),
	CHARGEMAN_APP_TOKEN_MAP("ChargeManAppTokenMap","充电工小程序令牌"),
	GUN("gun","枪"),PILE("pile","桩"),
	STATION("station","站点"), 
	AREA("area","区域"), 
	ALRM("alarm","告警"),
	GUN_PRICE("gunPrice","枪价格"),
	JD_BILL_RULE_DO("jdBillRuleDO","聚电计费规则DO") ,
	CHARGE_MONITOR("chargeMonitor","充电服务监控") ,
	PILE_AND_BILL("pileAndBill","桩与账单关系") , 
	MEMBER_AND_BILL("memberAndBill","会员与账单关系") ,
	BUS_EXCEPTION_LOG("busExceptionLog","业务异常日志"),
	GDEV_SELLER_TOKEN("GDEVToken","广东省电动汽车充电设施智能服务平台令牌"),
	GDEV_SELLER_SEQ("GdEvSellerSeq","广东省电动汽车充电设施智能服务平台序列"),
	KSEV_SELLER_SEQ("KSEvSellerSeq","昆山市政府监管平台序列"),
	MEMBER_BILL_TIME("mbttp","会员充电绑定时间温度功率信息"),
	CHARGING_MEMBER_BALANCE("chargeMemberBalance","充电中会员余额检查"),
	SHANGHAI_TOKEN("shanghaiToken","上海令牌"),
	KS_TOKEN("ksTokenRequest","昆山令牌"),
	KS_TOKEN_ID_MAP("ksTokenMap","昆山令牌映射"),
	SELLER_GOVERNMENT_INTERFACE("sellerGovernmentInterface","商家政府接口"),
	PILE_ID_NO_MAP("pileIdNoMap","桩id与桩体号映射"),
	STATION_RUN_INFO("stationRunInfo","站点运行信息"),
	PILE_RUN_INFO("pileRunInfo","桩运行信息"),
	GUN_ALARM("gunAlarm","枪告警"),
	WEB_SESSION_PERMISSION("webSessionPermission","网页session权限"),
	PILE_START_WATCH_TASK("pileStartWatchTask","桩启动充电任务"),
	PILE_STOP_WATCH_TASK("pileStopWatchTask","桩停止充电任务"),
	PILE_ONLINE_ID("pileOnlineId","桩在线id"),
	PILE_OFFLINE_ID("pileOfflineId","桩断网id"),
	PILE_HEART_BEAT_MAP("pileHeartBeatMap","桩心跳集合"),
	WX_COMMON_TOKEN("wxCommonToken","微信公众号token"),
	WX_MINI_APP_TOKEN("wxMiniAppToken","微信小程序token"),
	HUINENG_TOKEN("huiNengToken","汇能桩平台token"),
	HUINENG_SETTING("huiNengSetting","汇能桩平台配置"),
	YANGCHENGCHARGE_SEQ("yangChengChargeSeq","广州羊城充平台序列"),
	CAR_LOCK_TASK("carLockTask","车位锁检查任务"),
	GDEV_PUSH_QUEUE("gdEvPushQueue","广东省电动汽车充电设施智能服平台推送队列"),
	KS_PUSH_QUEUE("ksGdEvPushQueue","昆山市政府监管平台推送队列"),
	YANGCHENGCHARGE_PUSH_QUEUE("yangChengChargePushQueue","羊城充政府监管平台推送队列"),
	WX_MEMBER_CHANGE("wxMemberChange","微信切换会员"),
	VENDING_MACHINE_ORDER("vendingMachineOrder","贩卖机订单"),
	AJB_SECRETID("ajbSecretId","安居宝云密钥标识"),
	BILL_SOC("billSOC","账单SOC"),
	STATION_ID_SET("stationIdSet","站点ID集合"),
	JD_BILL_NO("JDBillNo","聚电流水号"),
	XIAOJU_PUSH_QUEUE("xiaojuPushQueue","小桔科技平台推送队列"),
	XIAOJU_REQUEST_TOKEN("xiaojuRequestToken","小桔科技平台token:用于请求"),
	XIAOJU_SELLER_TOKEN("xiaojuSellerToken","小桔科技平台token:用于推送"),
	METER_VALUE("meterValue","电表值"),
	CAR_STATISTICAL("CarStatisticalDto","车辆统计展现相关"),
	GUN_REAL_DATA_MAP("gunRealDataMap","枪实时数据映射"),
	BILL_METER_VALUE("billMeterValue","账单电表值"),
	DONGGUAN_PUSH_QUEUE("dongGuanPushQueue","东莞市政府监测平台推送队列"),
	DONGGUAN_REQUEST_TOKEN("dongGuanRequestToken","东莞市政府监测平台token:用于请求"),
	DONGGUAN_PUSH_TOKEN("dongGuanPushToken","东莞市政府监测平台token:用于推送"),
	DONGGUAN_SELLER_SEQ("dongGuanSellerSeq","东莞市政府监测平台序列"),
	CAOCAO_PUSH_QUEUE("caocaoPushQueue","曹操科技平台推送队列"),
	CAOCAO_REQUEST_TOKEN("caocaoRequestToken","曹操科技平台token:用于请求"),
	CAOCAO_SELLER_SEQ("caocaoSellerSeq","曹操科技平台序列"),
	SHANGHAI_PUSH_QUEUE("shanghaiPushQueue","上海市政府推送队列"),
	SHANGHAI_SEQ("shanghaiSeq","上海市政府序列"),
	KE_LU_BILL_NO("keLuBillNo","科陆账单流水"),
	JIAO_TOU_PUSH_QUEUE("jiaoTouPushQueue","交投平台推送队列"),
	INTER_FLOW_PUSH_QUEUE("interFlowPushQueue","互联互通推送队列"),
	ONE_KEY_CHARGE_GUN_LIST("oneKeyChargeGunList","一键启动枪列表"),
	ONE_KEY_CHARGE_SET("oneKeyChargeSet","一键启动集合"),
	ORDER_TASK("orderTask","预约任务"),
	ONE_KEY_CHARGE_MSG_LIST("oneKeyChargeMsgList","一进去启动结果"),
	CLOSED_PILE_SET("closedPileSet","关闭桩集合"),
	XIAO_JU_BILL("xiaoJuBill","小桔账单"),
	HU_LIAN_HU_TONG_BILL("huLinaHuTong","互联互通账单"),
	THIRD_PLATFORM_TOKEN("thirdPlatformToken","第三方平台令牌"),
	INTERFLOW_TOKEN("interFlowToken","广交平台令牌"),
	THIRD_PLATFORM_ORDER_NO("gzptBillNo","对第三方平台发起充电的流水号"),
	CHARGING_SOC("chargingSoc","充电中SOC"),
	CHARGE_MONITOR_SAVE_SETS("chargeMonitorSaveSet","充电服务监控保存集合"),
	APP_ID_CODE("appIdCode","app验证码"),
	THIRD_PLATFORM_CHARGING_BILL("thirdPlatformChargingBill","第三方平台在商家下充电的订单"),
	VEND_MACHINE_RUN_STATUS("vendMachineRunStatus","售后机运行状态"),
	BILL_OFFLINE_SOC("billOfflineSOC","离线账单SOC"),
	STATION_EVENTS("stationEvents","站点事件"),
	PILE_CHARGING_SOC_MAP("pileChargingSocMap","桩充电SOC信息集合"),
	PILE_CHARGE_CONTEXT("pileChargeContext","桩充电上下文"),
	BILL_GUN("billGun","账单绑定的枪"),
	CHONGQING_PUSH_QUEUE("ChongQingPushQueue","重庆市政府监测平台推送队列"),
	CHONGQING_REQUEST_TOKEN("ChongQingRequestToken","重庆市政府监测平台token:用于请求"),
	CHONGQING_PUSH_TOKEN("ChongQingPushToken","重庆市政府监测平台token:用于推送"),
	CHONGQING_SELLER_SEQ("ChongQingSellerSeq","重庆市政府监测平台序列"),
	AI_PARK_CITY_TOKEN("aiParkCityToken","如月停车平台token"),
	AI_PARK_CITY_CONFIG("aiParkCityConfig","如月停车平台"),
	CHARGE_MONITOR_CONDICTION("chargeMonitorCondiction","充电监控条件"),
	INTERFLOW_BILL_MAP("interFlowBillMap","互联互通账单"),
	APPOINT_BILL_MAP("appointBillMap","预约订单"),
	Jie_DIAN_LOCK("JieDianDianLock","捷电通订单锁:防止重复提交订单"),
	ALL_GUN_STATUS("allGunStatus","枪的所有状态"),
	KUAIDIAN_SELLER_TOKEN("kuaidianSellerToken","快电科技平台token:用于推送"),
	KUAIDIAN_REQUEST_TOKEN("kuaidianRequestToken","快电科技平台token:用于请求"),
	KUAIDIAN_PUSH_QUEUE("kuaidianPushQueue","快电科技平台推送队列"),
	KUAI_DIAN_LOCK("kuaiDianLock","快电抢锁:限制重复鉴权"),
	KUAI_DIAN_BILL("kuaiDianBill","快电账单");
	/*CONNECTOR_BY_ORDER("connectorIdByOrder","公交数据根据订单获取ConnectorId")*/
	
	private String prefix;
	private String description;
	
	private RedisDataEnum(String prefix, String description) {
		this.description = description;
		this.prefix = prefix;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
