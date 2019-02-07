<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:forEach items="${images}" var="IntImageVO" varStatus="iv">
  <img src="../assets/portfolioImage/${IntImageVO.getImg()}" class="Timg"/>
</c:forEach>