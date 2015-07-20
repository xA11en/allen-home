package com.tingyun.auto.saas.step.typroduct;

import static org.testng.Assert.fail;

import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.tingyun.auto.common.GlobalStep;
import com.tingyun.auto.framework.browser.BrowserType;
import com.tingyun.auto.framework.browser.DriverBrowser;
import com.tingyun.auto.reporter.TestResultListener;
import com.tingyun.auto.rpc.step.report.singletask.ReportStep;
import com.tingyun.auto.saas.page.RegisteredAndLoginPage;
import com.tingyun.auto.saas.page.typroduct.ConsultationAndFeedbackPage;
import com.tingyun.auto.utils.OperateProperties;
/**
* @author :mabingxie 
* @version :2015-7-16 上午10:05:37 
* @decription: Consultation And Feedback  page
 */
@Listeners({ TestResultListener.class })
public class ConsultationAndFeedbackStep extends GlobalStep{
	public static final String ConsuAndFeedB = "saas咨询与反馈";
	private static DriverBrowser driverBrowser;
	private static ConsultationAndFeedbackPage ConFeedPage;
	
	@BeforeClass
	public void init(){
		driverBrowser = new DriverBrowser(BrowserType.Chrome);
		ConFeedPage= new ConsultationAndFeedbackPage(driverBrowser);
		RegisteredAndLoginPage login = new RegisteredAndLoginPage(driverBrowser);
		driverBrowser.open(OperateProperties.readValue("saasLoginURL"));
		driverBrowser.pause(1000);
		login.saasLogin();
	}
	/**
	 * 咨询与反馈的新建、查看、删除咨询的测试用例
	 */
	@Test(description=ConsuAndFeedB)
	public void testSaasConsultationAndFeedback(){
			pinfo(ReportStep.class,ConsuAndFeedB+caseStart);
			ConFeedPage.saasConsultationAndFeedback();
		    pinfo(ReportStep.class,ConsuAndFeedB+caseEnd);
	}
	@AfterClass
	public void down(){
		driverBrowser.quit();
	}
}
