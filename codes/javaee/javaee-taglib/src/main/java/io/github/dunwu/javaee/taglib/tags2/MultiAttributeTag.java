package io.github.dunwu.javaee.taglib.tags2;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MultiAttributeTag extends SimpleTagSupport {

	private JspFragment body1;

	private JspFragment body2;

	public void setBody1(JspFragment body1) {
		this.body1 = body1;
	}

	public void setBody2(JspFragment body2) {
		this.body2 = body2;
	}

	@Override
	public void doTag() throws JspException, IOException {

		StringWriter writer1 = new StringWriter();
		StringWriter writer2 = new StringWriter();

		for (int i = 0; i < 5; i++) {
			// body1 调用 5 次
			body1.invoke(writer1);
		}

		for (int i = 0; i < 3; i++) {
			// body2 调用 3 次
			body2.invoke(writer2);
		}

		this.getJspContext().getOut().print("3 次调用 body2 后的结果：" + writer2.getBuffer().toString() + "<br/><br/>");

		this.getJspContext().getOut().print("5 次调用 body1 后的结果：" + writer1.getBuffer().toString() + "<br/><br/>");
	}

}
