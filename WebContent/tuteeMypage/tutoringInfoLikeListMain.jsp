<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<%@include file="../nav.jsp"%>
<link rel="stylesheet" href="assets/css/mypage/tutee/tutoringInfoLikeList.css" />
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">



<title>스타터스 : 찜하기</title>


<div class="container likelist">



  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link " href="starters?cmd=tuteeDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tutoringLike">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeMypageList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link " href="starters?cmd=interviewList">결제 내역</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeMypageList">면접 현황</a>
    </li>
  </ul>
  
<h3 class="mt-5">찜하기 목록</h3>

<p>${wishAllCount}개의 찜하기 내역</p>

<div class="tutoringInfoLikeList">
	<jsp:include page="tutoringInfoLikeList.jsp" />
</div>
</div>

<%@include file="../footer.jsp"%>
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