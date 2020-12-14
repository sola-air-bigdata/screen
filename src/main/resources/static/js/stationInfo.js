$(function() {
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]);
		return null;
	}
	var stationID = GetQueryString("stationID");
    function initFun(stationID){
    	getStationList();//获取站点下拉框数据    不刷新
		getStationInfoById(stationID);//根据id获得站点数据   不刷新
		getServiceCountByStationId(stationID);//总服务次数总电量总收入 尖峰平谷数据 15秒
		getStationEquipmentInfo(stationID);//设备总功率变压器功率充电电能   不刷新
		getFreeOfStation(stationID);//站点空闲枪数   不刷新
	}
	initFun(stationID);
	
	
	/**
	 * 定时器
	 * 
	 */ 
	 var timer1=setTimeout(FiveSec,15000);// 15秒钟执行一次
	 function FiveSec(){
		 getServiceCountByStationId(stationID);// 总服务次数总电量总收入
		 clearTimeout(timer1);
		 setTimeout(FiveSec,15000)
	 };
	 
	 /*var timer2=setTimeout(erSec,20000);// 15秒钟执行一次
	 function erSec(){
		 getServiceCountByStationId(stationID,null);// 尖峰平谷数据 15秒
		 clearTimeout(timer2);
		 setTimeout(erSec,20000)
	 };*/
	
	
 	//获取站点的下拉框
 	function getStationList(){
 		$.ajax({
 			url:"/statistic/gJStationViewAjax/getStationList.json",
 			type:"post",
 			dataType:"json",
 			data:{},
 			success:function(res){
 				var option='<option value="0">切换充电站</option>';
 				for(var i=0;i<res.length;i++){
 					option+='<option value="'+res[i].id+'">'+res[i].name+'</option>';
 				}
 				$("#stationList").html(option);
 			}
 		})
 	}
 	
 	//获取站点的数据
 	function getStationInfoById(id){
 		$.ajax({
 			url:"/statistic/gJStationViewAjax/getStationInfoByID.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				var image="./img/map1.png";
 				if(res.stationImage!=0){
 					image=res.stationImage;
 				}
 				$("#s_stationImage").attr("src",image);
 				$("#s_stationName").text(res.stationName==0?"":res.stationName);
 				$("#s_stationaddres").text(res.stationaddres==0?"":res.stationaddres);
 				$("#r_stationName").text(res.stationName==0?"":res.stationName);
 				$("#s_stationaddres").text(res.stationaddres);
 				$("#s_stationPile").text(res.stationPile);
 				$("#s_electricityPrice").text(parseFloat(res.electricityPrice)/10000);
 				document.getElementById("star_grade").innerHTML = "";
 				var xing=res.stationRating;
 				startXing(xing);
 			}
 		})
 	}
 	
 	function startXing(xing){
     	$("#star_grade").markingSystem({ //站点评分
            num: 5,
            havePoint: true,
            haveGrade: true,
            unit: '分',
            grade: xing,
            height: 20,
            width: 20,
        })
 	}
 	//根据站点id获取总服务次数，总电量，总收入，充电峰谷,今日总服务次数，总电量，总收入统计
 	
 	var s_monthServicesNums_f = 0;
 	var s_monthTotalPower_f = 0;
 	var s_monthTotalRevenue1_f = 0;
 	var s_jianCharge1_f = 0;
 	var s_fengCharge1_f = 0;
 	var s_pingCharge1_f = 0;
 	var s_guCharge1_f = 0;
 	function getServiceCountByStationId(id){
 		$.ajax({
 			url:"/statistic/gJStationViewAjax/getStationSumInfoByID.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id,
 				"month":null},
 			success:function(res){
 				//console.log(res);
					if(res.servicesNums>10000){
						var servicesNums = parseFloat(res.servicesNums)/10000;
						$("#s_monthServicesNums").text(toDecimal(servicesNums));//(月总服务次数)
						$("#serviceDw").text("总充电数(万次)");

						$('#s_monthServicesNums').lemCounter({
							value_to: toDecimal(servicesNums),
							value_from:s_monthServicesNums_f
						});	
						s_monthServicesNums_f = toDecimal(servicesNums);
					}else{
						$("#s_monthServicesNums").text(res.servicesNums);//(月总服务次数)
						$("#serviceDw").text("总充电数(次)");

						$('#s_monthServicesNums').lemCounter({
							value_to: res.servicesNums,
							value_from:s_monthServicesNums_f
						});	
						s_monthServicesNums_f = res.servicesNums;
					}
					//console.log(res.monthTotalPower);
     				if(parseFloat(res.totalPower)/1000>999.99){
						var totalPower = toDecimal(parseFloat(res.totalPower)/1000/10000);
     					$("#s_monthTotalPower").text(totalPower);
						 $("#isWan").text("总充电(万KWH)");

						$('#s_monthTotalPower').lemCounter({
							value_to: totalPower,
							value_from:s_monthTotalPower_f
						});	
						s_monthTotalPower_f = totalPower;
     				}else{
						var totalPower = toDecimal(parseFloat(res.totalPower)/1000);
     					$("#s_monthTotalPower").text(totalPower);//(总电量)
						$("#isWan").text("总充电(KWH)");

						$('#s_monthTotalPower').lemCounter({
							value_to:totalPower,
							value_from:s_monthTotalPower_f
						});	
						s_monthTotalPower_f = totalPower;
					 }
					 
     				var revenue=0;
     				if(parseFloat(res.totalRevenue)/100>10000){
     					revenue = toDecimal(parseFloat(res.totalRevenue)/100/10000);
						$("#s_monthTotalRevenue").text(revenue);
						$("#incomeDw").text("累计收入(万元)");

						$('#s_monthTotalRevenue').lemCounter({
							value_to: revenue,
							value_from:s_monthTotalRevenue1_f
						});	
						s_monthTotalRevenue1_f = revenue;	
     				}else{
						totalRevenue = parseInt(res.totalRevenue)/100.00;
						$("#s_monthTotalRevenue").text(totalRevenue);
						$("#incomeDw").text("累计收入(元)");

						$('#s_monthTotalRevenue').lemCounter({
							value_to: totalRevenue,
							value_from:s_monthTotalRevenue1_f
						});	
						s_monthTotalRevenue1_f = totalRevenue;	
     				}
     				// $("#s_monthTotalRevenue").html(revenueHtml);//(总收入统计)
 				
 					var jian=res.jianCharge;
 					var feng=res.fengCharge;
 					var ping=res.pingCharge;
 					var gu=res.guCharge; 
 					if((Math.abs(jian)/1000)>10000){
 						jian = parseFloat((jian/1000/10000).toFixed(2));
						$("#s_jianCharge").html("<span style='font-size: 36px;' class='myfont s_jianCharge1'>"+jian+"</span>万kwh");
						$('.s_jianCharge1').lemCounter({
							value_to: jian,
							value_from:s_jianCharge1_f
						});	
						s_jianCharge1_f = jian;
 					}else{
 						jian = toDecimal(jian/1000);
						$("#s_jianCharge").html("<span style='font-size: 36px;' class='myfont s_jianCharge1'>"+jian+"</span>kwh");
						$('.s_jianCharge1').lemCounter({
							value_to: jian,
							value_from:s_jianCharge1_f
						});	
						s_jianCharge1_f = jian; 
 					}
 					if((Math.abs(feng)/1000)>10000){
 						feng = parseFloat((feng/1000/10000).toFixed(2));
						$("#s_fengCharge").html("<span style='font-size: 36px;' class='myfont s_fengCharge1'>"+feng+"</span>万kwh");
						$('.s_fengCharge1').lemCounter({
							value_to: feng,
							value_from:s_fengCharge1_f
						});	
						s_fengCharge1_f = feng;
 					}else{
 						feng = toDecimal(feng/1000);
						$("#s_fengCharge").html("<span style='font-size: 36px;' class='myfont s_fengCharge1'>"+feng+"</span>kwh");
						$('.s_fengCharge1').lemCounter({
							value_to: feng,
							value_from:s_fengCharge1_f
						});	
						s_fengCharge1_f = feng;
 					}
 					if((Math.abs(ping)/1000)>10000){
 						ping = parseFloat((ping/1000/10000).toFixed(2));
						$("#s_pingCharge").html("<span style='font-size: 36px;' class='myfont s_pingCharge1'>"+ping+"</span>万kwh");

						$('.s_pingCharge1').lemCounter({
							value_to: ping,
							value_from:s_pingCharge1_f
						});	
						s_pingCharge1_f = ping;
 					}else{
 						ping = toDecimal(ping/1000);
						$("#s_pingCharge").html("<span style='font-size: 36px;' class='myfont s_pingCharge1'>"+ping+"</span>kwh");

						$('.s_pingCharge1').lemCounter({
							value_to: ping,
							value_from:s_pingCharge1_f
						});	
						s_pingCharge1_f = ping;
 					}
 					if((Math.abs(gu)/1000)>10000){
 						gu = parseFloat((gu/1000/10000).toFixed(2)) ;
						$("#s_guCharge").html("<span style='font-size: 36px;' class='myfont s_guCharge1'>"+gu+"</span>万kwh");
						$('.s_guCharge1').lemCounter({
							value_to: gu,
							value_from:s_guCharge1_f
						});	
						s_guCharge1_f = gu;
 					}else{
 						gu = toDecimal(gu/1000);
						$("#s_guCharge").html("<span style='font-size: 36px;' class='myfont s_guCharge1'>"+gu+"</span>kwh");
						$('.s_guCharge1').lemCounter({
							value_to: gu,
							value_from:s_guCharge1_f
						});	
						s_guCharge1_f = gu;
 					}		
 			}
	 	})
 	}
 	//四舍五入保留两位小数
 	 function toDecimal(x) { 
 		  var f = parseFloat(x); 
 		  if (isNaN(f)) { 
 		  return; 
 		  } 
 		  f = Math.round(x*100)/100; 
 		  return f; 
 		 } 
 	//充电站设备信息
	var s_totalPowerOfEquipment1_f= 0;
	var s_variableVoltagePower_f= 0;
	var s_chargingEnergy_f= 0;
 	function getStationEquipmentInfo(id){
 		$.ajax({
 			url:"/statistic/gJStationViewAjax/getStationEquipmentInfo.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				if(toDecimal(res.totalPowerOfEquipment)>10000){
					var totalPowerOfEquipment = toDecimal(res.totalPowerOfEquipment/10000);
					$("#s_totalPowerOfEquipment").html("<span style='font-size:42px;' class='myfont s_totalPowerOfEquipment1'>"+ totalPowerOfEquipment +"</span> 万KW");
					$('.s_totalPowerOfEquipment1').lemCounter({
						value_to:totalPowerOfEquipment,
						value_from:s_totalPowerOfEquipment1_f
					});	
					s_totalPowerOfEquipment1_f = totalPowerOfEquipment;
 				}else{
					var totalPowerOfEquipment = toDecimal(res.totalPowerOfEquipment);
					$("#s_totalPowerOfEquipment").html("<span style='font-size:42px;' class='myfont s_totalPowerOfEquipment1'>"+ totalPowerOfEquipment +"</span> KW");
					$('.s_totalPowerOfEquipment1').lemCounter({
						value_to:totalPowerOfEquipment,
						value_from:s_totalPowerOfEquipment1_f
					});	
					s_totalPowerOfEquipment1_f = totalPowerOfEquipment;
 				}
 				if(res.variableVoltagePower>10000){
					variableVoltagePower = toDecimal(res.variableVoltagePower/10000);
					$("#s_variableVoltagePower").html("<span  style='font-size:42px;' class='myfont s_variableVoltagePower1'>"+ variableVoltagePower +"</span>万KW");
					$('.s_variableVoltagePower').lemCounter({
						value_to:variableVoltagePower,
						value_from:s_variableVoltagePower_f
					});	
					s_variableVoltagePower_f = variableVoltagePower;
				}else{
					variableVoltagePower = res.variableVoltagePower
					$("#s_variableVoltagePower").html("<span  style='font-size:42px;' class='myfont s_variableVoltagePower1'>"+ variableVoltagePower +"</span>KW");
					$('.s_variableVoltagePower').lemCounter({
						value_to:variableVoltagePower,
						value_from:s_variableVoltagePower_f
					});	
					s_variableVoltagePower_f = variableVoltagePower;
				}
				
 				if(toDecimal(res.chargingEnergy)>10000){
					chargingEnergy = toDecimal(res.chargingEnergy/10000)
					$("#s_chargingEnergy").html("<span style='font-size:42px;' class='myfont s_chargingEnergy1'>"+ chargingEnergy +"</span> 万KWH");
					 
					$('.s_chargingEnergy1').lemCounter({
						value_to:chargingEnergy,
						value_from:s_chargingEnergy_f
					});	
					s_chargingEnergy_f = chargingEnergy;

 				}else{
					chargingEnergy = toDecimal(res.chargingEnergy)
					$("#s_chargingEnergy").html("<span style='font-size:42px;' class='myfont s_chargingEnergy1'>"+ chargingEnergy +"</span> KWH");
					 
					$('.s_chargingEnergy1').lemCounter({
						value_to:chargingEnergy,
						value_from:s_chargingEnergy_f
					});	
					s_chargingEnergy_f = chargingEnergy;

 				}
 				//console.log(res.chargingEnergy);
				//数字滚动
			
 			}
 			
 		})
 	}
 	
 	//站点空闲枪数
 	
 	function getFreeOfStation(id){
 		$.ajax({
 			url:"/statistic/gJStationViewAjax/getStationSumViewData.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				$("#s_free").text(res.free);
 			}
 			
 		})
 	}

})