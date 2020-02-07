<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>My JSP 'taglib.jsp' starting page</title>
</head>

<body>
<c:forEach var="item" items="${arrays}">
	<c:out value="item"></c:out>
</c:forEach>
</body>
</html>
