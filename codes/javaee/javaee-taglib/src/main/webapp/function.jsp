<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.victorzhang.com/function" prefix="fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		table {
			border-collapse: collapse;
			border: 1px solid #000000;
		}

		td {
			font-size: 12px;
			border: 1px solid #000000;
			padding: 2px;
		}

		.l {
			font-weight: bold;
		}
	</style>
</head>
<body>

<%
	request.setAttribute("string", "字符串测试");
%>

<table>
	<tr>
		<td class="l">字符串变量</td>
		<td>${ string }</td>
	</tr>
	<tr>
		<td class="l">字符串长度(按字节计)</td>
		<td>${ fn:length(string) }</td>
	</tr>
	<tr>
		<td class="l">截取 7 个字节</td>
		<td>${ fn:substring(string, 7) }</td>
	</tr>
</table>

</body>
</html>
