<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>使用 JSTL 函数</title>
</head>
<body>

<c:set var="string" value="China: I am from China."/>
<c:if test="${fn:startsWith(string, 'Google')}">
	<p>字符串以 Google 开头</p>
</c:if>
<br/>
<c:if test="${fn:startsWith(string, 'China')}">
	<p>字符串以 China 开头</p>
</c:if>

</body>
</html>
