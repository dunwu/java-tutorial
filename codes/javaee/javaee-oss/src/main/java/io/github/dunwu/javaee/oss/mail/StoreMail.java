package io.github.dunwu.javaee.oss.mail; /**
 * The Apache License 2.0 Copyright (c) 2016 Zhang Peng
 */

import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author Zhang Peng
 * @since 2017/4/5.
 */
public class StoreMail {

	private static final String MAIL_SERVER_HOST = "pop3.163.com";

	private static final String USER = "xxxxxx";

	private static final String PASSWORD = "******";

	private static final String MAIL_FROM = "xxxxxx@163.com";

	private static final String MAIL_TO = "xxxxxx@163.com";

	private static final String MAIL_CC = "xxxxxx@163.com";

	private static final String MAIL_BCC = "xxxxxx@163.com";

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

}
