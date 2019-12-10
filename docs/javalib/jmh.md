# JMH 应用指南

## 简介

### 什么是 JMH

[JMH](http://openjdk.java.net/projects/code-tools/jmh/) 即 Java Microbenchmark Harness，这是专门用于进行代码的微基准测试的一套工具 API。

JMH 由 OpenJDK/Oracle 里面那群开发了 Java 编译器的大牛们所开发 。何谓 Micro Benchmark 呢？ 简单地说就是在 method 层面上的 benchmark，精度可以精确到 **微秒级**。

### 为什么需要 JMH

#### 死码消除

所谓死码，是指注释的代码，不可达的代码块，可达但不被使用的代码等等 。

#### 常量折叠与常量传播

[常量折叠](https://zh.wikipedia.org/wiki/常數折疊#常數傳播) (Constant folding) 是一个在编译时期简化常数的一个过程，常数在表示式中仅仅代表一个简单的数值，就像是整数 `2`，若是一个变数从未被修改也可作为常数，或者直接将一个变数被明确地被标注为常数，例如下面的描述：

### JMH 的注意点

- 测试前需要预热。
- 防止无用代码进入测试方法中。
- 并发测试。
- 测试结果呈现。

### 应用场景

1. 当你已经找出了热点函数，而需要对热点函数进行进一步的优化时，就可以使用 JMH 对优化的效果进行定量的分析。
2. 想定量地知道某个函数需要执行多长时间，以及执行时间和输入 n 的相关性
3. 一个函数有两种不同实现（例如 JSON 序列化/反序列化有 Jackson 和 Gson 实现），不知道哪种实现性能更好

### JMH 概念

- `Iteration` - iteration 是 JMH 进行测试的最小单位，包含一组 invocations。
- `Invocation` - 一次 benchmark 方法调用。
- `Operation` - benchmark 方法中，被测量操作的执行。如果被测试的操作在 benchmark 方法中循环执行，可以使用`@OperationsPerInvocation`表明循环次数，使测试结果为单次 operation 的性能。
- `Warmup` - 在实际进行 benchmark 前先进行预热。因为某个函数被调用多次之后，JIT 会对其进行编译，通过预热可以使测量结果更加接近真实情况。

## 快速入门

### 添加 maven 依赖

```xml
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-core</artifactId>
    <version>${jmh.version}</version>
</dependency>
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-generator-annprocess</artifactId>
    <version>${jmh.version}</version>
    <scope>provided</scope>
</dependency>
```

### 测试代码

```java
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringBuilderBenchmark {

    @Benchmark
    public void testStringAdd() {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        // System.out.println(a);
    }

    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        // System.out.println(sb.toString());
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(StringBuilderBenchmark.class.getSimpleName())
            .output("d:/Benchmark.log")
            .build();
        new Runner(options).run();
    }

}
```

### 执行 JMH

#### 命令行

（1）初始化 **benchmarking** 工程

```
$ mvn archetype:generate \
          -DinteractiveMode=false \
          -DarchetypeGroupId=org.openjdk.jmh \
          -DarchetypeArtifactId=jmh-java-benchmark-archetype \
          -DgroupId=org.sample \
          -DartifactId=test \
          -Dversion=1.0
```

（2）构建 benchmark

```
$ cd test/
$ mvn clean install
```

（3）运行 benchmark

```
$ java -jar target/benchmarks.jar
```

#### 执行 main 方法

执行 main 方法，耐心等待测试结果，最终会生成一个测试报告，内容大致如下；

```
# JMH version: 1.22
# VM version: JDK 1.8.0_181, Java HotSpot(TM) 64-Bit Server VM, 25.181-b13
# VM invoker: C:\Program Files\Java\jdk1.8.0_181\jre\bin\java.exe
# VM options: -javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2019.2.3\lib\idea_rt.jar=58635:D:\Program Files\JetBrains\IntelliJ IDEA 2019.2.3\bin -Dfile.encoding=UTF-8
# Warmup: 3 iterations, 10 s each
# Measurement: 10 iterations, 5 s each
# Timeout: 10 min per iteration
# Threads: 8 threads, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: io.github.dunwu.javalib.jmh.StringBuilderBenchmark.testStringAdd

# Run progress: 0.00% complete, ETA 00:05:20
# Fork: 1 of 2
# Warmup Iteration   1: 21803.050 ops/ms
# Warmup Iteration   2: 22501.860 ops/ms
# Warmup Iteration   3: 20953.944 ops/ms
Iteration   1: 21627.645 ops/ms
Iteration   2: 21215.269 ops/ms
Iteration   3: 20863.282 ops/ms
Iteration   4: 21617.715 ops/ms
Iteration   5: 21695.645 ops/ms
Iteration   6: 21886.784 ops/ms
Iteration   7: 21986.899 ops/ms
Iteration   8: 22389.540 ops/ms
Iteration   9: 22507.313 ops/ms
Iteration  10: 22124.133 ops/ms

# Run progress: 25.00% complete, ETA 00:04:02
# Fork: 2 of 2
# Warmup Iteration   1: 22262.108 ops/ms
# Warmup Iteration   2: 21567.804 ops/ms
# Warmup Iteration   3: 21787.002 ops/ms
Iteration   1: 21598.970 ops/ms
Iteration   2: 22486.133 ops/ms
Iteration   3: 22157.834 ops/ms
Iteration   4: 22321.827 ops/ms
Iteration   5: 22477.063 ops/ms
Iteration   6: 22154.760 ops/ms
Iteration   7: 21561.095 ops/ms
Iteration   8: 22194.863 ops/ms
Iteration   9: 22493.844 ops/ms
Iteration  10: 22568.078 ops/ms


Result "io.github.dunwu.javalib.jmh.StringBuilderBenchmark.testStringAdd":
  21996.435 ±(99.9%) 412.955 ops/ms [Average]
  (min, avg, max) = (20863.282, 21996.435, 22568.078), stdev = 475.560
  CI (99.9%): [21583.480, 22409.390] (assumes normal distribution)


# JMH version: 1.22
# VM version: JDK 1.8.0_181, Java HotSpot(TM) 64-Bit Server VM, 25.181-b13
# VM invoker: C:\Program Files\Java\jdk1.8.0_181\jre\bin\java.exe
# VM options: -javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2019.2.3\lib\idea_rt.jar=58635:D:\Program Files\JetBrains\IntelliJ IDEA 2019.2.3\bin -Dfile.encoding=UTF-8
# Warmup: 3 iterations, 10 s each
# Measurement: 10 iterations, 5 s each
# Timeout: 10 min per iteration
# Threads: 8 threads, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: io.github.dunwu.javalib.jmh.StringBuilderBenchmark.testStringBuilderAdd

# Run progress: 50.00% complete, ETA 00:02:41
# Fork: 1 of 2
# Warmup Iteration   1: 241500.886 ops/ms
# Warmup Iteration   2: 134206.032 ops/ms
# Warmup Iteration   3: 86907.846 ops/ms
Iteration   1: 86143.339 ops/ms
Iteration   2: 74725.356 ops/ms
Iteration   3: 72316.121 ops/ms
Iteration   4: 77319.716 ops/ms
Iteration   5: 83469.256 ops/ms
Iteration   6: 87712.360 ops/ms
Iteration   7: 79421.899 ops/ms
Iteration   8: 80867.839 ops/ms
Iteration   9: 82619.163 ops/ms
Iteration  10: 87026.928 ops/ms

# Run progress: 75.00% complete, ETA 00:01:20
# Fork: 2 of 2
# Warmup Iteration   1: 228342.337 ops/ms
# Warmup Iteration   2: 124737.248 ops/ms
# Warmup Iteration   3: 82598.851 ops/ms
Iteration   1: 86877.318 ops/ms
Iteration   2: 89388.624 ops/ms
Iteration   3: 88523.558 ops/ms
Iteration   4: 87547.332 ops/ms
Iteration   5: 88376.087 ops/ms
Iteration   6: 88848.837 ops/ms
Iteration   7: 85998.124 ops/ms
Iteration   8: 86796.998 ops/ms
Iteration   9: 87994.726 ops/ms
Iteration  10: 87784.453 ops/ms


Result "io.github.dunwu.javalib.jmh.StringBuilderBenchmark.testStringBuilderAdd":
  84487.902 ±(99.9%) 4355.525 ops/ms [Average]
  (min, avg, max) = (72316.121, 84487.902, 89388.624), stdev = 5015.829
  CI (99.9%): [80132.377, 88843.427] (assumes normal distribution)


# Run complete. Total time: 00:05:23

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                                     Mode  Cnt      Score      Error   Units
StringBuilderBenchmark.testStringAdd         thrpt   20  21996.435 ±  412.955  ops/ms
StringBuilderBenchmark.testStringBuilderAdd  thrpt   20  84487.902 ± 4355.525  ops/ms
```

## API

下面来了解一下 jmh 常用 API

### @BenchmarkMode

基准测试类型。这里选择的是 `Throughput` 也就是吞吐量。根据源码点进去，每种类型后面都有对应的解释，比较好理解，吞吐量会得到单位时间内可以进行的操作数。

- `Throughput` - 整体吞吐量，例如“1 秒内可以执行多少次调用”。
- `AverageTime` - 调用的平均时间，例如“每次调用平均耗时 xxx 毫秒”。
- `SampleTime` - 随机取样，最后输出取样结果的分布，例如“99%的调用在 xxx 毫秒以内，99.99%的调用在 xxx 毫秒以内”
- `SingleShotTime` - 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup 次数设为 0，用于测试冷启动时的性能。
- `All` - 所有模式

### @Warmup

上面我们提到了，进行基准测试前需要进行预热。一般我们前几次进行程序测试的时候都会比较慢， 所以要让程序进行几轮预热，保证测试的准确性。其中的参数 iterations 也就非常好理解了，就是预热轮数。

为什么需要预热？因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度。所以为了让 benchmark 的结果更加接近真实情况就需要进行预热。

### @Measurement

度量，其实就是一些基本的测试参数。

- `iterations` - 进行测试的轮次
- `time` - 每轮进行的时长
- `timeUnit` - 时长单位

都是一些基本的参数，可以根据具体情况调整。一般比较重的东西可以进行大量的测试，放到服务器上运行。

### @Threads

每个进程中的测试线程，这个非常好理解，根据具体情况选择，一般为 cpu 乘以 2。

### @Fork

进行 fork 的次数。如果 fork 数是 2 的话，则 JMH 会 fork 出两个进程来进行测试。

### @OutputTimeUnit

这个比较简单了，基准测试结果的时间类型。一般选择秒、毫秒、微秒。

### @Benchmark

方法级注解，表示该方法是需要进行 benchmark 的对象，用法和 JUnit 的 @Test 类似。

### @Param

属性级注解，@Param 可以用来指定某项参数的多种情况。特别适合用来测试一个函数在不同的参数输入的情况下的性能。

### @Setup

方法级注解，这个注解的作用就是我们需要在测试之前进行一些准备工作，比如对一些数据的初始化之类的。

### @TearDown

方法级注解，这个注解的作用就是我们需要在测试之后进行一些结束工作，比如关闭线程池，数据库连接等的，主要用于资源的回收等。

### @State

当使用 @Setup 参数的时候，必须在类上加这个参数，不然会提示无法运行。

State 用于声明某个类是一个“状态”，然后接受一个 Scope 参数用来表示该状态的共享范围。 因为很多 benchmark 会需要一些表示状态的类，JMH 允许你把这些类以依赖注入的方式注入到 benchmark 函数里。Scope 主要分为三种。

- `Thread` - 该状态为每个线程独享。
- `Group` - 该状态为同一个组里面所有线程共享。
- `Benchmark` - 该状态在所有线程间共享。

关于 State 的用法，官方的 code sample 里有比较好的[例子](http://hg.openjdk.java.net/code-tools/jmh/file/cb9aa824b55a/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_03_States.java)。

## 参考资料

- [jmh 官方示例](http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/)
- [Java 微基准测试框架 JMH](https://www.xncoding.com/2018/01/07/java/jmh.html)
- [JAVA 拾遗 — JMH 与 8 个测试陷阱](https://www.cnkirito.moe/java-jmh/)
