/**
 * 
 */
package com.tingyun.auto.utils;

import java.util.ResourceBundle;

/**
 * @author jianping.gao
 *
 */
public class FtpConfig {

	private static ResourceBundle bundle = null;
	
	public FtpConfig(){
		bundle = ResourceBundle.getBundle("ftp");
	}

	public String getServer() {
		return bundle.getString("server");
	}

	public int getPort() {
		return Integer.parseInt(bundle.getString("port"));
	}

	public String getUsername() {
		return bundle.getString("username");
	}

	public String getPassword() {
		return bundle.getString("password");
	}

	public String getFtpPath() {
		return bundle.getString("ftpPath");
	}

	public String getLocalPath() {
		return bundle.getString("localPath");
	}
}
