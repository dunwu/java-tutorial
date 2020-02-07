package io.github.dunwu.javaee.filter.wrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class HttpCharacterResponseWrapper extends HttpServletResponseWrapper {

	private CharArrayWriter charArrayWriter = new CharArrayWriter();

	public HttpCharacterResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(charArrayWriter);
	}

	public CharArrayWriter getCharArrayWriter() {
		return charArrayWriter;
	}

}
