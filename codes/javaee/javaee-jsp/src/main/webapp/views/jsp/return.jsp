<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head><title>JSP Scriptlets</title>
</head>
<body>
<%
	String param = request.getParameter("param");
%>
昔我往矣，<br/>
杨柳依依。<br/>
今我来思，<br/>
雨雪霏霏。<br/>
<%
	if ("return".equals(param)) {
		return;
	}
%>
青青子衿，<br/>
悠悠我心，<br/>
但为君故，<br/>
沉吟至今！<br/>

<br/>
<a href="<%= request.getRequestURI() %>?param=return"><%= request.getRequestURI() %>?param=return</a>

</body>
</html>
