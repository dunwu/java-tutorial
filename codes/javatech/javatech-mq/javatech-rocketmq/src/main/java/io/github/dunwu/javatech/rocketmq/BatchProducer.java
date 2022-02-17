package io.github.dunwu.javatech.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 批量发送消息
 *
 * @author Zhang Peng
 */
public class BatchProducer {

	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
		producer.setNamesrvAddr(RocketConfig.HOST);
		producer.start();
		producer.setRetryTimesWhenSendAsyncFailed(0);

		String topic = "BatchTest";
		List<Message> messages = new ArrayList<>();
		messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
		messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
		messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
		// then you could split the large list into small ones:
		ListSplitter splitter = new ListSplitter(messages);

		while (splitter.hasNext()) {
			List<Message> listItem = splitter.next();
			producer.send(listItem);
		}

		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}

	public static class ListSplitter implements Iterator<List<Message>> {

		private final int SIZE_LIMIT = 1000 * 1000;

		private final List<Message> messages;

		private int currIndex;

		public ListSplitter(List<Message> messages) {
			this.messages = messages;
		}

		@Override
		public boolean hasNext() {
			return currIndex < messages.size();
		}

		@Override
		public List<Message> next() {
			int nextIndex = currIndex;
			int totalSize = 0;
			for (; nextIndex < messages.size(); nextIndex++) {
				Message message = messages.get(nextIndex);
				int tmpSize = message.getTopic().length() + message.getBody().length;
				Map<String, String> properties = message.getProperties();
				for (Map.Entry<String, String> entry : properties.entrySet()) {
					tmpSize += entry.getKey().length() + entry.getValue().length();
				}
				tmpSize = tmpSize + 20; // for log overhead
				if (tmpSize > SIZE_LIMIT) {
					// it is unexpected that single message exceeds the SIZE_LIMIT
					// here just let it go, otherwise it will block the splitting process
					if (nextIndex - currIndex == 0) {
						// if the next sublist has no element, add this one and then
						// break, otherwise just break
						nextIndex++;
					}
					break;
				}
				if (tmpSize + totalSize > SIZE_LIMIT) {
					break;
				} else {
					totalSize += tmpSize;
				}
			}
			List<Message> subList = messages.subList(currIndex, nextIndex);
			currIndex = nextIndex;
			return subList;
		}

	}

}
