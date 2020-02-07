<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="com.helloweenvsfei.util.ip.IPSeeker" %>
<%!
	// 全局变量
	private IPSeeker ipSeeker = IPSeeker.getInstance();

	// 方法一
	public String getArea(String ip) {
		return ipSeeker.getArea(ip);
	}

	//方法二
	public String getCountry(String ip) {
		return ipSeeker.getCountry(ip);
	}

	// 方法三 正则表达式判断是否合法 IP 地址
	public boolean isValidIp(String ip) {
		return ip != null && ip.trim().matches("^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$");
	}
%>
<html>
<head>
	<title>IP 地址查询</title>
	<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body><br/>
<%
	String ip = request.getParameter("ip");
	String area = "";
	String country = "";

	// 如果是合法的 IP 地址
	if (isValidIp(ip)) {
		// 调用方法一
		country = getCountry(ip);
		// 调用方法二
		area = getArea(ip);
	}

%>
<div align="center">
	<form action="method.jsp" method="get">
		<fieldset style='width: 500'>
			<legend>IP 地址查询</legend>
			<table align="center" width="400">
				<%
					if (isValidIp(ip)) {
				%>
				<tr>
					<td align="right">IP 地址：</td>
					<td><%= ip %>
					</td>
				</tr>
				<tr>
					<td align="right">国家：</td>
					<td><%= country %>
					</td>
				</tr>
				<tr>
					<td align="right">地区：</td>
					<td><%= area %>
					</td>
				</tr>
				<%
					}
				%>
				<tr height="40">
					<td align="right">请输入要查询的 IP 地址：</td>
					<td><input type="text" name="ip" value="" style="width:200px; "/></td>
				</tr>
				<tr height="40">
					<td colspan="2" align="center">
						<input type="submit" name="search" value=" 查询 IP 地址 " class="button">
					</td>
				</tr>

			</table>
		</fieldset>
	</form>
</div>

</body>
</html>
