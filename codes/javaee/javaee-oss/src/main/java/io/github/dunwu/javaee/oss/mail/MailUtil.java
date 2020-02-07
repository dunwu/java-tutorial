package io.github.dunwu.javaee.oss.mail;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * @author Victor Zhang
 * @since 2016/12/22.
 */
public class MailUtil {

    private static final String TYPE_TEXT = "text";

    private static final String TYPE_HTML = "html";

    private MailConfigDTO configDTO;

    /**
     * 以默认配置初始化邮件收发工具
     */
    public MailUtil() {
        this.configDTO = initEmailConfig();
    }

    private MailConfigDTO initEmailConfig() {
        MailConfigDTO configDTO = new MailConfigDTO();
        Properties p = new Properties();
        try {
            p.load(MailUtil.class.getResourceAsStream("/mail/mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        configDTO.setSmtpHost(p.getProperty("smtp.host"));
        configDTO.setPop3Host(p.getProperty("pop3.host"));
        configDTO.setMailDomain(p.getProperty("mail.host"));
        configDTO.setMailAccount(p.getProperty("mail.account"));
        configDTO.setMailPassword(p.getProperty("mail.password"));
        configDTO.setMailFromHost(configDTO.getMailAccount() + configDTO.getMailDomain());
        return configDTO;
    }

    /**
     * 以自定义配置初始化邮件收发工具
     */
    public MailUtil(MailConfigDTO configDTO) {
        this.configDTO = configDTO;
    }

    /**
     * 发送邮件
     *
     * @param info
     * @throws MessagingException
     */
    public void sendEmail(MailDTO info) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", configDTO.getSmtpHost());
        props.setProperty("mail.smtp.auth", "true");

        // 1、创建session
        Session session = Session.getInstance(props);

        // 2、通过session得到transport对象
        Transport ts = session.getTransport();

        // 3、连上邮件服务器
        ts.connect(configDTO.getSmtpHost(), configDTO.getMailAccount(), configDTO.getMailPassword());

        // 4、创建邮件
        MimeMessage message = new MimeMessage(session);
        if (!fillEmail(message, info)) {
            return;
        }

        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    private boolean fillEmail(MimeMessage message, MailDTO info) throws MessagingException {
        return fillEmailHeader(message, info) && fillEmailBody(message, info);
    }

    /**
     * 填充邮件头
     */
    private boolean fillEmailHeader(MimeMessage message, MailDTO info) throws MessagingException {
        // 邮件的发件人
        if (StringUtils.isNotBlank(configDTO.getMailFromHost())) {
            message.setFrom(new InternetAddress(configDTO.getMailFromHost()));
        } else {
            System.out.println("发件人不能为空");
            return false;
        }

        // 邮件的收件人
        if (StringUtils.isNotBlank(info.getTo())) {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(info.getTo()));
        } else {
            System.out.println("收件人不能为空");
            return false;
        }

        // 邮件的抄送人
        if (StringUtils.isNotBlank(info.getCc())) {
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(info.getCc()));
        }

        // 邮件的密送人
        if (StringUtils.isNotBlank(info.getBcc())) {
            message.setRecipient(Message.RecipientType.BCC, new InternetAddress(info.getBcc()));
        }

        // 邮件的标题
        if (StringUtils.isNotBlank(info.getCharset())) {
            message.setSubject(info.getSubject(), info.getCharset());
        } else {
            message.setSubject(info.getSubject());
        }

        return true;
    }

    /**
     * 填充邮件内容
     */
    private boolean fillEmailBody(MimeMessage message, MailDTO info) throws MessagingException {
        if (StringUtils.isBlank(info.getType()) || StringUtils.isBlank(info.getText())) {
            return false;
        }

        if (StringUtils.equals(info.getType(), TYPE_TEXT)) {
            if (StringUtils.isNotBlank(info.getCharset())) {
                message.setText(info.getText(), info.getCharset());
            } else {
                message.setText(info.getText());
            }
        } else if (StringUtils.equals(info.getType(), TYPE_HTML)) {
            String type = "text/html";
            if (StringUtils.isNotBlank(info.getCharset())) {
                type += ";charset=" + info.getCharset();
            }
            message.setContent(info.getText(), type);
        }

        return true;
    }

    public List<MailDTO> receiveEmail() throws MessagingException, IOException {
        // 创建一个有具体连接信息的Properties对象
        Properties prop = new Properties();
        prop.setProperty("mail.debug", "false");
        prop.setProperty("mail.store.protocol", "pop3");
        prop.setProperty("mail.pop3.host", configDTO.getPop3Host());

        // 1、创建session
        Session session = Session.getInstance(prop);

        // 2、通过session得到Store对象
        Store store = session.getStore();

        // 3、连上邮件服务器
        store.connect(configDTO.getPop3Host(), configDTO.getMailAccount(), configDTO.getMailPassword());

        // 4、获得邮箱内的邮件夹
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);

        // 获得邮件夹Folder内的所有邮件Message对象
        Message[] messages = folder.getMessages();

        List<MailDTO> results = new ArrayList<MailDTO>();
        for (int i = 0; i < messages.length; i++) {
            MailDTO dto = new MailDTO();
            dto.setFrom(MimeUtility.decodeText(messages[i].getFrom()[0].toString()));
            dto.setSubject(messages[i].getSubject());
            dto.setText(messages[i].getContent().toString());
            results.add(dto);
            System.out.println("第 " + (i + 1) + "封邮件的主题：" + dto.getSubject());
            System.out.println("第 " + (i + 1) + "封邮件的发件人地址：" + dto.getFrom());
            // System.out.println("第 " + (i + 1) + "封邮件的内容：\n" +
            // messages[i].getContent().toString());
        }

        // 5、关闭
        folder.close(false);
        store.close();
        return results;
    }

}
