package io.github.dunwu.javatech.rocketmq.filter;

import io.github.dunwu.javatech.rocketmq.RocketConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 批量发送消息（一次发送消息大小不超过 1MB）
 *
 * @author Zhang Peng
 */
public class FilterProducer {

    public static void main(String[] args) throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr(RocketConstant.HOST);
        producer.start();

        String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i,
                                      ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // Set some properties.
            msg.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(msg);

            System.out.printf("%s%n", sendResult);
        }

        producer.shutdown();
    }

}
