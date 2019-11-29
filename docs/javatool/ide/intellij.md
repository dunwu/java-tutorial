# Intellij IDEA 应用指南

## 快捷键

### 核心快捷键

IntelliJ IDEA 作为一个以快捷键为中心的 IDE，为大多数操作建议了键盘快捷键。在这个主题中，您可以找到最不可缺少的列表，使 IntelliJ IDEA 轻松实现第一步。

核心快捷键表：

| 操作                                                                                                                                                                                       | 快捷键                             |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------- |
| [根据名称查找操作](https://www.jetbrains.com/help/idea/navigating-to-action.html)                                                                                                          | `Ctrl+Shift+A`                     |
| 显示可用 [意图操作](https://www.jetbrains.com/help/idea/intention-actions.html) 列表                                                                                                       | `Alt+Enter`                        |
| 切换视图 ([Project](https://www.jetbrains.com/help/idea/project-tool-window.html),[Structure](https://www.jetbrains.com/help/idea/structure-tool-window-file-structure-popup.html), etc.). | `Alt+F1`                           |
| [切换](https://www.jetbrains.com/help/idea/navigating-between-files-and-tool-windows.html)工具窗口和在编辑器中打开的文件                                                                   | `Ctrl+Tab`                         |
| 显示 [导航栏](https://www.jetbrains.com/help/idea/navigation-bar.html).                                                                                                                    | `Alt+Home`                         |
| [插入代码模板](https://www.jetbrains.com/help/idea/generating-code.html).                                                                                                                  | `Ctrl+J`                           |
| [在周围插入代码模板](https://www.jetbrains.com/help/idea/creating-code-constructs-using-surround-templates.html).                                                                          | `Ctrl+Alt+J`                       |
| [Edit an item from the Project or another tree view](https://www.jetbrains.com/help/idea/opening-and-reopening-files-in-the-editor.html).                                                  | `F4`                               |
| [注释](https://www.jetbrains.com/help/idea/commenting-and-uncommenting-blocks-of-code.html)                                                                                                | `Ctrl+/` `Ctrl+Shift+/`            |
| [根据名称查找类或文件](https://www.jetbrains.com/help/idea/navigating-to-class-file-or-symbol-by-name.html).                                                                               | `Ctrl+N` `Ctrl+Shift+N`            |
| [拷贝当前行或指定的行](https://www.jetbrains.com/help/idea/adding-deleting-and-moving-code-elements.html#duplicate).                                                                       | `Ctrl+D`                           |
| [增加或减少选中的表达式](https://www.jetbrains.com/help/idea/selecting-text-in-the-editor.html).                                                                                           | `Ctrl+W` and `Ctrl+Shift+W`        |
| [在当前文件查找或替换](https://www.jetbrains.com/help/idea/finding-and-replacing-text-in-file.html).                                                                                       | `Ctrl+F` `Ctrl+R`                  |
| [在项目中或指定的目录中查找或替换](https://www.jetbrains.com/help/idea/finding-and-replacing-text-in-project.html)                                                                         | `Ctrl+Shift+F` `Ctrl+Shift+R`      |
| [全局搜索](https://www.jetbrains.com/help/idea/searching-everywhere.htmls)                                                                                                                 | 双击 `Shift`                       |
| [快速查看选中对象的引用](https://www.jetbrains.com/help/idea/highlighting-usages.html).                                                                                                    | `Ctrl+Shift+F7`                    |
| [展开或折叠编辑器中的代码块](https://www.jetbrains.com/help/idea/code-folding.html).                                                                                                       | `Ctrl+NumPad Plus` `Ctrl+NumPad -` |
| [调用代码完成](https://www.jetbrains.com/help/idea/auto-completing-code.html#basic_completion).                                                                                            | `Ctrl+Space`                       |
| [智能声明完成](https://www.jetbrains.com/help/idea/auto-completing-code.html#statements_completion).                                                                                       | `Ctrl+Shift+Enter`                 |
| [智能补全代码](https://www.jetbrains.com/help/idea/auto-completing-code.html#smart_completion)                                                                                             | `Ctrl+Shift+Space`                 |
| 显示可用的[重构](https://www.jetbrains.com/help/idea/refactoring-source-code.html)方法列表                                                                                                 | `Ctrl+Shift+Alt+T`                 |

### 快捷键分类

#### Tradition

| 快捷键           | 介绍                            |
| ---------------- | ------------------------------- |
| Ctrl + Z         | 撤销                            |
| Ctrl + Shift + Z | 取消撤销                        |
| Ctrl + X         | 剪切                            |
| Ctrl + C         | 复制                            |
| Ctrl + S         | 保存                            |
| Tab              | 缩进                            |
| Shift + Tab      | 取消缩进                        |
| Shift + Home/End | 选中光标到当前行头位置/行尾位置 |
| Ctrl + Home/End  | 跳到文件头/文件尾               |

#### Editing

| 快捷键                  | 介绍                                                                                                     |
| ----------------------- | -------------------------------------------------------------------------------------------------------- |
| Ctrl + Space            | 基础代码补全，默认在 Windows 系统上被输入法占用，需要进行修改，建议修改为 Ctrl + 逗号`（必备）`          |
| Ctrl + Alt + Space      | 类名自动完成                                                                                             |
| Ctrl + Shift + Enter    | 自动结束代码，行末自动添加分号`（必备）`                                                                 |
| Ctrl + P                | 方法参数提示显示                                                                                         |
| Ctrl + Q                | 光标所在的变量/类名/方法名等上面（也可以在提示补充的时候按），显示文档内容                               |
| Shift + F1              | 如果有外部文档可以连接外部文档                                                                           |
| Ctrl + F1               | 在光标所在的错误代码处显示错误信息`（必备）`                                                             |
| Alt + Insert            | 代码自动生成，如生成对象的 set/get 方法，构造函数，toString() 等`（必备）`                               |
| Ctrl + O                | 选择可重写的方法                                                                                         |
| Ctrl + I                | 选择可继承的方法                                                                                         |
| Ctrl + Alt + T          | 对选中的代码弹出环绕选项弹出层`（必备）`                                                                 |
| Ctrl + /                | 注释光标所在行代码，会根据当前不同文件类型使用不同的注释符号`（必备）`                                   |
| Ctrl + Shift + /        | 代码块注释`（必备）`                                                                                     |
| Ctrl + W                | 递进式选择代码块。可选中光标所在的单词或段落，连续按会在原有选中的基础上再扩展选中范围`（必备）`         |
| Ctrl + Shift + W        | 递进式取消选择代码块。可选中光标所在的单词或段落，连续按会在原有选中的基础上再扩展取消选中范围`（必备）` |
| Alt + Q                 | 弹出一个提示，显示当前类的声明/上下文信息                                                                |
| Alt + Enter             | IntelliJ IDEA 根据光标所在问题，提供快速修复选择，光标放在的位置不同提示的结果也不同`（必备）`           |
| Ctrl + Alt + L          | 格式化代码，可以对当前文件和整个包目录使用`（必备）`                                                     |
| Ctrl + Alt + O          | 优化导入的类，可以对当前文件和整个包目录使用`（必备）`                                                   |
| Ctrl + Alt + I          | 光标所在行 或 选中部分进行自动代码缩进，有点类似格式化                                                   |
| Ctrl + Shift + C        | 复制当前文件磁盘路径到剪贴板`（必备）`                                                                   |
| Ctrl + Shift + V        | 弹出缓存的最近拷贝的内容管理器弹出层                                                                     |
| Ctrl + Alt + Shift + C  | 复制参考信息                                                                                             |
| Ctrl + Alt + Shift + V  | 无格式黏贴`（必备）`                                                                                     |
| Ctrl + D                | 复制光标所在行 或 复制选择内容，并把复制内容插入光标位置下面`（必备）`                                   |
| Ctrl + Y                | 删除光标所在行 或 删除选中的行`（必备）`                                                                 |
| Ctrl + Shift + J        | 自动将下一行合并到当前行末尾`（必备）`                                                                   |
| Shift + Enter           | 开始新一行。光标所在行下空出一行，光标定位到新行位置`（必备）`                                           |
| Ctrl + Shift + U        | 对选中的代码进行大/小写轮流转换`（必备）`                                                                |
| Ctrl + Shift + ]/[      | 选中从光标所在位置到它的底部/顶部的中括号位置`（必备）`                                                  |
| Ctrl + Delete           | 删除光标后面的单词或是中文句`（必备）`                                                                   |
| Ctrl + BackSpace        | 删除光标前面的单词或是中文句`（必备）`                                                                   |
| Ctrl + +/-              | 展开/折叠代码块                                                                                          |
| Ctrl + Shift + +/-      | 展开/折叠所有代码`（必备）`                                                                              |
| Ctrl + F4               | 关闭当前编辑文件                                                                                         |
| Ctrl + Shift + Up/Down  | 光标放在方法名上，将方法移动到上一个/下一个方法前面，调整方法排序`（必备）`                              |
| Alt + Shift + Up/Down   | 移动光标所在行向上移动/向下移动`（必备）`                                                                |
| Ctrl + Shift + 左键单击 | 把光标放在某个类变量上，按此快捷键可以直接定位到该类中`（必备）`                                         |
| Alt + Shift + 左键双击  | 选择被双击的单词/中文句，按住不放，可以同时选择其他单词/中文句`（必备）`                                 |
| Ctrl + Shift + T        | 对当前类生成单元测试类，如果已经存在的单元测试类则可以进行选择`（必备）`                                 |

#### Search/Replace

| 快捷键           | 介绍                                                                 |
| ---------------- | -------------------------------------------------------------------- |
| Double Shift     | 弹出 Search Everywhere 弹出层                                        |
| F3               | 在查找模式下，定位到下一个匹配处                                     |
| Shift + F3       | 在查找模式下，查找匹配上一个                                         |
| Ctrl + F         | 在当前文件进行文本查找`（必备）`                                     |
| Ctrl + R         | 在当前文件进行文本替换`（必备）`                                     |
| Ctrl + Shift + F | 根据输入内容查找整个项目 或 指定目录内文件`（必备）`                 |
| Ctrl + Shift + R | 根据输入内容替换对应内容，范围为整个项目 或 指定目录内文件`（必备）` |

#### Usage Search

| 快捷键            | 介绍                                                                 |
| ----------------- | -------------------------------------------------------------------- |
| Alt + F7          | 查找光标所在的方法/变量/类被调用的地方                               |
| Ctrl + Alt + F7   | 显示使用的地方。寻找被该类或是变量被调用的地方，用弹出框的方式找出来 |
| Ctrl + Shift + F7 | 高亮显示所有该选中文本，按 Esc 高亮消失`（必备）`                    |

#### Compile and Run

| 快捷键            | 介绍                     |
| ----------------- | ------------------------ |
| Ctrl + F9         | 执行 Make Project 操作   |
| Ctrl + Shift + F9 | 编译选中的文件/包/Module |
| Shift + F9        | Debug                    |
| Shift + F10       | Run                      |
| Alt + Shift + F9  | 弹出 Debug 的可选择菜单  |
| Alt + Shift + F10 | 弹出 Run 的可选择菜单    |

#### Debugging

| 快捷键            | 介绍                                                                                                                      |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------- |
| F7                | 在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则进入当前方法体内，如果该方法体还有方法，则不会进入该内嵌的方法中 |
| F8                | 在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则不进入当前方法体内                                               |
| Shift + F7        | 在 Debug 模式下，智能步入。断点所在行上有多个方法调用，会弹出进入哪个方法                                                 |
| Shift + F8        | 在 Debug 模式下，跳出，表现出来的效果跟 F9 一样                                                                           |
| Alt + F8          | 在 Debug 模式下，选中对象，弹出可输入计算表达式调试框，查看该输入内容的调试结果                                           |
| Alt + F9          | 在 Debug 模式下，执行到光标处                                                                                             |
| F9                | 在 Debug 模式下，恢复程序运行，但是如果该断点下面代码还有断点则停在下一个断点上                                           |
| Ctrl + F8         | 在 Debug 模式下，设置光标当前行为断点，如果当前已经是断点则去掉断点                                                       |
| Ctrl + Shift + F8 | 在 Debug 模式下，指定断点进入条件                                                                                         |

#### Navigation

| 快捷键                   | 介绍                                                                                                                    |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------- |
| Ctrl + N                 | 跳转到类`（必备）`                                                                                                      |
| Ctrl + Shift + N         | 跳转到文件`（必备）`                                                                                                    |
| Ctrl + Alt + Shift + N   | 跳转到符号`（必备）`                                                                                                    |
| Alt + Left/Right         | 切换当前已打开的窗口中的子视图，比如 Debug 窗口中有 Output、Debugger 等子视图，用此快捷键就可以在子视图中切换`（必备）` |
| F12                      | 回到前一个工具窗口`（必备）`                                                                                            |
| ESC                      | 从工具窗口进入代码文件窗口`（必备）`                                                                                    |
| Shift + ESC              | 隐藏当前 或 最后一个激活的工具窗口                                                                                      |
| Ctrl + G                 | 跳转到当前文件的指定行处                                                                                                |
| Ctrl + E                 | 显示最近打开的文件记录列表`（必备）`                                                                                    |
| Ctrl + Shift + E         | 显示最近编辑的文件记录列表`（必备）`                                                                                    |
| Ctrl + Alt + Left/Right  | 跳转到上一个/下一个操作的地方`（必备）`                                                                                 |
| Ctrl + Shift + Backspace | 退回到上次修改的地方`（必备）`                                                                                          |
| Alt + F1                 | 显示当前文件选择目标弹出层，弹出层中有很多目标可以进行选择`（必备）`                                                    |
| Ctrl + B/Ctrl + 左键单击 | 跳转到声明处                                                                                                            |
| Ctrl + Alt + B           | 在某个调用的方法名上使用会跳到具体的实现处，可以跳过接口                                                                |
| Ctrl + Shift + B         | 跳转到类型声明处`（必备）`                                                                                              |
| Ctrl + Shift + I         | 快速查看光标所在的方法 或 类的定义                                                                                      |
| Ctrl + U                 | 前往当前光标所在的方法的父类的方法/接口定义`（必备）`                                                                   |
| Alt + Up/Down            | 跳转到当前文件的前一个/后一个方法`（必备）`                                                                             |
| Ctrl + ]/[               | 跳转到当前所在代码的花括号结束位置/开始位置                                                                             |
| Ctrl + F12               | 弹出当前文件结构层，可以在弹出的层上直接输入，进行筛选                                                                  |
| Ctrl + H                 | 显示当前类的层次结构                                                                                                    |
| Ctrl + Shift + H         | 显示方法层次结构                                                                                                        |
| Ctrl + Alt + H           | 调用层次                                                                                                                |
| F2/Shift + F2            | 跳转到下一个/上一个高亮错误 或 警告位置`（必备）`                                                                       |
| F4                       | 编辑源`（必备）`                                                                                                        |
| Alt + Home               | 定位/显示到当前文件的 Navigation Bar                                                                                    |
| F11                      | 添加书签`（必备）`                                                                                                      |
| Ctrl + F11               | 选中文件/文件夹，使用助记符设定/取消书签`（必备）`                                                                      |
| Shift + F11              | 弹出书签显示层`（必备）`                                                                                                |
| Alt + 1,2,3...9          | 显示对应数值的选项卡，其中 1 是 Project 用得最多`（必备）`                                                              |
| Ctrl + 1,2,3...9         | 定位到对应数值的书签位置`（必备）`                                                                                      |

#### Refactoring

| 快捷键                 | 介绍                           |
| ---------------------- | ------------------------------ |
| Shift + F6             | 对文件/文件夹 重命名`（必备）` |
| Ctrl + Alt + Shift + T | 打开重构菜单`（必备）`         |

#### VCS/Local History

| 快捷键          | 介绍                                               |
| --------------- | -------------------------------------------------- |
| Ctrl + K        | 版本控制提交项目，需要此项目有加入到版本控制才可用 |
| Ctrl + T        | 版本控制更新项目，需要此项目有加入到版本控制才可用 |
| `Alt + |`       | 显示版本控制常用操作菜单弹出层`（必备）`           |
| Alt + Shift + C | 查看最近操作项目的变化情况列表                     |
| Alt + Shift + N | 选择/添加 task`（必备）`                           |

#### Live Templates

| 快捷键         | 介绍                                         |
| -------------- | -------------------------------------------- |
| Ctrl + J       | 插入自定义动态代码模板`（必备）`             |
| Ctrl + Alt + J | 弹出模板选择窗口，将选定的代码加入动态模板中 |

#### General

| 快捷键                 | 介绍                                                                  |
| ---------------------- | --------------------------------------------------------------------- |
| Ctrl + Tab             | 编辑窗口切换，如果在切换的过程又加按上 delete，则是关闭对应选中的窗口 |
| Ctrl + Alt + Y         | 同步、刷新                                                            |
| Ctrl + Alt + S         | 打开 IntelliJ IDEA 系统设置`（必备）`                                 |
| Ctrl + Alt + Shift + S | 打开当前项目设置`（必备）`                                            |
| Ctrl + Shift + A       | 查找动作/设置`（必备）`                                               |
| Ctrl + Shift + F12     | 编辑器最大化`（必备）`                                                |
| Alt + Shift + F        | 显示添加到收藏夹弹出层/添加到收藏夹                                   |
| Alt + Shift + I        | 查看项目当前文件                                                      |

### Intellij IDEA 官方快捷键表

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-6a44121ae280a10e.png"/></div>

## 插件

推荐几个比较好用的插件

- [Key promoter](https://plugins.jetbrains.com/plugin/4455?pr=idea) [快捷键提示](https://plugins.jetbrains.com/plugin/4455?pr=idea)
- [CamelCase](https://plugins.jetbrains.com/plugin/7160?pr=idea) 驼峰式命名和下划线命名交替变化
- [CheckStyle-IDEA](https://plugins.jetbrains.com/plugin/1065?pr=idea) 代码规范检查
- [FindBugs-IDEA](https://plugins.jetbrains.com/plugin/3847?pr=idea)潜在 Bug 检查
- [MetricsReloaded](https://plugins.jetbrains.com/plugin/93?pr=idea) 代码复杂度检查
- [Statistic](https://plugins.jetbrains.com/plugin/4509?pr=idea) 代码统计
- [JRebel Plugin](https://plugins.jetbrains.com/plugin/?id=4441) 热部署
- [GsonFormat](https://plugins.jetbrains.com/plugin/7654?pr=idea) 把 JSON 字符串直接实例化成类
- [Eclipse Code Formatter](https://plugins.jetbrains.com/plugin/6546-eclipse-code-formatter) 如果你以前用的是 IDE，并有自己的一套代码风格配置，可以通过此插件导入到 IDEA
- [Alibaba Java Coding Guidelines](https://plugins.jetbrains.com/plugin/10046-alibaba-java-coding-guidelines) 阿里 Java 开发规范的静态检查工具
- [IDE Features Trainer](https://plugins.jetbrains.com/plugin/8554-ide-features-trainer) 官方的新手训练插件
- [Markdown Navigator](https://plugins.jetbrains.com/plugin/7896-markdown-navigator) Markdown 插件，适用于喜欢用 markdown 写文档的人

## 个性化

### 颜色主题

[intellij-colors-solarized](https://github.com/jkaving/intellij-colors-solarized) 个人觉得这种色彩搭配十分优雅

[下载地址](https://github.com/altercation/solarized)

## 破解

Intellij 是一个收费的 IDE，坦白说有点小贵，买不起。

所以，很惭愧，只好用下破解方法了。网上有很多使用注册码的网文，但是注册码不稳定，随时可能被封。还是自行搭建一个注册服务器比较稳定。我使用了 [ilanyu](http://blog.lanyus.com/) 博文 [IntelliJ IDEA License Server 本地搭建教程](http://blog.lanyus.com/archives/174.html) 的方法，亲测十分有效。

我的备用地址：[百度云盘](https://yun.baidu.com/disk/home?#list/vmode=list&path=%2F%E8%BD%AF%E4%BB%B6%2F%E5%BC%80%E5%8F%91%E8%BD%AF%E4%BB%B6%2FIDE)

下载并解压文中的压缩包到本地，选择适合操作系统的版本运行。

如果是在 Linux 上运行，推荐创建一个脚本，代码如下：

```bash
# 使用 nohup 创建守护进程，运行 IntelliJIDEALicenseServer_linux_amd64
# 如果运行在其他 Linux 发行版本，替换执行的脚本即可
nohup sh IntelliJIDEALicenseServer_linux_amd64 2>&1
```

这样做是因为：大部分人使用 linux 是使用仿真器连接虚拟机，如果断开连接，进程也会被 kill，每次启动这个注册服务器很麻烦不是吗？而启动了守护进程，则不会出现这种情况，只有你主动 kill 进程才能将其干掉。

Windows 版本是 exe 程序，将其设为开机自动启动即可，别告诉我你不知道怎么设置开机自动启动。

## 参考资料

- [IntelliJ-IDEA-Tutorial](https://github.com/judasn/IntelliJ-IDEA-Tutorial)
- [极客学院 - Intellij IDEA 使用教程](http://wiki.jikexueyuan.com/project/intellij-idea-tutorial/)
