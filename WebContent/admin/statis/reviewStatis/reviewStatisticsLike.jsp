<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container content">
		<div class="board">
			<p>${reviewAllCount}개의 후기</p>
			<table class="table interV">
			    <thead>
			      <tr>
			        <th>번호</th>
			        <th>제목</th>
			        <th>작성자</th>
			        <th>좋아요수</th>
			        <th>등록일</th>
			      </tr>
			    </thead>
			    
			<c:forEach items="${list}" var="reviewList">
			    <tbody> 
			    	<tr onclick="location.href='starters?cmd=reviewStatiscticsDetail&num=${reviewList.reviewId}'">
				        <td>${reviewList.reviewId}</td>
				         <td>${reviewList.reviewTitle}</td>
				         <td>${reviewList.tuteeRealName}</td>
				         <td>${reviewList.likeCount}</td>
				         <td>${reviewList.reviewDate}</td>
			    	</tr>
			    </tbody>
			</c:forEach>
			    </table>

		</div>
	</div>
  
			<!-- pagination -->
<div class="paging">
<c:if test="${ reviewAllCount > 0}">

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
			url : "starters?cmd=reviewStatiscticsLikePaging&pageNum=" + rStr,
			success : function(result) {
				$("div.reviewStatisticMain").html(result);
			}
		})
		//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
		//alert("starters?cmd=tutoringList?pageNum=" + rStr);
		//	$("#mainList").load(query);
		// alert(query);
	}

</script>
    
	

    