<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="demo" class="carousel slide" data-ride="carousel">
  
  <!-- The slideshow -->
	<div class="carousel-inner">
			<c:forEach items="${images}" var="ImageVO" varStatus="iv">
				<div class="carousel-item">
				<img src="../assets/companyImage/${ImageVO.getImg()}"/>
				</div>
			</c:forEach>
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
	</div>

  <!-- Indicators -->
  <ul class="carousel-indicators">
  </ul>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	/* 이미지  */
	var Child = $('.carousel-inner').children();
	//console.log("//////////"+Child)
	
	/* 캐러셀 이미지 처음에 active주는거 */
	var firstChild = Child.eq(0);
	firstChild.addClass('active');
	
	/* li요소 추가 */
	var countImg = Child.length;
	var size = ${images.size()};
	for(var i=0; i < size; i++){
		 var lis = $('.carousel-indicators');
		 lis.append('<li data-target="#demo" data-slide-to="' + i + '"></li>');
		 lis.children().eq(0).addClass('active');
		 // console.log(lis);  
	}
	
});
</script>