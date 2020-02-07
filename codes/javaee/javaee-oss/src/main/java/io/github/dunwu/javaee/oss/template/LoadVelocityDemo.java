/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author Victor Zhang
 * @since 2016/12/22.
 */
public class LoadVelocityDemo {

	public static void main(String[] args) throws IOException {
		loadByClasspath();
		loadByFilepath();
		loadByConfig();
	}

	/**
	 * 加载classpath目录下的vm文件
	 */
	public static void loadByClasspath() {
		System.out.println("========== loadByClasspath ==========");

		Properties p = new Properties();
		p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		Template t = ve.getTemplate("template/hello.vm");

		System.out.println(fillTemplate(t));
	}

	/**
	 * 根据绝对路径加载，vm文件置于硬盘某分区中
	 */
	public static void loadByFilepath() {
		System.out.println("========== loadByFilepath ==========");

		Properties p = new Properties();
		p.put(VelocityEngine.FILE_RESOURCE_LOADER_PATH,
			"D:\\01_Workspace\\Project\\zp\\javaparty\\src\\toolbox\\template\\src\\main\\resources");
		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		Template t = ve.getTemplate("hello.vm");

		System.out.println(fillTemplate(t));
	}

	/**
	 * 根据资源路径加载
	 */
	public static void loadByConfig() throws IOException {
		System.out.println("========== loadByConfig ==========");

		Properties p = new Properties();
		p.load(LoadVelocityDemo.class.getResourceAsStream("/template/velocity.properties"));
		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		Template t = ve.getTemplate("template/hello.vm");

		System.out.println(fillTemplate(t));
	}

	/**
	 * 使用文本文件,使用文本文件，如：velocity.properties
	 */
	private static String fillTemplate(Template t) {
		// 初始化VelocityContext
		VelocityContext ctx = new VelocityContext();
		ctx.put("name", "victor");
		ctx.put("date", (new Date()).toString());
		List temp = new ArrayList();
		temp.add("1");
		temp.add("2");
		ctx.put("list", temp);

		// 初始化Writer
		StringWriter sw = new StringWriter();

		t.merge(ctx, sw);
		return sw.toString();
	}

}
