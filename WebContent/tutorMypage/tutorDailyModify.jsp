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
<link rel="stylesheet" type="text/css" href="assets/css/mypage/tutee/DailyDetail.css">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">

<%@page errorPage="errorPage.jsp"%>



<title>스타터스 : 튜터링 일지</title>
<body>

	<div class="container">

  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tutorDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringList">튜터링 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringTuteeList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorTutoringCalenderList1">튜터링 일정</a>
    </li>
  </ul>
		
		<h3>튜터링 피드백 등록</h3>
		<br>

		<div class="regBox">

			<form method="post" action="starters?cmd=tutoringDailyModifyAction"
				id="upForm" enctype="multipart/form-data" name="dailyForm">
				<div class="form form-inline">

					<label class="mr-5">일지 등록 날짜</label> 
					<span>${dailyInfo.recordDate}</span>
				</div>

				<div class="form form-inline"></div>

				<div class="form-group mt-5">
					<label for="comment">피드백</label>
					<textarea class="form-control" rows="5" id="comment"
						name="tutoringFeedback">${dailyInfo.tutoringFeedback}</textarea>
				</div>

					<div class="filebox">
						<button type="button" class="filebtn btn btn-sm btn-secondary">
						<label for="tutorFile">업로드 </label>
						</button>
						<input class="upload-name" value="파일선택" disabled="disabled">
						<input type="file" id="tutorFile" type="file" name="tutorFile"  class="upload-hidden">
					</div>

				<div class="form-group mt-5">
					<label for="comment" class="mr-5">튜티 한마디</label>
					<span class="tuteeC">${dailyInfo.tutoringRecord}</span>
				</div>
				
				

				<div class="form-group mt-1">
					<label for="comment" class="mr-5">튜티 파일</label>
					<a>${dailyInfo.tuteeFile}</a>
				</div>
			</form>


		</div>


		<div class="Butn">
			<button class="btn btn-sm btn-secondary modify" type="submit" id="btnSubmit">피드백 등록</button>
			<button class="btn btn-sm btn-outline-danger ml-2 none" onclick="cancel()">취소</button>
		</div>

	</div>
	<!-- container 끝 -->


	<%@include file="../../footer.jsp"%>

</body>

<script src="assets/js/tabSizing.js"> </script>
 
<script> 


$(window).resize(function (){
	if($(document).width() < 768){
		verCul();
	} else if($(document).width() > 768){
		horCul()}
});

$(document).ready(function(){ 
	if($(document).width() < 768){
		verCul();
	}
	var fileTarget = $('.filebox .upload-hidden'); 
	fileTarget.on('change', function(){ // 값이 변경되면 
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		} else { // old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
			}// 추출한 파일명 삽입 
		$(this).siblings('.upload-name').val(filename); 
	}); 
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
	
	window.onload = function() {
		dailyForm.tutoringFeedback.focus();
		document.getElementById("btnSubmit").onclick = inputCheck;
	}
	function inputCheck() {
		if (dailyForm.tutoringFeedback.value == "") {
			alert("피드백 내용을 입력해주세요.");
			dailyForm.tutoringFeedback.focus();
			return false;
		}
		dailyForm.submit();
	}
	
	var cancel = function() {
		location.href = "starters?cmd=tutortutoringDailyDetail&num="+${dailyInfo.tutoringRecordId};
	}
	
 
</script>