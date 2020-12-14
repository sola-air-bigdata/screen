var id = GetQueryString("stationID");

TolgetStationSumInfoByID(); //根据站点id获取总服务次数，总电量，总收入，充电峰谷,今日总服务次数，总电量，总收入统计  15s更新一次
getStationSumViewData(); //站点总览 中间部分数据

/**
 * 定时器
 * 
 */
var timer6 = setTimeout(FiveSec, 8000); //5秒钟执行一次
function FiveSec() {
	getRealtimePower(); //实时功率
	if (mindate.length > 10) {
		var newmindate = mindate.slice(mindate.length - 10, mindate.length)
		var newvludata = vludata.slice(vludata.length - 10, vludata.length);
		echarts_3(newmindate, newvludata)
	} else {
		echarts_3(mindate, vludata)
	}
	clearTimeout(timer6);
	setTimeout(FiveSec, 8000)
};

/**
 * 定时器
 * 
 */
var timer6_2 = setTimeout(FiveSec5, 16000); //15秒钟执行一次
function FiveSec5() {
	TolgetStationSumInfoByID(); //执行方法 今日站点运营
	clearTimeout(timer6_2);
	setTimeout(FiveSec5, 16000)
};

var timer6_3 = setTimeout(FiveSec6, 7000); //15秒钟执行一次 
function FiveSec6() {
	getStationSumViewData() //执行方法 电枪状态
	clearTimeout(timer6_3);
	setTimeout(FiveSec6, 7000)
};

var siteGunCount_f = 0;
var sitecharging_f = 0;
var siteproblem_f = 0;
var siteoffLine_f = 0;
var sitechargeFinsh_f = 0;
function getStationSumViewData() {
	$.ajax({
		url: "/statistic/gJStationViewAjax/getStationSumViewData.json",
		type: "post",
		dataType: "json",
		data: {
			stationID: id
		},
		success: function (res) {
			$('#siteGunCount').text(res.gunCount);
			$('#sitecharging').text(res.charging);
			$('#siteproblem').text(res.problem);
			$('#siteoffLine').text(res.offLine);
			$('#sitechargeFinsh').text(res.chargeFinsh);
			$('#sitechargePrepare').text(res.chargePrepare);
			$('#sitebespeak').text(res.bespeak);

			//数字滚动
			$('#siteGunCount').lemCounter({
				value_to: res.gunCount,
				value_from: siteGunCount_f
			});
			siteGunCount_f = res.gunCount;

			$('#sitecharging').lemCounter({
				value_to: res.charging,
				value_from: sitecharging_f
			});
			sitecharging_f = res.charging;

			$('#siteproblem').lemCounter({
				value_to: res.problem,
				value_from: siteproblem_f
			});
			siteproblem_f = res.problem;

			$('#siteoffLine').lemCounter({
				value_to: res.offLine,
				value_from: siteoffLine_f
			});
			siteoffLine_f = res.offLine;

			$('#sitechargeFinsh').lemCounter({
				value_to: res.chargeFinsh,
				value_from: sitechargeFinsh_f
			});
			sitechargeFinsh_f = res.chargeFinsh;

		}
	})
}

getStationCarServiceTime(); //根据站点id获取这个月每天服务的车辆数
function getStationCarServiceTime() {
	$.ajax({
		url: "/statistic/gJStationViewAjax/getStationCarServiceTime.json",
		type: "post",
		dataType: "json",
		data: {
			stationID: id
		},
		success: function (res) {
			if (res) {
				var date = res.date;
				var value = res.carNums;
				var charingBalance = res.charingBalance;
				var newcharingBalance = [];
				for (var i = 0; i < charingBalance.length; i++) {
					var newBalance = parseFloat(charingBalance[i] / 10000 / 100).toFixed(2);
					newcharingBalance.push(newBalance)
				}

				if (!date.length) {
					date = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
						29, 30, 31
					];
					value = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				}
				echarts_1(date, value);
				echarts_2(date, value, newcharingBalance)
			} else {
				var date = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
					28, 29, 30, 31
				];
				var value = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				echarts_1(date, value);
			}
		},
		error: function (data) {
		}
	})
}


