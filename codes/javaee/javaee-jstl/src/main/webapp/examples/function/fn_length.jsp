<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>使用 JSTL 函数</title>
</head>
<body>

<c:set var="string1" value="This is first String."/>
<c:set var="string2" value="This is second String."/>

<p>字符串长度 (1) : ${fn:length(string1)}</p>
<p>字符串长度 (2) : ${fn:length(string2)}</p>


</body>
</html>
