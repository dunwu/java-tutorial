/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.html;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Victor Zhang
 * @since 2016/11/24.
 */
public class JsoupTest {

	final String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\html\\jsoup-cookbook.html";

	private Document docFromStr;

	private Document docFromUrl;

	private Document docFromFile;

	/**
	 * Jsoup 有三种方式加载文档(Document): HTML字符串、URL地址、html文件
	 *
	 * @throws IOException
	 */
	@Before
	public void before() throws IOException {
		// 从一个html字符串加载Document对象
		final String html = "<html><head><title>First parse</title></head>"
			+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		docFromStr = Jsoup.parse(html);

		// 从一个URL加载Document对象
		docFromUrl = Jsoup.connect("https://www.baidu.com/").get();

		// 从一个文件加载Document对象
		File input = new File(filePath);
		docFromFile = Jsoup.parse(input, "UTF-8");

		String htmlFragment = "<div><p>Lorem ipsum.</p>";
		Document doc = Jsoup.parse(htmlFragment);
	}

	/**
	 * 获取html的title、head、body
	 */
	@Test
	public void testGetHeadAndBody() {
		System.out.println("title内容：\n" + docFromStr.title());
		System.out.println("head内容：\n" + docFromStr.head());
		System.out.println("body内容：\n" + docFromStr.body());
	}

	/**
	 * 使用DOM方法来遍历一个文档
	 */
	@Test
	public void test01() {
		// 遍历一个Document对象中所有的链接
		Element content = docFromFile.body();
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			System.out.println("linkHref: " + link.attr("href"));
			System.out.println("linkText: " + link.text());
		}
	}

	/**
	 * 使用选择器语法来查找元素
	 */
	@Test
	public void testSelect() {
		// 带有href属性的a元素
		Elements hrefs = docFromUrl.select("a[href]");
		System.out.println("[hrefs]\n" + hrefs.toString());
		// 扩展名为.png的图片
		Elements pngs = docFromUrl.select("img[src$=.png]");
		System.out.println("[pngs]\n" + pngs.toString());
		// class等于masthead的div标签
		Element head_wrappers = docFromUrl.select("div.head_wrapper").first();
		System.out.println("[head_wrapper:]\n" + head_wrappers.toString());
		// 在h3元素之后的a元素
		Elements resultLinks = docFromUrl.select("div.head_wrapper > a");
		System.out.println("[resultLinks]\n" + resultLinks.toString());
	}

	@Test
	public void test() {
		// 从元素集合抽取属性、文本和html内容
		Element link = docFromUrl.select("a").first();// 查找第一个a元素
		System.out.println("outerHtml: " + link.outerHtml());
		System.out.println("html: " + link.html()); // 取得链接内的html内容
		System.out.println("href: " + link.attr("href")); // 取得字符串中的文本
		System.out.println("text: " + link.text()); // 取得链接地址中的文本
	}

}
