<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringApplyListVO" import="java.util.ArrayList"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="changView">
   <form action="" method="post">
      <div class="container ">
      <p>${interviewAllCount}개의신청내역</p>
			<table class="table interV">
				<thead>
				  <tr>
				    <th>면접 신청 기간</th>
				    <th>제안연봉</th>
				    <th>튜티명</th>
				    <th>포트폴리오명</th>
				    <th>면접신청결과</th>
				    <th>취소/환불</th>
				  </tr>
				</thead>
				<tbody>
					<c:forEach items="${interviewList}" var="interviewVO">
						<tr>
							<td>${interviewVO.interviewStartDate } ~ ${interviewVO.interviewEndDate }</td>
							<td>${interviewVO.interviewYearMoney }</td>
							<td>${interviewVO.memberName }</td>
							<td>${interviewVO.portfolioTitle }</td>
							<%-- <td>${interviewVO.interviewResult }</td> --%>
							<td>
							  	<c:choose>
									<c:when test='${interviewVO.interviewResult == 0}'>미응답</c:when>
									<c:when test='${interviewVO.interviewResult == 1}'>
									<button type="button" class="btn btn-outline-info btn-sm"
										data-toggle="modal"
										data-target="#sure${interviewVO.interviewId}">수락</button>
										<div class="modal fade" id="sure${interviewVO.interviewId}" role="dialog">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title">튜티 상세정보</h4>
													</div>
													<div class="modal-body" style="margin: 0 30;">
														<div class="row">
															<h5 class="col-4 text-left">포트폴리오명</h5>
															<p>${interviewVO.portfolioTitle}</p>
														</div>
														<div class="row">
															<h5 class="col-4 text-left">이름</h5>
															<p>${interviewVO.memberName}</p>
														</div>
														<div class="row">
															<h5 class="col-4 text-left">이메일</h5>
															<p>${interviewVO.email}</p>
														</div>
														<div class="row">
															<h5 class="col-4 text-left">전화번호</h5>
															<p>${interviewVO.phoneNum}</p>
														</div>
													</div>
													<div class="modal-footer">
														  <button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">닫기</button>
													</div>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test='${interviewVO.interviewResult == 2}'>거절</c:when>
									<c:when test='${interviewVO.interviewResult == 3}'>완료</c:when>
								</c:choose>							 
							 </td>
							 <td>
								 <c:choose>
										<c:when test='${interviewVO.interviewResult == 0}'>환불불가</c:when>
										<c:when test='${interviewVO.interviewResult == 1}'>환불불가</c:when>
										<c:when test='${interviewVO.interviewResult == 2}'>환불불가</c:when>
										<c:when test='${interviewVO.interviewResult == 3}'>환불불가</c:when>
								</c:choose>	
							 </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
      </div>
      <!-- container 끝 -->
   </form>
</div>

<div class="paging">

<!-- pagination -->
<c:if test="${ interviewAllCount > 0}">
	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary mr-1 w2">이전</button>
   		</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary mr-1 w1">${i}</button>
			</c:when>
			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary mr-1 w">${i}</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary w2">다음</button>
   		</c:if>
</c:if>


</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
/**페이징*/
function p(jumpBtn) {
	var rStr = jumpBtn.name;
	$.ajax({
		url : "starters?cmd=companyinterviewDateSelectPaging&pageNum=" + rStr,
		success : function(result) {
			$("div.tutoringInfoMain").html(result);
		}
	})
}
</script>
