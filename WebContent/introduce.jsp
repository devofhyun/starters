<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>스타터스 : 스타터스 소개</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<%@include file = "../nav.jsp" %>
<link rel="stylesheet" href="assets/css/introduce.css" />
</head>

<body>
<div class="intro d-flex">
	<div onclick="something('1')"><span>튜터링</span></div>
	<div onclick="something('2')"><span>포트폴리오</span></div>
	<div onclick="something('3')"><span>면접</span></div>
	<div onclick="something('4')"><span>실시간톡</span></div>
	<div onclick="something('5')"><span>일지</span></div>
	<div onclick="something('6')"><span>후기</span></div>
</div>



<div class="introduce">
	<div id="intro-1">
		<div class="container">
			<h4>튜터링</h4>
			<span>IT계열 현직자와 다양한 주제의 튜터링을 진행할 수 있습니다.</span>
			<img src="../assets/image/튜터링1.png">
		</div>
	</div>
	<div id="intro-2">
		<div class="container">
			<h4>포트폴리오</h4>
			<span>튜터링 결과물을 올리거나 혹은 개인 작업물을 올릴 수 있습니다.</span>
			<img src="../assets/image/포폴1.png">
		</div>
	</div>
	<div id="intro-3">
		<div class="container">
			<h4>면접</h4>
			<span>기업은 튜티들의 포트폴리오를 보고 면접을 신청할 수 있습니다.
			<br>
			기업 목록은 기업페이지에서 볼 수 있습니다.</span>
			<img src="../assets/image/면접1.png" style="max-width: 400px;">
			</div>
	</div>
	<div id="intro-4">
		<div class="container">
			<h4>실시간톡</h4>
			<span>튜터링을 진행하면서 스타터스의 실시간톡 기능으로 실시간으로 질문과 답변을 받을 수 있습니다.</span>
			<img src="../assets/image/실시간톡1.png">
		</div>
	</div>
	<div id="intro-5">
		<div class="container">
			<h4>일지</h4>
			<span>튜터링 진행 상황과 파일을 올리고 피드백을 받을 수 있습니다.</span>
			<img src="../assets/image/일지1.png">
		</div>
	</div>
	<div id="intro-6">
		<div class="container">
			<h4>후기</h4>
			<span>튜터링 후 후기를 쓸 수 있습니다. 
			<br>
			좋아요를 누를 수 있으며 좋아요를 많이 받은 후기의 튜터는 특별튜터가 될 수 있습니다.</span>
			<img src="../assets/image/후기1.png">
		</div>
	</div>
</div>



<%@include file = "../footer.jsp" %>

  </body>
</html>

<script>
$(document).ready(function() {
	$("#intro-1").addClass("active");
});

function something(seq){
	var introDiv = $("#intro-" + seq).addClass("active");
	$('.introduce > div').css('display', 'none');
	introDiv.fadeIn();
}
</script>
