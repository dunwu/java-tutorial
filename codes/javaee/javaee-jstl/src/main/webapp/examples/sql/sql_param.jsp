<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%--运行本例之前，先执行sql/create_employees.sql--%>

<html>
<head>
	<title>sql:param 示例</title>
</head>
<body>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
									 url="jdbc:mysql://localhost/test"
									 user="root" password="root"/>

<c:set var="empId" value="103"/>

<sql:update dataSource="${snapshot}" var="count">
	DELETE FROM Employees WHERE Id = ?
	<sql:param value="${empId}"/>
</sql:update>

<sql:query dataSource="${snapshot}" var="result">
	SELECT * from Employees;
</sql:query>

<table border="1" width="100%">
	<tr>
		<th>Emp ID</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Age</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
		<tr>
			<td><c:out value="${row.id}"/></td>
			<td><c:out value="${row.first}"/></td>
			<td><c:out value="${row.last}"/></td>
			<td><c:out value="${row.age}"/></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
