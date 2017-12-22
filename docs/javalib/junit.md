---
title: JUnit 使用小结
date: 2017/8/18
categories:
- javalib
tags:
- java
- javalib
- test
---

# JUnit 使用小结

## 安装

在 pom 中添加依赖

```
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.9</version>
  <scope>test</scope>
</dependency>
```

## JUnit 注解

JUnit 4 开始使用 Java 5 中的注解（annotation），常用的几个 annotation 介绍：

`@BeforeClass`：针对所有测试，只执行一次，且必须为static void

`@Before`：初始化方法

`@Test`：测试方法，在这里可以测试期望异常和超时时间

`@After`：释放资源

`@AfterClass`：针对所有测试，只执行一次，且必须为static void

`@Ignore`：忽略的测试方法

一个单元测试用例执行顺序为：

@BeforeClass –> @Before –> @Test –> @After –> @AfterClass
