package io.github.dunwu.javaee.oss.mail; /**
 * The Apache License 2.0 Copyright (c) 2016 Zhang Peng
 */

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Zhang Peng
 * @since 2017/4/5.
 */
public class ForwardMail {

	private static final String MAIL_SERVER_SMTP = "smtp.163.com";

	private static final String MAIL_SERVER_POP3 = "pop3.163.com";

	private static final String USER = "xxxxxx";

	private static final String PASSWORD = "******";

	private static final String MAIL_FROM = "xxxxxx@163.com";

	private static final String MAIL_TO = "xxxxxx@163.com";

	private static final String MAIL_CC = "xxxxxx@163.com";

	private static final String MAIL_BCC = "xxxxxx@163.com";

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

}
