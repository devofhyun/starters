<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="assets/css/fSelect.css" rel="stylesheet" type="text/css">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 없애도 됨 -->

<select name="middleSelect" class="middleSelect" id="middleSelect"
	style="width: 150px;" multiple="multiple">
	<c:forEach items="${middleCategNames}" var="middleCategNames">
		<option value="${middleCategNames.middleCategId}">${middleCategNames.middleName}</option>
	</c:forEach>
</select>
<p class="message">최대 3개까지 선택해주세요.</p>


<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<!-- 부트스트랩 date -->
<script type='text/javascript'
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/fSelect.js"></script>

<script type="text/javascript">
	// 직종입력
//	$(function() {
   //     $('.middleSelect').fSelect();
    // });




</script>
