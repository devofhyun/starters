<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<%@include file="../../nav2.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="assets/css/tutoring/tutee_tutoring_list.css"
	rel="stylesheet">

<title>스타터스 : 직종편집</title>
<style>
.row.mmrow {
    max-height: 400;
    overflow-y: auto;
}
input:focus {
    color: #495057!important;
    background-color: #fff!important;
    border-color: #80bdff!important;
    outline: 0!important;
    box-shadow: none!important;
}

button.btn-outline-info:focus {
	box-shadow: 0 0 0 0.1rem rgba(0,123,255,.25);
}
.middle > li {
    background-color: #eee;
    padding: 10 20;
}
@media screen and (max-width: 415px) {
	.ddd {
		display: none;
	}
}
</style>

<section>
	<article>
		<div class="container">
			<br>
			<h4>직종 편집</h4>
			<br>
			<div class="row mmrow">
			<div class="main col-12  col-sm-5  col-md-5  col-lg-3">
				<c:forEach items="${mainJob}" var="mainJob">
						<button class="btn btn-sm btn-outline-info btn-block text-left" name="${mainJob.mainCategId}" 
						onclick="p(this)" id="${mainJob.mainCategId}" value="${mainJob.mainName}">
						${mainJob.mainCategId}. ${mainJob.mainName}
						</button>
				</c:forEach>
			</div>
				<div class="middle col-12  col-sm-7  col-md-7  col-lg-9">
				</div>
			</div>
			
			
	<div class="row my-5">
	<div class="col-4">
	<h5>추가</h5>
			<!-- 대분류 등록 버튼-->
			<button type="button" class="btn btn-sm btn-outline-primary mb-2" data-toggle="modal"
				data-target="#myModal">대분류 추가</button>
	
			<!-- 대분류 등록 모달 -->
			<form method="post" action="starters?cmd=mainCategRegister"
				id="register">
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">대분류 추가</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								추가할 대분류명 <input type="text" name="mainName"
									class="form-control form-control-sm">
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm btn-outline-secondary list" id="btnSubmit">추가하기</button>
								<button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">취소</button>
							</div>
	
						</div>
					</div>
				</div>
			</form>
	
			<!-- 중분류 등록 버튼-->
			<button type="button" class="btn btn-sm btn-outline-primary" data-toggle="modal"
				data-target="#middleModal">중분류 추가</button>
	
			<!-- 중분류 등록 모달 -->
			<form method="post" action="starters?cmd=middleCategRegister"
				id="middleRegister">
				<div class="modal" id="middleModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">증분류 등록</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<table class="table table-borderless">
									<tr>
										<th>대분류명</th>
										<td><select name="selectBox" id="selectBox"
											class="select_02 form-control form-control-sm">
												<c:forEach items="${mainJob}" var="mainJob">
													<option value="${mainJob.mainCategId}">${mainJob.mainName}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<th>등록할 중분류명</th>
										<td><input type="text" name="middleName"
											class="form-control form-control-sm"></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm btn-outline-secondary list" id="btnSubmit">추가하기</button>
								<button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
			</form>
	</div>
	
	<div class="col-4">
	<h5>수정</h5>
			<!-- 대분류 수정 버튼-->
			<button type="button" class="btn btn-sm btn-outline-dark mb-2" data-toggle="modal"
				data-target="#mainModify">대분류 수정</button>
	
			<!-- 대분류 수정 모달 -->
			<form method="post" action="starters?cmd=updateMainJob" id="mainModi">
				<div class="modal" id="mainModify">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">대분류 수정</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<table class="table table-borderless">
									<tr>
										<th>대분류명</th>
										<td><select name="selectBox2" id="selectBox2"
											class="select_02 form-control form-control-sm">
												<c:forEach items="${mainJob}" var="mainJob">
													<option value="${mainJob.mainCategId}">${mainJob.mainName}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<th>수정할 대분류명</th>
										<td><input type="text" name="mainNameModify"
											class="form-control form-control-sm"></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm btn-outline-secondary list" id="btnSubmit">수정하기</button>
								<button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
			</form>
	
			<!-- 중분류 수정 버튼-->
			<button type="button" class="btn btn-sm btn-outline-dark" data-toggle="modal"
				data-target="#middleModify">중분류 수정</button>
	
			<!-- 중분류 수정 모달 -->
			<form method="post" action="starters?cmd=updateMiddleJob"
				id="middleModi">
				<div class="modal" id="middleModify">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">중분류 수정</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<table class="table table-borderless">
									<tr>
										<th>대분류명</th>
										<td><select name="selectMainCategory" id="selectBox3"
											class="select_02 form-control form-control-sm" value=""
											onclick="selectMain(this)">
												<c:forEach items="${mainJob}" var="mainJob">
													<option value="${mainJob.mainCategId}">${mainJob.mainName}</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<th>중분류명</th>
										<td class="middleSelect">
											<select name="selectBox4" id="selectBox4" class="selectBox4 form-control form-control-sm">
													
											</select>
										</td>
									</tr>
									<tr>
										<th>수정할 중분류명</th>
										<td><input type="text" name="middleNameModify"
											class="form-control form-control-sm"></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm btn-outline-secondary list" id="btnSubmit">수정하기</button>
								<button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
			</form>
	</div>
	
	<div class="col-4">
	<h5>삭제</h5>
			<!-- 대분류 삭제 버튼-->
			<button type="button" class="btn btn-sm btn-outline-danger mb-2" data-toggle="modal"
				data-target="#mainDelete">대분류 삭제</button>
	
			<!-- 대분류 삭제 모달 -->
			<form method="post" action="starters?cmd=deleteMainJob"
				id="mainDeleteId">
				<div class="modal" id="mainDelete">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">대분류 삭제</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<table class="table table-borderless">
									<tr>
										<th>대분류명</th>
										<td><select name="deleteMainId" id="selectBox2"
											class="select_02 form-control form-control-sm">
												<c:forEach items="${mainJob}" var="mainJob">
													<option value="${mainJob.mainCategId}">${mainJob.mainName}</option>
												</c:forEach>
										</select></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm btn-outline-secondary list" id="btnSubmit">삭제하기</button>
								<button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
			</form>
	
			<!-- 중분류 삭제 버튼-->
			<button type="button" class="btn btn-sm btn-outline-danger" data-toggle="modal"
				data-target="#middleDelete">중분류 삭제</button>
	
			<!-- 중분류 삭제 모달 -->
			<form method="post" action="starters?cmd=deleteMiddleJob"
				id="middleDeleteId">
				<div class="modal" id="middleDelete">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">중분류 삭제</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body">
								<table class="table table-borderless">
									<tr>
										<th>대분류명</th>
										<td><select name="deleteMiddleCategory" id="deleteMiddleId" class="deleteMiddle form-control form-control-sm" 
										value="" onclick="deleteMiddle(this)">
												<c:forEach items="${mainJob}" var="mainJob">
													<option value="${mainJob.mainCategId}">${mainJob.mainName}</option>
												</c:forEach>
										</select>
										</td>
									</tr>
									<tr>
										<th>중분류명</th>
										<td>
											<select name="selectBox5" id="selectBox5" class="selectBox5 form-control form-control-sm">
													
											</select>
										</td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm btn-outline-secondary list" id="btnSubmit">삭제하기</button>
								<button type="button" class="btn btn-sm btn-outline-danger" data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
			</form>
	</div>
	</div>
