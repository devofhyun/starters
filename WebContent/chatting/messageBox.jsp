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
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>실시간 채팅</title>
	<%
		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
			//out.print("/////////////////////");
		//	out.print(id);
		}
		String toID = null;
		if(request.getParameter("toID") != null){
			toID = (String) request.getParameter("toID");
		}
	%>
<script type="text/javascript">
// 안읽은 메시지 갯수
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
function chatBoxFunction(){
	var userID = '<%=id%>'
	$.ajax({
		type: "POST",
		url:'starters?cmd=messageBox',
		data:{
			userID: userID,
		},
		success: function(data){
			//console.log("data////////"+data);
			if(data == '') return;
			$('#boxTable').html('');
			var parsed = data;
			var result = parsed.result;
			for(var i = 0; i < result.length; i++){
				if(result[i]["value2"] == userID){
					result[i]["value2"] = result[i]["value1"];
				}else{
					result[i]["value1"] = result[i]["value2"];
				}
				addBox(result[i]["value2"], result[i]["value1"], result[i]["value3"], result[i]["value4"], result[i]["value5"]); // 메시지 목록 출력
			}
		}
	}); 
}
function addBox(lastID, toID, chatContent, chatTime, unread){
	$('#boxTable').append('<tr onclick = "location.href = \'starters?cmd=chatStart&toID=' + toID +'\'">' + 
			'<td style = "width: 150px; text-align: center;"><h5>' + lastID +	'<span class = "label label-info">' + unread +'</span>' +  '</h5></td>' +
			'<td>' + 
			'<h5>' + chatContent + 
			'</h5><div class = "pull-right">' + chatTime + '</div>' + 
			'</td>'+
			'</tr>');
}
function getInfiniteBox(){
	setInterval(function(){
		chatBoxFunction();
	}, 3000);
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
				<li><a href = "starters?cmd=chat">채팅방가기</a></li>
				<li class = "active"><a href = "starters?cmd=messageBoxMain">메시지함<span id = "unread" class = "label label-info"></span></a></li>
			</ul>
		</div>
	</nav>
	<div class = "container">
		<table class = "table" style = "margin:0 auto;">
			<thead>
				<tr>
					<th><h4>주고받은 메시지 목록</h4></th>
				</tr>
			</thead>
			<div style = "overflow-y:auto; width:100%; max-height: 450px;">
				<table class = "table table-bordered table-hover" style = "text-align: center; border: 1px solid #dddddd;">
					<tbody id = "boxTable">
					
					</tbody>
				</table>
			</div>
		</table>
	</div>
	<!-- 모달 만든건데 현재는 되지 않음 -->
	<div class = "modal fade" id = "checkModal" tabindex = "-1" role = "dialog" aria-hidden = "true">
	<div class = "vertical-alignment-helper">
		<div class = "modal-dialog vertical-align-center">
			<div id = "checkType" class = "modal-content panel-info">
				<div class = "modal-header panel-heading">
				<button type = "button" class = "close" data-dismiss = "modal">
				<span aria-hidden = "true"> &times</span>
				<span class = "sr-only">close</span>
				</button>
				<h4 class = "modal-title">확인메시지</h4>
				</div>
			</div>
			<div id = "checkMessage" class = "modal-body">
			</div>
			<div class = "modal-footer">
				<button type = "button" class = "btn btn-primary" data-dismiss = "modal">확인</button>
			</div>
		</div>
	</div>
	</div>
<%
if(id != null){
%>
<script type = "text/javascript">
	$(document).ready(function(){
		getUnread();
		getInfiniteUnread();
		chatBoxFunction();
		getInfiniteBox();
		
	})
</script>
<%
}
%>

</body>

</html>