getGunData(); //枪列表
function getGunData() {
	$.ajax({
		url: "/statistic/gJStationViewAjax/getGunData.json",
		type: "post",
		dataType: "json",
		data: {
			stationID: id
		},
		success: function (res) {
			var list = res;
			var html = "";
			var gun_detailshtml = "";
			for (var i = 0; i < list.length; i++) {
				html += '<li>';
				var gunJsonlist = list[i].gunJson;
				for (var j = 0; j < gunJsonlist.length; j += 2) {
					if (gunJsonlist.length > 1) {
						if ((j + 1) <= gunJsonlist.length) {
							html += '<div><div class="fl gun_left">';
							html += '<span>' + gunNostr(gunJsonlist[j].gunNo) + '</span>';
							html += GunstateImg(gunJsonlist[j].gunState);
							// html += '<img class="gun_img dis_block" src="./img/gun14.png" alt="">';
							html +=
								'<img class="checkbox_img dis_none" src="./img/checkbox_normal.png" alt="" onclick="changeImg(this)" dataid="' +
								list[i].pileID + '-' + gunJsonlist[j].gunNo + '"> ';
							html += '</div>';

							if ((j + 1) != gunJsonlist.length) {
								html += '<div class="fr gun_right">';
								html += '<span>' + gunNostr(gunJsonlist[j + 1].gunNo) + '</span>';
								// html += '<img class="gun_img dis_block" src="./img/gun14.png" alt="">';
								html += GunstateImg(gunJsonlist[j + 1].gunState);
								html +=
									'<img class="checkbox_img dis_none" src="./img/checkbox_normal.png" alt="" onclick="changeImg(this)" dataid="' +
									list[i].pileID + '-' + gunJsonlist[j + 1].gunNo + '">';
								// html += '<span>' + gunJsonlist[j+1].gunNo + '</span>';
								html += '</div>';
							}

							html += '</div>';

							gun_detailshtml += '<div class="dis_none gun_details">';
							gun_detailshtml += GunstateBkImg(gunJsonlist[j].gunState);
							gun_detailshtml += '<span>' + gunJsonlist[j].pileName + '</span><span>' + gunNostr(gunJsonlist[j].gunNo) +
								'</span>枪</div>';
							gun_detailshtml += '<ul style="margin-top:10px;">';
							gun_detailshtml += '<li>当前SOS：<span>' + gunJsonlist[j].soc + '</span><span>%</span></li>';
							gun_detailshtml += '<li>剩余时间：<span>' + gunJsonlist[j].remainTime + '</span><span>分钟</span></li>';
							gun_detailshtml += '<li>充电电流：<span>' + gunJsonlist[j].current + '</span><span>A</span></li>';
							gun_detailshtml += '<li>已充电量：<span>' + gunJsonlist[j].power + '</span><span>KWH</span></li>';
							gun_detailshtml += '</ul> ';
							gun_detailshtml += '<img class="details_close" src="./img/close.png" alt="">';
							gun_detailshtml += '</div>';

							if ((j + 1) != gunJsonlist.length) {
								gun_detailshtml += '<div class="dis_none gun_details">';
								gun_detailshtml += GunstateBkImg(gunJsonlist[j + 1].gunState);
								gun_detailshtml += '<span>' + gunJsonlist[j + 1].pileName + '</span><span>' + gunNostr(gunJsonlist[j + 1].gunNo) +
									'</span>枪</div>';
								gun_detailshtml += '<ul style="margin-top:10px;">';
								gun_detailshtml += '<li>当前SOS：<span>' + gunJsonlist[j + 1].soc + '</span><span>%</span></li>';
								gun_detailshtml += '<li>剩余时间：<span>' + gunJsonlist[j + 1].remainTime + '</span><span>分钟</span></li>';
								gun_detailshtml += '<li>充电电流：<span>' + gunJsonlist[j + 1].current + '</span><span>A</span></li>';
								gun_detailshtml += '<li>已充电量：<span>' + gunJsonlist[j + 1].power + '</span><span>KWH</span></li>';
								gun_detailshtml += '</ul> ';
								gun_detailshtml += '<img class="details_close" src="./img/close.png" alt="">';
								gun_detailshtml += '</div>';
							}
						}

					} else {
						html += '<div><div class="fl gun_left">';
						html += '<span>' + gunNostr(gunJsonlist[j].gunNo) + '</span>';
						html += GunstateImg(gunJsonlist[j].gunState);
						// html += '<img class="gun_img dis_block" src="./img/gun14.png" alt="">';
						html +=
							'<img class="checkbox_img dis_none" src="./img/checkbox_normal.png" alt="" onclick="changeImg(this)" dataid="' +
							list[i].pileID + '-' + gunJsonlist[j].gunNo + '">';
						html += '<input class="radio_box dis_none" type="radio" name="" id="">';
						html += '</div>';
						html += '</div>';
						gun_detailshtml += '<div class="dis_none gun_details">';
						gun_detailshtml += GunstateBkImg(gunJsonlist[j].gunState);
						gun_detailshtml += '<span>' + gunJsonlist[j].pileName + '</span><span>' + gunNostr(gunJsonlist[j].gunNo) +
							'</span>枪</div>';
						gun_detailshtml += '<ul style="margin-top:10px;">';
						gun_detailshtml += '<li>当前SOS：<span>' + gunJsonlist[j].soc + '</span><span>%</span></li>';
						gun_detailshtml += '<li>剩余时间：<span>' + gunJsonlist[j].remainTime + '</span><span>分钟</span></li>';
						gun_detailshtml += '<li>充电电流：<span>' + gunJsonlist[j].current + '</span><span>A</span></li>';
						gun_detailshtml += '<li>已充电量：<span>' + gunJsonlist[j].power + '</span><span>KWH</span></li>';
						gun_detailshtml += '</ul> ';
						gun_detailshtml += '<img class="details_close" src="./img/close.png" alt="">';
						gun_detailshtml += '</div>';
					}

				};
				html += '<span>' + list[i].pileName + '</span>';
				html += '</li>';
			}
			$('#gunlist').html(html);
			$('#gunde').html(gun_detailshtml);
			$(".gun_img").each(function (i) {
				$(this).click(function () {
					$('.gun_details').eq(i).removeClass("dis_none").addClass("dis_block");
				})
			})
			$(".details_close").each(function (i) {
				$(this).on('click', function () {
					$('.gun_details').eq(i).removeClass("dis_block").addClass("dis_none");
				})
			})

		}
	})
}

