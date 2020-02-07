<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>fmt:setLocale 标签</title>
</head>
<body>

<fmt:bundle basename="io.github.dunwu.javaee.util.Example">
	<fmt:message key="count.one"/><br/>
	<fmt:message key="count.two"/><br/>
	<fmt:message key="count.three"/><br/>
</fmt:bundle>

<!-- 修改地区-->
<fmt:setLocale value="es_ES"/>
<fmt:bundle basename="io.github.dunwu.javaee.util.Example">
	<fmt:message key="count.one"/><br/>
	<fmt:message key="count.two"/><br/>
	<fmt:message key="count.three"/><br/>
</fmt:bundle>

</body>
</html>
