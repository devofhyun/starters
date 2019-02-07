<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.CompanyVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


		<div class="col-lg-10 col-md-10">
			<p>${adminMemberCompanyListNumber}명</p>
		</div>
		<!-- 기업리스트 -->
		<table class="table companyList">
			<thead>
				<tr>
					<th>아이디</th>
					<th>기업명</th>
					<th>기업 주소</th>
					<th>위치</th>
					<th>가입날짜</th>

				</tr>
			</thead>

			<tbody id="myTable">
				<c:forEach items="${list}" var="CompanyVO" varStatus="mv">
					<tr onclick="location.href='starters?cmd=adminCompanyDetailAction&id=${CompanyVO.companyId}'">
						<td>${CompanyVO.companyId}</td>
						<td>${CompanyVO.name}</td>
						<td>${CompanyVO.companyUrl}</td>
						<td>${CompanyVO.address}</td>
						<td>${CompanyVO.registerDate}</td>
		
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<!-- <div id="details" class="modalDetail modal fade " role="dialog"></div>-->
		<!-- Modal -->


		<!-- 기업리스트 끝 -->
		<!-- pagination -->
		<div class="paging my-5">
			<c:if test="${ adminMemberCompanyListNumber > 0}">

				<c:if test="${startPage > pageBlock}">
					<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
						class="btn btn-sm btn-outline-dark w2">이전</button>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<c:choose>
						<c:when test="${currentPage == i}">
							<button id="ju" name="${i}" onclick="p(this)"
								class="btn btn-sm btn-outline-dark w1">${i}</button>
						</c:when>

						<c:otherwise>
							<button id="ju" name="${i}" onclick="p(this)"
								class="btn btn-sm btn-outline-dark w">${i}</button>
						</c:otherwise>

					</c:choose>
				</c:forEach>

				<c:if test="${endPage < pageCount }">
					<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
						class="btn btn-sm btn-outline-dark w2">다음</button>
				</c:if>


			</c:if>
		</div>


<script>

	// 모달 처리
	// 버튼 변수 지정
	//var btn = document.getElementById("addBtn");

	// 모달 값 불러오기
	//	var modal = document.getElementById('companyModal');

	// 닫기 변수 지정
	//	var span = document.getElementsByClassName("close")[0];

	// 버튼 누르면 
	//	btn.onclick = function() {
	//		modal.style.display = "block";
	//	}

	// x값 누르면
	//	span.onclick = function() {
	//		modal.style.display = "none";
	//	}

	// 모달 밖에 아무데나 누르면
	//window.onclick = function(event) {
	//		if (event.target == modal) {
	//			modal.style.display = "none";
	//	}
	//	}

	// 페이징 처리
	function p(jumpBtn) {
      var rStr = jumpBtn.name;
      // alert(rStr);
      $.ajax({
         url : "starters?cmd=adminMemberCompanyListSelectPaging&pageNum=" + rStr,
         success : function(result) {
            $("div.mainList").html(result);
         }
      })
   }
	
	
</script>