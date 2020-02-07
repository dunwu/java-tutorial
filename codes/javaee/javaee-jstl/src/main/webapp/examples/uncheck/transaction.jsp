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
		body, td {
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

		textarea {
			width: 400px;
			height: 50px;
		}
	</style>
</head>
<body>

<sql:setDataSource driver="com.mysql.jdbc.Driver" user="root"
									 password="admin"
									 url="jdbc:mysql://localhost:3306/jstl?charachterEncoding=UTF-8"
									 var="dataSource"/>

<c:catch var="e">

	<sql:transaction dataSource="${ dataSource }">

		<c:forEach var="i" begin="1" end="3">
			<sql:update var="result">
				insert into tb_corporation ( name, description ) values ('事务测试', '事务测试')
			</sql:update>

			已插入一条。<br/>
		</c:forEach>


		<sql:update var="result">
			insert into tb_corporation ( id, name, description ) values (1, '事务测试', '事务测试')
		</sql:update>

	</sql:transaction>

</c:catch>

<c:if test="${ e != null }">
	<div style="color: red; ">操作异常，原因：${ e.message }。事务已经回滚。</div>
</c:if>

<sql:query var="rs" dataSource="${ dataSource }">
	select * from tb_corporation
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
