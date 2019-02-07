<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
          
<style>
table.table {
    max-width: 100%;
	table-layout: auto;
}
</style>
     			<p class="float-right">금액(원)</p>
				<table class="table">
					<tr>
						<td>날짜</td>
						<td>튜터링 결제 금액</td>
						<td>수수료</td>
						<td>튜터 지급금</td>
						<td>면접신청비용</td>
						<td>총 금액</td>
					</tr>
					<c:forEach items="${one}" var="oneVO">
						<tr>
							<td>${oneVO.date}</td>
							<td><fmt:formatNumber value="${oneVO.tutoringBuyAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${oneVO.startersAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${oneVO.tutorAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber
									value="${oneVO.interviewBuyAmoutSum}" pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${oneVO.total}"
									pattern="#,###" />원</td>
						</tr>
					</c:forEach>
				</table>

<!-- pagination -->
<div class="paging">
<c:if test="${ count > 0}">

	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary btn-sm w2">이전</button>
	</c:if>

	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary btn-sm w1">${i}</button>
			</c:when>

			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary btn-sm w">${i}</button>
			</c:otherwise>

		</c:choose>
	</c:forEach>

	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary btn-sm">다음</button>
	</c:if>


</c:if>
</div>
<%-- 136번째줄 close --%>
<script>
function p(jumpBtn) {
	var rStr = jumpBtn.name;
	// alert(rStr);
	$.ajax({
		url : "starters?cmd=getBuyDayAllPaging&pageNum=" + rStr,
		success : function(result) {
			$("div.AllStatisticDayMain").html(result);
		}
	})
	//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
	//alert("starters?cmd=tutoringList?pageNum=" + rStr);
	//	$("#mainList").load(query);
	// alert(query);
}
</script>