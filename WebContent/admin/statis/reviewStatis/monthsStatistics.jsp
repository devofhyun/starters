<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.*"
    import = "org.json.*"
    import="com.starters.*"
%>
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
	        ['1월', <%=annualRevenue.get("count_01")%>],
	        ['2월', <%=annualRevenue.get("count_02")%>],
	        ['3월', <%=annualRevenue.get("count_03")%>],
	        ['4월', <%=annualRevenue.get("count_04")%>],
	        ['5월', <%=annualRevenue.get("count_05")%>],
	        ['6월', <%=annualRevenue.get("count_06")%>],
	        ['7월', <%=annualRevenue.get("count_07")%>],
	        ['8월', <%=annualRevenue.get("count_08")%>],
	        ['9월', <%=annualRevenue.get("count_09")%>],
	        ['10월', <%=annualRevenue.get("count_10")%>],
	        ['11월', <%=annualRevenue.get("count_11")%>],
	        ['12월', <%=annualRevenue.get("count_12")%>]
	    ]);

	    var lineOpts = {
	      chart: {
	        title: '<%= yyyy%> 년 월별 리뷰현황',
	        subtitle: ''
	      },
	      width: 800,
	      height: 500,
	     
	     
	    };

	    var linechart = new google.charts.Line(document.getElementById('line_chart'));
	    linechart.draw(linedata, google.charts.Line.convertOptions(lineOpts));
	
};






</script>

	<div class = "ml-auto mb-3"><%--m은 마진 l은 오른쪽 auto는 콘텐츠의 넓이 만큼만 너비로 주겠다.--%>
	<div id = "line_chart" style = "width:800px"></div>
	</div>