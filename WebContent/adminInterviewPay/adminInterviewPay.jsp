<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="com.starters.*"%>

	
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


<%@include file="../nav2.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="assets/css/admin/register_tutee.css">
<title>기업 면접 신청 가격 변경</title>

<section>
<article>
<div class="container mt-5 py-5">
	<form method="post" class="form-horizontal selfinfo" name="registerForm"
		action="starters?cmd=registerInterviewPayAction">
		<h3 class="my-5">면접 신청 가격 변경</h3>
		
		<div class="selfinfo1">
			<input type="hidden">
			
			<div class="row">
			<label for="name" class="col-3">면접 신청 가격</label>
				<div class="input-group col-4 mb-3">
				     <input type="text" class="form-control form-control-sm border-right-0"
						name="payMoney" id ="payMoney" placeholder="${companypricevo.price }" required="required">
				    <div class="input-group-append">
				      <span class="input-group-text form-control form-control-sm border-left-0" style="background-color: #fff">원</span>
				      <button type="submit" class="btn btn-sm btn-secondary yesnobtn" id="btnSubmit">수정</button>
				    </div>
				</div>
			</div>
		</div>
	</form>
	<!--form 닫기-->
</div>
<!--container닫기-->

<%@include file="../footer.jsp"%>

</article>
</section>


<script type="text/javascript">

/* 	// 회원가입체크
	window.onload = function() {
		registerForm.name.focus();
		document.getElementById("btnSubmit").onclick = inputCheck;

	} */

	
</script>