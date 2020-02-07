/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import io.github.dunwu.javaee.oss.image.dto.BarcodeParamDTO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 测试qrcode工具类
 *
 * @author Victor Zhang
 * @since 2017/1/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QRCodeUtilTest {

	private static final String qrcodeFile = "d:\\qrcode.png";

	private String jsonContent = null;

	private BarcodeParamDTO paramDTO = null;

	@Before
	public void before() throws JsonProcessingException {
		jsonContent = initTestJson();
		paramDTO = initBarcodeParam();
	}

	private String initTestJson() throws JsonProcessingException {
		Map<String, Object> userData = new HashMap<String, Object>();
		Map<String, String> fullname = new HashMap<String, String>();
		fullname.put("first", "Peng");
		fullname.put("last", "Zhang");
		userData.put("name", fullname);
		userData.put("gender", "MALE");
		userData.put("email", "aaa@163.com");

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		return mapper.writeValueAsString(userData);
	}

	private BarcodeParamDTO initBarcodeParam() {
		BarcodeParamDTO paramDTO = new BarcodeParamDTO();
		paramDTO.setWidth(200);
		paramDTO.setHeight(200);
		paramDTO.setFilepath(qrcodeFile);
		paramDTO.setImageFormat("png");
		paramDTO.setBarcodeFormat(BarcodeFormat.QR_CODE);

		// 编码参数
		Map<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>();
		encodeHints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		paramDTO.setEncodeHints(encodeHints);

		// 解码参数
		HashMap<DecodeHintType, Object> decodeHints = new HashMap<DecodeHintType, Object>();
		decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		paramDTO.setDecodeHints(decodeHints);

		return paramDTO;
	}

	/**
	 * 测试创建qrcode图片
	 */
	@Test
	public void test01() throws IOException, WriterException {
		QRCodeUtil.encode(jsonContent, paramDTO);
		File f = new File(qrcodeFile);
		Assert.assertTrue(f.exists());
	}

	/**
	 * 测试解析qrcode图片
	 */
	@Test
	public void test02() {
		String expect =
			"{\"gender\":\"MALE\",\"name\":{\"last\":\"Zhang\",\"first\":\"Peng\"},\"email\":\"aaa@163.com\"}";
		String content = QRCodeUtil.decode(paramDTO);
		Assert.assertEquals(expect, content);
	}

}
