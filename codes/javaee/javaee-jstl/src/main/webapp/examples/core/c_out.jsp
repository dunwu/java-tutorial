<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>c:out 标签实例</title>
</head>
<body>
<html>
<head>
	<title>&lt;c:out&gt;实例</title>
</head>
<body>
<h1>&lt;c:out&gt; 实例</h1>
<c:out value="&lt要显示的数据对象（未使用转义字符）&gt" escapeXml="true" default="默认值"></c:out><br/>
<c:out value="&lt要显示的数据对象（使用转义字符）&gt" escapeXml="false" default="默认值"></c:out><br/>
<c:out value="${null}" escapeXml="false">使用的表达式结果为null，则输出该默认值</c:out><br/>
</body>
</html>
