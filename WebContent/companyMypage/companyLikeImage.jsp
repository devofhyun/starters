<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${images}" var="IntImageVO" varStatus="iv">
	<!-- <img src="../StartersSevlet/assets/companyImage/${IntImageVO.getImg()}" style = "height: 300px; width: 400px;"/> -->
	<c:if test="${iv.index eq 0}">
		<img src="../assets/portfolioImg/${IntImageVO.getImg()}"  class="Timg" />
	</c:if>
</c:forEach> 



<%-- <c:forEach items="${images}" var="ImageVO" varStatus="iv">
	<img src="../assets/portfolioImg/${ImageVO.getImg()}" alt="${ImageVO.getImg()}"/>
</c:forEach> --%>