<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		body, input {
			font-size: 12px;
		}
	</style>
</head>
<body>

<fmt:requestEncoding value="UTF-8"/>

<form action="${ pageContext.request.requestURI }" method="post">
	关键字：<input name="key"/> <c:out value="${ param.key }" default="请输入关键字"></c:out>
	<br/><br/>
	<input type="submit">
</form>

</body>
</html>
