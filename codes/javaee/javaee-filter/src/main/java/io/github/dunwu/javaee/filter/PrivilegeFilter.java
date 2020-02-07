package io.github.dunwu.javaee.filter;

import io.github.dunwu.javaee.filter.exception.AccountException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2017/3/27.
 */
public class PrivilegeFilter extends MyFilter {

    private Properties pp = new Properties();

    @Override
    public void init(FilterConfig config) {

        // 从 初始化参数 中获取权 限配置文件 的位置
        String file = config.getInitParameter("file");
        String realPath = config.getServletContext().getRealPath(file);
        try {
            pp.load(new FileInputStream(realPath));
        } catch (Exception e) {
            config.getServletContext().log("读取权限控制文件失败。", e);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        logger.info("{} 开始做过滤处理", this.getClass().getName());

        HttpServletRequest request = (HttpServletRequest) req;

        // 获取访问的路径，例如：admin.jsp
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/", "");

        // 获取 action 参数，例如：add
        String action = req.getParameter("action");
        action = action == null ? "" : action;

        // 拼接成 URI。例如：log.do?action=list
        String uri = requestURI + "?action=" + action;

        // 从 session 中获取用户权限角色。
        String role = (String) request.getSession(true).getAttribute("role");
        role = role == null ? "guest" : role;

        boolean authentificated = false;
        // 开始检查该用户角色是否有权限访问 uri
        for (Object obj : pp.keySet()) {
            String key = ((String) obj);
            // 使用正则表达式验证 需要将 ? . 替换一下，并将通配符 * 处理一下
            if (uri.matches(key.replace("?", "\\?").replace(".", "\\.").replace("*", ".*"))) {
                // 如果 role 匹配
                if (role.equals(pp.get(key))) {
                    authentificated = true;
                    break;
                }
            }
        }
        if (!authentificated) {
            throw new RuntimeException(new AccountException("您无权访问该页面。请以合适的身份登陆后查看。"));
        }
        // 继续运行
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        pp = null;
    }

}
