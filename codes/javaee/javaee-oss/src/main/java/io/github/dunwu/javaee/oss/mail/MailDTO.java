/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.mail;

import javax.mail.internet.MimeMultipart;

/**
 * @author Victor Zhang
 * @since 2016/12/22.
 */
public class MailDTO {

	private String from;

	private String to; // 邮件的收件人

	private String cc; // 邮件的抄送人

	private String bcc; // 邮件的密送人

	private String subject; // 邮件主题

	private String type; // text或html两种类型

	private String text; // 邮件文本内容

	private String charset; // 邮件编码类型（如UTF-8、GBK等）

	private MimeMultipart content;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public MimeMultipart getContent() {
		return content;
	}

	public void setContent(MimeMultipart content) {
		this.content = content;
	}

}
