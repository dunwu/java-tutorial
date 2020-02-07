package io.github.dunwu.javaee.filter.wrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class UploadRequestWrapper extends HttpServletRequestWrapper {

	private static final String MULTIPART_HEADER = "Content-type";

	// 是否是上传文件
	private boolean multipart;

	// map，保存所有的域
	private Map<String, Object> params = new HashMap<String, Object>();

	@SuppressWarnings("all")
	public UploadRequestWrapper(HttpServletRequest request) {

		super(request);

		// 判断是否为上传文件
		multipart = request.getHeader(MULTIPART_HEADER) != null
			&& request.getHeader(MULTIPART_HEADER).startsWith("multipart/form-data");

		if (multipart) {

			try {
				// 使用apache的工具解析
				DiskFileUpload upload = new DiskFileUpload();
				upload.setHeaderEncoding("utf8");

				// 解析，获得所有的文本域与文件域
				List<FileItem> fileItems = upload.parseRequest(request);

				for (Iterator<FileItem> it = fileItems.iterator(); it.hasNext(); ) {

					// 遍历
					FileItem item = it.next();
					if (item.isFormField()) {

						// 如果是文本域，直接放到map里
						params.put(item.getFieldName(), item.getString("utf8"));
					} else {

						// 否则，为文件，先获取文件名称
						String filename = item.getName().replace("\\", "/");
						filename = filename.substring(filename.lastIndexOf("/") + 1);

						// 保存到系统临时文件夹中
						File file = new File(System.getProperty("java.io.tmpdir"), filename);

						// 保存文件内容
						OutputStream ous = new FileOutputStream(file);
						ous.write(item.get());
						ous.close();

						// 放到map中
						params.put(item.getFieldName(), file);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		System.out.println(System.getProperties().toString().replace(", ", "\r\n"));
	}

	@Override
	public Object getAttribute(String name) {

		// 如果为上传文件，则从map中取值
		if (multipart && params.containsKey(name)) {
			return params.get(name);
		}
		return super.getAttribute(name);
	}

	@Override
	public String getParameter(String name) {

		// 如果为上传文件，则从map中取值
		if (multipart && params.containsKey(name)) {
			return params.get(name).toString();
		}
		return super.getParameter(name);
	}

}
