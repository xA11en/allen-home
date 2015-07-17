package com.tingyun.auto.saas.step.typroduct;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.saas.page.typroduct.OpenServicePage;
import com.tingyun.auto.server.page.BackgroundTasksPage;
import com.tingyun.auto.utils.OperateProperties;
import com.tingyun.auto.utils.RedisManger;
/**
* @author :chenjingli 
* @version ：2015-6-30 下午2:28:05 
* @decription: saas 注册
 */
//@Listeners({ TestResultListener.class })
public class OpenServiceStep extends GlobalStep {
	
	public static final String openServiceDes = "开通app,server,cdn,sys服务";
	private static OpenServicePage servicePage;
	private static RegisteredAndLoginPage loginPage;
	public static DriverBrowser driverBrowser;
	@BeforeClass
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		servicePage = new OpenServicePage(driverBrowser);
		loginPage = new RegisteredAndLoginPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasLoginURL"));
		loginPage.saasLogin();
		driverBrowser.pause(1000);
	}
	/**
	* @author : chenjingli
	* @decription 开通app,server,cdn,sys服务"
	 */
	@Test(description=openServiceDes)
	public void testOpenService(){
	try{
			pinfo(ReportStep.class,openServiceDes+caseStart);
			servicePage.openService();
			pinfo(ReportStep.class,openServiceDes+caseEnd);	
	}catch(Error e){
		driverBrowser.failScreenShot("testOpenService");
		fail(openServiceDes+FAIL + e.getMessage(), e);
	}catch (Exception e) {
		driverBrowser.failScreenShot("testOpenService");
		throw new TestNGException(openServiceDes+FAIL + e.getMessage(), e);
	} 
	}
	
	@AfterSuite
	public void down(){
		driverBrowser.quit();
	}
}
