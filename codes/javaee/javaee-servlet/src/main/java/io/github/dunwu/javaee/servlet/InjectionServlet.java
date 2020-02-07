package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectionServlet extends HttpServlet {

	private static final long serialVersionUID = -8526907492073769090L;

	// 注入的 字符串
	private @Resource(name = "hello")
	String hello;

	// 注入的 整数
	private @Resource(name = "i")
	int i;

	// 注入更常见的写法
	@Resource(name = "persons")
	private String persons;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>资源注入</TITLE></HEAD>");
		out.println("<style>body {font-size:12px; }</style>");

		out.println("<b>注入的字符串</b>：<br/>&nbsp;&nbsp;-&nbsp;" + hello + "<br/>");
		out.println("<b>注入的整数</b>：<br/>&nbsp;&nbsp;-&nbsp;" + i + "<br/>");
		out.println("<b>注入的字符串数组</b>：<br/>");

		for (String person : persons.split(",")) {
			out.println("&nbsp;&nbsp;-&nbsp;" + person + "<br/>");
		}

		out.println("  <BODY>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
