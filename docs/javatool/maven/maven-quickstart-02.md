---
title: Maven 快速指南（二）
date: 2016/06/16
categories:
- javatool
tags:
- java
- tool
- build
---

## 使用指导

### 如何添加外部依赖jar包

在Maven工程中添加依赖jar包，很简单，只要在POM文件中引入对应的`<dependency>`标签即可。

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

`<dependency>`标签最常用的四个属性标签：

`<groupId>`：项目组织唯一的标识符，实际对应JAVA的包的结构。

`<artifactId>`：项目唯一的标识符，实际对应项目的名称，就是项目根目录的名称。

`<version>`：jar包的版本号。可以直接填版本数字，也可以在properties标签中设置属性值。

`<scope>`：jar包的作用范围。可以填写compile、runtime、test、system和provided。用来在编译、测试等场景下选择对应的classpath。

### 如何寻找jar包

可以在 [http://mvnrepository.com/](http://mvnrepository.com/) 站点搜寻你想要的jar包版本

例如，想要使用log4j，可以找到需要的版本号，然后拷贝对应的maven标签信息，将其添加到pom .xml文件中。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-10.png)

### 如何使用Maven插件(Plugin)

要添加Maven插件，可以在pom.xml文件中添加 `<plugin>` 标签。

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

在Maven中，允许一个Maven Project中有多个Maven Module

1.创建maven父工程步骤：new-->other-->选择maven project-->next-->勾选create a simple project-->next-->填写Group Id、Artifact Id、Version --> packaging选择pom-->finish。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-11.png)

2.创建maven子工程步骤：选中刚才创建的父工程右键-->new-->other-->选择maven module-->next-->勾选create a simple project-->填写module name（其实就是artifact id）-->next-->GAV继承父工程-->packaging选择你需要的-->finish。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-12.png)

3.完成，刷新父工程；如有多个子工程，继续按照第二步骤创建。

