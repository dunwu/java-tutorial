<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Java Bean Actions</title>
	<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body>
<br/>

<%-- 声明 Person 类对象 person --%>
<jsp:useBean id="person" class="io.github.dunwu.javaee.jsp.bean.Person" scope="page"></jsp:useBean>

<%-- 设置 person 的所有属性，所有的属性值从 request 中自动取得 --%>
<jsp:setProperty name="person" property="*"/>

<div align="center">
	<form action="method.jsp" method="get">
		<fieldset style='width: 300'>
			<legend>请填写 Person 信息</legend>
			<table align="center" width="400">
				<tr>
					<td align="right" style="font-weight: bold;">姓名：</td>
					<td>
						<%-- 获取 person 的 name 属性 --%>
						<jsp:getProperty name="person" property="name"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold;">年龄：</td>
					<td>
						<%-- 获取 person 的 age 属性 --%>
						<jsp:getProperty name="person" property="age"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold;">性别：</td>
					<td>
						<%-- 获取 person 的 sex 属性 --%>
						<jsp:getProperty name="person" property="sex"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold;"></td>
					<td><input type="button" onclick="history.go(-1); " value=" 返回 " class="button">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>

