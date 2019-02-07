<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="com.starters.*"%>
	
	
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@include file="../nav.jsp"%>
<link rel="stylesheet" type="text/css" href="assets/css/register_member/register_tutee.css">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">



<title>스타터스 - 나의 정보 수정</title>

<style>
@media screen and (min-width: 535px){
.input-group.col-9.mb-3,
.input-group-vertical {
    max-width: 350px;
}
}
</style>

<div class="container">
	<form method="post" class="selfinfo" name="tutorModifyForm"
		action="starters?cmd=companyInfoModifyAction" enctype = "multipart/form-data" >

  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=companyInfoDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyLikeList">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyInterview">면접내역 및 현황</a>
    </li>
  </ul>
		<h3>회원정보수정</h3>
		
		<div class="selfinfo1">
			<input type="hidden">
			<!--  기업명  -->
			<div class="row">
			<label for="name" class="col-3">기업명</label>
				<div class="input-group col-9 mb-3">
				     <span>${companyInfo.name }</span>
				</div>
			</div>

			<!--  아이디  -->
			<div class="row">
			<label for="name" class="col-3">기업명</label>
				<div class="input-group col-9 mb-3">
				     <span>${companyInfo.companyId}</span>
				</div>
			</div>
			
			<!--  비밀번호  -->
			<div class="row">
			<label for="passwd" class="col-3">비밀번호</label>
				<div class="input-group col-9 mb-3">
				     <input class="form-control form-control-sm"
					type="password" name="passwd" id="passwd" placeholder="********"
					required="required">
				</div>
			</div>

			<div class="row">
			<label for="passwd_check" class="col-3">비밀번호확인</label>
				<div class="input-group col-9 mb-3">
				    <input class="form-control form-control-sm"
				    type="password" name="passwd_check" id="passwd_check"
					placeholder="********" required="required">
				
				<div class="correctPw"></div>
				</div>
			</div>

		<!-- 4. 이메일 -->
		<div class="row">
				<label for="email" class="col-3">이메일</label>
				<div class="input-group col-9 mb-3">
					<input type="text" class="form-control form-control-sm"
						id="email" value="${fn:substring(companyInfo.email,0,fn:indexOf(companyInfo.email,'@')) }" name="email"
						required="required">
						@
					<input type="text" name="emailBox1"
						id="emailBox1" class="emailBox form-control form-control-sm"
						value="${fn:substring(companyInfo.email,fn:indexOf(companyInfo.email,'@')+1,fn:length(companyInfo.email)) }"
						required="required">
						<select id = "emailBoxSelect" name="emailBoxSelect"  class="form-control form-control-sm">
						<option value="">---</option>
						<option value="1">직접입력</option>
						<option value="@naver.com">naver.com</option>
						<option value="@hanmail.net">hanmail.net</option>
						<option value="@gmail.com">gmail.com</option>
						<option value="@nate.com">nate.com</option>
						</select>
				</div>
		</div>
<!-- 			<div class="row">
				<label for="email" class="col-3">이메일</label>
				<div class="input-group col-9 mb-3">
					<input type="text" class="form-control form-control-sm"
						id="email" placeholder="starters" name="email"
						required="required">
					    <input type="text" name="emailBox1"
						id="emailBox1" class="emailBox form-control form-control-sm" readonly="readonly"
						required="required">
						<select name="emailBoxSelect"  class="form-control form-control-sm"
						onchange="changeSelect(this,2);">
						<option value="">---</option>
						<option value="@naver.com">naver.com</option>
						<option value="@hanmail.net">hanmail.net</option>
						<option value="@gmail.com">gmail.com</option>
						<option value="@nate.com">nate.com</option>
						</select>
				</div>
			</div> -->
			
			
			<!-- 전화번호 -->
			<div class="row">
				<label for="tel" class="col-3">전화번호</label>
				<div class="input-group col-9 mb-3 ">
				 	<select name="phoneNum1"  class="form-control form-control-sm" id="phoneNum1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					</select>
					<input type="text" class="tel2 form-control form-control-sm" value="${fn:substring(companyInfo.phoneNum,3,7)}" name="phoneNum2"
					required="required" id = "phoneNum2" max="9999" maxlength="4" oninput="maxLengthCheck(this)">
					<input type="text" class="tel3 form-control form-control-sm"
					value="${fn:substring(companyInfo.phoneNum,7,12)}" name="phoneNum3" required="required" id = "phoneNum3" max="9999" maxlength="4" oninput="maxLengthCheck(this)">
				</div>
			</div>
