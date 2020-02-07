package io.github.dunwu.javaee.oss.image;

import io.github.dunwu.javaee.oss.image.dto.ImageParamDTO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.sf.jmimemagic.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * 图片工具类
 *
 * @author Victor Zhang
 * @since 2017/1/16.
 */
public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static void toFile(String oldFile, String newFile, ImageParamDTO params) throws IOException {
        if (StringUtils.isBlank(oldFile) || StringUtils.isBlank(newFile)) {
            logger.error("原文件名或目标文件名为空");
            return;
        }
        Thumbnails.Builder builder = Thumbnails.of(oldFile);
        fillBuilderWithParams(builder, params);
        if (null == builder) {
            return;
        }
        builder.toFile(newFile);
    }

    private static void fillBuilderWithParams(Thumbnails.Builder builder, ImageParamDTO params) throws IOException {
        if (null == params) {
            throw new IOException("图片格式化参数为空");
        }

        // 按照一定规则改变原图尺寸
        if (null != params.getWidth() && null != params.getHeight()) {
            builder.size(params.getWidth(), params.getHeight());
        } else if (null != params.getXscale() && null != params.getYscale()) {
            builder.scale(params.getXscale(), params.getYscale());
        } else if (null != params.getScale()) {
            builder.scale(params.getScale(), params.getScale());
        } else {
            builder.scale(1.0); // 如果没有设置尺寸参数，默认大小为原图大小
        }

        // 设置图片旋转角度
        if (null != params.getRotate()) {
            builder.rotate(params.getRotate());
        }

        // 设置图片压缩质量
        if (null != params.getQuality()) {
            builder.outputQuality(params.getQuality());
        }

        // 设置图片格式
        if (StringUtils.isNotBlank(params.getFormat())) {
            builder.outputFormat(params.getFormat());
        }

        // 设置水印
        ImageParamDTO.WaterMark waterMark = params.getWaterMark();
        if (null != waterMark) {
            Positions pos = ImageParamDTO.getPostionsByCode(waterMark.getPosition());
            if (null == pos) {
                throw new IOException("请检查水印图片的位置类型，有效范围在[1,9]");
            }
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(waterMark.getImage()));
            builder.watermark(pos, bufferedImage, waterMark.getOpacity());
        }
    }

    public static BufferedImage toBufferedImage(String oldFile, ImageParamDTO params) throws IOException {
        if (StringUtils.isBlank(oldFile)) {
            logger.error("原文件名或目标文件名为空");
            return null;
        }
        Thumbnails.Builder builder = Thumbnails.of(oldFile);
        fillBuilderWithParams(builder, params);
        if (null == builder) {
            return null;
        }
        return builder.asBufferedImage();
    }

    public static OutputStream toOutputStream(InputStream input, OutputStream output, ImageParamDTO params)
        throws IOException {
        Thumbnails.Builder builder = Thumbnails.of(input);
        if (null == builder) {
            return null;
        }

        try {
            fillBuilderWithParams(builder, params);
            builder.toOutputStream(output);
        } catch (IOException e) {
            logger.error("图片处理失败\n" + e.getMessage());
            throw e;
        }

        return output;
    }

    /**
     * 获取文件的 ContentType
     *
     * @param content
     * @return
     * @throws MagicParseException
     * @throws MagicException
     * @throws MagicMatchNotFoundException
     */
    public static String getContentType(byte[] content)
        throws MagicParseException, MagicException, MagicMatchNotFoundException {
        MagicMatch match = Magic.getMagicMatch(content);
        return match.getMimeType();
    }

    public static final InputStream bytes2InputStream(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static final byte[] inputStream2bytes(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}
