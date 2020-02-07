<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>jsp:setProperty和jsp:getProperty使用范例</title>
</head>
<body>

<h2>jsp:setProperty和jsp:getProperty使用范例</h2>
<jsp:useBean id="msg" class="io.github.dunwu.javaee.jsp.bean.Message"/>

<jsp:setProperty name="msg" property="content" value="打死也不说"/>

<p>输出信息....</p>

<jsp:getProperty name="msg" property="content"/>

</body>
</html>
