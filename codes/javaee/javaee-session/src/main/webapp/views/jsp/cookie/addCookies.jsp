<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<title>添加Cookie</title>
</head>
<body>
<form action=/servlet/AddCookies method="GET">
	站点名 ：<input type="text" name="name">
	<br/>
	站点 URL：<input type="text" name="url"/><br>
	<input type="submit" value="提交"/>
</form>
</body>
</html>
