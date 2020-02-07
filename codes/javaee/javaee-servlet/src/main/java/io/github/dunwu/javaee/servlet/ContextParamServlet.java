package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取web.xml中的<context-param> <init-param>配置在<servlet>中，只能让对应的servlet使用； <context-param>配置在全局中，可以让所有的servlet使用。
 */
public class ContextParamServlet extends HttpServlet {

	private static final long serialVersionUID = 3194071196406358461L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("ContextParamServlet 读取 web.xml 中的<context-param>");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>读取文档参数</TITLE></HEAD>");
		out.println("  <link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("  <BODY>");
		out.println("<div align=center><br/>");
		out.println("<fieldset style='width:90%'><legend>所有的文档参数</legend><br/>");

		ServletContext servletContext = this.getServletConfig().getServletContext();
		String uploadFolder = servletContext.getInitParameter("upload folder");
		String allowedFileType = servletContext.getInitParameter("allowed file type");

		out.println("<div class='line'>");
		out.println("	<div align='left' class='leftDiv'>上传文件夹</div>");
		out.println("	<div align='left' class='rightDiv'>" + uploadFolder + "</div>");
		out.println("</div>");

		out.println("<div class='line'>");
		out.println("	<div align='left' class='leftDiv'>实际磁盘路径</div>");
		out.println("	<div align='left' class='rightDiv'>" + servletContext.getRealPath(uploadFolder) + "</div>");
		out.println("</div>");

		out.println("<div class='line'>");
		out.println("	<div align='left' class='leftDiv'>允许上传的类型</div>");
		out.println("	<div align='left' class='rightDiv'>" + allowedFileType + "</div>");
		out.println("</div>");

		out.println("</fieldset></div>");

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
