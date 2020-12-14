	var p5 = echarts.init(document.getElementById("site_build"));    //充电站 站点总览
           
	//getRealPower();//实时数据  刷新 5s
	
	/**
	 * 定时器
	 * 
	 */ 
	//var timer1_2=setTimeout(FiveSec6,60000);//5秒钟执行一次
	//function FiveSec6(){
	//	getRealPower();//执行方法
	//	clearTimeout(timer1_2);
	//	setTimeout(FiveSec6,60000)
	//};
	
	var sitechargingPercent_f = 0;
	var offLinePercent_f = 0;
	var problemPercent_f = 0;
	var realTimePower_f = 0;
	function getRealPower(){
		console.log("+++")
		$.ajax({
			url:"/statistic/screenNewAjax/getRealPower.json",
			type:"post",
			dataType:"json",
			data:{},
			success:function(res){
				var realTimePower = res.realTimePower; //设备运营 实时功率
				$('.realTimePower').text(realTimePower);
				
				var chargingPercent = res.chargingPercent; ///显示当前充电电桩的百分比值
				if (chargingPercent == undefined) {
					chargingPercent = 0;
				}
				var chargingPercentceil=Math.ceil(chargingPercent);
				$('#chargingPercentBar').attr("aria-valuenow", chargingPercentceil);
				$('.chargingPercentBar1').text(chargingPercent + '%');
				$('.sitechargingPercent').text(chargingPercent);

				
				var problemPercent=res.problemPercent;//设备运营 实时功率
				$('#problemPercent').text(problemPercent);
				var offLinePercent=res.offLinePercent;///显示当前充电电桩的百分比值
				$('#offLinePercent').text(offLinePercent);


				$('.sitechargingPercent').lemCounter({
					value_to: res.chargingPercent,
					value_from:sitechargingPercent_f
				});	
				sitechargingPercent_f = res.chargingPercent;

				$('#offLinePercent').lemCounter({
					value_to: offLinePercent,
					value_from:offLinePercent_f
				});	
				offLinePercent_f = offLinePercent;

				$('#problemPercent').lemCounter({
					value_to: problemPercent,
					value_from:problemPercent_f
				});	
				problemPercent_f = problemPercent;
				
				$('.realTimePower').lemCounter({
					value_to: realTimePower,
					value_from:realTimePower_f
				});	
				realTimePower_f = realTimePower;
			}
		})
	}
	
	stationAllData();//站点总览数据
	var stationstatioTotal_f = 0;
	var stationpileCount_f = 0;
	var stationgunCount_f = 0;
	var inConstructionCount_f = 0;
	var toBeBulitCount_f = 0;
	var insideCount_f = 0;
	function stationAllData(){
		$.ajax({
			url:"/statistic/screenNewAjax/stationAllData.json",
			type:"post",
			dataType:"json",
			data:{},
			success:function(res){
				var station=res.station;
				$('.stationpileCount').text(station.pileCount);
				$('.stationgunCount').text(station.gunCount);
				$('.stationstatioTotal').text(station.statioTotal);
				$('.inConstructionCount').text(station.inConstructionCount);
				$('.toBeBulitCount').text(station.toBeBulitCount);
				$('.outsideCount').text('/'+station.outsideCount);
				$('.outsideRate').text(station.outsideRate+'%');
				$('.insideCount').text(station.insideCount);
				$('.insideRate').text(station.insideRate+'%');
				p5.setOption(pieChartOptions1('#00F6FF', station.insideRate)); 

				$('.stationstatioTotal').lemCounter({
					value_to: station.statioTotal,
					value_from:stationstatioTotal_f
				});	
				stationstatioTotal_f = station.statioTotal;

				$('.stationpileCount').lemCounter({
					value_to: station.pileCount,
					value_from:stationpileCount_f
				});	
				stationpileCount_f = station.pileCount;

				$('.stationgunCount').lemCounter({
					value_to: station.gunCount,
					value_from:stationgunCount_f
				});	
				stationgunCount_f = station.gunCount;

				$('.inConstructionCount').lemCounter({
					value_to: station.inConstructionCount,
					value_from:inConstructionCount_f
				});	
				inConstructionCount_f = station.inConstructionCount;
				
				$('.toBeBulitCount').lemCounter({
					value_to: station.toBeBulitCount,
					value_from:toBeBulitCount_f
				});	
				toBeBulitCount_f = station.toBeBulitCount;

				$('.insideCount').lemCounter({
					value_to: station.insideCount,
					value_from:insideCount_f
				});	
				insideCount_f = station.insideCount;
			}
		})
	}