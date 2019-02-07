<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" />

<!-- 달력 -->
<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/redmond/jquery-ui.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jqueryscript.net/css/jquerysctipttop.css">

<link rel="stylesheet" type="text/css" href="assets/css/loadimg.min.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css">



<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../../nav2.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/admin/portfolio/Tutee_tutoringInfo.css" rel="stylesheet">

<title>스타터스 관리자 : 포트폴리오</title>

<section>
	<article>

		<div class="m-5">
			<br>
			<h4>포트폴리오 관리</h4>
			<br>

			<div class="row search">

				<div class="row rangebox w-100">

					<div class="month col-12 col-sm-12 col-md-12 col-lg-12">
						<input type="text" class="form-control form-control-sm" id="from" name="from" value="" placeholder="시작일">
						<label>~</label>
						<input type="text" class="form-control form-control-sm mr-3" id="to" name="to" value="" placeholder="종료일">
						<button class="btn btn-sm btn-secondary" id="find" onclick="find()">조회하기</button>
						<button class="btn btn-sm btn-secondary" id="aMonth"
							onclick="aMonth()">1개월</button>

						<button class="btn btn-sm btn-secondary" id="threeMonth"
							onclick="threeMonth()">3개월</button>

						<button class="btn btn-sm btn-secondary" id="sixMonth"
							onclick="sixMonth()">6개월</button>

						<button class="btn btn-sm btn-secondary" id="all" onclick="all()">전체보기</button>
					</div>
				</div>

				<form class="w-100 " method="post"
					action="starters?cmd=adminPortfolioSearch">
					<div class="row search">
						<div class="col-lg-2 col-md-2">
							<select class="portfoliolist form-control form-control-sm"
								name="searchCateg">
								<option value="0" selected="selected">전체</option>
								<option value="1">키워드</option>
								<option value="2">튜티명</option>
							</select>
						</div>
						<div class="input-group col-10 mb-3">
							<input type="text"
								class="searchinput form-control form-control-sm"
								name="searchName" id="name">
							<div class="input-group-append">
								<button type="submit" class="btn btn-sm btn-secondary px-5"
									value="검색">검색</button>
							</div>
						</div>
					</div>
				</form>
			</div>
			
			<!-- /row search -->
		<div class="container">
		<div class="row basic-row">
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
			<c:choose>
				<c:when test='${empty searchName}'>
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
		<%@include file="../../footer.jsp"%>


	</article>
</section>

<!-- 달력 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<script>
	$('#aMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=adminPortfolioListAmonth",
			success : function(result) {
				$("div.mainList").html(result);
			}
		});
	});
	$('#threeMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=adminPortfolioListThreeMonth",
			success : function(result) {
				$("div.mainList").html(result);
			}
		});
	});
	$('#sixMonth').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=adminPortfolioListSixMonth",
			success : function(result) {
				$("div.mainList").html(result);
			}
		});
	});
	$('#all').click(function() {
		var rRrt = $.ajax({
			url : "starters?cmd=adminPortfolioListAll",
			success : function(result) {
				$("div.mainList").html(result);
			}
		});
	});

	
	$('#find').click(
			function() {
				var from = $('#from').val();
				var to = $('#to').val();
				if ( from == "" && to == "" ) {
					alert("날짜를 입력해주세요");
				} else if( from != ""  && to == "" ){
					alert("종료일을 입력해주세요");
				} else if( from == ""  && to != "" ){
					alert("시작일을 입력해주세요");
				} else{
					$.ajax({
						url : "starters?cmd=adminPortfolioDateSelectAction&date="
								+ from + to,
						success : function(result) {
							$("div.mainList").html(result);
						}
					});
				}
			});

	/**달력*/
	$.datepicker.regional['kr'] = {
		closeText : '닫기', // 닫기 버튼 텍스트 변경
		currentText : '오늘', // 오늘 텍스트 변경
		monthNames : [ '1 월', '2 월', '3 월', '4 월', '5 월', '6 월', '7 월', '8 월',
				'9 월', '10 월', '11 월', '12 월' ], // 개월 텍스트 설정
		monthNamesShort : [ '1 월', '2 월', '3 월', '4 월', '5 월', '6 월', '7 월',
				'8 월', '9 월', '10 월', '11 월', '12 월' ], // 개월 텍스트 설정
		dayNames : [ '월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일' ], // 요일 텍스트 설정
		dayNamesShort : [ '월', '화', '수', '목', '금', '토', '일' ], // 요일 텍스트 축약 설정&nbsp;   dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
		dateFormat : 'yy/mm/dd' // 날짜 포맷 설정
	};

	// Seeting up default language, Korean
	$.datepicker.setDefaults($.datepicker.regional['kr']);

	// Start Datepicker UI
	$("#datepicker").datepicker();

	var dateFormat = "yy/mm/dd", from = $("#from").datepicker({
		defaultDate : "+1w",
		changeMonth : true,
		numberOfMonths : 3
	}).on("change", function() {
		to.datepicker("option", "minDate", getDate(this));
	}), to = $("#to").datepicker({
		defaultDate : "+1w",
		changeMonth : true,
		numberOfMonths : 3
	}).on("change", function() {
		from.datepicker("option", "maxDate", getDate(this));
	});

	function getDate(element) {
		var date;
		try {
			date = $.datepicker.parseDate(dateFormat, element.value);
		} catch (error) {
			date = null;
		}
		return date;
	};

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

	// 검색
	function inputCheck(e) {

		if (searchForm.searchName.value == "") {
			alert("검색어를 선택해주세요.");
			//	console.log("여기1");
			return false;
		}

		searchForm.submit();
	}
	// 최신순, 번호순
	function abc(selectedguy) {
		var selectBox = selectedguy;
		console.log(selectBox);
		$.ajax({

			url : "starters?cmd=adminPortfolioListSelectAction&select="+ selectBox,
			success : function(result) {
				//console.log("////////////////////")
				//console.log(result)
				$("div.mainList").html(result);
			}
		})
	}
</script>