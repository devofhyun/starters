<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.starters.TuteeMypageDailyVO"%>

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
      <a class="nav-link" href="starters?cmd=tuteeMypageList">면접 현황</a>
    </li>
  </ul>
<c:choose>
   <c:when test="${DailyListNum == 0}">
      <div class="mb-5 text-center" style="min-height: 300px; margin-top: 200px;">
            <h4>튜터링일지가 아직 없습니다. 작성해보세요!</h4>
         </div>
    </c:when>

      <c:otherwise>

   
      <div class="row Dailytitle">
         <div class="col-6">
         <h4>${detailvo.tutoringTitle} - ${detailvo.tutorName}</h4>
         </div>
         <div class="col-6">
            <h6>${DailyListNum}개의 일지</h6>
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
               <c:forEach items="${tutoringDailyList}" var="tutoringDailyList">
                  <tr onclick="location.href='starters?cmd=tuteeDailyDetail&num=${tutoringDailyList.tutoringRecordId}'" style="cursor: pointer;">
                     <td>${tutoringDailyList.tutoringRecordId}</td>
                     <td>${tutoringDailyList.tutoringFeedback}</td>
                     <td>${tutoringDailyList.tutoringRecord}</td>
                     <td>${tutoringDailyList.tutoringRegisterDate}</td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </div>
      
   </c:otherwise>
   </c:choose>
      
      </div>

      <div class="container Butn">
         <button class="btn btn-sm btn-secondary list" onclick="List()">목록</button>
         <button class="btn btn-sm btn-secondary register" onclick="register()">등록하기</button>
      </div>
   <!-- container 끝 -->

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
      location.href = "starters?cmd=tuteeMypageList";
   }
   var register = function() {
      location.href = "starters?cmd=tuteeDailyRegister";
   }
</script>