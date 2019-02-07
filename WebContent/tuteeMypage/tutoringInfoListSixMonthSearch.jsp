<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringApplyListVO" import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="assets/css/mypage/tutee/Tutee_tutoringInfo.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container content">
		<div class="board">
		<p>${applyAllCount}개의 신청내역</p>
			<table class="table interV">
			    <thead>
			      <tr>
			        <th>번호</th>
			        <th>신청일자</th>
			        <th>튜터</th>
			        <th>튜터링명</th>
			        <th>수업시작날짜</th>
			        <th>진행상황</th>
			        <th>취소/환불</th>
			      </tr>
			    </thead>
			    <tbody>
					<c:forEach items="${tutoringApplySixMontList}" var="tutoringApplyInfo">
				      <tr>
				        <td>${tutoringApplyInfo.tutoringApplyId}</td>
				        <td>${tutoringApplyInfo.applyDate}</td>
				        <td>${tutoringApplyInfo.name}</td>
				        <td><a class="tltle">${tutoringApplyInfo.tutoringTitle}</a></td>
				          <td>${tutoringApplyInfo.startDate}</td>
				        <td>${tutoringApplyInfo.progress}</td>
				        <td>
				        <c:choose>
                     <c:when test="${tutoringApplyInfo.refundDeadline == '환불 불가능'}">   
                       
                     </c:when>
                        <c:otherwise>
                          <button type="button" class="btn btn-secondary btn-sm applyIdModal"
                           data-toggle="modal"
                           id="applyIdModal ${tutoringApplyInfo.tutoringApplyId}"
                           data-target="#applyModal${tutoringApplyInfo.tutoringApplyId}"
                           onclick="j(this)" name="${tutoringApplyInfo.tutoringApplyId}">환불하기</button>
                        </c:otherwise>
                     </c:choose>
							
							<!-- 환불하기 모달 -->
						<form method="post" action="starters?cmd=applyListDeleteAction" id="delete" enctype="multipart/form-data">
							<div class="modal fade"
								id="applyModal${tutoringApplyInfo.tutoringApplyId}"
								role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											취소 및 환불
											<button type="button" class="close" data-dismiss="modal"
												onclick="realx()">&times;</button>
										</div>
										<div class="modal-body">
										<a class="modalTitle">"${tutoringApplyInfo.tutoringTitle}"</a>
											<c:choose>
												<c:when
													test="${tutoringApplyInfo.refundDeadline == '환불 불가능'}">
													<p>${tutoringApplyInfo.refundDeadline}합니다.</p>
													<div class="modal footer exit">
														<button type="button" class="btn btn-secondary btn-sm"
															data-dismiss="modal">나가기</button>
													</div>
												</c:when>

												<c:otherwise>
													<p>${tutoringApplyInfo.refundDeadline}환불가능합니다.</p>
													<div class="modal-footer refund">
														<button type="button" class="btn btn-secondary btn-sm"
															data-dismiss="modal">취소</button>
														<button type="submit" class="btn btn-sm btn-danger">환불하기</button>

														<%-- <button class="btn refund" data-toggle="modal"
														id="refundModal ${tutoringApplyInfo.tutoringApplyId}"
														data-target="#refund${tutoringApplyInfo.tutoringApplyId}">환불하기</button>
													<button type="button" class="btn btn-default"
														data-dismiss="modal">취소</button>

													<div class="modal fade"
														id="refund${tutoringApplyInfo.tutoringApplyId}"
														role="dialog">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<h4 class="modal-title">환불하기</h4>
																</div>
																<div class="modal-body">
																	<p>정말 환불하시겠습니까?</p>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">취소</button>
																	<button type="submit" class="btn btn-danger"
																		onclick="applyDelete()">환불하기</button>
																</div>
															</div>
														</div>
													</div> --%>
														<!-- 정말 환불 모달 끝 -->
													</div>
												</c:otherwise>
											</c:choose>
											<!-- bodyTable 끝 -->
										</div>
										<!-- modal-body 끝 -->
									</div>
									<!-- modal-content 끝 -->
								</div>
								<!-- modal-dialog 끝 -->
							</div>
							<!-- 환불 modal 끝 -->
							</form>
							
							</td>
				      </tr>
					</c:forEach>
			    </tbody>
			</table>
		</div>

		<!-- board 끝-->
	<!-- container 끝 -->
</div>

	<!-- pagination -->
	<div class="paging">
	<c:if test="${ applyAllCount > 0}">
		<c:if test="${startPage > pageBlock}">
			<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
				class="btn btn-secondary btn-sm w2">이전</button>
	   		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<c:choose>
				<c:when test="${currentPage == i}">
					<button id="ju" name="${i}" onclick="p(this)" class="btn btn-secondary btn-sm w1">${i}</button>
				</c:when>
				<c:otherwise>
					<button id="ju" name="${i}" onclick="p(this)" class="btn btn-secondary btn-sm w">${i}</button>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${endPage < pageCount }">
			<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
				class="btn btn-secondary btn-sm w2">다음</button>
	   		</c:if>
	</c:if>
	</div>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
/**페이징*/
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		$.ajax({
			url : "starters?cmd=tutoringInfoListSixMonthSearchPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.tutoringInfoMain").html(result);
			}
		})
	}
/**환불하기*/
	function j(button) {
		var rStr = button.name;
		console.log("rStr" + rStr);
		//	 location.href = "starters?cmd=applyNum&tutoringApplyId="+ rStr;
		$.ajax({
			url : "starters?cmd=applyNum&tutoringApplyId=" + rStr,
			success : function(result) {
				$("div.applyIdModal${tutoringApplyInfo.tutoringApplyId}").html(result);
			}
		})
	}
	

/* 	var applyDelete = function() {
		location.href = "starters?cmd=applyListDeleteAction";
	};
 */

</script>

