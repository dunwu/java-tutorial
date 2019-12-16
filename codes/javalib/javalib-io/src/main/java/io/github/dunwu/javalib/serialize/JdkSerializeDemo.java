package io.github.dunwu.javalib.serialize;

import java.io.*;

/**
 * JDK 原生序列化、反序列化示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class JdkSerializeDemo {

    public static <T> byte[] serializ(T obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        byte[] bytes = baos.toByteArray();
        baos.close();
        oos.close();
        return bytes;
    }

    public static <T> T derialize(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
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
