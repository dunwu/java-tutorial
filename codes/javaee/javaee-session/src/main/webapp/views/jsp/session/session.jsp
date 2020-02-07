<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="io.github.dunwu.javaee.servlet.bean.Person"/>
<jsp:directive.page import="java.text.DateFormat"/>
<jsp:directive.page import="java.text.SimpleDateFormat"/>
<%@ page import="java.util.Date" %>
<%!
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>
<%
	response.setCharacterEncoding("UTF-8");

	Person[] persons = {new Person("Liu Jinghua", "password1", 34, dateFormat.parse("1982-01-01")),
		new Person("Hello Kitty", "hellokitty", 23, dateFormat.parse("1984-02-25")),
		new Person("Garfield", "garfield_pass", 23, dateFormat.parse("1994-09-12")),};

	String message = "";

	if (request.getMethod().equals("POST")) {

		for (int i = 0; i < persons.length; i++) {
			// 如果 用户名正确 且 密码正确
			if (persons[i].getName().equalsIgnoreCase(request.getParameter("username")) && persons[i].getPassword()
				.equals(
					request.getParameter(
						"password"))) {

				// 登录成功, 设置将用户的信息以及登录时间保存到 Session
				session.setAttribute("person", persons[i]);
				session.setAttribute("loginTime", new Date());

				response.sendRedirect(request.getContextPath() + "welcome.jsp");
				return;
			}
		}

		// 登录失败
		message = "用户名密码不匹配，登录失败。";
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>请先登录</title>
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<div align="center" style="margin:10px; ">
	<fieldset>
		<legend>登录</legend>
		<form action="session.jsp" method="post">
			<table>
				<% if (!message.equals("")) { %>
				<tr>
					<td>
					</td>
					<td>
						<span><img src="../../images/errorstate.gif"></span>
						<span style="color:red; "><%= message %></span>
					</td>
				</tr>
				<% } %>
				<tr>
					<td>
						帐号：
					</td>
					<td>
						<input type="text" name="username" style="width:200px; ">
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="password" name="password" style="width:200px; ">
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input type="submit" value=" 登  录 " class="button">
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>

Hello Kitty, hellokitty

</body>
</html>