![img](http://oyz7npk35.bkt.clouddn.com//image/java/libs/maven/maven-quickstart-13.png)

这时打开XXX中的pom.xml可以看到其中有以下标签

```xml
<modules>
  <module>xxx1</module>
</modules>
```

选择编译XXX时，会依次对它的所有Module执行相同操作。

### 常用Maven插件

#### maven-antrun-plugin

[http://maven.apache.org/plugins/maven-antrun-plugin/](http://maven.apache.org/plugins/maven-antrun-plugin/)

maven-antrun-plugin能让用户在Maven项目中运行Ant任务。用户可以直接在该插件的配置以Ant的方式编写Target， 然后交给该插件的run目标去执行。在一些由Ant往Maven迁移的项目中，该插件尤其有用。此外当你发现需要编写一些自定义程度很高的任务，同时又觉 得Maven不够灵活时，也可以以Ant的方式实现之。maven-antrun-plugin的run目标通常与生命周期绑定运行。

#### maven-archetype-plugin

[http://maven.apache.org/archetype/maven-archetype-plugin/](http://maven.apache.org/archetype/maven-archetype-plugin/)

Archtype指项目的骨架，Maven初学者最开始执行的Maven命令可能就是**mvn archetype:generate**，这实际上就是让maven-archetype-plugin生成一个很简单的项目骨架，帮助开发者快速上手。可能也有人看到一些文档写了**mvn archetype:create**， 但实际上create目标已经被弃用了，取而代之的是generate目标，该目标使用交互式的方式提示用户输入必要的信息以创建项目，体验更好。 maven-archetype-plugin还有一些其他目标帮助用户自己定义项目原型，例如你由一个产品需要交付给很多客户进行二次开发，你就可以为 他们提供一个Archtype，帮助他们快速上手。

#### maven-assembly-plugin

[http://maven.apache.org/plugins/maven-assembly-plugin/](http://maven.apache.org/plugins/maven-assembly-plugin/)

maven-assembly-plugin的用途是制作项目分发包，该分发包可能包含了项目的可执行文件、源代码、readme、平台脚本等等。 maven-assembly-plugin支持各种主流的格式如zip、tar.gz、jar和war等，具体打包哪些文件是高度可控的，例如用户可以 按文件级别的粒度、文件集级别的粒度、模块级别的粒度、以及依赖级别的粒度控制打包，此外，包含和排除配置也是支持的。maven-assembly- plugin要求用户使用一个名为`assembly.xml`的元数据文件来表述打包，它的single目标可以直接在命令行调用，也可以被绑定至生命周期。

#### maven-dependency-plugin

[http://maven.apache.org/plugins/maven-dependency-plugin/](http://maven.apache.org/plugins/maven-dependency-plugin/)

maven-dependency-plugin最大的用途是帮助分析项目依赖，**dependency:list**能够列出项目最终解析到的依赖列表，**dependency:tree**能进一步的描绘项目依赖树，**dependency:analyze**可以告诉你项目依赖潜在的问题，如果你有直接使用到的却未声明的依赖，该目标就会发出警告。maven-dependency-plugin还有很多目标帮助你操作依赖文件，例如**dependency:copy-dependencies**能将项目依赖从本地Maven仓库复制到某个特定的文件夹下面。

#### maven-enforcer-plugin

[http://maven.apache.org/plugins/maven-enforcer-plugin/](http://maven.apache.org/plugins/maven-enforcer-plugin/)

在一个稍大一点的组织或团队中，你无法保证所有成员都熟悉Maven，那他们做一些比较愚蠢的事情就会变得很正常，例如给项目引入了外部的 SNAPSHOT依赖而导致构建不稳定，使用了一个与大家不一致的Maven版本而经常抱怨构建出现诡异问题。maven-enforcer- plugin能够帮助你避免之类问题，它允许你创建一系列规则强制大家遵守，包括设定Java版本、设定Maven版本、禁止某些依赖、禁止 SNAPSHOT依赖。只要在一个父POM配置规则，然后让大家继承，当规则遭到破坏的时候，Maven就会报错。除了标准的规则之外，你还可以扩展该插 件，编写自己的规则。maven-enforcer-plugin的enforce目标负责检查规则，它默认绑定到生命周期的validate阶段。

#### maven-help-plugin

[http://maven.apache.org/plugins/maven-help-plugin/](http://maven.apache.org/plugins/maven-help-plugin/)
maven-help-plugin是一个小巧的辅助工具，最简单的**help:system**可以打印所有可用的环境变量和Java系统属性。**help:effective-pom**和**help:effective-settings**最 为有用，它们分别打印项目的有效POM和有效settings，有效POM是指合并了所有父POM（包括Super POM）后的XML，当你不确定POM的某些信息从何而来时，就可以查看有效POM。有效settings同理，特别是当你发现自己配置的 settings.xml没有生效时，就可以用**help:effective-settings**来验证。此外，maven-help-plugin的describe目标可以帮助你描述任何一个Maven插件的信息，还有all-profiles目标和active-profiles目标帮助查看项目的Profile。

#### maven-release-plugin

[http://maven.apache.org/plugins/maven-release-plugin/](http://maven.apache.org/plugins/maven-release-plugin/)

maven-release-plugin的用途是帮助自动化项目版本发布，它依赖于POM中的SCM信息。**release:prepare**用来准备版本发布，具体的工作包括检查是否有未提交代码、检查是否有SNAPSHOT依赖、升级项目的SNAPSHOT版本至RELEASE版本、为项目打标签等等。**release:perform**则 是签出标签中的RELEASE源码，构建并发布。版本发布是非常琐碎的工作，它涉及了各种检查，而且由于该工作仅仅是偶尔需要，因此手动操作很容易遗漏一 些细节，maven-release-plugin让该工作变得非常快速简便，不易出错。maven-release-plugin的各种目标通常直接在 命令行调用，因为版本发布显然不是日常构建生命周期的一部分。

#### maven-resources-plugin

[http://maven.apache.org/plugins/maven-resources-plugin/](http://maven.apache.org/plugins/maven-resources-plugin/)

为了使项目结构更为清晰，Maven区别对待Java代码文件和资源文件，maven-compiler-plugin用来编译Java代码，maven-resources-plugin则用来处理资源文件。默认的主资源文件目录是`src/main/resources`，很多用户会需要添加额外的资源文件目录，这个时候就可以通过配置maven-resources-plugin来实现。此外，资源文件过滤也是Maven的一大特性，你可以在资源文件中使用*${propertyName}*形式的Maven属性，然后配置maven-resources-plugin开启对资源文件的过滤，之后就可以针对不同环境通过命令行或者Profile传入属性的值，以实现更为灵活的构建。

#### maven-surefire-plugin

[http://maven.apache.org/plugins/maven-surefire-plugin/](http://maven.apache.org/plugins/maven-surefire-plugin/)

可能是由于历史的原因，Maven 2/3中用于执行测试的插件不是maven-test-plugin，而是maven-surefire-plugin。其实大部分时间内，只要你的测试 类遵循通用的命令约定（以Test结尾、以TestCase结尾、或者以Test开头），就几乎不用知晓该插件的存在。然而在当你想要跳过测试、排除某些 测试类、或者使用一些TestNG特性的时候，了解maven-surefire-plugin的一些配置选项就很有用了。例如 **mvn test -Dtest=FooTest** 这样一条命令的效果是仅运行FooTest测试类，这是通过控制maven-surefire-plugin的test参数实现的。

#### build-helper-maven-plugin

[http://mojo.codehaus.org/build-helper-maven-plugin/](http://mojo.codehaus.org/build-helper-maven-plugin/)

Maven默认只允许指定一个主Java代码目录和一个测试Java代码目录，虽然这其实是个应当尽量遵守的约定，但偶尔你还是会希望能够指定多个 源码目录（例如为了应对遗留项目），build-helper-maven-plugin的add-source目标就是服务于这个目的，通常它被绑定到 默认生命周期的generate-sources阶段以添加额外的源码目录。需要强调的是，这种做法还是不推荐的，因为它破坏了 Maven的约定，而且可能会遇到其他严格遵守约定的插件工具无法正确识别额外的源码目录。

build-helper-maven-plugin的另一个非常有用的目标是attach-artifact，使用该目标你可以以classifier的形式选取部分项目文件生成附属构件，并同时install到本地仓库，也可以deploy到远程仓库。

#### exec-maven-plugin

[http://mojo.codehaus.org/exec-maven-plugin/](http://mojo.codehaus.org/exec-maven-plugin/)

exec-maven-plugin很好理解，顾名思义，它能让你运行任何本地的系统程序，在某些特定情况下，运行一个Maven外部的程序可能就是最简单的问题解决方案，这就是**exec:exec**的 用途，当然，该插件还允许你配置相关的程序运行参数。除了exec目标之外，exec-maven-plugin还提供了一个java目标，该目标要求你 提供一个mainClass参数，然后它能够利用当前项目的依赖作为classpath，在同一个JVM中运行该mainClass。有时候，为了简单的 演示一个命令行Java程序，你可以在POM中配置好exec-maven-plugin的相关运行参数，然后直接在命令运行**mvn exec:java** 以查看运行效果。

#### jetty-maven-plugin

[http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin](http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin)

在进行Web开发的时候，打开浏览器对应用进行手动的测试几乎是无法避免的，这种测试方法通常就是将项目打包成war文件，然后部署到Web容器 中，再启动容器进行验证，这显然十分耗时。为了帮助开发者节省时间，jetty-maven-plugin应运而生，它完全兼容 Maven项目的目录结构，能够周期性地检查源文件，一旦发现变更后自动更新到内置的Jetty Web容器中。做一些基本配置后（例如Web应用的contextPath和自动扫描变更的时间间隔），你只要执行 **mvn jetty:run** ，然后在IDE中修改代码，代码经IDE自动编译后产生变更，再由jetty-maven-plugin侦测到后更新至Jetty容器，这时你就可以直接 测试Web页面了。需要注意的是，jetty-maven-plugin并不是宿主于Apache或Codehaus的官方插件，因此使用的时候需要额外 的配置`settings.xml`的pluginGroups元素，将org.mortbay.jetty这个pluginGroup加入。

#### versions-maven-plugin

[http://mojo.codehaus.org/versions-maven-plugin/](http://mojo.codehaus.org/versions-maven-plugin/)

很多Maven用户遇到过这样一个问题，当项目包含大量模块的时候，为他们集体更新版本就变成一件烦人的事情，到底有没有自动化工具能帮助完成这件 事情呢？（当然你可以使用sed之类的文本操作工具，不过不在本文讨论范围）答案是肯定的，versions-maven- plugin提供了很多目标帮助你管理Maven项目的各种版本信息。例如最常用的，命令 **mvn versions:set -DnewVersion=1.1-SNAPSHOT** 就能帮助你把所有模块的版本更新到1.1-SNAPSHOT。该插件还提供了其他一些很有用的目标，display-dependency- updates能告诉你项目依赖有哪些可用的更新；类似的display-plugin-updates能告诉你可用的插件更新；然后use- latest-versions能自动帮你将所有依赖升级到最新版本。最后，如果你对所做的更改满意，则可以使用 **mvn versions:commit** 提交，不满意的话也可以使用 **mvn versions:revert** 进行撤销。

更多详情请参考[https://maven.apache.org/plugins/](https://maven.apache.org/plugins/)

### 常用Maven命令

| **生命周期**                    | **阶段描述**                                 |
| --------------------------- | ---------------------------------------- |
| mvn validate                | 验证项目是否正确，以及所有为了完整构建必要的信息是否可用             |
| mvn generate-sources        | 生成所有需要包含在编译过程中的源代码                       |
| mvn process-sources         | 处理源代码，比如过滤一些值                            |
| mvn generate-resources      | 生成所有需要包含在打包过程中的资源文件                      |
| mvn process-resources       | 复制并处理资源文件至目标目录，准备打包                      |
| mvn compile                 | 编译项目的源代码                                 |
| mvn process-classes         | 后处理编译生成的文件，例如对Java类进行字节码增强（bytecode enhancement） |
| mvn generate-test-sources   | 生成所有包含在测试编译过程中的测试源码                      |
| mvn process-test-sources    | 处理测试源码，比如过滤一些值                           |
| mvn generate-test-resources | 生成测试需要的资源文件                              |
| mvn process-test-resources  | 复制并处理测试资源文件至测试目标目录                       |
| mvn test-compile            | 编译测试源码至测试目标目录                            |
| mvn test                    | 使用合适的单元测试框架运行测试。这些测试应该不需要代码被打包或发布        |
| mvn prepare-package         | 在真正的打包之前，执行一些准备打包必要的操作。这通常会产生一个包的展开的处理过的版本（将会在Maven 2.1+中实现） |
| mvn package                 | 将编译好的代码打包成可分发的格式，如JAR，WAR，或者EAR          |
| mvn pre-integration-test    | 执行一些在集成测试运行之前需要的动作。如建立集成测试需要的环境          |
| mvn integration-test        | 如果有必要的话，处理包并发布至集成测试可以运行的环境               |
| mvn post-integration-test   | 执行一些在集成测试运行之后需要的动作。如清理集成测试环境。            |
| mvn verify                  | 执行所有检查，验证包是有效的，符合质量规范                    |
| mvn install                 | 安装包至本地仓库，以备本地的其它项目作为依赖使用                 |
| mvn deploy                  | 复制最终的包至远程仓库，共享给其它开发人员和项目（通常和一次正式的发布相关）   |

**使用参数**

`-Dmaven.test.skip=true`: 跳过单元测试(eg: mcn clean package -Dmaven.test.skip=true)

## 常见问题

dependencies和dependencyManagement，plugins和pluginManagement有什么区别？

dependencyManagement是表示依赖jar包的声明，即你在项目中的dependencyManagement下声明了依赖，maven不会加载该依赖，dependencyManagement声明可以被继承。

dependencyManagement的一个使用案例是当有父子项目的时候，父项目中可以利用dependencyManagement声明子项目中需要用到的依赖jar包，之后，当某个或者某几个子项目需要加载该插件的时候，就可以在子项目中dependencies节点只配置 groupId 和 artifactId就可以完成插件的引用。

dependencyManagement主要是为了统一管理插件，确保所有子项目使用的插件版本保持一致，类似的还有plugins和pluginManagement。

## 参考资料

- [官方文档](https://maven.apache.org/index.html)

- [http://www.oschina.net/question/158170_29368](http://www.oschina.net/question/158170_29368)

- [http://www.cnblogs.com/crazy-fox/archive/2012/02/09/2343722.html](http://www.cnblogs.com/crazy-fox/archive/2012/02/09/2343722.html)
