<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%--执行本例之前，需要先运行 sql/create_students.sql--%>

<html>
<head>
	<title>sql:transaction 示例</title>
</head>
<body>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
									 url="jdbc:mysql://localhost/test"
									 user="root" password="root"/>

<%
	Date DoB = new Date("2001/12/16");
	int studentId = 100;
%>

<sql:transaction dataSource="${snapshot}">
	<sql:update var="count">
		UPDATE Students SET last = 'Ali' WHERE Id = 102
	</sql:update>
	<sql:update var="count">
		UPDATE Students SET last = 'Shah' WHERE Id = 103
	</sql:update>
	<sql:update var="count">
		INSERT INTO Students
		VALUES (104,'Nuha', 'Ali', '2010/05/26');
	</sql:update>
</sql:transaction>

<sql:query dataSource="${snapshot}" var="result">
	SELECT * from Students;
</sql:query>

<table border="1" width="100%">
	<tr>
		<th>Emp ID</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>DoB</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
		<tr>
			<td><c:out value="${row.id}"/></td>
			<td><c:out value="${row.first}"/></td>
			<td><c:out value="${row.last}"/></td>
			<td><c:out value="${row.dob}"/></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
