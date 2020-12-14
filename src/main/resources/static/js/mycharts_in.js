/*
var arrData = [0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30];
var arrData1 = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24];*/


function lineChartOptions2(arrData,data,smooth) {
    var option = {
		grid:{
			top:'16%',
			right:'10%',
			left:'10%',
		},
		tooltip: {
			trigger: 'axis'
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: arrData,
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
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
			}
		},
		yAxis: {
			type: 'value',
			nameTextStyle:{
				color:'#8F97A3',
				width:15,
				align: 'right',
			},
			min:0,
			
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
			},
			scale:true,
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
		series: [{
			data: data,
			type: 'line',
			symbol:'none',
			smooth:smooth,		//曲线是否平滑
			areaStyle:{
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0,0, 1, [{
						offset: 0,
						color: 'rgba(0,162,211,0.4)'
					}, {
						offset: 1,
						color: 'rgba(0,162,211,0)'
					}])
				}
			},
			itemStyle:{
				color:'#00CEF7',
			}
		}]
	};
    return option;
}
function pieChartOptions1(color,data) {
    var option = {
		color:color,
		tooltip: {
			trigger: 'item'
		},
		series : [
			{
				name:'忠实度统计',
				type:'pie',
				radius : [65, 80],
				center : ['50%', '60%'],
				roseType : 'radius',
				//minAngle:60,
				label: {
					normal: {
						show: false
					},
					emphasis: {
						show: false
					}
				},
				lableLine: {
					normal: {
						show: false
					},
					emphasis: {
						show: false
					}
				},
				data:data
			}
		]
	};  
    return option;
}
function pieChartOptions2(color,data) {
    var option = {
		color:color,
		series: [
			{
				type:'pie',
				radius: ['50%', '55%'],
				avoidLabelOverlap: false,
				hoverAnimation: false,
				labelLine: {
					normal: {
						show: false
					}
				},
				data:data
			}
		]
	};  
    return option;
}

function pieChartOptions3(color,data) {
    var option = {
		color:color,
		tooltip: {
			trigger: 'item'
		},
		series : [
			{
				name:'忠实度统计',
				type:'pie',
				radius : [40, 55],
				center : ['50%', '50%'],
				roseType : 'radius',
				//minAngle:60,
				label: {
					normal: {
						show: false
					},
					emphasis: {
						show: false
					}
				},
				lableLine: {
					normal: {
						show: false
					},
					emphasis: {
						show: false
					}
				},
				data:data
			}
		]
	};  
    return option;
}


/*var userArr = [240,400,400,401,500,540,592,664,666,788,824,824,924,952,952,998];
var line2 = echarts.init(document.getElementById("user_growth"));    
line2.setOption(lineChartOptions2(arrData,userArr,1000,200,true));   //新用户增长数 

var userArr1 = [2,2,2,20,40,42,48,56,32,28,40,64,56,40,38,40,64,76,80,86,80,74,70,64,50];		//个人用户
var line3 = echarts.init(document.getElementById("charging_time"));    
line3.setOption(lineChartOptions2(arrData1,userArr1,100,20,false));   */

/*var color = ['#FB6C33','#86CC6D','#5AC0EA'];
var statisticsArr = [
	{value:25, name:'WIN 0.7万元'},
	{value:20, name:'充电卡 63.3万元'},
	{value:55, name:'APP 35.1万元'}];
var pie1 = echarts.init(document.getElementById("user_statistics"));    //忠实粉丝用户统计
pie1.setOption(pieChartOptions1(color,statisticsArr));  */

/*var color = ['#5BE7BD', '#10349A']; 
var proportionArr = [
	{ value: 20, name: '运营充电(度)', label: { show: true, } },
	{ value: 80, name: 'none', label: { show: false, } }];
var pie2 = echarts.init(document.getElementById("fans"));    //忠实粉丝占比
pie2.setOption(pieChartOptions2(color,proportionArr)); */