package io.github.dunwu.javaee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleServlet extends HttpServlet {

	private static final long serialVersionUID = -7197419401412129310L;

	private static double startPoint = 0;

	@Override
	public void init() throws ServletException {
		this.log("执行 init() 方法 ... ");
		ServletConfig conf = this.getServletConfig();
		startPoint = Double.parseDouble(conf.getInitParameter("startPoint"));
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		this.log("执行 service() 方法 ... ");
		super.service(arg0, arg1);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.log("执行 doPost() 方法 ... ");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML><HEAD><TITLE>个人所得税计算</TITLE></HEAD>");
		out.println("<link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("<BODY>");

		out.println("<div align='center'><br/><fieldset style=width:90%><legend>个税计算器</legend><br/>");

		try {
			// 从参数中获取的工资数目
			double income = new Double(request.getParameter("income"));
			// 应纳税部分
			double charge = income - startPoint;
			// 缴税
			double tax = 0;

			if (charge <= 0) {
				tax = 0;
			}
			if (charge > 0 && charge <= 500) {
				tax = charge * 0.05;
			}
			if (charge > 500 && charge <= 2000) {
				tax = charge * 0.1 - 25;
			}
			if (charge > 2000 && charge <= 5000) {
				tax = charge * 0.15 - 125;
			}
			if (charge > 5000 && charge <= 20000) {
				tax = charge * 0.2 - 375;
			}
			if (charge > 20000 && charge <= 40000) {
				tax = charge * 0.25 - 1375;
			}
			if (charge > 40000 && charge <= 60000) {
				tax = charge * 0.30 - 3375;
			}
			if (charge > 60000 && charge <= 80000) {
				tax = charge * 0.35 - 6375;
			}
			if (charge > 80000 && charge <= 100000) {
				tax = charge * 0.4 - 10375;
			}
			if (charge > 100000) {
				tax = charge * 0.45 - 15375;
			}

			out.println("<div style='line'>");
			out.println("	<div class='leftDiv'>您的工资为</div><div class='rightDiv'>" + income + " 元</div>");
			out.println("</div>");

			out.println("<div style='line'>");
			out.println("	<div class='leftDiv'>您应纳税</div><div class='rightDiv'>" + tax + " 元</div>");
			out.println("</div><br/>");

			out.println("<input type='button' onclick='history.go(-1);' value='纳税光荣 逃税可耻 返回'  class=button>");
		} catch (Exception e) {
			out.println("请输入数值类型数据。<input type='button' onclick='history.go(-1);' value='返回'  class=button>");
		}
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.log("执行 doGet() 方法 ... ");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("<HTML><HEAD><TITLE>个人所得税计算</TITLE></HEAD>");

		out.println("<div align='center'><br/><fieldset style=width:90%><legend>个税计算器</legend><br/>");
		out.println("<form method='post' action='LifeCycleServlet'>");

		out.println("<div style='line'>");
		out.println(
			"	<div class='leftDiv'>您的工资为</div><div align='left' class='rightDiv'><input type='text' name='income'> 单位：元</div>");
		out.println("</div><br/>");

		out.println("<div style='line'>");
		out.println(
			"	<div class='leftDiv'></div><div align='left' class='rightDiv'><input type='submit' value='  计算个税  ' class=button></div>");
		out.println("</div>");

		out.println("</form>");

		out.println("<BODY>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	public void destroy() {
		this.log("执行 destroy() 方法 ... ");
		startPoint = 0;
	}

}
