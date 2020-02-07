package io.github.dunwu.javaee.oss.encode.digest;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

/**
 * 消息摘要算法(Message Digest, MD)是消息摘要算法。
 *
 * @author Zhang Peng
 * @since 2016年7月21日
 */
public class MdCoder {

	public static void main(String[] args) throws Exception {
		String msg = "Hello World!";
		byte[] encodeWithBase64 = MdCoder.encodeWithBase64(msg.getBytes(), MdTypeEn.MD5);

		String result = String.format("%s摘要:%s", MdTypeEn.MD5.name(), new String(encodeWithBase64));
		System.out.println("原文: " + msg);
		System.out.println(result);
	}

	public static byte[] encodeWithBase64(byte[] input, MdTypeEn type) throws Exception {
		return Base64.encodeBase64URLSafe(encode(input, type));
	}

	public static byte[] encode(byte[] input, MdTypeEn type) throws Exception {
		// 根据类型，初始化消息摘要对象
		MessageDigest md5Digest = MessageDigest.getInstance(type.name());

		// 更新要计算的内容
		md5Digest.update(input);

		// 完成哈希计算，返回摘要
		return md5Digest.digest();
	}

	/**
	 * JDK支持MD2和MD5两种MD算法
	 */
	public enum MdTypeEn {

		MD2, MD5
	}

}
