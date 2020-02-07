package io.github.dunwu.javaee.taglib.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoopTag extends BodyTagSupport {

	private static final long serialVersionUID = 5882067091737658241L;

	private int times;

	@Override
	public int doStartTag() throws JspException {
		times = 5;
		return super.doStartTag();
	}

	@Override
	public int doAfterBody() throws JspException {

		if (times-- > 0) {

			/** 只要 times > 0 就继续循环，同时 times 自减 */
			try {
				this.getPreviousOut().println(this.getBodyContent().getString());
			} catch (Exception e) {
			}

			return EVAL_BODY_AGAIN;
		} else {

			/** 结束运行，同时复原 times */

			times = 5;

			return SKIP_BODY;
		}
	}

}
