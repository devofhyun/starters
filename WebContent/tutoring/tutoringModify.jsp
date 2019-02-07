<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"  import="java.util.*" import="com.starters.*"%>
   
<!--  필수 css   -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" />

<!-- 달력 -->
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="http://www.jqueryscript.net/css/jquerysctipttop.css">

<link rel="stylesheet" type="text/css" href="assets/css/loadimg.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css" >

<%@include file="../nav.jsp"%>

<link rel="stylesheet" href="assets/css/tutoring/tutoring_register.css" />


<title>스타터스 : 튜터링 수정</title>
<form id="frmRegister" method = "post" action="starters?cmd=tutoringModifyAction" enctype = "multipart/form-data" name="tutoringForm">
    <input type="hidden" name="daySelect" id="daySelect" value="">
<div class="container">
	<div class="row ">
			<h2><strong>튜터링 수정</strong></h2>
		</div>
</div>
<div class="container info">
	<div class="row ">
		<div class="col-md-12 col-sm-12 col-lg-6 TimgD">
			<div class="tutoringImg">
				<img id="preview" src="../assets/image/prev.png"/>
				<label id="upload" for="ImgInput">사진을 추가해주세요</label>
				<input type="file" id="ImgInput" name="tutoringImg">
			</div>
		</div>
		
		<div class="col-md-12 col-sm-12 col-lg-6 con">
		
			<div class="row notitle">
			<!-- 메인 타이틀-->
			<div class="mainTitleClass" style="margin-left: -15px;">
				<h6>타이틀</h6>
				<input type="text" name="mainTitle" value = "${tutoringInfo.title}"
				class="form-control form-control-sm">
			</div>
			<!-- 서브 타이틀-->
			<div class="subTitleClass" style="margin-left: -15px;">
				<h6>서브타이틀</h6>
				<input type="text" name="subTitle" value = "${tutoringInfo.subtitle}"
				class="form-control form-control-sm">
			</div>
			<div class="row">
			
				<!-- 경력 -->
				<div class="career form-inline">
					<h6>경력</h6>
					<input type="text" name = "jobCareer" value = "${tutoringInfo.career}" 
					class="form-control form-control-sm"> 년
				</div>
				
				
			<div class="row job">
			<h6>강의 분야</h6>
			 <select name="mainSelect" id="mainSelect" class="mainSelect" multiple="multiple">
					<%
						ArrayList<MiddleCategVO> labels = (ArrayList<MiddleCategVO>) request.getAttribute("mainMiddleCategNames");
						out.print("labels" + labels);
				//			out.print("/////////////////////");
						String mainName = null;
						for (MiddleCategVO label : labels) {
							if (label.getMainName().equals(mainName) == false) {
								if (mainName != null) {
									out.print("</optgroup>");
								}
								out.print("<optgroup label=\"" + label.getMainName() + "\">");
								mainName = label.getMainName();
							}
							out.print("<option value=\"" + label.getMiddleCategId() + "\">" + label.getMiddleName() + "</option>");
						}
						if (mainName != null) {
							out.print("</optgroup>");
						}
					%>
				</select>

			</div>
				
				<!-- 강의가능날짜-->
				<div class="date">
					<h6>
						강의 날짜
					</h6>
					<input type="text" id="from" name="from" value = "${tutoringInfo.startDate}"
					class="form-control form-control-sm">
					<p>~</p>
					<input type="text" id="to" name="to" value = "${tutoringInfo.endDate}"
					class="form-control form-control-sm">
				</div>
	
				<!-- 강의가능요일-->
			<div id="Region1">
					<div class="day">
						<h6>
							강의 요일
						</h6>
						<!-- 추가 -->
						<input type="hidden" name="selectedDay" id="selectedDay" />
						<div id="Week1">
							<ul class="select">
								<li class="off" off = 0 value = "월"><input type = "hidden" name = "daySelect1" value = "월"><a>월</a></li>
								<li class="off" off = 1 value = "화" ><input type = "hidden" name = "daySelect2" value = "화"><a>화</a></li>
								<li class="off" off = 2 value = "수" ><input type = "hidden" name = "daySelect3" value = "수"><a>수</a></li>
								<li class="off" off = 3 value = "목" ><input type = "hidden" name = "daySelect4" value = "목"><a>목</a></li>
								<li class="off" off = 4 value = "금" ><input type = "hidden" name = "daySelect5" value = "금"><a>금</a></li>
								<li class="off" off = 5 value = "토" ><input type = "hidden" name = "daySelect6" value = "토"><a>토</a></li>
								<li class="off" off = 6 value = "일" ><input type = "hidden" name = "daySelect7" value = "일"><a>일</a></li>
							</ul>
						</div>
						
					</div>
				</div>
	  
				<!-- 강의가능시간-->
				<div class="row form-inline" id="sample1">
					<div class="form-inline">
						<h6>
							강의 시간
						</h6>
						<p>
							<input id = "s1Time1" name="s1Time1" value=""  class="form-control form-control-sm"/>
							 ~ <input id = "s1Time2" name="s1Time2" value=""  class="form-control form-control-sm"/>
						</p>
					</div>
						<a href="#" class="onA" onclick="add_div()"><i class="fas fa-plus-circle"></i></a>
				</div>
				<!-- field안에 sample1 속 인풋박스가 생김 -->
				<div id="insert"></div>
				<!-- 테스트 -->
				
				<!-- 튜터링 금액 -->
				<div class="money form-inline">
					<h6>금액</h6>
					<input type="text" name = "tutoringMoney" value = "${tutoringInfo.price}" 
					class="form-control form-control-sm">원
				</div>
			</div>
		</div>
		</div>
	</div>
	</div>

	<!-- 튜터링 내용-->
	<div class="container content">
		<h3>튜터링 내용</h3>
		<textarea class="plan" name = "tutoringPlan" >${tutoringInfo.contents}</textarea>
		
		
	
		<div class="contents text-left">
				<h6> 강의 가능 시간 하나 당 한 튜티만 수강 할 수 있습니다.</h6>
				<h6>예시) 3명의 튜티를 튜터링 하고 싶으면, + 버튼을 눌러 강의 가능 시간을 3타임으로 늘리세요.</h6>
		</div>
	</div>
		
