# Cat

## CAT 简介

CAT（Central Application Tracking），是基于 Java 开发的分布式实时监控系统。CAT 在基础存储、高性能通信、大规模在线访问、服务治理、实时监控、容器化及集群智能调度等领域提供业界领先的、统一的解决方案。CAT 目前在美团的产品定位是应用层的统一监控组件，基本接入了美团所有核心应用，在中间件（RPC、数据库、缓存、MQ 等）框架中得到广泛应用，为各业务线提供系统的性能指标、健康状况、实时告警等。

### CAT 的优势

- 实时处理：信息的价值会随时间锐减，尤其是事故处理过程中
- 全量数据：最开始的设计目标就是全量采集，全量的好处有很多
- 高可用：所有应用都倒下了，需要监控还站着，并告诉工程师发生了什么，做到故障还原和问题定位
- 故障容忍：CAT 本身故障不应该影响业务正常运转，CAT 挂了，应用不该受影响，只是监控能力暂时减弱
- 高吞吐：要想还原真相，需要全方位地监控和度量，必须要有超强的处理吞吐能力
- 可扩展：支持分布式、跨 IDC 部署，横向扩展的监控系统

### 支持的消息类型

CAT监控系统将每次URL、Service的请求内部执行情况都封装为一个完整的消息树、消息树可能包括Transaction、Event、Heartbeat、Metric等信息。 

- **Transaction** 适合记录跨越系统边界的程序访问行为,比如远程调用，数据库调用，也适合执行时间较长的业务逻辑监控，Transaction 用来记录一段代码的执行时间和次数
- **Event** 用来记录一件事发生的次数，比如记录系统异常，它和 transaction 相比缺少了时间的统计，开销比 transaction 要小
- **Heartbeat** 表示程序内定期产生的统计信息, 如 CPU 利用率, 内存利用率, 连接池状态, 系统负载等
- **Metric** 用于记录业务指标、指标可能包含对一个指标记录次数、记录平均值、记录总和，业务指标最低统计粒度为 1 分钟

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211174235.png)

## CAT 部署

Cat 部署可以参考 [官方 Wiki - 服务端部署](https://github.com/dianping/cat/wiki/readme_server) ，非常详细，不赘述。

## CAT 报表

与其他监控工具（如 Zipkin、SkyWalking）相比，CAT 的报表功能最丰富。支持以下报表类型：

- **[Transaction 报表](https://github.com/dianping/cat/wiki/transaction)** - 一段代码运行时间、次数，比如 URL、Cache、SQL 执行次数和响应时间
- **[Event 报表](https://github.com/dianping/cat/wiki/event)** - 一行代码运行次数，比如出现一个异常
- **[Problem 报表](https://github.com/dianping/cat/wiki/problem)** - 根据 Transaction/Event 数据分析出来系统可能出现的异常，包括访问较慢的程序等
- **[Heartbeat 报表](https://github.com/dianping/cat/wiki/heartbeat)** - JVM 内部一些状态信息，比如 Memory，Thread 等
- **[Business 报表](https://github.com/dianping/cat/wiki/business)** - 业务监控报表，比如订单指标，支付等业务指标

## CAT 配置

CAT 提供了以下配置：

- **[项目配置](https://github.com/dianping/cat/wiki/project)** 包括项目基本信息、机器分组配置
- **[告警配置](https://github.com/dianping/cat/wiki/alarm)** 包括基本告警配置、告警规则、以及具体告警配置
- **[全局配置](https://github.com/dianping/cat/wiki/global)** 包括服务端配置、消息采样配置、客户端路由
- **[业务指标](https://github.com/dianping/cat/wiki/business)** 包括业务监控配置、业务标签配置

## CAT 架构

CAT 主要分为三个模块：

- **cat-client** - 提供给业务以及中间层埋点的底层 SDK。
- **cat-consumer** - 用于实时分析从客户端的提供的数据。
- **cat-home** - 作为用户提供给用户的展示的控制端。

在实际开发和部署中，cat-consumer 和 cat-home 是部署在一个 jvm 内部，每个 CAT 服务端都可以作为 consumer 也可以作为 home，这样既能减少整个 CAT 层级结构，也可以增加整个系统稳定性。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211174001.png)

上图是 CAT 目前多机房的整体结构图：

- 路由中心是根据应用所在机房信息来决定客户端上报的 CAT 服务端地址
- 每个机房内部都有的独立的原始信息存储集群 HDFS
- cat-home 可以部署在一个机房也可以部署在多个机房，在做报表展示的时候，cat-home 会从 cat-consumer 中进行跨机房的调用，将所有的数据合并展示给用户
- 实际过程中，cat-consumer、cat-home 以及路由中心都是部署在一起，每个服务端节点都可以充当任何一个角色

## 参考资料

- [CAT Github](https://github.com/dianping/cat)
