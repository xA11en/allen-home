package com.tingyun.auto.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-5-12 上午11:46:23 
* @decription:
 */
public class IndexPage extends GlobalPage{
	
	
	public IndexPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	//点击听云app按钮
	@FindBy(xpath="//div[@class='content_try']/descendant::a[1]")
	public WebElement xpathCliApp;
	//点击听云server
	@FindBy(xpath="//div[@class='content_try']/descendant::a[2]")
	public WebElement xpathCliServer;
	//点击听云sys
	@FindBy(xpath="//div[@class='content_try']/descendant::a[3]")
	public WebElement xpathCliSys;
	//显示的元素个数
	@FindBy(xpath="//div[@class='content_try']/descendant::a")
	public List<WebElement> xpathGetas;
	
	
}
