# Maven 教程之 settings.xml 详解

> 📓 本文已归档到：「[blog](https://github.com/dunwu/blog/blob/master/source/_posts/java/javatool/build/maven/)」

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
  - [settings.xml 有什么用？](#settingsxml-有什么用)
  - [settings.xml 文件位置](#settingsxml-文件位置)
  - [配置优先级](#配置优先级)
- [settings.xml 元素详解](#settingsxml-元素详解)
  - [顶级元素概览](#顶级元素概览)
  - [LocalRepository](#localrepository)
  - [InteractiveMode](#interactivemode)
  - [UsePluginRegistry](#usepluginregistry)
  - [Offline](#offline)
  - [PluginGroups](#plugingroups)
  - [Servers](#servers)
  - [Mirrors](#mirrors)
  - [Proxies](#proxies)
  - [Profiles](#profiles)
  - [ActiveProfiles](#activeprofiles)
- [参考资料](#参考资料)

<!-- /TOC -->

## 简介

### settings.xml 有什么用？

从 settings.xml 的文件名就可以看出，它是用来设置 maven 参数的配置文件。settings.xml 中包含类似本地仓储位置、修改远程仓储服务器、认证信息等配置。

- settings.xml 是 maven 的**全局配置文件**。
- pom.xml 文件是本地**项目配置文件**。

### settings.xml 文件位置

settings.xml 文件一般存在于两个位置：

- **全局配置** - `${maven.home}/conf/settings.xml`
- **用户配置** - `${user.home}/.m2/settings.xml`

> 🔔 注意：用户配置优先于全局配置。`${user.home}` 和和所有其他系统属性只能在 3.0+版本上使用。请注意 windows 和 Linux 使用变量的区别。

### 配置优先级

> 重要：**局部配置优先于全局配置**。

配置优先级从高到低：pom.xml > user settings > global settings

如果这些文件同时存在，在应用配置时，会合并它们的内容，如果有重复的配置，优先级高的配置会覆盖优先级低的。

## settings.xml 元素详解

### 顶级元素概览

下面列举了`settings.xml`中的顶级元素

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository/>
  <interactiveMode/>
  <usePluginRegistry/>
  <offline/>
  <pluginGroups/>
  <servers/>
  <mirrors/>
  <proxies/>
  <profiles/>
  <activeProfiles/>
</settings>
```

### LocalRepository

**作用**：该值表示构建系统本地仓库的路径。

其默认值：\~/.m2/repository。

```xml
<localRepository>${user.home}/.m2/repository</localRepository>
```

### InteractiveMode

**作用**：表示 maven 是否需要和用户交互以获得输入。

如果 maven 需要和用户交互以获得输入，则设置成 true，反之则应为 false。默认为 true。

```xml
<interactiveMode>true</interactiveMode>
```

### UsePluginRegistry

**作用**：maven 是否需要使用 plugin-registry.xml 文件来管理插件版本。

如果需要让 maven 使用文件\~/.m2/plugin-registry.xml 来管理插件版本，则设为 true。默认为 false。

```xml
<usePluginRegistry>false</usePluginRegistry>
```

### Offline

**作用**：表示 maven 是否需要在离线模式下运行。

如果构建系统需要在离线模式下运行，则为 true，默认为 false。

当由于网络设置原因或者安全因素，构建服务器不能连接远程仓库的时候，该配置就十分有用。

```xml
<offline>false</offline>
```

### PluginGroups

**作用**：当插件的组织 id（groupId）没有显式提供时，供搜寻插件组织 Id（groupId）的列表。

该元素包含一个 pluginGroup 元素列表，每个子元素包含了一个组织 Id（groupId）。

当我们使用某个插件，并且没有在命令行为其提供组织 Id（groupId）的时候，Maven 就会使用该列表。默认情况下该列表包含了 `org.apache.maven.plugins` 和 `org.codehaus.mojo`。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <pluginGroups>
    <!--plugin的组织Id（groupId） -->
    <pluginGroup>org.codehaus.mojo</pluginGroup>
  </pluginGroups>
  ...
</settings>
```

### Servers

**作用**：一般，仓库的下载和部署是在 pom.xml 文件中的 `repositories` 和 `distributionManagement` 元素中定义的。然而，一般类似用户名、密码（**有些仓库访问是需要安全认证的**）等信息不应该在 pom.xml 文件中配置，这些信息可以配置在 `settings.xml` 中。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <!--配置服务端的一些设置。一些设置如安全证书不应该和pom.xml一起分发。这种类型的信息应该存在于构建服务器上的settings.xml文件中。 -->
  <servers>
    <!--服务器元素包含配置服务器时需要的信息 -->
    <server>
      <!--这是server的id（注意不是用户登陆的id），该id与distributionManagement中repository元素的id相匹配。 -->
      <id>server001</id>
      <!--鉴权用户名。鉴权用户名和鉴权密码表示服务器认证所需要的登录名和密码。 -->
      <username>my_login</username>
      <!--鉴权密码 。鉴权用户名和鉴权密码表示服务器认证所需要的登录名和密码。密码加密功能已被添加到2.1.0 +。详情请访问密码加密页面 -->
      <password>my_password</password>
      <!--鉴权时使用的私钥位置。和前两个元素类似，私钥位置和私钥密码指定了一个私钥的路径（默认是${user.home}/.ssh/id_dsa）以及如果需要的话，一个密语。将来passphrase和password元素可能会被提取到外部，但目前它们必须在settings.xml文件以纯文本的形式声明。 -->
      <privateKey>${usr.home}/.ssh/id_dsa</privateKey>
      <!--鉴权时使用的私钥密码。 -->
      <passphrase>some_passphrase</passphrase>
      <!--文件被创建时的权限。如果在部署的时候会创建一个仓库文件或者目录，这时候就可以使用权限（permission）。这两个元素合法的值是一个三位数字，其对应了unix文件系统的权限，如664，或者775。 -->
      <filePermissions>664</filePermissions>
      <!--目录被创建时的权限。 -->
      <directoryPermissions>775</directoryPermissions>
    </server>
  </servers>
  ...
</settings>
```

### Mirrors

**作用**：为仓库列表配置的下载镜像列表。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <mirrors>
    <!-- 给定仓库的下载镜像。 -->
    <mirror>
      <!-- 该镜像的唯一标识符。id用来区分不同的mirror元素。 -->
      <id>planetmirror.com</id>
      <!-- 镜像名称 -->
      <name>PlanetMirror Australia</name>
      <!-- 该镜像的URL。构建系统会优先考虑使用该URL，而非使用默认的服务器URL。 -->
      <url>http://downloads.planetmirror.com/pub/maven2</url>
      <!-- 被镜像的服务器的id。例如，如果我们要设置了一个Maven中央仓库（http://repo.maven.apache.org/maven2/）的镜像，就需要将该元素设置成central。这必须和中央仓库的id central完全一致。 -->
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
  ...
</settings>
```

### Proxies

**作用**：用来配置不同的代理。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <proxies>
    <!--代理元素包含配置代理时需要的信息 -->
    <proxy>
      <!--代理的唯一定义符，用来区分不同的代理元素。 -->
      <id>myproxy</id>
      <!--该代理是否是激活的那个。true则激活代理。当我们声明了一组代理，而某个时候只需要激活一个代理的时候，该元素就可以派上用处。 -->
      <active>true</active>
      <!--代理的协议。 协议://主机名:端口，分隔成离散的元素以方便配置。 -->
      <protocol>http</protocol>
      <!--代理的主机名。协议://主机名:端口，分隔成离散的元素以方便配置。 -->
      <host>proxy.somewhere.com</host>
      <!--代理的端口。协议://主机名:端口，分隔成离散的元素以方便配置。 -->
      <port>8080</port>
      <!--代理的用户名，用户名和密码表示代理服务器认证的登录名和密码。 -->
      <username>proxyuser</username>
      <!--代理的密码，用户名和密码表示代理服务器认证的登录名和密码。 -->
      <password>somepassword</password>
      <!--不该被代理的主机名列表。该列表的分隔符由代理服务器指定；例子中使用了竖线分隔符，使用逗号分隔也很常见。 -->
      <nonProxyHosts>*.google.com|ibiblio.org</nonProxyHosts>
    </proxy>
  </proxies>
  ...
</settings>
```

### Profiles

**作用**：根据环境参数来调整构建配置的列表。

`settings.xml` 中的 `profile` 元素是 `pom.xml` 中 `profile` 元素的**裁剪版本**。

它包含了`id`、`activation`、`repositories`、`pluginRepositories` 和 `properties` 元素。这里的 profile 元素只包含这五个子元素是因为这里只关心构建系统这个整体（这正是 settings.xml 文件的角色定位），而非单独的项目对象模型设置。如果一个 `settings.xml` 中的 `profile` 被激活，它的值会覆盖任何其它定义在 `pom.xml` 中带有相同 id 的 `profile`。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <profiles>
    <profile>
      <!-- profile的唯一标识 -->
      <id>test</id>
      <!-- 自动触发profile的条件逻辑 -->
      <activation />
      <!-- 扩展属性列表 -->
      <properties />
      <!-- 远程仓库列表 -->
      <repositories />
      <!-- 插件仓库列表 -->
      <pluginRepositories />
    </profile>
  </profiles>
  ...
</settings>
```

#### Activation

**作用**：自动触发 `profile` 的条件逻辑。

如 `pom.xml` 中的 `profile` 一样，`profile` 的作用在于它能够在某些特定的环境中自动使用某些特定的值；这些环境通过 `activation` 元素指定。
`activation` 元素并不是激活 `profile` 的唯一方式。`settings.xml` 文件中的 `activeProfile` 元素可以包含 `profile` 的 `id`。`profile` 也可以通过在命令行，使用 `-P` 标记和逗号分隔的列表来显式的激活（如，`-P test`）。

```xml
<activation>
  <!--profile默认是否激活的标识 -->
  <activeByDefault>false</activeByDefault>
  <!--当匹配的jdk被检测到，profile被激活。例如，1.4激活JDK1.4，1.4.0_2，而!1.4激活所有版本不是以1.4开头的JDK。 -->
  <jdk>1.5</jdk>
  <!--当匹配的操作系统属性被检测到，profile被激活。os元素可以定义一些操作系统相关的属性。 -->
  <os>
    <!--激活profile的操作系统的名字 -->
    <name>Windows XP</name>
    <!--激活profile的操作系统所属家族(如 'windows') -->
    <family>Windows</family>
    <!--激活profile的操作系统体系结构 -->
    <arch>x86</arch>
    <!--激活profile的操作系统版本 -->
    <version>5.1.2600</version>
  </os>
  <!--如果Maven检测到某一个属性（其值可以在POM中通过${name}引用），其拥有对应的name = 值，Profile就会被激活。如果值字段是空的，那么存在属性名称字段就会激活profile，否则按区分大小写方式匹配属性值字段 -->
  <property>
    <!--激活profile的属性的名称 -->
    <name>mavenVersion</name>
    <!--激活profile的属性的值 -->
    <value>2.0.3</value>
  </property>
  <!--提供一个文件名，通过检测该文件的存在或不存在来激活profile。missing检查文件是否存在，如果不存在则激活profile。另一方面，exists则会检查文件是否存在，如果存在则激活profile。 -->
  <file>
    <!--如果指定的文件存在，则激活profile。 -->
    <exists>${basedir}/file2.properties</exists>
    <!--如果指定的文件不存在，则激活profile。 -->
    <missing>${basedir}/file1.properties</missing>
  </file>
</activation>
```

> 注：在 maven 工程的 pom.xml 所在目录下执行 `mvn help:active-profiles` 命令可以查看中央仓储的 profile 是否在工程中生效。

#### properties

**作用**：对应`profile`的扩展属性列表。

maven 属性和 ant 中的属性一样，可以用来存放一些值。这些值可以在 `pom.xml` 中的任何地方使用标记\${X}来使用，这里 X 是指属性的名称。属性有五种不同的形式，并且都能在 settings.xml 文件中访问。

```xml
<!--
  1. env.X: 在一个变量前加上"env."的前缀，会返回一个shell环境变量。例如,"env.PATH"指代了$path环境变量（在Windows上是%PATH%）。
  2. project.x：指代了POM中对应的元素值。例如: <project><version>1.0</version></project>通过${project.version}获得version的值。
  3. settings.x: 指代了settings.xml中对应元素的值。例如：<settings><offline>false</offline></settings>通过 ${settings.offline}获得offline的值。
  4. Java System Properties: 所有可通过java.lang.System.getProperties()访问的属性都能在POM中使用该形式访问，例如 ${java.home}。
  5. x: 在<properties/>元素中，或者外部文件中设置，以${someVar}的形式使用。
 -->
<properties>
  <user.install>${user.home}/our-project</user.install>
</properties>
```

> 注：如果该 profile 被激活，则可以在`pom.xml`中使用\${user.install}。

#### Repositories

**作用**：远程仓库列表，它是 maven 用来填充构建系统本地仓库所使用的一组远程仓库。

```xml
<repositories>
  <!--包含需要连接到远程仓库的信息 -->
  <repository>
    <!--远程仓库唯一标识 -->
    <id>codehausSnapshots</id>
    <!--远程仓库名称 -->
    <name>Codehaus Snapshots</name>
    <!--如何处理远程仓库里发布版本的下载 -->
    <releases>
      <!--true或者false表示该仓库是否为下载某种类型构件（发布版，快照版）开启。 -->
      <enabled>false</enabled>
      <!--该元素指定更新发生的频率。Maven会比较本地POM和远程POM的时间戳。这里的选项是：always（一直），daily（默认，每日），interval：X（这里X是以分钟为单位的时间间隔），或者never（从不）。 -->
      <updatePolicy>always</updatePolicy>
      <!--当Maven验证构件校验文件失败时该怎么做-ignore（忽略），fail（失败），或者warn（警告）。 -->
      <checksumPolicy>warn</checksumPolicy>
    </releases>
    <!--如何处理远程仓库里快照版本的下载。有了releases和snapshots这两组配置，POM就可以在每个单独的仓库中，为每种类型的构件采取不同的策略。例如，可能有人会决定只为开发目的开启对快照版本下载的支持。参见repositories/repository/releases元素 -->
    <snapshots>
      <enabled />
      <updatePolicy />
      <checksumPolicy />
    </snapshots>
    <!--远程仓库URL，按protocol://hostname/path形式 -->
    <url>http://snapshots.maven.codehaus.org/maven2</url>
    <!--用于定位和排序构件的仓库布局类型-可以是default（默认）或者legacy（遗留）。Maven 2为其仓库提供了一个默认的布局；然而，Maven 1.x有一种不同的布局。我们可以使用该元素指定布局是default（默认）还是legacy（遗留）。 -->
    <layout>default</layout>
  </repository>
</repositories>
```

#### pluginRepositories

**作用**：发现插件的远程仓库列表。

和 `repository` 类似，只是 `repository` 是管理 jar 包依赖的仓库，`pluginRepositories` 则是管理插件的仓库。
maven 插件是一种特殊类型的构件。由于这个原因，插件仓库独立于其它仓库。`pluginRepositories` 元素的结构和 `repositories` 元素的结构类似。每个 `pluginRepository` 元素指定一个 Maven 可以用来寻找新插件的远程地址。

```xml
<pluginRepositories>
  <!-- 包含需要连接到远程插件仓库的信息.参见profiles/profile/repositories/repository元素的说明 -->
  <pluginRepository>
    <releases>
      <enabled />
      <updatePolicy />
      <checksumPolicy />
    </releases>
    <snapshots>
      <enabled />
      <updatePolicy />
      <checksumPolicy />
    </snapshots>
    <id />
    <name />
    <url />
    <layout />
  </pluginRepository>
</pluginRepositories>
```

### ActiveProfiles

**作用**：手动激活 profiles 的列表，按照`profile`被应用的顺序定义`activeProfile`。

该元素包含了一组 `activeProfile` 元素，每个 `activeProfile` 都含有一个 profile id。任何在 `activeProfile` 中定义的 profile id，不论环境设置如何，其对应的 `profile` 都会被激活。如果没有匹配的 `profile`，则什么都不会发生。

例如，env-test 是一个 activeProfile，则在 pom.xml（或者 profile.xml）中对应 id 的 profile 会被激活。如果运行过程中找不到这样一个 profile，Maven 则会像往常一样运行。

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <activeProfiles>
    <!-- 要激活的profile id -->
    <activeProfile>env-test</activeProfile>
  </activeProfiles>
  ...
</settings>
```

至此，maven settings.xml 中的标签都讲解完毕，希望对大家有所帮助。

## 参考资料

- [maven 官方文档之 settings](https://maven.apache.org/settings.html)
