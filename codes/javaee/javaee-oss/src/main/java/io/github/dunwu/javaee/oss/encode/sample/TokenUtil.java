package io.github.dunwu.javaee.oss.encode.sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.dunwu.javaee.oss.encode.digest.HmacCoder;
import org.apache.commons.codec.binary.Base64;

/**
 * 令牌工具类 token = accessKey + digest + policy digest = HmacSHA256(policy, secretKey)
 */
public final class TokenUtil {

	private static final String SEPARATOR = "|||";

	private static final String ACCESS_KEY = "ACCESS_KEY";

	private static final String SECRET_KEY = "SECRET_KEY";

	public static Object testGetPolicy(String token, String tokenType) throws Exception {
		String[] params = token.split(SEPARATOR);
		// byte[] accessKey = Base64.decodeBase64(params[0]);
		String digestBase64 = params[1];
		String policyBase64 = params[2];

		if (TokenTypeEN.UPLOAD.name().equals(tokenType)) {
			UploadPolicy policy = (UploadPolicy) getPolicy(policyBase64, digestBase64, SECRET_KEY.getBytes(),
				tokenType);
			return policy;
		} else if (TokenTypeEN.DOWNLOAD.name().equals(tokenType)) {
			DownloadPolicy policy = (DownloadPolicy) getPolicy(policyBase64, digestBase64, SECRET_KEY.getBytes(),
				tokenType);
			return policy;
		} else if (TokenTypeEN.MODIFY.name().equals(tokenType)) {
			ModifyPolicy policy = (ModifyPolicy) getPolicy(policyBase64, digestBase64, SECRET_KEY.getBytes(),
				tokenType);
			return policy;
		} else {
			return null;
		}
	}

	public static Object getPolicy(String policyBase64, String digestBase64, byte[] secretKey, String tokenType)
		throws Exception {
		String policy = new String(Base64.decodeBase64(policyBase64));

		// 根据secretKey和policy验证摘要是否被篡改
		byte[] checkDigest = HmacCoder.encode(policy.getBytes(), secretKey, HmacCoder.HmacTypeEn.HmacSHA512);
		String checkDigestBase64 = Base64.encodeBase64URLSafeString(checkDigest);
		if (!checkDigestBase64.equals(digestBase64)) {
			throw new SecurityException("The policy is not match to the digest.");
		}

		JSONObject json = JSONObject.parseObject(policy);
		if (TokenTypeEN.UPLOAD.name().equalsIgnoreCase(tokenType)) {
			return JSONObject.toJavaObject(json, UploadPolicy.class);
		} else if (TokenTypeEN.DOWNLOAD.name().equalsIgnoreCase(tokenType)) {
			return JSONObject.toJavaObject(json, DownloadPolicy.class);
		} else if (TokenTypeEN.MODIFY.name().equalsIgnoreCase(tokenType)) {
			return JSONObject.toJavaObject(json, ModifyPolicy.class);
		}

		return null;
	}

	public static void main(String[] args) throws Exception {
		testGetToken(TokenTypeEN.DOWNLOAD.name());
	}

	public static String testGetToken(String tokenType) throws Exception {
		String policy = null;
		if (TokenTypeEN.UPLOAD.name().equals(tokenType)) {
			policy = initUploadPolicy();
		} else if (TokenTypeEN.DOWNLOAD.name().equals(tokenType)) {
			policy = initDownladPolicy();
		} else if (TokenTypeEN.MODIFY.name().equals(tokenType)) {
			policy = initModifyPolicy();
		}
		String policyBase64 = Base64.encodeBase64URLSafeString(policy.getBytes());
		String accessKeyBase64 = Base64.encodeBase64URLSafeString(ACCESS_KEY.getBytes());

		System.out.println(String.format("============== %s ==============", tokenType));
		System.out.println("policy:" + policy);
		System.out.println("policyBase64:" + policyBase64);
		System.out.println("accessKeyBase64:" + accessKeyBase64);

		String token = getToken(policy.getBytes(), ACCESS_KEY.getBytes(), SECRET_KEY.getBytes(), tokenType);
		System.out.println("Token:" + token);
		return token;
	}

	private static String initUploadPolicy() {
		long deadline = System.currentTimeMillis() / 1000 + 3600 * 7;
		UploadPolicy policy = new UploadPolicy();
		policy.setNamespace("namespace");
		policy.setDeadline(deadline);
		policy.setfType("pdf");
		return JSON.toJSONString(policy);
	}

	private static String initDownladPolicy() {
		long deadline = System.currentTimeMillis() / 1000 + 3600 * 7;
		DownloadPolicy policy = new DownloadPolicy();
		policy.setFileId(5748527L);
		policy.setNamespace("namespace");
		policy.setDeadline(deadline);
		policy.setfType("pdf");
		return JSON.toJSONString(policy);
	}

	private static String initModifyPolicy() {
		long deadline = System.currentTimeMillis() / 1000 + 3600 * 7;
		ModifyPolicy policy = new ModifyPolicy();
		policy.setNamespace("namespace");
		policy.setDeadline(deadline);
		policy.setfType("png");
		return JSON.toJSONString(policy);
	}

	public static String getToken(byte[] policy, byte[] accessKey, byte[] secretKey, String tokenType)
		throws Exception {
		JSONObject policyJson = JSONObject.parseObject(new String(policy));

		// 检查令牌是否符合系统规格
		if (TokenTypeEN.UPLOAD.name().equalsIgnoreCase(tokenType)) {
			UploadPolicy uploadPolicy = JSONObject.toJavaObject(policyJson, UploadPolicy.class);
			if (!UploadPolicy.isValid(uploadPolicy)) {
				throw new Exception("The policy is not conform to the specifications of the system.");
			}
		} else if (TokenTypeEN.DOWNLOAD.name().equalsIgnoreCase(tokenType)) {
			DownloadPolicy downloadPolicy = JSONObject.toJavaObject(policyJson, DownloadPolicy.class);
			if (!DownloadPolicy.isValid(downloadPolicy)) {
				throw new Exception("The policy is not conform to the specifications of the system.");
			}
		} else if (TokenTypeEN.MODIFY.name().equalsIgnoreCase(tokenType)) {
			ModifyPolicy modifyPolicy = JSONObject.toJavaObject(policyJson, ModifyPolicy.class);
			if (!ModifyPolicy.isValid(modifyPolicy)) {
				throw new Exception("The policy is not conform to the specifications of the system.");
			}
		} else {
			throw new Exception("Required token is not supported.");
		}

		// 根据secretKey和policy生成消息摘要(使用基于口令编码的HmacSHA256算法)
		byte[] digest = HmacCoder.encode(policy, secretKey, HmacCoder.HmacTypeEn.HmacSHA512);

		// Token = AccessKey::Digest::Policy。数据拼接之前都要做URL安全的Base64编码
		String token = Base64.encodeBase64URLSafeString(accessKey) + SEPARATOR
			+ Base64.encodeBase64URLSafeString(digest) + SEPARATOR + Base64.encodeBase64URLSafeString(policy);

		return token;
	}

	public enum TokenTypeEN {

		UPLOAD, DOWNLOAD, MODIFY
	}

}
