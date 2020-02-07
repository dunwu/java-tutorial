package io.github.dunwu.javaee.servlet;

import io.github.dunwu.javaee.websocket.WebSocketServer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SocketServlet信息示例
 */
public class SocketServlet extends HttpServlet {

	private static final long serialVersionUID = -7936817351382556277L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Constructor of the object.
	 */
	public SocketServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置 request，response 编码方式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 取浏览器提交的 name 参数
		String id = request.getParameter("id");
		String text = request.getParameter("text");

		if (id != null && id.length() > 0) {
			WebSocketServer.send(id, text);
		} else {
			WebSocketServer.sendAll(text);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