<!-- 			<div class="row">
				<label for="tel" class="col-3">전화번호</label>
				<div class="input-group col-9 mb-3 ">
				 	<select name="phoneNum1"  class="form-control form-control-sm">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					</select>
					<input type="text" class="tel2 form-control form-control-sm" placeholder="5693" name="phoneNum2"
					required="required" id = "tel2">
					<input type="text" class="tel3 form-control form-control-sm"
					placeholder="1203" name="phoneNum3" required="required" id = "tel3">
				</div>
			</div> -->

			<!--  6. 주소  -->
			<div class="row">
				<label class="col-3">주소</label>
				<div class="input-group-vertical col-9 mb-3 ">
				 	<input type="text" name="postcode" class="form-control form-control-sm"
						placeholder="여기를 눌러서 주소 선택하세요." readonly="readonly"  value="${fn:substring(companyInfo.address,0,5)}" >
					<input
						type="text" name="address1" class="form-control form-control-sm"
						placeholder="선택한 주소가 자동입력 됩니다." readonly="readonly"> 
					<input
						type="text" name="address2" class="form-control form-control-sm"
						placeholder="나머지 주소를 입력하세요.">
				</div>
			</div>

			<!--  7. 홈페이지 주소  -->
			<div class="row">
			<label for="homepage" class="col-3">홈페이지 주소</label>
				<div class="input-group col-9 mb-3">
				     <input
					type="text" id="homepage" placeholder="www.starters.com"
					class="form-control form-control-sm homepage" name="homepage"  value="${companyInfo.companyUrl}">
				</div>
			</div>

			<!-- 8. 회사규모 -->
			<div class="row">
				<label for="range" class="col-3">회사규모</label> 
				<div class="input-group col-9 mb-3">
				<select class="form-control form-control-sm"
					name="range">
					<option selected>----</option>
					<option value="1~50">1~50명</option>
					<option value="51~100">51~100명</option>
					<option value="101~300">101~300명</option>
					<option value="300~">300명~</option>
				</select>
				</div>
			</div>

			<!--  9. 설립연도  -->
			<div class="row">
				<label for="comBirth" class="col-3">설립연도</label> 
				<div class="input-group col-9 mb-3">
				<input class="comBirth form-control form-control-sm" 
						type="date" max="2018-11-30"
						id="comBirth" name="comBirth" value="${companyInfo.companyBirth}">
				</div>
			</div>
			

			<!--  10. 기업이미지1  -->
			<div class="row">
				<label for="comimage1" class="col-3">기업이미지1</label>
				<div class="input-group col-9 mb-3">
				<input type="file" class="comimage1" name="comimage1">
				</div>
			</div>

			<!--  11. 기업이미지2  -->
			<div class="row">
				<label for="comimage2" class="col-3">기업이미지2</label>
				<div class="input-group col-9 mb-3">
				<input type="file" class="comimage2" name="comimage2">
				</div>
			</div>

			<!--  12. 기업이미지3  -->
			<div class="row">
				<label for="comimage3" class="col-3">기업이미지3</label>
				<div class="input-group col-9 mb-3">
				<input type="file" class="comimage3" name="comimage3">
				</div>
			</div>

			<!-- 13. 기업소개 -->
			<div class="row">
				<label for="companyintro" class="col-3">기업소개</label>
				<div class="input-group col-12 mb-3">
					<textarea name="intro" rows="10"
						class="form-control" required id="companyintro">${companyInfo.companyIntroduce}
					</textarea>
				</div>
			</div>
			<div class="yesnobtn text-right mb-5">
				<button type="submit" class="btn btn-md btn-secondary yesnobtn" id="btnSubmit">수정</button>
				<button type="button" class="btn btn-md btn-outline-secondary yesnobtn" onclick = "cancle()">취소</button>
			</div>

		</div>
		<!-- selfinfo1닫기 -->
	</form>
	<!--form 닫기-->
</div>
<!--container닫기-->






<%@include file="../footer.jsp"%>

<script src="assets/js/tabSizing.js"> </script>
 
<script> 
var firstPhone = "${fn:substring(companyInfo.phoneNum,0,3)}";
// $("#phoneNum1 option:eq(firstPhone)").prop("selected", true);
$("#phoneNum1").val(firstPhone).prop("selected", true);

var range = "${companyInfo.companyRange}";
console.log(range);
$('select[name = "range"]').val(range).prop("selected", true);


$(document).ready(function() {
	if($(document).width() < 768){
		verCul();
	}
	
	var address1 = '${companyInfo.address}';
	var address11 = address1.substring(5).split( ')', 1 ) + ")";
	$('input[name = "address1"]').val(address11);
	
	var address21 = address1.substring( (address11.length+5) );
	$('input[name = "address2"]').val(address21);
});

