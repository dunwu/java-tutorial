package io.github.dunwu.javatech.rocketmq.simple;

import io.github.dunwu.javatech.rocketmq.RocketConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 单向发送 RocketMQ 消息示例
 * <p>
 * 单向传输用于需要中等可靠性的情况，例如日志收集。
 *
 * @author Zhang Peng
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr(RocketConstant.HOST);
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */, ("Hello RocketMQ " + i).getBytes(
                RemotingHelper.DEFAULT_CHARSET) /* Message body */);
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        //Wait for sending to complete
        Thread.sleep(5000);
        producer.shutdown();
    }

}
