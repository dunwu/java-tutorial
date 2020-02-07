package io.github.dunwu.javaee.taglib.tags2;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public class RepeatTag extends SimpleTagSupport {

	private int times;

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
