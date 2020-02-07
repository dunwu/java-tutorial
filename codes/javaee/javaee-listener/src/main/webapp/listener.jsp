<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:directive.page
	import="io.github.dunwu.javaee.listener.util.ApplicationConstants"/>
<jsp:directive.page import="java.text.DateFormat"/>
<jsp:directive.page import="java.util.Date"/>
<%
	String action = request.getParameter("action");
	String name = request.getParameter("name");
	String value = request.getParameter("value");

	session.getId();

	if ("addRequestAttribute".equals(action)) {
		request.setAttribute(name, value);
	} else if ("removeRequestAttribute".equals(action)) {
		request.removeAttribute(name);
	} else if ("addSessionAttribute".equals(action)) {
		session.setAttribute(name, value);
	} else if ("removeSessionAttribute".equals(action)) {
		session.removeAttribute(name);
	} else if ("logout".equals(action)) {
		session.invalidate();
		out.println("<a href=listener.jsp>返回</a>");
		return;
	}
%>
<style>body, td {
	font-size: 12px;
}</style>
<table border=1>
	<tr>
		<td>
			<form><input name=name/> <input name=value> <input
				type=submit name=action value="addRequestAttribute"/> <input
				type=submit name=action value="removeRequestAttribute"/></form>
		</td>
		<td>
			<form><input name=name/> <input name=value> <input
				type=submit name=action value="addSessionAttribute"/> <input
				type=submit name=action value="removeSessionAttribute"/> <a
				href="listener.jsp?action=logout">删除 Session</a></form>
		</td>
	</tr>
</table>
<script>document.write(document.cookie); </script>
<br/>
<br/>
<br/>

服务器启动时间：<%=DateFormat.getDateTimeInstance().format(ApplicationConstants.START_DATE)%>，
累计共接待过 <%= ApplicationConstants.TOTAL_HISTORY_COUNT %> 访客。<br/>
同时在线人数最多为 <%= ApplicationConstants.MAX_ONLINE_COUNT %> 人，
发生在 <%=DateFormat.getDateTimeInstance().format(ApplicationConstants.MAX_ONLINE_COUNT_DATE)%>。 <br/>

目前在线总数：<%= ApplicationConstants.SESSION_MAP.size() %>，登录用户：<%=ApplicationConstants.CURRENT_LOGIN_COUNT%>。<br/>
<table border=1>
	<tr>
		<th>jsessionid</th>
		<th>account</th>
		<th>creationTime</th>
		<th>lastAccessedTime</th>
		<th>new</th>
		<th>activeTimes</th>
		<th>ip</th>
	</tr>
	<%
		for (String id : ApplicationConstants.SESSION_MAP.keySet()) {
			HttpSession sess = ApplicationConstants.SESSION_MAP.get(id);
	%>
	<tr>
		<td><%=id%>
		</td>
		<td><%=sess.getAttribute("account")%>
		</td>
		<td><%=DateFormat.getDateTimeInstance().format(new Date(sess.getCreationTime()))%>
		</td>
		<td><%=DateFormat.getDateTimeInstance().format(new Date(sess.getLastAccessedTime()))%>
		</td>
		<td><%=sess.isNew()%>
		</td>
		<td><%=sess.getAttribute("activeTimes")%>
		</td>
		<td><%=sess.getAttribute("ip")%>
		</td>
	</tr>
	<%
		}
	%>
</table>


