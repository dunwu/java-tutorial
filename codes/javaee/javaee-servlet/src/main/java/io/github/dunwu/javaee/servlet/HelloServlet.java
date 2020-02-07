/**
 * The Apache License 2.0 Copyright (c) 2016 Zhang Peng
 */
package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2016/12/23.
 */
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public HelloServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doPost method of the servlet. <br>
	 * <p>
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * The doGet method of the servlet. <br>
	 * <p>
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置 request，response 编码方式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 设置文档类型
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// 输出到客户端浏览器
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");

		String requestURI = request.getRequestURI();
		out.println("<form action='" + requestURI + "' method='post'>");
		out.println("请输入您的名字：<input type='text' name='name' />");
		out.println("<input type='submit' />");
		out.println("</form>");
		out.println("");

		// 取浏览器提交的 name 参数
		String name = request.getParameter("name");

		// 如果 name 不为空且长度大于 0
		if (name != null && name.trim().length() > 0) {
			out.println("您好, <b>" + name + "</b>. 欢迎来到 Java Web 世界. ");
		}

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
