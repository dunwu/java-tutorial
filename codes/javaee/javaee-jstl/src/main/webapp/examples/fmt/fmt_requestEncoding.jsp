<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>fmt:message 标签</title>
</head>
<body>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="es_ES"/>
<fmt:setBundle basename="io.github.dunwu.javaee.util.Example" var="lang"/>

<fmt:message key="count.one" bundle="${lang}"/><br/>
<fmt:message key="count.two" bundle="${lang}"/><br/>
<fmt:message key="count.three" bundle="${lang}"/><br/>

</body>
</html>
