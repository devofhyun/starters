<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div class = "abc">
<div class="container mb-5 pb-5"> 
 
  <table class="table">
    <thead>
      <tr>
		<th><input type="checkbox" id="massiveSelectCheckBox" /><label for="massiveSelectCheckBox"></label></th>
        <th>글번호</th>
        <th>대상</th>
        <th>제목</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="noticeVO">
      <tr>
		<td><input type="checkbox" class="deleteNoticeId" name="deleteNoticeId" id="${noticeVO.noticeId}" value="${noticeVO.noticeId}" /><label for="${noticeVO.noticeId}"></label></td>
        <td onclick="location.href='starters?cmd=adminNoticeListDetailAction&noticeNum=${noticeVO.noticeId}'">${noticeVO.noticeId}</td>
        <td onclick="location.href='starters?cmd=adminNoticeListDetailAction&noticeNum=${noticeVO.noticeId}'">${noticeVO.noticeMem}</td> 
        <td onclick="location.href='starters?cmd=adminNoticeListDetailAction&noticeNum=${noticeVO.noticeId}'" class="notitle">${noticeVO.noticeTitle}</td>
        <td onclick="location.href='starters?cmd=adminNoticeListDetailAction&noticeNum=${noticeVO.noticeId}'">${noticeVO.noticeDate}</td>
        <td onclick="location.href='starters?cmd=adminNoticeListDetailAction&noticeNum=${noticeVO.noticeId}'">${noticeVO.noticeHits}</td>
        
      </tr>
      </c:forEach>
      
    </tbody>
  </table>
  <c:if test = "${adminmembers == '999'}">
  <button class="btn btn-sm btn-outline-danger pull-right"  id="massiveDeleteBtn" >삭제</button>
  </c:if>
</div>
</div>
<!-- pagination -->
<div class="paging">
<c:if test="${ noticeListNumber > 0}">

	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary w2">이전</button> &nbsp;
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
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-sm btn-outline-secondary w2">다음</button> &nbsp;
	</c:if>


</c:if>
</div>
<%-- 136번째줄 close --%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script>
	
	 $("#massiveSelectCheckBox").click(function() {
        var isChecked = $(this).prop("checked");
        $(".deleteNoticeId").prop("checked", isChecked);
    });
	 
	 
	 $("#massiveDeleteBtn").click(function() {
         var isChecked = false;
         /* 각각의 클래스를 다 보면서 선택이 됬는지 안됬는지 체크한다. */
         $(".deleteNoticeId").each(function(index, data){
             if( data.checked ){
                 isChecked = data.checked;
             }
         });
         
         if(!isChecked){
             alert("삭제할 대상을 선택하세요.");
             return;
         }
         /* 사용자에게 한번 더 컨펌 */
         if( confirm("정말 삭제하시겠습니까?")){
             alert("삭제되었습니다");
             var form = $("#massiveDeleteForm");
             form.attr("method", "post");
             form.attr("action", "<c:url value='starters?cmd=adminNoticeDeleteMany' />");
             form.submit();
         }
	 });
	 
	
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=adminNoticeSearchPagingAction&pageNum=" + rStr,
			success : function(result) {
				$("div.mainList").html(result);
			}
		})
		//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
		//alert("starters?cmd=tutoringList?pageNum=" + rStr);
		//	$("#mainList").load(query);
		// alert(query);
	}
	
	
	</script>