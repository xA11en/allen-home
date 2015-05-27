package com.tingyun.auto.rpc.step.report;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.ReportPage;

/**
* @author :chenjingli 
* @version ：2015-5-19 下午5:19:19 
* @decription: rpc报表测试用例
 */
public class ReportStep extends GlobalStep {
	public static final String DESCHINAMAP = "性能概括-中国地图测试用例";
	public static final String DESWORDMAP = "性能概括-世界地图测试用例";
	public static final String rpcDesLogin = "rpc系统登录测试用例";
	private static DriverBrowser driverBrowser;
	
	@BeforeClass
	public void testRpcLogin(){
		pinfo(ReportStep.class,rpcDesLogin+caseStart);
		try {
			driverBrowser = new DriverBrowser(BrowserType.Chrome);
			driverBrowser.open(SeleniumSettings.URL);
			RpcLoginPage reportPage = new RpcLoginPage(driverBrowser);
			reportPage.RpcLogin();
		}catch(Error e){
			driverBrowser.failScreenShot("testRpcLogin");
			fail(rpcDesLogin+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRpcLogin");
			throw new TestNGException(rpcDesLogin+FAIL + e.getMessage(), e);
		} 
	}
	//单任务--中国地图
	//@Test(description=DESCHINAMAP)
	public void testChinaMap(){
		pinfo(ReportStep.class,DESCHINAMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.ChinaMap();
		pinfo(ReportStep.class,DESCHINAMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testChinaMap");
			fail(DESCHINAMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testChinaMap");
			throw new TestNGException(DESCHINAMAP+"" + e.getMessage(), e);
		} 
	}
	//单任务--世界地图
	@Test(description=DESWORDMAP)
	public void testWordMap(){
		pinfo(ReportStep.class,DESWORDMAP+caseStart);
		try {
			ReportPage reportPage = new ReportPage(driverBrowser);
			reportPage.wordMap();
		pinfo(ReportStep.class,DESWORDMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testWordMap");
			fail(DESWORDMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testWordMap");
			throw new TestNGException(DESWORDMAP+"" + e.getMessage(), e);
		} 
	}
	@AfterClass
	public void afterClass(){
		driverBrowser.quit();
	}
}
