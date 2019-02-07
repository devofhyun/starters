<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.ReviewDAO"
	import="com.starters.*" import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.bg-dark {
	background-color: #5a6268 !important;
}
.navbar-dark .navbar-nav .nav-link {
    color: #fff;
    font-size: 1.8vh;
}
.navbar-nav>li>a {
    text-shadow: 0 1px 0 rgba(255,255,255,.25);
}
</style>
<h4>이달의 베스트 후기<i class="fas fa-thumbs-up"></i></h4>
<form action="" method="post">
<c:forEach items="${best}" var="bestReview">
    <div class = "bestreview">
        <div class = "col-md-6">
            <div class="reviewImage" reviewIdImg="${bestReview.reviewId}" id="${bestReview.reviewId}"></div>
        </div>
        <div class = "col-md-6 besttitle">
            <ul class = " best">
                <h6>튜터링 이름:</h6>
                <li>${bestReview.tutoringTitle}</li>
            </ul>
            <ul class = " best">
                <h6>튜터 이름:</h6>
                <li>${bestReview.tutorName}</li>
            </ul> 
        <div class = "scrollBlind" id = "scroll"> 
            <pre-wrap>
            <i class="fas fa-comment"></i>
           		${bestReview.reviewContent}
            </pre>    
        </div>  <!-- scrollBlind end -->
        </div>  <!-- besttitle end -->
    </div> <!--bestreview end -->
    </c:forEach>
</form>

