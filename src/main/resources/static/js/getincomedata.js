var timer4_1 = setTimeout(FiveSec1, 2000); //15秒钟执行一次
function FiveSec1() {
	GetTransBalance(); //执行方法
	clearTimeout(timer4_1);
	setTimeout(FiveSec1, 2000)
};

var timer4_2 = setTimeout(FiveSec2, 2000); //15秒钟执行一次
function FiveSec2() {
	GetTransIncomeRank(); //执行方法
	clearTimeout(timer4_2);
	setTimeout(FiveSec2, 18000)
};

var timer4_3 = setTimeout(FiveSec3, 2000); //15秒钟执行一次
function FiveSec3() {
	IncomeWayData(); //执行方法
	clearTimeout(timer4_3);
	setTimeout(FiveSec3, 2000);
};

GetTransBalance(); // 首页 收入总览数据
var actualBalanceAll_f = 0;
var powerBalanceAll_f = 0;
var otherActualBalance_f = 0;
var otherPowerBalance_f = 0;
var otherPowerPercent_f = 0;
var GongJiaoPowerBalance_f = 0;
function GetTransBalance() {
	$.ajax({
		url: "/statistic/ScreenNewInCome/GetTransBalance.json",
		type: "post",
		dataType: "json",
		data: {},
		success: function(res) {
			if (res.getTransBalance &&
				res.getTransBalance.actualBalanceAll) {
					actualBalanceAll = res.getTransBalance.actualBalanceAll / 100.0
					$('#actualBalanceAll').text(actualBalanceAll);

					powerBalanceAll = res.getTransBalance.powerBalanceAll / 100.0
					$('#powerBalanceAll').text(powerBalanceAll);

					otherActualBalance = res.getTransBalance.otherActualBalance / 100.0
					$('#otherActualBalance').text(otherActualBalance);

					otherPowerBalance = res.getTransBalance.otherPowerBalance / 100.0
					// $('#otherPowerBalance').text(otherPowerBalance);

					if (otherPowerBalance > 10000) {
						otherPowerBalance = parseFloat((otherPowerBalance / 10000).toFixed(2));
						$('#otherPowerBalance').text(otherPowerBalance);
						$('#otherPowerBalance_text').text("万元 /");
					}

					GongJiaoPowerBalance = res.getTransBalance.GongJiaoPowerBalance / 100.0
					$('#GongJiaoPowerBalance').text(GongJiaoPowerBalance);

					GongJiaoActualBalance = res.getTransBalance.GongJiaoActualBalance / 100.0
					$('#GongJiaoActualBalance').text(GongJiaoActualBalance);

					$('#actualBalanceAll').lemCounter({
						value_to: actualBalanceAll,
						value_from:actualBalanceAll_f
					});	
					actualBalanceAll_f = actualBalanceAll;

					$('#powerBalanceAll').lemCounter({
						value_to: powerBalanceAll,
						value_from:powerBalanceAll_f
					});	
					powerBalanceAll_f = powerBalanceAll;

					$('#otherActualBalance').lemCounter({
						value_to: otherActualBalance,
						value_from:otherActualBalance_f
					});	
					otherActualBalance_f = otherActualBalance;

					$('#GongJiaoPowerBalance').lemCounter({
						value_to: GongJiaoPowerBalance,
						value_from:GongJiaoPowerBalance_f
					});	
					GongJiaoPowerBalance_f = GongJiaoPowerBalance;

					$('#otherPowerBalance').lemCounter({
						value_to: otherPowerBalance,
						value_from:otherPowerBalance_f
					});	
					otherPowerBalance_f = otherPowerBalance;

			} else {
				$('#actualBalanceAll').text(0);
				$('#powerBalanceAll').text(0);
				$('#otherActualBalance').text(0);
				$('#otherPowerBalance').text(0);
				$('#GongJiaoPowerBalance').text(0);
				$('#GongJiaoActualBalance').text(0);
			}
			$('#chargingPercent').text(res.chargingPercent + '%');
			$('#numGongJiaoPercent').text(res.numGongJiaoPercent + '%');
			$('#numQitaPercent').text(res.numQitaPercent + '%');
			$('#otherPowerPercent').text(res.otherPowerPercent + '%');
			$('.percentof>li').eq(1).css('width', res.numQitaPercent + '%');
			$('.percentof>li').eq(0).css('width', res.chargingPercent + '%');

			// $('#otherPowerPercent').lemCounter({
			// 	value_to: res.otherPowerPercent,
			// 	value_from:otherPowerPercent_f
			// });	
			// otherPowerPercent_f = res.otherPowerPercent;

			var p3 = echarts.init(document.getElementById("build_income")); // 当日充电收入 公交充电
			p3.setOption(pieChartOptions1('#00F6FF', res.numGongJiaoPercent));

		}
	})
}
GetTransIncomeRank(); // 首页 收入总览数据
function GetTransIncomeRank() {
	$.ajax({
		url: "/statistic/ScreenNewInCome/GetTransIncomeRank.json",
		type: "post",
		dataType: "json",
		data: {},
		success: function(res) {
			if (res.stateCode == 0 && res.getTransIncomeRank &&
				res.getTransIncomeRank[0]) {
				$('#name1').text(res.getTransIncomeRank[0].name);
				$('#actualBalance1').text(res.getTransIncomeRank[0].actualBalance / 100.0 + "元");

			} else {
				$('#name1').text("暂无");
				$('#actualBalance1').text(0); 
			}

			if (res.stateCode == 0 && res.getTransIncomeRank &&
				res.getTransIncomeRank[1]) {
				$('#name2').text(res.getTransIncomeRank[1].name);
				$('#actualBalance2').text(res.getTransIncomeRank[1].actualBalance / 100.0 + "元");
			} else {
				$('#name2').text("暂无");
				$('#actualBalance2').text(0);
			}

			if (res.stateCode == 0 && res.getTransIncomeRank &&
				res.getTransIncomeRank[2]) {
				$('#name3').text(res.getTransIncomeRank[2].name);
				$('#actualBalance3').text(res.getTransIncomeRank[2].actualBalance / 100.0 + "元");
			} else {
				$('#name3').text("暂无");
				$('#actualBalance3').text(0);
			}
		}
	})
}

IncomeWayData(); // 首页 收入总览数据
function IncomeWayData() {
	$.ajax({
		url: "/statistic/ScreenNewInCome/IncomeWayData.json",
		type: "post",
		dataType: "json",
		data: {},
		success: function(res) {
			$('#app').text((res.app / 1.0).toFixed(2) + "元");
			$('#card').text((res.card / 1.0).toFixed(2) + "元");
			$('#wx').text((res.wx / 1.0).toFixed(2) + "元");
			$('#vin').text((res.vin / 1.0).toFixed(2) + "元");
		}
	})
}
