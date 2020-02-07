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

	double[] numbers = {0, 10000, 55.0, -123.2568};
	request.setAttribute("numbers", numbers);
%>

<fmt:setLocale value="${ param.locale }"/>

当前格式：<c:out value="${ param.locale }" default="${ pageContext.request.locale } "></c:out>

&nbsp;

<c:forEach items="${ localeList }" var="locale">
	<a href="${ pageContext.request.requestURI }?locale=${ locale }">${ locale }</a>&nbsp;
</c:forEach>

<table>
	<tr class="title">
		<td>数字原值</td>
		<td>数字格式</td>
		<td>货币格式</td>
		<td>百分数格式</td>
	</tr>

	<c:forEach items="${ numbers }" var="number">
		<tr>
			<td>${ number }</td>
			<td><fmt:formatNumber value="${ number }" type="number"
														maxFractionDigits="4" minIntegerDigits="3" maxIntegerDigits="3"
														minFractionDigits="2"/></td>
			<td><fmt:formatNumber value="${ number }" type="currency"/></td>
			<td><fmt:formatNumber value="${ number }" type="percent"/></td>
		</tr>
	</c:forEach>

</table>


</body>
</html>
