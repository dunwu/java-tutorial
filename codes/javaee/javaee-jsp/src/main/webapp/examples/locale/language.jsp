<%@ page import="java.util.Locale" %>
<%
	//获取客户端本地化信息
	Locale locale = request.getLocale();
	String language = locale.getLanguage();
	String country = locale.getCountry();
%>
<html>
<head>
	<title>Detecting Locale</title>
</head>
<body>
<center>
	<h1>Detecting Locale</h1>
</center>
<p align="center">
	<%
		out.println("Language : " + language + "<br />");
		out.println("Country  : " + country + "<br />");
	%>
</p>
</body>
</html>
