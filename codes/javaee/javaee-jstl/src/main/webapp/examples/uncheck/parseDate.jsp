<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

<fmt:parseDate var="date" value="2008-12-12 08:00:00" pattern="yyyy-MM-dd HH:mm:ss" parseLocale="ja"></fmt:parseDate>
<fmt:formatDate value="${ date }" type="both" dateStyle="full" timeStyle="full" timeZone="America/Los_Angeles"/>

</body>
</html>
