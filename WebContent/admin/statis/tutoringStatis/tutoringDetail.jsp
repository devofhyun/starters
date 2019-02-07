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

<%@include file="../../../../nav2.jsp"%>
<title>스타터스 : 관리자 - 튜터링 결제</title>
<style>
.job span:after {
	content: ", "
}

.job span:last-child:after {
	content: ""
}


</style>
<section>
	<article>
<div class="table my-5 pt-5" id="reviewInfo">
<h4 class="text-body">튜터링 등록 현황</h4>
<br>
	<table class="table text-body ">
		<tbody>
			<tr>
				<th scope="row">튜터링번호</th>
				<td colspan="5">${tutoringVO.tutoringId}</td>
			</tr>
			
			<tr>
				<th scope="row">튜터명</th>
				<td colspan="5">${tutoringVO.tutorName}[${tutoringVO.tutorId}]</td>
			</tr>
			<tr>
				<th scope="row">튜터링명</th>
				<td>${tutoringVO.title}</td>
				<th scope="row">서브타이틀</th>
				<td colspan="3">${tutoringVO.subtitle}</td>
			</tr>
			<tr>
				<th scope="row">시작일 ~ 종료일</th>
				<td>${tutoringVO.startDate}~${tutoringVO.endDate}</td>
				<th scope="row">경력</th>
				<td class="tutoringCounts">${tutoringVO.career}년</td>
				<th scope="row">직종</th>
				<td class="job">
				<c:forEach items="${job}" var="IntJobSelectVO" varStatus="ijs">
						<span>${IntJobSelectVO.getMiddleCategName()}</span>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row">시간</th>
				<td class="job">	
					<c:forEach items="${times}" var="time" varStatus="ttv">
						<c:if test="${ttv.index eq 0}">
								<span for="first">${time.getTimes()}</span>
							<%--${IntImageVO.getImg()} --%>
						</c:if>
						<c:if test="${ttv.index eq 1}">
								<span>${time.getTimes()}</span>
						</c:if>
						<c:if test="${ttv.index eq 2}">
								<span for="first">${time.getTimes()}</span>
						</c:if>
					</c:forEach>
					</td>
				<th scope="row">가격</th>
				<td>${tutoringVO.price}</td>
				<th scope="row">요일</th>
				<td class="job">
				<c:forEach items="${days}" var="day">
						<span>${day.day}</span>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row">등록일</th>
				<td colspan="5">${tutoringVO.uploadDate}</td>
			</tr>
					<tr>
						<th scope="row">조회수</th>
						<td colspan="5">${tutoringVO.count}
						</td>
					</tr>
					<tr>
				<th scope="row">내용</th>
				<td colspan="5">${tutoringVO.contents}</td>
			</tr>
				</tbody>
	</table>
	<div class="btnzip my-5">
		<button class="btn btn-sm btn-outline-secondary" onclick="back()">목록</button>
	</div>
</div>
<%@include file = "../../../../footer2.jsp" %>
</article>
</section>

<script type="text/javascript">
	function back(){
		location.href="starters?cmd=tutoringStatisctics";
	}
</script>