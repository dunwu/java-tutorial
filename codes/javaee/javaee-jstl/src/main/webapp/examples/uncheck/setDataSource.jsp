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
</head>
<body>

<sql:setDataSource driver="com.mysql.jdbc.Driver" user="root"
									 password="123"
									 url="jdbc:mysql://localhost:3306/jstl?charachterEncoding=UTF-8"
									 var="dataSource" scope="page"/>

<sql:setDataSource dataSource="jdbc/jstl" var="serverDataSource"/>

数据源：${ dataSource.class.name }

${ serverDataSource }


</body>
</html>
