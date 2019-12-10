# Elastic 技术栈快速入门

> 开源协议：[Apache 2.0](https://github.com/elastic/elasticsearch/tree/7.4/licenses/APACHE-LICENSE-2.0.txt)

## 1. 简介

### 1.1. Elastic Stack 是什么

**Elastic Stack** 即 **ELK Stack**。

ELK 是指 Elastic 公司旗下三款产品 [ElasticSearch](https://www.elastic.co/cn/products/elasticsearch) 、[Logstash](https://www.elastic.co/cn/products/logstash) 、[Kibana](https://www.elastic.co/cn/products/kibana) 的首字母组合。

- Elasticsearch 是一个搜索和分析引擎。
- Logstash 是服务器端数据处理管道，能够同时从多个来源采集数据，转换数据，然后将数据发送到诸如 Elasticsearch 等“存储库”中。
- Kibana 则可以让用户在 Elasticsearch 中使用图形和图表对数据进行可视化。

而 Elastic Stack 是 ELK Stack 的更新换代产品，最新产品引入了轻量型的单一功能数据采集器，并把它们叫做 [Beats](https://www.elastic.co/cn/products/beats)。

### 1.2. 为什么使用 Elastic Stack

对于有一定规模的公司来说，通常会很多个应用，并部署在大量的服务器上。运维和开发人员常常需要通过查看日志来定位问题。如果应用是集群化部署，试想如果登录一台台服务器去查看日志，是多么费时费力。

而通过 ELK 这套解决方案，可以同时实现日志收集、日志搜索和日志分析的功能。

### 1.3. Elastic Stack 架构

<br><div align="center"><img src="https://www.elastic.co/guide/en/logstash/current/static/images/deploy3.png"/></div><br>

> **说明**
>
> 以上是 Elastic Stack 的一个架构图。从图中可以清楚的看到数据流向。
>
> - [Beats](https://www.elastic.co/products/beats) 是单一用途的数据传输平台，它可以将多台机器的数据发送到 Logstash 或 ElasticSearch。但 Beats 并不是不可或缺的一环，所以本文中暂不介绍。
> - [Logstash](https://www.elastic.co/products/logstash) 是一个动态数据收集管道。支持以 TCP/UDP/HTTP 多种方式收集数据（也可以接受 Beats 传输来的数据），并对数据做进一步丰富或提取字段处理。
> - [ElasticSearch](https://www.elastic.co/products/elasticsearch) 是一个基于 JSON 的分布式的搜索和分析引擎。作为 ELK 的核心，它集中存储数据。
>
> - [Kibana](https://www.elastic.co/products/kibana) 是 ELK 的用户界面。它将收集的数据进行可视化展示（各种报表、图形化数据），并提供配置、管理 ELK 的界面。

## 2. Logstash

> [Logstash](https://github.com/elastic/logstash) 是开源的服务器端数据处理管道，能够同时从多个来源采集数据，转换数据，然后将数据发送到您最喜欢的“存储库”中。

### 2.1. Logstash 简介

Logstash 可以传输和处理你的日志、事务或其他数据。

Logstash 是 Elasticsearch 的最佳数据管道。

Logstash 是插件式管理模式，在输入、过滤、输出以及编码过程中都可以使用插件进行定制。Logstash 社区有超过 200 种可用插件。

### 2.2. Logstash 原理

Logstash 有两个必要元素：`input` 和 `output` ，一个可选元素：`filter`。

这三个元素，分别代表 Logstash 事件处理的三个阶段：输入 > 过滤器 > 输出。

<br><div align="center"><img src="https://www.elastic.co/guide/en/logstash/current/static/images/basic_logstash_pipeline.png"/></div><br>

- **input** - 负责从数据源采集数据。
- **`filter`** - 将数据修改为你指定的格式或内容。
- **`output`** - 将数据传输到目的地。

在实际应用场景中，通常输入、输出、过滤器不止一个。Logstash 的这三个元素都使用插件式管理方式，用户可以根据应用需要，灵活的选用各阶段需要的插件，并组合使用。

## 3. Beats

> **[Beats](https://github.com/elastic/beats) 是安装在服务器上的数据中转代理**。
>
> Beats 可以将数据直接传输到 Elasticsearch 或传输到 Logstash 。

<br><div align="center"><img src="https://www.elastic.co/guide/en/beats/libbeat/current/images/beats-platform.png"/></div><br>

Beats 有多种类型，可以根据实际应用需要选择合适的类型。

常用的类型有：

- **Packetbeat：**网络数据包分析器，提供有关您的应用程序服务器之间交换的事务的信息。
- **Filebeat：**从您的服务器发送日志文件。
- **Metricbeat：**是一个服务器监视代理程序，它定期从服务器上运行的操作系统和服务收集指标。
- **Winlogbeat：**提供 Windows 事件日志。

### 3.1. Filebeat 简介

> _由于本人仅接触过 Filebeat，所以本文只介绍 Beats 组件中的 Filebeat。_

相比 Logstash，FileBeat 更加轻量化。

在任何环境下，应用程序都有停机的可能性。 Filebeat 读取并转发日志行，如果中断，则会记住所有事件恢复联机状态时所在位置。

Filebeat 带有内部模块（auditd，Apache，Nginx，System 和 MySQL），可通过一个指定命令来简化通用日志格式的收集，解析和可视化。

FileBeat 不会让你的管道超负荷。FileBeat 如果是向 Logstash 传输数据，当 Logstash 忙于处理数据，会通知 FileBeat 放慢读取速度。一旦拥塞得到解决，FileBeat 将恢复到原来的速度并继续传播。

<br><div align="center"><img src="https://www.elastic.co/guide/en/beats/filebeat/current/images/filebeat.png"/></div><br>

### 3.2. Filebeat 原理

Filebeat 有两个主要组件：

- `harvester`：负责读取一个文件的内容。它会逐行读取文件内容，并将内容发送到输出目的地。
- `prospector`：负责管理 harvester 并找到所有需要读取的文件源。比如类型是日志，prospector 就会遍历制定路径下的所有匹配要求的文件。

```yaml
filebeat.prospectors:
  - type: log
    paths:
      - /var/log/*.log
      - /var/path2/*.log
```

Filebeat 保持每个文件的状态，并经常刷新注册表文件中的磁盘状态。状态用于记住 harvester 正在读取的最后偏移量，并确保发送所有日志行。

Filebeat 将每个事件的传递状态存储在注册表文件中。所以它能保证事件至少传递一次到配置的输出，没有数据丢失。

## 4. 运维

- [ElasticSearch 运维](elastic-elasticsearch-ops.md)
- [Logstash 运维](elastic-logstash-ops.md)
- [Kibana 运维](elastic-kibana-ops.md)
- [Beats 运维](elastic-beats-ops.md)

## 5. 参考资料

- **官方**
  - [Elastic 官方](https://www.elastic.co/guide/index.html)
  - [Elasticsearch github](https://github.com/elastic/elasticsearch)
  - [Logstash github](https://github.com/elastic/logstash)
  - [Kibana github](https://github.com/elastic/kibana)
- **文章**
  - [什么是 ELK Stack？](https://www.elastic.co/cn/what-is/elk-stack)
