<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../nav.jsp"%>
<link rel="stylesheet" href="assets/css/tutoring/tutoringBuy.css" />
<head>
<title>스타터스 : 튜터링 결제</title>
<style>
tbody {
    text-align: center;
}

</style>
</head>

<%---------------------------------------------%>

<input type="hidden" id = "tutoringNumber" value="${tutoringNumber}"/>
<input type="hidden" id = "time" value="${time}"/>
<input type="hidden" id = "price" value="${price}"/>

<div role="main" class="container mt-5">
  <div class="table-responsive">
  <h5 class="mb-3">튜터링 정보</h5>
    <table class="table table-striped table-sm">
      <colgroup>
        <col width="30%" />
        <col width="20%" />
        <col width="20%" />
        <col width="20%" />
        <col width="10%" />
      </colgroup>
      <thead>
        <tr class="text-center">
          <th>튜터링명</th>
          <th>튜터명</th>
          <th>튜터링 기간</th>
          <th>튜터링 시간</th>
          <th>튜터링 가격</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>${tutoringList.title}</td> 
          <td>${tutoringList.tutorName}</td> 
          <td>${tutoringList.startDate} ~ ${tutoringList.endDate}</td>
          <td>${time}</td> 
          <td>${tutoringList.price}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <h5 class="mb-3">튜터링 결제 정보</h5>
  <%--<form class="needs-validation" name="buyForm" method="post" action=""> --%>
    <div class="mb-3">
      <ul class="list-group mb-3">
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">성명</h6>
          </div>
          <span class="text-muted">${membervo.name}</span>
        </li>
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">전화번호</h6>
          </div>
          <span class="text-muted">${membervo.phoneNum}</span>
        </li>
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">결제수단</h6>
          </div>
          <span class="text-muted" id = "payInfo">무통장 입금</span>
        </li>
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">결제계좌</h6>
          </div>
          <select name="account" id = "account">
          <c:forEach var="accountList" items="${accountLists}">
            <option value="${accountList}">${accountList}</option>
          </c:forEach>
          </select>
        </li>
      </ul>
    </div>
  <%--</form> --%>
  <div class="row mb-5">
      <div class="col-6">
        <input type="submit" class="btn btn-outline-primary btn-sm btn-block" value="신청하기" onclick = "pay();">
      </div>
      <div class="col-6">
        <input type="button" id="cancle" class="btn btn-outline-danger btn-sm btn-block" value="취소" onclick = "cancle();">
      </div>
    </div>
</div>
<%@include file="../footer.jsp"%>

<script>
var cancle = function() {
	location.href = "starters?cmd=tutoringListDetailAction&num="+${tutoringList.tutoringId}; 
};
// 면접신청
function pay(){
var tutoringId = $("#tutoringNumber").val();
var time = $("#time").val();
var price = $("#price").val();
var account = $("#account option:selected").val();
//console.log(account);
$.ajax({
	type:"POST",
	url: 'starters?cmd=tutoringApplyRegisterAction',
	data:{tutoringId :tutoringId, time : time, price : price, account : account},
	success: function(data){
		//console.log("data값///"+data.trim());
		 if(data) {
      		 data = data.trim();
      	 }
		 if(data !== "") {
             window.location.href = "starters?cmd=tutoringInfo";
          } else {
             alert("계좌번호를 선택해주세요.");
         }
	}		
});
}
</script>