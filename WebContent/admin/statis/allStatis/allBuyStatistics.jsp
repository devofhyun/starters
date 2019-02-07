<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     
<style>
table.table {
    max-width: 100%;
	table-layout: auto;
}
</style>
	<div id="curve_chart" style="width: 900px; height: 500px"></div>

				<p class="float-right">금액(원)</p>
				<table class="table">
					<tr>
						<td>분기</td>
						<td>튜터링 결제 금액</td>
						<td>수수료</td>
						<td>튜터 지급금</td>
						<td>면접신청비용</td>
						<td>총 금액</td>
					</tr>
					<c:forEach items="${quarter}" var="quarterVO">
						<tr>
							<td>${quarterVO.quarter}</td>
							<td><fmt:formatNumber value="${quarterVO.tutoringBuyAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${quarterVO.startersAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${quarterVO.tutorAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber
									value="${quarterVO.interviewBuyAmount}" pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${quarterVO.total}"
									pattern="#,###" />원</td>
						</tr>
					</c:forEach>
				</table>
 <script type="text/javascript">
  
      google.charts.load('current', {'packages':['bar', 'line']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        //  ['Quarter', '튜터링 수수료 총액', '면접수수료 총액'],
         // ['1분기',  1000,      400],
       //   ['2분기',  1170,      460],
       //   ['3분기',  660,       1120],
       //   ['4분기',  1030,      540]
          ['분기', '튜터링 수수료 총액', '면접수수료 총액', '총액'],
          <c:forEach var="quarters" items="${quarter}">
               ["${quarters.quarter}분기", parseInt("${quarters.startersAmout}"), parseInt("${quarters.interviewBuyAmount}"), parseInt("${quarters.total}")],
           </c:forEach>
        ]);

        var options = {
         // title: '',
          curveType: 'function',
          legend: { position: 'bottom' },

        };

        var chart = new google.charts.Line(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
      

    </script>

