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



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../nav.jsp"%>
<link href="assets/css/mypage/tutee/DailyDetail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">


<title>스타터스 : 튜터링 일지</title>
</head>
<body>

<div class="container">
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

<h3 class="pt-5">튜터링 일지 상세</h3>
<br>
   <div class="regBox">   
      <form>
         <div  class="form form-inline">
         
         <label class="mr-5">일지 등록 날짜</label>
         <span>${detailvo.recordDate}</span>
         </div>
         
         <div  class="form form-inline">
         </div>
         
         <div class="form-group mt-5">
           <label for="comment">피드백</label>
           <textarea class="form-control" rows="5" id="comment"  disabled >${detailvo.tutoringFeedback}</textarea>
         </div>
         
         <div class="form-group mt-1">
           <label for="comment" class="mr-5">튜터 파일</label>
           <a href ="../assets/tutoringDaily/${detailvo.tutorFile}">${detailvo.tutorFile}</a>
          </div>
        
        <div class="form-group mt-5">
           <label for="comment">튜티 한마디</label>
           <span class="tuteeC">${detailvo.tutoringRecord}</span>
         </div>

		<div class="form-group mt-1">
			<label for="comment" class="mr-5">튜티 파일</label>
	        <a href ="../assets/tuteeFile/${detailvo.tuteeFile}">${detailvo.tuteeFile}</a>
		</div>
      </form>
	</div>
   <div class="Butn">
      <button class="btn btn-sm btn-secondary register" onclick="changeForm()">피드백 등록</button>
      <button class="btn btn-sm btn-outline-secondary ml-2 none" onclick="List()">목록</button>
   </div>
</div><!-- container 끝 -->
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


var List = function() {
	location.href = "starters?cmd=applyDailyList&num="+${detailvo.tutoringApplyId};
};
 var changeForm = function() {
	location.href = "starters?cmd=tutoringDailyModify";
} 
</script>