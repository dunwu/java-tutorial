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

<!-- fmt:setLocale value="en"/ -->

<fmt:bundle basename="messages">

	<fmt:message key="prompt.hello">
		<fmt:param value="Helloween"></fmt:param>
	</fmt:message> <br/>

	<fmt:message key="prompt.greeting"></fmt:message>

</fmt:bundle>

<!--
<br/>
<br/>
<br/>

<fmt:bundle basename="com.helloweenvsfei.resources.messages" prefix="prompt.">

	<fmt:message key="hello" >
		<fmt:param value="Helloween"></fmt:param>
	</fmt:message> <br/>

	<fmt:message key="greeting"></fmt:message>

</fmt:bundle>
 -->

</body>
</html>
