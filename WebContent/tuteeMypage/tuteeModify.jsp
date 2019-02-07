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


<!-- 부트스트랩 date -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
<link href="assets/css/fSelect.css" rel="stylesheet" type="text/css">


<script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/fSelect.js"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@include file="../nav.jsp"%>
<link href="assets/css/mypage/tutor/tutorMyInfo_Modify.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">



<title>스타터스 : 회원정보수정</title>


<div class="container">
   <form method="post" class="selfinfo" name="tuteeModifyForm"
      action="starters?cmd=tuteeModifyAction">


  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tuteeDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutoringLike">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeMypageList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link " href="starters?cmd=tutoringInfo">결제 내역</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=interviewList">면접 현황</a>
    </li>
  </ul>
  
      <h3 class="mt-5">회원정보수정</h3>
      <div class="selfinfo1">
         <input type="hidden">
         
         
         <!--  이름  -->
         <div class="row">
         <label for="name" class="col-3">이름</label>
            <div class="input-group col-9 mb-3">
                 <input type="text" class="form-control form-control-sm"
                  name="name" id ="name"  value="${tuteeInfo.name }" required="required" disabled >
            </div>
         </div>
         

         <!--  아이디  -->
         <div class="row">
         <label for="id" class="col-3">아이디</label>
            <div class="input-group col-9 mb-3">
                 <input type="text" class="form-control form-control-sm"
                   name="username"  value="${tuteeInfo.memberId}" required="required" disabled >
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

         <!--  생년월일  -->
         <div class="row">
         <label for="inputEmail3" class="col-3 control-label">생년월일</label>
            <div class="input-group col-9 mb-3">
                <input class="form-control form-control-sm"
                  type="text" name="birth" value="${fn:substring(tuteeInfo.birth,0,4)}년" disabled>
                <input class="form-control form-control-sm"
                  type="text" name="birth" value="${fn:substring(tuteeInfo.birth,5,7)}월" disabled>
                <input class="form-control form-control-sm"
                  type="text" name="birth" value="${fn:substring(tuteeInfo.birth,8,10)}일" disabled>
            </div>
         </div>
         
         
         <!-- 성별 -->
         <div class="row">
            <label for="gender" class="col-3">성별</label>
            
            <div class="input-group col-9 mb-3">
               <c:if test="${tuteeInfo.gender=='F'}">
               여
               </c:if>
               <c:if test="${tuteeInfo.gender=='M'}">
               남
               </c:if>
            </div>
         </div>

      <!-- 4. 이메일 -->
         <div class="row">
            <label for="email" class="col-3">이메일</label>
            <div class="input-group col-9 mb-3">
               <input type="text" class="form-control form-control-sm" id="email"
                  value="${fn:substring(tuteeInfo.email,0,fn:indexOf(tuteeInfo.email,'@')) }" name="email" required="required">
               @
               <input type="text" name="emailBox" id="emailBox1"
                  class="emailBox form-control form-control-sm" readonly="readonly"
                  required="required" value="${fn:substring(tuteeInfo.email,fn:indexOf(tuteeInfo.email,'@')+1,fn:length(tuteeInfo.email)) }"> 
               <select  id = "emailBoxSelect" name="emailBoxSelect"
                  class="form-control form-control-sm">
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
            <label for="tel" class="col-3">전화번호</label>
            <div class="input-group col-9 mb-3 ">
               <select name="tel1" class="form-control form-control-sm" id="tel1">
                  <option value="010">010</option>
                  <option value="011">011</option>
                  <option value="016">016</option>
               </select> 
               <input type="text" class="tel2 form-control form-control-sm"
                  value="${fn:substring(tuteeInfo.phoneNum,3,7)}" name="tel2" required="required" id="tel2" max="9999" maxlength="4"
                  oninput="maxLengthCheck(this)">
               <input type="text" class="tel3 form-control form-control-sm"
                  value="${fn:substring(tuteeInfo.phoneNum,7,12)}" name="tel3" required="required" id="tel3" max="9999" maxlength="4"
                  oninput="maxLengthCheck(this)">
            </div>
         </div>

         <!-- 직종 추가 -->
         <div class="row">
         <label class="col-3" for="main1">직종 선택</label>
            <div class="input-group col-9 mb-3">
               <select name="mainSelect" id="mainSelect" class="mainSelect"
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

         </div>

         <!-- 자기소개 -->
         <div class="row">
            <label for="id_profile" class="col-3">자기소개</label>
            <div class="input-group col-12 mb-3">
               <textarea name="intro" class="form-control" id="id_profile" 
               required="required" >${tuteeInfo.intro} </textarea>
            </div>
         </div>


         <div class="yesnobtn text-right mb-5">
            <button type="submit" class="btn btn-md btn-secondary yesnobtn"
               id="btnSubmit">수정하기</button>
            <button type="button"
               class="btn btn-md btn-outline-secondary yesnobtn"
               onclick="cancle()">취소</button>
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

var firstPhone = "${fn:substring(tuteeInfo.phoneNum,0,3)}";
$("#tel1").val(firstPhone).prop("selected", true);

$(document).ready(function() {
   if($(document).width() < 768){
      verCul();
   }
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
      form = document.tuteeModifyForm;
      sel = s[s.selectedIndex].value;
      dis = 1;

      if (sel == "user") {
         sel = "";
         dis = 0;
      }
      form.emailBox.value = sel;
      form.emailBox.disabled = dis;
   }

   // 직종입력
   function changeJobSelect(s, no) {
      form1 = document.tuteeModifyForm;
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

   //회원정보 수정 입력검사
 window.onload = function() {
      document.getElementById("btnSubmit").onclick = inputCheck;

   }
   
   function inputCheck() {
        if (tuteeModifyForm.passwd.value != "") {
               // var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}/;
               var regex = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
               // alert(registerForm.passwd.value);
               // alert(passwd);
               if (!regex.test(tuteeModifyForm.passwd.value)) {
                  // alert(regex.test(registerForm.passwd.value));
                  alert("비밀번호는 문자, 숫자, 특수문자 포함 8자리 이상입니다.");
                  tuteeModifyForm.passwd.focus();
                  return false;
               } else {
                  return true;
               }

            }

      if (tuteeModifyForm.passwd.value == "") {
         alert("비밀번호를 입력하시오.");
         tuteeModifyForm.passwd.focus();
         return;
      }
      if (tuteeModifyForm.passwd.value !== tuteeModifyForm.passwd_check.value) {
         alert("비밀번호 불일치!");
         tuteeModifyForm.passwd_check.focus();
         return;
      }
      if (tuteeModifyForm.email.value == "") {
         alert("이메일을 입력하시오.");
         tuteeModifyForm.email.focus();
         return;
      }
      if (tuteeModifyForm.tel2.value == "" || tuteeModifyForm.tel3.value == "") {
         alert("전화번호를 입력하시오.");
         tuteeModifyForm.tel2.focus();
         return;
      }
      tuteeModifyForm.submit();
   }


   //회원가입시 취소버튼 누르면 전에 카테고리로 이동
   var cancle = function() {
      location.href = "starters?cmd=tuteeDetailAction";
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

      function maxLengthCheck(object) {
            if (object.value.length > object.maxLength) {
               object.value = object.value.slice(0, object.maxLength);
            }
         }
</script>
