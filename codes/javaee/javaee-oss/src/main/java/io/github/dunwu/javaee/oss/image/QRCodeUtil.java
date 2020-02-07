package io.github.dunwu.javaee.oss.image;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import io.github.dunwu.javaee.oss.image.dto.BarcodeParamDTO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 * 二维码工具类
 *
 * @author Victor Zhang
 * @since 2017/1/16.
 */
public class QRCodeUtil {

	/**
	 * 创建一个qrcode图片
	 *
	 * @param content  加密信息，建议使用json格式
	 * @param paramDTO qrcode 参数
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encode(String content, BarcodeParamDTO paramDTO) throws WriterException, IOException {
		// 生成矩阵
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, paramDTO.getBarcodeFormat(), paramDTO.getWidth(),
			paramDTO.getHeight(), paramDTO.getEncodeHints());
		Path path = FileSystems.getDefault().getPath(paramDTO.getFilepath());
		MatrixToImageWriter.writeToPath(bitMatrix, paramDTO.getImageFormat(), path);// 输出图像
	}

	/**
	 * 解析 qrcode 图片
	 *
	 * @param paramDTO qrcode 参数
	 * @return
	 */
	public static String decode(BarcodeParamDTO paramDTO) {
		try {
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(paramDTO.getFilepath()));
			LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap bitmap = new BinaryBitmap(binarizer);
			Result result = new MultiFormatReader().decode(bitmap, paramDTO.getDecodeHints());
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
