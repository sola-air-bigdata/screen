function GetRequest() {
    var url = decodeURI(location.search); //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
var stationId = GetRequest().stationID;
if (stationId == undefined) {
	stationId = 0;
}
getTeamUserData(stationId);
//站点所有会员
function getTeamUserData(stationId) {
	getTotalRechargeInfo(stationId);
	getTeamMemberStageElectro(stationId);
	getTeamStageElectro(stationId);
	getTeamMemberRank(stationId);
	getTeamMemberConsumeRank(stationId);
}
// 站点单个会员
function getTeamUserDataPer(stationId, memberId) {
	getTotalRechargeInfo(stationId, memberId);
}

// 车队用户总览显示--服务车队用户总数（个）-服务车队总数量（辆）-车队单车充电量（度/次）-车队单车消费（元/次）-车队单车充电时长（小时/辆）
// 车队日充电量（瓦）-车队均充电频率-车队日充电次数（次）
var serviceMember_f = 0;
var serviceTeamCar_f = 0;
var busPower_f = 0;
var busBalance_f = 0;
var busRechargeCount_f = 0;
var memberPower_f = 0;
var memberRechargeCount_f = 0;
var busRechargeTime_f = 0;
function getTotalRechargeInfo(stationId, memberId) {
	$.ajax({
		url: "/statistic/screenNewAjax/getTotalRechargeInfo.json",
		type: "post",
		dataType: "json",
		data: {'stationId' : stationId, 'memberId' : memberId},
		success: function(res) {
			var serviceMember = res.serviceMember;// 服务人数-服务车队用户总数（个）
			var serviceTeamCar = res.serviceTeamCar;// 服务车队-服务车队总数量（辆）
			var busPower = res.busPower;// 车队单车充电量
			var busBalance = res.busBalance;// 车队单车消费
			var busRechargeTime = res.busRechargeTime;// 车队单车充电时长
			if (serviceMember == undefined) {
				serviceMember = 0;
			}
			if (serviceTeamCar == undefined) {
				serviceTeamCar = 0;
			}
			if (busPower == undefined) {
				busPower = 0;
			}
			if (busBalance == undefined) {
				busBalance = 0;
			}
			if (busRechargeTime == undefined) {
				busRechargeTime = 0;
			}
			$("#serviceMember").text(serviceMember);
			$("#serviceTeamCar").text(serviceTeamCar);
			$("#busPower").text(busPower);
			$("#busBalance").text(busBalance);
			$("#busRechargeTime").text(busRechargeTime);
			
			var busRechargeCount = res.busRechargeCount;// 车队均充电频率
			var memberPower = res.memberPower;// 车队日充电量
			var memberRechargeCount = res.memberRechargeCount;// 车队日充电次数
			if (busRechargeCount == undefined) {
				busRechargeCount = 0;
			}
			if (memberPower == undefined) {
				memberPower = 0;
			}
			if (memberRechargeCount == undefined) {
				memberRechargeCount = 0;
			}
			$("#busRechargeCount").text(busRechargeCount);
			$("#memberPower").text(memberPower);
			$("#memberRechargeCount").text(memberRechargeCount);
			
			//数字滚动
			$('#serviceMember').lemCounter({
				value_to:serviceMember,
				value_from:serviceMember_f
			});	
			serviceMember_f = serviceMember;

			$('#serviceTeamCar').lemCounter({
				value_to:serviceTeamCar,
				value_from:serviceTeamCar_f
			});	
			serviceTeamCar_f = serviceTeamCar;

			$('#busPower').lemCounter({
				value_to:busPower,
				value_from:busPower_f
			});	
			busPower_f = busPower;

			$('#busBalance').lemCounter({
				value_to:busBalance,
				value_from:busBalance_f
			});	
			busBalance_f = busBalance;
			
			$('#busRechargeCount').lemCounter({
				value_to:busRechargeCount,
				value_from:busRechargeCount_f
			});	
			busRechargeCount_f = busRechargeCount;

			$('#memberPower').lemCounter({
				value_to:memberPower,
				value_from:memberPower_f
			});	
			memberPower_f = memberPower;

			$('#memberRechargeCount').lemCounter({
				value_to:memberRechargeCount,
				value_from:memberRechargeCount_f
			});	
			memberRechargeCount_f = memberRechargeCount;
			
			$('#busRechargeTime').lemCounter({
				value_to:busRechargeTime,
				value_from:busRechargeTime_f
			});	
			busRechargeTime_f = busRechargeTime;
			
		}
	})
}

//当日车队用户分析
function getTeamMemberStageElectro(stationId) {
	$.ajax({
		url: "/statistic/screenNewAjax/getTeamMemberStageElectro.json",
		type: "post",
		dataType: "json",
		data: {'stationId' : stationId},
		success: function(res) {
			var peakPower = res.peakPower;// 峰充电量
			var flatPower = res.flatPower;// 平充电量
			var ravinePower = res.ravinePower;// 谷充电量
			var peakPer = res.peakPercentage;// 峰充电量百分比
			var flatPer = res.flatPercentage;// 平充电量百分比
			var ravinePer = res.ravinePercentage;// 谷充电量百分比
			if (peakPower == undefined) {
				peakPower = 0;
			}
			if (flatPower == undefined) {
				flatPower = 0;
			}
			if (ravinePower == undefined) {
				ravinePower = 0;
			}
			if (peakPer == undefined) {
				peakPer = 0;
			}
			if (flatPer == undefined) {
				flatPer = 0;
			}
			if (ravinePer == undefined) {
				ravinePer = 0;
			}
			$("#peakPower").text(peakPower);
			$("#flatPower").text(flatPower);
			$("#ravinePower").text(ravinePower);
			$("#peakPer").text(peakPer);
			$("#flatPer").text(flatPer);
			$("#ravinePer").text(ravinePer);
		}
	})
}

// 车队峰平谷分析数据
function getTeamStageElectro(stationId) {
	$.ajax({
		url: "/statistic/screenNewAjax/getTeamStageElectro.json",
		type: "post",
		dataType: "json",
		data: {'stationId' : stationId},
		success: function(res) {
			//车队峰平谷分析数据
		    var timeList = res[0].timeList;
			var dataArr1 = res[0].peakPowerList;
		    var dataArr2 = res[0].flatPowerList;
		    var dataArr3 = res[0].ravinePowerList;
            if (timeList == undefined) {
            	timeList =  [];
			}
            if (dataArr1 == undefined) {
				dataArr1 = [];
			}
            if (dataArr2 == undefined) {
				dataArr2 = [];
			}
            if (dataArr3 == undefined) {
				dataArr3 = [];
			}
		    var line1 = echarts.init(document.getElementById("analysis")); 
            line1.setOption(lineChartOptions(timeList,dataArr1,dataArr2,dataArr3));   //车队峰平谷分析
		}
	})
}

function lineChartOptions(arrData,data1,data2,data3) {
    var option = {
	    tooltip: {
	        trigger: 'axis'
	    },
        legend: {
            data:['平','谷','峰'],
            bottom:0,
            icon:'rect',
            itemWidth:12,
            itemHeight:12,
            textStyle:{
				color:'#8BC6FB',
				fontSize:12
            }
        },
        grid: {
            left: '2%',
            right: '15%',
            bottom: '18%',
            top:'10%',
            containLabel: true
        },
        xAxis:{
            type: 'category',
            boundaryGap: false,
            data: arrData,
            axisTick:{
                show:false
            },
            splitLine:{
                show:false
            },
            axisLine:{
                lineStyle:{
                    color:'#354972'
                }
            },
            axisLabel:{
                color:'#5E8DCE'
            }
        },
        yAxis:{
			name : '',
			nameGap:5,
            type: 'value',
            axisTick:{
                show:false
            },
            splitLine:{
                show:true,
                lineStyle:{
                    type:'dotted',
                    color: '#1A3A71'
                }
            },
            axisLabel:{
                textStyle: {
                    color: '#5E8DCE'
                }
            },
            axisLine:{
                show:false
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
        series : [
            {
                name:'谷',
                type:'line',
                stack: '总量',
                symbol:'none',
                smooth:true,
                areaStyle:{
                    // origin:'end',
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {offset: 0,color: 'rgba(244,166,35,0.5)'}, 
                            {offset: 1,color: 'rgba(244,166,35,0)'}
                        ])

                    } 
                },
                itemStyle:{
                    color:'#F4A623',
                },
                data:data1
            },
            {
                name:'平',
                type:'line',
                stack: '总量',
                symbol:'none',
                smooth:true,
                areaStyle:{
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[
                            {offset: 0, color: 'rgba(0,162,211,0.5)'},
                            {offset: 1, color: 'rgba(0,162,211,0)'}
                        ])
                    }
                },
                itemStyle:{
                    color:'#00CEF7',
                },
                data:data2
            },
            {
                name:'峰',
                type:'line',
                // stack: '总量',
                symbol:'none',
                smooth:true,
                areaStyle:{
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[
                            {offset: 0, color: 'rgba(254,89,89,0.5)'},
                            {offset: 1, color: 'rgba(254,89,89,0)'}
                        ])
                    }
                },
                itemStyle:{
                    color:'#FE5959',
                },
                data:data3
            }
        ]
    };
    return option;
}