<div class="boardlist">
	<c:forEach items="${list}" var="reviewVO">
		<ul>
			<li>
				<div class="preimg">
					<p class="reviewpre">
					<div class="reviewImage" reviewIdImg="${reviewVO.reviewId}"
						id="${reviewVO.reviewId}"></div>
					</p>
				</div>
				<div class="reviewinfo">
					<div class="reviewtitle">
						<dl>
							<dt>제목:</dt>
							<dd>
								<a href="#"> ${reviewVO.reviewTitle} </a>
							</dd>
						</dl>
					</div>
					<div class="tutoringInfo">
						<dl>
							<dt>튜터링명</dt>
							<dd>${reviewVO.tutoringTitle}</dd>
							<dt>튜터명</dt>
							<dd>${reviewVO.tutorName}</dd>
						</dl>
					</div>
					<div class="writer">
						<strong>${reviewVO.reviewId}</strong>
						<div class="personinfo">
							<dl>
								<dt>작성자</dt>
							 	<dd>${reviewVO.memberId}</dd>
							</dl>
							<dl>
								<dt>좋아요</dt>
								<dd class = "favoriteLikeCount" reviewdetailnum="${reviewVO.reviewId}"> ${reviewVO.likeCount}</dd> 
							</dl>
							<dl>
								<dt>조회수</dt>
								<dd class="reviewCounts" reviewId="${reviewVO.reviewId}">${reviewVO.reviewCount}</dd>
							</dl>
							<dl>
								<dt>작성일</dt>
								<dd>${reviewVO.reviewDate}</dd>
							</dl>
						</div>
						<div id="previewsid" class="contents">
							<a href="#" class="previews"><i class="fas fa-comment"></i> <c:choose>
									<c:when test="${fn:length(reviewVO.reviewContent) > 14}">
										<c:out value="${fn:substring(reviewVO.reviewContent,0,13)}" />....
                                </c:when>
									<c:otherwise>
										<c:out value="${reviewVO.reviewContent}" />
									</c:otherwise>
								</c:choose> </a>
						</div>
								
						<button type="button" class="btn btn-info btn-lg reviewIdModal"
							data-toggle="modal" id="reviewIdModal ${reviewVO.reviewId}"
							data-target="#myModal${reviewVO.reviewId}" onclick="k(this)"
							name="${reviewVO.reviewId}">상세보기</button>
						<!-- 상세모달 시작 -->
						<div class="modal fade" id="myModal${reviewVO.reviewId}"
							role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										후기게시판 상세
										<button type="button" class="close" data-dismiss="modal"
											onclick="realx()">&times;</button>
									</div>
									<div class="modal-body">
										<div class="table" id="reviewInfo">
											<table>
												<colgroup>
													<col class="col1" />
													<col class="col2" />
													<col class="col3" />
													<col class="col4" />
												</colgroup>
												<tbody>
													<tr>
														<th scope="row">리뷰번호</th>
														<td>${reviewVO.reviewId}</td>
													</tr>
													<tr>
														<th scope="row">이미지</th>
														<td>
															<div class="reviewImage"
																reviewIdImg="${reviewVO.reviewId}"
																id="${reviewVO.reviewId}"></div>
															</p>
														</td>
													</tr>
													<tr>
														<th scope="row">제목</th>
														<td>${reviewVO.reviewTitle}</td>
													</tr>
													<tr>
														<th scope="row">튜터링명</th>
														<td>${reviewVO.tutoringTitle}</td>
														<th scope="row">튜터명</th>
														<td>${reviewVO.tutorName}</td>
													</tr>
													<tr>
														<th scope="row">작성자</th>
														<td>${reviewVO.memberId}</td>
														<th scope="row">조회수</th>
														<td class="reviewCounts">${reviewVO.reviewCount}</td>
														<th scope="row">작성일</th>
														<td>${reviewVO.reviewDate}</td>
													</tr>
													<tr>
														<th scope="row">내용</th>
														<td>${reviewVO.reviewContent}</td>
													</tr>
													<tr>
														<th scope="row">좋아요</th>
														
														  <td colspan="5">                   
                                                         <span>좋아요 ${reviewVO.likeCount} 명</span></span>
                                                </td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<!-- modal-body 끝 -->
									<c:if test="${id == reviewVO.memberId}">
										<div class="modal-footer">
											<!-- 수정 삭제 버튼-->
											<button class="btn modify" data-toggle="modal"
												id="reviewModal${reviewVO.reviewId}"
												data-target="#reviewModify${reviewVO.reviewId}">수정하기</button>

											<!-- 삭제 삭제 버튼-->
											<button class="btn remove" data-toggle="modal"
												id="${reviewVO.reviewId}"
												data-target="#reviewDelete${reviewVO.reviewId}">삭제하기</button>

											<!-- 수정모달 시작 -->
											<form method="post" action="starters?cmd=adminReviewModified"
												id="upForm" enctype="multipart/form-data">
												<div class="modal fade modify frm"
													id="reviewModify${reviewVO.reviewId}" role="dialog">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<h4 class="modal-title">리뷰 수정</h4>
															</div>
															<div class="modal-body">
																<table class="table">
																	<tr>
																		<td>제목</td>
																		<td><input type="text" name="reviewTitle"
																			value="${reviewVO.reviewTitle}"></td>
																	</tr>
																	<tr>
																		<td>튜터링명</td>
																		<td><select name="selectBox2" id="selectBox2"
																			class="select_02">
																				<c:forEach var="ModifyTutoringTitleList"
																					items="${ModifyTutoringTitleList}">
																					<option
																						value="${ModifyTutoringTitleList.tutoringApplyId}">${ModifyTutoringTitleList.tutoringName}</option>
																				</c:forEach>
																		</select></td>
																	</tr>
																	<tr>
																		<td>이미지등록</td>
																		<td>
																			<div class="col-md-5">
																				<label id="upload"> <input type="file"
																					name="reviewImage">
																				</label>
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td>내용</td>
																		<td><textarea rows="10" name="reviewContent">${reviewVO.reviewContent}</textarea></td>
																	</tr>
																</table>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">취소</button>
																<button type="submit" class="btn btn-danger"
																	onclick="realModify()">수정하기</button>
															</div>
														</div>
													</div>
												</div>
											</form>
											<!-- 수정모달 끝 -->
											<!-- 삭제모달 시작 -->
											<div class="modal fade" id="reviewDelete${reviewVO.reviewId}"
												role="dialog">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<h4 class="modal-title">리뷰를 삭제하시겠습니까?</h4>
														</div>
														<!-- <form method="post"
                                          action="starters?cmd=reviewDeleteAction" name="inform"> -->
														<div class="modal-body">
															<button type="button" class="btn btn-default"
																data-dismiss="modal">취소</button>
															<button type="submit" class="btn btn-danger"
																onclick="realDelete()">삭제하기</button>
														</div>
														<!-- </form> -->
													</div>
												</div>
											</div>
										</div>
									</c:if>
									<!-- 삭제모달 끝 -->
								</div>
								<!-- modal-content 끝 -->
							</div>
							<!-- modal-dialog 끝 -->
						</div>
						<!-- 상세 모달 끝 -->
					</div>
					<!-- writer 끝 -->
				</div> <!-- reviewinfo 끝 -->
			</li>
		</ul>
	</c:forEach>
