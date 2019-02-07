<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
   <form action="" method="post" class="selfinfo">

  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=companyInfoDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyLikeList">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyInterview">면접내역 및 현황</a>
    </li>
  </ul>

<h4 class="mt-5"><i class="fas fa-user-edit mr-3"></i>나의 정보</h4>
      <br>
      
     
      <div class="info-container">
         <div class="selfinfo1 col-md-12">
         <input type="hidden">
         <div class="form-group">
            <label for="id_full_name" class="col-3">기업명</label>
            <span>${companyvo.name}</span>
         </div>


         <div class="form-group">
            <label for="id_username" class="col-3">아이디</label>
            <span>${companyvo.companyId}</span>
         </div>
         
         
	        <div class="form-group">
               <label for="id_email" class="col-3"> 이메일</label> <span>${fn:substring(companyvo.email,0,fn:indexOf(companyvo.email,'@')) }
                  @
                  ${fn:substring(companyvo.email,fn:indexOf(companyvo.email,'@')+1,fn:length(companyvo.email)) }</span>

            </div>
            
            <div class="form-group">
               <label for="id_phone_number" class="col-3">전화번호</label> <span>${fn:substring(companyvo.phoneNum,0,3)}
                  - </span> <span>${fn:substring(companyvo.phoneNum,3,7)} - </span> <span>${fn:substring(companyvo.phoneNum,7,11)}</span>
            </div>


         <div class="form-group">
            <label for="id_address" class="col-3">주소</label>
			<span>${companyvo.address}</span>
         </div>

         <div class="form-group">
            <label for="id_address" class="col-3">홈페이지</label>
			<span>${companyvo.companyUrl}</span>
         </div>
         

         <div class="form-group">
            <label for="id_address" class="col-3">기업 규모</label>
			<span>${companyvo.companyRange}</span>
         </div>

         <div class="form-group">
            <label for="id_address" class="col-3">설립연도</label>
			<span>${companyvo.companyBirth}</span>
         </div>

         <c:forEach items="${image}" var="ImageVO">
            <div class="form-group">
            <label for="id_address" class="col-3">이미지</label>
			<span>${ImageVO.img}</span>
            </div>
         </c:forEach>
         
           <div class="form-group">
               <label for="id_profile" class="col-12">기업 소개</label>
				<span class="profile">${companyvo.companyIntroduce}</span>
            </div>


      </div>
   </div>
   </form>
   
   
   <div class="btnzip">
	   <c:if test="${id == companyvo.companyId}">
	      <button class="btn btn-outline-secondary edit" onclick="changeForm()">수정하기</button>
	   </c:if>
	   
	   <button class="btn btn-outline-danger edit" data-toggle="modal"
	      id="deleteMemberModal ${companyvo.companyId}"
	      data-target="#deleteModal${companyvo.companyId}"
	      name="deleteMember${companyvo.companyId}">회원탈퇴</button>
	</div>
   <!-- 결제정보를 가져오기 위한 모달 -->

   <div class="modal fade" id="deleteModal${companyvo.companyId}" role="dialog">
      <div class="modal-dialog modal-dialog-centered">
         <div class="modal-content">
            <div class="modal-header">
               <h4 class="modal-title">회원 탈퇴하기</h4>
            </div>
            <div class="modal-body">
               <p>${companyvo.companyId}님의 정보를 모두 삭제하기 위해 비밀번호를 입력해주세요.</p>
              <%--<form method="post"
                  action="starters?cmd=deleteCompanyMemberAction"
                  id="deleteMember" name="deleteForm"> --%> 
                  <input type="text" name="passwd"  class="form-control form-control-sm"
                     id="inputPasswd">
               <%--</form> --%> 
            </div>

            <div class="modal-footer">
               <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">취소</button>
               <button type="submit" class="btn btn-outline-danger"
                  onclick="inputCheck()">탈퇴하기</button>
            </div>
         </div>
      </div>
   </div>
   

</div>



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
    location.href = "starters?cmd=companyInfo";
 };
   
   function inputCheck() {
      //console.log($('input[name=firstTime]:checked').length);
      if ($('input[name=passwd]')=="") {
         alert("비밀번호를 입력해주세요.");
         return false;
      };
      var passwd = $("#inputPasswd").val();
      //console.log("passwd"+passwd);
      $.ajax({
    		type:"POST",
    		url: 'starters?cmd=deleteCompanyMemberAction',
    		data:{passwd :passwd},
    		success: function(data){
    			//console.log("data값///"+data.trim());
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