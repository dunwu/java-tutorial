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

<p>string3 字符串 : ${string3}</p>

<c:set var="string4" value="${fn:split(string3, '-')}"/>
<c:set var="string5" value="${fn:join(string4, ' ')}"/>

<p>string5 字符串: ${string5}</p>

</body>
</html>
