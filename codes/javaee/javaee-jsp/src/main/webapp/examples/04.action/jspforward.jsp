<jsp:directive.page language="java" contentType="text/html; charset=utf-8"/>
<jsp:directive.page trimDirectiveWhitespaces="false"/>
<%
	out.clear();
	if ("1".equals(request.getParameter("param"))) {
%>
<jsp:forward page="/somepage.jsp">
	<jsp:param name="param1" value="value1"/>
	<jsp:param name="param2" value="value2"/>
</jsp:forward>
<%
	}
%>
<html>
<head>
	<title>闀ㄤ讲鍓ラ弪镡煎禌?/title>
		<link rel='stylesheet' type='text/css' href='css/style.css'>
		</head>
<body>
i闀ㄥ洷娓规繛锲噭閵娿储鍕鹃柛褉锅挞梹妞ょ箰瀵剟寮?param=1 闀ㄤ礁娼″Λ鍓佹嫚閵夆敛钪妫婚ⅳ顑藉亾?
</body>
</html>

