# 细说 Java 主流日志工具库

> 在项目开发中，为了跟踪代码的运行情况，常常要使用日志来记录信息。
>
> 在 Java 世界，有很多的日志工具库来实现日志功能，避免了我们重复造轮子。
>
> 我们先来逐一了解一下主流日志工具。
>
> 📓 本文已归档到：「[blog](https://github.com/dunwu/blog)」

<!-- TOC depthFrom:2 depthTo:3 -->

- [日志框架](#日志框架)
  - [java.util.logging (JUL)](#javautillogging-jul)
  - [Log4j](#log4j)
  - [Logback](#logback)
  - [Log4j2](#log4j2)
  - [Log4j vs Logback vs Log4j2](#log4j-vs-logback-vs-log4j2)
- [日志门面](#日志门面)
  - [common-logging](#common-logging)
  - [slf4j](#slf4j)
  - [common-logging vs slf4j](#common-logging-vs-slf4j)
  - [总结](#总结)
- [实施日志解决方案](#实施日志解决方案)
  - [引入 jar 包](#引入-jar-包)
  - [使用 API](#使用-api)
- [log4j2 配置](#log4j2-配置)
- [logback 配置](#logback-配置)
  - [`<configuration>`](#configuration)
  - [`<appender>`](#appender)
  - [`<logger>`](#logger)
  - [`<root>`](#root)
  - [完整的 logback.xml 参考示例](#完整的-logbackxml-参考示例)
- [log4j 配置](#log4j-配置)
  - [完整的 log4j.xml 参考示例](#完整的-log4jxml-参考示例)
- [参考](#参考)

<!-- /TOC -->

## 日志框架

### java.util.logging (JUL)

JDK1.4 开始，通过 `java.util.logging` 提供日志功能。

它能满足基本的日志需要，但是功能没有 Log4j 强大，而且使用范围也没有 Log4j 广泛。

### Log4j

Log4j 是 apache 的一个开源项目，创始人 Ceki Gulcu。

Log4j 应该说是 Java 领域资格最老，应用最广的日志工具。从诞生之日到现在一直广受业界欢迎。

Log4j 是高度可配置的，并可通过在运行时的外部文件配置。它根据记录的优先级别，并提供机制，以指示记录信息到许多的目的地，诸如：数据库，文件，控制台，UNIX 系统日志等。

Log4j 中有三个主要组成部分：

- **loggers** - 负责捕获记录信息。
- **appenders** - 负责发布日志信息，以不同的首选目的地。
- **layouts** - 负责格式化不同风格的日志信息。

[官网地址](http://logging.apache.org/log4j/2.x/)

### Logback

Logback 是由 log4j 创始人 Ceki Gulcu 设计的又一个开源日记组件，目标是替代 log4j。

logback 当前分成三个模块：`logback-core`、`logback-classic` 和 `logback-access`。

- `logback-core` - 是其它两个模块的基础模块。
- `logback-classic` - 是 log4j 的一个 改良版本。此外 `logback-classic` 完整实现 SLF4J API 使你可以很方便地更换成其它日记系统如 log4j 或 JDK14 Logging。
- `logback-access` - 访问模块与 Servlet 容器集成提供通过 Http 来访问日记的功能。

[官网地址](http://logback.qos.ch/)

### Log4j2

[官网地址](http://logging.apache.org/log4j/2.x/)

按照官方的说法，Log4j2 是 Log4j 和 Logback 的替代。

Log4j2 架构：

<div align="center"><img src="http://dunwu.test.upcdn.net/cs/java/javalib/log/log4j2-architecture.jpg!zp"/></div>

### Log4j vs Logback vs Log4j2

按照官方的说法，**Log4j2 大大优于 Log4j 和 Logback。**

那么，Log4j2 相比于先问世的 Log4j 和 Logback，它具有哪些优势呢？

1. Log4j2 旨在用作审计日志记录框架。 Log4j 1.x 和 Logback 都会在重新配置时丢失事件。 Log4j 2 不会。在 Logback 中，Appender 中的异常永远不会对应用程序可见。在 Log4j 中，可以将 Appender 配置为允许异常渗透到应用程序。
2. Log4j2 在多线程场景中，[异步 Loggers](https://logging.apache.org/log4j/2.x/manual/async.html) 的吞吐量比 Log4j 1.x 和 Logback 高 10 倍，延迟低几个数量级。
3. Log4j2 对于独立应用程序是无垃圾的，对于稳定状态日志记录期间的 Web 应用程序来说是低垃圾。这减少了垃圾收集器的压力，并且可以提供更好的响应时间性能。
4. Log4j2 使用插件系统，通过添加新的 Appender、Filter、Layout、Lookup 和 Pattern Converter，可以非常轻松地扩展框架，而无需对 Log4j 进行任何更改。
5. 由于插件系统配置更简单。配置中的条目不需要指定类名。
6. 支持[自定义日志等级](https://logging.apache.org/log4j/2.x/manual/customloglevels.html)。
7. 支持 [lambda 表达式](https://logging.apache.org/log4j/2.x/manual/api.html#LambdaSupport)。
8. 支持[消息对象](https://logging.apache.org/log4j/2.x/manual/messages.html)。
9. Log4j 和 Logback 的 Layout 返回的是字符串，而 Log4j2 返回的是二进制数组，这使得它能被各种 Appender 使用。
10. Syslog Appender 支持 TCP 和 UDP 并且支持 BSD 系统日志。
11. Log4j2 利用 Java5 并发特性，尽量小粒度的使用锁，减少锁的开销。

## 日志门面

何谓日志门面？

日志门面是对不同日志框架提供的一个 API 封装，可以在部署的时候不修改任何配置即可接入一种日志实现方案。

### common-logging

common-logging 是 apache 的一个开源项目。也称**Jakarta Commons Logging，缩写 JCL**。

common-logging 的功能是提供日志功能的 API 接口，本身并不提供日志的具体实现（当然，common-logging 内部有一个 Simple logger 的简单实现，但是功能很弱，直接忽略），而是在**运行时**动态的绑定日志实现组件来工作（如 log4j、java.util.loggin）。

[官网地址](http://commons.apache.org/proper/commons-logging/)

### slf4j

全称为 Simple Logging Facade for Java，即 java 简单日志门面。

什么，作者又是 Ceki Gulcu！这位大神写了 Log4j、Logback 和 slf4j，专注日志组件开发五百年，一直只能超越自己。

类似于 Common-Logging，slf4j 是对不同日志框架提供的一个 API 封装，可以在部署的时候不修改任何配置即可接入一种日志实现方案。但是，slf4j 在**编译时**静态绑定真正的 Log 库。使用 SLF4J 时，如果你需要使用某一种日志实现，那么你必须选择正确的 SLF4J 的 jar 包的集合（各种桥接包）。

[官网地址](http://www.slf4j.org/)

<div align="center"><img src="http://dunwu.test.upcdn.net/cs/java/javalib/log/slf4j-to-other-log.png!zp"/></div>

### common-logging vs slf4j

slf4j 库类似于 Apache Common-Logging。但是，他在编译时静态绑定真正的日志库。这点似乎很麻烦，其实也不过是导入桥接 jar 包而已。

slf4j 一大亮点是提供了更方便的日志记录方式：

不需要使用`logger.isDebugEnabled()`来解决日志因为字符拼接产生的性能问题。slf4j 的方式是使用`{}`作为字符串替换符，形式如下：

```java
logger.debug("id: {}, name: {} ", id, name);
```

### 总结

综上所述，使用 slf4j + Logback 可谓是目前最理想的日志解决方案了。

接下来，就是如何在项目中实施了。

## 实施日志解决方案

使用日志解决方案基本可分为三步：

1.  引入 jar 包
2.  配置
3.  使用 API

常见的各种日志解决方案的第 2 步和第 3 步基本一样，实施上的差别主要在第 1 步，也就是使用不同的库。

### 引入 jar 包

这里首选推荐使用 slf4j + logback 的组合。

如果你习惯了 common-logging，可以选择 common-logging+log4j。

强烈建议不要直接使用日志实现组件(logback、log4j、java.util.logging)，理由前面也说过，就是无法灵活替换日志库。

还有一种情况：你的老项目使用了 common-logging，或是直接使用日志实现组件。如果修改老的代码，工作量太大，需要兼容处理。在下文，都将看到各种应对方法。

**_注：据我所知，当前仍没有方法可以将 slf4j 桥接到 common-logging。如果我孤陋寡闻了，请不吝赐教。_**

#### slf4j 直接绑定日志组件

**slf4j + logback**

添加依赖到 pom.xml 中即可。

_logback-classic-1.0.13.jar_ 会自动将 _slf4j-api-1.7.21.jar_ 和 _logback-core-1.0.13.jar_ 也添加到你的项目中。

```xml
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.0.13</version>
</dependency>
```

**slf4j + log4j**

添加依赖到 pom.xml 中即可。

_slf4j-log4j12-1.7.21.jar_ 会自动将 _slf4j-api-1.7.21.jar_ 和 _log4j-1.2.17.jar_ 也添加到你的项目中。

```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.21</version>
</dependency>
```

**slf4j + java.util.logging**

添加依赖到 pom.xml 中即可。

_slf4j-jdk14-1.7.21.jar_ 会自动将 _slf4j-api-1.7.21.jar_ 也添加到你的项目中。

```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-jdk14</artifactId>
  <version>1.7.21</version>
</dependency>
```

#### slf4j 兼容非 slf4j 日志组件

在介绍解决方案前，先提一个概念——桥接

**什么是桥接呢**

假如你正在开发应用程序所调用的组件当中已经使用了 common-logging，这时你需要 jcl-over-slf4j.jar 把日志信息输出重定向到 slf4j-api，slf4j-api 再去调用 slf4j 实际依赖的日志组件。这个过程称为桥接。下图是官方的 slf4j 桥接策略图：

<div align="center"><img src="http://dunwu.test.upcdn.net/cs/java/javalib/log/slf4j-bind-strategy.png!zp"/></div>

从图中应该可以看出，无论你的老项目中使用的是 common-logging 或是直接使用 log4j、java.util.logging，都可以使用对应的桥接 jar 包来解决兼容问题。

**slf4j 兼容 common-logging**

```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>jcl-over-slf4j</artifactId>
  <version>1.7.12</version>
</dependency>
```

**slf4j 兼容 log4j**

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>log4j-over-slf4j</artifactId>
    <version>1.7.12</version>
</dependency>
```

**slf4j 兼容 java.util.logging**

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
    <version>1.7.12</version>
</dependency>
```

#### spring 集成 slf4j

做 java web 开发，基本离不开 spring 框架。很遗憾，spring 使用的日志解决方案是 common-logging + log4j。

所以，你需要一个桥接 jar 包：_logback-ext-spring_。

```xml
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.1.3</version>
</dependency>
<dependency>
  <groupId>org.logback-extensions</groupId>
  <artifactId>logback-ext-spring</artifactId>
  <version>0.1.2</version>
</dependency>
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>jcl-over-slf4j</artifactId>
  <version>1.7.12</version>
</dependency>
```

#### common-logging 绑定日志组件

**common-logging + log4j**

添加依赖到 pom.xml 中即可。

```xml
<dependency>
  <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
  <version>1.2</version>
</dependency>
<dependency>
  <groupId>log4j</groupId>
  <artifactId>log4j</artifactId>
  <version>1.2.17</version>
</dependency>
```

### 使用 API

#### slf4j 用法

使用 slf4j 的 API 很简单。使用`LoggerFactory`初始化一个`Logger`实例，然后调用 Logger 对应的打印等级函数就行了。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        String msg = "print log, current level: {}";
        log.trace(msg, "trace");
        log.debug(msg, "debug");
        log.info(msg, "info");
        log.warn(msg, "warn");
        log.error(msg, "error");
    }
}
```

#### common-logging 用法

common-logging 用法和 slf4j 几乎一样，但是支持的打印等级多了一个更高级别的：**fatal**。

此外，common-logging 不支持`{}`替换参数，你只能选择拼接字符串这种方式了。

```java
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JclTest {
    private static final Log log = LogFactory.getLog(JclTest.class);

    public static void main(String[] args) {
        String msg = "print log, current level: ";
        log.trace(msg + "trace");
        log.debug(msg + "debug");
        log.info(msg + "info");
        log.warn(msg + "warn");
        log.error(msg + "error");
        log.fatal(msg + "fatal");
    }
}
```

## log4j2 配置

log4j2 基本配置形式如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>;
<Configuration>
  <Properties>
    <Property name="name1">value</property>
    <Property name="name2" value="value2"/>
  </Properties>
  <Filter type="type" ... />
  <Appenders>
    <Appender type="type" name="name">
      <Filter type="type" ... />
    </Appender>
    ...
  </Appenders>
  <Loggers>
    <Logger name="name1">
      <Filter type="type" ... />
    </Logger>
    ...
    <Root level="level">
      <AppenderRef ref="name"/>
    </Root>
  </Loggers>
</Configuration>
```

配置示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="debug" strict="true" name="XMLConfigTest"
               packages="org.apache.logging.log4j.test">
  <Properties>
    <Property name="filename">target/test.log</Property>
  </Properties>
  <Filter type="ThresholdFilter" level="trace"/>

  <Appenders>
    <Appender type="Console" name="STDOUT">
      <Layout type="PatternLayout" pattern="%m MDC%X%n"/>
      <Filters>
        <Filter type="MarkerFilter" marker="FLOW" onMatch="DENY" onMismatch="NEUTRAL"/>
        <Filter type="MarkerFilter" marker="EXCEPTION" onMatch="DENY" onMismatch="ACCEPT"/>
      </Filters>
    </Appender>
    <Appender type="Console" name="FLOW">
      <Layout type="PatternLayout" pattern="%C{1}.%M %m %ex%n"/><!-- class and line number -->
      <Filters>
        <Filter type="MarkerFilter" marker="FLOW" onMatch="ACCEPT" onMismatch="NEUTRAL"/>
        <Filter type="MarkerFilter" marker="EXCEPTION" onMatch="ACCEPT" onMismatch="DENY"/>
      </Filters>
    </Appender>
    <Appender type="File" name="File" fileName="${filename}">
      <Layout type="PatternLayout">
        <Pattern>%d %p %C{1.} [%t] %m%n</Pattern>
      </Layout>
    </Appender>
  </Appenders>

  <Loggers>
    <Logger name="org.apache.logging.log4j.test1" level="debug" additivity="false">
      <Filter type="ThreadContextMapFilter">
        <KeyValuePair key="test" value="123"/>
      </Filter>
      <AppenderRef ref="STDOUT"/>
    </Logger>

    <Logger name="org.apache.logging.log4j.test2" level="debug" additivity="false">
      <AppenderRef ref="File"/>
    </Logger>

    <Root level="trace">
      <AppenderRef ref="STDOUT"/>
    </Root>
  </Loggers>

</Configuration>
```

## logback 配置

### `<configuration>`

- 作用：`<configuration>` 是 logback 配置文件的根元素。
- 要点
  - 它有 `<appender>`、`<logger>`、`<root>` 三个子元素。

<div align="center"><img src="http://dunwu.test.upcdn.net/cs/java/javalib/log/logback-configuration.png!zp"/></div>

### `<appender>`

- 作用：将记录日志的任务委托给名为 appender 的组件。
- 要点
  - 可以配置零个或多个。
  - 它有 `<file>`、`<filter>`、`<layout>`、`<encoder>` 四个子元素。
- 属性
  - name：设置 appender 名称。
  - class：设置具体的实例化类。

#### `<file>`

- 作用：设置日志文件路径。

#### `<filter>`

- 作用：设置过滤器。
- 要点
  - 可以配置零个或多个。

#### `<layout>`

- 作用：设置 appender。
- 要点
  - 可以配置零个或一个。
- 属性
  - class：设置具体的实例化类。

#### `<encoder>`

- 作用：设置编码。
- 要点
  - 可以配置零个或多个。
- 属性
  - class：设置具体的实例化类。

<div align="center"><img src="http://dunwu.test.upcdn.net/cs/java/javalib/log/logback-appender.png!zp"/></div>

### `<logger>`

- 作用：设置 logger。
- 要点
  - 可以配置零个或多个。
- 属性
  - name
  - level：设置日志级别。不区分大小写。可选值：TRACE、DEBUG、INFO、WARN、ERROR、ALL、OFF。
  - additivity：可选值：true 或 false。

#### `<appender-ref>`

- 作用：appender 引用。
- 要点
  - 可以配置零个或多个。

### `<root>`

- 作用：设置根 logger。
- 要点
  - 只能配置一个。
  - 除了 level，不支持任何属性。level 属性和 `<logger>` 中的相同。
  - 有一个子元素 `<appender-ref>`，与 `<logger>` 中的相同。

### 完整的 logback.xml 参考示例

在下面的配置文件中，我为自己的项目代码（根目录：org.zp.notes.spring）设置了五种等级：

TRACE、DEBUG、INFO、WARN、ERROR，优先级依次从低到高。

因为关注 spring 框架本身的一些信息，我增加了专门打印 spring WARN 及以上等级的日志。

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!-- logback中一共有5种有效级别，分别是TRACE、DEBUG、INFO、WARN、ERROR，优先级依次从低到高 -->
<configuration scan="true" scanPeriod="60 seconds" debug="false">

  <property name="DIR_NAME" value="spring-helloworld"/>

  <!-- 将记录日志打印到控制台 -->
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <!-- RollingFileAppender begin -->
  <appender name="ALL" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/all.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>30MB</maxFileSize>
    </triggeringPolicy>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/error.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>10MB</maxFileSize>
    </triggeringPolicy>

    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>ERROR</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <appender name="WARN" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/warn.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>10MB</maxFileSize>
    </triggeringPolicy>

    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>WARN</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <appender name="INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/info.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>10MB</maxFileSize>
    </triggeringPolicy>

    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>INFO</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <appender name="DEBUG" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/debug.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>10MB</maxFileSize>
    </triggeringPolicy>

    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>DEBUG</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <appender name="TRACE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/trace.%d{yyyy-MM-dd}.log</fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>10MB</maxFileSize>
    </triggeringPolicy>

    <filter class="ch.qos.logback.classic.filter.LevelFilter">
      <level>TRACE</level>
      <onMatch>ACCEPT</onMatch>
      <onMismatch>DENY</onMismatch>
    </filter>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>

  <appender name="SPRING" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 根据时间来制定滚动策略 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>${user.dir}/logs/${DIR_NAME}/springframework.%d{yyyy-MM-dd}.log
      </fileNamePattern>
      <maxHistory>30</maxHistory>
    </rollingPolicy>

    <!-- 根据文件大小来制定滚动策略 -->
    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>10MB</maxFileSize>
    </triggeringPolicy>

    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] [%-5p] %c{36}.%M - %m%n</pattern>
    </encoder>
  </appender>
  <!-- RollingFileAppender end -->

  <!-- logger begin -->
  <!-- 本项目的日志记录，分级打印 -->
  <logger name="org.zp.notes.spring" level="TRACE" additivity="false">
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="ERROR"/>
    <appender-ref ref="WARN"/>
    <appender-ref ref="INFO"/>
    <appender-ref ref="DEBUG"/>
    <appender-ref ref="TRACE"/>
  </logger>

  <!-- SPRING框架日志 -->
  <logger name="org.springframework" level="WARN" additivity="false">
    <appender-ref ref="SPRING"/>
  </logger>

  <root level="TRACE">
    <appender-ref ref="ALL"/>
  </root>
  <!-- logger end -->

</configuration>
```

## log4j 配置

### 完整的 log4j.xml 参考示例

log4j 的配置文件一般有 xml 格式或 properties 格式。这里为了和 logback.xml 做个对比，就不介绍 properties 了，其实也没太大差别。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/'>

  <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
    <layout class="org.apache.log4j.PatternLayout">
      <param name="ConversionPattern"
             value="%d{yyyy-MM-dd HH:mm:ss,SSS\} [%-5p] [%t] %c{36\}.%M - %m%n"/>
    </layout>

    <!--过滤器设置输出的级别-->
    <filter class="org.apache.log4j.varia.LevelRangeFilter">
      <param name="levelMin" value="debug"/>
      <param name="levelMax" value="fatal"/>
      <param name="AcceptOnMatch" value="true"/>
    </filter>
  </appender>


  <appender name="ALL" class="org.apache.log4j.DailyRollingFileAppender">
    <param name="File" value="${user.dir}/logs/spring-common/jcl/all"/>
    <param name="Append" value="true"/>
    <!-- 每天重新生成日志文件 -->
    <param name="DatePattern" value="'-'yyyy-MM-dd'.log'"/>
    <!-- 每小时重新生成日志文件 -->
    <!--<param name="DatePattern" value="'-'yyyy-MM-dd-HH'.log'"/>-->
    <layout class="org.apache.log4j.PatternLayout">
      <param name="ConversionPattern"
             value="%d{yyyy-MM-dd HH:mm:ss,SSS\} [%-5p] [%t] %c{36\}.%M - %m%n"/>
    </layout>
  </appender>

  <!-- 指定logger的设置，additivity指示是否遵循缺省的继承机制-->
  <logger name="org.zp.notes.spring" additivity="false">
    <level value="error"/>
    <appender-ref ref="STDOUT"/>
    <appender-ref ref="ALL"/>
  </logger>

  <!-- 根logger的设置-->
  <root>
    <level value="warn"/>
    <appender-ref ref="STDOUT"/>
  </root>
</log4j:configuration>
```

## 参考

- [slf4 官方文档](http://www.slf4j.org/manual.html)
- [logback 官方文档](http://logback.qos.ch/)
- [log4j 官方文档](http://logging.apache.org/log4j/1.2/)
- [commons-logging 官方文档](http://commons.apache.org/proper/commons-logging/)
- http://blog.csdn.net/yycdaizi/article/details/8276265
