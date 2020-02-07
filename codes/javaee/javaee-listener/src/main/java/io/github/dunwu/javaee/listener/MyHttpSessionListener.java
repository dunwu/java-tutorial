package io.github.dunwu.javaee.listener;

import io.github.dunwu.javaee.listener.util.ApplicationConstants;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpSessionListener 接口用于监听 HttpSession 对象的创建和销毁。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyHttpSessionListener implements HttpSessionListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();

		// 将 session 放入 map
		ApplicationConstants.SESSION_MAP.put(session.getId(), session);
		// 总访问人数++
		ApplicationConstants.TOTAL_HISTORY_COUNT++;

		// 如果当前在线人数超过历史记录，则更新最大在线人数，并记录时间
		if (ApplicationConstants.SESSION_MAP.size() > ApplicationConstants.MAX_ONLINE_COUNT) {
			ApplicationConstants.MAX_ONLINE_COUNT = ApplicationConstants.SESSION_MAP.size();
			ApplicationConstants.MAX_ONLINE_COUNT_DATE = new Date();
		}

		logger.debug("创建了一个session: {}", session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		// 将session从map中移除
		ApplicationConstants.SESSION_MAP.remove(session.getId());

		logger.debug("销毁了一个session: {}", session);
	}

}
