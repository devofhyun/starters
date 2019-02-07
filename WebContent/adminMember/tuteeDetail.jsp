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


<!-- 부트스트랩 date -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
<link href="assets/css/fSelect.css" rel="stylesheet" type="text/css">


<script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/fSelect.js"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@include file="../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/admin/Member/tutorMyInfo.css">
<title>스타터스 : 튜티회원정보 - ${tuteevo.name}</title>
<section>
	<article>

<div class="container">
	<form action="" method="post" class="selfinfo">
		<h3><i class="fas fa-user-edit mr-3"></i>튜티회원정보</h3>
		<br>
		
		
		<div class="info-container">
		
		<div class="selfinfo1 col-md-12">
			<input type="hidden">
			
			
			<div class="form-group">
				<label for="id_full_name" class="col-3">이름</label>
				<span>${tuteevo.name}</span>
			</div>
			
			<div class="form-group">
				<label for="id_username" class="col-3">아이디</label>
				<span>${tuteevo.memberId}</span>
			</div>
			
			
			<div class="form-group">
				<label for="id_email" class="col-3"> 이메일</label>
				<span>${fn:substring(tuteevo.email,0,fn:indexOf(tuteevo.email,'@')) } @ ${fn:substring(tuteevo.email,fn:indexOf(tuteevo.email,'@')+1,fn:length(tuteevo.email)) }</span>

			</div>

			<div class="form-group">
				<label for="id_birth_date_year" class="col-3">생년월일</label>
				<span>${fn:substring(tuteevo.birth,0,4)}년 </span>
				<span>${fn:substring(tuteevo.birth,5,7)}월 </span>
				<span>${fn:substring(tuteevo.birth,8,10)}일</span>

			</div>
			
			<div class="form-group">
				<label for="id_gender" class="col-3">성별</label>
				<span><c:if test="${tuteevo.gender=='F'}">
				여
				</c:if>
				<c:if test="${tuteevo.gender=='M'}">
				남
				</c:if></span>
			</div>

			<div class="form-group">
				<label for="id_phone_number" class="col-3">핸드폰번호</label> 
				<span>${fn:substring(tuteevo.phoneNum,0,3)} - </span>
				<span>${fn:substring(tuteevo.phoneNum,3,7)} - </span>
				<span>${fn:substring(tuteevo.phoneNum,7,11)}</span>
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
				<label for="id_profile" class="col-12">자기소개</label>
				<span class="profile">${tuteevo.intro}</span>
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
	          location.href = "starters?cmd=adminMemberTuteeList";
	       };
	</script>
	
	