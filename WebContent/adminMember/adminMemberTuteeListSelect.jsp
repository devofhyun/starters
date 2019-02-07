<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.AdminTMemberVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="col-lg-10 col-md-10">
			<p>${adminMemberTuteeListNumber}명</p>
		</div>
		<!-- 기업리스트 -->
		<table class="table companyList">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>직종</th>
					<th>가입날짜</th>

				</tr>
			</thead>

			<tbody id="myTable">
				<c:forEach items="${list}" var="tutee">
				<tr onclick="location.href='starters?cmd=adminTuteeDetailAction&id=${tutee.memberId}'">
						<td>${tutee.memberId}</td>
						<td>${tutee.name}</td>
						<td>${tutee.birth}</td>
						<td>${tutee.middleName}</td>
						<td>${tutee.registerDate}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>


		<!-- 기업리스트 끝 -->
		<!-- pagination -->
		<div class="paging my-5">
			<c:if test="${ adminMemberTuteeListNumber > 0}">

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
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=adminMemberTuteeListSelectPaging&pageNum="
					+ rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})

	}
</script>
