(window.webpackJsonp=window.webpackJsonp||[]).push([[84],{447:function(e,v,a){"use strict";a.r(v);var r=a(14),_=Object(r.a)({},(function(){var e=this,v=e._self._c;return v("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[v("h1",{attrs:{id:"spring-泛型处理"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#spring-泛型处理"}},[e._v("#")]),e._v(" Spring 泛型处理")]),e._v(" "),v("h2",{attrs:{id:"java-泛型基础"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#java-泛型基础"}},[e._v("#")]),e._v(" Java 泛型基础")]),e._v(" "),v("p",[e._v("泛型类型")]),e._v(" "),v("ul",[v("li",[e._v("泛型类型是在类型上参数化的泛型类或接口")])]),e._v(" "),v("p",[e._v("泛型使用场景")]),e._v(" "),v("ul",[v("li",[e._v("编译时强类型检查")]),e._v(" "),v("li",[e._v("避免类型强转")]),e._v(" "),v("li",[e._v("实现通用算法")])]),e._v(" "),v("p",[e._v("泛型类型擦写")]),e._v(" "),v("ul",[v("li",[e._v("泛型被引入到 Java 语言中，以便在编译时提供更严格的类型检查并支持泛型编程。类型擦除确保不会\n为参数化类型创建新类；因此，泛型不会产生运行时开销。为了实现泛型，编译器将类型擦除应用于：\n"),v("ul",[v("li",[e._v("将泛型类型中的所有类型参数替换为其边界，如果类型参数是无边界的，则将其替换为\n“Object”。因此，生成的字节码只包含普通类、接口和方法")]),e._v(" "),v("li",[e._v("必要时插入类型转换以保持类型安全")]),e._v(" "),v("li",[e._v("生成桥方法以保留扩展泛型类型中的多态性")])])])]),e._v(" "),v("h2",{attrs:{id:"java-5-类型接口"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#java-5-类型接口"}},[e._v("#")]),e._v(" Java 5 类型接口")]),e._v(" "),v("p",[e._v("Java 5 类型接口 - "),v("code",[e._v("java.lang.reflect.Type")])]),e._v(" "),v("table",[v("thead",[v("tr",[v("th",[e._v("派生类或接口")]),e._v(" "),v("th",[e._v("说明")])])]),e._v(" "),v("tbody",[v("tr",[v("td",[v("code",[e._v("java.lang.Class")])]),e._v(" "),v("td",[e._v("Java 类 API，如 "),v("code",[e._v("java.lang.String")])])]),e._v(" "),v("tr",[v("td",[v("code",[e._v("java.lang.reflect.GenericArrayType")])]),e._v(" "),v("td",[e._v("泛型数组类型")])]),e._v(" "),v("tr",[v("td",[v("code",[e._v("java.lang.reflect.ParameterizedType")])]),e._v(" "),v("td",[e._v("泛型参数类型")])]),e._v(" "),v("tr",[v("td",[v("code",[e._v("java.lang.reflect.TypeVariable")])]),e._v(" "),v("td",[e._v("泛型类型变量，如 "),v("code",[e._v("Collection<E>")]),e._v(" 中的 E")])]),e._v(" "),v("tr",[v("td",[v("code",[e._v("java.lang.reflect.WildcardType")])]),e._v(" "),v("td",[e._v("泛型通配类型")])])])]),e._v(" "),v("p",[e._v("Java 泛型反射 API")]),e._v(" "),v("table",[v("thead",[v("tr",[v("th",[e._v("类型")]),e._v(" "),v("th",[e._v("API")])])]),e._v(" "),v("tbody",[v("tr",[v("td",[e._v("泛型信息（Generics Info）")]),e._v(" "),v("td",[v("code",[e._v("java.lang.Class#getGenericInfo()")])])]),e._v(" "),v("tr",[v("td",[e._v("泛型参数（Parameters）")]),e._v(" "),v("td",[v("code",[e._v("java.lang.reflect.ParameterizedType")])])]),e._v(" "),v("tr",[v("td",[e._v("泛型父类（Super Classes）")]),e._v(" "),v("td",[v("code",[e._v("java.lang.Class#getGenericSuperclass()")])])]),e._v(" "),v("tr",[v("td",[e._v("泛型接口（Interfaces）")]),e._v(" "),v("td",[v("code",[e._v("java.lang.Class#getGenericInterfaces()")])])]),e._v(" "),v("tr",[v("td",[e._v("泛型声明（Generics Declaration）")]),e._v(" "),v("td",[v("code",[e._v("java.lang.reflect.GenericDeclaration")])])])])]),e._v(" "),v("h2",{attrs:{id:"spring-泛型类型辅助类"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#spring-泛型类型辅助类"}},[e._v("#")]),e._v(" Spring 泛型类型辅助类")]),e._v(" "),v("p",[e._v("核心 API - "),v("code",[e._v("org.springframework.core.GenericTypeResolver")])]),e._v(" "),v("ul",[v("li",[e._v("版本支持：[2.5.2 , )")]),e._v(" "),v("li",[e._v("处理类型相关（Type）相关方法\n"),v("ul",[v("li",[v("code",[e._v("resolveReturnType")])]),e._v(" "),v("li",[v("code",[e._v("resolveType")])])])]),e._v(" "),v("li",[e._v("处理泛型参数类型（"),v("code",[e._v("ParameterizedType")]),e._v("）相关方法\n"),v("ul",[v("li",[v("code",[e._v("resolveReturnTypeArgument")])]),e._v(" "),v("li",[v("code",[e._v("resolveTypeArgument")])]),e._v(" "),v("li",[v("code",[e._v("resolveTypeArguments")])])])]),e._v(" "),v("li",[e._v("处理泛型类型变量（"),v("code",[e._v("TypeVariable")]),e._v("）相关方法\n"),v("ul",[v("li",[v("code",[e._v("getTypeVariableMap")])])])])]),e._v(" "),v("h2",{attrs:{id:"spring-泛型集合类型辅助类"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#spring-泛型集合类型辅助类"}},[e._v("#")]),e._v(" Spring 泛型集合类型辅助类")]),e._v(" "),v("p",[e._v("核心 API - "),v("code",[e._v("org.springframework.core.GenericCollectionTypeResolver")])]),e._v(" "),v("ul",[v("li",[e._v("版本支持：[2.0 , 4.3]")]),e._v(" "),v("li",[e._v("替换实现："),v("code",[e._v("org.springframework.core.ResolvableType")])]),e._v(" "),v("li",[e._v("处理 Collection 相关\n"),v("ul",[v("li",[v("code",[e._v("getCollection*Type")])])])]),e._v(" "),v("li",[e._v("处理 Map 相关\n"),v("ul",[v("li",[v("code",[e._v("getMapKey*Type")])]),e._v(" "),v("li",[v("code",[e._v("getMapValue*Type")])])])])]),e._v(" "),v("h2",{attrs:{id:"spring-方法参数封装-methodparameter"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#spring-方法参数封装-methodparameter"}},[e._v("#")]),e._v(" Spring 方法参数封装 - MethodParameter")]),e._v(" "),v("p",[e._v("核心 API - "),v("code",[e._v("org.springframework.core.MethodParameter")])]),e._v(" "),v("ul",[v("li",[e._v("起始版本：[2.0 , )")]),e._v(" "),v("li",[e._v("元信息\n"),v("ul",[v("li",[e._v("关联的方法 - Method")]),e._v(" "),v("li",[e._v("关联的构造器 - Constructor")]),e._v(" "),v("li",[e._v("构造器或方法参数索引 - parameterIndex")]),e._v(" "),v("li",[e._v("构造器或方法参数类型 - parameterType")]),e._v(" "),v("li",[e._v("构造器或方法参数泛型类型 - genericParameterType")]),e._v(" "),v("li",[e._v("构造器或方法参数参数名称 - parameterName")]),e._v(" "),v("li",[e._v("所在的类 - containingClass")])])])]),e._v(" "),v("h2",{attrs:{id:"spring-4-0-泛型优化实现-resolvabletype"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#spring-4-0-泛型优化实现-resolvabletype"}},[e._v("#")]),e._v(" Spring 4.0 泛型优化实现 - ResolvableType")]),e._v(" "),v("p",[e._v("核心 API - "),v("code",[e._v("org.springframework.core.ResolvableType")])]),e._v(" "),v("ul",[v("li",[e._v("起始版本：[4.0 , )")]),e._v(" "),v("li",[e._v("扮演角色："),v("code",[e._v("GenericTypeResolver")]),e._v(" 和 "),v("code",[e._v("GenericCollectionTypeResolver")]),e._v(" 替代者")]),e._v(" "),v("li",[e._v("工厂方法："),v("code",[e._v("for*")]),e._v(" 方法")]),e._v(" "),v("li",[e._v("转换方法："),v("code",[e._v("as*")]),e._v(" 方法")]),e._v(" "),v("li",[e._v("处理方法："),v("code",[e._v("resolve*")]),e._v(" 方法")])]),e._v(" "),v("h2",{attrs:{id:"resolvabletype-的局限性"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#resolvabletype-的局限性"}},[e._v("#")]),e._v(" ResolvableType 的局限性")]),e._v(" "),v("ul",[v("li",[e._v("局限一：ResolvableType 无法处理泛型擦写")]),e._v(" "),v("li",[e._v("局限二：ResolvableType 无法处理非具体化的 ParameterizedType")])]),e._v(" "),v("h2",{attrs:{id:"问题"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#问题"}},[e._v("#")]),e._v(" 问题")]),e._v(" "),v("p",[v("strong",[e._v("Java 泛型擦写发生在编译时还是运行时")]),e._v("？")]),e._v(" "),v("p",[e._v("运行时")]),e._v(" "),v("p",[v("strong",[e._v("请介绍 Java 5 Type 类型的派生类或接口")])]),e._v(" "),v("ul",[v("li",[v("code",[e._v("java.lang.Class")])]),e._v(" "),v("li",[v("code",[e._v("java.lang.reflect.GenericArrayType")])]),e._v(" "),v("li",[v("code",[e._v("java.lang.reflect.ParameterizedType")])]),e._v(" "),v("li",[v("code",[e._v("java.lang.reflect.TypeVariable")])]),e._v(" "),v("li",[v("code",[e._v("java.lang.reflect.WildcardType")])])]),e._v(" "),v("p",[v("strong",[e._v("请说明 ResolvableType 的设计优势")]),e._v("？")]),e._v(" "),v("ul",[v("li",[e._v("简化 Java 5 Type API 开发，屏蔽复杂 API 的运用，如 ParameterizedType")]),e._v(" "),v("li",[e._v("不变性设计（Immutability）")]),e._v(" "),v("li",[e._v("Fluent API 设计（Builder 模式），链式（流式）编程")])]),e._v(" "),v("h2",{attrs:{id:"参考资料"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#参考资料"}},[e._v("#")]),e._v(" 参考资料")]),e._v(" "),v("ul",[v("li",[v("a",{attrs:{href:"https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans",target:"_blank",rel:"noopener noreferrer"}},[e._v("Spring 官方文档之 Core Technologies"),v("OutboundLink")],1)]),e._v(" "),v("li",[v("a",{attrs:{href:"https://time.geekbang.org/course/intro/265",target:"_blank",rel:"noopener noreferrer"}},[e._v("《小马哥讲 Spring 核心编程思想》"),v("OutboundLink")],1)])])])}),[],!1,null,null,null);v.default=_.exports}}]);