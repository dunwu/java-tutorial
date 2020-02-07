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

<sql:update var="result" dataSource="${ dataSource }">
	drop table if exists tb_corporation
</sql:update>

DROP TABLE, 影响到的数据条数：${ result } <br/>

<sql:update var="result" dataSource="${ dataSource }">
	create table tb_corporation (
	id integer auto_increment,
	name varchar(255),
	description text,
	primary key(id)
	)
</sql:update>

CREATE TABLE, 影响到的数据条数：${ result } <br/>

<sql:update var="result" dataSource="${ dataSource }">
	insert into tb_corporation ( name, description ) values ('MicroSoft', '微软')
</sql:update>

INSERT, 影响到的数据条数：${ result } <br/>

<sql:update var="result" dataSource="${ dataSource }">
	insert into tb_corporation ( name, description ) values ('IBM', '国际商用机器')
</sql:update>

INSERT, 影响到的数据条数：${ result } <br/>

</body>
</html>
