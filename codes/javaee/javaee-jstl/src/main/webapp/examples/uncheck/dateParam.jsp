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

		textarea {
			width: 400px;
			height: 50px;
		}
	</style>
</head>
<body>

<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>

<sql:setDataSource driver="com.mysql.jdbc.Driver" user="root"
									 password="admin"
									 url="jdbc:mysql://localhost:3306/jstl?charachterEncoding=UTF-8"
									 var="dataSource" scope="page"/>

<sql:update dataSource="${ dataSource }">
	create table if not exists tb_person
	( id integer auto_increment,
	name varchar(255),
	birthday timestamp null,
	primary key (id)
	)
</sql:update>

<sql:update dataSource="${ dataSource }">
	insert into tb_person ( name, birthday ) values ( ?, ? )
	<sql:param value="Helloween"></sql:param>
	<sql:dateParam value="${ date }" type="timestamp"/>
</sql:update>

<sql:query var="rs" dataSource="${ dataSource }">
	select * from tb_person where birthday > ( ? - 1 )
	<sql:param value="${ date }"></sql:param>
</sql:query>

<table>
	<tr class="title">
		<c:forEach var="columnName" items="${ rs.columnNames }">
			<td>${ columnName }</td>
		</c:forEach>
	</tr>

	<c:forEach var="row" items="${ rs.rows }">
		<tr>
			<c:forEach var="columnName" items="${ rs.columnNames }">
				<td>${ row[columnName] }</td>
			</c:forEach>
		</tr>
	</c:forEach>

</table>

</body>
</html>
