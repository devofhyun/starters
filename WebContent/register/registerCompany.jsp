<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<%@include file="../nav.jsp"%>
<!-- 부트스트랩 date -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/register_member/register_tutee.css">


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>스타터스 : 회원가입 - 기업</title>


<div class="container">
	<form method="post" class="selfinfo" name="registerForm" action = "starters?cmd=registerCompanyAction" id = "registerForm" enctype = "multipart/form-data">
		<h3>회원가입 - 기업</h3>
		
		<div class="selfinfo1">
			<input type="hidden">
			
			<!--  1. 기업명  -->
			<div class="row">
			<label for="name" class="col-mb-2 col-lg-2 col-sm-2 col-12">기업명</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				     <input type="text" class="form-control form-control-sm"
						name="name" id = "name" placeholder="스타터스" required="required">
				</div>
			</div>

			<!--  2. 아이디  -->
			<div class="row">
			<label for="id"  class="col-mb-2 col-lg-2 col-sm-2 col-12">아이디</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
					<input type="text"
						id="id" placeholder="starters" class="companyId form-control form-control-sm" name="companyId"
						required="required" onkeydown = "inputIdChk()">
					<div class="input-group-append">
						<button class="btn btn-sm btn-secondary idCheck" onclick = "openIdChk()">아이디 중복 확인</button>
					</div>
				<div class="checkId" name="checkId"></div>
				<input type = "hidden" name = "idDuplication" value = "idUncheck">
				</div>
			</div>

			<!--  3. 비밀번호  -->
			<div class="row">
			<label for="passwd" class="col-mb-2 col-lg-2 col-sm-2 col-12">비밀번호</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				     <input class="form-control form-control-sm"
					type="password" name="passwd" id="passwd" placeholder="********"
					required="required">
				</div>
			</div>
			</div>

			<div class="row">
			<label for="passwd_check" class="col-mb-2 col-lg-2 col-sm-2 col-12">비밀번호확인</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				    <input class="form-control form-control-sm"
				    type="password" name="passwd_check" id="passwd_check"
					placeholder="********" required="required">
				</div>
				
				<div class="correctPw"></div>
			</div>

			<!-- 4. 이메일 -->
		<div class="row">
				<label for="email" class="col-mb-2 col-lg-2 col-sm-2 col-12">이메일</label>
				<div class="input-group col-sm-5 col-mb-5 col-lg-5 col-12 mb-3">
					<input type="text" class="form-control form-control-sm"
						id="email" placeholder="hongildong" name="email"
						required="required">
						@
					    <input type="text" name="emailBox1"
						id="emailBox1" class="emailBox form-control form-control-sm"
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

			<!-- 전화번호 -->
			<div class="row">
				<label for="tel" class="col-mb-2 col-lg-2 col-sm-2 col-12">전화번호</label>
				<div class="input-group col-sm-7 col-mb-7 col-lg-7 col-12 mb-3 ">
				 	<select name="tel1"  class="form-control form-control-sm">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					</select>
					<input type="number" class="tel2 form-control form-control-sm" placeholder="5693" name="tel2"
					required="required" id = "tel2" max="9999" maxlength="4" oninput="maxLengthCheck(this)">
					<input type="number" class="tel3 form-control form-control-sm"
					placeholder="1203" name="tel3" required="required" id = "tel3" max="9999" maxlength="4" oninput="maxLengthCheck(this)">
					
				</div>
			</div>

			<!--  6. 주소  -->
			<div class="row">
				<label class="col-mb-2 col-lg-2 col-sm-2 col-12">주소</label>
				<div class="input-group-vertical col-mb-5 col-lg-5 col-sm-5 col-12 mb-3 ">
				 	<input type="text" name="postcode" class="form-control form-control-sm"
						placeholder="여기를 눌러서 주소 선택하세요." readonly="readonly">
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
			<label for="homepage" class="col-mb-2 col-lg-2 col-sm-2 col-12">홈페이지 주소</label>
				<div class="input-group col-mb-4 col-lg-4 col-sm-4 col-12 mb-3">
				     <input
					type="text" id="homepage" placeholder="www.starters.com"
					class="form-control form-control-sm homepage" name="homepage">
				</div>
			</div>

			<!-- 8. 회사규모 -->
			<div class="row">
				<label for="range" class="col-mb-2 col-lg-2 col-sm-2 col-12">회사규모</label> 
				<div class="input-group col-mb-4 col-lg-4 col-sm-4 col-12 mb-3">
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
				<label for="comBirth" class="col-mb-2 col-lg-2 col-sm-2 col-12">설립연도</label> 
				<div class="input-group col-mb-4 col-lg-4 col-sm-4 col-12 mb-3">
				<input class="comBirth form-control form-control-sm" 
						type="date" max="2018-11-30"
						id="comBirth"name="comBirth">
				</div>
			</div>

			<!--  10. 기업이미지1  -->
			<div class="row">
				<label for="comimage1" class="col-mb-2 col-lg-2 col-sm-2 col-12">기업이미지1</label>
				<div class="input-group col-3 mb-3">
				<input type="file" class="comimage1" name="comimage1">
				</div>
			</div>

			<!--  11. 기업이미지2  -->
			<div class="row">
				<label for="comimage2" class="col-mb-2 col-lg-2 col-sm-2 col-12">기업이미지2</label>
				<div class="input-group col-3 mb-3">
				<input type="file" class="comimage2" name="comimage2">
				</div>
			</div>

			<!--  12. 기업이미지3  -->
			<div class="row">
				<label for="comimage3" class="col-mb-2 col-lg-2 col-sm-2 col-12">기업이미지3</label>
				<div class="input-group col-3 mb-3">
				<input type="file" class="comimage3" name="comimage3">
				</div>
			</div>

			<!-- 13. 기업소개 -->
			<div class="row">
				<label for="companyintro" class="col-mb-3 col-lg-3 col-sm-3 col-12">기업소개</label>
				<div class="input-group col-12 mb-3">
					<textarea name="companyintro" cols="40" rows="10"
						class="form-control" required id="companyintro">
					</textarea>
				</div>
			</div>

			<div class="row yesnobtn">
				<button type="submit" class="btn btn-md btn-secondary yesnobtn" id="btnSubmit">회원가입</button>
				<button type="button" class="btn btn-md btn-outline-secondary yesnobtn" onclick="cancle()">취소</button>
			</div>
	</form>
	<!--form 닫기-->
