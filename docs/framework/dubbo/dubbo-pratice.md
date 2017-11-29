# Dubbo 最佳实践

## 分包

建议将服务接口，服务模型，服务异常等均放在 API 包中，因为服务模型及异常也是 API 的一部分，同时，这样做也符合分包原则：重用发布等价原则(REP)，共同重用原则(CRP)。

如果需要，也可以考虑在 API 包中放置一份 spring 的引用配置，这样使用方，只需在 spring 加载过程中引用此配置即可，配置建议放在模块的包目录下，以免冲突，如：`com/alibaba/china/xxx/dubbo-reference.xml`。

## 粒度

服务接口尽可能大粒度，每个服务方法应代表一个功能，而不是某功能的一个步骤，否则将面临分布式事务问题，Dubbo 暂未提供分布式事务支持。

服务接口建议以业务场景为单位划分，并对相近业务做抽象，防止接口数量爆炸。

不建议使用过于抽象的通用接口，如：`Map query(Map)`，这样的接口没有明确语义，会给后期维护带来不便。

## 版本

每个接口都应定义版本号，为后续不兼容升级提供可能，如： `<dubbo:serviceinterface="com.xxx.XxxService" version="1.0" />`。

建议使用两位版本号，因为第三位版本号通常表示兼容升级，只有不兼容时才需要变更服务版本。

当不兼容时，先升级一半提供者为新版本，再将消费者全部升为新版本，然后将剩下的一半提供者升为新版本。

## 兼容性

服务接口增加方法，或服务模型增加字段，可向后兼容，删除方法或删除字段，将不兼容，枚举类型新增字段也不兼容，需通过变更版本号升级。

