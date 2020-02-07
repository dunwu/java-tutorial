<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="sun.misc.BASE64Decoder"/>
<jsp:directive.page trimDirectiveWhitespaces="true"/>
<%
	// 清除输出
	out.clear();

	for (Cookie cookie : request.getCookies()) {

		if (cookie.getName().equals("file")) {

			// 从 Cookie 中取二进制数据
			byte[] binary = BASE64Decoder.class.newInstance().decodeBuffer(cookie.getValue().replace(" ", ""));

			// 设置内容类型为 gif 图片
			response.setHeader("Content-Type", "image/gif");
			response.setHeader("Content-Disposition", "inline;filename=cookie.gif");
			response.setHeader("Connection", "close");

			// 设置长度
			response.setContentLength(binary.length);

			// 输出到客户端
			response.getOutputStream().write(binary);
			response.getOutputStream().flush();
			response.getOutputStream().close();

			return;
		}
	}

%>
