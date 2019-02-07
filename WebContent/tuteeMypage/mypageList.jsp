<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.ReviewVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<hr>
<!-- /row list -->
<h5>튜터링 목록</h5>
<div class="row text-center mb-5 pb-5">
	<c:forEach items="${tutoringMypageList}" var="tutoringMypageList">
		<div class="col-lg-4 col-md-4 mb-5">
			<div class="card">
				<div class="tutoringImage"
					tutoringIdImg="${tutoringMypageList.tutoringId }"
					id="${tutoringMypageList.tutoringId }"></div>
				<p class="title">${tutoringMypageList.title}</p>
				<a class="tutoringDaily" 
					href="starters?cmd=tuteeDailyList&num=${tutoringMypageList.tutoringApplyId}" >
				<button type="button" class="btn btn-outline-secondary">일지로 이동</button></a>
			</div>
		</div>
	</c:forEach>
</div>

<!-- /row -->

<div class="paging">
<!-- pagination -->
<c:if test="${ tutoringAllCount > 0}">
	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary w2 mr-1">이전</button>
   		</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary w1 mr-1">${i}</button>
			</c:when>
			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-secondary w mr-1">${i}</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary w2">다음</button>
   		</c:if>
</c:if>
</div>


<script src="assets/js/swiper.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>

/**페이징*/
function p(jumpBtn) {
	var rStr = jumpBtn.name;
	$.ajax({
		url : "starters?cmd=tuteeMypageListPaging&pageNum=" + rStr,
		success : function(result) {
			$("div.mypage").html(result);
		}
	})
}

	var list1 = new Array();
	<c:forEach items="${tutoringMypageList}" var="tutoringMypageList">
	list1.push("${tutoringMypageList.tutoringId}");
	var tutoringListId = ${tutoringMypageList.tutoringId};
	console.log("tutoringListId이다" + tutoringListId);
	$.ajax({
		data : list1,
		url : "starters?cmd=tuteetutoringListImage&tutoringListNum="
				+ tutoringListId,
		async : false,
		type : "POST",
		success : function(result) {
			console.log("result" + result);
			console.log("tutoringListId" + tutoringListId);
			$(".tutoringImage[tutoringIdImg = '" + tutoringListId + "']").html(
					result);
		}

	});
	</c:forEach>
	console.log(list1);
	console.log("리얼확인" + tutoringListId);
</script>
