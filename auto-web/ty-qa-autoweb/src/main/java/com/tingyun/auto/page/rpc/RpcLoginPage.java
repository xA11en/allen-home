package com.tingyun.auto.page.rpc;

import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.page.LoginPage;
import static org.testng.Assert.*;
/**
* @author :chenjingli 
* @version ：2015-5-18 下午4:53:20 
* @decription:
 */
public class RpcLoginPage extends LoginPage {

	public RpcLoginPage(DriverBrowser driverBrowser) {
		super(driverBrowser);
	}
	/**
	* @author : chenjingli
	* @decription rpc登陆测试
	* @return
	 */
	public void RpcLogin()throws Exception{
		//输入用户名
		driverBrowser.sendKeys(idTypeUserName, SeleniumSettings.USERNAME);
		//输入密码
		driverBrowser.sendKeys(idTypePassword, SeleniumSettings.PASSWORD);
		//点击登陆按钮
		driverBrowser.click(xpathCliLogin);
		//点击记住密码
		driverBrowser.click(xpathCliRemberPwd);
		//点击登陆
		driverBrowser.click(xpathCliLogin);
		//判断登陆是否成功
		assertEquals(driverBrowser.getWebDriver().getTitle(),"听云");
	}
}
