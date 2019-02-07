<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:forEach items="${selectMain}" var="selectMain" varStatus="iv">
 <li class="text-body"> ${selectMain.middleName}</li>
</c:forEach>