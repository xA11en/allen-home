package com.tingyun.auto.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author :chenjingli 
* @version ：2015-5-11 上午11:26:01 
* @decription:
 */
public class SeleniumSettings {
	/**
	 * 初始化selenium.pro文件内容
	 */
	public static Logger log = LoggerFactory.getLogger(SeleniumSettings.class);
	
	public static Properties prop;
	
	public static FileInputStream file;
	
	public static String arch;
	
	static{
		   prop = new Properties();
		   arch = System.getProperties().getProperty("os.arch");
	        try {
	            file = new FileInputStream("src/main/resources/selenium.properties");
	            prop.load(file);
	        } catch (Exception e) {
	        	log.error("初始化selenium.pro 文件错误",e);
	        }finally{
	        	if(file!=null){
	        		try {
						file.close();
					} catch (IOException e) {
			        	log.error("IO关闭异常",e);
					}
	        	}
	        }
	    }
	
	//火狐
	public static String FIREFOX= prop.getProperty("selenium.browser.firefox"); 
	//ie
	public static String IE = getIe();

	//谷歌
	public static String CHROME = prop.getProperty("selenium.browser.chrome");
	
	//# UI actions' timeout in millisecond
	public static int TIMEOUT =  Integer.parseInt(prop.getProperty("timeout"));
	
	//system url
	public static String URL = prop.getProperty("url");
	
	//username
	public static String USERNAME = prop.getProperty("username");
	
	//password
	public static String PASSWORD = prop.getProperty("password");
	
	//caste retry numbvers
	public static String CASE_RETRY_NUM = prop.getProperty("m_maxRetries");
	
	//Remote Driver Config  true or false
	public static boolean IS_REMOTE = prop.getProperty("isRemote").equals("true") ? Boolean.TRUE:Boolean.FALSE;
	
	//页面短暂停时间
	public static int    STEP_INTERVAL = Integer.parseInt(prop.getProperty("stepInterval"));
	
	//pageFactory 初始化默认时间
	public static int PAGE_FACTORY_TIME_OUT = Integer.parseInt(prop.getProperty("pageFactoryTimeOut"));
	
	//远程ip地址
	public static String     REMOTE_HTTP        = prop.getProperty("remoteIP");
	/**
	* @author : chenjingli
	* @decription 获得ie驱动类型
	* @return
	 */
	public static String getIe(){
		if(arch.contains("64"))
		return prop.getProperty("selenium.browser.ie.64");
	else
		return prop.getProperty("selenium.browser.ie.32");
	}
}

	
