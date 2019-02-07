<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="com.starters.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  필수 css   -->
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
<link rel="stylesheet" type="text/css"
	href="assets/css/wickedpicker.min.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css">



<%@include file="../nav.jsp"%>
<link rel="stylesheet"
	href="assets/css/mypage/company/company_interviewForm.css" />




<title>스타터스 : 면접신청</title>



<!-- 기업 목록 페이지 -->
<form id="frmRegister" method="post"
	action="starters?cmd=interviewApplyAction" name="tutoringForm"
	class="form-inline" role="form">
	<input type="hidden" name="daySelect" id="daySelect" value="">
	<input type="hidden" name="portfolioId" id="portfolioId"
		value="${portfoliovo.portfolioId }">


	<div class="container">

		<div class="interviewForm">
			<h2>
				<strong>면접신청서</strong>
			</h2>

			<div class="interviewFormBox">

				<table class="table compinfo">
					<tbody>
						<tr>
							<th>기업</th>
							<td>${companyvo.name}</td>
							<th>튜티</th>
							<td colspan="3">${portfoliovo.tuteeName }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${companyvo.email}</td>
							<th>홈페이지</th>
							<td colspan="3">${companyvo.companyUrl}</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td>${companyvo.phoneNum}</td>
							<th>규모</th>
							<td>${companyvo.companyRange}</td>
							<th>설립일</th>
							<td>${companyvo.companyBirth}</td>
						</tr>
						<tr>
							<th class="Ath">주소</th>
							<td colspan="5" class="Atd">${companyvo.address}</td>
						</tr>
						<tr>
							<th class="Cth">기업소개</th>
							<td colspan="5" class="Ctd">${companyvo.companyIntroduce}</td>
						</tr>
					</tbody>
				</table>

				<div class="content">



					<!-- 직종 추가 -->
					<div class="row ">
						<h6>제안 업무</h6>
						<select name="mainSelect" id="mainSelect" class="mainSelect "
							multiple="multiple">
							<%
								ArrayList<MiddleCategVO> labels = (ArrayList<MiddleCategVO>) request.getAttribute("mainMiddleCategNames");
								out.print("labels" + labels);
								// HashMap<String, ArrayList<MiddleCategVO>> detail = (HashMap<String, ArrayList<MiddleCategVO>>)request.getAttribute("mainMiddleCategNames");
								out.print("/////////////////////");
								String mainName = null;
								for (MiddleCategVO label : labels) {
									if (label.getMainName().equals(mainName) == false) {
										if (mainName != null) {
											out.print("</optgroup>");
										}
										out.print("<optgroup label=\"" + label.getMainName() + "\">");
										mainName = label.getMainName();
									}

									//   ArrayList<MiddleCategVO> list = detail.get(label.getMainCategId());
									//      for (MiddleCategVO option : labels) {
									out.print("<option value=\"" + label.getMiddleCategId() + "\">" + label.getMiddleName() + "</option>");
									//         }
									//                  out.print("</optgroup>");
								}
								if (mainName != null) {
									out.print("</optgroup>");
								}
							%>
						</select>

					</div>

					<!-- 면접가능날짜-->
					<div class="row date">
						<h6>면접 가능 날짜</h6>
						<input type="text" id="from" name="from"
							class="form-control form-control-sm">
						<p>~</p>
						<input type="text" id="to" name="to"
							class="form-control form-control-sm">
					</div>

					<!-- 면접가능요일 -->
					<!-- 강의가능요일-->
					<div id="Region1">
						<div class="row day">
							<h6>면접 가능 요일</h6>
							<input type="hidden" name="selectedDay" id="selectedDay" />
							<div id="Week1">
								<ul class="select">
									<li class="on" value="월"><input type="hidden"
										name="daySelect1" value="월"><a>월</a></li>
									<li class="off" value="화"><input type="hidden"
										name="daySelect2" value="화"><a>화</a></li>
									<li class="off" value="수"><input type="hidden"
										name="daySelect3" value="수"><a>수</a></li>
									<li class="off" value="목"><input type="hidden"
										name="daySelect4" value="목"><a>목</a></li>
									<li class="off" value="금"><input type="hidden"
										name="daySelect5" value="금"><a>금</a></li>
									<li class="off" value="토"><input type="hidden"
										name="daySelect6" value="토"><a>토</a></li>
									<li class="off" value="일"><input type="hidden"
										name="daySelect7" value="일"><a>일</a></li>
								</ul>
							</div>
						</div>
					</div>

					<!-- 면접가능시간 -->
					<!-- 강의가능시간-->
					<div class="row form-inline" id="sample1">
						<div class="form-inline inputTime">
							<h6>면접 가능 시간</h6>
							<input name="s1Time1" value=""
								class="form-control form-control-sm STime" /> ~<input
								name="s1Time2" value=""
								class="form-control form-control-sm STime" />
						</div>
						<a href="#" class="onA" onclick="add_div()"><i
							class="fas fa-plus-circle"></i></a>
					</div>
					<!-- field안에 sample1 속 인풋박스가 생김 -->
					<div class="row form-inline" id="insert"></div>
					<!-- 테스트 -->


					<!-- 제안연봉 -->
					<div class="form-group">
						<h6>제안 연봉</h6>
						<div class="text">
							<input class="form-control form-control-sm col-lg-6" id="inputYearPay"
								type="number" placeholder="ex) 2200" name="money"> <span>만원</span>
						</div>
					</div>


				</div>

			</div>

		</div>
		<div class="subbtn">
			<button type="submit" class="btn btn-outline-secondary btn-sm"
				onclick="inputCheck(event);">면접 신청</button>
		</div>
	</div>
	<!-- container end -->

