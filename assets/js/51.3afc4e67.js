(window.webpackJsonp=window.webpackJsonp||[]).push([[51],{415:function(t,e,a){"use strict";a.r(e);var r=a(14),s=Object(r.a)({},(function(){var t=this,e=t._self._c;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"jmeter-快速入门"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#jmeter-快速入门"}},[t._v("#")]),t._v(" JMeter 快速入门")]),t._v(" "),e("blockquote",[e("p",[e("a",{attrs:{href:"https://github.com/apache/jmeter",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter"),e("OutboundLink")],1),t._v(" 是一款基于 Java 开发的功能和性能测试软件。")]),t._v(" "),e("p",[t._v("🎁 本文编辑时的最新版本为：5.1.1")])]),t._v(" "),e("h2",{attrs:{id:"简介"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#简介"}},[t._v("#")]),t._v(" 简介")]),t._v(" "),e("p",[e("a",{attrs:{href:"https://github.com/apache/jmeter",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter"),e("OutboundLink")],1),t._v(" 是一款使用 Java 开发的功能和性能测试软件。")]),t._v(" "),e("h3",{attrs:{id:"特性"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#特性"}},[t._v("#")]),t._v(" 特性")]),t._v(" "),e("p",[t._v("Jmeter 能够加载和性能测试许多不同的应用程序/服务器/协议类型：")]),t._v(" "),e("ul",[e("li",[t._v("网络 - HTTP，HTTPS(Java，NodeJS，PHP，ASP.NET 等)")]),t._v(" "),e("li",[t._v("SOAP / REST Web 服务")]),t._v(" "),e("li",[t._v("FTP 文件")]),t._v(" "),e("li",[t._v("通过 JDBC 的数据库")]),t._v(" "),e("li",[t._v("LDAP")]),t._v(" "),e("li",[t._v("通过 JMS 的面向消息的中间件(MOM)")]),t._v(" "),e("li",[t._v("邮件-SMTP(S)，POP3(S)和 IMAP(S)")]),t._v(" "),e("li",[t._v("本机命令或 Shell 脚本")]),t._v(" "),e("li",[t._v("TCP 协议")]),t._v(" "),e("li",[t._v("Java 对象")])]),t._v(" "),e("h3",{attrs:{id:"工作流"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#工作流"}},[t._v("#")]),t._v(" 工作流")]),t._v(" "),e("p",[t._v("Jmeter 的工作原理是仿真用户向服务器发送请求，并收集服务器应答信息并计算统计信息。")]),t._v(" "),e("p",[t._v("Jmeter 的工作流如下图所示：")]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/cs/java/javaweb/technology/test/jmeter-workflow.png",alt:"img"}})]),t._v(" "),e("h3",{attrs:{id:"主要元素"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#主要元素"}},[t._v("#")]),t._v(" 主要元素")]),t._v(" "),e("p",[t._v("Jmeter 的主要元素如下：")]),t._v(" "),e("ul",[e("li",[e("strong",[e("code",[t._v("测试计划(Test Plan)")])]),t._v(" - 可以将测试计划视为 JMeter 的测试脚本 。测试计划由测试元素组成，例如线程组，逻辑控制器，样本生成控制器，监听器，定时器，断言和配置元素。")]),t._v(" "),e("li",[e("strong",[e("code",[t._v("线程组(Thread Group)")])]),t._v(" - 线程组的作用是：模拟大量用户负载的运行场景。\n"),e("ul",[e("li",[t._v("设置线程数")]),t._v(" "),e("li",[t._v("设置加速期")]),t._v(" "),e("li",[t._v("设置执行测试的次数")])])]),t._v(" "),e("li",[e("strong",[e("code",[t._v("控制器(Controllers)")])]),t._v(" - 可以分为两大类：\n"),e("ul",[e("li",[e("strong",[e("code",[t._v("采样器（Sampler）")])]),t._v(" - 采样器的作用是模拟用户对目标服务器发送请求。 采样器是必须将组件添加到测试计划中的，因为它只能让 JMeter 知道需要将哪种类型的请求发送到服务器。 请求可以是 HTTP，HTTP(s)，FTP，TCP，SMTP，SOAP 等。")]),t._v(" "),e("li",[e("strong",[e("code",[t._v("逻辑控制器")])]),t._v(" - 逻辑控制器的作用是：控制多个请求发送的循环次数及顺序等。")])])]),t._v(" "),e("li",[e("strong",[e("code",[t._v("监听器(Listeners)")])]),t._v(" - 监听器的作用是：收集测试结果信息。如查看结果树、汇总报告等。")]),t._v(" "),e("li",[e("strong",[e("code",[t._v("计时器(Timers)")])]),t._v(" - 计时器的作用是：控制多个请求发送的时间频次。")]),t._v(" "),e("li",[e("strong",[e("code",[t._v("配置元素(Configuration Elements)")])]),t._v(" - 配置元素的工作与采样器的工作类似。但是，它不发送请求，而是提供预备的数据等，如 CSV、函数助手。")]),t._v(" "),e("li",[e("strong",[e("code",[t._v("预处理器元素(Pre-Processor Elements)")])]),t._v(" - 预处理器元素在采样器发出请求之前执行，如果预处理器附加到采样器元素，那么它将在该采样器元素运行之前执行。预处理器元素用于在运行之前准备环境及参数。")]),t._v(" "),e("li",[e("strong",[e("code",[t._v("后处理器元素(Post-Processor Elements)")])]),t._v(" - 后处理器元素是在发送采样器请求之后执行的元素，常用于处理响应数据。")])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/cs/java/javaweb/technology/test/jmeter-elements.png",alt:"img"}})]),t._v(" "),e("blockquote",[e("p",[t._v("📌 提示：")]),t._v(" "),e("p",[t._v("Jmeter 元素的数量关系大致如下：")]),t._v(" "),e("ol",[e("li",[t._v("脚本中最多只能有一个测试计划。")]),t._v(" "),e("li",[t._v("测试计划中至少要有一个线程组。")]),t._v(" "),e("li",[t._v("线程组中至少要有一个取样器。")]),t._v(" "),e("li",[t._v("线程组中至少要有一个监听器。")])])]),t._v(" "),e("h2",{attrs:{id:"安装"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#安装"}},[t._v("#")]),t._v(" 安装")]),t._v(" "),e("h3",{attrs:{id:"环境要求"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#环境要求"}},[t._v("#")]),t._v(" 环境要求")]),t._v(" "),e("ul",[e("li",[e("p",[t._v("必要的。Jmeter 基于 JDK8 开发，所以必须运行在 JDK8 环境。")]),t._v(" "),e("ul",[e("li",[t._v("JDK8")])])]),t._v(" "),e("li",[e("p",[t._v("可选的。有些 jar 包不是 Jmeter 提供的，如果需要相应的功能，需要自行下载并置于 "),e("code",[t._v("lib")]),t._v(" 目录。")]),t._v(" "),e("ul",[e("li",[t._v("JDBC")]),t._v(" "),e("li",[t._v("JMS")]),t._v(" "),e("li",[e("a",{attrs:{href:"http://www.bouncycastle.org/test_releases.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("Bouncy Castle"),e("OutboundLink")],1)])])])]),t._v(" "),e("h3",{attrs:{id:"下载"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#下载"}},[t._v("#")]),t._v(" 下载")]),t._v(" "),e("p",[t._v("进入 "),e("a",{attrs:{href:"https://jmeter.apache.org/download_jmeter.cgi",target:"_blank",rel:"noopener noreferrer"}},[e("strong",[t._v("Jmeter 官网下载地址")]),e("OutboundLink")],1),t._v(" 选择需要版本进行下载。")]),t._v(" "),e("h3",{attrs:{id:"启动"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#启动"}},[t._v("#")]),t._v(" 启动")]),t._v(" "),e("p",[t._v("解压 Jmeter 压缩包，进入 bin 目录")]),t._v(" "),e("p",[t._v("Unix 类系统运行 "),e("code",[t._v("jmeter")]),t._v(" ；Windows 系统运行 "),e("code",[t._v("jmeter.bat")])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024104517721.png",alt:"image-20191024104517721"}})]),t._v(" "),e("h2",{attrs:{id:"使用"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#使用"}},[t._v("#")]),t._v(" 使用")]),t._v(" "),e("h3",{attrs:{id:"创建测试计划"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#创建测试计划"}},[t._v("#")]),t._v(" 创建测试计划")]),t._v(" "),e("blockquote",[e("p",[t._v("🔔 注意：")]),t._v(" "),e("ul",[e("li",[e("p",[t._v("在运行整个测试计划之前，应保存测试计划。")])]),t._v(" "),e("li",[e("p",[t._v("JMeter 的测试计划以 "),e("code",[t._v(".jmx")]),t._v(" 扩展文件的形式保存。")])])])]),t._v(" "),e("h4",{attrs:{id:"创建线程组"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#创建线程组"}},[t._v("#")]),t._v(" 创建线程组")]),t._v(" "),e("ul",[e("li",[e("p",[t._v("在“测试计划”上右键 【添加】=>【线程（用户）】=>【线程组】。")])]),t._v(" "),e("li",[e("p",[t._v("设置线程数和循环次数")])])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024105545736.png",alt:"image-20191024105545736"}})]),t._v(" "),e("h4",{attrs:{id:"配置原件"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#配置原件"}},[t._v("#")]),t._v(" 配置原件")]),t._v(" "),e("ul",[e("li",[e("p",[t._v("在新建的线程组上右键 【添加】=>【配置元件】=>【HTTP 请求默认值】。")])]),t._v(" "),e("li",[e("p",[t._v("填写协议、服务器名称或 IP、端口号")])])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024110016264.png",alt:"image-20191024110016264"}})]),t._v(" "),e("h4",{attrs:{id:"构造-http-请求"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#构造-http-请求"}},[t._v("#")]),t._v(" 构造 HTTP 请求")]),t._v(" "),e("ul",[e("li",[e("p",[t._v("在“线程组”上右键 【添加-】=>【取样器】=>【HTTP 请求】。")])]),t._v(" "),e("li",[e("p",[t._v("填写协议、服务器名称或 IP、端口号（如果配置了 HTTP 请求默认值可以忽略）")])]),t._v(" "),e("li",[e("p",[t._v("填写方法、路径")])]),t._v(" "),e("li",[e("p",[t._v("填写参数、消息体数据、文件上传")])])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024110953063.png",alt:"image-20191024110953063"}})]),t._v(" "),e("h4",{attrs:{id:"添加-http-请求头"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#添加-http-请求头"}},[t._v("#")]),t._v(" 添加 HTTP 请求头")]),t._v(" "),e("ul",[e("li",[t._v("在“线程组”上右键 【添加】=>【配置元件】=>【HTTP 信息头管理器】")]),t._v(" "),e("li",[t._v("由于我的测试例中传输的数据为 json 形式，所以设置键值对 "),e("code",[t._v("Content-Type")]),t._v("："),e("code",[t._v("application/json")])])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024111825226.png",alt:"image-20191024111825226"}})]),t._v(" "),e("h4",{attrs:{id:"添加断言"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#添加断言"}},[t._v("#")]),t._v(" 添加断言")]),t._v(" "),e("ul",[e("li",[t._v("在“线程组”上右键 【添加】=>【断言】=>【 响应断言 】")]),t._v(" "),e("li",[t._v("在我的案例中，以 HTTP 应答状态码为 200 来判断请求是否成功")])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024112335130.png",alt:"image-20191024112335130"}})]),t._v(" "),e("h4",{attrs:{id:"添加察看结果树"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#添加察看结果树"}},[t._v("#")]),t._v(" 添加察看结果树")]),t._v(" "),e("ul",[e("li",[t._v("在“线程组”上右键 【添加】=>【监听器】=>【察看结果树】")]),t._v(" "),e("li",[t._v("直接点击运行，就可以查看测试结果")])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024113849270.png",alt:"image-20191024113849270"}})]),t._v(" "),e("h4",{attrs:{id:"添加汇总报告"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#添加汇总报告"}},[t._v("#")]),t._v(" 添加汇总报告")]),t._v(" "),e("ul",[e("li",[t._v("在“线程组”上右键 【添加】=>【监听器】=>【汇总报告】")]),t._v(" "),e("li",[t._v("直接点击运行，就可以查看测试结果")])]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024114016424.png",alt:"image-20191024114016424"}})]),t._v(" "),e("h4",{attrs:{id:"保存测试计划"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#保存测试计划"}},[t._v("#")]),t._v(" 保存测试计划")]),t._v(" "),e("p",[t._v("执行测试计划前，GUI 会提示先保存配置为 "),e("code",[t._v("jmx")]),t._v(" 文件。")]),t._v(" "),e("h3",{attrs:{id:"执行测试计划"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#执行测试计划"}},[t._v("#")]),t._v(" 执行测试计划")]),t._v(" "),e("p",[t._v("官方建议不要直接使用 GUI 来执行测试计划，这种模式指适用于创建测试计划和 debug。")]),t._v(" "),e("p",[t._v("执行测试计划应该使用命令行模式，语法形式如下：")]),t._v(" "),e("div",{staticClass:"language-bash extra-class"},[e("pre",{pre:!0,attrs:{class:"language-bash"}},[e("code",[t._v("jmeter "),e("span",{pre:!0,attrs:{class:"token parameter variable"}},[t._v("-n")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token parameter variable"}},[t._v("-t")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("jmx file"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token parameter variable"}},[t._v("-l")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("results file"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token parameter variable"}},[t._v("-e")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token parameter variable"}},[t._v("-o")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),t._v("Path to web report folder"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v("\n")])])]),e("p",[t._v("执行测试计划后，在 "),e("code",[t._v("-e -o")]),t._v(" 参数后指定的 web 报告目录下，可以找到测试报告内容。在浏览器中打开 "),e("code",[t._v("index.html")]),t._v(" 文件，可以看到如下报告：")]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/jmeter/image-20191024120233058.png",alt:"image-20191024120233058"}})]),t._v(" "),e("h2",{attrs:{id:"问题"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#问题"}},[t._v("#")]),t._v(" 问题")]),t._v(" "),e("h3",{attrs:{id:"如何读取本地-txt-csv-文件作为请求参数"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#如何读取本地-txt-csv-文件作为请求参数"}},[t._v("#")]),t._v(" 如何读取本地 txt/csv 文件作为请求参数")]),t._v(" "),e("p",[t._v("参考："),e("a",{attrs:{href:"https://www.jianshu.com/p/3b2d3b643415",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter 读取本地 txt/csv 文件作为请求参数，实现接口自动化"),e("OutboundLink")],1)]),t._v(" "),e("p",[t._v("（1）依次点击【添加】=>【配置元件】=>【CSV 数据文件设置】")]),t._v(" "),e("p",[t._v("配置如下所示：")]),t._v(" "),e("p",[e("img",{attrs:{src:"https://raw.githubusercontent.com/dunwu/images/master/snap/image-20191127175820747.png",alt:"image-20191127175820747"}})]),t._v(" "),e("p",[t._v("重要配置说明（其他配置根据实际情况填）：")]),t._v(" "),e("ul",[e("li",[t._v("文件名：输入需要导入的数据文件位置。")]),t._v(" "),e("li",[t._v("文件编码：设为 UTF-8，避免乱码。")]),t._v(" "),e("li",[t._v("变量名称：使用 "),e("code",[t._v(",")]),t._v(" 分隔输入变量列表。如截图中设置了两个变量 "),e("code",[t._v("a")]),t._v(" 和 "),e("code",[t._v("b")])])]),t._v(" "),e("p",[t._v("（2）在 HTTP 请求的消息体数据中配置参数")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v('[{"a":"${a}","b":"${b}"}]\n')])])]),e("h3",{attrs:{id:"如何有序发送数据"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#如何有序发送数据"}},[t._v("#")]),t._v(" 如何有序发送数据")]),t._v(" "),e("p",[t._v("依次点击【添加】=>【逻辑控制器】=>【事务控制器】")]),t._v(" "),e("h2",{attrs:{id:"参考资料"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#参考资料"}},[t._v("#")]),t._v(" 参考资料")]),t._v(" "),e("ul",[e("li",[e("a",{attrs:{href:"https://jmeter.apache.org/",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter 官网"),e("OutboundLink")],1)]),t._v(" "),e("li",[e("a",{attrs:{href:"https://github.com/apache/jmeter",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter Github"),e("OutboundLink")],1)]),t._v(" "),e("li",[e("a",{attrs:{href:"https://www.cnblogs.com/TankXiao/p/4045439.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter 性能测试入门"),e("OutboundLink")],1)]),t._v(" "),e("li",[e("a",{attrs:{href:"https://www.yiibai.com/jmeter",target:"_blank",rel:"noopener noreferrer"}},[t._v("易百教程 - Jmeter 教程"),e("OutboundLink")],1)]),t._v(" "),e("li",[e("a",{attrs:{href:"https://www.jianshu.com/p/3b2d3b643415",target:"_blank",rel:"noopener noreferrer"}},[t._v("Jmeter 读取本地 txt/csv 文件作为请求参数，实现接口自动化"),e("OutboundLink")],1)])])])}),[],!1,null,null,null);e.default=s.exports}}]);