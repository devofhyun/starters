<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 폰트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../../nav2.jsp"%>
<link rel="stylesheet" href="assets/css/admin/tutoring_detail.css" />
<title>스타터스 관리자 : 포트폴리오 - ${pvo.portfolioId}</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="wrap">
<section>
	<article>	
<div class="container info">
	<br>
	<h4>포트폴리오 관리 상세</h4>
	<br>
	<div id="imgBox">
	<span>이미지를 보시려면 이미지 박스를 드래그해주세요.</span>
         <c:forEach items="${intimagevo}" var="IntImageVO" varStatus="iv">
            <c:if test="${iv.index eq 0}">
               <div class="tutoringImg">
                  <img class="TImg"
                     src="../assets/portfolioImage/${IntImageVO.getImg()}" />
                  <%--${IntImageVO.getImg()} --%>
               </div>
            </c:if>
         </c:forEach>
         <button id="resize" class="btn btn-sm btn-outline-light text-dark">되돌리기</button>
	</div>
	
	<table class="table portfolio">
			<tr>
				<th>제목</th>
				<td>${pvo.portfolioId}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${pvo.name}</td>
			</tr>
			<tr>
				<th>직종</th>
				<td>
					 <c:forEach items="${job}" var="IntJobSelectVO" varStatus="ijs">
	               			<span class="jobs">${IntJobSelectVO.getMiddleCategName()}</span>
	            	</c:forEach>
            	</td>
			</tr>
			<tr>
				<th>포트폴리오 구현방식</th>
				<td>${pvo.portfolioMethod}</td>
			</tr>
			<tr>
				<th>포트폴리오 url</th>
				<td>${pvo.portfolioUrl}</td>
			</tr>
			<tr>
				<th>생성일</th>
				<td>${pvo.uploadDate}</td>
			</tr>
	</table>
</div>


<div class="container content">
		<h4><strong>내용</strong></h4>
		<div class="plan">${pvo.portfolioText}</div>
	<button class="btn btn-sm btn-outline-secondary" onclick = "back()">목록</button>
	<button class="btn btn-sm btn-outline-danger pull-right" data-toggle="modal" data-target="#portfolioDelete">삭제하기</button>
	
</div>
	
	<!-- 모달 -->
         <form method="post" action="starters?cmd=adminPortfolioDeleteAction"name="inform">    
            <div class="modal fade" id="portfolioDelete" role="dialog">
               <div class="modal-dialog modal-dialog-centered">
                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">

                        <h4 class="modal-title">포트폴리오를을 삭제하시겠습니까?</h4>
                     </div>
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal">취소</button>
                           <button type="submit" class="btn btn-danger">삭제하기</button>

                        </div>
                  </div>
               </div>
            </div>
            </form>

<%@include file="../../footer.jsp"%>
	</article>
</section>
</div>
<script>
$(document).ready(function() {
	$('#resize').css('left', ($('#imgBox').offset().left + $('#imgBox').width() - $('#resize').outerWidth()) );
	
});

$(window).resize(function(){
	var imgBox = $('#imgBox').offset();
	console.log(imgBox.right);
	// $('#resize').css('left', (imgBox.left + $('#imgBox').width() - $('#resize').width()) );
	$('#resize').animate({
		left: (imgBox.left + $('#imgBox').width() - $('#resize').outerWidth())
	}, 300);	

});

$('#resize').click(function(){
		$('#imgBox').animate({
			height: '300'
		}, 1000);	
});

	var back = function() {
		location.href = "starters?cmd=adminPortfolioList";
	};
	

</script>