</form>



<%@include file="../footer.jsp"%>


<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

<script src="assets/js/loadimg.min.js"></script>
<script src="assets/js/fSelect.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>

<script type="text/javascript" src="assets/js/jquery.ptTimeSelect.js"></script>
<script type="text/javascript" src="assets/js/wickedpicker.js"></script>


<script type="text/javascript">
/* 요일 */


/* 시간 */
var options = {
	title: '면접가능 시간'
};

var option = {
		now:'6:00',
		title: '면접가능 시간'
	};


function thResize(){
	if($(document).width() > 878){
		var td = $('.Atd').outerHeight();	
		$('.Ath').css('height', td);
	}
}

function tdResize(){
	if($(document).width() > 878){
		var th = $('.Cth').outerHeight();	
		$('.Ctd').css('height', th);
	}
}

$(document).ready(
				function() {$('ul.select > li').click(function() {
								$('ul.select > li.off').click(function() {
											$(this).siblings().addClass('off').end().addClass('on');});
								$('ul.select > li.on').click(function() {
											$(this).removeClass('on').addClass('off').end().addClass('on');
										});
							});
				$(window).resize(function (){
					thResize();
					tdResize();
				});
				
				

				
					
					// $('.inputTime input').ptTimeSelect();
					
					$.datepicker.regional['kr'] = {
						closeText : '닫기', // 닫기 버튼 텍스트 변경
						currentText : '오늘', // 오늘 텍스트 변경
						monthNames : [ '1 월', '2 월', '3 월', '4 월', '5 월',
								'6 월', '7 월', '8 월', '9 월', '10 월', '11 월',
								'12 월' ], // 개월 텍스트 설정
						monthNamesShort : [ '1 월', '2 월', '3 월', '4 월',
								'5 월', '6 월', '7 월', '8 월', '9 월', '10 월',
								'11 월', '12 월' ], // 개월 텍스트 설정
						dayNames : [ '월요일', '화요일', '수요일', '목요일', '금요일',
								'토요일', '일요일' ], // 요일 텍스트 설정
						dayNamesShort : [ '월', '화', '수', '목', '금', '토', '일' ], // 요일 텍스트 축약 설정&nbsp;   dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
						dateFormat : 'yy/mm/dd' // 날짜 포맷 설정
					};

					// Seeting up default language, Korean
					$.datepicker.setDefaults($.datepicker.regional['kr']);

					// Start Datepicker UI
					$("#datepicker").datepicker();

					var dateFormat = "yy/mm/dd", from = $("#from")
							.datepicker({
								defaultDate : "+1w",
								changeMonth : true,
								numberOfMonths : 1,
								minDate:0
							}).on(
									"change",
									function() {
										to.datepicker("option", "minDate",
												getDate(this));
									}), to = $("#to").datepicker({
						defaultDate : "+1w",
						changeMonth : true,
						numberOfMonths : 1,
						minDate:0
					}).on(
							"change",
							function() {
								from.datepicker("option", "maxDate",
										getDate(this));
							});

					function getDate(element) {
						var date;
						try {
							date = $.datepicker.parseDate(dateFormat,
									element.value);
						} catch (error) {
							date = null;
						}

						return date;
					}
					
					// 시간
					$('.STime').wickedpicker(options);

				});



