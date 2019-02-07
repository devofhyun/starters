<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale=1">
<link rel="stylesheet" type="text/css" href="assets/css/chat.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="assets/js/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>실시간 채팅</title>
	<%
		String id = null;
		String name = null;
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
			name = (String) session.getAttribute("name");
			// out.print("/////////////////////");
			// out.print(id);
		}
		String toID = null;
		if(request.getParameter("toID") != null){
			toID = (String) request.getParameter("toID");
		}
	%>
<script type="text/javascript">
//안읽은 메시지 갯수
function getUnread(){
	$.ajax({
		type: "POST",
		url:'starters?cmd=chatUnread',
		data:{
			userID: '<%=id%>',
		},
		success: function(result){
			if(result >=1){
				showUnread(result);
			}else{
				showUnread('');
			}
		}
	});
}
function getInfiniteUnread(){
	setInterval(function(){
		getUnread();}, 1000);
}
function showUnread(result){
	$('#unread').html(result);
}
	function autoClosingAlert(selector, delay){
		var alert = $(selector).alert();
		alert.show();
		//window.setTimeout(alert.hide(), delay); //  이부분 틀리면 다시 보기!! 원해는 "function{}"이거 들어감!!
		window.setTimeout(function(){alert.hide()}, delay);
		}
	function submitFunction(){
		var fromID = '<%=id%>'
		//console.log(fromID);
		var toID = '<%=toID%>'
		var chatContent = $('#chatContent').val();
		$.ajax({
			type : "POST",
			url : "starters?cmd=chatAction",
			data : {
				fromID : fromID,
				toID : encodeURIComponent(toID),
				chatContent : chatContent,
			},
			success: function(result){
				console.log("result"+result)
				if(result == 1){
					//  alert("메시지 전송에 성공하셨습니다.");
					autoClosingAlert('#successMessage', 4000);
				}
				else if(result == 0){
					// alert("이름과 내용을 모두 입력해주세요.");
					autoClosingAlert('#dangerMessage', 4000);
				}else{
					// alert("데이터베이스 오류가 발생하였습니다.");
					autoClosingAlert('#waringMessage', 4000);
				}
			}
		});
		$('#chatContent').val('');
	}
	
	var lastID = 0;
	function chatListFunction(type){
		var fromID = '<%=id%>'
		var toID = '<%=toID%>'
		$.ajax({
			type : "POST",
			url : "starters?cmd=chatList",
			data : {
				fromID : fromID,
				toID : toID,
				listType: type,
			},
			success: function(data){
				//console.log("parsed"+data);
				if(data == "") return;
				var parsed = data;
				console.log(parsed);
				var result = parsed.result;
				console.log(result);
				for(var i = 0; i < result.length; i++){
					//console.log("///"+result[i][0].value);
					if(result[i]["value1"] == fromID){
						result[i]["value1"] = '나';
					}
					addChat(result[i]["value1"], result[i]["value3"], result[i]["value4"]);
				}
				lastID = Number(parsed.last);
				//console.log("lastID///////////////"+lastID);
			}
			});
	}
		function addChat(chatName, chatContent, chatTime){
			$('#chatList').append('<div class = "row">' + 
					'<div class = "col-lg-12">' +
					'<div class = "media">' +
					'<a class = "pull-left" href = "#">' +
					'<img class = "media-object img-circle" style =" width: 30px; height:30px;" src = "assets/image/user.png" alt = "회원이미지">' +
					'</a>' +
					'<div class = "media-body">' +
					'<h4 class = "media-heading"><strong>' +
					chatName + 
					'</strong><span class = "small pull-right">' +
					chatTime + 
					'</span>' +
					'</h4>' +
					'<p>' +
					chatContent +
					'</p>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<hr style="margin-bottom: 0;">');
			$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
		}
		function getInfiniteChat(){
			setInterval(function(){
				
				chatListFunction(lastID)}, 3000);
		}

</script>
</head>
<body>

	<nav class="navbar navbar-default">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
		<div class = "collapse navbar-collapse" id = "bs-example-navbar-collapse-1">
		<div class="navbar-header">
			<a class="navbar-brand" href="starters?cmd=chat">실시간 채팅</a>
		</div>
			<ul class = "nav navbar-nav">
				<li class = "active"><a href = "starters?cmd=chat">채팅방가기</a></li>
				<li><a href = "starters?cmd=messageBoxMain">메시지함<span id = "unread" class = "label label-info"></</span></a></li>
			</ul>
		</div>
	</nav>
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class="portlet portlet-default">
					<div class="portlet-heading">
					<div class = "portlet-title">
						<h4><i class = "fa fa-circle text-green"></i>실시간 채팅창</h4>
					</div>
						<div class="clearfix"></div>
					</div>
					<div id="chat" class="panel-collapse collapse in">
						<div id="chatList" class="porlet-body chat-widget"
							style="overflow-y: unset; overflow-x: hidden; width: auto; height: 400px;"></div>
						<div class="portlet-footer">
							
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style = "height: 80px;" name = "chatContent" id = "chatContent" class = "form-control" placeholder = "메시지를 입력하세요."></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type = "button" class = "btn btn-info btn-block" onclick = "submitFunction();">전송</button>
									<div class = "clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class = "alert alert-success" id = "successMessage" style = "display:none;">
		<strong>메시지 전송에 성공했습니다.</strong>
	</div>
	<div class = "alert alert-danger" id = "dangerMessage" style = "display:none;">
		<strong>이름과 내용을 모두 입력해주세요.</strong>
	</div>
	<div class = "alert alert-warning" id = "waringMessage" style = "display:none;">
		<strong>데이터베이스 오류가 발생했습니다.</strong>
	</div>
	
<script type = "text/javascript">
$(document).ready(function(){
	getUnread();
	chatListFunction('0');
	getInfiniteChat();
	getInfiniteUnread();
});

</script>



</body>

</html>