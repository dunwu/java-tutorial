package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化参数示例 读取web.xml中的<init-param> <init-param>配置在<servlet>中，只能让对应的servlet使用； <context-param>配置在全局中，可以让所有的servlet使用。
 */
public class InitParamServlet extends HttpServlet {

	private static final long serialVersionUID = 7298032096933866458L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 提交的 username 参数
		String username = request.getParameter("username");
		// 提交的 password 参数
		String password = request.getParameter("password");
		// 取所有的初始化参数名称
		Enumeration params = this.getInitParameterNames();
		while (params.hasMoreElements()) {
			String usernameParam = (String) params.nextElement();
			// 取参数值
			String passnameParam = this.getInitParameter(usernameParam);
			// 如果 username 匹配且 password 匹配. username 大小写不敏感，password大小写敏感
			if (usernameParam.equalsIgnoreCase(username) && passnameParam.equals(password)) {
				// 显示文件。/WEB-INF 下的文件不能通过浏览器访问到，因此是安全的
				request.getRequestDispatcher("/WEB-INF/notice.html").forward(request, response);
				return;
			}
		}
		// username，password 不匹配，显示登录页面
		logger.warn("用户名={}、密码={}不匹配。", username, password);
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("InitParamServlet 初始化用户名、密码参数");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>请登录查看 Notice 文件</TITLE></HEAD>");
		out.println("<style>body, td, div {font-size:12px; }</style>");
		out.println("  <BODY>");
		out.println("<form action='" + request.getRequestURI() + "' method='post'>");
		out.println("帐号：<input type='text' name='username' style='width:200px; '> <br/>");
		out.println("密码：<input type='password' name='password' style='width:200px; '> <br/><br/>");
		out.println("<input type='submit' value='  登录  '>");
		out.println("</form>");

		if (true) {
			out.println("<br/><br/><br/><br/><br/><br/><br/>用户名、密码为：<br/>");
			Enumeration params = this.getInitParameterNames();
			while (params.hasMoreElements()) {
				String username = (String) params.nextElement();
				String password = this.getInitParameter(username);
				out.println("[" + username + ", " + password + "], ");
				logger.info("用户名={}、密码={}", username, password);
			}
		}

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
