package com.tingyun.auto.step;

import org.testng.TestNGException;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.tingyun.auto.page.LoginPage;


/**
* @author :chenjingli 
* @version ：2015-5-6 上午11:47:29 
* @decription:
 */
public class LoginStep extends GlobalStep {
	
	
	private static LoginPage loginPage;
	private static final String desLogin = "系统登录测试用例";
	
	/**
	 * 系统登录 
	 */
	@Test(description=desLogin)
	public void testLogin(){
			//初始化登陆pase
			loginPage = new LoginPage(driverBrowser);
			pinfo(LoginStep.class,desLogin+caseStart);
			//执行登陆用例
			try {
				loginPage.login();
				pinfo(LoginStep.class,desLogin+caseEnd);
			}catch(Error e){
				driverBrowser.failScreenShot("testLogin");
				fail(desLogin+FAIL + e.getMessage(), e);
			}catch (Exception e) {
				driverBrowser.failScreenShot("testLogin");
				throw new TestNGException(desLogin+FAIL + e.getMessage(), e);
			} 
			
	}
	

}
