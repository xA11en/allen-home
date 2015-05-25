package com.tingyun.auto.step.rpc.report;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.Reporter;
import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.page.rpc.RpcLoginPage;
import com.tingyun.auto.page.rpc.report.ReportPage;
import com.tingyun.auto.step.GlobalStep;
import com.tingyun.auto.step.LoginStep;

/**
* @author :chenjingli 
* @version ：2015-5-19 下午5:19:19 
* @decription: rpc报表测试用例
 */
public class ReportStep extends GlobalStep{
	public static final String DESCHINAMAP = "性能概括-中国地图测试用例";
	private static DriverBrowser driverBrowser;
	
	@BeforeClass
	public void login() {
		try {
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		driverBrowser.open(SeleniumSettings.URL);
		RpcLoginPage reportPage = new RpcLoginPage(driverBrowser);
		reportPage.login();
		}catch(Error e){
			driverBrowser.failScreenShot("login");
			fail(e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("login");
			throw new TestNGException(DESCHINAMAP+"" + e.getMessage(), e);
		} 
	}
	@Test(description=DESCHINAMAP)
	public void testChinaMap(){
		pinfo(LoginStep.class,DESCHINAMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.ChinaMap();
		}catch(Error e){
			driverBrowser.failScreenShot("testChinaMap");
			fail(DESCHINAMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRpcLogin");
			throw new TestNGException(DESCHINAMAP+"" + e.getMessage(), e);
		} 
	}
	@AfterClass
	public void afterClass(){
		driverBrowser.quit();
	}
}
