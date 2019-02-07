<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 폰트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../nav.jsp"%>
<link rel="stylesheet" href="assets/css/tutoring/tutoring_detail.css" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>스타터스 : 튜터링 - ${tvo2.title}</title>

<c:set var="today" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="now" type="date" value="${today}" pattern="YYYY-MM-dd"/>

<div class="container info">
	<div class="row ">
		<div class="col-md-6 TimgD">
			<c:forEach items="${images}" var="IntImageVO" varStatus="iv">
				<c:if test="${iv.index eq 0}">
				<div class="tutoringImg">
					<img class="TImg"
						src="../assets/tutoringImg/${IntImageVO.getImg()}"
						 />
					<%--${IntImageVO.getImg()} --%>
				</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="col-md-6 con">
			<h2><strong>${tvo2.title}</strong></h2>
			<h4>${tvo2.subtitle}</h4>
			
			<div class="row notitle">
			
			<ul class="tutorname">
				<li>튜터이름</li>
				<li>${tvo2.tutorName}[${tvo2.tutorId}]</li>
			</ul>
			<ul class="job">
				<li>분류</li>
				<c:forEach items="${job}" var="IntJobSelectVO" varStatus="ijs">
						<li>${IntJobSelectVO.getMiddleCategName()} </li>
				</c:forEach>
			</ul>
			<ul class="career">
				<li>경력</li>
				<li>${tvo2.career} 년</li>
			</ul>
				
			<ul class="date">
				<li>기간</li>
				<li>${tvo2.startDate} ~ ${tvo2.endDate}</li>
			</ul>
				<div id="Region1">
					<div class="day">
						<li>요일</li>
						<div id="Week1">
							<ul class="select">
								<li class="off" id="wek" off=1 value="월">
								<input type="hidden" name="daySelect1" value="월"><a>월</a></li>
								<li class="off" id="wek" off=2 value="화"><input
									type="hidden" name="daySelect2" value="화"><a>화</a></li>
								<li class="off" id="wek" off=3 value="수"><input
									type="hidden" name="daySelect3" value="수"><a>수</a></li>
								<li class="off" id="wek" off=4 value="목"><input
									type="hidden" name="daySelect4" value="목"><a>목</a></li>
								<li class="off" id="wek" off=5 value="금"><input
									type="hidden" name="daySelect5" value="금"><a>금</a></li>
								<li class="off" id="wek" off=6 value="토"><input
									type="hidden" name="daySelect6" value="토"><a>토</a></li>
								<li class="off" id="wek" off=7 value="일"><input
									type="hidden" name="daySelect7" value="일"><a>일</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<ul class="time">
					<c:choose>
					<c:when test = "${timeCount>0}">
					<c:forEach items="${times}" var="TutoringTimeVO2" varStatus="ttv">
						<c:if test="${ttv.index eq 0}">
						<li>시간1</li>
							<div>
								<input type="radio" name="firstTime" id="first" value = "${TutoringTimeVO2.getTimes()}">
								<label for="first">${TutoringTimeVO2.getTimes()}
								</label>
							</div>
							<%--${IntImageVO.getImg()} --%>
						</c:if>
						<c:if test="${ttv.index eq 1}">
						<li>시간2</li>
							<div>
								<input type="radio" name="firstTime" id="second" value = "${TutoringTimeVO2.getTimes()}">
								<label for="second">${TutoringTimeVO2.getTimes()}
								</label>
							</div>
						</c:if>
						<c:if test="${ttv.index eq 2}">
						<li>시간3</li>
							<div>
								<input type="radio" name="firstTime" id="third" value = "${TutoringTimeVO2.getTimes()}">
								<label for="third">${TutoringTimeVO2.getTimes()}
								</label>
							</div>
						</c:if>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<li>시간</li>
						<!-- 튜터링 시작날짜가 현재날짜보다 늦을때 -->
						<label for="first">튜터링 신청이 마감되었습니다.</label>
					</c:otherwise>
					</c:choose>
				</ul>
				<ul class="money">
					<li>금액</li>
					<li> <fmt:formatNumber value="${tvo2.price}" pattern="#,###" />원</li>
				</ul>
				
				
				
			<div class="btnzip">
			<c:if test = "${id == null}">
			로그인을 해주세요.
			</c:if>
				<c:if test="${tuteemembers !=4 && id != null && id != tvo3.tutorId}">
				<!-- 튜티가 아니고 비회원도 아니고 튜터도 아닌 사람 -->
				<div class="content text-center">
				<h5>튜터링을 신청할 수 없습니다.</h5>
				</div>
				</c:if>
		         
				<c:if test="${tuteemembers==4 && id != null}">
				<!-- 튜티이고 아이디가 낫널이 아닌사람이다 -->
				
				<c:choose>
				<c:when test = "${now > tvo2.startDate}">
				튜터링 신청 마감되었습니다. 다음 수업을 기다려주세요.
				</c:when>
				
				<c:when test = "${timeCount>0}">
				
					<!--신청하기 튜티 -->
				<button class="btn edit" onclick="tutoringPay(this)" name = "${tvo2.tutoringId}">신청하기</button>


					<!-- 찜하기 버튼 -->
					<c:choose>
						<c:when test="${tutoringLikeCount==1}">
							<button class="btn btn-outline-info wish" onClick="alert('이미 찜한 튜터링입니다.');">찜하기</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-outline-info wish" data-toggle="modal"
								id="wishListModal ${tvo2.tutoringId}"
								data-target="#wishModal${tvo2.tutoringId}" onclick="u(this)"
								name="wishTutoring${tvo2.tutoringId}">찜하기</button>
						</c:otherwise>
					</c:choose>
					
					
               <!-- 찜하기를 위한 모달 -->
               <form method="post"
                  action="starters?cmd=tutoringlikeRegisterAction" id="tutoringWish">
                  <div class="modal fade" id="wishModal${tvo2.tutoringId}"
                     role="dialog">
                     <div class="modal-dialog">
                        <div class="modal-content">
                           <div class="modal-header">
                              <h4 class="modal-title">찜하기</h4>
                           </div>
                           <div class="modal-body">
                              <p>${tvo2.title}찜하시겠습니까?</p>
                           </div>
                           <div class="modal-footer">
                              <button type="submit" class="btn btn-outline-info">찜하기</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </form>
               <!-- 찜하기 모달 끝 -->
               </c:when>
				<c:otherwise>
				
				<!-- 찜하기 -->
				<button class="btn btn-outline-secondary edit" onClick="alert('튜터링 신청이 마감되었습니다.');">신청하기</button>

				
					
					<c:choose>
						<c:when test="${tutoringLikeCount==1}">
						<!-- 이미 찜했을 때 --><!-- 찜하기 버튼 -->
							<button class="btn btn-outline-info remove" onClick="alert('이미 찜한 튜터링입니다.');">찜하기</button>
						</c:when>
						<c:otherwise>
						<!-- 찜 안했을 때 모달창 띄워짐-->
							<button class="btn btn-outline-info wish" data-toggle="modal"
								id="wishListModal ${tvo2.tutoringId}"
								data-target="#wishModal${tvo2.tutoringId}" onclick="u(this)"
								name="wishTutoring${tvo2.tutoringId}">찜하기</button>
						</c:otherwise>
					</c:choose>


					<!-- 찜하기를 위한 모달 -->
               <form method="post" action="starters?cmd=tutoringlikeRegisterAction" id="tutoringWish">
                  <div class="modal fade" id="wishModal${tvo2.tutoringId}"
                     role="dialog">
                     <div class="modal-dialog">
                        <div class="modal-content">
                           <div class="modal-header">
                              <h4 class="modal-title">찜하기</h4>
                           </div>
                           <div class="modal-body">
                              <p>${tvo2.title}찜하시겠습니까?</p>
                           </div>
                           <div class="modal-footer">
                              <button type="submit" class="btn btn-outline-info">찜하기</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </form>
               <!-- 찜하기 모달 끝 -->
				</c:otherwise>
				</c:choose>
				
				</c:if>
				
				<c:if test="${id == tvo3.tutorId}">
				<!-- 튜터일때 -->
					<button class="btn btn-outline-secondary edit" onclick="edit()">수정하기</button>
					<c:if test="${tutoringApply !=0}">
					<!-- 튜터인데 진행중인 튜터링일때 -->
						<button type="submit" class="btn btn-outline-danger remove" onClick="alert('이미 진행중인 튜터링입니다.');">진행중인 튜터링</button>
					</c:if>
					<c:if test="${tutoringApply ==0}">
					<!-- 튜터인데 아무도 신청 안했을 떄 -->
						<button class="btn btn-outline-danger remove" data-toggle="modal"
							data-target="#tutoringDelete">삭제하기</button>
					</c:if>
				</c:if>
				<!-- Modal -->
	
				<div class="modal fade" id="tutoringDelete" role="dialog">
					<div class="modal-dialog">
	
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
	
								<h4 class="modal-title">튜터링을 삭제하시겠습니까?</h4>
							</div>
							<form method="post" action="starters?cmd=tutoringDeleteAction"
								name="inform">
								<!-- <div class="modal-body"><li>비밀번호 확인</li> <input type="password" name="passwd"></div> -->
								<div class="modal-footer">
									<button type="button" class="btn btn-outline-secondary"
										data-dismiss="modal">취소</button>
									<button type="submit" class="btn btn-outline-danger remove">삭제하기</button>
								</div>
							</form>
	
						</div>
	
					</div>
				</div>
				
				
				
	
	
	
			</div>
			</div>

		</div>
	</div>
