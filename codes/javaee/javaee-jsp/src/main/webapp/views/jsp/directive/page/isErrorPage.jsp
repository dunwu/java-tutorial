<!-- 如果某一个jsp页面是作为系统的错误处理页面，那么建议将page指令的isErrorPage属性(默认为false)设置为"true"来显式声明这个Jsp页面是一个错误处理页面。 -->
<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<head>
	<title>JSP</title>
</head>
<body>
<%
	out.println("程序抛出了一个异常：" + exception);
%>
</body>
</html>
