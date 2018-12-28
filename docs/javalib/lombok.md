# Lombok 使用小结

<!-- TOC depthFrom:2 depthTo:3 -->

- [Lombok 简介](#lombok-简介)
- [Lombok 安装](#lombok-安装)
- [Lombok 使用](#lombok-使用)
    - [API](#api)
    - [示例](#示例)
- [示例源码](#示例源码)
- [引用和引申](#引用和引申)

<!-- /TOC -->

## Lombok 简介

Lombok 是一种 Java 实用工具，可用来帮助开发人员消除 Java 的冗长，尤其是对于简单的 Java 对象（POJO）。它通过注释实现这一目的。通过在开发环境中实现 Lombok，开发人员可以节省构建诸如 `hashCode()` 和 `equals()` 、`getter / setter` 这样的方法以及以往用来分类各种 accessor 和 mutator 的大量时间。

## Lombok 安装

使 IntelliJ IDEA 支持 Lombok 方式如下：

（1）**Intellij 设置支持注解处理**

点击 `File > Settings > Build > Annotation Processors`

勾选 Enable annotation processing

（2）**安装插件**

点击 `Settings > Plugins > Browse repositories`

查找 Lombok Plugin 并进行安装

重启 IntelliJ IDEA

（3）**将 lombok 添加到 pom 文件**

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.16.8</version>
</dependency>
```

## Lombok 使用

### API

Lombok 提供注解 API 来修饰指定的类：

#### @Getter and @Setter

[@Getter and @Setter](http://jnb.ociweb.com/jnb/jnbJan2010.html#gettersetter) Lombok 代码：

```java
@Getter @Setter private boolean employed = true;
@Setter(AccessLevel.PROTECTED) private String name;
```

等价于 Java 源码：

```java
private boolean employed = true;
private String name;

public boolean isEmployed() {
    return employed;
}

public void setEmployed(final boolean employed) {
    this.employed = employed;
}

protected void setName(final String name) {
    this.name = name;
}
```

#### @NonNull

[@NonNull](http://jnb.ociweb.com/jnb/jnbJan2010.html#nonnull) Lombok 代码：

```
@Getter @Setter @NonNull
private List<Person> members;
```

等价于 Java 源码：

```java
@NonNull
private List<Person> members;

public Family(@NonNull final List<Person> members) {
    if (members == null) throw new java.lang.NullPointerException("members");
    this.members = members;
}

@NonNull
public List<Person> getMembers() {
    return members;
}

public void setMembers(@NonNull final List<Person> members) {
    if (members == null) throw new java.lang.NullPointerException("members");
    this.members = members;
}
```

#### @ToString

[@ToString](http://jnb.ociweb.com/jnb/jnbJan2010.html#tostring) Lombok 代码：

```java
@ToString(callSuper=true,exclude="someExcludedField")
public class Foo extends Bar {
    private boolean someBoolean = true;
    private String someStringField;
    private float someExcludedField;
}
```

等价于 Java 源码：

```java
public class Foo extends Bar {
    private boolean someBoolean = true;
    private String someStringField;
    private float someExcludedField;

    @java.lang.Override
    public java.lang.String toString() {
        return "Foo(super=" + super.toString() +
            ", someBoolean=" + someBoolean +
            ", someStringField=" + someStringField + ")";
    }
}
```

#### @EqualsAndHashCode

[@EqualsAndHashCode](http://jnb.ociweb.com/jnb/jnbJan2010.html#equals) Lombok 代码：

```java
@EqualsAndHashCode(callSuper=true,exclude={"address","city","state","zip"})
public class Person extends SentientBeing {
    enum Gender { Male, Female }

    @NonNull private String name;
    @NonNull private Gender gender;

    private String ssn;
    private String address;
    private String city;
    private String state;
    private String zip;
}
```

等价于 Java 源码：

```java
public class Person extends SentientBeing {

    enum Gender {
        /*public static final*/ Male /* = new Gender() */,
        /*public static final*/ Female /* = new Gender() */;
    }
    @NonNull
    private String name;
    @NonNull
    private Gender gender;
    private String ssn;
    private String address;
    private String city;
    private String state;
    private String zip;

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        if (!super.equals(o)) return false;
        final Person other = (Person)o;
        if (this.name == null ? other.name != null : !this.name.equals(other.name)) return false;
        if (this.gender == null ? other.gender != null : !this.gender.equals(other.gender)) return false;
        if (this.ssn == null ? other.ssn != null : !this.ssn.equals(other.ssn)) return false;
        return true;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + super.hashCode();
        result = result * PRIME + (this.name == null ? 0 : this.name.hashCode());
        result = result * PRIME + (this.gender == null ? 0 : this.gender.hashCode());
        result = result * PRIME + (this.ssn == null ? 0 : this.ssn.hashCode());
        return result;
    }
}
```

#### @Data

[@Data](http://jnb.ociweb.com/jnb/jnbJan2010.html#data) Lombok 代码：

```java
@Data(staticConstructor="of")
public class Company {
    private final Person founder;
    private String name;
    private List<Person> employees;
}
```

等价于 Java 源码：

```java
public class Company {
    private final Person founder;
    private String name;
    private List<Person> employees;

    private Company(final Person founder) {
        this.founder = founder;
    }

    public static Company of(final Person founder) {
        return new Company(founder);
    }

    public Person getFounder() {
        return founder;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(final List<Person> employees) {
        this.employees = employees;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Company other = (Company)o;
        if (this.founder == null ? other.founder != null : !this.founder.equals(other.founder)) return false;
        if (this.name == null ? other.name != null : !this.name.equals(other.name)) return false;
        if (this.employees == null ? other.employees != null : !this.employees.equals(other.employees)) return false;
        return true;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + (this.founder == null ? 0 : this.founder.hashCode());
        result = result * PRIME + (this.name == null ? 0 : this.name.hashCode());
        result = result * PRIME + (this.employees == null ? 0 : this.employees.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Company(founder=" + founder + ", name=" + name + ", employees=" + employees + ")";
    }
}
```

#### @Cleanup

[@Cleanup](http://jnb.ociweb.com/jnb/jnbJan2010.html#cleanup) Lombok 代码：

```java
public void testCleanUp() {
    try {
        @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(new byte[] {'Y','e','s'});
        System.out.println(baos.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

等价于 Java 源码：

```java
public void testCleanUp() {
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(new byte[]{'Y', 'e', 's'});
            System.out.println(baos.toString());
        } finally {
            baos.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

#### @Synchronized

[@Synchronized](http://jnb.ociweb.com/jnb/jnbJan2010.html#synchronized) Lombok 代码：

```java
private DateFormat format = new SimpleDateFormat("MM-dd-YYYY");

@Synchronized
public String synchronizedFormat(Date date) {
    return format.format(date);
}
```

等价于 Java 源码：

```java
private final java.lang.Object $lock = new java.lang.Object[0];
private DateFormat format = new SimpleDateFormat("MM-dd-YYYY");

public String synchronizedFormat(Date date) {
    synchronized ($lock) {
        return format.format(date);
    }
}
```

#### @SneakyThrows

[@SneakyThrows](http://jnb.ociweb.com/jnb/jnbJan2010.html#sneaky) Lombok 代码：

```java
@SneakyThrows
public void testSneakyThrows() {
    throw new IllegalAccessException();
}
```

等价于 Java 源码：

```java
public void testSneakyThrows() {
    try {
        throw new IllegalAccessException();
    } catch (java.lang.Throwable $ex) {
        throw lombok.Lombok.sneakyThrow($ex);
    }
}
```

### 示例

使用 Lombok 定义一个 Java Bean

```java
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "age")
public class Person {
    private String name;
    private Integer age;
    private String sex;
}
```

测试

```java
Person person = new Person();
person.setName("张三");
person.setAge(20);
person.setSex("男");
System.out.println(person.toString());
// output: Person(name=张三, sex=男)
```

## 示例源码

完整示例：[源码](https://github.com/dunwu/spring-boot-tutorial/tree/master/codes/core/sbe-bean-lombok)

## 引用和引申

**引申**

- [JavaStack](https://github.com/dunwu/javastack)

**参考**

- [Lombok 官网](https://projectlombok.org/)
- [Lombok Github](https://github.com/rzwitserloot/lombok)
- [IntelliJ IDEA - Lombok Plugin](http://plugins.jetbrains.com/plugin/6317-lombok-plugin)
