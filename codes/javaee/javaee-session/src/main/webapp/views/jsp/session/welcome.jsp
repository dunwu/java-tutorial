<%@ page language="java" pageEncoding="UTF-8" %>
<%
	if (session.getAttribute("person") == null) {
		response.sendRedirect("session.jsp");
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>欢迎您, ${ person.name }</title>
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<div align="center" style="margin:10px; ">
	<fieldset>
		<legend>欢迎您${ person.name }</legend>
		<table>
			<tr>
				<td>
					您的姓名：
				</td>
				<td>
					${ person.name }
				</td>
			</tr>
			<tr>
				<td>
					登录时间：
				</td>
				<td>
					${ loginTime }
				</td>
			</tr>
			<tr>
				<td>
					您的年龄：
				</td>
				<td>
					${ person.age }
				</td>
			</tr>
			<tr>
				<td>
					您的生日：
				</td>
				<td>
					${ person.birthday }
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<input type="button" value=" 重新登录 " onclick="location='session.jsp';" class="button">
				</td>
			</tr>
		</table>
	</fieldset>
</div>

</body>
</html>
