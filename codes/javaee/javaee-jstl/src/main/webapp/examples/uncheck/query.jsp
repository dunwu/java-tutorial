<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<sql:setDataSource driver="com.mysql.jdbc.Driver" user="root"
									 password="admin"
									 url="jdbc:mysql://localhost:3306/jstl?charachterEncoding=UTF-8"
									 var="dataSource"/>

<sql:query var="rs" dataSource="${ dataSource }"
					 sql="select * from tb_corporation">
</sql:query>

<table>
	<tr class="title">
		<td>ID</td>
		<td>Name</td>
		<td>Description</td>
	</tr>

	<c:forEach var="row" items="${ rs.rows }">
		<tr>
			<td>${ row['id'] }</td>
			<td>${ row['name'] }</td>
			<td>${ row['description'] }</td>
		</tr>
	</c:forEach>

</table>

</body>
</html>
