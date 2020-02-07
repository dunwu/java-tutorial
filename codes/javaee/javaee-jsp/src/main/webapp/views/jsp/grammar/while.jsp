<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head><title>JSP Scriptlets</title>
</head>
<body>
<%
	java.util.List<String> list = new java.util.ArrayList<String>();

	list.add("茕茕白兔");
	list.add("东走西顾");
	list.add("衣不如新");
	list.add("人不如故");

	java.util.Iterator it = list.iterator();

	while (it.hasNext()) {
%>  <%= it.next() %> <br/>
<%
	}

%>
</body>
</html>
