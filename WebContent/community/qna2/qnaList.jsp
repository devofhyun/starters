<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.*" import="java.util.ArrayList"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 	

<div class="container">

  <table class="table">
    <thead>
      <tr>
       <th>번호</th> 
        <th>작성자</th>
        <th>제목</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
		<tbody>
			<c:forEach items="${list}" var="qnaVO">
			
				<tr>
					 <td>${qnaVO.qnaId}</td>
					<td>${qnaVO.qnaWriterId}</td>
					<c:choose>
						<c:when test="${qnaVO.qanPasswd==''}">
							<td class="notitle">
					<a href="starters?cmd=qnaListDetailAction&qnaNum=${qnaVO.qnaId}" >${qnaVO.qnaTitle}</a>
					</td>		
						</c:when>
						<c:otherwise>
							<td class="notitle">
							<c:choose>
							<c:when test = "${qnaVO.qnaWriterId == userId || allMembers ==999}">
							<a href="starters?cmd=qnaListDetailAction&qnaNum=${qnaVO.qnaId}" >비밀글입니다.</a>
							</c:when>
							<c:otherwise>
							<a href="#" onclick="alert('작성자만 보실 수 있습니다.');">비밀글입니다.</a>
							</c:otherwise>
							</c:choose>
							
							
							
							</td>
							

						</c:otherwise>
					</c:choose>
					<td>${qnaVO.qnaDate}</td>
					<td>${qnaVO.qnaHits}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<!-- pagination -->
<div class="paging">
<c:if test="${ qnaListNumber > 0}">

	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary btn-sm w2">이전</button> &nbsp;
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
			class="btn btn-outline-secondary btn-sm w2">다음</button> &nbsp;
	</c:if>


</c:if>
</div>
<%-- 136번째줄 close --%>

	<script>
	
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=qnaListPaging&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
				   if($('button').hasClass('w1') === true){
					   $('.w1').addClass('active');
					   }
			}
		})
	
	}

		

		
	</script>
