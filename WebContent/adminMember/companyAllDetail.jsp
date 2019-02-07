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


<%@include file="../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/admin/Member/tutorMyInfo.css">
<title>스타터스 : 기업회원정보 - ${companyvo.name}</title>
<section>
	<article>
<div class="container">
   <form action="" method="post" class="selfinfo">
		<h3><i class="fas fa-user-edit mr-3"></i>기업회원정보</h3>
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
   <div class="text-right">
       <button class="btn btn-sm btn-outline-secondary mr-3" onclick="back()">목록</button>
   
   </div>

</div>

<%@include file="../footer2.jsp"%>
</article>
</section>

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

   
   var back = function() {
       location.href = "starters?cmd=adminMemberList";
    };
    
   </script>