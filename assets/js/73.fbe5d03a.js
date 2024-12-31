(window.webpackJsonp=window.webpackJsonp||[]).push([[73],{436:function(a,e,n){"use strict";n.r(e);var r=n(14),i=Object(r.a)({},(function(){var a=this,e=a._self._c;return e("ContentSlotsDistributor",{attrs:{"slot-key":a.$parent.slotKey}},[e("h1",{attrs:{id:"spring-bean-生命周期"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-生命周期"}},[a._v("#")]),a._v(" Spring Bean 生命周期")]),a._v(" "),e("h2",{attrs:{id:"spring-bean-元信息配置阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-元信息配置阶段"}},[a._v("#")]),a._v(" Spring Bean 元信息配置阶段")]),a._v(" "),e("p",[a._v("BeanDefinition 配置")]),a._v(" "),e("ul",[e("li",[a._v("面向资源\n"),e("ul",[e("li",[a._v("XML 配置")]),a._v(" "),e("li",[a._v("Properties 资源配置")])])]),a._v(" "),e("li",[a._v("面向注解")]),a._v(" "),e("li",[a._v("面向 API")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-元信息解析阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-元信息解析阶段"}},[a._v("#")]),a._v(" Spring Bean 元信息解析阶段")]),a._v(" "),e("ul",[e("li",[a._v("面向资源 BeanDefinition 解析\n"),e("ul",[e("li",[a._v("BeanDefinitionReader")]),a._v(" "),e("li",[a._v("XML 解析器 - BeanDefinitionParser")])])]),a._v(" "),e("li",[a._v("面向注解 BeanDefinition 解析\n"),e("ul",[e("li",[a._v("AnnotatedBeanDefinitionReader")])])])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-注册阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-注册阶段"}},[a._v("#")]),a._v(" Spring Bean 注册阶段")]),a._v(" "),e("p",[a._v("BeanDefinition 注册接口：BeanDefinitionRegistry")]),a._v(" "),e("h2",{attrs:{id:"spring-beandefinition-合并阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-beandefinition-合并阶段"}},[a._v("#")]),a._v(" Spring BeanDefinition 合并阶段")]),a._v(" "),e("p",[a._v("BeanDefinition 合并")]),a._v(" "),e("p",[a._v("父子 BeanDefinition 合并")]),a._v(" "),e("ul",[e("li",[a._v("当前 BeanFactory 查找")]),a._v(" "),e("li",[a._v("层次性 BeanFactory 查找")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-class-加载阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-class-加载阶段"}},[a._v("#")]),a._v(" Spring Bean Class 加载阶段")]),a._v(" "),e("ul",[e("li",[a._v("ClassLoader 类加载")]),a._v(" "),e("li",[a._v("Java Security 安全控制")]),a._v(" "),e("li",[a._v("ConfigurableBeanFactory 临时 ClassLoader")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-实例化前阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-实例化前阶段"}},[a._v("#")]),a._v(" Spring Bean 实例化前阶段")]),a._v(" "),e("p",[a._v("实例化方式")]),a._v(" "),e("ul",[e("li",[a._v("传统实例化方式：实例化策略（InstantiationStrategy）")]),a._v(" "),e("li",[a._v("构造器依赖注入")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-实例化阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-实例化阶段"}},[a._v("#")]),a._v(" Spring Bean 实例化阶段")]),a._v(" "),e("p",[a._v("非主流生命周期 - Bean 实例化前阶段")]),a._v(" "),e("p",[a._v("InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation")]),a._v(" "),e("h2",{attrs:{id:"spring-bean-实例化后阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-实例化后阶段"}},[a._v("#")]),a._v(" Spring Bean 实例化后阶段")]),a._v(" "),e("p",[a._v("Bean 属性赋值（Populate）判断")]),a._v(" "),e("p",[a._v("InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation")]),a._v(" "),e("h2",{attrs:{id:"spring-bean-属性赋值前阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-属性赋值前阶段"}},[a._v("#")]),a._v(" Spring Bean 属性赋值前阶段")]),a._v(" "),e("ul",[e("li",[a._v("Bean 属性值元信息\n"),e("ul",[e("li",[a._v("PropertyValues")])])]),a._v(" "),e("li",[a._v("Bean 属性赋值前回调\n"),e("ul",[e("li",[a._v("Spring 1.2 - 5.0：InstantiationAwareBeanPostProcessor#postProcessPropertyValues")]),a._v(" "),e("li",[a._v("Spring 5.1：InstantiationAwareBeanPostProcessor#postProcessProperties")])])])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-aware-接口回调阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-aware-接口回调阶段"}},[a._v("#")]),a._v(" Spring Bean Aware 接口回调阶段")]),a._v(" "),e("p",[a._v("Spring Aware 接口：")]),a._v(" "),e("ul",[e("li",[a._v("BeanNameAware")]),a._v(" "),e("li",[a._v("BeanClassLoaderAware")]),a._v(" "),e("li",[a._v("BeanFactoryAware")]),a._v(" "),e("li",[a._v("EnvironmentAware")]),a._v(" "),e("li",[a._v("EmbeddedValueResolverAware")]),a._v(" "),e("li",[a._v("ResourceLoaderAware")]),a._v(" "),e("li",[a._v("ApplicationEventPublisherAware")]),a._v(" "),e("li",[a._v("MessageSourceAware")]),a._v(" "),e("li",[a._v("ApplicationContextAware")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-初始化前阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-初始化前阶段"}},[a._v("#")]),a._v(" Spring Bean 初始化前阶段")]),a._v(" "),e("p",[a._v("已完成：")]),a._v(" "),e("ul",[e("li",[e("p",[a._v("Bean 实例化")])]),a._v(" "),e("li",[e("p",[a._v("Bean 属性赋值")])]),a._v(" "),e("li",[e("p",[a._v("Bean Aware 接口回调")])])]),a._v(" "),e("p",[a._v("方法回调：")]),a._v(" "),e("ul",[e("li",[a._v("BeanPostProcessor#postProcessBeforeInitialization")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-初始化阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-初始化阶段"}},[a._v("#")]),a._v(" Spring Bean 初始化阶段")]),a._v(" "),e("p",[a._v("Bean 初始化（Initialization）")]),a._v(" "),e("ul",[e("li",[a._v("@PostConstruct 标注方法")]),a._v(" "),e("li",[a._v("实现 InitializingBean 接口的 afterPropertiesSet() 方法")]),a._v(" "),e("li",[a._v("自定义初始化方法")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-初始化后阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-初始化后阶段"}},[a._v("#")]),a._v(" Spring Bean 初始化后阶段")]),a._v(" "),e("p",[a._v("方法回调：BeanPostProcessor#postProcessAfterInitialization")]),a._v(" "),e("h2",{attrs:{id:"spring-bean-初始化完成阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-初始化完成阶段"}},[a._v("#")]),a._v(" Spring Bean 初始化完成阶段")]),a._v(" "),e("p",[a._v("方法回调：Spring 4.1 +：SmartInitializingSingleton#afterSingletonsInstantiated")]),a._v(" "),e("h2",{attrs:{id:"spring-bean-销毁前阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-销毁前阶段"}},[a._v("#")]),a._v(" Spring Bean 销毁前阶段")]),a._v(" "),e("p",[a._v("方法回调：DestructionAwareBeanPostProcessor#postProcessBeforeDestruction")]),a._v(" "),e("h2",{attrs:{id:"spring-bean-销毁阶段"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-销毁阶段"}},[a._v("#")]),a._v(" Spring Bean 销毁阶段")]),a._v(" "),e("p",[a._v("Bean 销毁（Destroy）")]),a._v(" "),e("ul",[e("li",[a._v("@PreDestroy 标注方法")]),a._v(" "),e("li",[a._v("实现 DisposableBean 接口的 destroy() 方法")]),a._v(" "),e("li",[a._v("自定义销毁方法")])]),a._v(" "),e("h2",{attrs:{id:"spring-bean-垃圾收集"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#spring-bean-垃圾收集"}},[a._v("#")]),a._v(" Spring Bean 垃圾收集")]),a._v(" "),e("p",[a._v("Bean 垃圾回收（GC）")]),a._v(" "),e("ul",[e("li",[a._v("关闭 Spring 容器（应用上下文）")]),a._v(" "),e("li",[a._v("执行 GC")]),a._v(" "),e("li",[a._v("Spring Bean 覆盖的 finalize() 方法被回调")])]),a._v(" "),e("h2",{attrs:{id:"问题"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#问题"}},[a._v("#")]),a._v(" 问题")]),a._v(" "),e("p",[e("strong",[a._v("BeanPostProcessor 的使用场景有哪些")]),a._v("？")]),a._v(" "),e("p",[a._v("BeanPostProcessor 提供 Spring Bean 初始化前和初始化后的生命周期回调，分别对应 postProcessBeforeInitialization 以及 postProcessAfterInitialization 方法，允许对关心的 Bean 进行扩展，甚至是替换。")]),a._v(" "),e("p",[a._v("加分项：其中，ApplicationContext 相关的 Aware 回调也是基于 BeanPostProcessor 实现，即 ApplicationContextAwareProcessor。")]),a._v(" "),e("p",[e("strong",[a._v("BeanFactoryPostProcessor 与 BeanPostProcessor 的区别")]),a._v("？")]),a._v(" "),e("p",[a._v("BeanFactoryPostProcessor 是 Spring BeanFactory（实际为 ConfigurableListableBeanFactory） 的后置处理器，用于扩展 BeanFactory，或通过 BeanFactory 进行依赖查找和依赖注入。")]),a._v(" "),e("p",[a._v("BeanFactoryPostProcessor 必须有 Spring ApplicationContext 执行，BeanFactory 无法与其直接交互。")]),a._v(" "),e("p",[a._v("而 BeanPostProcessor 则直接与 BeanFactory 关联，属于 N 对 1 的关系。")]),a._v(" "),e("p",[e("strong",[a._v("BeanFactory 是怎样处理 Bean 生命周期")]),a._v("？")]),a._v(" "),e("p",[a._v("BeanFactory 的默认实现为 "),e("code",[a._v("DefaultListableBeanFactory")]),a._v("，其中 Bean生命周期与方法映射如下：")]),a._v(" "),e("ul",[e("li",[a._v("BeanDefinition 注册阶段 - registerBeanDefinition")]),a._v(" "),e("li",[a._v("BeanDefinition 合并阶段 - getMergedBeanDefinition")]),a._v(" "),e("li",[a._v("Bean 实例化前阶段 - resolveBeforeInstantiation")]),a._v(" "),e("li",[a._v("Bean 实例化阶段 - createBeanInstance")]),a._v(" "),e("li",[a._v("Bean 初始化后阶段 - populateBean")]),a._v(" "),e("li",[a._v("Bean 属性赋值前阶段 - populateBean")]),a._v(" "),e("li",[a._v("Bean 属性赋值阶段 - populateBean")]),a._v(" "),e("li",[a._v("Bean Aware 接口回调阶段 - initializeBean")]),a._v(" "),e("li",[a._v("Bean 初始化前阶段 - initializeBean")]),a._v(" "),e("li",[a._v("Bean 初始化阶段 - initializeBean")]),a._v(" "),e("li",[a._v("Bean 初始化后阶段 - initializeBean")]),a._v(" "),e("li",[a._v("Bean 初始化完成阶段 - preInstantiateSingletons")]),a._v(" "),e("li",[a._v("Bean 销毁前阶段 - destroyBean")]),a._v(" "),e("li",[a._v("Bean 销毁阶段 - destroyBean")])]),a._v(" "),e("h2",{attrs:{id:"参考资料"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#参考资料"}},[a._v("#")]),a._v(" 参考资料")]),a._v(" "),e("ul",[e("li",[e("a",{attrs:{href:"https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans",target:"_blank",rel:"noopener noreferrer"}},[a._v("Spring 官方文档之 Core Technologies"),e("OutboundLink")],1)]),a._v(" "),e("li",[e("a",{attrs:{href:"https://time.geekbang.org/course/intro/265",target:"_blank",rel:"noopener noreferrer"}},[a._v("《小马哥讲 Spring 核心编程思想》"),e("OutboundLink")],1)])])])}),[],!1,null,null,null);e.default=i.exports}}]);