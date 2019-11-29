# Ant 简易教程

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
- [下载和安装](#下载和安装)
  - [下载](#下载)
  - [配置环境变量](#配置环境变量)
  - [验证](#验证)
- [例子](#例子)
- [关键元素](#关键元素)
  - [Project 元素](#project-元素)
  - [Target 元素](#target-元素)
  - [Task 元素](#task-元素)
  - [Property 元素](#property-元素)
  - [extension-point 元素](#extension-point-元素)
- [参考资料](#参考资料)

<!-- /TOC -->

## 简介

`Apache Ant` 是一个将软件编译、测试、部署等步骤联系在一起加以自动化的一个工具，大多用于 Java 环境中的软件开发。由 Apache 软件基金会所提供。

Ant 是纯 Java 语言编写的，所以具有很好的跨平台性。

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-d9da2a06160103d0.png"/></div>

## 下载和安装

### 下载

ant 的官方下载地址：http://ant.apache.org/bindownload.cgi

进入页面后，在下图的红色方框中可以下载最新版本。笔者下载的版本是 **apache-ant-1.9.4。**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-72d3bc81cd29e68d.png"/></div>

### 配置环境变量

配置环境变量（我的电脑 -> 属性 -> 高级 -> 环境变量）。

设置 ant 环境变量：

**ANT_HOME** C:/ apache-ant-1.9.4

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-682a8e16b82a7532.png"/></div>

**path ** C:/ apache-ant-1.9.4/bin

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-ea61070f97b5a7cc.png"/></div>

**classpath** C:/apache-ant-1.9.4/lib

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-5bc45dbe64602bc7.png"/></div>

### 验证

点击 开始 -> 运行 -> 输入 cmd

**执行构建文件**

输入如下命令：**ant**

如果出现如下内容，说明安装成功：

> Buildfile: build.xml does not exist!
> Build failed

注意：因为 ant 默认运行 build.xml 文件，这个文件需要我们创建。

如果不想命名为 build.xml，运行时可以使用 **ant -buildfile test.xml** 命令指明要运行的构建文件。

**查看版本信息**

输入 **ant -version**，可以查看版本信息。

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-920e94f33b4d7dd9.png"/></div>

但如果出现 'ant' 不是内部或外部命令，也不是可运行的程序或批处理文件，说明安装失败：（可以重复前述步骤，直至安装成功。）

## 例子

在安装和配置成功后，我们就可以使用 ant 了。

为了让读者对 ant 有一个直观的认识，首先以 Ant 官方手册上的一个简单例子做一个说明。

以下是一个 build.xml 文件的内容：

```xml
<project name="MyProject" default="dist" basedir=".">
    <description>
        simple example build file
    </description>
  <!-- set global properties for this build -->
  <property name="src" location="src"/>
  <property name="build" location="build"/>
  <property name="dist"  location="dist"/>

  <target name="init">
    <!-- Create the time stamp -->
    <tstamp/>
    <!-- Create the build directory structure used by compile -->
    <mkdir dir="${build}"/>
  </target>

  <target name="compile" depends="init"
        description="compile the source " >
    <!-- Compile the java code from ${src} into ${build} -->
    <javac srcdir="${src}" destdir="${build}"/>
  </target>

  <target name="dist" depends="compile"
        description="generate the distribution" >
    <!-- Create the distribution directory -->
    <mkdir dir="${dist}/lib"/>

    <!-- Put everything in ${build} into the MyProject-${DSTAMP}.jar file -->
    <jar jarfile="${dist}/lib/MyProject-${DSTAMP}.jar" basedir="${build}"/>
  </target>

  <target name="clean"
        description="clean up" >
    <!-- Delete the ${build} and ${dist} directory trees -->
    <delete dir="${build}"/>
    <delete dir="${dist}"/>
  </target>
</project>
```

在这个 xml 文件中，有几个 target 标签，每个 target 对应一个执行目标。

我们将这个 build.xml 放在 D:\Temp\ant_test 路径下，然后在 dos 界面下进行测试。

**ant init**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-0d37a1be0ef4238a.png"/></div>

在 D:\Temp\ant_test 路径下创建了一个 build 目录，执行成功。

**ant compile**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-6f35ed13331c87c9.png"/></div>

提示错误，原来是在 build.xml 的所在目录下找不到 src 目录。好的，我们直接创建一个 src 目录，然后再次尝试。这次，执行成功。

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-9e84af99a8e952e0.png"/></div>

**ant dist **

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-daeaf201bf05e097.png"/></div>

在 D:\Temp\ant_test 路径下创建了一个 dist 目录，执行成功。

**ant clean**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-be427613f7867513.png"/></div>

清除创建的 build 和 dist 目录，执行成功。

**一个细节**

细心的读者，想必已经发现一个问题——在执行 ant compile 和 ant dist 命令的时候把前面的命令也执行了。这是为什么呢？

请留意一下 build.xml 中的内容。有部分 **target** 标签中含有 **depends** 关键字。

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-746a2156fbfb8d54.png"/></div>

这表明，当前的 target 在执行时需要依赖其他的 target，必须先执行依赖的 target，然后再执行。

## 关键元素

Ant 的构件文件都是 XML 格式的。每个构件文件包含一个 project 元素和至少一个 target。

target 元素可以包含多个 task 元素。

### Project 元素

**project 元素**是构建文件的根元素。

一个 project 元素可以有多个 target 元素，一个 target 元素可以有多个 task。

在上节的例子中，project 标签里有三个属性。

```xml
<project name="MyProject" default="dist" basedir=".">
```

**name 属性**，指示 project 元素的名字。例子中的名字就是 MyProject。

**default 属性**，指示这个 project 默认执行的 target。在本文的例子中，默认执行的 target 为 dist。

如果我们输入命令 ant 时，不指定 target 参数，默认会执行 dist 这个 target。

**basedir 属性**，指定根路径的位置。该属性没有指定时，使用 Ant 的构件文件的所在目录作为根目录。

### Target 元素

**target 元素**是 task 的容器，也就是 Ant 的一个基本执行单元。

以上节例子中的 compile 来举例。

```xml
<target name="compile" depends="init" description="compile the source " >
    <!-- Compile the java code from ${src} into ${build} -->
    <javac srcdir="${src}" destdir="${build}"/>
</target>
```

这个 target 中出现了几个属性。

**name 属性**，指示 target 元素的名称。

这个属性在一个 project 元素中必须是唯一的。这很好理解，如果出现重复，Ant 就不知道具体该执行哪个 target 了。

**depends 属性**，指示依赖的 target，当前的 target 必须在依赖的 target 之后执行。

**description 属性**，是关于 target 的简短说明。

此外，还有其他几个未出现在构建文件中的属性。

**if 属性**，验证指定的属性是否存在，若不存在，所在 target 将不会被执行。

**unless 属性**，**正好和 if 属性相反**，验证指定的属性是否存在，若存在，所在 target 将不会被执行。\*\*\*\*

**extensionOf 属性**，添加当前 target 到 **extension-point** 依赖列表。**——Ant1.8.0 新特性。**

> **extension-point 元素**和 target 元素十分类似，都可以指定依赖的 target。但是不同的是，extension-point 中不能包含任何 task。

请看以下实例：

```xml
<target name="create-directory-layout">
   ...
</target>
<extension-point name="ready-to-compile" depends="create-directory-layout"/>
<target name="compile" depends="ready-to-compile">
   ...
</target>
```

**调用 target 顺序**: create-directory-layout --> 'empty slot' --> compile

```xml
<target name="generate-sources" extensionOf="ready-to-compile">
   ...
</target>
```

**调用 target 顺序**: create-directory-layout --> generate-sources --> compile

**onMissingExtensionPoint 属性**：当无法找到一个 extension-point 时，target 尝试去做的动作("fail", "warn", "ignore")。_——Ant1.8.2 新特性_

### Task 元素

task 是一段可以被执行的代码。

一个 task 可以有多个属性， 一个属性可以包含对一个 **property** 的引用。

task 的通常结构为

```xml
<name attribute1="value1" attribute2="value2" ... />
```

其中，name 是 task 的名字， attributeN 是属性名， valueN 是这个属性的值。

还是以 compile 做为例子：

```xml
<target name="compile" depends="init" description="compile the source " >
    <!-- Compile the java code from srcintosrcinto{build} -->
    <javac srcdir="${src}" destdir="${build}"/>
</target>
```

在 compile 这个 target 标签中包含了一个任务。

这个任务的动作是：执行 JAVA 编译，编译 src 下的代码，并把编译生成的文件放在 build 目录中。

**常用 task **

**javac**：用于编译一个或者多个 Java 源文件，通常需要 srcdir 和 destdir 两个属性，用于指定 Java 源文件的位置和编译后 class 文件的保存位置。

```xml
<javac srcdir="${src}" destdir="${build}" classpath="abc.jar" debug="on" source="1.7" />
```

**java**：用于运行某个 Java 类，通常需要 classname 属性，用于指定需要运行哪个类。

```xml
<java classname="test.Main">
    <arg value="-h" />
    <classpath>
        <pathelement location="dist/test.jar" />
    </classpath>
</java>
```

**jar**：用于生成 JAR 包，通常需要指定 destfile 属性，用于指定所创建 JAR 包的文件名。除此之外，通常还应指定一个文件集，表明需要将哪些文件打包到 JAR 包里。

```xml
<jar jarfile="dist/lib/MyProject−dist/lib/MyProject−{DSTAMP}.jar" basedir="${build}"/>
```

**echo**：输出某个字符串。

```xml
<echo message="Building to ${builddir}"/>
<echo>You are using version ${java.version} of Java! This message spans two lines.</echo>
```

**copy**：用于复制文件或路径。

```xml
<copy todir="${builddir}/srccopy">
    <fileset dir="${srcdir}">
        <include name="**/*.java"/>
    </fileset>
    <filterset>
        <filter token="VERSION" value="${app.version}"/>
    </filterset>
</copy>
```

**delete**：用于删除文件或路径。

```xml
<copy todir="${builddir}/srccopy">
    <fileset dir="${srcdir}">
        <include name="**/*.java"/>
    </fileset>
    <filterset>
        <filter token="VERSION" value="${app.version}"/>
    </filterset>
</copy>
```

**mkdir**：用于创建文件夹。

```xml
<mkdir dir="${dist}/lib" />
```

**move**：用户移动文件和路径。

```xml
<move todir="some/new/dir">
    <fileset dir="my/src/dir">
        <include name="**/*.jar" />
        <exclude name="**/ant.jar" />
    </fileset>
</move>
```

### Property 元素

Property 是对参数的定义。

project 的属性可以通过 property 元素来设定，也可在 Ant 之外设定。若要在外部引入某文件，例如 build.properties 文件，可以通过如下内容将其引入：<property file=” build.properties”/>。

property 元素可用作 task 的属性值。在 task 中是通过将属性名放在“\${”和“}”之间，并放在 task 属性值的位置来实现的。

例如 complile 例子中，使用了前面定义的 src 作为源目录。

```xml
<javac srcdir="${src}" destdir="${build}"/>
```

Ant 提供了一些内置的属性，它能得到的系统属性的列表与 Java 文档中 System.getPropertis()方法得到的属性一致，这些系统属性可参考 sun 网站的说明。

### extension-point 元素

和 target 元素十分类似，都可以指定依赖的 target。但是不同的是，extension-point 中不能包含任何 task。

_——Ant1.8.0 新增特性。_

在 target 元素中的例子里已提到过，不再赘述。

## 参考资料

- [ant 官方手册](http://ant.apache.org/manual/index.html)
- [http://www.blogjava.net/amigoxie/archive/2007/11/09/159413.html](http://www.blogjava.net/amigoxie/archive/2007/11/09/159413.html)
