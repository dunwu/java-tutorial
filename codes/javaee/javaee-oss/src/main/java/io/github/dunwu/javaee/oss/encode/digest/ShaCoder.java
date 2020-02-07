package io.github.dunwu.javaee.oss.encode.digest;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

/**
 * 安全散列算法(Secure Hash Algorithm, SHA)是消息摘要算法
 *
 * @author Zhang Peng
 * @since 2016年7月21日
 */
public class ShaCoder {

	public static void main(String[] args) throws Exception {
		String msg = "Hello World!";
		byte[] encodeWithBase64 = ShaCoder.encodeWithBase64(msg.getBytes(), ShaTypeEn.SHA384);

		String result = String.format("%s摘要:%s", ShaTypeEn.SHA384.getName(), new String(encodeWithBase64));
		System.out.println("原文: " + msg);
		System.out.println(result);
	}

	public static byte[] encodeWithBase64(byte[] input, ShaTypeEn type) throws Exception {
		return Base64.encodeBase64URLSafe(encode(input, type));
	}

	public static byte[] encode(byte[] input, ShaTypeEn type) throws Exception {
		// 根据类型，初始化消息摘要对象
		MessageDigest md5Digest = MessageDigest.getInstance(type.getName());

		// 更新要计算的内容
		md5Digest.update(input);

		// 完成哈希计算，返回摘要
		return md5Digest.digest();
	}

	/**
	 * JDK支持SHA1、SHA256、SHA384和SHA512几种SHA算法
	 */
	public enum ShaTypeEn {

		SHA1("SHA1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");

		private String name;

		ShaTypeEn(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}