function changeImg(data) {
	var gunid = [];
	if ($(data).attr('src') == './img/checkbox_press.png') {
		$(data).attr('src', './img/checkbox_normal.png')
	} else {
		$(data).attr('src', './img/checkbox_press.png')
	}
	$('.checkbox_img').each(function () {
		if ($(this).attr('src') == './img/checkbox_press.png') {
			gunid.push($(this).attr('dataid'));
		}
	})
	$('#checkedimg').text(gunid.length)

}

$("#c_box").click(function () {
	var gunid = [];
	if ($(this).attr('src') == './img/box_normal.png') {
		$(this).attr('src', './img/box_press.png');
		$(".checkbox_img").attr('src', './img/checkbox_press.png');
		$('.checkbox_img').each(function () {
			if ($(this).attr('src') == './img/checkbox_press.png') {
				gunid.push($(this).attr('dataid'));
			}
			$('#checkedimg').text(gunid.length)
		})

	} else {
		$(this).attr('src', './img/box_normal.png');
		$(".checkbox_img").attr('src', './img/checkbox_normal.png');
		$('#checkedimg').text("0")
	}
})

$('#qidongBtn').on('click', function () {
	var gunid = [];
	$('.checkbox_img').each(function () {
		if ($(this).attr('src') == './img/checkbox_press.png') {
			gunid.push($(this).attr('dataid'));
		}
	})
	if (gunid.length) {
		$.ajax({
			url: "/statistic/statisticOfStationManageAjax/remoteEngineStart.json",
			dataType: 'json',
			type: 'post',
			data: {
				stationId: id,
				pileIdsAndGunNos: gunid.join(","),
				screenFlag: true
			},
			success: function (res) {
				layer.msg(res.msg);
				getGunData(); //枪列表
				$('#checkedimg').text('0');
				$("#start_up1").removeClass("dis_none").addClass("dis_block");
				$("#start_up").removeClass("dis_block").addClass("dis_none");
			}
		})
	} else {
		alert("请选择枪")
	}

})


