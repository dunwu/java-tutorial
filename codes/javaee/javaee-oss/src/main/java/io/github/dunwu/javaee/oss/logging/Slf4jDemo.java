/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试 slf4j + logback 输出日志
 *
 * @author Victor Zhang
 * @since 2016/11/18.
 */
public class Slf4jDemo {

	private static final Logger log = LoggerFactory.getLogger(Slf4jDemo.class);

	public static void main(String[] args) {
		String msg = "print log, current level: {}";
		log.trace(msg, "trace");
		log.debug(msg, "debug");
		log.info(msg, "info");
		log.warn(msg, "warn");
		log.error(msg, "error");
	}

}
