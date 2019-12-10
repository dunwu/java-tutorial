<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>javatool</title>
</head>

<body>
<h1>javatool</h1>
<p><%out.print("Server Ip：" + basePath);%></p>
<div>
  <h2>示例列表</h2>
  <ul>
    <li>
      <a href="<%=basePath%>/hello/name?name=root">访问/hello?name=root</a><br>
    </li>
    <li>
      <a href="<%=basePath%>/hello/log">访问/hello/log</a><br>
    </li>
  </ul>
</div>
</body>
</html>
