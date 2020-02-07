package io.github.dunwu.javaee.oss.template; /**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Velocity 的 HelloWorld 示例
 *
 * @author Victor Zhang
 * @since 2016/12/22.
 */
public class VelocityHelloWorld2 {

	public static void main(String args[]) {
		/* 1.初始化 Velocity */
		Properties p = new Properties();
		try {
			p.load(VelocityUtil.class.getResourceAsStream("/template/velocity.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init(p);

		/* 2.创建一个上下文对象 */
		VelocityContext context = new VelocityContext();

		/* 3.添加你的数据对象到上下文 */
		context.put("name", "Victor Zhang");
		context.put("project", "Velocity");

		/* 4.选择一个模板 */
		Template template = velocityEngine.getTemplate("template/hello.vm");

		/* 5.将你的数据与模板合并，产生输出内容 */
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		System.out.println("final output:\n" + sw);
	}

}
