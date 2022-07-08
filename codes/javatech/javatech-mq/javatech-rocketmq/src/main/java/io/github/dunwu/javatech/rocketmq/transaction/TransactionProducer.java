package io.github.dunwu.javatech.rocketmq.transaction;

import io.github.dunwu.javatech.rocketmq.RocketConstant;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr(RocketConstant.HOST);
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                                                                 new ArrayBlockingQueue<Runnable>(2000),
                                                                 new ThreadFactory() {
                                                                     @Override
                                                                     public Thread newThread(Runnable r) {
                                                                         Thread thread = new Thread(r);
                                                                         thread.setName(
                                                                             "client-transaction-msg-check-thread");
                                                                         return thread;
                                                                     }
                                                                 });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTestTx", tags[i % tags.length], "KEY" + i,
                                          ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }

}
