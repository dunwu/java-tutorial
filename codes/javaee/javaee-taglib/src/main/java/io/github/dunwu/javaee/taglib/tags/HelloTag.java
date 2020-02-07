package io.github.dunwu.javaee.taglib.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport {

	private static final long serialVersionUID = -8828591126748246256L;

	private String name;

	@Override
	public int doEndTag() throws JspException {

		try {
			this.pageContext.getOut().println("Hello, " + name);
		} catch (Exception e) {
			throw new JspException(e);
		}

		return EVAL_PAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
