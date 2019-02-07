<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<!-- 달력 -->
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="http://www.jqueryscript.net/css/jquerysctipttop.css">

<link rel="stylesheet" type="text/css" href="assets/css/loadimg.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css" >




<%@include file="../nav.jsp"%>

<link rel="stylesheet" type="text/css" href="assets/css/mypage/company/Company_tutoringInfo.css">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">


<title>스타터스 : 마이페이지 - 면접 현황 및 내역</title>


<div class="container main">

  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyInfoDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyLikeList">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=companyInterview">면접내역 및 현황</a>
    </li>
  </ul>
<h4 class="mt-5">${name}님의 면접내역</h4>
<br>

      <div class="row rangebox">
         <div class="month col-md-6">
			<label>기간 :</label>
					<input type="text" class="form-control form-control-sm" id="from" name="from" value="" placeholder="시작일">
					<label>~</label>
					<input type="text" class="form-control form-control-sm" id="to" name="to" value="" placeholder="종료일">
					<button class="btn btn-sm btn-secondary" id="find">조회하기</button>
				</div>
         <div class="range col-md-6">
			<button class="btn btn-sm btn-secondary" id="aMonth">1개월</button>

			<button class="btn btn-sm btn-secondary" id="threeMonth" >3개월</button>

			<button class="btn btn-sm btn-secondary" id="sixMonth" >6개월</button>

			<button class="btn btn-sm btn-secondary" id="all" >전체보기</button>
         </div>
      </div>
         </div>


<!--       <div class="row" id="date">
         <span class="col-md-5" id="lastdate" align="middle"> <select
            class="col-md-2">
               <option selected="">-----</option>
         </select> <a>년</a> <select class="col-md-2">
               <option selected="">-----</option>
         </select> <a>월</a> <select class="col-md-2">
               <option selected="">-----</option>
         </select> <a>일</a>
         </span> <a class="col-md-1" style="text-align: center;">~</a> <span
            class="col-md-5" id="leastdate" align="middle"> <select
            class="col-md-2">
               <option selected="">-----</option>
         </select> <a>년</a> <select class="col-md-2">
               <option selected="">-----</option>
         </select> <a>월</a> <select class="col-md-2">
               <option selected="">-----</option>
         </select> <a>일</a>
         </span>

      </div>
      <hr id="hrbottom">
   </div> -->

 <div class="tutoringInfoMain">
   <jsp:include page="companyInterview.jsp" />
</div> 
   

<%--    <c:choose>
      <c:when test='${empty }'> --%>
<%--           <div class="tutoringInfoMain">
         @@@@@@@@ 
            <jsp:include page="companyInterview.jsp" />
             @@@@@@@
         </div>  --%>
<%--       </c:when>
      <c:otherwise>
         <div class="changView">
            <jsp:include page="tutoringInfoListAmonthSearch.jsp" />
         </div>
      </c:otherwise>
   </c:choose>
 --%>


<%@include file="../footer.jsp"%>


   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
   <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
   
   <!-- 달력 -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script src="assets/js/tabSizing.js"> </script>
 
<script> 

$(document).ready(function() {
	if($(document).width() < 768){
		verCul();
	}
});

$(window).resize(function (){
	if($(document).width() < 768){
		verCul();
	} else if($(document).width() > 768){
		horCul()}
});

$('#aMonth').click(function() {
	var rRrt = $.ajax({
		url : "starters?cmd=interviewListAmonth",
		success : function(result) {
			$("div.tutoringInfoMain").html(result);
		}
	});
});

$('#threeMonth').click(function() {
	var rRrt = $.ajax({
		url : "starters?cmd=interviewListThreeMonth",
		success : function(result) {
			$("div.tutoringInfoMain").html(result);
		}
	});
});

$('#sixMonth').click(function() {
	var rRrt = $.ajax({
		url : "starters?cmd=interviewListSixMonth",
		success : function(result) {
			$("div.tutoringInfoMain").html(result);
		}
	});
});

$('#all').click(function() {
	var rRrt = $.ajax({
		url : "starters?cmd=interviewAll",
		success : function(result) {
			$("div.tutoringInfoMain").html(result);
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
			url : "starters?cmd=companyinterviewDateSelectAction&date="+from+to,
			success : function(result) {
				$("div.tutoringInfoMain").html(result);
			}
		});
	}
});

/**달력*/
$.datepicker.regional['kr'] = {
	closeText : '닫기', // 닫기 버튼 텍스트 변경
	currentText : '오늘', // 오늘 텍스트 변경
	monthNames : [ '1 월', '2 월', '3 월', '4 월', '5 월', '6 월', '7 월', '8 월',
			'9 월', '10 월', '11 월', '12 월' ], // 개월 텍스트 설정
	monthNamesShort : [ '1 월', '2 월', '3 월', '4 월', '5 월', '6 월', '7 월',
			'8 월', '9 월', '10 월', '11 월', '12 월' ], // 개월 텍스트 설정
	dayNames : [ '월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일' ], // 요일 텍스트 설정
	dayNamesShort : [ '월', '화', '수', '목', '금', '토', '일' ], // 요일 텍스트 축약 설정&nbsp;   dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
	dateFormat : 'yy/mm/dd' // 날짜 포맷 설정
};

// Seeting up default language, Korean
$.datepicker.setDefaults($.datepicker.regional['kr']);

// Start Datepicker UI
$("#datepicker").datepicker();

var dateFormat = "yy/mm/dd", from = $("#from").datepicker({
	defaultDate : "+1w",
	changeMonth : true,
	numberOfMonths : 1
}).on("change", function() {
	to.datepicker("option", "minDate", getDate(this));
}), to = $("#to").datepicker({
	defaultDate : "+1w",
	changeMonth : true,
	numberOfMonths : 1
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