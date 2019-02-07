<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.starters.*"
   import="com.starters.ReviewVO" import="java.util.ArrayList"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row list text-center listTotal mb-5">
   <c:forEach items="${tutoringWishList}" var="tutoringWishList">
      <div class="cardbox col-lg-4 col-md-4 col-sm-6">
         <div class="card">
            <div class="tutoringImage"
               tutoringIdImg="${tutoringWishList.tutoringId }"
               id="${tutoringWishList.tutoringId }"></div>
            <div class="tutoringTitle" 
               tutoringIdImg="${tutoringVO2.tutoringId }" id = "${tutoringVO2.tutoringId }"></div> 
            
            <div class="cardtext">
               <h5 class="title">${tutoringWishList.title}</h5>
               <div class="rowdetail">
               
                  <button type="button" class="btn btn-sm btn-outline-warning wishbtn" name="${tutoringWishList.tutoringId}" onclick="detail(this)">상세보기</button>

                  <!-- 찜하기 취소 버튼 -->
                  <button type="button" class="btn btn-sm btn-outline-danger wishIdModal"
                     data-toggle="modal" id="wishIdModal2"
                     data-target="#wishModal${tutoringWishList.tutoringId}"
                     onclick="h(this)" name="${tutoringWishList.tutoringId}">찜하기취소</button>

                  <form method="post" action="starters?cmd=wishListDeleteAction"
                     id="delete" enctype="multipart/form-data">
                     <!-- 취소하기 모달 -->
                     <div class="modal fade"
                        id="wishModal${tutoringWishList.tutoringId}" role="dialog">
                        <div class="modal-dialog modal-dialog-centered">
                           <div class="modal-content">
                              <div class="modal-header">
                                 찜하기 취소하기
                                 <button type="button" class="close" data-dismiss="modal"
                                    onclick="realx()">&times;</button>
                              </div>
                              <div class="modal-body">찜하기를 취소하시겠습니까?</div>
                              <!-- modal-body 끝 -->
                              <div class="modal-footer">
                                 <button type="submit" class="btn btn-outline-danger"
                                    onclick="realDelete()">취소하기</button>
                                 <button type="button" class="btn btn-outline-secondary"
                                    data-dismiss="modal">나가기</button>
                              </div>
                           </div>
                           <!-- modal-content 끝 -->
                        </div>
                        <!-- modal-dialog 끝 -->
                     </div>
                     <!-- 찜하기 취소 modal 끝 -->
                  </form>
               </div>
            </div>
         </div>
      </div>
   </c:forEach>
</div>

<!-- pagination -->

<div class="paging">
<c:if test="${ wishAllCount > 0}">
   <c:if test="${startPage > pageBlock}">
      <button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
         class="btn btn-outline-secondary btn-sm w2">이전</button>
         </c:if>
   <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
      <c:choose>
         <c:when test="${currentPage == i}">
            <button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary btn-sm w1">${i}</button>
         </c:when>
         <c:otherwise>
            <button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary btn-sm w">${i}</button>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   <c:if test="${endPage < pageCount }">
      <button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
         class="btn btn-outline-secondary btn-sm w2">다음</button>
         </c:if>
</c:if>



</div>

<script>
   /**페이징*/
   function p(jumpBtn) {
      var rStr = jumpBtn.name;
      $.ajax({
         url : "starters?cmd=tutoringLikePaging&pageNum=" + rStr,
         success : function(result) {
            $("div.tutoringInfoLikeList").html(result);
         }
      })
   }
   /**찜하기 취소*/
   function h(button) {
      var rStr = button.name;
//      console.log("rStr" + rStr);
      //       location.href = "starters?cmd=wishNum&tutoringNum="+ rStr;
      $.ajax({
         url : "starters?cmd=wishNum&tutoringnum=" + rStr,
         success : function(result) {
            $("#wishIdModal").html(result);
         }
      })
   }
   
   var realDelete = function() {
      location.href = "starters?cmd=wishListDeleteAction";
   };
   
   function detail(button) {
      var rStr = button.name;
//      console.log("rStr" + rStr);
      location.href = "starters?cmd=tutoringListDetailAction&num="+ rStr;
      
   }
   
   var list1 = new Array();
   <c:forEach items="${tutoringWishList}" var="tutoringWishList">
   list1.push("${tutoringWishList.tutoringId}");
   var tutoringListId = ${tutoringWishList.tutoringId};
//   console.log("tutoringListId이다" + tutoringListId);
   $.ajax({
      data : list1,
      url : "starters?cmd=tuteetutoringListImage&tutoringListNum="
            + tutoringListId,
      async : false,
      type : "POST",
      success : function(result) {
   //      console.log("result" + result);
   //      console.log("tutoringListId" + tutoringListId);
         $(".tutoringImage[tutoringIdImg = '" + tutoringListId + "']").html(
               result);
      }

   });
   </c:forEach>
//   console.log(list1);
//   console.log("리얼확인" + tutoringListId);
   
   
   
</script>