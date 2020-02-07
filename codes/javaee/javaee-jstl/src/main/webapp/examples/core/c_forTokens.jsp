<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>c:forTokens 标签实例</title>
</head>
<body>
<c:forTokens items="google,baidu,taobao" delims="," var="name">
<c:out value="${name}"/><p>
	</c:forTokens>
</body>
</html>