</form>
<div class="container Butn">
	<button class="btn btn-outline-secondary list registerTutoring registerTutoringDay" id="btnSubmit" onclick="inputCheck(event);">수정하기</button>
	
	</div>
<%@include file="../footer.jsp"%>


<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

<script src="assets/js/loadimg.min.js"></script>
<script src="assets/js/fSelect.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.ptTimeSelect.js"></script>

<script type="text/javascript">
//직종입력
function changeJobSelect(s, no) {
	form1 = document.tutoringForm;
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


/* 시간 박스 추가삭제 */
var count = 1;

function add_div() {
   var div = document.createElement('div');
   div.id = 'sample1';
   div.className = 'row form-inline';
   // <div class="row" id="sample1">
   

var html = '<div class="form-inline inputTime"> <h6>강의 시간'
				+ (count + 1)
				+ '</h6> <p> <input id = "s1Time'
				+ ((2 * count) + 1)
				+ '"  name="s1Time'
				+ ((2 * count) + 1)
				+ '" class="form-control form-control-sm" value="" /> ~ <input id = "s1Time'
				+ ((2 * count) + 2)
				+ '"name="s1Time'
				+ ((2 * count) + 2)
				+ '" class="form-control form-control-sm" value="" /> </p>'
				+ '</div> <a href="#" id="cd" onclick="remove_div(this)"><i class="fas fa-minus-circle"></i></a> </div>';

		div.innerHTML = html;
   

   /* div.innerHTML = document.getElementById('sample1').innerHTML; // #sample1의 innerHTML을 가져와서 같게 해줌 */

   count += 1;
   if(count > 3){
      alert('더이상 늘릴수 없습니다.');
      count=3;
   }else{
	   document.getElementById('insert').appendChild(div);
		$('.inputTime input').ptTimeSelect();
   }
}

function remove_div(obj) {
   count -= 1;
   document.getElementById('insert').removeChild(obj.parentNode);
}

/* 시간 박스 추가삭제 */

var sel_file;

$("#inputImg").on("change", handleImgFileSelect);

function handleImgFileSelect(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	filesArr.forEach(function(f) {
		if (!f.type.match("image.*")) {
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}
		sel_file = f;
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#img").attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
	});
}
	
	$('#upload').loadImg({
		"text" : "업로드할 사진을 넣어주세요.",
		"fileExt" : [ "png", "jpg" ],
		"fileSize_min" : 0,
		"fileSize_max" : 2
	});
	
	/* 이미지 미리보기 */
	function readURL(input) {
        if (input.files && input.files[0]) {
          var reader = new FileReader();
          reader.onload = function(e) {
            $('#preview').attr('src', e.target.result);
          }
          reader.readAsDataURL(input.files[0]);
        }
      }

      $("#ImgInput").change(function() {
        readURL(this);
      });
	 /* 이미지 미리보기 */
	
	/* 요일 */
	$(document).ready(function() {
		
		// 선택하고 취쇼(요일)
		$('ul.select > li').click(function() {
			$('ul.select > li.off').click(function(){
				$(this).siblings().addClass('off').end().addClass('on');
		});
		$('ul.select > li.on').click(function(){
				$(this).removeClass('on').addClass('off').end().addClass('on');
		});
		});

		var off = $("ul.select li.off");
		console.log(off);
		var selected = "";
		var rStr = [];
			for (var i = 0; i < off.length; i++) {
				selected = $(off[i]).attr("value"); // 요일
				
				var days2 = new Array();
				days2[0] = "${tutoringDayInfo[0].day}";
				days2[1] = "${tutoringDayInfo[1].day}";
				days2[2] = "${tutoringDayInfo[2].day}";
				days2[3] = "${tutoringDayInfo[3].day}";
				days2[4] = "${tutoringDayInfo[4].day}";
				days2[5] = "${tutoringDayInfo[5].day}";
				days2[6] = "${tutoringDayInfo[6].day}";
				
				for(var k = 0; k <7; k++){
					console.log(days2[k]);
					if (selected == days2[k]){
						var realStr = days2[k];
						$(".off[value ='" + realStr + "']").addClass('on');
					}
				}
				

		}
		// var rStr = $("ul.select li.off").attr("dayId");
		// console.log(rStr);
	

		

		/* 시간 */
		$('#sample1 p input').ptTimeSelect();
		
		$.datepicker.regional['kr'] = {
			    closeText: '닫기', // 닫기 버튼 텍스트 변경
			    currentText: '오늘', // 오늘 텍스트 변경
			    monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			    monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			    dayNames: ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'], // 요일 텍스트 설정
			    dayNamesShort: ['월','화','수','목','금','토','일'], // 요일 텍스트 축약 설정&nbsp;   dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
			    dateFormat: 'yy/mm/dd' // 날짜 포맷 설정
			};

			// Seeting up default language, Korean
			$.datepicker.setDefaults($.datepicker.regional['kr']);

			// Start Datepicker UI
			$("#datepicker").datepicker();
			
		var dateFormat = "yy/mm/dd", from = $("#from").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 1,
			minDate:0
		}).on("change", function() {
			to.datepicker("option", "minDate", getDate(this));
		}), to = $("#to").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 1,
			minDate:0
			
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
		}
		
		
	});
	
	// 튜터링등록체크
	window.onload = function() {
		tutoringForm.mainTitle.focus();
		document.getElementById("btnSubmit").onclick = inputCheck;
	}
	
	function inputCheck(e) {
		if (tutoringForm.tutoringImg.value == "") {
			alert("사진을 첨부해주세요.");
			tutoringForm.tutoringImg.focus();
			return false;
		}
		if (tutoringForm.mainTitle.value == "") {
			alert("메인 타이틀을 입력해주세요.");
			tutoringForm.mainTitle.focus();
			return;
		}
		if (tutoringForm.subTitle.value == "") {
			alert("서브 타이틀을 체크해주세요.");
			tutoringForm.subTitle.focus();
			return;
		}
		if (tutoringForm.from.value == "") {
			alert("강의 시작날짜를 입력해주세요.");
			tutoringForm.from.focus();
			return;
		}
		if (tutoringForm.to.value == "") {
			alert("강의 종료날짜를 입력해주세요.");
			tutoringForm.to.focus();
			return;
		}
		if (tutoringForm.s1Time1.value == "") {
			alert("강의 시작시간을 입력해주세요.");
			tutoringForm.s1Time1.focus();
			return;
		}
		if (tutoringForm.s1Time2.value == "") {
			alert("강의 종료시간을 입력해주세요.");
			tutoringForm.s1Time2.focus();
			return;
		}
		if (tutoringForm.jobCareer.value == "") {
			alert("경력을 입력해주세요.");
			tutoringForm.jobCareer.focus();
			return;
		}
		if (tutoringForm.tutoringMoney.value == "") {
			alert("튜터링 금액을 입력해주세요.");
			tutoringForm.tutoringMoney.focus();
			return;
		}
		if (tutoringForm.tutoringPlan.value == "") {
			alert("튜터링의 내용을 입력해주세요.");
			tutoringForm.tutoringPlan.focus();
			return;
		}
		if (tutoringForm.s1Time1 != "" && tutoringForm.s1Time2 != "") {
			if (tutoringForm.s1Time1.value == tutoringForm.s1Time2.value) {
				alert("강의 시간을 확인해주세요.");
				return;
			}
		}
		if ($("#s1Time3").length && $("#s1Time4").length) {
			if (tutoringForm.s1Time1.value == tutoringForm.s1Time3.value
					|| tutoringForm.s1Time1.value == tutoringForm.s1Time4.value
					|| tutoringForm.s1Time2.value == tutoringForm.s1Time3.value
					|| tutoringForm.s1Time2.value == tutoringForm.s1Time4.value) {
				alert("강의 시간을 확인해주세요.");
				return;
			}
		}
		if ($("#s1Time5").length && $("#s1Time6").length) {
			if (tutoringForm.s1Time1.value == tutoringForm.s1Time5.value
					|| tutoringForm.s1Time1.value == tutoringForm.s1Time6.value
					|| tutoringForm.s1Time2.value == tutoringForm.s1Time5.value
					|| tutoringForm.s1Time2.value == tutoringForm.s1Time6.value
					|| tutoringForm.s1Time3.value == tutoringForm.s1Time5.value
					|| tutoringForm.s1Time3.value == tutoringForm.s1Time6.value
					|| tutoringForm.s1Time4.value == tutoringForm.s1Time5.value
					|| tutoringForm.s1Time4.value == tutoringForm.s1Time6.value) {
				alert("강의 시간을 확인해주세요.");
				return;
			}
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
