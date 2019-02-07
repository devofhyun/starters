<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>커뮤니티</title>

<link rel="stylesheet" href="assets/css/needcssjs.css" />
<link rel="stylesheet" href="assets/css/community_tab.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<%@include file="../nav.jsp"%>
<script>
$(document).ready(function(){
    $("#mypage").load("starters?cmd=mypage");
});
$(document).ready(function(){
   $("#tutoringInfo").load("starters?cmd=tutoringInfo");
});
$(document).ready(function(){
    $("#tutoringLike").load("starters?cmd=tutoringLike");
});
$(document).ready(function(){
    $("#interviewInfo").load("starters?cmd=interviewInfo");
});
$(document).ready(function(){
    $("#myInfo").load("starters?cmd=myInfo");
});
</script>
</head>
<body>

<div class="container">

    <!-- <h2>Toggleable Pills</h2> -->
    <br>
    <!-- Nav pills -->
    <div class="row">
        <div class="col-lg-9 col-md-9">
            <ul class="nav nav-pills" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="pill" href="#mypage">마이페이지</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#tutoringInfo">수강신청내역</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#tutoringLike">찜하기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#interviewInfo">면접내역</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#myInfo">나의정보</a>
                </li>
            </ul>
        </div>

    </div>
    <!-- /row -->

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="mypage" class="container tab-pane active">
        </div>

        <div id="tutoringInfo" class="container tab-pane fade">
        </div>

        <div id="tutoringLike" class="container tab-pane fade">
        </div>
        
          <div id="interviewInfo" class="container tab-pane fade">
        </div>
        
          <div id="myInfo" class="container tab-pane fade">
        </div>
    </div>
</div>
<script>

$(document).on('ready', function(){
    $('#input-1').rating({min: 0, max: 8, step: 0.1, stars: 8});
});

</script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</body>
</html>