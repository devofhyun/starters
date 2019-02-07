<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 없애도 됨 -->
<select name="middleSelect" id="middleSelect">
	<c:forEach items="${middleCategNames}" var="middleCategNames">
		<option value="${middleCategNames.middleCategId}">${middleCategNames.middleName}</option>
	</c:forEach>
</select>