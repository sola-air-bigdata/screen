/**
 * 数字滚动
 */

$(function() {
	var rollInterval, total1,total2,total3 = 0;
	initPage($('#container1'), 6); // 初始化滚动数字最大的位数
	initPage($('#container2'), 6); // 初始化滚动数字最大的位数
	initPage($('#container3'), 6); // 初始化滚动数字最大的位数
	total1 = 689.5;
	total2 = 655;
	total3 = 659;
	setInterval(function(){
		total1+=3;
		total2+=5;
		total3+=6;
		fun(total1,total2,total3)
	},5000)
	
	var numItemHeight = $('.num-item').eq(0).height(); // 获取数字dom的高度

	 rollInterval = setInterval(fun(total1,total2,total3), 500); // 定时获取实时数据，实现数字滚动

	function fun(total1,total2,total3) {
		// total = total+50;
		
		numberRoll($('#container1'), total1);
		numberRoll($('#container2'), total2);
		numberRoll($('#container3'), total3);
	}

	function numberRoll(container, total) {
		var number = total.toString().split("").reverse().join("");
		for (var i = 0; i < number.length; i++) {
			var height = parseInt(number[i]) * numItemHeight;
			$(container).find('.num' + i + ' .numbers-view').animate({
				marginTop: -height
			}, 3000, "swing");
		}
	}

	function initPage(container, digit) {
		var _html = '';
		for (var i = digit - 1; i >= 0; i--) {
			_html += '<div class="number num' + i + '">' +
				'<div class="numbers-view">' +
				'<div class="num-item zero">0</div>' +
				'<div class="num-item one">1</div>' +
				'<div class="num-item two">2</div>' +
				'<div class="num-item three">3</div>' +
				'<div class="num-item four">4</div>' +
				'<div class="num-item five">5</div>' +
				'<div class="num-item six">6</div>' +
				'<div class="num-item seven">7</div>' +
				'<div class="num-item eight">8</div>' +
				'<div class="num-item nine">9</div>' +
				'</div>' +
				'</div>';
		}
		container.html(_html);
	};
})
