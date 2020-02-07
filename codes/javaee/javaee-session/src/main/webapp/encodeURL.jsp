<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Cookie Encoding</title>
</head>

<a href="<%= response.encodeURL("index.jsp?c=1&wd=Java") %>">Homepage</a>

<%= response.encodeURL("index.jsp") %>?c=1&wd=Java


<body>
</body>
</html>
