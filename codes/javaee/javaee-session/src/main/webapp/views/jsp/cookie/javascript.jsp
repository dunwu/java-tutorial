<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>欢迎您</title>
	<link rel="stylesheet" type="text/css" href="../../css/style.css">
	<script type="text/javascript" src="history.js">
    function getCookie(name) {
      var str = document.cookie;
      if (!str || str.indexOf(name + "=") < 0) return;
      var cookies = str.split("; ");
      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        if (cookie.indexOf(name + "=") == 0) {
          var value = cookie.substring(name.length + 1);
        }
      }
    }

    function setCookie(name, value) {
      var expires = (arguments.length > 2) ? arguments[2] : null;
      var path = (arguments.length > 3) ? arguments[3] : null;
      var domain = (arguments.length > 4) ? arguments[4] : null;
      var secure = (arguments.length > 5) ? arguments[5] : false;
      document.cookie = name + "=" + encodeURI(value) + ((expires == null) ? "" : ("; expires="
          + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain="
          + domain)) + ((secure == true) ? "; secure" : "");
    }
	</script>
</head>
<body>
<div align="center" style="margin:10px; ">
	<fieldset>
		<legend>当前有效的 Cookie</legend>
		<div id="cookieDiv"></div>
		<script type="text/javascript">
      cookieDiv.innerHTML = document.cookie;
		</script>
	</fieldset>
	<fieldset>
		<legend>
			欢迎您
		</legend>
		<table>
			<tr>
				<td>
					读取 Cookie：
				</td>
				<td>
					<input name="name1"/> <input class="button" type="button" value="读取"
																			 onclick="alert(getCookie(name1.value));">
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					设置 Cookie：
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td align="right">
					Name 属性：
				</td>
				<td>
					<input name="name2"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					Value 属性：
				</td>
				<td>
					<input name="value2"/>
				</td>
			</tr>
			<!--
			<tr>
				<td align="right">
					Age 属性：
				</td>
				<td>
					<input name="age2" /> 单位：毫秒
				</td>
			</tr>
			 -->
			<tr>
				<td>
				</td>
				<td>
					<input type="button" value="设置"
								 onclick="setCookie(name2.value, value2.value); cookieDiv.innerHTML = document.cookie; " class="button">
				</td>
			</tr>
		</table>
	</fieldset>
</div>
</body>
</html>
