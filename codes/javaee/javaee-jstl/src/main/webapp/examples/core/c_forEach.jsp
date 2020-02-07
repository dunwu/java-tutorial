<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>c:forEach 标签实例</title>
</head>
<body>
<c:forEach var="i" begin="1" end="5">
Item <c:out value="${i}"/><p>
	</c:forEach>
</body>
</html>
