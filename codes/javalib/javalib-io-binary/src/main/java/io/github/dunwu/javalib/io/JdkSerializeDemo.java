package io.github.dunwu.javalib.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * JDK 默认序列化、反序列化机制示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class JdkSerializeDemo {

    /**
     * 将对象序列化为 byte 数组后，再使用 Base64 编码
     *
     * @param obj 任意对象
     * @param <T> 对象的类型
     * @return 序列化后的字符串
     */
    public static <T> String writeToString(T obj) throws IOException {
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
    public static <T> byte[] writeToBytes(T obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        byte[] bytes = baos.toByteArray();
        baos.close();
        oos.close();
        return bytes;
    }

    /**
     * 将字符串反序列化为原对象，先使用 Base64 解码
     *
     * @param str   {@link #writeToString} 方法序列化后的字符串
     * @param clazz 原对象的类型
     * @param <T>   原对象的类型
     * @return 原对象
     */
    public static <T> T readFromString(String str, Class<T> clazz) throws IOException, ClassNotFoundException {
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
    public static <T> T readFromBytes(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        if (clazz.isInstance(obj)) {
            return (T) obj;
        } else {
            throw new IOException("derialize failed");
        }
    }

}
