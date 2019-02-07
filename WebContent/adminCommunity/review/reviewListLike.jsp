<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${result1==true}">
	<%--좋아요 --%>
	<span class="favorite" style=" padding-right: 5px;"
		reviewdetailnum="${reviewVO.reviewId}"><i class="fas fa-heart"></i><span>좋아요 ${reviewLikeCount} 명</span></span>
</c:if>
<c:if test="${result2==true}">
	<%--안좋아요 --%>
	<span class="favorite" style=" padding-right: 5px;"
		reviewdetailnum="${reviewVO.reviewId}"><i class="far fa-heart"></i><span>좋아요 ${reviewLikeCount} 명</span></span>
</c:if>
