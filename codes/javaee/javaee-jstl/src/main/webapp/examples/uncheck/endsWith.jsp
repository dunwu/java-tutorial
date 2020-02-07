<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.File" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body, td {
			font-size: 12px;
		}

		table {
			border-collapse: collapse;
			border: 1px solid #000000;
		}

		td {
			border: 1px solid #000000;
			padding: 2px;
		}

		.title td {
			text-align: center;
			background: #EEEEEE;
		}
	</style>
</head>
<body>

<%
	request.setAttribute("files", new File("c:\\").listFiles());
%>

<table>
	<tr class="title">
		<td>File Name</td>
		<td>Type</td>
	</tr>

	<c:forEach var="file" items="${ files }">
		<tr>
			<td>${ file.name }</td>
			<td>

				<c:choose>
					<c:when test="${ file.directory }">文件夹</c:when>
					<c:otherwise>
						<c:if test="${ fn:endsWith(file.name, '.jpg') }">JPG 图片</c:if>
						<c:if test="${ fn:endsWith(file.name, '.exe') }">EXE 应用程序</c:if>
						<c:if test="${ fn:endsWith(file.name, '.gif') }">GIF 图片</c:if>
						<c:if test="${ fn:endsWith(file.name, '.txt') }">TXT 文本文件</c:if>
						<c:if test="${ fn:endsWith(file.name, '.doc') }">WORD 文件</c:if>
						<c:if test="${ fn:endsWith(file.name, '.xls') }">Excel 文件</c:if>
						<c:if test="${ fn:endsWith(file.name, '.log') }">LOG 日志文件</c:if>
						<c:if test="${ fn:endsWith(file.name, '.sql') }">SQL 数据库脚本文件</c:if>
					</c:otherwise>
				</c:choose>

			</td>
		</tr>
	</c:forEach>

</table>

</body>
</html>
