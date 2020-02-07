package io.github.dunwu.javaee.filter.wrapper;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class WaterMarkResponseWrapper extends HttpServletResponseWrapper {

	private String originFile;

	// 水印图片位置
	private String waterMarkFile;

	// 原response
	private HttpServletResponse response;

	// 自定义servletOutputStream，用于缓冲图像数据
	private WaterMarkOutputStream waterMarkOutputStream;

	public WaterMarkResponseWrapper(HttpServletResponse response, String originFile, String waterMarkFile)
		throws IOException {
		super(response);
		this.response = response;
		this.originFile = originFile;
		this.waterMarkFile = waterMarkFile;
		this.waterMarkOutputStream = new WaterMarkOutputStream();
	}

	// 覆盖getOutputStream()，返回自定义的waterMarkOutputStream
	public ServletOutputStream getOutputStream() throws IOException {
		return waterMarkOutputStream;
	}

	public void flushBuffer() throws IOException {
		waterMarkOutputStream.flush();
	}

	// 将图像数据打水印，并输出到客户端浏览器
	public void finishResponse() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(waterMarkFile);
		BufferedImage wartermarkImage = ImageIO.read(fileInputStream);
		Thumbnails.Builder builder = Thumbnails.of(this.originFile);
		builder.scale(1.0, 1.0);
		builder.watermark(Positions.BOTTOM_RIGHT, wartermarkImage, 0.5f);

		// 打水印后的图片数据
		builder.toOutputStream(response.getOutputStream());
	}

}
