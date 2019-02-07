<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.CompanyVO" import="java.util.*"
	import="org.json.*"%>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	Map<String, Object> members = (Map<String, Object>) request.getAttribute("ageMember");
%>

		<div class="ml-auto mb-3">
			<%--m은 마진 l은 오른쪽 auto는 콘텐츠의 넓이 만큼만 너비로 주겠다.--%>
	<div id = "bar_chart" class = "mb-3" style = "width:800px"></div> <%--barChart밑에 3px의 여유를 주겠다.--%>
			
			<%--barChart밑에 3px의 여유를 주겠다.--%>
		</div>

<script>
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	
	function drawChart() {
		var bardata = new google.visualization.DataTable();
		bardata.addColumn('string', '나이');
		bardata.addColumn('number', '가입자 수');

	bardata.addRows([ [ '10대',<%=members.get("age10")%>],
				[ '20대',<%=members.get("age20")%>],
				[ '30대',<%=members.get("age30")%>],
				[ '40대',<%=members.get("age40")%>],
				[ '50대',<%=members.get("age50")%>],
				[ '60대',<%=members.get("age60")%>] ]);
	 var barOpts = {
			    width: 800,
			    height: bardata.getNumberOfRows() * 50 + 100,
			    chart: {
			      title: '연령별 가입자 수',
			      subtitle: ''
			    },
			    bar: {groupWidth: "95%"},
			    legend: { position: "none" }
			  };

		var barchart = new google.charts.Bar(document.getElementById('bar_chart'));
		barchart.draw(bardata, google.charts.Bar.convertOptions(barOpts));

	};
</script>
