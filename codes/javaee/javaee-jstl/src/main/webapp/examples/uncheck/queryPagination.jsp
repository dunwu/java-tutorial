<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.helloweenvsfei.util.Pagination" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body, td, input, select, button {
			font-size: 12px;
		}

		table {
			border-collapse: collapse;
			border: 1px solid #000000;
		}

		td {
			border: 1px solid #000000;
			padding: 2px;
		}

		.title td {
			text-align: center;
			background: #DDDDDD;
		}
	</style>
</head>
<body>

<%
	request.setAttribute("pagination", new Pagination(request, response));
%>

<sql:setDataSource driver="com.mysql.jdbc.Driver" user="root"
									 password="admin"
									 url="jdbc:mysql://localhost:3306/mysql?charachterEncoding=UTF-8"
									 var="dataSource"/>

<sql:query var="rs" dataSource="${ dataSource }">
	SELECT count(*) count FROM help_topic
</sql:query>

<c:forEach var="row" items="${ rs.rows }">
	<jsp:setProperty name="pagination" property="recordCount"
									 value="${ row.count }"/>
</c:forEach>

<sql:query var="rs" dataSource="${ dataSource }"
					 startRow="${ pagination.firstResult }"
					 maxRows="${ pagination.pageSize }">
	SELECT * FROM help_topic
</sql:query>

<table>
	<tr class="title">
		<td>Help_ID</td>
		<td>Name</td>
		<td>Description</td>
	</tr>

	<c:forEach var="row" items="${ rs.rows }">
		<tr>
			<td align="center">${ row['help_topic_id'] }</td>
			<td>${ row['name'] }</td>
			<td>${ row['description'] }</td>
		</tr>
	</c:forEach>

</table>
<br/>
${ pagination }

</body>
</html>
