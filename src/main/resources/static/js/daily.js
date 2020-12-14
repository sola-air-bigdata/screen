function mytime(){
    var time = document.getElementById('time').innerHTML;
    time = time.split(" ")[1];
    time1 = time.split(":")[0];
    time2 = time.split(":")[1];
    time = time1 + ":";
    time_1 = time + time2;
    return time_1;
}
function mygun(){
    array = ["1#","2#","3#","4#","5#","6#","7#","8#","10#","11#","12#","13#","14#","15#","16#"];
    var a = parseInt(array.length*Math.random());
    return array[a];
}
function hm(){
    array = ["1小时20分","1小时05分","1小时32分","58分","1小时27分","1小时12分","1小时30分","1小时08分","1小时16分"];
    var a = parseInt(array.length*Math.random());
    return array[a];
}
function gg(){
    array = ["A","B"];
    var a = parseInt(array.length*Math.random());
    return array[a];
}

setInterval(function(){
    time = mytime();
    gun = mygun();
    use_time = hm();
    g = gg();
    $("#charging_log_ul").prepend("<li><img width='60' src='./img/timeline1.png' style='position: absolute;top: -6px;left: -14px;z-index: 999;'><p><span class='mc_8bc' id='time1'>"+time+"</span>&nbsp;&nbsp;<span>"+gun+"</span>&nbsp;&nbsp;<span>"+g+"枪</span>&nbsp;&nbsp;<span>充电完成</span></p><p>"+gun+""+g+"枪完成充电，用时"+use_time+"</p></li>");
}, 60000);
setInterval(function(){
    time = mytime();
    gun = mygun();
    use_time = hm();
    g = gg();
    $("#charging_log_ul").prepend("<li><img width='60' src='./img/timeline3.png' style='position: absolute;top: -6px;left: -14px;z-index: 999;'><p style='margin-left:50px;height:24px;'><span class='mc_8bc' id='time6'>"+time+"</span>&nbsp;&nbsp;<span>"+gun+"</span>&nbsp;&nbsp;<span>"+g+"枪</span>&nbsp;&nbsp;<span>已经预约</span></p><p>"+gun+""+g+"枪已经预约，已锁定</p></li>");
}, 90000);
setInterval(function(){
    time = mytime();
    gun = mygun();
    use_time = hm();
    g = gg();
    $("#charging_log_ul").prepend("<li class='fault'><img width='60' src='./img/timeline2.png' style='position: absolute;top: -6px;left: -14px;z-index: 999;'><p><span id='time5'>"+time+"</span>&nbsp;&nbsp;<span>"+gun+"</span>&nbsp;&nbsp;<span>"+g+"枪</span>&nbsp;&nbsp;<span>出现故障</span></p><p>"+gun+""+g+"枪出现故障，无法使用</p></li>");
}, 1800000);
setInterval(function(){
    $("#charging_log_ul>li:eq(8)").remove();
}, 1000);