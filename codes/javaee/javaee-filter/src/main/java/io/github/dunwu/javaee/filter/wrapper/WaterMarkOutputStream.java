package io.github.dunwu.javaee.filter.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class WaterMarkOutputStream extends ServletOutputStream {

	// 缓冲图片数据
	private ByteArrayOutputStream byteArrayOutputStream;

	public WaterMarkOutputStream() throws IOException {
		byteArrayOutputStream = new ByteArrayOutputStream();
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {

	}

	public void write(int b) throws IOException {
		byteArrayOutputStream.write(b);
	}

	public void close() throws IOException {
		byteArrayOutputStream.close();
	}

	public void flush() throws IOException {
		byteArrayOutputStream.flush();
	}

	public void write(byte[] b, int off, int len) throws IOException {
		byteArrayOutputStream.write(b, off, len);
	}

	public void write(byte[] b) throws IOException {
		byteArrayOutputStream.write(b);
	}

	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
	}

}
