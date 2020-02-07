package io.github.dunwu.javaee.filter;

import java.io.IOException;
import javax.servlet.*;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class FilterImpl implements Filter {

    private boolean enable;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化代码
        enable = "true".equals(filterConfig.getInitParameter("enable"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        System.out.println("befor doFilter(). ");

        chain.doFilter(request, response);

        System.out.println("after doFitler(). ");
    }

    @Override
    public void destroy() {
        // 资源销毁代码
    }

}
