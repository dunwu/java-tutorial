<!-- errorPage指定当JSP页面发生异常时需要转向的错误处理页面 -->
<%@ page language="java" errorPage="isErrorPage.jsp" pageEncoding="UTF-8" %>
<html>
<head>
	<title>JSP指令page-属性errorPage</title>
</head>
<body>
<%
	//这行代码肯定会出错，因为除数是0，一运行就会抛出异常
	int x = 1 / 0;
%>
</body>
</html>