var mindate = []; //实时功率 时间
var vludata = []; //实时功率 值
var realTimePower_f = 0;
function getRealtimePower() {
	var newtime;
	$.ajax({
		url: "/statistic/gJStationViewAjax/getRealtimePower.json",
		type: "post",
		dataType: "json",
		data: {
			stationID: id
		},
		success: function (res) {
			var myDate = new Date();
			var newhours = myDate.getHours();
			var newmin = myDate.getMinutes();
			if (newmin < 10) {
				newmin = "0" + newmin;
			}
			newtime = newhours + ':' + newmin
			var realTimePower = res.realTimePower;
			$('#realTimePower').text(' ' + realTimePower);
			mindate.push(newtime);
			vludata.push(realTimePower);

			// $('.realTimePower1').lemCounter({
			// 	value_to: realTimePower,
			// 	value_from: realTimePower_f
			// });
			// realTimePower_f = realTimePower;
		}
	})

}


setTimeout(function () {
	getRealtimePower();
	echarts_3(mindate, vludata)
}, 1000)


getStationSumInfoByID(); //根据站点id获取总服务次数，总电量，总收入，充电峰谷,今日总服务次数，总电量，总收入统计
var monthTotalPower_f = 0;
var monthTotalRevenue_f = 0;
var monthServicesNums_f = 0;
function getStationSumInfoByID() {
	$.ajax({
		url: "/statistic/gJStationViewAjax/getStationSumInfoByID.json",
		type: "post",
		dataType: "json",
		data: {
			stationID: id,
			month: new Date()
		},
		success: function (res) {
			var monthTotalPower = res.monthTotalPower / 1000;
			var monthTotalRevenue = res.monthTotalRevenue / 100;
			var monthServicesNums = res.monthServicesNums;
			if (monthTotalPower > 10000) {
				monthTotalPower = parseFloat((monthTotalPower / 10000).toFixed(2));
				$("#monthTotalPower").text(monthTotalPower);
				$('#monthTotalPowerDw').text("(万kwh)");
			} else {
				monthTotalPower = parseFloat((monthTotalPower).toFixed(2));
				$('#monthTotalPower').text(monthTotalPower);
				$('#monthTotalPowerDw').text("(kwh)");	
			}
			$('#monthTotalPower').lemCounter({
				value_to: monthTotalPower,
				value_from: monthTotalPower_f
			});	
			monthTotalPower_f = monthTotalPower;

			if (monthTotalRevenue > 10000) {
				monthTotalRevenue = parseFloat((monthTotalRevenue / 10000).toFixed(2));
				$('#monthTotalRevenue').text(monthTotalRevenue);
				$('#monthTotalRevenueDw').text("(万元)");
			} else {
				monthTotalRevenue = parseFloat((monthTotalRevenue).toFixed(2));
				$('#monthTotalRevenue').text(monthTotalRevenue);
				$('#monthTotalRevenueDw').text("(元)");
			}
			$('#monthTotalRevenue').lemCounter({
				value_to: monthTotalRevenue,
				value_from: monthTotalRevenue_f
			});
			monthTotalRevenue_f = monthTotalRevenue;

			if (monthServicesNums > 10000) {
				monthServicesNums = parseFloat((monthServicesNums / 10000).toFixed(2));
				$('#monthServicesNums').text(monthServicesNums);
				$('#monthServicesNumsDw').text("(万个)");
			} else {
				monthServicesNums = parseFloat(monthServicesNums);
				$('#monthServicesNums').text(monthServicesNums);
				$('#monthServicesNumsDw').text("(个)");
			}
			$('#monthServicesNums').lemCounter({
				value_to: monthServicesNums,
				value_from: monthServicesNums_f
			});
			monthServicesNums_f = monthServicesNums;
		}
	})
}

