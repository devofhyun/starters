<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.starters.ReviewDAO"
   import="com.starters.*" import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.fa-heart:before {
    font-family: fontawesome;
    content: '\f004';
    font-style: normal;
    border: 1px solid #eee;
    padding: 15;
    border-radius: 50%;
    background-color: #fff;
	color: #ddd;
}

.fa-heart.like:before {
	color: red;
}
</style>


   <div class="container boardlist">
   <div class="row review-Card">
      <c:forEach items="${list}" var="reviewVO">
      
      <div class="col-md-12 col-lg-6 col-sm-12">
      <div class="row reviewcard">
          <div class="card-img-top reviewImage col-md-6 col-lg-6 col-sm-6" reviewIdImg="${reviewVO.reviewId}"
                     id="${reviewVO.reviewId}"></div>
          <div class="card-body col-md-6 col-lg-6 col-sm-6">
            <h4 class="card-title">${reviewVO.reviewTitle}</h4>
            <p class="card-text title"><strong>튜터링 제목  :</strong>${reviewVO.tutoringTitle}</p>
            <p class="card-text"><strong>튜터 이름  : </strong>${reviewVO.tutorName}</p>
            <div class="card-text">
                           <dl class="col-md-6 col-sm-6 col-xs-6">
                           <dt>작성자</dt>
                            <dd>${reviewVO.memberId}</dd>
                        </dl>
                        <dl class="col-md-6 col-sm-6 col-xs-6">
                           <dt>작성일</dt>
                           <dd>${reviewVO.reviewDate}</dd>
                        </dl>
                        <dl class="col-md-6 col-sm-6 col-xs-6">
                           <dt>조회수</dt>
                           <dd class="reviewCounts" reviewId="${reviewVO.reviewId}">${reviewVO.reviewCount}</dd>
                        </dl>
                        <dl class="col-md-6 col-sm-6 col-xs-6">
                           <dt>좋아요</dt>
                           <dd class = "favoriteLikeCount" reviewdetailnum="${reviewVO.reviewId}"> ${reviewVO.likeCount}</dd> 
                        </dl>
            </div>
            <button type="button" class="btn btn-block btn-ouline-info btn-sm reviewIdModal"
                        data-toggle="modal" id="reviewIdModal ${reviewVO.reviewId}"
                        data-target="#myModal${reviewVO.reviewId}" onclick="k(this)"
                        name="${reviewVO.reviewId}">상세보기</button>
                        
                        <!-- 상세모달 시작 -->
                     <div class="modal fade" id="myModal${reviewVO.reviewId}"
                        role="dialog">
                        <div class="modal-dialog modal-dialog-centered">
                           <!-- Modal content-->
                           <div class="modal-content">
                              <div class="modal-header">
                                 <h4>후기 상세 - No. ${reviewVO.reviewId}</h4>
                                 <button type="button" class="close" data-dismiss="modal"
                                    onclick="realx()">&times;</button>
                              </div>
                              <div class="modal-body">
                              
                              <!-- 상세 수정 -->
                              <div>
	                              <div class="modalImg">
		                              <div class="reviewImage" reviewIdImg="${reviewVO.reviewId}"  id="${reviewVO.reviewId}">
		                              </div>
		                             </div>
	                              <div class="row p-0 my-2">
		                              <div class="col-lg-3 col-md-3 col-sm-3 col-12 title">
		                              	<span class="font-weight-bold">제목</span>
		                              </div>
		                              <div class="col-lg-9 col-md-9 col-sm-12 col-12 ">
		                              	<span>${reviewVO.reviewTitle}</span>
		                              </div>
		                           </div>
	                              <div class="row p-0 my-2 enter">
		                              <div class="col-lg-2 col-md-2 col-sm-3 col-12 title">
		                              	<span class="font-weight-bold">튜터링 제목</span>
		                              </div>
		                              <div class="col-lg-4 col-md-4 col-sm-12 col-12">
		                              	<span>${reviewVO.tutoringTitle}</span>
		                              </div>
		                              <div class="col-lg-2 col-md-2 col-sm-3 col-12 title txt">
		                              	<span class="font-weight-bold">튜터</span>
		                              </div>
		                              <div class="col-lg-4 col-md-4 col-sm-12 col-12 mr-auto">
		                              	<span>${reviewVO.tutorName}</span>
		                              </div>
	                              </div>
	                              <div class="row p-0 my-2 enter">
		                              <div class="col-lg-2 col-md-2 col-sm-3 col-12 title">
		                              	<span class="font-weight-bold">튜티</span>
		                              </div>
		                              <div class="col-lg-4 col-md-4 col-sm-3 col-12">
		                              	<span>${reviewVO.memberId}</span>
		                              </div>
		                              <div class="col-lg-2 col-md-2 col-sm-3 col-12 title txt">
		                              	<span class="font-weight-bold">조회수</span>
		                              </div>
		                              <div class="col-lg-4 col-md-4 col-sm-3 col-12 mr-auto">
		                              	<span class="reviewCounts" reviewId="${reviewVO.reviewId}">${reviewVO.reviewCount}</span>
		                              </div>
	                              </div>
	                              <div class="row p-0 my-2 enter">
		                              <div class="col-lg-2 col-md-3 col-sm-3 col-12 title">
		                              	<span class="font-weight-bold">후기</span>
		                              </div>
		                              <div class="col-lg-10 col-md-12 col-sm-12 col-12">
		                              	<span>${reviewVO.reviewContent}</span>
		                              </div>
	                              </div>
	                              <div class="row p-0 my-2 enter">
		                              <div class="col-lg-2 col-md-2 col-sm-3 col-12 title">
		                              	<span class="font-weight-bold">좋아요</span>
		                              </div>
		                              <div class="col-lg-4 col-md-4 col-sm-3 col-12 likes py-3">
	                                             <c:choose>
	                                                   <c:when test="${reviewVO.likeAllCount == 0 && id != reviewVO.memberId}">
	                                                      <span class="favorite" style="cursor: pointer; padding-right: 5px;"
	                                                         reviewdetailnum="${reviewVO.reviewId}" name = "${reviewVO.reviewId}">
	                                                         <i id="likebtn" class="far fa-heart"></i>
	                                                         <span id="likespan">${reviewVO.likeCount}</span></span>
	                                                   </c:when>
	                                                   <c:when test="${id == reviewVO.memberId}">
	                                                      <span class="favorites" style="cursor: pointer; padding-right: 5px;" onclick = "alert('작성자는 좋아요를 하실 수 없습니다.');" name = "${reviewVO.reviewId}">
	                                                      <i id="likebtn" class="far fa-heart"></i>
	                                                         <span id="likespan">${reviewVO.likeCount}</span></span>
	                                                   </c:when>
	                                                   <c:otherwise>
	                                                      <span class="favorite" style="cursor: pointer; padding-right: 5px;"
	                                                         reviewdetailnum="${reviewVO.reviewId}" name = "${reviewVO.reviewId}">
	                                                         <i id="likebtn" class="fas fa-heart like"></i>
	                                                         <span id="likespan">${reviewVO.likeCount}</span></span>
	                                                   </c:otherwise>
	                                                </c:choose> 
		                              </div>
		                              <div class="col-lg-2 col-md-2 col-sm-3 col-12 title txt">
		                              	<span class="font-weight-bold">작성일</span>
		                              </div>
		                              <div class="col-lg-4 col-md-4 col-sm-3 col-12 mr-auto">
		                              	<span>${reviewVO.reviewDate}</span>
		                              </div>
	                              </div>
	                              </div>
                              </div>
                              
                              
                              
                              <!-- modal-body 끝 -->
                              <c:if test="${id == reviewVO.memberId}">
                                 <div class="modal-footer">
                                    <!-- 수정 삭제 버튼-->
                                    <button class="btn  btn-sm btn-outline-info modify" data-toggle="modal"
                                       id="reviewModal${reviewVO.reviewId}"
                                       data-target="#reviewModify${reviewVO.reviewId}">수정하기</button>
   
                                    <!-- 삭제 삭제 버튼-->
                                    <button class="btn btn-sm btn-outline-danger remove" data-toggle="modal"
                                       id="${reviewVO.reviewId}"
                                       data-target="#reviewDelete${reviewVO.reviewId}">삭제하기</button>
   
                                    <!-- 수정모달 시작 -->
                                    <form method="post" action="starters?cmd=reviewModified"
                                       id="upForm" enctype="multipart/form-data">
                                       <div class="modal fade modify frm"
                                          id="reviewModify${reviewVO.reviewId}" role="dialog">
                                          <div class="modal-dialog modal-dialog-centered">
                                             <div class="modal-content">
                                                <div class="modal-header">
                                                   <h4 class="modal-title">리뷰 수정</h4>
                                                   <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                <div class="modal-body">
                                                   <table class="table reviewRegister">
                                                      <tr>
                                                         <th>제목</th>
                                                         <td>${reviewVO.reviewTitle}</td>
                                                      </tr>
                                                      <tr>
                                                         <th>튜터링명</th>
                                                         <td>
                                                <input type = "hidden"  name = selectBox2 value = "${reviewVO.tutoringApplyId}">${reviewVO.tutoringTitle}</td>
                                                      </tr>
                                                      <tr>
                                                         <th>이미지등록</th>
                                                         <td>
                                                               <label id="upload"> <input type="file"
                                                                  name="reviewImage">
                                                               </label>
                                                         </td>
                                                      </tr>
                                                      <tr>
                                                         <th>내용</th>
                                                         <td><textarea class="form-control form-control-sm" rows="10" name="reviewContent">${reviewVO.reviewContent}</textarea></td>
                                                      </tr>
                                                   </table>
                                                </div>
                                                <div class="modal-footer">
                                                   <button type="submit" class="btn btn-sm btn-outline-info"
                                                      onclick="realModify()">수정하기</button>
                                                   <button type="button" class="btn btn-sm btn-outline-danger"
                                                      data-dismiss="modal">취소</button>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </form>
                                    <!-- 수정모달 끝 -->
                                    
                                    <!-- 삭제모달 시작 -->
                                    <div class="modal fade" id="reviewDelete${reviewVO.reviewId}" role="dialog">
                                       <div class="modal-dialog modal-dialog-centered" style="max-width: 500;">
                                          <div class="modal-content">
                                             <div class="modal-header">
                                                <h4 class="modal-title m-auto">리뷰를 삭제하시겠습니까?</h4>
                                             </div>
                                             <!-- <form method="post"
                                             action="starters?cmd=reviewDeleteAction" name="inform"> -->
                                             <div class="modal-body text-center">
                                                <button type="button" class="btn btn-sm btn-outline-secondary"
                                                   data-dismiss="modal">취소</button>
                                                <button type="submit" class="btn btn-sm btn-outline-danger"
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
        </div>
        </div>
      </c:forEach>
   </div>
        
        
         

   </div>
   
   <!-- boardlist end1_건들ㄴㄴ -->
   
   <!-- pagination -->
   <div class="paging">
      <c:if test="${ reviewAllCount > 0}">
         <c:if test="${startPage > pageBlock}">
            <button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
               class="btn btn-sm btn-outline-secondary  w2">이전</button>
         </c:if>
         <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
            <c:choose>
               <c:when test="${currentPage == i}">
                  <button id="ju" name="${i}" onclick="p(this)" 
                  class="btn btn-sm btn-outline-secondary  w1">${i}</button>
               </c:when>
               <c:otherwise>
                  <button id="ju" name="${i}" onclick="p(this)" 
                  class="btn btn-sm btn-outline-secondary  w">${i}</button>
                  
               </c:otherwise>
            </c:choose>
         </c:forEach>
         <c:if test="${endPage < pageCount }">
            <button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
               class="btn btn-sm btn-outline-secondary  w2">다음</button>
         </c:if>
      </c:if>
   </div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
