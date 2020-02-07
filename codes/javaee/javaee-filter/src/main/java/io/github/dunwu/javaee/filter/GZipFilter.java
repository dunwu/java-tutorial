package io.github.dunwu.javaee.filter;

import io.github.dunwu.javaee.filter.wrapper.GZipResponseWrapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class GZipFilter extends MyFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		logger.info("{} 开始做过滤处理", this.getClass().getName());

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String acceptEncoding = httpServletRequest.getHeader("Accept-Encoding");
		System.out.println("Accept-Encoding: " + acceptEncoding);

		if (acceptEncoding != null && acceptEncoding.toLowerCase().indexOf("gzip") != -1) {

			// 如果客户浏览器支持 GZIP 格式, 则使用 GZIP 压缩数据
			GZipResponseWrapper gzipResponse = new GZipResponseWrapper(httpServletResponse);
			chain.doFilter(httpServletRequest, gzipResponse);

			// 输出压缩数据
			gzipResponse.finishResponse();
		} else {
			// 否则, 不压缩
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

}
