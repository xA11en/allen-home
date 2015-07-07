package com.tingyun.auto;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;



public class test {
//	private static WebDriver driverBrowser;
//	@BeforeClass
//	public void test(){
//		// TODO Auto-generated method stub
//		 DesiredCapabilities capability = DesiredCapabilities.chrome();
//	     capability.setBrowserName("chrome"); 
//	     try {
//			driverBrowser = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String seleniumServerPath = classpath+SeleniumSettings.SELENIUM_SERVER;
//		String nodeJson = classpath+SeleniumSettings.NODE_JSON;
//		String starChrome = classpath+SeleniumSettings.START_CHROME;
//		try {
//			Runtime.getRuntime().exec("cmd /c start "+starChrome+" "+nodeJson);
//		} catch (IOException e) {
//			e.printStackTrace();
//			logger.error("执行批出理文件异常{}",e);
//		}
	@Test
	public void t() throws MalformedURLException{
//				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//				capabilities
//				.setCapability(
//						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
//						true);
//		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
//				"about:blank");
//		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,
//				true);
//		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,
//				true);
//		capabilities.setCapability(
//				InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
//		capabilities.setBrowserName("internetExplorer"); 
//		WebDriver	 webDriver = new RemoteWebDriver(new URL(SeleniumSettings.REMOTE_HTTP), capabilities);
//		webDriver.get("http://www.baidu.com");
		//节点ip：节点端口号
		DriverBrowser driverBrowser = new DriverBrowser(BrowserType.Chrome);
		driverBrowser.open("http://www.baidu.com");
		driverBrowser.getWebDriver().findElement(By.id("kw")).sendKeys("test");
		driverBrowser.pause(2000);
		driverBrowser.getWebDriver().findElement(By.id("su")).click();
		driverBrowser.quit();
			}	
	}
