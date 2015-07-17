package com.tingyun.auto.saas.step.typroduct;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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
import com.tingyun.auto.saas.page.typroduct.ProductPricePage;
import com.tingyun.auto.server.page.BackgroundTasksPage;
import com.tingyun.auto.utils.OperateProperties;
import com.tingyun.auto.utils.RedisManger;
/**
* @author :chenjingli 
* @version ：2015-6-30 下午2:28:05 
* @decription: saas 注册
 */
//@Listeners({ TestResultListener.class })
public class ProductPriceStep extends GlobalStep {
	
	public static final String buyTingYunAppService = "购买听云app服务小微版测试用例";
	public static final String appRenewal = "续费听云app服务测试用例";
	private static ProductPricePage pricePage;
	private static DriverBrowser driverBrowser;
	private static RegisteredAndLoginPage loginPage;
	
	@BeforeClass
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		pricePage = new ProductPricePage(driverBrowser);
		loginPage = new RegisteredAndLoginPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasLoginURL"));
		loginPage.saasLogin();
		driverBrowser.getWebDriver().findElement(By.linkText("产品价格")).click();
		driverBrowser.pause(1000);
	}
	/**
	* @author : chenjingli
	* @decription 购买听云app服务小微版测试用例"
	 */
	@Test(description=buyTingYunAppService)
	public void testBuyTingYunAppService(){
	try{
			pinfo(ReportStep.class,buyTingYunAppService+caseStart);
			pricePage.appBuyXiaoWeiService();
			pinfo(ReportStep.class,buyTingYunAppService+caseEnd);	
	}catch(Error e){
		driverBrowser.failScreenShot("testBuyTingYunAppService");
		fail(buyTingYunAppService+FAIL + e.getMessage(), e);
	}catch (Exception e) {
		driverBrowser.failScreenShot("testBuyTingYunAppService");
		throw new TestNGException(buyTingYunAppService+FAIL + e.getMessage(), e);
	} 
	}
	
	/**
	* @author : chenjingli
	* @decription 续费听云app服务小微版"
	 */
	@Test(description=appRenewal)
	public void testAppRenewalService(){
	try{
			driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/userService/myproduct");
			pinfo(ReportStep.class,appRenewal+caseStart);
			pricePage.appRenewal();
			pinfo(ReportStep.class,appRenewal+caseEnd);
	}catch(Error e){
		driverBrowser.failScreenShot("testAppRenewalService");
		fail(appRenewal+FAIL + e.getMessage(), e);
	}catch (Exception e) {
		driverBrowser.failScreenShot("testAppRenewalService");
		throw new TestNGException(appRenewal+"" + e.getMessage(), e);
	} 
	}
	
	@AfterClass
	public void down(){
		driverBrowser.quit();
	}
}
