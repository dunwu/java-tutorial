<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Checkbox</title>
</head>
<body>
<h1>从复选框中读取数据</h1>
<ul>
	<li><p><b>谷歌是否选中:</b>
		<%= request.getParameter("google")%>
	</p></li>
	<li><p><b>百度是否选中:</b>
		<%= request.getParameter("baidu")%>
	</p></li>
	<li><p><b>淘宝是否选中:</b>
		<%= request.getParameter("taobao")%>
	</p></li>
</ul>
</body>
</html>
