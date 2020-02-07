/**
 * The Apache License 2.0 Copyright (c) 2016 Victor Zhang
 */
package io.github.dunwu.javaee.oss.mail;

/**
 * @author Victor Zhang
 * @since 2016/12/23.
 */
public class MailConfigDTO {

	private String smtpHost;

	private String pop3Host;

	private String mailDomain;

	private String mailAccount;

	private String mailPassword;

	private String mailFromHost;

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getPop3Host() {
		return pop3Host;
	}

	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}

	public String getMailDomain() {
		return mailDomain;
	}

	public void setMailDomain(String mailDomain) {
		this.mailDomain = mailDomain;
	}

	public String getMailAccount() {
		return mailAccount;
	}

	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailFromHost() {
		return mailFromHost;
	}

	public void setMailFromHost(String mailFromHost) {
		this.mailFromHost = mailFromHost;
	}

}
