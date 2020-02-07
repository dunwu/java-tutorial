<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat,java.util.Date" %>
<html>
<head>
	<title>显示当前时间与日期</title>
</head>
<body>

<h1>显示当前时间与日期</h1>

<%
	Date date = new Date();
	out.print("<h2 align=\"center\">" + date.toString() + "</h2>");
%>

<h1>使用SimpleDateFormat格式化日期</h1>
<%
	Date dNow = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	out.print("<h2 align=\"center\">" + ft.format(dNow) + "</h2>");
%>
</body>
</html>
