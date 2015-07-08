package com.tingyun.auto.saas.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-6-30 上午10:59:32 
* @decription: SAAS 公共page
 */
public class CommonPage extends GlobalPage {

	public CommonPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	
	@FindBy(linkText="听云产品")
	public WebElement linCliTYProduct;//首页点击 “听云产品”
	
	@FindBy(linkText="立即开通")
	public WebElement linCliOpen; //cli立即开通
	
	
	
}
