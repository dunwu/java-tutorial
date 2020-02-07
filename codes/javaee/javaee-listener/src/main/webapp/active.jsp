<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="io.github.dunwu.javaee.listener.bean.PersonInfo" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

<%
	PersonInfo personInfo = (PersonInfo) session.getAttribute("personInfo");
	if (personInfo == null) {
		personInfo = new PersonInfo();
		personInfo.setAccount("Zhang Peng");
		session.setAttribute("personInfo", personInfo);
		out.println("PersonInfo 对象不存在。已经成功新建。sessionId: " + session.getId());
	} else {
		out.println("PersonInfo 对象存在。无需新建。sessionId: " + session.getId());
	}
%>

</body>
</html>
