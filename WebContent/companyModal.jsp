<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="modal-content">
	<span class="close">&times;</span>
	</div>
 	<!-- <div class="modal-content">
	<span class="close">&times;</span>
	</div>
	
	<div class="table" id="compinfo">
		<table>
			<colgroup>
				<col class="col1" />
				<col class="col2" />
				<col class="col3" />
				<col class="col4" />
			</colgroup>
			<tbody>
			<c:forEach items="${cvo}" var="cvo">
				<tr>
				
					<th scope="row">기업명</th>
					<td>${cvo.name}</td>
					<th scope="row">대표자명</th>
					<td>${cvo.gender}</td>
				</tr>
				<tr>
					<th scope="row">업종</th>
					<td colspan="3">${cvo.email}</td>
				</tr>
				<tr>
					<th scope="row">사원수</th>
					<td colspan="3">${cvo.phoneNum}</td>
				</tr>
				<tr>
					<th scope="row">대표전화</th>
					<td colspan="3">${cvo.phoneNum}</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td colspan="3">${cvo.address}</td>
				</tr>
				<tr>
					<th scope="row">홈페이지</th>
					<td colspan="3"><a href="https://linepluscorp.com/">${cvo.companyUrl}</a></td>
				</tr>
</c:forEach>
			</tbody>
		</table>
	</div>
	 -->