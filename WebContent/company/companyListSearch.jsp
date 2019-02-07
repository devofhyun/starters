<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.MemberVO" import="java.util.ArrayList"%>
	
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
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									기업정보
									<button type="button" class="close" data-dismiss="modal" onclick="Remove()">&times;</button>
								</div>
								<div class="modal-body">
										<div class="mainimg" companyIdImg="${CompanyVO.companyId}">
											<!-- <img
												src="../StartersSevlet/assets/companyImage/${images}" /> -->
											<%-- 	<%ArrayList<ImageVO> imagefiles = (ArrayList<ImageVO>) request.getAttribute("images"); --%>
												<!-- out.print("///////////");
												out.print("imagefiles" +  imagefiles);
												String imagefile = null;
												for (ImageVO image : images){
													out.print("images" +  image.getImg());
													// if(image.getImg().equals(imagefile) == false){
														out.print("<img src = \"" + image.getImg() + "\"/>");
													//imagefile = image.getImg();
													}
												}
												%> --> 
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
					class="btn btn-sm btn-outline-dark w2">이전</button>
			</c:if>
		
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<c:choose>
					<c:when test="${currentPage == i}">
						<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-dark w1">${i}</button>
					</c:when>
		
					<c:otherwise>
						<button id="ju" name="${i}" onclick="p(this)" class="btn btn-sm btn-outline-dark w">${i}</button>
					</c:otherwise>
		
				</c:choose>
			</c:forEach>
			
				<c:if test="${endPage < pageCount }">
					<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
						class="btn btn-sm btn-outline-dark w2">다음</button>
				</c:if>
		
		
		</c:if>
	</div>
<!-- Bootstrap core JavaScript -->

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
			url : "starters?cmd=companySearchListPaging&pageNum=" + rStr,
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
</script>