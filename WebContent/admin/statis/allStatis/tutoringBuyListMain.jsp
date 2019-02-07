<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<!-- 달력 -->
<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/redmond/jquery-ui.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jqueryscript.net/css/jquerysctipttop.css">

<link rel="stylesheet" type="text/css" href="assets/css/loadimg.min.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!-- 달력 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>


<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script
	src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/none.js"></script>


<%@include file="../../../../nav2.jsp"%>

<link rel="stylesheet" type="text/css"
	href="assets/css/mypage/tutee/Tutee_tutoringInfo.css">
<link rel="stylesheet" type="text/css" href="assets/css/admin/tab.css">
<style>
.container {
    width: 100%;
    margin: auto;
}

table.table {
    max-width: 100%;
	table-layout: auto;
}

.AllStatisticMain .table tbody tr:hover {
    cursor: pointer;
    background-color: rgba(0,0,0,.1);
}
</style>
<title>통계</title>
<div id="wrap">
	<section>
		<article>
			<div class="container">
				<br>
				<h4><a href = "starters?cmd=allstatistic" style = "color: #333; text-decoration: none;">매출관리</a></h4>
				<br>

				<ul class="nav nav-tabs border-bottom-0 mb-3">
					<li class="nav-item">
						<a class="nav-link active" href="starters?cmd=tutoringStatistic">튜터링</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="starters?cmd=interviewStatistic">면접신청</a>
					</li>
				</ul>
				
				<div class="row rangebox">
				<div class="month col-md-12 col-lg-12 col-sm-12 col-12">
				<input type="text"
							class="form-control form-control-sm" id="from" name="from"
							value="" placeholder="시작일"> <label>~</label> <input
							type="text" class="form-control form-control-sm" id="to"
							name="to" value="" placeholder="종료일">
						<button class="btn btn-sm btn-secondary" id="find">조회하기</button>

						<button class="btn btn-sm btn-secondary" id="aMonth">1개월</button>

						<button class="btn btn-sm btn-secondary" id="threeMonth">3개월</button>

						<button class="btn btn-sm btn-secondary" id="sixMonth">6개월</button>

						<button class="btn btn-sm btn-secondary" id="all">전체보기</button>
					</div>
				</div>
				
			</div>


			<div class="container AllStatisticMain">
				<%--<jsp:include page="reviewStatistics.jsp" /> --%>
				
					<div class="contents table-responsive" id="con1"><jsp:include page="tutoringBuyList.jsp" /></div>
					
				
			</div>

			<%@include file="../../../../footer2.jsp"%>
		</article>
	</section>

</div>

<script type="text/javascript">
	
	
	$('#all').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=tutoringBuyStatiscticsAll",
			success : function(result) {
				console.log("전체");
				$("div.AllStatisticMain").html(result);
			}
		});
	});
	$('#aMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=tutoringBuyStatiscticsOne",
			success : function(result) {
				console.log("1개월");
				$("div.AllStatisticMain").html(result);
			}
		});
	});
	$('#threeMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=tutoringBuyStatiscticsThree",
			success : function(result) {
				console.log("3개월");
				$("div.AllStatisticMain").html(result);
			}
		});
	});
	$('#sixMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=tutoringBuyStatiscticsSix",
			success : function(result) {
				$("div.AllStatisticMain").html(result);
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
				url : "starters?cmd=tutoringBuyStatiscticsDates&date="+from+to,
				success : function(result) {
					$("div.AllStatisticMain").html(result);
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
