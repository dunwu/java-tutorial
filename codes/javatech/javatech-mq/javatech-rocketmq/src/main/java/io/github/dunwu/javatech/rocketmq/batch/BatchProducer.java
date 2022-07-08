package io.github.dunwu.javatech.rocketmq.batch;

import io.github.dunwu.javatech.rocketmq.RocketConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量发送消息（一次发送消息大小不超过 1MB）
 *
 * @author Zhang Peng
 */
public class BatchProducer {

    public static void main(String[] args) throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        producer.setNamesrvAddr(RocketConstant.HOST);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        String topic = "BatchTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
        try {
            producer.send(messages);
        } catch (Exception e) {
            e.printStackTrace();
            //handle the error
        }

        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
