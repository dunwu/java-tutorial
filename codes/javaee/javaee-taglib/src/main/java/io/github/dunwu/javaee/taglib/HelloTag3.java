/**
 * The Apache License 2.0 Copyright (c) 2017 Zhang Peng
 */
package io.github.dunwu.javaee.taglib;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/4/3.
 */
public class HelloTag3 extends SimpleTagSupport {

	StringWriter sw = new StringWriter();

	private String message;

	public void setMessage(String msg) {
		this.message = msg;
	}

	public void doTag() throws JspException, IOException {
		if (message != null) {
			/* 从属性中使用消息 */
			JspWriter out = getJspContext().getOut();
			out.println(message);
		} else {
			/* 从内容体中使用消息 */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}

}
