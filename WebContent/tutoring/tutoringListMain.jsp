<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../nav.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/tutoring/tutee_tutoring_list.css" rel="stylesheet">


<title>스타터스 : 튜터링</title>

<div class="container">
	<br>
	<h4>튜터링 목록</h4>
	<br>
	
		<div class="row search">
		<form class="col-lg-11 col-md-11 col-sm-10 col-xs-10" method="post" action="starters?cmd=TutoringListAction" name = "searchForm">
			
			
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-4 col-4">
				<div class="tutoringwrap">
					<select class="tutoringlist form-control form-control-sm" name = "searchCateg">
						<option value="0" selected="selected">전체</option>
						<option value="1">튜터명</option>
					</select>
				</div>
			</div>
	
			<div class="input-group col-lg-10 col-md-10 col-sm-8 col-5 mb-3">
				<input type="text" class="searchinput form-control form-control-sm" name="searchName" id="name">
			</div>
		</div>
			

	
			<div class="sort col-lg-2 col-md-2">
				<strong>나이</strong>
			</div>
			<div class="sort col-lg-9 col-md-9 input">
				<input type="radio" name="all" value="전체나이" id="전체나이">
				<label for = "전체나이">전체</label>
				<input type="radio" name="all" value="20대" id="20대">
				<label for = "20대">20대</label>
				<input type="radio" name="all" value="30대" id="30대" > 
				<label for = "30대">30대</label>
				<input type="radio" name="all" value="40대이상" id="40대이상"> 
				<label for = "40대이상">40대 이상</label>
			</div>
	
			<div class="sort col-lg-2 col-md-2">
				<strong>성별</strong>
			</div>
			<div class="sort col-lg-9 col-md-9 input">
				<input type="radio" name="allPerson" value="전체성별" id="allg">
				<label for = "allg">전체</label>
				<input type="radio" name="allPerson" value="남" id="male">
				<label for="male">남</label>
				<input type="radio" name="allPerson" value="여" id="female">
				<label for="female">여</label>
			</div>
	
			<div class="sort col-lg-2 col-md-2">
				<strong>경력</strong>
			</div>
			<div class="sort col-lg-9 col-md-9 input">
				<input type="radio" name="year" value="1년이상" id="3year">
				<label for="3year">1년 이상</label>
				<input type="radio" name="year" value="3년이상" id="5year"> 
				<label for="5year">3년 이상</label>
				<input type="radio" name="year" value="5년이상" id="10year">
				<label for="10year">5년 이상 </label>
			</div>
	
			<div class="sort col-lg-2 col-md-2">
				<strong>금액</strong>
			</div>
			<div class="sort col-lg-9 col-md-9">
				<input type="text" name="price1" class="price col-lg-3 col-md-3 col-sm-3 col-4" placeholder="0"><label class="wave">원 ~</label>
				<input type="text" name="price2" class="price col-lg-3 col-md-3 col-sm-3 col-4"  placeholder="50000">원 
			</div>
	
		</form>
		
		<!-- 검색버튼 -->
		<div class="btnclass col-lg-1 col-md-1 col-sm-2 col-2 text-center" >
			<button type="submit" class="btn btn-secondary btn-sm tutoringSbtn" value="검색" onclick="inputCheck(event);">검색</button>
		</div>
		
		
			<!-- 화살표 -->
			<div class="arrow down col-lg-12 col-md-12">
				<h4>
				<i class="fas fa-angle-down down"></i>
				</h4>
			</div>
			<div class="arrow col-lg-12 col-md-12">
				<h4>
					<i class="fas fa-angle-up up"></i>
				</h4>
			</div>
			<!-- /화살표 -->
	</div>
	
				
	
	<!-- /row search -->

<div class="container">
	<div class="row basic-row">
		<div class="col-lg-10 col-md-10 col-10">
			<p>${tutoringListNumber}개의 튜터링</p>
			<c:if test="${tutoringmembers== 1}"> <!-- 튜터이면 -->
			<a href="starters?cmd=tutoringRegister" id="tutoringRegister">
				<button class="btn btn-sm btn-outline-secondary reg ml-2">튜터링 등록</button>
			</a>
				
			</c:if>
		</div>
		<div class="dropenroll col-lg-2 col-md-2 col-2">
			<div class="tutoringListSelect">
					<select class="tutoringListSelect" onchange="abc(this.selectedIndex);">
						<option value="0" selected="selected" onclick = "t(this)">---</option>
						<option value="1" onclick = "t(this)">번호순</option>
						<option value="2" onclick = "t(this)">조회수순</option>
						<option value="3" onclick = "t(this)">최신순</option>
					</select>
				</div>
		</div>
	</div>
</div>


	<c:choose>
	<c:when test = '${empty searchName}'>
	<div class="mainList">
		<jsp:include page="tutoringList.jsp" />
	</div>
	</c:when>
	<c:otherwise>
	<div class="mainList">
		<jsp:include page="tutoringListSearch.jsp" />
	</div>
	</c:otherwise>
	</c:choose>

</div>

<%@include file="../footer.jsp"%>



<script>

	$('.arrow > h4').click(function() {
		// 화살표
		if ($(".down").is(":hidden")) {
			$(".down").show();
			$(".up").hide();
		} else {
			$(".down").hide();
			$(".up").show();
		}
		// 검색 필터
		if ($(".sort").is(":hidden")) {
			$(".sort").slideDown("slow");
		} else {
			$(".sort").slideUp("slow");
		}
		// 검색 버튼
		if ($("button.btn.btn2.sortbtn2").is(":hidden")) {
			$("button.btn.btn2.sortbtn2").slideDown("slow");
			$("button.btn.btn2.sortbtn").hide();
		} else {
			$("button.btn.btn2.sortbtn2").hide();
			$("button.btn.btn2.sortbtn").show();
		}
	});
	
	// 조회수순, 최신순, 번호순
	function abc(selectedguy) {
		var selectBox = selectedguy;
	//	console.log(selectBox);
		$.ajax({
			
			url : "starters?cmd=TutoringListSelectAction&select=" + selectBox,
			success : function(result) {
				//console.log("////////////////////")
				//console.log(result)
				$("div.mainList").html(result);
			}
		})
	}
	
	// 검색
	function inputCheck(e) {
		//console.log("여기2");
		
		if(searchForm.searchName.value == "" && (searchForm.all.value != "" || searchForm.allPerson.value != "" || searchForm.year.value != "" || searchForm.price1.value != "" || searchForm.price2.value != "")){
			// console.log("여기3");
			if (searchForm.all.value == "") {
				alert("나이를 선택해주세요.");
				return false;
			}
			if (searchForm.allPerson.value == "") {
				alert("성별을 선택해주세요.");
				return false;
			}
			if (searchForm.year.value == "") {
				alert("경력을 선택해주세요.");
				return false;
			}
			if (searchForm.price1.value == "") {
				alert("가격을 입력해주세요.");
				return false;
			}
			if (searchForm.price2.value == "") {
				alert("가격을 입력해주세요.");
				return false;
			}
		
		}
		if (searchForm.searchName.value == "" && searchForm.all.value == ""&& searchForm.allPerson.value == "" && searchForm.year.value == "" && searchForm.price1.value == "" && searchForm.price2.value == "" ) {
			alert("검색어를 선택해주세요.");
		//	console.log("여기1");
			return false;
		}
		
		searchForm.submit();
		}
</script>