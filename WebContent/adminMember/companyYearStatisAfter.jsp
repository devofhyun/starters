<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="org.json.*"
	import="com.starters.*" import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<fmt:formatDate var="now" type="date" value="${today}" pattern="YYYY" />
<%
String yyyy = (String)request.getAttribute("yyyy");
Map<String, Object> registerDateCompany = (Map<String, Object>)request.getAttribute("registerDateCompany");
%>

<div class="ml-auto mb-3">
	<%--m은 마진 l은 오른쪽 auto는 콘텐츠의 넓이 만큼만 너비로 주겠다.--%>
	<div id="line_chart" style="width: 800px"></div>
	<div id="line_chart2" style="width: 800px"></div>
</div>
<div class="years">
<button class="btn btn-sm btn-outline-info"  id="${yyyy}" name="${yyyy-1}" onclick="prev(this)">
	이전년도</button>
<c:if test="${now > yyyy}">
	<button class="btn btn-sm btn-outline-info pull-right"  id="${yyyy+1}" name="${yyyy+1}" onclick="after(this)">
		다음년도</button>
</c:if>

</div>
<script type="text/javascript">
//차트 자바스크립(bar.html에서 가져옴)
//이 두줄은 한번만 호출
google.charts.load('current', {'packages':['line']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() { // 여기다가 필요한 차트를 계속 붙여넣는다.
	// line chart부분
	   var linedata = new google.visualization.DataTable();
	    linedata.addColumn('string', '분기');
	    linedata.addColumn('number', '가입자 수');

	    linedata.addRows([
	        ['1분기', <%=registerDateCompany.get("count_01")%>],
	        ['2분기', <%=registerDateCompany.get("count_02")%>],
	        ['3분기', <%=registerDateCompany.get("count_03")%>],
	        ['4분기', <%=registerDateCompany.get("count_04")%>]
	    ]);

	    var lineOpts = {
	      chart: {
	        title: '<%= yyyy%> 년 분기별 기업 등록 현황',
	        subtitle: ''
	      },
	      width: 800,
	      height: 500
	    };

	    var linechart = new google.charts.Line(document.getElementById('line_chart'));
	    linechart.draw(linedata, google.charts.Line.convertOptions(lineOpts));
	    
};




      function prev(jumpBtn) {
    		var rStr = jumpBtn.name;
    		// console.log(rStr);
    		$.ajax({
    			url : "starters?cmd=companyYearStatisPrev&yearNum=" + rStr,
    			success : function(result) {
    				$("div.AllStatisticMain").html(result);
    			}
    		})

    	}

    	function after(jumpBtn) {
    		var rStr = jumpBtn.name;
    		//console.log(rStr);
    		$.ajax({
    			url : "starters?cmd=companyYearStatisAfter&yearNum=" + rStr,
    			success : function(result) {
    				$("div.AllStatisticMain").html(result);
    			}
    		})

    	}
    </script>
