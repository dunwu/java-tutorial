package io.github.dunwu.javaee.listener;

import io.github.dunwu.javaee.listener.util.ApplicationConstants;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServletContextListener 接口用于监听 ServletContext 对象的创建和销毁事件。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyServletContextListener implements ServletContextListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 启动时，记录服务器启动时间
		ApplicationConstants.START_DATE = new Date();
		ServletContext servletContext = event.getServletContext();
		logger.info("即将启动 {}", servletContext.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 关闭时，将结果清除。也可以将结果保存到硬盘上。
		ApplicationConstants.START_DATE = null;
		ApplicationConstants.MAX_ONLINE_COUNT_DATE = null;
		ServletContext servletContext = event.getServletContext();
		logger.info("即将关闭 {}", servletContext.getContextPath());
	}

}
