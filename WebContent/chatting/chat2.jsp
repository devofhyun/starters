<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale=1">
<link rel="stylesheet" type="text/css" href="assets/css/chat.css">
<link rel="stylesheet" type="text/css"
	href="assets/bootstrap-4.1.1/css/bootstrap.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<title>실시간 채팅</title>
</head>
<body>
	<%
		String userName = null;
		if (session.getAttribute("id") != null) {
			userName = (String) session.getAttribute("name");
		}
	%>
	<nav class="navbar navbar-dark bg-dark">
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
								<div class="form-group col-xs-12">
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





<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>

</body>

</html>