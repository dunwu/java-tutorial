<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>fmt:bundle 标签</title>
</head>
<body>

<fmt:bundle basename="io.github.dunwu.javaee.util.Example" prefix="count.">
	<fmt:message key="one"/><br/>
	<fmt:message key="two"/><br/>
	<fmt:message key="three"/><br/>
</fmt:bundle>

</body>
</html>