</div>


<div class="container content">
	<div class="col-md-12">
		<h4><strong>튜터링 내용</strong></h4>
		<div class="plan"><p style="word-break: break-all;">${tvo2.contents}</p></div>
	</div>
<div class="text-right mb-5 mx-3">
<button class="btn btn-sm btn-outline-secondary" onclick = "back()">목록</button>
</div>
</div>


<%@include file="../footer.jsp"%>
</body>


<script>

var off = $("ul.select li.off");
// console.log(off);
var selected = "";
var rStr = [];
	for (var i = 0; i < off.length; i++) {
		selected = $(off[i]).attr("value"); // 요일
		//console.log(selected);
		var days2 = new Array();
		days2[0] = "${days[0].day}";
		days2[1] = "${days[1].day}";
		days2[2] = "${days[2].day}";
		days2[3] = "${days[3].day}";
		days2[4] = "${days[4].day}";
		days2[5] = "${days[5].day}";
		days2[6] = "${days[6].day}";
		
		for(var k = 0; k <7; k++){
			// console.log(days2[k]);
			if (selected == days2[k]){
				var realStr = days2[k];
				$(".off[value ='" + realStr + "']").addClass('on');
			}
		}
		
		//days2 = "${days}";
		//console.log(days2.length);
		
		//  var dayReal = "";
		//	console.log("rStr.length"+rStr.length);
		//	for (var k = 0; k < 9; k++){
		//		dayReal = rStr[k].day;
		//		console.log("dayReal"+dayReal);
		//	}
		
		//var rStr1 = "${days[0].day}";
		//var rStr2 = "${days[1].day}";
		//var rStr3 = "${days[2].day}";
		//var rStr4 = "${days[3].day}";
		//var rStr5 = "${days[4].day}";
		//var rStr6 = "${days[5].day}";
		//var rStr7 = "${days[6].day}";
	}

	var edit = function() {
		location.href = "starters?cmd=tutoringModify";
	};
	
	var back = function() {
		location.href = "starters?cmd=tutoringList";
	};
	

	
	
	function tutoringPay(button) {
		var rStr = button.name;
		var time = "";
		if ($('input[name=firstTime]:checked').length == 0) {
			alert("시간을 선택해주세요.");
			return false;
		}
		time += $('input[name="firstTime"]:checked').attr("value");
		// $("#timeSelect").val(time);
		//console.log(time);
		location.href = "starters?cmd=tutoringApply&tutoringId="+rStr + "&time="+time;
	}

</script>