/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.image.dto;

import java.io.Serializable;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author Victor Zhang
 * @since 2017/1/16.
 */
public class ImageParamDTO implements Serializable {

    public static String[] IMAGE_TYPES = { "png", "jpg", "jpeg", "bmp", "gif" };

	private Integer width; // 宽度

	private Integer height; // 高度

	private Double xscale; // 宽度比例

	private Double yscale; // 高度比例

	private Double scale; // 总比例，相当于将xscale和yscale都设为同比例

	private Double rotate; // 旋转角度，范围为[0.0, 360.0]

	private Double quality; // 压缩质量，范围为[0.0, 1.0]

	private String format; // 图片格式，支持jpg,jpeg,png,bmp,gif

	private WaterMark waterMark; // 水印信息

	/**
	 * 将位置类型码转换为 thumbnailator 可以识别的位置类型
	 *
	 * @param code
	 * @return
	 */
	public static Positions getPostionsByCode(Integer code) {
		switch (code) {
			case 1:
				return Positions.TOP_LEFT;
			case 2:
				return Positions.TOP_CENTER;
			case 3:
				return Positions.TOP_RIGHT;
			case 4:
				return Positions.CENTER_LEFT;
			case 5:
				return Positions.CENTER;
			case 6:
				return Positions.CENTER_RIGHT;
			case 7:
				return Positions.BOTTOM_LEFT;
			case 8:
				return Positions.BOTTOM_CENTER;
			case 9:
				return Positions.BOTTOM_RIGHT;
			default:
				return null;
		}
	}

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

	public Double getXscale() {
		return xscale;
	}

	public void setXscale(Double xscale) {
		this.xscale = xscale;
	}

	public Double getYscale() {
		return yscale;
	}

	public void setYscale(Double yscale) {
		this.yscale = yscale;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public Double getRotate() {
		return rotate;
	}

	public void setRotate(Double rotate) {
		this.rotate = rotate;
	}

	public Double getQuality() {
		return quality;
	}

	public void setQuality(Double quality) {
		this.quality = quality;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public WaterMark getWaterMark() {
		return waterMark;
	}

	public void setWaterMark(WaterMark waterMark) {
		this.waterMark = waterMark;
	}

	public static class WaterMark {

		private Integer position;

		private String image;

		private Float opacity;

		public WaterMark() {
		}

		public WaterMark(Integer position, String image, Float opacity) {
			this.position = position;
			this.image = image;
			this.opacity = opacity;
		}

		public Integer getPosition() {
			return position;
		}

		public void setPosition(Integer position) {
			this.position = position;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public Float getOpacity() {
			return opacity;
		}

		public void setOpacity(Float opacity) {
			this.opacity = opacity;
		}

	}

}
