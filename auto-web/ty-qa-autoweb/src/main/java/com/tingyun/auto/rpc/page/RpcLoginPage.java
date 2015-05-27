package com.tingyun.auto.rpc.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tingyun.auto.common.GlobalPage;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.DriverBrowser;
import static org.testng.Assert.*;
/**
* @author :chenjingli 
* @version ：2015-5-18 下午4:53:20 
* @decription:
 */
public class RpcLoginPage extends GlobalPage {

	public RpcLoginPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	/**
	* @author : chenjingli
	* @decription rpc登陆测试
	* @return
	 */
	/**
	 * 用户名
	 */
	@FindBy(id="username")
	public WebElement idTypeUserName;
	/**
	 * 密码
	 */
	@FindBy(id="password")
	public WebElement idTypePassword;
	/**
	 * 记住密码
	 */
	@FindBy(xpath="//input[@name='rememberMe']")
	public WebElement xpathCliRemberPwd;
	
	/**
	 * 点击登陆
	 */
	@FindBy(xpath="//a[contains(text(),'登')]")
	public WebElement xpathCliLogin;
	
	public void RpcLogin()throws Exception{
		//输入用户名
		driverBrowser.sendKeys(idTypeUserName, SeleniumSettings.USERNAME);
		//输入密码
		driverBrowser.sendKeys(idTypePassword, SeleniumSettings.PASSWORD);
		//点击记住密码
		driverBrowser.click(xpathCliRemberPwd);
		//点击登陆
		driverBrowser.click(xpathCliLogin);
		//判断登陆是否成功
		assertEquals(driverBrowser.getWebDriver().getTitle(),"听云");
	}
}
