<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>커뮤니티</title>

<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" /> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css">
<link rel="stylesheet" href="assets/css/community_tab.css" />
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


</head>
<body>

<%@include file="../nav.jsp"%>
<div class="container">

    <!-- <h2>Toggleable Pills</h2> -->
    <br>
    <!-- Nav pills -->
    <div class="row">
        <div class="col-lg-9 col-md-9">
            <ul class="nav nav-pills" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="pill" href="#notice"  onclick="p(this); return false;">공지사항</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#qna">Q&A</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#review" >후기게시판</a>
                </li>
            </ul>
        </div>

    </div>
    <!-- /row -->

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="notice" class="container tab-pane active">
        <div class = "noticehere"></div>
        </div>

        <div id="qna" class="container tab-pane fade">
         <div class = "qnahere"></div>
        </div>

        <div id="review" class="container tab-pane fade">
         <div class = "reviewhere"></div>
        </div>
    </div>
</div>



<script>
$(document).ready(function(){
    $("#notice").load("starters?cmd=notice");
});
<%--$(document).ready(function(){
   $("#qna").load("starters?cmd=qna");
});
$(document).ready(function(){
    $("#review").load("starters?cmd=review");
}); --%>

$(document).on('ready', function(){
    $('#input-1').rating({min: 0, max: 8, step: 0.1, stars: 8});
});

function p(noticeButton) {
	var rStr = noticeButton.href;
	//console.log(rStr);
	$.ajax({
		url : "starters?cmd=notice",
		success : function(result) {
		$("div.noticehere").html(result);
		}
	})
	return true;

}
</script>

</body>
</html>