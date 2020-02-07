<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>javaee-filter 首页</title>
</head>

<body>
<h1>javaee-filter 首页</h1>
<p><%out.print("Server Ip：" + basePath);%></p>
<div>
	<h2>示例列表</h2>
	<ul>
		<li>
			<a href="/views/jsp/image.jsp">防盗链Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testCharacterEncodingFilter.jsp">字符编码Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testExceptionHandlerFilter.jsp">捕捉异常Filter示例</a><br>
		</li>
		<li>
			<a href="http://localhost:8080/list123.do?action=add">权限验证Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testOutputReplaceFilter.jsp">内容替换Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testGZipFilter.jsp">GZIP压缩Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testWaterMarkFilter.jsp">图像水印Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testCacheFilter.jsp">缓存Filter示例</a><br>
		</li>
		<li>
			<a href="/views/xml/demo.xml">XSLT转换Filter示例</a><br>
		</li>
		<li>
			<a href="/views/jsp/testUploadFilter.jsp">文件上传Filter示例</a><br>
		</li>
	</ul>
</div>
</body>
</html>
