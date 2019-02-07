<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="w-100 table-responsive-lg">
${portfolioListNumber} 개의 내역
	<table class="table companyList text-center">
		<thead>
			<tr>
				<th>포트폴리오번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>좋아요</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody id="myTable">
			<c:forEach items="${list}" var="portfolio">
				<tr onclick="location.href='starters?cmd=adminPortfolioListDetailAction&num=${portfolio.portfolioId}'">
					<td>${portfolio.portfolioId}</td>
					<td>${portfolio.portfolioTitle}</td>
					<td>${portfolio.name}</td>
					<td>${portfolio.portfolioLikeCounts}</td>
					<td>${portfolio.portfolioCount}</td>
					<td>${portfolio.uploadDate}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>

<!-- pagination -->
<div class="paging">
	<c:if test="${ portfolioListNumber > 0}">

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
			url : "starters?cmd=adminPortfolioSearchPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})
	}
	           
</script>







