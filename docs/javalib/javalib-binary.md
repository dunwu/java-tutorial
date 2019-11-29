# Java 二进制序列化库

## 简介

### 为什么需要二进制序列化库

原因很简单，就是 Java 默认的序列化机制（`ObjectInputStream` 和 `ObjectOutputStream`）具有很多缺点。

> 不了解 Java 默认的序列化机制，可以参考：[Java 序列化](https://github.com/dunwu/javacore/blob/master/docs/io/Java序列化.md)

Java 自身的序列化方式具有以下缺点：

- **无法跨语言使用 **。这点最为致命，对于很多需要跨语言通信的异构系统来说，不能跨语言序列化，即意味着完全无法通信（彼此数据不能识别，当然无法交互了）。
- **序列化的性能不高**。序列化后的数据体积较大，这大大影响存储和传输的效率。
- 序列化一定需要实现 `Serializable` 接口。
- 需要关注 `serialVersionUID`。

引入二进制序列化库就是为了解决这些问题，这在 RPC 应用中尤为常见。

### 主流序列化库简介

#### Protobuf

[Protobuf](https://developers.google.com/protocol-buffers/) 是 Google 开发的结构序列化库。

它具有以下特性：

- 结构化数据存储格式（xml,json 等）
- 高性能编解码技术
- 语言和平台无关，扩展性好
- 支持 Java, C++, Python 三种语言

#### Thrift

> [Thrift](https://github.com/apache/thrift) 是 apache 开源项目，是一个点对点的 RPC 实现。

它具有以下特性：

- 支持多种语言（目前支持 28 种语言，如：C++、go、Java、Php、Python、Ruby 等等）。
- 使用了组建大型数据交换及存储工具，对于大型系统中的内部数据传输，相对于 Json 和 xml 在性能上和传输大小上都有明显的优势。
- 支持三种比较典型的编码方式（通用二进制编码，压缩二进制编码，优化的可选字段压缩编解码）。

#### Hessian

> [Hessian](http://hessian.caucho.com/) 是一种二进制传输协议。
>
> RPC 框架 Dubbo 就支持 Thrift 和 Hession。

它具有以下特性：

- 支持多种语言。如：Java、Python、C++、C#、PHP、Ruby 等。
- 相对其他二进制序列化库较慢。

#### Kryo

> [Kryo](https://github.com/EsotericSoftware/kryo) 是用于 Java 的快速高效的二进制对象图序列化框架。Kryo 还可以执行自动的深拷贝和浅拷贝。 这是从对象到对象的直接复制，而不是从对象到字节的复制。

它具有以下特性：

- 速度快，序列化体积小
- 官方不支持 Java 以外的其他语言

#### FST

> [FST](https://github.com/RuedigerMoeller/fast-serialization) 是一个 Java 实现二进制序列化库。

它具有以下特性：

- 近乎于 100% 兼容 JDK 序列化，且比 JDK 原序列化方式快 10 倍
- 2.17 开始与 Android 兼容
- （可选）2.29 开始支持将任何可序列化的对象图编码/解码为 JSON（包括共享引用）

#### 小结

了解了以上这些常见的二进制序列化库的特性。在技术选型时，我们就可以做到有的放矢。

**（1）选型参考依据**

对于二进制序列化库，我们的选型考量一般有以下几点：

- **是否支持跨语言**
  - 根据业务实际需求来决定。一般来说，支持跨语言，为了兼容，使用复杂度上一般会更高一些。
- **序列化、反序列化的性能**
- **类库是否轻量化，API 是否简单易懂**

**（2）选型建议**

- 如果需要跨语言通信，那么可以考虑：Protobuf、Thrift、Hession。

  - [thrift](https://github.com/apache/thrift)、[protobuf](https://github.com/protocolbuffers/protobuf) - 适用于对性能敏感，对开发体验要求不高的内部系统。
  - [hessian](http://hessian.caucho.com/doc/hessian-overview.xtp) - 适用于对开发体验敏感，性能有要求的内外部系统。

- 如果不需要跨语言通信，可以考虑：[Kryo](https://github.com/EsotericSoftware/kryo) 和 [FST](https://github.com/RuedigerMoeller/fast-serialization)，性能不错，且 API 十分简单。

## FST 应用

### 引入依赖

```xml
<dependency>
	<groupId>de.ruedigermoeller</groupId>
	<artifactId>fst</artifactId>
	<version>2.56</version>
</dependency>
```

### FST API

示例：

```java
import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FstDemo {

	private static FSTConfiguration DEFAULT_CONFIG = FSTConfiguration.createDefaultConfiguration();

	/**
	 * 将对象序列化为 byte 数组
	 *
	 * @param obj 任意对象
	 * @param <T> 对象的类型
	 * @return 序列化后的 byte 数组
	 */
	public static <T> byte[] writeToBytes(T obj) {
		return DEFAULT_CONFIG.asByteArray(obj);
	}

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
	 * 将 byte 数组反序列化为原对象
	 *
	 * @param bytes {@link #writeToBytes} 方法序列化后的 byte 数组
	 * @param clazz 原对象的类型
	 * @param <T>   原对象的类型
	 * @return 原对象
	 */
	public static <T> T readFromBytes(byte[] bytes, Class<T> clazz) throws IOException {
		Object obj = DEFAULT_CONFIG.asObject(bytes);
		if (clazz.isInstance(obj)) {
			return (T) obj;
		} else {
			throw new IOException("derialize failed");
		}
	}

	/**
	 * 将字符串反序列化为原对象，先使用 Base64 解码
	 *
	 * @param str   {@link #writeToString} 方法序列化后的字符串
	 * @param clazz 原对象的类型
	 * @param <T>   原对象的类型
	 * @return 原对象
	 */
	public static <T> T readFromString(String str, Class<T> clazz) throws IOException {
		byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
		return readFromBytes(Base64.getDecoder().decode(bytes), clazz);
	}

}
```

测试：

```java
long begin = System.currentTimeMillis();
for (int i = 0; i < BATCH_SIZE; i++) {
    TestBean oldBean = BeanUtils.initJdk8Bean();
    byte[] bytes = FstDemo.writeToBytes(oldBean);
    TestBean newBean = FstDemo.readFromBytes(bytes, TestBean.class);
}
long end = System.currentTimeMillis();
System.out.printf("FST 序列化/反序列化耗时：%s", (end - begin));
```

## Kryo 应用

### 引入依赖

```xml
<dependency>
  <groupId>com.esotericsoftware</groupId>
  <artifactId>kryo</artifactId>
  <version>5.0.0-RC4</version>
</dependency>
```

### Kryo API

示例：

```java
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
	 * 获得当前线程的 Kryo 实例
	 *
	 * @return 当前线程的 Kryo 实例
	 */
	public static Kryo getInstance() {
		return kryoLocal.get();
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

}
```

测试：

```java
long begin = System.currentTimeMillis();
for (int i = 0; i < BATCH_SIZE; i++) {
    TestBean oldBean = BeanUtils.initJdk8Bean();
    byte[] bytes = KryoDemo.writeToBytes(oldBean);
    TestBean newBean = KryoDemo.readFromBytes(bytes, TestBean.class);
}
long end = System.currentTimeMillis();
System.out.printf("Kryo 序列化/反序列化耗时：%s", (end - begin));
```

## 参考资料

- **官方**
  - [Protobuf 官网](https://developers.google.com/protocol-buffers/)
  - [Protobuf Github](https://github.com/protocolbuffers/protobuf)
  - [Thrift Github](https://github.com/apache/thrift)
  - [Kryo Github](https://github.com/EsotericSoftware/kryo)
  - [Hessian 官网](http://hessian.caucho.com/)
  - [FST Github](https://github.com/RuedigerMoeller/fast-serialization)
- **文章**
  - [java 序列化框架对比](https://www.jianshu.com/p/937883b6b2e5)
