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
getStationInServiceMapData();
	/* var viewData = window.setInterval("getUsersViewData()",30000);
	var trendData = window.setInterval("getUserTrendOfMonthlyData()",60000);
	var statisticData = window.setInterval("getUserCountOfMonthlyAndDayData()",60000); */
	var inServiceMap = window.setInterval("getStationInServiceMapData()",60000);
/* *
 *  获取中间地图站点数据
 * array [] element：stationId(站点ID) stationName(站点名称) lng(经度) lat(纬度) stationInServiceUserCount(站点服务中用户数量)        
 * */
function getStationInServiceMapData(){
	$.ajax({
		url : "/statistic/GuangJiaoUsersViewAjax/getStationInServiceMapData.json",
		data : {},
		dataType : "json",
		type : "POST",
		success : function(data) {
			/* TODO */
			initdata(data);
		},
		error : function() {
			AMUI.dialog.alert({ title: '温馨提示', content: "网络异常！请稍后..."});
		}
	});
 }
var site=[];
function initdata(data){
    site=[];
    for(var i in data){
        var obj={"siteid":data[i].stationId,"sitename":data[i].stationName,"sitexy":[data[i].lng, data[i].lat],"value":data[i].stationInServiceUserCount};
        site.push(obj)
    }
    var option = {
    	    title: {
    	        show: false
    	    },
    	    geo: [{
    	        name: '广州',
    	        type: 'map',
    	        map: 'gz',   //地图注册名称
    	        //zoom: 2,    //地图缩放
    	        label: {
    	            normal: {
    	                show: false
    	            },
    	            emphasis: {show: false}
    	        },
    	        roam: true, //移动
    	        itemStyle: {
    	            normal: {
    	                borderColor: '#81dcf3',
    	                borderWidth: 1,
    	                areaColor: {
    	                    type: 'radial', x: 0.5, y: 0.5, r: 0.8,
    	                    colorStops: [{
    	                        offset: 0, color: 'rgba(147, 235, 248, 0)' // 0% 处的颜色
    	                    }, {
    	                        offset: 1, color: 'rgba(147, 235, 248, .2)' // 100% 处的颜色
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
    	        name: "金额标签",
    	        type: 'custom',                         //配置显示方式为用户自定义
    	        coordinateSystem: 'geo',
    	        renderItem: function (params, api) {    //具体实现自定义图标的方法
    	            return {
    	                type: 'group',
    	                children: [
    	                    {
    	                        type: 'image',
    	                        position: [-10, -42],
    	                        style: {
    	                            image: "img/icon_user.png",
    	                            x: api.coord([site[params.dataIndex].sitexy[0], site[params.dataIndex].sitexy[1]])[0],
    	                            y: api.coord([site[params.dataIndex].sitexy[0], site[params.dataIndex].sitexy[1]])[1]
    	                        }
    	                    },
    	                    {
    	                        type: 'text',
    	                        position: [20, -30],
    	                        style: {
    	                            text: site[params.dataIndex].sitename,
    	                            font: '18px "STHeiti"',
    	                            textAlign: 'left',
    	                            fill: '#8dc9ff',
    	                            x: api.coord([site[params.dataIndex].sitexy[0], site[params.dataIndex].sitexy[1]])[0],
    	                            y: api.coord([site[params.dataIndex].sitexy[0], site[params.dataIndex].sitexy[1]])[1]
    	                        }
    	                    },

    	                    {
    	                        type: 'text',
    	                        position: [25 + getCharSize(site[params.dataIndex].sitename, "18px"), -32],
    	                        style: {
    	                            text: site[params.dataIndex].value,
    	                            font: '24px "STHeiti"',
    	                            textAlign: 'left',
    	                            fill: '#ffffff',
    	                            x: api.coord([site[params.dataIndex].sitexy[0], site[params.dataIndex].sitexy[1]])[0],
    	                            y: api.coord([site[params.dataIndex].sitexy[0], site[params.dataIndex].sitexy[1]])[1]
    	                        }
    	                    }
    	                ]
    	            }
    	        },
    	        data: site
    	    }]
    	};
    chart.setOption(option);
}
var chart;
$(function(){
    echarts.registerMap('gz', gzJson);
    chart = echarts.init(document.getElementById('map2'));
    //标志点击事件，点击后弹框
    chart.on('click', function (e) {
        if (e.componentType == 'series') {
        	location.href="./individual-users-inside.html?stationID="+e.data.siteid;
        }
    })

});



//ajax请求site后刷新数据
/*setInterval(function () {
    initdata();
    chart.resize()
}, 10000)*/


//计算宽度
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
    
    var spanwidth=parseFloat(window.getComputedStyle(span).width) - result.width;
    span.remove();
    return spanwidth;
}
