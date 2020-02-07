package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第一个Servlet
 */
public class FirstServlet extends HttpServlet {

	private static final long serialVersionUID = 2386052823761867369L;

	/**
	 * 以 GET 方式访问页面时执行该函数。 执行 doGet 前会先执行 getLastModified，如果浏览器发现 getLastModified 返回的数值
	 * 与上次访问时返回的数值相同，则认为该文档没有更新，浏览器采用缓存而不执行 doGet。 如果 getLastModified 返回 -1，则认为是时刻更新的，总是执行该函数。
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 调用 HttpServlet 自带的日志函数输出信息到控制台
		this.log("执行 doGet 方法... ");

		// 处理 doGet
		this.execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 访问该 Servlet 的 URI
		String requestURI = request.getRequestURI();
		// 访问该 Servlet 的方式，是 GET 还是 POST
		String method = request.getMethod();
		// 客户端提交的参数 param 值
		String param = request.getParameter("param");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("	以 " + method + " 方式访问该页面。取到的 param 参数为：" + param + "<br/>");

		out.println("	<form action='" + requestURI
			+ "' method='get'><input type='text' name='param' value=''><input type='submit' value='以 GET 方式访问 RequestServlet'></form>");
		out.println("	<form action='" + requestURI
			+ "' method='post'><input type='text' name='param' value=''><input type='submit' value='以 POST 方式访问 RequestServlet'></form>");

		// 由客户端浏览器读取该文档的更新时间
		out.println("	<script>document.write('本页面最后更新时间：' + document.lastModified + '<br />'); </script>");
		out.println("	<script>document.write('本页面URL：' + location + '<br/>' ); </script>");

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * 以 POST 方式访问页面时执行该函数。
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 调用 HttpServlet 自带的日志函数输出信息到控制台
		this.log("执行 doPost 方法... ");

		// 处理 doPost
		this.execute(request, response);
	}

	/**
	 * 返回该 Servlet 生成的文档的更新时间。对 Get 方式访问有效。 返回的时间为相对于 1970年1月1日08:00:00 的毫秒数。 如果为 -1 则认为是实时更新。默认为 -1。
	 */
	@Override
	public long getLastModified(HttpServletRequest request) {

		// 调用 HttpServlet 自带的日志函数输出信息到控制台
		this.log("执行 getLastModified 方法... ");

		return 0;
	}

}
