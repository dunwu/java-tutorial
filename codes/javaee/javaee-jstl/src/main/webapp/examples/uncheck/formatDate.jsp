<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.lang.reflect.Field" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Locale" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body {
			font-size: 14px;
		}

		table {
			border-collapse: collapse;
			border: 1px solid #000000;
			margin-top: 5px;
		}

		td {
			border: 1px solid #000000;
			padding: 2px;
			text-align: center;
			font-size: 12px;
		}

		.title td {
			background: #EEEEEE;
			width: 100px;
		}
	</style>
</head>
<body>

<%
	Field[] field = Locale.class.getFields();

	List<Locale> localeList = new ArrayList<Locale>();

	for (int i = 0; i < field.length; i++) {
		if (field[i].getType().equals(Locale.class)) {
			localeList.add((Locale) field[i].get(null));
		}
	}

	request.setAttribute("localeList", localeList);
%>


<table>
	<tr>
		<td>Locale</td>
		<td>Date and Time</td>
		<td>Number</td>
		<td>currency</td>
	</tr>

	<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>

	<c:forEach var="locale" items="${ localeList }">
		<fmt:setLocale value="${ locale }"/>
		<tr>
			<td>${ locale }</td>
			<td><fmt:formatDate value="${ date }" type="both"/></td>
			<td><fmt:formatNumber value="10000.5"/></td>
			<td><fmt:formatNumber value="10000.5" type="currency"/></td>
		</tr>
	</c:forEach>

</table>

</body>
</html>
