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
	        <th>대상</th>
	        <td colspan="3">${noticevo.noticeMem}</td>
	      </tr>
	      <tr>
	        <th>제목</th>
	        <td colspan="3" class="title">${noticevo.noticeTitle}</td>
	      </tr>
	      <tr>
	        <th>작성일시</th>
	        <td>${noticevo.noticeDate}</td>
	        <th class="a">글번호</th>
	        <td class="a">${noticevo.noticeId}</td>
	        <%--<c:forEach items="${noticememvo}" var="NoticeMemVO" varStatus="nmv">
				<td>[${NoticeMemVO.getNoticeMem()}]</td>
		</c:forEach> --%>
	      </tr>
	    </tbody>
	  </table>
	<div class="form-group mb-0">
		<p class="notice">${noticevo.noticeContent}</p>
		
		<div class="w-100" style="text-align: right;">
			<c:if test = "${admin == 999}">
				<button class="btn btn-sm btn-outline-secondary list" onclick="location.href='starters?cmd=noticeDelete'">삭제</button>
				<button class="btn btn-sm btn-outline-secondary list" onclick="location.href='starters?cmd=noticeModify'">수정</button>
			</c:if>
			<button class="btn  btn-sm btn-outline-secondary list" onclick="location.href='starters?cmd=notice'">목록</button>
		</div>
	</div>
	
		<div class="w-100 mt-2">
			<c:if test = "${noticevobefore.noticeId != ''}">
			<div class="previous">
				<%--<i class="fas fa-angle-up"></i><a>이전글</a><a
					href="starters?cmd=noticeListDetailPreAction&noticeNum=${noticevo.noticeId}">신규기업추가</a> --%>
			<i class="fas fa-angle-up"></i><span class="pl-2">이전글</span><a class="pn-txt"
					href="#" onclick = "prev(this)" name = "${noticevobefore.noticeId}">${noticevobefore.noticeTitle}</a>
			</div>
			</c:if>
			<c:if test = "${noticevoafter.noticeId != ''}">
			<div class="next">
				<%--<i class="fas fa-angle-down"></i><a>다음글</a><a
					href="starters?cmd=noticeListDetailAfterAction&noticeNum=${noticevo.noticeId}">튜터
					목록 개선</a> --%>
					<i class="fas fa-angle-down"></i><span class="pl-2">다음글</span><a class="pn-txt"
					href="#" onclick = "after(this)" name = "${noticevoafter.noticeId}">${noticevoafter.noticeTitle}</a>
			</div>
			</c:if>
		</div>
		

</div>

</div>
<script>
function prev(jumpBtn) {
	var rStr = jumpBtn.name;
	// console.log(rStr);
	$.ajax({
		url : "starters?cmd=noticeListDetailPreAjax&DetailNum=" + rStr,
		success : function(result) {
			$("div.noticeMainList").html(result);
		}
	})

}

function after(jumpBtn) {
	var rStr = jumpBtn.name;
	//console.log(rStr);
	$.ajax({
		url : "starters?cmd=noticeListDetailAfterAjax&DetailNum=" + rStr,
		success : function(result) {
			$("div.noticeMainList").html(result);
		}
	})

}

$( window ).resize( function() {
	  // do somthing
	​} );
</script>