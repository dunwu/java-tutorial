<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head><title>JSP Scriptlets</title>
</head>
<body>
<%

	for (int i = 0; i < 5; i++) {
%>    break 所在的循环, i = <%= i %>. <br/>
<%
		if (i == 2) {
			break;
		}
	}
%>    break 循环完毕. <br/>
<%
	for (int i = 0; i < 5; i++) {
%>    return 所在的循环, i = <%= i %>. <br/>
<%
		if (i == 2) {
			return;
		}
	}
%>    return 循环完毕. <br/>

</body>
</html>
