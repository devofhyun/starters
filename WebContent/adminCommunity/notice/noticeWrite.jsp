<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">

<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<link href='https://fonts.googleapis.com/css?family=Roboto:400,300' rel='stylesheet' type='text/css'>
 
 <%@include file="../../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/admin/notice/noticeWrite.css" />
 <title>공지사항 등록</title>
 <div id="wrap">
 <section>
 <article>
 <div class="container">
 <br>
 <h4 class="mb-5">공지사항 등록</h4>
 <br>
 <form method="post" action="starters?cmd=adminNoticeRegisterAction"
		id="upForm" name="noticeForm">
	 <table class="table">
	    <tbody>
	      <tr>
	        <th>제목</th>
	        <td><input type="text" name="noticeTitle"></td>
	      </tr>
	      <tr>
	        <td>대상</td>
	        <td>
	        <input type="checkbox" id="optionsRadios1" value="튜티" name="memberCateg1"> 튜티
	         
	         <input type="checkbox" id="optionsRadios2" value="튜터" name="memberCateg2">튜터
			
			<input type="checkbox" id="optionsRadios3" value="기업" name="memberCateg3"> 기업
			
			</td>
	      </tr>
	    </tbody>
	  </table>
	<div class="form-group">
		<textarea class="notice" rows="10"  name = "notice"></textarea>
	</div>
	
	
	<!-- ######  -->
	 <div class="btnzip text-right " style="margin-bottom: 100px!important;">
	<button class="btn btn-sm btn-outline-secondary pull-right" id = "btnSubmit">게시</button>
	</div>

</form>
</div>

<%@include file="../../footer.jsp"%>
 </article>
 </section>
 </div>
 
 
 <script>
//포폴등록체크
	window.onload = function() {
		noticeForm.noticeTitle.focus();
		document.getElementById("btnSubmit").onclick = inputCheck;
	}
	
	function inputCheck() {
		if (noticeForm.noticeTitle.value == "") {
			alert("제목을 입력해주세요.");
			noticeForm.noticeTitle.focus();
			return false;
		}
		if (noticeForm.notice.value == "") {
			alert("내용을 입력해주세요.");
			noticeForm.notice.focus();
			return false;
		}
		if (noticeForm.memberCateg.value == "") {
			alert("대상을 체크해주세요.");
			noticeForm.memberCateg.focus();
			return false;
		}
	
		noticeForm.submit();
	}
 
 </script>