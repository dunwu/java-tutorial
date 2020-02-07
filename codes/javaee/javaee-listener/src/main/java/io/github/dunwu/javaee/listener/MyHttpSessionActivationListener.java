package io.github.dunwu.javaee.listener;

import java.io.Serializable;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpSessionActivationListener 接口的 JavaBean 对象可以感知自己被序列化或反序列化的事件。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyHttpSessionActivationListener implements HttpSessionActivationListener, Serializable {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String name;

	public MyHttpSessionActivationListener(String name) {
		this.name = name;
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {

		logger.debug(name + "和session一起被序列化到硬盘了，session的id是：" + se.getSession().getId());
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		logger.debug(name + "和session一起从硬盘反序列化回到内存了，session的id是：" + se.getSession().getId());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
