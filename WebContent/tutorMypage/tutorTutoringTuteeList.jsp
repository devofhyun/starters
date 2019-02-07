<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row list1 text-center">
	<c:forEach items="${list}" var="tutoringVO2">

		<div class="col-lg-4 col-md-6 col-sm-6" id = "${tutoringVO2.tutoringApplyId }" name = "${tutoringVO2.tutoringApplyId }" onclick = "clickDetail(this)" >
		    <div class="card daily" id = "${tutoringVO2.tutoringApplyId }" name = "${tutoringVO2.tutoringApplyId }">
	            <div class="photo"></div>
	            <div class="tutoringImage banner" tutoringIdImg="${tutoringVO2.tutoringId }" id = "${tutoringVO2.tutoringApplyId }"></div>
	            <ul>
	                <li>
				<a class="tutorstutoring text-wrap"
					href="starters?cmd=applyDailyList&num=${tutoringVO2.tutoringApplyId}"><b>${tutoringVO2.title}</b></a></li>
	                <li>${tutoringVO2.name}</li>
	            </ul>
            </div>
		</div>
	</c:forEach>
</div>

<!-- pagination -->

<div class="paging">
<c:if test="${ tutortutoringtuteeListNumber > 0}">

	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary w2">이전</button>
	</c:if>

	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary w1">${i}</button>
			</c:when>

			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary w">${i}</button>
			</c:otherwise>

		</c:choose>
	</c:forEach>

	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary w2">다음</button>
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
			url : "starters?cmd=tutorTutoringTuteeListPagingAction&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})
	}
	
	function clickDetail(detailNum) {
		var detailId = detailNum.id;
		location.href ="starters?cmd=applyDailyList&num="+detailId;
	}
	//$('.clickDetail').click(function(){
//		var name_by_id = $('.daily').attr('name');
	//	var id = $('.tutoringImage').attr('id');
	//	console.log(name_by_id);
	//	console.log(id);
		// var rStr = jumpBtn.name;
	//	<c:forEach items="${list}" var="tutoringVO2">
	//	console.log("${tutoringVO2.tutoringApplyId}");
	//		location.href ="starters?cmd=applyDailyList&num="+${tutoringVO2.tutoringApplyId};
	//	</c:forEach>
	// });
	
	
				var list1 = new Array();
				<c:forEach items="${list}" var="item1">
				list1.push("${item1.tutoringId}");
				var tutoringListId = ${item1.tutoringId};
			//	console.log("tutoringListId"+tutoringListId);
				$.ajax({
					data: list1,
					url : "starters?cmd=tutortutoringtuteeListImage&tutoringListNum="+ tutoringListId,
					async : false,
					type : "POST",
					success : function(result) {
				//		console.log("result"+result);
				//		console.log("tutoringListId"+tutoringListId);
						 $(".tutoringImage[tutoringIdImg = '" + tutoringListId + "']").html(result);
					}

				});
				</c:forEach>
		//		console.log(list1);
			//	console.log("리얼확인"+tutoringListId);

</script>