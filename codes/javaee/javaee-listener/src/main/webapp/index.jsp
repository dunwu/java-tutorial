<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>javaee-listener 首页</title>
</head>

<body>
This is my JSP page. <br>
<a href="listener.jsp">Listener</a><br>
<a href="active.jsp">active</a><br>
<a href="testLoginSessionListener.jsp">登录</a><br>
<a href="online.jsp">在线用户统计</a><br>
<a href="listener.jsp">Listener</a><br>
</body>
</html>
