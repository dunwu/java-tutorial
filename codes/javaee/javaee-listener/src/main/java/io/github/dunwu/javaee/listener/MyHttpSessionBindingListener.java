package io.github.dunwu.javaee.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpSessionBindingListener 接口的 JavaBean 对象可以感知自己被绑定或解绑定到 Session 中的事件。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		logger.debug("HttpSessionBinding valueBound");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		logger.debug("HttpSessionBinding valueUnbound");
	}

}
