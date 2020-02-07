<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>计数器</title>
	<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body><br/>

<%-- 定义一个 session 范围内的计数器，记录个人的访问信息 --%>
<jsp:useBean id="personCount" class="io.github.dunwu.javaee.jsp.bean.Counter" scope="session"/>

<%-- 定义一个 application 范围内的计数器，记录所有人的访问信息 --%>
<jsp:useBean id="totalCount" class="io.github.dunwu.javaee.jsp.bean.Counter" scope="application"/>

<div align="center">
	<form action="method.jsp" method="get">
		<fieldset style='width: 300'>
			<legend>计数器</legend>
			<table align="center" width="400">
				<tr>
					<td width=150 align="right" style="font-weight:bold; ">您的访问次数：</td>
					<td>
						<%-- 获取各人的访问次数 --%>
						<jsp:getProperty name="personCount" property="count"/>
						次
					</td>
				</tr>
				<tr>
					<td width=150 align="right" style="font-weight:bold; ">总共的访问次数：</td>
					<td>
						<%-- 获取所有人的访问次数 --%>
						<jsp:getProperty name="totalCount" property="count"/>
						次
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>

</body>
</html>

