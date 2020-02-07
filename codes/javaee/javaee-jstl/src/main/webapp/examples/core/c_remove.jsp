<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>c:remove 标签实例</title>
</head>
<body>
<c:set var="salary" scope="session" value="${2000*2}"/>
<p>salary 变量值: <c:out value="${salary}"/></p>
<c:remove var="salary"/>
<p>删除 salary 变量后的值: <c:out value="${salary}"/></p>
</body>
</html>
