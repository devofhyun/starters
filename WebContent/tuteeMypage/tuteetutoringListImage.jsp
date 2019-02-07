<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${images}" var="IntImageVO" varStatus="iv">
   <c:if test="${iv.index eq 0}">
      <img src="../assets/tutoringImg/${IntImageVO.getImg()}" class="Timg" />
   </c:if>
</c:forEach>
      
		
		
<script>

$(document).ready(function() {

	/* 이미지  */
	var Child = $('.carousel-inner').children()
	
	/* 캐러셀 이미지 처음에 active주는거 */
	var firstChild = Child.eq(0);
	firstChild.addClass('active');
	


});

</script>