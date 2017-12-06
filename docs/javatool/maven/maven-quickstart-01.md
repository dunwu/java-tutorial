---
title: Maven 快速指南（一）
date: 2016/06/16
categories:
- javatool
tags:
- java
- tool
- build
---

## 概念

### Maven是什么

Maven 是一个项目管理工具。它负责管理项目开发过程中的几乎所有的东西。

- **版本** maven有自己的版本定义和规则。

- **构建** maven支持许多种的应用程序类型，对于每一种支持的应用程序类型都定义好了一组构建规则和工具集。

- **输出物管理** maven可以管理项目构建的产物，并将其加入到用户库中。这个功能可以用于项目组和其他部门之间的交付行为。

- **依赖关系** maven对依赖关系的特性进行细致的分析和划分，避免开发过程中的依赖混乱和相互污染行为

- **文档和构建结果** maven的site命令支持各种文档信息的发布，包括构建过程的各种输出，javadoc，产品文档等。

- **项目关系** 一个大型的项目通常有几个小项目或者模块组成，用maven可以很方便地管理。

- **移植性管理** maven可以针对不同的开发场景，输出不同种类的输出结果。


### Maven的生命周期

maven把项目的构建划分为不同的生命周期(lifecycle)。粗略一点的话，它这个过程(phase)包括：编译、测试、打包、集成测试、验证、部署。maven中所有的执行动作(goal)都需要指明自己在这个过程中的执行位置，然后maven执行的时候，就依照过程的发展依次调用这些goal进行各种处理。

这个也是maven的一个基本调度机制。一般来说，位置稍后的过程都会依赖于之前的过程。当然，maven同样提供了配置文件，可以依照用户要求，跳过某些阶段。

### Maven的标准工程结构

Maven的标准工程结构如下：

```
|-- pom.xml(maven的核心配置文件)
|-- src
|-- main
	|-- java(java源代码目录)
	|-- resources(资源文件目录)
|-- test
    |-- java(单元测试代码目录)
|-- target(输出目录，所有的输出物都存放在这个目录下)
    |-- classes(编译后的class文件存放处)
```

### Maven的"约定优于配置"

所谓的"约定优于配置"，在maven中并不是完全不可以修改的，他们只是一些配置的默认值而已。但是除非必要，并不需要去修改那些约定内容。maven默认的文件存放结构如下：

每一个阶段的任务都知道怎么正确完成自己的工作，比如compile任务就知道从src/main/java下编译所有的java文件，并把它的输出class文件存放到target/classes中。

对maven来说，采用"约定优于配置"的策略可以减少修改配置的工作量，也可以降低学习成本，更重要的是，给项目引入了统一的规范。

### Maven的版本规范

maven使用如下几个要素来唯一定位某一个输出物：

- **groupId** 团体、组织的标识符。团体标识的约定是，它以创建这个项目的组织名称的逆向域名(reverse domain name)开头。一般对应着JAVA的包的结构。例如org.apache
- **artifactId** 单独项目的唯一标识符。比如我们的tomcat, commons等。不要在artifactId中包含点号(.)。
- **version** 一个项目的特定版本。
- **packaging** 项目的类型，默认是jar，描述了项目打包后的输出。类型为jar的项目产生一个JAR文件，类型为war的项目产生一个web应用。

maven有自己的版本规范，一般是如下定义 `<major version>`、`<minor version>`、`<incremental version>-<qualifier>` ，比如1.2.3-beta-01。要说明的是，maven自己判断版本的算法是major,minor,incremental部分用数字比 较，qualifier部分用字符串比较，所以要小心 alpha-2和alpha-15的比较关系，最好用 alpha-02的格式。

maven在版本管理时候可以使用几个特殊的字符串 SNAPSHOT，LATEST，RELEASE。比如"1.0-SNAPSHOT"。各个部分的含义和处理逻辑如下说明：

- **SNAPSHOT** 这个版本一般用于开发过程中，表示不稳定的版本。
- **LATEST** 指某个特定构件的最新发布，这个发布可能是一个发布版，也可能是一个snapshot版，具体看哪个时间最后。
- **RELEASE** 指最后一个发布版。

## 安装

### 官网下载地址

[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi)

### 配置环境变量

> **注意：安装maven之前，必须先确保你的机器中已经安装了JDK。**

1．解压压缩包（以apache-maven-3.3.9-bin.zip为例）

2．添加环境变量MAVEN_HOME，值为apache-maven-3.3.9的安装路径

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-01.png)

3．在Path环境变量的变量值末尾添加%MAVEN_HOME%\bin

4．在cmd输入mvn –version，如果出现maven的版本信息，说明配置成功。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-02.png)

### 本地仓储配置

从中央仓库下载的jar包，都会统一存放到本地仓库中。我们需要配置本地仓库的位置。

打开maven安装目录，打开conf目录下的setting.xml文件。

可以参照下图配置本地仓储位置。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-03.png)

## 第一个Maven工程

### 在Eclipse中创建Maven工程

#### Maven插件

在Eclipse中创建Maven工程，需要安装Maven插件。

一般较新版本的Eclipse都会带有Maven插件，如果你的Eclipse中已经有Maven插件，可以跳过这一步骤。

点击Help -> Eclipse Marketplace，搜索maven关键字，选择安装红框对应的Maven插件。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-04.png)

#### Maven环境配置

点击Window -> Preferences

如下图所示，配置settings.xml文件的位置

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-05.png)

#### 创建Maven工程

File -> New -> Maven Project -> Next，在接下来的窗口中会看到一大堆的项目模板，选择合适的模板。

接下来设置项目的参数，如下：

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-06.png)

**groupId**是项目组织唯一的标识符，实际对应JAVA的包的结构，是main目录里java的目录结构。

**artifactId**就是项目的唯一的标识符，实际对应项目的名称，就是项目根目录的名称。

点击Finish，Eclipse会创建一个Maven工程。

### 使用Maven进行构建

**Eclipse中构建方式**

在Elipse项目上右击 -> Run As 就能看到很多Maven操作。这些操作和maven命令是等效的。例如Maven clean，等同于mvn clean命令。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-07.png)

你也可以点击Maven build，输入组合命令，并保存下来。如下图：

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-08.png)

**Maven****命令构建方式**

当然，你也可以直接使用maven命令进行构建。

进入工程所在目录，输入maven命令就可以了。

如下图

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-09.png)

## 参考资料

- [官方文档](https://maven.apache.org/index.html)

- [http://www.oschina.net/question/158170_29368](http://www.oschina.net/question/158170_29368)

- [http://www.cnblogs.com/crazy-fox/archive/2012/02/09/2343722.html](http://www.cnblogs.com/crazy-fox/archive/2012/02/09/2343722.html)
