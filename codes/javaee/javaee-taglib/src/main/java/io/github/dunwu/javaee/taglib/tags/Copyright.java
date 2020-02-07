package io.github.dunwu.javaee.taglib.tags;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class Copyright implements Tag {

	private Tag parent;

	private PageContext pageContext;

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();

		try {
			out.println("<div align=center style='line-height: 22px; font-size: 12px; '>");
			out.println(ResourceBundle.getBundle("copyright").getString("copyright"));
			out.println("</div>");
		} catch (IOException e) {
			throw new JspException(e);
		}

		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public Tag getParent() {
		return this.parent;
	}

	@Override
	public void setParent(Tag parent) {
		this.parent = parent;
	}

	@Override
	public void release() {
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

}

// end
