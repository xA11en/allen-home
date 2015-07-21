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
import com.tingyun.auto.server.page.ExternalApplicationPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - 外部应用 step
 */
@Listeners({ TestResultListener.class })
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
			pinfo(ReportStep.class,validationMostSlowWebAPPMap+caseStart);
			externalApplicationPage.validationMostSlowWebAPPMap(validationMostSlowWebAPPMap);
			pinfo(ReportStep.class,validationMostSlowWebAPPMap+caseEnd);	
	}

	/**
	* @author : chenjingli
	* @decription   代码缓存 
	 */
	@Test(description=valiWebAppThroughtMap)
	public void testValiWebAppThroughtMap(){
			pinfo(ReportStep.class,valiWebAppThroughtMap+caseStart);
			externalApplicationPage.valiWebAppThroughtMap(valiWebAppThroughtMap);
			pinfo(ReportStep.class,valiWebAppThroughtMap+caseEnd);	
	}

	
	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
