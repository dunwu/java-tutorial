# ZXing 应用指南

<!-- TOC depthFrom:2 depthTo:3 -->

- [简介](#简介)
- [实战](#实战)
  - [安装](#安装)
  - [生成二维码图片](#生成二维码图片)
  - [解析二维码图片](#解析二维码图片)
- [参考](#参考)

<!-- /TOC -->

## 简介

`ZXing` 是一个开源 Java 类库用于解析多种格式的 1D/2D 条形码。目标是能够对 QR 编码、Data Matrix、UPC 的 1D 条形码进行解码。 其提供了多种平台下的客户端包括：J2ME、J2SE 和 Android。

官网：[<u>ZXing github 仓库</u>](https://github.com/zxing/zxing)

## 实战

**_本例演示如何在一个非 android 的 Java 项目中使用 ZXing 来生成、解析二维码图片。_**

### 安装

maven 项目只需引入依赖：

```xml
<dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>core</artifactId>
  <version>3.3.0</version>
</dependency>
<dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>javase</artifactId>
  <version>3.3.0</version>
</dependency>
```

如果非 maven 项目，就去官网下载发布版本：[<u>下载地址</u>](https://github.com/zxing/zxing/releases)

### 生成二维码图片

ZXing 生成二维码图片有以下步骤：

1. `com.google.zxing.MultiFormatWriter` 根据内容以及图像编码参数生成图像 2D 矩阵。
2. ​ `com.google.zxing.client.j2se.MatrixToImageWriter` 根据图像矩阵生成图片文件或图片缓存 `BufferedImage` 。

```java
public void encode(String content, String filepath) throws WriterException, IOException {
	int width = 100;
	int height = 100;
	Map<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>();
	encodeHints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, encodeHints);
	Path path = FileSystems.getDefault().getPath(filepath);
	MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
}
```

### 解析二维码图片

ZXing 解析二维码图片有以下步骤：

1. 使用 `javax.imageio.ImageIO` 读取图片文件，并存为一个 `java.awt.image.BufferedImage` 对象。

2. 将 `java.awt.image.BufferedImage` 转换为 ZXing 能识别的 `com.google.zxing.BinaryBitmap` 对象。

3. `com.google.zxing.MultiFormatReader` 根据图像解码参数来解析 `com.google.zxing.BinaryBitmap` 。

```java
public String decode(String filepath) throws IOException, NotFoundException {
	BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filepath));
	LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
	Binarizer binarizer = new HybridBinarizer(source);
	BinaryBitmap bitmap = new BinaryBitmap(binarizer);
	HashMap<DecodeHintType, Object> decodeHints = new HashMap<DecodeHintType, Object>();
	decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
	Result result = new MultiFormatReader().decode(bitmap, decodeHints);
	return result.getText();
}
```

完整参考示例：[<u>测试例代码</u>](https://github.com/dunwu/JavaParty/blob/master/toolbox/image/src/test/java/org/zp/image/QRCodeUtilTest.java)

以下是一个生成的二维码图片示例：

<div align="center"><img src="http://upload-images.jianshu.io/upload_images/3101171-26b73730088f0ab8.png"/></div>

## 参考

[ZXing github 仓库](https://github.com/zxing/zxing)
