<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.starters.ReviewDAO"
   import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<link rel="stylesheet"
   href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
   

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<%@include file="../../nav.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="assets/css/community/review.css">


<title>스타터스 : 후기</title>
<div class="container main">
   <br>
   <h4>후기 목록</h4>
   <br>
   <form method="post" action="starters?cmd=reviewListSearch">
   <div class="row search">
      <div class="input-group col-12 mb-3">
         <input type="text" class="searchinput form-control form-control-sm" 
         name="searchName" id="name"  placeholder = "원하는 키워드를 입력하세요.">
         <div class="input-group-append">
            <button type="submit" class="btn btn-sm btn-secondary" value="검색" style="width: 100px">검색</button>
         </div>
      </div>

   </div>

   </form>
   
   <div class="row basic-row">

<div class="col-lg-10 col-md-10 col-8">
   <p>${reviewAllCount}개의게시글</p>
   <c:if test="${tuteemembers==4 && id != null}">
      <button type="button" class="btn btn-sm btn-outline-primary" data-toggle="modal"
         data-target="#myModal">리뷰 작성</button>
   

   <!-- The Modal -->
   
      <div class="modal" id="myModal">
         <div class="modal-dialog">
            <div class="modal-content">

               <!-- Modal Header -->
               <div class="modal-header">
                  <h4 id="modal-title" class="modal-title">후기게시글 등록</h4>
                  <button type="button" class="close" data-dismiss="modal">X</button>
               </div>
               <form method="post" action="starters?cmd=reviewRegisterAction" id="upForm" enctype="multipart/form-data" name = "reviewRegister">
               <!-- Modal body -->
               <div class="modal-body">

                  <table class="table reviewRegister">
                     <tr>
                        <th>제목</th>
                        <td><input type="text" name="reviewTitle" class=" form-control form-control-sm"></td>
                     </tr>
                     <tr>
                        <th>튜터링명</th>
                        <td><select name="selectBox" id="selectBox"
                           class="select_02 form-control form-control-sm">
                           <c:choose>
                           <c:when test = "${count > 0}">
                              <c:forEach var="tutoringTitleList" items="${tutoringTitleList}">
                                 <option value="${tutoringTitleList.tutoringApplyId}">${tutoringTitleList.tutoringName}</option>
                              </c:forEach>
                           </c:when>
                           <c:otherwise>
                           <option value="">작성하실 튜터링이 없습니다.</option>
                           </c:otherwise>
                           </c:choose>
                        </select></td>
                     </tr>
                     <tr>
                        <th>이미지</th>
                        <td>
                           <label id="upload">
                           <input type="file" name="reviewImage">
                           </label>
                        </td>
                     </tr>
                     <tr>
                        <th>내용</th>
                        <td><textarea class=" form-control form-control-sm" rows="10" name="reviewContent"></textarea></td>
                     </tr>
                  </table>
               </div>
            </form>
               <!-- Modal footer -->
               <div class="modal-footer">
                  <button type="submit" class="btn btn-sm btn-outline-primary" onclick = "inputCheck(event);">등록</button>
               </div>

            </div>
         </div>
      </div>
   
   </c:if>
   <%--    </c:if> --%>

</div>
      <div class="col-lg-2 col-md-2 col-4">
         <select class="tutoringListSelect  form-control form-control-sm" onchange="abc(this.selectedIndex);">
            <option value="0" onclick="t(this)" selected="selected">---</option>
            <option value="1" onclick="t(this)">번호순</option>
            <option value="2" onclick="t(this)">최신순</option>
            <option value="3" onclick="t(this)">조회수순</option>
            <option value="4" onclick="t(this)">좋아요순</option>
         </select>
      </div>
      </div>

   <div class="container">
   <h4 class="MB">이달의 베스트 후기<i class="fas fa-thumbs-up"></i></h4>
   <form action="" method="post">
   <c:forEach items="${best}" var="bestReview">
   
       <div class = "row bestreview">
           <div class = "col-md-6">
               <div class="reviewImage" reviewIdImg="${bestReview.reviewId}" id="${bestReview.reviewId}"></div>
           </div>
           <div class = "col-md-6 besttitle">
           <table class="table table-borderless">
             <tbody>
             <tr>
                 <th >리뷰 제목</th>
                 <th>:</th>
                 <td>${bestReview.reviewTitle}</td>
               </tr>
               <tr>
                 <th >튜터링 이름</th>
                 <th>:</th>
                 <td>${bestReview.tutoringTitle}</td>
               </tr>
               <tr>
                 <th>튜터 이름</th>
                 <th>:</th>
                 <td>${bestReview.tutorRealName}</td>
               </tr>
             </tbody>
           </table>

               
               <i class="fas fa-sticky-note"></i>내용
              <div class = "scrollBind" id = "scroll"> 
                  <pre-wrap>
                       ${bestReview.reviewContent}
                  </pre>    
              </div>  <!-- scrollBlind end -->
           </div>  <!-- besttitle end -->
       </div> <!--bestreview end -->
       </c:forEach>
   </form>
   
</div>
   

<c:choose>
   <c:when test='${empty searchName}'>
      <div class="mainList ">
         <jsp:include page="reviewList.jsp" />
      </div>
   </c:when>
   <c:otherwise>
      <div class="mainList">
         <jsp:include page="reviewListSearch.jsp" />
      </div>
   </c:otherwise>
</c:choose>
</div>



<%@include file="../../footer.jsp"%>

<script type="text/javascript">
// 조회수순, 최신순, 번호순
function abc(selectedguy) {
	var selectBox = selectedguy;
//	console.log(selectBox);
	$.ajax({
		
		url : "starters?cmd=reviewListSelectAction&select=" + selectBox,
		success : function(result) {
			//console.log("////////////////////")
			//console.log(result)
			$("div.mainList").html(result);
		}
	})
}
   
   function inputCheck(e) {
      if (reviewRegister.selectBox.value == "") {
         alert("후기를 작성할 튜터링을 선택해주세요");
         return false;
      }
      reviewRegister.submit();
   }
</script>