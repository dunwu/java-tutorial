# Memcached 应用指南

## 一、Memcached 简介

Memcached 是一个自由开源的，高性能，分布式内存对象缓存系统。

Memcached 是一种基于内存的 key-value 存储，用来存储小块的任意数据（字符串、对象）。这些数据可以是数据库调用、API 调用或者是页面渲染的结果。

Memcached 简洁而强大。它的简洁设计便于快速开发，减轻开发难度，解决了大数据量缓存的很多问题。它的 API 兼容大部分流行的开发语言。本质上，它是一个简洁的 key-value 存储系统。

### Memcached 特性

memcached 作为高速运行的分布式缓存服务器，具有以下的特点。

- 协议简单
- 基于 libevent 的事件处理
- 内置内存存储方式
- memcached 不互相通信的分布式

## 二、Memcached 命令

可以通过 telnet 命令并指定主机ip和端口来连接 Memcached 服务。

```
telnet 127.0.0.1 11211

Trying 127.0.0.1...
Connected to 127.0.0.1.
Escape character is '^]'.
set foo 0 0 3                                                   保存命令
bar                                                             数据
STORED                                                          结果
get foo                                                         取得命令
VALUE foo 0 3                                                   数据
bar                                                             数据
END                                                             结束行
quit                                                            退出
```

## 三、Java 连接 Memcached

使用 Java 程序连接 Memcached，需要在你的 classpath 中添加 Memcached jar 包。

本站 jar 包下载地址：[spymemcached-2.10.3.jar](https://www.runoob.com/try/download/spymemcached-2.10.3.jar)。

Google Code jar 包下载地址：[spymemcached-2.10.3.jar](http://code.google.com/p/spymemcached/downloads/list)（需要科学上网）。

以下程序假定 Memcached 服务的主机为 127.0.0.1，端口为 11211。

```java
import net.spy.memcached.MemcachedClient;
import java.net.*;
 
 
public class MemcachedJava {
   public static void main(String[] args) {
      try{
         // 本地连接 Memcached 服务
         MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
         System.out.println("Connection to server sucessful.");
         
         // 关闭连接
         mcc.shutdown();
         
      }catch(Exception ex){
         System.out.println( ex.getMessage() );
      }
   }
}
```

## 参考资料

- [Memcached 官网](https://memcached.org/)
- [Memcached Github](https://github.com/memcached/memcached/)
- [Memcached 教程](https://www.runoob.com/memcached/memcached-tutorial.html)
