<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.TutoringDAO"
	import="com.starters.TutoringVO2" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="w-100">
${tutoringAll} 개의 내역
	<table class="table companyList">
		<thead>
			<tr>
				<th>튜터링번호</th>
				<th>튜터아이디</th>
				<th>튜터명</th>
				<th>튜터링명</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody id="myTable">
			<c:forEach items="${tutoringList}" var="tutoringList">
				<tr onclick="location.href='starters?cmd=adminTutoringListDetailAction&num=${tutoringList.tutoringId}'">
					<td>${tutoringList.tutoringId}</td>
					<td>${tutoringList.tutorId}</td>
					<td>${tutoringList.tutorName}</td>
					<td>${tutoringList.title}</td>
					<td>${tutoringList.uploadDate}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>

<!-- pagination -->
<div class="paging">
	<c:if test="${ tutoringAll > 0}">

		<c:if test="${startPage > pageBlock}">
			<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
				class="btn btn-sm btn-outline-secondary w2">이전</button>
		</c:if>

		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<c:choose>
				<c:when test="${currentPage == i}">
					<button id="ju" name="${i}" onclick="p(this)"
						class="btn btn-sm btn-outline-secondary  w1">${i}</button>
				</c:when>

				<c:otherwise>
					<button id="ju" name="${i}" onclick="p(this)"
						class="btn btn-sm btn-outline-secondary w">${i}</button>
				</c:otherwise>

			</c:choose>
		</c:forEach>

		<c:if test="${endPage < pageCount }">
			<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
				class="btn btn-sm btn-outline-secondary w2">다음</button>
		</c:if>


	</c:if>
</div>



<%-- 136번째줄 close --%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=adminTutoringDateSelectPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})
	}
</script>







