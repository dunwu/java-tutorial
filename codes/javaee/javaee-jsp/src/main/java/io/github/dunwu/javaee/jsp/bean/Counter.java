package io.github.dunwu.javaee.jsp.bean;

public class Counter {

	private int count;

	public int getCount() {
		return ++count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
