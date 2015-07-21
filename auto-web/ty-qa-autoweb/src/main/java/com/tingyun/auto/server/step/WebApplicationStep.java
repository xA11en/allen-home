package com.tingyun.auto.server.step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.server.page.WebApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - web应用过程 step
 */
@Listeners({ TestResultListener.class })
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
			pinfo(ReportStep.class,webapp+caseStart);
			applicationPage.validationWebMap(webapp);
			pinfo(ReportStep.class,webapp+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription 响应时间和吞吐率
	 */
	@Test(description=timeAndThrougth)
	public void testTimeAndThroughput(){
			pinfo(ReportStep.class,timeAndThrougth+caseStart);
			applicationPage.valiTimeAndThroughput(timeAndThrougth);
			pinfo(ReportStep.class,timeAndThrougth+caseEnd);	
	}
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