</div>
<!-- boardlist end1_건들ㄴㄴ -->

<!-- pagination -->
<c:if test="${ reviewAllCount > 0}">
	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="w2">이전</button> &nbsp;
   </c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="w1">${i}</button>
			</c:when>
			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="w">${i}</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="w2">다음</button> &nbsp;
   </c:if>
</c:if>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
/**후기 리스트 사진 가져오기*/
var list1 = new Array();
<c:forEach items="${list}" var="item1">
list1.push("${item1.reviewId}");
var reviewListId = ${item1.reviewId};
//console.log("reviewListId" + reviewListId);
$.ajax({
	data : list1,
	url : "starters?cmd=adminReviewListImage&reviewListNum=" + reviewListId,
	async : false,
	type : "POST",
	success : function(result) {
	//	console.log("result" + result);
	//	console.log("reviewListId" + reviewListId);
		$(".reviewImage[reviewIdImg = '" + reviewListId + "']").html(result);
	}
});
</c:forEach>

/**베스트 후기 사진 가져오기*/
var list2 = new Array();
<c:forEach items="${best}" var="item2">
list2.push("${item2.reviewId}");
var reviewListId = ${item2.reviewId};
//console.log("reviewListId" + reviewListId);
$.ajax({
	data : list2,
	url : "starters?cmd=adminReviewListImage&reviewListNum=" + reviewListId,
	async : false,
	type : "POST",
	success : function(result) {
	//	console.log("result" + result);
	//	console.log("reviewListId" + reviewListId);
		$(".reviewImage[reviewIdImg = '" + reviewListId + "']").html(result);
	}
});
</c:forEach>


/**페이징*/
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		$.ajax({
			url : "starters?cmd=adminReviewListPagingAction&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})
	}
/**조회수*/
	function k(button) {
		var rStr = button.name;
		//console.log(rStr);
		//   location.href = "starters?cmd=reviewNum&reviewid="+ rStr;
		$.ajax({
			url : "starters?cmd=adminReviewNum&reviewid=" + rStr,
			success : function(result) {
				$("dd.reviewCounts[reviewid='" + rStr + "']").html(result);
			}
		})
	}

	/**삭제하기*/
	var realDelete = function() {
		location.href = "starters?cmd=adminReviewDeleteAction";
	};

	/**좋아요*/
   /**좋아요*/
    $("span.favorite").click(function() {
      var reviewDetailNum = $(this).attr("reviewdetailnum");              
               $.ajax({
                  url : "starters?cmd=adminReviewLikeCount&reviewDetailNum="+ reviewDetailNum,
                  async : false,
                  type : "POST",
                  success : function(result) {
                     $(".favoriteLikeCount[reviewdetailnum = '"+ reviewDetailNum + "']").html(result);
                     //$(".favoriteLikeCount[reviewdetailnum = '" + reviewDetailNum + "']").html(result);
                  }
               });
            });

</script>