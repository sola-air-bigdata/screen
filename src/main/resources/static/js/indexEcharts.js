/**
 *1累计充电量公交占比 
 */
function echarts_1(datavalue1, datavalue2) {
	var myChart = echarts.init(document.getElementById("charging"));
	option = {
		color: ['#2BDEF7', '#37445F'],
		tooltip: {
			show: false,
		},
		grid: {
			left: '0%',
			right: '0%',
			bottom: '0%',
			containLabel: true
		},
		legend: {
			show: false,
			data: ['运营充电(度)', 'none']
		},
		series: [{
			type: 'pie',
			radius: ['70%', '80%'],
			avoidLabelOverlap: false,
			hoverAnimation: false,
			label: {
				normal: {
					show: false,
					position: 'center',
					formatter: [
						'{a|{c}%}',
						'{b|{b}}'
					].join('\n'),
					rich: {
						a: {
							color: '#fff',
							fontSize: 22,
							lineHeight: 30
						},
						b: {
							color: '#8BC6FB',
							fontSize: 16,
						}
					},
				},
			},
			labelLine: {
				normal: {
					show: false
				}
			},
			data: [{
					value: datavalue1,
					name: '公交占比',
					label: {
						show: true,
					}
				},
				{
					value: datavalue2,
					name: 'none',
					label: {
						show: false,
					}
				},
			]
		}]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	window.addEventListener("resize", function() {
		myChart.resize();
	});
}

/**
 *2 累计充电量柱状图 
 */
function echarts_2(arrdataMonth) {
	var myChart1 = echarts.init(document.getElementById("chart"));
	option1 = {
		legend: {
			bottom: 0,
			icon: 'rect',
			itemWidth: 12,
			itemHeight: 12,
			textStyle: {
				color: '#8BC6FB',
				fontSize: 14
			}
		},
		grid: {
			top: 28,
			left: 48,
			right: 0,
			bottom: 68
		},
		tooltip: {
			trigger: 'axis'
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
		dataset: {
			dimensions: ['data', '公交车', '其他'],
			source: arrdataMonth
		},
		xAxis: {
			type: 'category',
			axisTick: {
				show: false
			},
			axisLine: {
				lineStyle: {
					color: '#354972'
				}
			},
			axisLabel: {
				color: '#5E8DCE',
			},
		},
		yAxis: {
			type: 'value',
			max: 10000000000,
			name: 'K \n W',
			nameRotate: 360,
			nameGap: 35,
			nameLocation: 'center',
			nameTextStyle: {
				color: '#5E8DCE'
			},
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			splitLine: {
				lineStyle: {
					type: 'dashed',
					color: '#1A3A71'
				}
			},
			axisTick: {
				show: false
			},
			axisLine: {
				show: false
			},
		},
		series: [{
				type: 'bar',
				barWidth: 12,
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[{
									offset: 0,
									color: '#00D9F4'
								},
								{
									offset: 0.7,
									color: '#13C2F6'
								},
								{
									offset: 1,
									color: '#2D95EB'
								}
							]
						)
					}
				},
			},
			{
				type: 'bar',
				barWidth: 12,
				color: '#FF9024'
			}
		]
	};
	myChart1.setOption(option1);
	window.addEventListener("resize", function() {
		myChart1.resize();
	});
}

/**
 *3 设备故障率 
 */
function echarts_3(datavalue1, datavalue2) {
	var myChart2 = echarts.init(document.getElementById("failure"));
	option2 = {
		color: ['#FE3737', '#37445F'],
		tooltip: {
			show: false,
		},
		grid: {
			left: '0%',
			right: '0%',
			bottom: '0%',
			containLabel: true
		},
		legend: {
			show: false,
			data: ['运营充电(度)', 'none']
		},
		series: [{
			type: 'pie',
			radius: ['70%', '80%'],
			avoidLabelOverlap: false,
			hoverAnimation: false,
			label: {
				fontStyle: 12,
				normal: {
					show: false,
					position: 'center',
					formatter: [
						'{a|{c}%}',
						'{b|{b}}'
					].join('\n'),
					rich: {
						a: {
							color: '#FE3737',
							fontSize: 14,
							lineHeight: 30,
							fontWeight: 600
						},
						b: {
							color: '#8BC6FB',
							fontSize: 12,
						}
					},
				},
			},
			labelLine: {
				normal: {
					show: false
				}
			},
			data: [{
					value: datavalue1,
					name: '故障率',
					label: {
						show: true,
					}
				},
				{
					value: datavalue2,
					name: 'none',
					label: {
						show: false,
					}
				},
			]
		}]
	};
	myChart2.setOption(option2);
	window.addEventListener("resize", function() {
		myChart2.resize();
	});
}