</div>
<!--container닫기-->


<%@include file="../footer.jsp"%>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 date -->
<script type='text/javascript'
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="assets/js/jquery.form.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">



	// 아이디 중복확인
//	$(".idCheck").click(function() {
//		var inputId = $('.companyId').val();
//		// location.href = "starters?cmd=memberIdCheck&id=inputId"
//		//alert(inputId);	 
//		$.ajax({ // .ajax({객체를 집어넣는 거야})
//			url : "starters?cmd=memberIdCheck&id=" + inputId,
//			success : function(result) {
//				$("div.checkId").html(result);
//			}
//		});
//	});

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
	


	// 회원가입체크
	window.onload = function() {
		registerForm.name.focus();
		document.getElementById("btnSubmit").onclick = inputCheck;

	}
	//회원가입 입력검사

	function inputCheck() {
		
	if (registerForm.passwd.value != "") {

			// var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}/;
			var regex = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
			// alert(registerForm.passwd.value);
			// alert(passwd);
			if (!regex.test(registerForm.passwd.value)) {
				// alert(regex.test(registerForm.passwd.value));
				alert("비밀번호는 특수문자 포함 8자리 이상입니다.");
				registerForm.passwd.focus();
				return false;
			} else {
				return true;
			}

		}

		if (registerForm.idDuplication.value != "idCheck") {
			alert("아이디 중복체크를 해주세요.");
			registerForm.id.focus();
			return false;
		}
		if (registerForm.name.value == "") {
			alert("이름을 입력하시오.");
			registerForm.name.focus();
			return false;
		}
		if (registerForm.id.value == "") {
			alert("아이디를 입력하시오.");
			registerForm.id.focus();
			return false;
		}
		if (registerForm.passwd.value == "") {
			alert("비밀번호를 입력하시오.");
			registerForm.passwd.focus();
			return false;
		}
		if (registerForm.passwd.value !== registerForm.passwd_check.value) {
			alert("비밀번호 불일치!");
			registerForm.passwd_check.focus();
			return false;
		}
		if (registerForm.homepage.value == "") {
			alert("주소를 입력하시오.");
			registerForm.birthdate.focus();
			return false;
		}
		if (registerForm.email.value == "") {
			alert("이메일을 입력하시오.");
			registerForm.email.focus();
			return false;
		}
		if (registerForm.tel2.value == "" || registerForm.tel3.value == "") {
			alert("전화번호를 입력하시오.");
			registerForm.tel2.focus();
			return false;
		}
		registerForm.submit();
	}

	// 아이디 중복체크 화면open
	function openIdChk() {
		window.name = "parentForm";
		window.open("starters?cmd=memberIdCheck2", "chkForm",
				"width=500, height=300, resizable = no, scrollbars = no");
	}
	function inputIdChk() {
		document.registerForm.idDuplication.value = "idUncheck";
	}

	//회원가입시 취소버튼 누르면 전에 카테고리로 이동
	var cancle = function() {
		location.href = "starters?cmd=registerCateg";
	};

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
</script>
