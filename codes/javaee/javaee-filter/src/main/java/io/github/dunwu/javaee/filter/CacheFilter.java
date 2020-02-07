package io.github.dunwu.javaee.filter;

import io.github.dunwu.javaee.filter.wrapper.CacheResponseWrapper;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class CacheFilter extends MyFilter {

    private ServletContext servletContext;

    // 缓存文件夹，使用Tomcat工作目录
    private File temporalDir;

    // 缓存时间，配置在Filter初始化参数中
    private long cacheTime = Long.MAX_VALUE;

    @Override
    public void init(FilterConfig filterConfig) {
        super.init(filterConfig);
        temporalDir = (File) filterConfig.getServletContext().getAttribute("javax.servlet.context.tempdir");
        servletContext = filterConfig.getServletContext();
        cacheTime = new Long(filterConfig.getInitParameter("cacheTime"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        logger.info("{} 开始做过滤处理", this.getClass().getName());

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 如果为 POST, 则不经过缓存
        if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 请求的 URI
        String uri = httpServletRequest.getRequestURI();
        if (uri == null) {
            uri = "";
        }
        uri = uri.replace(httpServletRequest.getContextPath() + "/", "");
        uri = uri.trim().length() == 0 ? "index.jsp" : uri;
        uri = httpServletRequest.getQueryString() == null ? uri : (uri + "?" + httpServletRequest.getQueryString());

        // 对应的缓存文件
        File cacheFile = new File(temporalDir, URLEncoder.encode(uri, "UTF-8"));
        System.out.println(cacheFile);

        // 如果缓存文件不存在 或者已经超出缓存时间 则请求 Servlet
        if (!cacheFile.exists() || cacheFile.length() == 0
            || cacheFile.lastModified() < System.currentTimeMillis() - cacheTime) {

            CacheResponseWrapper cacheResponse = new CacheResponseWrapper(httpServletResponse);

            chain.doFilter(httpServletRequest, cacheResponse);

            // 将内容写入缓存文件
            char[] content = cacheResponse.getCacheWriter().toCharArray();

            temporalDir.mkdirs();
            cacheFile.createNewFile();

            Writer writer = new OutputStreamWriter(new FileOutputStream(cacheFile), "UTF-8");
            writer.write(content);
            writer.close();
        }

        // 请求的ContentType
        String mimeType = servletContext.getMimeType(httpServletRequest.getRequestURI());
        httpServletResponse.setContentType(mimeType);

        // 读取缓存文件的内容，写入客户端浏览器
        Reader ins = new InputStreamReader(new FileInputStream(cacheFile), "UTF-8");
        StringBuffer buffer = new StringBuffer();
        char[] cbuf = new char[1024];
        int len;
        while ((len = ins.read(cbuf)) > -1) {
            buffer.append(cbuf, 0, len);
        }
        ins.close();
        // 输出到客户端
        httpServletResponse.getWriter().write(buffer.toString());
    }

}
