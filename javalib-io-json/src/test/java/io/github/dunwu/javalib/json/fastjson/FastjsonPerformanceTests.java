package io.github.dunwu.javalib.json.fastjson;

import com.alibaba.fastjson.JSON;
import io.github.dunwu.javalib.json.TestBean2;
import io.github.dunwu.javalib.json.util.BeanUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Fastjson 性能测试
 *
 * @author Zhang Peng
 * @since 2019-03-18
 */
public class FastjsonPerformanceTests {

    private static final int BATCH_SIZE = 100000;

    /**
     * 测试十次，每次序列化、反序列化 100000 条数据，平均耗时约 380 ms
     */
    @Test
    public void testPerformance() {
        long time = 0L;
        for (int i = 0; i < 10; i++) {
            time += donSerializeAndDeserialize();
        }
        System.out.println(String.format("time: %d ms", TimeUnit.NANOSECONDS.toMillis(time / 10)));
    }

    /**
     * 循环序列化、反序列 {@link #BATCH_SIZE} 条数据，测试性能
     */
    private long donSerializeAndDeserialize() {
        TestBean2 bean = BeanUtils.initNotJdk8Bean();
        long begin = System.nanoTime();
        for (int i = 0; i < BATCH_SIZE; i++) {
            String json = JSON.toJSONString(bean);
            assertThat(json).isNotBlank();
            TestBean2 newBean = JSON.parseObject(json, TestBean2.class);
            assertThat(newBean).isNotNull();
        }
        long end = System.nanoTime();
        return (end - begin);
    }

}
