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


<%@include file="../nav.jsp"%>

<link rel="stylesheet" href="assets/css/tutoring/tutoring_detail.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>스타터스 : 포트폴리오 - ${pvo.portfolioTitle}</title>
<head>

<style>
i:before {
   font-family: fontawesome;
   content: '\f004';
   font-style: normal;
   border: 1px solid #eee;
   padding: 15;
   border-radius: 50%;
	background-color: #fff;
}

i.changed:before {
   font-family: fontawesome;
   content: '\f004';
   font-style: normal;
   display: none;
}
</style>
</head>

<div class="container info">
   <div class="row ">
      <div class="col-md-8 TimgD">
         <c:forEach items="${intimagevo}" var="IntImageVO" varStatus="iv">
            <c:if test="${iv.index eq 0}">
               <div class="tutoringImg">
                  <img class="TImg"
                     src="../assets/portfolioImage/${IntImageVO.getImg()}" />
                  <%--${IntImageVO.getImg()} --%>
               </div>
            </c:if>
         </c:forEach>
      </div>
      <div class="col-md-4 con">
         <h2 class="my-3">
            <strong>${pvo.portfolioTitle}</strong>
         </h2>
         <div class="mb-3">
            <h5>
               <strong>튜터링 분류</strong>
            </h5>
            <c:forEach items="${job}" var="IntJobSelectVO" varStatus="ijs">
               <h6 class="method">${IntJobSelectVO.getMiddleCategName()}</h6>
            </c:forEach>
         </div>
         <div class="mb-3">
            <h5>
               <strong>포트폴리오 URL</strong>
            </h5>
            <h6>
               <a href="//${pvo.portfolioUrl}">${pvo.portfolioUrl}</a>
            </h6>
         </div>
         <div class="mb-3">
            <h5>
               <strong>포트폴리오 구현 방식</strong>
            </h5>
            <h6>${pvo.portfolioMethod}</h6>
         </div>
         <div class="mb-3">
            <h5>
               <strong>포트폴리오 첨부파일</strong>
            </h5>
            <h6>
               <a href="../assets/portfolioImage/${pvo.portfolioFile}">${pvo.portfolioFile}</a>
            </h6>
         </div>
         <c:choose>
         
            <c:when test="${id!=pvo.memberId && id != null}">


<%--                <c:if test="${id != null}"> --%>
                  <div class="row like">

                     <c:if test="${portfoliolike==1}">
                        <%--좋아요 --%>
                        <i class="here" id="favorite"
                           style="cursor: pointer; color: red;"
                           portfolioDetailNum="${pvo.portfolioId}"> <span>${portfolioLikeCount}</span></i>
                     </c:if>
                     <c:if test="${portfoliolike==0}">
                        <%--안좋아요 --%>
                        <i class="here" id="favorite"
                           style="cursor: pointer; color: #aaa;"
                           portfolioDetailNum="${pvo.portfolioId}"> <span>${portfolioLikeCount}</span></i>
                     </c:if>
                  </div>
