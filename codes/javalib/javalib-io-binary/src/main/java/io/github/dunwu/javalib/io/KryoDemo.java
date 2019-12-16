package io.github.dunwu.javalib.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <a href="https://github.com/EsotericSoftware/kryo">Kyro</a> 序列化/反序列化示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @author <a href="https://www.cnblogs.com/hntyzgn/p/7122709.html">Kryo 应用指南</a>
 * @since 2019-11-26
 */
public class KryoDemo {

    // 每个线程的 Kryo 实例
    private static final ThreadLocal<Kryo> kryoLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();

        /**
         * 不要轻易改变这里的配置！更改之后，序列化的格式就会发生变化，
         * 上线的同时就必须清除 Redis 里的所有缓存，
         * 否则那些缓存再回来反序列化的时候，就会报错
         */
        //支持对象循环引用（否则会栈溢出）
        kryo.setReferences(true); //默认值就是 true，添加此行的目的是为了提醒维护者，不要改变这个配置

        //不强制要求注册类（注册行为无法保证多个 JVM 内同一个类的注册编号相同；而且业务系统中大量的 Class 也难以一一注册）
        kryo.setRegistrationRequired(false); //默认值就是 false，添加此行的目的是为了提醒维护者，不要改变这个配置

        //Fix the NPE bug when deserializing Collections.
        ((DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
            .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());

        return kryo;
    });

    /**
     * 将对象序列化为 byte 数组后，再使用 Base64 编码
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的字符串
     */
    public static <T> String writeToString(T obj) {
        byte[] bytes = writeToBytes(obj);
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
    }

    /**
     * 将对象序列化为 byte 数组
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的 byte 数组
     */
    public static <T> byte[] writeToBytes(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        Kryo kryo = getInstance();
        kryo.writeObject(output, obj);
        output.flush();

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 获得当前线程的 Kryo 实例
     *
     * @return 当前线程的 Kryo 实例
     */
    public static Kryo getInstance() {
        return kryoLocal.get();
    }

    /**
     * 将字符串反序列化为原对象，先使用 Base64 解码
     *
     * @param str   {@link #writeToString} 方法序列化后的字符串
     * @param clazz 原对象的类型
     * @param <T>   原对象的类型
     * @return 原对象
     */
    public static <T> T readFromString(String str, Class<T> clazz) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return readFromBytes(Base64.getDecoder().decode(bytes), clazz);
    }

    /**
     * 将 byte 数组反序列化为原对象
     *
     * @param bytes {@link #writeToBytes} 方法序列化后的 byte 数组
     * @param clazz 原对象的类型
     * @param <T>   原对象的类型
     * @return 原对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T readFromBytes(byte[] bytes, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);

        Kryo kryo = getInstance();
        return (T) kryo.readObject(input, clazz);
    }

}