TolgetStationSumInfoByID(); //根据站点id获取总服务次数，总电量，总收入，充电峰谷,今日总服务次数，总电量，总收入统计
var todayTotalPower_f = 0;
var todayTotalRevenue_f = 0;
var todayServicesNums_f = 0;
function TolgetStationSumInfoByID() {
	$.ajax({
		url: "/statistic/gJStationViewAjax/getStationSumInfoByID.json",
		type: "post",
		dataType: "json",
		data: {
			stationID: id,
		},
		success: function (res) {
			var todayTotalPower = res.todayTotalPower / 1000;
			var todayTotalRevenue = res.todayTotalRevenue / 100;
			if (todayTotalPower > 10000) {
				var todayTotalPower = parseFloat((todayTotalPower / 10000).toFixed(2));
				$('#todayTotalPower').text(todayTotalPower);
				$('#todayTotalPowerDw').text("(万kwh)");

				$('#todayTotalPower').lemCounter({
					value_to: todayTotalPower,
					value_from: todayTotalPower_f
				});
				todayTotalPower_f = todayTotalPower;

			} else {
				var todayTotalPower = parseFloat((todayTotalPower).toFixed(2));
				$('#todayTotalPower').text(todayTotalPower);
				$('#todayTotalPowerDw').text("(kwh)");

				$('#todayTotalPower').lemCounter({
					value_to: todayTotalPower,
					value_from: todayTotalPower_f
				});
				todayTotalPower_f = todayTotalPower;

			}
			if (todayTotalRevenue > 10000) {
				var todayTotalRevenue = parseFloat((todayTotalRevenue / 10000).toFixed(2));
				$('#todayTotalRevenue').text(todayTotalRevenue);
				$('#todayTotalRevenueDw').text("(万元)");

				$('#todayTotalRevenue').lemCounter({
					value_to: todayTotalRevenue,
					value_from: todayTotalRevenue_f
				});
				todayTotalRevenue_f = todayTotalRevenue;

			} else {
				var todayTotalRevenue = parseFloat((todayTotalRevenue).toFixed(2));
				$('#todayTotalRevenue').text(todayTotalRevenue);
				$('#todayTotalRevenueDw').text("(元)");

				$('#todayTotalRevenue').lemCounter({
					value_to: todayTotalRevenue,
					value_from: todayTotalRevenue_f
				});
				todayTotalRevenue_f = todayTotalRevenue;
				
			}

			// $('#todayTotalPower').text(numBuWan(res.todayTotalPower / 1000));
			// $('#todayTotalRevenue').text(numBuWan(res.todayTotalRevenue / 100));
			$('#todayServicesNums').text(numBuWan(res.todayServicesNums));

			$('#todayServicesNums').lemCounter({
				value_to: res.todayServicesNums,
				value_from: todayServicesNums_f
			});
			todayServicesNums_f = res.todayServicesNums;

		}
	})
}

//获取地址栏参数

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}

function gunNostr(gunNo) {
	switch (gunNo) {
		case "0":
			return "A";
		case "1":
			return "B";
		case "2":
			return "C";
		case "3":
			return "D";
		case "4":
			return "E";
		case "5":
			return "F";
		case "6":
			return "G";
		case "7":
			return "H";
		case "8":
			return "I";
		case "9":
			return "J";
		case "10":
			return "K";
		case "11":
			return "L";
		case "12":
			return "M";
		case "13":
			return "N";
		case "14":
			return "D";
		case "15":
			return "O";
		case "16":
			return "P";
		case "17":
			return "Q";
		default:
			return "A";
	}
}

