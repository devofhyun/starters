<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.*"
    import = "org.json.*"
    import="com.starters.*"
    import = "java.text.SimpleDateFormat"
%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String yyyy = (String)request.getAttribute("yyyy");
Map<String, Object> annualRevenue = (Map<String, Object>)request.getAttribute("annualRevenue");

%>

<script>
// 차트 자바스크립(bar.html에서 가져옴)
// 이 두줄은 한번만 호출
google.charts.load('current', {'packages':['bar', 'line']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() { // 여기다가 필요한 차트를 계속 붙여넣는다.
	// line chart부분
	   var linedata = new google.visualization.DataTable();
	    linedata.addColumn('string', ' ');
	    linedata.addColumn('number', '후기갯수');

	    linedata.addRows([
	        ['1분기', <%=annualRevenue.get("count_01")%>],
	        ['2분기', <%=annualRevenue.get("count_02")%>],
	        ['3분기', <%=annualRevenue.get("count_03")%>],
	        ['4분기', <%=annualRevenue.get("count_04")%>]
	    ]);

	    var lineOpts = {
	      chart: {
	        title: '<%= yyyy%> 년 분기별 리뷰 현황',
	        subtitle: ''
	      },
	      width: 800,
	      height: 500
	    };

	    var linechart = new google.charts.Line(document.getElementById('line_chart'));
	    linechart.draw(linedata, google.charts.Line.convertOptions(lineOpts));
	
};






</script>

	<div class = "ml-auto mb-3"><%--m은 마진 l은 오른쪽 auto는 콘텐츠의 넓이 만큼만 너비로 주겠다.--%>
	<div id = "line_chart" style = "width:800px"></div>
	<div style="width: 680px">
		<button class="btn btn-sm btn-outline-info"
		id="${yyyy}" name="${yyyy-1}" onclick="prev(this)">
		이전년도</button>
		<c:if test="${now > yyyy}">
			<button class="btn btn-sm btn-outline-info pull-right"
			id="${yyyy+1}" name="${yyyy+1}" onclick="after(this)">
				다음년도</button>
		</c:if>
	
	</div>
	
	</div>
	<script>
    function prev(jumpBtn) {
		var rStr = jumpBtn.name;
		// console.log(rStr);
		$.ajax({
			url : "starters?cmd=reviewPartStatisPrev&yearNum=" + rStr,
			success : function(result) {
				$("div.reviewStatisticMain").html(result);
			}
		})

	}

	function after(jumpBtn) {
		var rStr = jumpBtn.name;
		//console.log(rStr);
		$.ajax({
			url : "starters?cmd=reviewPartStatisAfter&yearNum=" + rStr,
			success : function(result) {
				$("div.reviewStatisticMain").html(result);
			}
		})

	}
	</script>