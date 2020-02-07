package io.github.dunwu.javaee.oss.encode.sample;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by Zhang Peng on 2016/7/26.
 */
public class UploadConstant {

	public static final long FSIZE_MIN = 1024L; // 1KB

	public static final long FSIZE_MAX = 5 * 1024 * 1024L; // 5MB

	public static final long FSIZE_MIN_DEFAULT = 1024L; // 1KB

	public static final long FSIZE_MAX_DEFAULT = 2 * 1024 * 1024L; // 2MB

	public static final String TOKEN_UPLOAD = "UPLOAD";

	public static final String TOKEN_DOWNLOAD = "DOWNLOAD";

	public static final String TOKEN_MODIFY = "MODIFY";

	public static final String SUPPORT_FILE_TYPE = "pdf|doc|docx|png|jpg|jpeg|gif";

	public static final Set<String> SUPPORT_FILE_TYPE_SET;

	static {
		SUPPORT_FILE_TYPE_SET = new HashSet<String>();
		String[] supportedTypes = UploadConstant.SUPPORT_FILE_TYPE.split("\\|");
		CollectionUtils.addAll(SUPPORT_FILE_TYPE_SET, supportedTypes);
	}
}
