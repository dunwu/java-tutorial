<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body {
			font-size: 12px;
		}
	</style>
</head>
<body>

<c:import var="baidu" url="http://www.baidu.com" charEncoding="gbk">
</c:import>

Baidu 的源代码为：

<c:out value="${ baidu }"/>


</body>
</html>
