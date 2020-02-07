package io.github.dunwu.javaee.oss.template; /**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */

import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Velocity 的 HelloWorld 示例
 *
 * @author Victor Zhang
 * @since 2016/12/22.
 */
public class VelocityHelloWorld {

	public static void main(String args[]) {
		/* 1.初始化 Velocity */
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(VelocityEngine.RESOURCE_LOADER, "file");
		velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,
			"D:/01_Workspace/Project/zp/javaparty/src/toolbox/template/src/main/resources");
		velocityEngine.init();

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
