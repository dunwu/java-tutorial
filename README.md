<p align="center">
    <a href="https://dunwu.github.io/java-tutorial/" target="_blank" rel="noopener noreferrer">
        <img src="https://raw.githubusercontent.com/dunwu/images/dev/common/dunwu-logo-200.png" alt="logo" width="150px"/>
    </a>
</p>

<p align="center">
    <img src="https://badgen.net/github/license/dunwu/java-tutorial" alt="license">
    <img src="https://travis-ci.com/dunwu/java-tutorial.svg?branch=master" alt="build">
</p>

<h1 align="center">JavaTutorial</h1>

> ☕ **java-tutorial** 是一个 Java 教程，汇集一个老司机在 Java 领域的十年积累。
>
> - 🔁 项目同步维护：[Github](https://github.com/dunwu/java-tutorial/) | [Gitee](https://gitee.com/turnon/java-tutorial/)
> - 📖 电子书阅读：[Github Pages](https://dunwu.github.io/java-tutorial/) | [Gitee Pages](https://turnon.gitee.io/java-tutorial/)

## javacore

> 📚 [javacore](https://dunwu.github.io/javacore/) 是一个 Java 核心技术教程。内容包含：Java 基础特性、Java 高级特性、Java 并发、JVM、Java IO 等。

## javaee

> [☕ JavaEE](docs/javaee/README.md) 技术是 Java Web 的基石

- [JavaEE 面经](docs/javaee/javaee-interview.md)
- [JavaEE 之 Servlet 指南](docs/javaee/javaee-servlet.md)
- [JavaEE 之 Jsp 指南](docs/javaee/javaee-jsp.md)
- [JavaEE 之 Filter 和 Listener](docs/javaee/javaee-filter-listener.md)
- [JavaEE 之 Cookie 和 Session](docs/javaee/javaee-cookie-sesion.md)

## javatech

> 📚 [javatech](https://dunwu.github.io/javatech/) 是一个 Java 应用技术教程。内容包含 Java 开发中常见应用技术，如：框架、缓存、消息队列、存储、安全、微服务、测试、服务器等。

## spring-tutorial

> 📚 [spring-tutorial](https://dunwu.github.io/spring-tutorial/) 是一个 Spring 实战教程。

## spring-boot-tutorial

> 📚 [Spring Boot 教程](https://dunwu.github.io/spring-boot-tutorial/) 是一个 Spring Boot 实战教程。

## javatool

### 构建

> Java 项目需要通过 [**构建工具**](docs/javatool/build) 来管理项目依赖，完成编译、打包、发布、生成 JavaDoc 等任务。
>
> - 目前最主流的构建工具是 Maven，它的功能非常强大。
> - Gradle 号称是要替代 Maven 等构件工具，它的版本管理确实简洁，但是需要学习 Groovy，学习成本比 Maven 高。
> - Ant 功能比 Maven 和 Gradle 要弱，现代 Java 项目基本不用了，但也有一些传统的 Java 项目还在使用。

- [Maven](docs/javatool/build/maven) 📚
  - [Maven 入门指南](docs/javatool/build/maven/maven-quickstart.md)
  - [Maven 教程之 pom.xml 详解](docs/javatool/build/maven/maven-pom.md)
  - [Maven 教程之 settings.xml 详解](docs/javatool/build/maven/maven-settings.md)
  - [Maven 实战问题和最佳实践](docs/javatool/build/maven/maven-action.md)
  - [Maven 教程之发布 jar 到私服或中央仓库](docs/javatool/build/maven/maven-deploy.md)
  - [Maven 插件之代码检查](docs/javatool/build/maven/maven-checkstyle-plugin.md)
- [Ant 简易教程](docs/javatool/build/ant.md)

### IDE

> 自从有了 [**IDE**](docs/javatool/ide)，写代码从此就告别了刀耕火种的蛮荒时代。
>
> - [Eclipse](docs/javatool/ide/eclipse.md) 是久负盛名的开源 Java IDE，我的学生时代一直使用它写 Java。
> - 曾经抗拒从转 [Intellij Idea](docs/javatool/ide/intellij-idea.md) ，但后来发现真香，不得不说，确实是目前最优秀的 Java IDE。
> - 你可以在 [vscode](docs/javatool/ide/vscode.md) 中写各种语言，只要安装相应插件即可。如果你的项目中使用了很多种编程语言，又懒得在多个 IDE 之间切换，那么就用 vscode 来一网打尽吧。

- [Intellij Idea](docs/javatool/ide/intellij-idea.md)
- [Eclipse](docs/javatool/ide/eclipse.md)
- [vscode](docs/javatool/ide/vscode.md)

### 监控/诊断

> [监控/诊断](docs/javatool/monitor) 工具主要用于 Java 应用的运维。通过采集、分析、存储、可视化应用的有效数据，帮助开发者、使用者快速定位问题，找到性能瓶颈。

- [监控工具对比](docs/javatool/monitor/monitor-summary.md)
- [CAT](docs/javatool/monitor/cat.md)
- [Zipkin](docs/javatool/monitor/zipkin.md)
- [SkyWalking](docs/javatool/monitor/skywalking.md)
- [Arthas](docs/javatool/monitor/arthas.md)

---

## 其他技术栈

- [db-tutorial](https://dunwu.github.io/db-tutorial/) - 是对数据库领域开发经验的总结。内容包含：关系型数据库和 Nosql 理论、Mysql、Redis 等。
- [algorithm-tutorial](https://dunwu.github.io/algorithm-tutorial/) - 是对数据结构和算法的总结。内容包含：一些基本的数据结构、算法。
- [linux-tutorial](https://github.com/dunwu/linux-tutorial) - 是对 Linux 操作系统的经验总结。内容包含：Linux 常用命令；各种常见软件的 Linux 环境安装配置；运维、部署脚本；Shell、Python 语法教程；Git、Docker 教程。
- [frontend-tutorial](https://github.com/dunwu/frontend-tutorial) - 前端教程

---

## 学习资源

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
