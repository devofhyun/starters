<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="com.starters.*"
   import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
<link rel="stylesheet"
   href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<%@include file="../nav.jsp"%>
<link rel="stylesheet" href="assets/css/mypage/tutee/tuteeMypage.css">
<link rel="stylesheet" type="text/css" href="assets/css/tab.css">


<title>스타터스 : 튜터링 목록</title>


<div class="container">



  <ul class="nav nav-tabs nav-justified mb-5" id="myTab">
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tuteeDetailAction">나의 정보</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutoringLike">찜 목록</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="starters?cmd=tuteeMypageList">튜터링 일지</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=tutoringInfo">결제 내역</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="starters?cmd=interviewList">면접 현황</a>
    </li>
  </ul>



   <h4 class="mt-5">${name}님에게 추천하는 튜터링</h4>
   <br>
	<div class="row recomand">
	            <c:forEach items="${recommendTutoring}" var="recommendTutoring">
               <div class="card col-md-4">
                        <a class="tutoringDetailGO"
                           href="starters?cmd=tutoringListDetailAction&num=${recommendTutoring.tutoringId}">
                           <div class="text-center">${recommendTutoring.title}</div>
                           <div class="panel-thumbnail tutoringImage" tutoringIdImg="${recommendTutoring.tutoringId }" id="${recommendTutoring.tutoringId}"></div>
                        </a>
               </div>
            </c:forEach>
	</div>
	
	
   <div class="container-fluid">
      <div id="carouselExample" class="carousel slide" data-ride="carousel">
         <div class="carousel-inner row w-100 mx-auto" role="listbox">

            <c:forEach items="${recommendTutoring}" var="recommendTutoring">
               <div class="carousel-item col-md-4">
                  <div class="panel panel-default">
                     <div class="panel-thumbnail">
                        <a class="tutoringDetailGO"
                           href="starters?cmd=tutoringListDetailAction&num=${recommendTutoring.tutoringId}">
                           <div class="text-center">${recommendTutoring.title}</div>
                           <div class="panel-thumbnail tutoringImage" tutoringIdImg="${recommendTutoring.tutoringId }" id="${recommendTutoring.tutoringId}"></div>
                        </a>
                     </div>
                  </div>
               </div>
            </c:forEach>
         </div>
         <a class="carousel-control-prev" href="#carouselExample"
            role="button" data-slide="prev"> <span
            class="carousel-control-prev-icon" aria-hidden="true"></span> <span
            class="sr-only">Previous</span>
         </a> <a class="carousel-control-next text-faded" href="#carouselExample"
            role="button" data-slide="next"> <span
            class="carousel-control-next-icon" aria-hidden="true"></span> <span
            class="sr-only">Next</span>
         </a>
      </div>
   </div>


   <div class="mypage mt-5">
      <jsp:include page="mypageList.jsp" />
   </div>

</div>


<%@include file="../footer.jsp"%>
<script src="assets/js/tabSizing.js"> </script>
<script>
   var list1 = new Array();
   <c:forEach items="${recommendTutoring}" var="recommendTutoring">
   list1.push("${recommendTutoring.tutoringId}");
   var tutoringListId = ${recommendTutoring.tutoringId};
   console.log("tutoringListId이다" + tutoringListId);
   $.ajax({
      data : list1,
      url : "starters?cmd=tuteetutoringListImage&tutoringListNum="
            + tutoringListId,
      async : false,
      type : "POST",
      success : function(result) {
         console.log("result" + result);
         console.log("tutoringListId" + tutoringListId);
         $(".tutoringImage[tutoringIdImg = '" + tutoringListId + "']").html(
               result);
      }

   });
   </c:forEach>
   console.log(list1);
   console.log("리얼확인" + tutoringListId);
   

/*    $('#carouselExample').on('slide.bs.carousel', function (e) {

     
       var $e = $(e.relatedTarget);
       var idx = $e.index();
       var itemsPerSlide = 4;
       var totalItems = $('.carousel-item').length;
       
       if (idx >= totalItems-(itemsPerSlide-1)) {
           var it = itemsPerSlide - (totalItems - idx);
           for (var i=0; i<it; i++) {
               // append slides to end
               if (e.direction=="left") {
                   $('.carousel-item').eq(i).appendTo('.carousel-inner');
               }
               else {
                   $('.carousel-item').eq(0).appendTo('.carousel-inner');
               }
           }
       }
   }); */

     $(document).ready(function() {
   /* show lightbox when clicking a thumbnail */
       $('a.thumb').click(function(event){
         event.preventDefault();
         var content = $('.modal-body');
         content.empty();
           var title = $(this).attr("title");
           $('.modal-title').html(title);        
           content.html($(this).html());
           $(".modal-profile").modal({show:true});
       });
   
      /* 이미지  */
      var Child = $('.carousel-inner').children()
      
      /* 캐러셀 이미지 처음에 active주는거 */
      var firstChild = Child.eq(0);
      firstChild.addClass('active');

  	if($(document).width() < 768){
  		verCul();
  	}


     });
     

   $(window).resize(function (){
   	if($(document).width() < 768){
   		verCul();
   	} else if($(document).width() > 768){
   		horCul()}
   });

</script>