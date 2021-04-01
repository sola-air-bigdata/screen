$(function () {

	var p1 = echarts.init(document.getElementById("charging")); //累计充电量公交占比
	// var p2 = echarts.init(document.getElementById("failure")); // 设备故障率 公交占比
	var b1 = echarts.init(document.getElementById("chart")); //累计充电量柱状图
	var line1 = echarts.init(document.getElementById("failure1")); //设备故障率 按月统计表 数据数组
	var line2 = echarts.init(document.getElementById("growth_trend")); // 总用户增长趋势 
	var line3 = echarts.init(document.getElementById("active_trend")); // 用户活跃趋势
	var b2 = echarts.init(document.getElementById("trend1")); //收入运营 总收入趋势 
	var b3 = echarts.init(document.getElementById("trend2")); //收入运营 非公交总收入趋势 
	var p4 = echarts.init(document.getElementById("revenue_statistics")); //收入方式统计
	var rollInterval, total1, total2, total3 = 0;
	initPage($('#container1'), 6); // 初始化滚动数字最大的位数
	initPage($('#container2'), 6); // 初始化滚动数字最大的位数
	initPage($('#container3'), 6); // 初始化滚动数字最大的位数

	var numItemHeight = $('.num-item').eq(0).height(); // 获取数字dom的高度

	getAllPowerAndCarCharging(); //总功率 一分钟刷新一次
	deviceProblemData(); //设备故障率部分  一分钟刷新一次 
	historyTotalCharging(); //累计充电量部分的数据 一分钟刷新一次
	getStationServiceRanking(); //站点服务频率排行 一分钟刷新一次

	getTotalNum(); //数字滚动部分数据  累计 收入 服务  30秒刷新 一次
	serviceOperateData(); //累计服务时长 30秒刷新 一次
	incomeWayData(); //收入方式统计  30秒刷新 一次  右下角

	//getRealPower(); //实时数据  五秒刷新一次

	incomeOperate(); //收入运营  不刷新
	incomeTrend(); //收入运营 总收入趋势  不刷新
	addAndActiveTrend(); //总用户增长趋势 用户活跃量趋势 不刷新
	userOperateData(); //用户运营 不刷新

	/**
	 * 定时器
	 * 
	 */
	var timer1 = setTimeout(FiveSec, 2000); //5秒钟执行一次
	function FiveSec() {
		getRealPower(); //执行方法
		clearTimeout(timer1);
		setTimeout(FiveSec, 2000)
	};

	var timer2 = setTimeout(TreeSec1, 2000); //30秒执行一次
	function TreeSec1() {
		getTotalNum(); //数字滚动部分数据  累计 收入 服务  30秒刷新 一次
		clearTimeout(timer2);
		setTimeout(TreeSec1, 2000)
	}

	var timer2_1 = setTimeout(TreeSec2, 2000); //30秒执行一次
	function TreeSec2() {
		incomeWayData(); //收入方式统计  30秒刷新 一次  右下角
		serviceOperateData(); //累计服务时长 30秒刷新 一次
		clearTimeout(timer2_1);
		setTimeout(TreeSec2, 2000)
	}

	var timer3_1 = setTimeout(OneMin1, 2000); //1分钟执行一次
	function OneMin1() {
		getAllPowerAndCarCharging(); //总功率 一分钟刷新一次
		getStationServiceRanking(); //站点服务频率排行 一分钟刷新一次
		clearTimeout(timer3_1);
		setTimeout(OneMin1, 2000)
	}

	var timer3_2 = setTimeout(OneMin2, 2000); //1分钟执行一次
	function OneMin2() {
		historyTotalCharging(); //累计充电量部分的数据 一分钟刷新一次
		clearTimeout(timer3_2);
		setTimeout(OneMin2, 2000)
	}

	var timer3_3 = setTimeout(OneMin3, 2000); //1分钟执行一次
	function OneMin3() {
		deviceProblemData(); //设备故障率部分  一分钟刷新一次
		clearTimeout(timer3_3);
		setTimeout(OneMin3, 2000)
	}

	var allPower_f = 0;
	function getAllPowerAndCarCharging() {
		$.ajax({
			url: "/statistic/screenNewAjax/getAllPowerAndCarCharging.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var allPower = parseInt(res.allPower); //总功率
				if (allPower > 10000) {
					allPower = parseFloat((allPower / 10000).toFixed(2));
					$('.allPower').text(allPower);
					$('#allPowerDw').text("万KW");
				} else {
					$('.allPower').text(allPower);
					$('#allPowerDw').text("KW");
				}
				$('.allPower').lemCounter({
					value_to: allPower,
					value_from: allPower_f
				});
				allPower_f = allPower;
			}
		})
	}

	function deviceProblemData() {
		$.ajax({
			url: "/statistic/screenNewAjax/deviceProblemData.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var falutMap = res.falutMap; ////故障曲线图
				var faultCountList = falutMap.faultCountList;
				var falutMaptimeList = falutMap.timeList;
				line1.setOption(lineChartOptions(falutMaptimeList, faultCountList, '按月统计'));
				var faultCount = res.faultCount; //月故障数
				$('.faultCount').text(faultCount);
				var rate = parseFloat(res.rate).toFixed(2);
				$('#faultCountrate').text(rate + '%');
				// p2.setOption(pieChartOptions('#FE3737', '#FE3737', rate, 14, 30, 12, 600, "故障率")); // 设备故障率 
			}
		})
	}

	var realTimePower_f = 0;
	function getRealPower() {
		$.ajax({
			url: "/statistic/screenNewAjax/getRealPower.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var realTimePower = res.realTimePower; //设备运营 实时功率
				$('.realTimePower').text(realTimePower);
				var chargingPercent = res.chargingPercent; ///显示当前充电电桩的百分比值
				if (chargingPercent == undefined) {
					chargingPercent = 0;
				}
				var chargingPercentceil = Math.ceil(chargingPercent);
				$('#chargingPercentBar').attr("aria-valuenow", chargingPercentceil);
				$('.chargingPercentBar1').text(chargingPercent + '%');
				$('.sitechargingPercent').text(chargingPercent);

				$('.realTimePower').lemCounter({
					value_to: realTimePower,
					value_from: realTimePower_f
				});
				realTimePower_f = realTimePower;
			}
		})
	}

	var sumPower_f = 0;
	function historyTotalCharging() {
		$.ajax({
			url: "/statistic/screenNewAjax/historyTotalCharging.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
					var team12ChargingData = res.team12ChargingData; //2 累计充电量柱状图
					b1.setOption(barChartOptions(team12ChargingData, 'K \n w \n h ', '公交车', '其他', true, 75)); //累计充电量柱状图
					var carChargingPercent = parseFloat(res.carChargingPercent).toFixed(2); //累计充电量公交占比
					p1.setOption(pieChartOptions('#2BDEF7', '#fff', carChargingPercent, 12, 20, 12, 600, "公交占比")); // 累计充电量公交占比
					var sumPower = parseFloat(res.sumPower);
					if (sumPower > 10000) {
						sumPower = parseFloat((res.sumPower / 10000).toFixed(2));
						$('.sumPower').text(sumPower);
						$('#sumPowerDw').text("万KWH");
					} else {
						$('.sumPower').text(sumPower);
						$('#sumPowerDw').text("KWH");
					}

					$('.sumPower').lemCounter({
						value_to: sumPower,
						value_from: sumPower_f
					});
					sumPower_f = sumPower;

			}
		})
	}
	window.addEventListener("resize", function () {
		b1.resize();
	});

	//收入运营  不刷新
	function incomeOperate() {
		$.ajax({
			url: "/statistic/screenNewAjax/incomeOperate.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var {
					otherBalanceRate,
					otherIncomeRate,
					powerBalanceRate,
					teamIncomeRate
				} = res;
				$('.otherBalanceRate').text(otherBalanceRate + '%');
				$('.otherIncomeRate').text(otherIncomeRate + '%');
				$('.powerBalanceRate').text(powerBalanceRate + '%');
				$('.teamIncomeRate').text(teamIncomeRate + '%');
				$('.otherIncomeRateUl>li').eq(1).css('width', otherIncomeRate + '%');
				$('.otherIncomeRateUl>li').eq(0).css('width', teamIncomeRate + '%');
				$('.otherBalanceRateUl>li').eq(0).css('width', powerBalanceRate + '%');
				$('.otherBalanceRateUl>li').eq(1).css('width', otherBalanceRate + '%');
			}
		})
	}

	//四舍五入保留两位小数
	function toDecimal(x) {
		var f = parseFloat(x);
		if (isNaN(f)) {
			return;
		}
		f = Math.round(x * 100) / 100;
		return f;
	}
	//收入方式统计
	function incomeWayData() {
		$.ajax({
			url: "/statistic/screenNewAjax/incomeWayData.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var {
					app,
					card,
					vin,
					wx
				} = res;
				app = parseFloat(app);
				card = parseFloat(card);
				vin = parseFloat(vin);
				wx = parseFloat(wx);
				if (app > 10000) {
					app = (app / 10000).toFixed(2);
					var appstr = 'APP ' + app + '万元';
				} else {
					var appstr = 'APP ' + app + '元';
				}
				if (vin > 10000) {
					vin = (vin / 10000).toFixed(2);
					var vinstr = 'VIN ' + vin + '万元';
				} else {
					var vinstr = 'VIN ' + vin + '元';
				}
				if (card > 10000) {
					card = (card / 10000).toFixed(2);
					var cardstr = '充电卡 ' + card + '万元';
				} else {
					var cardstr = '充电卡 ' + card + '元';
				}
				if (wx > 10000) {
					wx = (wx / 10000).toFixed(2);
					var wxstr = '微信 ' + wx + '万元';
				} else {
					var wxstr = '微信 ' + wx + '元';
				}

				var revenueArrstr = [];
				revenueArrstr.push(vinstr, cardstr, wxstr, appstr)
				var revenueArrjson = [];
				var vinjson = {};
				vinjson.value = parseFloat(res.vin);
				vinjson.name = vinstr;
				var appjson = {};
				appjson.value = parseFloat(res.app);
				appjson.name = appstr;
				var cardjson = {};
				cardjson.value = parseFloat(res.card);
				cardjson.name = cardstr;
				var wxjson = {};
				wxjson.value = parseFloat(res.wx);
				wxjson.name = wxstr;
				revenueArrjson.push(vinjson, appjson, cardjson, wxjson);
				var color = ['#FB6C33', '#86CC6D', '#5AC0EA', '#FFC93C'];
				p4.setOption(pieChartOptions2(color, revenueArrstr, revenueArrjson));
			}
		})
	}

	//收入运营 总收入趋势 
	function incomeTrend() {
		$.ajax({
			url: "/statistic/screenNewAjax/incomeTrend.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var totalIncomeTrend = res.totalIncomeTrend; //总收入趋势
				var notTotalIncomeTrend = res.notTotalIncomeTrend; //非公交总收入趋势
				b2.setOption(barChartOptions(totalIncomeTrend, '', '充电', '其他', true, 75));
				b3.setOption(barChartOptions(notTotalIncomeTrend, '', '充电', '其他', true, 75));
			}
		})
	}

	//数字滚动函数
	function getTotalNum() {
		$.ajax({
			url: "/statistic/screenNewAjax/getTotalNum.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var {
					totalMoney,
					totalServiceMemberNum,
					serviceTotalCount
				} = res;
				if (serviceTotalCount > 999999) {
					serviceTotalCount = parseInt(serviceTotalCount / 10000);
					$('#serviceTotalCountIndex').text('万次');
				} else {
					serviceTotalCount = parseInt(serviceTotalCount);
					$('#serviceTotalCountIndex').text('次');
				}
				if (totalServiceMemberNum > 999999) {
					totalServiceMemberNum = parseInt(totalServiceMemberNum / 10000);
					$('#totalServiceMemberNumIndex').text('万个');
				} else {
					totalServiceMemberNum = parseInt(totalServiceMemberNum);
					$('#totalServiceMemberNumIndex').text('个');
				}

				if (parseFloat(totalMoney) > 999999) {
					totalMoney = parseInt(parseFloat(totalMoney) / 10000);
					$('#totalMoneyIndex').text('万元');
				} else {
					totalMoney = parseInt(totalMoney);
					$('#totalMoneyIndex').text('元');
				}
				fun(totalServiceMemberNum, totalMoney, serviceTotalCount); //实现数字滚动
			}
		})
	}

	//站点服务频率排行
	function getStationServiceRanking() {
		$.ajax({
			url: "/statistic/screenNewAjax/getStationServiceRanking.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var rankList = res.rankList; //站点服务频率排行 
				for (let i = 0; i < rankList.length; i++) {
					$('.rankList').eq(i).find("h1").text(rankList[i].stationName);
					if (parseInt(rankList[i].allChargeCount) < 10000) {
						$('.allChargeCount').eq(i).text(parseInt(rankList[i].allChargeCount) + '次');
					} else {
						$('.allChargeCount').eq(i).text(parseInt(rankList[i].allChargeCount / 10000) + '万次')
					}
					if (parseInt(rankList[i].chargeCount) < 10000) {
						$('.chargeCount').eq(i).text(parseInt(rankList[i].chargeCount));
						$('.chargeCount').eq(i).next().text("次")
					} else {
						$('.chargeCount').eq(i).text(parseInt(rankList[i].chargeCount / 10000));
						$('.chargeCount').eq(i).next().text("万次")
					}
					$('.gunCount').eq(i).text(parseInt(rankList[i].gunCount) + '支');
				}
			}
		})
	}

	//用户运营
	function userOperateData() {
		$.ajax({
			url: "/statistic/screenNewAjax/userOperateData.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				var memberData = res.memberData;
				$('.silverRate').text(memberData.silverRate + '%');
				$('.platinumRate').text(memberData.platinumRate + '%');
				$('.goldRate').text(memberData.goldRate + '%');
				$('.diamondRate').text(memberData.diamondRate + '%');
				$('.blackGoldRate').text(memberData.blackGoldRate + '%');
				$('.data_list1>li').eq(0).css('width', memberData.silverRate + '%');
				$('.data_list1>li').eq(1).css('width', memberData.goldRate + '%');
				$('.data_list1>li').eq(2).css('width', memberData.platinumRate + '%');
				$('.data_list1>li').eq(3).css('width', memberData.diamondRate + '%');
				$('.data_list1>li').eq(4).css('width', memberData.blackGoldRate + '%');
				$('.teamCount').text(memberData.teamCount + "个 " + memberData.teamRate + "%");
				$('.personageCount').text(memberData.personageCount + "个 " + memberData.personageRate + "%");
			}
		})
	}

	//总用户增长趋势 用户活跃量趋势 
	function addAndActiveTrend() {
		$.ajax({
			url: "/statistic/screenNewAjax/addAndActiveTrend.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				// 总用户增长趋势 曲线图
				var memberAddTrend = res.memberAddTrend;
				line2.setOption(lineChartOptions1(memberAddTrend.timeList, memberAddTrend.teamList, memberAddTrend.personageList, 40)); // 总用户增长趋势 
				//用户活跃量趋势memberActiveTrend
				var memberActiveTrend = res.memberActiveTrend;
				line3.setOption(lineChartOptions1(memberActiveTrend.timeList, memberActiveTrend.teamList, memberActiveTrend.personageList, 40)); //用户活跃量趋势
			}
		})
	}


	//累计服务时长
	var totalChargingTime_f = 0;
	var gongChargingTime_f = 0;
	var otherChargingTime_f = 0;
	function serviceOperateData() {
		$.ajax({
			url: "/statistic/screenNewAjax/serviceOperateData.json",
			type: "post",
			dataType: "json",
			data: {},
			success: function (res) {
				//公交车辆平均充电时长AverageTimeForGong
				//其他车辆平均充电时长AverageTimeForOther
				var AverageTimeForGong = res.AverageTimeForGong;
				var AverageTimeForOther = res.AverageTimeForOther;
				$('.AverageTimeForGong').text(AverageTimeForGong);
				$('.AverageTimeForOther').text(AverageTimeForOther);
				var gongPercent = res.gongPercent; //服务运营下公交百分比
				var anotherPercent = res.anotherPercent; //服务运营下其他百分比
				$('.gongPercent').text(gongPercent + '%');
				$('.anotherPercent').text(anotherPercent + '%');
				var gongPercentceil = Math.ceil(gongPercent);
				$('#gongPercentBar').attr("aria-valuenow", gongPercentceil);

				var totalChargingTime = parseFloat(res.totalChargingTime); //累计服务时长 总时长 
				var gongChargingTime = parseFloat(res.gongChargingTime); //累计服务时长 公交总时长 
				var otherChargingTime = parseFloat(res.otherChargingTime); //累计服务时长 其他总时长 
				if (Math.abs(totalChargingTime) > 10000) {
					totalChargingTime = parseFloat((totalChargingTime / 10000).toFixed(2));
					$('.totalChargingTime').text(totalChargingTime);
					$('.totalChargingTime+b').text("万小时");
				} else {
					totalChargingTime = parseFloat(totalChargingTime);
					$('.totalChargingTime').text(totalChargingTime);
					$('.totalChargingTime+b').text("小时");
				}

				$('.totalChargingTime').lemCounter({
					value_to: totalChargingTime,
					value_from: totalChargingTime_f
				});
				totalChargingTime_f = totalChargingTime;

				if (Math.abs(gongChargingTime) > 10000) {
					gongChargingTime = parseInt(gongChargingTime / 10000);
					$('.gongChargingTime').text(gongChargingTime);
					$('.gongChargingTime+b').text("万小时")
				} else {
					gongChargingTime = parseInt(gongChargingTime);
					$('.gongChargingTime').text(gongChargingTime);
					$('.gongChargingTime+b').text("小时")
				}

				$('.gongChargingTime').lemCounter({
					value_to: gongChargingTime,
					value_from: gongChargingTime_f
				});
				gongChargingTime_f = gongChargingTime;

				if (Math.abs(otherChargingTime) > 10000) {
					otherChargingTime = parseInt(otherChargingTime / 10000)
					$('.otherChargingTime').text(otherChargingTime);
					$('.otherChargingTime+b').text("万小时")
				} else {
					otherChargingTime = parseInt(otherChargingTime)
					$('.otherChargingTime').text(otherChargingTime);
					$('.otherChargingTime+b').text("小时")
				}
				var serviceAnotherCarCount = res.serviceAnotherCarCount; //服务运营其他次数
				var serviceGongCount = res.serviceGongCount; //服务运营其他次数
				$('.serviceAnotherCarCount').text(numBuWan(serviceAnotherCarCount) + '次');
				$('.serviceGongCount').text(numBuWan(serviceGongCount) + '次');

				$('.otherChargingTime').lemCounter({
					value_to: otherChargingTime,
					value_from: otherChargingTime_f
				});
				otherChargingTime_f = otherChargingTime;
			}
		})
	}

	function fun(total1, total2, total3) {
		numberRoll($('#container1'), total1);
		numberRoll($('#container2'), total2);
		numberRoll($('#container3'), total3);
	}

	function numberRoll(container, total) {
		var number = total.toString().split("").reverse().join("");
		for (var i = 0; i < number.length; i++) {
			var height = parseInt(number[i]) * numItemHeight;
			$(container).find('.num' + i + ' .numbers-view').animate({
				marginTop: -height
			}, 3000, "swing");
		}
	}

	function initPage(container, digit) {
		var _html = '';
		for (var i = digit - 1; i >= 0; i--) {
			_html += '<div class="number num' + i + '">' +
				'<div class="numbers-view">' +
				'<div class="num-item zero">0</div>' +
				'<div class="num-item one">1</div>' +
				'<div class="num-item two">2</div>' +
				'<div class="num-item three">3</div>' +
				'<div class="num-item four">4</div>' +
				'<div class="num-item five">5</div>' +
				'<div class="num-item six">6</div>' +
				'<div class="num-item seven">7</div>' +
				'<div class="num-item eight">8</div>' +
				'<div class="num-item nine">9</div>' +
				'</div>' +
				'</div>';
		}
		container.html(_html);
	};

})
