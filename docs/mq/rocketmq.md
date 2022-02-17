# RocketMQ

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
- [安装](#安装)
  - [环境要求](#环境要求)
  - [下载解压](#下载解压)
  - [启动 Name Server](#启动-name-server)
  - [启动 Broker](#启动-broker)
  - [收发消息](#收发消息)
  - [关闭服务器](#关闭服务器)
- [API](#api)
  - [Producer](#producer)
  - [Consumer](#consumer)
  - [FAQ](#faq)
- [架构](#架构)
  - [NameServer](#nameserver)
  - [Broker](#broker)
  - [Producer](#producer-1)
  - [Consumer](#consumer-1)
- [原理](#原理)
  - [顺序消息](#顺序消息)
  - [消息重复](#消息重复)
  - [事务消息](#事务消息)
- [参考资料](#参考资料)

<!-- /TOC -->

## 简介

RocketMQ 是一款开源的分布式消息队列，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。

RocketMQ 被阿里巴巴捐赠给 Apache，成为 Apache 的孵化项目。

![img](https://raw.githubusercontent.com/dunwu/images/dev/cs/java/javaweb/distributed/mq/rocketmq/rmq-model.png)

RocketMQ 有以下核心概念：

- **Producer** - 将业务应用程序系统生成的消息发送给代理。RocketMQ 提供多种发送范例：同步，异步和单向。
  - **Producer Group** - 具有相同角色的 Producer 组合在一起。如果原始 Producer 在事务之后崩溃，则代理可以联系同一 Producer 组的不同 Producer 实例以提交或回滚事务。**_警告：考虑到提供的 Producer 在发送消息方面足够强大，每个 Producer 组只允许一个实例，以避免不必要的生成器实例初始化。_**
- **Consumer** - Consumer 从 Broker 那里获取消息并将其提供给应用程序。从用户应用的角度来看，提供了两种类型的 Consumer：
  - **PullConsumer** - PullConsumer 积极地从 Broker 那里获取消息。一旦提取了批量消息，用户应用程序就会启动消费过程。
  - **PushConsumer** - PushConsumer 封装消息提取，消费进度并维护其他内部工作，为最终用户留下回调接口，这个借口会在消息到达时被执行。
  - **Consumer Group** - 完全相同角色的 Consumer 被组合在一起并命名为 Consumer Group。Consumer Group 是一个很好的概念，在消息消费方面实现负载平衡和容错目标非常容易。**_警告：Consumer Group 中的 Consumer 实例必须具有完全相同的主题订阅。_**
- **Broker** - Broker 是 RocketMQ 的主要组成部分。它接收从 Producer 发送的消息，存储它们并准备处理来自 Consumer 的消费请求。它还存储与消息相关的元数据，包括 Consumer Group，消耗进度偏移和主题/队列信息。
- Name Server - 充当路由信息提供者。Producer/Consumer 客户查找主题以查找相应的 Broker 列表。
- **Topic** - 是 Producer 传递消息和 Consumer 提取消息的类别。
- **Message** - 是要传递的信息。消息必须有一个主题，可以将其解释为您要发送给的邮件地址。消息还可以具有可选 Tag 和额外的键值对。例如，您可以为消息设置业务密钥，并在代理服务器上查找消息以诊断开发期间的问题。
  - **Message Queue** - 主题被划分为一个或多个子主题“消息队列”。
  - **Tag** - 即子主题，为用户提供了额外的灵活性。对于 Tag，来自同一业务模块的具有不同目的的消息可以具有相同的主题和不同的 Tag。

## 安装

### 环境要求

- 推荐 64 位操作系统：Linux/Unix/Mac
- 64bit JDK 1.8+
- Maven 3.2.x
- Git

### 下载解压

进入官方下载地址：<https://rocketmq.apache.org/dowloading/releases/，选择合适版本>

建议选择 binary 版本。

解压到本地：

```bash
> unzip rocketmq-all-4.2.0-source-release.zip
> cd rocketmq-all-4.2.0/
```

### 启动 Name Server

```bash
> nohup sh bin/mqnamesrv &
> tail -f ~/logs/rocketmqlogs/namesrv.log
The Name Server boot success...
```

### 启动 Broker

```bash
> nohup sh bin/mqbroker -n localhost:9876 -c conf/broker.conf &
> tail -f ~/logs/rocketmqlogs/broker.log
The broker[%s, 172.30.30.233:10911] boot success...
```

### 收发消息

执行收发消息操作之前，不许告诉客户端命名服务器的位置。在 RocketMQ 中有多种方法来实现这个目的。这里，我们使用最简单的方法——设置环境变量 `NAMESRV_ADDR` ：

```bash
> export NAMESRV_ADDR=localhost:9876
> sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
SendResult [sendStatus=SEND_OK, msgId= ...

> sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
ConsumeMessageThread_%d Receive New Messages: [MessageExt...
```

### 关闭服务器

```bash
> sh bin/mqshutdown broker
The mqbroker(36695) is running...
Send shutdown request to mqbroker(36695) OK

> sh bin/mqshutdown namesrv
The mqnamesrv(36664) is running...
Send shutdown request to mqnamesrv(36664) OK
```

## API

首先在项目中引入 maven 依赖：

```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client</artifactId>
    <version>4.2.0</version>
</dependency>
```

### Producer

Producer 在 RocketMQ 中负责发送消息。

RocketMQ 有三种消息发送方式：

- 可靠的同步发送
- 可靠的异步发送
- 单项发送

#### 可靠的同步发送

可靠的同步传输用于广泛的场景，如重要的通知消息，短信通知，短信营销系统等。

```java
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
            DefaultMQProducer("please_rename_unique_group_name");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ " +
                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
```

#### 可靠的异步发送

异步传输通常用于响应时间敏感的业务场景。

```java
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 100; i++) {
                final int index = i;
                //Create a message instance, specifying topic, tag and message body.
                Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                    }
                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
```

#### 单向传输

单向传输用于需要中等可靠性的情况，例如日志收集。

```java
public class OnewayProducer {
    public static void main(String[] args) throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ " +
                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);

        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
```

### Consumer

Consumer 在 RocketMQ 中负责接收消息。

```java
public class OrderedConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr(RocketConfig.HOST);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicTest", "TagA || TagC || TagD");

        consumer.registerMessageListener(new MessageListenerOrderly() {

            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                this.consumeTimes.incrementAndGet();
                if ((this.consumeTimes.get() % 2) == 0) {
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if ((this.consumeTimes.get() % 3) == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;

            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
```

### FAQ

#### connect to `<172.17.0.1:10909>` failed

启动后，Producer 客户端连接 RocketMQ 时报错：

```java
org.apache.rocketmq.remoting.exception.RemotingConnectException: connect to <172.17.0.1:10909> failed
    at org.apache.rocketmq.remoting.netty.NettyRemotingClient.invokeSync(NettyRemotingClient.java:357)
    at org.apache.rocketmq.client.impl.MQClientAPIImpl.sendMessageSync(MQClientAPIImpl.java:343)
    at org.apache.rocketmq.client.impl.MQClientAPIImpl.sendMessage(MQClientAPIImpl.java:327)
    at org.apache.rocketmq.client.impl.MQClientAPIImpl.sendMessage(MQClientAPIImpl.java:290)
    at org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl.sendKernelImpl(DefaultMQProducerImpl.java:688)
    at org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl.sendSelectImpl(DefaultMQProducerImpl.java:901)
    at org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl.send(DefaultMQProducerImpl.java:878)
    at org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl.send(DefaultMQProducerImpl.java:873)
    at org.apache.rocketmq.client.producer.DefaultMQProducer.send(DefaultMQProducer.java:369)
    at com.emrubik.uc.mdm.sync.utils.MdmInit.sendMessage(MdmInit.java:62)
    at com.emrubik.uc.mdm.sync.utils.MdmInit.main(MdmInit.java:2149)
```

原因：RocketMQ 部署在虚拟机上，内网 ip 为 10.10.30.63，该虚拟机一个 docker0 网卡，ip 为 172.17.0.1。RocketMQ broker 启动时默认使用了 docker0 网卡，Producer 客户端无法连接 172.17.0.1，造成以上问题。

解决方案

（1）干掉 docker0 网卡或修改网卡名称

（2）停掉 broker，修改 broker 配置文件，重启 broker。

修改 conf/broker.conf，增加两行来指定启动 broker 的 IP：

```
namesrvAddr = 10.10.30.63:9876
brokerIP1 = 10.10.30.63
```

启动时需要指定配置文件

```bash
nohup sh bin/mqbroker -n localhost:9876 -c conf/broker.conf &
```

## 架构

![img](https://raw.githubusercontent.com/dunwu/images/dev/cs/java/javaweb/distributed/mq/rocketmq/rmq-basic-arc.png)

RocketMQ 由四部分组成：NameServer、Broker、Producer、Consumer。其中任意一个组成都可以水平扩展为集群模式，以避免单点故障问题。

### Producer

Producers 支持分布式集群方式部署。Producer 通过 MQ 的负载均衡模块选择相应的 Broker 集群队列进行消息投递，投递的过程支持快速失败并且低延迟。

### Consumer

Consumer 支持分布式集群方式部署。支持以 push 推，pull 拉两种模式对消息进行消费。同时也支持集群方式和广播方式的消费，它提供实时消息订阅机制，可以满足大多数用户的需求。

### NameServer

NameServer 是一个 Topic 路由注册中心，其角色类似 Dubbo 中的 zookeeper，支持 Broker 的动态注册与发现。主要包括两个功能：

- **Broker 管理**，NameServer 接受 Broker 集群的注册信息并且保存下来作为路由信息的基本数据。然后提供心跳检测机制，检查 Broker 是否还存活；
- **路由信息管理**，每个 NameServer 将保存关于 Broker 集群的整个路由信息和用于客户端查询的队列信息。然后 Producer 和 Conumser 通过 NameServer 就可以知道整个 Broker 集群的路由信息，从而进行消息的投递和消费。

NameServer 通常也是集群的方式部署，各实例间相互不进行信息通讯。Broker 是向每一台 NameServer 注册自己的路由信息，所以每一个 NameServer 实例上面都保存一份完整的路由信息。当某个 NameServer 因某种原因下线了，Broker 仍然可以向其它 NameServer 同步其路由信息，Producer、Consumer 仍然可以动态感知 Broker 的路由的信息。

NameServer 是一个功能齐全的服务器，主要包括两个功能：

1. Broker 管理 - NameServer 接受来自 Broker 集群的注册，并提供心跳机制来检查 Broker 节点是否存活。
2. 路由管理 - 每个 NameServer 将保存有关 Broker 集群的完整路由信息和客户端查询的查询队列。

RocketMQ 客户端（Producer/Consumer）将从 NameServer 查询队列路由信息。

将 NameServer 地址列表提供给客户端有四种方法：

1. 编程方式 - 类似：`producer.setNamesrvAddr("ip:port")`
2. Java 选项 - 使用 `rocketmq.namesrv.addr` 参数
3. 环境变量 - 设置环境变量 `NAMESRV_ADDR`
4. HTTP 端点

> 更详细信息可以参考官方文档：[here](http://rocketmq.apache.org/rocketmq/four-methods-to-feed-name-server-address-list/)

### Broker

Broker 主要负责消息的存储、投递和查询以及服务高可用保证，为了实现这些功能，Broker 包含了以下几个重要子模块。

Broker 有几个重要的子模块：

- **Remoting Module**：整个 Broker 的实体，负责处理来自 clients 端的请求。
- **Client Manager**：负责管理客户端(Producer/Consumer)和维护 Consumer 的 Topic 订阅信息。
- **Store Service**：提供方便简单的 API 接口处理消息存储到物理硬盘和查询功能。
- **HA Service**：高可用服务，提供 Master Broker 和 Slave Broker 之间的数据同步功能。
- **Index Service**：根据特定的 Message key 对投递到 Broker 的消息进行索引服务，以提供消息的快速查询。

![img](https://raw.githubusercontent.com/dunwu/images/dev/cs/java/javaweb/distributed/mq/rocketmq/rmq-basic-component.png)

## 原理

分布式消息系统作为实现分布式系统可扩展、可伸缩性的关键组件，需要具有高吞吐量、高可用等特点。而谈到消息系统的设计，就回避不了两个问题：

1. 消息的顺序问题
2. 消息的重复问题

### 顺序消息

#### 第一种模型

假如生产者产生了 2 条消息：M1、M2，要保证这两条消息的顺序，应该怎样做？你脑中想到的可能是这样：

<div align="center">
<img src="http://upload-images.jianshu.io/upload_images/3101171-bb5ec534363e2fb4" />
</div>

假定 M1 发送到 S1，M2 发送到 S2，如果要保证 M1 先于 M2 被消费，那么需要 M1 到达消费端被消费后，通知 S2，然后 S2 再将 M2 发送到消费端。

这个模型存在的问题是，如果 M1 和 M2 分别发送到两台 Server 上，就不能保证 M1 先达到 MQ 集群，也不能保证 M1 被先消费。换个角度看，如果 M2 先于 M1 达到 MQ 集群，甚至 M2 被消费后，M1 才达到消费端，这时消息也就乱序了，说明以上模型是不能保证消息的顺序的。

<div align="center">
<img src="http://upload-images.jianshu.io/upload_images/3101171-5a6313fe906a678b" />
</div>

#### 第二种模型

如何才能在 MQ 集群保证消息的顺序？一种简单的方式就是将 M1、M2 发送到同一个 Server 上：

这样可以保证 M1 先于 M2 到达 MQServer（生产者等待 M1 发送成功后再发送 M2），根据先达到先被消费的原则，M1 会先于 M2 被消费，这样就保证了消息的顺序。

这个模型也仅仅是理论上可以保证消息的顺序，在实际场景中可能会遇到下面的问题：

<div align="center">
<img src="http://upload-images.jianshu.io/upload_images/3101171-d430f5a3ec6c48ad" />
</div>

只要将消息从一台服务器发往另一台服务器，就会存在网络延迟问题。如上图所示，如果发送 M1 耗时大于发送 M2 的耗时，那么 M2 就仍将被先消费，仍然不能保证消息的顺序。即使 M1 和 M2 同时到达消费端，由于不清楚消费端 1 和消费端 2 的负载情况，仍然有可能出现 M2 先于 M1 被消费的情况。

如何解决这个问题？将 M1 和 M2 发往同一个消费者，且发送 M1 后，需要消费端响应成功后才能发送 M2。

这可能产生另外的问题：如果 M1 被发送到消费端后，消费端 1 没有响应，那是继续发送 M2 呢，还是重新发送 M1？一般为了保证消息一定被消费，肯定会选择重发 M1 到另外一个消费端 2，就如下图所示。

<div align="center">
<img src="http://upload-images.jianshu.io/upload_images/3101171-3c0e822d37a85e1e" />
</div>

这样的模型就严格保证消息的顺序，细心的你仍然会发现问题，消费端 1 没有响应 Server 时有两种情况，一种是 M1 确实没有到达(数据在网络传送中丢失)，另外一种消费端已经消费 M1 且已经发送响应消息，只是 MQ Server 端没有收到。如果是第二种情况，重发 M1，就会造成 M1 被重复消费。也就引入了我们要说的第二个问题，消息重复问题，这个后文会详细讲解。

回过头来看消息顺序问题，严格的顺序消息非常容易理解，也可以通过文中所描述的方式来简单处理。总结起来，要实现严格的顺序消息，简单且可行的办法就是：

**保证生产者 - MQServer - 消费者是一对一对一的关系。**

这样的设计虽然简单易行，但也会存在一些很严重的问题，比如：

1. 并行度就会成为消息系统的瓶颈（吞吐量不够）
2. 更多的异常处理，比如：只要消费端出现问题，就会导致整个处理流程阻塞，我们不得不花费更多的精力来解决阻塞的问题。

RocketMQ 的解决方案：通过合理的设计或者将问题分解来规避。如果硬要把时间花在解决问题本身，实际上不仅效率低下，而且也是一种浪费。从这个角度来看消息的顺序问题，我们可以得出两个结论：

1. 不关注乱序的应用实际大量存在
2. 队列无序并不意味着消息无序

最后我们从源码角度分析 RocketMQ 怎么实现发送顺序消息。

RocketMQ 通过轮询所有队列的方式来确定消息被发送到哪一个队列（负载均衡策略）。比如下面的示例中，订单号相同的消息会被先后发送到同一个队列中：

```java
// RocketMQ 通过 MessageQueueSelector 中实现的算法来确定消息发送到哪一个队列上
// RocketMQ 默认提供了两种 MessageQueueSelector 实现：随机/Hash
// 当然你可以根据业务实现自己的 MessageQueueSelector 来决定消息按照何种策略发送到消息队列中
SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        Integer id = (Integer) arg;
        int index = id % mqs.size();
        return mqs.get(index);
    }
}, orderId);
```

在获取到路由信息以后，会根据 MessageQueueSelector 实现的算法来选择一个队列，同一个 OrderId 获取到的肯定是同一个队列。

```java
private SendResult send()  {
    // 获取topic路由信息
    TopicPublishInfo topicPublishInfo = this.tryToFindTopicPublishInfo(msg.getTopic());
    if (topicPublishInfo != null && topicPublishInfo.ok()) {
        MessageQueue mq = null;
        // 根据我们的算法，选择一个发送队列
        // 这里的arg = orderId
        mq = selector.select(topicPublishInfo.getMessageQueueList(), msg, arg);
        if (mq != null) {
            return this.sendKernelImpl(msg, mq, communicationMode, sendCallback, timeout);
        }
    }
}
```

### 消息重复

造成消息重复的根本原因是：网络不可达。只要通过网络交换数据，就无法避免这个问题。所以解决这个问题的办法就是绕过这个问题。那么问题就变成了：如果消费端收到两条一样的消息，应该怎样处理？

1. 消费端处理消息的业务逻辑保持幂等性。
2. 保证每条消息都有唯一编号且保证消息处理成功与去重表的日志同时出现。

第 1 条很好理解，只要保持幂等性，不管来多少条重复消息，最后处理的结果都一样。

第 2 条原理就是利用一张日志表来记录已经处理成功的消息的 ID，如果新到的消息 ID 已经在日志表中，那么就不再处理这条消息。

第 1 条解决方案，很明显应该在消费端实现，不属于消息系统要实现的功能。

第 2 条可以消息系统实现，也可以业务端实现。正常情况下出现重复消息的概率其实很小，如果由消息系统来实现的话，肯定会对消息系统的吞吐量和高可用有影响，所以最好还是由业务端自己处理消息重复的问题，这也是 RocketMQ 不解决消息重复的问题的原因。

**RocketMQ 不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重。**

### 事务消息

RocketMQ 除了支持普通消息，顺序消息，另外还支持事务消息。

假设这样的场景：

![img](https://upload-images.jianshu.io/upload_images/3101171-253d8bd65736694f.png)

图中执行本地事务（Bob 账户扣款）和发送异步消息应该保证同时成功或者同时失败，也就是扣款成功了，发送消息一定要成功，如果扣款失败了，就不能再发送消息。那问题是：我们是先扣款还是先发送消息呢？

![img](http://upload-images.jianshu.io/upload_images/3101171-088dc074c4ecd192)

RocketMQ 分布式事务步骤：

发送 Prepared 消息 2222222222222222222，并拿到接受消息的地址。
执行本地事务
通过第 1 步骤拿到的地址去访问消息，并修改消息状态。

## 参考资料

- [RocketMQ 官方文档](http://rocketmq.apache.org/docs/quick-start/)
- [分布式开放消息系统(RocketMQ)的原理与实践](https://www.jianshu.com/p/453c6e7ff81c)
