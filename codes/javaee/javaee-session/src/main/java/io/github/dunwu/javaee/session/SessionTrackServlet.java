/**
 * The Apache License 2.0 Copyright (c) 2017 Zhang Peng
 */
package io.github.dunwu.javaee.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/26.
 */
@WebServlet("/servlet/SessionTrackServlet")
public class SessionTrackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 如果不存在 session 会话，则创建一个 session 对象
		HttpSession session = request.getSession(true);
		// 获取 session 创建时间
		Date createTime = new Date(session.getCreationTime());
		// 获取该网页的最后一次访问时间
		Date lastAccessTime = new Date(session.getLastAccessedTime());

		// 设置日期输出的格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String title = "Servlet Session 实例";
		Integer visitCount = new Integer(0);
		String visitCountKey = new String("visitCount");
		String userIDKey = new String("userID");
		String userID = new String("admin");

		// 检查网页上是否有新的访问者
		if (session.isNew()) {
			session.setAttribute(userIDKey, userID);
		} else {
			visitCount = (Integer) session.getAttribute(visitCountKey);
			visitCount = visitCount + 1;
			userID = (String) session.getAttribute(userIDKey);
		}
		session.setAttribute(visitCountKey, visitCount);

		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String docType = "<!DOCTYPE html>\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
			+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
			+ "<h2 align=\"center\">Session 信息</h2>\n" + "<table border=\"1\" align=\"center\">\n"
			+ "<tr bgcolor=\"#949494\">\n" + "  <th>Session 信息</th><th>值</th></tr>\n" + "<tr>\n" + "  <td>id</td>\n"
			+ "  <td>" + session.getId() + "</td></tr>\n" + "<tr>\n" + "  <td>创建时间</td>\n" + "  <td>"
			+ df.format(createTime) + "  </td></tr>\n" + "<tr>\n" + "  <td>最后访问时间</td>\n" + "  <td>"
			+ df.format(lastAccessTime) + "  </td></tr>\n" + "<tr>\n" + "  <td>用户 ID</td>\n" + "  <td>" + userID
			+ "  </td></tr>\n" + "<tr>\n" + "  <td>访问统计：</td>\n" + "  <td>" + visitCount + "</td></tr>\n"
			+ "</table>\n" + "</body></html>");
	}

}
