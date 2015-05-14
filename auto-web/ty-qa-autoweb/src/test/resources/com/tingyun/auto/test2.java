package com.tingyun.auto;

import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;

public class test2 {
	public static void main(String[] args) {
		DriverBrowser db = new DriverBrowser(BrowserType.Firefox);
		db.open("http://www.baidu.com");
		db.quit();
	}
}
