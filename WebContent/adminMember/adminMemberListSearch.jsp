<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.AdminAMemberVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<div class="col-lg-10 col-md-10">
			<p>${adminMemberListNumber}명</p>
		</div>
		<!-- 기업리스트 -->
		<table class="table companyList">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>가입날짜</th>
					<th>구분</th>

				</tr>
			</thead>

			<tbody id="myTable">
				<c:forEach items="${list}" var="list">
					<tr>
						<td>
						<c:choose>
								<c:when test="${list.categ == '튜터' }">
									<a href="starters?cmd=adminAllTutorDetailAction&id=${list.memberId}">${list.memberId}</a>
								</c:when>
								<c:when test="${list.categ == '튜티' }">
									<a href="starters?cmd=adminAllTuteeDetailAction&id=${list.memberId}">${list.memberId}</a>
								</c:when>
								<c:otherwise>
									<a href="starters?cmd=adminAllCompanyDetailAction&id=${list.memberId}">${list.memberId}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${list.name}</td>
						<td>${list.registerDate}</td>
						<td>${list.categ}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>


		<!-- 기업리스트 끝 -->
		<!-- pagination -->
		<div class="paging my-5">
			<c:if test="${ adminMemberListNumber > 0}">

				<c:if test="${startPage > pageBlock}">
					<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
						class="btn btn-sm btn-outline-dark w2">이전</button>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<c:choose>
						<c:when test="${currentPage == i}">
							<button id="ju" name="${i}" onclick="p(this)"
								class="btn btn-sm btn-outline-dark w1">${i}</button>
						</c:when>

						<c:otherwise>
							<button id="ju" name="${i}" onclick="p(this)"
								class="btn btn-sm btn-outline-dark w">${i}</button>
						</c:otherwise>

					</c:choose>
				</c:forEach>

				<c:if test="${endPage < pageCount }">
					<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
						class="btn btn-sm btn-outline-dark w2">다음</button>
				</c:if>


			</c:if>
		</div>
<script>

	// 페이징 처리
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=adminMemberListSearchPaging&pageNum="
					+ rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})
		//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
		//alert("starters?cmd=tutoringList?pageNum=" + rStr);
		//	$("#mainList").load(query);
		// alert(query);
	}

	
</script>