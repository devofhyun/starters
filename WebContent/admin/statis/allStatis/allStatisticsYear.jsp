<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     	<div id="curve_chart" style="width: 900px; height: 500px"></div>
     

     			<p class="float-right">금액(원)</p>
				<table class="table">
					<tr>
						<td>연도</td>
						<td>튜터링 결제 금액</td>
						<td>수수료</td>
						<td>튜터 지급금</td>
						<td>면접신청비용</td>
						<td>총 금액</td>
					</tr>
					<c:forEach items="${year}" var="yearVO">
						<tr>
							<td>${yearVO.date}</td>
							<td><fmt:formatNumber value="${yearVO.tutoringBuyAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${yearVO.startersAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${yearVO.tutorAmout}"
									pattern="#,###" />원</td>
							<td><fmt:formatNumber
									value="${yearVO.interviewBuyAmoutSum}" pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${yearVO.total}"
									pattern="#,###" />원</td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="paging">
<c:if test="${ count > 0}">

	<c:if test="${startPage > pageBlock}">
		<button id="jumPre" name="${startPage-pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary btn-sm w2">이전</button>
	</c:if>

	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${currentPage == i}">
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary btn-sm w1">${i}</button>
			</c:when>

			<c:otherwise>
				<button id="ju" name="${i}" onclick="p(this)" class="btn btn-outline-secondary btn-sm w">${i}</button>
			</c:otherwise>

		</c:choose>
	</c:forEach>

	<c:if test="${endPage < pageCount }">
		<button id="juN" name="${startPage+pageBlock}" onclick="p(this)"
			class="btn btn-outline-secondary btn-sm">다음</button>
	</c:if>


</c:if>
</div>

				 <script type="text/javascript">
  
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        //  ['Quarter', '튜터링 수수료 총액', '면접수수료 총액'],
         // ['1분기',  1000,      400],
       //   ['2분기',  1170,      460],
       //   ['3분기',  660,       1120],
       //   ['4분기',  1030,      540]
          ['연도', '총액'],
          <c:forEach var="years" items="${year}">
               ["${years.date}분기",parseInt("${years.total}")],
           </c:forEach>
        ]);

        var options = {
         // title: '',
          curveType: 'function',
          legend: { position: 'bottom' },

        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
      
      function p(jumpBtn) {
    		var rStr = jumpBtn.name;
    		// alert(rStr);
    		$.ajax({
    			url : "starters?cmd=getBuyYearAllPaging&pageNum=" + rStr,
    			success : function(result) {
    				$("div.AllStatisticYearMain").html(result);
    			}
    		})
    		//var query = "starters?cmd=TutoringListPagingAction&pageNum=" + rStr;
    		//alert("starters?cmd=tutoringList?pageNum=" + rStr);
    		//	$("#mainList").load(query);
    		// alert(query);
    	}
    </script>
				