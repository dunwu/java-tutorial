package io.github.dunwu.javaee.oss.mail; /**
 * The Apache License 2.0 Copyright (c) 2016 Zhang Peng
 */

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Zhang Peng
 * @since 2017/4/5.
 */
public class SendAttachmentMail {

    private static final String MAIL_SERVER_HOST = "smtp.163.com";

    private static final String USER = "xxxxxx";

    private static final String PASSWORD = "******";

    private static final String MAIL_FROM = "xxxxxx@163.com";

    private static final String MAIL_TO = "xxxxxx@163.com";

    private static final String MAIL_CC = "xxxxxx@163.com";

    private static final String MAIL_BCC = "xxxxxx@163.com";

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
        String[] files = { "D:\\00_Temp\\temp\\1.jpg", "D:\\00_Temp\\temp\\2.png" };

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

}
