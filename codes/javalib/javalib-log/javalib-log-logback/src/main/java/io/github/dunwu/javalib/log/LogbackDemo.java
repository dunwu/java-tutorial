package io.github.dunwu.javalib.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback 示例
 *
 * @author Zhang Peng
 * @see <a href="https://logback.qos.ch/">logback 官网</a>
 * @since 2018/3/29
 */
public class LogbackDemo {

    private static final Logger logger = LoggerFactory.getLogger(LogbackDemo.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            logger.trace("NO.{} 这是一条 {} 日志记录", i, "trace");
            logger.debug("NO.{} 这是一条 {} 日志记录", i, "debug");
            logger.info("NO.{} 这是一条 {} 日志记录", i, "info");
            logger.warn("NO.{} 这是一条 {} 日志记录", i, "warn");
            logger.error("NO.{} 这是一条 {} 日志记录", i, "error");
        }
    }

}
