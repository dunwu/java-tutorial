<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URL" %>
<%@page import="java.net.URLConnection" %>
<%@page import="java.text.NumberFormat" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body {
			margin-top: 2px;
		}

		table {
			margin-top: 10px;
			border-collapse: collapse;
			border: 1px solid #000000;
			width: 350px;
		}

		td {
			border: 1px solid #000000;
			font-size: 12px;
			padding: 2px;
			background: #DDDDFF;
		}
	</style>
</head>
<body>
<%!
	public void test(JspWriter out, String url) throws Exception {

		// 模拟支持 GZIP 的浏览器
		URLConnection connGzip = new URL(url).openConnection();
		connGzip.setRequestProperty("Accept-Encoding", "gzip");
		int lengthGzip = connGzip.getContentLength();

		// 模拟不支持 GZIP 的浏览器
		URLConnection connCommon = new URL(url).openConnection();
		int lengthCommon = connCommon.getContentLength();

		double rate = new Double(lengthGzip) / lengthCommon;

		out.println("<table>");
		out.println("	<tr>");
		out.println("		<td colspan=3>网址: " + url + "</td>");
		out.println("	</tr>");
		out.println("	<tr>");
		out.println("		<td>压缩后:" + lengthGzip + " byte</td>");
		out.println("		<td>压缩前:" + lengthCommon + " byte</td>");
		out.println("		<td>比率:" + NumberFormat.getPercentInstance().format(rate) + "</td>");
		out.println("	</tr>");
		out.println("</table>");
	}
%>
<%
	String[] urls = {"http://localhost:9899/views/js/dojo.js", "http://localhost:9899/views/images/image.jsp",
		"http://localhost:9899/views/images/winter.jpg", "http://www.baidu.com", "http://www.google.cn",};
	for (String url : urls) {
		test(out, url);
	}
%>

</body>
</html>
