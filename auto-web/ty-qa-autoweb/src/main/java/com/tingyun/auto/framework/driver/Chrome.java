package com.tingyun.auto.framework.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.framework.SeleniumSettings;

/**
* @author :chenjingli 
* @version ：2015-5-11 下午3:26:33 
* @decription: start chrome driver
 */
public class Chrome extends Driver{
	
	private final static Logger logger = LoggerFactory
			.getLogger(Chrome.class);
	
	private static String classpath=ClassLoader.getSystemResource("").getPath();;	
	@Override
	public RemoteWebDriver getRemWebDriver() throws MalformedURLException  {
		// TODO Auto-generated method stub
		 DesiredCapabilities capability = DesiredCapabilities.chrome();
	     capability.setBrowserName("chrome"); 
	     capability.setVersion("38");
	     remWebDriver = new RemoteWebDriver(new URL(SeleniumSettings.REMOTE_HTTP), capability);
	     return remWebDriver;
	}

	@Override
	public WebDriver getwebDriver() {
		String browserPath = classpath
				+ SeleniumSettings.CHROME;
		try{
		File file = new File(browserPath);
		if (!file.exists()) {
			browserPath = new File(classpath).getParentFile().getPath()	
					+ "\\classes\\"
					+ SeleniumSettings.CHROME;
		}
		System.setProperty("webdriver.chrome.driver", browserPath);
		WebDriver driver = new ChromeDriver();
		logger.info("启动Chrome浏览器   [{}]", browserPath);
		return driver;
		}catch(Exception e){
			logger.error("启动Chrome浏览器报错",e);
		}
		return null;
	}

}
