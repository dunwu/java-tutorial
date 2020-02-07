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

<form method="get" action="${ pageContext.request.requestURL }">
	<textarea name=sql>${ param.sql }</textarea> <br/>
	<input type="submit"/>
</form>

<sql:setDataSource driver="com.mysql.jdbc.Driver" user="root"
									 password="admin"
									 url="jdbc:mysql://localhost:3306/mysql?charachterEncoding=UTF-8"
									 var="dataSource"/>

<c:catch var="e">

	<sql:query var="rs" dataSource="${ dataSource }">
		${ param.sql }
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

</c:catch>

<c:if test="${ e != null }">
	<div style="color: #FF0000; ">Exception: ${ e.message } </div>
</c:if>

</body>
</html>
