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
// 튜터 찾기
	function findFunction(){
		var userID = $('#findID').val();
		$.ajax({
			type:"POST",
			url: 'starters?cmd=chatfindId',
			data:{userID :userID},
			success: function(data){
				//console.log(data);
				if(data.trim() !== ""){
					 alert('튜터찾기에 성공했습니다.');
					// $('#checkMessage').html('튜터찾기에 성공했습니다.');
					
					//$('#checkType').attr('class', 'modal-content panel-success');
					//console.log(data);
					getTutor(data);
				}else{
					alert('찾으시는 튜터가 없습니다.');
					//$('#checkMessage').html('찾으시는 튜터가 없습니다.');
				//	 $('#checkType').attr('class', 'modal-content panel-warning');
					failTutor(data);
					
				}
				// $("#checkModal").modal("show");
			}		
		});
	}
		function getTutor(findID){
			var userID = $('#findID').val();
			//console.log("findID/////////"+findID);
			$('#tutorResult').html(
				'<thread>' + 
				'<tr>' + 
				'<th><h4>검색결과</h4></th>' +
				'</tr>' +
				'</thead>' +
				'<tbody>' +
				'<tr>' +
				'<td style = "text-align:center;"><h4>' +userID +'['+findID + ']'+'</h4><a href="starters?cmd=chatStart&toID='+ findID+'" class = "btn btn-info pull-right">' + '메시지 보내기</a></td>'+
				'</tr>' +
				'</tbody>'
			);
		}
		function failTutor(findID){
			$('#tutorResult').html('');
		}
		
		// 튜티찾기
		function findtuteeFunction(){
		var userID = $('#findID').val();
		$.ajax({
			type:"POST",
			url: 'starters?cmd=chatfindTuteeId',
			data:{userID :userID},
			success: function(data){
				//console.log("data값///"+data.trim());
				if(data.trim() !== ""){
					 alert('튜티찾기에 성공했습니다.');
						getTutee(data);
					
				}else{
					//console.log("data값@@@@"+data.trim());
					alert('찾으시는 튜티가 없습니다.');
					failTutee(data);
				}
				// $("#checkModal").modal("show");
			}		
		});
	}
		function getTutee(findID){
			var userID = $('#findID').val();
			$('#tutorResult').html(
				'<thead>' + 
				'<tr>' + 
				'<th><h4>검색결과</h4></th>' +
				'</tr>' +
				'</thead>' +
				'<tbody>' +
				'<tr>' +
				'<td style = "text-align:center;"><h4>' +userID+'['+findID+ ']'+'</h4><a href="starters?cmd=chatStart&toID='+ findID+'" class = "btn btn-info pull-right">' + '메시지 보내기</a></td>'+
				'</tr>' +
				'</tbody>'
			);
		}
		function failTutee(findID){
			$('#tutorResult').html('');
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
	<div class = "container">
		<table class = "table table-bordered table-hover" style = "text-align:center; border : 1px solid #dddddd">
		<c:choose>
			<c:when test = '${allMembers == 50}'>
		<thead>
			<tr>
				<th colspan = "2"><h4>검색으로 튜터찾기</h4></th>
			</tr>
		</thead>
		<tbody>
		
			<tr>
				<td style = "width:110px;"><h5>튜터 이름</h5></td>
				<td><input class  = "form-control" type = "text" id = "findID" maxlength = "20" placeholder = "찾으실 튜터명을 입력하세요."></td>
			</tr>
			<tr>
				<td colspan = "2"><button class = "btn btn-info pull-right" onclick = "findFunction();">검색</button></td>
			</tr>
			</c:when>
			<c:otherwise>
			<thead>
			<tr>
				<th colspan = "2"><h4>검색으로 튜티찾기</h4></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style = "width:110px;"><h5>튜티 이름</h5></td>
				<td><input class  = "form-control" type = "text" id = "findID" maxlength = "20" placeholder = "찾으실 튜티명을 입력하세요."></td>
			</tr>
			<tr>
				<td colspan = "2"><button class = "btn btn-info pull-right" onclick = "findtuteeFunction();">검색</button></td>
			</tr>
			</c:otherwise>
			</c:choose>
		</tbody>
		</table>
	</div>
	<div class = "container">
	<table id = "tutorResult" class = "table table-bordered table-hover" style = "text-align:center; border : 1px solid #dddddd">
	
		
				
				<c:choose>
				
			<c:when test = '${allMembers == 50}'>
			<thead>
			<tr>
				<th><h4>튜터명</h4></th>
				</tr>
				</thead>
				<c:forEach items="${list}" var="find">
			<tbody>
			
			<tr>
			
				<td style = "text-align:center;"><h4>${find.memberName}[${find.memberId}]</h4><a href="starters?cmd=chatStart&toID=${find.memberId}" class = "btn btn-info pull-right">메시지 보내기</a></td>
				</tr>
				</tbody>
	</c:forEach>
				</c:when>
			<c:otherwise>
			<thead>
			<tr>
			<th><h4>튜티명</h4></th>
			</tr>
				</thead>
				<c:forEach items="${list1}" var="find">
			<tbody>
			<tr>
			
				<td style = "text-align:center;"><h4>${find.memberName}[${find.memberId}]</h4><a href="starters?cmd=chatStart&toID=${find.memberId}" class = "btn btn-info pull-right">메시지 보내기</a></td>
				</tr>
				</tbody>
	</c:forEach>
			</c:otherwise>
			</c:choose>
				
				
	</table>
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
	})
</script>
<%
}
%>

</body>

</html>