package io.github.dunwu.javaee.filter;

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
public class ImageRedirectFilter extends MyFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		logger.info("{} 开始做过滤处理", this.getClass().getName());

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// 禁止缓存
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragrma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);

		// 链接来源地址
		String referer = httpServletRequest.getHeader("referer");

		if (referer == null || !referer.contains(httpServletRequest.getServerName())) {
			// 如果 链接地址来自其他网站，则返回错误图片
			httpServletRequest.getRequestDispatcher("/views/images/error.gif").forward(httpServletRequest,
				httpServletResponse);
		} else {
			// 图片正常显示
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

}
