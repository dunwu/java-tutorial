/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author Victor Zhang
 * @since 2016/12/23.
 */
public class VelocityUtil {

	private static VelocityEngine velocityEngine;

	static {
		Properties props = new Properties();
		try {
			props.load(VelocityUtil.class.getResourceAsStream("/template/velocity.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		velocityEngine = new VelocityEngine();
		velocityEngine.init(props);
	}

	public static String getMergeOutput(VelocityContext context, String templateName) {
		Template template = velocityEngine.getTemplate(templateName);

		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		String output = sw.toString();
		try {
			sw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

}
