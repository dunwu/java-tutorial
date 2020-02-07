<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>JSP config 示例</title>
</head>
<body>
<p>
	<%
		String message = config.getInitParameter("message");
		out.println(message);
	%>
</p>
</body>
</html>
