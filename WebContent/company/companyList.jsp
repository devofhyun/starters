<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.*" import="java.util.ArrayList"%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!-- 기업리스트 -->
<table class="table companyList">
	<thead>
		<tr>
			<th>기업명</th>
			<th>기업 주소</th>
			<th>위치</th>
			<th>자세히</th>
		</tr>
	</thead>

	<tbody id="myTable">
		<c:forEach items="${list}" var="CompanyVO" varStatus="mv">
			<tr>

				<td>${CompanyVO.name}</td>
				<td>${CompanyVO.companyUrl}</td>
				<td>${CompanyVO.address}</td>
				<!-- href="starters?cmd=companyListDetailAction&num=${CompanyVO.companyId}"-->
				<td>
					<!-- <button id="addBtn" class="addBtn" name="${CompanyVO.companyId}"
						data-toggle="modal" data-target="#details">더보기</button>-->
					<button type="button" class="btn btn-sm btn-secondary"
						data-toggle="modal" data-target="#myModal${CompanyVO.companyId}"
						id="addBtn ${CompanyVO.companyId}" name="${CompanyVO.companyId}"
						onclick="a(this)">더보기</button>

					<div class="modal fade" id="myModal${CompanyVO.companyId}"
						role="dialog">
						<div class="modal-dialog modal-dialog-centered">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									기업정보
									<button type="button" class="close" data-dismiss="modal" onclick="Remove()">&times;</button>
								</div>
								<div class="modal-body">
										<div class="mainimg" companyIdImg="${CompanyVO.companyId}">
											
										</div>

									<div class="table" id="compinfo">
										<table>
											<colgroup>
												<col class="col1" />
												<col class="col2" />
												<col class="col3" />
												<col class="col4" />
											</colgroup>
											<tbody>
												<tr>
													<th scope="row">기업명</th>
													<td>${CompanyVO.name}</td>
													<th scope="row">연락처</th>
													<td>${CompanyVO.phoneNum}</td>
												</tr>
												<tr>
													<th scope="row">대표전화</th>
													<td colspan="3">${CompanyVO.phoneNum}</td>
												</tr>
												<tr>
													<th scope="row">주소</th>
													<td colspan="3">${CompanyVO.address}</td>
												</tr>
												<tr>
													<th scope="row">회사소개</th>
													<td colspan="3">${CompanyVO.companyIntroduce}</td>
												</tr>
												<tr>
													<th scope="row">홈페이지</th>
													<td colspan="3"><a href="${CompanyVO.companyUrl}">${CompanyVO.companyUrl}</a></td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>


				</td>

			</tr>
		</c:forEach>
	</tbody>

</table>
<!-- <div id="details" class="modalDetail modal fade " role="dialog"></div>-->
<!-- Modal -->


<!-- 기업리스트 끝 -->
<!-- pagination -->
	<div class="paging">
		<c:if test="${ companyAllCount > 0}">
		
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
<!-- Bootstrap core JavaScript -->


<script>
	// 페이징 처리
	function p(jumpBtn) {
		var rStr = jumpBtn.name;
		// alert(rStr);
		$.ajax({
			url : "starters?cmd=companyListPagingAction&pageNum=" + rStr,
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

	function a(button) {
		var buttonName = button.name;
		//console.log(buttonName);
		//location.href = "starters?cmd=companyListModal&companyId=" + buttonName
		$.ajax({ // .ajax({객체를 집어넣는 거야})
			url : "starters?cmd=companyListModal&companyId=" + buttonName,
			success : function(result) {
				$(".mainimg[companyIdImg = '" + buttonName + "']").html(result);
			}
 		});
		
		
	}
	
	function Remove(){
		var Child = $('.carousel-inner').children()
		var lis = $('.carousel-indicators').children()
		lis.remove(); // ul 삭제
		Child.remove(); // 이미지 삭제
	}


	//	$("#addBtn").click(function() {
	//		console.log("123");
	//		var target = document.getElementById("addBtn").name;
	// var target2 = document.getElementById("addBtn").data-target;
	//		console.log(target);
	// console.log("target2/"+target2);
	//alert(target);
	//alert("starters?cmd=companyListDetailAction&id=" + target);
	//location.href = "starters?cmd=companyList&id=" + target
	// location.href = "starters?cmd=companyListDetailAction&id=target"

	//alert(inputId);	 
	//	$.ajax({ // .ajax({객체를 집어넣는 거야})
	//	url : "starters?cmd=compnayModalDetailAction&id=" + target,
	//	type : "post",
	//		success : function(result) {
	//			$("#mainModal").html(result);
	//	}
	//	});
	//});
</script>