<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>스타터스 : QnA</title>
<%@include file="../../nav.jsp"%>
<link rel="stylesheet" href="assets/css/community/qna_List.css" />


<body>
<div class="container allB">
<form method="post" action="starters?cmd=qnaSearchAction">
<div class="container searchbox">
<br>
<h3><strong>QnA</strong></h3>
<br>
	<div class="form-row searchbox">
			<input type="text" class="form-control form-control-sm col-8 col-md-10 col-sm-8 col-lg-10" id="txtsearch" name = "searchName" placeholder = "작성자를 입력해주세요.">
			<div class="col-4 col-md-2 col-sm-4 col-lg-2">
				<button type="submit" class="btn btn-block btn-sm btn-secondary"  id="sbtn">검색</button>
			</div>
	</div>
</div>
	</form>
	
 <div class="container basic-row">
	<div class="row basic-row">
		<div class="col-lg-10 col-md-10">
			<p>${qnaListNumber}개의 게시글</p>
		</div>
		<div class="col-lg-2 col-md-2">
			<c:if test="${allMembers== 2 || allMembers== 3}">

				<button class="btn btn-sm btn-outline-secondary pull-right"
					id="wbtn" onclick="qnaWrite()">글쓰기</button>
			</c:if>
		</div>
		
	</div>
</div>

	<c:choose>
	<c:when test = '${empty searchName}'>
	<div class="mainList">
		<jsp:include page="qnaList.jsp" />
	</div>
	</c:when>
	<c:otherwise>
	<div class="mainList">
	<jsp:include page="qnaListSearch.jsp" />
	</div>
	</c:otherwise>
	</c:choose>
	
	


</div>

<%@include file="../../footer.jsp"%>

</body>
<script type="text/javascript">
	var qnaWrite = function(){
		location.href = "starters?cmd=qnaRegister";
	};

</script>