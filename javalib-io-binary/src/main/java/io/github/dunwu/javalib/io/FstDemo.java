package io.github.dunwu.javalib.io;

import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;

/**
 * fast-serialization lib Demo
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://github.com/RuedigerMoeller/fast-serialization">FST</a>
 * @since 2019-11-22
 */
public class FstDemo {

	private static FSTConfiguration DEFAULT_CONFIG = FSTConfiguration.createDefaultConfiguration();

	public static <T> byte[] serialize(T obj) {
		return DEFAULT_CONFIG.asByteArray(obj);
	}

	public static <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
		Object obj = DEFAULT_CONFIG.asObject(bytes);
		if (clazz.isInstance(obj)) {
			return (T) obj;
		} else {
			throw new IOException("derialize failed");
		}
	}

}