/**
 *4 设备故障率 按月统计表 
 */

function echarts_4(failure1ArrMonth, failure1Arrdata) {
	var myChart3 = echarts.init(document.getElementById("failure1"));
	option3 = {
		title: {
			text: '按月统计',
			// textAlign:'center',
			left: '48%',
			top: '1%',
			textStyle: {
				color: '#ddd',
				fontWeight: 500,
				fontStyle: 16
			}
		},
		grid: {
			top: '10%',
			right: "6%",
			
		},
		tooltip: {
			trigger: 'axis'
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
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: failure1ArrMonth,
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				},
				// interval: 0,
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
			}
		},
		yAxis: {
			type: 'value',
			name: '故\n\n障\n\n数',
			nameLocation: 'center',
			nameRotate: 360,
			nameGap: 30,
			nameTextStyle: {
				color: '#5E8DCE',
				width: 15,
				align: 'right',
			},
			min: 0,
			max: 100,
			interval: 10,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'dashed',
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
			}
		},
		series: [{
			data: failure1Arrdata,
			type: 'line',
			symbol: 'none',
			smooth: true,
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(254,89,89,0.4)'
					}, {
						offset: 1,
						color: 'rgba(254,89,89,0)'
					}])

				}
			},
			itemStyle: {
				color: '#FE5959',
			}
		}]
	};
	myChart3.setOption(option3);
	window.addEventListener("resize", function() {
		myChart3.resize();
	});
}

/**
 *5 收入运营 总收入趋势 
 */
function echarts_5(trend1Arr) {
	var myChart4 = echarts.init(document.getElementById("trend1"));
	option4 = {
		legend: {
			bottom: 0,
			icon: 'rect',
			itemWidth: 12,
			itemHeight: 12,
			textStyle: {
				color: '#8BC6FB',
				fontSize: 14
			}
		},
		grid: {
			top: 28,
			left: 48,
			right: 0
		},
		tooltip: {},
		dataset: {
			dimensions: ['data', '充电', '其他'],
			source: trend1Arr
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
		xAxis: {
			type: 'category',
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
			},
		},
		yAxis: {
			type: 'value',
			max: 1000,
			nameLocation: 'center',
			nameTextStyle: {
				color: '#5E8DCE'
			},
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			splitLine: {
				lineStyle: {
					type: 'dashed',
					color: '#1A3A71'
				}
			},
			axisTick: {
				show: false
			},
			axisLine: {
				show: false
			},
		},
		series: [{
				type: 'bar',
				barWidth: 16,
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[{
									offset: 0,
									color: '#00D9F4'
								},
								{
									offset: 0.7,
									color: '#13C2F6'
								},
								{
									offset: 1,
									color: '#2D95EB'
								}
							]
						)
					}
				},
			},
			{
				type: 'bar',
				barWidth: 16,
				color: '#FF9024'
			}
		]
	};
	myChart4.setOption(option4);
	window.addEventListener("resize", function() {
		myChart4.resize();
	});
}

/**
 *6 收入运营 非公交总收入趋势 
 */
function echarts_6(trend2Arr) {
	var myChart10 = echarts.init(document.getElementById("trend2"));
	option10 = {
		legend: {
			bottom: 0,
			icon: 'rect',
			itemWidth: 12,
			itemHeight: 12,
			textStyle: {
				color: '#8BC6FB',
				fontSize: 14
			}
		},
		grid: {
			top: 28,
			left: 48,
			right: 0
		},
		tooltip: {},
		dataset: {
			dimensions: ['data', '充电', '其他'],
			source: trend2Arr
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
		xAxis: {
			type: 'category',
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
			},
		},
		yAxis: {
			type: 'value',
			max: 1000,
			nameLocation: 'center',
			nameTextStyle: {
				color: '#5E8DCE'
			},
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				}
			},
			splitLine: {
				lineStyle: {
					type: 'dashed',
					color: '#1A3A71'
				}
			},
			axisTick: {
				show: false
			},
			axisLine: {
				show: false
			},
		},
		series: [{
				type: 'bar',
				barWidth: 16,
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[{
									offset: 0,
									color: '#00D9F4'
								},
								{
									offset: 0.7,
									color: '#13C2F6'
								},
								{
									offset: 1,
									color: '#2D95EB'
								}
							]
						)
					}
				},
			},
			{
				type: 'bar',
				barWidth: 16,
				color: '#FF9024'
			}
		]
	};
	myChart10.setOption(option10);
	window.addEventListener("resize", function() {
		myChart10.resize();
	});
}

