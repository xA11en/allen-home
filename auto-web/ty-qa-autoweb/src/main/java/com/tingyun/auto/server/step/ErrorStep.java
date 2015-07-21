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
import com.tingyun.auto.server.page.ErrorPage;
/**
* @author :chenjingli 
* @version ：2015-6-11 上午11:50:36 
* @decription:  server - error step
 */
@Listeners({ TestResultListener.class })
public class ErrorStep extends GlobalStep{
	
	private static final String error = "应用错误率--展现测试用例";
	private static DriverBrowser driverBrowser;
	private static ErrorPage errorPage;
	
	@BeforeMethod
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		errorPage = new ErrorPage(driverBrowser);
		driverBrowser.open("http://demo.tingyun.com/application/27589/errors");
		driverBrowser.pause(1000);
	}
	
	/**
	* @author : chenjingli
	* @decription 应用错误率
	 */
	@Test(description=error)
	public void testValidationAppErrorMap(){
			pinfo(ReportStep.class,error+caseStart);
			errorPage.validationAppErrorMap(error);
			pinfo(ReportStep.class,error+caseEnd);	
	}

	@AfterMethod(alwaysRun=true)
	public void afterClass(){
		driverBrowser.quit();
	}
}
