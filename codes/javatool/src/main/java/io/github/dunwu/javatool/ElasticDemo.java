package io.github.dunwu.javatool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 向 Elastic 日志中心传输日志
 * logstash-logback-encoder jar 包会根据 logback 中的配置，将日志数据定向传输到 logstash
 * 详见 src/main/resources/logback.xml appender 配置
 * 使用 udp 方式传输时，有丢失日志的情况（ELK-UDP）
 * 使用 tcp 方式传输时，不会丢失日志（ELK-TCP）
 * @author Zhang Peng
 */
public class ElasticDemo {
    private static final Logger logger = LoggerFactory.getLogger(ElasticDemo.class);
    private static volatile int index = 0;

    public static void main(String[] args) {
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
}
