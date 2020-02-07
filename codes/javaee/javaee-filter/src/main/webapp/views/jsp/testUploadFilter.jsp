<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.File" %>
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

header['Content-type'] = ${ header['Content-type'] }

<form action="" method="post" enctype="multipart/form-data">

	<input type="text" name="text1"> <%= request.getParameter("text1") %><br/>
	<input type="file" name="file1">
	<%
		File file1 = (File) request.getAttribute("file1");
		if (file1 != null) {
			out.println("<br/>文件: " + file1 + ", <br/>大小: " + file1.length());
		}
	%>  <br/><br/>

	<input type="text" name="text2"> <%= request.getParameter("text2") %><br/>
	<input type="file" name="file2">
	<%
		File file2 = (File) request.getAttribute("file2");
		if (file2 != null) {
			out.println("<br/>文件: " + file2 + ", <br/>大小: " + file2.length());
		}
	%> <br/><br/>

	<input type="submit" value=" 上传文件 ">

</form>

</body>
</html>
