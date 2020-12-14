var data=[];
//生成随机数据
function random(min, max) {
	return Math.floor(Math.random() * (max - min)) + min;
}
getStationBySeller(); //站点总览地图数据
function getStationBySeller() {
	$.ajax({
		url: "/statistic/screenNewAjax/getStationBySeller.json",
		type: "post",
		dataType: "json",
		data: {},
		success: function(res) {
			var stationList=res.stationList;
			for(var i=0;i<stationList.length;i++){
				data[i]={
					"name":stationList[i].name,
					"value":[parseFloat(stationList[i].longitude),parseFloat(stationList[i].latitude),random(3, 19)],
					"id":stationList[i].id
				}
			}
			var option = {
						title: {
							show: false
						},
						geo: [{
							name: '广州',
							type: 'map',
							map: 'gz', //地图注册名称
							zoom: 1, //地图缩放
							label: {
								normal: {
									show: true,
									color: '#fff'
								},
								emphasis: {
									show: true,
									color: '#fff'
								}
							},
							roam: true, //移动
							itemStyle: {
								normal: {
									borderColor: '#81dcf3',
									borderWidth: 1,
									areaColor: {
										type: 'radial',
										x: 0.5,
										y: 0.5,
										r: 0.8,
										colorStops: [{
											offset: 0,
											color: 'rgba(147, 235, 248, 0)' // 0% 处的颜色
										}, {
											offset: 1,
											color: 'rgba(147, 235, 248, .2)' // 100% 处的颜色
										}],
										globalCoord: false // 缺省为 false
									},
									shadowColor: 'rgba(128, 217, 248, 1)',
									shadowBlur: 10
								},
								emphasis: { 
									areaColor: '#389BB7',
									borderWidth: 1,
									areaColor: 'rgba(3,90, 219, 0.5)',
									shadowColor: 'rgba(128, 217, 248, 1)',
									shadowBlur: 10
								}
							}
						}],
						series: [{
							type: 'scatter',
							coordinateSystem: 'geo',
							data: data,
							symbolSize: function(val) {
								return val[2];
							},
							label: {
								normal: {
									show: false
								},
								emphasis: {
									show: true,
									formatter: '{b}',
									position: 'right',
									color: '#fff'
								}
							},
							itemStyle: {
								normal: {
									show: true,
									color: "#f8e71c", //字体和点颜色
									label: {
										textStyle: {
											fontSize: 14, //字体大小
											color: "#ffffff"
										}
									},
								}
							},
						}]
					};
			chart2.setOption(option);
		}
	})
}


// function initdata() {
// 	data = []
// 	for (var i = 0; i < 200; i++) {
// 
// 		data[i] = {
// 			"name": "充电站" + i,
// 			"value": [random(113268461, 113825084) / 1000000, random(23131895, 23633367) / 1000000, random(3, 10)]
// 		}
// 	}
// 	console.log(data)
// 	return data
// }


// initdata();
var chart2;
$(function(csJson) {
	
	echarts.registerMap('gz', gzJson);
	chart2 = echarts.init(document.getElementById('map1'));
	
	chart2.on('click', function(e) {
		if (e.componentType == 'series') {
			// alert(data[e.dataIndex].id);
			location.href="./site-overview-inside.html?stationID="+data[e.dataIndex].id;
			console.log(data[e.dataIndex].name)
		}
	})
})
//ajax请求site后刷新数据
// setInterval(function() {
// 	// initdata();
// 	getStationBySeller();
// 	option = chart.getOption();
// 	option.series[0].data = data;
// 	chart.setOption(option, true);
// }, 60000)
