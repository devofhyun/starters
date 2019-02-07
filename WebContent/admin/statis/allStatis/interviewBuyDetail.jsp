<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../../../nav2.jsp"%>
<title>스타터스 : 관리자 - 면접 결제 상세</title>
<title>스타터스관리자 : 튜터링 결제 상세</title>
<div id="wrap">
<section>
	<article>
<br><h4>튜터링 결제 상세 - ${interviewBuyVO.payId}</h4>
<div class="container table-responsive-md my-5 py-5" id="reviewInfo">
	<table class="table text-body">
		<tbody>
			<tr>
				<th>결제번호</th>
				<td>${interviewBuyVO.payId}</td>
				<th>결제자 정보</th>
				<td colspan="3">${interviewBuyVO.companyId}(기업명: ${interviewBuyVO.companyName})</td>
			</tr>
			<tr>
				<th>튜티 아이디</th>
				<td>${interviewBuyVO.tuteeId}</td>
				<th>튜티 이름</th>
				<td colspan="3">${interviewBuyVO.tuteeName}</td>
			</tr>
			<tr>
				<th>포트폴리오 번호</th>
				<td>${interviewBuyVO.portfolioId}</td>
				<th>포트폴리오 명</th>
				<td colspan="3">${interviewBuyVO.portfolioTitle}</td>
			</tr>
			<tr>
				<th>금액</th>
				<td><fmt:formatNumber value="${interviewBuyVO.payPrice}" pattern="#,###" />원</td>
				<th>결제 수단</th>
				<td>${interviewBuyVO.payInfo}</td>
				<th>결제 일시</th>
				<td>${interviewBuyVO.askDate}</td>
			
			</tr>
			
				</tbody>
	</table>
	<button class="btn btn-sm btn-outline-secondary mb-5" onclick = "back()">목록</button>
</div>
<%@include file = "../../../../footer2.jsp" %>
</article>
</section>
</div>


<script>
var back = function() {
	location.href = "starters?cmd=interviewStatistic";
};
</script>