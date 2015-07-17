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
	
	@FindBy(linkText="产品价格")
	public WebElement linCliTYProductPrice;//首页点击 “产品价格”
	
	@FindBy(linkText="我的服务")
	public WebElement linCliMyService;//点击 “我的服务”
	
	@FindBy(linkText="个人信息")
	public WebElement linCliPersonalInfo; //点击 “个人信息”
	
	@FindBy(linkText="我的订单")
	public WebElement linCliMyOrder; //点击 “我的订单”
	
	@FindBy(linkText="我的优惠券")
	public WebElement linCliMyCoupons; //点击 “我的优惠券”
	
	@FindBy(linkText="咨询与反馈")
	public WebElement linCliFeedback; //点击 “咨询与反馈”
	
	@FindBy(linkText="账号管理")
	public WebElement linCliAccountManager; //点击 “账号管理”
	
	@FindBy(xpath="//*[@id='centerleft']/div/div")
	public WebElement xpathBoolOpenIsSuccess; //判断开通是否成功
	
	/**
	 * 进入点击退出的iframe
	 */
	@FindBy(xpath="//div[@class='center_bj1']/descendant::iframe")
	public WebElement xpathIntoIframe; //进入iframe
	
	@FindBy(xpath="//a[contains(text(),'退出')]")
	public WebElement xpathCliLoginOut; //点击退出按钮
	
}
