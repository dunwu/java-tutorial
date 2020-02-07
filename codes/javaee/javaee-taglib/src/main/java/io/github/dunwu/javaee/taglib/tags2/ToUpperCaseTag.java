package io.github.dunwu.javaee.taglib.tags2;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ToUpperCaseTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {

		// 将 标签体内容读入该 writer
		StringWriter writer = new StringWriter();

		// 标签体 为 JspFragment 的形式
		JspFragment jspFragment = this.getJspBody();

		// 通过 invoke 输出到指定的 writer 中。
		// 如果参数为 null，将输出到默认的 writer 中，即 getJspContext().getOut() 输出到HTML中
		jspFragment.invoke(writer);

		String content = writer.getBuffer().toString();
		this.getJspContext().getOut().print(content.toUpperCase());
	}

}
