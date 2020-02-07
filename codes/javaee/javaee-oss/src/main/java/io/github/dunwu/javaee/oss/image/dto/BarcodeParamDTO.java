/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.image.dto;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import java.util.Map;

/**
 * @author Victor Zhang
 * @since 2017/1/17.
 */
public class BarcodeParamDTO {

	private Integer width; // 图像宽度

	private Integer height; // 图像高度

	private String filepath; // 图片路径

	private String imageFormat; // 图片文件格式

	private BarcodeFormat barcodeFormat; // 二维码形式

	private Map<EncodeHintType, Object> encodeHints; // 二维码的编码参数

	private Map<DecodeHintType, Object> decodeHints; // 二维码的解码参数

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public BarcodeFormat getBarcodeFormat() {
		return barcodeFormat;
	}

	public void setBarcodeFormat(BarcodeFormat barcodeFormat) {
		this.barcodeFormat = barcodeFormat;
	}

	public Map<EncodeHintType, Object> getEncodeHints() {
		return encodeHints;
	}

	public void setEncodeHints(Map<EncodeHintType, Object> encodeHints) {
		this.encodeHints = encodeHints;
	}

	public Map<DecodeHintType, Object> getDecodeHints() {
		return decodeHints;
	}

	public void setDecodeHints(Map<DecodeHintType, Object> decodeHints) {
		this.decodeHints = decodeHints;
	}

}
