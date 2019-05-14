# Maven 实战问题和最佳实践

<!-- TOC depthFrom:2 depthTo:3 -->

- [1. 常见问题](#1-常见问题)
    - [1.1. 去哪儿找 maven dependency ？](#11-去哪儿找-maven-dependency-)
    - [1.2. 如何指定编码？](#12-如何指定编码)
    - [1.3. 如何指定 JDK 版本？](#13-如何指定-jdk-版本)
    - [1.4. 如何避免将 dependency 打包到构件中？](#14-如何避免将-dependency-打包到构件中)
    - [1.5. 如何跳过单元测试](#15-如何跳过单元测试)
    - [1.6. IDEA 修改 JDK 版本后编译报错](#16-idea-修改-jdk-版本后编译报错)
    - [1.7. 重复引入依赖](#17-重复引入依赖)
    - [1.8. 如何引入本地 jar](#18-如何引入本地-jar)
    - [1.9. 如何排除依赖](#19-如何排除依赖)
- [2. 最佳实践](#2-最佳实践)
    - [2.1. 通过 bom 统一管理版本](#21-通过-bom-统一管理版本)

<!-- /TOC -->

## 1. 常见问题

### 1.1. 去哪儿找 maven dependency ？

问：刚接触 maven 的新手，往往会有这样的疑问，我该去哪儿找 jar？

答：官方推荐的搜索 maven dependency 网址：

- https://search.maven.org
- https://repository.apache.org
- https://mvnrepository.com

### 1.2. 如何指定编码？

问：众所周知，不同编码格式常常会产生意想不到的诡异问题，那么 maven 构建时如何指定 maven 构建时的编码？

答：在 properties 中指定 `project.build.sourceEncoding`

```xml
<properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

### 1.3. 如何指定 JDK 版本？

问：如何指定 maven 构建时的 JDK 版本？

答：有两种方法：

（1）properties 方式

```xml
<project>
  ...
  <properties>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>
  ...
</project>
```

（2）使用 maven-compiler-plugin 插件，并指定 source 和 target 版本

```xml
<build>
...
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.3</version>
      <configuration>
        <source>1.7</source>
        <target>1.7</target>
      </configuration>
    </plugin>
  </plugins>
...
</build>
```

### 1.4. 如何避免将 dependency 打包到构件中？

答：指定 maven dependency 的 scope 为 `provided`，这意味着：依赖关系将在运行时由其容器或 JDK 提供。
具有此范围的依赖关系不会传递，也不会捆绑在诸如 WAR 之类的包中，也不会包含在运行时类路径中。

### 1.5. 如何跳过单元测试

问：执行 mvn package 或 mvn install 时，会自动编译所有单元测试(src/test/java 目录下的代码)，如何跳过这一步？

答：在执行命令的后面，添加命令行参数 `-Dmaven.test.skip=true` 或者 `-DskipTests=true`

### 1.6. IDEA 修改 JDK 版本后编译报错

**错误现象**

修改 JDK 版本，指定 maven-compiler-plugin 的 source 和 target 为 1.8 。

然后，在 Intellij IDEA 中执行 maven 指令，报错：

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.0:compile (default-compile) on project apollo-common: Fatal error compiling: 无效的目标版本： 1.8 -> [Help 1]
```

**错误原因**

maven 的 JDK 源与指定的 JDK 编译版本不符。

**排错手段**

- **查看 Project Settings**

Project SDK 是否正确

<div align="center"><img src="http://dunwu.test.upcdn.net/snap/20181127203324.png"/></div>

SDK 路径是否正确

<div align="center"><img src="http://dunwu.test.upcdn.net/snap/20181127203427.png"/></div>

- **查看 Settings > Maven 的配置**

JDK for importer 是否正确

<div align="center"><img src="http://dunwu.test.upcdn.net/snap/20181127203408.png"/></div>

Runner 是否正确

<div align="center"><img src="http://dunwu.test.upcdn.net/snap/20181127203439.png"/></div>

### 1.7. 重复引入依赖

在 Idea 中，选中 Module，使用 <kbd>Ctrl+Alt+Shift+U</kbd>，打开依赖图，检索是否存在重复引用的情况。

### 1.8. 如何引入本地 jar

问：有时候，需要引入在中央仓库找不到的 jar，但又想通过 maven 进行管理，那么应该如何做到呢？
答：可以通过设置 dependency 的 scope 为 system 来引入本地 jar。
例：

- 将私有 jar 放置在 resouces/lib 下，然后以如下方式添加依赖：
- groupId 和 artifactId 可以按照 jar 包中的 package 设置，只要和其他 jar 不冲突即可。

```xml
<dependency>
    <groupId>xxx</groupId>
    <artifactId>xxx</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/lib/xxx-6.0.0.jar</systemPath>
</dependency>
```

### 1.9. 如何排除依赖

问：如何排除依赖一个依赖关系？比方项目中使用的 libA 依赖某个库的 1.0 版。libB 以来某个库的 2.0 版，如今想统一使用 2.0 版，怎样去掉 1.0 版的依赖？

答：通过 exclusion 排除指定依赖即可。

例：

```xml
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.4.12</version>
    <optional>true</optional>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

## 2. 最佳实践

### 2.1. 通过 bom 统一管理版本

采用类似 spring-framework-bom 的方式统一管理依赖版本。

spring-framework-bom 形式：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.springframework</groupId>
  <artifactId>spring-framework-bom</artifactId>
  <version>4.3.13.RELEASE</version>
  <packaging>pom</packaging>

  ...

  <dependencyManagement>
    <dependencies>
    </dependency>
  </dependencyManagement>
</project>
```

其他项目引入 spring-framework-bom 来管理 spring-framework 依赖版本的方式：

```xml
<dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-framework-bom</artifactId>
        <version>${spring.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
</dependencyManagement>
```
