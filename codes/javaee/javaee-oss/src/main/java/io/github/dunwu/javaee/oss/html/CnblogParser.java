/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.html;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 博客园博文抓取工具
 *
 * @author Victor Zhang
 * @since 2016/11/8.
 */
public class CnblogParser {

	private static final String BLOG_URL = "http://www.cnblogs.com/jingmoxukong/";

	public static void main(String[] args) throws Exception {
		int total = 0;
		for (int page = 0; page <= 16; page++) {
			total += printAllTitleInPage(BLOG_URL, page);
		}
		System.out.println("总文章数：" + total);
	}

	/**
	 * 获取指定页HTML 文档指定的body
	 *
	 * @throws IOException
	 */
	private static int printAllTitleInPage(String blogUrl, int page) throws IOException {
		int count = 0;
		Document doc = Jsoup.connect(blogUrl + "default.html?page=" + page).get();
		Elements postTitles = doc.body().getElementsByClass("postTitle");
		for (Element postTitle : postTitles) {
			Elements links = postTitle.getElementsByTag("a");
			for (Element link : links) {
				if (link.hasText()) {
					System.out.println(link.text());
					System.out.println(link.attr("href"));
					count++;
				}
			}
		}
		return count;
	}

}
