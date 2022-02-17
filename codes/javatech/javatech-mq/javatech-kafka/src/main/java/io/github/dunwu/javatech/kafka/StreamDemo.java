package io.github.dunwu.javatech.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

/**
 * Kafka 流示例 消费者配置参考：https://kafka.apache.org/documentation/#streamsconfigs
 */
public class StreamDemo {

	private static final String HOST = "localhost:9092";

	public static void main(String[] args) {
		// 1. 指定流的配置
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		// 设置流构造器
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> textLines = builder.stream("TextLinesTopic");
		KTable<String, Long> wordCounts = textLines
			.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
			.groupBy((key, word) -> word)
			.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
		wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));

		// 根据流构造器和流配置初始化 Kafka 流
		KafkaStreams streams = new KafkaStreams(builder.build(), config);
		streams.start();
	}

}
