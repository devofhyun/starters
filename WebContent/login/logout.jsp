<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css"
	rel="stylesheet" />

<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">


<%@include file="../nav.jsp"%>
<link rel="stylesheet" href="assets/css/login_out/logout.css">

<title>로그아웃 되셨습니다.</title>

<div class="logout_container">
	<form class="form-horizontal" role="form" method="POST" action="/find_id_after">
		<div class="form">
			
			<div class="row">
				<div class="col-md-12 h2m">
					<h2>로그아웃</h2>
					<h6>로그아웃 되셨습니다. 감사합니다.</h6>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-md-10 inputbb">
				<div class="form-control-feedback">
					<!-- <a type="submit" class="btn loginbtn" href="starters">메인화면으로 돌아가기</a> -->
					<button type="button" class="btn btn-outline-secondary loginbtn" onclick = "backToMain()">메인화면으로 돌아가기</button>
					
				</div>
			</div>
		</div>
	</form>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
	var backToMain = function(){
		location.href = "starters";
	};
</script>