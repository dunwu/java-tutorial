package io.github.dunwu.javalib.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dunwu.javalib.json.TestBean2;
import io.github.dunwu.javalib.json.util.BeanUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Jackson 性能测试
 *
 * @author Zhang Peng
 * @since 2019-03-18
 */
public class JacksonPerformanceTests {

    private static final int BATCH_SIZE = 100000;

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 测试十次，每次序列化、反序列化 100000 条数据，平均耗时约 334 ms
     */
    @Test
    public void testPerformance() throws IOException {
        long time = 0L;
        for (int i = 0; i < 10; i++) {
            time += donSerializeAndDeserialize();
        }
        System.out.println(String.format("time: %d ms", TimeUnit.NANOSECONDS.toMillis(time / 10)));
    }

    /**
     * 循环序列化、反序列 {@link #BATCH_SIZE} 条数据，测试性能
     */
    private long donSerializeAndDeserialize() throws IOException {
        TestBean2 bean = BeanUtils.initNotJdk8Bean();
        long begin = System.nanoTime();
        for (int i = 0; i < BATCH_SIZE; i++) {
            String json = mapper.writeValueAsString(bean);
            assertThat(json).isNotBlank();
            TestBean2 newBean = mapper.readValue(json, TestBean2.class);
            assertThat(newBean).isNotNull();
        }
        long end = System.nanoTime();
        return (end - begin);
    }

}
