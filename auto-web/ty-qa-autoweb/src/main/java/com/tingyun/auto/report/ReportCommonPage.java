package com.tingyun.auto.report;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-7-21 下午4:51:14 
* @decription: report 报表首页登陆后公共page
 */
public class ReportCommonPage extends GlobalPage
{
	static final String REGIST_EMAIL= "ZiregistEmail";
	public ReportCommonPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 点击听云app
	 */
	@FindBy(xpath="//a[contains(text(),'进入控制台')]")
	public WebElement xpathCliLoginApp;
	
	@FindBy(id="username")
	public WebElement idTypeUsername;
	
	@FindBy(id="password")
	public WebElement idTypePassword;
	
	@FindBy(xpath="//a[contains(text(),'登')]")
	public WebElement xpathCliLogin;
	
	@FindBy(linkText="自动化saas测试专用授权测试数据1勿删")
	public WebElement AssertText;
	
	/**
	 * 登录基调透镜系统
	 */
	public void JiDiaoTouJingLogin(){
		//输入用户名
		driverBrowser.sendKeys(idTypeUsername, getRadisKey2(REGIST_EMAIL));
		//输入密码
		driverBrowser.sendKeys(idTypePassword, readValue("saasLoginPwd"));
		//点击登录
		driverBrowser.click(xpathCliLogin);
		//点击进入控制台
		driverBrowser.click(xpathCliLoginApp);
		//验证权限是否分配成功
		Assert.assertEquals(AssertText.isDisplayed(), true);
	}
}