function GunstateImg(gunstate) {
	switch (gunstate) {
		case "OFFLINE":
			return '<img class="gun_img dis_block" src="./img/gun16.png" alt="" >';
		case "CHARGING":
			return '<img class="gun_img dis_block" src="./img/gun11.png" alt="">';
		case "FREE":
			return '<img class="gun_img dis_block" src="./img/gun15.png" alt="">';
		case "CHARGEFINISH":
			return '<img class="gun_img dis_block" src="./img/gun12.png" alt="">';
		case "CHARGEPREPARE":
			return '<img class="gun_img dis_block" src="./img/gun13.png" alt="">';
		case "BESPEAK":
			return '<img class="gun_img dis_block" src="./img/gun14.png" alt="">';
		case "Problem":
			return '<img class="gun_img dis_block" src="./img/gun10.png" alt="">';
		default:
			return '<img class="gun_img dis_block" src="./img/gun15.png" alt="">';
	}
}


function GunstateBkImg(gunstate) {
	switch (gunstate) {
		case "OFFLINE":
			return '<div style="background: url(./img/gun_6.png) no-repeat;background-size: 60% 100%;">';
		case "CHARGING":
			return '<div style="background: url(./img/gun_2.png) no-repeat;background-size: 60% 100%;">';
		case "FREE":
			return '<div style="background: url(./img/gun_5.png) no-repeat;background-size: 60% 100%;">';
		case "CHARGEFINISH":
			return '<div style="background: url(./img/gun_3.png) no-repeat;background-size: 60% 100%;">';
		case "CHARGEPREPARE":
			return '<div style="background: url(./img/gun_1.png) no-repeat;background-size: 60% 100%;">';
		case "BESPEAK":
			return '<div style="background: url(./img/gun_7.png) no-repeat;background-size: 60% 100%;">';
		case "Problem":
			return '<div style="background: url(./img/gun_4.png) no-repeat;background-size: 60% 100%;">';
		default:
			return '<div style="background: url(./img/gun_5.png) no-repeat;background-size: 60% 100%;">';
	}
}

function echarts_1(xdata, serdata) {
	var myChart = echarts.init(document.getElementById("service_vehicle"));
	option = {
		grid: {
			top: '8%',
			right: "4%",
			left: "12%"
		},
		tooltip: {
			trigger: 'axis'
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: xdata,
			axisLabel: {
				textStyle: {
					color: '#8BC6FB'
				}
			},
			axisTick: {
				show: false
			},
			splitLine: {
				show: false
			},
			axisLine: {
				lineStyle: {
					color: '#354972'
				}
			},
			axisLabel: {
				color: '#5E8DCE'
			}
		},
		yAxis: {
			type: 'value',
			nameTextStyle: {
				color: '#8F97A3',
				width: 15,
				align: 'right',
			},
			min: 0,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'dotted',
					color: '#1A3A71'
				}
			},
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			axisLine: {
				show: false
			},
			scale: true,
		},
		// dataZoom: [{
		// 	type: 'slider',
		// 	show: false,
		// 	start: 60,
		// 	end: 100,
		// 	handleSize: 15
		// },
		// {
		// 	type: 'inside',
		// 	start: 60,
		// 	end: 100
		// },
		// {
		// 	type: 'slider',
		// 	show: false,
		// 	yAxisIndex: 0,
		// 	filterMode: 'empty',
		// 	width: 6,
		// 	height: '70%',
		// 	handleSize: 15,
		// 	showDataShadow: false,
		// 	left: '93%'
		// }
		// ],
		series: [{
			data: serdata,
			type: 'line',
			symbol: 'none',
			smooth: true,
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(0,162,211,0.4)'
					}, {
						offset: 1,
						color: 'rgba(0,162,211,0)'
					}])
				}
			},
			itemStyle: {
				color: '#00CEF7',
			}
		}]
	};
	myChart.setOption(option);

}