</div>
	

		<%@include file="../../footer.jsp"%>
	</article>
</section>

<script>


	function p(btn) {
		var mainCategId = btn.name;
		var Id = '#' + btn.name;
		var mainCategName = $(Id).attr('value');
		
		console.log("mainCategId" + mainCategId);

		$(".main button").removeClass('active');
		
		
		$.ajax({
			url : "starters?cmd=adminGetMiddleJobList&mainCategId=" + mainCategId,
			success : function(result) {
				$(Id).addClass('active');
				$(".middle").html(result);
				$(".middle").prepend('<h5>' + mainCategName + '</h5>');

			}
		});
	}

	function selectMain(op) {
		var selectMainCategId = $('#selectBox3').val();
		console.log("selectMainCategId" + selectMainCategId);
		$.ajax({
			url : "starters?cmd=selectMainJob&selectMainCategId="+ selectMainCategId,
			success : function(result) {
				console.log("test");
				$(".selectBox4").html(result);
			}
		});
	}
	
	function deleteMiddle(op) {
		var deleteMiddleCategId = $('#deleteMiddleId').val();
		console.log("deleteMiddleCategId" + deleteMiddleCategId);
		$.ajax({
			url : "starters?cmd=deleteSelectMainJob&deleteMiddleCategId="+ deleteMiddleCategId,
			success : function(result) {
				console.log("test");
				$(".selectBox5").html(result);
			}
		});
	}
</script>

