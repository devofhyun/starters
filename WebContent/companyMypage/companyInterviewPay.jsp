<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
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
<title>스타터스 : 면접 신청</title>
<%---------------------------------------------%>


<div role="main" class="container mt-5">
  <div class="table-responsive">
  <h4 class="mb-3">면접 신청 포트폴리오 정보</h4>
    <table class="table table-striped table-sm">
      <colgroup>
        <col width="40px" />
        <col width="40%" />
        <col width="20%" />
        <col width="20%" />
        <col width="20%" />
      </colgroup>
      <thead>
        <tr class="text-center">
          <th>포트폴리오명</th>
          <th>튜티명</th>
          <th>판매가격</th>
        </tr>
      </thead>
      <tbody>
        <tr class="text-center">
          <td>${portfoliovo.portfolioTitle}</td> 
          <td>${portfoliovo.tuteeName}</td> 
          <td>${companypricevo.price }원</td>
        </tr>
      </tbody>
    </table>
  </div>
  <h4 class="mb-3">면접 신청 기업 정보</h4>
  <%--<form class="needs-validation" name="buyForm" method="post" action=""> --%>
    <div class="mb-3">
      <ul class="list-group mb-3">
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">성명</h6>
          </div>
          <span class="text-muted">${companyvo.name}</span>
        </li>
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">전화번호</h6>
          </div>
          <span class="text-muted">${companyvo.phoneNum}</span>
        </li>
        
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">결제금액</h6>
          </div>
          <div class="text-right">
          
          	<span class="text-muted" id = "payMoney">${companypricevo.price }</span><span class="text-muted" >원</span>
          </div>
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
        <input type="submit" class="btn btn-outline-info btn-sm btn-block" value="신청하기" onclick = "pay();">
      </div>
      <div class="col-6">
        <input type="button" id="cancle" class="btn btn-outline-danger btn-sm btn-block" value="취소" onclick = "cancle();">
      </div>
    </div>
</div>
<%@include file="../footer.jsp"%>

<script>
var cancle = function() {
	location.href = "starters?cmd=interviewPayCancel";
};
// 면접신청
function pay(){
var payInfo = $('#payInfo').text();
var account = $("#account option:selected").val();
var payMoney = $("#payMoney").text();
$.ajax({
	type:"POST",
	url: 'starters?cmd=interviewPayMoney',
	data:{payInfo :payInfo, account : account, payMoney : payMoney},
	success: function(data){
		//console.log("data값///"+data.trim());
		 if(data) {
      		 data = data.trim();
      	 }
		 if(data !== "") {
             window.location.href = "starters?cmd=companyInterviewApplySuccess";
          } else {
             alert("계좌번호를 선택해주세요.");
         }
	}		
});
}
</script>