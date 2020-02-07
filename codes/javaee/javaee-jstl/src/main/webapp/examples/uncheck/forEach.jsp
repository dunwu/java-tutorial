<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		div {
			float: left;
			padding: 3px;
			width: 50px;
			text-align: center;
			border: 1px solid #000000;
		}

		table {
			border-collapse: collapse;
			border: 1px solid #000000;
		}

		td {
			border: 1px solid #000000;
			padding: 2px;
		}

		.header {
			background: #DDDDDD;
			text-align: center;
		}
	</style>
</head>
<body>

<c:forEach var="num" begin="2" end="100" step="2">
	<div>${ num }</div>
</c:forEach>

<br/>
<br/>
<br/>


<table>
	<tr>
		<td class="header">Header Name</td>
		<td class="header">Header Value</td>
	</tr>
	<c:forEach var="headerName" items="${ pageContext.request.headerNames }">
		<tr>
			<td>${ headerName }</td>
			<td>${ header[headerName] }</td>
		</tr>
	</c:forEach>
</table>

<br/>
<br/>
<br/>


<table>
	<tr>
		<td class="header">Header Name</td>
		<td class="header">Header Value</td>
	</tr>
	<c:forEach var="item" items="${ header }">
		<tr>
			<td>${ item.key }</td>
			<td>${ item.value }</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
