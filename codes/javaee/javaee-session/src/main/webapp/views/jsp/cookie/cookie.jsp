<%@ page language="java" pageEncoding="UTF-8" errorPage="login.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String username = "";
	int visitTimes = 0;

	// 所有的 cookie
	Cookie[] cookies = request.getCookies();

	// 遍历所有的 Cookie 寻找 用户帐号信息与登录次数信息
	for (int i = 0; cookies != null && i < cookies.length; i++) {
		Cookie cookie = cookies[i];
		if ("username".equals(cookie.getName())) {
			username = cookie.getValue();
		} else if ("visitTimes".equals(cookie.getName())) {
			visitTimes = Integer.parseInt(cookie.getValue());
			cookie.setValue("" + ++visitTimes);
		}
	}

	// 如果没有找到 Cookie 中保存的用户名，则转到登录界面
	if (username == null || username.trim().equals("")) {
		throw new Exception("您还没有登录。请先登录");
	}

	// 修改 Cookie，更新用户的访问次数
	Cookie visitTimesCookie = new Cookie("visitTimes", Integer.toString(++visitTimes));
	response.addCookie(visitTimesCookie);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Cookie</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<div align="center" style="margin:10px; ">
	<fieldset>
		<legend>登录信息</legend>
		<form action="login.jsp" method="post">
			<table>
				<tr>
					<td>
						您的帐号：
					</td>
					<td>
						<%= username %>
					</td>
				</tr>
				<tr>
					<td>
						登录次数：
					</td>
					<td>
						<%= visitTimes %>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input type="button" value=" 刷  新 "
									 onclick="location='<%= request.getRequestURI() %>?ts=' + new Date().getTime(); "
									 class="button">
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>

</body>
</html>
