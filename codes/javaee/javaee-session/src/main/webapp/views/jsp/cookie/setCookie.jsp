<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="java.net.URLEncoder"/>
<%!
	boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}
%>
<%
	request.setCharacterEncoding("UTF-8");

	if ("POST".equals(request.getMethod())) {

		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String maxAge = request.getParameter("maxAge");
		String domain = request.getParameter("domain");
		String path = request.getParameter("path");
		String comment = request.getParameter("comment");
		String secure = request.getParameter("secure");

		if (!isNull(name)) {

			Cookie cookie = new Cookie(URLEncoder.encode(name, "UTF-8"), URLEncoder.encode(value, "UTF-8"));

			if (!isNull(maxAge)) {
				cookie.setMaxAge(Integer.parseInt(maxAge));
			}
			if (!isNull(domain)) {
				cookie.setDomain(domain);
			}
			if (!isNull(path)) {
				cookie.setPath(path);
			}
			if (!isNull(comment)) {
				cookie.setComment(comment);
			}
			if (!isNull(secure)) {
				cookie.setSecure("true".equalsIgnoreCase(secure));
			}

			response.addCookie(cookie);
		}
	}
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
		<legend>当前有效的 Cookie</legend>
		<script type="text/javascript">
      document.write(document.cookie);
		</script>
	</fieldset>
	<fieldset>
		<legend>设置新 Cookie</legend>
		<form action="setCookie.jsp" method="POST">
			<table>
				<tr>
					<td>name:</td>
					<td><input name="name" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td>value:</td>
					<td><input name="value" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td>maxAge:</td>
					<td><input name="maxAge" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td>domain:</td>
					<td><input name="domain" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td>path:</td>
					<td><input name="path" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td>comment:</td>
					<td><input name="comment" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td>secure:</td>
					<td><input name="secure" type="text" style="width:200px; "></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value=" 提  交 " class="button">
						<input type="button" value=" 刷  新 " onclick="location='setCookie.jsp'" class="button">
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>

</body>
</html>
