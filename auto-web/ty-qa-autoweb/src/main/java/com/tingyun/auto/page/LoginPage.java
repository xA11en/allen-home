package com.tingyun.auto.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.TestNGException;

import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.DriverBrowser;

/**
* @author :chenjingli 
* @version ：2015-5-6 上午10:29:27 
* @decription: 登陆page
 */
public class LoginPage extends IndexPage{

	public LoginPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	/**
	 * 用户名
	 */
	@FindBy(id="username")
	private WebElement idTypeUserName;
	/**
	 * 密码
	 */
	@FindBy(id="password")
	private WebElement idTypePassword;
	/**
	 * 记住密码
	 */
	@FindBy(xpath="//input[@name='rememberMe']")
	private WebElement xpathCliRemberPwd;
	
	/**
	 * 点击登陆
	 */
	@FindBy(xpath="//a[contains(text(),'登')]")
	private WebElement xpathCliLogin;
	

	//************************************************** 对应用例*******************************
	
	/**
	 * 登录测试用例
	 */
	public void login()throws Exception{
		//输入用户名
		driverBrowser.sendKeys(idTypeUserName, SeleniumSettings.USERNAME);
		//输入密码
		driverBrowser.sendKeys(idTypePassword, SeleniumSettings.PASSWORD);
		//点击登陆按钮
		driverBrowser.click(xpathCliLogin);
		//判断登陆后元素是否存在
		int number = driverBrowser.getElementNums(xpathGetas);
		
		assertEquals(number, 3);
	}
	
}
