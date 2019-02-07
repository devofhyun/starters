<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.starters.MemberDAO"
	import="com.starters.CompanyVO" import="java.util.*"
	import="org.json.*"%>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	Map<String, Object> members = (Map<String, Object>) request.getAttribute("registerDateMember");
%>


<section>
	<article>
		<div class="ml-auto mb-3">
			<%--m은 마진 l은 오른쪽 auto는 콘텐츠의 넓이 만큼만 너비로 주겠다.--%>
	<div id = "bar_chart" class = "mb-3" style = "width:800px"></div> <%--barChart밑에 3px의 여유를 주겠다.--%>
			
			<%--barChart밑에 3px의 여유를 주겠다.--%>
		</div>
	</article>
</section>

<script>
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	
	function drawChart() {
		var bardata = new google.visualization.DataTable();
		bardata.addColumn('string', '나이');
		bardata.addColumn('number', '가입자 수');

	bardata.addRows([
				[ '1분기',<%=members.get("count_01")%>],
				[ '2분기',<%=members.get("count_02")%>],
				[ '3분기',<%=members.get("count_03")%>],
				[ '4분기',<%=members.get("count_04")%>] ]);
	 var barOpts = {
			    width: 800,
			    height: bardata.getNumberOfRows() * 50 + 100,
			    chart: {
			      title: '분기별 가입자 수',
			      subtitle: ''
			    },
			    bar: {groupWidth: "95%"},
			    legend: { position: "none" }
			  };

		var barchart = new google.charts.Bar(document.getElementById('bar_chart'));
		barchart.draw(bardata, google.charts.Bar.convertOptions(barOpts));

	};
</script>
