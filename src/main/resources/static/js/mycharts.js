function pieChartOptions(color,color1, percentage,fontSize1,fontSize2,fontSize3,fontWeight,name) {
    var total = 100;
    // var calcVal = total * (percentage / 100);
	var calcVal = percentage;
    var option = {
        color: [color, '#37445F'],
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
                            color: color1,
                            fontSize: fontSize1,
                            lineHeight: fontSize2,
                            fontWeight: fontWeight
                        },
                        b: {
                            color: '#8BC6FB',
                            fontSize: fontSize3,
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
                    value: calcVal,
                    name: name,
                    label: {
                        show: true,
                    }
                },
                {
                    value: total-calcVal,
                    name: 'none',
                    label: {
                        show: false,
                    }
                },
            ]
        }]
    };
    return option;
}
function pieChartOptions1(color, percentage) {
    var total = 100;
    var calcVal = total * (percentage / 100);
    var option = {
        color: [color, '#3A4A97'],
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
                    value: calcVal,
                    name: '对内',
                    label: {
                        show: false,
                    }
                },
                {
                    value: total-calcVal,
                    name: 'none',
                    label: {
                        show: false,
                    }
                },
            ]
        }]
    };
    return option;
}
function pieChartOptions2(color, revenueArr1,revenueArr2) {
    var option = {
        color: color,
		legend: {
			type: 'scroll',
			orient: 'vertical',
			right: '0%',
			top: '18%',
			itemGap: 18,
			data: revenueArr1,
			icon: 'pin',
			textStyle: {
				color: '#8BC6FB',
				fontSize: 12
			}
		},
		tooltip: {
			trigger: 'item'
		},
		series: [{
			name: '收入方式',
			type: 'pie',
			radius: [35, 58],
			center: ['22%', '50%'],
			minAngle: 2, 
			hoverAnimation:false,
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
			itemStyle:{
                		borderWidth:1, //设置border的宽度有多大
                		borderColor:'#334462',
            		},
			data: revenueArr2
		}]
    };
    return option;
}
function barChartOptions(value,textName,dataName1,dataName2,labelShow,left) {
    var option = {
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
			left: left,
			right: 0,
			bottom: 48
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
			dimensions: ['data',dataName1,dataName2],
			source: value
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
			// max:max,
			name: textName,
			nameRotate: 360,
			nameGap: 60,
			nameLocation: 'center',
			nameTextStyle: {
				color: '#5E8DCE'
			},
			axisLabel: {
                show: labelShow,
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
			scale:true,
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
    return option;
}
function lineChartOptions(arrMonth,arrData,title) {
    var option = {
        title: {
			text: title,
			left: '48%',
			top: '1%',
			textStyle: {
				color: '#D2D7DF',
				fontWeight: 500,
				fontStyle: 16
			}
		},
		grid: {
			top: '10%',
			right: '7%',
			left: '12%'
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
			data: arrMonth,
			axisLabel: {
				textStyle: {
					color: '#5E8DCE'
				},
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
			nameGap: 26,
			nameTextStyle: {
				color: '#5E8DCE',
				width: 15,
				align: 'right',
			},
			min: 0,
			// max: 100,
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
			},
			scale:true,
		},
		series: [{
			data: arrData,
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
    return option;
}
function lineChartOptions1(arrMonth,tuanDuiData,geRenData,nameGap) {
	//max:最大值 interval：y轴间隔数
    var option = {
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
			left: 10,
			right: '6%',
			bottom: '14%',
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
			data: arrMonth,
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
			// max: max,
            // interval: interval,
            name: ' ',
			nameLocation: 'center',
			nameRotate: 360,
            nameGap: nameGap,
            nameTextStyle: {
				color: '#5E8DCE',
				width: 15,
				align: 'right',
			},
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
			},
			scale:true,
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
    return option;
}

function numBuWan(num){
	if(num>10000){
		return parseInt(num/10000)+"万"
	}else{
		return num
	}
}