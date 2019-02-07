<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*" import="java.util.*"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>스타터스 : 공지 - ${noticevo.noticeTitle}</title>

<div class="preNextDetail">
<div class="container">
	<br>
	<h4>공지사항</h4>
	<br>
	 <table class="table">
	    <tbody>
	      <tr>
	        <th>제목</th>
	        <td colspan="5" class="title">${noticevo.noticeTitle}</td>
	      </tr>
	      <tr>
	        <td>작성일시</td>
	        <td>${noticevo.noticeDate}</td>
	        <td>글번호</td>
	        <td>${noticevo.noticeId}</td>
	        <td>대상</td>
	        <td>${noticevo.noticeMem}</td>
	        <%--<c:forEach items="${noticememvo}" var="NoticeMemVO" varStatus="nmv">
				<td>[${NoticeMemVO.getNoticeMem()}]</td>
		</c:forEach> --%>
	      </tr>
	    </tbody>
	  </table>
	<div class="form-group">
		<p class="notice">${noticevo.noticeContent}</p>
	</div>

	<div class="row">
		<div class="col-9">
			<c:if test = "${noticevobefore.noticeId != ''}">
			<div class="previous">
				<%--<i class="fas fa-angle-up"></i><a>이전글</a><a
					href="starters?cmd=noticeListDetailPreAction&noticeNum=${noticevo.noticeId}">신규기업추가</a> --%>
			<i class="fas fa-angle-up"></i><a>이전글</a><a
					href="#" onclick = "prev(this)" name = "${noticevobefore.noticeId}">${noticevobefore.noticeTitle}</a>
			</div>
			</c:if>
			<c:if test = "${noticevoafter.noticeId != ''}">
			<div class="next">
				<%--<i class="fas fa-angle-down"></i><a>다음글</a><a
					href="starters?cmd=noticeListDetailAfterAction&noticeNum=${noticevo.noticeId}">튜터
					목록 개선</a> --%>
					<i class="fas fa-angle-down"></i><a>다음글</a><a
					href="#" onclick = "after(this)" name = "${noticevoafter.noticeId}">${noticevoafter.noticeTitle}</a>
			</div>
			</c:if>
		</div>
		
		<div class="col-3">
			<a href="starters?cmd=adminNotice" style="text-decoration: none;">
			<button class="btn  btn-sm btn-outline-secondary col-3">목록
			</button></a>
			<c:if test = "${admin == 999}">
			<a href="starters?cmd=adminNoticeDelete" style="text-decoration: none;">
			<button class="btn btn-sm btn-outline-secondary col-3">삭제
			</button></a>
			<a href="starters?cmd=adminNoticeModify" style="text-decoration: none;">
			<button class="btn btn-sm btn-outline-secondary col-3">수정
			</button></a>
			</c:if>
		</div>
	</div>

</div>
</div>
<script>
function prev(jumpBtn) {
	var rStr = jumpBtn.name;
	// console.log(rStr);
	$.ajax({
		url : "starters?cmd=adminNoticeListDetailPreAjax&DetailNum=" + rStr,
		success : function(result) {
			$("div.noticeMainList").html(result);
		}
	})

}

function after(jumpBtn) {
	var rStr = jumpBtn.name;
	//console.log(rStr);
	$.ajax({
		url : "starters?cmd=adminNoticeListDetailAfterAjax&DetailNum=" + rStr,
		success : function(result) {
			$("div.noticeMainList").html(result);
		}
	})

}
</script>