/**후기 리스트 사진 가져오기*/
var list1 = new Array();
<c:forEach items="${list}" var="item1">
list1.push("${item1.reviewId}");
var reviewListId = ${item1.reviewId};
//console.log("reviewListId" + reviewListId);
$.ajax({
   data : list1,
   url : "starters?cmd=reviewListImage&reviewListNum=" + reviewListId,
   async : false,
   type : "POST",
   success : function(result) {
   //   console.log("result" + result);
   //   console.log("reviewListId" + reviewListId);
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
   url : "starters?cmd=reviewListImage&reviewListNum=" + reviewListId,
   async : false,
   type : "POST",
   success : function(result) {
   //   console.log("result" + result);
   //   console.log("reviewListId" + reviewListId);
      $(".reviewImage[reviewIdImg = '" + reviewListId + "']").html(result);
   }
});
</c:forEach>


/**페이징*/
   function p(jumpBtn) {
      var rStr = jumpBtn.name;
      $.ajax({
         url : "starters?cmd=reviewListPagingAction&pageNum=" + rStr,
         success : function(result) {
            $("div.mainList").html(result);
            if($('button').hasClass('w1') === true){
         	   $('.w1').addClass('active');
            }
         }
      })
   }
/**조회수*/
   function k(button) {
      var rStr = button.name;
      //console.log(rStr);
      //   location.href = "starters?cmd=reviewNum&reviewid="+ rStr;
      $.ajax({
         url : "starters?cmd=reviewNum&reviewid=" + rStr,
         success : function(result) {
            $("dd.reviewCounts[reviewid='" + rStr + "']").html(result);
            $("span.reviewCounts[reviewid='" + rStr + "']").html(result);
         }
      })
   }

   /**삭제하기*/
   var realDelete = function() {
      location.href = "starters?cmd=reviewDeleteAction";
   };

   /**좋아요*/
    $("span.favorite").click(function() {
      var reviewDetailNum = $(this).attr("reviewdetailnum");
      // console.log("reviewDetailNum"+reviewDetailNum);
      var member_id = "<%=(String) session.getAttribute("id")%>";
            if (member_id == "null") {
               alert("비회원은 좋아요를 하실 수 없습니다.");
               return false;
            } else {
               $.ajax({
                  url : "starters?cmd=reviewLikeAction&reviewDetailNum="+ reviewDetailNum,
                  async : false,
                  type : "POST",
                  success : function(result) {
                     $(".favorite[reviewdetailnum = '"+ reviewDetailNum + "']").html(result);
                     //$(".favoriteLikeCount[reviewdetailnum = '" + reviewDetailNum + "']").html(result);
                  }
               });
               
               $.ajax({
                  url : "starters?cmd=reviewLikeCount&reviewDetailNum="+ reviewDetailNum,
                  async : false,
                  type : "POST",
                  success : function(result) {
                     $(".favoriteLikeCount[reviewdetailnum = '"+ reviewDetailNum + "']").html(result);
                     //$(".favoriteLikeCount[reviewdetailnum = '" + reviewDetailNum + "']").html(result);
                  }
               });
            }
         });

</script>