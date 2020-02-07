<%@ page import="java.text.DateFormat,java.util.Date" %>
<%@ page import="java.util.Locale " %>

<%
	String title = "Locale Specific Dates";
	//Get the client's Locale
	Locale locale = request.getLocale();
	String date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, locale).format(new Date());
%>
<html>
<head>
	<title><% out.print(title); %></title>
</head>
<body>
<h1 align="center"><% out.print(title); %></h1>
<div align="center">
	<p>Local Date: <% out.print(date); %></p>
</div>
</body>
</html>
