<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>fmt:formatNumber 标签</title>
</head>
<body>
<h3>数字格式化:</h3>
<c:set var="balance" value="120000.2309"/>
<p>格式化数字 (1): <fmt:formatNumber value="${balance}"
																type="currency"/></p>
<p>格式化数字 (2): <fmt:formatNumber type="number"
																maxIntegerDigits="3" value="${balance}"/></p>
<p>格式化数字 (3): <fmt:formatNumber type="number"
																maxFractionDigits="3" value="${balance}"/></p>
<p>格式化数字 (4): <fmt:formatNumber type="number"
																groupingUsed="false" value="${balance}"/></p>
<p>格式化数字 (5): <fmt:formatNumber type="percent"
																maxIntegerDigits="3" value="${balance}"/></p>
<p>格式化数字 (6): <fmt:formatNumber type="percent"
																minFractionDigits="10" value="${balance}"/></p>
<p>格式化数字 (7): <fmt:formatNumber type="percent"
																maxIntegerDigits="3" value="${balance}"/></p>
<p>格式化数字 (8): <fmt:formatNumber type="number"
																pattern="###.###E0" value="${balance}"/></p>
<p>美元 :
	<fmt:setLocale value="en_US"/>
	<fmt:formatNumber value="${balance}" type="currency"/></p>
</body>
</html>
