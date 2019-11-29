# JavaMail 应用指南

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
  - [邮件相关的标准](#邮件相关的标准)
  - [JavaMail 简介](#javamail-简介)
  - [邮件传输过程](#邮件传输过程)
  - [Message 结构](#message-结构)
- [JavaMail 的核心类](#javamail-的核心类)
  - [java.util.Properties 类（属性对象）](#javautilproperties-类属性对象)
  - [javax.mail.Session 类（会话对象）](#javaxmailsession-类会话对象)
  - [javax.mail.Transport 类（邮件传输）](#javaxmailtransport-类邮件传输)
  - [javax.mail.Store 类（邮件存储 ）](#javaxmailstore-类邮件存储-)
  - [javax.mail.Message 类（消息对象）](#javaxmailmessage-类消息对象)
  - [javax.mail.Address 类（地址）](#javaxmailaddress-类地址)
  - [Authenticator 类（认证者）](#authenticator-类认证者)
- [实例](#实例)
  - [发送文本邮件](#发送文本邮件)
  - [发送 HTML 格式的邮件](#发送-html-格式的邮件)
  - [发送带附件的邮件](#发送带附件的邮件)
  - [获取邮箱中的邮件](#获取邮箱中的邮件)
  - [转发邮件](#转发邮件)

<!-- /TOC -->

## 简介

### 邮件相关的标准

厂商所提供的 JavaMail 服务程序可以有选择地实现某些邮件协议，常见的邮件协议包括：

- `SMTP(Simple Mail Transfer Protocol)` ：即简单邮件传输协议，它是一组用于由源地址到目的地址传送邮件的规则，由它来控制信件的中转方式。
- `POP3(Post Office Protocol - Version 3)` ：即邮局协议版本 3 ，用于接收电子邮件的标准协议。
- `IMAP(Internet Mail Access Protocol)` ：即 Internet 邮件访问协议。是 POP3 的替代协议。

这三种协议都有对应 SSL 加密传输的协议，分别是 **SMTPS **， **POP3S **和 **IMAPS **。

`MIME(Multipurpose Internet Mail Extensions)` ：即多用途因特网邮件扩展标准。它不是邮件传输协议。但对传输内容的消息、附件及其它的内容定义了格式。

### JavaMail 简介

JavaMail 是由 Sun 发布的用来处理 email 的 API 。它并没有包含在 Java SE 中，而是作为 Java EE 的一部分。

- `mail.jar` ：此 JAR 文件包含 JavaMail API 和 Sun 提供的 SMTP 、 IMAP 和 POP3 服务提供程序；
- `activation.jar` ：此 JAR 文件包含 JAF API 和 Sun 的实现。

JavaMail 包中用于处理电子邮件的核心类是： `Properties` 、 `Session` 、 `Message` 、 `Address` 、 `Authenticator` 、 `Transport` 、 `Store` 等。

### 邮件传输过程

如上图，电子邮件的处理步骤如下：

1. 创建一个 Session 对象。
2. Session 对象创建一个 Transport 对象 /Store 对象，用来发送 / 保存邮件。
3. Transport 对象 /Store 对象连接邮件服务器。
4. Transport 对象 /Store 对象创建一个 Message 对象 ( 也就是邮件内容 ) 。
5. Transport 对象发送邮件； Store 对象获取邮箱的邮件。

### Message 结构

- `MimeMessage` 类：代表整封邮件。
- `MimeBodyPart` 类：代表邮件的一个 MIME 信息。
- `MimeMultipart` 类：代表一个由多个 MIME 信息组合成的组合 MIME 信息。

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-948230d2f5c7a620.png"/></div>

## JavaMail 的核心类

JavaMail 对收发邮件进行了高级的抽象，形成了一些关键的的接口和类，它们构成了程序的基础，下面我们分别来了解一下这些最常见的对象。

### java.util.Properties 类（属性对象）

java.util.Properties 类代表一组属性集合。

它的每一个键和值都是 String **类型。**

由于 JavaMail 需要和邮件服务器进行通信，这就要求程序提供许多诸如服务器地址、端口、用户名、密码等信息， JavaMail 通过 Properties 对象封装这些属性信息。

例： 如下面的代码封装了几个属性信息：

```java
Properties prop = new Properties();
prop.setProperty("mail.debug", "true");
prop.setProperty("mail.host", "[email protected]");
prop.setProperty("mail.transport.protocol", "smtp");
prop.setProperty("mail.smtp.auth", "true");
```

针对不同的的邮件协议， JavaMail 规定了服务提供者必须支持一系列属性，

下表是一些常见属性（属性值都以 String 类型进行设置，属性类型栏仅表示属性是如何被解析的）：

| 关键词                  | 类型    | 描述                                          |
| ----------------------- | ------- | --------------------------------------------- |
| mail.debug              | boolean | debug 开关。                                  |
| mail.host               | String  | 指定发送、接收邮件的默认邮箱服务器。          |
| mail.store.protocol     | String  | 指定接收邮件的协议。                          |
| mail.transport.protocol | String  | 指定发送邮件的协议。                          |
| mail.debug.auth         | boolean | debug 输出中是否包含认证命令。默认是 false 。 |

详情请参考官方 API 文档：

https://javamail.java.net/nonav/docs/api/ 。

### javax.mail.Session 类（会话对象）

`Session` 表示一个邮件会话。

Session 的主要作用包括两个方面：

- 接收各种配置属性信息：通过 Properties 对象设置的属性信息；
- 初始化 JavaMail 环境：根据 JavaMail 的配置文件，初始化 JavaMail 环境，以便通过 Session 对象创建其他重要类的实例。

JavaMail 在 Jar 包的 META-INF 目录下，通过以下文件提供了基本配置信息，以便 session 能够根据这个配置文件加载提供者的实现类：

- javamail.default.providers
- javamail.default.address.map

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-b59382c69385df45.png"/></div>

**例：**

```java
Properties props = new Properties();
props.setProperty("mail.transport.protocol", "smtp");
Session session = Session.getInstance(props);
```

### javax.mail.Transport 类（邮件传输）

邮件操作只有发送或接收两种处理方式。

JavaMail 将这两种不同操作描述为传输（ javax.mail.Transport ）和存储（ javax.mail.Store ），传输对应邮件的发送，而存储对应邮件的接收。

- `getTransport` - Session 类中的 getTransport **() **有多个重载方法，可以用来创建 Transport 对象。
- `connect` - 如果设置了认证命令—— mail.smtp.auth ，那么使用 Transport 类的 connect 方法连接服务器时，则必须加上用户名和密码。
- `sendMessage` - Transport 类的 sendMessage 方法用来发送邮件消息。
- `close` - Transport 类的 close 方法用来关闭和邮件服务器的连接。

### javax.mail.Store 类（邮件存储 ）

- `getStore` - Session 类中的 getStore () 有多个重载方法，可以用来创建 Store 对象。
- `connect` - 如果设置了认证命令—— mail.smtp.auth ，那么使用 Store 类的 connect 方法连接服务器时，则必须加上用户名和密码。
- `getFolder` - Store 类的 getFolder 方法可以 获取邮箱内的邮件夹 Folder 对象
- `close` - Store 类的 close 方法用来关闭和邮件服务器的连接。

### javax.mail.Message 类（消息对象）

- `javax.mail.Message` - 是个抽象类，只能用子类去实例化，多数情况下为 `javax.mail.internet.MimeMessage`。
- `MimeMessage` - 代表 MIME 类型的电子邮件消息。

要创建一个 Message ，需要将 Session 对象传递给 `MimeMessage` 构造器：

```java
MimeMessage message = new MimeMessage(session);
```

注意：还存在其它构造器，如用按 RFC822 格式的输入流来创建消息。

- setFrom - 设置邮件的发件人
- setRecipient - 设置邮件的发送人、抄送人、密送人

三种预定义的地址类型是：

- `Message.RecipientType.TO` - 收件人
- `Message.RecipientType.CC` - 抄送人
- `Message.RecipientType.BCC` - 密送人
- `setSubject` - 设置邮件的主题
- `setContent` - 设置邮件内容
- `setText` - 如果邮件内容是纯文本，可以使用此接口设置文本内容。

### javax.mail.Address 类（地址）

一旦您创建了 Session 和 Message ，并将内容填入消息后，就可以用 Address 确定信件地址了。和 Message 一样， Address 也是个抽象类。您用的是 javax.mail.internet.InternetAddress 类。

若创建的地址只包含电子邮件地址，只要传递电子邮件地址到构造器就行了。

**例：**

```java
Address address = new InternetAddress("[email protected]");
```

### Authenticator 类（认证者）

与 java.net 类一样， JavaMail API 也可以利用 `Authenticator` 通过用户名和密码访问受保护的资源。对于 JavaMail API 来说，这些资源就是邮件服务器。`Authenticator` 在 javax.mail 包中，而且它和 java.net 中同名的类 Authenticator 不同。两者并不共享同一个 Authenticator ，因为 JavaMail API 用于 Java 1.1 ，它没有 java.net 类别。

要使用 Authenticator ，先创建一个抽象类的子类，并从 `getPasswordAuthentication()` 方法中返回 `PasswordAuthentication` 实例。创建完成后，您必需向 session 注册 `Authenticator` 。然后，在需要认证的时候，就会通知 `Authenticator` 。您可以弹出窗口，也可以从配置文件中（虽然没有加密是不安全的）读取用户名和密码，将它们作为 `PasswordAuthentication` 对象返回给调用程序。

**例：**

```java
Properties props = new Properties();
Authenticator auth = new MyAuthenticator();
Session session = Session.getDefaultInstance(props, auth);
```

## 实例

### 发送文本邮件

```java
public static void main(String[] args) throws Exception {
    Properties prop = new Properties();
    prop.setProperty("mail.debug", "true");
    prop.setProperty("mail.host", MAIL_SERVER_HOST);
    prop.setProperty("mail.transport.protocol", "smtp");
    prop.setProperty("mail.smtp.auth", "true");

    // 1、创建session
    Session session = Session.getInstance(prop);
    Transport ts = null;

    // 2、通过session得到transport对象
    ts = session.getTransport();

    // 3、连上邮件服务器
    ts.connect(MAIL_SERVER_HOST, USER, PASSWORD);

    // 4、创建邮件
    MimeMessage message = new MimeMessage(session);

    // 邮件消息头
    message.setFrom(new InternetAddress(MAIL_FROM)); // 邮件的发件人
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(MAIL_TO)); // 邮件的收件人
    message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_CC)); // 邮件的抄送人
    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_BCC)); // 邮件的密送人
    message.setSubject("测试文本邮件"); // 邮件的标题

    // 邮件消息体
    message.setText("天下无双。");

    // 5、发送邮件
    ts.sendMessage(message, message.getAllRecipients());
    ts.close();
}
```

### 发送 HTML 格式的邮件

```java
public static void main(String[] args) throws Exception {
    Properties prop = new Properties();
    prop.setProperty("mail.debug", "true");
    prop.setProperty("mail.host", MAIL_SERVER_HOST);
    prop.setProperty("mail.transport.protocol", "smtp");
    prop.setProperty("mail.smtp.auth", "true");

    // 1、创建session
    Session session = Session.getInstance(prop);
    Transport ts = null;

    // 2、通过session得到transport对象
    ts = session.getTransport();

    // 3、连上邮件服务器
    ts.connect(MAIL_SERVER_HOST, USER, PASSWORD);

    // 4、创建邮件
    MimeMessage message = new MimeMessage(session);

    // 邮件消息头
    message.setFrom(new InternetAddress(MAIL_FROM)); // 邮件的发件人
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(MAIL_TO)); // 邮件的收件人
    message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_CC)); // 邮件的抄送人
    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_BCC)); // 邮件的密送人
    message.setSubject("测试HTML邮件"); // 邮件的标题

    String htmlContent = "<h1>Hello</h1>" + "<p>显示图片<img src='cid:abc.jpg'>1.jpg</p>";
    MimeBodyPart text = new MimeBodyPart();
    text.setContent(htmlContent, "text/html;charset=UTF-8");
    MimeBodyPart image = new MimeBodyPart();
    DataHandler dh = new DataHandler(new FileDataSource("D:\\05_Datas\\图库\\吉他少年背影.png"));
    image.setDataHandler(dh);
    image.setContentID("abc.jpg");

    // 描述数据关系
    MimeMultipart mm = new MimeMultipart();
    mm.addBodyPart(text);
    mm.addBodyPart(image);
    mm.setSubType("related");
    message.setContent(mm);
    message.saveChanges();

    // 5、发送邮件
    ts.sendMessage(message, message.getAllRecipients());
    ts.close();
}
```

### 发送带附件的邮件

```java
public static void main(String[] args) throws Exception {
    Properties prop = new Properties();
    prop.setProperty("mail.debug", "true");
    prop.setProperty("mail.host", MAIL_SERVER_HOST);
    prop.setProperty("mail.transport.protocol", "smtp");
    prop.setProperty("mail.smtp.auth", "true");

    // 1、创建session
    Session session = Session.getInstance(prop);

    // 2、通过session得到transport对象
    Transport ts = session.getTransport();

    // 3、连上邮件服务器
    ts.connect(MAIL_SERVER_HOST, USER, PASSWORD);

    // 4、创建邮件
    MimeMessage message = new MimeMessage(session);

    // 邮件消息头
    message.setFrom(new InternetAddress(MAIL_FROM)); // 邮件的发件人
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(MAIL_TO)); // 邮件的收件人
    message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_CC)); // 邮件的抄送人
    message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_BCC)); // 邮件的密送人
    message.setSubject("测试带附件邮件"); // 邮件的标题

    MimeBodyPart text = new MimeBodyPart();
    text.setContent("邮件中有两个附件。", "text/html;charset=UTF-8");

    // 描述数据关系
    MimeMultipart mm = new MimeMultipart();
    mm.setSubType("related");
    mm.addBodyPart(text);
    String[] files = {
            "D:\\00_Temp\\temp\\1.jpg", "D:\\00_Temp\\temp\\2.png"
    };

    // 添加邮件附件
    for (String filename : files) {
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(filename);
        mm.addBodyPart(attachPart);
    }

    message.setContent(mm);
    message.saveChanges();

    // 5、发送邮件
    ts.sendMessage(message, message.getAllRecipients());
    ts.close();
}
```

### 获取邮箱中的邮件

```java
 public static void main(String[] args) throws Exception {

    // 创建一个有具体连接信息的Properties对象
    Properties prop = new Properties();
    prop.setProperty("mail.debug", "true");
    prop.setProperty("mail.store.protocol", "pop3");
    prop.setProperty("mail.pop3.host", MAIL_SERVER_HOST);

    // 1、创建session
    Session session = Session.getInstance(prop);

    // 2、通过session得到Store对象
    Store store = session.getStore();

    // 3、连上邮件服务器
    store.connect(MAIL_SERVER_HOST, USER, PASSWORD);

    // 4、获得邮箱内的邮件夹
    Folder folder = store.getFolder("inbox");
    folder.open(Folder.READ_ONLY);

    // 获得邮件夹Folder内的所有邮件Message对象
    Message[] messages = folder.getMessages();
    for (int i = 0; i < messages.length; i++) {
        String subject = messages[i].getSubject();
        String from = (messages[i].getFrom()[0]).toString();
        System.out.println("第 " + (i + 1) + "封邮件的主题：" + subject);
        System.out.println("第 " + (i + 1) + "封邮件的发件人地址：" + from);
    }

    // 5、关闭
    folder.close(false);
    store.close();
}
```

### 转发邮件

例：获取指定邮件夹下的第一封邮件并转发

```java
 public static void main(String[] args) throws Exception {
    Properties prop = new Properties();
    prop.put("mail.store.protocol", "pop3");
    prop.put("mail.pop3.host", MAIL_SERVER_POP3);
    prop.put("mail.pop3.starttls.enable", "true");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.host", MAIL_SERVER_SMTP);

    // 1、创建session
    Session session = Session.getDefaultInstance(prop);

    // 2、读取邮件夹
    Store store = session.getStore("pop3");
    store.connect(MAIL_SERVER_POP3, USER, PASSWORD);
    Folder folder = store.getFolder("inbox");
    folder.open(Folder.READ_ONLY);

    // 获取邮件夹中第1封邮件信息
    Message[] messages = folder.getMessages();
    if (messages.length <= 0) {
        return;
    }
    Message message = messages[0];

    // 打印邮件关键信息
    String from = InternetAddress.toString(message.getFrom());
    if (from != null) {
        System.out.println("From: " + from);
    }

    String replyTo = InternetAddress.toString(message.getReplyTo());
    if (replyTo != null) {
        System.out.println("Reply-to: " + replyTo);
    }

    String to = InternetAddress.toString(message.getRecipients(Message.RecipientType.TO));
    if (to != null) {
        System.out.println("To: " + to);
    }

    String subject = message.getSubject();
    if (subject != null) {
        System.out.println("Subject: " + subject);
    }

    Date sent = message.getSentDate();
    if (sent != null) {
        System.out.println("Sent: " + sent);
    }

    // 设置转发邮件信息头
    Message forward = new MimeMessage(session);
    forward.setFrom(new InternetAddress(MAIL_FROM));
    forward.setRecipient(Message.RecipientType.TO, new InternetAddress(MAIL_TO));
    forward.setSubject("Fwd: " + message.getSubject());

    // 设置转发邮件内容
    MimeBodyPart bodyPart = new MimeBodyPart();
    bodyPart.setContent(message, "message/rfc822");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(bodyPart);
    forward.setContent(multipart);
    forward.saveChanges();

    Transport ts = session.getTransport("smtp");
    ts.connect(USER, PASSWORD);
    ts.sendMessage(forward, forward.getAllRecipients());

    folder.close(false);
    store.close();
    ts.close();
    System.out.println("message forwarded successfully....");
}
```
