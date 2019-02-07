<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.*"
    import = "org.json.*"
    import="com.starters.*"
%>
<style>
#chartdiv,#chartdiv2{
	width	: 100%;
	height	: 500px;
}			
</style>

<%
// 튜터별 등록현황
List<Map<String, Object>> tutorReview = (List<Map<String, Object>>)request.getAttribute("tutortutoring");
JSONArray jsonData = new JSONArray();
for(Map<String, Object> rowData: tutorReview){
	JSONObject jsonRow = new JSONObject();
	jsonRow.put("name", rowData.get("name")); // 넣는순서대로 인덱스가 만들어진다.
	jsonRow.put("points", rowData.get("tutoring_count"));
	//jsonRow.put("color", "#7F8DA9"); // 넣는순서대로 인덱스가 만들어진다.
	jsonRow.put("bullet", "https://www.amcharts.com/lib/images/faces/A04.png");
	jsonData.put(jsonRow);
}
// 브라우저로 전송
String jsonString = jsonData.toString();
// out.print(jsonString);

// 2신청
List<Map<String, Object>> tutorTutoring = (List<Map<String, Object>>)request.getAttribute("tutortutoring2");
JSONArray jsonData2 = new JSONArray();
for(Map<String, Object> rowData: tutorTutoring){
	JSONObject jsonRow = new JSONObject();
	jsonRow.put("name", rowData.get("name")); // 넣는순서대로 인덱스가 만들어진다.
	jsonRow.put("points", rowData.get("tutoring_apply_count"));
	//jsonRow.put("color", "#7F8DA9"); // 넣는순서대로 인덱스가 만들어진다.
	jsonRow.put("bullet", "https://www.amcharts.com/lib/images/faces/A04.png");
	jsonData2.put(jsonRow);
}
// 브라우저로 전송
String jsonString2 = jsonData2.toString();
%>

<script>
var colorCode = "#" + Math.round(Math.random() * 0xFFFFFF).toString(16);
var chart = AmCharts.makeChart("chartdiv",
{
    "type": "serial",
    "theme": "none",
    "dataProvider":<%=jsonData%>,
    "valueAxes": [{
        "maximum":50,
        "minimum": 0,
        "axisAlpha": 0,
        "dashLength": 4,
        "position": "left"
    }],
    "startDuration": 1,
    "graphs": [{
        "balloonText": "<span style='font-size:13px;'>[[category]]: <b>[[value]]</b></span>",
        "bulletOffset": 15,
        "bulletSize": 40,
        "colorField": "colorCode",
        "cornerRadiusTop": 8,
        "customBulletField": "bullet",
        "fillAlphas": 0.8,
        "lineAlpha": 0,
        "type": "column",
        "valueField": "points"
    }],
    "marginTop": 0,
    "marginRight": 0,
    "marginLeft": 0,
    "marginBottom": 0,
    "autoMargins": false,
    "categoryField": "name",
    "categoryAxis": {
        "axisAlpha": 0,
        "gridAlpha": 0,
        "inside": true,
        "tickLength": 0
    },
    "export": {
      "enabled": true
     }
});





// 신청
var chart2 = AmCharts.makeChart("chartdiv2",
{
    "type": "serial",
    "theme": "none",
    "dataProvider":<%=jsonData2%>,
    "valueAxes": [{
        "maximum":50,
        "minimum": 0,
        "axisAlpha": 0,
        "dashLength": 4,
        "position": "left"
    }],
    "startDuration": 1,
    "graphs": [{
        "balloonText": "<span style='font-size:13px;'>[[category]]: <b>[[value]]</b></span>",
        "bulletOffset": 15,
        "bulletSize": 40,
        "colorField": "colorCode",
        "cornerRadiusTop": 8,
        "customBulletField": "bullet",
        "fillAlphas": 0.8,
        "lineAlpha": 0,
        "type": "column",
        "valueField": "points"
    }],
    "marginTop": 0,
    "marginRight": 0,
    "marginLeft": 0,
    "marginBottom": 0,
    "autoMargins": false,
    "categoryField": "name",
    "categoryAxis": {
        "axisAlpha": 0,
        "gridAlpha": 0,
        "inside": true,
        "tickLength": 0
    },
    "export": {
      "enabled": true
     }
});
</script>

<!-- HTML -->
<h5>튜터별 튜터링 등록현황</h5>
<div id="chartdiv" class="pb-5 mb-5"></div>
<h5>튜터링 신청 현황</h5>
<div id="chartdiv2"></div>