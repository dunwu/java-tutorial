<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%
	// 获取session创建时间
	Date createTime = new Date(session.getCreationTime());
	// 获取最后访问页面的时间
	Date lastAccessTime = new Date(session.getLastAccessedTime());

	String note = "再次访问";
	Integer visitCount = 0;
	String visitCountKey = "visitCount";
	String userIDKey = "userID";
	String userID = "ABCD";

	// 检测网页是否由新的访问用户
	if (session.isNew()) {
		note = "第一次访问";
		session.setAttribute(userIDKey, userID);
		session.setAttribute(visitCountKey, visitCount);
	} else {
		visitCount = (Integer) session.getAttribute(visitCountKey);
		if (null == visitCount) {
			visitCount = 0;
		}
		visitCount += 1;
		userID = (String) session.getAttribute(userIDKey);
		if (null == userID) {
			userID = "ABCD";
		}
		session.setAttribute(visitCountKey, visitCount);
	}
%>
<html>
<head>
	<title>Session 示例</title>

</head>
<body>

<%--提示语--%>
<h2><% out.print(note); %></h2>

<table border="1" align="center">
	<tr bgcolor="#949494">
		<th>Session 信息</th>
		<th>值</th>
	</tr>
	<tr>
		<td>id</td>
		<td><% out.print(session.getId()); %></td>
	</tr>
	<tr>
		<td>创建时间</td>
		<td><% out.print(createTime); %></td>
	</tr>
	<tr>
		<td>最后访问时间</td>
		<td><% out.print(lastAccessTime); %></td>
	</tr>
	<tr>
		<td>用户 ID</td>
		<td><% out.print(userID); %></td>
	</tr>
	<tr>
		<td>访问次数</td>
		<td><% out.print(visitCount); %></td>
	</tr>
</table>
</body>
</html>
