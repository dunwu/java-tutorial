package io.github.dunwu.javalib.serialize;

import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class FstDemo {

    private static FSTConfiguration DEFAULT_CONFIG = FSTConfiguration.createDefaultConfiguration();

    public static <T> byte[] serializ(T obj) {
        return DEFAULT_CONFIG.asByteArray(obj);
    }

    public static <T> T derialize(byte[] bytes, Class<T> clazz) throws IOException {
        Object obj = DEFAULT_CONFIG.asObject(bytes);
        if (clazz.isInstance(obj)) {
            return (T) obj;
        } else {
            throw new IOException("derialize failed");
        }
    }

}
