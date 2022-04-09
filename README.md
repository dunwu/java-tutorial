<p align="center">
    <a href="https://dunwu.github.io/java-tutorial/" target="_blank" rel="noopener noreferrer">
        <img src="https://raw.githubusercontent.com/dunwu/images/dev/common/dunwu-logo-200.png" alt="logo" width="150px"/>
    </a>
</p>

<p align="center">

  <a href="https://github.com/dunwu/java-tutorial">
      <img alt="star" class="no-zoom" src="https://img.shields.io/github/stars/dunwu/java-tutorial?style=for-the-badge">
  </a>

  <a href="https://github.com/dunwu/java-tutorial">
      <img alt="fork" class="no-zoom" src="https://img.shields.io/github/forks/dunwu/java-tutorial?style=for-the-badge">
  </a>

  <a href="https://github.com/dunwu/java-tutorial/commits/master">
      <img alt="commit" class="no-zoom" src="https://img.shields.io/github/workflow/status/dunwu/java-tutorial/CI?style=for-the-badge">
  </a>

  <a href="https://www.apache.org/licenses/LICENSE-2.0">
      <img alt="code style" class="no-zoom" src="https://img.shields.io/github/license/dunwu/java-tutorial?style=for-the-badge">
  </a>

</p>

<h1 align="center">JavaTutorial</h1>

