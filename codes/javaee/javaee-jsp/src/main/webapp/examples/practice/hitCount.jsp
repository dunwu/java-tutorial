<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<html>
<html>
<head>
	<title>访问量统计</title>
</head>
<body>
<%
	Integer hitsCount = (Integer) application.getAttribute("hitCounter");
	if (hitsCount == null || hitsCount == 0) {
		/* 第一次访问 */
		out.println("欢迎访问!");
		hitsCount = 1;
	} else {
		/* 返回访问值 */
		out.println("欢迎再次访问!");
		hitsCount += 1;
	}
	application.setAttribute("hitCounter", hitsCount);
%>

<p>页面访问量为: <%= hitsCount%>
</p>

</body>
</html>