//车队单车充电频率排名
function getTeamMemberRank(stationId) {
	var count = 3;
	$.ajax({
		url: "/statistic/screenNewAjax/getTeamMemberRank.json",
		type: "post",
		dataType: "json",
		data: {'stationId' : stationId,'count' : count},
		success: function(res) {
			for (var i = 0;i < count; i++) {
				if(i < res.length) {
					var name = res[i].name;
					var avePower = res[i].avePower;
					var frePower = res[i].frePower;
					var upOrDown = res[i].upOrDown;
					$("#carName" + i).text(res[i].name);
					$("#carPower" + i).text(res[i].avePower);
					$("#carRank" + i).text(res[i].frePower);
					if (upOrDown == 1) {
						document.getElementById("imgArrow" + i).src="./img/arrow-up.png"
					} else {
						document.getElementById("imgArrow" + i).src="./img/arrow-down.png"
					}
				} else {
					$("#carName" + i).text("--");
					$("#carPower" + i).text("--");
					$("#carRank" + i).text("--");
					document.getElementById("imgArrow" + i).src=""
				}
			}
		}
	})
}


//车队单车充电频率排名
function getTeamMemberConsumeRank(stationId) {
	var count = 8;
	$.ajax({
		url: "/statistic/screenNewAjax/getTeamMemberConsumeRank.json",
		type: "post",
		dataType: "json",
		data: {'stationId' : stationId},
		success: function(res) {
			for (var i = 0;i < count; i++) {
				if(i < res.length) {
					var name = res[i].name;
					var actualConsume = res[i].actualConsume;
					var billCount = res[i].billCount;
					var carCount = res[i].carCount;
					var upOrDown = res[i].upOrDown;
					var icon = res[i].icon;
					$("#carCName" + i).text(res[i].name);
					$("#carCMoney" + i).text(res[i].actualConsume);
					$("#carCBill" + i).text(res[i].billTotal);
					$("#carCCount" + i).text(res[i].carCount);
					$("#memberId" + i).text(res[i].memberId);
		            if (icon != undefined && icon != "") {
		            	document.getElementById("carCIcon" + i).src=icon;
					}
					if (upOrDown == 1) {
						document.getElementById("carCImg" + i).src="./img/arrow-up.png"
					} else {
						document.getElementById("carCImg" + i).src="./img/arrow-down.png"
					}
				} else {
					$("#carCName" + i).text("--");
					$("#carCMoney" + i).text("--");
					$("#carCBill" + i).text("--");
					$("#carCCount" + i).text("--");
					document.getElementById("carCImg" + i).src=""
				}
			}
		}
	})
}