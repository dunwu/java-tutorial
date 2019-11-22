# 细说 Java 主流 JSON 库

<!-- TOC depthFrom:2 depthTo:3 -->

- [1. JSON 简介](#1-json-简介)
  - [1.1. JSON 数据结构](#11-json-数据结构)
  - [1.2. Java JSON 库](#12-java-json-库)
- [2. Fastjson](#2-fastjson)
  - [2.1. 添加 maven 依赖](#21-添加-maven-依赖)
  - [2.2. Fastjson API](#22-fastjson-api)
  - [2.3. Fastjson 注解](#23-fastjson-注解)
- [3. Jackson](#3-jackson)
  - [3.1. 添加 maven 依赖](#31-添加-maven-依赖)
  - [3.2. Jackson API](#32-jackson-api)
  - [3.3. Jackson 注解](#33-jackson-注解)
- [4. 示例源码](#4-示例源码)
- [5. 参考资料](#5-参考资料)

<!-- /TOC -->

## 1. JSON 简介

### 1.1. JSON 数据结构

JSON（JavaScript Object Notation）是一种基于文本的数据交换格式。几乎所有的编程语言都有很好的库或第三方工具来提供基于 JSON 的 API 支持，因此你可以非常方便地使用任何自己喜欢的编程语言来处理 JSON 数据。

JSON 建构于两种结构：

- “名称/值”对的集合。不同的语言中，它被理解为*对象（object）*，纪录（record），结构（struct），字典（dictionary），哈希表（hash table），有键列表（keyed list），或者关联数组 （associative array）。
- 值的有序列表（An ordered list of values）。在大部分语言中，它被理解为数组（array）。

> 扩展阅读：
>
> - http://www.json.org/json-zh.html - 图文并茂介绍 json 数据形式
>
> - [json 的 RFC 文档](http://tools.ietf.org/html/rfc4627)

### 1.2. Java JSON 库

Java 中比较流行的 JSON 库有：

- [Fastjson](https://github.com/alibaba/fastjson)
- [Jackson](http://wiki.fasterxml.com/JacksonHome)
- [Gson](https://github.com/google/gson)

## 2. Fastjson

[fastjson](https://github.com/alibaba/fastjson) 是阿里巴巴的开源 JSON 解析库。

### 2.1. 添加 maven 依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>x.x.x</version>
</dependency>
```

### 2.2. Fastjson API

#### JavaBean 的序列化和反序列化

```java
String text = JSON.toJSONString(obj); //序列化
VO vo = JSON.parseObject("{...}", VO.class); //反序列化
```

### 2.3. Fastjson 注解

#### `@JSONField`

可以配置在属性（setter、getter）和字段（必须是 public field）上。

> 扩展阅读：[JSONField 用法](https://github.com/alibaba/fastjson/wiki/JSONField)

```java
@JSONField(name="ID")
public int getId() {return id;}

// 配置date序列化和反序列使用yyyyMMdd日期格式
@JSONField(format="yyyyMMdd")
public Date date1;

// 不序列化
@JSONField(serialize=false)
public Date date2;

// 不反序列化
@JSONField(deserialize=false)
public Date date3;

// 按ordinal排序
@JSONField(ordinal = 2)
private int f1;

@JSONField(ordinal = 1)
private int f2;
```

#### `@JSONType`

- 自定义序列化：[ObjectSerializer](https://github.com/alibaba/fastjson/wiki/JSONType_serializer)
- 子类型处理：[SeeAlso](https://github.com/alibaba/fastjson/wiki/JSONType_seeAlso_cn)

JSONType.alphabetic 属性: fastjson 缺省时会使用字母序序列化，如果你是希望按照 java fields/getters 的自然顺序序列化，可以配置 JSONType.alphabetic，使用方法如下：

```java
@JSONType(alphabetic = false)
public static class B {
    public int f2;
    public int f1;
    public int f0;
}
```

## 3. Jackson

以下仅列举个人认为比较常用的特性。

### 3.1. 添加 maven 依赖

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.8</version>
</dependency>
```

### 3.2. Jackson API

> 扩展阅读：更多 API 使用细节可以参考 [jackson-databind 官方说明](https://github.com/FasterXML/jackson-databind)

#### JavaBean 的序列化和反序列化

反序列化示例：

```java
ObjectMapper mapper = new ObjectMapper();

MyValue value = mapper.readValue(new File("data.json"), MyValue.class);
// or:
value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
// or:
value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
```

序列化示例：

```java
mapper.writeValue(new File("result.json"), myResultObject);
// or:
byte[] jsonBytes = mapper.writeValueAsBytes(myResultObject);
// or:
String jsonString = mapper.writeValueAsString(myResultObject);
```

#### 容器的序列化和反序列化

> 扩展阅读：更多 API 使用细节可以参考 [jackson-databind 官方说明](https://github.com/FasterXML/jackson-databind)

```java
Person p = new Person("Tom", 20);
Person p2 = new Person("Jack", 22);
Person p3 = new Person("Mary", 18);

List<Person> persons = new LinkedList<>();
persons.add(p);
persons.add(p2);
persons.add(p3);

Map<String, List> map = new HashMap<>();
map.put("persons", persons);

String json = null;
try {
	json = mapper.writeValueAsString(map);
} catch (JsonProcessingException e) {
	e.printStackTrace();
}
```

### 3.3. Jackson 注解

> 扩展阅读：更多注解使用细节可以参考 [jackson-annotations 官方说明](https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations)

#### `@JsonProperty`

```java
public class MyBean {
   private String _name;

   // without annotation, we'd get "theName", but we want "name":
   @JsonProperty("name")
   public String getTheName() { return _name; }

   // note: it is enough to add annotation on just getter OR setter;
   // so we can omit it here
   public void setTheName(String n) { _name = n; }
}
```

#### `@JsonIgnoreProperties` 和 `@JsonIgnore`

```java
// means that if we see "foo" or "bar" in JSON, they will be quietly skipped
// regardless of whether POJO has such properties
@JsonIgnoreProperties({ "foo", "bar" })
public class MyBean {
   // will not be written as JSON; nor assigned from JSON:
   @JsonIgnore
   public String internal;

   // no annotation, public field is read/written normally
   public String external;

   @JsonIgnore
   public void setCode(int c) { _code = c; }

   // note: will also be ignored because setter has annotation!
   public int getCode() { return _code; }
}
```

#### `@JsonCreator`

```java
public class CtorBean {
  public final String name;
  public final int age;

  @JsonCreator // constructor can be public, private, whatever
  private CtorBean(@JsonProperty("name") String name,
    @JsonProperty("age") int age)
  {
      this.name = name;
      this.age = age;
  }
}
```

#### `@JsonPropertyOrder`

alphabetic 设为 true 表示，json 字段按自然顺序排列，默认为 false。

```java
@JsonPropertyOrder(alphabetic = true)
public class JacksonAnnotationBean {}
```

## 4. 示例源码

> 示例源码：[javalib-io-json](https://github.com/dunwu/java-tutorial/tree/master/javalib-io-json)

## 5. 参考资料

- http://www.json.org/json-zh.html
- [json 的 RFC 文档](http://tools.ietf.org/html/rfc4627)
- [fastjson](https://github.com/alibaba/fastjson)
- [jackson 官方文档](https://github.com/FasterXML/jackson-docs)
- [jackson-databind](https://github.com/FasterXML/jackson-databind)
- [JSON 最佳实践](https://kimmking.github.io/2017/06/06/json-best-practice/)
- [【简明教程】JSON](https://www.jianshu.com/p/8b428e1d1564)
