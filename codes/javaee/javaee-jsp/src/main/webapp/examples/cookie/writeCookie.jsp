<%--
访问本页面的形式如：http://127.0.0.1:8080/runoobDemos/writeCookie.jsp?name=张三&url=www.baidu.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%
	// 编码，解决中文乱码
	String name = URLEncoder.encode(request.getParameter("name"), "utf-8");

	// 设置 nameCookie 和 urlCookie cookie
	Cookie nameCookie = new Cookie("name", name);
	Cookie urlCookie = new Cookie("url", request.getParameter("url"));

	// 设置cookie过期时间为24小时。
	nameCookie.setMaxAge(60 * 60 * 24);
	urlCookie.setMaxAge(60 * 60 * 24);

	// 在响应头部添加cookie
	response.addCookie(nameCookie);
	response.addCookie(urlCookie);
%>
<html>
<head>
	<title>设置 Cookie</title>
</head>
<body>

<h1>设置 Cookie</h1>

<ul>
	<li><p><b>网站名:</b>
		<%= request.getParameter("name")%>
	</p></li>
	<li><p><b>网址:</b>
		<%= request.getParameter("url")%>
	</p></li>
</ul>
</body>
</html>
