package io.github.dunwu.javatech.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Kafka 生产者生产消息示例 生产者配置参考：https://kafka.apache.org/documentation/#producerconfigs
 */
public class ProducerDemo {

	private static final String HOST = "localhost:9092";

	public static void main(String[] args) {
		// 1. 指定生产者的配置
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, 0);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
			"org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
			"org.apache.kafka.common.serialization.StringSerializer");

		// 2. 使用配置初始化 Kafka 生产者
		Producer<String, String> producer = new KafkaProducer<>(properties);

		try {
			// 3. 使用 send 方法发送异步消息
			for (int i = 0; i < 100; i++) {
				String msg = "Message " + i;
				producer.send(new ProducerRecord<>("HelloWorld", msg));
				System.out.println("Sent:" + msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4. 关闭生产者
			producer.close();
		}
	}

}
