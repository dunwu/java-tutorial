<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="java.io.File"/>
<jsp:directive.page import="java.io.InputStream"/>
<jsp:directive.page import="sun.misc.BASE64Encoder"/>
<%
	File file = new File(this.getServletContext().getRealPath("../../images/cookie.gif"));

	// 二进制数组
	byte[] binary = new byte[(int) file.length()];

	// 从图片文件读取二进制数据.
	InputStream ins = this.getServletContext().getResourceAsStream(file.getName());
	ins.read(binary);
	ins.close();

	// BASE64 编码
	String content = BASE64Encoder.class.newInstance().encode(binary);

	// 包含二进制数据的 Cookie
	Cookie cookie = new Cookie("file", content);

	// 将 Cookie 发送到客户端
	response.addCookie(cookie);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Cookie Encoding</title>
</head>
<body>
从 Cookie 中获取到的二进制图片：<img src="base64_decode.jsp"/> <br/>
<textarea id='cookieArea' style='width:100%; height:200px; '></textarea>
<script type="text/javascript">cookieArea.value = document.cookie;</script>
</body>
</html>
