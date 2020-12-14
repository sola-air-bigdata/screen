jQuery.myAnimate = {
	siteAnimate:function () {
		let stationParams = {//充电站
			container: document.getElementById("charging_station"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/station/data.json"
		}
		let stationAnim = lottie.loadAnimation(stationParams);

		let pileParams = {//充电桩
			container: document.getElementById("charging_pile"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/pile/data.json"
		}
		let pileAnim = lottie.loadAnimation(pileParams);
	},
	userAnimate:function () {
		let serviceParams = {//当日服务用户数
			container: document.getElementById("service_users"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/service_users/data.json"
		}
		let serviceAnim = lottie.loadAnimation(serviceParams);

		let addParams = {//累计新增用户数
			container: document.getElementById("add_user"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/add_user/data.json"
		}
		let addAnim = lottie.loadAnimation(addParams);
	},
	incomeAnimate:function () {
		let amountParams = {//当日累计收入
			container: document.getElementById("amount_income"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/amount_income/data.json"
		}
		let amountAnim = lottie.loadAnimation(amountParams);

		let grossParams = {//当日非公交总收入
			container: document.getElementById("gross_income"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/pile/data.json"
		}
		let grossAnim = lottie.loadAnimation(grossParams);
	},
	insideAnimate:function () {
		let totalParams = {//总服务次数
			container: document.getElementById("total_num"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/total_number/data.json"
		}
		let totalAnim = lottie.loadAnimation(totalParams);

		let quantityParams = {//总电量
			container: document.getElementById("total_quantity"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/total_quantity/data.json"
		}
		let quantityAnim = lottie.loadAnimation(quantityParams);

		let totalIncomeParams = {//总收入
			container: document.getElementById("total_income"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/total_income/data.json"
		}
		let totalIncomeAnim = lottie.loadAnimation(totalIncomeParams);

		// let peakValleyParams = {//充电峰谷统计
		// 	container: document.getElementById("peak_valley"),
		// 	renderer: "svg",
		// 	loop: true,
		// 	autoplay: true,
		// 	path: "./animation/peak_valley/data.json"
		// }
		// let peakValleyAnim = lottie.loadAnimation(peakValleyParams);

		// let chassisParams = {//头像下方旋转
		// 	container: document.getElementById("center_bg"),
		// 	renderer: "svg",
		// 	loop: true,
		// 	autoplay: true,
		// 	path: "./animation/chassis/data.json"
		// }
		// let chassisAnim = lottie.loadAnimation(chassisParams);
		// chassisAnim.setSpeed(0.5);
	},
	overviewAnimate:function () {
		let chargingLogParams = {//充电日志
			container: document.getElementById("charging_log"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/charging_log/data.json"
		}
		let chargingLogAnim = lottie.loadAnimation(chargingLogParams);

		let siteOperationParams = {//今日站点运营
			container: document.getElementById("site_operation"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/site_operation/data.json"
		}
		let siteOperationAnim = lottie.loadAnimation(siteOperationParams);

		let totalGunsParams = {//总枪数
			container: document.getElementById("total_guns"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/total_guns/data.json"
		}
		let totalGunsAnim = lottie.loadAnimation(totalGunsParams);
	},
	teamAnimate:function () {
		let fleetUserParams = {//车队用户分析
			container: document.getElementById("fleet_user"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/fleet_user/data.json"
		}
		let fleetUserAnim = lottie.loadAnimation(fleetUserParams);

		let consumptionRankParams = {//车队平均月充电消费排名
			container: document.getElementById("consumption_rank"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/consumption_rank/data.json"
		}
		let consumptionRankAnim = lottie.loadAnimation(consumptionRankParams);

		let fleetParams1 = {//服务车队用户总数
			container: document.getElementById("fleet1"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet01/data.json"
		}
		let fleetAnim1 = lottie.loadAnimation(fleetParams1);

		let fleetParams2 = {//服务车队车辆总数
			container: document.getElementById("fleet2"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet02/data.json"
		}
		let fleetAnim2 = lottie.loadAnimation(fleetParams2);

		let fleetParams3 = {//车队单车充电量
			container: document.getElementById("fleet3"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet03/data.json"
		}
		let fleetAnim3 = lottie.loadAnimation(fleetParams3);

		let fleetParams4 = {//车队单车消费
			container: document.getElementById("fleet4"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet04/data.json"
		}
		let fleetAnim4 = lottie.loadAnimation(fleetParams4);

		let fleetParams5 = {//车队均充电频率
			container: document.getElementById("fleet5"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet05/data.json"
		}
		let fleetAnim5 = lottie.loadAnimation(fleetParams5);

		let fleetParams6 = {//车队日充电量
			container: document.getElementById("fleet6"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet06/data.json"
		}
		let fleetAnim6 = lottie.loadAnimation(fleetParams6);

		let fleetParams7 = {//车队日充电次数
			container: document.getElementById("fleet7"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet07/data.json"
		}
		let fleetAnim7 = lottie.loadAnimation(fleetParams7);

		let fleetParams8 = {//车队均充电时长
			container: document.getElementById("fleet8"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/fleet08/data.json"
		}
		let fleetAnim8 = lottie.loadAnimation(fleetParams8);
	},
	memberAnimate:function () {
		let individualUserParams = {//个人用户分析
			container: document.getElementById("individual_user"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/individual_user/data.json"
		}
		let individualUserAnim = lottie.loadAnimation(individualUserParams);

		let userRankParams = {//个人用户消费排名
			container: document.getElementById("user_rank"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/consumption_rank/data.json"
		}
		let userRankAnim = lottie.loadAnimation(userRankParams);

		let userParams1 = {//站点服务用户总数
			container: document.getElementById("user1"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/user01/data.json"
		}
		let userAnim1 = lottie.loadAnimation(userParams1);

		let userParams2 = {//人均充电消费
			container: document.getElementById("user2"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/user02/data.json"
		}
		let userAnim2 = lottie.loadAnimation(userParams2);

		let userParams3 = {//月人均充电
			container: document.getElementById("user3"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/user03/data.json"
		}
		let userAnim3 = lottie.loadAnimation(userParams3);

		let userParams4 = {//月充电预约
			container: document.getElementById("user4"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/user04/data.json"
		}
		let userAnim4 = lottie.loadAnimation(userParams4);

		let userParams5 = {//月活动参加
			container: document.getElementById("user5"),
			renderer: "svg",
			loop: true,
			autoplay: true,
			path: "/resources/screen/expoScreen/animation/user-fleet/user05/data.json"
		}
		let userAnim5 = lottie.loadAnimation(userParams5);
	}
}
