<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:directive.page import="java.util.HashMap"/>
<jsp:directive.page import="java.util.Map"/>
<jsp:directive.page import="java.util.TimeZone"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		table, td, tr {
			font-size: 12px;
		}

		table {
			border-collapse: collapse;
			border: 1px solid #000000;
		}

		td, th {
			border: 1px solid #000000;
			padding: 2px;
			padding-left: 10px;
			padding-right: 10px;
		}

		.title {
			background: #EEEEEE;
		}

		.title td {
			text-align: center;
		}
	</style>
</head>
<body>

<%
	Map<String, TimeZone> hashMap = new HashMap<String, TimeZone>();

	for (String ID : TimeZone.getAvailableIDs()) {
		hashMap.put(ID, TimeZone.getTimeZone(ID));
	}

	request.setAttribute("timeZoneIds", TimeZone.getAvailableIDs());
	request.setAttribute("timeZone", hashMap);
%>

<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>

<fmt:setLocale value="zh_CN"/>

现在时刻：<%= TimeZone.getDefault().getDisplayName() %>
<fmt:formatDate value="${ date }" type="both"/> <br/>

<table>
	<tr>
		<th>时区ID</th>
		<th>时区</th>
		<th>现在时间</th>
		<th>时差</th>
	</tr>

	<c:forEach var="ID" items="${ timeZoneIds }" varStatus="status">
		<tr>
			<td>${ ID }</td>
			<td>${ timeZone[ID].displayName }</td>
			<td>
				<fmt:timeZone value="${ ID }">
					<fmt:formatDate value="${ date }" type="both" timeZone="${ ID }"/>
				</fmt:timeZone>
			</td>
			<td>${ timeZone[ID].rawOffset / 60 / 60 / 1000 }</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
