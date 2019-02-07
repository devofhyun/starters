<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.*"
	import = "java.util.*"
    import = "org.json.*"
	import="com.starters.TutoringBuyVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<%-- 차트 스크립 --%>
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/pie.js"></script>
<script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>

<%@include file="../../../../nav2.jsp"%>
<style>
#chartdiv {
  width: 100%;
  height: 500px;
  font-size: 11px;
}

.amcharts-pie-slice {
  transform: scale(1);
  transform-origin: 50% 50%;
  transition-duration: 0.3s;
  transition: all .3s ease-out;
  -webkit-transition: all .3s ease-out;
  -moz-transition: all .3s ease-out;
  -o-transition: all .3s ease-out;
  cursor: pointer;
  box-shadow: 0 0 30px 0 #000;
}

.amcharts-pie-slice:hover {
  transform: scale(1.1);
  filter: url(#shadow);
}


.far:hover {
	color: #feca01;
}
.likesName:after {
	content: ",  ";
	padding-right: 10px;
}
.likesName:last-child:after {
	content: ""
}


</style>
<%
// 튜터별 등록현황
List<Map<String, Object>> memberJob = (List<Map<String, Object>>)request.getAttribute("memberJob");
JSONArray jsonData = new JSONArray();
for(Map<String, Object> rowData: memberJob){
	JSONObject jsonRow = new JSONObject();
	jsonRow.put("country", rowData.get("middle_name")); // 넣는순서대로 인덱스가 만들어진다.
	jsonRow.put("litres", rowData.get("middleCount"));
	jsonData.put(jsonRow);
}
// 브라우저로 전송
String jsonString = jsonData.toString();
// out.print(jsonString);

%>

<script>
var chart = AmCharts.makeChart("chartdiv", {
  "type": "pie",
  "startDuration": 0,
   "theme": "light",
  "addClassNames": true,
  "legend":{
    "position":"right",
    "marginRight":100,
    "autoMargins":false
  },
  "innerRadius": "30%",
  "defs": {
    "filter": [{
      "id": "shadow",
      "width": "200%",
      "height": "200%",
      "feOffset": {
        "result": "offOut",
        "in": "SourceAlpha",
        "dx": 0,
        "dy": 0
      },
      "feGaussianBlur": {
        "result": "blurOut",
        "in": "offOut",
        "stdDeviation": 5
      },
      "feBlend": {
        "in": "SourceGraphic",
        "in2": "blurOut",
        "mode": "normal"
      }
    }]
  },
  "dataProvider": <%=jsonData%>,
  "valueField": "litres",
  "titleField": "country",
  "export": {
    "enabled": true
  }
});

chart.addListener("init", handleInit);

chart.addListener("rollOverSlice", function(e) {
  handleRollOver(e);
});

function handleInit(){
  chart.legend.addListener("rollOverItem", handleRollOver);
}

function handleRollOver(e){
  var wedge = e.dataItem.wedge.node;
  wedge.parentNode.appendChild(wedge);
}
</script>
<title>스타터스 : 후기 통계 상세</title>
<section>
	<article>
<div class="table-responsive mt-5" id="reviewInfo">
	<table class="table mt-5" style="table-layout: fixed; cursor: context-menu;">
		<tbody>
			<tr>
				<th scope="row">리뷰번호</th>
				<td colspan="5">${reviewVO.reviewId}</td>
			</tr>
			
			<tr>
				<th scope="row">제목</th>
				<td colspan="5">${reviewVO.reviewTitle}</td>
			</tr>
			<tr>
				<th scope="row">튜터링명</th>
				<td colspan="3">${reviewVO.tutoringTitle}</td>
				<th scope="row">튜터명</th>
				<td>${reviewVO.tutorRealName} <span class="text-body">( ID : ${reviewVO.tutorName} )</span></td>
			</tr>
			<tr>
				<th scope="row">작성자</th>
				<td>${reviewVO.tuteeRealName} <span class="text-body">( ID : ${reviewVO.memberId} )</span></td>
				<th scope="row" class="text-right">조회수</th>
				<td class="reviewCounts Counts" reviewId="${reviewVO.reviewId}">${reviewVO.reviewCount}</td>
				<th scope="row">작성일</th>
				<td>${reviewVO.reviewDate}</td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td colspan="5">${reviewVO.reviewContent}</td>
			</tr>
					<tr>
						<th scope="row">좋아요</th>
						<td colspan="5"><span class="Counts">${reviewVO.likeCount}</span>명<i id="likeP" class="far fa-eye-slash" style="padding-left: 10px"></i>
						</td>
					</tr>
					<tr id="likesName" style="opacity: 0;">
						<th scope="row" class="border-top-0"></th>
						<td class="border-top-0" colspan="5">
						<c:forEach items="${list}" var="userId">
						<span class="likesName">${userId.memberId}</span>
						</c:forEach>
						</td>
					</tr>
				</tbody>
	</table>
</div>
<div id="chartdiv"></div> 
<button class="btn btn-sm btn-outline-secondary mb-5" onclick="location.href='starters?cmd=reviewStatisctics'">목록</button>
<%@include file = "../../../../footer2.jsp" %>
</article>
</section>


<script src="../../assets/js/jquery.animateNumber.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js"></script>

<script>
$(document).ready(function() {

	$(".Counts").counterUp({time: 100});


	// $('#likeC').animateNumber({ number: "${reviewVO.likeCount}" });
	// $('#Counts').animateNumber({ number: "${reviewVO.reviewCount}" });
});


$("#likeP").click(function() {
	if($("#likeP").hasClass("fa-eye-slash")){
		$("#likeP").attr('class','far fa-eye');
		$( "#likesName" ).animate({opacity:1})
	} else {
		$("#likeP").attr('class','far fa-eye-slash');
		$( "#likesName" ).animate({opacity:0})
	}
});

</script>