<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="java.util.ArrayList"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form action="" method="post">
	<div class="container content">
		<div class="board">
			<p>${interviewSixMonthCount}개의 면접요청</p>
			<table class="table interV">
			    <thead>
			      <tr>
			        <th>날짜</th>
			        <th>면접일</th>
			        <th>기업명</th>
			        <th>면접정보</th>
			        <th>수락</th>
			        <th>거절</th>
			      </tr>
			    </thead>
			<c:forEach items="${interviewSixMontList}" var="interviewList">
			    <tbody> 
			    	<tr>
				        <td>${interviewList.askDate}</td>
				        <td>${interviewList.startDate } ~ ${interviewList.endDate}</td>
				        <td>${interviewList.companyName}</td>
				        <td>
				        				        <a class="interviewMoreInfo" 
					href="starters?cmd=interviewMoreInfo&num=${interviewList.interviewId}" >
				        <button type="button" class="btn btn-sm btn-secondary interviewbtn">면접정보 더보기</button>
				        </a> 
						</td><!-- 면접정보 더보기 -->
		
				        <td>
							<c:choose>
								<c:when test="${interviewList.interviewResult == 0}">
									<button type="button" class="btn btn-secondary btn-sm"
										data-toggle="modal"
										data-target="#okInterview${interviewList.interviewId}"
										id="ok ${interviewList.interviewId}">수락</button>
									<div class="modal fade"
										id="okInterview${interviewList.interviewId}">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="myModalLabel">면접신청</h4>
													<button type="button" class="close" data-dismiss="modal"
												onclick="realx()">&times;</button>
												</div>
												<div class="modal-body">면접을 수락하시겠습니까?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-sm btn-outline-danger"
														data-dismiss="modal">취소</button>
													<button type="button" class="btn  btn-sm btn-outline-info"
														data-dismiss="modal" name="${interviewList.interviewId}"
														onclick="g(this)">수락</button>
												</div>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${interviewList.interviewResult == 1}">
									<button type="button" class="btn btn-sm btn-outline-info"
										data-toggle="modal"
										data-target="#okInterview${interviewList.interviewId}"
										id="ok ${interviewList.interviewId}">수락</button>
									<div class="modal fade"
										id="okInterview${interviewList.interviewId}">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="myModalLabel">면접신청</h4>
													<button type="button" class="close" data-dismiss="modal"
												onclick="realx()">&times;</button>
												</div>
												<div class="modal-body">이미 수락한 면접입니다.</div>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-sm btn-outline-danger"
										data-toggle="modal"
										data-target="#okInterview${interviewList.interviewId}"
										id="ok ${interviewList.interviewId}" disabled>수락</button>
								</c:otherwise>
							</c:choose>


				        </td>
				        <td>

							<c:choose>
								<c:when test="${interviewList.interviewResult == 0}">
									<button type="button" class="btn btn-secondary btn-sm"
										data-toggle="modal"
										data-target="#nonterview${interviewList.interviewId}"
										id="no ${interviewList.interviewId}">거절</button>
									<div class="modal fade"
										id="nonterview${interviewList.interviewId}">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="myModalLabel">면접신청</h4>
													<button type="button" class="close" data-dismiss="modal"
												onclick="realx()">&times;</button>
												</div>
												<div class="modal-body">
													면접을 거절하시겠습니까?<br> 거절하시면 변경하실 수 없습니다.
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-sm btn-outline-info"
														data-dismiss="modal">취소</button>
													<button type="button" class="btn btn-sm btn-outline-danger"
														data-dismiss="modal" name="${interviewList.interviewId}"
														onclick="k(this)">거절</button>
												</div>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${interviewList.interviewResult == 2}">
									<button type="button" class="btn btn-secondary btn-sm"
										data-toggle="modal"
										data-target="#nonterview${interviewList.interviewId}"
										id="no ${interviewList.interviewId}">거절</button>
									<div class="modal fade"
										id="nonterview${interviewList.interviewId}">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="myModalLabel">면접신청</h4>
													<button type="button" class="close" data-dismiss="modal"
												onclick="realx()">&times;</button>
												</div>
												<div class="modal-body">이미 거절한 면접입니다.</div>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-secondary btn-sm"
										data-toggle="modal"
										data-target="#nonterview${interviewList.interviewId}"
										id="no ${interviewList.interviewId}" disabled>거절</button>
								</c:otherwise>
							</c:choose>
				        
				        </td>
			    	</tr>
			    </tbody>
			</c:forEach>
			    </table>

		</div>
	</div>
</form>


<div class="paging">
<!-- pagination -->
<c:if test="${ interviewSixMonthCount > 0}">
	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-secondary w2">이전</button>
   		</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-secondary w1">${i}</button>
			</c:when>
			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-secondary w">${i}</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-secondary w2">다음</button>
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
			url : "starters?cmd=interviewInfoListSixMonthSearchPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.interviewMain").html(result);
			}
		})
	}
	/**면접정보 더보기*/
	function g(button) {
		var rStr = button.name;
	//	console.log("rStr" + rStr);
		$.ajax({
			url : "starters?cmd=okInterview&interviewId=" + rStr,
			success : function(result) {
				$("div.interviewInfo").html(result);
				location.reload();
			}
		})
	}
	function k(button) {
		var rStr = button.name;
	//	console.log("rStr" + rStr);
		$.ajax({
			url : "starters?cmd=noInterview&interviewId=" + rStr,
			success : function(result) {
				$("div.interviewInfo").html(result);
				location.reload();
			}
		})
	}
	/**기업 정보 더보기 이미지*/
	function a(button) {
		var buttonName = button.name;
	//	console.log("buttonName" + buttonName);
		//location.href = "starters?cmd=companyListModal&companyId=" + buttonName
		$.ajax({
			url : "starters?cmd=moreInfoCompany&companyId="+ buttonName,
			success : function(result) {
			$(".mainimg[companyIdImg = '" + buttonName + "']").html(result);
			}
		});
	}
</script>