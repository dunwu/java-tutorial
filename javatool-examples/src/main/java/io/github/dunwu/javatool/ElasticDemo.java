package io.github.dunwu.javatool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 向 Elastic 日志中心传输日志 Logback logstash-logback-encoder jar 包会根据 logback 中的配置，将日志数据定向传输到 logstash 详见
 * src/main/resources/logback.xml appender 配置 使用 udp 方式传输时，有丢失日志的情况（ELK-UDP） 使用 tcp 方式传输时，不会丢失日志（ELK-TCP） Log4j 通过
 * org.apache.log4j.net.SocketAppender 发送 TCP 数据，Logstash 服务器使用 log4j input 插件 接收
 *
 * @author Zhang Peng
 */
public class ElasticDemo {

    private static final Logger logger = LoggerFactory.getLogger(ElasticDemo.class);

    private static final org.apache.log4j.Logger log4jLog = org.apache.log4j.Logger.getLogger(ElasticDemo.class);

    private static volatile int index = 0;

    public static void main(String[] args) {
        // sendLog4jLog();
        sendLogbackLog();
    }

    private static void sendLogbackLog() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    logger.info("这是第 {} 条日志", ++index);
                }
            });
        }
    }

    private static void sendLog4jLog() {
        for (int i = 0; i < 100; i++) {
            log4jLog.info(String.format("这是第 %d 条日志", ++index));
        }
    }

}
