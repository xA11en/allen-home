package com.tingyun.auto.report;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-7-21 下午4:51:14 
* @decription: report 报表首页登陆后公共page
 */
public class ReportCommonPage extends GlobalPage
{
	public ReportCommonPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 点击听云app
	 */
	@FindBy(xpath="//a[contains(text(),'进入控制台')]")
	public WebElement xpathCliLoginApp;
	
}
