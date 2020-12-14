/* //数据格式
    var site = [
       {
           "sitename": "新华站",
           "sitexy": [113.352582, 23.437167],
           "value": "220.21",
       },
       {
           "sitename": "增城站",
           "sitexy": [113.40321, 23.174914],
           "value": "1853.12",
       }
   ]*/

//var site = [];
//// 生成随机数据
//function random(min, max) {
//	return Math.floor(Math.random() * (max - min)) + min;
//}
//
// function initdata(){
// site=[];
// for(var i=0;i<10;i++){
// var obj={"sitename":"充电站"+i,"sitexy":[random(113268461,113825084)/1000000,
// random(23131895, 23633367)/1000000],"value":random(10000,10000000)/100};
// site.push(obj)
// }
// }
// initdata();
getStationByIncome(); // 站点总览地图数据

function getStationByIncome() {
	$
			.ajax({
				url : "/statistic/ScreenNewInCome/GetBalances.json",
				type : "post",
				dataType : "json",
				data : {},
				success : function(res) {
					site = [];
					var stationList = res.data;
					for (var i = 0; i < stationList.length; i++) {
							var obj = {
								'sitename' : stationList[i].stationName,
								'sitexy' : [
									parseFloat(stationList[i].longitude),
									parseFloat(stationList[i].latitude)],
							
								'value' : stationList[i].income,
								'id' : stationList[i].stationId
							};
							site.push(obj)
					}
					var option = {
						title : {
							show : false
						},
						geo : [ {
							name : '广州',
							type : 'map',
							map : 'gz', // 地图注册名称
							// zoom: 2, //地图缩放
							label : {
								normal : {
									show : false
								},
								emphasis : {
									show : false
								}
							},
							roam : true, // 移动
							itemStyle : {
								normal : {
									borderColor : '#7FDBF3',
									borderWidth : 1,
									areaColor : {
										type : 'radial',
										x : 0.5,
										y : 0.5,
										r : 0.8,
										colorStops : [ {
											offset : 0,
											color : 'rgba(147, 235, 248, 0)' // 0%
										// 处的颜色
										}, {
											offset : 1,
											color : 'rgba(147, 235, 248, .2)' // 100%
										// 处的颜色
										} ],
										globalCoord : false
									// 缺省为 false
									},
									shadowColor : 'rgba(128, 217, 248, 1)',
									shadowBlur : 10
								},
								emphasis : {
									areaColor : '#389BB7',
									borderWidth : 1,
									areaColor : 'rgba(3,90, 219, 0.5)',
									shadowColor : 'rgba(128, 217, 248, 1)',
									shadowBlur : 10
								}
							}
						} ],
						series : [ {
							name : "金额标签",
							type : 'custom', // 配置显示方式为用户自定义
							coordinateSystem : 'geo',
							renderItem : function(params, api) { // 具体实现自定义图标的方法
								return {
									type : 'group',
									children : [
											{
												type : 'image',
												position : [ -10, -42 ],
												style : {
													image : "img/ico_site.png",
													x : api
															.coord([
																	site[params.dataIndex].sitexy[0],
																	site[params.dataIndex].sitexy[1] ])[0],
													y : api
															.coord([
																	site[params.dataIndex].sitexy[0],
																	site[params.dataIndex].sitexy[1] ])[1]
												}
											},
											{
												type : 'text',
												position : [ 20, -30 ],
												style : {
													text : site[params.dataIndex].sitename,
													font : '18px "STHeiti"',
													textAlign : 'left',
													fill : '#8dc9ff',
													x : api
															.coord([
																	site[params.dataIndex].sitexy[0],
																	site[params.dataIndex].sitexy[1] ])[0],
													y : api
															.coord([
																	site[params.dataIndex].sitexy[0],
																	site[params.dataIndex].sitexy[1] ])[1]
												}
											},

											{
												type : 'text',
												position : [
														25 + getCharSize(
																site[params.dataIndex].sitename,
																"18px"), -32 ],
												style : {
													text : site[params.dataIndex].value,
													font : '24px "STHeiti"',
													textAlign : 'left',
													fill : '#ffffff',
													x : api
															.coord([
																	site[params.dataIndex].sitexy[0],
																	site[params.dataIndex].sitexy[1] ])[0],
													y : api
															.coord([
																	site[params.dataIndex].sitexy[0],
																	site[params.dataIndex].sitexy[1] ])[1]
												}
											}

									]
								}
							},
							data : site
						} ]
					};
					chart1.setOption(option);
				}
			})
}

var chart1;
$(function() {
	echarts.registerMap('gz', gzJson);
	chart1 = echarts.init(document.getElementById('map'));
	getStationByIncome();

	// 标志点击事件，点击后弹框
	// chart1.on('click', function(e) {
	// 	if (e.componentType == 'series') {
	// 		location.href="./site-overview-inside.html?stationID="+site[e.dataIndex].id;
	// 		console.log(site[e.dataIndex].name)
	// 	}
	// })

})

// ajax请求site后刷新数据
//setInterval(function() {
//	getStationByIncome();
//	chart.resize()
//}, 10000)

// 计算宽度
function getCharSize(text, fontSize) {
	var span = document.createElement("span");
	var result = {};
	result.width = span.offsetWidth;
	result.height = span.offsetHeight;
	span.style.visibility = "hidden";
	span.style.fontSize = fontSize;
	span.style.fontFamily = "STHeiti";
	span.style.display = "inline-block";
	document.body.appendChild(span);
	if (typeof span.textContent != "undefined") {
		span.textContent = text;
	} else {
		span.innerText = text;
	}
	// return parseFloat(window.getComputedStyle(span).width) - result.width;

	var spanwidth = parseFloat(window.getComputedStyle(span).width)
			- result.width;
	span.remove();
	return spanwidth;
}
