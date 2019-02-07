<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    
<c:forEach items="${images}" var="IntImageVO" varStatus="iv">
	<!-- <img src="../StartersSevlet/assets/companyImage/${IntImageVO.getImg()}" style = "height: 300px; width: 400px;"/> -->
<c:if test="${iv.index eq 0}">
<img src="../assets/tutoringImg/${IntImageVO.getImg()}" class="Timg"/> 
	<%--${IntImageVO.getImg()} --%>
	</c:if>
</c:forEach>