<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>fmt:setTimeZone 标签</title>
</head>
<body>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<p>当前时区时间: <fmt:formatDate value="${now}"
													 type="both" timeStyle="long" dateStyle="long"/></p>
<p>修改为 GMT-8 时区:</p>
<fmt:setTimeZone value="GMT-8"/>
<p>Date in Changed Zone: <fmt:formatDate value="${now}"
																				 type="both" timeStyle="long" dateStyle="long"/></p>
</body>
</html>
