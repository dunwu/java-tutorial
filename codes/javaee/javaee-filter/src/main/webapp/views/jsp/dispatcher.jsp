<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${ pageContext.request.requestURI }</title>
</head>
<body>

你正在访问 ${ pageContext.request.requestURI }?${ pageContext.request.queryString }.

</body>
</html>
