<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<%@include file = "../nav.jsp" %>
<link rel="stylesheet" type="text/css" href="assets/css/register_member/register_box.css">


<title>회원가입 분류</title>

	<div class="container">
		<h3>회원가입</h3>
		<div class="title">Starters에 오신것을 환영합니다.</div>
		<div class="row">
			<div class="col-md-4">
				<div class="card mb-4 shadow-sm" onclick="location.href='starters?cmd=registerTutee'">
					<div class="card-body">
						<div>
							<div class="registerbox">
								<p class="card-text">튜티 회원가입</p>
								<button type="button" class="btn col-4 col-md-4 btn-sm btn-outline-secondary" onclick = "tuteeRegister()">바로가기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card mb-4 shadow-sm" onclick="location.href='starters?cmd=registerTutor'">
					<div class="card-body">
						<div>
							<div class="registerbox">
								<p class="card-text">튜터 회원가입</p>
								<!-- <button type="button" class="btn col-md-4 btn-sm btn-outline-secondary"><a href="starters?cmd=registerTutor" id="registerTutor">바로가기</a></button> -->
								<button type="button" class="btn col-4 col-md-4 btn-sm btn-outline-secondary" onclick = "tutorRegister()">바로가기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card mb-4 shadow-sm" onclick="location.href='starters?cmd=registerCompany'">
					<div class="card-body">
						<div>
							<div class="registerbox">
								<p class="card-text">기업 회원가입</p>
								<button type="button" class="btn col-4 col-md-4 btn-sm btn-outline-secondary" onclick = "companyRegister()">바로가기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<%@include file = "../footer.jsp" %>
		
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var tuteeRegister = function(){
		location.href = "starters?cmd=registerTutee";
	};
	var tutorRegister = function(){
		location.href = "starters?cmd=registerTutor";
	};
	var companyRegister = function(){
		location.href = "starters?cmd=registerCompany";
	};
</script>