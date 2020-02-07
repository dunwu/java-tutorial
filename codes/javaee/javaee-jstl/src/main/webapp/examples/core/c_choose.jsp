<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>c:choose 标签实例</title>
</head>
<body>
<c:set var="salary" scope="session" value="${2000*2}"/>
<p>你的工资为 : <c:out value="${salary}"/></p>
<c:choose>
	<c:when test="${salary <= 0}">
		太惨了。
	</c:when>
	<c:when test="${salary > 1000}">
		不错的薪水，还能生活。
	</c:when>
	<c:otherwise>
		什么都没有。
	</c:otherwise>
</c:choose>
</body>
</html>
