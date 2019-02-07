<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<!-- 달력 -->
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="http://www.jqueryscript.net/css/jquerysctipttop.css">

<link rel="stylesheet" type="text/css" href="assets/css/loadimg.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css" >

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
 
 <%@include file="../../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/community/qna_Detail.css" />
 <title>스타터스 : QnA 수정</title>

 <form method="post" action="starters?cmd=adminQnaModifyAction"
		id="upForm" name="qnaForm">
 
 <div class="container">
<br>
<h3><strong>QnA 등록</strong></h3>
<br>
	 <table class="table">
	    <tbody>
	      <tr>
	        <th style = "width : 180px;">제목</th>
	        <td><input class = "form-control" type="text" name="qnaTitle" maxlength = "50" value = "${qnaInfo.qnaTitle}"></td>
	      </tr>
	      <tr>
	        <td style = "width : 180px;">작성자</td>
	        <td><%=(String)request.getSession().getAttribute("id")%></td>
	      </tr>
	       <tr>
	        <td style = "width : 180px;">글내용</td>
	        <td><textarea class="qna form-control" rows = "10" name = "qna" maxlength = "2048">${qnaInfo.qnaContent}</textarea></td>
	      </tr>
	      <tr>

	    <%--    <c:choose>
	      <c:when test = "${qnaInfo.qanPasswd == ''}">
	      </c:when>
	      <c:otherwise>
	        <td style = "width : 180px;">비밀번호</td>
	     <td><input class = "form-control" type="text" name="qnaPasswd" maxlength = "50" placeholder = "비밀번호를 입력해주세요. 입력안할시 공개글로 설정됩니다. "></td>

	      </c:otherwise>
	      </c:choose> --%>  
	      </tr>
	    </tbody>
	  </table>
	
	
	<!-- ######  -->
	<div class="btnzip" style="text-align: right;">
		<button class="btn btn-outline-secondary" id = "btnSubmit">수정</button>
		<button class="btn btn-outline-danger" onclick = "cancel()">취소</button>
	</div>
	
	
</div>



	

</form>

<%@include file="../../footer.jsp"%>
 
 
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
		//if (qnaForm.qnaPasswd.value == "") {
		//	alert("비밀번호를 입력해주세요.");
		//	qnaForm.qnaPasswd.focus();
		//	return false;
		//}
		qnaForm.submit();
	}
	
	var cancel = function() {
		location.href = "starters?cmd=adminQnaListDetailAction&qnaNum=${qnaInfo.qnaId}";
	};
 </script>