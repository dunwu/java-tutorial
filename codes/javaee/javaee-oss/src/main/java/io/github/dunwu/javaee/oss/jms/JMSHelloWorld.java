/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.jms;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Victor Zhang
 * @since 2016/11/29.
 */
public class JMSHelloWorld {

	public static void main(String[] args) throws Exception {
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		Thread.sleep(1000);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		Thread.sleep(1000);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldProducer(), false);
		Thread.sleep(1000);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldConsumer(), false);
		thread(new HelloWorldProducer(), false);
	}

	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

	public static class HelloWorldProducer implements Runnable {

		public void run() {
			try {
				// Create a ConnectionFactory
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

				// Create a Connection
				Connection connection = connectionFactory.createConnection();
				connection.start();

				// Create a Session
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

				// Create the destination (Topic or Queue)
				Destination destination = session.createQueue("TEST.FOO");

				// Create a MessageProducer from the Session to the Topic or Queue
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				// Create a messages
				String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
				TextMessage message = session.createTextMessage(text);

				// Tell the producer to send the message
				System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
				producer.send(message);

				// Clean up
				session.close();
				connection.close();
			} catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
			}
		}

	}

	public static class HelloWorldConsumer implements Runnable, ExceptionListener {

		public void run() {
			try {

				// Create a ConnectionFactory
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

				// Create a Connection
				Connection connection = connectionFactory.createConnection();
				connection.start();

				connection.setExceptionListener(this);

				// Create a Session
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

				// Create the destination (Topic or Queue)
				Destination destination = session.createQueue("TEST.FOO");

				// Create a MessageConsumer from the Session to the Topic or Queue
				MessageConsumer consumer = session.createConsumer(destination);

				// Wait for a message
				Message message = consumer.receive(1000);

				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("Received: " + text);
				} else {
					System.out.println("Received: " + message);
				}

				consumer.close();
				session.close();
				connection.close();
			} catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
			}
		}

		public synchronized void onException(JMSException ex) {
			System.out.println("JMS Exception occured.  Shutting down client.");
		}

	}

}
