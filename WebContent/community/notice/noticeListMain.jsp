<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import="com.starters.TutoringBuyVO" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.4.1/css/all.css' integrity='sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz' crossorigin='anonymous'>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<%@include file="../../nav.jsp"%>
<link rel="stylesheet" href="assets/css/community/noticeList.css" />
	
	

<title>스타터스 : 공지사항</title>

	
 <div class="container search">
 <br>
 <h3 class="font-weight-bold"> 공지사항 </h3><br>
<div class="row search">
 	
 	<form method="post" action="starters?cmd=noticeSearchAction"  class="col-lg-12 col-md-12col-sm-12 col-12 mx-0">
 	  <div class="input-group input-group-sm">
	    <div class="input-group-prepend">
		<select class="form-control form-control-sm" id="select" name = "searchCateg">
			<option value = "0">대상</option>
			<option value = "1">제목</option>
			<option value = "2">내용</option>
			<option value = "3">제목 + 내용</option>
		</select>
	    </div>
		<input type="text" class="form-control" id="txtsearch" name = "searchName">
	    <div class="input-group-append">
			<button type="submit" class="btn btn-block btn-secondary"  id="sbtn">검색</button>
	    </div>
	  </div>
	</form>

</div>
	<div class="row enroll">
		<div class="col-lg-10 col-md-10 col-sm-10 col-7">
			<c:if test="${adminmembers== 999}"> <!-- 튜터이면 -->
				<button class="btn btn-sm btn-outline-secondary pull-right"  id="wbtn" onclick = "noticeWrite()">글쓰기</button>
			</c:if> 
			<p>${noticeListNumber}개의 공지</p>
		</div>
		<div class="dropenroll col-lg-2 col-md-2 col-sm-2 col-5">
				<select class="tutoringListSelect  form-control-sm" onchange="abc(this.selectedIndex);">
					<option value="0" onclick="t(this)" selected="selected">---</option>
					<option value="1" onclick="t(this)">번호순</option>
					<option value="2" onclick="t(this)">최신순</option>
					<option value="3" onclick="t(this)">조회수순</option>
				</select>
		</div>
	</div>
</div>


	<c:choose>
	<c:when test = '${empty searchName}'>
	<div class="mainList">
		<jsp:include page="noticeList.jsp" />
	</div>
	</c:when>
	<c:otherwise>
	<div class="mainList">
	<jsp:include page="noticeListSearch.jsp" />
	</div>
	</c:otherwise>
	</c:choose>


<%@include file="../../footer.jsp"%>

    <script type="text/javascript">
	var noticeWrite = function(){
		location.href = "starters?cmd=noticeRegister";
	};
	
	// 조회수순, 최신순, 번호순
	function abc(selectedguy) {
		var selectBox = selectedguy;
		//console.log(selectBox);
		$.ajax({
			
			url : "starters?cmd=noticeListSelectAction&select=" + selectBox,
			success : function(result) {
				//console.log("////////////////////")
				//console.log(result)
				$("div.mainList").html(result);
				   if($('button').hasClass('w1') === true){
					   $('.w1').addClass('active');
				   }
			}
		})
	}

</script>