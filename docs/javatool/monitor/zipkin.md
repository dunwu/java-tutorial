# Zipkin 应用指南

**Zipkin 是一个基于 Java 开发的、开源的、分布式实时数据跟踪系统（Distributed Tracking System）**。它采集有助于解决服务架构中延迟问题的实时数据。

Zipkin 主要功能是聚集来自各个异构系统的实时监控数据。分布式跟踪系统还有其他比较成熟的实现，例如：Naver 的 Pinpoint、Apache 的 HTrace、阿里的鹰眼 Tracing、京东的 Hydra、新浪的 Watchman，美团点评的 CAT，skywalking 等。

Zipkin 基于 Google Dapper 的论文设计而来，由 Twitter 公司开发贡献。

## 一、Zipkin 简介

### 特性

如果日志文件中有跟踪 ID，则可以直接跳至该跟踪 ID。 否则，您可以基于属性进行查询，例如服务，操作名称，标签和持续时间。 将为您总结一些有趣的数据，例如在服务中花费的时间百分比以及操作是否失败。

Zipkin UI 还提供了一个依赖关系图，该关系图显示了每个应用程序中跟踪了多少个请求。这对于识别聚合行为（包括错误路径或对不赞成使用的服务的调用）很有帮助。

![Zipkin UI](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211161706.png)

### 多平台

Zipkin 官方支持 C#、Go、Java、JavaScript、Ruby、Scala、PHP 语言。

