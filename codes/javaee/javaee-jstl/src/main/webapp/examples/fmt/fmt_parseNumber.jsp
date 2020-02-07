<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>fmt:parseNumber 标签</title>
</head>
<body>
<h3>数字解析:</h3>
<c:set var="balance" value="1250003.350"/>

<fmt:parseNumber var="i" type="number" value="${balance}"/>
<p>数字解析 (1) : <c:out value="${i}"/></p>
<fmt:parseNumber var="i" integerOnly="true"
								 type="number" value="${balance}"/>
<p>数字解析 (2) : <c:out value="${i}"/></p>

</body>
</html>
