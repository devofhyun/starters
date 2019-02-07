<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/none.js"></script>


<%@include file="../nav2.jsp"%>

<link rel="stylesheet" type="text/css" href="assets/css/mypage/tutee/Tutee_tutoringInfo.css">
<link rel="stylesheet" type="text/css" href="assets/css/admin/tab.css">

<title>통계</title>
<div id="wrap">
	<section>
		<article>
			<div class="container">
				<br>
				<h4 class="text-body">튜티,튜터회원 통계</h4>
				<br>
				<div class="row rangebox">
				<div class="col-lg-7 col-md-7">
				<ul class="nav nav-tabs">
				  <li class="nav-item">
				    <a class="nav-link active" href="starters?cmd=MemberStatistic">튜티&튜터</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="starters?cmd=companyStatistic">기업</a>
				  </li>
				</ul>
				
				</div>
				

				<div class="dropenroll col-lg-2 col-md-2">
					<div class="AllListSelect">
						<select class="AllListSelect form-control form-control-sm" onchange="day(this.selectedIndex);">
							<option value="0" onclick="t(this)" selected="selected" >연령별</option>
							<option value="1" onclick="t(this)">성별</option>
							<option value="2" onclick="t(this)">분기별</option>
							<option value="3" onclick="t(this)">연도별</option>
						</select>
					</div>
				</div>
			</div>


			<div class="container AllStatisticMain">
			
			<%@include file="ageMemberS.jsp"%>
			</div>
			</div>
			
			
			
		<%@include file="../footer2.jsp"%>
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
  			url : "starters?cmd=selectMemberStatistic&select=" + selectBox,
  			success : function(result) {
  				//console.log(result);
  				$("div.AllStatisticMain").html(result);
  			}
  		})
  	}
    </script>
