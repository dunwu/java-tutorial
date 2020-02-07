<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>


header['User-Agent'] = "${ header['User-Agent'] }"; <br/><br/>

您使用
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'MSIE') }">IE 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'Firefox') }">Firefox 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'Maxthon') }">Maxth 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'MyIE2') }">MyIE2 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'Opera') }">Opera 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'TencentTraveler ') }">腾讯 Traveler 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'TheWorld ') }">世界之窗 浏览器</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'Kubuntu') }">Kubuntu 浏览器</c:if>
，
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'Windows') }">Windows 操作系统</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'linux') }">Linux 操作系统</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'SunOS') }">Sun 操作系统</c:if>
<c:if test="${ fn:containsIgnoreCase(header['User-Agent'], 'Mac') }">Mac 操作系统</c:if>
。

</body>
</html>
