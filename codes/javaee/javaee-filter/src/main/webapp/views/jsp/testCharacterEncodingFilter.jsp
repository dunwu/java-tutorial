<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		body, input, textarea {
			font-size: 12px;
		}

		textarea {
			width: 400px;
			height: 50px;
		}
	</style>
</head>
<body>
<pre>
<b>您输入了</b>：
${ param.text }
</pre>
<form action="${ param.request.requestURL }" method="post"><textarea
	name="text">${ param.text }</textarea> <br/>
	<input type="submit"></form>

</body>
</html>
