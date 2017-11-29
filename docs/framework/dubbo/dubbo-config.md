# Dubbo 配置

## 简介

### 配置方式

Dubbo 支持四中配置方式：

- XML 配置
- 属性配置
- API 配置
- 注解配置

我认为根据自己实际需要去掌握配置方式即可，没必要全都了解。更多内容可以参考：[Dubbo 官方用户手册](https://dubbo.gitbooks.io/dubbo-user-book/)

在这里，只记录我使用到的 xml 配置方式。

### 配置分类

所有配置项分为三大类

- 服务发现：表示该配置项用于服务的注册与发现，目的是让消费方找到提供方。

- 服务治理：表示该配置项用于治理服务间的关系，或为开发测试提供便利条件。

- 性能调优：表示该配置项用于调优性能，不同的选项对性能会产生影响。

所有配置最终都将转换为 URL 表示，并由服务提供方生成，经注册中心传递给消费方，各属性对应 URL 的参数，参见配置项一览表中的 "对应URL参数" 列。

> **注意**
>
> 只有 group，interface，version 是服务的匹配条件，三者决定是不是同一个服务，其它配置项均为调优和治理参数。
>
> **URL 格式**
>
> `protocol://username:password@host:port/path?key=value&key=value`

## xml 配置

### provider.xml 示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">  
    <dubbo:application name="hello-world-app"  />  
    <dubbo:registry address="multicast://224.5.6.7:1234" />  
    <dubbo:protocol name="dubbo" port="20880" />  
    <dubbo:service interface="com.alibaba.dubbo.demo.DemoService" ref="demoServiceLocal" />  
    <dubbo:reference id="demoServiceRemote" interface="com.alibaba.dubbo.demo.DemoService" />  
</beans>
```

所有标签都支持自定义参数，用于不同扩展点实现的特殊配置，如：

```xml
<dubbo:protocol name="jms">
    <dubbo:parameter key="queue" value="your_queue" />
</dubbo:protocol>
```

或：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">  
    <dubbo:protocol name="jms" p:queue="your_queue" />  
</beans>
```

### 配置之间的关系

![image.png](http://upload-images.jianshu.io/upload_images/3101171-5c7371e9ab5999e7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

| 标签                     | 用途     | 解释                                       |
| ---------------------- | ------ | ---------------------------------------- |
| `<dubbo:service/>`     | 服务配置   | 用于暴露一个服务，定义服务的元信息，一个服务可以用多个协议暴露，一个服务也可以注册到多个注册中心 |
| `<dubbo:reference/>`   | 引用配置   | 用于创建一个远程服务代理，一个引用可以指向多个注册中心              |
| `<dubbo:protocol/>`    | 协议配置   | 用于配置提供服务的协议信息，协议由提供方指定，消费方被动接受           |
| `<dubbo:application/>` | 应用配置   | 用于配置当前应用信息，不管该应用是提供者还是消费者                |
| `<dubbo:module/>`      | 模块配置   | 用于配置当前模块信息，可选                            |
| `<dubbo:registry/>`    | 注册中心配置 | 用于配置连接注册中心相关信息                           |
| `<dubbo:monitor/>`     | 监控中心配置 | 用于配置连接监控中心相关信息，可选                        |
| `<dubbo:provider/>`    | 提供方配置  | 当 ProtocolConfig 和 ServiceConfig 某属性没有配置时，采用此缺省值，可选 |
| `<dubbo:consumer/>`    | 消费方配置  | 当 ReferenceConfig 某属性没有配置时，采用此缺省值，可选     |
| `<dubbo:method/>`      | 方法配置   | 用于 ServiceConfig 和 ReferenceConfig 指定方法级的配置信息 |
| `<dubbo:argument/>`    | 参数配置   | 用于指定方法参数配置                               |

### 配置覆盖关系

以 timeout 为例，显示了配置的查找顺序，其它 retries, loadbalance, actives 等类似：

- 方法级优先，接口级次之，全局配置再次之。
- 如果级别一样，则消费方优先，提供方次之。

其中，服务提供方配置，通过 URL 经由注册中心传递给消费方。

![image.png](http://upload-images.jianshu.io/upload_images/3101171-0b046a7b9ac95ff1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

建议由服务提供方设置超时，因为一个方法需要执行多长时间，服务提供方更清楚，如果一个消费方同时引用多个服务，就不需要关心每个服务的超时设置。

理论上 ReferenceConfig 的非服务标识配置，在 ConsumerConfig，ServiceConfig, ProviderConfig 均可以缺省配置。

## 资料

[Dubbo 配置](https://dubbo.gitbooks.io/dubbo-user-book/configuration/)

[Dubbo 官方 schema 配置参考手册](https://dubbo.gitbooks.io/dubbo-user-book/references/xml/introduction.html)