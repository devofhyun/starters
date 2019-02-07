<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.*"
    import = "org.json.*"
    import="com.starters.*"
%>
<%
// 월간 매출 현황
String yyyyMM = (String)request.getAttribute("yyyyMM");
List<Map<String, Object>> revenue = (List<Map<String, Object>>)request.getAttribute("revenue");
JSONArray jsonData = new JSONArray();
for(Map<String, Object> rowData: revenue){
	JSONArray jsonRow = new JSONArray();
	jsonRow.put(rowData.get("title")); // 넣는순서대로 인덱스가 만들어진다.
	jsonRow.put(rowData.get("tutoring_apply_count"));
	jsonData.put(jsonRow);
}
%>

<script>
// 차트 자바스크립(bar.html에서 가져옴)
// 이 두줄은 한번만 호출
google.charts.load('current', {'packages':['bar', 'line']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() { // 여기다가 필요한 차트를 계속 붙여넣는다.
	
  var bardata = new google.visualization.DataTable();
  bardata.addColumn('string', '튜터링명'); // 첫번째 인덱스 명
  bardata.addColumn('number', '튜터링 신청수'); // 두번째 인덱스 명
  bardata.addRows(<%= jsonData.toString()%>);

  var barOpts = {
    width: 800,
    height: bardata.getNumberOfRows() * 50 + 100, // 보통 예제는 BAR차트의 높이를 지정해줬지만 여기서는 바의 갯수만큼 높이를 자동 조절, getNumberOfRows--> ROW의 건수
    
    chart: {
      title: '<%= yyyyMM%> 월간 튜터링 신청 현황',
      subtitle: ''
    },
    bars: 'horizontal', // Required for Material Bar Charts.
    bar: {groupWidth: "50%"},
    legend: { position: "none" },
    hAxis: { ticks: [5,10,15,20] }
    // hAxis: {maxValue:35,minValue:0}
   //hAxis: { 
    //	 ticks: [1,2,3,4,5] 
   // } 
  };


  var barChart = new google.charts.Bar(document.getElementById('bar_chart'));
  barChart.draw(bardata, barOpts);
};






</script>

	<div class = "ml-auto mb-3"><%--m은 마진 l은 오른쪽 auto는 콘텐츠의 넓이 만큼만 너비로 주겠다.--%>
	<div id = "bar_chart" class = "mb-3" style = "width:800px"></div> <%--barChart밑에 3px의 여유를 주겠다.--%>
	</div>