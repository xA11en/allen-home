package com.tingyun.auto.server.step;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.ExternalApplicationPage;
import com.tingyun.auto.server.page.JVMPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - 外部应用 step
 */
public class ExternalApplicationStep extends GlobalStep{
	
	private static final String validationMostSlowWebAPPMap = "总耗时最慢的外部应用曲线图--展现测试用例";
	private static final String valiWebAppThroughtMap = "外部应用吞吐率曲线图--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static ExternalApplicationPage externalApplicationPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		externalApplicationPage = new ExternalApplicationPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/externals");
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 内存使用量
	 */
	@Test(description=validationMostSlowWebAPPMap)
	public void testValidationMostSlowWebAPPMap(){
		try {
			pinfo(ReportStep.class,validationMostSlowWebAPPMap+caseStart);
			externalApplicationPage.validationMostSlowWebAPPMap(validationMostSlowWebAPPMap);
			pinfo(ReportStep.class,validationMostSlowWebAPPMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testValidationMostSlowWebAPPMap");
			fail(validationMostSlowWebAPPMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testValidationMostSlowWebAPPMap");
			throw new TestNGException(validationMostSlowWebAPPMap+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription   代码缓存 
	 */
	@Test(description=valiWebAppThroughtMap)
	public void testValiWebAppThroughtMap(){
		try {
			pinfo(ReportStep.class,valiWebAppThroughtMap+caseStart);
			externalApplicationPage.valiWebAppThroughtMap(valiWebAppThroughtMap);
			pinfo(ReportStep.class,valiWebAppThroughtMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testValiWebAppThroughtMap");
			fail(valiWebAppThroughtMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testValiWebAppThroughtMap");
			throw new TestNGException(valiWebAppThroughtMap+"" + e.getMessage(), e);
		} 
	}

	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
