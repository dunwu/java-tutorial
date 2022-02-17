package io.github.dunwu.javatech.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Zhang Peng
 */
public class ProducerInTransaction {

	private static final String HOST = "192.168.28.32:9092";

	public static void main(String[] args) {
		onlyProduceInTransaction();
		consumeTransferProduce();
	}

	/**
	 * 在一个事务只有生产消息操作
	 */
	public static void onlyProduceInTransaction() {
		Producer producer = buildProducer();

		// 1.初始化事务
		producer.initTransactions();

		// 2.开启事务
		producer.beginTransaction();

		try {
			// 3.kafka写操作集合
			// 3.1 do业务逻辑

			// 3.2 发送消息
			producer.send(new ProducerRecord<String, String>("test", "transaction-data-1"));
			producer.send(new ProducerRecord<String, String>("test", "transaction-data-2"));

			// 3.3 do其他业务逻辑,还可以发送其他topic的消息。

			// 4.事务提交
			producer.commitTransaction();
		} catch (Exception e) {
			// 5.放弃事务
			producer.abortTransaction();
		}
	}

	/**
	 * 在一个事务内,即有生产消息又有消费消息
	 */
	public static void consumeTransferProduce() {
		// 1.构建上产者
		Producer producer = buildProducer();
		// 2.初始化事务(生成productId),对于一个生产者,只能执行一次初始化事务操作
		producer.initTransactions();

		// 3.构建消费者和订阅主题
		Consumer consumer = buildConsumer();
		consumer.subscribe(Arrays.asList("test"));
		while (true) {
			// 4.开启事务
			producer.beginTransaction();

			// 5.1 接受消息
			ConsumerRecords<String, String> records = consumer.poll(500);

			try {
				// 5.2 do业务逻辑;
				System.out.println("customer Message---");
				Map<TopicPartition, OffsetAndMetadata> commits = new HashMap<>();
				for (ConsumerRecord<String, String> record : records) {
					// 5.2.1 读取消息,并处理消息。print the offset,key and value for the consumer
					// records.
					System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(),
						record.value());

					// 5.2.2 记录提交的偏移量
					commits.put(new TopicPartition(record.topic(), record.partition()),
						new OffsetAndMetadata(record.offset()));

					// 6.生产新的消息。比如外卖订单状态的消息,如果订单成功,则需要发送跟商家结转消息或者派送员的提成消息
					producer.send(new ProducerRecord<String, String>("test", "data2"));
				}

				// 7.提交偏移量
				producer.sendOffsetsToTransaction(commits, "group0323");

				// 8.事务提交
				producer.commitTransaction();
			} catch (Exception e) {
				// 7.放弃事务
				producer.abortTransaction();
			}
		}
	}

	public static Producer buildProducer() {
		// 1. 指定生产者的配置
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, 1);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "first-transactional");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
			"org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
			"org.apache.kafka.common.serialization.StringSerializer");

		// 2. 使用配置初始化 Kafka 生产者
		Producer<String, String> producer = new KafkaProducer<>(properties);
		return producer;
	}

	public static Consumer buildConsumer() {
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
		Consumer<String, String> consumer = new KafkaConsumer<>(props);
		return consumer;
	}

}
