<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:directive.page import="io.github.dunwu.javaee.listener.bean.PersonInfo"/>
<%
	String action = request.getParameter("action");
	String account = request.getParameter("account");

	if ("login".equals(action) && account.trim().length() > 0) {

		// 登录，将personInfo放入session
		PersonInfo personInfo = new PersonInfo();
		personInfo.setAccount(account.trim().toLowerCase());
		personInfo.setIp(request.getRemoteAddr());
		personInfo.setLoginDate(new java.util.Date());

		session.setAttribute("personInfo", personInfo);

		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		return;
	} else if ("logout".equals(action)) {

		// 注销，将personInfo从session中移除
		session.removeAttribute("personInfo");

		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body {
			font-size: 12px;
		}
	</style>
</head>
<body>

<c:choose>

	<c:when test="${ personInfo != null }">
		<!-- 已经登录，将显示帐号信息 -->
		欢迎您，${ personInfo.account }。<br/>
		您的登录IP为${ personInfo.ip }，<br/>
		登录时间为<fmt:formatDate value="${ personInfo.loginDate }" pattern="yyyy-MM-dd HH:mm"/>。
		<a href="${ pageContext.request.requestURI }?action=logout">退出</a>

		<!-- 每5秒钟刷新一次页面 -->
		<script>setTimeout("location=location; ", 5000); </script>
	</c:when>

	<c:otherwise>
		<!-- 没有登录，将显示登录页面 -->
		${ msg }
		<c:remove var="msg" scope="session"/>
		<form action="${ pageContext.request.requestURI }?action=login" method="post">
			帐号：
			<input name="account"/>
			<input type="submit" value="登录">
		</form>
	</c:otherwise>

</c:choose>

</body>
</html>
