<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
    /* 폰트 */
* {
   font-family: 'Noto Sans KR', sans-serif;
}
.container {
    margin: 10vh auto;
}

ul.first {
    padding: 0;
   text-align: center;
   list-style: none;
}

.first > li > a {
    font-size: 40px;
    font-weight: 900;
    display: block;
    text-decoration: none;
}

ul.second {
    padding: 0;
    margin-bottom: 100px;
}

ul.third {
    display: contents;
}

.sitemap ul.second > li {
    border: 1px solid #feca01;
    width: 150;
    display: inline-table;
    margin-top: 20px;
    min-height: 170;
}

.sitemap ul.second > li > a {
   display: block;
    padding: 10 30;
    background-color: #feca01;
    color: #fff;
    font-weight: 900;
   transition: 0.4s;
    transform-style: preserve-3d;
}

.sitemap ul.third > li {
   display: block;
    padding: 10;
   text-align: center;    
   transition: 0.4s;
    transform-style: preserve-3d;
}

.sitemap ul.third > li > a {
    font-size: 14px;
}


.sitemap ul li a {
    text-align: center;
    color: #000;
}

    </style>
    
<%@include file="../nav.jsp"%>    
<title>사이트맵</title>

<div class="container">
<nav class="sitemap">
    <ul class="first">
      <li><a href="starters" class="mb-5">STARTERS
      <h6>사이트맵</h6></a>
        <ul class="second">
          <li><a href="#">Member</a>
            <ul class="third">
              <li><a href="starters?cmd=login">로그인</a></li>
              <li><a href="starters?cmd=registerCateg">회원가입</a></li>
              <li><a href="starters?cmd=findId">아이디/비밀번호 찾기</a></li>
            </ul>
          </li>
          <li><a href="starters?cmd=intro">Info</a>
            <ul class="third">
              <li><a href="starters?cmd=intro">인사말</a></li>
            </ul>
          </li>
          <li><a href="starters?cmd=tutoringList">Tutoring</a>
            <ul class="third">
              <li><a href="starters?cmd=tutoringList">튜터링</a></li>
            </ul>
          </li>
          <li><a href="starters?cmd=portfolioList">Portfolio</a>
            <ul class="third">
              <li><a href="starters?cmd=portfolioList">포트폴리오</a></li>
            </ul>
          </li>
          <li><a href="starters?cmd=companyList">Company</a>
            <ul class="third">
              <li><a href="starters?cmd=companyList">기업</a></li>
            </ul>
          </li>
          <li><a href="starters?cmd=notice">Community</a>
            <ul class="third">
              <li><a href="starters?cmd=notice">공지사항</a></li>
              <li><a href="starters?cmd=qna">Q&A</a></li>
              <li><a href="starters?cmd=reviewList">후기</a></li>
            </ul>
          </li>
            <c:choose>
               <c:when test="${empty name}">
               </c:when>
               <c:when test='${allMembers ==4}'>
                  <li><a href="starters?cmd=notice">Mypage</a>
                     <ul class="third">
                        <li><a href="starters?cmd=tuteeMypageList">튜터링 수업방</a></li>
                        <li><a href="starters?cmd=tutoringLike">찜 목록</a></li>
                        <li><a href="starters?cmd=tutoringInfo">튜터링 결제 내역</a></li>
                        <li><a href="starters?cmd=interviewList">면접 현황</a></li>
                        <li><a href="starters?cmd=tuteeDetailAction">회원 정보 수정</a></li>
                        <li><a href="starters?cmd=tuteeDetailAction">회원 탈퇴</a></li>
                   <li><a href="#" onclick="chatting(); return false;" id="chat">실시간톡</a></li>
                     </ul></li>
               </c:when>
               <c:when test='${allMembers ==2}'>
                  <li><a href="starters?cmd=notice">Mypage</a>
                     <ul class="third">
                        <li><a href="starters?cmd=tutorTutoringList">튜터링 등록 현황</a></li>
                        <li><a href="starters?cmd=tutorTutoringTuteeList">튜터링 신청 튜티 목록</a></li>
                        <li><a href="starters?cmd=tutorTutoringCalenderList1">튜터링 일정</a></li>
                        <li><a href="starters?cmd=tutorDetailAction">회원 정보 수정</a></li>
                        <li><a href="starters?cmd=tutorDetailAction">회원 탈퇴</a></li>
                         <li><a href="#" onclick="chatting(); return false;" id="chat">실시간톡</a></li>
                     </ul></li>
               </c:when>
               <c:when test='${allMembers ==3}'>
                  <li><a href="starters?cmd=notice">Mypage</a>
                     <ul class="third">
                        <li><a href="starters?cmd=companyLikeList">찜 목록</a></li>
                        <li><a href="starters?cmd=companyInterview">면접 현황 및 내역</a></li>
                        <li><a href="starters?cmd=companyInfoDetailAction">회원 정보 수정</a></li>
                        <li><a href="starters?cmd=companyInfoDetailAction">회원 탈퇴</a></li>
                     </ul></li>
               </c:when>
               <c:otherwise>
                  <li><a href="starters?cmd=notice">Mypage</a>
                     <ul class="third">
                        <li><a href="starters?cmd=adminMemberList">회원관리</a></li>
                        <li><a href="starters?cmd=adminTutoringList">튜터링 등록 현황</a></li>
                        <li><a href="starters?cmd=adminNotice">고객 편의</a></li>
                        <li><a href="starters?cmd=adminPortfolioList">포트폴리오 관리</a></li>
                        <li><a href="starters?cmd=allstatistic">통계</a></li>
                        <li><a href="starters?cmd=adminGetJobList">직종 편집</a></li>
                        <li><a href="starters?cmd=adminInterviewPay">면접신청가격 변경</a></li>
                     </ul></li>
               </c:otherwise>
            </c:choose>
         </ul>
          </li>
          </ul>
          </nav>

</div>
<%@include file="../footer.jsp"%>     


<script>
function chatting() {
    window.name = "parentForm";
     window.open("starters?cmd=chat",
             "chkForm", "width=800, height=500, resizable = no, scrollbars = no");    
return true;
}
</script>     