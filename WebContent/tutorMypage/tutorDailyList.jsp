<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
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


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../nav.jsp"%>

<%@page errorPage="errorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/mypage/tutor/tutorDaliyList.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">



<title>스타터스 : 튜터링 일지</title>
<body>
	<div class="container TB">
		
  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringList">튜터링 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tutorTutoringTuteeList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringCalenderList1">튜터링 일정</a>
    </li>
  </ul>
		
  
	<c:choose>



		<c:when test="${DailyListNumber == 0}">
		<div class="mb-5 text-center" style="min-height: 300px; margin-top: 200px;">
		<h4>아직 튜티가 튜터링 일지를 등록하지 않았습니다.</h4>
		
		</div>
    </c:when>

		<c:otherwise>

		<div class="row Dailytitle pt-5">

			<div class="col-6">
				<h5>${detailvo.title}- ${detailvo.name}</h5>

			</div>
			<div class="col-6">
				<h6>${DailyListNumber}개의 일지</h6>
			</div>
		</div>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>일지 번호</th>
						<th>피드백</th>
						<th>튜티 한마디</th>
						<th>날짜</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<c:forEach items="${list}" var="dailyVO">

						<tr onclick="location.href='starters?cmd=tutortutoringDailyDetail&num=${dailyVO.tutoringRecordId}'" style="cursor: pointer;">
							<td>${dailyVO.tutoringRecordId}</td>
							<td>${dailyVO.tutoringFeedback}</td>
							<td>${dailyVO.tutoringRecord}</td>
							<td>${dailyVO.recordDate}</td>

						</tr>
					</c:forEach>
					<!-- 리스트 돌려 -->
				</tbody>
			</table>
		</div>

	<!-- container 끝 -->


		</c:otherwise>


	</c:choose>

		<div class="container Butn">
			<button class="btn btn-sm btn-secondary list" onclick="List()">목록</button>
		</div>


	</div>

	<%@include file="../../footer.jsp"%>

</body>

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

	$(window).on(
			"load resize ",
			function() {
				var scrollWidth = $('.tbl-content').width()
						- $('.tbl-content table').width();
				$('.tbl-header').css({
					'padding-right' : scrollWidth
				});
			}).resize();

	var List = function() {
		location.href = "starters?cmd=tutorTutoringTuteeList";
	}
</script>