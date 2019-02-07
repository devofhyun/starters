<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 폰트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/admin/tutoring_detail.css" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>스타터스 관리자 : 튜터링 - ${tvo2.title}</title>
<div id="wrap">
<section>
	<article>
	
<div class="container info">
	<br>
	<h4>튜터링 등록현황 상세</h4>
	<br>
	<table class="table companyList">
			<tr>
				<th>튜터링번호</th>
				<td>${tvo2.tutoringId}</td>
			</tr>
			<tr>
				<th>튜터명</th>
				<td>${tvo2.tutorName}</td>
			</tr>
			<tr>
				<th>튜터아이디</th>
				<td>${tvo2.tutorId}</td>
			</tr>
			<tr>
				<th>튜터링명</th>
				<td>${tvo2.title}</td>
			</tr>
			<tr>
				<th>직종</th>
				<td>${job.middleCategName}</td>
			</tr>
			<tr>
				<th>금액</th>
				<td>${tvo2.price}</td>
			</tr>
			<tr>
				<th>튜터링인원</th>
				<td>${tutoringCount}명</td>
			</tr>			
	</table>
</div>


<div class="container content">
	<div class="col-md-12">
		<h4><strong>튜터링 내용</strong></h4>
		<div class="plan">${tvo2.contents}</div>
	</div>
	<button class="btn btn-sm btn-outline-secondary" onclick = "back()">목록</button>				
	<button class="btn btn-sm btn-outline-danger pull-right" data-toggle="modal" data-target="#tutoringDelete">삭제하기</button>
						
</div>	
				<!-- Modal -->
				<div class="modal fade" id="tutoringDelete" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
	
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
	
								<h4 class="modal-title">튜터링을 삭제하시겠습니까?</h4>
							</div>
							<form method="post" action="starters?cmd=adminTutoringDeleteAction"
								name="inform">
								<div class="modal-footer">
									<button type="button" class="btn btn-outline-secondary"
										data-dismiss="modal">취소</button>
									<button type="submit" class="btn btn-outline-danger remove">삭제하기</button>
								</div>
							</form>
	
						</div>
	
					</div>
				</div>


<%@include file="../../footer.jsp"%>
	</article>
</section>
</div>
<script>

	var back = function() {
		location.href = "starters?cmd=adminTutoringList";
	};
	

</script>