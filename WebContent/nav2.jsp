<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<link rel="stylesheet" href="assets/css/Anavi.css" />
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
						<a href="#" onclick="chatting(); return false;" id="chat">실시간톡
						</a>
						<a href="starters?cmd=logoutAction" id="logout">로그아웃 </a>
					</c:when>
					<c:when test='${membersCateg == 3}'>
            ${name}<p class="nim">기업님</p>
						<a href="starters?cmd=logoutAction" id="logout">로그아웃 </a>
					</c:when>
					<c:when test='${membersCateg == 1}'>
            ${name}<p class="nim">튜티님</p>
						<a href="#" onclick="chatting(); return false;" id="chat">실시간톡
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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top"
	id="bgbg">
	<a class="navbar-brand" href="starters"><img alt=""
		src="assets/image/로고y.png"></a>
	<button class="navbar-toggler" id="navbar-toggler" onclick="navH()"
		type="button" data-target="#navbarsExample04"
		aria-controls="navbarsExample04" aria-expanded="false"
		aria-label="Toggle navigation">

		<span class="navbar-icon top"></span> <span class="navbar-icon bottom"></span>
	</button>

	<!-- 네비 div 시작 -->
	<div class="collapse navbar-collapse" id="navbarsExample04">
		<ul class="navbar-nav mr-auto justify-content-end">
			<li class="nav-item active"><a class="nav-link"
				href="starters?cmd=intro">스타터스</a></li>
			<li class="nav-item"><a class="nav-link"
				href="starters?cmd=tutoringList">튜터링 </a></li>
			<li class="nav-item"><a class="nav-link"
				href="starters?cmd=portfolioList">포트폴리오</a></li>
			<li class="nav-item"><a class="nav-link"
				href="starters?cmd=companyList">기업</a></li>
			<li class="nav-item dropdown community"><a
				class="nav-link dropdown-toggle" href="https://example.com"
				id="dropdown04" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">커뮤니티</a>
				<div class="dropdown-menu" aria-labelledby="dropdown04">
					<a class="dropdown-item" href="starters?cmd=notice">공지사항</a> <a
						class="dropdown-item" href="starters?cmd=qna">QnA</a> <a
						class="dropdown-item" href="starters?cmd=reviewList">후기</a>
				</div></li>
			<c:choose>
				<c:when test='${empty name}'>
				</c:when>
				<c:when test='${membersCateg ==2}'>
					<li class="nav-item"><a class="nav-link"
						href="starters?cmd=tutorTutoringList">마이페이지</a></li>

				</c:when>
				<c:when test='${membersCateg == 3}'>
					<li class="nav-item"><a class="nav-link"
						href="starters?cmd=companyInfoDetailAction">마이페이지</a></li>


				</c:when>
				<c:when test='${membersCateg == 1}'>
					<li class="nav-item"><a class="nav-link"
						href="starters?cmd=tuteeDetailAction">마이페이지</a></li>

				</c:when>
				<c:when test='${membersCateg == 999}'>
					<li class="nav-item mypage"><a class="nav-link"
						href="starters?cmd=adminMain">관리자마이페이지</a></li>
				</c:when>

			</c:choose>
		</ul>
	</div>
</nav>



<!--            <li class="nav-item">
			<h2 id="slideL">접기</h2>
			</li> -->

<div id="content-wrap">
	<i class="fas fa-arrow-circle-right" id="sildeL"></i>
	<aside>
		<ul class="navbar-nav">


			<li class="nav-item">
				<h3>
					<span class="icon-dashboard">회원관리</span>
				</h3>
				<ul>
					<li><a class="nav-link"
						href="starters?cmd=adminMemberTuteeList">튜티</a></li>
					<li><a class="nav-link"
						href="starters?cmd=adminMemberTutorList">튜터</a></li>
					<li><a class="nav-link"
						href="starters?cmd=adminMemberCompanyList">기업</a></li>
					<li><a class="nav-link" href="starters?cmd=adminMemberList">전체보기</a></li>
					<li><a class="nav-link" href="starters?cmd=MemberStatistic">통계</a></li>

				</ul>
			</li>


			<li class="nav-item">


				<h3>
					<span class="icon-tasks">고객편의</span>
				</h3>
				<ul>
					<li><a class="nav-link" href="starters?cmd=adminNotice">공지사항</a></li>
					<li><a class="nav-link" href="starters?cmd=adminQna">Q&A</a></li>
					<li><a class="nav-link" href="starters?cmd=adminReviewList">후기게시판</a></li>
				</ul>
			</li>


			<li class="nav-item">
				<h3>
					<span class="icon-calendar">통계</span>
				</h3>
				<ul>
					<li><a class="nav-link" href="starters?cmd=allstatistic">매출</a></li>
					<li><a class="nav-link"
						href="starters?cmd=tutoringStatisctics">튜터링 통계</a></li>
					<li><a class="nav-link" href="starters?cmd=reviewStatisctics">후기
							통계</a></li>
				</ul>
			</li>


			<li class="nav-item">
				<h3>
					<a class="nav-link" href="starters?cmd=adminTutoringList">튜터링
						등록현황</a>
				</h3>
			</li>

			<li class="nav-item">
				<h3>
					<a class="nav-link" href="starters?cmd=adminPortfolioList">포트폴리오
						관리</a>
				</h3>
			</li>

			<li class="nav-item">
				<h3>
					<a class="nav-link" href="starters?cmd=adminGetJobList">직종 편집</a>
				</h3>
			</li>

			<li class="nav-item">
				<h3>
					<a class="nav-link" href="starters?cmd=adminInterviewPay">면접 신청
						가격 변경</a>
				</h3>
			</li>
		</ul>
	</aside>

	<!-- end of content-wrap -->
</div>


<script type="text/javascript">
$(function(){
    $("#content-wrap h3").click(function(){
       $("#content-wrap ul ul").slideUp();
       if(!$(this).next().is(":visible"))
       {
          $(this).next().slideDown();
       }
    })
 })

$("#sildeL").click(function() {
   if($("#sildeL").hasClass("fa-arrow-circle-left")){
      $("#sildeL").attr('class','fas fa-arrow-circle-right');
      $( "aside, #content-wrap" ).animate({left: -200,}, 1000);
      $("article").animate({ marginLeft: -200,}, 1000);
   } else {
      $("#sildeL").attr('class','fas fa-arrow-circle-left');
      $( "aside, #content-wrap" ).animate({ left: 0,}, 1000);
      $("article").animate({ marginLeft: 0,}, 1000);
   }
});


function chatting() {
    window.name = "parentForm";
     window.open("starters?cmd=chat",
             "chkForm", "width=1000, height=1000, resizable = no, scrollbars = no");    
return true;
}
function navH() {
	       document.getElementById('navbar-toggler').classList.toggle('open');
	       document.getElementById('navbarsExample04').classList.toggle('show');
}
</script>