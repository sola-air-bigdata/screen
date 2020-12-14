	getUsersViewData();
	getUserTrendOfMonthlyData();
	getUserCountOfMonthlyAndDayData();
	getTotalUserIncreaseTrendData();
	var timeout1 = setTimeout(usersViewData,30000);
	function usersViewData(){
		getUsersViewData();
		clearTimeout(timeout1);
		setTimeout(usersViewData,30000);
	}
	var timeout2 = setTimeout(userCountView,60000);
	function userCountView(){
		getUserCountOfMonthlyAndDayData();
		clearTimeout(timeout2);
		setTimeout(userCountView,60000);
	}
	var timeout3 = setTimeout(inServiceUsers,5000);
	function inServiceUsers(){
		getInServiceUsersCount();
		clearTimeout(timeout3);
		setTimeout(inServiceUsers,5000);
	}
	var p6 = echarts.init(document.getElementById("user_build"));    //当日服务用户数 用户总览

	var in_service_user_total_f = 0; 
	var app_users_f = 0; 
	var wechat_users_f = 0; 
	function getUsersViewData(){
		$.ajax({
			url : "/statistic/GuangJiaoUsersViewAjax/getUsersViewData.json",
			data : {},
			dataType : "json",
			type : "POST",
			success : function(data) {
				$('#in_service_user_total').text(data.memberTotal); 
				$('#person_count').text(data.personageCount);
				$('#team_count').text(data.teamCount);
				var p_percent = data.personageRate+'%';
				var t_percent = data.teamRate+'%';
				$('#p_percent').width(p_percent);
				$('#t_percent').width(t_percent);
				$('#person_percent').text(p_percent);
				$('#team_percent').text(t_percent);
				$('#app_users').text(data.appCount);
				$('#wechat_users').text(data.wxCount);

				$('#in_service_user_total').lemCounter({
					value_to: data.memberTotal,
					value_from:in_service_user_total_f
				});	
				in_service_user_total_f = data.memberTotal;

				$('#app_users').lemCounter({
					value_to: data.appCount,
					value_from:app_users_f
				});	
				app_users_f = data.appCount;

				$('#wechat_users').lemCounter({
					value_to: data.wxCount,
					value_from:wechat_users_f
				});	
				wechat_users_f = data.wxCount;
			},
			error : function() {
				AMUI.dialog.alert({ title: '温馨提示', content: "网络异常！请稍后..."});
			}
		});
	}

	var in_service_users_f = 0;
	function getInServiceUsersCount(){
		$.ajax({
			url : "/statistic/GuangJiaoUsersViewAjax/getInServiceUsersCount.json",
			data : {},
			dataType : "json",
			type : "POST",
			success : function(data) {
				$('#in_service_users').text(data.inServiceUserCount);
				p6.setOption(pieChartOptions1('#00F6FF', data.inServiceUserCount));

				$('#in_service_users').lemCounter({
					value_to: data.inServiceUserCount,
					value_from:in_service_users_f
				});	
				in_service_users_f = data.inServiceUserCount;
			},
			error : function() {
				AMUI.dialog.alert({ title: '温馨提示', content: "网络异常！请稍后..."});
			}
		});
	}
	var add_user_count_f = 0;
	var add_user_today_f = 0;
	function getUserCountOfMonthlyAndDayData(){
		$.ajax({
			url : "/statistic/GuangJiaoUsersViewAjax/getUserCountOfMonthlyAndDayData.json",
			data : {},
			dataType : "json",
			type : "POST",
			success : function(data) {
				$('#add_user_count').text(data.memberCountOfMonthly);
				var today = data.memberCountOfToday;
				$('#add_user_today').text(today);

				$('#add_user_count').lemCounter({
					value_to: data.memberCountOfMonthly,
					value_from:add_user_count_f
				});	
				add_user_count_f = data.memberCountOfMonthly;

				$('#add_user_today').lemCounter({
					value_to: today,
					value_from:add_user_today_f
				});	
				add_user_today_f = today;
			},
			error : function() {
				AMUI.dialog.alert({ title: '温馨提示', content: "网络异常！请稍后..."});
			}
		});
	}
    
    var b5 = echarts.init(document.getElementById("user_total"));    //总用户增长趋势 用户总览
    var arrdataMonth = [];
    function getTotalUserIncreaseTrendData(){
    	$.ajax({
    		url : "/statistic/GuangJiaoUsersViewAjax/getTotalUserIncreaseTrendData.json",
    		data : {},
    		dataType : "json",
    		type : "POST",
    		success : function(data) {
    			 arrdataMonth = [];
    			 var oneWeek = {'data':'1周','个人':data.person[0],'车队':data.team[0]};
    			 var twoWeek = {'data':'2周','个人':data.person[1],'车队':data.team[1]};
    			 var threeWeek = {'data':'3周','个人':data.person[2],'车队':data.team[2]};
    			 var fourWeek = {'data':'4周','个人':data.person[3],'车队':data.team[3]};
    			 var fiveWeek = {'data':'5周','个人':data.person[4],'车队':data.team[4]};
    			 arrdataMonth.push(oneWeek);
    			 arrdataMonth.push(twoWeek);
    			 arrdataMonth.push(threeWeek);
    			 arrdataMonth.push(fourWeek);
    			 arrdataMonth.push(fiveWeek);
    			 b5.setOption(barChartOptions(arrdataMonth,'','个人', '车队',true,20));
    		},
    		error : function() {
    			AMUI.dialog.alert({ title: '温馨提示', content: "网络异常！请稍后..."});
    		}
    	});
    }
   
    var line4 = echarts.init(document.getElementById("add_trend"));   // 新用户增长趋势 用户总览
    function getUserTrendOfMonthlyData(){
    	$.ajax({
    		url : "/statistic/GuangJiaoUsersViewAjax/getUserTrendOfMonthlyData.json",
    		data : {},
    		dataType : "json",
    		type : "POST",
    		success : function(data) {
    			/*debugger;*/
    			var timeList =[];
    			var str;
    			for(var i in data.timeList){
    				str = data.timeList[i].substring(5);
    				timeList.push(str);
    			}
    			line4.setOption(lineChartOptions1(timeList,data.teamList, data.personageList,false,400));
    		},
    		error : function() {
    			AMUI.dialog.alert({ title: '温馨提示', content: "网络异常！请稍后..."});
    		}
    	});
    }