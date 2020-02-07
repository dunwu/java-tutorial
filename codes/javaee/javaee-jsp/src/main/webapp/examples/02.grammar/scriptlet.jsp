<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head><title>JSP Scriptlets</title>
</head>
<body>
<%
	int num = 10;
	int result = 1;
	for (int i = 1; i <= num; i++) {
		result *= i;
	}
	out.println("<hr/>");
	out.println("数字 " + num + " 的阶乘为：" + result);
%>
</body>
</html>
