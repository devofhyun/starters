<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     	<div id="curve_chart" style="width: 900px; height: 500px"></div>
     
				<table class="table mt-5">
					<tr>
						<td>연도</td>
						<td>기업 수</td>

					</tr>
					<c:forEach items="${year}" var="yearVO">
						<tr>
							<td>${yearVO.date}</td>
							<td>${yearVO.memberR}</td>
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
  
      google.charts.load('current', {'packages':['line']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        //  ['Quarter', '튜터링 수수료 총액', '면접수수료 총액'],
         // ['1분기',  1000,      400],
       //   ['2분기',  1170,      460],
       //   ['3분기',  660,       1120],
       //   ['4분기',  1030,      540]
          ['', '회원'],
          <c:forEach var="years" items="${year}">
               ["${years.date}년",parseInt("${years.memberR}")],
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
      
      function p(jumpBtn) {
    		var rStr = jumpBtn.name;
    		// alert(rStr);
    		$.ajax({
    			url : "starters?cmd=getCompanyYearAllPaging&pageNum=" + rStr,
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
				