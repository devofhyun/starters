<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
		String userName = null;
	String userId = null;
		if (session.getAttribute("id") != null) {
			userName = (String) session.getAttribute("name");
			userId = (String) session.getAttribute("id");
		}
		String sentId = null;
		if(request.getParameter("sentId") != null){
			sentId = (String) request.getParameter("sentId");
		}
	%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale=1">
<link rel="stylesheet" type="text/css" href="assets/css/chat.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="assets/js/jquery-3.3.1.js"></script>
<script type = "text/javascript">
	function autoClosingAlert(selector, delay){
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() { alert.hide()}, delay);
	}
	function submitFunction(){
		var receptionId = '<%=userId %>';
		var sentId = '<%= sentId%>';
		var chatContent = $('#chatContent').val();
		$.ajax({
			type: "POST",
			url: 
		});
	}
</script>
<title>실시간 채팅</title>
</head>
<body>
	
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="starters?cmd=chat">실시간 채팅</a>
			<p class="username"><%= userName %></p>
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
							style="overflow-y: auto; width: auto; height: 600px;"></div>
						<div class="portlet-footer">
							<div class="row">
								<div class="form-group col-xs-4">
									<input style="height: 40px;" type="text" id="text"
										id="chatName" class="form-control" placeholder="이름"
										maxlength="8">
								</div>
							</div>
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style = "height: 80px;" id = "chatContent" class = "form-control" placeholder = "메시지를 입력하세요." maxlength = "100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type = "button" class = "btn btn-default pull-right" onclick = "submitFunction();">전송</button>
									<div class = "clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>






</body>

</html>