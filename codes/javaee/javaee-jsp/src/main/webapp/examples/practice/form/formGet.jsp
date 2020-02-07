<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>表单 - Get操作</title>
</head>
<body>
<h1>使用 GET 方法读取数据</h1>
<ul>
	<li><p><b>站点名:</b>
		<%= request.getParameter("name")%>
	</p></li>
	<li><p><b>网址:</b>
		<%= request.getParameter("url")%>
	</p></li>
</ul>
</body>
</html>
