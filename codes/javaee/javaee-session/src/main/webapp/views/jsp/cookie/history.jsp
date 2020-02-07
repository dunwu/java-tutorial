<%@ page language="java" pageEncoding="UTF-8" isErrorPage="false" %>
<%!

%>
<%


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>.</title>
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<div align="center" style="margin:10px; ">
	<fieldset>
		<legend>当前有效的 Cookie</legend>
		<script>document.write(document.cookie);</script>
	</fieldset>
	<fieldset>
		<legend>历史记录</legend>
		<form action="${ pageContext.request.requestURI }?action=login" method="post">
			<table>
				<tr>
					<td>
						帐号：
					</td>
					<td>
						<input type="text" name="account" style="width:200px; ">
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
						有效期：
					</td>
					<td>
						<input type="radio" name="timeout" value="-1" checked> 关闭浏览器即失效 <br/>
						<input type="radio" name="timeout" value="<%= 30 * 24 * 60 * 60 %>"> 30天内有效 <br/>
						<input type="radio" name="timeout" value="<%= Integer.MAX_VALUE %>"> 永久有效 <br/>
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

</body>
</html>
