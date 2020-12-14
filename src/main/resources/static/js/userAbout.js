$(function(){
	
	
	function GetQueryString(name) {
		 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		 var r = window.location.search.substr(1).match(reg);
		 if (r != null) return unescape(r[2]);
		 return null;
	}
	var stationId=GetQueryString("stationID");
	userAboutData(stationId);
	function userAboutData(id){
		$.ajax({
 			url:"/statistic/gJStationViewAjax/getUserServiceNum.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				//console.log(res);
 				$("#yin").text(res[0].memNums);
 				$("#yinPercent").text(res[0].proportion.toFixed(2)+"%");
 				$("#yinSpeed").css({"width":res[0].proportion.toFixed(2)+"%"});
 				$("#jin").text(res[1].memNums);
 				$("#jinPercent").text(res[1].proportion.toFixed(2)+"%");
 				$("#jinSpeed").css({"width":res[1].proportion.toFixed(2)+"%"});
 				$("#bai").text(res[2].memNums);
 				$("#baiPercent").text(res[2].proportion.toFixed(2)+"%");
 				$("#baiSpeed").css({"width":res[2].proportion.toFixed(2)+"%"});
 				$("#diamond").text(res[3].memNums);
 				$("#diamondPercent").text(res[3].proportion.toFixed(2)+"%");
 				$("#diamondSpeed").css({"width":res[3].proportion.toFixed(2)+"%"});
 				$("#black").text(res[4].memNums);
 				$("#blackPercent").text(res[4].proportion.toFixed(2)+"%");
 				$("#blackSpeed").css({"width":res[4].proportion.toFixed(2)+"%"});
 			}
 		})
	}
	
	//allservice(服务用户总数) perCapitaCharging(人均充电消费)monthAppointment(月充电预约) 
	//monthEvent(月活动参与) fans(忠实粉丝占比)chargingConsumption(月均人均充电)
	getStationMemData(stationId);
	var pie2 = echarts.init(document.getElementById("fans"));    //忠实粉丝占比
	var u_allservice_f = 0;
	var u_perCapitaCharging_f = 0;
	var u_chargingConsumption_f = 0;
	var u_monthAppointment_f = 0;
	var u_monthEvent_f = 0;
	function getStationMemData(id){
		$.ajax({
 			url:"/statistic/gJStationViewAjax/getStationMemData.json",
 			type:"post", 
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
				 //console.log(res);
				perCapitaCharging = res.perCapitaCharging/100.00
 				$("#u_allservice").text(res.allservice);
 				$("#u_perCapitaCharging").text(perCapitaCharging);
 				$("#u_monthAppointment").text(res.monthAppointment);
 				$("#u_monthEvent").text(res.monthEvent);
 				$("#u_fans").text(res.fans.toFixed(2)+"%");
 				$("#u_chargingConsumption").text(res.chargingConsumption);
 				//忠实粉丝占比
 				var color = ['#5BE7BD', '#10349A']; 
 				var proportionArr = [
 					{ value: res.fans.toFixed(2) },{ value: (100-res.fans.toFixed(2))}];
				 pie2.setOption(pieChartOptions2(color,proportionArr)); 
				 
				$('#u_allservice').lemCounter({
					value_to: res.allservice,
					value_from:u_allservice_f
				});	
				u_allservice_f = res.allservice;

				$('#u_perCapitaCharging').lemCounter({
					value_to:perCapitaCharging,
					value_from:u_perCapitaCharging_f
				});	
				u_perCapitaCharging_f = perCapitaCharging;

				$('#u_chargingConsumption').lemCounter({
					value_to:res.chargingConsumption,
					value_from:u_chargingConsumption_f
				});	
				u_chargingConsumption_f = res.chargingConsumption;

				$('#u_monthAppointment').lemCounter({
					value_to:res.monthAppointment,
					value_from:u_monthAppointment_f
				});	
				u_monthAppointment_f = res.monthAppointment;
				
				$('#u_monthEvent').lemCounter({
					value_to:res.monthEvent,
					value_from:u_monthEvent_f
				});	
				u_monthEvent_f = res.monthEvent;
				
 			}
 		})
	}
	var line2 = echarts.init(document.getElementById("user_growth"));    
	
	//新用户增长数曲线图
	getMemberAddByMonth(stationId);
	function getMemberAddByMonth(id){
		$.ajax({
 			url:"/statistic/gJStationViewAjax/getMemberAddByMonth.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				//console.log(res);
 				var timeList=[];
 				var countList=[];
 				for (var i = 0; i < res.length; i++) {
					timeList.push(res[i].time);
					countList.push(res[i].memberNum);
				}
 				line2.setOption(lineChartOptions2(timeList,countList,true));
 			}
 		})
	}
	
	//个人充电时间分布曲线图
	var line3 = echarts.init(document.getElementById("charging_time"));    
	
	getMemberByHours(stationId);
	function getMemberByHours(id){
		$.ajax({
 			url:"/statistic/gJStationViewAjax/getMemberByHours.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				//console.log(res);
 				var timeList=[];
 				var countList=[];
 				for (var i = 0; i < res.length; i++) {
					timeList.push(res[i].time);
					countList.push(res[i].charingNum);
				}
 				line3.setOption(lineChartOptions2(timeList,countList,false)); 
 			}
 		})
	}
	
	//忠实粉丝用户统计
	var pie1 = echarts.init(document.getElementById("user_statistics"));
	getFansProportion(stationId);
	function getFansProportion(id){
		$.ajax({
 			url:"/statistic/gJStationViewAjax/getFansProportion.json",
 			type:"post",
 			dataType:"json",
 			data:{"stationID":id},
 			success:function(res){
 				//console.log(res);
 				//oneToFive（1≤X﹤5） fiveToTen(5≤X﹤10) tenToTwenty(10≤X﹤20)twentyTo(20≤X﹤∞)
 				$("#u_oneToFive").text(res.oneToFive+"%");
 				$("#u_fiveToTen").text(res.fiveToTen+"%");
 				$("#u_tenToTwenty").text(res.tenToTwenty+"%");
 				$("#u_twentyTo").text(res.twentyTo+"%");
 				var color = ['#FB6C33','#86CC6D','#5AC0EA','#FFC93C'];
 				var statisticsArr = [
 					{value:res.twentyTo},
 					{value:res.tenToTwenty},
 					{value:res.fiveToTen},
 					{value:res.oneToFive}];
 				    //忠实粉丝用户统计
 				pie1.setOption(pieChartOptions3(color,statisticsArr));  
 			}
 		})
	}
	
	//个人用户消费排名
	getConsumptionRanking(stationId);
	function getConsumptionRanking(id){
		$.ajax({
 			url:"/statistic/gJStationViewAjax/getConsumptionRanking.json",
 			type:"post",
 			data:{"stationID":id},
 			success:function(res){
 				//console.log("yonghu======"+res.length);
 				var option="";
 				var image="./img/5.png";
 				if(res.length>0){
	 				for(var i=0;i<res.length;i++){
	 					if(res[i].image!=0)image=res[i].image;
	 					var name="--";
	 					if(res[i].name!=0)name=res[i].name;
	 					option+="<li class='imgBtn'><div class='ranking_circle'>"+
	                            "<div><img src="+image+"></div>"+
	                            "</div>"+
	                            "<div class='ranking_right'>"+
	                                    "<div class='fl'>"+name+"</div>"+
	                                    "<div class='fr'>"+res[i].allbalance/100.00+"元</div>"+
	                                    "<div class='time'>充电次数：<span>"+res[i].charingNum+"</span>次</div>"+
	                             "</div></li>";
	 				}
 				}else{
 					for(var i=0;i<8;i++){
	 					option+="<li class='imgBtn' ><div class='ranking_circle'>"+
	                            "<div><img src="+image+"></div>"+
	                            "</div>"+
	                            "<div class='ranking_right'>"+
	                                    "<div class='fl'>--</div>"+
	                                    "<div class='fr'>--元</div>"+
	                                    "<div class='time'>充电次数：<span>--</span>次</div>"+
	                            "</div></li>";
 					}
	                        
 				}
 				$("#u_ranking ul").html(option);
				
				$('.imgBtn').on('click',function(){
					var imgsrc=$(this).find(".ranking_circle img").attr('src');
					$('#center_img').attr('src',imgsrc)
				})
				
 			}
 		})
	}
	
})