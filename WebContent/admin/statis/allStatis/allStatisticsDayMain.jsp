<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   


<link rel="stylesheet" type="text/css"
	href="assets/css/mypage/tutee/Tutee_tutoringInfo.css">

				<h5>총매출-2018년-일별</h5>
				<br>
				
				<div class="row rangebox">


				<div class="month col-md-7">
						<label>기간 :</label> <input type="text"
							class="form-control form-control-sm" id="from" name="from"
							value="" placeholder="시작일"> <label>~</label> <input
							type="text" class="form-control form-control-sm" id="to"
							name="to" value="" placeholder="종료일">
						<button class="btn btn-sm btn-secondary" id="find">조회하기</button>
					</div>
					<div class="range col-md-5">
						<button class="btn btn-sm btn-secondary" id="aMonth">1개월</button>

						<button class="btn btn-sm btn-secondary" id="threeMonth">3개월</button>

						<button class="btn btn-sm btn-secondary" id="sixMonth">6개월</button>

						<button class="btn btn-sm btn-secondary" id="all">전체보기</button>
					</div>
				</div>
				


			<div class="container AllStatisticDayMain">
			<%@include file="allStatisticsDay.jsp"%>
			</div>
	

<script type="text/javascript">
	
	$('#all').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=getBuyDayAll",
			success : function(result) {
				console.log("전체");
				$("div.AllStatisticDayMain").html(result);
			}
		});
	});
	$('#aMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=getBuyDayOne",
			success : function(result) {
				console.log("1개월");
				$("div.AllStatisticDayMain").html(result);
			}
		});
	});
	$('#threeMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=getBuyDayThree",
			success : function(result) {
				console.log("3개월");
				$("div.AllStatisticDayMain").html(result);
			}
		});
	});
	$('#sixMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=getBuyDaySix",
			success : function(result) {
				$("div.AllStatisticDayMain").html(result);
			}
		});
	});
	
	$('#find').click(function() { 
		var from = $('#from').val();
		var to = $('#to').val(); 
		if ( from == "" && to == "" ) {
			alert("날짜를 입력해주세요");
		} else if( from != ""  && to == "" ){
			alert("종료일을 입력해주세요");
		} else if( from == ""  && to != "" ){
			alert("시작일을 입력해주세요");
		} else{
			$.ajax({
				url : "starters?cmd=getBuyDaySelectDate&date="+from+to,
				success : function(result) {
					$("div.AllStatisticDayMain").html(result);
				}
			});
		}
	});
	
	$.datepicker.regional['kr'] = {
			closeText : '닫기', 
			currentText : '오늘', 
			monthNames : [ '1 월', '2 월', '3 월', '4 월', '5 월', '6 월', '7 월', '8 월',
					'9 월', '10 월', '11 월', '12 월' ], 
			monthNamesShort : [ '1 월', '2 월', '3 월', '4 월', '5 월', '6 월', '7 월',
					'8 월', '9 월', '10 월', '11 월', '12 월' ], 
			dayNames : [ '월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일' ], 
			dayNamesShort : [ '월', '화', '수', '목', '금', '토', '일' ], 
			dateFormat : 'yy/mm/dd' 
		};

		$.datepicker.setDefaults($.datepicker.regional['kr']);

		$("#datepicker").datepicker();

		var dateFormat = "yy/mm/dd", from = $("#from").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 3
		}).on("change", function() {
			to.datepicker("option", "minDate", getDate(this));
		}), to = $("#to").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 3
		}).on("change", function() {
			from.datepicker("option", "maxDate", getDate(this));
		});

		function getDate(element) {
			var date;
			try {
				date = $.datepicker.parseDate(dateFormat, element.value);
			} catch (error) {
				date = null;
			}
			return date;
		};
</script>
