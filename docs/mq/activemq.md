# ActiveMQ

## JMS 基本概念

`JMS` 即 **Java 消息服务（Java Message Service）API**，是一个 Java 平台中关于面向消息中间件的 API。它用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。Java 消息服务是一个与具体平台无关的 API，绝大多数 MOM 提供商都对 JMS 提供支持。

### 消息模型

JMS 有两种消息模型：

- Point-to-Point(P2P)
- Publish/Subscribe(Pub/Sub)

#### P2P 的特点

![img](https://raw.githubusercontent.com/dunwu/images/dev/cs/java/javalib/jms/jms-pointToPoint.gif)

在点对点的消息系统中，消息分发给一个单独的使用者。点对点消息往往与队列 `javax.jms.Queue` 相关联。

每个消息只有一个消费者（Consumer）(即一旦被消费，消息就不再在消息队列中)。

发送者和接收者之间在时间上没有依赖性，也就是说当发送者发送了消息之后，不管接收者有没有正在运行，它不会影响到消息被发送到队列。

接收者在成功接收消息之后需向队列应答成功。

如果你希望发送的每个消息都应该被成功处理的话，那么你需要 P2P 模式。

#### Pub/Sub 的特点

![img](https://raw.githubusercontent.com/dunwu/images/dev/cs/java/javalib/jms/jms-publishSubscribe.gif)

发布/订阅消息系统支持一个事件驱动模型，消息生产者和消费者都参与消息的传递。生产者发布事件，而使用者订阅感兴趣的事件，并使用事件。该类型消息一般与特定的主题 `javax.jms.Topic` 关联。

每个消息可以有多个消费者。

发布者和订阅者之间有时间上的依赖性。针对某个主题（Topic）的订阅者，它必须创建一个订阅者之后，才能消费发布者的消息，而且为了消费消息，订阅者必须保持运行的状态。

为了缓和这样严格的时间相关性，JMS 允许订阅者创建一个可持久化的订阅。这样，即使订阅者没有被激活（运行），它也能接收到发布者的消息。

如果你希望发送的消息可以不被做任何处理、或者被一个消息者处理、或者可以被多个消费者处理的话，那么可以采用 Pub/Sub 模型。

### JMS 编程模型

![img](https://raw.githubusercontent.com/dunwu/images/dev/cs/java/javalib/jms/jms-publishSubscribe.gif)

#### ConnectionFactory

创建 `Connection` 对象的工厂，针对两种不同的 jms 消息模型，分别有 `QueueConnectionFactory` 和`TopicConnectionFactory` 两种。可以通过 JNDI 来查找 `ConnectionFactory` 对象。

#### Connection

`Connection` 表示在客户端和 JMS 系统之间建立的链接（对 TCP/IP socket 的包装）。`Connection` 可以产生一个或多个`Session`。跟 `ConnectionFactory` 一样，`Connection` 也有两种类型：`QueueConnection` 和 `TopicConnection`。

#### Destination

`Destination` 是一个包装了消息目标标识符的被管对象。消息目标是指消息发布和接收的地点，或者是队列 `Queue` ，或者是主题 `Topic` 。JMS 管理员创建这些对象，然后用户通过 JNDI 发现它们。和连接工厂一样，管理员可以创建两种类型的目标，点对点模型的 `Queue`，以及发布者/订阅者模型的 `Topic`。

#### Session

`Session` 表示一个单线程的上下文，用于发送和接收消息。由于会话是单线程的，所以消息是连续的，就是说消息是按照发送的顺序一个一个接收的。会话的好处是它支持事务。如果用户选择了事务支持，会话上下文将保存一组消息，直到事务被提交才发送这些消息。在提交事务之前，用户可以使用回滚操作取消这些消息。一个会话允许用户创建消息，生产者来发送消息，消费者来接收消息。同样，`Session` 也分 `QueueSession` 和 `TopicSession`。

#### MessageConsumer

`MessageConsumer` 由 `Session` 创建，并用于将消息发送到 `Destination`。消费者可以同步地（阻塞模式），或（非阻塞）接收 `Queue` 和 `Topic` 类型的消息。同样，消息生产者分两种类型：`QueueSender` 和`TopicPublisher`。

#### MessageProducer

`MessageProducer` 由 `Session` 创建，用于接收被发送到 `Destination` 的消息。`MessageProducer` 有两种类型：`QueueReceiver` 和 `TopicSubscriber`。可分别通过 `session` 的 `createReceiver(Queue)` 或 `createSubscriber(Topic)` 来创建。当然，也可以 `session` 的 `creatDurableSubscriber` 方法来创建持久化的订阅者。

#### Message

是在消费者和生产者之间传送的对象，也就是说从一个应用程序传送到另一个应用程序。一个消息有三个主要部分：

- 消息头（必须）：包含用于识别和为消息寻找路由的操作设置。
- 一组消息属性（可选）：包含额外的属性，支持其他提供者和用户的兼容。可以创建定制的字段和过滤器（消息选择器）。
- 一个消息体（可选）：允许用户创建五种类型的消息（文本消息，映射消息，字节消息，流消息和对象消息）。

消息接口非常灵活，并提供了许多方式来定制消息的内容。

| Common            | Point-to-Point              | Publish-Subscribe      |
| ----------------- | --------------------------- | ---------------------- |
| ConnectionFactory | QueueConnectionFactory      | TopicConnectionFactory |
| Connection        | QueueConnection             | TopicConnection        |
| Destination       | Queue                       | Topic                  |
| Session           | QueueSession                | TopicSession           |
| MessageProducer   | QueueSender                 | TopicPublisher         |
| MessageSender     | QueueReceiver, QueueBrowser | TopicSubscriber        |

## 安装

**安装条件**

JDK1.7 及以上版本

本地配置了 **JAVA_HOME** 环境变量。

**下载**

支持 Windows/Unix/Linux/Cygwin

[ActiveMQ 官方下载地址](http://activemq.apache.org/download.html)

**Windows 下运行**

（1）解压压缩包到本地；

（2）打开控制台，进入安装目录的 `bin` 目录下；

```
cd [activemq_install_dir]
```

（3）执行 `activemq start` 来启动 ActiveMQ

```
bin\activemq start
```

**测试安装是否成功**

（1）ActiveMQ 默认监听端口为 61616

```
netstat -an|find “61616”
```

（2）访问 <http://127.0.0.1:8161/admin/>

（3）输入用户名、密码

```
Login: admin
Passwort: admin
```

（4）点击导航栏的 Queues 菜单

（5）添加一个队列（queue）

## 项目中的应用

**引入依赖**

```xml
<dependency>
  <groupId>org.apache.activemq</groupId>
  <artifactId>activemq-all</artifactId>
  <version>5.14.1</version>
</dependency>
```

**Sender.java**

```java
public class Sender {
    private static final int SEND_NUMBER = 4;

    public static void main(String[] args) {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageProducer：消息发送者
        MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("FirstQueue");
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构造消息，此处写死，项目就是参数，或者方法获取
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
        for (int i = 1; i <= SEND_NUMBER; i++) {
            TextMessage message = session
                    .createTextMessage("ActiveMq 发送的消息" + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}
```

**Receiver.java**

```java
public class Receiver {
    public static void main(String[] args) {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
}
```

**运行**

先运行 Receiver.java 进行消息监听，再运行 Send.java 发送消息。

**输出**

Send 的输出内容

```
发送消息：Activemq 发送消息0
发送消息：Activemq 发送消息1
发送消息：Activemq 发送消息2
发送消息：Activemq 发送消息3
```

Receiver 的输出内容

```
收到消息ActiveMQ 发送消息0
收到消息ActiveMQ 发送消息1
收到消息ActiveMQ 发送消息2
收到消息ActiveMQ 发送消息3
```

## 资源

- [ActiveMQ 官网](http://activemq.apache.org/)
- [oracle 官方的 jms 介绍](https://docs.oracle.com/cd/E19575-01/819-3669/6n5sg7cgq/index.html)
