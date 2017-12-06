# 发布项目到中央仓库

## 创建工单

在发布Java包到Maven中央仓库首先需要在[https://issues.sonatype.org/secure/Dashboard.jspa](https://www.iteblog.com/redirect.php?url=aHR0cHM6Ly9pc3N1ZXMuc29uYXR5cGUub3JnL3NlY3VyZS9EYXNoYm9hcmQuanNwYQ==&article=true)网站创建一个工单(Issues)，第一次使用这个网站的时候需要注册自己的帐号（这个帐号和密码需要记住，后面会用到），之后创建自己的Issue。

然后根据你Java包的功能分别写上`Summary`、`Description`、`Group Id`、`SCM url`以及`Project URL`等必要信息，可以参见我之前创建的Issue：[https://issues.sonatype.org/browse/OSSRH-33765](https://issues.sonatype.org/browse/OSSRH-33765)。创建完之后需要等待Sonatype的工作人员审核处理，审核时间还是很快的，我的审核差不多等待了两小时。当Issue的Status变为`RESOLVED`后，就可以进行下一步操作了。

> 注：如果你的Group Id填写的是自己的网站（我的就是这种情况），Sonatype的工作人员会询问你那个Group Id是不是你的域名，你只需要在上面回答是就行，然后就会通过审核。

## gpg

### 安装、下载 gpg

上面创建的issuce经过审核之后，我们可以使用gpg生成密钥对，这里分两种情况：

1. 如果使用的是Windows，可以到[https://www.gpg4win.org/download.html](https://www.iteblog.com/redirect.php?url=aHR0cHM6Ly93d3cuZ3BnNHdpbi5vcmcvZG93bmxvYWQuaHRtbA==&article=true)下载gpg4win，推荐使用 Gpg4win-Vanilla 2.3.3版本，因为它仅包括 GnuPG，这个工具才是我们所需要的；
2. 如果使用的是Linux，可以通过`yum install gpg`命令安装gpg。

之后可以通过`gpg --version`命令查看是否安装成功，如果出现版本等信息说明安装成功了。

### 生成密钥

安装 gpg 成功后，执行命令：

```bash
$ gpg --gen-key
```

按照提示信息依次设置好名字、邮箱等信息。不过输入Passphrase的值需要记住，这个相当于密钥的密码，发布过程中进行签名操作的时候会用到。

到这里我们就设置好密钥对了。上面代码中导数第四行的`B15C5AA3`需要记住，其相当于我们生成的key，后面会用到。

### 上传keys

```bash
$ gpg --list-keys
xxxx/gnupg/pubring.gpg
--------------------------------------------------------
pub   2048R/D416FE08 2017-12-06
uid       [ultimate] Zhang Peng (author) <forbreak@163.com>
sub   2048R/CBD8FA39 2017-12-06


$ gpg --keyserver hkp://keyserver.ubuntu.com:11371 --send-keys D416FE08
gpg: sending key D416FE08 to hkp server keyserver.ubuntu.com
```

## settings 配置

修改 maven 的 settings.xml 文件

```xml
<servers>
  <server>
    <id>sonatype-nexus-snapshots</id>
    <username>Sonatype网站的账号</username>
    <password>Sonatype网站的密码</password>
  </server>
  <server>
    <id>sonatype-nexus-staging</id>
    <username>Sonatype网站的账号</username>
    <password>Sonatype网站的密码</password>
  </server>
</servers>
```

## pom 配置

licenses、scm、developers 配置：

```xml
<licenses>
  <license>
    <name>The Apache Software License, Version 2.0</name>
    <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    <distribution>repo</distribution>
  </license>
</licenses>

<developers>
  <developer>
    <name>VictorZhang</name>
    <email>forbreak@163.com</email>
    <url>https://github.com/dunwu</url>
  </developer>
</developers>

<scm>
  <url>https://github.com/dunwu/tent</url>
  <connection>git@github.com:dunwu/tent.git</connection>
  <developerConnection>https://github.com/dunwu</developerConnection>
</scm>
```

distributionManagement 配置

```xml
<distributionManagement>
  <snapshotRepository>
    <id>nexus</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
  </snapshotRepository>
  <repository>
    <id>nexus</id>
    <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
  </repository>
</distributionManagement>	
```

profiles 配置

```xml
<profiles>
    <profile>
      <id>release</id>
      <build>
        <plugins>
          <plugin>
            <groupId>org.sonatype.plugins</groupId>
            <artifactId>nexus-staging-maven-plugin</artifactId>
            <version>1.6.7</version>
            <extensions>true</extensions>
            <configuration>
              <serverId>nexus</serverId>
              <nexusUrl>https://oss.sonatype.org/</nexusUrl>
              <autoReleaseAfterClose>true</autoReleaseAfterClose>
            </configuration>
          </plugin>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <version>2.10.3</version>
            <configuration>
              <failOnError>false</failOnError>
              <quiet>true</quiet>
            </configuration>
            <executions>
              <execution>
                <id>attach-javadocs</id>
                <goals>
                  <goal>jar</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-gpg-plugin</artifactId>
            <version>1.6</version>
            <executions>
              <execution>
                <id>sign-artifacts</id>
                <phase>verify</phase>
                <goals>
                  <goal>sign</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
        </plugins>
      </build>
    </profile>
  </profiles>
```

## 部署和发布

```bash
$ mvn clean deploy -P sonatype-oss-release -Darguments="gpg.passphrase=设置gpg设置密钥时候输入的Passphrase" -Dmaven.test.skip=true
```