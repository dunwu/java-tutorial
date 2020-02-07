package io.github.dunwu.javaee.oss.jms;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息的生产者
 *
 * @author Victor Zhang
 * @since 2016/11/28.
 */
public class JMSSender {

	private static final int SEND_NUMBER = 4;

	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;
		// TextMessage message;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQueue");
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取
			sendMessage(session, producer);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (Throwable ignore) {
			}
		}
	}

	/**
	 * 发送消息
	 *
	 * @param session
	 * @param messageProducer 消息生产者
	 * @throws Exception
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
		for (int i = 0; i < SEND_NUMBER; i++) {
			// 创建一条文本消息
			TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
			System.out.println("发送消息：Activemq 发送消息" + i);
			// 通过消息生产者发出消息
			messageProducer.send(message);
		}
	}

}
