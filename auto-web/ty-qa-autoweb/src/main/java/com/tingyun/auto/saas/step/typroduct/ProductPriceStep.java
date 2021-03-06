package com.tingyun.auto.saas.step.typroduct;


import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.saas.page.typroduct.ProductPricePage;
import com.tingyun.auto.utils.OperateProperties;
/**
* @author :chenjingli 
* @version ：2015-6-30 下午2:28:05 
* @decription: saas 注册
 */
@Listeners({ TestResultListener.class })
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
		DriverBrowser.getWebDriver().findElement(By.linkText("产品价格")).click();
		driverBrowser.pause(1000);
	}
	/**
	* @author : chenjingli
	* @decription 购买听云app服务小微版测试用例"
	 */
	@Test(description=buyTingYunAppService)
	public void testBuyTingYunAppService(){
	//try{
			pinfo(ProductPriceStep.class,buyTingYunAppService+caseStart);
			pricePage.appBuyXiaoWeiService();
			pinfo(ProductPriceStep.class,buyTingYunAppService+caseEnd);	
//	}catch(Error e){
//		driverBrowser.failScreenShot("testBuyTingYunAppService");
//		fail(buyTingYunAppService+FAIL + e.getMessage(), e);
//	}catch (Exception e) {
//		driverBrowser.failScreenShot("testBuyTingYunAppService");
//		throw new TestNGException(buyTingYunAppService+FAIL + e.getMessage(), e);
//	} 
	}
	
	/**
	* @author : chenjingli
	* @decription 续费听云app服务小微版"
	 */
	@Test(description=appRenewal)
	public void testAppRenewalService(){
//	try{
			driverBrowser.open("http://saas.networkbench.com:8080/lens-saas/userService/myproduct");
			pinfo(ProductPriceStep.class,appRenewal+caseStart);
			pricePage.appRenewal();
			pinfo(ProductPriceStep.class,appRenewal+caseEnd);
//	}catch(Error e){
//		driverBrowser.failScreenShot("testAppRenewalService");
//		fail(appRenewal+FAIL + e.getMessage(), e);
//	}catch (Exception e) {
//		driverBrowser.failScreenShot("testAppRenewalService");
//		throw new TestNGException(appRenewal+"" + e.getMessage(), e);
//	} 
	}
	
	@AfterClass
	public void down(){
		driverBrowser.quit();
	}
}