function echarts_2(xdate, yvalue1, yvalue2) {
	var myChart = echarts.init(document.getElementById("station_operation"));
	option = {
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				crossStyle: {
					color: '#8BC6FB'
				}
			}
		},
		grid: {
			left: '15%',
			right: '10%',
			top: '6%'
		},
		legend: {
			bottom: 0,
			data: [{
				name: '收入',
				icon: 'rect',
			}, {
				name: '服务车辆',
				icon: 'rect'
			}],
			textStyle: {
				color: '#8BC6FB'
			}
		},
		dataZoom: [{
			type: 'slider',
			show: false,
			start: 60,
			end: 100,
			handleSize: 15
		},
		{
			type: 'inside',
			start: 60,
			end: 100
		},
		{
			type: 'slider',
			show: false,
			yAxisIndex: 0,
			filterMode: 'empty',
			width: 6,
			height: '70%',
			handleSize: 15,
			showDataShadow: false,
			left: '93%'
		}
		],
		xAxis: [{
			type: 'category',
			min: 0,
			data: xdate,
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			axisTick: {
				show: false
			},
			axisLine: {
				lineStyle: {
					color: '#354972'
				}
			}
		}],
		yAxis: [{
			type: 'value',
			name: '万 \n 元',
			nameRotate: 360,
			nameGap: 28,
			nameLocation: 'center',
			min: 0,
			// max: 10,
			// interval: 2,
			axisLine: {
				show: false
			},
			axisTick: {
				show: false
			},
			splitLine: {
				lineStyle: {
					type: 'dotted',
					color: '#1A3A71'
				},
				show: true
			},
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			nameTextStyle: {
				color: '#5E8DCE'
			},
			scale: true,
		},
		{
			type: 'value',
			name: '车 \n 辆',
			nameRotate: 360,
			nameGap: 28,
			nameLocation: 'center',
			min: 0,
			// max: 100,
			// interval: 20,
			axisLine: {
				show: false
			},
			axisTick: {
				show: false
			},
			splitLine: {
				lineStyle: {
					type: 'dotted',
					color: '#1A3A71'
				},
				show: true
			},
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			nameTextStyle: {
				color: '#5E8DCE'
			},
			scale: true,
		}
		],
		series: [

			{
				name: '服务车辆',
				type: 'line',
				yAxisIndex: 1,
				data: yvalue1,
				smooth: true,
				symbol: 'none',
				itemStyle: {
					color: '#EFA223'
				}
			},
			{
				name: '收入',
				type: 'bar',
				data: yvalue2,
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[{
								offset: 0,
								color: '#00E5FF'
							},
							{
								offset: 0.5,
								color: '#0CCEF9'
							},
							{
								offset: 1,
								color: '#3489E8'
							}
							]
						)
					}
				}
			}
		]
	};
	myChart.setOption(option);
}


function echarts_3(newdate, newvlue) {
	var myChart = echarts.init(document.getElementById("time_power"));
	option = {
		grid: {
			top: '10%',
			left: '5%'
		},
		tooltip: {
			trigger: 'axis'
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: newdate,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'solid',
					color: '#00459D'
				}
			},
			axisLine: {
				lineStyle: {
					color: '#00459D'
				}
			},
			axisLabel: {
				color: '#8BC6FB'
			}
		},
		yAxis: {
			type: 'value',
			min: 0,
			// max: 8,
			// interval: 1,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'solid',
					color: '#00459D'
				}
			},
			axisLabel: {
				show: false
			},
			axisLine: {
				lineStyle: {
					color: '#00459D'
				}
			},
			scale: true
		},
		series: [{
			data: newvlue,
			type: 'line',
			symbol: 'none',
			emphasis: {
				itemStyle: {
					color: '#00D7FF'
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(0,162,211,0.5)'
					}, {
						offset: 1,
						color: 'rgba(0,162,211,0)'
					}])

				}
			},
			itemStyle: {
				color: '#00A2D3',
			}
		}]
	};
	myChart.setOption(option);
}


function numBuWan(num) {
	if (num > 10000) {
		return parseFloat(num / 10000).toFixed(2) + "万"
	} else {
		return num
	}
}

