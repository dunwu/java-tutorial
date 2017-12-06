---
title: Maven 排错
date: 2017/11/10
categories:
- javatool
tags:
- java
- tool
- build
---

> 本文收录我在开发过程中遇到的各种 maven 问题，持续更新。。。

## 问题

### IDEA 修改 JDK 版本后编译报错

**错误现象**

修改 JDK 版本，指定 maven-compiler-plugin 的 source 和 target 为 1.8 。

然后，在 Intellij IDEA 中执行 maven 指令，报错：

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.0:compile (default-compile) on project apollo-common: Fatal error compiling: 无效的目标版本： 1.8 -> [Help 1]
...
```

**错误原因**

maven 的 JDK 源与指定的 JDK 编译版本不符。

**排错手段**

- **查看 Project Settings**

Project SDK 是否正确

![image.png](http://upload-images.jianshu.io/upload_images/3101171-d1d56489ca196361.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

SDK 路径是否正确

![image.png](http://upload-images.jianshu.io/upload_images/3101171-6aa4d32f6ea8833a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2. **查看 Settings > Maven 的配置**

JDK for importer 是否正确

![image.png](http://upload-images.jianshu.io/upload_images/3101171-6f76b18625f7fd1d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Runner 是否正确

![image.png](http://upload-images.jianshu.io/upload_images/3101171-a7973d82931236a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
