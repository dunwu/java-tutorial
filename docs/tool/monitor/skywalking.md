# SkyWalking

SkyWalking 是一个基于 Java 开发的分布式系统的应用程序性能监视工具，专为微服务、云原生架构和基于容器（Docker、K8s、Mesos）架构而设计。

## 一、SkyWalking 简介

SkyWalking 是观察性分析平台和应用性能管理系统。

提供分布式追踪、服务网格遥测分析、度量聚合和可视化一体化解决方案。

![img](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211152235.png)

### SkyWalking 特性

- 多种监控手段，语言探针和 service mesh
- 多语言自动探针，Java，.NET Core 和 Node.JS
- 轻量高效，不需要大数据
- 模块化，UI、存储、集群管理多种机制可选
- 支持告警
- 优秀的可视化方案

### SkyWalking 核心概念

- **Service** - 服务。代表一组为传入请求提供相同的行为的工作负载。 使用代理或 SDK 时，可以定义服务名称。
- **Service Instance** - 服务实例。服务组中的每个工作负载都称为一个实例。就像 Kubernetes 中的 Pod 一样，它在 OS 中不必是单个进程。
- **Endpoint** - 端点。它是特定服务中用于传入请求的路径，例如 HTTP URI 路径或 RPC 服务类+方法签名。

## 二、SkyWalking 架构

从逻辑上讲，SkyWalking 分为四个部分：探针（Probes），平台后端，存储和 UI。

![SkyWalking 架构](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211153516.png)

- **探针（Probes）** - 探针是指集成到目标系统中的代理或SDK库。它们负责收集数据（包括跟踪数据和统计数据）并将其按照 SkyWalking 的要求重新格式化为。
- **平台后端** - 平台后端是一个提供后端服务的集群。它用于聚合、分析和驱动从探针到 UI 的流程。它还为传入格式（如 Zipkin 的格式），存储实现程序和集群管理提供可插入功能。 您甚至可以使用 Observability Analysis Language 自定义聚合和分析。
- **存储** - 您可以选择一个 SkyWalking 已实现的存储，如由 Sharding-Sphere 管理的 ElasticSearch，H2 或 MySQL 集群，也可以自行实现一个存储。
- **UI** - 用户界面很酷，对于 SkyWalking 最终用户而言非常强大。它也可以自定义以匹配您的自定义后端。

## 三、SkyWalking 安装

进入 [Apache SkyWalking 官方下载页面](http://skywalking.apache.org/downloads/)，选择安装版本，下载解压到本地。

![SkyWalking 组件](https://raw.githubusercontent.com/dunwu/images/dev/snap/20200211154612.png)

安装分为三个部分：

- [Backend setup document](https://github.com/apache/skywalking/blob/master/docs/en/setup/backend/backend-setup.md)
- [UI setup document](https://github.com/apache/skywalking/blob/master/docs/en/setup/backend/ui-setup.md)
- [CLI set up document](https://github.com/apache/skywalking-cli)

## 参考资料

- [SkyWalking Github](https://github.com/apache/skywalking)
