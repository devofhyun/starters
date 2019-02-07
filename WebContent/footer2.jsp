<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="assets/css/footer.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="footer">
	<div class="row footer_cont">
		<div class="col-9 info_box">
			<div class="info">
				<b>COMPANY</b>
				<ul>
					<li><a href="#">서비스 소개</a></li>
				</ul>
			</div>
			<div class="info">
				<b>POLICIES</b>
				<ul>
					<li><a href="#">이용약관</a></li>
					<li><a href="#">개인정보취급방침</a></li>
				</ul>
			</div>
			<div class="info">
				<b>SUPPORT</b>
				<ul>
					<li><a href="starters?cmd=notice"> 공지사항</a></li>
					<li><a href="starters?cmd=qna">Q&A</a></li>
					<c:choose>
						<c:when test="${empty name}">
							<li><a href="starters?cmd=sitemap">사이트맵</a></li>
						</c:when>
						<c:when test='${allMembers ==4}'>
							<li><a href="starters?cmd=sitemap">사이트맵</a></li>
						</c:when>
						<c:when test='${allMembers ==2}'>
							<li><a href="starters?cmd=sitemap">사이트맵</a></li>
						</c:when>
						<c:when test='${allMembers ==3}'>
							<li><a href="starters?cmd=sitemap">사이트맵</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="starters?cmd=sitemap">사이트맵</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>
		</div>
		<div class = "col-3 info_box2">
			<div class="info">
				<b>스타터스</b>
				<ul>
					<li>운영시간</li>
					<li>평일 10:00~18:00</li>
					<li>점심 13:00~14:00</li>
				</ul>
			</div>
		</div>
		<div class="row" style="width: 100%; padding-top: 79px">
			<div class="col-9 bt_info">(주)스타터스 | 주소 : 서울특별시 성동구 살곶이길 200, 3층 
			 <br>Copyright ⓒ2018 Starters inc, ltd. All rights reserved
			 </div>
			 <div class = "col-3 logo_box">
			 <a href = "https://ko-kr.facebook.com/"> <i class="fa fa-facebook fa-fw"></i></a>
			  <a href = "https://twitter.com/?lang=ko"> <i class="fa fa-twitter fa-fw"></i></a>
			   <a href = "https://www.instagram.com/?hl=ko">  <i class="fa fa-instagram  fa-fw"></i></a>
			 </div>
		</div>
	</div>
	
	
</div>

 