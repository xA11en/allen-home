package com.tingyun.auto.server.step;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.TestNGException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.SeleniumSettings;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.rpc.page.RpcLoginPage;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.WebApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
public class WebApplicationStep extends GlobalStep{
	
	private static final String webapp = "最耗时Web应用过程(墙钟时间比)堆叠图--展现测试用例";
	private static final String timeAndThrougth = "响应时间和吞吐率--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static WebApplicationPage applicationPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Firefox);
		applicationPage = new WebApplicationPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/actions");
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 最耗时Web应用过程(墙钟时间比)堆叠图
	 */
	@Test(description=webapp)
	public void testWebApplicationProcess(){
		try {
			pinfo(ReportStep.class,webapp+caseStart);
			applicationPage.validationWebMap(webapp);
			pinfo(ReportStep.class,webapp+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testWebApplicationProcess");
			fail(webapp+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testWebApplicationProcess");
			throw new TestNGException(webapp+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription 响应时间和吞吐率
	 */
	@Test(description=timeAndThrougth)
	public void testTimeAndThroughput(){
		try {
			pinfo(ReportStep.class,timeAndThrougth+caseStart);
			applicationPage.valiTimeAndThroughput(timeAndThrougth);
			pinfo(ReportStep.class,timeAndThrougth+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testTimeAndThroughput");
			fail(timeAndThrougth+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testTimeAndThroughput");
			throw new TestNGException(timeAndThrougth+"" + e.getMessage(), e);
		} 
	}
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
