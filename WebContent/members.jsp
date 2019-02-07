<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>멤버 리스트</h1>
<table border="1">
	<tr> <!-- 타이틀 역할 -->
		<td>튜터 아이디</td>
		<td>튜터 이름</td>
		<td>튜터 생년월일</td>
		<td>튜터 성별</td>
		<td>튜터 이메일</td>
		<td>튜터 전화번호</td>
		<td>튜터 자기소개</td>
		<td>튜터 이력서</td>
		<td>튜터 포트폴리오</td>
		<td>튜터 가입일자</td>
	</tr>
	<c:forEach items= "${getMemberTenList}" var="memberVO" >
	<tr>
	<td>${memberVO.memberId}</td>
		<td>${memberVO.name}</td>
		<td>${memberVO.birth}</td>
		<td>${memberVO.gender}</td>
		<td>${memberVO.email}</td>
		<td>${memberVO.phoneNum}</td>
		<td>${memberVO.intro}</td>
		<td>${memberVO.resume}</td>
		<td>${memberVO.portfolio}</td>
		<td>${memberVO.registerDate}</td>
	</tr>
	</c:forEach>
</table>