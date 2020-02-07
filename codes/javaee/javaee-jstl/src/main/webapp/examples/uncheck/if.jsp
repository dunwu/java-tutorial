<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<style>
		body, td, input, div, span {
			font-size: 12px;
		}
	</style>
</head>
<body>

<fieldset>

	<c:if test="${ param.action == 'add' }">
		<legend>添加操作</legend>
		<table>
			<tr>
				<td>帐号</td>
				<td><input type="text" name="login"/></td>
			</tr>
			<tr>
				<td>真实姓名</td>
				<td><input type="text" name="name"/></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${ param.action == 'edit' }">
		<legend>修改操作</legend>
		<table>
			<tr>
				<td>帐号</td>
				<td><c:out value="${ param.login }" default="Param login required. "/></td>
			</tr>
			<tr>
				<td>真实姓名</td>
				<td><input type="text" name="name"/></td>
			</tr>
		</table>
	</c:if>

	<table>
		<tr>
			<td><input type="submit"/></td>
		</tr>
	</table>


</fieldset>


</body>
</html>