$(window).resize(function (){
	if($(document).width() < 768){
		verCul();
	} else if($(document).width() > 768){
		horCul()}
});

	
	// 비밀번호 일치 여부
	$('#passwd_check').keyup(function() {
		if ($('#passwd').val() != $('#passwd_check').val()) {
			$('.correctPw').text('');
			$('.correctPw').html("비밀번호가 일치하지 않습니다").css("color", "red");
		} else {
			$('.correctPw').text('');
			$('.correctPw').html("비밀번호가 일치합니다!").css("color", "black");
		}
	});

	// 이메일 자동입력
	function changeSelect(s, no) {
		form = document.tutorModifyForm;
		sel = s[s.selectedIndex].value;
		dis = 1;

		if (sel == "user") {
			sel = "";
			dis = 0;
		}
		form.emailBox.value = sel;
		form.emailBox.disabled = dis;
	}
	
	$('input[name = "postcode"]').click(function() {
		sample4_execDaumPostcode();
	});

	//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	function sample4_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 도로명 조합형 주소 변수

				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				}
				// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				$('input[name = "postcode"]').val(data.zonecode); // 5자리 우편번호 사용
				$('input[name = "address1"]').val(fullRoadAddr);
				// document.getElementById('sample4_jibunAddress').value = data.jibunAddress; // 지번주소
			}
		}).open();
	}
	
	
	

	// 직종입력
	function changeJobSelect(s, no) {
		form1 = document.tutorModifyForm;
		sel = s[s.selectedIndex].text;
		dis = 1;

		if (sel == "user") {
			sel = "";
			dis = 0;
		}
		form1.jobSelects.value = sel;
		form1.jobSelects.disabled = dis;
	}

	//회원정보 수정 입력검사
   window.onload = function() {
      document.getElementById("btnSubmit").onclick = inputCheck;

   }
	
	function inputCheck() {
		
		if (tutorModifyForm.passwd.value != "") {

				// var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}/;
				var regex = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
				// alert(registerForm.passwd.value);
				// alert(passwd);
				if (!regex.test(tutorModifyForm.passwd.value)) {
					// alert(regex.test(registerForm.passwd.value));
					alert("비밀번호는 특수문자 포함 8자리 이상입니다.");
					tutorModifyForm.passwd.focus();
					return false;
				} else {
					return true;
				}

			}
		
		if (tutorModifyForm.passwd.value == "") {
			alert("비밀번호를 입력하시오.");
			tutorModifyForm.passwd.focus();
			return;
		}
		if (tutorModifyForm.passwd.value !== tutorModifyForm.passwd_check.value) {
			alert("비밀번호 불일치!");
			tutorModifyForm.passwd_check.focus();
			return;
		}
		if (tutorModifyForm.email.value == "") {
			alert("이메일을 입력하시오.");
			tutorModifyForm.email.focus();
			return;
		}
		if (tutorModifyForm.resume.value == "") {
			alert("이력서를 넣으시오");
			tutorModifyForm.resume.focus();
			return;
		}
		if (tutorModifyForm.mainSelect.value == "") {
			alert("직종을 선택해주시오.");
			tutorModifyForm.mainSelect.focus();
			return;
		}
		if (tutorModifyForm.mainSelect.value == "") {
			alert("직종을 선택해주시오.");
			tutorModifyForm.mainSelect.focus();
			return;
		}
		if (tutorModifyForm.intro.value == "") {
			alert("소개를 입력하시오.");
			tutorModifyForm.intro.focus();
			return;
		}
		tutorModifyForm.submit();
	}


	$('#emailBoxSelect').change(function() {
		// form1 = document.registerForm;

		$("#emailBoxSelect option:selected").each(function() {
			if ($(this).val() == '1') { //직접입력일 경우
				$("#emailBox1").val('');
				$("#emailBox1").attr("readonly", false); //활성화
			} else { //직접입력이 아닐경우
				// sel = $("#emailBox1").val();
				$("#emailBox1").val($(this).text()); //선택값 입력 
				console.log($("#emailBox1").val());
				//  form1.emailBox1.value = $("#emailBox1").val();
				$("#emailBox1").attr("readonly", true);

			}
		})
	});

	 function maxLengthCheck(object){
	      if (object.value.length > object.maxLength){
	       object.value = object.value.slice(0, object.maxLength);
	        }
	 }
	 
	//회원가입시 취소버튼 누르면 전에 카테고리로 이동
	var cancle = function() {
		location.href = "starters?cmd=companyInfoDetailAction";
	};
</script>