除此以外，社区还贡献了多种其他语言的支持，详情可以参考官方文档：[Tracers and Instrumentation](https://zipkin.io/pages/tracers_instrumentation.html)

### 数据

Zipkin 服务器捆绑了用于采集和存储数据的扩展。

默认情况下，数据可以通过 `Http`，`Kafka` 、`RabbitMQ` 或 RPC 传输。

并存储在内存中或 `MySQL`、`Cassandra` 或 `Elasticsearch` 中。

数据以 json 形式存储，可以参考：[Zipkin 官方的 Swagger API](https://zipkin.io/zipkin-api/#/default/post_spans)

![Zipkin Swagger API](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211162055.png)

## 二、Zipkin 安装

### Docker

Docker 启动方式：

```shell
docker run -d -p 9411:9411 openzipkin/zipkin
```

### Java

> 注意：必须运行在 JDK8+ 环境

Java 启动方式：

```shell
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```

### 编译方式

适用于需要订制化的场景。

```shell
# get the latest source
git clone https://github.com/openzipkin/zipkin
cd zipkin
# Build the server and also make its dependencies
./mvnw -DskipTests --also-make -pl zipkin-server clean install
# Run the server
java -jar ./zipkin-server/target/zipkin-server-*exec.jar
```

## 三、Zipkin 架构

ZipKin 可以分为两部分，

- 一部分是 Zipkin server，用来作为数据的采集存储、数据分析与展示；
- 另一部分是 Zipkin client 是 Zipkin 基于不同的语言及框架封装的一些列客户端工具，这些工具完成了追踪数据的生成与上报功能。

架构如下：

![Zipkin 架构](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211155836.png)

### Zipkin Server

Zipkin Server 主要包括四个模块：

- **Collector** - 负责采集客户端传输的数据。
- **Storage** - 负责存储采集的数据。当前支持 Memory，MySQL，Cassandra，ElasticSearch 等，默认存储在内存中。
- **API（Query）** - 负责查询 Storage 中存储的数据。提供简单的 JSON API 获取数据，主要提供给 web UI 使用。
- **UI** - 提供简单的 web 界面。

Instrumented Client 和 Instrumented Server，是指分布式架构中使用了 Trace 工具的两个应用，Client 会调用 Server 提供的服务，两者都会向 Zipkin 上报 Trace 相关信息。在 Client 和 Server 通过 Transport 上报 Trace 信息后，由 Zipkin 的 Collector 模块接收，并由 Storage 模块将数据存储在对应的存储介质中，然后 Zipkin 提供 API 供 UI 界面查询 Trace 跟踪信息。Non-Instrumented Server，指的是未使用 Trace 工具的 Server，显然它不会上报 Trace 信息。

### Zipkin Client

- **Tracer** - `Tracer` 存在于你的应用中，它负责采集关于已发生操作的实时元数据。它们通常会检测库，因此对于用户是透明的。例如，已检测的 Web 服务器记录它何时接收到请求，以及何时发送响应。收集的跟踪数据称为跨度（Span）。
- **Instrumentation** - Instrumentation 保证了生产环境的安全性和很少的开销。因此，它们仅在内部传播 ID，以告知接收方正在进行追踪。完成的 Span 将通过外部通信告知 Zipkin，类似于应用程序异步报告指标的方式。例如，当跟踪某个操作并且需要发出 http 请求时，会添加一些 header 来传播 ID。header 不用于发送详细信息，例如操作名称。
- **Reporter** - 能够将数据发送到 Zipkin 的检测应用程序中的组件，被称为 Reporter。Reporter 有多种传输方式，可以将跟踪数据发送到 Zipkin 采集器，后者将跟踪数据持久化保存到存储中。稍后，API 会查询存储以向 UI 提供渲染数据。

以下是 Zipkin 的一个示例工作流：

```shell
┌─────────────┐ ┌───────────────────────┐  ┌─────────────┐  ┌──────────────────┐
│ User Code   │ │ Trace Instrumentation │  │ Http Client │  │ Zipkin Collector │
└─────────────┘ └───────────────────────┘  └─────────────┘  └──────────────────┘
       │                 │                         │                 │
           ┌─────────┐
       │ ──┤GET /foo ├─▶ │ ────┐                   │                 │
           └─────────┘         │ record tags
       │                 │ ◀───┘                   │                 │
                           ────┐
       │                 │     │ add trace headers │                 │
                           ◀───┘
       │                 │ ────┐                   │                 │
                               │ record timestamp
       │                 │ ◀───┘                   │                 │
                             ┌─────────────────┐
       │                 │ ──┤GET /foo         ├─▶ │                 │
                             │X-B3-TraceId: aa │     ────┐
       │                 │   │X-B3-SpanId: 6b  │   │     │           │
                             └─────────────────┘         │ invoke
       │                 │                         │     │ request   │
                                                         │
       │                 │                         │     │           │
                                 ┌────────┐          ◀───┘
       │                 │ ◀─────┤200 OK  ├─────── │                 │
                           ────┐ └────────┘
       │                 │     │ record duration   │                 │
            ┌────────┐     ◀───┘
       │ ◀──┤200 OK  ├── │                         │                 │
            └────────┘       ┌────────────────────────────────┐
       │                 │ ──┤ asynchronously report span     ├────▶ │
                             │                                │
                             │{                               │
                             │  "traceId": "aa",              │
                             │  "id": "6b",                   │
                             │  "name": "get",                │
                             │  "timestamp": 1483945573944000,│
                             │  "duration": 386000,           │
                             │  "annotations": [              │
                             │--snip--                        │
                             └────────────────────────────────┘
```

Instrumented client 和 server 是分别使用了 ZipKin Client 的服务，Zipkin Client 会根据配置将追踪数据发送到 Zipkin Server 中进行数据存储、分析和展示。

## 四、Zipkin 客户端

[Brave](https://github.com/openzipkin/brave) 是 Java 版的 zipkin 客户端。

一般不会手动编写 Trace 相关的代码，Brave 提供可一些开箱即用的库，帮助我们追踪一些特定的请求。比如：dubbo、grpc、servlet、mysql、httpClient、kafka、springMVC 等。

## 参考资料

- [Zipkin 官网](https://zipkin.io/)
- [Zipkin Github](https://github.com/openzipkin/zipkin)
- [brave](https://github.com/openzipkin/brave)
