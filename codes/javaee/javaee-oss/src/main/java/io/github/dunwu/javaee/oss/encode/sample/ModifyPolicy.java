package io.github.dunwu.javaee.oss.encode.sample;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Zhang Peng on 2016/7/22.
 */
public class ModifyPolicy implements Serializable {

	private static final long serialVersionUID = -547821705196510884L;

	/**
	 * 操作类型
	 */
	private final String operate = UploadConstant.TOKEN_MODIFY;

	/**
	 * 下载文件所属空间
	 */
	private String namespace;

	/**
	 * 令牌有效的截止时间。用Unix时间表示。单位秒
	 */
	private Long deadline;

	/**
	 * 允许下载文件类型
	 */
	private String fType = UploadConstant.SUPPORT_FILE_TYPE;

	/**
	 * 判断数据是否有效
	 *
	 * @param policy
	 * @return boolean
	 * @author Zhang Peng
	 * @since 2016年7月22日
	 */
	public static boolean isValid(ModifyPolicy policy) {
		// 检查必要项是否为空
		if (StringUtils.isBlank(policy.namespace) || null == policy.deadline) {
			return false;
		}

		// 令牌截止时间不能是已过期时间
		long life = policy.deadline - System.currentTimeMillis() / 1000;
		if (life <= 0) {
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

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public String getOperate() {
		return operate;
	}

}
