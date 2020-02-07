package io.github.dunwu.javaee.filter;

import io.github.dunwu.javaee.filter.wrapper.HttpCharacterResponseWrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class OutputReplaceFilter extends MyFilter {

    private Properties properties = new Properties();

    @Override
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);
        String file = filterConfig.getInitParameter("file");
        String realPath = filterConfig.getServletContext().getRealPath(file);
        try {
            properties.load(new FileInputStream(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        logger.info("{} 开始做过滤处理", this.getClass().getName());

        // 自定义的 response
        HttpCharacterResponseWrapper wrapper = new HttpCharacterResponseWrapper((HttpServletResponse) response);

        // 提交给 Servlet 或者下一个 Filter
        chain.doFilter(request, wrapper);

        // 得到缓存在自定义 response 中的输出内容
        String output = wrapper.getCharArrayWriter().toString();

        // 修改，替换
        for (Object obj : properties.keySet()) {
            String key = (String) obj;
            output = output.replace(key, properties.getProperty(key));
        }

        // 输出
        PrintWriter out = response.getWriter();
        out.write(output);
        out.println("<!-- Generated at " + new java.util.Date() + " -->");
    }

}
