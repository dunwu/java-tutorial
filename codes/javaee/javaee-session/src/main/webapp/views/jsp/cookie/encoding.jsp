<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="java.net.URLDecoder"/>
<jsp:directive.page import="java.net.URLEncoder"/>
<%
	// 使用中文的 Cookie. name 与 value 都使用 UTF-8 编码.
	Cookie cookie = new Cookie(URLEncoder.encode("姓名", "UTF-8"), URLEncoder.encode("张三", "UTF-8"));

	// 发送到客户端
	response.addCookie(cookie);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Cookie Encoding</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	if (null != cookies) {
		for (int i = 0; i < cookies.length; i++) {

			String cookieName = URLDecoder.decode(cookies[i].getName(), "UTF-8");
			String cookieValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");

			out.println(cookieName + "=");
			out.println(cookieValue + "; <br/>");
		}
	} else {
		out.println("Cookie 已经写入客户端. 请刷新页面. ");
	}
%>
</body>
</html>
