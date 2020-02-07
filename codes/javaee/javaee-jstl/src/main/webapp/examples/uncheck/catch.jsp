<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		body {
			font-size: 12px;
		}
	</style>
</head>
<body>

<c:catch var="e">
	<c:set target="someBean" property="someProperty" value="Some Value"></c:set>
</c:catch>

<c:if test="${ e != null }">
	程序抛出了异常 ${ e.class.name }，原因: ${ e.message }
</c:if>


</body>
</html>
