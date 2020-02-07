<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>使用 JSTL 函数</title>
</head>
<body>

<c:set var="string1" value="I am from google"/>
<c:set var="string2" value="${fn:replace(string1,
                                'google', 'china')}"/>

<p>替换后的字符串 : ${string2}</p>


</body>
</html>
