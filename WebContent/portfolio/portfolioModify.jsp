<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="com.starters.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="http://www.jqueryscript.net/css/jquerysctipttop.css">
<link rel="stylesheet" type="text/css" href="assets/css/loadimg.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/fSelect.css">


<%@include file="../nav.jsp"%>
<link rel="stylesheet" href="assets/css/tutoring/portfolioRegister.css" />


<title>스타터스 : 포트폴리오 수정</title>
	<form method="post" action="starters?cmd=portfolioModifyAction"
		id="upForm"  enctype = "multipart/form-data" name="portfolioForm">
		
			<div class="container">
		<br>
		<h2><strong>포트폴리오 수정</strong></h2>
		<br>	


		<div class="row">
			<div class="col-md-6 PimgD" >
			<div class="tutoringImg">
				<img id="preview" src="../assets/image/prev.png"/>
				<label id="upload" for="ImgInput">사진을 추가해주세요</label>
				<input type="file" name="portfolioImage" 
					id="ImgInput" accept="image/x-png,image/gif,image/jpeg"/>
			</div>
			
			</div>
			<div class="col-md-6 con">

				<!-- 제목-->
				<div class="row mainTitleClass">
					<h6>제목</h6>
					<input type="text" name="portfolioTitle"   value = "${portfolioInfo.portfolioTitle}" required autofocus>
				</div>

				<div class="row MethodClass">
					<h6>유형</h6>
					<div class="inline">
						<div class="form-check-inline">
						    <input type="radio" class="form-check-input"
						     id="optionsRadios1" value="웹" name="portfolioMethod" ${portfolioInfo.portfolioMethod == "앱" ? "" : "checked"}>
						  <label class="form-check-label" for="optionsRadios1">웹
						  </label>
						</div>
						<div class="form-check-inline">
						    <input type="radio" class="form-check-input" 
						     id="optionsRadios2" value="앱" name="portfolioMethod">
						  <label class="form-check-label" for="optionsRadios2" ${portfolioInfo.portfolioMethod == "앱" ? "checked" : ""}>앱
						  </label>
						</div>
					</div>
					</div>

					<!-- url -->
					<div class="row urlClass">
						<h6>URL</h6>
						<input type="text" name="portfolioUrl"  value = "${portfolioInfo.portfolioUrl}" >
					</div>
					
					<!-- 직종 추가 -->
					<div class="row ">
					<h6>직종 선택</h6>
						<div class="form-group">
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
					</div>
					
					
					<!-- 포폴 업로드 -->
					<!-- File -->
					<div class="row fileClass">
					<h6>파일 선택</h6>
					<div class="filebox">
						<button type="button" class="filebtn btn btn-sm btn-secondary">
						<label for="portFile">업로드 </label>
						</button>
						<input class="upload-name" value="파일선택" disabled="disabled">
						<input type="file" id="portFile" type="file" name="portfolioFile"  class="upload-hidden">
					</div>
				</div>
			</div>		
	</div>
	</div>
	
	<!-- 튜터링 내용-->
		<div class="container content">
			<h4><strong>포트폴리오 내용</strong></h4>

			<textarea class="plan" name="portfolioText" class="form-control form-control-sm">${portfolioInfo.portfolioText}</textarea>
		</div>

	</form>
		<div class="container yesnobtn">
			<button class="btn btn-secondary" type="submit" id="btnSubmit">등록하기</button>
		</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="assets/js/loadimg.min.js"></script>

<script src="assets/js/fSelect.js"></script>
<script type="text/javascript">
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
	   
	   
		// 포폴등록체크
		window.onload = function() {
			portfolioForm.portfolioTitle.focus();
			document.getElementById("btnSubmit").onclick = inputCheck;
		}
		
		function inputCheck() {
			if (portfolioForm.portfolioImage.value == "") {
				alert("사진을 첨부해주세요.");
				portfolioForm.portfolioImage.focus();
				return false;
			}
			if (portfolioForm.portfolioTitle.value == "") {
				alert("포트폴리오 제목을 입력해주세요.");
				portfolioForm.portfolioTitle.focus();
				return false;
			}
			if (portfolioForm.portfolioMethod.value == "") {
				alert("구현방법을 체크해주세요.");
				portfolioForm.portfolioMethod.focus();
				return false;
			}
			if (portfolioForm.portfolioUrl.value == "") {
				alert("포트폴리오URL을 입력해주세요.");
				portfolioForm.portfolioUrl.focus();
				return false;
			}
			if (portfolioForm.mainSelect.value == "") {
				alert("직종을 선택해주세요.");
				portfolioForm.mainSelect.focus();
				return false;
			}
			if (portfolioForm.portfolioFile.value == "") {
				alert("파일을 첨부해주세요.");
				portfolioForm.portfolioFile.focus();
				return false;
			}
			portfolioForm.submit();
		}
		
		$(document).ready(function(){ 
			var fileTarget = $('.filebox .upload-hidden'); 
			fileTarget.on('change', function(){ // 값이 변경되면 
				if(window.FileReader){ // modern browser 
					var filename = $(this)[0].files[0].name; 
				} else { // old IE 
					var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
					}// 추출한 파일명 삽입 
				$(this).siblings('.upload-name').val(filename); 
			}); 
		}); 

</script>