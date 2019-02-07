<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../nav.jsp"%>
<%@page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/mypage/tutee/DailyDetail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">


<title>스타터스 : 튜터링 일지</title>
<body>
<div class="container">

  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutoringLike">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tuteeMypageList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=interviewList">결제 내역</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=interviewList">면접 현황</a>
    </li>
  </ul>


<h3 class="mt-5">튜터링 일지 상세</h3>
<br>

   <div class="regBox">
   
      <form>
      <c:forEach items="${dailyDetail}" var="dailyDetail">
         <div  class="form form-inline">
	         <label class="mr-5">일지 등록 날짜</label>
	        ${dailyDetail.tutoringRegisterDate}
         </div>
         
         <div class="form-group mt-5">
           <label for="comment">피드백</label>
           <textarea class="form-control" rows="5" id="comment" disabled >${dailyDetail.tutoringFeedback}</textarea>
         </div>
         
         <div class="form-group mt-1">
           <label for="comment" class="mr-5">튜터 파일</label>
           <a href ="../assets/tutoringDaily/${dailyDetail.tutorFile}">${dailyDetail.tutorFile}</a>
          </div>
      
        <div class="form-group mt-5">
           <label for="comment">튜티 한마디</label>
           <span class="tuteeC">${dailyDetail.tutoringRecord}</span>
         </div>

		<div class="form-group mt-1">
			<label for="comment" class="mr-5">튜티 파일</label>
	        <a href ="../assets/tuteeFile/${dailyDetail.tuteeFile}">${dailyDetail.tuteeFile}</a>
		</div>
         


        </c:forEach>
      </form>
      
   
   </div>


   <div class="Butn">
      <button class="btn btn-sm btn-secondary modify" onclick="modify()">수정하기</button>
      <button class="btn btn-sm btn-outline-secondary list ml-2" onclick="list()">목록</button>
   </div>

</div><!-- container 끝 -->


    <%@include file="../../footer.jsp"%>

</body>

<script src="assets/js/tabSizing.js"> </script>
<script>

$(window).on("load resize ", function() {
     var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
     $('.tbl-header').css({'padding-right':scrollWidth});
   }).resize();
   
	var modify = function() {
		location.href = "starters?cmd=tuteeDailyModify";
	};
	var list = function(){
		 location.href = "starters?cmd=tuteeDailyList&num="+${detailvo.tutoringApplyId};
	};
	
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