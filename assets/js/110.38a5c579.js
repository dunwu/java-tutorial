(window.webpackJsonp=window.webpackJsonp||[]).push([[110],{473:function(t,a,s){"use strict";s.r(a);var n=s(14),e=Object(n.a)({},(function(){var t=this,a=t._self._c;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"spring-集成-dubbo"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#spring-集成-dubbo"}},[t._v("#")]),t._v(" Spring 集成 Dubbo")]),t._v(" "),a("h2",{attrs:{id:"zookeeper"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#zookeeper"}},[t._v("#")]),t._v(" ZooKeeper")]),t._v(" "),a("p",[t._v("ZooKeeper 可以作为 Dubbo 的注册中心。")]),t._v(" "),a("p",[t._v("Dubbo 未对 Zookeeper 服务器端做任何侵入修改，只需安装原生的 Zookeeper 服务器即可，所有注册中心逻辑适配都在调用 Zookeeper 客户端时完成。")]),t._v(" "),a("p",[a("strong",[t._v("安装")])]),t._v(" "),a("p",[t._v("在 "),a("a",{attrs:{href:"http://zookeeper.apache.org/releases.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("ZooKeeper 发布中心"),a("OutboundLink")],1),t._v(" 选择需要的版本，下载后解压到本地。")]),t._v(" "),a("p",[a("strong",[t._v("配置")])]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("vi conf/zoo.cfg\n\n")])])]),a("p",[t._v("如果不需要集群，"),a("code",[t._v("zoo.cfg")]),t._v(" 的内容如下 "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-admin-book/content/install/zookeeper.html#fn_2",target:"_blank",rel:"noopener noreferrer"}},[t._v("2"),a("OutboundLink")],1),t._v("：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("tickTime=2000\ninitLimit=10\nsyncLimit=5\ndataDir=/home/dubbo/zookeeper-3.3.3/data\nclientPort=2181\n")])])]),a("p",[t._v("如果需要集群，"),a("code",[t._v("zoo.cfg")]),t._v(" 的内容如下 "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-admin-book/content/install/zookeeper.html#fn_3",target:"_blank",rel:"noopener noreferrer"}},[t._v("3"),a("OutboundLink")],1),t._v("：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("tickTime=2000\ninitLimit=10\nsyncLimit=5\ndataDir=/home/dubbo/zookeeper-3.3.3/data\nclientPort=2181\nserver.1=10.20.153.10:2555:3555\nserver.2=10.20.153.11:2555:3555\n\n")])])]),a("p",[t._v("并在 data 目录 "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-admin-book/content/install/zookeeper.html#fn_4",target:"_blank",rel:"noopener noreferrer"}},[t._v("4"),a("OutboundLink")],1),t._v(" 下放置 myid 文件：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("mkdir data\nvi myid\n\n")])])]),a("p",[t._v("myid 指明自己的 id，对应上面 "),a("code",[t._v("zoo.cfg")]),t._v(" 中 "),a("code",[t._v("server.")]),t._v(" 后的数字，第一台的内容为 1，第二台的内容为 2，内容如下：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("1\n\n")])])]),a("p",[a("strong",[t._v("启动")])]),t._v(" "),a("p",[t._v("Linux 下执行 "),a("code",[t._v("bin/zkServer.sh")]),t._v(" ；Windows "),a("code",[t._v("bin/zkServer.cmd")]),t._v(" 启动 ZooKeeper 。")]),t._v(" "),a("p",[a("strong",[t._v("命令行")])]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("telnet 127.0.0.1 2181\ndump\n")])])]),a("p",[t._v("或者:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("echo dump | nc 127.0.0.1 2181\n")])])]),a("p",[t._v("用法:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("dubbo.registry.address=zookeeper://10.20.153.10:2181?backup=10.20.153.11:2181\n\n")])])]),a("p",[t._v("或者:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('<dubbo:registry protocol="zookeeper" address="10.20.153.10:2181,10.20.153.11:2181" />\n\n')])])]),a("blockquote",[a("ol",[a("li",[t._v("Zookeeper 是 Apache Hadoop 的子项目，强度相对较好，建议生产环境使用该注册中心")]),t._v(" "),a("li",[t._v("其中 data 目录需改成你真实输出目录")]),t._v(" "),a("li",[t._v("其中 data 目录和 server 地址需改成你真实部署机器的信息")]),t._v(" "),a("li",[t._v("上面 "),a("code",[t._v("zoo.cfg")]),t._v(" 中的 "),a("code",[t._v("dataDir")])]),t._v(" "),a("li",[a("a",{attrs:{href:"http://zookeeper.apache.org/doc/r3.3.3/zookeeperAdmin.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("http://zookeeper.apache.org/doc/r3.3.3/zookeeperAdmin.html"),a("OutboundLink")],1)])])]),t._v(" "),a("h2",{attrs:{id:"dubbo"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#dubbo"}},[t._v("#")]),t._v(" Dubbo")]),t._v(" "),a("p",[t._v("Dubbo 采用全 Spring 配置方式，透明化接入应用，对应用没有任何 API 侵入，只需用 Spring 加载 Dubbo 的配置即可，Dubbo 基于 Spring 的 Schema 扩展进行加载。")]),t._v(" "),a("p",[t._v("如果不想使用 Spring 配置，可以通过 "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/configuration/api.md",target:"_blank",rel:"noopener noreferrer"}},[t._v("API 的方式"),a("OutboundLink")],1),t._v(" 进行调用。")]),t._v(" "),a("h2",{attrs:{id:"服务提供者"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#服务提供者"}},[t._v("#")]),t._v(" 服务提供者")]),t._v(" "),a("p",[t._v("完整安装步骤，请参见："),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-admin-book/install/provider-demo.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("示例提供者安装"),a("OutboundLink")],1)]),t._v(" "),a("h3",{attrs:{id:"定义服务接口"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#定义服务接口"}},[t._v("#")]),t._v(" 定义服务接口")]),t._v(" "),a("p",[t._v("DemoService.java "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-user-book/quick-start.html#fn_1",target:"_blank",rel:"noopener noreferrer"}},[t._v("1"),a("OutboundLink")],1),t._v("：")]),t._v(" "),a("div",{staticClass:"language-java extra-class"},[a("pre",{pre:!0,attrs:{class:"language-java"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("package")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("com"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("alibaba"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("dubbo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("demo")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n\n"),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("interface")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("DemoService")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("sayHello")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),t._v(" name"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),a("h3",{attrs:{id:"在服务提供方实现接口"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#在服务提供方实现接口"}},[t._v("#")]),t._v(" 在服务提供方实现接口")]),t._v(" "),a("p",[t._v("DemoServiceImpl.java "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-user-book/quick-start.html#fn_2",target:"_blank",rel:"noopener noreferrer"}},[t._v("2"),a("OutboundLink")],1),t._v("：")]),t._v(" "),a("div",{staticClass:"language-java extra-class"},[a("pre",{pre:!0,attrs:{class:"language-java"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("package")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("com"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("alibaba"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("dubbo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("demo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("provider")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n\n"),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("import")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token import"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("com"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("alibaba"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("dubbo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("demo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")])]),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("DemoService")])]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n\n"),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("DemoServiceImpl")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("implements")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("DemoService")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("sayHello")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),t._v(" name"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("return")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"Hello "')]),t._v(" "),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("+")]),t._v(" name"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),a("h3",{attrs:{id:"用-spring-配置声明暴露服务"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#用-spring-配置声明暴露服务"}},[t._v("#")]),t._v(" 用 Spring 配置声明暴露服务")]),t._v(" "),a("p",[t._v("provider.xml：")]),t._v(" "),a("div",{staticClass:"language-xml extra-class"},[a("pre",{pre:!0,attrs:{class:"language-xml"}},[a("code",[a("span",{pre:!0,attrs:{class:"token prolog"}},[t._v('<?xml version="1.0" encoding="UTF-8"?>')]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),t._v("beans")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("xmlns")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://www.springframework.org/schema/beans"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("xmlns:")]),t._v("xsi")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://www.w3.org/2001/XMLSchema-instance"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("xmlns:")]),t._v("dubbo")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://code.alibabatech.com/schema/dubbo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("xsi:")]),t._v("schemaLocation")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans.xsd        http://code.alibabatech.com/schema/dubbo        http://code.alibabatech.com/schema/dubbo/dubbo.xsd"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 提供方应用信息，用于计算依赖关系 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("application")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("name")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("hello-world-app"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 使用multicast广播注册中心暴露服务地址 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("registry")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("address")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("multicast://224.5.6.7:1234"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 用dubbo协议在20880端口暴露服务 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("protocol")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("name")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("dubbo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("port")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("20880"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 声明需要暴露的服务接口 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("service")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("interface")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("com.alibaba.dubbo.demo.DemoService"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("ref")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("demoService"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 和本地bean一样实现服务 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),t._v("bean")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("id")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("demoService"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("class")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("com.alibaba.dubbo.demo.provider.DemoServiceImpl"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("</")]),t._v("beans")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n")])])]),a("p",[t._v("如果注册中心使用 ZooKeeper，可以将 dubbo:registry 改为 zookeeper://127.0.0.1:2181")]),t._v(" "),a("h3",{attrs:{id:"加载-spring-配置"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#加载-spring-配置"}},[t._v("#")]),t._v(" 加载 Spring 配置")]),t._v(" "),a("p",[t._v("Provider.java：")]),t._v(" "),a("div",{staticClass:"language-java extra-class"},[a("pre",{pre:!0,attrs:{class:"language-java"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("import")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token import"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("org"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("springframework"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("context"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("support"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")])]),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("ClassPathXmlApplicationContext")])]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n\n"),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("Provider")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("static")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("void")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("main")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v(" args"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("throws")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("Exception")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("ClassPathXmlApplicationContext")]),t._v(" context "),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("new")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("ClassPathXmlApplicationContext")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("new")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"http://10.20.160.198/wiki/display/dubbo/provider.xml"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n        context"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("start")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n        "),a("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("System")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("in"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),a("span",{pre:!0,attrs:{class:"token function"}},[t._v("read")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("// 按任意键退出")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),a("h2",{attrs:{id:"服务消费者"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#服务消费者"}},[t._v("#")]),t._v(" 服务消费者")]),t._v(" "),a("p",[t._v("完整安装步骤，请参见："),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-admin-book/install/consumer-demo.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("示例消费者安装"),a("OutboundLink")],1)]),t._v(" "),a("h3",{attrs:{id:"通过-spring-配置引用远程服务"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#通过-spring-配置引用远程服务"}},[t._v("#")]),t._v(" 通过 Spring 配置引用远程服务")]),t._v(" "),a("p",[t._v("consumer.xml：")]),t._v(" "),a("div",{staticClass:"language-xml extra-class"},[a("pre",{pre:!0,attrs:{class:"language-xml"}},[a("code",[a("span",{pre:!0,attrs:{class:"token prolog"}},[t._v('<?xml version="1.0" encoding="UTF-8"?>')]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),t._v("beans")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("xmlns")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://www.springframework.org/schema/beans"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("xmlns:")]),t._v("xsi")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://www.w3.org/2001/XMLSchema-instance"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("xmlns:")]),t._v("dubbo")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://code.alibabatech.com/schema/dubbo"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("xsi:")]),t._v("schemaLocation")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans.xsd        http://code.alibabatech.com/schema/dubbo        http://code.alibabatech.com/schema/dubbo/dubbo.xsd"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("application")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("name")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("consumer-of-helloworld-app"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v("  "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 使用multicast广播注册中心暴露发现服务地址 --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("registry")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("address")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("multicast://224.5.6.7:1234"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[t._v("\x3c!-- 生成远程服务代理，可以和本地bean一样使用demoService --\x3e")]),t._v("\n    "),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),a("span",{pre:!0,attrs:{class:"token namespace"}},[t._v("dubbo:")]),t._v("reference")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("id")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("demoService"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("interface")]),a("span",{pre:!0,attrs:{class:"token attr-value"}},[a("span",{pre:!0,attrs:{class:"token punctuation attr-equals"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')]),t._v("com.alibaba.dubbo.demo.DemoService"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v('"')])]),t._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("/>")])]),t._v("\n"),a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token tag"}},[a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("</")]),t._v("beans")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n")])])]),a("p",[t._v("如果注册中心使用 ZooKeeper，可以将 dubbo:registry 改为 zookeeper://127.0.0.1:2181")]),t._v(" "),a("h3",{attrs:{id:"加载-spring-配置-并调用远程服务"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#加载-spring-配置-并调用远程服务"}},[t._v("#")]),t._v(" 加载 Spring 配置，并调用远程服务")]),t._v(" "),a("p",[t._v("Consumer.java "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-user-book/quick-start.html#fn_3",target:"_blank",rel:"noopener noreferrer"}},[t._v("3"),a("OutboundLink")],1),t._v("：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('import org.springframework.context.support.ClassPathXmlApplicationContext;\nimport com.alibaba.dubbo.demo.DemoService;\n\npublic class Consumer {\n    public static void main(String[] args) throws Exception {\n        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"http://10.20.160.198/wiki/display/dubbo/consumer.xml"});\n        context.start();\n        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理\n        String hello = demoService.sayHello("world"); // 执行远程方法\n        System.out.println( hello ); // 显示调用结果\n    }\n}\n')])])]),a("blockquote",[a("ol",[a("li",[t._v("该接口需单独打包，在服务提供方和消费方共享")]),t._v(" "),a("li",[t._v("对服务消费方隐藏实现")]),t._v(" "),a("li",[t._v("也可以使用 IoC 注入")])])]),t._v(" "),a("h2",{attrs:{id:"faq"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#faq"}},[t._v("#")]),t._v(" FAQ")]),t._v(" "),a("p",[t._v("建议使用 "),a("code",[t._v("dubbo-2.3.3")]),t._v(" 以上版本的 zookeeper 注册中心客户端。")]),t._v(" "),a("h2",{attrs:{id:"资料"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#资料"}},[t._v("#")]),t._v(" 资料")]),t._v(" "),a("p",[a("strong",[t._v("Dubbo")])]),t._v(" "),a("p",[a("a",{attrs:{href:"https://github.com/alibaba/dubbo",target:"_blank",rel:"noopener noreferrer"}},[t._v("Github"),a("OutboundLink")],1),t._v(" | "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-user-book/content/",target:"_blank",rel:"noopener noreferrer"}},[t._v("用户手册"),a("OutboundLink")],1),t._v(" | "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-dev-book/content/",target:"_blank",rel:"noopener noreferrer"}},[t._v("开发手册"),a("OutboundLink")],1),t._v(" | "),a("a",{attrs:{href:"https://dubbo.gitbooks.io/dubbo-admin-book/content/",target:"_blank",rel:"noopener noreferrer"}},[t._v("管理员手册"),a("OutboundLink")],1)]),t._v(" "),a("p",[a("strong",[t._v("ZooKeeper")])]),t._v(" "),a("p",[a("a",{attrs:{href:"http://zookeeper.apache.org/",target:"_blank",rel:"noopener noreferrer"}},[t._v("官网"),a("OutboundLink")],1),t._v(" | "),a("a",{attrs:{href:"http://zookeeper.apache.org/doc/trunk/",target:"_blank",rel:"noopener noreferrer"}},[t._v("官方文档"),a("OutboundLink")],1)])])}),[],!1,null,null,null);a.default=e.exports}}]);