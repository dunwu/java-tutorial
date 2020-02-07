<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head><title>JSP Scriptlets</title>
	<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body>
<br/>
<%
	Object[][] letters = {{true, "恭喜您注册的信息已经生效", "e_inn@163.com", "helloweenvsfei@gmail.com", "2007-8-8"},
		{true, "Java EE 5.0 release！！", "admin@sun.com", "helloweenvsfei@gmail.com", "2007-6-24"},
		{false, "来信已经收到，下周末见面商谈", "foo@bar.com", "helloweenvsfei@gmail.com", "2007-5-20"},
		{false, "您的博客有新的留言", "blog@foo.bar.com", "helloweenvsfei@gmail.com", "2007-3-2"},};
	String[] colors = {"#DDDDDD", "#AAAAAA",};
%>
<table border=0 cellspacing=1 cellpadding=2 width=700 align=center>
	<tr style="background: url(images/vertical_line.gif); ">
		<td align="center" style="line-height:22px; ">&nbsp;</td>
		<td align="center" style="line-height:22px; ">标题&nbsp;</td>
		<td align="center" style="line-height:22px; ">发信人&nbsp;</td>
		<td align="center" style="line-height:22px; ">收信人&nbsp;</td>
		<td align="center" style="line-height:22px; ">时间&nbsp;</td>
	</tr>
	<%
		for (int i = 0; i < letters.length; i++) {
			Object[] letter = letters[i];
	%>
	<tr style='background: <%= colors[i%2] %>'>
		<td align="center">
			<%
				if (letter[0] == Boolean.TRUE) {
			%>
			<img src="images/mail.gif"/>
			<%
				} else {
					out.println("&nbsp;");
				}
			%>
		</td>
		<td><a href="#"><%= letter[1] %>
		</a></td>
		<td><%= letter[2] %>
		</td>
		<td><%= letter[3] %>
		</td>
		<td align="center"><%= letter[4] %>
		</td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>
