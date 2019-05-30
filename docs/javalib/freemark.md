# Freemark 使用指南

> FreeMarker 是一款 模板引擎： 即一种基于模板和要改变的数据， 并用来生成输出文本(HTML 网页，电子邮件，配置文件，源代码等)的通用工具。 它不是面向最终用户的，而是一个 Java 类库，是一款程序员可以嵌入他们所开发产品的组件。

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
    - [什么是 Freemark？](#什么是-freemark)
- [入门](#入门)
    - [创建 Configuration 实例](#创建-configuration-实例)
    - [创建数据模型](#创建数据模型)
    - [获取模板](#获取模板)
    - [合并模板和数据模型](#合并模板和数据模型)
    - [完整示例](#完整示例)
- [基础](#基础)
    - [数值](#数值)
    - [类型](#类型)
    - [模板](#模板)
- [参考资料](#参考资料)

<!-- /TOC -->

## 简介

### 什么是 Freemark？

FreeMarker 是一款 模板引擎： 即一种基于模板和要改变的数据， 并用来生成输出文本(HTML 网页，电子邮件，配置文件，源代码等)的通用工具。 它不是面向最终用户的，而是一个 Java 类库，是一款程序员可以嵌入他们所开发产品的组件。

简言之：模板 + 数据 = 输出

<div align="center"><img src="http://freemarker.foofun.cn/figures/overview.png"/></div>

## 入门

### 创建 Configuration 实例

首先，你应该创建一个 `freemarker.template.Configuration` 实例， 然后调整它的设置。Configuration 实例是存储 FreeMarker 应用级设置的核心部分。同时，它也处理创建和缓存预解析模板(比如 Template 对象)的工作。

```java
// Create your Configuration instance, and specify if up to what FreeMarker
// version (here 2.3.22) do you want to apply the fixes that are not 100%
// backward-compatible. See the Configuration JavaDoc for details.
Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

// Specify the source where the template files come from. Here I set a
// plain directory for it, but non-file-system sources are possible too:
cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));

// Set the preferred charset template files are stored in. UTF-8 is
// a good choice in most applications:
cfg.setDefaultEncoding("UTF-8");

// Sets how errors will appear.
// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
```

> 注：不需要重复创建 Configuration 实例；它的代价很高，尤其是会丢失缓存。Configuration 实例就是应用级别的单例。

### 创建数据模型

在简单的示例中你可以使用 `java.lang` 和 `java.util` 包中的类， 还有用户自定义的 Java Bean 来构建数据对象：

- 使用 `java.lang.String` 来构建字符串。
- 使用 `java.lang.Number` 来派生数字类型。
- 使用 `java.lang.Boolean` 来构建布尔值。
- 使用 `java.util.List` 或 Java 数组来构建序列。
- 使用 `java.util.Map` 来构建哈希表。
- 使用自定义的 bean 类来构建哈希表，bean 中的项和 bean 的属性对应。比如， `product` 的 `price` 属性 (`getProperty()`)可以通过 `product.price` 获取。(bean 的 action 也可以通过这种方式拿到； 要了解更多可以参看 [这里](http://freemarker.foofun.cn/pgui_misc_beanwrapper.html))

示例：

```java
// Create the root hash
Map<String, Object> root = new HashMap<>();
// Put string ``user'' into the root
root.put("user", "Big Joe");
// Create the hash for ``latestProduct''
Map<String, Object> latest = new HashMap<>();
// and put it into the root
root.put("latestProduct", latest);
// put ``url'' and ``name'' into latest
latest.put("url", "products/greenmouse.html");
latest.put("name", "green mouse");
```

### 获取模板

模板代表了 `freemarker.template.Template` 实例。典型的做法是从 `Configuration` 实例中获取一个 `Template` 实例。无论什么时候你需要一个模板实例， 都可以使用它的 `getTemplate` 方法来获取。在 [之前](http://freemarker.foofun.cn/pgui_quickstart_createconfiguration.html) 设置的目录中的 `test.ftl` 文件中存储 [示例模板](http://freemarker.foofun.cn/dgui_quickstart_basics.html#example.first)，那么就可以这样来做：

```java
Template temp = cfg.getTemplate("test.ftl");
```

当调用这个方法的时候，将会创建一个 `test.ftl` 的 `Template` 实例，通过读取 `*/where/you/store/templates/*test.ftl` 文件，之后解析(编译)它。`Template` 实例以解析后的形式存储模板， 而不是以源文件的文本形式。

`Configuration` 缓存 `Template` 实例，当再次获得 `test.ftl` 的时候，它可能再读取和解析模板文件了， 而只是返回第一次的 `Template`实例。

### 合并模板和数据模型

我们已经知道，数据模型+模板=输出，我们有了一个数据模型 (`root`) 和一个模板 (`temp`)， 为了得到输出就需要合并它们。这是由模板的 `process` 方法完成的。它用数据模型 root 和 `Writer` 对象作为参数，然后向 `Writer` 对象写入产生的内容。 为简单起见，这里我们只做标准的输出：

```
Writer out = new OutputStreamWriter(System.out);
temp.process(root, out);
```

这会向你的终端输出你在模板开发指南部分的 [第一个示例](http://freemarker.foofun.cn/dgui_quickstart_basics.html#example.first) 中看到的内容。

Java I/O 相关注意事项：基于 `out` 对象，必须保证 `out.close()` 最后被调用。当 `out` 对象被打开并将模板的输出写入文件时，这是很电影的做法。其它时候， 比如典型的 Web 应用程序，那就 _不能_ 关闭 `out` 对象。FreeMarker 会在模板执行成功后 (也可以在 `Configuration` 中禁用) 调用 `out.flush()`，所以不必为此担心。

请注意，一旦获得了 `Template` 实例， 就能将它和不同的数据模型进行不限次数 (`Template`实例是无状态的)的合并。此外， 当 `Template`实例创建之后 `test.ftl` 文件才能访问，而不是在调用处理方法时。

### 完整示例

[**源码**](https://github.com/dunwu/spring-boot-tutorial/tree/master/codes/template/sbe-tmpl-freemark)

## 基础

### 数值

注意观察每个数据模型的例子你也许能发现：被"(root)"所标识的内容就是哈希表类型的值。 当编写如 `user` 这样的代码时，那就意味着要把"user"变量存储在哈希表的根上。 就像编写 `root.user`一样，这里但并没有名"root"为的变量， 那么这就起不到任何作用了。

某些人也许会被这种数据模型的例子所困惑，也就是说，根哈希表包含更多的哈希表或序列 (`lotteryNumbers` and `cargo`)。其它就没有更特殊的内容了。 哈希表包含其他变量，那些变量包含其它值，这些数值可以是字符串，数字等变量， 当然也可以是哈希表或序列变量。最初我们解释过的，就像字符串和数字， 序列或哈希表也是一种值的表示形式。

### 类型

Freemark 支持的类型有：

- 标量：
  - 字符串
  - 数字
  - 布尔值
  - 日期/时间 (日期，时间或日期时间)
- 容器：
  - 哈希表
  - 序列
  - 集合
- 子程序：
  - [方法和函数](http://freemarker.foofun.cn/dgui_datamodel_types.html#dgui_datamodel_method)
  - [用户自定义指令](http://freemarker.foofun.cn/dgui_datamodel_types.html#dgui_datamodel_userdefdir)
- 其它/很少使用：
  - [结点](http://freemarker.foofun.cn/dgui_datamodel_types.html#dgui_datamodel_node)

### 模板

#### 总体结构

模板(FTL 编程)是由如下部分混合而成的：

- **文本**：文本会照着原样来输出。
- **插值**：这部分的输出会被计算的值来替换。插值由 `${` and `}` 所分隔。
- **FTL 标签**：FTL 标签和 HTML 标签很相似，但是它们却是给 FreeMarker 的指示， 而且不会打印在输出内容中。
- **注释**：注释和 HTML 的注释也很相似，但它们是由 `<#--` 和 `-->`来分隔的。注释会被 FreeMarker 直接忽略， 更不会在输出内容中显示。

<div align="center"><img src="https://gitee.com/turnon/images/raw/master/snap/ftl-template.png"/></div>

> :warning: 注意：
>
> - FTL 是区分大小写的。
> - `插值` 仅仅可以在 `文本` 中使用。
> - `FTL 标签` 不可以在其他 `FTL 标签` 和 `插值` 中使用。
> - `注释` 可以放在 `FTL 标签` 和 `插值` 中。

#### 指令

使用 FTL 标签来调用 **指令**。

FTL 标签分为两种：

- 开始标签： `<#*directivename* *parameters*>`
- 结束标签： `</#*directivename*>`

除了标签以 `#` 开头外，其他都和 HTML，XML 的语法很相似。 如果标签没有嵌套内容(在开始标签和结束标签之间的内容)，那么可以只使用开始标签。 例如 `<#if *something*>*...*</#if>`， 而 FreeMarker 知道 `<#include *something*>` 中的 `include` 指令没有可嵌套的内容。

`*parameters*` 的格式由 `*directivename*`来决定。

事实上，指令有两种类型： [预定义指令](http://freemarker.foofun.cn/gloss.html#gloss.predefinedDirective) 和 [用户自定义指令](http://freemarker.foofun.cn/gloss.html#gloss.userDefinedDirective)。 对于用户自定义的指令使用 `@` 来代替 `#`。

> :warning: 注意：
>
> - FreeMarker 仅仅关心 FTL 标签的嵌套而不关心 HTML 标签的嵌套。 它只会把 HTML 看做是文本，不会来解释 HTML。
> - 如果你尝试使用一个不存在的指令(比如，输错了指令的名称)， FreeMarker 就会拒绝执行模板，同时抛出错误信息。
> - FreeMarker 会忽略 FTL 标签中多余的 [空白标记](http://freemarker.foofun.cn/gloss.html#gloss.whiteSpace)。

#### 表达式

以下为快速浏览清单，如果需要了解更多细节，请参考[**这里**](http://freemarker.foofun.cn/dgui_template_exp.html)。

- [直接指定值](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct)
  - [字符串](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct_string)： `"Foo"` 或者 `'Foo'` 或者 `"It's \"quoted\""` 或者 `'It\'s "quoted"'` 或者 `r"C:\raw\string"`
  - [数字](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct_number)： `123.45`
  - [布尔值](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct_boolean)： `true`， `false`
  - [序列](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct_seuqence)： `["foo", "bar", 123.45]`； 值域： `0..9`, `0..<10` (或 `0..!10`), `0..`
  - [哈希表](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct_hash)： `{"name":"green mouse", "price":150}`
- [检索变量](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_var)
  - [顶层变量](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_var_toplevel)： `user`
  - [从哈希表中检索数据](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_var_hash)： `user.name`， `user["name"]`
  - [从序列中检索数据](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_var_sequence)： `products[5]`
  - [特殊变量](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_var_special)： `.main`
- [字符串操作](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_stringop)
  - [插值(或连接)](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_stringop_interpolation)： `"Hello ${user}!"` (或 `"Hello " + user + "!"`)
  - [获取一个字符](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_get_character)： `name[0]`
  - [字符串切分：](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_stringop_slice) 包含结尾： `name[0..4]`，不包含结尾： `name[0..<5]`，基于长度(宽容处理)： `name[0..*5]`，去除开头：`name[5..]`
- [序列操作](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_sequenceop)
  - [连接](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_sequenceop_cat)： `users + ["guest"]`
  - [序列切分](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_seqenceop_slice)：包含结尾： `products[20..29]`， 不包含结尾： `products[20..<30]`，基于长度(宽容处理)：`products[20..*10]`，去除开头： `products[20..]`
- [哈希表操作](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_hashop)
  - [连接](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_hashop_cat)： `passwords + { "joe": "secret42" }`
- [算术运算](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_arit)： `(x * 1.5 + 10) / 2 - y % 100`
- [比较运算](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_comparison)： `x == y`， `x != y`， `x < y`， `x > y`， `x >= y`， `x <= y`， `x lt y`， `x lte y`， `x gt y`， `x gte y`， 等等。。。。。。
- [逻辑操作](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_logicalop)： `!registered && (firstVisit || fromEurope)`
- [内建函数](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_builtin)： `name?upper_case`, `path?ensure_starts_with('/')`
- [方法调用](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_methodcall)： `repeat("What", 3)`
- [处理不存在的值](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_missing)
  - [默认值](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_missing_default)： `name!"unknown"` 或者 `(user.name)!"unknown"` 或者 `name!` 或者 `(user.name)!`
  - [检测不存在的值](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_missing_test)： `name??` 或者 `(user.name)??`
- [赋值操作](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_assignment)： `=`, `+=`, `-=`, `*=`, `/=`, `%=`, `++`, `--`

#### 插值

插值的使用格式是： `${*expression*}`，这里的 `*expression*` 可以是所有种类的表达式(比如 `${100 + x}`)。

插值是用来给 `*表达式*` 插入具体值然后转换为文本(字符串)。插值仅仅可以在两种位置使用：在 [文本 区](http://freemarker.foofun.cn/dgui_template_overallstructure.html) (比如 `<h1>Hello ${name}!</h1>`) 和 [字符串表达式](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_stringop_interpolation) (比如 `<#include "/footer/${company}.html">`)中。

表达式的结果必须是字符串，数字或者日期/时间/日期-时间值， 因为(默认是这样)仅仅这些值可以被插值自动转换为字符串。其它类型的值 (比如布尔值，序列)必须 "手动地" 转换成字符串(后续会有一些建议)， 否则就会发生错误，中止模板执行。

注意：插值 _仅仅_ 在 [文本区](http://freemarker.foofun.cn/dgui_template_overallstructure.html) (比如 `<h1>Hello ${name}!</h1>`) 和 [字符串](http://freemarker.foofun.cn/dgui_template_exp.html#dgui_template_exp_direct_string) 中起作用。

:heavy_check_mark: `<#include "/footer/${company}.html">`

:heavy_check_mark: `<#if big>...</#if>`

:x: `<#if ${big}>...</#if>`

:x: `<#if "${big}">...</#if>`

## 参考资料

- http://freemarker.foofun.cn/
