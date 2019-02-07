<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.PortfolioDAO"
	import="com.starters.PortfolioVO" import="com.starters.ImageVO"
	import="java.util.ArrayList"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row list1 text-center">
	<c:forEach items="${list}" var="portfolioVO">
	
		<div class="col-lg-4 col-md-4">
			<div class="card">
				<a class="portfolio"
					href="starters?cmd=portfolioListDetailAction&num=${portfolioVO.portfolioId}"> 
					<div class="portfolioImage"
						portfolioIdImg="${portfolioVO.portfolioId }"
						id="${portfolioVO.portfolioId }"></div>
				</a> 
				<div class="cardtext">
				<a class="title" href="starters?cmd=portfolioListDetailAction&num=${portfolioVO.portfolioId}">
				${portfolioVO.portfolioTitle}
				</a>
				<p class="readCount">조회수 ${portfolioVO.portfolioCount}</p>
				</div>
			</div>
		</div>
	</c:forEach>
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
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary w1">${i}</button>
			</c:when>

			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary w">${i}</button>
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
			url : "starters?cmd=PortfolioListPagingAction&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})

	}
	
	
    var list1 = new Array();
    <c:forEach items="${list}" var="item1">
    list1.push("${item1.portfolioId}");
    var portfolioListId = ${item1.portfolioId};
   // console.log("portfolioListId"+portfolioListId);
    $.ajax({
       data: list1,
       url : "starters?cmd=portfolioListImage&protfolioListNum="+ portfolioListId,
       async : false,
       type : "POST",
       success : function(result) {
         // console.log("result"+result);
        //  console.log("portfolioListId"+portfolioListId);
           $(".portfolioImage[portfolioIdImg = '" + portfolioListId + "']").html(result);
       }

    });
    </c:forEach>
	
	
</script>