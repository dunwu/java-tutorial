package io.github.dunwu.javatech.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Kafka 消费者消费消息示例 消费者配置参考：https://kafka.apache.org/documentation/#consumerconfigs
 */
public class ConsumerAOC {

	private static final String HOST = "localhost:9092";

	public static void main(String[] args) {
		// 1. 指定消费者的配置
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
			"org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
			"org.apache.kafka.common.serialization.StringDeserializer");

		// 2. 使用配置初始化 Kafka 消费者
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

		// 3. 消费者订阅 Topic
		consumer.subscribe(Arrays.asList("t1"));
		while (true) {
			// 4. 消费消息
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
			}
		}
	}

}
