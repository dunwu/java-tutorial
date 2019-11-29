# Elasticsearch 运维

> [Elasticsearch](https://github.com/elastic/elasticsearch) 是一个分布式、RESTful 风格的搜索和数据分析引擎，能够解决不断涌现出的各种用例。 作为 Elastic Stack 的核心，它集中存储您的数据，帮助您发现意料之中以及意料之外的情况。

## 部署

### 安装步骤

> [Elasticsearch 官方开源版本安装说明](https://www.elastic.co/cn/downloads/elasticsearch-oss)

（1）下载解压

访问 [官方下载地址](https://www.elastic.co/cn/downloads/elasticsearch-oss) ，选择需要的版本，下载解压到本地。

（2）运行

运行 `bin/elasticsearch` (Windows 系统上运行 `bin\elasticsearch.bat` ) 

（3）访问

执行 `curl http://localhost:9200/`  测试服务是否启动

### 集群规划

ElasticSearch 集群需要根据业务实际情况去合理规划。

需要考虑的问题点：

- 集群部署几个节点？
- 有多少个索引？
- 每个索引有多大数据量？
- 每个索引有多少个分片？

一个参考规划：

- 3 台机器，每台机器是 6 核 64G 的。
- 我们 es 集群的日增量数据大概是 2000 万条，每天日增量数据大概是 500MB，每月增量数据大概是 6 亿，15G。目前系统已经运行了几个月，现在 es 集群里数据总量大概是 100G 左右。
- 目前线上有 5 个索引（这个结合你们自己业务来，看看自己有哪些数据可以放 es 的），每个索引的数据量大概是 20G，所以这个数据量之内，我们每个索引分配的是 8 个 shard，比默认的 5 个 shard 多了 3 个 shard。

## FAQ

### elasticsearch 不允许以 root 权限来运行

**问题：**在 Linux 环境中，elasticsearch 不允许以 root 权限来运行。

如果以 root 身份运行 elasticsearch，会提示这样的错误：

```
can not run elasticsearch as root
```

**解决方法：**使用非 root 权限账号运行 elasticsearch

```bash
# 创建用户组
groupadd elk
# 创建新用户，-g elk 设置其用户组为 elk，-p elk 设置其密码为 elk
useradd elk -g elk -p elk
# 更改 /opt 文件夹及内部文件的所属用户及组为 elk:elk
chown -R elk:elk /opt # 假设你的 elasticsearch 安装在 opt 目录下
# 切换账号
su elk
```

### vm.max_map_count 不低于 262144

**问题：**`vm.max_map_count` 表示虚拟内存大小，它是一个内核参数。elasticsearch 默认要求 `vm.max_map_count` 不低于 262144。

```
max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```

**解决方法：**

你可以执行以下命令，设置 `vm.max_map_count` ，但是重启后又会恢复为原值。

```
sysctl -w vm.max_map_count=262144
```

持久性的做法是在 `/etc/sysctl.conf` 文件中修改 `vm.max_map_count` 参数：

```
echo "vm.max_map_count=262144" > /etc/sysctl.conf
sysctl -p
```

> **注意**
>
> 如果运行环境为 docker 容器，可能会限制执行 sysctl 来修改内核参数。
>
> 这种情况下，你只能选择直接修改宿主机上的参数了。

### nofile 不低于 65536

**问题：** `nofile` 表示进程允许打开的最大文件数。elasticsearch 进程要求可以打开的最大文件数不低于 65536。

```
max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
```

**解决方法：**

在 `/etc/security/limits.conf` 文件中修改 `nofile` 参数：

```
echo "* soft nofile 65536" > /etc/security/limits.conf
echo "* hard nofile 131072" > /etc/security/limits.conf
```

### nproc 不低于 2048

**问题：** `nproc` 表示最大线程数。elasticsearch 要求最大线程数不低于 2048。

```
max number of threads [1024] for user [user] is too low, increase to at least [2048]
```

**解决方法：**

在 `/etc/security/limits.conf` 文件中修改 `nproc` 参数：

```
echo "* soft nproc 2048" > /etc/security/limits.conf
echo "* hard nproc 4096" > /etc/security/limits.conf
```

## 参考资料

- [Elasticsearch 官网](https://www.elastic.co/cn/products/elasticsearch)
- [Elasticsearch Github](https://github.com/elastic/elasticsearch)
- [Elasticsearch 官方文档](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Install Elasticsearch with RPM]( https://www.elastic.co/guide/en/elasticsearch/reference/current/rpm.html#rpm)