/**
 *7 总用户增长趋势
 */

function echarts_7(growthTrendArrmonth, tuanDuiData, geRenData) {
	var myChart5 = echarts.init(document.getElementById("growth_trend"));
	option5 = {
		legend: {
			data: ['个人', '车队'],
			bottom: 0,
			icon: 'rect',
			itemWidth: 12,
			itemHeight: 12,
			textStyle: {
				color: '#8BC6FB'
			}
		},
		grid: {
			left: 0,
			right: '3%',
			bottom: '8%',
			top: '8%',
			containLabel: true
		},
		tooltip: {
			trigger: 'axis'
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
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: growthTrendArrmonth,
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
				color: '#5E8DCE',
			}
		},
		yAxis: {
			type: 'value',
			min: 0,
			max: 1000,
			interval: 200,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'dashed',
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
			}
		},
		series: [{
				name: '车队',
				type: 'line',
				stack: '总量',
				symbol: 'none',
				smooth: true,
				areaStyle: {
					origin: 'end',
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: 'rgba(244,166,35,0.4)'
							},
							{
								offset: 1,
								color: 'rgba(244,166,35,0)'
							}
						])

					}
				},
				itemStyle: {
					color: '#F4A623',
				},
				data: tuanDuiData
			},
			{
				name: '个人',
				type: 'line',
				stack: '总量',
				symbol: 'none',
				smooth: true,
				areaStyle: {
					origin: 'start',
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: 'rgba(0,162,211,0.4)'
							},
							{
								offset: 1,
								color: 'rgba(0,162,211,0)'
							}
						])
					}
				},
				itemStyle: {
					color: '#00CEF7',
				},
				data: geRenData
			}
		]
	};
	myChart5.setOption(option5);
	window.addEventListener("resize", function() {
		myChart5.resize();
	});
}

/**
 *8 用户活跃趋势
 */
function echarts_8(activeTrendArrDate, activeTrendArrTuandui, activeTrendArrGeren) {
	var myChart6 = echarts.init(document.getElementById("active_trend"));
	option6 = {
		legend: {
			data: ['个人', '车队'],
			bottom: 0,
			icon: 'rect',
			itemWidth: 15,
			itemHeight: 15,
			textStyle: {
				color: '#8BC6FB'
			}
		},
		grid: {
			left: 0,
			right: '2%',
			bottom: '8%',
			top: '8%',
			containLabel: true
		},
		tooltip: {
			trigger: 'axis'
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
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: activeTrendArrDate,
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
				color: '#5E8DCE',
				interval: 0,
			}
		},
		yAxis: [{
			type: 'value',
			min: 0,
			max: 1000,
			interval: 200,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'dashed',
					color: ['#1A3A71']
				}
			},
			axisLabel: {
				textStyle: {
					color: '#ddd'
				}
			},
			axisLine: {
				show: false
			}
		}, {
			type: 'value',
			min: 0,
			max: 1000,
			interval: 200,
			axisTick: {
				show: false
			},
			splitLine: {
				show: true,
				lineStyle: {
					type: 'dashed',
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
			}
		}],
		series: [{
				name: '车队',
				type: 'line',
				stack: '总量',
				symbol: 'none',
				smooth: true,
				areaStyle: {
					origin: 'end',
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: 'rgba(244,166,35,0.4)'
							},
							{
								offset: 1,
								color: 'rgba(244,166,35,0)'
							}
						])

					}
				},
				itemStyle: {
					color: '#F4A623',
				},
				data: activeTrendArrTuandui
			},
			{
				name: '个人',
				type: 'line',
				stack: '总量',
				symbol: 'none',
				smooth: true,
				areaStyle: {
					origin: 'start',
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: 'rgba(0,162,211,0.4)'
							},
							{
								offset: 1,
								color: 'rgba(0,162,211,0)'
							}
						])
					}
				},
				itemStyle: {
					color: '#00CEF7',
				},
				data: activeTrendArrGeren
			}
		]
	};
	myChart6.setOption(option6);
	window.addEventListener("resize", function() {
		myChart6.resize();
	});
}

