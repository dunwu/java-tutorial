<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8" %>
<%@ page import="java.util.Calendar,java.util.GregorianCalendar" %>
<html>
<head>
	<title>自动刷新实例</title>
</head>
<body>

<h2>每秒刷新时间</h2>
<%
	// 设置每秒刷新一次
	response.setIntHeader("Refresh", 1);
	// 获取当前时间
	Calendar calendar = new GregorianCalendar();
	String am_pm;
	int hour = calendar.get(Calendar.HOUR);
	int minute = calendar.get(Calendar.MINUTE);
	int second = calendar.get(Calendar.SECOND);
	if (calendar.get(Calendar.AM_PM) == 0) {
		am_pm = "AM";
	} else {
		am_pm = "PM";
	}
	String CT = hour + ":" + minute + ":" + second + " " + am_pm;
	out.println("当前时间为: " + CT + "\n");
%>

</body>
</html>
