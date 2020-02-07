<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>c:catch 标签实例</title>
</head>
<body>

<c:catch var="catchException">
	<% int x = 5 / 0;%>
</c:catch>

<c:if test="${catchException != null}">
	<p>异常为 : ${catchException} <br/>
		发生了异常: ${catchException.message}</p>
</c:if>

</body>
</html>