/**
 *9 收入方式统计
 */
function echarts_9(revenueArr1, revenueArr2) {
	var myChart7 = echarts.init(document.getElementById("revenue_statistics"));
	option7 = {
		color: ['#FB6C33', '#86CC6D', '#5AC0EA', '#FFC93C'],
		legend: {
			type: 'scroll',
			orient: 'vertical',
			right: '12%',
			top: '26%',
			itemGap: 20,
			data: revenueArr1,
			icon: 'pin',
			textStyle: {
				color: '#8BC6FB',
				fontSize: 16
			}
		},
		series: [{
			name: '半径模式',
			type: 'pie',
			radius: [50, 95],
			center: ['24%', '50%'],
			roseType: 'radius',
			minAngle: 60,
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
			data: revenueArr2
		}]
	};
	myChart7.setOption(option7);
	window.addEventListener("resize", function() {
		myChart7.resize();
	});
}

/**
 *10 当日充电收入 公交充电
 */
function echarts_10(buildValue1, buildValue2) {
	var myChart8 = echarts.init(document.getElementById("build"));
	option8 = {
		color: ['#00F6FF', '#3A4A97'],
		tooltip: {
			show: false,
		},
		grid: {
			left: '0%',
			right: '0%',
			bottom: '0%',
			containLabel: true
		},
		legend: {
			show: false,
			data: ['运营充电(度)', 'none']
		},
		series: [{
			type: 'pie',
			radius: ['70%', '80%'],
			avoidLabelOverlap: false,
			hoverAnimation: false,
			labelLine: {
				normal: {
					show: false
				}
			},
			data: [{
					value: buildValue1,
					name: '对内',
					label: {
						show: false,
					}
				},
				{
					value: buildValue2,
					name: 'none',
					label: {
						show: false,
					}
				},
			]
		}]
	};
	myChart8.setOption(option8);
	window.addEventListener("resize", function() {
		myChart8.resize();
	});
}

/**
 *11 非公交总收入统计
 */

function echarts_11(totalArr) {
	var myChart9 = echarts.init(document.getElementById("total"));
	option9 = {
		legend: {
			bottom: 0,
			icon: 'rect',
			itemWidth: 12,
			itemHeight: 12,
			textStyle: {
				color: '#8BC6FB',
				fontSize: 14
			}
		},
		grid: {
			top: 28,
			left: 20,
			right: 0
		},
		tooltip: {
			axisPointer: {
				type: 'shadow'
			},
			trigger: 'axis',
		},
		dataset: {
			dimensions: ['data', '个人', '车队'],
			source: totalArr
		},
		xAxis: {
			type: 'category',
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				},
			},
			axisTick: {
				show: false
			},
			axisLine: {
				lineStyle: {
					color: '#5E8DCE'
				}
			},
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
		yAxis: {
			type: 'value',
			max: 1000,
			nameLocation: 'center',
			nameTextStyle: {
				color: '#8F97A3'
			},
			axisLabel: {
				show: true,
				textStyle: {
					color: '#5E8DCE'
				}
			},
			splitLine: {
				lineStyle: {
					type: 'dashed',
					color: '#1A3A71'
				}
			},
			axisTick: {
				show: false
			},
			axisLine: {
				show: false
			},
		},
		series: [{
				type: 'bar',
				barWidth: 8,
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(
							0, 0, 0, 1,
							[{
									offset: 0,
									color: '#00D9F4'
								},
								{
									offset: 0.7,
									color: '#13C2F6'
								},
								{
									offset: 1,
									color: '#2D95EB'
								}
							]
						)
					}
				},
			},
			{
				type: 'bar',
				barWidth: 8,
				color: '#FF9024'
			}
		]
	};
	myChart9.setOption(option9);
	window.addEventListener("resize", function() {
		myChart9.resize();
	});
}


function numBuWan(num){
	if(num>10000){
		return parseInt(num/10000)+"万"
	}else{
		return num
	}
}