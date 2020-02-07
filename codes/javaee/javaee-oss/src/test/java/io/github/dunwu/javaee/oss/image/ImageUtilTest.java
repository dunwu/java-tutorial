package io.github.dunwu.javaee.oss.image;

import io.github.dunwu.javaee.oss.image.dto.ImageParamDTO;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.junit.Assert;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author Victor Zhang
 * @since 2017/1/16.
 */
public class ImageUtilTest {

    @Test
    public void testToFile() throws IOException {
        final String oldFile = System.getProperty("user.dir") + "/src/test/resources/images/lion2.jpg";
        final String newFile = System.getProperty("user.dir") + "/src/test/resources/images/lion2_watermark";
        final String warterFile = System.getProperty("user.dir") + "/src/test/resources/images/wartermark.png";

        ImageParamDTO params = new ImageParamDTO();
        params.setFormat("png");
        params.setWaterMark(new ImageParamDTO.WaterMark(9, warterFile, 0.6f));
        ImageUtil.toFile(oldFile, newFile, params);
    }

    @Test
    public void testToBufferedImage() throws IOException {
        final String oldFile = System.getProperty("user.dir") + "/src/test/resources/images/lion2.jpg";

        ImageParamDTO params = new ImageParamDTO();
        params.setWidth(64);
        params.setHeight(64);

        BufferedImage bufferedImage = ImageUtil.toBufferedImage(oldFile, params);
        Assert.assertNotNull(bufferedImage);
    }

    @Test
    public void testGetContentType()
        throws MagicParseException, MagicException, MagicMatchNotFoundException, IOException {
        final String oldFile = System.getProperty("user.dir") + "/src/test/resources/images/lion2.jpg";
        byte[] bytes = toBytes(new File(oldFile));
        String type = ImageUtil.getContentType(bytes);
        Assert.assertEquals("image/jpeg", type);
    }

    private static byte[] toBytes(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = input.read(buffer)) != -1) {
            output.write(buffer, 0, length);
        }
        byte[] data = output.toByteArray();
        output.close();
        input.close();
        return data;
    }

}
