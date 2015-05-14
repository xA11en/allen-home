package com.tingyun.auto.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现FTP 客户端的各种操作
 *
 *
 * @author
 * @version 2008-06-10 Ftp.java
 *
 */
public class FtpUtil {

	/**
	 * slf4j logback
	 */
	private final static Logger logger = LoggerFactory
.getLogger(FtpUtil.class);

	private static FTPClient ftp;
	private static FtpConfig ftpConfig;

	/**
	 * 
	 * @param path
	 *            上传到ftp服务器哪个路径下
	 * @param addr
	 *            地址
	 * @param port
	 *            端口号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	private static boolean connect(String path, String addr, int port,
			String username, String password, Date reportTime) throws Exception {
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(addr, port);
		result = ftp.login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}

		String env = ResourceBundle.getBundle("selenium").getString("env");
		// String env = "test";
		ftp.changeWorkingDirectory(path + env);
		
		String current = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(reportTime);
		ftp.makeDirectory(current);
		ftp.changeWorkingDirectory(current);
		result = true;
		return result;
	}

	/**
	 * 
	 * @param file
	 *            上传的文件或文件夹
	 * @throws Exception
	 */
	public static void upload(Date reportTime) throws Exception {
		ftpConfig = new FtpConfig();
		connect(ftpConfig.getFtpPath(), ftpConfig.getServer(),
				ftpConfig.getPort(), ftpConfig.getUsername(),
				ftpConfig.getPassword(), reportTime);
		upload(new File(System.getProperty("user.dir")
				+ ftpConfig.getLocalPath()));
		logger.info("测试报告上传服务器完成");
	}


	/**
	 * 
	 * @param file
	 *            上传的文件或文件夹
	 * @throws Exception
	 */
	private static void upload(File file) throws Exception {
		if (file.isDirectory()) {
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath() + "\\" + files[i]);
				if (file1.isDirectory()) {
					upload(file1);
					ftp.changeToParentDirectory();
				} else {
					File file2 = new File(file.getPath() + "\\" + files[i]);
					FileInputStream input = new FileInputStream(file2);
					ftp.storeFile(file2.getName(), input);
					input.close();
				}
			}
		} else {
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			ftp.storeFile(file2.getName(), input);
			input.close();
		}
	}
}