/* 시간 박스 추가삭제 */
var count = 1;

function add_div() {
	var div = document.createElement('div');
	div.id = 'sample1';
	 div.className = 'row form-inline';  

 	var html = '<div class="form-inline inputTime"> <h6>면접 가능 시간' + (count+1) + '</h6><input name="s1Time'
			+ ((2 * count) + 1) + '"   class="form-control form-control-sm STime2"' 
			+ ' value="" /> ~ <input name="s1Time'
			+ ((2 * count) + 2) + '"   class="form-control form-control-sm STime2"' 
			+ ' value="" /> '
			+ '</div> <a href="#" id="cd" onclick="remove_div(this)"><i class="fas fa-minus-circle"></i></a> </div>'; 

	div.innerHTML = html;

	/* div.innerHTML = document.getElementById('sample1').innerHTML; // #sample1의 innerHTML을 가져와서 같게 해줌 */
	count += 1;
	if (count > 3) {
		alert('더이상 늘릴수 없습니다.');
		count = 3;
	}else {

		document.getElementById('insert').appendChild(div);
		$('.STime2').wickedpicker(option);
		//$('.inputTime input').ptTimeSelect();
	}
	
}

function remove_div(obj) {
	count -= 1;
	document.getElementById('insert').removeChild(obj.parentNode);
}

		// 직종입력
		function changeJobSelect(s, no) {
			form1 = document.registerForm;
			sel = s[s.selectedIndex].text;
			dis = 1;

			if (sel == "user") {
				sel = "";
				dis = 0;
			}
			form1.jobSelects.value = sel;
			form1.jobSelects.disabled = dis;
		}

		$('.mainSelect').fSelect();

		
function inputCheck(e) {
	if (tutoringForm.mainSelect.value == "") {
		alert("업무를 선택해주세요.");
		tutoringForm.mainSelect.focus();
		return false;
	}
	if (tutoringForm.from.value == "") {
		alert("면접가능 시작날짜를 입력해주세요.");
		tutoringForm.from.focus();
		return;
	}
	if (tutoringForm.to.value == "") {
		alert("면접가능 종료날짜를 입력해주세요.");
		tutoringForm.to.focus();
		return;
	}
	if (tutoringForm.s1Time1.value == "") {
		alert("면접가능 시작시간을 입력해주세요.");
		tutoringForm.s1Time1.focus();
		return;
	}
	if (tutoringForm.s1Time2.value == "") {
		alert("면접가능 종료시간을 입력해주세요.");
		tutoringForm.s1Time2.focus();
		return;
	}
	if (tutoringForm.money.value == "") {
		alert("제안연봉을 입력해주세요.");
		tutoringForm.money.focus();
		return;
	}
	var lions = $("ul.select li.on");
	var week = "";
	for (var i = 0; i < lions.length; i++) {
		week += $(lions[i]).attr("value")
		//console.log(week)
	}
	
	
	$("#daySelect").val(week);
	

	tutoringForm.submit();
}
</script>
