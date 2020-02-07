/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 测试 common-logging + log4j 输出日志
 *
 * @author Victor Zhang
 * @since 2016/11/18.
 */
public class JclDemo {

	private static final Log log = LogFactory.getLog(JclDemo.class);

	public static void main(String[] args) {
		String msg = "print logging, current level: ";
		log.trace(msg + "trace");
		log.debug(msg + "debug");
		log.info(msg + "info");
		log.warn(msg + "warn");
		log.error(msg + "error");
		log.fatal(msg + "fatal");
	}

}
