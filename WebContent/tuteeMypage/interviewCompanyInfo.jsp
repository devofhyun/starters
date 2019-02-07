<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="com.starters.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  필수 css   -->
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
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" />

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



<%@include file="../nav.jsp"%>
<link rel="stylesheet"
	href="assets/css/mypage/company/company_interviewForm.css" />
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">



<title>스타터스 : 면접신청</title>

	<div class="container">


<form id="frmRegister" method="post" class="form-inline" role="form">
  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutoringLike">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeMypageList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=interviewList">결제 내역</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=interviewList">면접 현황</a>
    </li>
  </ul>

		<div class="interviewForm">
			<h2>
				<strong>면접신청서</strong>
			</h2>

			<div class="interviewFormBox">

				<table class="table compinfo">
					<tbody>
						<tr>
							<th>기업</th>
							<td>${info.companyName}</td>
							<th>튜티</th>
							<td colspan="3">${info.memberName }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${info.email}</td>
							<th>홈페이지</th>
							<td colspan="3">${info.url}</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td>${info.phoneNum}</td>
							<th>규모</th>
							<td>${info.range}</td>
							<th>설립일</th>
							<td>${info.birth}</td>
						</tr>
						<tr>
							<th>주소</th>
							<td colspan="5">${info.address}</td>
						</tr>
						<tr>
							<th>기업소개</th>
							<td colspan="5">${info.introduce}</td>
						</tr>
					</tbody>
				</table>
				<div class="content">

					<!-- 직종 추가 -->
					<div class="row ">
						<h6>제안 업무</h6>
						<c:forEach items="${sjob}" var="TuteeMypageInterviewJobSelectVO"
							varStatus="sjs">
					<span class="job pr-1 pd">${TuteeMypageInterviewJobSelectVO.getMiddleName()}</span>
					</c:forEach>
					</div>

					<!-- 면접가능날짜-->
					<div class="row date">
						<h6>면접가능날짜</h6>
						<span class="pd">${info.startDate} ~ ${info.endDate}</span>
					</div>

					<!-- 면접가능요일 -->
					<!-- 강의가능요일-->
					<div id="Region1">
						<div class="row day">
							<h6>면접 가능 요일</h6>
							<c:forEach items="${day}" var="InterviewDayVO" varStatus="sjs">
								<span class="days pr-1 pd">${InterviewDayVO.getDay()}</span>
							</c:forEach>
						</div>
					</div>

					<!-- 면접가능시간 -->
					<!-- 강의가능시간-->
					<div class="row" id="sample">
						<div class="inputTime">
							<h6>면접 가능 시간</h6>
								<c:forEach items="${time}" var="InterviewTimeVO" varStatus="sjs">
								<span class="pd">${InterviewTimeVO.getStartTime()} ~ ${InterviewTimeVO.getEndTime()}</span>
								</c:forEach>
						</div>
					</div>

					<!-- 제안연봉 -->
					<div class="row">
						<h6>제안 연봉</h6>
						<div>
							<span class="pd">${info.money} 만원</span>
						</div>
					</div>


				</div>

			</div>

		</div>
	<!-- container end -->
</form>

<div class="subbtn">
	<button class="btn btn-outline-secondary btn-sm" onclick="list()">목록</button>
</div>

	</div>

<%@include file="../footer.jsp"%>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

<script src="assets/js/loadimg.min.js"></script>
<script src="assets/js/fSelect.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.ptTimeSelect.js"></script>

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

var list = function(){
	 location.href = "starters?cmd=interviewList";
}

</script>
