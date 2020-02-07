package io.github.dunwu.javaee.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServletContextAttributeListener 用于监听 ServletRequest 中的属性变化
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017-04-04
 */
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		logger.debug("ServletRequest域对象中添加了属性:{}，属性值是:{}", srae.getName(), srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		logger.debug("ServletRequest域对象中删除了属性:{}，属性值是:{}", srae.getName(), srae.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		logger.debug("ServletRequest域对象中替换了属性:{}，原值是:{}， 现值是:{}", srae.getName(), srae.getSource(), srae.getValue());
	}

}
