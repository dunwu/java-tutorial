package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostServlet extends HttpServlet {

	private static final long serialVersionUID = 2112378505872783022L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("请使用 POST 方式提交数据。");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 从 文本框 text 中取姓名
		String name = request.getParameter("name");
		// 从 密码域 password 中取密码
		String password = request.getParameter("password");
		// 从 单选框 checkbox 中取性别
		String sex = request.getParameter("sex");

		int age = 0;
		try {
			// 取 年龄. 需要把 字符串 转换为 int.
			// 如果格式不对会抛出 NumberFormattingException
			age = Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
		}

		Date birthday = null;
		try {
			// 取 生日. 需要把 字符串 转化为 Date.
			// 如果格式不对会抛出 ParseException
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			birthday = format.parse(request.getParameter("birthday"));
		} catch (Exception e) {
		}

		// 从 多选框 checkbox 中取多个值
		String[] interesting = request.getParameterValues("interesting");
		// 从 下拉框 select 中取值
		String area = request.getParameter("area");
		// 从 文本域 textarea 中取值
		String description = request.getParameter("description");

		// 取 提交按钮 的键值
		String btn = request.getParameter("btn");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD><TITLE>感谢您提交信息</TITLE>");
		out.println("<style>");
		out.println("body, div, td, input {font-size:12px; margin:0px; }");
		out.println(".line {margin:2px; }");
		out.println(".leftDiv {width:110px; float:left; height:22px; line-height:22px; font-weight:bold; }");
		out.println(".rightDiv {height:22px; line-height:22px; }");
		out.println(".button {");
		out.println("	color:#fff;");
		out.println("	font-weight:bold;");
		out.println("	font-size: 11px; ");
		out.println("	text-align:center;");
		out.println("	padding:.17em 0 .2em .17em;");
		out.println("	border-style:solid;");
		out.println("	border-width:1px;");
		out.println("	border-color:#9cf #159 #159 #9cf;");
		out.println("	background:#69c url(/servlet/images/bg-btn-blue.gif) repeat-x;");
		out.println("</style>");
		out.println("</HEAD>");

		out.println("<div align=\"center\"><br/>");
		out.println("<fieldset style='width:90%'><legend>填写用户信息</legend><br/>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>您的姓名：</div>");
		out.println("			<div align='left' class='rightDiv'>" + name + "</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>您的密码：</div>");
		out.println("			<div align='left' class='rightDiv'>" + password + "</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>您的性别：</div>");
		out.println("			<div align='left' class='rightDiv'>" + sex + "</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>您的年龄：</div>");
		out.println("			<div align='left' class='rightDiv'>" + age + "</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>您的生日：</div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println(new SimpleDateFormat("yyyy年MM月dd日").format(birthday));
		out.println("			</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>您的兴趣：</div>");
		out.println("			<div align='left' class='rightDiv'>");

		if (interesting != null) {
			for (String str : interesting) {
				out.println(str + ", ");
			}
		}

		out.println("			</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>自我描述：</div>");
		out.println("			<div align='left' class='rightDiv'>" + description + "</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>按钮键值：</div>");
		out.println("			<div align='left' class='rightDiv'>" + btn + "</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'></div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println(
			"				<br/><input type='button' name='btn' value='返回上一页' onclick='history.go(-1); ' class='button'><br/>");
		out.println("			</div>");
		out.println("		</div>");

		out.println("<BODY>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
