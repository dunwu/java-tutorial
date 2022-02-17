package io.github.dunwu.javatech.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 发送广播消息示例
 *
 * @author Zhang Peng
 */
public class BroadcastProducer {

	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr(RocketConfig.HOST);
		producer.start();

		for (int i = 0; i < 100; i++) {
			Message msg = new Message("TopicTest", "TagA", "OrderID188",
				"Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		producer.shutdown();
	}

}
