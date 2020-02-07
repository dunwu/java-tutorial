/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.template;

import org.apache.velocity.VelocityContext;
import org.junit.Test;

/**
 * @author Victor Zhang
 * @since 2016/12/23.
 */
public class VelocityUtilTest {

	@Test
	public void test() {
		VelocityContext context = new VelocityContext();
		context.put("name", "Victor Zhang");
		context.put("project", "Velocity");
		System.out.println(VelocityUtil.getMergeOutput(context, "template/hello.vm"));
	}

}
