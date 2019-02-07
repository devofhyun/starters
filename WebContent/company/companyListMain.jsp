<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../../nav.jsp"%>


<link rel="stylesheet" href="assets/css/company_list.css" />
<title>스타터스 : 기업</title>


<!-- 기업 목록 페이지 -->

<div class="container">

	<div class="complist">
		<h4>기업리스트</h4>

		<!-- 기업 검색 -->
		<div class="compSearchBox">
			<form class="col-12 m-0 p-0" role="form" method="post"
				action="starters?cmd=companySearchListAction">
				<div class="input-group">
					<input class="form-control form-control-sm" id="compName"
						type="text" name="searchName" placeholder="기업명을 검색해주세요">
					<div class="input-group-append">
						<button type="submit" class="btn btn-sm btn-secondary nonn"
							style="max-width: 120px;" value="검색">검색</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 기업 검색 끝-->

		<!-- 기업리스트 -->
		<c:choose>
			<c:when test='${empty searchName}'>
				<div class="mainList">
					<jsp:include page="companyList.jsp" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="mainList">
					<jsp:include page="companyListSearch.jsp" />
				</div>
			</c:otherwise>
		</c:choose>

		<!-- 기업리스트 끝 -->

</div>

<%@include file="../../footer.jsp"%>


