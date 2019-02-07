<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*" import="java.util.*"%>
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


<%@include file="../../nav.jsp"%>
<!-- <link rel="stylesheet" href="assets/css/community/notice_Detail.css" /> -->
<link rel="stylesheet" href="assets/css/community/qna_Detail.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>스타터스 : QnA - ${qvo.qnaTitle}</title>
<div class="container">
<h3><strong>QnA</strong></h3>
<br>
	 <table class="table">
	    <tbody>
	      <tr>
	        <th>글번호</th>
	        <td colspan="3" class="title">${qvo.qnaId}</td>
	      </tr>
	      <tr>
	        <th>제목</th>
	        <td colspan="3" class="title">${qvo.qnaTitle}</td>
	      </tr>
	      <tr>
	        <th>작성일시</th>
	        <td>${qvo.qnaDate}</td>
	        <th>작성자</th>
	        <td>${qvo.qnaWriterId}</td>
	        <%--<c:forEach items="${noticememvo}" var="NoticeMemVO" varStatus="nmv">
				<td>[${NoticeMemVO.getNoticeMem()}]</td>
		</c:forEach> --%>
	      </tr>
	    </tbody>
	  </table>
	<div class="form-group">
		<p class="notice">${qvo.qnaContent}</p>
	</div>


<div  class="btnzip">
	<button class="btn btn-sm btn-outline-secondary list">
		<a href="starters?cmd=qna">목록</a>
	</button>
	
<%--<button type="button" value="답글" onclick="changeView(1)" >--%>

<c:if test="${id == qnavo.qnaWriterId}">
	<button class="btn btn-sm btn-outline-secondary list">
		<a href="starters?cmd=qnaModify">수정</a>
	</button>
	<button class="btn btn-sm btn-outline-danger remove" data-toggle="modal"
		id="${qvo.qnaId}"
		data-target="#qnaDelete${qvo.qnaId}">삭제</button>
	<div class="modal fade" id="qnaDelete${qvo.qnaId}"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">해당QnA를 삭제하시겠습니까?</h5>
				</div>
				<div class="modal-body">
					<button type="button" class="btn btn-sm btn-outline-secondary" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-sm btn-outline-danger" onclick="realDelete()">삭제하기</button>
				</div>
				<!-- </form> -->
			</div>
		</div>
	</div>
	
	</c:if>
	<br><br>
	 <!-- 댓글 부분 -->


</div>

<div id="comment">
		

	<table border="1" bordercolor="lightgray" class="comment">
		<!-- 댓글 목록 -->
		<c:if test="${getQnAList != null}">
			<c:forEach var="comment" items="${getQnAList}">
				<tr>
					<!-- 아이디, 작성날짜 -->
					<td style=" width: 20%;">
						<div>
						<c:if test="${empty comment.reLevel || comment.reLevel ne 0}">
							<%--들여쓰기 --%>
							<c:forEach begin="1" end="${comment.reLevel}">
							&nbsp;
							</c:forEach>
							RE:
					</c:if>
							${comment.commentWriterId}<br> <font size="2" color="lightgray">${comment.commentDate}</font>
						</div>
					</td>
					<!-- 본문내용 -->
					<td style="padding-left: 20" >
						<div class="text_wrapper">${comment.commentContent}</div>
					</td>
					<!-- 버튼 -->
					<td  style="text-align: center; width: 20%;">
						<div id="btn" style="text-align: center;">
							<a href="#" onclick="ReplyOpen(${comment.commentId})">[답변]</a><br>
							<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
							<c:if test="${comment.commentWriterId == userId}">
							<a href="#" onclick = "UpdateOpen('${comment.commentId}')">[수정]</a><br> 
							<a href="#" onclick = "DeleteOpen('${comment.commentId}')">[삭제]</a>
							</c:if> 
						</div>
					</td>
				</tr>

			</c:forEach>
		</c:if>

		<!-- 로그인 했을 경우만 댓글 작성가능 -->

			<c:if test="${userId !=null}">
				<%--<form method="post" action="starters?cmd=commentRegister" id="upForm" name="commentForm"> --%>
				<form id="writeCommentForm" name="commentForm">
				<tr bgcolor="#F5F5F5">
					<input type="hidden" name="qnaIds" value="${qvo.qnaId}">
					
					<!-- 아이디-->
					<td style="width: 20%;">${userId}</td>
					<!-- 본문 작성-->
					<td >
							<textarea name="comment_content" style="width:100%;"></textarea>
					</td>
					<!-- 댓글 등록 버튼 -->
					<td class="text-center" style="width: 20%;">
						<a href="#" onclick="writeCmt()" class="text-center">댓글등록
						</a>
					</td>

				</tr>
				<%--</form> --%>
			</c:if>
		

	</table>
	
</div>


</div>


	
<%@include file="../../footer.jsp"%>


<script>
		var realDelete = function() {
		location.href = "starters?cmd=qnaDeleteAction";
	};
	
//	window.onload = function() {
//		commentForm.comment_content.focus();
// 	document.getElementById("btnSubmit").onclick = inputCheck;
//	}
	
//	function inputCheck(e) {
//		if (commentForm.comment_content.value == "") {
//			alert("내용을 입력해주세요.");
//			commentForm.comment_content.focus();
//			return false;
//		}
//		commentForm.submit();
//	}
 var httpRequest = null;
        
        // httpRequest 객체 생성
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }
        
        // 댓글 등록
        function writeCmt()
        {
            var form = document.getElementById("writeCommentForm");
            
            var board = form.qnaIds.value
            var content = form.comment_content.value;
            
            if(!content)
            {
                alert("내용을 입력하세요.");
                return false;
            }
            else
            {    
                var param="comment_board="+board+"&comment_content="+content;
                    
                httpRequest = getXMLHttpRequest();
                httpRequest.onreadystatechange = checkFunc;
                httpRequest.open("POST", "starters?cmd=commentRegister", true);    
                httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
                httpRequest.send(param);
            }
        }
        
        function checkFunc(){
            if(httpRequest.readyState == 4){
                // 결과값을 가져온다.
                var resultText = httpRequest.responseText;
                if(resultText == 1){ 
                    document.location.reload(); // 상세보기 창 새로고침
                }
            }
        }



function DeleteOpen(commentNum){
	var msg = confirm("댓글을 삭제합니다.");
	if(msg == true){
		deleteCmt(commentNum);
	}else{
		return false;
	}
}
//삭제
function deleteCmt(commentNum){
	console.log(commentNum);
	location.href = "starters?cmd=commentDelete&commentId="+commentNum;
}

// 수정
function UpdateOpen(commentNum){
	console.log(commentNum);
	 window.name = "parentForm";
     window.open("starters?cmd=qnaCommentUpdate&commentId="+commentNum,
                 "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
 }
 
//대댓글
function ReplyOpen(commentNum){
	var userId = '${userId}';
	console.log(commentNum);
	if(userId == "" || userId == null){
        alert("로그인후 사용가능합니다.");
        return false;
    }else{
	 window.name = "parentForm";
    window.open("starters?cmd=qnaCommentReply&commentId="+commentNum,
                 "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
    }
	
 }
 
	</script>
