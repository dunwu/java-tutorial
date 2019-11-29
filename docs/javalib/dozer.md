# Dozer 应用指南

这篇文章是本人在阅读 Dozer 官方文档（5.5.1 版本，官网已经一年多没更新了）的过程中，整理下来我认为比较基础的应用场景。

本文中提到的例子应该能覆盖 JavaBean 映射的大部分场景，希望对你有所帮助。

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
- [安装](#安装)
  - [引入 jar 包](#引入-jar-包)
  - [Eclipse 插件](#eclipse-插件)
- [使用](#使用)
  - [准备](#准备)
  - [Dozer 的配置](#dozer-的配置)
  - [与 Spring 整合](#与-spring-整合)
- [Dozer 支持的数据类型转换](#dozer-支持的数据类型转换)
- [Dozer 的映射配置](#dozer-的映射配置)
  - [用注解来配置映射](#用注解来配置映射)
  - [用 API 来配置映射](#用-api-来配置映射)
  - [用 XML 来配置映射](#用-xml-来配置映射)
- [参考](#参考)

<!-- /TOC -->

## 简介

**Dozer 是什么?**

**Dozer 是一个 JavaBean 映射工具库。**

它支持简单的属性映射，复杂类型映射，双向映射，隐式显式的映射，以及递归映射。

它支持三种映射方式：注解、API、XML。

它是开源的，遵从[Apache 2.0 协议](http://www.apache.org/licenses/LICENSE-2.0)

## 安装

### 引入 jar 包

**maven 方式**

如果你的项目使用 maven，添加以下依赖到你的 pom.xml 即可：

```xml
<dependency>
    <groupId>net.sf.dozer</groupId>
    <artifactId>dozer</artifactId>
    <version>5.4.0</version>
</dependency>
```

**非 maven 方式**

如果你的项目不使用 maven，那就只能发扬不怕苦不怕累的精神了。

使用 Dozer 需要引入 Dozer 的 jar 包以及其依赖的第三方 jar 包。

- [Dozer](http://sourceforge.net/project/showfiles.php?group_id=133517)
- [Dozer 依赖的第三方 jar 包](http://dozer.sourceforge.net/dependencies.html)

### Eclipse 插件

Dozer 有插件可以在 Eclipse 中使用(不知道是否好用，反正我没用过)

插件地址: http://dozer.sourceforge.net/eclipse-plugin

## 使用

将 Dozer 引入到工程中后，我们就可以来小试一番了。

实践出真知，先以一个最简单的例子来展示 Dozer 映射的处理过程。

### 准备

我们先准备两个要互相映射的类

NotSameAttributeA.java

```java
public class NotSameAttributeA {
    private long id;
    private String name;
    private Date date;

    // 省略getter/setter
}
```

NotSameAttributeB.java

```java
public class NotSameAttributeB {
    private long id;
    private String value;
    private Date date;

    // 省略getter/setter
}
```

这两个类存在属性名不完全相同的情况：name 和 value。

### Dozer 的配置

#### 为什么要有映射配置?

如果要映射的两个对象有完全相同的属性名，那么一切都很简单。

只需要直接使用 Dozer 的 API 即可：

```java
Mapper mapper = new DozerBeanMapper();
DestinationObject destObject =
    mapper.map(sourceObject, DestinationObject.class);
```

但实际映射时，往往存在属性名不同的情况。

所以，你需要一些配置来告诉 Dozer 应该转换什么，怎么转换。

**_注：官网着重建议：在现实应用中，最好不要每次映射对象时都创建一个`Mapper`实例来工作，这样会产生不必要的开销。如果你不使用 IoC 容器（如：spring）来管理你的项目，那么，最好将`Mapper`定义为单例模式。_**

#### 映射配置文件

在`src/test/resources`目录下添加`dozer/dozer-mapping.xml`文件。
`<mapping>`标签中允许你定义`<class-a>`和`<class-b>`，对应着相互映射的类。
`<field>`标签里定义要映射的特殊属性。需要注意`<a>`和`<class-a>`对应，`<b>`和`<class-b>`对应，聪明的你，猜也猜出来了吧。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mappings xmlns="http://dozer.sourceforge.net" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://dozer.sourceforge.net
          http://dozer.sourceforge.net/schema/beanmapping.xsd">
  <mapping date-format="yyyy-MM-dd">
    <class-a>org.zp.notes.spring.common.dozer.vo.NotSameAttributeA</class-a>
    <class-b>org.zp.notes.spring.common.dozer.vo.NotSameAttributeB</class-b>
    <field>
      <a>name</a>
      <b>value</b>
    </field>
  </mapping>
</mappings>
```

### 与 Spring 整合

#### 配置 DozerBeanMapperFactoryBean

在`src/test/resources`目录下添加`spring/spring-dozer.xml`文件。

Dozer 与 Spring 的整合很便利，你只需要声明一个`DozerBeanMapperFactoryBean`，
将所有的 dozer 映射配置文件作为属性注入到`mappingFiles`，
`DozerBeanMapperFactoryBean`会加载这些规则。

spring-dozer.xml 文件范例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-2.0.xsd"
       default-autowire="byName" default-lazy-init="false">

  <bean id="mapper" class="org.dozer.spring.DozerBeanMapperFactoryBean">
    <property name="mappingFiles">
      <list>
        <value>classpath*:dozer/dozer-mapping.xml</value>
      </list>
    </property>
  </bean>
</beans>
```

#### 自动装配

至此，万事具备，你只需要自动装配`mapper`。

```java
RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dozer.xml"})
@TransactionConfiguration(defaultRollback = false)
public class DozerTest extends TestCase {
    @Autowired
    Mapper mapper;

    @Test
    public void testNotSameAttributeMapping() {
        NotSameAttributeA src = new NotSameAttributeA();
        src.setId(007);
        src.setName("邦德");
        src.setDate(new Date());

        NotSameAttributeB desc = mapper.map(src, NotSameAttributeB.class);
        Assert.assertNotNull(desc);
    }
}
```

运行一下单元测试，绿灯通过。

## Dozer 支持的数据类型转换

Dozer 可以自动做数据类型转换。当前，Dozer 支持以下数据类型转换（都是双向的）

- **Primitive to Primitive Wrapper**

  原型(int、long 等)和原型包装类(Integer、Long)

- **Primitive to Custom Wrapper**

  原型和定制的包装

- **Primitive Wrapper to Primitive Wrapper**

  原型包装类和包装类

- **Primitive to Primitive**

  原型和原型

- **Complex Type to Complex Type**

  复杂类型和复杂类型

- **String to Primitive**

  字符串和原型

- **String to Primitive Wrapper**

  字符串和原型包装类

- **String to Complex Type if the Complex Type contains a String constructor**

  字符串和有字符串构造器的复杂类型（类）

- **String to Map**

  字符串和 Map

- **Collection to Collection**

  集合和集合

- **Collection to Array**

  集合和数组

- **Map to Complex Type**

  Map 和复杂类型

- **Map to Custom Map Type**

  Map 和定制 Map 类型

- **Enum to Enum**

  枚举和枚举

- **Each of these can be mapped to one another: java.util.Date, java.sql.Date, java.sql.Time, java.sql.Timestamp, java.util.Calendar, java.util.GregorianCalendar**

  这些时间相关的常见类可以互换：java.util.Date, java.sql.Date, java.sql.Time, java.sql.Timestamp, java.util.Calendar, java.util.GregorianCalendar

- **String to any of the supported Date/Calendar Objects.**

  字符串和支持 Date/Calendar 的对象

- **Objects containing a toString() method that produces a long representing time in (ms) to any supported Date/Calendar object. **

  如果一个对象的 toString()方法返回的是一个代表 long 型的时间数值（单位：ms），就可以和任何支持 Date/Calendar 的对象转换。

## Dozer 的映射配置

在前面的简单例子中，我们体验了一把 Dozer 的映射流程。但是两个类进行映射，有很多复杂的情况，相应的，你也需要一些更复杂的配置。

Dozer 有三种映射配置方式：

- **注解方式**
- **API 方式**
- **XML 方式**

### 用注解来配置映射

**Dozer 5.3.2**版本开始支持注解方式配置映射（只有一个注解：`@Mapping`）。可以应对一些简单的映射处理，复杂的就玩不转了。

看一下`@Mapping`的声明就可以知道，这个注解只能用于元素和方法。

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Mapping {
  String value() default "";
}
```

让我们来试试吧：

TargetBean.java

```java
public class SourceBean {

    private Long id;

    private String name;

    @Mapping("binaryData")
    private String data;

    @Mapping("pk")
    public Long getId() {
        return this.id;
    }

    //其余getter/setter方法略
}
```

TargetBean.java

```java
public class TargetBean {

    private String pk;

    private String name;

    private String binaryData;

    //getter/setter方法略
}
```

定义了两个相互映射的 Java 类，只需要在源类中用`@Mapping`标记和目标类中对应的属性就可以了。

```java
@Test
public void testAnnotationMapping() {
	SourceBean src = new SourceBean();
	src.setId(7L);
	src.setName("邦德");
	src.setData("00000111");

	TargetBean desc = mapper.map(src, TargetBean.class);
	Assert.assertNotNull(desc);
}
```

测试一下，绿灯通过。

官方文档说，虽然当前版本（文档的版本对应 Dozer 5.5.1）仅支持`@Mapping`，但是在未来的发布版本会提供其他的注解功能，那就敬请期待吧（再次吐槽一下：一年多没更新了）。

### 用 API 来配置映射

个人觉得这种方式比较麻烦，不推荐，也不想多做介绍，就是这么任性。

### 用 XML 来配置映射

需要**强调**的是：如果两个类的所有属性都能很好的互转，可以你中有我，我中有你，不分彼此，那么就不要画蛇添足的在 xml 中去声明映射规则了。

#### 属性名不同时的映射(Basic Property Mapping)

**Dozer 会自动映射属性名相同的属性，所以不必添加在 xml 文件中。**

```xml
<field>
  <a>one</a>
  <b>onePrime</b>
</field>
```

#### 字符串和日期映射(String to Date Mapping)

字符串在和日期进行映射时，允许用户指定日期的格式。

格式的设置分为三个作用域级别：

**属性级别**

对当前属性有效（这个属性必须是日期字符串）

```xml
<field>
  <a date-format="MM/dd/yyyy HH:mm:ss:SS">dateString</a>
  <b>dateObject</b>
</field>
```

**类级别**

对这个类中的所有日期相关的属性有效

```xml
<mapping date-format="MM-dd-yyyy HH:mm:ss">
  <class-a>org.dozer.vo.TestObject</class-a>
  <class-b>org.dozer.vo.TestObjectPrime</class-b>
  <field>
    <a>dateString</a>
    <b>dateObject</b>
  </field>
</mapping>
```

**全局级别**

对整个文件中的所有日期相关的属性有效。

```xml
<mappings>
  <configuration>
    <date-format>MM/dd/yyyy HH:mm</date-format>
  </configuration>

  <mapping wildcard="true">
    <class-a>org.dozer.vo.TestObject</class-a>
    <class-b>org.dozer.vo.TestObjectPrime</class-b>
    <field>
      <a>dateString</a>
      <b>dateObject</b>
    </field>
  </mapping>
</mappings>
```

#### 集合和数组映射(Collection and Array Mapping)

Dozer 可以自动处理以下类型的双向转换。

- List to List
- List to Array
- Array to Array
- Set to Set
- Set to Array
- Set to List

**使用 hint**

如果使用泛型或数组，没有必要使用 hint。

如果不使用泛型或数组。在处理集合或数组之间的转换时，你需要用`hint`指定目标列表的数据类型。

若你不指定`hint`，Dozer 将认为目标集合和源集合的类型是一致的。

使用 Hints 的范例：

```xml
<field>
  <a>hintList</a>
  <b>hintList</b>
  <b-hint>org.dozer.vo.TheFirstSubClassPrime</b-hint>
</field>
```

**累计映射和非累计映射（Cumulative vs. Non-Cumulative List Mapping）**

如果你要转换的目标类已经初始化，你可以选择让 Dozer 添加或更新对象到你的集合中。

而这取决于`relationship-type`配置，默认是累计。

它的设置有作用域级别：

- 全局级

```
<mappings>
  <configuration>
     <relationship-type>non-cumulative</relationship-type>
  </configuration>
</mappings>
```

- 类级别

```xml
<mappings>
  <mapping relationship-type="non-cumulative">
    <!-- 省略 -->
  </mapping>
</mappings>
```

- 属性级别

```xml
<field relationship-type="cumulative">
  <a>hintList</a>
  <b>hintList</b>
  <a-hint>org.dozer.vo.TheFirstSubClass</a-hint>
  <b-hint>org.dozer.vo.TheFirstSubClassPrime</b-hint>
</field>
```

**移动孤儿(Removing Orphans)**

这里的孤儿是指目标集合中存在，但是源集合中不存在的元素。

你可以使用`remove-orphans`开关来选择是否移除这样的元素。

```xml
<field remove-orphans="true">
  <a>srcList</a>
  <b>destList</b>
</field>
```

#### 深度映射(Deep Mapping)

所谓深度映射，是指允许你指定属性的属性（比如一个类的属性本身也是一个类）。举例来说

Source.java

```java
public class Source {
	private long id;
    private String info;
}
```

Dest.java

```java
public class Dest {
	private long id;
    private Info info;
}
```

```java
public class Info {
	private String content;
}
```

映射规则

```xml
<mapping>
  <class-a>org.zp.notes.spring.common.dozer.vo.Source</class-a>
  <class-b>org.zp.notes.spring.common.dozer.vo.Dest</class-b>
  <field>
    <a>info</a>
    <b>info.content</b>
  </field>
</mapping>
```

#### 排除属性(Excluding Fields)

就像任何团体都有捣乱分子，类之间转换时也有想要排除的因子。

如何在做类型转换时，自动排除一些属性，Dozer 提供了几种方法，这里只介绍一种比较通用的方法。

更多详情参考[官网](http://dozer.sourceforge.net/documentation/exclude.html)。

field-exclude 可以排除不需要映射的属性。

```xml
<field-exclude>
  <a>fieldToExclude</a>
  <b>fieldToExclude</b>
</field-exclude>
```

#### 单向映射(One-Way Mapping)

**_注：本文的映射方式，无特殊说明，都是双向映射的。_**

有的场景可能希望转换过程不可逆，即单向转换。

单向转换可以通过使用`one-way`来开启

类级别

```xml
<mapping type="one-way">
  <class-a>org.dozer.vo.TestObjectFoo</class-a>
  <class-b>org.dozer.vo.TestObjectFooPrime</class-b>
    <field>
      <a>oneFoo</a>
      <b>oneFooPrime</b>
    </field>
</mapping>
```

属性级别

```xml
<mapping>
  <class-a>org.dozer.vo.TestObjectFoo2</class-a>
  <class-b>org.dozer.vo.TestObjectFooPrime2</class-b>
  <field type="one-way">
    <a>oneFoo2</a>
    <b>oneFooPrime2</b>
  </field>

  <field type="one-way">
    <a>oneFoo3.prime</a>
    <b>oneFooPrime3</b>
  </field>
```

#### 全局配置(Global Configuration)

全局配置用来设置全局的配置信息。此外，任何定制转换都是在这里定义的。

全局配置都是可选的。

- `<date-format>`表示日期格式
- `<stop-on-errors>`错误处理开关
- `<wildcard>`通配符
- `<trim-strings>`裁剪字符串开关

```xml
<configuration >

  <date-format>MM/dd/yyyy HH:mm</date-format>
  <stop-on-errors>true</stop-on-errors>
  <wildcard>true</wildcard>
  <trim-strings>false</trim-strings>

  <custom-converters> <!-- these are always bi-directional -->
    <converter type="org.dozer.converters.TestCustomConverter" >
      <class-a>org.dozer.vo.TestCustomConverterObject</class-a>
      <class-b>another.type.to.Associate</class-b>
    </converter>

  </custom-converters>
</configuration>
```

全局配置的作用是帮助你少配置一些参数，如果个别类的映射规则需要变更，你可以 mapping 中覆盖它。

覆盖的范例如下

```xml
<mapping date-format="MM-dd-yyyy HH:mm:ss">
  <!-- 省略 -->
</mapping>

<mapping wildcard="false">
  <!-- 省略 -->
</mapping>

<mapping stop-on-errors="false">
  <!-- 省略 -->
</mapping>

<mapping trim-strings="true">
  <!-- 省略 -->
</mapping>
```

#### 定制转换(Custom Converters)

如果 Dozer 默认的转换规则不能满足实际需要，你可以选择定制转换。

定制转换通过配置 XML 来告诉 Dozer 如何去转换两个指定的类。当 Dozer 转换这两个指定类的时候，会调用你的映射规则去替换标准映射规则。

为了让 Dozer 识别，你必须实现`org.dozer.CustomConverter`接口。否则，Dozer 会抛异常。

具体做法：

(1) 创建一个类实现`org.dozer.CustomConverter`接口。

```java
public class TestCustomConverter implements CustomConverter {

  public Object convert(Object destination, Object source,
      Class destClass, Class sourceClass) {
    if (source == null) {
      return null;
    }
    CustomDoubleObject dest = null;
    if (source instanceof Double) {
      // check to see if the object already exists
      if (destination == null) {
        dest = new CustomDoubleObject();
      } else {
        dest = (CustomDoubleObject) destination;
      }
      dest.setTheDouble(((Double) source).doubleValue());
      return dest;
    } else if (source instanceof CustomDoubleObject) {
      double sourceObj =
        ((CustomDoubleObject) source).getTheDouble();
      return new Double(sourceObj);
    } else {
      throw new MappingException("Converter TestCustomConverter "
          + "used incorrectly. Arguments passed in were:"
          + destination + " and " + source);
    }
  }
```

(2) 在 xml 中引用定制的映射规则

引用定制的映射规则也是分级的，你可以酌情使用。

- 全局级

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mappings xmlns="http://dozer.sourceforge.net"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://dozer.sourceforge.net
          http://dozer.sourceforge.net/schema/beanmapping.xsd">
  <configuration>
    <!-- 总是双向转换的 -->
    <custom-converters>
      <converter type="org.dozer.converters.TestCustomConverter" >
        <class-a>org.dozer.vo.CustomDoubleObject</class-a>
        <class-b>java.lang.Double</class-b>
      </converter>

      <!-- You are responsible for mapping everything between
           ClassA and ClassB -->
      <converter
        type="org.dozer.converters.TestCustomHashMapConverter" >
        <class-a>org.dozer.vo.TestCustomConverterHashMapObject</class-a>
        <class-b>org.dozer.vo.TestCustomConverterHashMapPrimeObject</class-b>
      </converter>
    </custom-converters>
  </configuration>
</mappings>
```

- 属性级

```xml
<mapping>
  <class-a>org.dozer.vo.SimpleObj</class-a>
  <class-b>org.dozer.vo.SimpleObjPrime2</class-b>
  <field custom-converter=
    "org.dozer.converters.TestCustomConverter">
    <a>field1</a>
    <b>field1Prime</b>
  </field>
</mapping>
```

#### 映射的继承(Inheritance Mapping)

Dozer 支持映射规则的继承机制。

属性如果有着相同的名字则不需要在 xml 中配置，除非使用了`hint`

我们来看一个例子

```xml
<mapping>
  <class-a>org.dozer.vo.SuperClass</class-a>
  <class-b>org.dozer.vo.SuperClassPrime</class-b>

  <field>
    <a>superAttribute</a>
    <b>superAttr</b>
  </field>
</mapping>

<mapping>
  <class-a>org.dozer.vo.SubClass</class-a>
  <class-b>org.dozer.vo.SubClassPrime</class-b>

  <field>
    <a>attribute</a>
    <b>attributePrime</b>
  </field>
</mapping>

<mapping>
  <class-a>org.dozer.vo.SubClass2</class-a>
  <class-b>org.dozer.vo.SubClassPrime2</class-b>

  <field>
    <a>attribute2</a>
    <b>attributePrime2</b>
  </field>
</mapping>
```

在上面的例子中 SubClass、SubClass2 是 SuperClass 的子类；

SubClassPrime 和 SubClassPrime2 是 SuperClassPrime 的子类。

superAttribute 和 superAttr 的映射规则会被子类所继承，所以不必再重复的在子类中去声明。

## 参考

[Dozer 官方文档](http://dozer.sourceforge.net/documentation/gettingstarted.html) | [Dozer 源码地址](https://github.com/DozerMapper/dozer)