> ☕ **java-tutorial** 是一个 Java 教程，汇集一个老司机在 Java 领域的十年积累。
>
> - 🔁 项目同步维护：[Github](https://github.com/dunwu/java-tutorial/) | [Gitee](https://gitee.com/turnon/java-tutorial/)
> - 📖 电子书阅读：[Github Pages](https://dunwu.github.io/java-tutorial/) | [Gitee Pages](https://turnon.gitee.io/java-tutorial/)
>
> 说明：
>
> - 下面的内容清单中，凡是有 📚 标记的技术，都已整理成详细的教程。
> - 部分技术因为可以应用于不同领域，所以可能会同时出现在不同的类别下。

## 📖 内容

### JavaSE

> 📚 [javacore](https://dunwu.github.io/javacore/) 是一个 Java 核心技术教程。内容包含：Java 基础特性、Java 高级特性、Java 并发、JVM、Java IO 等。

### JavaEE

> [☕ JavaEE](docs/01.JavaEE/README.md) 技术是 Java Web 的基石

- [JavaEE 面经](docs/01.JavaEE/01.JavaEE面经.md)
- [JavaEE 之 Servlet 指南](docs/01.JavaEE/02.JavaEE之Servlet指南.md)
- [JavaEE 之 Jsp 指南](docs/01.JavaEE/03.JavaEE之Jsp指南.md)
- [JavaEE 之 Filter 和 Listener](docs/01.JavaEE/04.JavaEE之Filter和Listener.md)
- [JavaEE 之 Cookie 和 Session](docs/01.JavaEE/05.JavaEE之Cookie和Session.md)

### Java 软件

#### Java 构建

> Java 项目需要通过 [**构建工具**](docs/02.Java软件/01.Java构建) 来管理项目依赖，完成编译、打包、发布、生成 JavaDoc 等任务。
>
> - 目前最主流的构建工具是 Maven，它的功能非常强大。
> - Gradle 号称是要替代 Maven 等构件工具，它的版本管理确实简洁，但是需要学习 Groovy，学习成本比 Maven 高。
> - Ant 功能比 Maven 和 Gradle 要弱，现代 Java 项目基本不用了，但也有一些传统的 Java 项目还在使用。

- [Maven](docs/02.Java软件/01.Java构建/01.Maven) 📚
  - [Maven 入门指南](docs/02.Java软件/01.Java构建/01.Maven/01.Maven入门指南.md)
  - [Maven 教程之 pom.xml 详解](docs/02.Java软件/01.Java构建/01.Maven/02.Maven教程之pom.xml详解.md)
  - [Maven 教程之 settings.xml 详解](docs/02.Java软件/01.Java构建/01.Maven/03.Maven教程之settings.xml详解.md)
  - [Maven 实战问题和最佳实践](docs/02.Java软件/01.Java构建/01.Maven/04.Maven实战问题和最佳实践.md)
  - [Maven 教程之发布 jar 到私服或中央仓库](docs/02.Java软件/01.Java构建/01.Maven/05.Maven教程之发布jar到私服或中央仓库.md)
  - [Maven 插件之代码检查](docs/02.Java软件/01.Java构建/01.Maven/06.Maven插件之代码检查.md)
- [Ant 简易教程](docs/02.Java软件/01.Java构建/02.Ant.md)

#### Java IDE

> 自从有了 [**IDE**](docs/02.Java软件/02.JavaIDE)，写代码从此就告别了刀耕火种的蛮荒时代。
>
> - [Eclipse](docs/02.Java软件/02.JavaIDE/02.Eclipse.md) 是久负盛名的开源 Java IDE，我的学生时代一直使用它写 Java。
> - 曾经抗拒从转 [Intellij Idea](docs/02.Java软件/02.JavaIDE/01.Intellij.md) ，但后来发现真香，不得不说，确实是目前最优秀的 Java IDE。
> - 你可以在 [vscode](docs/02.Java软件/02.JavaIDE/03.VsCode.md) 中写各种语言，只要安装相应插件即可。如果你的项目中使用了很多种编程语言，又懒得在多个 IDE 之间切换，那么就用 vscode 来一网打尽吧。

- [Intellij Idea](docs/02.Java软件/02.JavaIDE/01.Intellij.md)
- [Eclipse](docs/02.Java软件/02.JavaIDE/02.Eclipse.md)
- [vscode](docs/02.Java软件/02.JavaIDE/03.VsCode.md)

#### Java 服务器

> Tomcat 和 Jetty 都是 Java 比较流行的轻量级服务器。
>
> Nginx 是目前最流行的反向代理服务器，也常用于负载均衡。

- [Tomcat 应用指南](docs/02.Java软件/03.Java服务器/01.Tomcat/01.Tomcat应用指南.md)
- [Tomcat 连接器](docs/02.Java软件/03.Java服务器/01.Tomcat/02.Tomcat连接器.md)
- [Tomcat 容器](docs/02.Java软件/03.Java服务器/01.Tomcat/03.Tomcat容器.md)
- [Tomcat 优化](docs/02.Java软件/03.Java服务器/01.Tomcat/04.Tomcat优化.md)
- [Tomcat 和 Jetty](docs/02.Java软件/03.Java服务器/01.Tomcat/05.Tomcat和Jetty.md)
- [Jetty](docs/02.Java软件/03.Java服务器/02.Jetty.md)

#### Java 监控诊断

> [监控/诊断](docs/02.Java软件/04.Java监控诊断) 工具主要用于 Java 应用的运维。通过采集、分析、存储、可视化应用的有效数据，帮助开发者、使用者快速定位问题，找到性能瓶颈。

- [监控工具对比](docs/02.Java软件/04.Java监控诊断/01.监控诊断工具.md)
- [CAT](docs/02.Java软件/04.Java监控诊断/02.CAT.md)
- [Zipkin](docs/02.Java软件/04.Java监控诊断/03.Zipkin.md)
- [SkyWalking](docs/02.Java软件/04.Java监控诊断/04.Skywalking.md)
- [Arthas](docs/02.Java软件/04.Java监控诊断/05.Arthas.md)

### Java 工具

#### Java 序列化工具

- [JSON 序列化](docs/03.Java工具/01.Java序列化工具/01.JSON序列化.md) - [fastjson](https://github.com/alibaba/fastjson)、[Jackson](https://github.com/FasterXML/jackson)、[Gson](https://github.com/google/gson)
- [二进制序列化](docs/03.Java工具/01.Java序列化工具/02.二进制序列化.md) - [Protobuf](https://developers.google.com/protocol-buffers)、[Thrift](https://thrift.apache.org/)、[Hessian](http://hessian.caucho.com/)、[Kryo](https://github.com/EsotericSoftware/kryo)、[FST](https://github.com/RuedigerMoeller/fast-serialization)

#### JavaBean 工具

- [Lombok](docs/03.Java工具/02.JavaBean工具/01.Lombok.md)
- [Dozer](docs/03.Java工具/02.JavaBean工具/02.Dozer.md)

#### Java 模板引擎

- [Freemark](docs/03.Java工具/03.Java模板引擎/01.Freemark.md)
- [Velocity](docs/03.Java工具/03.Java模板引擎/02.Thymeleaf.md)
- [Thymeleaf](docs/03.Java工具/03.Java模板引擎/03.Velocity.md)

#### Java 测试工具

- [Freemark](docs/03.Java工具/04.Java测试工具/01.Junit.md)
- [Velocity](docs/03.Java工具/04.Java测试工具/02.Mockito.md)
- [Thymeleaf](docs/03.Java工具/04.Java测试工具/03.Jmeter.md)
- [Thymeleaf](docs/03.Java工具/04.Java测试工具/04.JMH.md)

#### 其他

- [Java 日志](docs/03.Java工具/05.其他Java工具/01.Java日志.md)
- [Java 工具包](docs/03.Java工具/05.其他Java工具/02.Java工具包.md)
- [Java 反射工具](docs/03.Java工具/05.其他Java工具/03.Java反射工具.md)
- [JavaMail](docs/03.Java工具/05.其他Java工具/04.JavaMail.md)
- [Jsoup 应用](docs/03.Java工具/05.其他Java工具/05.Jsoup应用.md)
- [Thumbnailator 应用](docs/03.Java工具/05.其他Java工具/06.Thumbnailator应用.md)
- [Zxing 应用](docs/03.Java工具/05.其他Java工具/07.Zxing应用.md)

### Java 框架

#### Spring

📚 [spring-tutorial](https://dunwu.github.io/spring-tutorial/) 是一个 Spring 实战教程。

#### Spring Boot

📚 [Spring Boot 教程](https://dunwu.github.io/spring-boot-tutorial/) 是一个 Spring Boot 实战教程。

#### ORM

- [Mybatis 应用指南](docs/04.Java框架/01.ORM/01.Mybatis应用指南.md)
- [Mybatis 原理](docs/04.Java框架/01.ORM/02.Mybatis原理.md)

#### 安全

> Java 领域比较流行的安全框架就是 shiro 和 spring-security。
>
> shiro 更为简单、轻便，容易理解，能满足大多数基本安全场景下的需要。
>
> spring-security 功能更丰富，也比 shiro 更复杂。值得一提的是由于 spring-security 是 spring 团队开发，所以集成 spring 和 spring-boot 框架更容易。

- [Shiro](docs/04.Java框架/02.安全/01.Shiro.md)
- [SpringSecurity](docs/04.Java框架/02.安全/02.SpringSecurity.md)

#### IO

- [Shiro](docs/04.Java框架/03.IO/01.Netty.md)

### Java 中间件

#### 消息队列

> 消息队列（Message Queue，简称 MQ）技术是分布式应用间交换信息的一种技术。
>
> 消息队列主要解决应用耦合，异步消息，流量削锋等问题，实现高性能，高可用，可伸缩和最终一致性架构。是大型分布式系统不可缺少的中间件。
>
> 如果想深入学习各种消息队列产品，建议先了解一下 [消息队列基本原理](https://github.com/dunwu/blog/blob/master/source/_posts/theory/mq.md) ，有助于理解消息队列特性的实现和设计思路。

- [消息队列面试](docs/05.Java中间件/01.消息队列/01.消息队列面试.md)
- [消息队列基本原理](docs/05.Java中间件/01.消息队列/02.消息队列基本原理.md)
- [RocketMQ](docs/05.Java中间件/01.消息队列/03.RocketMQ.md)
- [ActiveMQ](docs/05.Java中间件/01.消息队列/04.ActiveMQ.md)

#### 缓存

> 缓存可以说是优化系统性能的第一手段，在各种技术中都会有缓存的应用。
>
> 如果想深入学习缓存，建议先了解一下 [缓存基本原理](https://dunwu.github.io/design/distributed/分布式缓存.html)，有助于理解缓存的特性、原理，使用缓存常见的问题及解决方案。

- [缓存面试题](docs/05.Java中间件/02.缓存/01.缓存面试题.md)
- [Java 缓存框架](docs/05.Java中间件/02.缓存/02.Java缓存框架.md)
- [Memcached 应用指南](docs/05.Java中间件/02.缓存/03.Memcached应用指南.md)
- [Ehcache 应用指南](docs/05.Java中间件/02.缓存/04.Ehcache应用指南.md)
- [Java 缓存库](docs/05.Java中间件/02.缓存/05.Java缓存库.md)
- [Http 缓存](docs/05.Java中间件/02.缓存/06.Http缓存.md)

#### 流量控制

- [Hystrix](docs/05.Java中间件/03.流量控制/01.Hystrix.md)

#### 微服务

- [Dubbo](docs/05.Java中间件/04.微服务/01.Dubbo.md)

### [大数据](https://dunwu.github.io/bigdata-tutorial)

> 大数据技术点以归档在：[bigdata-tutorial](https://dunwu.github.io/bigdata-tutorial)

- [Hdfs](https://dunwu.github.io/bigdata-tutorial/hdfs) 📚
- [Hbase](https://dunwu.github.io/bigdata-tutorial/hbase) 📚
- [Hive](https://dunwu.github.io/bigdata-tutorial/hive) 📚
- [MapReduce](https://dunwu.github.io/bigdata-tutorial/mapreduce)
- [Yarn](https://dunwu.github.io/bigdata-tutorial/yarn)
- [ZooKeeper](https://dunwu.github.io/bigdata-tutorial/zookeeper) 📚
- [Kafka](https://dunwu.github.io/bigdata-tutorial/kafka) 📚
- Spark
- Storm
- [Flink](https://dunwu.github.io/bigdata-tutorial/tree/master/docs/flink)

## 📚 资料

- Java 经典书籍
  - [《Effective Java 中文版》](https://item.jd.com/12507084.html) - 本书介绍了在 Java 编程中 78 条极具实用价值的经验规则，这些经验规则涵盖了大多数开发人员每天所面临的问题的解决方案。同推荐《重构 : 改善既有代码的设计》、《代码整洁之道》、《代码大全》，有一定的内容重叠。
  - [《Java 并发编程实战》](https://item.jd.com/10922250.html) - 本书深入浅出地介绍了 Java 线程和并发，是一本完美的 Java 并发参考手册。
  - [《深入理解 Java 虚拟机》](https://item.jd.com/11252778.html) - 不去了解 JVM 的工程师，和咸鱼有什么区
  - [《Maven 实战》](https://item.jd.com/10476794.html) - 国内最权威的 Maven 专家的力作，唯一一本哦！
- 其他领域书籍
  - [《Redis 设计与实现》](https://item.jd.com/11486101.html) - 系统而全面地描述了 Redis 内部运行机制。图示丰富，描述清晰，并给出大量参考信息，是 NoSQL 数据库开发人员案头必备。
  - [《鸟哥的 Linux 私房菜 （基础学习篇）》](https://item.jd.com/12443890.html) - 本书是最具知名度的 Linux 入门书《鸟哥的 Linux 私房菜基础学习篇》的最新版，全面而详细地介绍了 Linux 操作系统。内容非常全面，建议挑选和自己实际工作相关度较高的，其他部分有需要再阅读。
  - [《Head First 设计模式》](https://item.jd.com/10100236.html) - 《Head First 设计模式》(中文版)共有 14 章，每章都介绍了几个设计模式，完整地涵盖了四人组版本全部 23 个设计模式。
  - [《HTTP 权威指南》](https://item.jd.com/11056556.html) - 本书尝试着将 HTTP 中一些互相关联且常被误解的规则梳理清楚，并编写了一系列基于各种主题的章节，对 HTTP 各方面的特性进行了介绍。纵观全书，对 HTTP“为什么”这样做进行了详细的解释，而不仅仅停留在它是“怎么做”的。
  - [《TCP/IP 详解 系列》](https://item.jd.com/11966296.html) - 完整而详细的 TCP/IP 协议指南。针对任何希望理解 TCP/IP 协议是如何实现的读者设计。
  - [《剑指 Offer：名企面试官精讲典型编程题》](https://item.jd.com/12163054.html) - 剖析了 80 个典型的编程面试题，系统整理基础知识、代码质量、解题思路、优化效率和综合能力这 5 个面试要点。

## 🚪 传送

◾ 🏠 [JAVA-TUTORIAL 首页](https://github.com/dunwu/java-tutorial) ◾ 🎯 [我的博客](https://github.com/dunwu/blog) ◾

> 你可能会感兴趣：

- [Java 教程](https://github.com/dunwu/java-tutorial) 📚
- [JavaCore 教程](https://dunwu.github.io/javacore/) 📚
- [Spring 教程](https://dunwu.github.io/spring-tutorial/) 📚
- [Spring Boot 教程](https://dunwu.github.io/spring-boot-tutorial/) 📚
- [数据库教程](https://dunwu.github.io/db-tutorial/) 📚
- [数据结构和算法教程](https://dunwu.github.io/algorithm-tutorial/) 📚
- [Linux 教程](https://dunwu.github.io/linux-tutorial/) 📚
- [Nginx 教程](https://github.com/dunwu/nginx-tutorial/) 📚
