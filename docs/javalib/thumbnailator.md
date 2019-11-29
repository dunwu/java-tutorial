# Thumbnailator 应用指南

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
- [核心 API](#核心-api)
  - [Thumbnails](#thumbnails)
  - [Thumbnails.Builder](#thumbnailsbuilder)
  - [工作流](#工作流)
- [实战](#实战)
  - [安装](#安装)
  - [图片缩放](#图片缩放)
  - [图片旋转](#图片旋转)
  - [加水印](#加水印)
  - [批量处理图片](#批量处理图片)
- [参考](#参考)

<!-- /TOC -->

## 简介

`Thumbnailator` 是一个开源的 **Java** 项目，它提供了非常简单的 API 来对图片进行缩放、旋转以及加水印的处理。

有多简单呢？简单到一行代码就可以完成图片处理。形式如下：

```java
Thumbnails.of(new File("path/to/directory").listFiles())
    .size(640, 480)
    .outputFormat("jpg")
    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
```

当然，Thumbnailator 还有一些使用细节，下面我会一一道来。

## 核心 API

### Thumbnails

`Thumbnails` 是使用 Thumbnailator 创建缩略图的主入口。

它提供了一组初始化 `Thumbnails.Builder` 的接口。

先看下这组接口的声明：

```java
// 可变长度参数列表
public static Builder<File> of(String... files) {...}
public static Builder<File> of(File... files) {...}
public static Builder<URL> of(URL... urls) {...}
public static Builder<? extends InputStream> of(InputStream... inputStreams) {...}
public static Builder<BufferedImage> of(BufferedImage... images) {...}
// 迭代器（所有实现 Iterable 接口的 Java 对象都可以，当然也包括 List、Set）
public static Builder<File> fromFilenames(Iterable<String> files) {...}
public static Builder<File> fromFiles(Iterable<File> files) {...}
public static Builder<URL> fromURLs(Iterable<URL> urls) {...}
public static Builder<InputStream> fromInputStreams(Iterable<? extends InputStream> inputStreams) {...}
public static Builder<BufferedImage> fromImages(Iterable<BufferedImage> images) {...}
```

很显然，**Thumbnails 允许通过传入文件名、文件、网络图的 URL、图片流、图片缓存多种方式来初始化构造器**。

因此，你可以根据实际需求来灵活的选择图片的输入方式。

需要注意一点：**如果输入是多个对象（无论你是直接输入容器对象或使用可变参数方式传入多个对象），则输出也必须选用输出多个对象的方式，否则会报异常。**

### Thumbnails.Builder

`Thumbnails.Builder` 是 `Thumbnails` 的内部静态类。它用于设置生成缩略图任务的相关参数。

**_注：`Thumbnails.Builder` 的构造函数是私有函数。所以，它只允许通过 `Thumbnails` 的实例化函数来进行初始化。_**

#### 设置参数的函数

`Thumbnails.Builder` 提供了一组函数链形式的接口来设置缩放图参数。

以设置大小函数为例：

```java
public Builder<T> size(int width, int height)
{
	updateStatus(Properties.SIZE, Status.ALREADY_SET);
	updateStatus(Properties.SCALE, Status.CANNOT_SET);

	validateDimensions(width, height);
	this.width = width;
	this.height = height;

	return this;
}
```

通过返回 this 指针，使得设置参数函数可以以链式调用的方式来使用，形式如下：

```java
Thumbnails.of(new File("original.jpg"))
        .size(160, 160)
        .rotate(90)
        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("watermark.png")), 0.5f)
        .outputQuality(0.8)
        .toFile(new File("image-with-watermark.jpg"));
```

好处，不言自明：那就是大大简化了代码。

#### 输出函数

`Thumbnails.Builder` 提供了一组重载函数来输出生成的缩放图。

函数声明如下：

```java
// 返回图片缓存
public List<BufferedImage> asBufferedImages() throws IOException {...}
public BufferedImage asBufferedImage() throws IOException {...}
// 返回文件列表
public List<File> asFiles(Iterable<File> iterable) throws IOException {...}
public List<File> asFiles(Rename rename) throws IOException {...}
public List<File> asFiles(File destinationDir, Rename rename) throws IOException {...}
// 创建文件
public void toFile(File outFile) throws IOException {...}
public void toFile(String outFilepath) throws IOException {...}
public void toFiles(Iterable<File> iterable) throws IOException {...}
public void toFiles(Rename rename) throws IOException {...}
public void toFiles(File destinationDir, Rename rename) throws IOException {...}
// 创建输出流
public void toOutputStream(OutputStream os) throws IOException {...}
public void toOutputStreams(Iterable<? extends OutputStream> iterable) throws IOException {...}
```

### 工作流

Thumbnailator 的工作步骤十分简单，可分为三步：

1. **输入**：`Thumbnails` 根据输入初始化构造器—— `Thumbnails.Builder` 。

2. **设置**：`Thumbnails.Builder` 设置缩放图片的参数。

3. **输出**：`Thumbnails.Builder` 输出图片文件或图片流。

> 更多详情可以参考： [<u>Thumbnailator 官网 javadoc</u>](https://coobird.github.io/thumbnailator/javadoc/0.4.8/)

## 实战

前文介绍了 Thumbnailator 的核心 API，接下来我们就可以通过实战来看看 Thumbnailator 究竟可以做些什么。

Thumbnailator 生成什么样的图片，是根据设置参数来决定的。

### 安装

maven 项目中引入依赖：

```xml
<dependency>
  <groupId>net.coobird</groupId>
  <artifactId>thumbnailator</artifactId>
  <version>[0.4, 0.5)</version>
</dependency>
```

### 图片缩放

`Thumbnails.Builder` 的 `size` 函数可以设置新图片精确的宽度和高度，也可以用 `scale` 函数设置缩放比例。

```java
Thumbnails.of("oldFile.png")
		.size(16, 16)
		.toFile("newFile_16_16.png");

Thumbnails.of("oldFile.png")
		.scale(2.0)
		.toFile("newFile_scale_2.0.png");

Thumbnails.of("oldFile.png")
		.scale(1.0, 0.5)
		.toFile("newFile_scale_1.0_0.5.png");
```

**oldFile.png**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-ba63439898602e8f.png"/></div>

**newFile_scale_1.0_0.5.png**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-a01ea4515fff865d.png"/></div>

### 图片旋转

`Thumbnails.Builder` 的 `size` 函数可以设置新图片的旋转角度。

```java
Thumbnails.of("oldFile.png")
		.scale(0.8)
		.rotate(90)
		.toFile("newFile_rotate_90.png");

Thumbnails.of("oldFile.png")
		.scale(0.8)
		.rotate(180)
		.toFile("newFile_rotate_180.png");
```

**newFile_rotate_90.png**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-17d54bc33b38d45b.png"/></div>

### 加水印

`Thumbnails.Builder` 的 `watermark` 函数可以为图片添加水印图片。第一个参数是水印的位置；第二个参数是水印图片的缓存数据；第三个参数是透明度。

```java
BufferedImage watermarkImage = ImageIO.read(new File("wartermarkFile.png"));
Thumbnails.of("oldFile.png")
		.scale(0.8)
		.watermark(Positions.BOTTOM_LEFT, watermarkImage, 0.5f)
		.toFile("newFile_watermark.png");
```

**wartermarkFile.png**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-97909ee6c066c195.png?imageMogr2/auto-orient/strip"/></div>

**newFile_watermark.png**

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-93eb7ef71b811a0c.png"/></div>

### 批量处理图片

下面以批量给图片加水印来展示一下如何处理多个图片文件。

```java
BufferedImage watermarkImage = ImageIO.read(new File("wartermarkFile.png"));

File destinationDir = new File("D:\\watermark\\");
Thumbnails.of("oldFile.png", "oldFile2.png")
		.scale(0.8)
		.watermark(Positions.BOTTOM_LEFT, watermarkImage, 0.5f)
		.toFiles(destinationDir, Rename.PREFIX_DOT_THUMBNAIL);
```

> **需要参考完整测试例代码请** [<u>**点击这里**</u>](https://github.com/dunwu/JavaParty/blob/master/toolbox/image/src/test/java/org/zp/image/ThumbnailatorTest.java)

## 参考

[Thumbnailator 官方示例文档](https://github.com/coobird/thumbnailator/wiki/Examples)
