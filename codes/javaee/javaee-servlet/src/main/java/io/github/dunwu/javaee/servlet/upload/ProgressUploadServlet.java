package io.github.dunwu.javaee.servlet.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProgressUploadServlet extends HttpServlet {

    private static final long serialVersionUID = -4935921396709035718L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 上传状态
        UploadStatus status = new UploadStatus();

        // 监听器
        UploadListener listener = new UploadListener(status);

        // 把 UploadStatus 放到 session 里
        request.getSession(true).setAttribute("uploadStatus", status);

        // Apache 上传工具
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

        // 设置 listener
        upload.setProgressListener(listener);

        try {
            List itemList = upload.parseRequest(request);

            for (Iterator it = itemList.iterator(); it.hasNext(); ) {
                FileItem item = (FileItem) it.next();
                if (item.isFormField()) {
                    System.out.println("FormField: " + item.getFieldName() + " = " + item.getString());
                } else {
                    System.out.println("File: " + item.getName());

                    // 统一 Linux 与 windows 的路径分隔符
                    String fileName = item.getName().replace("/", "\\");
                    fileName = fileName.substring(fileName.lastIndexOf("\\"));

                    File saved = new File("C:\\upload_test", fileName);
                    saved.getParentFile().mkdirs();

                    InputStream ins = item.getInputStream();
                    OutputStream ous = new FileOutputStream(saved);

                    byte[] tmp = new byte[1024];
                    int len = -1;

                    while ((len = ins.read(tmp)) != -1) {
                        ous.write(tmp, 0, len);
                    }

                    ous.close();
                    ins.close();

                    response.getWriter().println("已保存文件：" + saved);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("上传发生错误：" + e.getMessage());
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragrma", "no-cache");
        response.setDateHeader("Expires", 0);

        UploadStatus status = (UploadStatus) request.getSession(true).getAttribute("uploadStatus");

        if (status == null) {
            response.getWriter().println("没有上传信息");
            return;
        }

        long startTime = status.getStartTime();
        long currentTime = System.currentTimeMillis();

        // 已传输的时间 单位：s
        long time = (currentTime - startTime) / 1000 + 1;

        // 传输速度 单位：byte/s
        double velocity = ((double) status.getBytesRead()) / (double) time;

        // 估计总时间 单位：s
        double totalTime = status.getContentLength() / velocity;

        // 估计剩余时间 单位：s
        double timeLeft = totalTime - time;

        // 已完成的百分比
        int percent = (int) (100 * (double) status.getBytesRead() / (double) status.getContentLength());

        // 已完成数 单位：M
        double length = ((double) status.getBytesRead()) / 1024 / 1024;

        // 总长度 单位：M
        double totalLength = ((double) status.getContentLength()) / 1024 / 1024;

        // 格式：百分比||已完成数(M)||文件总长度(M)||传输速率(K)||已用时间(s)||估计总时间(s)||估计剩余时间(s)||正在上传第几个文件
        String value = percent + "||" + length + "||" + totalLength + "||" + velocity + "||" + time + "||" + totalTime
            + "||" + timeLeft + "||" + status.getItems();

        response.getWriter().println(value);
    }

}
