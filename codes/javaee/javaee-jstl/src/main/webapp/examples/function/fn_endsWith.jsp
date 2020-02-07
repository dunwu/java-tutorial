<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>使用 JSTL 函数</title>
</head>
<body>

<c:set var="theString" value="I am from China 123"/>

<c:if test="${fn:endsWith(theString, '123')}">
<p>字符串以 123 结尾
<p>
	</c:if>

	<c:if test="${fn:endsWith(theString, 'china')}">
<p>字符串以 china 结尾
<p>
	</c:if>

</body>
</html>
