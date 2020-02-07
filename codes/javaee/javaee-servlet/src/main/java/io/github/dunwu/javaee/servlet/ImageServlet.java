package io.github.dunwu.javaee.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = -5446593186536558309L;

    public ImageServlet() {
        System.out.println("正在加载 " + this.getClass().getName() + " ... ");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String referer = request.getHeader("referer");

        // 如果直接输入的网址，或者是从别的网站打开的，显示错误信息。
        if (referer == null || !referer.toLowerCase().startsWith("http://" + request.getServerName().toLowerCase())) {
            // 打开图片 error.gif
            request.getRequestDispatcher("/error.gif").forward(request, response);
            return;
        }

        String requestURI = request.getRequestURI();
        String fileName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        // 请求的文件位置
        File file = new File(this.getServletContext().getRealPath("upload"), fileName);
        this.log("请求文件 " + file.getAbsolutePath());

        // 如果文件不存在，显示错误信息
        if (!file.exists()) {
            response.getWriter().println("File " + requestURI + " doesn't exist. ");
            return;
        }

        // 设置打开方式为 inline，浏览器中打开
        response.setHeader("Content-Disposition", "inline;filename=" + file.getName());
        response.setHeader("Connection", "close");

        if (fileName.toLowerCase().endsWith(".jpg"))
        // .jpg 图片格式
        {
            response.setHeader("Content-Type", "image/jpeg");
        } else if (fileName.toLowerCase().endsWith(".gif"))
        // .gif 图片格式
        {
            response.setHeader("Content-Type", "image/gif");
        } else if (fileName.toLowerCase().endsWith(".doc"))
        // word 格式
        {
            response.setHeader("Content-Type", "application/msword");
        } else
        // 其他格式
        {
            response.setHeader("Content-Type", "application/octet-stream");
        }

        // 通过 ins 读取文件
        InputStream ins = new FileInputStream(file);
        // 通过 ous 发送给客户端
        OutputStream ous = response.getOutputStream();

        try {
            // 缓存
            byte[] buffer = new byte[1024];
            int len = 0;

            // 读取文件内容并将它发送给客户端浏览器
            while ((len = ins.read(buffer)) > -1) {
                ous.write(buffer, 0, len);
            }
        } finally {
            if (ous != null) {
                ous.close();
            }
            if (ins != null) {
                ins.close();
            }
        }
    }

}
