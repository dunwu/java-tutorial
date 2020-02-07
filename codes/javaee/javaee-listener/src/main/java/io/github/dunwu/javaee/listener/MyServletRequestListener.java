package io.github.dunwu.javaee.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServletRequestListener 接口用于监听 ServletRequest 对象的创建和销毁。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyServletRequestListener implements ServletRequestListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void requestInitialized(ServletRequestEvent event) {

		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();

		HttpSession session = request.getSession(true);

		// 记录IP地址
		session.setAttribute("ip", request.getRemoteAddr());

		// 记录访问次数，只记录访问 .html, .do, .jsp, .action 的累计次数
		String uri = request.getRequestURI();
		uri = request.getQueryString() == null ? uri : (uri + "?" + request.getQueryString());
		request.setAttribute("dateCreated", System.currentTimeMillis());
        String[] suffix = { ".html", ".do", ".jsp", ".action" };
		for (int i = 0; i < suffix.length; i++) {
			if (uri.endsWith(suffix[i])) {
				break;
			}
			if (i == suffix.length - 1) {
				return;
			}
		}

		Integer activeTimes = (Integer) session.getAttribute("activeTimes");

		if (activeTimes == null) {
			activeTimes = 0;
		}

		session.setAttribute("activeTimes", activeTimes + 1);
		logger.debug("IP: {} 请求 {}", request.getRemoteAddr(), uri);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		long time = System.currentTimeMillis() - (Long) request.getAttribute("dateCreated");
		logger.debug("{} 请求处理结束, 用时 {} 毫秒", request.getRemoteAddr(), time);
	}

}
