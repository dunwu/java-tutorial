package io.github.dunwu.javaee.filter;

import io.github.dunwu.javaee.filter.wrapper.WaterMarkResponseWrapper;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class WaterMarkFilter extends MyFilter {

    // 水印图片，配置在初始化参数中
    private String waterMarkFile;

    @Override
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);
        String file = filterConfig.getInitParameter("waterMarkFile");
        waterMarkFile = filterConfig.getServletContext().getRealPath(file);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        logger.info("{} 开始做过滤处理", this.getClass().getName());

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String requestURI = request.getRequestURI();

        String originImageFile = request.getServletContext().getRealPath("/") + requestURI;

        // 自定义的response
        WaterMarkResponseWrapper waterMarkRes = new WaterMarkResponseWrapper(response, originImageFile, waterMarkFile);

        chain.doFilter(request, waterMarkRes);

        // 打水印，输出到客户端浏览器
        waterMarkRes.finishResponse();

        logger.info("图片 {} 已添加水印图片 {}", originImageFile, waterMarkFile);
    }

}
