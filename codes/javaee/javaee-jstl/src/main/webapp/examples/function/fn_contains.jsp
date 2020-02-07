<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>fn:contains 示例</title>
</head>
<body>

<c:set var="theString" value="I am from China"/>

<c:if test="${fn:contains(theString, 'china')}">
<p>找到 china
<p>
	</c:if>

	<c:if test="${fn:contains(theString, 'CHINA')}">
<p>找到 CHINA
<p>
	</c:if>

</body>
</html>
