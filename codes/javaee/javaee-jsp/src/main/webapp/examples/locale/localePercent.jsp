<%@ page import="java.text.NumberFormat,java.util.Locale" %>

<%
	String title = "Locale Specific Percentage";
	//Get the client's Locale
	Locale locale = request.getLocale();
	NumberFormat nft = NumberFormat.getPercentInstance(locale);
	String formattedPerc = nft.format(0.51);
%>
<html>
<head>
	<title><% out.print(title); %></title>
</head>
<body>
<h1 align="center"><% out.print(title); %></h1>
<div align="center">
	<p>Formatted Percentage: <% out.print(formattedPerc); %></p>
</div>
</body>
</html>
