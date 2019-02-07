<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
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

<link rel="stylesheet" type="text/css" href="assets/css/mypage/tutee/Tutee_tutoringInfo.css">
<link rel="stylesheet" type="text/css" href="assets/css/admin/tab.css">

<title>통계</title>
<div id="wrap">
	<section>
		<article>
			<div class="container">
				<br>
				<h4><a href = "starters?cmd=allstatistic" style = "color: #333; text-decoration: none;">매출관리</a></h4>
				<br>

				<ul class="nav nav-tabs border-bottom-0">
					<li class="nav-item">
						<a class="nav-link" href="starters?cmd=tutoringStatistic">튜터링</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="starters?cmd=interviewStatistic">면접신청</a>
					</li>
				</ul>
				
				<div class="row rangebox float-right">

				<div class="dropenroll">
					<div class="AllListSelect">
						<select class="AllListSelect form-control form-control-sm" onchange="day(this.selectedIndex);">
							<option value="0" onclick="t(this)" selected="selected" >분기별</option>
							<option value="1" onclick="t(this)">일별</option>
							<option value="2" onclick="t(this)">월별</option>
							<option value="3" onclick="t(this)">연도별</option>
						</select>
					</div>
				</div>
			</div>


			<div class="container AllStatisticMain">
			
				<h5>총매출-2018년-분기</h5>
			<%@include file="allBuyStatistics.jsp"%>
			</div>
			</div>
		<%@include file="../../../../footer2.jsp"%>
		</article>
	</section>
	
</div>

  <script type="text/javascript">

      
  	// 일별, 월별, 연도별, 분기별
  	function day(selectedguy) {
 		var selectBox = selectedguy;
 		console.log("selectedguy"+selectedguy);
  		// location.href = "starters?cmd=reviewMonthStatis&select=" + selectBox;
  		$.ajax({
  			url : "starters?cmd=selectAllstatistic&select=" + selectBox,
  			success : function(result) {
  				//console.log(result);
  				$("div.AllStatisticMain").html(result);
  			}
  		})
  	}
    </script>
