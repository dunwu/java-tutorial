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
public class LogFilter extends MyFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		long startTime = System.currentTimeMillis();
		String requestURI = request.getRequestURI();

		requestURI = request.getQueryString() == null ? requestURI : (requestURI + "?" + request.getQueryString());

		chain.doFilter(request, response);

		long endTime = System.currentTimeMillis();

		logger.info("{} 访问了 {}，总用时 {} 毫秒", request.getRemoteAddr(), requestURI, (endTime - startTime));
	}

}
