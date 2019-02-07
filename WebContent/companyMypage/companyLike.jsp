<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.starters.*" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="container">
      <!-- <h4><i class="fas fa-gift"></i> 찜한 내역</h4><br> -->
      <!-- <p>Sed ut </p> -->
         <div class="row list text-center">
         
      <c:forEach items="${companyWishList}" var="portfolioWishList">
            <div class="col-lg-4 col-md-4">
               <div class="card">
                  <%-- <a class="portfolio" href="starters?cmd=portfolioListDetailAction&num=${portfolioVO.portfolioId}">  --%>
                     <div class="portfolioImage" 
                     portfolioIdImg="${portfolioWishList.portfolioId}" 
                     id="${portfolioWishList.portfolioId}"></div>
                  <!-- </a>  -->
                  <div class="cardtext">
                  <p class="title"></p>${portfolioWishList.title}
                  <!-- <img class="portfolio" src="https://dummyimage.com/300x200/ddd/000" alt="">
                  <p class="title">Do it! 튜터링</p> -->
                  
                  <div class="rowdetail">
                  <button type="button" class="btn btn-sm btn-outline-warning wishbtn" name="${portfolioWishList.portfolioId}" onclick="detail(this)">상세보기</button>

                     <button type="button" class="btn btn-sm btn-outline-danger wishIdModal"
                     data-toggle="modal" id="wishIdModal"
                     data-target="#wishModal${portfolioWishList.portfolioId}"
                     onclick="h(this)" name="${portfolioWishList.portfolioId}">찜하기취소</button>
                     
                     <!-- 취소하기 모달 -->
                     <div class="modal fade"
                        id="wishModal${portfolioWishList.portfolioId}" role="dialog">
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
                                 <button type="button" class="btn btn-sm btn-outline-secondary"
                                    data-dismiss="modal">취소</button>
                                 <button type="submit" class="btn btn-sm btn-outline-danger"
                                    onclick="realDelete()">삭제하기</button>
                              </div>
                           </div>
                           <!-- modal-content 끝 -->
                        </div>
                        <!-- modal-dialog 끝 -->
                     </div>
                     <!-- 찜하기 취소 modal 끝 -->

                  </div>                  
                  </div>

               </div>
            </div>
      </c:forEach>
         </div>


</div>
            <!-- /row -->

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

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
   /**페이징*/
   function p(jumpBtn) {
      var rStr = jumpBtn.name;
      $.ajax({
         url : "starters?cmd=companyLikePaging&pageNum=" + rStr,
         success : function(result) {
            $("div.tutoringInfoLikeList").html(result);
         }
      })
   }
   
   /**찜하기 취소*/
   function h(button) {
      var rStr = button.name;
      //console.log("rStr" + rStr);
      $.ajax({
         url : "starters?cmd=companywishNum&portfolioNum=" + rStr
         
      })
   }
   
   var realDelete = function() {
      location.href = "starters?cmd=companyLikeDeleteAction";
   };
   
   
   var list1 = new Array();
   <c:forEach items="${companyWishList}" var="portfolioWishList">
   list1.push("${portfolioWishList.portfolioId}");
   var portfolioListId = ${portfolioWishList.portfolioId};
   //console.log("portfolioListId이다" + portfolioListId);
   $.ajax({
      data : list1,
      url : "starters?cmd=companywishImage&portfolioNum="
            + portfolioListId,
      async : false,
      type : "POST",
      success : function(result) {
         //console.log("result" + result);
         //console.log("portfolioListId" + portfolioListId);
         $(".portfolioImage[portfolioIdImg = '" + portfolioListId + "']").html(
               result);
      }

   });
   </c:forEach>
   
   function detail(button) {
      var rStr = button.name;
//      console.log("rStr" + rStr);
      location.href = "starters?cmd=portfolioListDetailAction&num="+ rStr;
      
   }
   //console.log(list1);
   //console.log("리얼확인" + portfolioListId);
   
</script>