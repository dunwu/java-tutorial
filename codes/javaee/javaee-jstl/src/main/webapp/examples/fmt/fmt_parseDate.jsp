<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>fmt:parseDate 标签</title>
</head>
<body>
<h3>日期解析:</h3>
<c:set var="now" value="20-10-2015"/>

<fmt:parseDate value="${now}" var="parsedEmpDate"
							 pattern="dd-MM-yyyy"/>
<p>解析后的日期为: <c:out value="${parsedEmpDate}"/></p>

</body>
</html>
