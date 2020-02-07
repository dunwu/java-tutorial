package io.github.dunwu.javaee.oss.encode.digest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * 消息认证码算法(Message Autherntication Code, MAC)是基于密码的消息摘要算法。 它兼容了MD/SHA的特性，并以此为基础加入了密钥。
 *
 * @author Zhang Peng
 * @since 2016年7月21日
 */
public class HmacCoder {

	public static void main(String[] args) throws Exception {
		String msg = "Hello World!";
		byte[] secretKey = "Secret_Key".getBytes("UTF8");
		byte[] digest = HmacCoder.encode(msg.getBytes(), secretKey, HmacTypeEn.HmacSHA256);
		System.out.println("原文: " + msg);
		System.out.println("摘要: " + Base64.encodeBase64URLSafeString(digest));
	}

	public static byte[] encode(byte[] plaintext, byte[] secretKey, HmacTypeEn type) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(secretKey, type.name());
		Mac mac = Mac.getInstance(keySpec.getAlgorithm());
		mac.init(keySpec);
		return mac.doFinal(plaintext);
	}

	/**
	 * JDK支持HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512
	 */
	public enum HmacTypeEn {

		HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512;
	}

}
