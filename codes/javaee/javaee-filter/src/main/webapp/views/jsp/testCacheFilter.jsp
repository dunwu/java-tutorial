<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body {
			font-size: 12px;
		}

		div {
			float: left;
			margin-left: 20px;
		}

		a {
			color: #0000FF;
		}
	</style>
</head>
<body>

<div id="loginDiv">
	<a href="testCacheFilter.jsp" onclick="setCookie('username','zhangsan');">登录</a>
	<a href="testCacheFilter.jsp" onclick="setCookie('username','lisi');setCookie('role','admin');">登录为管理员</a>
</div>

<div id="adminDiv" style="display: none;">
	<a href="testCacheFilter.jsp">会员管理</a>
	<a href="testCacheFilter.jsp">公告管理</a>
</div>

<div id="controlDiv" style="display: none;">
	<a href="testCacheFilter.jsp">个人设置</a>
	<a href="testCacheFilter.jsp">修改密码</a>
	<a href="testCacheFilter.jsp" onclick="setCookie('username','');setCookie('role','');">退出</a>
</div>

<script type="text/javascript">
  function setCookie(name, value) {
    document.cookie = name + '=' + value;
  }

  function getCookie(name) {
    var search = name + "=";
    if (document.cookie.length > 0) {
      offset = document.cookie.indexOf(search);
      if (offset != -1) {
        offset += search.length;
        end = document.cookie.indexOf(";", offset);
        if (end == -1) end = document.cookie.length;
        return unescape(document.cookie.substring(offset, end))
      } else return ""
    }
  }

  if (getCookie('username')) {
    // 已经登录  隐藏登录菜单
    document.getElementById('loginDiv').innerText = '欢迎你, ' + getCookie('username');
    // 显示登录后的操作
    document.getElementById('controlDiv').style.display = 'block';
  }
  if (getCookie('role') == 'admin') {
    // 为管理员 显示管理员操作
    document.getElementById('adminDiv').style.display = 'block';
  }
</script>

</body>
</html>
