<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>使用 JSTL 函数</title>
</head>
<body>

<c:set var="string1" value="I am from China         "/>
<p>string1 长度 : ${fn:length(string1)}</p>

<c:set var="string2" value="${fn:trim(string1)}"/>
<p>string2 长度 : ${fn:length(string2)}</p>
<p>字符串为 : ${string2}</p>

</body>
</html>
