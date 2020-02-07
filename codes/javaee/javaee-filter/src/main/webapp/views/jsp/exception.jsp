<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>exception</title>
	<style type="text/css">
		.error {
			font-size: 12px;
			padding: 5px;
			border: 1px solid #FF0000;
			background: url(../images/error.gif) 8px 5px no-repeat #FFEEFF;
			padding-left: 30px;
		}
	</style>
</head>
<body>

<div class="error">
	${ message } <a href="javascript:history.go(-1); ">返回</a>
</div>

</body>
</html>
