package io.github.dunwu.javaee.filter.test;

import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;

public class GZipTest {

	public static void main(String[] args) throws Exception {
		test("http://localhost:8080/filter/dojo/dojo.js");
		test("http://localhost:8080/filter/image.jsp");
		test("http://localhost:8080/filter/winter.jpg");
	}

	public static void test(String url) throws Exception {

		/** 支持 GZIP 的连接 */
		URLConnection connGzip = new URL(url).openConnection();
		connGzip.setRequestProperty("Accept-Encoding", "gzip");
		int lengthGzip = connGzip.getContentLength();

		/** 不支持 GZIP 的连接 */
		URLConnection connCommon = new URL(url).openConnection();
		int lengthCommon = connCommon.getContentLength();

		double rate = new Double(lengthGzip) / lengthCommon;

		System.out.println("网址: " + url);
		System.out.println("压缩后: " + lengthGzip + " byte, \t压缩前: " + lengthCommon + " byte, \t比率: "
			+ NumberFormat.getPercentInstance().format(rate));
		System.out.println();
	}

}
