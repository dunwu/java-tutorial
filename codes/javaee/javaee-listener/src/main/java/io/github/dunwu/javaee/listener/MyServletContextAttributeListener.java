package io.github.dunwu.javaee.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServletContextAttributeListener 用于监听 ServletContext 中的属性变化
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		logger.debug("ServletContext域对象中添加了属性:{}，属性值是:{}", scab.getName(), scab.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		logger.debug("ServletContext域对象中删除了属性:{}，属性值是:{}", scab.getName(), scab.getValue());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		logger.debug("ServletContext域对象中替换了属性:{}，原值是:{}， 现值是:{}", scab.getName(), scab.getSource(), scab.getValue());
	}

}
