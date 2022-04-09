---
title: Java 序列化工具
date: 2022-04-09 11:52:34
permalink: /pages/f28b5d/
---

# Java 序列化工具

Java 官方的序列化存在许多问题，因此，很多人更愿意使用优秀的第三方序列化工具来替代 Java 自身的序列化机制。 如果想详细了解 Java 自身序列化方式，可以参考：[深入理解 Java 序列化](https://github.com/dunwu/javacore/blob/master/docs/io/java-serialization.md)

序列化库技术选型：

- [thrift](https://github.com/apache/thrift)、[protobuf](https://github.com/protocolbuffers/protobuf) - 适用于对性能敏感，对开发体验要求不高的内部系统。
- [hessian](http://hessian.caucho.com/doc/hessian-overview.xtp) - 适用于对开发体验敏感，性能有要求的内外部系统。
- [jackson](https://github.com/FasterXML/jackson)、[gson](https://github.com/google/gson)、[fastjson](https://github.com/alibaba/fastjson) - 适用于对序列化后的数据要求有良好的可读性（转为 json 、xml 形式）。

## 内容

- [JSON](01.JSON序列化.md) - Fastjson、Jackson、Gson
- [二进制](02.二进制序列化.md) - Protobuf、Thrift、Hessian、Kryo、FST

## 资料

- [Thrift Github](https://github.com/apache/thrift)
- [Protobuf Github](https://github.com/protocolbuffers/protobuf)
- [Hessian 官网](http://hessian.caucho.com/doc/hessian-overview.xtp)
- [Fastjson Github](https://github.com/alibaba/fastjson)
- [Jackson Github](https://github.com/FasterXML/jackson)
- [Gson Github](https://github.com/google/gson)
