package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 7516608289980410869L;

	/**
	 * Constructor of the object.
	 */
	public AnnotationServlet() {
		this.log("AnnotationServlet()");
	}

	public void log(String str) {
		System.out.println(str);
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		this.log("destroy()");
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

		this.log("doGet()");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
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
		this.log("init()");
	}

	@PostConstruct
	public void postConstruct() {
		this.log("postConstruct()");
	}

	public @PreDestroy
	void preDestroy() {
		this.log("preDestroy()");
	}

}
