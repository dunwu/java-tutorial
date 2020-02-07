<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

<c:set var="totalCount" value="${ totalCount + 1 }" scope="application"></c:set>
<c:set var="count" value="${ count + 1 }" scope="session"></c:set>
本网站总访问人次：${ totalCount } <br/>
其中您的访问次数：${ count } <br/>

<c:set var="test" value="by value property"></c:set>
<c:set var="test">by body</c:set>

<br/>
<br/>
<br/>
<br/>
<%
	request.setAttribute("person", new com.helloweenvsfei.jstl.bean.Person());
	request.setAttribute("map", new java.util.HashMap());
%>
<c:set target="${ person }" property="name" value="${ param.name }"></c:set>
${ person.name }

<c:set target="${ map }" property="name" value="${ param.name }"/>
${ map.name }

</body>
</html>
