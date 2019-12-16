package io.github.dunwu.javalib.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.dunwu.javalib.json.TestBean2;
import io.github.dunwu.javalib.json.util.BeanUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Gson 性能测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class GsonPerformanceTests {

    private static final int BATCH_SIZE = 100000;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 测试十次，每次序列化、反序列化 100000 条数据，平均耗时约 704 ms
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
            String json = gson.toJson(bean);
            assertThat(json).isNotBlank();
            TestBean2 newBean = gson.fromJson(json, TestBean2.class);
            assertThat(newBean).isNotNull();
        }
        long end = System.nanoTime();
        return (end - begin);
    }

}
