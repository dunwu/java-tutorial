# javatool-server

> 本示例代码主要展示嵌入式服务器和 web 项目的集成。
>
> 你可以在本项目中体验嵌入式 Tomcat 和嵌入式 Jetty 的启动方式。
>

**版本**

* JDK：1.8
* Tomcat：8.5.24

## Tomcat

### Windows 启动

执行 `io.github.dunwu.javatool.server.SimpleTomcatServer#main` 方法。

或执行 `io.github.dunwu.javatool.server.TomcatServer.main` 方法。

启动后，访问 http://localhost:8080/javatool-server/

### 插件启动嵌入式 Tomcat

由于插件很久没有更新（最新版本发布时间：2013-11-11），目前只能找到 Tomcat6 、Tomcat7 插件，所以弃用。

### 脚本启动

> 本项目添加了脚本启动范例。
>
> 脚本代码全在 [`scripts`](https://github.com/dunwu/JavaStack/tree/master/scripts) 目录下。

* 初始化

```sh
wget https://raw.githubusercontent.com/dunwu/JavaStack/master/scripts/init.sh
chmod 777 init.sh
./init.sh
```

* 发布

```
cd /home/zp/source/JavaStack/scripts
./javatool-server-release.sh master develop
```

## Jetty

待添加。。。
