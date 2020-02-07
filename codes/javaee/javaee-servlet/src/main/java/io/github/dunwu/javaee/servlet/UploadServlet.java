package io.github.dunwu.javaee.servlet;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 7523024737218332088L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("请以 POST 方式上传文件");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        File file1 = null, file2 = null;
        String description1 = null, description2 = null;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <link rel='stylesheet' type='text/css' href='../css/style.css'>");
        out.println("  <BODY>");

        out.println("<div align=center><br/>");
        out.println("<fieldset style='width:90%'><legend>上传文件</legend><br/>");

        out.println("		<div class='line'>");
        out.println("			<div align='left' class='leftDiv'>上传日志：</div>");
        out.println("			<div align='left' class='rightDiv'>");

        // 使用 DiskFileUpload 对象解析 request
        DiskFileUpload diskFileUpload = new DiskFileUpload();
        try {
            // 将 解析的结果 放置在 List 中
            List<FileItem> list = diskFileUpload.parseRequest(request);
            out.println("遍历所有的 FileItem ... <br/>");
            // 遍历 list 中所有的 FileItem
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {
                    // 如果是 文本域
                    if ("description1".equals(fileItem.getFieldName())) {
                        // 如果该 FileItem 名称为 description1
                        out.println("遍历到 description1 ... <br/>");
                        description1 = new String(fileItem.getString().getBytes(), "UTF-8");
                    }
                    if ("description2".equals(fileItem.getFieldName())) {
                        // 如果该 FileItem 名称为 description2
                        out.println("遍历到 description2 ... <br/>");
                        description2 = new String(fileItem.getString().getBytes(), "UTF-8");
                    }
                } else {
                    // 否则，为文件域
                    if ("file1".equals(fileItem.getFieldName())) {
                        // 客户端文件路径构建的 File 对象
                        File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
                        out.println("遍历到 file1 ... <br/>");
                        out.println("客户端文件位置: " + remoteFile.getAbsolutePath() + "<br/>");
                        // 服务器端文件，放在 upload 文件夹下
                        file1 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
                        file1.getParentFile().mkdirs();
                        file1.createNewFile();

                        // 写文件，将 FileItem 的文件内容写到文件中
                        InputStream ins = fileItem.getInputStream();
                        OutputStream ous = new FileOutputStream(file1);

                        try {
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while ((len = ins.read(buffer)) > -1) {
                                ous.write(buffer, 0, len);
                            }
                            out.println("已保存文件" + file1.getAbsolutePath() + "<br/>");
                        } finally {
                            ous.close();
                            ins.close();
                        }
                    }
                    if ("file2".equals(fileItem.getFieldName())) {
                        // 客户端文件路径构建的 File 对象
                        File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
                        out.println("遍历到 file2 ... <br/>");
                        out.println("客户端文件位置: " + remoteFile.getAbsolutePath() + "<br/>");
                        // 服务器端文件，放在 upload 文件夹下
                        file2 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
                        file2.getParentFile().mkdirs();
                        file2.createNewFile();

                        // 写文件，将 FileItem 的文件内容写到文件中
                        InputStream ins = fileItem.getInputStream();
                        OutputStream ous = new FileOutputStream(file2);

                        try {
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while ((len = ins.read(buffer)) > -1) {
                                ous.write(buffer, 0, len);
                            }
                            out.println("已保存文件" + file2.getAbsolutePath() + "<br/>");
                        } finally {
                            ous.close();
                            ins.close();
                        }
                    }
                }
            }
            out.println("Request 解析完毕");
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        out.println("			</div>");
        out.println("		</div>");

        if (file1 != null) {
            out.println("		<div class='line'>");
            out.println("			<div align='left' class='leftDiv'>file1：</div>");
            out.println("			<div align='left' class='rightDiv'>");
            out.println("				<a href='" + request.getContextPath() + "/attachment/" + file1.getName()
                + "' target=_blank>" + file1.getName() + "</a>");
            out.println("			</div>");
            out.println("		</div>");
        }

        if (file2 != null) {
            out.println("		<div class='line'>");
            out.println("			<div align='left' class='leftDiv'>file2：</div>");
            out.println("			<div align='left' class='rightDiv'>");
            out.println("				<a href='" + request.getContextPath() + "/attachment/"
                + URLEncoder.encode(file2.getName(), "UTF-8") + "' target=_blank>" + file2.getName() + "</a>");
            out.println("			</div>");
            out.println("		</div>");
        }

        out.println("		<div class='line'>");
        out.println("			<div align='left' class='leftDiv'>description1：</div>");
        out.println("			<div align='left' class='rightDiv'>");
        out.println(description1);
        out.println("			</div>");
        out.println("		</div>");

        out.println("		<div class='line'>");
        out.println("			<div align='left' class='leftDiv'>description2：</div>");
        out.println("			<div align='left' class='rightDiv'>");
        out.println(description2);
        out.println("			</div>");
        out.println("		</div>");

        out.println("</fieldset></div>");

        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

}
