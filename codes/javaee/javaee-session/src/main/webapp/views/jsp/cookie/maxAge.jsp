<%@ page language="java" pageEncoding="UTF-8" %>
<%
	Cookie cookie = new Cookie("username", "helloweenvsfei");
	cookie.setMaxAge(0);

	// 必须执行这一句
	response.addCookie(cookie);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Cookie maxAge</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>

<body>
This is my JSP page. <br>
</body>
</html>
