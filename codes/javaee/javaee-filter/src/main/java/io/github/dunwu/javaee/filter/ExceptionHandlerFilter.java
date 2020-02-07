package io.github.dunwu.javaee.filter;

import io.github.dunwu.javaee.filter.exception.AccountException;
import io.github.dunwu.javaee.filter.exception.BusinessException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class ExceptionHandlerFilter extends MyFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			logger.info("{} 捕捉到异常", this.getClass().getName());
			Throwable rootCause = e;

			while (rootCause.getCause() != null) {
				rootCause = rootCause.getCause();
			}

			String message = rootCause.getMessage();

			message = message == null ? "异常：" + rootCause.getClass().getName() : message;

			request.setAttribute("message", message);
			request.setAttribute("e", e);

			if (rootCause instanceof AccountException) {
				request.getRequestDispatcher("/views/jsp/accountException.jsp").forward(request, response);
			} else if (rootCause instanceof BusinessException) {
				request.getRequestDispatcher("/views/jsp/businessException.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/views/jsp/exception.jsp").forward(request, response);
			}
		}
	}

}
