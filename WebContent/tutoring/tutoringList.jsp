<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.TutoringDAO"
	import="com.starters.TutoringVO2" import="java.util.*"
	import = "java.text.SimpleDateFormat"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<div class="row list1 text-center">
<c:set var="today" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="now" type="date" value="${today}" pattern="YYYY-MM-dd"/>
	<c:forEach items="${list}" var="tutoringVO2">
		<c:choose>
		<c:when test = "${now > tutoringVO2.startDate}">
		<div class="col-lg-4 col-md-4 col-sm-6  col-12">
			<div class="card" onclick="location.href='starters?cmd=tutoringListDetailAction&num=${tutoringVO2.tutoringId}'">
				<a class="portfolio" href="starters?cmd=tutoringListDetailAction&num=${tutoringVO2.tutoringId}">
					<div class="tutoringImage" tutoringIdImg="${tutoringVO2.tutoringId }" id = "${tutoringVO2.tutoringId }">
					</div>
				</a>
					<div class="cardtext">
						<a class="title" 
							href="starters?cmd=tutoringListDetailAction&num=${tutoringVO2.tutoringId}" id="${tutoringVO2.tutoringId }">${tutoringVO2.title}</a>
						<p class="readCount">조회수 ${tutoringVO2.count}</p>
						<a class="soldoutbox" 
							>SOLD OUT</a>
					</div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
		<div class="col-lg-4 col-md-4 col-sm-6  col-12"">
			<div class="card">
				<a class="portfolio" href="starters?cmd=tutoringListDetailAction&num=${tutoringVO2.tutoringId}">
					
					<div class="tutoringImage" tutoringIdImg="${tutoringVO2.tutoringId }" id = "${tutoringVO2.tutoringId }">
					</div>
					
				</a>
					<div class="cardtext">
						<a class="title" 
							href="starters?cmd=tutoringListDetailAction&num=${tutoringVO2.tutoringId}" id="${tutoringVO2.tutoringId }">${tutoringVO2.title}</a>
						<p class="readCount">조회수 ${tutoringVO2.count}</p>
					</div>
			</div>
		</div>
		</c:otherwise>
		</c:choose>
		
	</c:forEach>
</div>

<!-- pagination -->
<div class="paging">
<c:if test="${ tutoringListNumber > 0}">

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



<%-- 136번째줄 close --%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>


	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=TutoringListPagingAction&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
				   if($('button').hasClass('w1') === true){
					   $('.w1').addClass('active');
				   }
			}
		})
		//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
		//alert("starters?cmd=tutoringList?pageNum=" + rStr);
		//	$("#mainList").load(query);
		// alert(query);
	}
	
//	$(document).ready(
//			function() {
	
				var list1 = new Array();
				<c:forEach items="${list}" var="item1">
				list1.push("${item1.tutoringId}");
				var tutoringListId = ${item1.tutoringId};
			//	console.log("tutoringListId"+tutoringListId);
				$.ajax({
					data: list1,
					url : "starters?cmd=tutoringListImage&tutoringListNum="+ tutoringListId,
					async : false,
					type : "POST",
					success : function(result) {
						//data = data.replace(/(^\s*)|(\s*$)/gi, "");
						// $("div.tutoringImage").html(result);
						// console.log("result"+result);
						// console.log("tutoringListId"+tutoringListId);
						//$(".tutoringImage").html(result);
						 $(".tutoringImage[tutoringIdImg = '" + tutoringListId + "']").html(result);
					}

				});
				</c:forEach>
				// console.log(list1);
				// console.log("리얼확인"+tutoringListId);
//				$.ajax({
//					data: list1,
//					url : "starters?cmd=tutoringListImage&tutoringListNum="+ list1,
//					async : false,
//					type : "POST",
	//				success : function(result) {
//						//data = data.replace(/(^\s*)|(\s*$)/gi, "");
//						// $("div.tutoringImage").html(result);
//						console.log("result"+result);
//						$(".tutoringImage").html(result);
//						 $(".tutoringImage[tutoirngIdImg = '" + tutoringListId + "']").html(result);
//					}

//				});



//			});
</script>







