<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--<c:choose>
	<c:when test="${result == true}">
		<span id="favorite"
			style="color: red; font-size: 50px; cursor: pointer;"
			portfolioDetailNum="${pvo.portfolioId}">♥</span>
	</c:when>
	<c:otherwise>
		<span id="favorite"
			style="color: red; font-size: 50px; cursor: pointer;"
			portfolioDetailNum="${pvo.portfolioId}">♡</span>
	</c:otherwise>
</c:choose> --%>
<c:if test="${result == true}">
		<i id="favorite"style="cursor:pointer;
		  color:red;
		  transition:.2s;"
		   portfolioDetailNum="${pvo.portfolioId}">
		<span>${portfolioLikeCount}</span></i>
</c:if>
<c:if test="${result2 == true}">
	<%--안좋아요 --%>
		<i id="favorite" style="cursor:pointer;
		  color:#aaa;
		  transition:.2s;" 
		  portfolioDetailNum="${pvo.portfolioId}">
		<span>${portfolioLikeCount}</span></i>
</c:if>