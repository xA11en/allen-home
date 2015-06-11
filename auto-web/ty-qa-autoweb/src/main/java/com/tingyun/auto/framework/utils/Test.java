package com.tingyun.auto.framework.utils;

import java.io.IOException;


import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;

public class Test {
	@org.testng.annotations.Test
	public void startBrowser(){
		DriverBrowser db = new DriverBrowser(BrowserType.IE);
		db.open("http://www.baidu.com");
	}
	public static void main(String[] args) throws IOException {
		
//		File pathToFirefoxBinary = new File(SeleniumSettings.FIREFOX); 
//		FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);
//	      DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//	      capabilities.setPlatform(platform)
//	      capabilities.setCapability(capabilityName, value)
//	      capabilities.setCapability("firefox_profile", firefoxbin);
//	      WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//	      driver.get("http://www.baidu.com");
	}
}
