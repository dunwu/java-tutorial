# Maven 教程之入门指南

> **📦 本文已归档在 [java-tutorial](https://dunwu.github.io/java-tutorial/#/)**

<!-- TOC depthFrom:2 depthTo:3 -->

- [一、Maven 简介](#一maven-简介)
  - [Maven 是什么](#maven-是什么)
  - [Maven 的生命周期](#maven-的生命周期)
  - [Maven 的标准工程结构](#maven-的标准工程结构)
  - [Maven 的"约定优于配置"](#maven-的约定优于配置)
  - [Maven 的版本规范](#maven-的版本规范)
- [二、Maven 安装](#二maven-安装)
  - [环境准备](#环境准备)
  - [下载解压](#下载解压)
  - [环境变量](#环境变量)
  - [检测安装成功](#检测安装成功)
  - [Maven 配置文件](#maven-配置文件)
- [三、快速入门](#三快速入门)
  - [创建 Maven 工程](#创建-maven-工程)
  - [在 Intellij 中创建 Maven 工程](#在-intellij-中创建-maven-工程)
  - [在 Eclipse 中创建 Maven 工程](#在-eclipse-中创建-maven-工程)
- [四、使用说明](#四使用说明)
  - [如何添加依赖](#如何添加依赖)
  - [如何寻找 jar 包](#如何寻找-jar-包)
  - [如何使用 Maven 插件(Plugin)](#如何使用-maven-插件plugin)
  - [如何一次编译多个工程](#如何一次编译多个工程)
  - [常用 Maven 插件](#常用-maven-插件)
  - [Maven 命令](#maven-命令)
- [参考资料](#参考资料)

<!-- /TOC -->

## 一、Maven 简介

### Maven 是什么

[Maven](https://github.com/apache/maven) 是一个项目管理工具。它负责管理项目开发过程中的几乎所有的东西。

- **版本** - maven 有自己的版本定义和规则。
- **构建** - maven 支持许多种的应用程序类型，对于每一种支持的应用程序类型都定义好了一组构建规则和工具集。
- **输出物管理** - maven 可以管理项目构建的产物，并将其加入到用户库中。这个功能可以用于项目组和其他部门之间的交付行为。
- **依赖关系** - maven 对依赖关系的特性进行细致的分析和划分，避免开发过程中的依赖混乱和相互污染行为
- **文档和构建结果** - maven 的 site 命令支持各种文档信息的发布，包括构建过程的各种输出，javadoc，产品文档等。
- **项目关系** - 一个大型的项目通常有几个小项目或者模块组成，用 maven 可以很方便地管理。
- **移植性管理** - maven 可以针对不同的开发场景，输出不同种类的输出结果。

### Maven 的生命周期

maven 把项目的构建划分为不同的生命周期(lifecycle)。粗略一点的话，它这个过程(phase)包括：编译、测试、打包、集成测试、验证、部署。maven 中所有的执行动作(goal)都需要指明自己在这个过程中的执行位置，然后 maven 执行的时候，就依照过程的发展依次调用这些 goal 进行各种处理。

这个也是 maven 的一个基本调度机制。一般来说，位置稍后的过程都会依赖于之前的过程。当然，maven 同样提供了配置文件，可以依照用户要求，跳过某些阶段。

### Maven 的标准工程结构

Maven 的标准工程结构如下：

```shell
|-- pom.xml(maven的核心配置文件)
|-- src
|-- main
  |-- java(java源代码目录)
  |-- resources(资源文件目录)
|-- test
    |-- java(单元测试代码目录)
|-- target(输出目录，所有的输出物都存放在这个目录下)
    |-- classes(编译后的class文件存放处)
```

### Maven 的"约定优于配置"

所谓的"约定优于配置"，在 maven 中并不是完全不可以修改的，他们只是一些配置的默认值而已。但是除非必要，并不需要去修改那些约定内容。maven 默认的文件存放结构如下：

每一个阶段的任务都知道怎么正确完成自己的工作，比如 compile 任务就知道从 src/main/java 下编译所有的 java 文件，并把它的输出 class 文件存放到 target/classes 中。

对 maven 来说，采用"约定优于配置"的策略可以减少修改配置的工作量，也可以降低学习成本，更重要的是，给项目引入了统一的规范。

### Maven 的版本规范

maven 使用如下几个要素来唯一定位某一个输出物：

- **groupId** - 团体、组织的标识符。团体标识的约定是，它以创建这个项目的组织名称的逆向域名(reverse domain name)开头。一般对应着 JAVA 的包的结构。例如 org.apache
- **artifactId** - 单独项目的唯一标识符。比如我们的 tomcat, commons 等。不要在 artifactId 中包含点号(.)。
- **version** - 一个项目的特定版本。
- **packaging** - 项目的类型，默认是 jar，描述了项目打包后的输出。类型为 jar 的项目产生一个 JAR 文件，类型为 war 的项目产生一个 web 应用。

例如：想在 maven 工程中引入 4.12 版本的 junit 包，添加如下依赖即可。

```xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>compile</scope>
</dependency>
```

maven 有自己的版本规范，一般是如下定义 `<major version>`、`<minor version>`、`<incremental version>-<qualifier>` ，比如 1.2.3-beta-01。要说明的是，maven 自己判断版本的算法是 major,minor,incremental 部分用数字比 较，qualifier 部分用字符串比较，所以要小心 alpha-2 和 alpha-15 的比较关系，最好用 alpha-02 的格式。

maven 在版本管理时候可以使用几个特殊的字符串 SNAPSHOT，LATEST，RELEASE。比如"1.0-SNAPSHOT"。各个部分的含义和处理逻辑如下说明：

- **SNAPSHOT** - 这个版本一般用于开发过程中，表示不稳定的版本。
- **LATEST** - 指某个特定构件的最新发布，这个发布可能是一个发布版，也可能是一个 snapshot 版，具体看哪个时间最后。
- **RELEASE** - 指最后一个发布版。

## 二、Maven 安装

> Linux 环境安装可以使用我写一键安装脚本：https://github.com/dunwu/linux-tutorial/tree/master/codes/linux/ops/service/maven

### 环境准备

Maven 依赖于 Java，所以本地必须安装 JDK。

打开控制台，执行 `java -version` 确认本地已安装 JDK。

```shell
$ java -version
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```

### 下载解压

进入 **[官网下载地址](https://maven.apache.org/download.cgi)**，选择合适版本，下载并解压到本地。解压命令如下：

```shell
# 以下解压命令分别针对 zip 包和 tar 包
unzip apache-maven-3.6.3-bin.zip
tar xzvf apache-maven-3.6.3-bin.tar.gz
```

### 环境变量

添加环境变量 `MAVEN_HOME`，值为 Maven 的安装路径。

#### 配置 Unix 系统环境变量

输入 `vi /etc/profile` ，添加环境变量如下：

```shell
# MAVEN 的根路径
export MAVEN_HOME=/opt/maven/apache-maven-3.5.2
export PATH=$MAVEN_HOME/bin:$PATH
```

执行 `source /etc/profile` ，立即生效。

#### 配置 Windows 系统环境变量

右键 "计算机"，选择 "属性"，之后点击 "高级系统设置"，点击"环境变量"，来设置环境变量，有以下系统变量需要配置：

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200108143017.png)

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200108143038.png)

### 检测安装成功

检验是否安装成功，执行 `mvn -v` 命令，如果输出类似下面的 maven 版本信息，说明配置成功。

```shell
$ mvn -v
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-18T02:33:14+08:00)
Maven home: /opt/maven/apache-maven-3.5.4
Java version: 1.8.0_191, vendor: Oracle Corporation, runtime: /mnt/disk1/jdk1.8.0_191/jre
Default locale: zh_CN, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-327.el7.x86_64", arch: "amd64", family: "unix"
```

### Maven 配置文件

`setting.xml` 文件是 Maven 的默认配置文件，其默认路径为：`<Maven 安装目录>/conf/settings.xml`。

如果需要修改 Maven 配置，直接修改 `setting.xml` 并保持即可。

例如：想要修改本地仓库位置可以按如下配置，这样，所有通过 Maven 下载打包的 jar 包都会存储在 `D:\maven\repo` 路径下。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd">
  <localRepository>D:\maven\repo<localRepository/>
  <!-- 略 -->
</settings>
```

## 三、快速入门

### 创建 Maven 工程

#### 初始化工程

执行指令：

```shell
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

会在当前路径新建一个名为 `my-app` 的 Maven 工程，其目录结构如下：

```shell
my-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- mycompany
    |               `-- app
    |                   `-- App.java
    `-- test
        `-- java
            `-- com
                `-- mycompany
                    `-- app
                        `-- AppTest.java
```

其中， `src/main/java` 目录包含 java 源码， `src/test/java` 目录包含 java 测试源码，而 `pom.xml` 文件是 maven 工程的配置文件。

#### POM 配置

pom.xml 是 maven 工程的配置文件，它描述了 maven 工程的构建方式，其配置信息是很复杂的，这里给一个最简单的配置示例：

```xml
project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

#### 构建项目

执行以下命令，即可构建项目：

```shell
mvn clean package -Dmaven.test.skip=true -B -U
```

构建成功后，会输出类似下面的信息：

```shell
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.953 s
[INFO] Finished at: 2019-11-24T13:05:10+01:00
[INFO] ------------------------------------------------------------------------
```

这时，在当前路径下会产生一个 `target` 目录，其中包含了构建的输出物，如：jar 包、class 文件等。

这时，我们可以执行以下命令启动 jar 包：

```shell
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
```

### 在 Intellij 中创建 Maven 工程

（1）创建 Maven 工程

依次点击 File -> New -> Project 打开创建工程对话框，选择 Maven 工程。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/1555414103572.png)

（2）输入项目信息

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/1555415549748.png)

（3）点击 Intellij 侧边栏中的 Maven 工具界面，有几个可以直接使用的 maven 命令，可以帮助你进行构建。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/1555415806237.png)

### 在 Eclipse 中创建 Maven 工程

（1）Maven 插件

在 Eclipse 中创建 Maven 工程，需要安装 Maven 插件。

一般较新版本的 Eclipse 都会带有 Maven 插件，如果你的 Eclipse 中已经有 Maven 插件，可以跳过这一步骤。

点击 Help -> Eclipse Marketplace，搜索 maven 关键字，选择安装红框对应的 Maven 插件。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20181127195117.png)

（2）Maven 环境配置

点击 Window -> Preferences

如下图所示，配置 settings.xml 文件的位置

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20181127195128.png)

（3）创建 Maven 工程

File -> New -> Maven Project -> Next，在接下来的窗口中会看到一大堆的项目模板，选择合适的模板。

接下来设置项目的参数，如下：

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20181127195151.png)

**groupId**是项目组织唯一的标识符，实际对应 JAVA 的包的结构，是 main 目录里 java 的目录结构。

**artifactId**就是项目的唯一的标识符，实际对应项目的名称，就是项目根目录的名称。

点击 Finish，Eclipse 会创建一个 Maven 工程。

（4）使用 Maven 进行构建

Eclipse 中构建方式：

在 Elipse 项目上右击 -> Run As 就能看到很多 Maven 操作。这些操作和 maven 命令是等效的。例如 Maven clean，等同于 mvn clean 命令。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20181127195208.png)

你也可以点击 Maven build，输入组合命令，并保存下来。如下图：

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20181127195219.png)

Maven 命令构建方式：

当然，你也可以直接使用 maven 命令进行构建。

进入工程所在目录，输入 maven 命令就可以了。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20181127195243.png)

## 四、使用说明

### 如何添加依赖

在 Maven 工程中添加依赖 jar 包，很简单，只要在 POM 文件中引入对应的`<dependency>`标签即可。

参考下例：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

  <modelVersion>4.0.0</modelVersion>
  <groupId>com.zp.maven</groupId>
  <artifactId>MavenDemo</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>
  <name>MavenDemo</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <junit.version>3.8.1</junit.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.12</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>
</project>
```

`<dependency>` 标签最常用的四个属性标签：

- `<groupId>` - 项目组织唯一的标识符，实际对应 JAVA 的包的结构。
- `<artifactId>` - 项目唯一的标识符，实际对应项目的名称，就是项目根目录的名称。
- `<version>` - jar 包的版本号。可以直接填版本数字，也可以在 properties 标签中设置属性值。
- `<scope>` - jar 包的作用范围。可以填写 compile、runtime、test、system 和 provided。用来在编译、测试等场景下选择对应的 classpath。

### 如何寻找 jar 包

可以在 [http://mvnrepository.com/](http://mvnrepository.com/) 站点搜寻你想要的 jar 包版本

例如，想要使用 log4j，可以找到需要的版本号，然后拷贝对应的 maven 标签信息，将其添加到 pom .xml 文件中。

### 如何使用 Maven 插件(Plugin)

要添加 Maven 插件，可以在 pom.xml 文件中添加 `<plugin>` 标签。

```xml
<build>
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
</build>
```

`<configuration>` 标签用来配置插件的一些使用参数。

### 如何一次编译多个工程

假设要创建一个父 maven 工程，它有两个子工程：my-app 和 my-webapp：

```shell
+- pom.xml
+- my-app
| +- pom.xml
| +- src
|   +- main
|     +- java
+- my-webapp
| +- pom.xml
| +- src
|   +- main
|     +- webapp
```

app 工程的 pom.xml 如下，重点在于在 modules 中引入两个子 module：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.mycompany.app</groupId>
  <artifactId>app</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <modules>
    <module>my-app</module>
    <module>my-webapp</module>
  </modules>
</project>
```

选择编译 XXX 时，会依次对它的所有 Module 执行相同操作。

### 常用 Maven 插件

> 更多详情请参考：[https://maven.apache.org/plugins/](https://maven.apache.org/plugins/)

#### [maven-antrun-plugin](http://maven.apache.org/plugins/maven-antrun-plugin/)

maven-antrun-plugin 能让用户在 Maven 项目中运行 Ant 任务。用户可以直接在该插件的配置以 Ant 的方式编写 Target， 然后交给该插件的 run 目标去执行。在一些由 Ant 往 Maven 迁移的项目中，该插件尤其有用。此外当你发现需要编写一些自定义程度很高的任务，同时又觉 得 Maven 不够灵活时，也可以以 Ant 的方式实现之。maven-antrun-plugin 的 run 目标通常与生命周期绑定运行。

#### [maven-archetype-plugin](http://maven.apache.org/archetype/maven-archetype-plugin/)

Archtype 指项目的骨架，Maven 初学者最开始执行的 Maven 命令可能就是**mvn archetype:generate**，这实际上就是让 maven-archetype-plugin 生成一个很简单的项目骨架，帮助开发者快速上手。可能也有人看到一些文档写了**mvn archetype:create**， 但实际上 create 目标已经被弃用了，取而代之的是 generate 目标，该目标使用交互式的方式提示用户输入必要的信息以创建项目，体验更好。 maven-archetype-plugin 还有一些其他目标帮助用户自己定义项目原型，例如你由一个产品需要交付给很多客户进行二次开发，你就可以为 他们提供一个 Archtype，帮助他们快速上手。

#### [maven-assembly-plugin](http://maven.apache.org/plugins/maven-assembly-plugin/)

maven-assembly-plugin 的用途是将项目打包，该包可能包含了项目的可执行文件、源代码、readme、平台脚本等等。 maven-assembly-plugin 支持各种主流的格式如 zip、tar.gz、jar 和 war 等，具体打包哪些文件是高度可控的，例如用户可以 按文件级别的粒度、文件集级别的粒度、模块级别的粒度、以及依赖级别的粒度控制打包，此外，包含和排除配置也是支持的。maven-assembly- plugin 要求用户使用一个名为`assembly.xml`的元数据文件来表述打包，它的 single 目标可以直接在命令行调用，也可以被绑定至生命周期。

#### [maven-dependency-plugin](http://maven.apache.org/plugins/maven-dependency-plugin/)

maven-dependency-plugin 最大的用途是帮助分析项目依赖，**dependency:list**能够列出项目最终解析到的依赖列表，**dependency:tree**能进一步的描绘项目依赖树，**dependency:analyze**可以告诉你项目依赖潜在的问题，如果你有直接使用到的却未声明的依赖，该目标就会发出警告。maven-dependency-plugin 还有很多目标帮助你操作依赖文件，例如**dependency:copy-dependencies**能将项目依赖从本地 Maven 仓库复制到某个特定的文件夹下面。

#### [maven-enforcer-plugin](http://maven.apache.org/plugins/maven-enforcer-plugin/)

在一个稍大一点的组织或团队中，你无法保证所有成员都熟悉 Maven，那他们做一些比较愚蠢的事情就会变得很正常，例如给项目引入了外部的 SNAPSHOT 依赖而导致构建不稳定，使用了一个与大家不一致的 Maven 版本而经常抱怨构建出现诡异问题。maven-enforcer- plugin 能够帮助你避免之类问题，它允许你创建一系列规则强制大家遵守，包括设定 Java 版本、设定 Maven 版本、禁止某些依赖、禁止 SNAPSHOT 依赖。只要在一个父 POM 配置规则，然后让大家继承，当规则遭到破坏的时候，Maven 就会报错。除了标准的规则之外，你还可以扩展该插 件，编写自己的规则。maven-enforcer-plugin 的 enforce 目标负责检查规则，它默认绑定到生命周期的 validate 阶段。

#### [maven-help-plugin](http://maven.apache.org/plugins/maven-help-plugin/)

maven-help-plugin 是一个小巧的辅助工具，最简单的**help:system**可以打印所有可用的环境变量和 Java 系统属性。**help:effective-pom**和**help:effective-settings**最 为有用，它们分别打印项目的有效 POM 和有效 settings，有效 POM 是指合并了所有父 POM（包括 Super POM）后的 XML，当你不确定 POM 的某些信息从何而来时，就可以查看有效 POM。有效 settings 同理，特别是当你发现自己配置的 settings.xml 没有生效时，就可以用**help:effective-settings**来验证。此外，maven-help-plugin 的 describe 目标可以帮助你描述任何一个 Maven 插件的信息，还有 all-profiles 目标和 active-profiles 目标帮助查看项目的 Profile。

#### [maven-release-plugin](http://maven.apache.org/plugins/maven-release-plugin/)

maven-release-plugin 的用途是帮助自动化项目版本发布，它依赖于 POM 中的 SCM 信息。**release:prepare**用来准备版本发布，具体的工作包括检查是否有未提交代码、检查是否有 SNAPSHOT 依赖、升级项目的 SNAPSHOT 版本至 RELEASE 版本、为项目打标签等等。**release:perform**则 是签出标签中的 RELEASE 源码，构建并发布。版本发布是非常琐碎的工作，它涉及了各种检查，而且由于该工作仅仅是偶尔需要，因此手动操作很容易遗漏一 些细节，maven-release-plugin 让该工作变得非常快速简便，不易出错。maven-release-plugin 的各种目标通常直接在 命令行调用，因为版本发布显然不是日常构建生命周期的一部分。

#### [maven-resources-plugin](http://maven.apache.org/plugins/maven-resources-plugin/)

为了使项目结构更为清晰，Maven 区别对待 Java 代码文件和资源文件，maven-compiler-plugin 用来编译 Java 代码，maven-resources-plugin 则用来处理资源文件。默认的主资源文件目录是`src/main/resources`，很多用户会需要添加额外的资源文件目录，这个时候就可以通过配置 maven-resources-plugin 来实现。此外，资源文件过滤也是 Maven 的一大特性，你可以在资源文件中使用*\${propertyName}*形式的 Maven 属性，然后配置 maven-resources-plugin 开启对资源文件的过滤，之后就可以针对不同环境通过命令行或者 Profile 传入属性的值，以实现更为灵活的构建。

#### [maven-surefire-plugin](http://maven.apache.org/plugins/maven-surefire-plugin/)

可能是由于历史的原因，Maven 2.3 中用于执行测试的插件不是 maven-test-plugin，而是 maven-surefire-plugin。其实大部分时间内，只要你的测试 类遵循通用的命令约定（以 Test 结尾、以 TestCase 结尾、或者以 Test 开头），就几乎不用知晓该插件的存在。然而在当你想要跳过测试、排除某些 测试类、或者使用一些 TestNG 特性的时候，了解 maven-surefire-plugin 的一些配置选项就很有用了。例如 **mvn test -Dtest=FooTest** 这样一条命令的效果是仅运行 FooTest 测试类，这是通过控制 maven-surefire-plugin 的 test 参数实现的。

#### [build-helper-maven-plugin](http://mojo.codehaus.org/build-helper-maven-plugin/)

Maven 默认只允许指定一个主 Java 代码目录和一个测试 Java 代码目录，虽然这其实是个应当尽量遵守的约定，但偶尔你还是会希望能够指定多个 源码目录（例如为了应对遗留项目），build-helper-maven-plugin 的 add-source 目标就是服务于这个目的，通常它被绑定到 默认生命周期的 generate-sources 阶段以添加额外的源码目录。需要强调的是，这种做法还是不推荐的，因为它破坏了 Maven 的约定，而且可能会遇到其他严格遵守约定的插件工具无法正确识别额外的源码目录。

build-helper-maven-plugin 的另一个非常有用的目标是 attach-artifact，使用该目标你可以以 classifier 的形式选取部分项目文件生成附属构件，并同时 install 到本地仓库，也可以 deploy 到远程仓库。

#### [exec-maven-plugin](http://mojo.codehaus.org/exec-maven-plugin/)

exec-maven-plugin 很好理解，顾名思义，它能让你运行任何本地的系统程序，在某些特定情况下，运行一个 Maven 外部的程序可能就是最简单的问题解决方案，这就是**exec:exec**的 用途，当然，该插件还允许你配置相关的程序运行参数。除了 exec 目标之外，exec-maven-plugin 还提供了一个 java 目标，该目标要求你 提供一个 mainClass 参数，然后它能够利用当前项目的依赖作为 classpath，在同一个 JVM 中运行该 mainClass。有时候，为了简单的 演示一个命令行 Java 程序，你可以在 POM 中配置好 exec-maven-plugin 的相关运行参数，然后直接在命令运行**mvn exec:java** 以查看运行效果。

#### [jetty-maven-plugin](http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin)

在进行 Web 开发的时候，打开浏览器对应用进行手动的测试几乎是无法避免的，这种测试方法通常就是将项目打包成 war 文件，然后部署到 Web 容器 中，再启动容器进行验证，这显然十分耗时。为了帮助开发者节省时间，jetty-maven-plugin 应运而生，它完全兼容 Maven 项目的目录结构，能够周期性地检查源文件，一旦发现变更后自动更新到内置的 Jetty Web 容器中。做一些基本配置后（例如 Web 应用的 contextPath 和自动扫描变更的时间间隔），你只要执行 **mvn jetty:run** ，然后在 IDE 中修改代码，代码经 IDE 自动编译后产生变更，再由 jetty-maven-plugin 侦测到后更新至 Jetty 容器，这时你就可以直接 测试 Web 页面了。需要注意的是，jetty-maven-plugin 并不是宿主于 Apache 或 Codehaus 的官方插件，因此使用的时候需要额外 的配置`settings.xml`的 pluginGroups 元素，将 org.mortbay.jetty 这个 pluginGroup 加入。

#### [versions-maven-plugin](http://mojo.codehaus.org/versions-maven-plugin/)

很多 Maven 用户遇到过这样一个问题，当项目包含大量模块的时候，为他们集体更新版本就变成一件烦人的事情，到底有没有自动化工具能帮助完成这件 事情呢？（当然你可以使用 sed 之类的文本操作工具，不过不在本文讨论范围）答案是肯定的，versions-maven- plugin 提供了很多目标帮助你管理 Maven 项目的各种版本信息。例如最常用的，命令 **mvn versions:set -DnewVersion=1.1-SNAPSHOT** 就能帮助你把所有模块的版本更新到 1.1-SNAPSHOT。该插件还提供了其他一些很有用的目标，display-dependency- updates 能告诉你项目依赖有哪些可用的更新；类似的 display-plugin-updates 能告诉你可用的插件更新；然后 use- latest-versions 能自动帮你将所有依赖升级到最新版本。最后，如果你对所做的更改满意，则可以使用 **mvn versions:commit** 提交，不满意的话也可以使用 **mvn versions:revert** 进行撤销。

### Maven 命令

常用 maven 命令清单：

| **生命周期**                | **阶段描述**                                                                                                    |
| --------------------------- | --------------------------------------------------------------------------------------------------------------- |
| mvn validate                | 验证项目是否正确，以及所有为了完整构建必要的信息是否可用                                                        |
| mvn generate-sources        | 生成所有需要包含在编译过程中的源代码                                                                            |
| mvn process-sources         | 处理源代码，比如过滤一些值                                                                                      |
| mvn generate-resources      | 生成所有需要包含在打包过程中的资源文件                                                                          |
| mvn process-resources       | 复制并处理资源文件至目标目录，准备打包                                                                          |
| mvn compile                 | 编译项目的源代码                                                                                                |
| mvn process-classes         | 后处理编译生成的文件，例如对 Java 类进行字节码增强（bytecode enhancement）                                      |
| mvn generate-test-sources   | 生成所有包含在测试编译过程中的测试源码                                                                          |
| mvn process-test-sources    | 处理测试源码，比如过滤一些值                                                                                    |
| mvn generate-test-resources | 生成测试需要的资源文件                                                                                          |
| mvn process-test-resources  | 复制并处理测试资源文件至测试目标目录                                                                            |
| mvn test-compile            | 编译测试源码至测试目标目录                                                                                      |
| mvn test                    | 使用合适的单元测试框架运行测试。这些测试应该不需要代码被打包或发布                                              |
| mvn prepare-package         | 在真正的打包之前，执行一些准备打包必要的操作。这通常会产生一个包的展开的处理过的版本（将会在 Maven 2.1+中实现） |
| mvn package                 | 将编译好的代码打包成可分发的格式，如 JAR，WAR，或者 EAR                                                         |
| mvn pre-integration-test    | 执行一些在集成测试运行之前需要的动作。如建立集成测试需要的环境                                                  |
| mvn integration-test        | 如果有必要的话，处理包并发布至集成测试可以运行的环境                                                            |
| mvn post-integration-test   | 执行一些在集成测试运行之后需要的动作。如清理集成测试环境。                                                      |
| mvn verify                  | 执行所有检查，验证包是有效的，符合质量规范                                                                      |
| mvn install                 | 安装包至本地仓库，以备本地的其它项目作为依赖使用                                                                |
| mvn deploy                  | 复制最终的包至远程仓库，共享给其它开发人员和项目（通常和一次正式的发布相关）                                    |

示例：最常用的 maven 构建命令

```shell
mvn clean install -Dmaven.test.skip=true -B -U
```

清理本地输出物，并构建 maven 项目，最后将输出物归档在本地仓库。

> :bulb: 想了解更多 maven 命令行细节可以参考官方文档：
>
> - [Maven 构建生命周期说明](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
> - [Maven 命令行参数说明](https://maven.apache.org/ref/3.6.3/maven-embedder/cli.html)

## 参考资料

- [Maven Github](https://github.com/apache/maven)
- [Maven 官方文档](https://maven.apache.org/ref/current)
- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)
- [maven 常见问题问答](http://www.oschina.net/question/158170_29368)
- [常用 Maven 插件介绍](https://www.cnblogs.com/crazy-fox/archive/2012/02/09/2343722.html)
