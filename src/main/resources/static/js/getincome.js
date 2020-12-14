
	  var p5 = echarts.init(document.getElementById("site_build"));    //充电站 站点总览
           
	getRealPower();//实时数据
	function getRealPower(){
		$.ajax({
			url:"/statistic/screenNewAjax/getRealPower.json",
			type:"post",
			dataType:"json",
			data:{},
			success:function(res){
				var problemPercent=res.problemPercent;//设备运营 实时功率
				$('#problemPercent').text(problemPercent);
				var offLinePercent=res.offLinePercent;///显示当前充电电桩的百分比值
				$('#offLinePercent').text(offLinePercent);
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
					value_to:station.statioTotal,
					value_from:stationstatioTotal_f
				});	
				stationstatioTotal_f = station.statioTotal;

				$('.stationpileCount').lemCounter({
					value_to:station.pileCount,
					value_from:stationpileCount_f
				});	
				stationpileCount_f = station.pileCount;

				$('.stationgunCount').lemCounter({
					value_to:station.gunCount,
					value_from:stationgunCount_f
				});	
				stationgunCount_f = station.gunCount;

				$('.inConstructionCount').lemCounter({
					value_to:station.inConstructionCount,
					value_from:inConstructionCount_f
				});	
				inConstructionCount_f = station.inConstructionCount;
				
				$('.toBeBulitCount').lemCounter({
					value_to:station.toBeBulitCount,
					value_from:toBeBulitCount_f
				});	
				toBeBulitCount_f = station.toBeBulitCount;

				$('.insideCount').lemCounter({
					value_to:station.insideCount,
					value_from:insideCount_f
				});	
				insideCount_f = station.insideCount;

			}
		})
	}