<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<head>
	<title>sql:setDataSource 示例</title>
</head>
<body>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
									 url="jdbc:mysql://localhost/world"
									 user="root" password="root"/>

<sql:query dataSource="${snapshot}" sql="select * from world.city;" var="result"/>

</body>
</html>
