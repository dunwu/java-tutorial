<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Checkbox2</title>
</head>
<body>
<h1>读取所有表单参数</h1>
<table width="100%" border="1" align="center">
	<tr bgcolor="#949494">
		<th>参数名</th>
		<th>参数值</th>
	</tr>
	<%
		Enumeration paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			out.print("<tr><td>" + paramName + "</td>\n");
			String paramValue = request.getParameter(paramName);
			out.println("<td> " + paramValue + "</td></tr>\n");
		}
	%>
</table>
</body>
</html>
