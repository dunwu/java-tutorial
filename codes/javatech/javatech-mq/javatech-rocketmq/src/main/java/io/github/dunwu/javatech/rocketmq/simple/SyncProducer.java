package io.github.dunwu.javatech.rocketmq.simple;

import io.github.dunwu.javatech.rocketmq.RocketConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 同步发送 RocketMQ 消息示例
 * <p>
 * 可靠同步传输应用场景广泛，如重要通知消息、短信通知、短信营销系统等。
 *
 * @author Zhang Peng
 */
public class SyncProducer {

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
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
