<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.CompanyVO" import="java.util.ArrayList"%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<%@include file="../nav2.jsp"%>

<link rel="stylesheet"
	href="assets/css/admin/Member/MemberList.css">
	
<title>스타터스 관리자 : 기업회원 목록</title>
	
<div id="wrap">
<section>
	<article >
	<div class="container Main">
	<br>
		<div class="compSearchBox">
		<h4 onclick="location.href='starters?cmd=adminMemberCompanyList'" class="mb-5 text-body">기업회원 목록</h4>
		
		
			<form class="col-12" role="form" method = "post" action = "starters?cmd=adminMemberCompanyListAction">
					<div class="row">
						<div class="input-group col-12 col-lg-12 col-md-12 col-sm-12 mb-3">
							<input class="form-control form-control-sm" id="compName" type="text" name = "searchName"
								placeholder="검색할 기업정보를 입력해주세요">
							<div class="input-group-append">
								<button type="submit" class="btn btn-sm btn-secondary px-5" value="검색">검색</button>
							</div>
						</div>
					</div>
			</form>
		</div>
		<!-- /row search -->

			<div class="row basic-row">
					<div class="adminMemberTuteeListSelect">
						<select class="portfolioListSelect  form-control form-control-sm"
							onchange="abc(this.selectedIndex);">
							<option value="0" selected="selected" onclick="t(this)">---</option>
							<option value="1" onclick="t(this)">아이디순</option>
							<option value="2" onclick="t(this)">기업이름순</option>
							<option value="3" onclick="t(this)">가입날짜순</option>
						</select>
				</div>
			</div>
				<div class="mainList">
		<c:choose>
			<c:when test='${empty searchName}'>
					<jsp:include page="adminMemberCompanyList.jsp" />
			</c:when>
			<c:otherwise>
					<jsp:include page="adminMemberCompanyListSearch.jsp" />
			</c:otherwise>
		</c:choose>
				</div>
</div>

<%@include file="../footer2.jsp"%>
	</article>
</section>
</div>
<script>

	// 조회수순, 최신순, 번호순
	function abc(selectedguy) {
		var selectBox = selectedguy;
		//console.log(selectBox);
		$.ajax({
			
			url : "starters?cmd=adminMemberCompanyListSelectAction&select=" + selectBox,
			success : function(result) {
			//	console.log("////////////////////")
			//	console.log(result)
				$("div.mainList").html(result);
			}
		})
	}
</script>