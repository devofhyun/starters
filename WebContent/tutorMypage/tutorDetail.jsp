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
   href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<!-- 부트스트랩 date -->
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
<link href="assets/css/fSelect.css" rel="stylesheet" type="text/css">


<script type='text/javascript'
   src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/fSelect.js"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@include file="../nav.jsp"%>
<link href="assets/css/mypage/tutor/tutorMyInfo.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">


<title>스타터스 : 나의 정보</title>

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
		
  
   <form action="" method="post" class="selfinfo">
		<h3><i class="fas fa-user-edit mr-3"></i>나의 정보</h3>
      <br>

      <div class="info-container">
         <div class="selfinfo1 col-md-12">
            <input type="hidden">
            <div class="form-group">
               <label for="id_full_name" class="col-3">이름</label> <span>${tutorvo.name}</span>

            </div>


            <div class="form-group">
               <label for="name" class="col-3">아이디</label> <span>${tutorvo.memberId}</span>

            </div>

            <div class="form-group">
               <label for="id_email" class="col-3"> 이메일</label> <span>${fn:substring(tutorvo.email,0,fn:indexOf(tutorvo.email,'@')) }
                  @
                  ${fn:substring(tutorvo.email,fn:indexOf(tutorvo.email,'@')+1,fn:length(tutorvo.email)) }</span>

            </div>

            <div class="form-group">
               <label for="id_birth_date_year" class="col-3">생년월일</label> <span>${fn:substring(tutorvo.birth,0,4)}년
               </span> <span>${fn:substring(tutorvo.birth,5,7)}월 </span> <span>${fn:substring(tutorvo.birth,8,10)}일</span>

            </div>

            <div class="form-group">
               <label for="id_gender" class="col-3">성별</label> <span><c:if
                     test="${tutorvo.gender=='F'}">
            여
            </c:if> <c:if test="${tutorvo.gender=='M'}">
            남
            </c:if></span>
            </div>

            <div class="form-group">
               <label for="id_phone_number" class="col-3">핸드폰번호</label> <span>${fn:substring(tutorvo.phoneNum,0,3)}
                  - </span> <span>${fn:substring(tutorvo.phoneNum,3,7)} - </span> <span>${fn:substring(tutorvo.phoneNum,7,11)}</span>
            </div>

            <div class="form-group">
               <label for="id_job_selects1" class="col-3">직종</label>
				<div class="col-8 jobtable">
                  <c:forEach items="${sjob}" var="StringJobSelectVO" varStatus="sjs">
						<span class="job">${StringJobSelectVO.getMiddleCategName()} </span>
	               </c:forEach>
               </div>
            </div>
            <div class="form-group">
               <label for="id_job_file" class="col-3">이력서 파일</label> <span>${tutorvo.resume}</span>
            </div>
            <div class="form-group">
               <label for="id_portfolio_file" class="col-3">포트폴리오 파일</label> <span>${tutorvo.portfolio}</span>
            </div>

            <div class="form-group">
               <label for="id_profile" class="col-12">자기소개</label> <span
                  class="profile">${tutorvo.intro}</span>
            </div>


         </div>
      </div>
   </form>

   <div class="btnzip">
   

         <button class="btn btn-outline-secondary edit" onclick="changeForm()">수정하기</button>

	   	<button class="btn btn-outline-danger edit" data-toggle="modal"
	      id="deleteMemberModal ${tutorvo.memberId}"
	      data-target="#deleteModal${tutorvo.memberId}"
	      name="deleteMember${tutorvo.memberId}">탈퇴</button>

   </div>


   <!-- 결제정보를 가져오기 위한 모달 -->

   <div class="modal fade" id="deleteModal${tutorvo.memberId}" role="dialog">
      <div class="modal-dialog modal-dialog-centered">
         <div class="modal-content">
            <div class="modal-header">
               <h4 class="modal-title">회원 탈퇴하기</h4>
            </div>
            <div class="modal-body">
               <p>${tutorvo.memberId}님의 정보를 모두 삭제하기 위해 비밀번호를 입력해주세요.</p>
                  <input type="text" name="passwd" 
                     id="inputPasswd" class="form-control form-control-sm">
            </div>

            <div class="modal-footer">
               <button type="button" class="btn btn-outline-secondary btn-sm" data-dismiss="modal">취소</button>
               <button type="submit" class="btn btn-outline-danger btn-sm"
                  onclick="inputCheck()">탈퇴하기</button>
            </div>
         </div>
      </div>
   </div>

</div>



<%@include file="../footer.jsp"%>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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

   var changeForm = function() {
      location.href = "starters?cmd=tutorModify";
   };
   
      function inputCheck() {
            //console.log($('input[name=firstTime]:checked').length);
            if ($('input[name=passwd]')=="") {
               alert("비밀번호를 입력해주세요.");
               return false;
            }
            var passwd = $("#inputPasswd").val();
           //  console.log("passwd"+passwd);
            $.ajax({
                type:"POST",
                url: 'starters?cmd=deleteTutorMemberAction',
                data:{passwd :passwd},
                success: function(data){
                 //  console.log("data값///"+data.trim());
                    if(data) {
                          data = data.trim();
                       }
                    if(data == 1) {
                       alert("탈퇴되었습니다.");
                          window.location.href = "starters";
                       } else {
                          alert("비밀번호를 확인해주세요.");
                      }
                }      
             });
         };


   </script>