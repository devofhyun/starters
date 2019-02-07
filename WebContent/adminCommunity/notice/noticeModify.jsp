<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@include file="../../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/admin/notice/noticeWrite.css" />
 <title>공지사항 수정</title>
<div id="wrap">
<section>
<article>
 <div class="container">
 <br>
 <h4 class="mb-5">공지사항 수정</h4>
 <br>
 <form method="post" action="starters?cmd=adminNoticeModifyAction"
		id="upForm" name="noticeForm">
 
	 <table class="table">
	    <tbody>
	      <tr>
	        <th>제목</th>
	        <td><input type="text" name="noticeTitle" value = "${noticeInfo.noticeTitle }"></td>
	      </tr>
	      <tr>
	        <td>대상</td>
	        <td>
	        <input type="checkbox" id="optionsRadios1" value="튜티" name="memberCateg1" > 튜티
	         
	         <input type="checkbox" id="optionsRadios2" value="튜터" name="memberCateg2" >튜터
			
			<input type="checkbox" id="optionsRadios3" value="기업" name="memberCateg3" > 기업
			
			</td>
	      </tr>
	    </tbody>
	  </table>
	<div class="form-group">
		<textarea class="notice" rows="10"  name = "notice">${noticeInfo.noticeContent }</textarea>
	</div>
	 <!-- ######  -->
	 <div class="btnzip text-right" style="margin-bottom: 100px!important;">
	<button class="btn btn-sm btn-outline-secondary pull-right" id = "btnSubmit">수정</button>
	 
	 </div>
	
</form>
	

</div>
 
<%@include file="../../footer.jsp"%>
</article>
</section>
</div>

 
 <script>
 var first = "${noticeInfo.noticeMem}".substring(0,2);
 var second = "${noticeInfo.noticeMem}".substring(3,5);
 var third = "${noticeInfo.noticeMem}".substring(6,8);
 $('input:checkbox[name="memberCateg1"]').each(function() {
     if(this.value == first || this.value == second || this.value == third){ // 값 비교
            this.checked = true; //checked 처리
      }
 });
 $('input:checkbox[name="memberCateg2"]').each(function() {
     if(this.value == first || this.value == second || this.value == third){ 
            this.checked = true; //checked 처리
      }
 });
 
 $('input:checkbox[name="memberCateg3"]').each(function() {
     if(this.value == first || this.value == second || this.value == third){ // 값 비교
            this.checked = true; //checked 처리
      }
 });


//공지사항 등록체크
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