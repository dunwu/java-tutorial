package io.github.dunwu.javatech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Kafka生产者
 *
 * @author Zhang Peng
 * @since 2018-11-29
 */
@Component
public class KafkaProducer {

	private final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate template;

	@Transactional(rollbackFor = RuntimeException.class)
	public void sendTransactionMsg(String topic, String data) {
		log.info("向kafka发送数据:[{}]", data);
		template.executeInTransaction(t -> {
			t.send(topic, "prepare");
			if ("error".equals(data)) {
				throw new RuntimeException("failed");
			}
			t.send(topic, "finish");
			return true;
		});
	}

}
