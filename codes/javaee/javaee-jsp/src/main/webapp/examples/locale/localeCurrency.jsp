<%@ page import="java.text.NumberFormat,java.util.Locale" %>

<%
	String title = "Locale Specific Currency";
	//Get the client's Locale
	Locale locale = request.getLocale();
	NumberFormat nft = NumberFormat.getCurrencyInstance(locale);
	String formattedCurr = nft.format(1000000);
%>
<html>
<head>
	<title><% out.print(title); %></title>
</head>
<body>
<h1 align="center"><% out.print(title); %></h1>
<div align="center">
	<p>Formatted Currency: <% out.print(formattedCurr); %></p>
</div>
</body>
</html>
