---
title: 细说 Java 主流日志工具库
date: 2016/10/14
categories:
- javalib
tags:
- javalib
- log
---

# 细说 Java 主流日志工具库

## 概述

在项目开发中，为了跟踪代码的运行情况，常常要使用日志来记录信息。

在Java世界，有很多的日志工具库来实现日志功能，避免了我们重复造轮子。

我们先来逐一了解一下主流日志工具。

### java.util.logging (JUL)

JDK1.4开始，通过`java.util.logging`提供日志功能。

它能满足基本的日志需要，但是功能没有Log4j强大，而且使用范围也没有Log4j广泛。

### Log4j

Log4j是apache的一个开源项目，创始人Ceki Gulcu。

Log4j应该说是Java领域资格最老，应用最广的日志工具。从诞生之日到现在一直广受业界欢迎。

Log4j是高度可配置的，并可通过在运行时的外部文件配置。它根据记录的优先级别，并提供机制，以指示记录信息到许多的目的地，诸如：数据库，文件，控制台，UNIX系统日志等。

Log4j中有三个主要组成部分：

- **loggers:** 负责捕获记录信息。
- **appenders :** 负责发布日志信息，以不同的首选目的地。
- **layouts:** 负责格式化不同风格的日志信息。

[官网地址](http://logging.apache.org/log4j/2.x/)

### Logback

Logback是由log4j创始人Ceki Gulcu设计的又一个开源日记组件，目标是替代log4j。

logback当前分成三个模块：`logback-core`,`logback- classic`和`logback-access`。

`logback-core`是其它两个模块的基础模块。

`logback-classic`是log4j的一个 改良版本。此外`logback-classic`完整实现SLF4J API使你可以很方便地更换成其它日记系统如log4j或JDK14 Logging。

`logback-access`访问模块与Servlet容器集成提供通过Http来访问日记的功能。

[官网地址](http://logback.qos.ch/)

### Log4j vs Logback

Logback相比Log4j具有许多好处：

**性能提升**

logback在log4j基础上做了优化，使性能提高了近10倍。此外，内存开销也减少了。

**更充足的测试**

尽管log4j也做了测试，但是logback的测试更加充分。所以，logback应该更加稳定。

**天然支持slf4j**

因为Logback-classic完全实现了slf4j的接口，所以天然支持slf4j。使用slf4j，有利于你切换日志工具库，减少工作量。

**自动重载配置文件**

Logback-classic可以自动重载更新过的配置文件。

**自动移除旧日志**

通过配置文件最大数和过期时间，Logback可以控制日志文件数并自动清除过期的日志。

**更灵活、更精细的配置**

Logback在配置中提供更加丰富的功能来帮助你更加精细的去定制你的日志组件：

`<filter>`提供比log4j更丰富的过滤条件；

增加`<if>`, `<then>` 和 `<else>`这样的条件控制;

**打印异常的调用栈信息**

Logback在打印异常时，会打印调用栈的包装数据。

**Logback-access**

Logback-access支持Logback-classic的所有特性，并且它可以提供丰富的HTTP-access日志功能。

**总结**

以上优点摘自官方推荐理由：[Reasons to prefer logback over log4j](http://logback.qos.ch/reasonsToSwitch.html)。

由于Logback的作者也是Log4j的作者，所有推荐理由应该比较靠谱。

总之，相比于Log4j，好处多多，你心动了没？

### common-logging

common-logging是apache的一个开源项目。也称**Jakarta Commons Logging，缩写JCL**。

common-logging的功能是提供日志功能的API接口，本身并不提供日志的具体实现（当然，common-logging内部有一个Simple logger的简单实现，但是功能很弱，直接忽略），而是在**运行时**动态的绑定日志实现组件来工作（如log4j、java.util.loggin）。

[官网地址](http://commons.apache.org/proper/commons-logging/)

### slf4j

全称为Simple Logging Facade for Java，即java简单日志门面。

什么，作者又是Ceki Gulcu！这位大神写了Log4j、Logback和slf4j，专注日志组件开发五百年，一直只能超越自己。

类似于Common-Logging，slf4j是对不同日志框架提供的一个API封装，可以在部署的时候不修改任何配置即可接入一种日志实现方案。但是，slf4j在**编译时**静态绑定真正的Log库。使用SLF4J时，如果你需要使用某一种日志实现，那么你必须选择正确的SLF4J的jar包的集合（各种桥接包）。

[官网地址](http://www.slf4j.org/)

![slf4j工作模型](http://oyz7npk35.bkt.clouddn.com//image/java/libs/log/slf4j-to-other-log.png)

### common-logging vs slf4j

slf4j库类似于Apache Common-Logging。但是，他在编译时静态绑定真正的日志库。这点似乎很麻烦，其实也不过是导入桥接jar包而已。

slf4j一大亮点是提供了更方便的日志记录方式：

不需要使用`logger.isDebugEnabled()`来解决日志因为字符拼接产生的性能问题。slf4j的方式是使用`{}`作为字符串替换符，形式如下：

```
logger.debug("id: {}, name: {} ", id, name);
```

### 总结

综上所述，使用slf4j + Logback可谓是目前最理想的日志解决方案了。

接下来，就是如何在项目中实施了。

## 实施日志解决方案

使用日志解决方案基本可分为三步：

1. 引入jar包
2. 配置
3. 使用API

常见的各种日志解决方案的第2步和第3步基本一样，实施上的差别主要在第1步，也就是使用不同的库。

### 引入jar包

这里首选推荐使用slf4j + logback 的组合。

如果你习惯了common-logging，可以选择common-logging+log4j。

强烈建议不要直接使用日志实现组件(logback、log4j、java.util.logging)，理由前面也说过，就是无法灵活替换日志库。

还有一种情况：你的老项目使用了common-logging，或是直接使用日志实现组件。如果修改老的代码，工作量太大，需要兼容处理。在下文，都将看到各种应对方法。

***注：据我所知，当前仍没有方法可以将slf4j桥接到common-logging。如果我孤陋寡闻了，请不吝赐教。***

#### slf4j直接绑定日志组件

**slf4j + logback**

添加依赖到pom.xml中即可。

*logback-classic-1.0.13.jar* 会自动将 *slf4j-api-1.7.21.jar* 和 *logback-core-1.0.13.jar* 也添加到你的项目中。

```xml
<dependency> 
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.0.13</version>
</dependency>
```

**slf4j + log4j**

添加依赖到pom.xml中即可。

*slf4j-log4j12-1.7.21.jar* 会自动将 *slf4j-api-1.7.21.jar* 和 *log4j-1.2.17.jar* 也添加到你的项目中。

```xml
<dependency> 
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.21</version>
</dependency>
```

**slf4j + java.util.logging**

添加依赖到pom.xml中即可。

*slf4j-jdk14-1.7.21.jar* 会自动将 *slf4j-api-1.7.21.jar* 也添加到你的项目中。

```xml
<dependency> 
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-jdk14</artifactId>
  <version>1.7.21</version>
</dependency>
```

#### slf4j兼容非slf4j日志组件

在介绍解决方案前，先提一个概念——桥接

**什么是桥接呢**

假如你正在开发应用程序所调用的组件当中已经使用了common-logging，这时你需要 jcl-over-slf4j.jar把日志信息输出重定向到 slf4j-api，slf4j-api再去调用slf4j实际依赖的日志组件。这个过程称为桥接。
下图是官方的slf4j桥接策略图：

![slf4j桥接策略](http://oyz7npk35.bkt.clouddn.com//image/java/libs/log/slf4j-bind-strategy.png)

从图中应该可以看出，无论你的老项目中使用的是common-logging或是直接使用log4j、java.util.logging，都可以使用对应的桥接jar包来解决兼容问题。

**slf4j兼容common-logging**

```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>jcl-over-slf4j</artifactId>
  <version>1.7.12</version>
</dependency>
```

**slf4j兼容log4j**

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>log4j-over-slf4j</artifactId>
    <version>1.7.12</version>
</dependency>
```

**slf4j兼容java.util.logging**

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
    <version>1.7.12</version>
</dependency>
```

#### spring 集成 slf4j

做java web开发，基本离不开spring框架。很遗憾，spring使用的日志解决方案是common-logging + log4j。

所以，你需要一个桥接jar包：*logback-ext-spring*。

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

#### common-logging绑定日志组件

**common-logging + log4j**

添加依赖到pom.xml中即可。

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

### 配置

日志配置文件大同小异，需要注意的是：**logback.xml和log4j.xml都需要放在classpath路径下**。

#### 完整的logback.xml参考示例

在下面的配置文件中，我为自己的项目代码（根目录：org.zp.notes.spring）设置了五种等级：

TRACE、DEBUG、INFO、WARN、ERROR，优先级依次从低到高。

因为关注spring框架本身的一些信息，我增加了专门打印spring WARN及以上等级的日志。

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

#### 完整的log4j.xml参考示例

log4j的配置文件一般有xml格式或properties格式。这里为了和logback.xml做个对比，就不介绍properties了，其实也没太大差别。

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

#### logback配置参数说明

logback基本兼容log4j的配置，并提供更多的功能。

这里奉献一张本人整理的logback配置思维导图，高清无码。

![logback配置](http://oyz7npk35.bkt.clouddn.com//image/java/libs/log/popular-logs-mind.png)

### 使用API

#### slf4j用法

使用slf4j的API很简单。使用`LoggerFactory`初始化一个`Logger`实例，然后调用Logger对应的打印等级函数就行了。

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

#### common-logging用法

common-logging用法和slf4j几乎一样，但是支持的打印等级多了一个更高级别的：**fatal**。

此外，common-logging不支持`{}`替换参数，你只能选择拼接字符串这种方式了。

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

## 参考

- [slf4官方文档](http://www.slf4j.org/manual.html)
- [logback官方文档](http://logback.qos.ch/)
- [log4j官方文档](http://logging.apache.org/log4j/1.2/)
- [commons-logging官方文档](http://commons.apache.org/proper/commons-logging/)
- http://blog.csdn.net/yycdaizi/article/details/8276265
