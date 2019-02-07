<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="com.starters.*"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<link href="assets/css/fSelect.css" rel="stylesheet" type="text/css">
<!-- 부트스트랩 date -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">



<%@include file="../nav.jsp"%>
<link rel="stylesheet" type="text/css" href="assets/css/register_member/register_tutor.css">


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<title>스타터스 : 회원가입 - 튜터</title>


<div class="container">
<form method="post" class="selfinfo" name="registerForm"
		action="starters?cmd=registerTutorAction">
		<h3>회원가입 - 튜터</h3>
		
		<div class="selfinfo1">
			<input type="hidden">
			
			<!--  이름  -->
			<div class="row">
			<label for="name" class="col-mb-2 col-lg-2 col-sm-2 col-12">이름</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				     <input type="text" class="form-control form-control-sm"
						name="name" id ="name" placeholder="홍길동" required="required">
				</div>
			</div>
			
			<!--  아이디  -->
			<div class="row">
			<label for="id" class="col-mb-2 col-lg-2 col-sm-2 col-12">아이디</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				     <input type="text"
					id="id" placeholder="hongildong" class="form-control form-control-sm tuteeId" name="memberId"
					required="required" onkeydown = "inputIdChk()">
					<div class="input-group-append">
						<button class="btn btn-sm btn-secondary idCheck" onclick = "openIdChk()">아이디 중복 확인</button>
					</div>
					<div class="checkId" name="checkId"></div>
					<input type = "hidden" name = "idDuplication" value = "idUncheck">
				</div>
			</div>

			
			<!--  비밀번호  -->
			<div class="row">
			<label for="passwd" class="col-mb-2 col-lg-2 col-sm-2 col-12">비밀번호</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				     <input class="form-control form-control-sm"
					type="password" name="passwd" id="passwd" placeholder="********"
					required="required">
				</div>
			</div>
			
			<div class="row">
			<label for="passwd_check" class="col-mb-2 col-lg-2 col-sm-2 col-12">비밀번호확인</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				    <input class="form-control form-control-sm"
				    type="password" name="passwd_check" id="passwd_check"
					placeholder="********" required="required">
				<div class="correctPw"></div>
				</div>
			</div>

			<!--  생년월일  -->
			<div class="row">
			<label for="inputEmail3" class="col-mb-2 col-lg-2 col-sm-2 col-12 control-label">생년월일</label>
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
				 	<input class="form-control form-control-sm"
						type="date" max="2018-11-30" class="birthdate"
						name="birthdate" id = "birthdate" required="required">
				</div>
			</div>

			<!-- 성별 -->
			<div class="row">
				<label for="gender" class="col-mb-2 col-lg-2 col-sm-2 col-12">성별</label>
				
				<div class="input-group col-sm-4 col-mb-4 col-lg-4 col-12 mb-3">
					<label class="maleGender" for="optionRadios1">
					<input type="radio" id="optionsRadios1"
						value="M" name="gender" checked required="required">남자</label>
						
					<label class="femaleGender" for="optionRadios2">
					<input type="radio" id="optionsRadios2" 
						value="F" name="gender" required="required">여자</label>
				</div>
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
			
			<!-- 이력서 -->
			<div class="row">
				<label for="wish_work" class="col-mb-2 col-lg-2 col-sm-2 col-12">이력서</label>
				<div class="input-group col-4 mb-3">
					<label><input
					type="file" name="resume" required="required"/></label>
				</div>
			</div>
			
			<!-- 포트폴리오 -->
			<div class="row">
				<label for="wish_work" class="col-mb-2 col-lg-2 col-sm-2 col-12">포트폴리오</label>
				<div class="input-group col-4 mb-3">
					<label><input type="file" name="portfolio" /></label>
				</div>
			</div>

			<!-- 직종 추가 -->
			<div class="row">
			<label class="col-mb-2 col-lg-2 col-sm-2 col-12" for="main1">직종 선택</label>
				<div class="input-group col-10 mb-3">
				<select
					name="mainSelect" id="mainSelect" class="mainSelect"
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

							//	ArrayList<MiddleCategVO> list = detail.get(label.getMainCategId());
							//		for (MiddleCategVO option : labels) {
							out.print("<option value=\"" + label.getMiddleCategId() + "\">" + label.getMiddleName() + "</option>");
							//			}
							//						out.print("</optgroup>");
						}
						if (mainName != null) {
							out.print("</optgroup>");
						}
					%>
				</select>
				</div>
			</div>

			<!-- 자기소개 -->
			
			<div class="row">
				<label for="id_profile" class="col-mb-3 col-lg-3 col-sm-3 col-12"">자기소개</label>
				<div class="input-group col-12 mb-3">
				 	<textarea name="intro" rows="10" class="form-control"
					required id="id_profile" required="required">
				</textarea>
				</div>
			</div>

			<div class="row yesnobtn">
				<button type="submit" class="btn btn-md btn-secondary yesnobtn" id="btnSubmit">회원가입</button>
				<button type="button" class="btn btn-md btn-outline-secondary yesnobtn" onclick="cancle()">취소</button>
			</div>
		</div>
	</form>
	<!--form 닫기-->
</div>
<!--container닫기-->


<%@include file="../footer.jsp"%>

<script src="assets/js/jquery-3.3.1.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 date -->
<script type='text/javascript'
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/fSelect.js"></script>
<!-- <script src="assets/js/jquery-octofilter.js"></script> -->

<!--<script src="assets/js/select.js"></script>-->


<!-- <script type="text/javascript"
	src="assets/bootstrap-4.1.1/js/bootstrap-multiselect.js"></script> -->


<script type="text/javascript">
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
		if (registerForm.birthdate.value == "") {
			alert("생년월일을 입력하시오.");
			registerForm.birthdate.focus();
			return false;

		}
		if (registerForm.email.value == "") {
			alert("이메일을 입력하시오.");
			registerForm.email.focus();
			return false;
		}
		if (registerForm.tel2.value == "") {
			alert("전화번호를 입력하시오.");
			registerForm.tel2.focus();
			return false;
		}
		if (registerForm.tel3.value == "") {
			alert("전화번호를 입력하시오.");
			registerForm.tel3.focus();
			return false;
		}
		if (registerForm.resume.value == "") {
			alert("이력서를 넣으시오");
			registerForm.resume.focus();
			return false;
		}
		if (registerForm.mainSelect.value == "") {
			alert("직종을 선택해주시오.");
			registerForm.mainSelect.focus();
			return false;
		}
		if (registerForm.mainSelect.value == "") {
			alert("직종을 선택해주시오.");
			registerForm.mainSelect.focus();
			return false;
		}
		if (registerForm.intro.value == "") {
			alert("소개를 입력하시오.");
			registerForm.intro.focus();
			return false;
		}
		
		
		
		registerForm.submit();
	}

	// 아이디 중복체크 화면open
    function openIdChk(){
        window.name = "parentForm";
        window.open("starters?cmd=memberIdCheck2",
                "chkForm", "width=500, height=300, resizable = no, scrollbars = no");    
    }	
	function inputIdChk(){
		document.registerForm.idDuplication.value = "idUncheck";
	}



	//회원가입시 취소버튼 누르면 전에 카테고리로 이동
	var cancle = function() {
		location.href = "starters?cmd=registerCateg";
	};
	
	$('#emailBoxSelect').change(function(){
		// form1 = document.registerForm;
		
		$("#emailBoxSelect option:selected").each(function(){
			if($(this).val()== '1'){ //직접입력일 경우
				$("#emailBox1").val(''); 
				$("#emailBox1").attr("readonly",false); //활성화
			}else{ //직접입력이 아닐경우
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

