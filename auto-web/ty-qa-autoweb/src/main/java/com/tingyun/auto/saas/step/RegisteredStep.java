package com.tingyun.auto.saas.step;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.utils.OperateProperties;
/**
* @author :chenjingli 
* @version ：2015-6-30 下午2:28:05 
* @decription: saas 注册
 */
@Listeners({ TestResultListener.class})
public class RegisteredStep extends GlobalStep {
	
	public static final String registe = "saas注册";
	private static RegisteredAndLoginPage registPage;
	private static DriverBrowser driverBrowser;
	@BeforeClass
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		registPage = new RegisteredAndLoginPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasRegisterUrl"));
		driverBrowser.pause(1000);
	}
	/**
	* @author : chenjingli
	* @decription 最耗时后台任务图表
	 */
	@Test(description=registe)
	public void testSaasRegiste(){
//	try{
			pinfo(RegisteredStep.class,registe+caseStart);
			registPage.saasRegist();
			pinfo(RegisteredStep.class,registe+caseEnd);
//	}catch(Error e){
//		driverBrowser.failScreenShot("testSaasRegiste");
//		fail(registe+FAIL + e.getMessage(), e);
//	}catch (Exception e) {
//		driverBrowser.failScreenShot("testSaasRegiste");
//		throw new TestNGException(registe+FAIL + e.getMessage(), e);
//	} 
	}
	
	@AfterClass
	public void down(){
		driverBrowser.quit();
	}
}
