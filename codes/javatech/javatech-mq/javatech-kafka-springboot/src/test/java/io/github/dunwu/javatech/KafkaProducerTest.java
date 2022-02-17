package io.github.dunwu.javatech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhang Peng
 * @since 2018-11-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MsgKafkaApplication.class)
public class KafkaProducerTest {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Test
	public void test() {
		kafkaProducer.sendTransactionMsg("test", "上联：天王盖地虎");
		kafkaProducer.sendTransactionMsg("test", "下联：宝塔镇河妖");
	}

}
