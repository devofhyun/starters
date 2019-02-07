<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${selectMiddleModi}" var="selectMiddleModi">
		<option value="${selectMiddleModi.middleCategId}">${selectMiddleModi.middleName}</option>
</c:forEach>
