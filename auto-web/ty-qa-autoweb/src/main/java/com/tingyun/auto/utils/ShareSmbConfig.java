/**
 * 
 */
package com.tingyun.auto.utils;

import java.util.ResourceBundle;

/**
 * @author jianping.gao
 *
 */
public class ShareSmbConfig {

	private static ResourceBundle bundle = null;
	private static final String SAVEPICFILENAMEPATH = "/"+"failScreenShot"; 
	
	static{
		bundle = ResourceBundle.getBundle("ftp");
	}
	
	public static String getSmbPath() {
		return bundle.getString("shareSmbPath");
	}
	public static String getLocalPath() {
		return bundle.getString("localPath");
	}
	public static String getSmbFailPicPath() {
		return bundle.getString("shareSmbPath")+SAVEPICFILENAMEPATH;
	}
	public static String getLocalFailPicPath() {
		return bundle.getString("localPath")+SAVEPICFILENAMEPATH;
	}
	public static String getSharePath() {
		return bundle.getString("sharePath");
	}
}
