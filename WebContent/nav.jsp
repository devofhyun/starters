<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
<link rel="stylesheet" href="assets/css/navi.css" />
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">

	<!-- 파비콘 -->
	<link rel=" shortcut icon" href="assets/image/stsrter.ico">
	<link rel="icon" href="assets/image/stsrter.ico">
	
<style>
.label {
    display: inline;
    padding: .2em .6em .3em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    color: #fff;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: .25em;
}
.label-info{
    background-color: #fdd95f;
    }
.label:empty {
    display: none;
}
</style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
      String id = null;
      if (session.getAttribute("id") != null) {
         id = (String) session.getAttribute("id");
         // out.print("/////////////////////");
         // out.print(id);
      }
   
   %>
   <script>
   function getUnread(){
      $.ajax({
         type: "POST",
         url:'starters?cmd=chatUnread',
         data:{
            userID: '<%=id%>',
         },
         success: function(result){
            if(result >=1){
               showUnread(result);
            }else{
               showUnread('');
            }
         }
      });
   }
   function showUnread(result){
      $('#unread').html(result);
   }
   function getInfiniteUnread(){
      setInterval(function(){
         getUnread();}, 1000);
   }
   </script>
   
   </head>
<div id="top">
   <ul>
      <li>
         <p>
            <c:choose>
               <c:when test='${empty name}'>
                  <a href="starters?cmd=login" id="login">로그인 </a>
                  <a href="starters?cmd=registerCateg" id="register">회원가입</a>
               </c:when>

               <c:when test='${membersCateg ==2}'>
            ${name}<p class="nim">튜터님</p>
                  <a href="#" onclick="chatting(); return false;" id="chat">실시간톡<span id = "unread" class = "label label-info"></span>
                  </a>
                  <a href="starters?cmd=logoutAction" id="logout">로그아웃 </a>
               </c:when>
               <c:when test='${membersCateg == 3}'>
            ${name}<p class="nim">기업님</p>
                  <a href="starters?cmd=logoutAction" id="logout">로그아웃 </a>
               </c:when>
               <c:when test='${membersCateg == 1}'>
            ${name}<p class="nim">튜티님</p>
                  <a href="#" onclick="chatting(); return false;" id="chat">실시간톡<span id = "unread" class = "label label-info"></span>
                  </a>
                  <a href="starters?cmd=logoutAction" id="logout">로그아웃 </a>
               </c:when>
                 <c:when test='${membersCateg == 999}'>
            ${name}<p class="nim">관리자님</p>
                  <a href="starters?cmd=logoutAction" id="logout">로그아웃 </a>
               </c:when>
               <c:otherwise>

                  <a href="starters?cmd=login" id="login">로그인 </a>
                  <a href="starters?cmd=registerCateg" id="register">회원가입</a>
               </c:otherwise>
            </c:choose>
         </p>
      </li>
   </ul>



</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top" id="bgbg">
      <a class="navbar-brand" href="starters"><img alt="" src="assets/image/로고y.png"></a>
      <button class="navbar-toggler" id= "navbar-toggler" 
                  onclick="navH()" type="button"
                  data-target="#navbarsExample04" aria-controls="navbarsExample04" 
                  aria-expanded="false" aria-label="Toggle navigation">
                  
        <span class="navbar-icon top"></span>
        <span class="navbar-icon middle"></span>
        <span class="navbar-icon bottom"></span>
      </button>

   <!-- 네비 div 시작 -->
      <div class="collapse navbar-collapse" id="navbarsExample04">
        <ul class="navbar-nav mr-auto justify-content-end">
          <li class="nav-item active">
            <a class="nav-link" href="starters?cmd=intro">스타터스</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="starters?cmd=tutoringList">튜터링 </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="starters?cmd=portfolioList">포트폴리오</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="starters?cmd=companyList">기업</a>
          </li>
          <li class="nav-item dropdown community">
            <a class="nav-link dropdown-toggle" href="https://example.com" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
            <div class="dropdown-menu" aria-labelledby="dropdown04">
              <a class="dropdown-item" href="starters?cmd=notice">공지사항</a>
              <a class="dropdown-item" href="starters?cmd=qna">QnA</a>
              <a class="dropdown-item" href="starters?cmd=reviewList">후기</a>
            </div>
          </li>
            <c:choose>
   <c:when test='${empty name}'>
   </c:when>
      <c:when test='${membersCateg ==2}'>
       <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutorDetailAction">마이페이지</a>
      </li>

   </c:when>
      <c:when test='${membersCateg == 3}'>
       <li class="nav-item">
      <a class="nav-link" href="starters?cmd=companyInfoDetailAction">마이페이지</a>
      </li>


   </c:when>
      <c:when test='${membersCateg == 1}'>
       <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeDetailAction">마이페이지</a>

      </li>

   </c:when>
   <c:when test='${membersCateg == 999}'>
      <li class="nav-item mypage">
         <a class="nav-link" href="starters?cmd=adminMain">관리자마이페이지</a>
      </li>
   </c:when>

</c:choose>
        </ul>
      </div>
    </nav>
    
  

<script type="text/javascript">
function chatting() {
    window.name = "parentForm";
     window.open("starters?cmd=chat",
             "chkForm", "width=800, height=700, resizable = no, scrollbars = no");    
return true;
}

function navH() {
          document.getElementById('navbar-toggler').classList.toggle('open');
          document.getElementById('navbarsExample04').classList.toggle('show');
}



$(document).ready(function(){
   getUnread();
   if($('button').hasClass('w1') === true){
	   $('.w1').addClass('active');
   }
   
});
</script>