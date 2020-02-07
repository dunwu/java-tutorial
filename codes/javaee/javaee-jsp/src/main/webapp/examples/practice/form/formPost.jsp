<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>表单 - Post操作</title>
</head>
<body>
<h1>使用 POST 方法读取数据</h1>
<ul>
	<li><p><b>站点名:</b>
		<%
			// 解决中文乱码的问题
			String name = new String((request.getParameter("name")).getBytes("ISO-8859-1"), "UTF-8");
		%>
		<%=name%>
	</p></li>
	<li><p><b>网址:</b>
		<%= request.getParameter("url")%>
	</p></li>
</ul>
</body>
</html>
