<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<h5>면접 결제</h5>
<p>${tutoringAllCount}개의 면접 신청 현황</p>
<table class="table">
	<thead>
		<tr>
			<!-- 타이틀 역할 -->
			<th>결제번호</th>
			<th>회사 아이디</th>
			<th>포트폴리오 명</th>
			<th>결제금액</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="interviewBuyVO">
			<tr onclick="location.href='starters?cmd=interviewBuyStatiscticsDetail&num=${interviewBuyVO.payId}'">
				<td>${interviewBuyVO.payId}</td>
				<td>${interviewBuyVO.companyId}</td>
				<td>${interviewBuyVO.portfolioTitle}</td>
				<td><fmt:formatNumber value="${interviewBuyVO.payPrice}" pattern="#,###" />원</td>
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
			url : "starters?cmd=interviewBuyStatiscticsDatesPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.AllStatisticMain").html(result);
			}
		})
	}

</script>
    