<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>使用 JSTL 函数</title>
</head>
<body>

<c:set var="string1" value="www baidu com"/>
<c:set var="string2" value="${fn:split(string1, ' ')}"/>
<c:set var="string3" value="${fn:join(string2, '-')}"/>

<p>字符串为 : ${string3}</p>

</body>
</html>
