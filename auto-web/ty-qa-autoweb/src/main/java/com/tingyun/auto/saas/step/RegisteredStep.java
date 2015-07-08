package com.tingyun.auto.saas.step;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.typroduct.RegisteredAndLoginPage;
import com.tingyun.auto.server.page.BackgroundTasksPage;
import com.tingyun.auto.utils.OperateProperties;
import com.tingyun.auto.utils.RedisManger;
/**
* @author :chenjingli 
* @version ：2015-6-30 下午2:28:05 
* @decription: saas 注册
 */
public class RegisteredStep extends GlobalStep {
	
	public static final String registe = "saas注册";
	private static DriverBrowser driverBrowser;
	private static RegisteredAndLoginPage registPage;
	
	@BeforeMethod
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
		try {
			pinfo(ReportStep.class,registe+caseStart);
			registPage.saasRegist();
			pinfo(ReportStep.class,registe+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testSaasRegiste");
			fail(registe+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testSaasRegiste");
			throw new TestNGException(registe+"" + e.getMessage(), e);
		} 
	}
	
	@AfterMethod
	public void down(){
		driverBrowser.quit();
	}
}
