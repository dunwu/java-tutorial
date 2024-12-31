(window.webpackJsonp=window.webpackJsonp||[]).push([[75],{438:function(e,t,r){"use strict";r.r(t);var a=r(14),i=Object(a.a)({},(function(){var e=this,t=e._self._c;return t("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[t("h1",{attrs:{id:"spring-应用上下文生命周期"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下文生命周期"}},[e._v("#")]),e._v(" Spring 应用上下文生命周期")]),e._v(" "),t("h2",{attrs:{id:"spring-应用上下文启动准备阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下文启动准备阶段"}},[e._v("#")]),e._v(" Spring 应用上下文启动准备阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#prepareRefresh() 方法")]),e._v(" "),t("ul",[t("li",[e._v("启动时间 - startupDate")]),e._v(" "),t("li",[e._v("状态标识 - closed(false)、active(true)")]),e._v(" "),t("li",[e._v("初始化 PropertySources - initPropertySources()")]),e._v(" "),t("li",[e._v("检验 Environment 中必须属性")]),e._v(" "),t("li",[e._v("初始化事件监听器集合")]),e._v(" "),t("li",[e._v("初始化早期 Spring 事件集合")])]),e._v(" "),t("h2",{attrs:{id:"beanfactory-创建阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#beanfactory-创建阶段"}},[e._v("#")]),e._v(" BeanFactory 创建阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#obtainFreshBeanFactory() 方法")]),e._v(" "),t("ul",[t("li",[e._v("刷新 Spring 应用上下文底层 BeanFactory - refreshBeanFactory()\n"),t("ul",[t("li",[e._v("销毁或关闭 BeanFactory，如果已存在的话")]),e._v(" "),t("li",[e._v("创建 BeanFactory - createBeanFactory()")]),e._v(" "),t("li",[e._v("设置 BeanFactory Id")]),e._v(" "),t("li",[e._v("设置“是否允许 BeanDefinition 重复定义” - customizeBeanFactory(DefaultListableBeanFactory)")]),e._v(" "),t("li",[e._v("设置“是否允许循环引用（依赖）” - customizeBeanFactory(DefaultListableBeanFactory)")]),e._v(" "),t("li",[e._v("加载 BeanDefinition - loadBeanDefinitions(DefaultListableBeanFactory) 方法")]),e._v(" "),t("li",[e._v("关联新建 BeanFactory 到 Spring 应用上下文")])])]),e._v(" "),t("li",[e._v("返回 Spring 应用上下文底层 BeanFactory - getBeanFactory()")])]),e._v(" "),t("h2",{attrs:{id:"beanfactory-准备阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#beanfactory-准备阶段"}},[e._v("#")]),e._v(" BeanFactory 准备阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory) 方法")]),e._v(" "),t("ul",[t("li",[e._v("关联 ClassLoader")]),e._v(" "),t("li",[e._v("设置 Bean 表达式处理器")]),e._v(" "),t("li",[e._v("添加 PropertyEditorRegistrar 实现 - ResourceEditorRegistrar")]),e._v(" "),t("li",[e._v("添加 Aware 回调接口 BeanPostProcessor 实现 - ApplicationContextAwareProcessor")]),e._v(" "),t("li",[e._v("忽略 Aware 回调接口作为依赖注入接口")]),e._v(" "),t("li",[e._v("注册 ResolvableDependency 对象 - BeanFactory、ResourceLoader、ApplicationEventPublisher 以及 ApplicationContext")]),e._v(" "),t("li",[e._v("注册 ApplicationListenerDetector 对象")]),e._v(" "),t("li",[e._v("注册 LoadTimeWeaverAwareProcessor 对象")]),e._v(" "),t("li",[e._v("注册单例对象 - Environment、Java System Properties 以及 OS 环境变量")])]),e._v(" "),t("h2",{attrs:{id:"beanfactory-后置处理阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#beanfactory-后置处理阶段"}},[e._v("#")]),e._v(" BeanFactory 后置处理阶段")]),e._v(" "),t("ul",[t("li",[e._v("AbstractApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory) 方法\n"),t("ul",[t("li",[e._v("由子类覆盖该方法")])])]),e._v(" "),t("li",[e._v("AbstractApplicationContext#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory 方法\n"),t("ul",[t("li",[e._v("调用 BeanFactoryPostProcessor 或 BeanDefinitionRegistry 后置处理方法")]),e._v(" "),t("li",[e._v("注册 LoadTimeWeaverAwareProcessor 对象")])])])]),e._v(" "),t("h2",{attrs:{id:"beanfactory-注册-beanpostprocessor-阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#beanfactory-注册-beanpostprocessor-阶段"}},[e._v("#")]),e._v(" BeanFactory 注册 BeanPostProcessor 阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory) 方法")]),e._v(" "),t("ul",[t("li",[e._v("注册 PriorityOrdered 类型的 BeanPostProcessor Beans")]),e._v(" "),t("li",[e._v("注册 Ordered 类型的 BeanPostProcessor Beans")]),e._v(" "),t("li",[e._v("注册普通 BeanPostProcessor Beans")]),e._v(" "),t("li",[e._v("注册 MergedBeanDefinitionPostProcessor Beans")]),e._v(" "),t("li",[e._v("注册 ApplicationListenerDetector 对象")])]),e._v(" "),t("h2",{attrs:{id:"初始化內建-bean-messagesource"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#初始化內建-bean-messagesource"}},[e._v("#")]),e._v(" 初始化內建 Bean：MessageSource")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#initMessageSource() 方法")]),e._v(" "),t("h2",{attrs:{id:"初始化內建-bean-spring-事件广播器"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#初始化內建-bean-spring-事件广播器"}},[e._v("#")]),e._v(" 初始化內建 Bean：Spring 事件广播器")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#initApplicationEventMulticaster() 方法")]),e._v(" "),t("h2",{attrs:{id:"spring-应用上下文刷新阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下文刷新阶段"}},[e._v("#")]),e._v(" Spring 应用上下文刷新阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#onRefresh() 方法")]),e._v(" "),t("p",[e._v("子类覆盖该方法")]),e._v(" "),t("ul",[t("li",[e._v("org.springframework.web.context.support.AbstractRefreshableWebApplicationContext#onRefresh()")]),e._v(" "),t("li",[e._v("org.springframework.web.context.support.GenericWebApplicationContext#onRefresh()")]),e._v(" "),t("li",[e._v("org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext#onRefresh()")]),e._v(" "),t("li",[e._v("org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext#onRefresh()")]),e._v(" "),t("li",[e._v("org.springframework.web.context.support.StaticWebApplicationContext#onRefresh()")])]),e._v(" "),t("h2",{attrs:{id:"spring-事件监听器注册阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-事件监听器注册阶段"}},[e._v("#")]),e._v(" Spring 事件监听器注册阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#registerListeners() 方法")]),e._v(" "),t("ul",[t("li",[e._v("添加当前应用上下文所关联的 ApplicationListener 对象（集合）")]),e._v(" "),t("li",[e._v("添加 BeanFactory 所注册 ApplicationListener Beans")]),e._v(" "),t("li",[e._v("广播早期 Spring 事件")])]),e._v(" "),t("h2",{attrs:{id:"beanfactory-初始化完成阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#beanfactory-初始化完成阶段"}},[e._v("#")]),e._v(" BeanFactory 初始化完成阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory) 方法")]),e._v(" "),t("ul",[t("li",[e._v("BeanFactory 关联 ConversionService Bean，如果存在")]),e._v(" "),t("li",[e._v("添加 StringValueResolver 对象")]),e._v(" "),t("li",[e._v("依赖查找 LoadTimeWeaverAware Bean")]),e._v(" "),t("li",[e._v("BeanFactory 临时 ClassLoader 置为 null")]),e._v(" "),t("li",[e._v("BeanFactory 冻结配置")]),e._v(" "),t("li",[e._v("BeanFactory 初始化非延迟单例 Beans")])]),e._v(" "),t("h2",{attrs:{id:"spring-应用上下刷新完成阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下刷新完成阶段"}},[e._v("#")]),e._v(" Spring 应用上下刷新完成阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#finishRefresh() 方法")]),e._v(" "),t("ul",[t("li",[e._v("清除 ResourceLoader 缓存 - clearResourceCaches() @since 5.0")]),e._v(" "),t("li",[e._v("初始化 LifecycleProcessor 对象 - initLifecycleProcessor()")]),e._v(" "),t("li",[e._v("调用 LifecycleProcessor#onRefresh() 方法")]),e._v(" "),t("li",[e._v("发布 Spring 应用上下文已刷新事件 - ContextRefreshedEvent")]),e._v(" "),t("li",[e._v("向 MBeanServer 托管 Live Beans")])]),e._v(" "),t("h2",{attrs:{id:"spring-应用上下文启动阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下文启动阶段"}},[e._v("#")]),e._v(" Spring 应用上下文启动阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#start() 方法")]),e._v(" "),t("ul",[t("li",[e._v("启动 LifecycleProcessor\n"),t("ul",[t("li",[e._v("依赖查找 Lifecycle Beans")]),e._v(" "),t("li",[e._v("启动 Lifecycle Beans")])])]),e._v(" "),t("li",[e._v("发布 Spring 应用上下文已启动事件 - ContextStartedEvent")])]),e._v(" "),t("h2",{attrs:{id:"spring-应用上下文停止阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下文停止阶段"}},[e._v("#")]),e._v(" Spring 应用上下文停止阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#stop() 方法")]),e._v(" "),t("ul",[t("li",[e._v("停止 LifecycleProcessor\n"),t("ul",[t("li",[e._v("依赖查找 Lifecycle Beans")]),e._v(" "),t("li",[e._v("停止 Lifecycle Beans")])])]),e._v(" "),t("li",[e._v("发布 Spring 应用上下文已停止事件 - ContextStoppedEvent")])]),e._v(" "),t("h2",{attrs:{id:"spring-应用上下文关闭阶段"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#spring-应用上下文关闭阶段"}},[e._v("#")]),e._v(" Spring 应用上下文关闭阶段")]),e._v(" "),t("p",[e._v("AbstractApplicationContext#close() 方法")]),e._v(" "),t("ul",[t("li",[e._v("状态标识：active(false)、closed(true)")]),e._v(" "),t("li",[e._v("Live Beans JMX 撤销托管\n"),t("ul",[t("li",[e._v("LiveBeansView.unregisterApplicationContext(ConfigurableApplicationContext)")])])]),e._v(" "),t("li",[e._v("发布 Spring 应用上下文已关闭事件 - ContextClosedEvent")]),e._v(" "),t("li",[e._v("关闭 LifecycleProcessor\n"),t("ul",[t("li",[e._v("依赖查找 Lifecycle Beans")]),e._v(" "),t("li",[e._v("停止 Lifecycle Beans")])])]),e._v(" "),t("li",[e._v("销毁 Spring Beans")]),e._v(" "),t("li",[e._v("关闭 BeanFactory")]),e._v(" "),t("li",[e._v("回调 onClose()")]),e._v(" "),t("li",[e._v("注册 Shutdown Hook 线程（如果曾注册）")])]),e._v(" "),t("h2",{attrs:{id:"问题"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#问题"}},[e._v("#")]),e._v(" 问题")]),e._v(" "),t("p",[t("strong",[e._v("Spring 应用上下文生命周期有哪些阶段")]),e._v("？")]),e._v(" "),t("ul",[t("li",[e._v("刷新阶段 - ConfigurableApplicationContext#refresh()")]),e._v(" "),t("li",[e._v("启动阶段 - ConfigurableApplicationContext#start()")]),e._v(" "),t("li",[e._v("停止阶段 - ConfigurableApplicationContext#stop()")]),e._v(" "),t("li",[e._v("关闭阶段 - ConfigurableApplicationContext#close()")])]),e._v(" "),t("h2",{attrs:{id:"参考资料"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#参考资料"}},[e._v("#")]),e._v(" 参考资料")]),e._v(" "),t("ul",[t("li",[t("a",{attrs:{href:"https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans",target:"_blank",rel:"noopener noreferrer"}},[e._v("Spring 官方文档之 Core Technologies"),t("OutboundLink")],1)]),e._v(" "),t("li",[t("a",{attrs:{href:"https://time.geekbang.org/course/intro/265",target:"_blank",rel:"noopener noreferrer"}},[e._v("《小马哥讲 Spring 核心编程思想》"),t("OutboundLink")],1)])])])}),[],!1,null,null,null);t.default=i.exports}}]);