<%--                </c:if> --%>
            </c:when>
            <c:when test="${id ==pvo.memberId}">

         		<div class="row like">
                  <i class="here" id="favoriteTutee"
                     style="cursor: pointer; color: #aaa;"
                     portfolioDetailNum="${pvo.portfolioId}"
                     onClick="alert('작성자는 좋아요를 누르실 수 없습니다.');"> <span>${portfolioLikeCount}</span></i>
            		</div>
            </c:when>
            <c:otherwise>
         		<div class="row like">
                  <i class="here" id="favoriteTutee"
                     style="cursor: pointer; color: #aaa;"
                     portfolioDetailNum="${pvo.portfolioId}"
                     onClick="alert('로그인을 해야 좋아요를 누를 수 있습니다.');"> <span>${portfolioLikeCount}</span></i>
            		</div>
            </c:otherwise>
         </c:choose>

         <div class="row btnz">

            <c:if test="${tuteemembers==0 && id != null}">
               <button class="btn edit">신청하기</button>
               <button class="btn remove">찜하기</button>

            </c:if>
            <!--    <button class="btn like" action="starters?cmd=portfolioLikeAction">좋아요</button> -->


            <!-- 기업 -->


            <c:if test="${membersCateg==3 && id!= null}">

               <c:choose>
                  <c:when test="${applyListsCount == 1 }">
                     <button class="col-5 btn btn-outline-secondary btn-block mr-1"
                        onclick="alert('이미 면접 신청한 포트폴리오입니다.');">면접신청하기</button>
                  </c:when>
                  <c:otherwise>
                     <button class="col-5 btn btn-outline-secondary btn-block mr-1"
                        onclick="companyInterviewApply(this)" name="${pvo.portfolioId}">면접신청하기</button>
                  </c:otherwise>
               </c:choose>

               <c:choose>
                  <c:when test="${wishListsCount==1}">
                     <button class="col-5 btn btn-outline-info btn-block wish"
                        onClick="alert('이미 찜한 포트폴리오입니다.');">찜하기</button>
                  </c:when>
                  <c:otherwise>
                     <button class="col-5 btn btn-outline-info btn-block wish"
                        data-toggle="modal" id="wishListModal ${pvo.portfolioId}"
                        data-target="#wishModal${pvo.portfolioId}" onclick="u(this)"
                        name="wishCompany${pvo.portfolioId}">찜하기</button>
                  </c:otherwise>
               </c:choose>

               <form method="post" action="starters?cmd=companyLikeAction"
                  id="companyWish">
                  <div class="modal fade" id="wishModal${pvo.portfolioId}"
                     role="dialog">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <div class="modal-header">
                              <h4 class="modal-title">찜하기</h4>
                           </div>
                           <div class="modal-body">
                              <p>${pvo.portfolioTitle}찜하시겠습니까?</p>
                           </div>
                           <div class="modal-footer">
                              <button type="submit" class="btn btn-sm btn-outline-info ">찜하기</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </form>
            </c:if>


            <!-- 기업 끝 -->



            <c:if test="${id == pvo.memberId}">
               <button class="col-5 btn btn-outline-secondary btn-block mr-1 edit"
                  onclick="changeForm()">수정하기</button>

               <c:if test="${portfolio ==1}">
                  <a>면접이 진행 중인 포트폴리오 입니다.</a>
               </c:if>
               <c:if test="${portfolio ==0}">
                  <button class="col-5 btn btn-outline-danger btn-block remove"
                     data-toggle="modal" data-target="#portfolioDelete">삭제하기</button>
               </c:if>
            </c:if>






            <!-- Modal -->

            <div class="modal fade" id="portfolioDelete" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">

                        <h4 class="modal-title">포트폴리오를을 삭제하시겠습니까?</h4>
                     </div>
                     <form method="post" action="starters?cmd=portfolioDeleteAction"
                        name="inform">
                        <!-- <div class="modal-body"><li>비밀번호 확인</li> <input type="password" name="passwd"></div> -->
                        <div class="modal-footer">
                           <button type="button" class="btn btn-sm btn-outline-secondary"
                              data-dismiss="modal">취소</button>


                           <button type="submit" class="btn btn-sm btn-outline-danger">삭제하기</button>

                        </div>
                     </form>

                  </div>

               </div>
            </div>
         </div>
      </div>
   </div>
</div>

<div class="container content">
   <div class="col-md-12">
      <h4>
         <strong>포트폴리오 내용</strong>
      </h4>
      <div class="plan">${pvo.portfolioText}</div>

   </div>
<div class="mx-3 text-right mb-5">
   <button class="btn btn-sm btn-outline-secondary" onclick="back()">목록</button>
</div>
</div>


<%@include file="../footer.jsp"%>

</body>

<script>
      $(document).ready(function() {
         $('ul.select > li').click(function() {
            // $(this).siblings().removeClass('on').addClass('off').end().addClass('on');
            $(this).addClass('on');
         });
      });
      function selectWeek(val, obj, num) {
         var area = $('#Week' + val);
         area.find('a').removeClass('on');
         area.find('a').addClass('off');
         $(obj).addClass('on');

      }

      var changeForm = function() {
         location.href = "starters?cmd=portfolioModify";
      }
      


      var toLogin = function() {
         location.href = "starters?cmd=login";
      }

      $("#favorite").click(function() {
                     var portfolioDetailNum = $("#favorite").attr("portfolioDetailNum");
                     //console.log(portfolioDetailNum);
                     // location.href = "starters?cmd=portfolioLikeAction&portfolioDetailNum=" + portfolioDetailNum
                     if($('i.here').hasClass("changed") === false){
                        $('i.here').toggleClass('changed');
                        $('i.here.changed span').css('display', 'none');
                        //console.log("바뀜");
                     }
                     $.ajax({
                              url : "starters?cmd=portfolioLikeAction&portfolioDetailNum="+ portfolioDetailNum,
                           success : function(result) {
                           $("i#favorite").html(result);
                              }
                           })
                           
                  });
      
      
      var back = function() {
         location.href = "starters?cmd=portfolioList";
      };
      
      
      
      
      function companyInterviewApply(button) {
         var rStr = button.name;
         location.href = "starters?cmd=companyInterviewApply&portfolioId="+rStr;
      }
      
   </script>