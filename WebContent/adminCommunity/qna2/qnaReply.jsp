<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">

<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<link rel="stylesheet" href="assets/css/notice_Detail.css" />
<link href='https://fonts.googleapis.com/css?family=Roboto:400,300' rel='stylesheet' type='text/css'>
 
 <%@include file="../../nav2.jsp"%>
 <title>qna 답변-필요없음</title>

 <form method="post" action="starters?cmd=adminQnaReplyAction"
		id="upForm" name="qnaForm">
 
 <div class="container">
	 <table class="table">
	    <tbody>
	      <tr>
	        <th style = "width : 180px;">제목</th>
	        <td><input class = "form-control" type="text" name="qnaTitle" maxlength = "50" placeholder = "질문하실 제목을 입력해주세요."></td>
	      </tr>
	      <tr>
	        <td style = "width : 180px;">작성자</td>
	        <td><%=(String)request.getSession().getAttribute("id")%></td>
	      </tr>
	       <tr>
	        <td style = "width : 180px;">글내용</td>
	        <td><textarea class="qna form-control" rows = "10" name = "qna" maxlength = "2048" placeholder = "질문하실 내용을 입력해주세요."></textarea></td>
	      </tr>
	      <tr>
	        <td style = "width : 180px;">비밀번호</td>
	        <td><input class = "form-control" type="text" name="qnaPasswd" maxlength = "50" placeholder = "비밀번호를 입력해주세요. 입력안할시 공개글로 설정됩니다. "></td>
	      </tr>
	    </tbody>
	  </table>
	
	
	<!-- ######  -->
	<button class="btn btn-sm btn-outline-secondary pull-right" id = "btnSubmit">답변등록</button>

</div>
</form>
 
 
 
 <script>
	window.onload = function() {
		qnaForm.noticeTitle.focus();
		document.getElementById("btnSubmit").onclick = inputCheck;
	}
	
	function inputCheck() {
		if (qnaForm.qnaTitle.value == "") {
			alert("제목을 입력해주세요.");
			qnaForm.noticeTitle.focus();
			return false;
		}
		if (qnaForm.qna.value == "") {
			alert("내용을 입력해주세요.");
			qnaForm.qna.focus();
			return false;
		}
		qnaForm.submit();
	}
 
 </script>