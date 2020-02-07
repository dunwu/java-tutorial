<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>JSP Scriptlets</title>
</head>
<body>
<%
	java.util.List<String> list = new java.util.ArrayList<String>();

	list.add("青青子衿");
	list.add("悠悠我心");
	list.add("但为君故");
	list.add("沉吟至今");

	java.util.Iterator it = list.iterator();

	while (it.hasNext()) {
%>  <%= it.next() %> <br/>
<%
	}
%>
</body>
</html>
