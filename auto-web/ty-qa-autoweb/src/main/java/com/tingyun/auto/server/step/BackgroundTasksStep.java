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
import com.tingyun.auto.server.page.BackgroundTasksPage;
import com.tingyun.auto.server.page.DataSourcePage;
import com.tingyun.auto.server.page.NOSQLPage;
import com.tingyun.auto.server.page.WebApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - 后台任务 step
 */
public class BackgroundTasksStep extends GlobalStep{
	
	private static final String mostTimeConsumingMap  = "最耗时后台任务图表--展现测试用例";
	private static final String webCpuMap = "应用CPU使用率图表--展现测试用例";
	private static final String webAppMemoryMap = "应用内存使用量图表--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static BackgroundTasksPage tasksPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		tasksPage = new BackgroundTasksPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/backgrounds");
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 最耗时后台任务图表
	 */
	@Test(description=mostTimeConsumingMap)
	public void testMostTimeConsumingMap(){
		try {
			pinfo(ReportStep.class,mostTimeConsumingMap+caseStart);
			tasksPage.validationTimeConsumingMap(mostTimeConsumingMap);
			pinfo(ReportStep.class,mostTimeConsumingMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testMostTimeConsumingMap");
			fail(mostTimeConsumingMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testMostTimeConsumingMap");
			throw new TestNGException(mostTimeConsumingMap+"" + e.getMessage(), e);
		} 
	}

	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	//@Test(description=webCpuMap)
	public void testWebCpuMap (){
		try {
			pinfo(ReportStep.class,webCpuMap+caseStart);
			tasksPage.valiAppMemoryMap(webCpuMap);
			pinfo(ReportStep.class,webCpuMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testWebCpuMap");
			fail(webCpuMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testWebCpuMap");
			throw new TestNGException(webCpuMap+"" + e.getMessage(), e);
		} 
	}
	/**
	* @author : chenjingli
	* @decription 数据库响应时间曲线图
	 */
	//@Test(description=webAppMemoryMap)
	public void testAppMemoryMap(){
		try {
			pinfo(ReportStep.class,webAppMemoryMap+caseStart);
			tasksPage.valiAppMemoryMap(webAppMemoryMap);
			pinfo(ReportStep.class,webAppMemoryMap+caseEnd);	
		}catch(Error e){
			driverBrowser.failScreenShot("testAppMemoryMap");
			fail(webAppMemoryMap+FAIL + e.getMessage(), e);
		}catch (Exception e) {
			driverBrowser.failScreenShot("testAppMemoryMap");
			throw new TestNGException(webAppMemoryMap+"" + e.getMessage(), e);
		} 
	}
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
