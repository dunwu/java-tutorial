# Reflections 应用指南

<!-- TOC depthFrom:2 depthTo:3 -->

- [使用](#使用)
- [ReflectionUtils](#reflectionutils)

<!-- /TOC -->

引入

```xml
<dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>0.9.11</version>
</dependency>
```

典型应用

```java
Reflections reflections = new Reflections("my.project");
Set<Class<? extends SomeType>> subTypes = reflections.getSubTypesOf(SomeType.class);
Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SomeAnnotation.class);
```

## 使用

基本上，使用 Reflections 首先使用 urls 和 scanners 对其进行实例化

```java
//scan urls that contain 'my.package', include inputs starting with 'my.package', use the default scanners
Reflections reflections = new Reflections("my.package");

//or using ConfigurationBuilder
new Reflections(new ConfigurationBuilder()
     .setUrls(ClasspathHelper.forPackage("my.project.prefix"))
     .setScanners(new SubTypesScanner(),
                  new TypeAnnotationsScanner().filterResultsBy(optionalFilter), ...),
     .filterInputsBy(new FilterBuilder().includePackage("my.project.prefix"))
     ...);
```

然后，使用方便的查询方法

```java
// 子类型扫描
Set<Class<? extends Module>> modules =
    reflections.getSubTypesOf(com.google.inject.Module.class);
// 类型注解扫描
Set<Class<?>> singletons =
    reflections.getTypesAnnotatedWith(javax.inject.Singleton.class);
// 资源扫描
Set<String> properties =
    reflections.getResources(Pattern.compile(".*\\.properties"));
// 方法注解扫描
Set<Method> resources =
    reflections.getMethodsAnnotatedWith(javax.ws.rs.Path.class);
Set<Constructor> injectables =
    reflections.getConstructorsAnnotatedWith(javax.inject.Inject.class);
// 字段注解扫描
Set<Field> ids =
    reflections.getFieldsAnnotatedWith(javax.persistence.Id.class);
// 方法参数扫描
Set<Method> someMethods =
    reflections.getMethodsMatchParams(long.class, int.class);
Set<Method> voidMethods =
    reflections.getMethodsReturn(void.class);
Set<Method> pathParamMethods =
    reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
// 方法参数名扫描
List<String> parameterNames =
    reflections.getMethodParamNames(Method.class)
// 方法使用扫描
Set<Member> usages =
    reflections.getMethodUsages(Method.class)
```

说明：

- 如果未配置扫描程序，则将使用默认值 - SubTypesScanner 和 TypeAnnotationsScanner。
- 还可以配置类加载器，它将用于从名称中解析运行时类。
- Reflection 默认情况下会扩展超类型。 这解决了传输 URL 不被扫描的一些问题。

## ReflectionUtils

```java
import static org.reflections.ReflectionUtils.*;

Set<Method> getters = getAllMethods(someClass,
  withModifier(Modifier.PUBLIC), withPrefix("get"), withParametersCount(0));

//or
Set<Method> listMethodsFromCollectionToBoolean =
  getAllMethods(List.class,
    withParametersAssignableTo(Collection.class), withReturnType(boolean.class));

Set<Field> fields = getAllFields(SomeClass.class, withAnnotation(annotation), withTypeAssignableTo(type));
```
