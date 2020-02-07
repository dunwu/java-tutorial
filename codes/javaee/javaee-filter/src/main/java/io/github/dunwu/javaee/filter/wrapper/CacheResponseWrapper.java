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
public class CacheResponseWrapper extends HttpServletResponseWrapper {

	// 缓存字符类输出
	private CharArrayWriter cacheWriter = new CharArrayWriter();

	public CacheResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(cacheWriter);
	}

	@Override
	public void flushBuffer() throws IOException {
		cacheWriter.flush();
	}

	public void finishResponse() throws IOException {
		cacheWriter.close();
	}

	public CharArrayWriter getCacheWriter() {
		return cacheWriter;
	}

	public void setCacheWriter(CharArrayWriter cacheWriter) {
		this.cacheWriter = cacheWriter;
	}

}
