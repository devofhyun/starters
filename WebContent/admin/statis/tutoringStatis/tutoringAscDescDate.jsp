<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container content">
		<div class="board">
			<p>${tutoringAllCount}개의 튜터링</p>
			<table class="table interV">
			    <thead>
			      <tr>
			        <th>번호</th>
			        <th>튜터명</th>
			        <th>튜터링명</th>
			        <th>조회수</th>
			        <th>등록일</th>
			      </tr>
			    </thead>
			    
			<c:forEach items="${list}" var="tutoringList">
			    <tbody> 
			    	<tr onclick="location.href='starters?cmd=tutoringStatiscticsDetail&num=${tutoringList.tutoringId}'">
				        <td>${tutoringList.tutoringId}</td>
				        <td>${tutoringList.tutorName}</td>
				         <td>${tutoringList.title}</td>         
				          <td>${tutoringList.count}</td>
				         <td>${tutoringList.uploadDate}</td>
			    	</tr>
			    </tbody>
			    
			</c:forEach>
			    </table>

		</div>
	</div>
  
		
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
			url : "starters?cmd=tutoringDatesSelectActionPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.tutoringStatisticMain").html(result);
			}
		})
		//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
		//alert("starters?cmd=tutoringList?pageNum=" + rStr);
		//	$("#mainList").load(query);
		// alert(query);
	}

</script>
    