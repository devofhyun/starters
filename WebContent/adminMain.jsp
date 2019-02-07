<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js"></script>

<title>스타터스 관리자 : 메인</title>
<%@include file="../../nav2.jsp"%>

<link rel="stylesheet" href="assets/css/admin/Amain.css" />
<style>
.col, .col .num{
	cursor: context-menu;
}
.col img{
	cursor: pointer;
}
</style>

<div id="wrap">
	<section>
		<article>
			<h1 class="adminNow">전체 현황</h1>
			<div class="middle">
				<div class="counting-sec">
					<div class="inner-width">

						<div class="col" onclick="location.href='starters?cmd=adminMemberTuteeList'">
							<img src="https://image.flaticon.com/icons/svg/948/948256.svg">
							<div class="num">${tutee }</div>
							튜티
						</div>

						<div class="col" onclick="location.href='starters?cmd=adminMemberTutorList'">
							<img src="https://image.flaticon.com/icons/svg/27/27759.svg">
							<div class="num">${tutor }</div>
							튜터
						</div>

						<div class="col" onclick="location.href='starters?cmd=adminMemberCompanyList'">
							<img src="https://image.flaticon.com/icons/svg/753/753114.svg">
							<div class="num">${company }</div>
							기업
						</div>
					</div>
					<div class="inner-width">
						<div class="col" onclick="location.href='starters?cmd=adminTutoringList'">
							<img src="https://image.flaticon.com/icons/svg/1225/1225877.svg">
							<div class="num">${tutoring }</div>
							튜터링
						</div>

						<div class="col" onclick="location.href='starters?cmd=adminPortfolioList'">
							<img src="https://image.flaticon.com/icons/svg/942/942799.svg">
							<div class="num">${portfolio }</div>
							<%--<i class="far fa-newspaper"></i>
					<div class = "num" style="margin-top: 40px;">${portfolio }</div>--%>
							포트폴리오
						</div>

						<div class="col" onclick="location.href='starters?cmd=tutoringStatistic'">
							<img src="https://image.flaticon.com/icons/svg/1019/1019709.svg">
							<div class="num">${tutoringBuy }</div>
							튜터링 횟수
						</div>

						<div class="col" onclick="location.href='starters?cmd=interviewStatistic'">
							<img src="https://image.flaticon.com/icons/svg/509/509602.svg">
							<div class="num">${interview}</div>
							면접신청횟수
						</div>
					</div>
				</div>
			</div>
			<%@include file="../../footer.jsp"%>

		</article>
	</section>
</div>
<script>
	$(".num").counterUp({
		delay : 10,
		time : 1000
	});
</script>
