/**
 * The Apache License 2.0 Copyright (c) 2017 Zhang Peng
 */
package io.github.dunwu.javaee.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/26.
 */
@WebServlet("/servlet/ReadCookies")
public class ReadCookies extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadCookies() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
		Cookie[] cookies = null;
		// 获取与该域相关的 Cookie 的数组
		cookies = request.getCookies();

		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String title = "Delete Cookie Example";
		String docType = "<!DOCTYPE html>\n";
		out.println(
			docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n");
		if (cookies != null) {
			out.println("<h2>Cookie 名称和值</h2>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ((cookie.getName()).compareTo("name") == 0) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					out.print("已删除的 cookie：" + cookie.getName() + "<br/>");
				}
				out.print("名称：" + cookie.getName() + "，");
				out.print("值：" + URLDecoder.decode(cookie.getValue(), "utf-8") + " <br/>");
			}
		} else {
			out.println("<h2 class=\"tutheader\">No Cookie founds</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
