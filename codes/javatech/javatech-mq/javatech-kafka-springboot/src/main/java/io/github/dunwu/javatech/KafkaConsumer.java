package io.github.dunwu.javatech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka 消费者
 *
 * @author Zhang Peng
 * @since 2018-11-28
 */
@Component
public class KafkaConsumer {

	private final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "test")
	public void processMessage(String data) {
		log.info("收到kafka消息：{}", data);
	}

}
