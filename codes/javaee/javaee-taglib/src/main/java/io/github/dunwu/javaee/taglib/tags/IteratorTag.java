package io.github.dunwu.javaee.taglib.tags;

import java.util.Collection;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IteratorTag extends TagSupport {

	private static final long serialVersionUID = -8828591126748246256L;

	private Collection<Object> connection;

	@Override
	public int doEndTag() throws JspException {

		try {
			for (Object obj : connection) {
				this.pageContext.getOut().println(obj + ", <br/>");
			}
		} catch (Exception e) {
			throw new JspException(e);
		}

		return EVAL_PAGE;
	}

	public void setConnection(Collection<Object> connection) {
		this.connection = connection;
	}

}
