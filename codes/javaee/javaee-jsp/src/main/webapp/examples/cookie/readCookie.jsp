<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>获取 Cookie</title>
</head>
<body>
<%
	Cookie cookie = null;
	Cookie[] cookies = null;
	// 获取cookies的数据,是一个数组
	cookies = request.getCookies();
	if (cookies != null) {
		out.println("<h2> 查找 Cookie 名与值</h2>");
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];

			out.print("参数名 : " + cookie.getName());
			out.print("<br>");
			out.print("参数值: " + URLDecoder.decode(cookie.getValue(), "utf-8") + " <br>");
			out.print("------------------------------------<br>");
		}
	} else {
		out.println("<h2>没有发现 Cookie</h2>");
	}
%>
</body>
</html>
