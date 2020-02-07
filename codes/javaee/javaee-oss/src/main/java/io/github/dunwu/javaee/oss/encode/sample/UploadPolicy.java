package io.github.dunwu.javaee.oss.encode.sample;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Zhang Peng on 2016/7/22.
 */
public class UploadPolicy implements Serializable {

	private static final long serialVersionUID = 8289239747395166646L;

	/**
	 * 操作类型
	 */
	private final String operate = UploadConstant.TOKEN_UPLOAD;

	/**
	 * 上传文件所属空间
	 */
	private String namespace;

	/**
	 * 令牌有效的截止时间。用Unix时间表示。单位秒
	 */
	private Long deadline;

	/**
	 * 文件上传后保留时间。单位天。默认值-1，表示永久保留
	 */
	private Integer deleteAfterDays = -1;

	/**
	 * 上传文件大小上限。单位Byte。
	 */
	private Long fsizeMin = UploadConstant.FSIZE_MIN_DEFAULT;

	/**
	 * 上传文件大小上限。单位Byte。
	 */
	private Long fsizeMax = UploadConstant.FSIZE_MAX_DEFAULT;

	/**
	 * 允许上传文件类型
	 */
	private String fType = UploadConstant.SUPPORT_FILE_TYPE;

	/**
	 * 是否认证令牌。0表示认证，1表示不认证。默认为0
	 */
	private Integer verifyToken = 0;

	/**
	 * 判断数据是否有效
	 *
	 * @param policy
	 * @return boolean
	 * @author Zhang Peng
	 * @since 2016年7月22日
	 */
	public static boolean isValid(UploadPolicy policy) {
		// 检查必要项是否为空
		if (StringUtils.isBlank(policy.namespace) || null == policy.deadline) {
			return false;
		}

		// 令牌截止时间不能是已过期时间
		long life = policy.deadline - System.currentTimeMillis() / 1000;
		if (life <= 0) {
			return false;
		}

		// 判断文件大小的上限、下限是否符合系统规格
		if (policy.fsizeMin > policy.fsizeMax || policy.fsizeMin < UploadConstant.FSIZE_MIN
			|| policy.fsizeMax > UploadConstant.FSIZE_MAX) {
			return false;
		}

		// 检查文件类型
		if (StringUtils.isBlank(policy.fType)) {
			return false;
		}
		String[] requestTypes = policy.fType.split("\\|");
		for (String item : requestTypes) {
			if (!UploadConstant.SUPPORT_FILE_TYPE_SET.contains(item)) {
				return false;
			}
		}

		return true;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Long getDeadline() {
		return deadline;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}

	public Integer getDeleteAfterDays() {
		return deleteAfterDays;
	}

	public void setDeleteAfterDays(Integer deleteAfterDays) {
		this.deleteAfterDays = deleteAfterDays;
	}

	public Long getFsizeMin() {
		return fsizeMin;
	}

	public void setFsizeMin(Long fsizeMin) {
		this.fsizeMin = fsizeMin;
	}

	public Long getFsizeMax() {
		return fsizeMax;
	}

	public void setFsizeMax(Long fsizeMax) {
		this.fsizeMax = fsizeMax;
	}

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public Integer getVerifyToken() {
		return verifyToken;
	}

	public void setVerifyToken(Integer verifyToken) {
		this.verifyToken = verifyToken;
	}

	public String getOperate() {
		return operate;
	}

}
