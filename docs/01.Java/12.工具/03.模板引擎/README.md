---
title: Java 模板引擎
date: 2022-02-17 22:34:30
categories:
  - Java
  - 工具
  - 模板引擎
tags:
  - Java
  - 模板引擎
permalink: /pages/9d37fa/
hidden: true
index: false
---

# Java 模板引擎

模板引擎不属于特定技术领域，它是跨领域跨平台的概念。 模板引擎的作用就是分离业务数据和最终呈现内容，它可以生成特定格式的文档（模板） 。

模板引擎简单来说，就是：**_`模板 + 数据模型 = 输出`_**

较早，也比较经典的模板引擎是 JavaEE 的标准技术 JSP。

但 JSP 存在以下缺点，导致逐渐被淘汰：

- **性能差**
  - JSP 本质上是 Servlet，第一次请求 JSP 页面，必须要在 web 服务器中编译成 servlet，所以第一次响应较慢。
  - 每次请求 JSP 都是访问 servlet 再用输出流输出的 html 页面。
  - JSP 中的内容很多，页面响应会很慢，因为是同步加载。
- **无法前后端分离**
  - 动态资源和静态资源全部耦合在一起，无法做到前后端分离。一旦服务器出现状况，前后台一起玩完。
  - 而且 Java 工程师既当爹又当妈，又要维护 Java 代码，又要维护 JSP 代码，痛苦。
  - 前端工程师如果不理解 JSP 语法，面对各种 JSP 标签、表达式、指令，会一脸懵逼，痛苦。
- **不是所有服务器都支持** - JSP 必须要在支持 JSP 技术的 web 服务器里运行（如 Tomcat）。但有些服务器则不支持 JSP ，如 Nginx。

在 Java 领域，目前最常见的模板引擎就是：

- Freemark
- Thymeleaf
- Velocity

## 内容

- [Freemark](01.Freemark.md)
- [Thymeleaf](02.Thymeleaf.md)
- [Velocity](03.Velocity.md)

## 资源

- **Freemark**
  - [Freemark Github](https://github.com/apache/freemarker/)
  - [Freemark 中文教程](http://freemarker.foofun.cn/)
  - [在线 Freemark 工具](https://try.freemarker.apache.org/)
- **Velocity**
  - [Velocity Github](https://github.com/apache/velocity-engine/)
  - [Velocity 官网](https://velocity.apache.org/)
  - [Velocity 中文文档](https://wizardforcel.gitbooks.io/velocity-doc/content/)
  - [velocity-spring-boot-project](https://github.com/alibaba/velocity-spring-boot-project)