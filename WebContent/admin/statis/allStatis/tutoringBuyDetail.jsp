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
<!-- 달력 -->

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../../../nav2.jsp"%>
<title>스타터스관리자 : 튜터링 결제 상세</title>
<div id="wrap">
<section>
	<article>
<br><h4>튜터링 결제 상세 - ${tutoringBuyVO.tutoringApplyId}</h4>
<div class="container table-responsive-md my-5 py-5" id="reviewInfo">
	<table class="table text-body">
		<tbody>
			<tr>
				<th scope="row">결제번호</th>
				<td>${tutoringBuyVO.tutoringApplyId}</td>
				<th scope="row">결제자 정보</th>
				<td>${tutoringBuyVO.tuteeName} (ID :  ${tutoringBuyVO.tuteeId})</td>
			</tr>
			<tr>
				<th scope="row">튜터 아이디</th>
				<td>${tutoringBuyVO.tutorId}</td>
				<th scope="row">튜터 이름</th>
				<td>${tutoringBuyVO.tutorName}</td>
			</tr>
			<tr>
				<th scope="row">금액</th>
				<td><fmt:formatNumber value="${tutoringBuyVO.tutoringPrice}" pattern="#,###" />원</td>
				<th scope="row">수수료</th>
				<td><fmt:formatNumber value="${tutoringBuyVO.tutoringPrice*0.3}" pattern="#,###" />원</td>
			</tr>
			<tr>
				<th scope="row">결제 일시</th>
				<td>${tutoringBuyVO.applyDate}</td>
				<th scope="row">결제 계좌</th>
				<td>${tutoringBuyVO.paymentInfo}</td>
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
	location.href = "starters?cmd=tutoringStatistic";
};
</script>