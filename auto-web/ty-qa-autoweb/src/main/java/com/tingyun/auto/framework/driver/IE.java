package com.tingyun.auto.framework.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tingyun.auto.framework.SeleniumSettings;

/**
* @author :chenjingli 
* @version ：2015-5-11 下午3:27:29 
* @decription: chose ie driver
 */
public class IE extends Driver {

	private final static Logger logger = LoggerFactory
			.getLogger(IE.class);
	
	private static String classpath=ClassLoader.getSystemResource("").getPath();
	
	
	@Override
	public WebDriver getRemWebDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = this.ieCapabilities();
        webDriver = new RemoteWebDriver(new URL(SeleniumSettings.REMOTE_HTTP), capabilities);
        return webDriver;
	}

	@Override
	public WebDriver getwebDriver() {
		String browserPath = classpath
				+ SeleniumSettings.IE;
		try{
		File file = new File(browserPath);
		if (!file.exists()) {
			browserPath = new File(classpath).getParentFile().getPath()
					+ "\\classes\\"
					+ SeleniumSettings.IE;
		}
		DesiredCapabilities capabilities = this.ieCapabilities();
		System.setProperty("webdriver.ie.driver", browserPath);
		InternetExplorerDriverService service = InternetExplorerDriverService
				.createDefaultService();
	
		WebDriver driver = new InternetExplorerDriver(service, capabilities);
		//webDriver.manage().window().maximize();
		logger.info("启动IE浏览器   [{}]", browserPath);
		return driver;
		}catch(Exception e){
			logger.error("启动IE浏览器",e);
			}
		return null;
	}
	
	DesiredCapabilities ieCapabilities(){
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
				"about:blank");
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,
				true);
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,
				true);
		capabilities.setCapability(
				InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		return capabilities;
	}
}
