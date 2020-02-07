package io.github.dunwu.javaee.taglib.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ToLowerCaseTag extends BodyTagSupport {

	private static final long serialVersionUID = -2529343271020971948L;

	@Override
	public int doEndTag() throws JspException {

		String content = this.getBodyContent().getString();

		try {
			this.pageContext.getOut().print(content.toLowerCase());
		} catch (Exception e) {
		}

		return EVAL_PAGE;
	}

}
