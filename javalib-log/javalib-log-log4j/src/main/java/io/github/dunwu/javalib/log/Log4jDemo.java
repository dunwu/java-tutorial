package io.github.dunwu.javalib.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j 示例
 *
 * @author Zhang Peng
 * @see <a href="http://logging.apache.org/log4j/1.2/">log4j 官网</a>
 * @since 2018/3/29
 */
public class Log4jDemo {

    private static final Logger logger = LoggerFactory.getLogger(Log4jDemo.class);

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
