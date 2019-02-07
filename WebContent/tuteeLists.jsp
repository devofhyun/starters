<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>튜티 회원 목록</h1>
<table border="1">
	<tr>
		<!-- 타이틀 역할 -->
		<td>튜티 아이디</td>
		<td>튜티 이름</td>
		<td>튜티 생년월일</td>
		<td>튜티 성별</td>
		<td>튜티 이메일</td>
		<td>튜티 전화번호</td>
		<td>튜티 자기소개</td>
		<td>튜티 가입일시</td>
	</tr>
	<c:forEach items="${tuteeList}" var="memberVO">
		<tr>
			<td>${memberVO.memberId}</td>
			<td>${memberVO.name}</td>
			<td>${memberVO.birth}</td>
			<td>${memberVO.gender}</td>
			<td>${memberVO.email}</td>
			<td>${memberVO.phoneNum}</td>
			<td>${memberVO.intro}</td>
			<td>${memberVO.registerDate}</td>
		</tr>
	</c:forEach>
</table>

