<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%! int day = 1; %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>02.JSP语法 - if...else示例</title>
</head>
<body>
<h3>if...else示例</h3>
<% if (day == 1 | day == 7) { %>
<p>今天是周末</p>
<% } else { %>
<p>今天不是周末</p>
<% } %>
</body>
</html>
