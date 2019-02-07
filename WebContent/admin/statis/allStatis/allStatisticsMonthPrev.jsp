<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*"
	import = "java.text.SimpleDateFormat"%>
    	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <c:set var="today" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="now" type="date" value="${today}" pattern="YYYY"/>
     	<h5>총매출-${yyyy}년-월별</h5>
     	<div id="curve_chart" style="width: 900px; height: 500px"></div>
     
     			<p class="float-right">금액(원)</p>
				<table class="table">
					<tr>
						<td>월</td>
						<td>튜터링 결제 금액</td>
						<td>수수료</td>
						<td>튜터 지급금</td>
						<td>면접신청비용</td>
						<td>총 금액</td>
					</tr>
					<c:forEach items="${month}" var="monthVO">
						<tr>
							<td>${monthVO.date}</td>
							<td><fmt:formatNumber value="${monthVO.tutoringBuyAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${monthVO.startersAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${monthVO.tutorAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber
									value="${monthVO.interviewBuyAmoutSum}" pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${monthVO.total}"
									pattern="#,###" />원</td>
						</tr>
					</c:forEach>
				</table>
				
				
				<div class="btnzip w-100">
					<button class="btn btn-sm btn-outline-info" id = "${yyyy-1}" name = "${yyyy-1}" onclick = "prev(this)"> 이전년도</button>
					<button class="btn btn-sm btn-outline-info pull-right" id = "${yyyy+1}" name = "${yyyy+1}" onclick = "after(this)"> 다음년도 </button>
				</div>
<script type="text/javascript">
  
      google.charts.load('current', {'packages':['line']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        //  ['Quarter', '튜터링 수수료 총액', '면접수수료 총액'],
         // ['1분기',  1000,      400],
       //   ['2분기',  1170,      460],
       //   ['3분기',  660,       1120],
       //   ['4분기',  1030,      540]
          ['분기', '튜터링 수수료 총액', '면접수수료 총액', '총액'],
          <c:forEach var="months" items="${month}">
               ["${months.date}월", parseInt("${months.startersAmout}"), parseInt("${months.interviewBuyAmoutSum}"), parseInt("${months.total}")],
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
      
      function prev(jumpBtn) {
    		var rStr = jumpBtn.name;
    		// console.log(rStr);
    		$.ajax({
    			url : "starters?cmd=MonthAllstatisticPrev&yearNum=" + rStr,
    			success : function(result) {
    				$("div.AllStatisticMonthMain").html(result);
    			}
    		})

    	}

    	function after(jumpBtn) {
    		var rStr = jumpBtn.name;
    		//console.log(rStr);
    		$.ajax({
    			url : "starters?cmd=MonthAllstatisticAfter&yearNum=" + rStr,
    			success : function(result) {
    				$("div.AllStatisticMonthMain").html(result);
    			}
    		})

    	}
    </script>
				