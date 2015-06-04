package com.tingyun.auto.rpc.step.report.singletask;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.page.report.singletask.GeneralizPerforPage;
/**
* @author :chenjingli 
* @version ：2015-6-4 下午4:17:00 
* @decription: 性能指标
 */
public class GeneralizPerforStep extends GlobalStep{
	public static final String DESHISTORYMAP = "性能指标-历史曲线图测试用例";
	public static final String rpcDesLogin = "rpc系统登录测试用例";
	
	@BeforeMethod(alwaysRun=true)
	public void testRpcLogin(){
		pinfo(ReportStep.class,rpcDesLogin+caseStart);
		try {
			driverBrowser.open(SeleniumSettings.URL);
			reportPage.RpcLogin();
			driverBrowser.pause(1000);
			genPage.clickGenPerfor();
		}catch(Error e){
			driverBrowser.failScreenShot("testRpcLogin");
			fail(rpcDesLogin+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testRpcLogin");
			throw new TestNGException(rpcDesLogin+FAIL + e.getMessage(), e);
		} 
	}
	
	//性能指标-历史曲线图测试用例
	@Test(description=DESHISTORYMAP)
	public void testHistoryMap(){
		pinfo(ReportStep.class,DESHISTORYMAP+caseStart);
		try {
			GeneralizPerforPage genPage = new GeneralizPerforPage(driverBrowser);
			genPage.historyCurveMap();
		pinfo(ReportStep.class,DESHISTORYMAP+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testHistoryMap");
			fail(DESHISTORYMAP+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testHistoryMap");
			throw new TestNGException(DESHISTORYMAP+"" + e.getMessage(), e);
		} 
	}
}
