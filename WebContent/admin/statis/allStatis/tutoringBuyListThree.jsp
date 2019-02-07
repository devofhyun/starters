<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<h5>튜터링 결제</h5>
<p>${tutoringAllCount}개의 튜터링 신청 현황</p>
<table class="table text-body">
	<thead>
		<tr>
			<!-- 타이틀 역할 -->
			<th>결제번호</th>
			<th>튜티 아이디</th>
			<th>튜터링 명</th>
			<th>결제 가격</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${list}" var="tutoringBuyVO">
		<tr onclick="location.href='starters?cmd=tutoringBuyStatiscticsDetail&num=${tutoringBuyVO.tutoringApplyId}'">
			<td>${tutoringBuyVO.tutoringApplyId}</td>
			<td>${tutoringBuyVO.tuteeId}</td>
			<td>${tutoringBuyVO.tutoringTitle}</td>
			<td><fmt:formatNumber value="${tutoringBuyVO.tutoringPrice}" pattern="#,###" />원</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<!-- pagination -->
<div class="paging">
<c:if test="${ tutoringAllCount > 0}">

	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary w2">이전</button>
	</c:if>

	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary  w1">${i}</button>
			</c:when>

			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary w">${i}</button>
			</c:otherwise>

		</c:choose>
	</c:forEach>

	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)" class="btn btn-sm btn-outline-secondary w2">다음</button>
	</c:if>


</c:if>
</div>

    <script>


	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=tutoringBuyStatiscticsThreePaging&pageNum=" + rStr,
			success : function(result) {
				$("div.AllStatisticMain").html(result);
			}
		})
	}

</script>
    