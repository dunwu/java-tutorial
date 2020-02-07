<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>Using JSTL Functions</title>
</head>
<body>

<c:set var="string1" value="This is first String."/>
<c:set var="string2" value="This <abc>is second String.</abc>"/>

<p>使用 escapeXml() 函数:</p>
<p>string (1) : ${fn:escapeXml(string1)}</p>
<p>string (2) : ${fn:escapeXml(string2)}</p>

<p>不使用 escapeXml() 函数:</p>
<p>string (1) : ${string1}</p>
<p>string (2) : ${string2}</p>

</body>
</html>