各协议的兼容性不同，参见： [服务协议](https://dubbo.gitbooks.io/dubbo-user-book/references/protocol/introduction.html)

## 枚举值

如果是完备集，可以用 `Enum`，比如：`ENABLE`, `DISABLE`。

如果是业务种类，以后明显会有类型增加，不建议用 `Enum`，可以用 `String` 代替。

如果是在返回值中用了 `Enum`，并新增了 `Enum` 值，建议先升级服务消费方，这样服务提供方不会返回新值。

如果是在传入参数中用了 `Enum`，并新增了 `Enum` 值，建议先升级服务提供方，这样服务消费方不会传入新值。

## 序列化

服务参数及返回值建议使用 POJO 对象，即通过 `setter`, `getter` 方法表示属性的对象。

服务参数及返回值不建议使用接口，因为数据模型抽象的意义不大，并且序列化需要接口实现类的元信息，并不能起到隐藏实现的意图。

服务参数及返回值都必需是 byValue 的，而不能是 byReference 的，消费方和提供方的参数或返回值引用并不是同一个，只是值相同，Dubbo 不支持引用远程对象。

## 异常

建议使用异常汇报错误，而不是返回错误码，异常信息能携带更多信息，以及语义更友好。

如果担心性能问题，在必要时，可以通过 override 掉异常类的 `fillInStackTrace()` 方法为空方法，使其不拷贝栈信息。

查询方法不建议抛出 checked 异常，否则调用方在查询时将过多的 `try...catch`，并且不能进行有效处理。

服务提供方不应将 DAO 或 SQL 等异常抛给消费方，应在服务实现中对消费方不关心的异常进行包装，否则可能出现消费方无法反序列化相应异常。

## 调用

不要只是因为是 Dubbo 调用，而把调用 `try...catch` 起来。`try...catch` 应该加上合适的回滚边界上。

对于输入参数的校验逻辑在 Provider 端要有。如有性能上的考虑，服务实现者可以考虑在 API 包上加上服务 Stub 类来完成检验。

## 推荐用法

### 在 Provider 上尽量多配置 Consumer 端属性

原因如下：

- 作服务的提供者，比服务使用方更清楚服务性能参数，如调用的超时时间，合理的重试次数，等等
- 在 Provider 配置后，Consumer 不配置则会使用 Provider 的配置值，即 Provider 配置可以作为 Consumer 的缺省值。否则，Consumer 会使用 Consumer 端的全局设置，这对于 Provider 不可控的，并且往往是不合理的

Provider 上尽量多配置 Consumer 端的属性，让 Provider 实现者一开始就思考 Provider 服务特点、服务质量的问题。

示例：

```
<dubbo:service interface="com.alibaba.hello.api.HelloService" version="1.0.0" ref="helloService"
    timeout="300" retry="2" loadbalance="random" actives="0"
/>

<dubbo:service interface="com.alibaba.hello.api.WorldService" version="1.0.0" ref="helloService"
    timeout="300" retry="2" loadbalance="random" actives="0" >
    <dubbo:method name="findAllPerson" timeout="10000" retries="9" loadbalance="leastactive" actives="5" />
<dubbo:service/>
```

在 Provider 上可以配置的 Consumer 端属性有：

1. `timeout` 方法调用超时
2. `retries` 失败重试次数，缺省是 2
3. `loadbalance` 负载均衡算法，缺省是随机 `random`。还可以有轮询 `roundrobin`、最不活跃优先`leastactive`
4. `actives` 消费者端，最大并发调用限制，即当 Consumer 对一个服务的并发调用到上限后，新调用会 Wait 直到超时 在方法上配置 `dubbo:method` 则并发限制针对方法，在接口上配置 `dubbo:service`，则并发限制针对服务

详细配置说明参见：[Dubbo配置参考手册](https://dubbo.gitbooks.io/dubbo-user-book/references/xml/introduction.html)

### Provider 上配置合理的 Provider 端属性

```xml
<dubbo:protocol threads="200" /> 
<dubbo:service interface="com.alibaba.hello.api.HelloService" version="1.0.0" ref="helloService"
    executes="200" >
    <dubbo:method name="findAllPerson" executes="50" />
</dubbo:service>
```

Provider 上可以配置的 Provider 端属性有：

1. `threads` 服务线程池大小
2. `executes` 一个服务提供者并行执行请求上限，即当 Provider 对一个服务的并发调用到上限后，新调用会 Wait，这个时候 Consumer可能会超时。在方法上配置 `dubbo:method` 则并发限制针对方法，在接口上配置 `dubbo:service`，则并发限制针对服务

### 配置管理信息

目前有负责人信息和组织信息用于区分站点。有问题时便于的找到服务的负责人，至少写两个人以便备份。负责人和组织的信息可以在注册中心的上看到。

应用配置负责人、组织：

```xml
<dubbo:application owner=”ding.lid,william.liangf” organization=”intl” />
```

service 配置负责人：

```xml
<dubbo:service owner=”ding.lid,william.liangf” />
```

reference 配置负责人：

```xml
<dubbo:reference owner=”ding.lid,william.liangf” />
```

`dubbo:service`、`dubbo:reference` 没有配置负责人，则使用 `dubbo:application` 设置的负责人。

## 配置 Dubbo 缓存文件

提供者列表缓存文件：

```xml
<dubbo:registry file=”${user.home}/output/dubbo.cache” />
```

注意：

1. 文件的路径，应用可以根据需要调整，保证这个文件不会在发布过程中被清除。
2. 如果有多个应用进程注意不要使用同一个文件，避免内容被覆盖。

这个文件会缓存注册中心的列表和服务提供者列表。有了这项配置后，当应用重启过程中，Dubbo 注册中心不可用时则应用会从这个缓存文件读取服务提供者列表的信息，进一步保证应用可靠性。

### 监控配置

1. 使用固定端口暴露服务，而不要使用随机端口

   这样在注册中心推送有延迟的情况下，消费者通过缓存列表也能调用到原地址，保证调用成功。

2. 使用 Dragoon 的 http 监控项监控注册中心上服务提供方

   Dragoon 监控服务在注册中心上的状态：<http://dubbo-reg1.hst.xyi.cn.alidc.net:8080/status/com.alibaba.morgan.member.MemberService:1.0.5> 确保注册中心上有该服务的存在。

3. 服务提供方，使用 Dragoon 的 telnet 或 shell 监控项

   监控服务提供者端口状态：`echo status | nc -i 1 20880 | grep OK | wc -l`，其中的 20880 为服务端口

4. 服务消费方，通过将服务强制转型为 EchoService，并调用 `$echo()` 测试该服务的提供者是可用

   如 `assertEqauls(“OK”, ((EchoService)memberService).$echo(“OK”));`

### 不要使用 dubbo.properties 文件配置，推荐使用对应 XML 配置

Dubbo 中所有的配置项都可以配置在 Spring 配置文件中，并且可以针对单个服务配置。

如完全不配置则使用 Dubbo 缺省值，参见 [Dubbo配置参考手册](https://dubbo.gitbooks.io/dubbo-user-book/references/xml/introduction.html) 中的说明。

#### dubbo.properties 中属性名与 XML 的对应关系

1. 应用名 `dubbo.application.name`

   ```
    <dubbo:application name="myalibaba" >
   ```

2. 注册中心地址 `dubbo.registry.address`

   ```
    <dubbo:registry address="11.22.33.44:9090" >
   ```

3. 调用超时 `dubbo.service.*.timeout`

   可以在多个配置项设置超时 `timeout`，由上至下覆盖（即上面的优先）[5](https://dubbo.gitbooks.io/dubbo-user-book/recommend.html#fn_5)，其它的参数（`retries`、`loadbalance`、`actives`等）的覆盖策略也一样示例如下：

   提供者端特定方法的配置

   ```xml
    <dubbo:service interface="com.alibaba.xxx.XxxService" >
        <dubbo:method name="findPerson" timeout="1000" />
    </dubbo:service>
   ```

   提供者端特定接口的配置

   ```xml
    <dubbo:service interface="com.alibaba.xxx.XxxService" timeout="200" />
   ```

4. 服务提供者协议 `dubbo.service.protocol`、服务的监听端口 `dubbo.service.server.port`

   ```xml
   <dubbo:protocol name="dubbo" port="20880" />
   ```

5. 服务线程池大小 `dubbo.service.max.thread.threads.size`

   ```xml
    <dubbo:protocol threads="100" />
   ```

6. 消费者启动时，没有提供者是否抛异常 Fast-Fail `alibaba.intl.commons.dubbo.service.allow.no.provider`

   ```xml
    <dubbo:reference interface="com.alibaba.xxx.XxxService" check="false" />
   ```