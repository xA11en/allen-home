/**
 * 
 */
package com.tingyun.alarm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public static void generateMailHtml(File file) {
		ResourceBundle bundle = ResourceBundle.getBundle("ftp");
		String to = bundle.getString("toMail");
		if (to == null) {
			return;
		}
		String[] toMail = bundle.getString("toMail").split(";");
		 StringBuffer sb = new StringBuffer();
		  try {
              String encoding="utf-8";
              if(file.isFile() && file.exists()){ //判断文件是否存在
                  InputStreamReader read = new InputStreamReader(
                  new FileInputStream(file),encoding);//考虑到编码格式
                  BufferedReader bufferedReader = new BufferedReader(read);
                  String lineTxt = null;
                  while((lineTxt = bufferedReader.readLine()) != null){
                	  sb.append(lineTxt);
                  }
                  read.close();
      }else{
          System.out.println("找不到指定的文件");
      }
      } catch (Exception e) {
          System.out.println("读取文件内容出错");
          e.printStackTrace();
      }
		
		StringBuilder html = new StringBuilder("<html>");
		html.append("<h1 style='text-align:center;color:blue'>测 试 报 告</h1>");
		html.append("<h2>"+sb.toString()+"</h2>").append("</html>");
		String subject = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒")
		.format(new Date())
		+ "  "
		+ "auto"
		+ "环境      "
		+ bundle.getString("subject");
		HtmlMail.send(toMail, subject, html.toString());
	}
}
