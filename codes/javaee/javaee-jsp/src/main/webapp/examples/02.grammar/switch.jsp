<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%! int day = 3; %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>02.JSP语法 - switch...case示例</title>
</head>
<body>
<h3>Sswitch...case示例</h3>
<%
	switch (day) {
		case 0:
			out.println("星期天");
			break;
		case 1:
			out.println("星期一");
			break;
		case 2:
			out.println("星期二");
			break;
		case 3:
			out.println("星期三");
			break;
		case 4:
			out.println("星期四");
			break;
		case 5:
			out.println("星期五");
			break;
		default:
			out.println("星期六");
	}
%>
</body>
</html>
