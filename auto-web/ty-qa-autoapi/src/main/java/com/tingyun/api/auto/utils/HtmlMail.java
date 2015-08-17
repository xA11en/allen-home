/**
 * 
 */
package com.tingyun.api.auto.utils;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenjingli
 *
 */
public class HtmlMail {
	/**
	 * slf4j logback
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(HtmlMail.class);

	public static String hostName = "";
	public static String from = "";
	public static String pwd = "";
	private static ResourceBundle bundle = null;

	static {
		try {
			bundle = ResourceBundle.getBundle("ftp");
			if (bundle.getString("mail.smtp") != null
					&& !bundle.getString("mail.smtp").trim().equals("")) {
				hostName = bundle.getString("mail.smtp");
			}
			if (bundle.getString("mail.from") != null
					&& !bundle.getString("mail.from").trim().equals("")) {
				from = bundle.getString("mail.from");
			}

			if (bundle.getString("mail.pwd") != null
					&& !bundle.getString("mail.pwd").trim().equals("")) {
				pwd = bundle.getString("mail.pwd");
			}
		} catch (Exception e) {
			logger.error("获取邮件配置信息异常：{}",e);
			e.printStackTrace();
		}
	}

	/**
	 * 发送邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public static void send(String to, String subject, String content) {
		String[] tos = { to };
		send(tos, subject, content, null, null);
	}

	/**
	 * 群发邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public static void send(String[] to, String subject, String content) {
		send(to, subject, content, null, null);
	}

	/**
	 * 群发邮件
	 * 
	 * @param to
	 *            收件人邮箱集合
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param cc
	 *            抄送人
	 * @param bcc
	 *            秘密抄送人
	 */
	public static void send(final String[] to, final String subject,
			final String content, final List<String> cc, final List<String> bcc) {
		HtmlEmail email = new HtmlEmail();
		email.setCharset("UTF-8");
		try {

			// 设置邮件服务器
			email.setHostName(hostName);
			// 设置登录邮件服务器用户名和密码
			email.setAuthentication(from, pwd);
			// 设置发件人
			email.setFrom(from, "tingyun-QA");
			if (null != to && to.length > 0) {
				for (int i = 0; i < to.length; i++) {
					email.addTo(to[i]); // 接收方
				}
			}
			if (null != cc && cc.size() > 0) {
				for (int i = 0; i < cc.size(); i++) {
					email.addCc(cc.get(i)); // 抄送方
				}
			}
			if (null != bcc && bcc.size() > 0) {
				for (int i = 0; i < bcc.size(); i++) {
					email.addBcc(bcc.get(i)); // 秘密抄送方
				}
			}
			// 设置邮件标题
			email.setSubject(subject);
			// 设置邮件正文内容
			email.setHtmlMsg(content);
			email.send();
			logger.info("邮件发送完成");
		} catch (Exception e) {
			logger.error("邮件发送异常：{}",e);
			e.printStackTrace();
		}
	}
}
