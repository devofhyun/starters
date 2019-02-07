<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../nav.jsp"%>
<link href="assets/css/tutoring/tutee_tutoring_list.css" rel="stylesheet">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>스타터스 : 포트폴리오</title>
<div class="container">
	<br>
	<h4>포트폴리오 목록</h4>
	<br>
<form class="col-12" method="post" action="starters?cmd=PortfolioListAction">
	<div class="row search">

		<div class="col-lg-2 col-md-2 col-4">
				<select class="portfoliolist form-control form-control-sm"  name = "searchCateg">
					<option value="0" selected="selected">전체</option>
					<option value="1">키워드</option>
					<option value="2">튜티명</option>
				</select>
		</div>
		<div class="input-group col-lg-10 col-md-10 col-8 mb-3">
			<input type="text" class="searchinput form-control form-control-sm" name="searchName" id="name">
			<div class="input-group-append">
				<button type="submit" class="btn btn-sm btn-secondary pfbtn" value="검색">검색</button>
			</div>
		</div>
	

	</div>
	</form>
	<!-- /row search -->

	<div class="container">
		<div class="row basic-row">
			<div class="col-lg-10 col-md-10 col-10">
				<p>${portfolioListNumber}개의 포트폴리오</p>
				<c:if test="${portfoliomembers== 1}"> <!-- 튜티 sql따로 만들어서 구분 짓기  -->
			<a href="starters?cmd=portfolioRegister" id="register">
				<button class="btn btn-sm btn-outline-secondary reg ml-2">포트폴리오 등록</button>
			</a>
				</c:if>
			</div>
			<div class=" col-lg-2 col-md-2 col-2">
				<div class="portfolioListSelect">
					<select class="portfolioListSelect  form-control form-control-sm" onchange="abc(this.selectedIndex);">
						<option value="0" selected="selected" onclick = "t(this)">---</option>
						<option value="1" onclick = "t(this)">번호순</option>
						<option value="2" onclick = "t(this)">조회수순</option>
						<option value="3" onclick = "t(this)">최신순</option>
						<option value="4" onclick = "t(this)">좋아요순</option>
					</select>
				</div>
			</div>
		</div>
	</div>
		<c:choose>
		<c:when test = '${empty searchName}'>
		<div class="mainList">
			<jsp:include page="portfolioList.jsp" />
		</div>
		</c:when>
		<c:otherwise>
		<div class="mainList">
			<jsp:include page="portfolioListSearch.jsp" />
		</div>
		</c:otherwise>
		</c:choose>
</div>

<%@include file="../footer.jsp"%>
<script>
	
	// 조회수순, 최신순, 번호순
	function abc(selectedguy) {
		var selectBox = selectedguy;
		//console.log(selectBox);
		$.ajax({
			
			url : "starters?cmd=portfolioListSelectAction&select=" + selectBox,
			success : function(result) {
			//	console.log("////////////////////")
			//	console.log(result)
				$("div.mainList").html(result);
			}
		})
	}
</script>