package io.github.dunwu.javaee.filter.wrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class GZipResponseWrapper extends HttpServletResponseWrapper {

	// 默认的 response
	private HttpServletResponse response;

	// 自定义的 outputStream, 执行close()的时候对数据压缩，并输出
	private GZipOutputStream gzipOutputStream;

	// 自定义 printWriter，将内容输出到 GZipOutputStream 中
	private PrintWriter writer;

	public GZipResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
		this.response = response;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (gzipOutputStream == null) {
			gzipOutputStream = new GZipOutputStream(response);
		}
		return gzipOutputStream;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new PrintWriter(new OutputStreamWriter(new GZipOutputStream(response), "UTF-8"));
		}
		return writer;
	}

	// 压缩后数据长度会发生变化 因此将该方法内容置空
	public void setContentLength(int contentLength) {
	}

	public void flushBuffer() throws IOException {
		gzipOutputStream.flush();
	}

	public void finishResponse() throws IOException {
		if (gzipOutputStream != null) {
			gzipOutputStream.close();
		}
		if (writer != null) {
			writer.close();
		}
	}

}
