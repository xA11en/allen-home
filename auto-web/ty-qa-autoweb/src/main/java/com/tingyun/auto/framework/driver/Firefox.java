package com.tingyun.auto.framework.driver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.framework.SeleniumSettings;

/**
* @author :chenjingli 
* @version ：2015-5-11 下午2:34:36 
* @decription: start firefox driver
 */
public class Firefox extends Driver{
	/**
	 * slf4j logback
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(Firefox.class);
	
	private FirefoxProfile firefoxProfile;
	private static String classpath=ClassLoader.getSystemResource("").getPath();	
	
	//远程浏览器拓展
	@Override
	public WebDriver getRemWebDriver() {
		logger.info("-------------------开始选择远程启动火狐浏览器--------------------");
		try { 
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setCapability(FirefoxDriver.PROFILE, this.firefox());
			//capability.setCapability(FirefoxDriver.BINARY, SeleniumSettings.FIREFOX);
		return new RemoteWebDriver(new URL(SeleniumSettings.REMOTE_HTTP), capability);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("启动火狐浏览器异常",e);
		} 
		return null;
	}
	
	/**
	 * Firefox browser instance
	 * @param browserPath
	 * @return
	 */
	@Override
	public WebDriver getwebDriver() {
		logger.info("-------------------开始选择本地启动火狐浏览器--------------------");
		try {
			classpath = new File(classpath).getParentFile().getPath()
						+ "\\classes\\";
			File pathToFirefoxBinary = new File(SeleniumSettings.FIREFOX);
			FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);
			//webDriver.manage().window().maximize();
			logger.info("启动本地Firefox浏览器   [{}]",
					SeleniumSettings.FIREFOX);
			return new FirefoxDriver(firefoxbin, this.firefox(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("启动Firefox浏览器",e);
		}
		return null;
	}
	
	
	private FirefoxProfile firefox()throws Exception{
		classpath = new File(classpath).getParentFile().getPath()
				+ "\\classes\\";
		firefoxProfile = new FirefoxProfile();
		firefoxProfile.addExtension(new File(classpath
				+ "browser\\firebug-1.9.0.xpi"));
		//firefoxProfile.addExtension(new File(classpath
			//	+ "browser\\firepath-0.9.7-fx.xpi"));
		
		firefoxProfile.setPreference("extensions.firebug.currentVersion",
				"1.9.2");
//		firefoxProfile.setPreference("extensions.firepath.currentVersion",
//				"0.9.7");
		firefoxProfile.setPreference("webdriver.accept.untrusted.certs",
				true);
		System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
				"com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
		return firefoxProfile;
	}
}
