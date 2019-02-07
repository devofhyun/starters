<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<!-- <link href="assets/css/mypage/tutor/tutorMyInfo.css" rel="stylesheet"> -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../nav.jsp"%>
<link href="assets/css/mypage/tutor/myTutoringList.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">



<title>스타터스 : 나의 튜터링 목록</title>

<div class="container">

  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tutorTutoringList">튜터링 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringTuteeList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringCalenderList1">튜터링 일정</a>
    </li>
  </ul>
	<h3>
		<strong> 나의 튜터링 목록</strong>
	</h3>
	<br>
	<div class="row enroll">
		<div class="col-lg-10 col-md-10">
			<p>${tutortutoringListNumber}개의튜터링</p>
		</div>

	</div>



	<div class="mainList">
		<jsp:include page="tutorTutoringList.jsp" />
	</div>
</div>




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
</script>